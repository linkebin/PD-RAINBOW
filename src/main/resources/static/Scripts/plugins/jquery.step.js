!function(i) {
    i.fn.step = function(e) {
        var t = this,
        n = {
            index: 0,
            time: 400,
            title: [],
            us: 'ui-step-item-num span',
			textshowID:'pageNumber',    //页面数字展示  里面展示的是ID名称
			prevBtnID:'prevBtn',        // 上一页按钮
			nextBtnID:'nextBtn',        // 下一页按钮
			jumpBtnID:'jumpBtn',        // 跳转按钮
			inputPageID:'inputPage'     // 输入框
			
        },
		tc=t.children();
		l = tc.length,
        s = (e = i.extend({},n, e)).title,
        d = s.length,
        u = e.time,
        p = t.width() / l;
		
        t.index = e.index;
		
		tc.hide().first().show();
		
	//	var $step = $("#step");
	//	var $index = $("#index");
		
		
     
		
		var a = function(){
			var e ="";
			var th=t.html();
			 
			l > 0 &&( e +='<div class="ui-step-wrap">'
			
			+'<ul class="ui-step">',
			 i.each(tc,function(i,t) {
				var  title = tc.eq(i).attr("title");
                e += '<li class="ui-step-item" style="width:'+ 1/l*100 +'%">'
				+'<div class="ui-step-item-title">' + title + '</div>'
				+'<div class="ui-step-item-num"><span>'+ (i+1) +'</span></div></li>'
            }),
			
			e +="</ul>"
			+'<div class="ui-step-bg"></div>'
			+'<div class="ui-step-progress"></div>'
			+"</div>"),
			
			t.before(e),
            t.toStep(t.index)
		//	console.log(t.find(".ui-step").children(".ui-step-item").html())
		};
		
		
        return t.toStep = function(e) {
		var n = t.prev(".ui-step-wrap").find(".ui-step").children(".ui-step-item");
            t.index = e,
			tc.hide();
			tc.eq(t.index).fadeIn();
			t.prev(".ui-step-wrap").find(".ui-step-progress").animate({
                width: 1/l*100 * (e + 1)+"%"
            },u,function(){
                i.each(n,function(t) {
                    t > e ? i(this).removeClass("active") : i(this).addClass("active")
                })
            })
			
        },
        t.getIndex = function() {
            return t.index+1
        },
        t.nextStep = function() {
			if(t.index>=l-1) layer.msg("已经是最后一页了");
            t.index > l - 2 || (t.index++, t.toStep(t.index));
        },
        t.prevStep = function() {
			if(t.index<=0) layer.msg("已经是第一页了");
            t.index < 1 || (t.index--, t.toStep(t.index));
        },
		t.pageToStep = function () {

         //   console.log($(this).closest("li").index());

        },



/*

            $("."+e.us).on("click", function() {
					alert("d");
            }),

*/



        //上一页
		$("#"+ e.prevBtnID).on("click", function() {
			t.prevStep();
			$("#"+e.textshowID).text(t.getIndex());
		}),
		
		//下一页
		$("#"+e.nextBtnID).on("click", function() {
			t.nextStep();
			$("#"+e.textshowID).text(t.getIndex());
		}),

		
		//跳转
		$("#"+e.jumpBtnID).on("click", function() {
			var pl=parseInt($("#"+e.inputPageID).val());
			 if(!isNaN(pl)){
					pl>l? layer.msg("已超出最大页数"):pl< 1?layer.msg("对不起，最小值不能小于1"):t.toStep(pl-1);
				} else{
					layer.msg("不闹，请输入数字！")
				}
			$("#"+e.textshowID).text(t.getIndex());
		}),


		//页数展示
		$("#"+e.textshowID).text(t.getIndex()),

            //跳转



	    a(),
        this
    }
} (jQuery);
























