package com.yidusoft.project.business.dao;

import com.yidusoft.core.Mapper;
import com.yidusoft.project.business.domain.ActiveParticipant;

import java.util.List;

public interface ActiveParticipantMapper extends Mapper<ActiveParticipant> {
    //获取参与人活动的人的信息
    List<ActiveParticipant> getParticipantInfo(String activityId);
}