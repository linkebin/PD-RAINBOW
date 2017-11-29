package com.yidusoft.project.business.service.impl;
import com.yidusoft.core.AbstractService;
import com.yidusoft.core.Result;
import com.yidusoft.core.ResultGenerator;
import com.yidusoft.project.business.dao.VisitorRegisterMapper;
import com.yidusoft.project.business.domain.VisitorRegister;
import com.yidusoft.project.business.service.VisitorRegisterService;
import com.yidusoft.project.questionnaire.dao.DataAcquisitionMapper;
import com.yidusoft.project.questionnaire.dao.QuestionnaireAnswerMapper;
import com.yidusoft.project.questionnaire.dao.QuestionnaireMapper;
import com.yidusoft.project.questionnaire.dao.QuestionnaireQuestionMapper;
import com.yidusoft.project.questionnaire.domain.DataAcquisition;
import com.yidusoft.project.questionnaire.service.DataAcquisitionService;
import com.yidusoft.project.transaction.service.UserQuestionnairesService;
import com.yidusoft.utils.CodeHelper;
import com.yidusoft.utils.MyException;
import com.yidusoft.utils.Security;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;


/**
 * Created by CodeGenerator on 2017/10/11.
 */
@Service
@Transactional
public class VisitorRegisterServiceImpl extends AbstractService<VisitorRegister> implements VisitorRegisterService {
    @Resource
    private VisitorRegisterMapper visitorRegisterMapper;
    @Resource
    private DataAcquisitionMapper dataAcquisitionMapper;
    @Resource
    private UserQuestionnairesService userQuestionnairesService;
    @Override
    public List<VisitorRegister> findViitorByCounselorId(VisitorRegister visitorRegister) {
        return visitorRegisterMapper.findViitorByCounselorId(visitorRegister);
    }
    //为来访者使用问卷
    @Override
    public Result getCheckQuestionnaireForVisitor() {
        try{
        //判断账号余额是否够
        if(!userQuestionnairesService.flgBalance()){
            throw  new MyException("账号余额不足！");
        }

        }catch (Exception e){
            return ResultGenerator.genFailResult(e.getMessage());
        }
        return ResultGenerator.genSuccessResult();
    }

    @Override
    public List<Map<String, Object>> findViitorByCounselorIdSortTime(VisitorRegister visitorRegister) {
        return visitorRegisterMapper.findViitorByCounselorIdSortTime(visitorRegister);
    }

    @Override
    public List<VisitorRegister> findVitorByCreator(String creator) {
        return visitorRegisterMapper.findVitorByCreator(creator);
    }
}
