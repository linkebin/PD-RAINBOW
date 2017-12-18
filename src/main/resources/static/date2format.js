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
    function getMonthStartDate()
    {
        var date = new Date();
        var monthStartDate = new Date(date.getFullYear(),date.getMonth()+1,1);

        return (date.getFullYear() + "-" + p(date.getMonth()+1) + "-01");
    }

    //获得月份的结束日期
    function getMonthEndDate(nowyear,months)
    {
        var days= getDaysInOneMonth(nowyear,months);//获取当月总共有多少天
        var monthEndDate = new Date(nowyear,months, days);
        return (nowyear + "-" + p(months) + "-" + days);
    }

    //获得某月的天数
    function getDaysInOneMonth(year, month){
        month = parseInt(month, 10);
        var d= new Date(year, month, 0);
        return d.getDate();
    }

    //上一个月
    function getNowLastMonth() {
        var currentYear=new Date().getFullYear();
        var currentMonth=new Date().getMonth()+1;
        var lastMonth=new Date().getMonth();
        var currentDate=new Date().getDate();

        var prevCurrentYear=0;
        var prevCurrentMonth=0;
        if(currentMonth==0){
            prevCurrentYear=currentYear-1;
            prevCurrentMonth=12;
        }else{
            prevCurrentYear=currentYear;
            prevCurrentMonth=currentMonth-1;
        }
        var lastmonth=prevCurrentYear+"-"+prevCurrentMonth+"-"+p(currentDate)
        var obj= new Object();
        obj.prevCurrentYear=prevCurrentYear;
        obj.prevCurrentMonth=prevCurrentMonth;
        obj.currentDate=currentDate;
        obj.lastmonth=lastmonth;
        return obj;
    }

    //格式化
    function p(s) {
        return s < 10 ? '0' + s: s;
    }
    

    //往后几个月
    function addmulMonth(dtstr, n)
    {
        var s = dtstr.split("-");
        var yy = parseInt(s[0]);
        var mm = parseInt(s[1])-1 ;
        var dd = parseInt(s[2]);
        var dt = new Date(yy, mm, dd);
        dt.setMonth(dt.getMonth() + n);
        var month = parseInt(dt.getMonth()) + 1;
        return dt.getFullYear() + "-" + month  + "-" + dd;
    }

    //倒退几个月
    function removemulMonth(dtstr, n)
    {
        var s = dtstr.split("-");
        var yy = parseInt(s[0]);
        var mm = parseInt(s[1]-1);
        var dd = parseInt(s[2]);
        var dt = new Date(yy, mm, dd);
        dt.setMonth(dt.getMonth() - n);
        var month = parseInt(dt.getMonth()) + 1;
        return dt.getFullYear() + "-" + p(month)  + "-" + p(dd);
    }
