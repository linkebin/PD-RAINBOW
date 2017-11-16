package com.yidusoft.project.activityExamine.controller;

/**
 * Created by jery on 2016/11/23.
 */

import com.github.pagehelper.PageInfo;
import com.yidusoft.core.Result;
import com.yidusoft.core.ResultGenerator;
import com.yidusoft.project.activityExamine.service.ActivityService;
import com.yidusoft.project.activityExamine.service.ChannelActivityService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class ChannelActivityController {
	@Autowired
	private ChannelActivityService channelActivityService;



	//开启渠道审批流程实例
	@RequestMapping(value = "/channelProcess", method = RequestMethod.POST)
	public void startProcessInstance( String channelId, String channelName) {
		channelActivityService.startProcess(channelId,channelName);
	}

	//审批渠道
	@RequestMapping(value = "/channelComplete/{channelAapproval_}/{taskId}/{msg}", method = RequestMethod.POST)
	public Result complete(@PathVariable Boolean channelAapproval_, @PathVariable String taskId, @PathVariable String msg) {

		 return channelActivityService.completeTasks(channelAapproval_, taskId,msg);
	}
}