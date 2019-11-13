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
    // $(this).find('.modal-content .modal-body p').val(stations)
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