<%--
  Created by IntelliJ IDEA.
  User: luopa
  Date: 2017/3/12
  Time: 21:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/js/easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/easyui/themes/icon.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.3.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui/jquery.easyui.min.js"></script>
</head>
<body class="easyui-layout">
        <div data-options="region:'north',title:'CRM管理系统'" style="height: 100px">北部区域</div>
        <div data-options="region:'west',title:'系统菜单'" style="width: 200px">
            <!-- 折叠面板 -->
            <div class="easyui-accordion" data-options="fit:true">
                <!-- 每个子div是一个面板 -->
                <div data-options="iconCls:'icon-search'" title="面板一">
                    内容一
                </div>
                <div title="面板二">
                    内容二
                </div>
                <div title="面板三">
                    内容三
                </div>
            </div>
        </div>
        <div data-options="region:'center'">中部区域</div>
        <%--<div data-options="region:'east'" style="width: 180px">东部区域</div>--%>
        <div data-options="region:'south'" style="height: 100px">南部区域</div>
</body>

</html>
