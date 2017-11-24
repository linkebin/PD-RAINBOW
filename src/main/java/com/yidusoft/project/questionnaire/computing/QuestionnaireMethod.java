package com.yidusoft.project.questionnaire.computing;

import com.alibaba.fastjson.JSON;
import com.yidusoft.core.Result;
import com.yidusoft.core.ResultGenerator;
import com.yidusoft.project.questionnaire.domain.QuestionnaireAnswer;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zcb on 2017/11/20.
 */


public class QuestionnaireMethod {

        // 症状自评量表-SCL90  的规则结论
    public Result gauge_4(ArrayList<QuestionnaireAnswer> questionnaireAnswerList) throws  Exception{
        //1.躯体化
       Map<String,Object> factor1=getFactorScore("躯体化分","1,4,12,27,40,42,48,49,52,53,56,58",questionnaireAnswerList);
        //2.强迫症状
        Map<String,Object> factor2=getFactorScore("强迫症状","3,9,10,28,38,45,46,51,55,65",questionnaireAnswerList);
        //3.人际关系敏感
        Map<String,Object> factor3=getFactorScore("人际敏感","6,21,34,36,37,41,61,69,73",questionnaireAnswerList);
        // 4.抑郁
        Map<String,Object>factor4=getFactorScore("抑郁症状","5,14,15,20,22,26,29,30,31,32,54,71,79",questionnaireAnswerList);
        // 5.焦虑
        Map<String,Object>factor5=getFactorScore("焦虑症状","2,17,23,33,39,57,72,78,80,86",questionnaireAnswerList);
        //6.敌对
        Map<String,Object>factor6=getFactorScore("敌对症状","11,24,63,67,74,81",questionnaireAnswerList);
        //7.恐怖
        Map<String,Object>factor7=getFactorScore("恐怖症状","13,25,47,50,70,75,82",questionnaireAnswerList);
        //8.偏执
        Map<String,Object>factor8=getFactorScore("偏执症状","8,18,43,68,76,83",questionnaireAnswerList);
        //9.精神病性
        Map<String,Object>factor9=getFactorScore("精神症状","7,16,35,62,77,84,85,87,88,90",questionnaireAnswerList);
        //10.其他
        Map<String,Object>factor10=getFactorScore("其他","19,44,59,60,64,66,89",questionnaireAnswerList);
        //总分
        double totalScore=0;
        //阳性项目数
        double  positiveTotal=0;
        //阳性总分
        double positiveTotalScore=0;
        //阴性项目数
        double negativeTotal=0;
        //阴性总分
        double negativeTotalScore=0;

        for(int i=0;i<questionnaireAnswerList.size();i++){
            totalScore=totalScore+questionnaireAnswerList.get(i).getAnswerScore();
            //阳性
            if(questionnaireAnswerList.get(i).getAnswerScore()>=2){
                positiveTotal++;
                positiveTotalScore=positiveTotalScore+questionnaireAnswerList.get(i).getAnswerScore();
            }
            //阴性
            if(questionnaireAnswerList.get(i).getAnswerScore()==1){
                negativeTotal++;
                negativeTotalScore=negativeTotalScore+questionnaireAnswerList.get(i).getAnswerScore();
            }
        }
        //阳性症状均分
        if(positiveTotalScore>0){
            double positiveMean=decimal(positiveTotalScore/positiveTotal);
        }
        //阴性症状均分
        if(negativeTotalScore>0){
            double negativeMean=decimal(negativeTotalScore/negativeTotal);
        }

        //标准分
        double standard=totalScore*1.25;
        int  standardPoints=(int)standard;
        Map<String,Object>  score=new HashMap<>();
        score.put("总分",totalScore);
        score.put("总均分",decimal(totalScore/90));
        score.put("标准分",standardPoints);
        score.put("躯体化分",factor1.get("躯体化分"));
        score.put("强迫症状",factor2.get("强迫症状"));
        score.put("人际敏感",factor3.get("人际敏感"));
        score.put("抑郁症状",factor4.get("抑郁症状"));
        score.put("焦虑症状",factor5.get("焦虑症状"));
        score.put("敌对症状",factor6.get("敌对症状"));
        score.put("恐怖症状",factor7.get("恐怖症状"));
        score.put("偏执症状",factor8.get("偏执症状"));
        score.put("精神症状",factor9.get("精神症状"));
        Map<String,Object>  average=new HashMap<>();
        average.put("躯体化分",decimal(Double.valueOf(factor1.get("躯体化分").toString())/12));
        average.put("强迫症状",decimal(Double.valueOf(factor2.get("强迫症状").toString())/10));
        average.put("人际敏感",decimal(Double.valueOf(factor3.get("人际敏感").toString())/9));
        average.put("抑郁症状",decimal(Double.valueOf(factor4.get("抑郁症状").toString())/13));
        average.put("焦虑症状",decimal(Double.valueOf(factor5.get("焦虑症状").toString())/10));
        average.put("敌对症状",decimal(Double.valueOf(factor6.get("敌对症状").toString())/6));
        average.put("恐怖症状",decimal(Double.valueOf(factor7.get("恐怖症状").toString())/7));
        average.put("偏执症状",decimal(Double.valueOf(factor8.get("偏执症状").toString())/6));
        average.put("精神症状",decimal(Double.valueOf(factor9.get("精神症状").toString())/10));
        Map<String,Object>  maps=new HashMap<>();
        maps.put("score",score);
        maps.put("average",average);
        return ResultGenerator.genSuccessResult(JSON.toJSONString(maps));
    }

