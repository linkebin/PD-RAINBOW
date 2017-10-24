/**
 * Created by linkb on 2017/10/19.
 */
//表单
eui.use(['form', 'laydate', 'element'], function () {
    var form = eui.form
        , element = eui.element
        , laydate = eui.laydate;
    var obj = new Object();
    $.post('/schedule/scheduleTimeOrEvent',{type:'1',json:JSON.stringify(obj)},function (result) {
        // 日期
        var ins1 =  laydate.render({
            elem: '#test-n1'
            , position: 'static'
            ,mark: result.data
            ,change: function(value, date, endDate){
                $("#selectDate").html(date.month+'-'+date.date);
                var o = new Object();
                o.visitorTimeStr=value;
                loadEvends(o);
            }
        });
        $("#selectDate").html(new Date().Format('MM-dd'));
        obj.visitorTimeStr=new Date().Format('yyyy-MM-dd');
        loadEvends(obj);
    });
});

function loadEvends(obj) {
    $("#scheduleData").html('');
    $.post('/schedule/scheduleTimeOrEvent',{type:'2',json:JSON.stringify(obj)},function (result) {
        console.log(result.data)
        var v = result.data;
        if (result.data.length ==0){
            $("#scheduleData").append('<div class="eui-overflowH"> ' +
                '<div class="eui-float-left eui-marginB5">无事件</div> ');
        }else {
            $.each(v,function (index,item) {
                var count = '';
                if (item.visitorTotal >0){
                    count='第'+item.visitorTotal+'次咨询';
                }else {
                    count='首次来访';
                }
                $("#scheduleData").append('<div class="eui-overflowH"> ' +
                    '<div class="eui-float-left eui-marginB5">'+new Date(item.visitorTime).Format('hh:mm')+'</div> ' +
                    '<div class="eui-float-left eui-marginB10 eui-marginL10">'+item.visitorName+''+count+'</div></div>')
            });
        }
    });
}

//
// $("ul#cymfNav").on("click","li",function(){
//     $(this).addClass("nav-this");
//     $(this).siblings().removeClass("nav-this")
// });

