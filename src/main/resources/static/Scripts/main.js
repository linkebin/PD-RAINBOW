/* 
 * Copyright 2017, hangzyihao
 * Date: 2017-09-03
 */
//--------------

(function(){

    //----- 系统当前时间 start --------
    function currentTime(){
        var d = new Date(),str = '';
        str += d.getFullYear()+'年';
        str  += d.getMonth() + 1+'月';
        str  += d.getDate()+'日';
        str += d.getHours()+':';
        str  += d.getMinutes()+':';
        str+= d.getSeconds()+'';
        return str;
    }
    setInterval(function(){$('.localTime').html(currentTime)},1000);
    // 使用class="localTime" 就可以系统当前时间，格式例子为：2017年9月3日15:41:48
    //----- 系统当前时间 end --------













})(jQuery)