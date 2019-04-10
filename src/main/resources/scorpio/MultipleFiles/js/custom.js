// tab js strat
$(document).on('click', '.browse', function(){
  var file = $(this).parent().parent().parent().find('.file');
  file.trigger('click');
});
$(document).on('change', '.file', function(){
  $(this).parent().find('.form-control').val($(this).val().replace(/C:\\fakepath\\/i, ''));
});
// tab js end

$(document).ready(function(){

    $('.folder').on('click', function(e) {
        $(this).toggleClass('folder-open');
        e.stopPropagation();
    });
    
    $('.file').on('click', function(e) {
       e.stopPropagation(); 
    });
});

//FANCYBOX strat
$(document).ready(function(){
    //FANCYBOX
    //https://github.com/fancyapps/fancyBox
    $(".fancybox").fancybox({
        openEffect: "none",
        closeEffect: "none"
    });
});
//FANCYBOX END