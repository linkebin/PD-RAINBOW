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
    public Result acquisitionOfStatisticalAnalysis(Date startTime, Date endTime, String sex, String maritalStatus,
                                                   String belief,String ageGroupsIds,String goalIds,String types) {
        List<String> maritals = new ArrayList<String>();
        List<String> beliefs = new ArrayList<String>();
        List<Map<String,Object>> ageGroupsIdsMap = new ArrayList<Map<String,Object>>();
        List<String> sexs = new ArrayList<String>();
        List<String> goals = new ArrayList<String>();

        String mariltalArr [] = maritalStatus.split(",");
        String beliefArr [] = belief.split(",");
        String ageGroupArr [] = ageGroupsIds.split(",");
        String sexsArr [] = sex.split(",");
        String goalsArr [] = goalIds.split(",");

            for (String s1 : mariltalArr){
                maritals.add(s1);
            }
            for (String s0 : sexsArr){
                sexs.add(s0);
            }

            for (String s2 : beliefArr){
                beliefs.add(s2);
            }

            for (String s4 : goalsArr){
                goals.add(s4);
            }

            for (String s3 : ageGroupArr){
                Map<String,Object> map = new HashMap<String,Object>();
                if (s3.equals("0")){
                    map.put("ageStart",0);
                    map.put("ageEnd",18);
                }
                if (s3.equals("1")){
                    map.put("ageStart",19);
                    map.put("ageEnd",44);
                }
                if (s3.equals("2")){
                    map.put("ageStart",45);
                    map.put("ageEnd",59);
                }
                if (s3.equals("3")){
                    map.put("ageStart",60);
                    map.put("ageEnd",74);
                }
                if (s3.equals("4")){
                    map.put("ageStart",75);
                    map.put("ageEnd",89);
                }
                if (s3.equals("5")){
                    map.put("ageStart",90);
                    map.put("ageEnd",200);
                }
                ageGroupsIdsMap.add(map);
            }
        List<VisitorRegister> reslutList =
                visitorRegisterMapper.
                        acquisitionOfStatisticalAnalysisNew(maritals,beliefs,ageGroupsIdsMap,sexs,goals,getMap(startTime, endTime,types));
        return ResultGenerator.genSuccessResult(reslutList);
    }

    /**
     * 设置Map
     *
     * @param startTime
     * @param endTime
     * @return
     */
    public Map getMap(Date startTime, Date endTime,String types) {
        Map map = new HashMap<>();
        map.put("userId",Security.getUserId());
        map.put("startTime", startTime);
        map.put("endTime", endTime);
        map.put("types", types);
        return map;
    }
}