    //焦虑
    public Result gauge_34(ArrayList<QuestionnaireAnswer>  questionnaireAnswerList)throws Exception {
        //总分
               Map<String,Object> map =getTotalScore("totalScore",questionnaireAnswerList);
                double standardScore=Double.valueOf(map.get("totalScore").toString())*1.25;
                map.put("standardScore",standardScore);

        return  ResultGenerator.genSuccessResult(JSON.toJSONString(map));
    }


    //抑郁 规则结论
    public Result gauge_30(ArrayList<QuestionnaireAnswer>  questionnaireAnswerList)throws Exception {
           //总分
          Map<String,Object> map =getTotalScore("totalScore",questionnaireAnswerList);
                double severityIndex=decimal(Double.valueOf(map.get("totalScore").toString())/80);
                map.put("severityIndex",severityIndex);


        return  ResultGenerator.genSuccessResult(JSON.toJSONString(map));
    }


    //贝克抑郁自评量表gauge_32(BDI)
    public Result gauge_32(ArrayList<QuestionnaireAnswer>  questionnaireAnswerList)throws Exception {
        //总分
        Map<String,Object> map =getTotalScore("totalScore",questionnaireAnswerList);

        return  ResultGenerator.genSuccessResult(JSON.toJSONString(map));
    }
   //流调用抑郁自评量表(CES-D)
    public Result gauge_31(ArrayList<QuestionnaireAnswer>  questionnaireAnswerList)throws Exception {
       //总分
       Map<String,Object> map =getTotalScore("totalScore",questionnaireAnswerList);

       return  ResultGenerator.genSuccessResult(JSON.toJSONString(map));
   }

   // 儿少心理健康量表(MHS-CA)
    public Result gauge_5(ArrayList<QuestionnaireAnswer>  questionnaireAnswerList)throws Exception {
        //认知维度 认知维度=感知觉+注意力+记忆力+智力+学习与工作
        Map<String,Object> cognitiveDimensionMap  =getFactorScore("认知维度","1,2,3,4,5",questionnaireAnswerList);
        //思维维度  思维维度=思维过程+思维内容+思维自主性+语言表达+语言理解
        Map<String,Object> thinkingDimensionMap  =getFactorScore("思维维度","6,7,8,9,10",questionnaireAnswerList);
        //情绪维度 情绪维度=焦虑体验+愉快体验+情绪反应
        Map<String,Object> emotionDimensionMap  =getFactorScore("情绪维度","11,12,13",questionnaireAnswerList);
        //意志行为 意志行为=行为+活动+兴趣+人际交往+健康关注
        Map<String,Object> volitionalBehaviorMap  =getFactorScore("意志行为","14,15,16,17,18",questionnaireAnswerList);
        //个性特征 个性特征=自信与自尊+安全与信任+责任感+活泼性+仁慈心+需要满足
        Map<String,Object> personalityCharacteristicsMap  =getFactorScore("个性特征","14,15,16,17,18",questionnaireAnswerList);
        Map<String,Object> map=new HashMap<>();
        map.put("cognitiveDimension",cognitiveDimensionMap.get("认知维度"));
        map.put("thinkingDimension",thinkingDimensionMap.get("思维维度"));
        map.put("emotionDimension",emotionDimensionMap.get("情绪维度"));
        map.put("volitionalBehavior",volitionalBehaviorMap.get("意志行为"));
        map.put("personalityCharacteristics",personalityCharacteristicsMap.get("个性特征"));
        Map<String,Object>score = getTotalScore("totalScore",questionnaireAnswerList);
        map.put("totalScore",score.get("totalScore"));
       return  ResultGenerator.genSuccessResult(JSON.toJSONString(map));
   }

