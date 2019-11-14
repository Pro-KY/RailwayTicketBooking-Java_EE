$(function () {
    $('#datetimepicker3').datetimepicker({
        format: 'LT'
    });
});

$(function () {
    $('#datetimepicker4').datetimepicker({
        format: 'L'
    });
});

$('#myModal').on('show.bs.modal', function (e) {
    console.log(this);
    let stations = $(e.relatedTarget).data('stations');
    console.log(stations);
    $(this).find('.modal-body input').val(stations);
});

$(document).ready(function($) {
    $(".table-row").click(function() {
        window.document.location = $(this).data("href");
    });
});

$('.example-popover').popover({
    trigger: 'focus'
});

$(document).ready(function(){
    let countSelector = $('.count');

    countSelector.prop('disabled', true);
    $(document).on('click','.plus',function(){
        countSelector.val(parseInt(countSelector.val()) + 1 );
    });
    $(document).on('click','.minus',function(){
        countSelector.val(parseInt(countSelector.val()) - 1 );
        if (countSelector.val() === 0) {
            countSelector.val(1);
        }
    });
});
