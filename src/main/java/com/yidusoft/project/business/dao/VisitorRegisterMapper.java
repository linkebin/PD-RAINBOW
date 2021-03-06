package com.yidusoft.project.business.dao;

import com.yidusoft.core.Mapper;
import com.yidusoft.project.business.domain.VisitorRegister;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface VisitorRegisterMapper extends Mapper<VisitorRegister> {

    //查询来访者的省份
    List<Map<String,Object>> findVisitorRegisterProvince();

    /**
     * 根据咨询师id查询所对应的客户
     * @param visitorRegister
     * @return
     */
    List<VisitorRegister> findViitorByCounselorId(VisitorRegister visitorRegister);

    /**
     * 根据咨询师id查询所对应的客户,按最近时间排序
     * @param visitorRegister
     * @return
     */
    List<Map<String,Object>> findViitorByCounselorIdSortTime(VisitorRegister visitorRegister);

    /**
     * 根据创建人id获取其客户的信息
     * @param map
     * @return
     */
    List<VisitorRegister> findVitorByCreator(Map map);

    /**
     * 客户统计分析
     * @param map
     * @return
     */
    List<VisitorRegister> acquisitionOfStatisticalAnalysis(Map map);

    List<VisitorRegister> acquisitionOfStatisticalAnalysisNew(@Param("maritals")List<String> maritals,
                                                              @Param("beliefs")List<String> beliefs,
                                                              @Param("ageGroupsIdsMap")List<Map<String,Object>> ageGroupsIdsMap,
                                                              @Param("sexs")List<String> sexs,
                                                              @Param("goals")List<String> goals,
                                                              @Param("map")Map<String,Object> map);
    /**
     * 获取问卷类型的统计
     * @param map
     * @return
     */
    List<VisitorRegister> getQuestion(Map map);
}