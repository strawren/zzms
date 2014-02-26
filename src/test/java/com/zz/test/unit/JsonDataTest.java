package com.zz.test.unit;

import java.util.ArrayList;
import java.util.List;

import com.zz.bsp.core.UiMenu;
import com.zz.bsp.util.JsonUtils;

public class JsonDataTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		UiMenu uiMenu = new UiMenu();
        uiMenu.setIconCls("icon-sys");
        uiMenu.setId("1");
        uiMenu.setName("我的菜单");
        uiMenu.setPid("0");
        uiMenu.setUrl("javascript:void(0);");
        
        List<UiMenu> subList = new ArrayList<UiMenu>(1);
        UiMenu subUiMenu = new UiMenu();
        subUiMenu.setIconCls("icon-adds");
        subUiMenu.setId("11");
        subUiMenu.setName("用户管理");
        subUiMenu.setPid("1");
        subUiMenu.setUrl("user/list.html");
        
        subList.add(subUiMenu);
        uiMenu.setChild(subList);
        
        System.out.println(JsonUtils.objectToJson(uiMenu));

	}

}
