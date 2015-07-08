<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Home</title>
<!-- Bootstrap core CSS -->
<link rel="shortcut icon" href="${pageContext.request.contextPath}/assets/images/appicon.png" type="image/icon">
<link
	href="${pageContext.request.contextPath}/assets/bootstrap/dist/css/bootstrap.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/assets/bootstrap/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/assets/libraries/jquery-ui/development-bundle/themes/base/jquery.ui.all.css"
	rel="stylesheet">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/assets/libraries/jPushMenu/css/jPushMenu.css" />

<!-- Custom CSS -->
<link
	href="${pageContext.request.contextPath}/assets/customCSS/synapp-style.css"
	rel="stylesheet" />
	
	<link href="${pageContext.request.contextPath}/assets/toastr/toastr.css" rel="stylesheet"/>
	
<script type="text/javascript"
	src="${pageContext.request.contextPath}/assets/libraries/jPushMenu/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/assets/libraries/jPushMenu/js/jPushMenu.js"></script>


<!-- Core JS -->
<script type="text/javascript"
	src="${pageContext.request.contextPath}/assets/plugins/Datatables-master/media/js/jquery.js"></script>
<script
	src="${pageContext.request.contextPath}/assets/plugins/rwdtables/_js/lib/jquery-ui.widget.min.js"></script>
<script
	src="${pageContext.request.contextPath}/assets/plugins/rwdtables/_js/rwd-table.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/assets/libraries/jquery-ui/js/rowclick.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/assets/plugins/Datatables-master/media/js/jquery.dataTables.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/assets/plugins/Datatables-master/BS3/assets/js/datatables.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/assets/bootstrap/dist/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/assets/bootstrap/dist/js/bootstrap.js"></script>

<script type="text/javascript"
	src="${pageContext.request.contextPath}/assets/plugins/jquery-validation/dist/jquery.validate.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/assets/plugins/jquery-validation/dist/additional-methods.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/assets/bootstrap/dist/js/bootstrap-tabs.js"></script>
	
	<script src="${pageContext.request.contextPath}/assets/toastr/toastr.js"></script>
	<script src="${pageContext.request.contextPath}/assets/customJS/homejs.js"></script>
<style>
.center {
	margin: 15px auto !important;
	float: none !important;
}

.alignText {
	text-align: center;
}

.panel_width {
	max-width: 500px;
}



.jar_total_style {
	float: right;
	font-size: 30px;
}

.jar_name_style {
	float: left;
	font-size: 25px;
	margin-left: 6px;
}

.jar_progress_style {
	float: center;
	font-size: 25px;
	margin: 0 auto;
}

.item_cost_style {
	float: right;
	font-size: 30px;
}

.item_name_style {
	float: left;
	font-size: 25px;
	margin-left: 6px;
}

#allstudents {
	max-width: 700px;
}
.toast-top-center {
    bottom: 15px;
    left: 50%;
    margin-left: -150px;
}
.edit_selection{
	cursor:pointer;
}
.create{
	cursor:pointer;
}
.cog{
	cursor: pointer;
	font-size:20px;
	padding-left: 10px;
}

#create_item input.error {
	border: 1px solid #B80000;
}

#create_item input.valid {
	border: 1px solid #0066FF;
}

#edit_item input.error {
	border: 1px solid #B80000;
}

#edit_item input.valid {
	border: 1px solid #0066FF;
}
#newFname input.error {
	border: 1px solid #B80000;
}

#newLname input.valid {
	border: 1px solid #0066FF;
}

#jar_name input.error {
	border: 1px solid #B80000;
}

#jar_total input.valid {
	border: 1px solid #0066FF;
}

.jumboHead{
	width:300px;
}
</style>

</head>

