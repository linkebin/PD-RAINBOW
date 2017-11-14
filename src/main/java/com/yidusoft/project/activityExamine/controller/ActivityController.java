package com.yidusoft.project.activityExamine.controller;

/**
 * Created by jery on 2016/11/23.
 */

import com.alibaba.fastjson.JSON;
import com.yidusoft.core.Result;
import com.yidusoft.core.ResultGenerator;
import com.yidusoft.project.activityExamine.service.ActivityService;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
	public Result getTasks(@RequestParam String assignee) {
		List<Task> tasks = myService.getTasks(assignee);
		List<TaskRepresentation> dtos = new ArrayList<TaskRepresentation>();
		for (Task task : tasks) {
			dtos.add(new TaskRepresentation(task.getId(), task.getName()));
		}
		return ResultGenerator.genSuccessResult(dtos);
	}
	
	//用户审批活动
	@RequestMapping(value = "/complete/{launchApproved}/{taskId}/{msg}", method = RequestMethod.GET)
	public Result complete(@PathVariable Boolean launchApproved, @PathVariable String taskId, @PathVariable String msg) {
		 return myService.completeTasks(launchApproved, taskId,msg);
	}

	//Task的dto
	static class TaskRepresentation

	{
		private String id;
		private String name;

		public TaskRepresentation(String id, String name) {
			this.id = id;
			this.name = name;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
	}
}