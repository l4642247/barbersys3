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
        elem: "#LAY-expense-manage",
        url: "/account/recharge/list",
        cols: [[
            {type: "checkbox", fixed: "left"},
            {field: "id", width: 80, title: "ID", sort: !0},
            {field: "orderNo", title: "消费单号", minWidth: 100},
            {field: "price", title: "价格", width: 80},
            {field: "number", title: "数量", width: 80},
            {field: "sum", title: "合计"},
            {field: "cardNo", title: "会员卡号"},
            {field: "expenseDate", title: "消费日期"},
            {field: "creator", title: "操作员"},
            {field: "createTime", title: "添加时间", sort: !0},
            {title: "操作", width: 150, align: "center", fixed: "right", toolbar: "#table-useradmin-webuser"}]],
        page: !0,
        limit: 30,
        height: "full-220",
        text: "对不起，加载出现异常！"
    })
});