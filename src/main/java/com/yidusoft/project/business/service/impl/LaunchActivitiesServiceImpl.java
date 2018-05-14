package com.yidusoft.project.business.service.impl;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yidusoft.core.AbstractService;
import com.yidusoft.core.Result;
import com.yidusoft.core.ResultGenerator;
import com.yidusoft.project.activitis.service.ActivityService;
import com.yidusoft.project.business.dao.LaunchActivitiesMapper;
import com.yidusoft.project.business.domain.LaunchActivities;
import com.yidusoft.project.business.service.LaunchActivitiesService;
import com.yidusoft.project.questionnaire.domain.QuestionnaireAnswer;
import com.yidusoft.project.questionnaire.domain.QuestionnaireQuestion;
import com.yidusoft.project.questionnaire.service.DataAcquisitionService;
import com.yidusoft.project.questionnaire.service.QuestionnaireAnswerService;
import com.yidusoft.project.questionnaire.service.QuestionnaireQuestionService;
import com.yidusoft.utils.CodeHelper;
import com.yidusoft.utils.IdCard;
import com.yidusoft.utils.Security;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.net.UnknownHostException;
import java.util.*;


/**
 * Created by CodeGenerator on 2017/10/11.
 */
@Service
@Transactional
public class LaunchActivitiesServiceImpl extends AbstractService<LaunchActivities> implements LaunchActivitiesService {
    @Resource
    private LaunchActivitiesMapper launchActivitiesMapper;

    @Resource
    private LaunchActivitiesService launchActivitiesService;

    @Resource
    private ActivityService activityService;
    @Resource
    private QuestionnaireQuestionService  questionnaireQuestionService;
    @Resource
    private  QuestionnaireAnswerService questionnaireAnswerService;
    @Resource
    private DataAcquisitionService dataAcquisitionService;

    /**
     * 添加活动
     *
     * @param launchActivitiesJson
     * @return
     */
    @Override
    public Result addActivites(String launchActivitiesJson, HttpServletRequest request) throws UnknownHostException {
        LaunchActivities launchActivities = JSON.parseObject(launchActivitiesJson, LaunchActivities.class);
        launchActivities.setActivityCode(CodeHelper.getCode("LA"));
        launchActivities.setDeleted(0);
        launchActivities.setId(UUID.randomUUID().toString());
        launchActivities.setCreator(Security.getUser().getUserName());
        launchActivities.setUserId(Security.getUserId());
        launchActivities.setCreateTime(new Date());
        if (Security.getUser().getAccountType() == 0) {
            launchActivities.setInitiatorType(2);
            launchActivities.setActivityStatus(1);
            String uri = "/web/launchActivities/acdetail?id="+launchActivities.getId();
            activityService.startProcess(launchActivities.getId(),launchActivities.getActivityName()+"  活动申请",uri);
        } else {
            int port = request.getServerPort();//获取服务器端口
            String ip = request.getServerName();//获取服务端ip

            if(launchActivities.getInitiatorType()==null || launchActivities.getInitiatorType()!=3){
                launchActivities.setInitiatorType(1);
                launchActivities.setUestionnaireUri("http://" + ip + "/web/activities/fillingPage");

                launchActivities.setActivityPorn(CodeHelper.randomCode(8));
            }else{
                String uri = "/web/launchActivities/acdetail?id="+launchActivities.getId();
                activityService.startProcess(launchActivities.getId(),launchActivities.getActivityName()+"  活动申请",uri);
                launchActivities.setActivityStatus(1);
            }
        }
        launchActivitiesService.save(launchActivities);

        return ResultGenerator.genSuccessResult();
    }

    /**
     * 获取用户的所有活动
     *
     * @param launchActivities
     * @return
     */
    @Override
    public List<LaunchActivities> getActivityAll(LaunchActivities launchActivities) {
        return launchActivitiesMapper.getActivityAll(launchActivities);
    }

    @Override
    public LaunchActivities getIdByPorn(LaunchActivities launchActivities) {
        return launchActivitiesMapper.getIdByPorn(launchActivities);
    }

    @Override
    public LaunchActivities getActivityById(String id) {
        return launchActivitiesMapper.getActivityById(id);
    }

