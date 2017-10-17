$(function() {
    function ratingEnable() {


        $('.example-0').barrating('show', {
            wrapperClass: 'br-wrapper-e',
            initialRating: 'A',
            showValues: true,
            showSelectedRating: false
            //onSelect:function(value, text) {
            //    alert('Selected rating: ' + value);
            //}
        });

        $('#example-f').barrating({
            wrapperClass: 'br-wrapper-f',
            showSelectedRating: false
        });
        $('#example-q').barrating({
            wrapperClass: 'br-wrapper-f',
            showSelectedRating: false
        });
        $('#example-w').barrating({
            wrapperClass: 'br-wrapper-f',
            showSelectedRating: false
        });
        $('#example-p').barrating({
            wrapperClass: 'br-wrapper-f',
            showSelectedRating: false
        });
        $('#example-z').barrating({
            wrapperClass: 'br-wrapper-f',
            showSelectedRating: false
        });
        $('#example-t').barrating({
            wrapperClass: 'br-wrapper-f',
            showSelectedRating: false
        });
        $('#example-x').barrating({
            wrapperClass: 'br-wrapper-f',
            showSelectedRating: false
        });
        $('#example-j').barrating({
            wrapperClass: 'br-wrapper-f',
            showSelectedRating: false
        });

    }

    function ratingDisable() {
        $('select').barrating('destroy');
    }

    $('.rating-enable').click(function(event) {
        event.preventDefault();

        ratingEnable();

        $(this).addClass('deactivated');
        $('.rating-disable').removeClass('deactivated');
    });

    $('.rating-disable').click(function(event) {
        event.preventDefault();

        ratingDisable();

        $(this).addClass('deactivated');
        $('.rating-enable').removeClass('deactivated');
    });

    ratingEnable();
});
