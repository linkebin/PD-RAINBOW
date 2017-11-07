package com.yidusoft.project.business.service;

import com.yidusoft.core.Service;
import com.yidusoft.project.business.domain.ActiveParticipant;

import java.util.List;


/**
 * Created by CodeGenerator on 2017/11/06.
 */
public interface ActiveParticipantService extends Service<ActiveParticipant> {
    //获取参与人活动的人的信息
    List<ActiveParticipant> getParticipantInfo(String activityId);
}
