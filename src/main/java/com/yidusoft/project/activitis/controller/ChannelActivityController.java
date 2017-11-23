package com.yidusoft.project.activitis.controller;

/**
 * Created by jery on 2016/11/23.
 */

import com.yidusoft.core.Result;
import com.yidusoft.core.ResultGenerator;
import com.yidusoft.project.activitis.service.ChannelActivityService;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.engine.*;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.impl.ProcessEngineImpl;
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.activiti.engine.impl.context.Context;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.image.ProcessDiagramGenerator;
import org.activiti.spring.ProcessEngineFactoryBean;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

@RestController
public class ChannelActivityController {
	@Autowired
	private ChannelActivityService channelActivityService;

	@Autowired
	private ProcessEngine processEngine;

	@Autowired
	private RepositoryService repositoryService;

	@Autowired
	private RuntimeService runtimeService;

	@Autowired
	private HistoryService historyService;

	@Autowired
	private ManagementService managementService;

	//开启渠道审批流程实例
	@RequestMapping(value = "/channelProcess", method = RequestMethod.POST)
	public Result startProcessInstance( String channelId, String channelName) {
		String uri = "/web/channel/manage/acdetail?id="+channelId;
		channelActivityService.startProcess(channelId,channelName,uri);
		return  ResultGenerator.genSuccessResult();
	}



	//审批渠道
	@RequestMapping(value = "/channelComplete/{channelAapproval_}/{taskId}/{msg}", method = RequestMethod.POST)
	public Result complete(@PathVariable Boolean channelAapproval_, @PathVariable String taskId, @PathVariable String msg) {

		 return channelActivityService.completeTasks(channelAapproval_, taskId,msg);
	}

	public InputStream viewImage() {
		// 创建仓库服务对对象
		RepositoryService repositoryService = processEngine.getRepositoryService();
		// 从仓库中找需要展示的文件
		String deploymentId = "280001";
		List<String> names = repositoryService.getDeploymentResourceNames(deploymentId);
		String imageName = null;
		for (String name : names) {
			if (name.indexOf(".png") >= 0) {
				imageName = name;
			}
		}

		File f=null;
		if (imageName != null) {
			 f= new File(imageName);
			// 通过部署ID和文件名称得到文件的输入流
//			 in = repositoryService.getResourceAsStream(deploymentId, imageName);
		}
		FileInputStream in = null;
		try {
			in = new FileInputStream(f);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return in;
	}

		/**
         * 根据流程的key生成图片
         *
         * @param
         * @param response
         * @param wfKey 流程定义的key
         */
	@RequestMapping(value="/readResource")
	public void readResource(String wfKey, HttpServletResponse response) {

		try {
			String processDefinitionId = historyService
					.createHistoricProcessInstanceQuery()
					.processInstanceId(wfKey).singleResult()
					.getProcessDefinitionId();

			ProcessDefinitionEntity processDefinition = (ProcessDefinitionEntity) ((RepositoryServiceImpl) repositoryService)
					.getDeployedProcessDefinition(processDefinitionId);

			InputStream imageStream = viewImage();


			// 输出资源内容到相应对象
			byte[] b = new byte[1024];
			int len;
			while ((len = imageStream.read(b, 0, 1024)) != -1) {
				response.getOutputStream().write(b, 0, len);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}



	public InputStream getFlowImageByKey(String flowKey){
		ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionKey(flowKey).latestVersion()
				.singleResult();
		BpmnModel bpmnModel = repositoryService.getBpmnModel(processDefinition.getId());
		// 不使用spring请使用下面的两行代码
//		ProcessEngineImpl defaultProcessEngine = (ProcessEngineImpl) ProcessEngines.getDefaultProcessEngine();
//		Context.setProcessEngineConfiguration(defaultProcessEngine.getProcessEngineConfiguration());

		// 使用spring注入引擎请使用下面的这行代码
		 Context.setProcessEngineConfiguration((ProcessEngineConfigurationImpl) processEngine.getProcessEngineConfiguration());
		ProcessDiagramGenerator a = processEngine.getProcessEngineConfiguration().getProcessDiagramGenerator();

		return a.generateDiagram(bpmnModel, "png", "宋体", "微软雅黑", "黑体",null, 2.0);
	}
}