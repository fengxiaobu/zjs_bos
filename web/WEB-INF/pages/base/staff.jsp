<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
    <!-- 导入jquery核心类库 -->
    <script type="text/javascript"
            src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
    <!-- 导入easyui类库 -->
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath }/js/easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath }/js/easyui/themes/icon.css">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath }/js/easyui/ext/portal.css">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath }/css/default.css">
    <script type="text/javascript"
            src="${pageContext.request.contextPath }/js/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath }/js/easyui/ext/jquery.portal.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath }/js/easyui/ext/jquery.cookie.js"></script>
    <script
            src="${pageContext.request.contextPath }/js/easyui/locale/easyui-lang-zh_CN.js"
            type="text/javascript"></script>
    <%--防止窗口脱出浏览器--%>
    <script src="${pageContext.request.contextPath }/js/easyui/outOfBounds.js" type="text/javascript"></script>
    <script type="text/javascript">
        function doAdd() {
            //alert("增加...");
            $('#addStaffWindow').window("open");
            // $('#addStaffWindow').center();
        }

        function doView() {
            //alert("查看...");
            $("#searchWindow").window("open");
        }
        //作废
        function doDelete() {
            var rows = $('#grid').datagrid("getSelections");
            if (rows.length == 0) {
                message.alert("提示", "请至少选择一条数据!", "warning");
            } else {
                var arr = new Array();
                for (var i = 0; i < rows.length; i++) {
                    arr.push(rows[i].id);
                }
                var ids = arr.join(",");
                window.location.href = "${pageContext.request.contextPath}/staffAction_delete.action?ids=" + ids;
            }
        }
        //将取派员还原
        function doRestore() {
            var rows = $('#grid').datagrid("getSelections");
            if (rows.length == 0) {
                message.alert("提示", "请至少选择一条数据!", "warning");
            } else {
                var arr = new Array();
                for (var i = 0; i < rows.length; i++) {
                    arr.push(rows[i].id);
                }
                var ids = arr.join(",");
                window.location.href = "${pageContext.request.contextPath}/staffAction_restore.action?ids=" + ids;
            }
        }
        //工具栏
        var toolbar = [{
            id: 'button-view',
            text: '查询',
            iconCls: 'icon-search',
            handler: doView
        }, {
            id: 'button-add',
            text: '增加',
            iconCls: 'icon-add',
            handler: doAdd
        }, {
            id: 'button-delete',
            text: '作废',
            iconCls: 'icon-cancel',
            handler: doDelete
        }, {
            id: 'button-save',
            text: '还原',
            iconCls: 'icon-save',
            handler: doRestore
        }];
        // 定义列
        var columns = [[{
            field: 'id',
            checkbox: true,
        }, {
            field: 'name',
            title: '姓名',
            width: 120,
            align: 'center'
        }, {
            field: 'telephone',
            title: '手机号',
            width: 120,
            align: 'center'
        }, {
            field: 'haspda',
            title: '是否有PDA',
            width: 120,
            align: 'center',
            formatter: function (data, row, index) {
                if (data == "1") {
                    return "有";
                } else {
                    return "无";
                }
            }
        }, {
            field: 'deltag',
            title: '是否作废',
            width: 120,
            align: 'center',
            formatter: function (data, row, index) {
                if (data == "0") {
                    return "正常使用"
                } else {
                    return "已作废";
                }
            }
        }, {
            field: 'standard',
            title: '取派标准',
            width: 120,
            align: 'center'
        }, {
            field: 'station',
            title: '所谓单位',
            width: 200,
            align: 'center'
        }]];

        $(function () {
            // 先将body隐藏，再显示，不会出现页面刷新效果
            $("body").css({visibility: "visible"});

            // 取派员信息表格
            $('#grid').datagrid({
                iconCls: 'icon-forward',
                fit: true,
                border: false,
                rownumbers: true,
                fitColumns: true,
                striped: true,
                pagerSize:30,
                pageList: [30, 50, 100],
                pagination: true,
                toolbar: toolbar,
                url: "${pageContext.request.contextPath}/staffAction_pageQuery.action",
                idField: 'id',
                columns: columns,
                onDblClickRow: doDblClickRow
            });

            // 添加取派员窗口
            $('#addStaffWindow').window({
                title: '添加取派员',
                width: 400,
                modal: true,
                shadow: true,
                closed: true,
                height: 400,
                resizable: false
            });

            // 添加取派员窗口
            $('#updateStaffWindow').window({
                title: '修改取派员',
                width: 400,
                modal: true,
                shadow: true,
                closed: true,
                height: 400,
                resizable: false
            });

            //查询取派员窗口
            $('#searchWindow').window({
                title: '查询取派员',
                width: 400,
                modal: true,
                shadow: true,
                closed: true,
                height: 400,
                resizable: false
            });
            //为保存按钮绑定事件
            $("#save").click(function () {
                var validate = $("#saveForm").form("validate");
                if (validate) {
                    $("#saveForm").submit();
                }
            });

            //为修改按钮绑定事件
            $("#update").click(function () {
                var validate = $("#updateForm").form("validate");
                if (validate) {
                    $("#updateForm").submit();
                }
            });

            //为查询按钮绑定事件
            $("#search").click(function () {
                $("#searchForm").submit();
            });

            //使用正则表达式，扩展手机号校验规则
            $.extend(
                $.fn.validatebox.defaults.rules, {
                    //自定义验证
                    phoneNumber: {
                        validator: function (value, param) {
                            var phone = /^1[3|5|7|8][0-9]{9}$/;
                            return phone.test(value);
                        }, message: '手机号输入有误!'
                    }
                });

        });

        function doDblClickRow(rowIndex, rowData) {
            $("#updateStaffWindow").window("open");//改变窗口状态
            $("#updateForm").form("load", rowData);//加载双击数据
        }


    </script>
