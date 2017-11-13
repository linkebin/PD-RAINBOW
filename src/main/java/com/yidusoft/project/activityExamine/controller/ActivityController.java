package com.yidusoft.project.activityExamine.controller;

/**
 * Created by jery on 2016/11/23.
 */

import com.yidusoft.core.Result;
import com.yidusoft.project.activityExamine.service.ActivityService;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ActivityController {
	@Autowired
	private ActivityService myService;
	
	//开启活动审批流程实例
	@RequestMapping(value = "/process/{launchId}", method = RequestMethod.POST)
	public void startProcessInstance(@PathVariable String launchId) {
		myService.startProcess(launchId);
	}
	
	//获取当前人的任务
	@RequestMapping(value = "/tasks", method = RequestMethod.GET)
	public List getTasks(@RequestParam String assignee) {
		List<Task> tasks = myService.getTasks(assignee);
		return tasks;
	}
	
	//用户审批活动
	@RequestMapping(value = "/complete/{launchApproved}/{taskId}/{msg}", method = RequestMethod.GET)
	public Result complete(@PathVariable Boolean launchApproved, @PathVariable String taskId, @PathVariable String msg) {
		 return myService.completeTasks(launchApproved, taskId,msg);
	}
}