    /***
     * 查询活动问卷关于所有参与活动的人对应问题的答案，需要进行每题每项答案统计
     *   《有相关特殊问卷无法通用此方法》
     * @param activityId
     * @return
     */
    @Override
    public List<Map<String,Object>> getQuestionForActivityQuestionnaire(String activityId) {
         LaunchActivities launchActivities= launchActivitiesService.findById(activityId);
         //查询问卷所有的问题
         List<QuestionnaireQuestion> questionnaireQuestionList=questionnaireQuestionService.findQuestionForQuestionnaire(launchActivities.getUestionnaireId());
         //通过活动查询所有的填写答案
         List<QuestionnaireAnswer> questionnaireAnswerList=questionnaireAnswerService.findAnswerForActivity(activityId);
        //参与活动的总人数
         Integer userTotal= dataAcquisitionService.findCountByActivityId(activityId);
         //结果集
        List<Map<String,Object>> mapList=new ArrayList<>();
         //题目  本题平均分 选项  小计  比例
        for(QuestionnaireQuestion questionnaireQuestion :questionnaireQuestionList){
            Map<String,Object> map=new HashMap<>();
            //问题内容
            map.put("questionContent",questionnaireQuestion.getQuestionContent());
            map.put("optionAnswer",questionnaireQuestion.getOptionAnswer());
            map.put("optionScore",questionnaireQuestion.getOptionScore());

            //选项  1||2||3
            String[] optionAnswers=questionnaireQuestion.getOptionAnswer().split("\\|\\|");;
            //当前问题的总分
            int optionAnswerTotalScore=0;
            //记录每个问题  每个选项的总分
            List<Integer> optionScoreList=new ArrayList<>();
            //记录每个问题  每个选项的人数比例
            List<String> userProportion=new ArrayList<>();
            //每个选项的总分
            for(int i=0;i<optionAnswers.length;i++){
                //单个选项的总分
                int optionTotalScore=0;
                //单个选项的总人数
                int  userOptionTotal=0;
                //筛选对应的题目   选项对应的答案
                for(QuestionnaireAnswer questionnaireAnswer:questionnaireAnswerList){

                     if(optionAnswers[i].trim().equals(questionnaireAnswer.getAnswer().trim())
                           &&questionnaireAnswer.getQuestionId().equals(questionnaireQuestion.getId())){
                         //这个问题 这个选项的分数总计
                         optionTotalScore+=questionnaireAnswer.getAnswerScore();
                         //这个问题 这个选项的总人数
                          userOptionTotal++;
                     }
                }
                optionScoreList.add(optionTotalScore);
                Double proportion=getDouble(Double.valueOf(userOptionTotal)/Double.valueOf(userTotal),4)*100;
                userProportion.add(proportion+"%");
                //计算问题的总分
                optionAnswerTotalScore+=optionTotalScore;
           }
            //题目的平均分
            map.put("avgScore",getDouble(Double.valueOf(optionAnswerTotalScore)/Double.valueOf(userTotal),2));
            //题目的选项总分
            map.put("optionScore",optionScoreList);
           //题目的选项比例
            map.put("userProportion",userProportion);
            mapList.add(map);
        }

         return mapList;
    }