</head>
<body class="easyui-layout" style="visibility:hidden;">
<div region="center" border="false">
    <table id="grid"></table>
</div>
<div class="easyui-window" title="对收派员进行添加或者修改" id="addStaffWindow" collapsible="false" minimizable="false"
     maximizable="false" style="top:20px;left:200px">
    <div region="north" style="height:31px;overflow:hidden;" split="false" border="false">
        <div class="datagrid-toolbar">
            <a id="save" icon="icon-save" href="#" class="easyui-linkbutton" plain="true">保存</a>
        </div>
    </div>

    <div region="center" style="overflow:auto;padding:5px;" border="false">
        <form id="saveForm" action="${pageContext.request.contextPath}/staffAction_save.action" method="post">
            <table class="table-edit" width="80%" align="center">
                <tr class="title">
                    <td colspan="2">收派员信息</td>
                </tr>
                <!-- TODO 这里完善收派员添加 table -->
                <tr>
                    <td>取派员编号</td>
                    <td><input type="text" name="id" class="easyui-validatebox" required="true"/></td>
                </tr>
                <tr>
                    <td>姓名</td>
                    <td><input type="text" name="name" class="easyui-validatebox" required="true"/></td>
                </tr>
                <tr>
                    <td>手机</td>
                    <td><input type="text" name="telephone" class="easyui-validatebox" required="true"
                               validType="phoneNumber"/></td>
                </tr>
                <tr>
                    <td>单位</td>
                    <td><input type="text" name="station" class="easyui-validatebox" required="true"/></td>
                </tr>
                <tr>
                    <td colspan="2">
                        <input type="checkbox" name="haspda" value="1"/>
                        是否有PDA
                    </td>
                </tr>
                <tr>
                    <td>取派标准</td>
                    <td>
                        <input type="text" name="standard" class="easyui-validatebox" required="true"/>
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>

<div class="easyui-window" title="对收派员进行添加或者修改" id="updateStaffWindow" collapsible="false" minimizable="false"
     maximizable="false" style="top:20px;left:200px">
    <div region="north" style="height:31px;overflow:hidden;" split="false" border="false">
        <div class="datagrid-toolbar">
            <a id="update" icon="icon-save" href="#" class="easyui-linkbutton" plain="true">保存</a>
        </div>
    </div>

    <div region="center" style="overflow:auto;padding:5px;" border="false">
        <form id="updateForm" action="${pageContext.request.contextPath}/staffAction_update.action" method="post">
            <table class="table-edit" width="80%" align="center">
                <tr class="title">
                    <td colspan="2">收派员信息</td>
                </tr>
                <!-- TODO 这里完善收派员添加 table -->
                <tr>
                    <td>取派员编号</td>
                    <td><input type="text" name="id" readonly="readonly" class="easyui-validatebox" required="true"/></td>
                </tr>
                <tr>
                    <td>姓名</td>
                    <td><input type="text" name="name" class="easyui-validatebox" required="true"/></td>
                </tr>
                <tr>
                    <td>手机</td>
                    <td><input type="text" name="telephone" class="easyui-validatebox" required="true"
                               validType="phoneNumber"/></td>
                </tr>
                <tr>
                    <td>单位</td>
                    <td><input type="text" name="station" class="easyui-validatebox" required="true"/></td>
                </tr>
                <tr>
                    <td colspan="2">
                        <input type="checkbox" name="haspda" value="1"/>
                        是否有PDA
                    </td>
                </tr>
                <tr>
                    <td>取派标准</td>
                    <td>
                        <input type="text" name="standard" class="easyui-validatebox" required="true"/>
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>

<!-- 查询取派员 -->
<div class="easyui-window" title="查询取派员窗口" id="searchWindow" collapsible="false" minimizable="false" maximizable="false"
     style="top:100px;left:300px">
    <div region="north" style="height:31px;overflow:hidden;" split="false" border="false">
        <div class="datagrid-toolbar">
            <a id="search" icon="icon-save" href="#" class="easyui-linkbutton" plain="true">保存</a>
        </div>
    </div>
    <div style="overflow:auto;padding:5px;" border="false">
        <form id="searchForm" action="${pageContext.request.contextPath}/staffAction_pageQuery.action" method="post">
            <table class="table-edit" width="80%" align="center">
                <tr class="title">
                    <td colspan="2">条件查询</td>
                </tr>
                <tr>
                    <td>取派员编号</td>
                    <td><input type="text" name="id" class="easyui-validatebox" required="true"/></td>
                </tr>
                <tr>
                    <td>姓名</td>
                    <td><input type="text" name="name" class="easyui-validatebox" required="true"/></td>
                </tr>
                <tr>
                    <td>手机</td>
                    <td><input type="text" name="telephone" class="easyui-validatebox" required="true"
                               validType="phoneNumber"/></td>
                </tr>
                <tr>
                    <td>单位</td>
                    <td><input type="text" name="station" class="easyui-validatebox" required="true"/></td>
                </tr>
                <tr>
                    <td >
                        是否有PAD
                    </td>
                    <td colspan="2">
                        <select name="haspda">
                            <option value="">--请选择--</option>
                            <option value="1">有</option>
                            <option value="0">无</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td >
                        是否作废
                    </td>
                    <td colspan="2">
                        <select name="deltag">
                            <option value="">--请选择--</option>
                            <option value="1">是</option>
                            <option value="0">否</option>
                        </select>
                    </td>
                </tr>

                <tr>
                    <td>取派标准</td>
                    <td>
                        <input type="text" name="standard" class="easyui-validatebox" required="true"/>
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>
</body>
</html>	