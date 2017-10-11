/**
 * Created by linkb on 2017/7/18.
 */
Date.prototype.format = function(format) {
    var date = {
        "M+": this.getMonth() + 1,
        "d+": this.getDate(),
        "h+": this.getHours(),
        "m+": this.getMinutes(),
        "s+": this.getSeconds(),
        "q+": Math.floor((this.getMonth() + 3) / 3),
        "S+": this.getMilliseconds()
    };
    if (/(y+)/i.test(format)) {
        format = format.replace(RegExp.$1, (this.getFullYear() + '').substr(4 - RegExp.$1.length));
    }
    for (var k in date) {
        if (new RegExp("(" + k + ")").test(format)) {
            format = format.replace(RegExp.$1, RegExp.$1.length == 1
                ? date[k] : ("00" + date[k]).substr(("" + date[k]).length));
        }
    }
    return format;
}

$.ligerDefaults.Grid.formatters['toDate'] = function (num, column) {
    //num 当前的值
    //column 列信息
    if (!num) return "";
    var date =new Date(num)
    return date.format(column.format);
};
function dateT(date,forma) {
    var date =new Date(date);
    return date.format(forma);
}
function _hideOprcolMenu(target, event) {
    var a =target;
    var b =event;
}
function _showOprcolMenu(target,event) {
    var a =target;
    var b =event;

}
function _initOper() {
    return '<div id="operMenu" class="l-menubar" ligeruiid="operMenu" ><img src="/plugin/ligerUI/skins/icons/ico_opr.gif" /></div>';

}