    /**
     * 查询活动的进度，还有相关统计信息
     * @param activityId
     * @return
     */
    @Override
    public Map<String, Object> getActivitySchedule(String activityId) {
        Map<String, Object> map=launchActivitiesMapper.getActivitySchedule(activityId);
        if(map.size()>0){
            if(map.get("time_second_max")==null){
                map.put("time_second_max",0);
            }
            if(map.get("time_second_min")==null){
                map.put("time_second_min",0);
            }
            if(map.get("time_second_avg")==null){
                map.put("time_second_avg",0);
            }

        }
        return map;
    }
    /**
     * 查询参与活动所有人相关问卷结论，性别，年龄
     * @param activityId
     * @param type
     * @return
     */
    @Override
    public Map<String, Object> getActivityNumberPeopleForResult(String activityId,int type) {

        Map<String,Object> maps=new HashMap<>();
       List<Map<String, Object>> mapList= launchActivitiesMapper.getActivityNumberPeopleForResult(activityId);
       //标准分的集
        List<Double> standardScoreList=new ArrayList<>();
        //年龄的集
        List<Integer> ageList=new ArrayList<>(); ;
        //男的人数
        int  man=0;
        //女的人数
        int  woman=0;
        //标准分和
        double totalScore=0;
        //定义的存储 无  轻度 中度 重度 四种类型的焦虑人数
        List<Integer> typeList=new ArrayList(){{add(0); add(0);add(0);add(0);}};
        for(Map<String, Object> map:mapList){
           if(map.get("data_result")!=null &&
               map.get("sex")!=null && map.get("age")!=null){
               //获取结果 ，结果是json的格式
               String  dataResult=map.get("data_result").toString();
               //获取性别  1 男 0 女
               String  sex=map.get("sex").toString();
               //通过身份证得到年龄
               int age=Integer.valueOf(map.get("age").toString());
               //标准分  standardScore 结果参考QuestionnireMethod类里面，方法对应的问卷，对应的结论
               Double standardScore =Double.valueOf(((Map)JSONObject.parse(dataResult)).get("standardScore").toString());
               standardScoreList.add(standardScore);

               totalScore+=standardScore;
               typeList=getUserNumFor_34(typeList,standardScore);
               switch (type){
                   case  0:
                     if("1".equals(sex)){
                         man++;
                     }else {
                         woman++;
                     }
                       ageList.add(age);
                        break;
                   case  1:
                      if(standardScore<50){
                          if("1".equals(sex)){
                              man++;
                          }else {
                              woman++;
                          }
                          ageList.add(age);
                      }
                       break;
                   case  2:
                       if(standardScore<59 && standardScore>=50){
                           if("1".equals(sex)){
                               man++;
                           }else {
                               woman++;
                           }
                           ageList.add(age);
                       }
                       break;
                   case  3:
                       if(standardScore<69 && standardScore>=60){
                           if("1".equals(sex)){
                               man++;
                           }else {
                               woman++;
                           }
                           ageList.add(age);
                       }
                       break;
                   case  4:
                       if(standardScore>=70){
                           if("1".equals(sex)){
                               man++;
                           }else {
                               woman++;
                           }
                           ageList.add(age);
                       }
                       break;
               }
           }
       }
        double totalScoreMax=0;
        double totalScoreMin=0;
        double totalScoreAvg=0;
        if(standardScoreList.size()>0) {
            // 最高标准分
             totalScoreMax = Collections.max(standardScoreList);
            //最低标准分
             totalScoreMin = Collections.min(standardScoreList);
            //平均标准分
             totalScoreAvg = getDouble(totalScore / mapList.size(),2);
        }
        maps.put("totalScoreMax",totalScoreMax);
        maps.put("totalScoreMin",totalScoreMin);
        maps.put("totalScoreAvg",totalScoreAvg);
        //男女比例环形
        maps.put("man",man);
        maps.put("woman",woman);
        //面积统计
        maps.put("ageList",ageList);
        //气泡图形
        maps.put("typeList",typeList);
        maps.put("typeListMax",Collections.max(typeList));
        int  total= typeList.stream().mapToInt(Integer::intValue).sum();
        double y1=getDouble(Double.valueOf(typeList.get(0))/total,4);
        double y2=getDouble(Double.valueOf(typeList.get(1))/total,4);
        double y3=getDouble(Double.valueOf(typeList.get(2))/total,4);
        double y4=getDouble(Double.valueOf(typeList.get(3))/total,4);
        List<Double> typeListY =new ArrayList(){{add(y1);add(y2);add(y3);add(y4);}};
        maps.put("typeListY",typeListY);
        return maps;
    }

