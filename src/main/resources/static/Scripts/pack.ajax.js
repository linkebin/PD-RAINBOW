/**简化ajax请求
 * Created by Administrator on 2017/7/26.
 */
var TIME_OUT = 60000;
/**
 * 提交post请求,返回json数据
 * @param url
 * @param data ：var data =new Object(); var data = {id:'1'};
 * @param callback
 */
$.postJSON = function(url,data,callback){
    $.ajax({
        url:url,
        type:"post",
        dataType:"json",
        data:data,
        timeout:TIME_OUT,
        beforeSend:function(XMLHttpRequest){
        },
        success:function(data){
            if(data.code!=undefined)
            {}
            callback(data);
        },
        error:function(XMLHttpRequest,textStatus,errorThrown){

        }
    });
};


/**
 * 提交post请求,返回操作状态
 * @param url
 * @param data :var data =new Object(); var data = {id:'1'};
 * @param callback
 */
$.postStatus = function(url,data,callback){
    $.ajax({
        url:url,
        type:"post",
        dataType:"json",
        data:data,
        timeout:TIME_OUT,
        success:function(data){
            if(data.code!=undefined) {
                if(data.code==200)
                {
                    callback(data);
                }
                else
                {
                    console.log(data.message);
                }
            }
        },
        error:function(XMLHttpRequest,textStatus,errorThrown){
        }
    });
};

$.fn.serializeObject = function()
{
    var o = {};
    var a = this.serializeArray();
    $.each(a, function() {
        if (o[this.name] !== undefined) {
            if (!o[this.name].push) {
                o[this.name] = [o[this.name]];
            }
            o[this.name].push(this.value || '');
        } else {
            o[this.name] = this.value || '';
        }
    });
    return o;
};