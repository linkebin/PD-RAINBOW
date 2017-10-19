/**
 * Created by linkb on 2017/10/19.
 */
//表单
eui.use(['form', 'laydate', 'element'], function () {
    var form = eui.form
        , element = eui.element
        , laydate = eui.laydate;

    // 日期
    laydate.render({
        elem: '#test-n1'
        , position: 'static'
    });

});

//
// $("ul#cymfNav").on("click","li",function(){
//     $(this).addClass("nav-this");
//     $(this).siblings().removeClass("nav-this")
// });

