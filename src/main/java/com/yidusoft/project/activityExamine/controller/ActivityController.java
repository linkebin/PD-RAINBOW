package com.yidusoft.project.activityExamine.controller;

/**
 * Created by jery on 2016/11/23.
 */

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yidusoft.core.Result;
import com.yidusoft.core.ResultGenerator;
import com.yidusoft.project.activityExamine.service.ActivityService;
import com.yidusoft.utils.Data;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.TaskService;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class ActivityController {
	@Autowired
	private ActivityService myService;

	@Autowired
	private ProcessEngine processEngine;
	
	//开启活动审批流程实例
	@RequestMapping(value = "/launchProcess", method = RequestMethod.POST)
	public void startProcessInstance( String launchId, String launchName) {
		myService.startProcess(launchId,launchName);
	}
	
	//获取当前人的任务
	@RequestMapping(value = "/tasks", method = RequestMethod.POST)
	public Result getTasks(String assignee,Integer page,Integer pagesize) {
		TaskService taskService = processEngine.getTaskService();
		List<Task> tasks = myService.getTasks(assignee,page-1,pagesize);
		List<TaskRepresentation> dtos = new ArrayList<TaskRepresentation>();
		for (Task task : tasks) {
				String ID_ = (String) taskService.getVariable(task.getId(), "ID_S");
				String NAME_ = (String) taskService.getVariable(task.getId(), "NAME_S");
				dtos.add(new TaskRepresentation(task.getId(),task.getName(),task.getCreateTime(),ID_,NAME_));
		}
		PageInfo pageInfo = new PageInfo(dtos);
		pageInfo.setTotal(myService.getTasksCount(assignee));
		return ResultGenerator.genSuccessResult(pageInfo);
	}
	
	//用户审批活动
	@RequestMapping(value = "/complete/{launchApproved}/{taskId}/{msg}", method = RequestMethod.GET)
	public Result complete(@PathVariable Boolean launchApproved, @PathVariable String taskId, @PathVariable String msg) {
		 return myService.completeTasks(launchApproved, taskId,msg);
	}

	static class TaskRepresentation

	{
		private String taskId;
		private String taskName;
		private Date taskTime;
		private String id_;
		private String name_;

		public TaskRepresentation(String taskId, String taskName,Date taskTime,String id_,String name_) {
			this.taskId = taskId;
			this.taskName = taskName;
			this.taskTime = taskTime;
			this.id_=id_;
			this.name_ = name_;
		}

		public String getTaskId() {
			return taskId;
		}

		public void setTaskId(String taskId) {
			this.taskId = taskId;
		}

		public String getTaskName() {
			return taskName;
		}

		public void setTaskName(String taskName) {
			this.taskName = taskName;
		}

		public Date getTaskTime() {
			return taskTime;
		}

		public void setTaskTime(Date taskTime) {
			this.taskTime = taskTime;
		}

		public String getId_() {
			return id_;
		}

		public void setId_(String id_) {
			this.id_ = id_;
		}

		public String getName_() {
			return name_;
		}

		public void setName_(String name_) {
			this.name_ = name_;
		}
	}
}