    @Override
    public Map<String, Object> getActivityNumberPeopleForResult_30(String activityId,int type) {

        Map<String,Object> maps=new HashMap<>();
        List<Map<String, Object>> mapList= launchActivitiesMapper.getActivityNumberPeopleForResult(activityId);
        //抑郁严重指数的集
        List<Double> standardScoreList=new ArrayList<>();
        //年龄的集
        List<Integer> ageList=new ArrayList<>(); ;
        //男的人数
        int  man=0;
        //女的人数
        int  woman=0;
        //标准分和
        double totalScore=0;
        //定义的存储 无  轻度 中度 重度 四种类型的焦虑人数
        List<Integer> typeList=new ArrayList(){{add(0); add(0);add(0);add(0);}};
        for(Map<String, Object> map:mapList){
            if(map.get("data_result")!=null &&
                    map.get("sex")!=null && map.get("age")!=null){
                //获取结果 ，结果是json的格式
                String  dataResult=map.get("data_result").toString();
                //获取性别  1 男 0 女
                String  sex=map.get("sex").toString();
                //通过身份证得到年龄
                int age=Integer.valueOf(map.get("age").toString());
                //抑郁指数  severityIndex 结果参考QuestionnireMethod类里面，方法对应的问卷，对应的结论
                Double standardScore =Double.valueOf(((Map)JSONObject.parse(dataResult)).get("severityIndex").toString());
                standardScoreList.add(standardScore);

                totalScore+=standardScore;
                typeList=getUserNumFor_30(typeList,standardScore);
                switch (type){
                    case  0:
                        if("1".equals(sex)){
                            man++;
                        }else {
                            woman++;
                        }
                        ageList.add(age);
                        break;
                    case  1:
                        if(standardScore<0.5){
                            if("1".equals(sex)){
                                man++;
                            }else {
                                woman++;
                            }
                            ageList.add(age);
                        }
                        break;
                    case  2:
                        if(standardScore<=0.59 && standardScore>=0.5){
                            if("1".equals(sex)){
                                man++;
                            }else {
                                woman++;
                            }
                            ageList.add(age);
                        }
                        break;
                    case  3:
                        if(standardScore<=0.69 && standardScore>=0.6){
                            if("1".equals(sex)){
                                man++;
                            }else {
                                woman++;
                            }
                            ageList.add(age);
                        }
                        break;
                    case  4:
                        if(standardScore>=0.7){
                            if("1".equals(sex)){
                                man++;
                            }else {
                                woman++;
                            }
                            ageList.add(age);
                        }
                        break;
                }
            }
        }
        double totalScoreMax=0;
        double totalScoreMin=0;
        double totalScoreAvg=0;
        if(standardScoreList.size()>0){
            // 最高抑郁指数
             totalScoreMax=Collections.max(standardScoreList);
            //最低抑郁指数
             totalScoreMin=Collections.min(standardScoreList);
            //平均抑郁指数
             totalScoreAvg=getDouble(totalScore/mapList.size(),2);
        }

        maps.put("totalScoreMax",totalScoreMax);
        maps.put("totalScoreMin",totalScoreMin);
        maps.put("totalScoreAvg",totalScoreAvg);
        //男女比例环形
        maps.put("man",man);
        maps.put("woman",woman);
        //面积统计
        maps.put("ageList",ageList);
        //气泡图形
        maps.put("typeList",typeList);
        maps.put("typeListMax",Collections.max(typeList));
        int  total= typeList.stream().mapToInt(Integer::intValue).sum();
        double y1=getDouble(Double.valueOf(typeList.get(0))/total,4);
        double y2=getDouble(Double.valueOf(typeList.get(1))/total,4);
        double y3=getDouble(Double.valueOf(typeList.get(2))/total,4);
        double y4=getDouble(Double.valueOf(typeList.get(3))/total,4);
        List<Double> typeListY =new ArrayList(){{add(y1);add(y2);add(y3);add(y4);}};
        maps.put("typeListY",typeListY);
        return maps;
    }


    /**
     * 查询所有的活动
     *活动名称、类型、状态（颜色区分-未完成、已完成、进行中）、区域、发起人、进度
     * @param map
     * @return
     */
    @Override
    public List<Map<String, Object>> getLaunchActivityAll(Map map) {
        return launchActivitiesMapper.getLaunchActivityAll(map);
    }

    /**
     *
     */
    private  List<Integer> getUserNumFor_34( List<Integer> list,Double score ){
        if(score<50){
            list.set(0,(list.get(0)+1));
        }else if(score<59 && score>=50){
            list.set(1,(list.get(1)+1));
        }else if(score<69 && score>=60){
            list.set(2,(list.get(2)+1));
        }else if(score>=70){
            list.set(3,(list.get(3)+1));
        }

        return  list;
    }

    private  List<Integer> getUserNumFor_30( List<Integer> list,Double score ){
        if(score<0.5){
            list.set(0,(list.get(0)+1));
        }else if(score<=0.59 && score>=0.5){
            list.set(1,(list.get(1)+1));
        }else if(score<=0.69 && score>=0.6){
            list.set(2,(list.get(2)+1));
        }else if(score>= 0.7){
            list.set(3,(list.get(3)+1));
        }

        return  list;
    }

    /**
     * 将list转换成||分割的字符串
     * @param list
     * @return
     */
    private  String getListToString(List<Object> list){
        StringBuilder  result=new  StringBuilder();
          for (int i=0;i<list.size();i++){
              if(i==list.size()-1){
                  result.append(list.get(i).toString());
              }else {
                  result.append(list.get(i).toString()+"||");
              }
          }

        return result.toString();
    }

    /**
     * 保留两位小数
     * @param value
     * @return
     */
    private  double getDouble(Double  value,int position ){
       if(!value.isNaN() && value!=null){
           BigDecimal b = new BigDecimal(value);
           double df = b.setScale(position, BigDecimal.ROUND_HALF_UP).doubleValue();
           return  df;
       }

        return  0;
    }

}
