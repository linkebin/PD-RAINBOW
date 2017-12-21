/**
 * Created by ldx on 2017/12/15.
 */
/**
 * 活动判断用户的行为
 * @param params
 * @returns {number}
 */
getActiveState = function (params) {
    //1为正常进行、2为活动结束、3为请求数据异常
    var state = 3;
    $.ajax({
        url: "/launch/activities/getActivityById",
        type: 'post',
        async: false,
        data: {id: params},
        success: function (result) {
            console.log(result);
            if (result.code == 200) {
                //如果返回的数据不为空，说明活动还没有结束
                if (result.data != null) {
                    var activity = result.data;
                    $.ajax({
                        url: "/data/acquisition/findCount",
                        type: 'post',
                        async: false,
                        data: {activityId: activity.id},
                        success: function (res) {
                            if (res.code == 200) {
                                //如果填报人数小于活动人数
                                if (res.data < activity.activityTotal) {
                                    state = 1;
                                }else{
                                    state = 2;
                                }
                            }
                        }
                    })
                }else{
                    state = 2;
                }
            }
        }
    })
    return state;
}

/**
 * 提交问卷  （包括活动，来访者问卷提交）
 * @param array
 * @param questionnaireId
 * @param userId
 * @param visitorTimes
 * @param timeConsuming
 * @param activityId
 * @param userName
 * @param token
 */
submit_Questionnaire=function (array,questionnaireId,userId,visitorTimes,timeConsuming,activityId,userName,token){
    //加载层
    var index = layer.load(0, {shade: false}); //0代表加载的风格，支持0-2
    //判断活动id是否为空 不为空是活动 为空是来访者
    if(activityId!=null && activityId.length>0){
      var flag = getActiveState(activityId);
      if(flag==1){
        $.post("/cube/questionnaire/subQuestionnaire",
            {
                "param": JSON.stringify(array),
                "questionnaireId": questionnaireId,
                "userId": userId,
                "visitorTimes": visitorTimes,
                "timeConsuming": timeConsuming,
                "activityId": activityId,
                "userName": userName
            }, function (result) {
                layer.closeAll();
                console.log(result);
                if (result.code == 200) {
                    window.location.href = "/web/cube/getSuccess";
                } else {
                    layer.msg('填写失败', {
                        icon: 2,
                        time: 3000
                    });
                    layer.close(index);
                    $("#submitQuestionnaire").removeAttr("disabled");
                }
            });
    }else if(flag==2){
        layer.confirm('活动已结束', {
            btn: ['确定']
        });
        layer.close(index);
          $("#submitQuestionnaire").removeAttr("disabled");

      } else{
        layer.msg('提交失败', {
            icon: 2,
            time: 3000
        });
        layer.close(index);
          $("#submitQuestionnaire").removeAttr("disabled");
      }
    }else {
        //来访者提交问卷 的方法
        $.ajax({
            url: "/cube/questionnaire/submitQuestionnaire",
            headers: {
                "token":token
            },
            data: {
                "param":JSON.stringify(array),
                "questionnaireId":questionnaireId,
                "userId":userId,
                "visitorTimes":visitorTimes,
                "timeConsuming":timeConsuming
            },
            type: "post",
            success: function (result) {
                layer.closeAll();
                if(result.code==200){
                    window.location.href="/web/cube/getSuccess";
                }else {
                    $("#submitQuestionnaire").removeAttr("disabled");
                    layer.msg('填写失败！', {
                        icon: 1,
                        time: 5000
                    });
                }
            }
        });
    }
}



/**
 * 进入全全屏模式
 *
 */
fullScreen=function(){
    eui.use(['form', 'layedit', 'laydate', 'laypage', 'layer'], function () {
        var form = eui.form
            , layer = eui.layer
            , layedit = eui.layedit
            , laydate = eui.laydate
            , laypage = eui.laypage;

        //全屏模式询问框
        layer.confirm('欢迎来到本页面，开始答题！', {
            btn: ['确定'], //按钮
            closeBtn: 0,
            btnAlign: 'c'
        }, function () {
            launchFullscreen(document.documentElement);
            layer.closeAll();
        });
    });

}
/**
 * 全屏的切换
 */
toggleFullScreen=function () {
    if (!document.fullscreenElement &&    // alternative standard method
        !document.mozFullScreenElement && !document.webkitFullscreenElement) {  // current working methods
        if (document.documentElement.requestFullscreen) {
            document.documentElement.requestFullscreen();
        } else if (document.documentElement.mozRequestFullScreen) {
            document.documentElement.mozRequestFullScreen();
        } else if (document.documentElement.webkitRequestFullscreen) {
            document.documentElement.webkitRequestFullscreen(Element.ALLOW_KEYBOARD_INPUT);
        }
    } else {
        if (document.cancelFullScreen) {
            document.cancelFullScreen();
        } else if (document.mozCancelFullScreen) {
            document.mozCancelFullScreen();
        } else if (document.webkitCancelFullScreen) {
            document.webkitCancelFullScreen();
        }
    }
}

/**
 * 判断各种浏览器，找到正确的方法
 * @param element
 */
 launchFullscreen=function(element) {
    if(element.requestFullscreen) {
        element.requestFullscreen();
    } else if(element.mozRequestFullScreen) {
        element.mozRequestFullScreen();
    } else if(element.webkitRequestFullscreen) {
        element.webkitRequestFullscreen();
    } else if(element.msRequestFullscreen) {
        element.msRequestFullscreen();
    }
}