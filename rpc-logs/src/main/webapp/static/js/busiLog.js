;
(function($, window, undefined) {

  var j_search = {};

  j_search.bindDate = function() {

    $("#beginTime").datepicker({
      defaultDate: "+1w",
      changeMonth: true,
      numberOfMonths: 1,
      onClose: function(selectedDate) {
        $("#endTime").datepicker("option", "minDate", selectedDate);
      }
    });

    $("#endTime").datepicker({
      defaultDate: "+1w",
      changeMonth: true,
      numberOfMonths: 1,
      onClose: function(selectedDate) {
        $("#beginTime").datepicker("option", "maxDate", selectedDate);
      }

    });

  };

  // 检查
  j_search.checkAll = function() {
    var flag = true;
    var $msg = $("<div title='警告'></div>");
    var shopId = $("#shopId option:selected").val();
    if (!j_util.validationText(shopId)) {

      // 判断是否选择QA
      var qaUserId = $("#qaUserId option:selected").val();
      if (!j_util.validationText(qaUserId)) {
        flag = false;
        $msg.append($("<p class='text-danger'>请选择店铺。</p>"));
      }
    }

    var beginTime = $("#beginTime").val();
    if (!j_util.validationText(beginTime)) {
      flag = false;
      $msg.append($("<p class='text-danger'>请选择开始时间。</p>"));
    }

    var endTime = $("#endTime").val();
    if (!j_util.validationText(endTime)) {
      flag = false;
      $msg.append($("<p class='text-danger'>请选择结束时间 。</p>"));
    }

    if (!flag) {
      $($msg).dialog({
        modal: true
      });
    }

    return flag;
  };

  // 查询考评
  j_search.search = function() {
    if (j_search.checkAll()) {
      $(this).prop("disabled", "disabled");
      $("#searchForm").submit();
    }
  };

  $(function() {
    j_search.bindDate();
    //j_search.search();
    j_util.queryPaging();
  });

})(jQuery, window);