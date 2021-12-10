$(document).on("click", ".delete", function(){
	var email = $(this).parents("tr").find("td:first-child").text();
	$.post("/delete-user/"+email, function(){
		window.location.href="manage-users";
	});
});
