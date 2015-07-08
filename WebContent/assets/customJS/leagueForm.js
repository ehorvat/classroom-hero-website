var payload;
		jQuery(function($) {

			$("#signup").validate({
				onkeyup : true,
				rules : {
					fname : {
						required : true
					},
					lname : {
						required : true
					},
					email : {
						required : true
					},
					password : {
						required : true
					},
					confpass : {
						required : true
					},
					school : {
						required : true
					},
					creationcode : {
						required : true
					}
				},
				errorPlacement : function() {
					return false;
				},
				submitHandler : function(form) {
					payload = $('#signup').serialize();
					addLeague(payload);
				}

			});

		});
		function addLeague(payload) {
			$("#signup input").attr("disabled", 'disabled');

			$.ajax({
				type : 'POST',
				url : "http://localhost/ClassHero/register/league",
				contentType : 'application/x-www-form-urlencoded',
				data : payload,
				success : function(result) {
					if (result == "success") {
						$("#passMis").css("visibility", 'hidden');
						$("#schoolPass").css("visibility", 'hidden');
						$("#signup input").removeAttr("disabled");
						$("#signup").remove();
						$("#successMsg").css("visibility", "visible");
					} else if (result == "wrongCode") {
						$("#signup input").removeAttr("disabled");
						$("#schoolPass").css("visibility", 'visible');
						$("#schoolPass").css("display", 'block');
						$("#passMis").css("visibility", 'hidden');
						$("#passMis").css("display", 'none');
					} else if (result == "passMismatch") {
						$("#signup input").removeAttr("disabled");
						$("#passMis").css("visibility", 'visible');
						$("#passMis").css("display", 'block');
						$("#schoolPass").css("visibility", 'hidden');
						$("#schoolPass").css("display", 'none');
					}

				},
				error : function() {
					alert("The server encountered an error");
				}
			});
		}