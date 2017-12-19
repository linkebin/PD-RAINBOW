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
import java.util.concurrent.TimeUnit;


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
    public List<Map<String, Object>> findVisitorRegisterProvince() {
        return visitorRegisterMapper.findVisitorRegisterProvince();
    }

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
    public List<VisitorRegister> findVitorByCreator(Map map) {
        return visitorRegisterMapper.findVitorByCreator(map);
    }

    /**
     * 客户统计分析
     *
     * @param startTime
     * @param endTime
     * @return
     */
    @Override
    public Result acquisitionOfStatisticalAnalysis(Date startTime, Date endTime, String sex, String maritalStatus, String belief) {
        List<String> maritals = new ArrayList<String>();
        List<String> beliefs = new ArrayList<String>();

        String mariltalArr [] = maritalStatus.split(",");
        String beliefArr [] = belief.split(",");

            for (String s1 : mariltalArr){
                maritals.add(s1);
            }

            for (String s2 : beliefArr){
                beliefs.add(s2);
            }

        List<VisitorRegister> reslutList =
                visitorRegisterMapper.
                        acquisitionOfStatisticalAnalysisNew(maritals,beliefs,getMap(startTime, endTime, sex));

        List<VisitorRegister> thisYearList = getList(maritalStatus,belief,reslutList);
        return ResultGenerator.genSuccessResult(thisYearList);
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
        map.put("userId",Security.getUserId());
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

    /**
     * 获取上一期的时间段
     * @param startTime
     * @param endTime
     * @return
     */
    public Date lastPhaseDate(Date startTime, Date endTime){
        long t1 = startTime.getTime();
        long t2 = endTime.getTime();
        long millis = t2 - t1;
        long days = TimeUnit.MILLISECONDS.toDays(millis);
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(startTime);
        rightNow.add(Calendar.DATE, -(int)days);
        Date date = rightNow.getTime();
        return date;
    }

    /**
     * 返回今年的数据集合
     * @param maritalStatus
     * @param list
     * @return
     */
    public List getList(String maritalStatus,String belief,List<VisitorRegister> list){
        List thisYearList = new ArrayList<>();
        String status[] = maritalStatus.split(",");
        String bel[] = belief.split(",");
        for (VisitorRegister visitorRegister : list) {
            if(status.length>1){
                for (String i : status) {
                    if (Integer.parseInt(i) == visitorRegister.getMaritalStatus()) {
                        if(bel.length>1){
                            for (String j : bel) {
                                if (Integer.parseInt(j) == visitorRegister.getReligiousBelief()) {
                                    thisYearList.add(visitorRegister);
                                }
                            }
                        }else{
                            thisYearList.add(visitorRegister);
                        }
                    }
                }
            }else if(bel.length>1){
                for (String i : bel) {
                    if (Integer.parseInt(i) == visitorRegister.getReligiousBelief()) {
                        thisYearList.add(visitorRegister);
                    }
                }
            }
        }
        return thisYearList;
    }

}
