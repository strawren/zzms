/**
 * Copyright : http://www.orientpay.com , 2007-2012
 * Project : oecs-g2-framework-trunk
 * $Id$
 * $Revision$
 * Last Changed by jason at 2011-11-24 下午1:58:16
 * $URL$
 * 
 * Change Log
 * Author      Change Date    Comments
 *-------------------------------------------------------------
 * jason     2011-11-24        Initailized
 */

package com.zz.bsp.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.zz.bsp.constants.Constants;


/**
 * 重复提交过滤器
 *
 */
public class TokenCheckFilter  implements Filter {
    protected final Log log = LogFactory.getLog(getClass());

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        
        String formToken = request.getParameter(Constants.OECS_TOKEN_CHECK_NAME);
        if (StringUtils.isBlank(formToken)) {
            chain.doFilter(request, response);
            return;
        }

        Object tokenObj = request.getSession().getAttribute(Constants.OECS_TOKEN_CHECK_NAME_IN_SESSION);
        if (tokenObj != null && StringUtils.isNotEmpty(formToken) && tokenObj.equals(formToken)) {
            log.debug("check token ok !!!");
            request.getSession().removeAttribute(Constants.OECS_TOKEN_CHECK_NAME_IN_SESSION);
            chain.doFilter(request, response);
            return;
        } 
        else {
            log.debug("check token fail !!!");
            String errBackUrl = request.getParameter(Constants.OECS_TOKEN_ERR_BACK_URL_NAME);
            response.sendRedirect(StringUtils.isBlank(errBackUrl) ? request.getHeader("Referer") : errBackUrl);
            return;
        }
    }

    public void init(FilterConfig arg0) throws ServletException {
        log.debug("init ....");
    }
    
    public void destroy() {
        log.debug("destory !!!");
    }
}
