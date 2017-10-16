package com.yidusoft.redisMq;

import com.yidusoft.project.system.domain.SecUser;

/**
 * Created by linkb on 2017/8/23.
 */
public class MsgGenerator {
    /**
     * 登录日志
     * @return
     */
    public static SysMessage genLoginLogMessage(SecUser user) {
        SysMessage sysMessage = new SysMessage();
        sysMessage.setMessageType("登录日志");
        sysMessage.setMessageTitle(user.getUserName());
        sysMessage.setMessage(user.getAccount());
        sysMessage.setDeleted(user.getAccountType());
        sysMessage.setRecid(user.getId());
        return sysMessage;
    }
}
