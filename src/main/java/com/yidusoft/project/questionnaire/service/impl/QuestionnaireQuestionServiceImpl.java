package com.yidusoft.project.questionnaire.service.impl;

import com.alibaba.fastjson.JSON;
import com.yidusoft.core.AbstractService;
import com.yidusoft.core.Result;
import com.yidusoft.core.ResultGenerator;
import com.yidusoft.project.business.domain.ActiveParticipant;
import com.yidusoft.project.business.domain.LaunchActivities;
import com.yidusoft.project.business.service.ActiveParticipantService;
import com.yidusoft.project.business.service.LaunchActivitiesService;
import com.yidusoft.project.questionnaire.computing.QuestionnaireMethod;
import com.yidusoft.project.questionnaire.dao.DataAcquisitionMapper;
import com.yidusoft.project.questionnaire.dao.QuestionnaireAnswerMapper;
import com.yidusoft.project.questionnaire.dao.QuestionnaireMapper;
import com.yidusoft.project.questionnaire.dao.QuestionnaireQuestionMapper;
import com.yidusoft.project.questionnaire.domain.DataAcquisition;
import com.yidusoft.project.questionnaire.domain.Questionnaire;
import com.yidusoft.project.questionnaire.domain.QuestionnaireAnswer;
import com.yidusoft.project.questionnaire.domain.QuestionnaireQuestion;
import com.yidusoft.project.questionnaire.service.DataAcquisitionService;
import com.yidusoft.project.questionnaire.service.QuestionnaireQuestionService;
import com.yidusoft.project.transaction.dao.UserQuestionnairesMapper;
import com.yidusoft.project.transaction.domain.AccountInfo;
import com.yidusoft.project.transaction.domain.UserQuestionnaires;
import com.yidusoft.project.transaction.service.AccountInfoService;
import com.yidusoft.project.transaction.service.UserQuestionnairesService;
import com.yidusoft.utils.CodeHelper;
import com.yidusoft.utils.EntityReflex;
import com.yidusoft.utils.Security;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.yidusoft.configurer.ResourcesStatic.GAUGE;


/**
 * Created by CodeGenerator on 2017/10/11.
 */
@Service
@Transactional
public class QuestionnaireQuestionServiceImpl extends AbstractService<QuestionnaireQuestion> implements QuestionnaireQuestionService {
    @Resource
    private QuestionnaireQuestionMapper questionnaireQuestionMapper;
    @Resource
    private DataAcquisitionMapper dataAcquisitionMapper;
    @Resource
    private QuestionnaireAnswerMapper questionnaireAnswerMapper;
    @Resource
    private UserQuestionnairesService userQuestionnairesService;
    @Resource
    private DataAcquisitionService dataAcquisitionService;
    @Resource
    private QuestionnaireMapper questionnaireMapper;
    @Resource
    private ActiveParticipantService activeParticipantService;
    @Resource
    private UserQuestionnairesMapper userQuestionnairesMapper;
    @Resource
    private LaunchActivitiesService launchActivitiesService;
    @Resource
    private AccountInfoService accountInfoService;

    //分页条件查询问题
    @Override
    public List<QuestionnaireQuestion> questionListByPage(QuestionnaireQuestion questionnaireQuestion) {
        return questionnaireQuestionMapper.questionListByPage(questionnaireQuestion);
    }


    //问卷或量表  添加问题 查询没有添加的问题
    @Override
    public List<QuestionnaireQuestion> findQuestionBYid(QuestionnaireQuestion questionnaireQuestion) {
        Map<String, Object> map = new HashMap<>();
        map.put("ids", questionnaireQuestion.getId().split(","));
        map.put("questionCode", questionnaireQuestion.getQuestionCode());
        map.put("questionContent", questionnaireQuestion.getQuestionContent());
        map.put("questionType", questionnaireQuestion.getQuestionType());
        map.put("ascriptionType", questionnaireQuestion.getAscriptionType());
        return questionnaireQuestionMapper.findQuestionBYid(map);
    }

