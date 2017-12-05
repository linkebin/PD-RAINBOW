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
import org.apache.poi.ss.formula.functions.T;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;


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
        try {
            //判断账号余额是否够
            if (!userQuestionnairesService.flgBalance()) {

                throw new MyException("账号余额不足！");
            }

        } catch (Exception e) {
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

    /**
     * 客户统计分析
     *
     * @param startTime
     * @param endTime
     * @return
     */
    @Override
    public Result acquisitionOfStatisticalAnalysis(Date startTime, Date endTime, String sex, String maritalStatus) {
        String arr[] = maritalStatus.split(",");
        List<VisitorRegister> reslutList = visitorRegisterMapper.acquisitionOfStatisticalAnalysis(getMap(startTime, endTime, sex));
        List<VisitorRegister> thisYearList = new ArrayList<>();
        for (VisitorRegister visitorRegister : reslutList) {
            if(arr.length>1){
                for (String i : arr) {
                    if (Integer.parseInt(i) == visitorRegister.getMaritalStatus()) {
                        thisYearList.add(visitorRegister);
                    }
                }
            }
        }
        Map map = getMap(operationDate(startTime), operationDate(endTime), sex);//设置时间map
        List<VisitorRegister> lastYearList = visitorRegisterMapper.acquisitionOfStatisticalAnalysis(map);
        Map<String, List> resultMap = new HashMap<>();
        resultMap.put("thisYearList", thisYearList);
        if(thisYearList.size()==0){
            resultMap.put("thisYearList", reslutList);
        }
        resultMap.put("lastYearList", lastYearList);
        return ResultGenerator.genSuccessResult(resultMap);
    }

    /**
     * 设置Map
     *
     * @param startTime
     * @param endTime
     * @return
     */
    public Map getMap(Date startTime, Date endTime, String sex) {
        Map map = new HashMap<>();
        map.put("startTime", startTime);
        map.put("endTime", endTime);
        map.put("sex", sex);
        return map;
    }

    /**
     * 年份减1
     *
     * @param date
     * @return
     */
    public Date operationDate(Date date) {
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(date);
        //表示年份减一.
        rightNow.add(Calendar.YEAR, -1);
        Date dt = rightNow.getTime();
        return dt;
    }
}
