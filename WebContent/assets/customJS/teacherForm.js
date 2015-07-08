	var payload;
	var toggle = 1;

		jQuery(function($) {
			
			
			$("#school").change(function(e){

				
				var visibility = $("#school").val().split(",");
				
				if($("#school").val() == ""){
					$("#school").css({
						"border-color" : "#B80000",
						"border-width" : "1px",
						"border-style" : "solid"
					});
				}else{
					$("#school").css({
						"border-color" : "#00C957",
						"border-width" : "1px",
						"border-style" : "solid"
					});
				}
				
				if(visibility[1] == "private"){
					//Spawn password field
					spawnPasswordField();
				}
				
				
			});
			
			$(".email").blur(function(){
				  //check to two here.
				  
				});

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
					leaguePass : {
						required : true
					},
					schoolName : {
						required : true
					}
					
				},
				errorPlacement : function() {
					return false;
				},
				submitHandler : function(form) {
				
						payload = $('#signup').serialize();
						addTeacherAndSchoolDetails(payload);
				
					
				}

			});

		});
		
		function passwordMismatchErr(){
			$("#signup input").removeAttr("disabled");
			$("#passMis").css("visibility", 'visible');
			$("#passMis").css("display", 'block');
			$("#schoolPass").css("visibility", 'hidden');
			$("#schoolPass").css("display", 'none');
		}
		
		function addTeacherAndSchoolDetails(payload) {
			$("#signup input").prop("disabled", true);

			$.ajax({
				type : 'POST',
				url : "http://www.classroom-hero.com/register/general",
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
					} else if (result == "Passwords don't match") {
						$("#signup input").removeAttr("disabled");
						$("#passMis").css("visibility", 'visible');
						$("#passMis").css("display", 'block');
						$("#schoolPass").css("visibility", 'hidden');
						$("#schoolPass").css("display", 'none');
					}else if(result == "invalidEmail"){
						$("#signup input").removeAttr("disabled");
						$("#invalidEmail").css("visibility", 'visible');
						$("#invalidEmail").css("display", 'block');
						$("#schoolPass").css("visibility", 'hidden');
						$("#schoolPass").css("display", 'none');
						$("#passMis").css("visibility", 'hidden');
						$("#passMis").css("display", 'none');
					}

				},
				error : function() {
					alert("Server encountered an error");
				}
			});
		}
		
		function spawnLeagueField(){
			if(toggle == 1){
				toggle = 0;
				$("#leagueDiv").css("visibility", 'visible');
				$("#leagueDiv").css("display", 'block');
				$("#toggleField").html('Nevermind');
			}else{
				toggle = 1;
				$("#leagueDiv").css("visibility", 'hidden');
				$("#leagueDiv").css("display", 'none');
				
				$("#leaguePassDiv").css("visibility", 'hidden');
				$("#leaguePassDiv").css("display", 'none');
				
				$("#toggleField").html('Join a League');
			}
			
		}
		
		function spawnPasswordField(){
			$("#leaguePassDiv").css("visibility", 'visible');
			$("#leaguePassDiv").css("display", 'block');
		}
		