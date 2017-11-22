package com.yidusoft.project.activitis.controller;

/**
 * Created by jery on 2016/11/23.
 */

import com.yidusoft.core.Result;
import com.yidusoft.project.activitis.service.ChannelActivityService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.history.HistoricActivityInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ChannelActivityController {
	@Autowired
	private ChannelActivityService channelActivityService;

	@Autowired
	private ProcessEngine processEngine;

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