<body>

	<div class="navbar navbar-default navbar-fixed-top headroom">
		<div class="container">
			<div class="navbar-header">
				<!-- Button for smallest screens -->
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".navbar-collapse">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a id="company" class="navbar-brand" href="teacherhome.html"
					style="color: #1E90FF;">Classroom Hero</a>
			</div>
			<div class="navbar-collapse collapse">
				<ul class="nav navbar-nav pull-right">
					<li><a href="#"><i class="icon-cog"></i>&nbsp;${name}</a></li>
					<li><a href="#"><span style="font-size: 18px;">Class Stats</span><br><span style="font-size: 8.5px;  color: black;">Feature Coming Soon!</span></a></li>
					<li><a href="#"><span style="font-size: 18px;">League</span><br><span style="font-size: 8.5px;  color: black;">Feature Coming Soon!</span></a></li>
					<li><a href="logout.html"><i class="glyphicon glyphicon-chevron-right"></i>&nbsp; Logout</a></li>
				</ul>
			</div>
			<!--/.nav-collapse -->
		</div>
	</div>


	<div class="collapse navbar-collapse container">
		<ul class="nav nav-pills nav-justified">
			<li><a href=""><i class="icon-bar-chart"></i>&nbsp;&nbsp;
					Students</a></li>
			<li><a href=""><i class="icon-list-alt"></i>&nbsp;&nbsp;Categories</a></li>
			<li><a href=""><i class="icon-cog"></i>&nbsp;&nbsp; Items</a></li>
			<li><a href=""><i class="icon-file-text"></i>&nbsp;&nbsp;
					Stats</a></li>

		</ul>
	</div>


	<!-- STUDENTS -->
	<div class="jumbotron top-space">
		<div class="container">

			<div class="center jumboHead alignText">
				<p style="font-size:28px;" class="post-title">Heroes</p>
				<a id="student_edit" class="edit_selection alignText">Edit</a>
			</div>
			<div class="table-wrapper">


				<table cellspacing="0" id="allstudents" class="table center">
					<thead>
						<tr style="color: black;">
							<th class="essential">Name</th>
							<th class="essential">Current Coins</th>
							<th class="essential">Total Coins Earned</th>
						</tr>
					</thead>
					<tbody style="color: black;">
						<c:forEach var='item' items='${list}' varStatus="theCount" >
						<c:set value="${theCount.index}" var="idStu"></c:set>  
							<TR id='rowId${theCount.index}'>
								<TD><c:out value='${item.fname}${item.value}' /> <c:out value='${item.lname}${item.value}' /></TD>

								<TD style="padding-left:23px;"><c:out value='${item.current}${item.value}' /></TD>
								
								<TD><c:out value='${item.total}${item.value}' /></TD>
								
							</TR>
						</c:forEach>
						
					</tbody>
				</table>
					<script type="text/javascript">
							lastStu.idStu = "${idStu}";
						</script>
				<p style="font-size:25px; text-align:center;">Add a Hero</p>
					<form id="newStudentForm" method="POST" class="form-inline" role="form" style="text-align:center;">
						
							<input id="newFname" style="height:40px; width:200px;" type="text" placeholder="First Name" name="fname" class="form-control input">
							<input id="newLname" style="height:40px; width:200px;" type="text" placeholder="Last Name" name="lname" class="form-control input">
							<button type="submit" class="btn btn-primary insert">Add</button>
							<p style="visibility:hidden;" id="stuErr">Please enter a student's name</p>
						
					</form>
			</div>

		
				
					
				</div>
			</div>

	<!-- REWARDS -->
	<div class="jumbotron top-space">
		<div class="container">


	<div class="center jumboHead alignText">
			<p style="font-size:28px;" class="post-title">Rewards and Privileges</p>
			<a id="list" class="center create" style="text-align: center; max-width: 400px; padding-right:40px;" data-target="#myModal" data-toggle="modal">Add Reward</a>
			<a id="item_edit" class="edit_selection center">Edit</a>

		</div>
		
	<div class="post-description" id="rewardPost">
				
				<c:forEach var='item' items='${itemList}' varStatus="theCount">
				<c:set value="${theCount.index}" var="id"></c:set>   
					<DIV class="panel panel-primary center panel_width"style="text-align: center;">
				
						<DIV class="panel-heading show_reward_cog" style="height: 50px;" id='itemId${theCount.index}'>
							
								 <span class="item_name_style"><c:out value='${item.name}${item.value}' /></span>
								 <span class="item_cost_style"> <c:out value='${item.cost}${item.value}' /></span>
							
						</DIV>


					</DIV>
				</c:forEach>
				<script type="text/javascript">
					lastId.id = "${id}";
				</script>

			</div>
		</div>
	</div>

	<!-- CATAGORIES -->
	<div class="jumbotron top-space">
		<div class="container">
		<div class="center jumboHead alignText">
			<p style="font-size:28px;">Point Categories</p>
			
			<a id="list" class="create" style="text-align: center; max-width: 400px; padding-right:40px;" data-target="#addCategoryModal" data-toggle="modal">Add Category</a>
			<a id="category_edit" class="edit_selection">Edit</a>
		</div>

		<div class="post-description" id="categoryPost">
			
				
				<c:forEach var='item' items='${categoriesList}' varStatus="theCount">
					<c:set value="${theCount.index}" var="idCat"></c:set>
					<DIV class="panel panel-success center panel_width editCategory"
						style="text-align: center;">

						<DIV id="panelNo${theCount.index}" class="panel-heading show_category_cog" style="height: 50px;">

							<span class="item_name_style editCategory"><c:out value='${item}' /></span>


						</DIV>

					</DIV>
				</c:forEach>
				<script type="text/javascript">
				lastCategory.idCat = "${idCat}";
				</script>
			</div>
		</div>
	</div>
	
	<!-- CLASS JAR -->
	<div class="jumbotron top-space">
		<div class="container">
		<div class="center jumboHead alignText">
			<p style="font-size:28px;">Class Jar</p>			
			<a id="addJar" class="create" style="text-align: center; max-width: 400px; padding-right:40px;" data-target="#addJarModal" data-toggle="modal">Add Jar</a>
			<a id="editJar" data-target="#editJarModal" data-toggle="modal" class="edit_selection">Edit</a>
		</div>

		<div class="post-description" id="jarPost">			
			
					<DIV class="panel panel-warning center panel_width editCategory"
						style="text-align: center;">

						<DIV class="panel-heading" style="height: 50px;">

							<span class="jar_name_style jarName"><c:out value='${jarName}' /></span>
							<span class="jar_progress_style jarProgress"><c:out value='${jarProgress}' /></span>
							<span class="jar_total_style jarTotal"><c:out value='${jarTotal}' /></span>


						</DIV>

					</DIV>
			</div>
		</div>
	</div>


	<!-- Add Item Modal -->
	<form role="form" id="create_item" method="post"
		name="create_item_form">

		<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true"
			style="text-align: center;">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel">Create New Item</h4>
					</div>
					<div class="modal-body">
						<input type="text" name="item_title" id="item_title"
							class="center input form-control fields" placeholder="Name"
							style="max-width: 250px;"> <input type="text"
							name="item_cost" id="item_cost"
							class="center form-control fields" placeholder="Cost"
							style="max-width: 75px;">
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
						<button id="save_item" type="submit" class="btn btn-primary">Save Item</button>
					</div>
					<p style="visibility:hidden; color:red;" id="errP">Cost must be a whole number!</p>
				</div>
			</div>
		</div>
	</form>

	<!-- Add Category Modal -->
	<form role="form" id="addCategory" method="post"
		name="add_category_form">

		<div class="modal fade" id="addCategoryModal" tabindex="-1"
			role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"
			style="text-align: center;">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel">Add a Category</h4>
					</div>
					<div class="modal-body">
						<input type="text" name="newCategoryName" id="category_field"
							class="center input form-control fields" placeholder="Name"
							style="max-width: 250px;">
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
						<button id="save_item" type="submit" class="btn btn-primary">Add Category</button>
					</div>
					<p style="visibility:hidden; color:red;" id="errPCat">You can only have 10 Categories!</p>
				</div>
			
			</div>
		</div>
		
		
	
	
		

	
	</form>
	
	
		<!-- Edit Item Model -->
	<form role="form" id="editItem" method="post" name="edit_item_form">

		<div class="modal fade" id="editItemModal" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true"
			style="text-align: center;">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel">Edit Item</h4>
					</div>
					<div class="modal-body">
						<input type="text" name="item_title" id="edit_title" class="center input form-control fields" placeholder="Name" style="max-width: 250px;"> 
						<input type="text" name="item_cost" id="edit_cost" class="center form-control fields" placeholder="Cost" style="max-width: 75px;">
					</div>
					<div class="modal-footer">
					<button id="delete_item" type="button" class="btn btn-warning delete" style="float:left;">Delete Reward</button>
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
						<button id="save_item_changes" type="submit" class="btn btn-primary">Save Changes</button>
					</div>
					<p style="visibility:hidden; color:red;" id="errEditItemNum">Total must be a whole number!</p>
				</div>
			</div>
		</div>

	</form>

	<!-- Edit Category Modal -->
	<form role="form" id="editCategory" method="post"
		name="add_category_form">

		<div class="modal fade" id="editCategoryModal" tabindex="-1"
			role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"
			style="text-align: center;">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel">Edit Category</h4>
					</div>
					<div class="modal-body">
						<input type="text" name="newCategoryName" id="category_field"
							class="center input form-control fields" placeholder="Name"
							style="max-width: 250px;">
					</div>
					<div class="modal-footer">
					<button id="delete_category" type="button" class="btn btn-warning delete" style="float:left;">Delete Category</button>
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
						<button id="save_item" type="submit" class="btn btn-primary">Save Category</button>
					</div>
				</div>

			</div>
		</div>
	
	</form>

			<!-- Edit Student Model -->
	<form role="form" id="editStudent" method="post" name="edit_item_form">

		<div class="modal fade" id="editStudentModal" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true"
			style="text-align: center;">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel">Edit Student</h4>
					</div>
					<div class="modal-body">
						<input type="text" name="edit_name" id="edit_name" class="center input form-control fields" placeholder="Name" style="max-width: 250px;"> 
					</div>
					<div class="modal-footer">
						<button id="delete_student" type="button" class="btn btn-warning delete" style="float:left;">Delete Student</button>
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
						<button id="save_student" type="submit" class="btn btn-primary save">Save Changes</button>
					</div>
				<p style="visibility:hidden; color:red;" id="errPStu">Please enter a first and last name!</p>
				</div>
			</div>
		</div>

	</form>
	
	<!-- Edit Jar Model -->
	<form role="form" id="editJarForm" method="post" name="edit_item_form">

		<div class="modal fade" id="editJarModal" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true"
			style="text-align: center;">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel">Edit Jar</h4>
					</div>
					<div class="modal-body">
						<input type="text" name="editJarName" id="jar_name" class="center input form-control fields" placeholder="Name" style="max-width: 250px;">
						<input type="text" name="editJarTotal" id="jar_total" class="center input form-control fields" placeholder="Total" style="max-width: 250px;">
					</div>
					<div class="modal-footer">
						<button id="deleteJar" type="button" class="btn btn-warning delete" style="float:left;">Delete Jar</button>
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
						<button id="save_jar" type="submit" class="btn btn-primary save">Save Changes</button>
					</div>
				<p style="visibility:hidden; color:red;" id="errPJar">Please fill all fields</p>
				<p style="visibility:hidden; color:red;" id="errEditJarNum">Total must be a whole number!</p>
				</div>
			</div>
		</div>

	</form>
	
		<!-- Add Jar Modal -->
	<form role="form" id="addJarForm" method="post"
		name="add_jar_form">

		<div class="modal fade" id="addJarModal" tabindex="-1"
			role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"
			style="text-align: center;">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel">Add Jar</h4>
					</div>
					<div class="modal-body">
						<input type="text" name="newJarName" id="jar_name" class="center input form-control fields" placeholder="Name" style="max-width: 250px;">
						<input type="text" name="newJarTotal" id="jar_total" class="center input form-control fields" placeholder="Total" style="max-width: 250px;">
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
						<button id="save_jar" type="submit" class="btn btn-primary">Create Jar</button>
					</div>
				<p style="visibility:hidden; color:red;" id="errAddJarNum">Total must be a whole number!</p>
				</div>
			
			</div>
		</div>
	</form>
	


	
	
	<footer id="footer" class="top-space">

		<div class="footer1">
			<div class="container">
				<div class="row">

					<div class="col-md-3 widget">
						<h3 class="widget-title">Contact</h3>
						<div class="widget-body">
							<p>
								707-535-9089<br> <a href="mailto:#">eric.horvat@gmail.com</a><br>
							</p>
						</div>
					</div>

					

				</div>
				<!-- /row of widgets -->
			</div>
		</div>

		<div class="footer2">
			<div class="container">
				<div class="row">

				

					<div class="col-md-6 widget">
						<div class="widget-body">
							<p class="text-right">
								Copyright &copy; 2014, www.classroom-hero.com
							</p>
						</div>
					</div>

				</div>
				<!-- /row of widgets -->
			</div>
		</div>
		

	</footer>
	
	
	
	
</body>
</html>