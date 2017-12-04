package com.yidusoft.project.questionnaire.service.impl;

import com.yidusoft.core.AbstractService;
import com.yidusoft.core.Result;
import com.yidusoft.core.ResultGenerator;
import com.yidusoft.project.business.service.VisitingRecordService;
import com.yidusoft.project.questionnaire.dao.DataAcquisitionMapper;
import com.yidusoft.project.questionnaire.dao.QuestionnaireAnswerMapper;
import com.yidusoft.project.questionnaire.domain.DataAcquisition;
import com.yidusoft.project.questionnaire.domain.QuestionnaireAnswer;
import com.yidusoft.project.questionnaire.service.DataAcquisitionService;
import com.yidusoft.utils.Security;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
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
    @Resource
    private VisitingRecordService visitingRecordService;

    //根据日期查询来访者使用的问卷
    @Override
    public List<DataAcquisition> findQuestionnaireForVisitor(DataAcquisition dataAcquisition) {
        return dataAcquisitionMapper.findQuestionnaireForVisitor(dataAcquisition);
    }

    @Override
    public List<DataAcquisition> findMyQuestionnaireListByPage(Map<String,Object> map) {
        return dataAcquisitionMapper.findMyQuestionnaireListByPage(map);
    }
    /**
     *  查询来访者填写问卷的总数
     * @param userId
     * @return
     */
    @Override
    public int getDataAcquisitionTotal(String userId) {
        return dataAcquisitionMapper.getDataAcquisitionTotal(userId);
    }

    /**
     * 填写问卷类别总数
     * @param userId
     * @return
     */
    @Override
    public List<DataAcquisition> getQuestionnaireTypeTotal(String userId) {
        return dataAcquisitionMapper.getQuestionnaireTypeTotal(userId);
    }
    /**
     * 查询来访者的填写结果
     * @param dataAcquisition
     * @return
     */
    @Override
    public  List<DataAcquisition> findDataAcquistionForVisitor(DataAcquisition dataAcquisition) {
        //来访者问卷使用记录
        List<DataAcquisition> dataAcquisitionList= dataAcquisitionMapper.findDataAcquistionForVisitor(dataAcquisition);
        return dataAcquisitionList;
    }

    /**
     * 来访者的问卷统计分析
     *  @return
     */
    @Override
    public Result getDataAcquisitionForVisitingCount(String userId) {
        //填写问卷类别总数
        List<DataAcquisition> dataAcquisitions= dataAcquisitionMapper.getQuestionnaireTypeTotal(userId);
        //填写问卷总数
        int dataAcquisitionTotal =dataAcquisitionMapper.getDataAcquisitionTotal(userId);
        //来访总次数
        int visitingTotal= visitingRecordService.getVisitingTotal(userId);
        List<DataAcquisition>  questionnaireList= dataAcquisitionMapper.findQuestionnaireForVisitorAll(userId);
        Map<String,Object> map=new HashMap<>();
        map.put("dataAcquisitions",dataAcquisitions);
        map.put("questionnaireList",questionnaireList);
        map.put("dataAcquisitionTotal",dataAcquisitionTotal);
        map.put("visitingTotal",visitingTotal);
        return ResultGenerator.genSuccessResult(map);
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

    @Override
    public Integer findCountByActivityId(String activityId) {
        return dataAcquisitionMapper.findCountByActivityId(activityId);
    }
}
