package com.yidusoft.project.questionnaire.service.impl;

import com.yidusoft.project.questionnaire.dao.DataAcquisitionMapper;
import com.yidusoft.project.questionnaire.dao.QuestionnaireAnswerMapper;
import com.yidusoft.project.questionnaire.domain.DataAcquisition;
import com.yidusoft.project.questionnaire.domain.QuestionnaireAnswer;
import com.yidusoft.project.questionnaire.service.DataAcquisitionService;
import com.yidusoft.core.AbstractService;

import com.yidusoft.utils.MyException;
import com.yidusoft.utils.Security;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by CodeGenerator on 2017/10/11.
 */
@Service
@Transactional
public class DataAcquisitionServiceImpl extends AbstractService<DataAcquisition> implements DataAcquisitionService {
    @Resource
    private DataAcquisitionMapper dataAcquisitionMapper;
    @Resource
    private QuestionnaireAnswerMapper questionnaireAnswerMapper;
    // 症状自评量表-SCL90  的规则结论
    @Override
    public String symptomConclusion(String acquisitionId, String userId) {
        QuestionnaireAnswer questionnaireAnswer=new QuestionnaireAnswer();
        questionnaireAnswer.setUserId(userId);
        questionnaireAnswer.setAcquisitionId(acquisitionId);
        List<QuestionnaireAnswer>  questionnaireAnswerList=questionnaireAnswerMapper.findAnswerForAcquisition(questionnaireAnswer);
        Map<String,Object> map=new HashMap<>();
        for(int i=0;i<questionnaireAnswerList.size();i++){
            String ids=(i+1)+"";
            for(int j=0;j<questionnaireAnswerList.size();j++){
                if(questionnaireAnswerList.get(j).getQuestionId().equals(ids)){
                    map.put(ids,questionnaireAnswerList.get(j));
                }
            }

        }
        //1.躯体化
        String [] factor1={"1","4","12","27","40","42","48","49","52","53","56","58"};
        //2.强迫症状
        String [] factor2={"3","9","10","28","38","45","46","51","55","65"};
        //3.人际关系敏感
        String [] factor3={"6","21","34","36","37","41","61","69","73"};
        // 4.抑郁
        String [] factor4={"5","14","15","20","22","26","29","30","31","32","54","71","79"};
        // 5.焦虑
        String [] factor5={"2","17","23","33","39","57","72","78","80","86"};
        //6.敌对
        String [] factor6={"11","24","63","67","74","81"};
        //7.恐怖
        String [] factor7={"13","25","47","50","70","75","82"};
        //8.偏执
        String [] factor8={"8","18","43","68","76","83"};
        //9.精神病性
        String [] factor9={"7","16","35","62","77","84","85","87","88","90"};
        //10.其他
        String [] factor10={"19","44","59","60","64","66","89"};
        Map<String,Map<String,Object>> factor=new HashMap<>();
        Map<String,Object> map1=new  HashMap<>();
        map1.put("factorMean","");
        map1.put("factor",factor1);
        factor.put("1",map1);
        Map<String,Object> map2=new  HashMap<>();
        map2.put("factorMean","");
        map2.put("factor",factor2);
        factor.put("2",map2);
        Map<String,Object> map3=new  HashMap<>();
        map3.put("factorMean","");
        map3.put("factor",factor3);
        factor.put("3",map3);
        Map<String,Object> map4=new  HashMap<>();
        map4.put("factorMean","");
        map4.put("factor",factor4);
        factor.put("4",map4);
        Map<String,Object> map5=new  HashMap<>();
        map5.put("factorMean","");
        map5.put("factor",factor5);
        factor.put("5",map5);
        Map<String,Object> map6=new  HashMap<>();
        map6.put("factorMean","");
        map6.put("factor",factor6);
        factor.put("6",map6);
        Map<String,Object> map7=new  HashMap<>();
        map7.put("factorMean","");
        map7.put("factor",factor7);
        factor.put("7",map7);
        Map<String,Object> map8=new  HashMap<>();
        map8.put("factorMean","");
        map8.put("factor",factor8);
        factor.put("8",map8);
        Map<String,Object> map9=new  HashMap<>();
        map9.put("factorMean","");
        map9.put("factor",factor9);
        factor.put("9",map9);
        Map<String,Object> map10=new  HashMap<>();
        map10.put("factorMean","");
        map10.put("factor",factor10);
        factor.put("10",map10);

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
        double positiveMean=decimal(positiveTotalScore/positiveTotal);
        //阴性症状均分
        double negativeMean=decimal(negativeTotalScore/negativeTotal);
        //因子平均分factorMean
        for(int i=0;i<factor.size();i++){
          Map<String,Object>factorMap=factor.get((i+1)+"");
            String [] factorArray=(String []) factorMap.get("factor");
            //因子总分
            double factorTotalScore=0;
            for(int j=0;j<factorArray.length;j++){
                QuestionnaireAnswer q= (QuestionnaireAnswer)map.get(factorArray[j]);
                factorTotalScore=factorTotalScore+q.getAnswerScore();
            }
            //因子平均分

            double  factorMean= decimal(factorTotalScore/factorArray.length);
            factor.get((i+1)+"").put("factorMean",factorMean);
            factor.get((i+1)+"").put("factorTotalScore",factorTotalScore);
        }

        //标准分
        double standard=totalScore*1.25;
        int  standardPoints=(int)standard;
        StringBuffer stringBuffer=new StringBuffer();
        stringBuffer.append("您的总分为"+totalScore+"分，总均分为"+totalScore/90+"分，标准分为"+standardPoints+"分#");
        stringBuffer.append("因子分：均分±标准差：#");
        stringBuffer.append("躯体化分: "+factor.get("1").get("factorTotalScore")+"分，均分"+factor.get("1").get("factorMean")+" （1.37±0.48）#");
        stringBuffer.append("强迫症状: "+factor.get("2").get("factorTotalScore")+"分，均分"+factor.get("2").get("factorMean")+" （1.62±0.58）#");
        stringBuffer.append("人际敏感:"+factor.get("3").get("factorTotalScore")+"分，均分"+factor.get("3").get("factorMean")+" （1.65±0.51）#");
        stringBuffer.append("抑郁症状: "+factor.get("4").get("factorTotalScore")+"分，均分"+factor.get("4").get("factorMean")+" （1.50±0.59）#");
        stringBuffer.append("焦虑症状: "+factor.get("5").get("factorTotalScore")+"分，均分"+factor.get("5").get("factorMean")+" （1.39±0.43）#");
        stringBuffer.append("敌对症状: "+factor.get("6").get("factorTotalScore")+"分，均分"+factor.get("6").get("factorMean")+" （1.48±0.56）#");
        stringBuffer.append("恐怖症状: "+factor.get("7").get("factorTotalScore")+"分，均分"+factor.get("7").get("factorMean")+" （1.23±0.41）#");
        stringBuffer.append("偏执症状 "+factor.get("8").get("factorTotalScore")+"分，均分"+factor.get("8").get("factorMean")+" （1.43±0.57）#");
        stringBuffer.append("精神症状 "+factor.get("9").get("factorTotalScore")+"分，均分"+factor.get("9").get("factorMean")+" （1.29±0.42）#");
        stringBuffer.append("总分超过160分，单项均分超过2分，应做进一步检查；#");
        stringBuffer.append("标准分：＞ 200 分，有很明显的心理问题，可求助于心理咨询；#");
        stringBuffer.append(" 标准分：＞ 250 分，比较严重，需要做医学上的仔细检查，可能需要做针对性的心理治疗或在医生的指导下服药。");


        return stringBuffer.toString();
    }


