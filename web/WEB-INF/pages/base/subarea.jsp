<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>管理分区</title>
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
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.ocupload-1.1.2.js"></script>
    <script type="text/javascript">
        function doAdd() {
            $('#addSubareaWindow').window("open");
        }

        function doEdit() {
            alert("修改...");
        }

        function doDelete() {
            alert("删除...");
        }

        function doSearch() {
            $('#searchWindow').window("open");
        }

        function doExport() {
            var data = $("#grid").datagrid("getRows");
            var arr = new Array();
            for (var i = 0; i < data.length; i++) {
                arr.push(data[i].id);
            }
            var ids = arr.join(',');
            //alert(ids);
            window.location.href = "${pageContext.request.contextPath}/subareaAction_exportXLS.action?ids=" + ids;
        }

        function doImport() {

        }

        //工具栏
        var toolbar = [{
            id: 'button-search',
            text: '查询',
            iconCls: 'icon-search',
            handler: doSearch
        }, {
            id: 'button-add',
            text: '增加',
            iconCls: 'icon-add',
            handler: doAdd
        }, {
            id: 'button-edit',
            text: '修改',
            iconCls: 'icon-edit',
            handler: doEdit
        }, {
            id: 'button-delete',
            text: '删除',
            iconCls: 'icon-cancel',
            handler: doDelete
        }, {
            id: 'button-import',
            text: '导入',
            iconCls: 'icon-redo',
            handler: doImport
        }, {
            id: 'button-export',
            text: '导出',
            iconCls: 'icon-undo',
            handler: doExport
        }];
        // 定义列
        var columns = [[{
            field: 'id',
            checkbox: true,
        }, {
            field: 'showid',
            title: '分拣编号',
            width: 120,
            align: 'center',
            formatter: function (data, row, index) {
                return row.id;
            }
        }, {
            field: 'province',
            title: '省',
            width: 120,
            align: 'center',
            formatter: function (data, row, index) {
                return row.region.province;
            }
        }, {
            field: 'city',
            title: '市',
            width: 120,
            align: 'center',
            formatter: function (data, row, index) {
                return row.region.city;
            }
        }, {
            field: 'district',
            title: '区',
            width: 120,
            align: 'center',
            formatter: function (data, row, index) {
                return row.region.district;
            }
        }, {
            field: 'addresskey',
            title: '关键字',
            width: 120,
            align: 'center'
        }, {
            field: 'startnum',
            title: '起始号',
            width: 100,
            align: 'center'
        }, {
            field: 'endnum',
            title: '终止号',
            width: 100,
            align: 'center'
        }, {
            field: 'single',
            title: '单双号',
            width: 100,
            align: 'center'
        }, {
            field: 'position',
            title: '位置',
            width: 200,
            align: 'center'
        }]];

        $(function () {
            // 先将body隐藏，再显示，不会出现页面刷新效果
            $("body").css({visibility: "visible"});

            // 收派标准数据表格
            $('#grid').datagrid({
                iconCls: 'icon-forward',
                fit: true,
                border: true,
                rownumbers: true,
                striped: true,
                pageList: [30, 50, 100],
                pagination: true,
                toolbar: toolbar,
                url: "${pageContext.request.contextPath}/subareaAction_pageQuery.action",
                idField: 'id',
                columns: columns,
                onDblClickRow: doDblClickRow
            });

            // 添加、修改分区
            $('#addSubareaWindow').window({
                title: '添加修改分区',
                width: 600,
                modal: true,
                shadow: true,
                closed: true,
                height: 400,
                resizable: false
            });

            // 查询分区
            $('#searchWindow').window({
                title: '查询分区',
                width: 400,
                modal: true,
                shadow: true,
                closed: true,
                height: 400,
                resizable: false
            });
            //将表单数据序列化为json格式的数据
            $.fn.serializeJson = function () {
                var serializeObj = {};
                var array = this.serializeArray();
                $(array).each(function () {
                    if (serializeObj[this.name]) {
                        if ($.isArray(serializeObj[this.name])) {
                            serializeObj[this.name].push(this.value);
                        } else {
                            serializeObj[this.name] = [serializeObj[this.name], this.value];
                        }
                    } else {
                        serializeObj[this.name] = this.value;
                    }
                });
                return serializeObj;
            };
            //查询分区
            $("#btn").click(function () {
                var para = $("#searchForm").serializeJson();
                //重新发送ajax请求查询
                $("#grid").datagrid("load", para);
                //关闭查询窗口
                $("#searchWindow").window("close");
            });
            /*导入分区*/
            $("#button-import").upload({
                action: "${pageContext.request.contextPath}/subareaAction_importXLS.action",
                name: 'myFile',
                onComplete: function (data, self, element) {
                    if (data == "1") {
                        $.messager.alert("系统提示", "添加成功!", "info")
                    } else {
                        $.messager.alert("系统提示", "添加失败!", "waring")
                    }
                    $('#grid').datagrid('reload');
                }
            });
        });

        function doDblClickRow() {
            /*获取page和rows*/
            var pg = $("#grid").datagrid("getPager");
            alert(pg)
            if (pg) {
                $(pg).pagination({
                    onBeforeRefresh: function () {
                        alert('before refresh');
                    },
                    onRefresh: function (pageNumber, pageSize) {
                        alert(pageNumber);
                        alert(pageSize);
                    },
                    onChangePageSize: function () {
                        alert('pagesize changed');
                    },
                    onSelectPage: function (pageNumber, pageSize) {
                        alert(pageNumber);
                        alert(pageSize);
                    }
                });
            }
            alert("双击表格数据...");
        }
    </script>
