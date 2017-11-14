package com.yidusoft.project.transaction.dao;

import com.yidusoft.core.Mapper;
import com.yidusoft.project.transaction.domain.ModifyTrajectory;

import java.util.List;

public interface ModifyTrajectoryMapper extends Mapper<ModifyTrajectory> {
    List<ModifyTrajectory> getAll(ModifyTrajectory modifyTrajectory);
}