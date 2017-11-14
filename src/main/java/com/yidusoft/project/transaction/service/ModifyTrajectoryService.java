package com.yidusoft.project.transaction.service;

import com.yidusoft.core.Service;
import com.yidusoft.project.transaction.domain.ModifyTrajectory;

import java.util.List;


/**
 * Created by CodeGenerator on 2017/11/14.
 */
public interface ModifyTrajectoryService extends Service<ModifyTrajectory> {
    List<ModifyTrajectory> getAll(ModifyTrajectory modifyTrajectory);
}
