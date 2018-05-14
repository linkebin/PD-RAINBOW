/*-----20180417 by zhangyihao-----*/

eui.use(['element','layer','jquery'], function(){

    var element = eui.element
        ,layer = eui.layer
        ,jquery = eui.jquery;

    $(function(){

    layer.confirm('是否进入全屏模式！', {
        	title:'提示信息'
            ,closeBtn:0
            ,btn: ['我要全屏','我不要'] //按钮
        }, function(index){
        enterfullscreen();
            startTime();
            layer.close(index)
        }, function(){
            startTime();

        });

        /*----秒表计时器 start------------------------*/

        var h=0,m=0,s=0;
        var int;

        function  startTime() {
            int = setInterval(timer,1000);
        }

        function timer() {
            s++;
            if(s>=60){
                s = 0;
                m = m+1;
            }
            if(m>=60){
                m = 0;
                h = h+1;
            }
            s = checkTime(s);
            m = checkTime(m);
            h = checkTime(h);
            $("#timer").text(h+":"+ m +":"+s);
            m = parseInt(m);
            h = parseInt(h);
        }

        function checkTime(i)    //检查是否小于10
        {
            if (i<10){
                i ="0" + i;
            }
            return i;
        }


        /*------秒表计时器 end----------*/

    });


    //全屏按钮

  //  var f=0;
    $("#fullScreen").on("click",function () {
       if($(this).text()=="全屏"){
           enterfullscreen()
       }else{
           exitfullscreen();

       }
    });

    //控制全屏
    function enterfullscreen() {//进入全屏

        var docElm = document.documentElement;
//W3C
        if (docElm.requestFullscreen) {
            docElm.requestFullscreen();
        }
//FireFox
        else if (docElm.mozRequestFullScreen) {
            docElm.mozRequestFullScreen();
        }
//Chrome等
        else if (docElm.webkitRequestFullScreen) {
            docElm.webkitRequestFullScreen();
        }
//IE11
        else if (elem.msRequestFullscreen) {
            elem.msRequestFullscreen();
        }

        document.getElementById("fullScreen").innerHTML='退出全屏';



    }
    function exitfullscreen() { //退出全屏

        if (document.exitFullscreen) {
            document.exitFullscreen();
        }
        else if (document.mozCancelFullScreen) {
            document.mozCancelFullScreen();
        }
        else if (document.webkitCancelFullScreen) {
            document.webkitCancelFullScreen();
        }
        else if (document.msExitFullscreen) {
            document.msExitFullscreen();
        }
        document.getElementById("fullScreen").innerHTML='全屏';


    }


    document.onkeydown = function(event){if ((event.keyCode == 122)||(event.keyCode == 27)) {console.log(event.keyCode);return false;}}
    window.onhelp = function(){return false;}

});
