$(document).ready(function(){
        $("#upFiles").click(function(){
        		  $("#doc-modal-1").modal({width:'600px'});
        });
});
$(function() {
    'use strict';
    // 初始化
    var $image = $('#image');
    $image.cropper({
        aspectRatio: '1',
        autoCropArea:0.8,
        preview: '.up-pre-after',

    });

    // 事件代理绑定事件
    $('.docs-buttons').on('click', '[data-method]', function() {

        var $this = $(this);
        var data = $this.data();
        var result = $image.cropper(data.method, data.option, data.secondOption);
        switch (data.method) {
            case 'getCroppedCanvas':
            if (result) {
                // 显示 Modal
                $('#cropped-modal').modal().find('.am-modal-bd').html(result);
                $('#download').attr('href', result.toDataURL('image/jpeg'));
            }
            break;
        }
    });



    // 上传图片
    var $inputImage = $('#inputImage');
    var URL = window.URL || window.webkitURL;
    var blobURL;

    if (URL) {
        $inputImage.change(function () {
            var files = this.files;
            var file;

            if (files && files.length) {
               file = files[0];

               if (/^image\/\w+$/.test(file.type)) {
                    blobURL = URL.createObjectURL(file);
                    $image.one('built.cropper', function () {
                        // Revoke when load complete
                       URL.revokeObjectURL(blobURL);
                    }).cropper('reset').cropper('replace', blobURL);
                    $inputImage.val('');
                } else {

                }
            }

            // Amazi UI 上传文件显示代码
            var fileNames = '';
            $.each(this.files, function() {
                fileNames += '<span class="am-badge">' + this.name + '</span> ';
            });
            $('#file-list').html(fileNames);
        });
    } else {
        $inputImage.prop('disabled', true).parent().addClass('disabled');
    }

    //绑定上传事件
    $('#up-btn-ok').on('click',function(){
    	var $modal = $('#my-modal-loading');
    	var $modal_alert = $('#my-alert');
    	var img_src=$image.attr("src");
    	if(img_src==""){
    		set_alert_info("请选择图片");
    		return false;
    	}

    	$modal.modal();
        $modal.modal('close');
    	var url=$(this).attr("url");
    	var canvas=$("#image").cropper('getCroppedCanvas');
    	var data=canvas.toDataURL().toString(); //转成base64
        var jsonObj  =new Object();  ;
        jsonObj.image=data;
        $.ajax( {
                url:url,
                contentType: "application/json;charset=utf-8",
                dataType:"json",
                type: "POST",
                data: JSON.stringify(jsonObj),
                success: function(result){
                    $modal.modal();
                    $modal.modal('close');
                    $modal_alert.modal();
                	if(result.code== 200){
                        $("#userInfoForm input[name='headImg']").val(result.data);
                        $("#headImg").attr('src','/files'+result.data+'?t='+ Math.random());
                        $("#left_img_head").attr('src','/files'+result.data+'?t='+ Math.random());
                        $("#doc-modal-1").hide();
                        $(".am-active").hide();
                        parent.childCallParent('/files'+result.data+'?t='+ Math.random())
                	}else {
                        layer.msg(result.message);
                    }
                },
                error: function(){
                	$modal.modal('close');
                	set_alert_info("上传文件失败了！");
                	$modal_alert.modal();
                }
         });

    });

});

function rotateimgright() {
$("#image").cropper('rotate', 90);
}


function rotateimgleft() {
$("#image").cropper('rotate', -90);
}

function set_alert_info(content){

	layer.msg(content)
}



 