    //抑郁 或者 焦虑 自评 的规则结论
    @Override
    public String depressedOrAnxiousConclusion(String acquisitionId, String userId,String type) {
        StringBuffer stringBuffer=new StringBuffer();
        try{
            QuestionnaireAnswer questionnaireAnswer=new QuestionnaireAnswer();
            questionnaireAnswer.setUserId(userId);
            questionnaireAnswer.setAcquisitionId(acquisitionId);
            List<QuestionnaireAnswer>  questionnaireAnswerList=questionnaireAnswerMapper.findAnswerForAcquisition(questionnaireAnswer);
            //总分
            double totalScore=0;
            for(int i=0;i<questionnaireAnswerList.size();i++) {
                totalScore=totalScore+questionnaireAnswerList.get(i).getAnswerScore();

            }
            if(type.equals("depressed")){
                double severityIndex=decimal(totalScore/80);
                stringBuffer.append("抑郁严重指数为:"+severityIndex+"#");
                stringBuffer.append("无抑郁：抑郁严重指数 ＜ 0.5#");
                stringBuffer.append("轻度抑郁：抑郁严重指数 0.5~0.59#");
                stringBuffer.append("中度抑郁：抑郁严重指数 0.6~0.69#");
                stringBuffer.append("重度抑郁：抑郁严重指数 0.7以上");
            }
            if(type.equals("anxious")){

                double standardScore=totalScore*1.25;
                stringBuffer.append("标准分:"+standardScore+"#");
                stringBuffer.append("无焦虑：标准分 ＜ 50分#");
                stringBuffer.append("轻度焦虑：标准分 50-59分#");
                stringBuffer.append("中度焦虑：标准分 60-69分#");
                stringBuffer.append("重度焦虑：标准分 70分及以上");
        }

        }catch (Exception e){
            e.printStackTrace();
        }

        return stringBuffer.toString();
    }
    //根据日期查询来访者使用的问卷
    @Override
    public List<DataAcquisition> findQuestionnaireForVisitor(DataAcquisition dataAcquisition) {
        return dataAcquisitionMapper.findQuestionnaireForVisitor(dataAcquisition);
    }

    @Override
    public List<DataAcquisition> findMyQuestionnaireListByPage(DataAcquisition dataAcquisition) {
        return dataAcquisitionMapper.findMyQuestionnaireListByPage(dataAcquisition);
    }

    //保留两位小数 不四舍五入
    public  double decimal(double f){
        BigDecimal b=new BigDecimal(f);
        double f1=b.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
        return  f1;
    }

    @Override
    public List<DataAcquisition> questionnaireUseInfoListByPage() {

        List<DataAcquisition> list = dataAcquisitionMapper.questionnaireUseInfoListByPage(Security.getUserId());
        return list;
    }
}
