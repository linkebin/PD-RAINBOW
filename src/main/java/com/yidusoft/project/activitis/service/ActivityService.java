package com.yidusoft.project.activitis.service;

import com.yidusoft.core.Result;
import com.yidusoft.core.ResultGenerator;
import com.yidusoft.project.business.domain.LaunchActivities;
import com.yidusoft.project.business.service.LaunchActivitiesService;
import com.yidusoft.project.system.domain.SecUser;
import com.yidusoft.project.system.service.SecUserService;
import com.yidusoft.utils.CodeHelper;
import com.yidusoft.utils.Security;
import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
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
public class ActivityService {
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
    private IdentityService identityService;

    @Autowired
    private LaunchActivitiesService launchActivitiesService;

    @Value("${server.port}")
    private String port;
    @Value("${server.ip}")
    private String ip;

    private final static Logger logger = LoggerFactory.getLogger(ActivityService.class);
    /**
     * 开始流程 输入活动ID
     * @param launchId
     */
    public void startProcess(String launchId,String launchName,String objuri) {
        identityService.setAuthenticatedUserId(Security.getUser().getUserName());
        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("objid", launchId);
        variables.put("objname", launchName);
        variables.put("objuri", objuri);
        runtimeService.startProcessInstanceByKey("launchL",variables);
    }

    /**
     * 获得用户的任务列表、待办
     * @param assignee
     * @return
     */
    public List<Task> getTasks(String assignee,Integer page,Integer pagesize) {
        return taskService.createTaskQuery().taskCandidateUser(assignee).listPage(page*pagesize,pagesize);
    }

    public int getTasksCount(String assignee) {
        return taskService.createTaskQuery().taskCandidateUser(assignee).list().size();
    }

    //审批
    public Result completeTasks(Boolean launchApproved, String taskId, String msg) {
        Map<String, Object> taskVariables = new HashMap<String, Object>();
        taskVariables.put("flag", launchApproved);

        // 使用任务id,获取任务对象，获取流程实例id
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();

        Map map1=new HashMap();
        map1.put("userTaskApply",Security.getUser().getUserName());
        taskService.setVariablesLocal(taskId,map1);

        //利用任务对象，获取流程实例id
        String processInstancesId = task.getProcessInstanceId();
        // 添加批注时候的审核人
        Authentication.setAuthenticatedUserId(Security.getUser().getUserName());
        //添加批注
        taskService.addComment(taskId, processInstancesId, msg);
        taskService.setAssignee(taskId,Security.getUser().getUserName());
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

    /**
     * 流程轨迹
     */
    public void findCommentByTaskId() {

        String taskId = "42503"; // 现在的任务id
        List list = new ArrayList();
        //使用当前的任务ID，查询当前流程对应的历史任务ID

        //使用当前任务ID，获取当前任务对象
        Task task = taskService.createTaskQuery()//
                .taskId(taskId)//使用任务ID查询
                .singleResult();
        //获取流程实例ID
        String processInstanceId = task.getProcessInstanceId();
        //使用流程实例ID，查询历史任务，获取历史任务对应的每个任务ID
        List htiList = historyService.createHistoricTaskInstanceQuery()//历史任务表查询
                .processInstanceId(processInstanceId)//使用流程实例ID查询
                .list();
        //遍历集合，获取每个任务ID
//        if (htiList != null && htiList.size() > 0) {
//            for (HistoricTaskInstance hti : htiList) {
//            //任务ID
//                String htaskId = hti.getId();
//            //获取批注信息
//                List taskList = taskService.getTaskComments(htaskId);//对用历史完成后的任务ID
//                list.addAll(taskList);
//            }
//        }
//        list = taskService.getProcessInstanceComments(processInstanceId);
//
//
//        for (Comment com : list) {
//            System.out.println("ID:" + com.getId());
//            System.out.println("Message:" + com.getFullMessage());
//            System.out.println("TaskId:" + com.getTaskId());
//            System.out.println("ProcessInstanceId:" + com.getProcessInstanceId());
//            System.out.println("UserId:" + com.getUserId());
//        }
//
//        System.out.println(list);
    }
    /**
     *
     */



    public void launch(DelegateExecution execution) {
        Boolean bool = (Boolean) execution.getVariable("flag");
        String launchId = (String) execution.getVariable("objid");
        LaunchActivities launchActivities=launchActivitiesService.findById(launchId);
        if (bool) {
            launchActivities.setActivityStatus(2);
            launchActivities.setActivityPorn(CodeHelper.randomCode(8));
            launchActivities.setUestionnaireUri("http://"+ip+"/web/activities/fillingPage");
            logger.info("活动审批通过");
        } else {
            launchActivities.setActivityStatus(3);
            logger.info("活动审批退回");
        }
        launchActivitiesService.update(launchActivities);
    }

}