    // 查询问卷相关的问题
    @Override
    public List<QuestionnaireQuestion> findQuestionForQuestionnaire(String id) {
        return questionnaireQuestionMapper.findQuestionForQuestionnaire(id);
    }

    //提交问卷
    @Override
    public Result submitQuestionnaire(String param, String questionnaireId, String userId, String visitorTimes, String timeConsuming, String activityId, String userName) {
        try {
            List<Map<String, Object>> mapList = (List<Map<String, Object>>) JSON.parse(param);
            //问卷使用的id
            String dataAcquisitionId = UUID.randomUUID().toString();
            //总分
            int totalScore = getQuestionnaireTotalScore(mapList, dataAcquisitionId, userName,userId);

            //查询问卷的相关答案
             QuestionnaireAnswer questionnaireAnswer=new QuestionnaireAnswer();
             questionnaireAnswer.setUserId(userId);
             questionnaireAnswer.setAcquisitionId(dataAcquisitionId);
             List<QuestionnaireAnswer>  questionnaireAnswerList=questionnaireAnswerMapper.findAnswerForAcquisition(questionnaireAnswer);
            //判断问卷的类型
             Questionnaire questionnaire = questionnaireMapper.findQuestionnaireType(questionnaireId);
             String result = "";
             //获得方法名字
             String method=GAUGE.get(questionnaire.getGaugeName()).toString();
             //调QuestionnaireMethod 类里面method的方法 得到返回的分析结果
             Result methodResult= EntityReflex.getMethods(QuestionnaireMethod.class,method,questionnaireAnswerList);
             result= methodResult.getData().toString();
           //添加使用问卷
            DataAcquisition dataAcquisition = new DataAcquisition();
            dataAcquisition.setId(dataAcquisitionId);
            dataAcquisition.setDataResult(result);
            dataAcquisition.setTimeConsuming(timeConsuming);
            dataAcquisition.setDataQuestion(questionnaireId);
            dataAcquisition.setActivityId(activityId);
            dataAcquisition.setDataCode(CodeHelper.getCode("SY"));
            dataAcquisition.setTotalScore(totalScore);
            dataAcquisition.setUserId(userId);
            dataAcquisition.setDeleted(0);
            if (userName != null && userName != "") {
                dataAcquisition.setDataUser(userName);
            } else {
                dataAcquisition.setDataUser(Security.getUser().getUserName());
            }
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");//小写的mm表示的是分钟  
            java.util.Date date=sdf.parse(visitorTimes);
            dataAcquisition.setCreateTime(date);
            dataAcquisitionMapper.insert(dataAcquisition);

           /*    //添加填报时间
            ActiveParticipant activeParticipant=activeParticipantService.findById(userId);
            if(activeParticipant!=null){
                activeParticipant.setFillingTime(new Date());
                activeParticipantService.update(activeParticipant);
            }*/
            //活动扣除余额
            deleteDuction(activityId);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultGenerator.genFailResult(e.getMessage());
        }
        return ResultGenerator.genSuccessResult();
    }

