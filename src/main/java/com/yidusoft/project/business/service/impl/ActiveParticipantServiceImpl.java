package com.yidusoft.project.business.service.impl;

import com.yidusoft.core.AbstractService;
import com.yidusoft.project.business.dao.ActiveParticipantMapper;
import com.yidusoft.project.business.domain.ActiveParticipant;
import com.yidusoft.project.business.service.ActiveParticipantService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by CodeGenerator on 2017/11/06.
 */
@Service
@Transactional
public class ActiveParticipantServiceImpl extends AbstractService<ActiveParticipant> implements ActiveParticipantService {
    @Resource
    private ActiveParticipantMapper activeParticipantMapper;

    @Override
    public List<ActiveParticipant> getParticipantInfo(String activityId) {
        return activeParticipantMapper.getParticipantInfo(activityId);
    }
}
