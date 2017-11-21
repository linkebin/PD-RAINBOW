package com.yidusoft.project.activitis.controller;

/**
 * Created by jery on 2016/11/23.
 */

import com.github.pagehelper.PageInfo;
import com.yidusoft.core.Result;
import com.yidusoft.core.ResultGenerator;
import com.yidusoft.project.activitis.service.ActivityService;
import com.yidusoft.utils.Security;
import org.activiti.engine.HistoryService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.history.HistoricVariableInstance;
import org.activiti.engine.runtime.ProcessInstance;
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

	@Autowired
	private RuntimeService runtimeService;

	@Autowired
	private TaskService taskService;

	@Autowired
	private HistoryService historyService;

	//开启活动审批流程实例
	@RequestMapping(value = "/launchProcess", method = RequestMethod.POST)
	public void startProcessInstance( String launchId, String launchName) {
		myService.startProcess(launchId,launchName);
	}
	
	//获取当前人的任务
	@RequestMapping(value = "/tasks", method = RequestMethod.POST)
	public Result getTasks(String assignee,Integer page,Integer pagesize) {
		List<Task> tasks = myService.getTasks(assignee,page-1,pagesize);
		List<TaskRepresentation> dtos = new ArrayList<TaskRepresentation>();
		for (Task task : tasks) {
				String ID_ = (String) taskService.getVariable(task.getId(), "objid");
				String NAME_ = (String) taskService.getVariable(task.getId(), "objname");
				String startName = (String) taskService.getVariable(task.getId(), "applyuser");
			    String userTaskApply = (String) taskService.getVariableLocal(task.getId(),"userTaskApply");
			ProcessInstance processInstance = getProcessInstance(task);
				dtos.add(new TaskRepresentation(task.getId(),task.getName(),task.getCreateTime(),ID_,NAME_,startName
				,processInstance.getProcessDefinitionName(),processInstance.getProcessDefinitionKey()));
		}
		PageInfo pageInfo = new PageInfo(dtos);
		pageInfo.setTotal(myService.getTasksCount(assignee));
		return ResultGenerator.genSuccessResult(pageInfo);
	}

	//获取当前人的任务
	@RequestMapping(value = "/historyTaskList", method = RequestMethod.POST)
	public Result historyTaskList(Integer page,Integer pagesize) {
		String assignee = Security.getUser().getUserName();
		List<HistoricTaskInstance> tasks=processEngine.getHistoryService() // 历史任务Service
				.createHistoricTaskInstanceQuery() // 创建历史任务实例查询
				.taskAssignee(assignee) // 指定办理人
				.finished() // 查询已经完成的任务
				.listPage(page-1,pagesize);

		List<TaskRepresentation> dtos = new ArrayList<TaskRepresentation>();
		for (HistoricTaskInstance historicTaskInstance : tasks) {

			List<HistoricVariableInstance> list = historyService
					.createHistoricVariableInstanceQuery()
					.processInstanceId(historicTaskInstance.getProcessInstanceId())
					.list();
			TaskRepresentation representation = new TaskRepresentation();
			representation.setTaskId(historicTaskInstance.getId());
			representation.setTaskName(historicTaskInstance.getName());
			representation.setStartTime(historicTaskInstance.getStartTime());
			representation.setEndTime(historicTaskInstance.getEndTime());
			representation.setAssignee(historicTaskInstance.getAssignee());

			if(list != null && list.size()>0){
				for(HistoricVariableInstance hvi:list){
					if (hvi.getVariableName().equals("objname")){
						representation.setName_((String) hvi.getValue());
					}
					if (hvi.getVariableName().equals("objid")){
						representation.setId_((String) hvi.getValue());
					}
					if (hvi.getVariableName().equals("flag")){
						representation.setFlag((boolean) hvi.getValue());
					}
					if (hvi.getVariableName().equals("applyuser")){
						representation.setStartName((String) hvi.getValue());
					}
				}
			}
			dtos.add(representation);
		}
		PageInfo pageInfo = new PageInfo(dtos);
		pageInfo.setTotal(getHistoryTasksCount(assignee));
		return ResultGenerator.genSuccessResult(pageInfo);
	}

	public int getHistoryTasksCount(String assignee){
		int count=processEngine.getHistoryService() // 历史任务Service
				.createHistoricTaskInstanceQuery() // 创建历史任务实例查询
				.taskAssignee(assignee) // 指定办理人
				.finished() // 查询已经完成的任务
				.list().size();
		return count;
	}



	public ProcessInstance getProcessInstance(Task task){
		String processInstanceId=task.getProcessInstanceId(); // 获取流程实例id
		ProcessInstance pi=runtimeService.createProcessInstanceQuery() // 根据流程实例id获取流程实例
				.processInstanceId(processInstanceId)
				.singleResult();
		return  pi;
	}
	
	//用户审批活动
	@RequestMapping(value = "/complete/{launchApproved}/{taskId}/{msg}", method = RequestMethod.POST)
	public Result complete(@PathVariable Boolean launchApproved, @PathVariable String taskId, @PathVariable String msg) {
		System.out.println(1222);
		 return myService.completeTasks(launchApproved, taskId,msg);
	}

	static class TaskRepresentation

	{
		private String taskId;
		private String taskName;
		private Date taskTime;
		private String id_;
		private String name_;
		private String startName;
		private String processInstanceName;

		private Date startTime;
		private Date endTime;

		private String assignee;

		private boolean flag;

		private String processDefinitionKey;

		public TaskRepresentation(){

		}

		public TaskRepresentation(String taskId, String taskName,Date taskTime,String id_,String name_,String startName
		,String processInstanceName,String processDefinitionKey) {
			this.taskId = taskId;
			this.taskName = taskName;
			this.taskTime = taskTime;
			this.id_=id_;
			this.name_ = name_;
			this.startName=startName;
			this.processInstanceName=processInstanceName;
			this.processDefinitionKey=processDefinitionKey;
		}

		public String getProcessDefinitionKey() {
			return processDefinitionKey;
		}

		public void setProcessDefinitionKey(String processDefinitionKey) {
			this.processDefinitionKey = processDefinitionKey;
		}

		public boolean isFlag() {
			return flag;
		}

		public void setFlag(boolean flag) {
			this.flag = flag;
		}

		public String getAssignee() {
			return assignee;
		}

		public void setAssignee(String assignee) {
			this.assignee = assignee;
		}

		public Date getStartTime() {
			return startTime;
		}

		public void setStartTime(Date startTime) {
			this.startTime = startTime;
		}

		public Date getEndTime() {
			return endTime;
		}

		public void setEndTime(Date endTime) {
			this.endTime = endTime;
		}

		public String getProcessInstanceName() {
			return processInstanceName;
		}

		public void setProcessInstanceName(String processInstanceName) {
			this.processInstanceName = processInstanceName;
		}

		public String getStartName() {
			return startName;
		}

		public void setStartName(String startName) {
			this.startName = startName;
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