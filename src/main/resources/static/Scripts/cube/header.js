/**
 * Created by linkb on 2017/10/19.
 */
//表单
var laydate;

eui.use(['form', 'laydate', 'element'], function () {
    var form = eui.form
        , element = eui.element;
    laydate = eui.laydate
    var obj = new Object();
    initLaydateTest_n1(obj);
    //控制左侧菜单样式固定
    var pageIndex = $("#pageIndex").val();
    $("#cymfNav li").each(function(i){
              if(pageIndex!=""&&pageIndex==i){
                $(this).addClass("nav-this")
              }
    });

    //控制头部菜单样式固定
    var headIndex = $("#headIndex").val();
        if(headIndex==0){
            $("#head_nav_left .eui-nav-bar").css({
                "left":"20px",
                "top":"55px",
                "width":"68px",
                "opacity":"1"
            })
        }else  if(headIndex==1){
            $("#head_nav_left .eui-nav-bar").css({
                "left":"88px",
                "top":"55px",
                "width":"96px",
                "opacity":"1"
            })
        }else if(headIndex==2){
            $("#head_nav_left .eui-nav-bar").css({
                "left":"184px",
                "top":"55px",
                "width":"96px",
                "opacity":"1"
            })
        }else if(headIndex==3){
            $("#head_nav_left .eui-nav-bar").css({
                "left":"280px",
                "top":"55px",
                "width":"68px",
                "opacity":"1"
            })
        }else if(headIndex==4){
            $("#head_nav_left .eui-nav-bar").css({
                "left":"348px",
                "top":"55px",
                "width":"96px",
                "opacity":"1"
            })
        }else if(headIndex==5){
            $("#head_nav_left .eui-nav-bar").css({
                "left":"20px",
                "top":"55px",
                "width":"68px",
                "opacity":"1"
            })
        }else if(headIndex==5){
            $("#head_nav_left .eui-nav-bar").css({
                "left":"20px",
                "top":"55px",
                "width":"68px",
                "opacity":"1"
            })
        }else if(headIndex==6){
            $("#head_nav_right .eui-nav-bar").css({
                "left":"20px",
                "top":"55px",
                "width":"124px",
                "opacity":"1"
            })
        }else if(headIndex==7){
            $("#head_nav_right .eui-nav-bar").css({
                "left":"144.281px",
                "top":"55px",
                "width":"128px",
                "opacity":"1"
            })
        }else {

        }
});

function initLaydateTest_n1(obj) {
    $.post('/schedule/scheduleTimeOrEvent',{type:'1',json:JSON.stringify(obj)},function (result) {
        // 日期
        laydate.render({
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

    $.post('/schedule/backToday30',{json:JSON.stringify(obj)},function (result) {

        if (result.data.length==0){
            $("#classify").append(' <div class="eui-margin10"> <div class="eui-overflowH eui-marginB10"> ' +
                '<div class="eui-float-left eui-font14">暂无预约记录</div> </div> ' +
                '</div>');
            return;
        }
        $.each(result.data,function (index,item) {
            var dateFormat = new Date(item.visitorTime).Format('yyyy-MM-dd');
            if(!$("#Z"+dateFormat)[0]){
                $("#classify").append(' <div id="Z'+dateFormat+'" class="eui-margin10"> <div class="eui-overflowH eui-marginB10"> ' +
                    '<div class="eui-float-left eui-font14">'+new Date(item.visitorTime).Format('MM-dd')+'</div> </div> ' +
                    '<div id="list'+dateFormat+'"> </div> ' +
                    '</div>');
            }
        });

        $.each(result.data,function (index,item) {
            var dateFormat = new Date(item.visitorTime).Format('yyyy-MM-dd');
            if($("#list"+dateFormat)[0]){
                $("#list"+dateFormat).append(' <div class="eui-overflowH eui-font14"> <div class="eui-float-left eui-marginB5">'+ new Date(item.visitorTime).Format('hh:mm')+'</div> ' +
                    '<div title="'+item.describes+'" style="text-overflow: ellipsis;" class="eui-float-left eui-marginB10 eui-marginL10 test">'+item.visitorName+'  '+item.describes+'</div> ' +
                    '</div>');
            }
        });
    });
}

function loadEvends(obj) {
    layer.load(2);
    $("#scheduleData").html('');
    $.post('/schedule/scheduleTimeOrEvent',{type:'2',json:JSON.stringify(obj)},function (result) {
        var v = result.data;
        if (result.data.length ==0){
            $("#scheduleData").append('<div class="eui-overflowH"> ' +
                '<div class="eui-float-left eui-marginB5">无事件</div> ');
        }else {
            $.each(v,function (index,item) {
                $("#scheduleData").append('<div class="eui-overflowH"> ' +
                    '<div class="eui-float-left eui-marginB5">'+new Date(item.visitorTime).Format('hh:mm')+'</div> ' +
                    '<div title="'+item.describes+'"  style="text-overflow: ellipsis;" class="eui-float-left eui-marginB10 eui-marginL10 test">'+item.visitorName+'  '+item.describes+'</div></div>')
            });
        }
        layer.closeAll();
    });
}
