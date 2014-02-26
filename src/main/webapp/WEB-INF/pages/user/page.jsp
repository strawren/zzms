<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"  "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <base href="${ctx }" />
    <title>用户管理</title>
	<%@ include file="/common/meta.jsp"%>
	<%@ include file="/common/variable.jsp"%>
	<%@ include file="/common/common.jsp"%>
	<script type="text/javascript">
			var $dg;
			var $grid;
			$(function() {
				 $dg = $("#dg");
				 $grid=$dg.datagrid({
					url : "${ctx}/user/list.html",
					width : 'auto',
					height : $(this).height()-85,
					pagination:true,
					rownumbers:true,
					border:true,
					striped:true,
					singleSelect:true,
					columns : [ [ {field : 'userName',title : '用户名',width : parseInt($(this).width()*0.1),editor : {type:'validatebox',options:{required:true}}},
					              {field : 'userPwd',title : '密码',width : parseInt($(this).width()*0.1),align : 'left',editor : "text"}
					          ] ],toolbar:'#tb'
				});
				//搜索框
				$("#searchbox").searchbox({ 
					 menu:"#mm", 
					 prompt :'模糊查询',
				    searcher:function(value,name){   
				    	//var str="{\"searchName\":\""+name+"\",\"searchValue\":\""+value+"\"}";
				    	var str="{\"" + name + "\":\""+value +"\"}";
			            var obj = eval('('+str+')');
			            $dg.datagrid('reload',obj); 
				    }
				   
				}); 
			});
			function endEdit(){
				var flag=true;
				var rows = $dg.datagrid('getRows');
				for ( var i = 0; i < rows.length; i++) {
					$dg.datagrid('endEdit', i);
					var temp=$dg.datagrid('validateRow', i);
					if(!temp){flag=false;}
				}
				return flag;
			}
			function addRows(){
				$dg.datagrid('appendRow', {});
				var rows = $dg.datagrid('getRows');
				$dg.datagrid('beginEdit', rows.length - 1);
			}
			function editRows(){
				var rows = $dg.datagrid('getSelections');
				$.each(rows,function(i,row){
					if (row) {
						var rowIndex = $dg.datagrid('getRowIndex', row);
						$dg.datagrid('beginEdit', rowIndex);
					}
				});
			}
			function removeRows(){
				var rows = $dg.datagrid('getSelections');
				$.each(rows,function(i,row){
					if (row) {
						var rowIndex = $dg.datagrid('getRowIndex', row);
						$dg.datagrid('deleteRow', rowIndex);
					}
				});
			}
			function saveRows(){
				if(endEdit()){
					if ($dg.datagrid('getChanges').length) {
						var inserted = $dg.datagrid('getChanges', "inserted");
						var deleted = $dg.datagrid('getChanges', "deleted");
						var updated = $dg.datagrid('getChanges', "updated");
						
						var effectRow = new Object();
						if (inserted.length) {
							effectRow["inserted"] = JSON.stringify(inserted);
						}
						if (deleted.length) {
							effectRow["deleted"] = JSON.stringify(deleted);
						}
						if (updated.length) {
							effectRow["updated"] = JSON.stringify(updated);
						}
						$.post("${ctx}/user/save.html", effectRow, function(rsp) {
							if(rsp.status){
								$dg.datagrid('acceptChanges');
							}
							$.messager.alert(rsp.title, rsp.message);
						}, "JSON").error(function() {
							$.messager.alert("提示", "提交错误了！");
						});
					}
				}else{
					$.messager.alert("提示", "字段验证未通过!请查看");
				}
			}
			//删除
			function delRows(){
				var row = $dg.datagrid('getSelected');
				if(row){
					var rowIndex = $dg.datagrid('getRowIndex', row);
					parent.$.messager.confirm("提示","确定要删除记录吗?",function(r){  
					    if (r){  
					    	$dg.datagrid('deleteRow', rowIndex);
							$.ajax({
								url:"${ctx}/user/del.html",
								data: "id="+row.id,
								success: function(rsp){
									parent.$.messager.show({
										title : rsp.title,
										msg : rsp.message,
										timeout : 1000 * 2
									});
								}
							});
					    }  
					});
				}else{
					parent.$.messager.show({
						title : "提示",
						msg :"请选择一行记录!",
						timeout : 1000 * 2
					});
				}
			}
			//弹窗修改
			function updRowsOpenDlg() {
				var row = $dg.datagrid('getSelected');
				if (row) {
					parent.$.modalDialog({
						title : '编辑用户',
						width : 600,
						height : 400,
						href : "${ctx}/user/edit.html",
						onLoad:function(){
							var f = parent.$.modalDialog.handler.find("#form");
							f.form("load", row);
						},			
						buttons : [ {
							text : '编辑',
							iconCls : 'icon-ok',
							handler : function() {
								parent.$.modalDialog.openner= $grid;//因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
								var f = parent.$.modalDialog.handler.find("#form");
								f.submit();
							}
						}, {
							text : '取消',
							iconCls : 'icon-cancel',
							handler : function() {
								parent.$.modalDialog.handler.dialog('destroy');
								parent.$.modalDialog.handler = undefined;
							}
						}
						]
					});
				}else{
					parent.$.messager.show({
						title :"提示",
						msg :"请选择一行记录!",
						timeout : 1000 * 2
					});
				}
			}
			//弹窗增加
			function addRowsOpenDlg() {
				parent.$.modalDialog({
					title : '添加用户',
					width : 600,
					height : 400,
					href : '${ctx}/user/edit.html',
					buttons : [ {
						text : '保存',
						iconCls : 'icon-ok',
						handler : function() {
							parent.$.modalDialog.openner= $grid;//因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
							var f = parent.$.modalDialog.handler.find("#form");
							f.submit();
						}
					}, {
						text : '取消',
						iconCls : 'icon-cancel',
						handler : function() {
							parent.$.modalDialog.handler.dialog('destroy');
							parent.$.modalDialog.handler = undefined;
						}
					}
					]
				});
			}
			
			//高级搜索 删除 row
			function tbCompanySearchRemove(curr) {
					$(curr).closest('tr').remove();
			}
			//高级查询
			function tbsCompanySearch() {
				jqueryUtil.gradeSearch($dg,"#tbCompanySearchFm","${ctx}/user/search.html");
			}
			//excel导出
			function exportExcel(){
				var rows = $dg.datagrid("getRows");
				if(rows.length){
					var isCheckedIds=[];
					$.each(rows,function(i,row){
						if (row) {
							isCheckedIds.push(row.companyId);
						}
					});
					//window.location.href="${ctx}/excel/export.html?isCheckedIds="+isCheckedIds;
					parent.$.messager.show({
						title :"提示",
						msg :"暂不提供此功能!",
						timeout : 1000 * 2
					});
				}
				else{
					parent.$.messager.show({
						title :"提示",
						msg :"暂无导出数据!",
						timeout : 1000 * 2
					});
				}
			}
		</script>
  </head>
  <body>
      <div data-options="region:'center',border : false">
  		<div class="well well-small" style="margin-left: 5px;margin-top: 5px">
				<span class="badge">提示</span>
				<p>
					在此你可以对<span class="label-info"><strong>用户管理</strong></span>进行编辑!
				</p>
		</div>
		<div id="tb" style="padding:2px 0">
			<table cellpadding="0" cellspacing="0">
				<tr>
					<td style="padding-left:2px">
						<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="addRowsOpenDlg();">添加</a>
						<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="updRowsOpenDlg();">编辑</a>
						<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="delRows();">删除</a>
						<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-excel" plain="true" onclick="exportExcel();">导出Excel</a>
					</td>
					<td style="padding-left:2px">
						<input id="searchbox" type="text"/>
					</td>
					<td style="padding-left:2px">
						<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="tbsCompanySearch();">高级查询</a>
					</td>
				</tr>
			</table>
		</div>
		<div id="mm">
				<div name="filter_LIKES_USER_NAME">用户名</div>
				<div name="filter_EQS_USER_PWD">密码</div>
		</div>
		<table id="dg" title="用户管理"></table>
  	</div>	
  </body>
</html>