    /**
     * 添加问题回答答案 得到总分
     * score 分数
     *
     */
    public  int  getQuestionnaireTotalScore( List<Map<String, Object>> mapList,String dataAcquisitionId,String userName,String userId) {
          int totalScore=0;
          for (Map map : mapList) {
            //分数
            String score = map.get("score").toString();
            //问题id
            String questionId = map.get("questionId").toString();
            //答案
            String answer = map.get("answer").toString();
            //答案类型
            String questionType = map.get("questionType").toString();

            //判断答案正误
            boolean flg=false;

            String[] correctAnswerArray;
            String[] answerArray;
            String[] scoreArray;
           //
            if (answer.contains("||")) {
                answerArray = answer.split("\\|\\|");
            } else {
                answerArray = new String[]{answer};
            }
            if (score.contains("||")) {
                scoreArray = score.split("\\|\\|");
            } else {
                scoreArray = new String[]{score};
            }
            //判断正确答案里面有没有值 如果有 则说明这个问题不是多选就是单选
            if (map.get("correctAnswer") != null) {
                if (map.get("correctAnswer").toString().contains("||")) {
                    correctAnswerArray = map.get("correctAnswer").toString().split("\\|\\|");
                } else {
                    correctAnswerArray = new String[]{map.get("correctAnswer").toString()};
                }
                //得到答案 是否正确
                for (int j = 0; j < answerArray.length; j++) {
                    int state=0;
                    for (int i = 0; i < correctAnswerArray.length; i++) {
                        if(state==0&&i==correctAnswerArray.length-1 && !correctAnswerArray[i].equals(answerArray[j])){
                           //有错误答案
                            flg=true;
                        }else {
                          if(correctAnswerArray[i].equals(answerArray[j])){
                              //有这个答案正确
                              state++;
                          }
                        }
                    }
                }
            }
            QuestionnaireAnswer questionnaireAnswer = new QuestionnaireAnswer();
            questionnaireAnswer.setId(UUID.randomUUID().toString());
            questionnaireAnswer.setAcquisitionId(dataAcquisitionId);
            questionnaireAnswer.setAnswer(answer);
            questionnaireAnswer.setQuestionId(questionId);
            questionnaireAnswer.setCreateTime(new Date());
            questionnaireAnswer.setCreator(userName);
            questionnaireAnswer.setDeleted(0);
            questionnaireAnswer.setUserId(userId);
            //判断  1  单选  2多选  3评分单选
            if (!questionType.equals("3")) {
                //答案正确
                if (!flg) {
                    int scores = 0;
                    for (int i = 0; i < answerArray.length; i++) {
                        totalScore += Integer.valueOf(scoreArray[i]);
                        scores += Integer.valueOf(scoreArray[i]);
                    }

                    questionnaireAnswer.setAnswerScore(scores);

                } else {
                    //答案错误
                    questionnaireAnswer.setAnswerScore(0);
                }

                questionnaireAnswerMapper.insert(questionnaireAnswer);

            } else {
                int scores = 0;
                for (int i = 0; i < answerArray.length; i++) {
                    totalScore += Integer.valueOf(scoreArray[i]);
                    scores += Integer.valueOf(scoreArray[i]);
                }
                questionnaireAnswer.setAnswerScore(scores);
                questionnaireAnswerMapper.insert(questionnaireAnswer);

            }

        }
     return  totalScore;
    }


    //活动提交问卷
    @Override
    public Result subQuestionnaire(String param, String questionnaireId, String userId, String visitorTimes, String timeConsuming, String activityId, String userName) {
        try {
            List<Map<String, Object>> mapList = (List<Map<String, Object>>) JSON.parse(param);
            //问卷使用的id
            String dataAcquisitionId = UUID.randomUUID().toString();
            //总分
            int totalScore =getQuestionnaireTotalScore(mapList, dataAcquisitionId, userName,userId);

            //查询问卷的相关答案
            QuestionnaireAnswer questionnaireAnswer=new QuestionnaireAnswer();
            questionnaireAnswer.setUserId(userId);
            questionnaireAnswer.setAcquisitionId(dataAcquisitionId);
            List<QuestionnaireAnswer>  questionnaireAnswerList=questionnaireAnswerMapper.findAnswerForAcquisition(questionnaireAnswer);
            //判断问卷的类型
            Questionnaire questionnaire = questionnaireMapper.findQuestionnaireType(questionnaireId);
            String result = "";
            //获得方法名字
            String method=GAUGE.get(questionnaire.getGaugeName()).toString();
            //调QuestionnaireMethod 类里面method的方法 得到返回的分析结果
            Result methodResult= EntityReflex.getMethods(QuestionnaireMethod.class,method,questionnaireAnswerList);
            result= methodResult.getData().toString();

            DataAcquisition dataAcquisition = new DataAcquisition();
            dataAcquisition.setId(dataAcquisitionId);
            //dataAcquisition.setActivityId();
            dataAcquisition.setDataResult(result);
            dataAcquisition.setTimeConsuming(timeConsuming);
            dataAcquisition.setDataQuestion(questionnaireId);
            dataAcquisition.setActivityId(activityId);
            dataAcquisition.setDataCode(CodeHelper.randomCode(8));
            dataAcquisition.setTotalScore(totalScore);
            dataAcquisition.setUserId(userId);
            dataAcquisition.setDeleted(0);
            dataAcquisition.setDataUser(userName);
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");//小写的mm表示的是分钟  
            java.util.Date date=sdf.parse(visitorTimes);
            dataAcquisition.setCreateTime(date);
            dataAcquisitionMapper.insert(dataAcquisition);

            //添加填报时间
            ActiveParticipant activeParticipant=activeParticipantService.findById(userId);
            if(activeParticipant!=null){
                activeParticipant.setFillingTime(new Date());
                activeParticipantService.update(activeParticipant);
            }
            //活动扣除余额
            deleteDuction(activityId);
            //记录到账户信息
        } catch (Exception e) {
            return ResultGenerator.genFailResult(e.getMessage());
        }
        return ResultGenerator.genSuccessResult();
    }

