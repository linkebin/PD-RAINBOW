package com.yidusoft.project.activityExamine.service;

import com.yidusoft.core.Result;
import com.yidusoft.core.ResultGenerator;
import com.yidusoft.project.business.service.LaunchActivitiesService;
import com.yidusoft.project.channel.domain.ChannelManage;
import com.yidusoft.project.channel.service.ChannelManageService;
import com.yidusoft.project.system.domain.SecUser;
import com.yidusoft.project.system.service.SecUserService;
import com.yidusoft.utils.Security;
import org.activiti.engine.HistoryService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.impl.identity.Authentication;
import org.activiti.engine.task.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
    private LaunchActivitiesService launchActivitiesService ;

    @Autowired
    private SecUserService secUserService;

    @Autowired
    private ProcessEngine processEngine;

    private final static Logger logger = LoggerFactory.getLogger(ChannelActivityService.class);
    /**
     * 开始流程 输入渠道ID
     * @param channelId
     */
    public void startProcess(String channelId,String channelName) {
        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("ID_S", channelId);
        variables.put("NAME_S", channelName);
        runtimeService.startProcessInstanceByKey("channelProcess", variables);
    }

    /**
     * 获得用户的任务列表、待办
     * @param assignee
     * @return
     */
    public List<Task> getTasks(String assignee) {
        return taskService.createTaskQuery().taskCandidateUser(assignee).list();
    }

    //审批
    public Result completeTasks(Boolean launchApproved, String taskId, String msg) {
        Map<String, Object> taskVariables = new HashMap<String, Object>();
        taskVariables.put("channelAapproval_", launchApproved);

        // 使用任务id,获取任务对象，获取流程实例id
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        //利用任务对象，获取流程实例id
        String processInstancesId = task.getProcessInstanceId();
        // 添加批注时候的审核人
        Authentication.setAuthenticatedUserId(Security.getUserId());
        //添加批注
        taskService.addComment(taskId, processInstancesId, msg);
        //审批
        taskService.complete(taskId, taskVariables);
        return ResultGenerator.genSuccessResult();
    }

    /**
     * 获取审批人
     *
     * @param execution
     * @return
     */
    public List<String> findUsers(DelegateExecution execution) {
        List<SecUser> secUsers = secUserService.userListByRoleId("c47beaee-4935-4961-a4d2-bc60e3f66ac2");
        List<String> userIds = new ArrayList<>();
        for (SecUser secUser:secUsers) {
            userIds.add(secUser.getAccount());
        }
        return userIds;
    }

    @Autowired
    private ChannelManageService channelManageService;


    public void channel(DelegateExecution execution) {
        Boolean bool = (Boolean) execution.getVariable("channelAapproval_");
        if (bool) {
            String channelId = (String) execution.getVariable("ID_S");
            ChannelManage channelManage = channelManageService.findById(channelId);
            channelManage.setStatus(2);
            channelManageService.update(channelManage);
            SecUser secUser = secUserService.findChannelDefaultAccount(channelManage.getId());
            secUser.setStatus(1);
            secUserService.update(secUser);

            logger.info("渠道审批通过");
        } else {
            logger.info("渠道审批退回");
        }
    }

}