</head>
<body class="easyui-layout" style="visibility:hidden;">
<div region="center" border="false">
    <table id="grid"></table>
</div>
<!-- 添加 修改分区 -->
<div class="easyui-window" title="分区添加修改" id="addSubareaWindow" collapsible="false" minimizable="false"
     maximizable="false" style="top:20px;left:200px">
    <div style="height:31px;overflow:hidden;" split="false" border="false">
        <div class="datagrid-toolbar">
            <a id="save" icon="icon-save" href="#" class="easyui-linkbutton" plain="true">保存</a>
        </div>
    </div>

    <div style="overflow:auto;padding:5px;" border="false">
        <form id="saveForm" action="${pageContext.request.contextPath}/subareaAction_save.action" method="post">
            <table class="table-edit" width="80%" align="center">
                <tr class="title">
                    <td colspan="2">分区信息</td>
                </tr>
                <tr>
                    <td>分拣编码</td>
                    <td><input type="text" name="id" class="easyui-validatebox" required="true"/></td>
                </tr>
                <tr>
                    <td>选择区域</td>
                    <td>
                        <input class="easyui-combobox" name="region.id"
                               data-options="valueField:'id',textField:'name',url:'${pageContext.request.contextPath}/regionAction_findAll.action'"/>
                <TR>
                    <TD align="right">地区 省级</TD>
                    <TD align="left">
                        <INPUT style="WIDTH: 128px" id=province class=easyui-validatebox type=text name=province
                               validType="selectValid['--请选择--']">
                    </TD>
                </TR>
                <TR>
                    <TD align="right">地区 县市区</TD>
                    <TD align="left">
                        <INPUT style="WIDTH: 128px" id=city class=easyui-validatebox type=text name=city
                               validType="selectValid['--请选择--']">
                    </TD>
                </TR>
                    </td>
                </tr>
                <tr>
                    <td>关键字</td>
                    <td><input type="text" name="addresskey" class="easyui-validatebox" required="true"/></td>
                </tr>
                <tr>
                    <td>起始号</td>
                    <td><input type="text" name="startnum" class="easyui-validatebox" required="true"/></td>
                </tr>
                <tr>
                    <td>终止号</td>
                    <td><input type="text" name="endnum" class="easyui-validatebox" required="true"/></td>
                </tr>
                <tr>
                    <td>单双号</td>
                    <td>
                        <select class="easyui-combobox" name="single" style="width:150px;">
                            <option value="0">单双号</option>
                            <option value="1">单号</option>
                            <option value="2">双号</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>位置信息</td>
                    <td><input type="text" name="position" class="easyui-validatebox" required="true"
                               style="width:250px;"/></td>
                </tr>
            </table>
        </form>
    </div>
</div>
<!-- 查询分区 -->
<div class="easyui-window" title="查询分区窗口" id="searchWindow" collapsible="false" minimizable="false" maximizable="false"
     style="top:20px;left:200px">
    <div style="overflow:auto;padding:5px;" border="false">
        <form id="searchForm">
            <table class="table-edit" width="80%" align="center">
                <tr class="title">
                    <td colspan="2">查询条件</td>
                </tr>
                <tr>
                    <td>省</td>
                    <td><input type="text" name="region.province"/></td>
                </tr>
                <tr>
                    <td>市</td>
                    <td><input type="text" name="region.city"/></td>
                </tr>
                <tr>
                    <td>区（县）</td>
                    <td><input type="text" name="region.district"/></td>
                </tr>
                <tr>
                    <td>关键字</td>
                    <td><input type="text" name="addresskey"/></td>
                </tr>
                <tr>
                    <td colspan="2"><a id="btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a>
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>
</body>
</html>