   //简易应对方式问卷
    public Result gauge_17(ArrayList<QuestionnaireAnswer>  questionnaireAnswerList)throws Exception {
      //积极应对因子总分
       Map<String,Object> positiveCopingFactorMap=getFactorScore("积极应对","1,2,3,4,5,6,7,8,9,10,11,12",questionnaireAnswerList);
       // 积极应对标准分
       double positiveCopingStandard=(Double.valueOf(positiveCopingFactorMap.get("积极应对").toString())/12-1.78)/0.52;
       //消极应对因子总分
       Map<String,Object> negativeCopingFactorMap=getFactorScore("消极应对","13,14,15,16,17,18,19,20",questionnaireAnswerList);
       // 消极应对标准分
       double  negativeCopingStandard=(Double.valueOf(negativeCopingFactorMap.get("消极应对").toString())/8-1.59)/0.66;
       //应对倾向=积极应对标准分—消极应对标准分
       double copingTendency=positiveCopingStandard-negativeCopingStandard;
       Map<String,Object> map=new HashMap<>();
       map.put("positiveCopingStandard",decimalTo4(positiveCopingStandard));
       map.put("negativeCopingFactorMap",decimalTo4(negativeCopingStandard));
       map.put("copingTendency",decimalTo4(copingTendency));
       Map<String,Object>score = getTotalScore("totalScore",questionnaireAnswerList);
       map.put("totalScore",score.get("totalScore"));
       return  ResultGenerator.genSuccessResult(JSON.toJSONString(map));
   }
   //中学生应激源量表(SSMSS)
    public Result gauge_14(ArrayList<QuestionnaireAnswer>  questionnaireAnswerList)throws Exception {
        //1.学习压力因子 包括1、7、13、27、31共5个条目
        Map<String,Object> learningStressFactor=getFactorScore("学习压力","1,7,13,27,31",questionnaireAnswerList);
        //学习压力因子均分
        double learningStressFactorAverage=Double.valueOf(learningStressFactor.get("学习压力").toString())/5;

        //2.教师压力因子 包括2、8、14、18、23、28、32共7个条目
        Map<String,Object> teacherStressFactor=getFactorScore("教师压力","2,8,14,18,23,28,32",questionnaireAnswerList);
        //教师压力因子均分
        double teacherStressFactorAverage=Double.valueOf(teacherStressFactor.get("教师压力").toString())/7;

        //3.家庭环境压力因子：包括3、9、33、36、38共5个条目，
        Map<String,Object> familyStressFactor=getFactorScore("家庭环境压力","3,9,33,36,38",questionnaireAnswerList);
       //家庭环境压力因子均分
        double familyStressFactorAverage=Double.valueOf(familyStressFactor.get("家庭环境压力").toString())/5;

        //4.父母教养方式压力因子：包括15、19、24、29共4个条目
        Map<String,Object> parentStressFactor=getFactorScore("父母教养方式压力","15,19,24,29",questionnaireAnswerList);
       //父母教养方式压力均分
        double parentStressFactorAverage=Double.valueOf(parentStressFactor.get("父母教养方式压力").toString())/4;

        //5、同学朋友压力因子：包括4、10、16、20、25、34、37共7个条目
        Map<String,Object> classmateStressFactor=getFactorScore("同学朋友压力","4,10,16,20,25,34,37",questionnaireAnswerList);
      //同学朋友压力均分
        double classmateStressFactorAverage=Double.valueOf(classmateStressFactor.get("同学朋友压力").toString())/7;

        //6、社会文化压力因子：包括5、11、17、21、26、39 共6个条目
        Map<String,Object> societyStressFactor=getFactorScore("社会文化压力","5,11,17,21,26,39",questionnaireAnswerList);
      //社会文化压力因子均分
        double societyStressFactorAverage=Double.valueOf(societyStressFactor.get("社会文化压力").toString())/6;


        //7、自我身心压力因子：包括6、12、22、30、35共5个条目
        Map<String,Object> selfStressFactor=getFactorScore("自我身心压力","6,12,22,30,35",questionnaireAnswerList);
       //自我身心压力均分
        double selfStressFactorAverage=Double.valueOf(selfStressFactor.get("自我身心压力").toString())/5;
        //总分
        Map<String,Object> map =getTotalScore("totalScore",questionnaireAnswerList);
        //总均分
        double totalScoreAverage=Double.valueOf(map.get("totalScore").toString())/39;
        map.put("totalScoreAverage",decimal(totalScoreAverage));

        map.put("learningStress",decimal(learningStressFactorAverage));
        map.put("teacherStress",decimal(teacherStressFactorAverage));
        map.put("familyStress",decimal(familyStressFactorAverage));
        map.put("parentStress",decimal(parentStressFactorAverage));
        map.put("classmateStress",decimal(classmateStressFactorAverage));
        map.put("societyStress",decimal(societyStressFactorAverage));
        map.put("selfStress",decimal(selfStressFactorAverage));

        return  ResultGenerator.genSuccessResult(JSON.toJSONString(map));
    }
   // 自杀态度问卷(SAQ)
    public Result gauge_27(ArrayList<QuestionnaireAnswer>  questionnaireAnswerList)throws Exception {
       //  1、对自杀行为的态度（F1）：条目1、7、12、17、19、22、23、26、29，共9项。
       Map<String,Object> f1Map=getFactorScore("F1","1,7,12,17,19,22,23,26,29",questionnaireAnswerList);
       // F1均分=该条目总分除以条目数
       double f1Average=Double.valueOf(f1Map.get("F1").toString())/9;

       // 2、对自杀者的态度（F2）：条目2、3、8、9、13、14、18、20、24、25，共10项。
       Map<String,Object> f2Map=getFactorScore("F2","2,3,8,9,13,14,18,20,24,25",questionnaireAnswerList);
       // F2均分=均分=该条目总分除以条目数
       double f2Average=Double.valueOf(f2Map.get("F2").toString())/10;

      // 3、对自杀者家属的态度（F3）：4、6、10、15、28，共5项。
       Map<String,Object> f3Map=getFactorScore("F3","4,6,10,15,28",questionnaireAnswerList);
       //  F3均分=该条目总分除以条目数
       double f3Average=Double.valueOf(f3Map.get("F3").toString())/5;

      // 4、对安乐死的态度（F4）：5、11、16、21、27，共5项。
       Map<String,Object> f4Map=getFactorScore("F4","5,11,16,21,27",questionnaireAnswerList);
       // F4均分=该条目总分除以条目数
       double f4Average=Double.valueOf(f4Map.get("F4").toString())/5;
      //总分
       Map<String,Object> map =getTotalScore("totalScore",questionnaireAnswerList);
       map.put("f1",decimal(f1Average));
       map.put("f2",decimal(f2Average));
       map.put("f3",decimal(f3Average));
       map.put("f4",decimal(f4Average));
       return  ResultGenerator.genSuccessResult(JSON.toJSONString(map));
   }

