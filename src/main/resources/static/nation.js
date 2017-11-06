/**
 * Created by smy on 2017/11/1.
 */
var nationUtil = {
    checkRadioFieldNoNull : function(names,formId) {
        var ival ='';
        $.each(names,function (key,val) {

            var val22=$('#'+formId+ ' input:radio[name="'+key+'"]:checked').val();

            var desc = $('#'+formId+ ' input:radio[name="'+key+'"]').eq(0).attr('disabled');

            if (desc!='disabled'){
                if(val22 ==null || val22 == undefined){
                    $('#'+formId+ ' input:radio[name="'+key+'"]').eq(0).parents('.eui-input-block,' +
                        '.eui-input-inline')
                        .css('border','1px solid red');
                    ival=val;
                    return false;
                }
            }

        });
        return ival;
    },
    nations: [
        {
            label: "H",
            value: "汉族"
        },
        {
            label: "M",
            value: "蒙古族"
        },
        {
            label: "H",
            value: "回族"
        },
        {
            label: "Z",
            value: "藏族"
        },
        {
            label: "W",
            value: "维吾尔族"
        },
        {
            label: "M",
            value: "苗族"
        },
        {
            label: "Y",
            value: "彝族"
        },
        {
            label: "Z",
            value: "壮族"
        },
        {
            label: "B",
            value: "布依族"
        },
        {
            label: "C",
            value: "朝鲜族"
        },
        {
            label: "M",
            value: "满族"
        },
        {
            label: "D",
            value: "侗族"
        },
        {
            label: "Y",
            value: "瑶族"
        },
        {
            label: "B",
            value: "白族"
        },
        {
            label: "T",
            value: "土家族"
        },
        {
            label: "H",
            value: "哈尼族"
        },
        {
            label: "H",
            value: "哈萨克族"
        },
        {
            label: "D",
            value: "傣族"
        },
        {
            label: "L",
            value: "黎族"
        },
        {
            label: "S",
            value: "僳僳族"
        },
        {
            label: "W",
            value: "佤族"
        },
        {
            label: "S",
            value: "畲族"
        },
        {
            label: "G",
            value: "高山族"
        },
        {
            label: "L",
            value: "拉祜族"
        },
        {
            label: "S",
            value: "水族"
        },
        {
            label: "D",
            value: "东乡族"
        },
        {
            label: "N",
            value: "纳西族"
        },
        {
            label: "J",
            value: "景颇族"
        },
        {
            label: "K",
            value: "柯尔克孜族"
        },
        {
            label: "T",
            value: "土族"
        },
        {
            label: "D",
            value: "达斡尔族"
        },
        {
            label: "M",
            value: "仫佬族"
        },
        {
            label: "Q",
            value: "羌族"
        },
        {
            label: "B",
            value: "布朗族"
        },
        {
            label: "S",
            value: "撒拉族"
        },
        {
            label: "M",
            value: "毛南族"
        },
        {
            label: "Y",
            value: "仡佬族"
        },
        {
            label: "X",
            value: "锡伯族"
        },
        {
            label: "A",
            value: "阿昌族"
        },
        {
            label: "P",
            value: "普米族"
        },
        {
            label: "T",
            value: "塔吉克族"
        },
        {
            label: "N",
            value: "怒族"
        },
        {
            label: "W",
            value: "乌孜别克族"
        },
        {
            label: "E",
            value: "俄罗斯族"
        },
        {
            label: "E",
            value: "鄂温克族"
        },
        {
            label: "D",
            value: "德昂族"
        },
        {
            label: "B",
            value: "保安族"
        },
        {
            label: "Y",
            value: "裕固族"
        },
        {
            label: "J",
            value: "京族"
        },
        {
            label: "T",
            value: "塔塔尔族"
        },
        {
            label: "D",
            value: "独龙族"
        },
        {
            label: "E",
            value: "鄂伦春族"
        },
        {
            label: "C",
            value: "赫哲族"
        },
        {
            label: "M",
            value: "门巴族"
        },
        {
            label: "L",
            value: "珞巴族"
        },
        {
            label: "J",
            value: "基诺族"
        }
    ]
};