/*-----20180417 by zhangyihao-----*/

eui.use(['element','layer','jquery'], function(){

    var element = eui.element
        ,layer = eui.layer
        ,jquery = eui.jquery;

    $(function(){

        calculateFillmain();

                var itemSum=0;
                var listNum=10;   //一页多少条
                var con=""
                    ,conbody =""
                    ,number=1;
                 var array=new Array;
                 var datas=new Array;
                    datas.code=1;
                    datas.stem='头痛';
                    datas.option={"answer1":1,"answer2":2,"answer3":3,"answer4":4,"answer5":5};
                    var datas1=new Array;
                    datas1.code=2;
                    datas1.stem='头痛';
                    datas1.option={"answer1":1,"answer2":2,"answer3":3,"answer4":4,"answer5":5};
                 array.push(datas);
                 array.push(datas1);
                var arr = new Array();   //存储分数数组

         $.each(array,function(i,data){
            arr[itemSum]=new Array();
            var j = 0;
            con +='<div class="itemList" code="'+ data.code  +'">'
            +'<div class="itemLeft">'+ number +'、'+ data.stem +'</div>'
            +'<div class="itemRight">';
            $.each(data.option,function(n,data){
                //arr[itemSum][j] = data;
                con +=' <a href="###"></a>';
                j++;
            });
            con +=' </div>'

            +' </div>';
            itemSum++;
            number++;
             console.info(itemSum+'=='+listNum);
             console.info(itemSum%listNum);
            if(itemSum%listNum === 0){
                console.info(0);
                conbody +='<div class="stepItem" title="">'
                +'<div class="itemListHeader">'
                +'<div class="itemLeft">'
                +' </div>'
                +'<div class="itemRight">'
                +'<a href="###">没有</a>'
                +'<a href="###">很轻</a>'
                +'<a href="###">中等</a>'
                +'<a href="###">偏重</a>'
                +'<a href="###">严重</a>'
                +'</div></div>'+ con +'</div>';
                console.info(conbody);
                $(".stepContent").append(conbody);
                con="";
                conbody="";
            }
         });

             //将剩下的进行打包加到后面

             if(itemSum%listNum!="0"){
                 conbody +='<div class="stepItem" title="">'
                 +'<div class="itemListHeader">'
                 +'<div class="itemLeft">'
                 +' </div>'
                 +'<div class="itemRight">'
                 +'<a href="###">没有</a>'
                 +'<a href="###">很轻</a>'
                 +'<a href="###">中等</a>'
                 +'<a href="###">偏重</a>'
                 +'<a href="###">严重</a>'
                 +'</div></div>'+ con +'</div>';
                 $(".stepContent").append(conbody);
                 console.info(conbody);
             };


             var $sum = $("#sumS");
            //进行渲染
            var $step = $("#step");
            $step.step({
                nextBtnID:""
            });

            //提示未完成，是否继续下一页
            $("#nextBtn").on("click", function(e) {
                var $itemList=$(".stepItem:visible .itemList");
                var tf=true;
                var test="";
                $itemList .each(function () {
                    var s =$(this).find(".itemRight .selected").length;
                    tf= s>0 ? true:false;
                    if(!tf){
                        $(this).addClass("itemUnSelect");
                    }
                });
                if(tf){
                    $step.nextStep();
                }else {
                    layer.confirm('当前页面还有题目未完成，是否继继进入下一页？', {
                        title:'提示信息'
                        ,btn: ['确认','取消'] //按钮
                    }, function(index){
                        layer.close(index);
                        //   layer.msg('的确很重要', {icon: 1});
                        $step.nextStep();
                    });
                }

            });

            /* $("#submitQ").on("click",function () {

             var $itemList=$(".stepItem .itemList");
             var tf=true;
             var scoreSum=0;

             var curCode="";

             $itemList .each(function () {
             var s =$(this).find(".itemRight .selected").length;

             tf= s>0 ? true:false;
             if(tf){
             curCode=parseInt($(this).closest(".itemList").attr("code"));
             scoreSum = scoreSum + arr[curCode-1][s-1];
             }else{
             $(this).addClass("itemUnSelect");
             };

             });
             if(tf){
             layer.confirm('你已完成了所有题目，是否确认提交', {
             title:'提示信息'
             ,btn: ['确认提交','我再想想'] //按钮
             }, function(index){
             layer.close(index);
             layer.msg('提交成功，总分：'+ scoreSum, {icon: 1});

             console.log("总分："+ scoreSum);

             });

             }else {

             layer.confirm('当前页面还有题目未完成，不能提交！', {
             title:'提示信息'
             ,btn: ['返回填写'] //按钮
             }, function(index){
             layer.close(index);
             //   layer.msg('的确很重要', {icon: 1});

             });
             }

             });*/

            clickOption(itemSum);
            calculate();


    });



    //点击选择题目（90项清单类型的）
    function clickOption(e,itemSum) {


        var fpValue=parseInt($("#fillProgress> div").attr("e-percent"))
            ,addV=parseFloat((100/e).toFixed(2))
            ,sn=0;

       // var curCode="",optionCode="";

        $(".itemList .itemRight a").on("click", function() {
            $(this).closest(".itemList").removeClass("itemUnSelect");
            //进度条
            var tf=$(this).closest(".itemRight").find("a").hasClass("selected");
            if(!tf){
                fpValue =parseFloat((fpValue + addV).toFixed(2));
                ++sn;
                sn >= e?element.progress('fillProgress','100%'):element.progress('fillProgress', fpValue+'%');

        //        curCode=$(this).closest(".itemList").attr("code");
            }

        //    optionCode=$(this).index()+1;
        //    console.log("第 "+curCode+" 题，第 "+ optionCode+" 项");

            var index = $(this).index();
            $(this).closest(".itemRight").find("a:lt("+ index+1 +")").addClass("selected");
            $(this).closest(".itemRight").find("a:gt("+ index +")").removeClass("selected");


        });

    }


    //计算元素高度：包括内外边距
    function CH(e) {return $(e).outerHeight(true);}

    var sCH= CH(".stepContent");
    function calculate() {
       var itemRightAH=CH(".stepContent")-CH(".itemListHeader")
           ,itemRightAL=$(".stepItem:visible .itemList").length;
        $(".itemList").height(itemRightAH/itemRightAL-10);

    }

    function calculateFillmain() {
        var tipsDivH=$(".tipsDiv").offset().top + $(".tipsDiv").outerHeight();
        $(".fillMain").css("top",tipsDivH+8);
    }





    //监听浏览器窗口变化
    $(window).resize(function (){
        calculateFillmain();
        calculate();
    });







});
