
	
$(".logout_button").on( "click", function() {
	$.post("/logout-user/", function(){
		window.location.href="index";
	})	
});
