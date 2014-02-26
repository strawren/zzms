package com.zz.test.web.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.zz.bsp.core.UiMenu;
import com.zz.bsp.orm.query.PropertyFilter;
import com.zz.bsp.util.JsonUtils;
import com.zz.bsp.web.action.BaseMultiActionController;
import com.zz.test.model.user.UserModel;
import com.zz.test.service.user.UserService;

@Controller
public class IndexController extends BaseMultiActionController{
    @Autowired
    UserService userService;
    
    @RequestMapping("/index.html")
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response){
        log.debug("index begin...");
        
        ModelAndView mv = new ModelAndView("index");
        return mv;
    }
    
    @RequestMapping("/login.html")  
    public ModelAndView login(HttpServletRequest request, HttpServletResponse response, UserModel userModel){
        log.debug("login begin...");
        boolean isOk = false;
        
        PropertyFilter filter = new PropertyFilter("EQS_USER_NAME", userModel.getUserName());
        List<PropertyFilter> filterList = new ArrayList<PropertyFilter>(1);
        filterList.add(filter);
        
        List<UserModel> userList = userService.search(filterList);
        if(userList != null && userList.size() > 0) {
        	log.info("find user :" + userModel.getUserName());
        	UserModel dbUser = userList.get(0);
        	if(userModel.getUserPwd().equalsIgnoreCase(dbUser.getUserPwd())) {
        		log.debug("login ok ~~~~");
        		isOk = true;
        	}
        	else {
        		log.debug("login fail req pwd ->" + userModel.getUserPwd());
        	}
        }
        return new ModelAndView("login", "result", isOk);
    }
    
    //获取菜单
    @RequestMapping("/menu.html")
    public ModelAndView menu(HttpServletRequest request, HttpServletResponse response, String menuCode){
        log.debug("menu begin, menu code : " +menuCode);
        
        List<UiMenu> menuList = new ArrayList<UiMenu>(1);
        
        UiMenu uiMenu = new UiMenu();
        uiMenu.setIconCls("icon-sys");
        uiMenu.setId("1");
        uiMenu.setName("我的菜单");
        uiMenu.setPid("0");
        uiMenu.setUrl("javascript:void(0);");
        menuList.add(uiMenu);
        
        UiMenu uiMenu2 = new UiMenu();
        uiMenu2.setIconCls("icon-sys");
        uiMenu2.setId("2");
        uiMenu2.setName("空菜单");
        uiMenu2.setPid("0");
        uiMenu2.setUrl("javascript:void(0);");
        menuList.add(uiMenu2);
        
        List<UiMenu> subList = new ArrayList<UiMenu>(1);
        UiMenu subUiMenu = new UiMenu();
        subUiMenu.setIconCls("icon-adds");
        subUiMenu.setId("11");
        subUiMenu.setName("用户管理");
        subUiMenu.setPid("1");
        subUiMenu.setUrl(request.getContextPath() + "/user/page.html");
        
        subList.add(subUiMenu);
        uiMenu.setChild(subList);
        
        ModelAndView mv = new ModelAndView("menu", "uiMenu", JsonUtils.objectToJson(menuList));
        return mv;
    }
}
