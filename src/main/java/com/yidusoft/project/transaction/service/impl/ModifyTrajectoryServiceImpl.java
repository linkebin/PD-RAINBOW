package com.yidusoft.project.transaction.service.impl;

import com.yidusoft.project.transaction.dao.ModifyTrajectoryMapper;
import com.yidusoft.project.transaction.domain.ModifyTrajectory;
import com.yidusoft.project.transaction.service.ModifyTrajectoryService;
import com.yidusoft.core.AbstractService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by CodeGenerator on 2017/11/14.
 */
@Service
@Transactional
public class ModifyTrajectoryServiceImpl extends AbstractService<ModifyTrajectory> implements ModifyTrajectoryService {
    @Resource
    private ModifyTrajectoryMapper modifyTrajectoryMapper;

    @Override
    public List<ModifyTrajectory> getAll(ModifyTrajectory modifyTrajectory) {
        return modifyTrajectoryMapper.getAll(modifyTrajectory);
    }
}