    /**
     * 判断是调研活动还是企业活动
     * @param activityId
     */
    public void deleteDuction(String activityId) {
        LaunchActivities launchActivities = launchActivitiesService.findById(activityId);
        if(launchActivities.getInitiatorType()!=1){//如果是企业活动
            updateUserQuestionnaires(launchActivities.getUserId());
        }
    }

    /**
     * 判断用户余额和是否是会员
     * 来决定是否扣除问卷
     * @param userId
     */
    public Result updateUserQuestionnaires(String userId){
        UserQuestionnaires userQuestionnaires= userQuestionnairesMapper.flgBalance(userId);
        if(userQuestionnaires.getMember()!=1){//如果不是会员
            if(userQuestionnaires.getQuestionnairesTotal()>0){//如果使用券大于0
                userQuestionnaires.setQuestionnairesTotal(userQuestionnaires.getQuestionnairesTotal()-1);
                userQuestionnaires.setQuestionnairesCumulativeTotal(userQuestionnaires.getQuestionnairesCumulativeTotal()+1);
            }else{//如果使用卷小于0
                return ResultGenerator.genFailResult("使用券不足");
            }
        }else{//如果是会员
            if(userQuestionnaires.getEndTime().getTime()-new Date().getTime()<=0){//如果会员到期
                userQuestionnaires.setQuestionnairesTotal(userQuestionnaires.getQuestionnairesTotal()-1);
                userQuestionnaires.setQuestionnairesCumulativeTotal(userQuestionnaires.getQuestionnairesCumulativeTotal()+1);
            }
        }
        userQuestionnairesService.update(userQuestionnaires);
        return addAccount(userId);
    }

    /**
     * 添加到账户信息
     * @param userId
     */
    public Result addAccount(String userId){
        UserQuestionnaires userQuestionnaires= userQuestionnairesMapper.flgBalance(userId);
        if(userQuestionnaires!=null && userQuestionnaires.getMember()!=1){
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
            String orderCode = formatter.format(new Date());
            orderCode=orderCode+""+(int)Math.random()*100000;
            AccountInfo accountInfo = new AccountInfo();
            accountInfo.setId(UUID.randomUUID().toString());
            accountInfo.setSerialNumber(orderCode);
            accountInfo.setAccountDate(new Date());
            accountInfo.setAccountRemarks("使用了问卷");
            accountInfo.setAccountTotal("1张");
            accountInfo.setBuyTotal("--");
            BigDecimal volumn = new BigDecimal("0");
            accountInfo.setCostMoney(volumn);
            accountInfo.setUserId(userId);
            UserQuestionnaires uq = userQuestionnairesService.findBy("userId", userId);
            if(uq!=null){
                accountInfo.setAccountSurplus(uq.getQuestionnairesTotal());
            }
            accountInfoService.save(accountInfo);
        }
        return ResultGenerator.genSuccessResult();
    }
}
