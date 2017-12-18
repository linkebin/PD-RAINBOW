/**
 * Created by ldx on 2017/12/15.
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