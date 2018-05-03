package com.yidusoft.redisMq;

import com.yidusoft.project.monitor.domain.OperLog;
import com.yidusoft.project.system.domain.SecUser;

/**
 * Created by linkb on 2017/8/23.
 */
public class MsgGenerator {
    /**
     * 登录日志
     * @return
     */
    public static Message genLoginLogMessage(SecUser user) {
        Message msg = new Message();
        msg.setType("登录日志");
        msg.setObject(user);
        return msg;
    }
    /**
     * 操作日志
     * @return
     */
    public static Message genOperMessage(OperLog operLog) {
        Message msg = new Message();
        msg.setType("操作日志");
        msg.setObject(operLog);
        return msg;
    }
}
