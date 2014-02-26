/**
 * Copyright : http://www.orientpay.com , 2007-2012
 * Project : oecs-g2-component-trunk
 * $Id$
 * $Revision$
 * Last Changed by qinxiang at 2011-9-18 下午6:58:27
 * $URL$
 * 
 * Change Log
 * Author      Change Date    Comments
 *-------------------------------------------------------------
 * qinxiang     2011-9-18        Initailized
 */

package com.zz.test.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zz.bsp.dao.Dao;
import com.zz.bsp.service.DefaultEntityService;
import com.zz.test.dao.user.UserDao;
import com.zz.test.model.user.UserModel;


/**
 * 登帐记录Service
 *
 */
@Service("userService")
@Transactional(timeout=10)
public class UserService extends DefaultEntityService<UserModel, Long>{
 
    @Autowired
    UserDao userDao;

    protected Dao<UserModel, Long> getDao() {
        return userDao;
    }
}
