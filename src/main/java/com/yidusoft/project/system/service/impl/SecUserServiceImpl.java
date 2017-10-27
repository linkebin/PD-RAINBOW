package com.yidusoft.project.system.service.impl;

import com.alibaba.fastjson.JSON;
import com.yidusoft.core.AbstractService;
import com.yidusoft.core.Result;
import com.yidusoft.core.ResultGenerator;
import com.yidusoft.project.system.dao.SecUserMapper;
import com.yidusoft.project.system.domain.SecRole;
import com.yidusoft.project.system.domain.SecUser;
import com.yidusoft.project.system.service.SecUserService;
import com.yidusoft.utils.CodeHelper;
import com.yidusoft.utils.PasswordHelper;
import com.yidusoft.utils.TreeNode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;


/**
 * Created by CodeGenerator on 2017/07/16.
 */
@Service
@Transactional
public class SecUserServiceImpl extends AbstractService<SecUser> implements SecUserService {
    @Resource
    private SecUserMapper secUserMapper;

    @Override
    public SecUser findUserInfoAndOrgInfoById(String id) {
        return secUserMapper.findUserInfoAndOrgInfoById(id);
    }

    @Override
    public List<SecUser> findSecUserOrgInfos(List<String> ids) {
        return secUserMapper.findSecUserOrgInfos(ids);
    }

    @Override
    public List<SecUser> userListByRoleId(String roleId) {

        return secUserMapper.userListByRoleId(roleId);
    }

    @Override
    public SecUser getSecUserInfo(String account) {
        return secUserMapper.getSecUserInfo(account);
    }

    @Override
    public SecUser isSecUser(String account) {
        return secUserMapper.isSecUser(account);
    }

    @Override
    public int isSecUser1(String account) {
        return secUserMapper.isSecUser1(account);
    }

    @Override
    public List<SecUser> roleUserName(String id) { return secUserMapper.roleUserName(id); }

    @Override

    public List roleChooseUser(Map map){ return secUserMapper.roleChooseUser(map);};

    @Override
    public  List<SecUser> getQueryAll(SecUser secUser){
        return secUserMapper.getQueryAll(secUser);
    }

    @Override
    public List<SecRole> getUserNoRoleList(String userId) {
        return secUserMapper.getUserNoRoleList(userId);
    }

    @Override
    public int getQueryAllTotal(SecUser secUser) {
        return secUserMapper.getQueryAllTotal(secUser);
    }

    @Override
    public List<TreeNode> userTreeList(String id) {
        return secUserMapper.userTreeList(id);
    }

    @Override
    public List<SecUser> findOrgUserList(String orgId) {
        return secUserMapper.findOrgUserList(orgId);
    }

    @Override
    public int isExist(String params, String type){
        Example example = new Example(SecUser.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("deleted",0);
        List<SecUser> secUsers = null;
        if ("account".equals(type)){
            criteria.andEqualTo("account",params);
            secUsers = secUserMapper.selectByCondition(example);
        }else if ("mobile".equals(type)){
            criteria.andEqualTo("mobile",params);
            secUsers = secUserMapper.selectByCondition(example);
        }else {
            criteria.andEqualTo("email",params);
            secUsers = secUserMapper.selectByCondition(example);
        }
        return secUsers.size();
    }

    @Override
    public Result updateUser(String params){
        SecUser secUser = JSON.parseObject(params, SecUser.class);
        SecUser oldUser = secUserMapper.selectByPrimaryKey(secUser.getId());
        String account = secUser.getAccount();
        if (account!=null){//判断账号是否唯一
            if (isExist(account,"account")>0&&!account.equals(oldUser.getAccount())){
                return ResultGenerator.genFailResult("该账号已存在！");
            }
        }
        String mobile = secUser.getMobile();
        if (mobile!=null){//判断手机是否唯一
            if (isExist(mobile,"mobile")>0&&!mobile.equals(oldUser.getMobile())){
                return ResultGenerator.genFailResult("该手机号已存在！");
            }
        }
        String email = secUser.getEmail();
        if (email!=null){//判断账号是否唯一
            if (isExist(email,"email")>0&&!email.equals(oldUser.getEmail())){
                return ResultGenerator.genFailResult("该邮箱已存在！");
            }
        }

//        if ("".equals(secUser.getUserPass())||secUser.getUserPass()==null){//未修改密码
//            secUser.setUserPass(oldUser.getUserPass());
//        }else{
//            //加密
//            PasswordHelper.encryptPassword(secUser);
//        }
        if( secUser.getHeadImg()==null||secUser.getHeadImg().equals("") ){
            secUser.setHeadImg(oldUser.getHeadImg());
        }
        update(secUser);
        return ResultGenerator.genSuccessResult();
    }

    @Override
    public Result addUser(String params){
        SecUser secUser = JSON.parseObject(params, SecUser.class);
        String account = secUser.getAccount();
        if (account!=null){//判断账号是否唯一
            if (isExist(account,"account")>0){
                return ResultGenerator.genFailResult("该账号已存在！");
            }
        }
        String mobile = secUser.getMobile();
        if (mobile!=null){//判断手机是否唯一
            if (isExist(mobile,"mobile")>0){
                return ResultGenerator.genFailResult("该手机号已存在！");
            }
        }
        String email = secUser.getEmail();
        if (email!=null){//判断账号是否唯一
            if (isExist(email,"email")>0){
                return ResultGenerator.genFailResult("该邮箱已存在！");
            }
        }

        String id = UUID.randomUUID().toString();
        secUser.setDeleted(0);
        secUser.setId(id);
        secUser.setCreateTime(new Date());
        secUser.setAccountType(0);
        //判断账号是否存在
        SecUser secUser1=isSecUser(secUser.getAccount());
        if(secUser1!=null){
            return ResultGenerator.genFailResult("账号已存在！");
        }else{
            //加密
            PasswordHelper.encryptPassword(secUser);
            save(secUser);
            return ResultGenerator.genSuccessResult(secUser);
        }
    }

    @Override
    public SecUser findSecUserByInviterCode(String inviterCode) {
        return secUserMapper.findSecUserByInviterCode(inviterCode);
    }

    @Override
    public List<TreeNode> leadingTreeList(SecUser secUser) {
        return secUserMapper.leadingTreeList(secUser);
    }

    @Override
    public List<SecUser> findOrgByUsers(SecUser secUser) {
        return secUserMapper.findOrgByUsers(secUser);
    }
}
