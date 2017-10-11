var menus = {
};
$.pageLoad.register("before", function () {
    $.ajax({
        type: 'POST',
        url: '/sec/menu/indexMenuTree',
        success:function (result) {
            menus = result.data;
            var _menus = menus;
            Index.shortcut.render(shortcuts) ;
            Index.menu.init(_menus);
        }
    });
});
var shortcuts = [
    { id: "2", name: "退出系统", thumbnail: "../Styles/images/icons/stop.png", url: "logout" }
];












