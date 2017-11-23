package com.yidusoft.project.activitis.service;

import com.yidusoft.core.Result;
import com.yidusoft.core.ResultGenerator;
import com.yidusoft.project.channel.domain.ChannelManage;
import com.yidusoft.project.channel.service.ChannelManageService;
import com.yidusoft.project.system.domain.SecUser;
import com.yidusoft.project.system.service.SecUserService;
import com.yidusoft.utils.Security;
import org.activiti.engine.*;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.impl.identity.Authentication;
import org.activiti.engine.task.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jery on 2016/11/23.
 */

@Service
@Transactional
public class ChannelActivityService {
    //注入为我们自动配置好的服务
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;

    @Autowired
    private HistoryService  historyService ;

    @Autowired
    private SecUserService secUserService;

    @Autowired
    private ProcessEngine processEngine;

    @Autowired
    private IdentityService identityService;

    @Autowired
    private ChannelManageService channelManageService;

    private final static Logger logger = LoggerFactory.getLogger(ChannelActivityService.class);
    /**
     * 开始流程 输入渠道ID
     * @param channelId
     */
    public void startProcess(String channelId,String channelName,String objuri) {

        ChannelManage channelManage = channelManageService.findById(channelId);
        channelManage.setStatus(1);
        channelManageService.update(channelManage);

        //设置流程的启动人
        identityService.setAuthenticatedUserId(Security.getUser().getUserName());
        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("objid", channelId);
        variables.put("objname", channelName);
        variables.put("objuri", objuri);
        runtimeService.startProcessInstanceByKey("channelL", variables);
    }

    public Result completeTasks(Boolean launchApproved, String taskId, String msg) {
        Map<String, Object> taskVariables = new HashMap<String, Object>();
        taskVariables.put("flag", launchApproved);

        // 使用任务id,获取任务对象，获取流程实例id
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        //利用任务对象，获取流程实例id
        String processInstancesId = task.getProcessInstanceId();
        // 添加批注时候的审核人
        Authentication.setAuthenticatedUserId(Security.getUser().getUserName());

        Map map1=new HashMap();
        map1.put("userTaskApply",Security.getUser().getUserName());
        taskService.setVariablesLocal(taskId,map1);

        //添加批注
        taskService.addComment(taskId, processInstancesId, msg);
        taskService.setAssignee(taskId,Security.getUser().getUserName());
        //审批
        taskService.complete(taskId, taskVariables);
        return ResultGenerator.genSuccessResult();
    }

    public void channel(DelegateExecution execution) {
        Boolean bool = (Boolean) execution.getVariable("flag");
        String channelId = (String) execution.getVariable("objid");
        ChannelManage channelManage = channelManageService.findById(channelId);
        if (bool) {
            channelManage.setStatus(2);
            SecUser secUser = secUserService.findChannelDefaultAccount(channelManage.getId());
            secUser.setStatus(1);
            secUserService.update(secUser);
            logger.info("渠道审批通过");
        } else {
            channelManage.setStatus(4);
            logger.info("渠道审批退回");
        }
        channelManageService.update(channelManage);
    }
}
