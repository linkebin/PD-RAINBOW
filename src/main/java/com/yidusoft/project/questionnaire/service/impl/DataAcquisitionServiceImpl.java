package com.yidusoft.project.questionnaire.service.impl;

import com.yidusoft.core.AbstractService;
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


    //根据日期查询来访者使用的问卷
    @Override
    public List<DataAcquisition> findQuestionnaireForVisitor(DataAcquisition dataAcquisition) {
        return dataAcquisitionMapper.findQuestionnaireForVisitor(dataAcquisition);
    }

    @Override
    public List<DataAcquisition> findMyQuestionnaireListByPage(Map<String,Object> map) {
        return dataAcquisitionMapper.findMyQuestionnaireListByPage(map);
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
