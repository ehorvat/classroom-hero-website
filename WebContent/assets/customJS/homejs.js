var topRange = 200, // measure from the top of the viewport to X pixels down
		edgeMargin = 20, // margin above the top or margin from the end of the page
		animationTime = 1200, // time in milliseconds
		contentTop = [];
		var item_name, itemCost, itemId, original, rowId, elementId;
		var student_cog = 0;
		var item_cog = 0;
		var category_cog = 0;
		var lastId = lastId || {};
		var lastCategory = lastCategory || {};
		var lastStu = lastStu || {};
		var categoryCount;
		var rewardCount;
		var newId = 12;
		var activate;
		
		$(document).ready(function() {
			$('#allstudents').dataTable({
				"sDom" : '<"top"><"bottom"><"clear">',
				'iDisplayLength' : 100,
				"oLanguage": {
				    "sEmptyTable": '',
				    "sInfoEmpty": ''
				   }
			});
			
			toastr.options.positionClass = 'toast-top-center';
			
			categoryCount = $("#categoryPost .panel").children("div").length;
			rewardCount = $("#rewardPost .panel").children("div").length;
		
			if($("#jarPost span.jarName").text()==""){
				$('#editJar').click(function () {return false;});
			}else{
				$('#addJar').click(function () {return false;});
			}
			
			activate = 0;
		
		});

		jQuery(function($) {


			
			$("#addCategory").validate({
				onkeyup : true,
				rules : {
					newCategoryName : {
						required : true
					}
				},
				errorPlacement : function() {
					return false;
				},
				submitHandler : function(form) {
					$("#addCategory #save_item").attr("disabled", true);
					addCategory();
				}
			});
			$("#editCategory").validate({
				onkeyup : true,
				rules : {
					newCategoryName : {
						required : true
					}
				},
				errorPlacement : function() {
					return false;
				},
				submitHandler : function(form) {
					saveCategoryChanges();
				}
			});
			
			
			
			
			
			$("#create_item").validate({

				onkeyup : true,
				rules : {
					item_title : {
						required : true
					},
					item_description : {
						required : true
					},
					item_cost : {
						required : true
					}
				},
				errorPlacement : function() {
					return false;
				},
				submitHandler : function(form) {
					createItem();
				}
			});

			$("#editItem").validate({
				onkeyup : true,
				rules : {
					item_title : {
						required : true
					},
					item_cost : {
						required : true
					}
				},
				errorPlacement : function() {
					return false;
				},
				submitHandler : function(form) {
					
					saveItemChanges();
				}
			});
			
			$("#editStudent").validate({
				onkeyup : true,
				rules : {
					edit_name : {
						required : true
					}
				},
				errorPlacement : function() {
					return false;
				},
				submitHandler : function(form) {
					
					saveStudentChanges();
				}
			});
			
			$("#newStudentForm").validate({
				onkeyup : true,
				rules : {
					fname : {
						required : true
					},
					lname : {
						required: true
					}
				},
				errorPlacement : function() {
					return false;
				},
				submitHandler : function(form) {
					
					addStudentDetails();
				}
			});
			
			$("#addJarForm").validate({
				onkeyup : true,
				rules : {
					newJarName : {
						required : true
					},
					newJarTotal : {
						required: true
					}
				},
				errorPlacement : function() {
					return false;
				},
				submitHandler : function(form) {
					
					$("#addJarForm #save_jar").attr("disabled", true);
				
					addJar();
				}
			});
			
			$("#editJarForm").validate({
				onkeyup : true,
				rules : {
					editJarName : {
						required : true
					},
					editJarTotal : {
						required: true
					}
				},
				errorPlacement : function() {
					return false;
				},
				submitHandler : function(form) {
				
					editJar();
				}
			});
		
			
			
		});
		
		function editJar(){
			payload = $('#editJarForm').serialize();
			
			var values = $("#editJarForm").serializeArray();
			
			var jarName = values[0].value;
			var jarTotal = values[1].value;	
			
			if(Math.floor(jarTotal) == jarTotal && $.isNumeric(jarTotal)){

			$.ajax({
				type : 'POST',
				url : "http://www.classroom-hero.com/jar/edit",
				contentType : 'application/x-www-form-urlencoded',
				dataType : 'json',
				data : payload,
				success : function(result) {
			
					
					$("#jarPost span.jarName").text(jarName);
					$("#jarPost span.jarTotal").text(jarTotal);
					//$("#editJarModal .modal-body #category_field").val("");
					$('#editJarModal').modal('hide');



				},
				error : function() {
					alert("Server encountered an error.");
				}
			});
			}else{
				$("#addJarForm #save_jar").attr("disabled", false);

				$("#errEditJarNum").css("visibility", 'visible');
			}
		}
		
		function addJar(){
			
			payload = $('#addJarForm').serialize();
			
			var values = $("#addJarForm").serializeArray();
			
			var jarName = values[0].value;
			var jarTotal = values[1].value;	
			
			if(Math.floor(jarTotal) == jarTotal && $.isNumeric(jarTotal)){
			
			$.ajax({
				type : 'POST',
				url : "http://www.classroom-hero.com/jar/add",
				contentType : 'application/x-www-form-urlencoded',
				dataType : 'json',
				data : payload,
				success : function(result) {
		
					$("#jarPost span.jarName").text(jarName);
					$("#jarPost span.jarProgress").text('0');
					$("#jarPost span.jarTotal").text(jarTotal);
				
	
					$("#addJarModal").modal('hide');
					
					$('#editJar').unbind('click');
					$('#addJar').click(function () {return false;});
					toastr.success('Successfully created '+result.name);
		
				},
				error : function() {
					alert("Server encountered an error.");
				}
			});
			}else{
				$("#addJarForm #save_jar").attr("disabled", false);
				$("#errAddJarNum").css("visibility", 'visible');
			}
		}
		
		
		function saveStudentChanges(){
			
			payload = $('#editStudent').serialize() + "&original=" + original;
		
			var values = $("#editStudent").serializeArray();
			
			var newName = values[0].value;	
			
			$.ajax({
				type : 'POST',
				url : "http://www.classroom-hero.com/changeStudent",
				contentType : 'application/x-www-form-urlencoded',
				data : payload,
				success : function(result) {
					
					$("#"+elementId).find("td:first").text(newName);
					$('#editStudentModal').modal('hide');


				},
				error : function() {
					alert("Server encountered an error");
				}
			});
		}
		
		function saveCategoryChanges(){
			payload = $('#editCategory').serialize() + "&original=" + original;
			
			var values = $("#editCategory").serializeArray();
			
			var newCategory = values[0].value;
			
			
			$.ajax({
				type : 'POST',
				url : "http://www.classroom-hero.com/editCategory",
				contentType : 'application/x-www-form-urlencoded',
				dataType : 'json',
				data : payload,
				success : function(result) {
			
					$("#"+elementId).find('span.editCategory').text(newCategory);
					$("#editCategoryModal .modal-body #category_field").val("");
					$('#editCategoryModal').modal('hide');


				},
				error : function() {
					alert("Server encountered an error");
				}
			});
		}


		
		function saveItemChanges(){
			
			var values = $("#editItem").serializeArray();
			
			var newTitle = values[0].value;
			var newCost = values[1].value;
			
			payload = $('#editItem').serialize() + "&original=" + original;
			
			if(Math.floor(newCost) == newCost && $.isNumeric(newCost)){
			
			$.ajax({
				type : 'POST',
				url : "http://www.classroom-hero.com/item/change",
				contentType : 'application/x-www-form-urlencoded',
				dataType : 'json',
				data : payload,
				success : function(result) {
					
					$("#"+elementId).find('span:nth-child(1)').text(newTitle);
					$("#"+elementId).find('span:nth-child(2)').text(newCost);
					$('#editItemModal').modal('hide');
						

				},
				error : function() {
					alert("The server encountered an error");
				}
			});
			
			}else{
				$("errPNum").css("visibility", 'visible');
			}
		}

		
		
		
		
		function addStudentDetails() {
		
		
			newId = newId + 1;
			
			payload = $('#newStudentForm').serialize();
		
			

				$.ajax({
					type : 'POST',
					url : "http://www.classroom-hero.com/register/addStudent",
					contentType : 'application/x-www-form-urlencoded',
					dataType : 'json',
					data : payload,
					success : function(result) {

					
							//Append return value to table row
							//Or render JSP with values
							$("table tbody").append(
									"<tr id='rowId"+newId+"'"+">" + "<td>" + result.fname + " "
											+ result.lname + "</td>" + "<td style='padding-left:23px;'>0</td>"
											+ "</tr>");
							
						$("#newStudentForm input").val("");
						
						toastr.success('Successfully added '+ result.fname + " " + result.lname);

						

					},
					error : function() {
						alert("Server encountered an error");
					}
				});

		}
	
		
		function createItem() {
			
			//alert("count "+rewardCount);
			
	
				payload = $('#create_item').serialize();
				
				var values = $('#create_item').serializeArray();
				
				var newTitle = values[0].value;
				var newCost = values[1].value;
				

				if(Math.floor(newCost) == newCost && $.isNumeric(newCost)){
					$.ajax({
						type : 'POST',
						url : "http://www.classroom-hero.com/item/create",
						contentType : 'application/x-www-form-urlencoded',
						dataType : 'json',
						data : payload,
						success : function(result) {
					
						
								
								var html = "<DIV class='panel panel-primary center panel_width' style='text-align: center;'>" +
												"<DIV class='panel-heading show_reward_cog' style='height: 50px;' id='itemId"+newId+"'"+">" +
												"<span class='item_name_style'>" + result.name + "</span>" +
												"<span class='item_cost_style'>" + result.cost + "</span>" +
												"</DIV>" +
											"</DIV>";
								
								
								$('#rewardPost').append(html);
								
								
								$("#myModal").modal('hide');
								
								$("#myModal #edit_title").html("");
								$("#myModal #edit_cost").html("");
								toastr.success('Successfully created '+result.name);
								rewardCount = rewardCount + 1;
							

						},
						error : function() {
							alert("Server encountered an error");
						}
					});
				}else{
					$("#errP").css("visibility", 'visible');
				}			
	
		}


		function addCategory() {
			
	
				
				payload = $('#addCategory').serialize();
				var newCategory = $("#category_field").val();
				
				
				$.ajax({
							type : 'POST',
							url : "http://www.classroom-hero.com/createCategory",
							contentType : 'application/x-www-form-urlencoded',
							data : payload,
							success : function(result) {
								$("#addCategory #save_item").attr("disabled", false);
						
									var html2 = "<DIV class='panel panel-success center panel_width' style='text-align: center;'>" +
									"<DIV class='panel-heading show_category_cog' style='height: 50px;' id='panelNo"+newId+"'"+">" +
									"<span class='item_name_style editCategory'>" + newCategory + "</span>" +
									"</DIV>" +
									"</DIV>";
									
									$('#categoryPost').append(html2);
									$(".modal-body #category_field").text("");
									toastr.success('Successfully created '+newCategory);
									
									$("#addCategoryModal").modal('hide');
								
								

							},
							error : function(jqXHR, textStatus, errorThrown) {
								$("#addCategory #save_item").attr("disabled", false);
							}
						});
			
			
			
		}
	

		$(function() {
			
		
	
		
			$(document).on('click', '.edit_selection', function(event){
				
				var option = event.target.id;
			
				if(option == "student_edit"){
					
					if(student_cog == 0){
					   student_cog = 1;
					   $("#allstudents tbody tr td:nth-child(3)").append("<span id='student_cog' style='float:right;' class='glyphicon glyphicon-cog cog' data-target='#editStudentModal' data-toggle='modal'></span>");
					}else{
						student_cog = 0;
						$("#allstudents tbody tr span.cog").remove();
					}
				}else if(option == "item_edit"){
					$("#errP").css("visibility", 'hidden');
					
					if(item_cog == 0){
						   item_cog = 1;
							$(".show_reward_cog span.item_cost_style").append("<span id='item_cog' style='float:right; padding-top:10px;' class='glyphicon glyphicon-cog cog' data-target='#editItemModal' data-toggle='modal'></span>");
						}else{
							item_cog = 0;
							$(".show_reward_cog span.cog").remove();
						}
					
				}else if(option == "category_edit"){
	
					if(category_cog == 0){
						   category_cog = 1;
						   $(".show_category_cog").append("<span id='category_cog' style='float:right;' class='glyphicon glyphicon-cog cog' data-target='#editCategoryModal' data-toggle='modal'></span>");
						   
						}else{
							category_cog = 0;
							$(".show_category_cog span.cog").remove();
						}
					
					
					
				}
				
			});
			
			$(document).on('click', '.cog', function(event){
				
				rowId = event.target.id;
				
				
				if(rowId == "student_cog"){
					elementId = $(this).parent().parent().attr('id');
					
					original = $(this).parent().parent().find("td:first").html();
					$(".modal-body #edit_name").val(original);
					
				}else if(rowId == "item_cog"){
					elementId = $(this).parent().parent().attr('id');
					
					original = $(this).parent().parent().find('span:nth-child(1)').html();
					$(".modal-body #edit_title").val(original);
					$(".modal-body #edit_cost").val(parseInt($(this).parent().parent().find('span:nth-child(2)').text().trim(),10));
					
				}else if(rowId == "category_cog"){
					elementId = $(this).parent().attr('id');
					original = $(this).parent().find('span.editCategory').html();
					$(".modal-body #category_field").val(original);
					
				}
			});
			
			
			$(document).on('click', '.delete', function(event){
			
				var id = event.target.id;
				
				if(id == "delete_student"){
					//delete student from DB
					
					
					var values = $("#editStudent").serializeArray();
					
					var student_name = values[0].value;
					
					
					$.ajax({
						url: "http://www.classroom-hero.com/deleteStudent",
						type: 'POST',
						data: "name=" + student_name,						
						success : function(result) {
				
								$("#"+elementId).remove();
								$("#editStudentModal").modal('hide');
								toastr.success('Successfully removed '+ student_name); 
						


						},
						error : function() {
							alert("Server encountered an error");
						}
					});
					return false;
			
					
					
				}else if(id == "delete_category"){
					//delete category from DB
					
					categoryCount = categoryCount - 1;
					
					var values = $("#editCategory").serializeArray();
			
					var item_name = values[0].value;
					
					$.ajax({
						url: "http://www.classroom-hero.com/deleteCategory",
						type: 'POST',
						data: "name=" + item_name,
						success : function(result) {
							
						
								
								$("#"+elementId).parent().remove();
								$("#editCategoryModal").modal('hide');
								toastr.success('Successfully removed '+ item_name); 
							


						},
						error : function() {
							alert("Server encountered an error");
						}
					});
					return false;
					
				
					
					
				}else if(id == "delete_item"){
					//delete item from DB
					
					rewardCount = rewardCount - 1;
					var values = $("#editItem").serializeArray();
			
					var item_name = values[0].value;
					var item_cost = values[1].value;
					
					$.ajax({
						url: "http://www.classroom-hero.com/item/delete",
						type: 'POST',
						data: "name=" + item_name + "&cost=" + item_cost,						
						success : function(result) {
						
								$("#"+elementId).parent().remove();
								$("#editItemModal").modal('hide');
								toastr.success('Successfully removed '+ item_name); 
							


						},
						error : function() {
							alert("Server encountered an error");
						}
					});
					return false;
				}
			});
			
			
			
			
			$(document).on('click', '#editJar', function(event){
				
				$(".modal-body #jar_name").val($("#jarPost .jar_name_style").text());
				$(".modal-body #jar_total").val(parseInt($("#jarPost .jar_total_style").text().trim(),10));
				
				return false;
			});
			
			
			
			
			$(document).on('click', '#editJarModal #deleteJar', function(event){
				
				var values = $("#editJarForm").serializeArray();
				
				var jarName = values[0].value;
				
				
				$.ajax({
					url: "http://www.classroom-hero.com/jar/delete",
					type: 'POST',
					data: "name=" + jarName,					
					success : function(result) {
					
							$("#addJarForm #save_jar").attr("disabled", false);
							$('#addJar').unbind('click');
							$("#jarPost span.jarName").text("");
							$("#jarPost span.jarTotal").text("");
							$("#jarPost span.jarProgress").text("No Class Jar!");
							$("#editJarModal").modal('hide');
							$('#editJar').click(function () {return false;});
							
							toastr.success('Successfully removed '+ jarName); 
						


					},
					error : function() {
					
						alert("Server encountered an error");
					}
				});
				return false;
			});
			
		});