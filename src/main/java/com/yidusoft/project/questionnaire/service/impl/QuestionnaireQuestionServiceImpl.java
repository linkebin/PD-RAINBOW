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
            int totalScore = 0;
            for (Map map : mapList) {
                //分数
                String score = map.get("score").toString();
                //问题id
                String questionId = map.get("questionId").toString();
                //答案
                String answer = map.get("answer").toString();

                //正确答案
                //String correctAnswer=map.get("correctAnswer").toString();
                //答案类型
                String questionType = map.get("questionType").toString();

                //   判断正确的答案  与填写答案
                int state = 0;
                String[] correctAnswerArray;
                String[] answerArray;
                String[] scoreArray;

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
                if (map.get("correctAnswer") != null) {
                    if (map.get("correctAnswer").toString().contains("||")) {
                        correctAnswerArray = map.get("correctAnswer").toString().split("\\|\\|");
                    } else {
                        correctAnswerArray = new String[]{map.get("correctAnswer").toString()};
                    }
                    for (int j = 0; j < answerArray.length; j++) {
                        for (int i = 0; i < correctAnswerArray.length; i++) {
                            if (correctAnswerArray[i].equals(answerArray[j])) {
                                state += 1;
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
                if (userName != null && userName != "") {
                    questionnaireAnswer.setCreator(userName);
                } else {
                    if(Security.getUser().getUserName()!=null){
                        questionnaireAnswer.setCreator(Security.getUser().getUserName());
                    }
                }
                questionnaireAnswer.setDeleted(0);
                questionnaireAnswer.setUserId(userId);
                //判断  1  单选  2多选  3评分单选 4 收集单选  5 收集多选
                if (!questionType.equals("3") && !questionType.equals("5")) {
                    //答案正确
                    if (answerArray.length == state) {
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
            QuestionnaireAnswer questionnaireAnswer=new QuestionnaireAnswer();
            questionnaireAnswer.setUserId(userId);
            questionnaireAnswer.setAcquisitionId(dataAcquisitionId);
            List<QuestionnaireAnswer>  questionnaireAnswerList=questionnaireAnswerMapper.findAnswerForAcquisition(questionnaireAnswer);
            //判断问卷的类型
             Questionnaire questionnaire = questionnaireMapper.findQuestionnaireType(questionnaireId);
             String result = "";
             //获得方法名字
             String method=GAUGE.get(questionnaire.getGaugeName()).toString();
             Result methodResult= EntityReflex.getMethods(QuestionnaireMethod.class,method,questionnaireAnswerList);
             result= methodResult.getData().toString();
            //  result = dataAcquisitionService.gauge_34(dataAcquisitionId, userId, "anxious");
            /*if ("症状自评量表-SCL90".equals(questionnaire.getGaugeName())) {
                result = dataAcquisitionService.symptomConclusion(dataAcquisitionId, userId);

            } else if ("抑郁自评量表(SDS)".equals(questionnaire.getGaugeName())) {
                result = dataAcquisitionService.depressedOrAnxiousConclusion(dataAcquisitionId, userId, "depressed");
            } else {
                result = dataAcquisitionService.depressedOrAnxiousConclusion(dataAcquisitionId, userId, "anxious");
                //焦虑自评量表(SAS)
            }*/

            DataAcquisition dataAcquisition = new DataAcquisition();
            dataAcquisition.setId(dataAcquisitionId);
            //dataAcquisition.setActivityId();
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

            //添加填报时间
            ActiveParticipant activeParticipant=activeParticipantService.findById(userId);
            if(activeParticipant!=null){
                activeParticipant.setFillingTime(new Date());
                activeParticipantService.update(activeParticipant);
            }
            //扣除余额
            userQuestionnairesService.deduction();
        } catch (Exception e) {
            return ResultGenerator.genFailResult(e.getMessage());
        }
        return ResultGenerator.genSuccessResult();
    }

    //活动提交问卷
    @Override
    public Result subQuestionnaire(String param, String questionnaireId, String userId, String visitorTimes, String timeConsuming, String activityId, String userName) {
        try {
            List<Map<String, Object>> mapList = (List<Map<String, Object>>) JSON.parse(param);
            //问卷使用的id
            String dataAcquisitionId = UUID.randomUUID().toString();
            //总分
            int totalScore = 0;
            for (Map map : mapList) {
                //分数
                String score = map.get("score").toString();
                //问题id
                String questionId = map.get("questionId").toString();
                //答案
                String answer = map.get("answer").toString();

                //正确答案
                //String correctAnswer=map.get("correctAnswer").toString();
                //答案类型
                String questionType = map.get("questionType").toString();

                //   判断正确的答案  与填写答案
                int state = 0;
                String[] correctAnswerArray;
                String[] answerArray;
                String[] scoreArray;

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
                if (map.get("correctAnswer") != null) {
                    if (map.get("correctAnswer").toString().contains("||")) {
                        correctAnswerArray = map.get("correctAnswer").toString().split("\\|\\|");
                    } else {
                        correctAnswerArray = new String[]{map.get("correctAnswer").toString()};
                    }
                    for (int j = 0; j < answerArray.length; j++) {
                        for (int i = 0; i < correctAnswerArray.length; i++) {
                            if (correctAnswerArray[i].equals(answerArray[j])) {
                                state += 1;
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
                    if (answerArray.length == state) {
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
            //判断问卷的类型
            Questionnaire questionnaire = questionnaireMapper.findQuestionnaireType(questionnaireId);
            String result = "";
            EntityReflex.getMethod(dataAcquisitionService.getClass(),"gauge_34",dataAcquisitionId,userId,"anxious");
            if ("症状自评量表-SCL90".equals(questionnaire.getQuestionnaireTypeName())) {
                result = dataAcquisitionService.symptomConclusion(dataAcquisitionId, userId);

            } else if ("抑郁自评量表(SDS)".equals(questionnaire.getQuestionnaireTypeName())) {
                result = dataAcquisitionService.depressedOrAnxiousConclusion(dataAcquisitionId, userId, "depressed");
            } else {
                //焦虑自评量表(SAS)
                result = dataAcquisitionService.depressedOrAnxiousConclusion(dataAcquisitionId, userId, "anxious");

            }
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

    //消费扣除活动发起人的使用卷
    public void deleteDuction(String activityId) {
        LaunchActivities launchActivities = launchActivitiesService.findById(activityId);
        if(launchActivities.getInitiatorType()!=2){
            updateUserQuestionnaires(launchActivities.getUserId());
        }
    }

    public void updateUserQuestionnaires(String userId){
        UserQuestionnaires userQuestionnaires= userQuestionnairesMapper.flgBalance(userId);
        if(userQuestionnaires.getMember()!=1){
            userQuestionnaires.setQuestionnairesTotal(userQuestionnaires.getQuestionnairesTotal()-1);
            userQuestionnaires.setQuestionnairesCumulativeTotal(userQuestionnaires.getQuestionnairesCumulativeTotal()+1);
        }else{
            if(userQuestionnaires.getEndTime().getTime()-new Date().getTime()<=0){
                userQuestionnaires.setQuestionnairesTotal(userQuestionnaires.getQuestionnairesTotal()-1);
                userQuestionnaires.setQuestionnairesCumulativeTotal(userQuestionnaires.getQuestionnairesCumulativeTotal()+1);
            }
        }
        userQuestionnairesService.update(userQuestionnaires);
        addAccount(userId);
    }

    public void addAccount(String userId){
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
            accountInfo.setCostMoney("--");
            accountInfo.setUserId(userId);
            UserQuestionnaires uq = userQuestionnairesService.findBy("userId", userId);
            if(uq!=null){
                accountInfo.setAccountSurplus(uq.getQuestionnairesTotal());
            }
            accountInfoService.save(accountInfo);
        }

    }
}
