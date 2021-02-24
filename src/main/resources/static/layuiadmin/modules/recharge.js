/** layuiAdmin.std-v1.1.0 LPPL License By http://www.layui.com/admin/ */
;layui.define(["table", "form", 'laydate'], function (e) {
    var t = layui.$, i = layui.table ,l = layui.laydate;
    layui.form;
    //日期范围
    l.render({
        elem: '#rechargeDate'
        ,range: true
    });
    i.render({
        elem: "#LAY-recharge-manage",
        url: "/account/recharge/list",
        cols: [[
            {type: "checkbox", fixed: "left"},
            {field: "id", width: 100, title: "ID", sort: !0},
            {field: "number", title: "卡号", minWidth: 100},
            {field: "name", title: "姓名", minWidth: 100},
            {field: "amount", title: "充值金额", width: 100},
            {field: "rechargeDate", title: "充值日期"},
            {field: "creator", title: "操作员"},
            {field: "createTime", title: "添加时间", sort: !0},
            {title: "操作", width: 150, align: "center", fixed: "right", toolbar: "#table-useradmin-webuser"}]],
        page: !0,
        limit: 30,
        height: "full-220",
        text: "对不起，加载出现异常！"
    })
});