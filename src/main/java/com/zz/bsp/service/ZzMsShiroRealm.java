package com.zz.bsp.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.zz.bsp.orm.query.PropertyFilter;
import com.zz.test.model.user.UserModel;
import com.zz.test.service.user.UserService;

public class ZzMsShiroRealm extends AuthorizingRealm {
	protected Log log = LogFactory.getLog(getClass());
	
	@Autowired
    UserService userService;
	
	/**
	 * 授权
	 */
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
		log.debug("get auth info --》" + arg0);
		return null;
	}

	/**
	 * 认证
	 */
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
		log.debug("begin to auth -->" + authcToken);
		
		UsernamePasswordToken token = (UsernamePasswordToken)authcToken;
        String userName = token.getUsername();
		String password = String.valueOf(token.getPassword());
		
		PropertyFilter filter = new PropertyFilter("EQS_USER_NAME", userName);
        List<PropertyFilter> filterList = new ArrayList<PropertyFilter>(1);
        filterList.add(filter);
        
        List<UserModel> userList = userService.search(filterList);
        if(userList != null && userList.size() > 0) {
        	log.info("find user :" + userName);
        	UserModel dbUser = userList.get(0);
        	if(password.equalsIgnoreCase(dbUser.getUserPwd())) {
        		log.debug("login ok ~~~~");
        		return new SimpleAuthenticationInfo(userName,password, getName());
        	}
        	else {
        		log.debug("login fail req pwd ->" + password);
        		return null;
        	}
        }
        return null;
	}
}
