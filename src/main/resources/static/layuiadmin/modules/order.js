/** layuiAdmin.std-v1.1.0 LPPL License By http://www.layui.com/admin/ */
;layui.define(["table", "form", 'util', 'HttpRequest'], function (e) {
    var t = layui.$, i = layui.table , util = layui.util, HttpRequest = layui.HttpRequest;
    i.render({
        elem: "#LAY-order",
        url: "/order/page",
        cols: [[
            {type: "checkbox", fixed: "left"},
            {type: 'numbers', width: 80, title: "ID"},
            {field: "cardNo", title: "卡号"},
            {field: "memberId", title: "姓名"},
            {field: "type", title: "类型" ,templet: function (d) {return d.type == '1'?"充值":"消费";}},
            {field: "amount", width: 100, title: "金额"},
            {field: "status", title: "状态" ,templet: function (d) {return d.status == '1'?"已完成":"取消";}},
            {field: "createTime", width: 170,title: "创建时间", templet: function (d) {
                    return util.toDateString(d.createTime);}},
            {field: "creator", title: "操作人"}]],
        page: true,
        text: "对不起，加载出现异常！"
    }), e("order", {})
});