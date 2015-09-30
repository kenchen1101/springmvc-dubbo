;
(function($, undefined) {
  $(function() {
    $("#queryButton").click(function() {
      jpage.ajaxPage('queryForm', 'busiLogBlock');
    });
  });

})(jQuery);