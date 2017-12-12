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
    /*public Result acquisitionOfStatisticalAnalysis(Date startTime, Date endTime, String sex, String maritalStatus, String belief,String questionName,String name,String province,String city) {
        List<VisitorRegister> reslutList = visitorRegisterMapper.acquisitionOfStatisticalAnalysis(getMap(startTime, endTime, sex, name, province,city));
        List<VisitorRegister> thisYearList = getList(maritalStatus,belief,questionName,reslutList);
        return ResultGenerator.genSuccessResult(thisYearList);
    }*/
    public Result acquisitionOfStatisticalAnalysis(Date startTime, Date endTime, String sex, String maritalStatus, String belief,String questionName) {
        List<VisitorRegister> reslutList = visitorRegisterMapper.acquisitionOfStatisticalAnalysis(getMap(startTime, endTime, sex));
        List<VisitorRegister> thisYearList = getList(maritalStatus,belief,questionName,reslutList);
        return ResultGenerator.genSuccessResult(thisYearList);
    }

    /**
     * 获取问卷类型
     * @param startTime
     * @param endTime
     * @param sex
     * @param maritalStatus
     * @return
     */
    @Override
    public Result getQuestion(Date startTime, Date endTime, String sex, String maritalStatus, String belief,String questionName) {
        List<VisitorRegister> reslutList = visitorRegisterMapper.acquisitionOfStatisticalAnalysis(getMap(startTime, endTime, sex));
        List<VisitorRegister> list = visitorRegisterMapper.acquisitionOfStatisticalAnalysis(getMap(operationDate(startTime), operationDate(endTime), sex));
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(startTime);
        rightNow.add(Calendar.DATE, -1);
        Date date = rightNow.getTime();
        List<VisitorRegister> lastPhase = visitorRegisterMapper.getQuestion(getMap(lastPhaseDate(startTime,endTime), date, sex));
        List<VisitorRegister> thisYearList = getList(maritalStatus,belief,questionName,reslutList);
        List<VisitorRegister> LastYearList = getList(maritalStatus,belief,questionName,list);
        List<VisitorRegister> lastPhaseList = getList(maritalStatus,belief,questionName,lastPhase);
        Map map = new HashMap();
        map.put("thisYearList",thisYearList);
        map.put("LastYearList",LastYearList);
        map.put("lastPhaseList",lastPhaseList);
        return ResultGenerator.genSuccessResult(map);
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
    public List getList(String maritalStatus,String belief,String questionName,List<VisitorRegister> list){
        List thisYearList = new ArrayList<>();
        String status[] = maritalStatus.split(",");
        String bel[] = belief.split(",");
        String typeName[] = questionName.split(",");
        for (VisitorRegister visitorRegister : list) {
            if(status.length>1){
                for (String i : status) {
                    if (Integer.parseInt(i) == visitorRegister.getMaritalStatus()) {
                        if(bel.length>1){
                            for (String j : bel) {
                                if (Integer.parseInt(j) == visitorRegister.getReligiousBelief()) {
                                    if(typeName.length>1){
                                        for (String name : typeName) {
                                            if (name.equals(visitorRegister.getQuestionnaireName())) {
                                                thisYearList.add(visitorRegister);
                                            }
                                        }
                                    }else{
                                        thisYearList.add(visitorRegister);
                                    }
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
                        if(typeName.length>1){
                            for (String name : typeName) {
                                if (name.equals(visitorRegister.getQuestionnaireName())) {
                                    thisYearList.add(visitorRegister);
                                }
                            }
                        }else{
                            thisYearList.add(visitorRegister);
                        }
                    }
                }
            } else if(typeName.length>1){
                for (String name : typeName) {
                    if (name.equals(visitorRegister.getQuestionnaireName())) {
                        thisYearList.add(visitorRegister);
                    }
                }
            }
        }
        return thisYearList;
    }

}
