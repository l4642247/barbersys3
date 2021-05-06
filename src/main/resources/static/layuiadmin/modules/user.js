/** layuiAdmin.std-v1.1.0 LPPL License By http://www.layui.com/admin/ */
;layui.define(["table", "form", 'util', 'HttpRequest'], function (e) {
    var t = layui.$, i = layui.table , util = layui.util, HttpRequest = layui.HttpRequest;

    i.render({
        elem: "#LAY-user-manage",
        url: "/user/page",
        cols: [[
            {type: "checkbox", fixed: "left"},
            {type: 'numbers', width: 80, title: "ID"},
            {field: "username", title: "工号", minWidth: 100},
            {field: "name", title: "姓名" },
            {field: "sex", title: "性别", align:"center", templet: function (d) {return d.sex == '1'?"男":"女";}},
            {field: "roleStr", title: "职位"},
            {field: "phone", title: "联系电话"},
            {field: "status", title: "状态", align:"center", templet: function (d) {return d.status == '1'?"启用":"禁用";}},
            {field: "createTime", title: "添加时间", templet: function (d) {return util.toDateString(d.createTime);}},
            {title: "操作", width: 150, align: "center", fixed: "right", toolbar: "#table-option"}]],
        page: !0,
        limit: 30,
        height: "full-220",
        text: "对不起，加载出现异常！"
    }), i.on("tool(LAY-user-manage)", function (e) {
        if ("del" === e.event) layer.confirm("确定删除此用户？", function (t) {
            var httpRequest = new HttpRequest("/user/delete/"+e.data.id, 'delete', function (data) {
                e.del(), layer.close(t)
            });
            httpRequest.start(true);
        }); else if ("edit" === e.event) {
            layer.open({
                type: 2,
                title: "编辑员工信息",
                content: "userform?id=" + e.data.id,
                area: ["800px", "550px"],
                btn: ["确定", "取消"],
                yes: function (e, t) {
                    var l = window["layui-layer-iframe" + e],
                        r = t.find("iframe").contents().find("#LAY-user-submit");
                    l.layui.form.on("submit(LAY-user-submit)", function (t) {
                        var httpRequest = new HttpRequest("/user/save", 'post', function (data) {
                            i.reload("LAY-user-manage");
                            layer.close(e)
                        });
                        httpRequest.set(t.field);
                        httpRequest.start(true);
                    }), r.trigger("click")
                },
                success: function (e, t) {
                }
            })
        }
    }), e("user", {})
});