/**
 * Created by Administrator on 2017/8/10.
 */
Date.prototype.Format = function (fmt) { //author: meizz
    var o = {
        "M+": this.getMonth() + 1, //月份
        "d+": this.getDate(), //日
        "h+": this.getHours(), //小时
        "m+": this.getMinutes(), //分
        "s+": this.getSeconds(), //秒
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度
        "S": this.getMilliseconds() //毫秒
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}


    //获得本月的开始日期
    function getMonthStartDate(nowyear,months)
    {
        var monthStartDate = new Date(nowyear,months,1);
        if (months < 10) {
            months = "0" + months;
        }
        return (nowyear + "-" + months + "-01");
    }

    //获得本月的结束日期
    function getMonthEndDate(nowyear,months)
    {
        var days= getDaysInOneMonth(nowyear,months);//获取当月总共有多少天
        var monthEndDate = new Date(nowyear,months, days);
        if (days < 10) {
            days = "0" + days;
        }
        return (nowyear + "-" + months + "-" + days);
    }
    //获得某月的天数
    function getDaysInOneMonth(year, month){
        month = parseInt(month, 10);
        var d= new Date(year, month, 0);
        return d.getDate();
    }