    /**
     * 生活事件量表(LES)
     *
     * 事件发生时间  1
     * 次            2
     * 性质          3
     *精神影响程度   4
     * 影响持续时间  5
     */
    public Result gauge_12(ArrayList<QuestionnaireAnswer>  questionnaireAnswerList)throws Exception {

         //4个维度
        //1、	事件发生时间
        double dimension1=0;
        //2、	性质  （1.好事  2.坏事）
        double dimension2=0;
           //好事   正性事件
          double  goodDeed=0;
          //正性事件得分及次序列表
          Map<String,Object> goodDeedMap=new HashMap<>();
          //坏事  正性事件
          double  badThing=0;
          //负性事件得分及次序列表
           Map<String,Object> badThingMap=new HashMap<>();
        //3、	精神影响程度：
        double dimension3=0;
        //4、	影响持续时间
        double dimension4=0;
        //5、某事件刺激=该事件影响程分x该事件持续时间分x该事件发生次数
        //6、总分：以上四个维度之和
        double totalScore=0;
        for(QuestionnaireAnswer qa :questionnaireAnswerList){
             String[] idSplit= qa.getQuestionId().split("_");
               switch(Integer.valueOf(idSplit[2])){
                 case 1:
                     if(qa.getAnswerScore()>0){
                         dimension1++;
                     }
                     break;
                 case 3:
                     //精神影响程度(4)x该事件持续时间分(5)x该事件发生次数(2)
                     double num1=0;
                     double num2=0;
                     double num3=0;
                     String ids=idSplit[0]+"_"+idSplit[1]+"_";
                     for(int i=0;i<questionnaireAnswerList.size();i++){
                       String questionId=  questionnaireAnswerList.get(i).getQuestionId();
                          if(questionId.equals(ids+4)){
                              num1=questionnaireAnswerList.get(i).getAnswerScore();
                          }else  if(questionId.equals(ids+5)){
                              num2=questionnaireAnswerList.get(i).getAnswerScore();
                          } else if(questionId.equals(ids+2)){
                              num3=Double.valueOf(questionnaireAnswerList.get(i).getAnswer());
                          }
                     }
                     if(qa.getAnswer().equals("好事")){
                         goodDeed++;
                         //得到好事的 问题序号   和正性事件得分 idSplit[1]:为题目的序号
                         goodDeedMap.put(idSplit[1],num1*num2*num3);
                     }else if(qa.getAnswer().equals("坏事")){
                         badThing++;
                         //得到坏事的 问题序号   和负性事件得分 idSplit[1]:为题目的序号
                         badThingMap.put(idSplit[1],num1*num2*num3);

                     }
                     break;
                 case 4:
                     if(qa.getAnswerScore()>=2){
                         dimension3++;
                     }
                     break;
                 case 5:
                     if(qa.getAnswerScore()>=3){
                         dimension4++;
                     }
                     break;
           }
            totalScore+=qa.getAnswerScore();
        }
        Map<String,Object> map=new HashMap<>();
        //长期性事件
        map.put("dimension1",dimension1);
        //生活事件总刺激量
        map.put("dimension2",goodDeed+badThing);
        //正性事件
        map.put("goodDeed",goodDeed);
        //负性事件
        map.put("badThing",badThing);
       //正性事件得分及次序列表
        map.put("goodDeedMap",goodDeedMap);
        //负性事件得分及次序列表
        map.put("badThingMap",badThingMap);
        //精神影响程度在中度以上（包括中度）
        map.put("dimension3",dimension3);
        //影响持续时间在半年以上
        map.put("dimension4",dimension4);
        return  ResultGenerator.genSuccessResult(JSON.toJSONString(map));
    }
  //计算总分
    public  Map<String,Object>  getTotalScore(String key,ArrayList<QuestionnaireAnswer>  questionnaireAnswerList){
        //总分
        double totalScore=0;
         Map<String,Object> map=new HashMap<>();
        for(int i=0;i<questionnaireAnswerList.size();i++) {
            totalScore=totalScore+questionnaireAnswerList.get(i).getAnswerScore();
        }
        map.put(key,totalScore);

        return map ;
    }
    /**
     *  计算因子分
     *  用到的场景：问卷计算分数的标准，分为各个因子，如 某某因子：1，13，15 这三个为题目的序号
     *    需要相加，得到某某因子的总分
     *
     */
   public  Map<String,Object> getFactorScore(String key ,String questionNumber ,ArrayList<QuestionnaireAnswer>  questionnaireAnswerList){
            String[] questionNumberArray=questionNumber.split(",");
            Map<String,Object> map=new HashMap<>();
            double factorScore=0;
           for(int i=0;i<questionNumberArray.length;i++){
                for(QuestionnaireAnswer questionnaireAnswer :questionnaireAnswerList ){
                    String questionId= questionnaireAnswer.getQuestionId();
                    //问题的id 格式  如：dsdasda_1   1为问题的序号
                     String sequence=questionId.split("_")[1];
                    if(sequence.equals(questionNumberArray[i]+"")){
                        factorScore+=questionnaireAnswer.getAnswerScore();
                    }
                }
           }
           map.put(key,factorScore);

       return  map;
   }
   //针对 生活事件量表(LES) 设计计算个维度的分数


    //保留两位小数 不四舍五入
    public     double decimal(double f){
        BigDecimal b=new BigDecimal(f);
        double f1=b.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
        return  f1;
    }
   //保留5位小数 不四舍五入
   public    double decimalTo4(double f){
       BigDecimal b=new BigDecimal(f);
       double f1=b.setScale(4,BigDecimal.ROUND_HALF_UP).doubleValue();
       return  f1;
   }

}
