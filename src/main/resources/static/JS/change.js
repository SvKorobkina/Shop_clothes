const tabParent = $('.tabs-block');

tabParent.on('click', '.tabs > div', function(){
    if(!$(this).hasClass('--active')) {
        tabParent
            .find('.tabs > .--active, .content .--active')
            .removeClass('--active');

        $(this)
            .addClass('--active');

        tabParent
            .find('.content > div')
            .eq($(this).index())
            .addClass('--active');
    }
});