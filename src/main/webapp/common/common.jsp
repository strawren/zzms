<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%
	String fullUrl = request.getRequestURL().toString();
	String curUri = request.getRequestURI();
	String url = fullUrl.substring(0, fullUrl.length() - curUri.length());
	String context = request.getContextPath();
	if(context != null && (!"/".equals(context))){
    	url = url + context;
	}

	String easyuiThemeName="metro";
	Cookie cookies[] =request.getCookies();
	if(cookies!=null&&cookies.length>0){
		for(Cookie cookie : cookies){
			if (cookie.getName().equals("cookiesColor")) {
				easyuiThemeName = cookie.getValue();
				break;
			}
		}
	}
%>
	<link rel="stylesheet" type="text/css" href="${ctx }/themes/<%=easyuiThemeName %>/easyui.css">
	<script type="text/javascript" src="${ctx }/js/xheditor/jquery-1.8.0.min.js"></script>
	<script type="text/javascript" src="${ctx }/js/xheditor/xheditor-1.1.14-zh-cn.min.js" charset="utf-8"></script>
	<link rel="stylesheet" type="text/css" href="${ctx }/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="${ctx }/css/common.css">
	<script type="text/javascript" src="${ctx }/js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${ctx }/js/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="${ctx }/js/jqueryUtil.js"></script>
	<script type="text/javascript" src="${ctx }/js/json2.js"></script>
	<script type="text/javascript" src="${ctx }/js/FusionCharts/FusionCharts.js"></script>
	<script type="text/javascript" src="${ctx }/js/FusionCharts/FusionCharts.jqueryplugin.js"></script>
	<script type="text/javascript" src="${ctx }/js/FusionCharts/FusionChartsExportComponent.js"></script>
	<script type="text/javascript">
		$(function(){
				
               /* $('#tt').tabs({
                    onLoad:function(panel){
                        var plugin = panel.panel('options').title;
                    },
                    tools:[{
                            iconCls:'icon-color',
                            handler:function(){
                                //window.open("http://easyui.btboys.com/reply.html");
                              
                               $('#mm').menu({   
								    onClick:function(item){   
                            	   			var cookiesColor1=jqueryUtil.cookies.get("cookiesColor");
                            	   		if(cookiesColor1!=item.id){
								        	jqueryUtil.cookies.set("cookiesColor",item.id,30);
								        	jqueryUtil.chgSkin(item.id,cookiesColor1);
                            	   		}
								    }   
								});
                                $('#mm').menu('show', {   
								  left: 1222,   
								  top: 90   
								});
                            }
                        }],
						extractor:function(data){
							var tmp = $('<div></div>').html(data);
							data = tmp.find('#content').html();
							tmp.remove();
							return data;
						}
                });
				try{
				　　top.location.hostname;
				　　if (top.location.hostname != window.location.hostname) {
				　　　　top.location.href =window.location.href;
				　　}
				}
				catch(e){
				　　top.location.href = window.location.href;
				}*/
				
            });
	</script>
	<style type="text/css">
		body {
		    font-family:helvetica,tahoma,verdana,sans-serif;
		    font-size:13px;
		    margin:0px 0px 0px 0px;
		    padding:0px 0px 0px 0px;
		}
	</style>