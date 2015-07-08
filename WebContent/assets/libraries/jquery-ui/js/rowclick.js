$(document).ready(
		function() {
			$('.open').html("open");

			$('tr.available').click(
					function() {
						if ($(this).hasClass("active")) {
							$(this).removeClass("active").find('span')
									.removeClass("glyphicon glyphicon-ok")
									.html("open");
						} else {
							$(this).addClass("active").find('span').html("")
									.addClass("glyphicon glyphicon-ok");
						}
					});
			$('#clear').click(
					function() {
						$('table tbody tr').each(
								function() {
									if ($(this).hasClass("active")) {
										$(this).removeClass("active").find(
												'span').removeClass(
												"glyphicon glyphicon-ok");
									}
									$(this).find('span').html("open");
								});
					});
		});