/**
 * Copyright : http://www.orientpay.com , 2007-2012
 * Project : oecs-g2-uams-admin-trunk
 * $Id$
 * $Revision$
 * Last Changed by shitz at 2011-9-13 上午11:00:03
 * $URL$
 *
 * Change Log
 * Author Change Date Comments
 * -------------------------------------------------------------
 * shitz 2011-9-13 Initailized
 */

package com.zz.test.web.action.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.zz.bsp.core.GridViewDto;
import com.zz.bsp.orm.query.Page;
import com.zz.bsp.orm.query.PropertyFilter;
import com.zz.bsp.util.JsonUtils;
import com.zz.bsp.util.PropertyFilterUtils;
import com.zz.bsp.web.action.BaseMultiActionController;
import com.zz.test.model.user.UserModel;
import com.zz.test.service.user.UserService;

/**
 * 备付金账户余额勾兑Controller
 *
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseMultiActionController {
	@Autowired
	UserService userService;
	
    /**
     * 分页查询资金划拨
     *
     * @param request
     * @param response
     * @param page
     * @return
     */
    @RequestMapping("/page.html")
    public ModelAndView page(HttpServletRequest request, HttpServletResponse response) {
        log.debug("page method begin...");
        return new ModelAndView("user/page");
    }

    /**
     * 分页查询资金划拨
     *
     * @param request
     * @param response
     * @param page
     * @return
     */
    @RequestMapping("/list.html")
    public ModelAndView list(HttpServletRequest request, HttpServletResponse response, Page<UserModel> page) {
        log.debug("list method begin...");
        
        System.out.println("-----" + request.getParameterMap());
        List<PropertyFilter> filters = PropertyFilterUtils.buildMutilPropertyFilters(request);
        System.out.println("-----" + filters);
        
        page = userService.search(page, filters);
        log.debug("list method end.");
        
        GridViewDto<UserModel> dto = new GridViewDto<UserModel>();
        dto.setRows(page.getResult());
        dto.setTotal(page.getTotalCount());
        
        ModelAndView mv = new ModelAndView("user/list", "dto", JsonUtils.objectToJson(dto));
        return mv;
    }
    
    /**
     * 显示资金划拨详细信息
     *
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping("/view.html")
    public ModelAndView view(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView("user/view");
        Long id = Long.parseLong(request.getParameter("id"));
        UserModel model = userService.get(id);
        mv.addObject("model", model);
        return mv;
    }

    /**
     * 资金划拨勾兑 (权限判断)
     *
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping("/edit.html")
    public ModelAndView edit(HttpServletRequest request, HttpServletResponse response, UserModel model) {
        log.debug("edit method begin, id->" + model.getId());
        String id = request.getParameter("id");
        ModelAndView mv = new ModelAndView("user/edit");
        if (id != null) {
            model = userService.get(new Long(id));
            mv.addObject("model", model);
        }
        else {
        	mv.addObject("model", new UserModel());
        }
        return mv;
    }


    /**
     * 保存或更新
     *
     * @param request
     * @param response
     * @param model
     *            资金划拨
     * @return
     */
    @RequestMapping("/save.html")
    public ModelAndView save(HttpServletRequest request, HttpServletResponse response, UserModel model) {
        log.debug("save method begin...");
        
        ModelAndView mv = new ModelAndView("user/save", "result", true);
        String id = request.getParameter("id");
        log.debug("model --->" + model);
        
        if(StringUtils.isBlank(id) || "0".equals(id)) {
        	userService.save(model);
        }
        else {
        	userService.update(model);
        }
        
        log.debug("saveOrUpdate method end.");
        return mv;
    }

    /**
     * 根据操作员id或ids取消备付金账户信息
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/del.html")
    public ModelAndView del(HttpServletRequest request, HttpServletResponse response) {
        log.debug("del method begin...");
        
        String id = getModelId(request);
        ModelAndView mv = new ModelAndView("user/result", "result", true);
        if(StringUtils.isNotBlank(id)) {
        	userService.delete(Long.parseLong(id));
        }
        
        log.debug("del method end.");
        return mv;
    }
    
    /**
     * 分页查询资金划拨
     *
     * @param request
     * @param response
     * @param page
     * @return
     */
    @RequestMapping("/search.html")
    public ModelAndView search(HttpServletRequest request, HttpServletResponse response) {
        log.debug("page method begin...");
        return new ModelAndView("user/search");
    }
}
