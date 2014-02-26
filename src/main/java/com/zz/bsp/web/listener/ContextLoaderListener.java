/**
 * Copyright : http://www.orientpay.com , 2007-2012
 * Project : oecs-g2-framework-trunk
 * $Id$
 * $Revision$
 * Last Changed by jason at 2011-9-13 下午4:47:42
 * $URL$
 * 
 * Change Log
 * Author      Change Date    Comments
 *-------------------------------------------------------------
 * jason     2011-9-13        Initailized
 */

package com.zz.bsp.web.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.zz.bsp.util.SpringContextUtils;


/**
 * 系统加载监听器
 *
 */
public class ContextLoaderListener implements ServletContextListener {
    protected Log log = LogFactory.getLog(getClass());
    
    public void contextInitialized(ServletContextEvent arg0) {
        log.debug("contextInitialized begin...");
        
        ApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(arg0.getServletContext());
        
        log.debug("init springContextUtils...");
        SpringContextUtils.init(ctx);
        log.debug("contextInitialized end");
    }

    public void contextDestroyed(ServletContextEvent arg0) {
        log.debug("contextDestroyed !");
    }
}
