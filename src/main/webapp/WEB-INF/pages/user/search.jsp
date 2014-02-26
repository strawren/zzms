<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/variable.jsp"%>

<!-- 高级查询 -->
<div id="tbCompanySearchDlg">
	<form id="tbCompanySearchFm" method="post">
		<table>
			<tr>
				<th>条件</th>
				<th>字段名</th>
				<th>条件</th>
				<th>值</th>
			</tr>
			<tr>
				<td>
					<div class="gradeSearchBox">
					<select name="searchAnds" class="gradeSelectSearchBox"> 
						<option value="and">并且</option>
						<option value="or">或者</option>
					</select>
					</div> 
				</td>
				<td>
				<div class="gradeSearchBox">
					<select name="searchColumnNames" class="gradeSelectSearchBox">
						<option value="S_USER_NAME">用户名</option>
						<option value="S_USER_PWD">密码</option>
					</select>
					</div> 
				</td>
				<td>
				<div class="gradeSearchBox">
					<select name="searchConditions" class="gradeSelectSearchBox">
						<option value="EQ">等于</option>
						<option value="NEQ">大于小于</option>
						<option value="LT">小于</option>
						<option value="GT">大于</option>
						<option value="LIKE">模糊</option>
					</select>
					</div> 
				</td>
					<td><input class="easyui-textbox easyui-validatebox" name="searchVals" size="18"> <a style="display: none;" href="javascript:void(0);" onclick="tbCompanySearchRemove(this);">删除</a>
				</td>
			</tr>
		</table>
	</form>
</div>
