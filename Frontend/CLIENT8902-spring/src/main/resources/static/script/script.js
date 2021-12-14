$( "#advanced_btn" ).click(function() {     
   $('#advanced_search').toggle();
});

$(".edit").on("click", function(){
	var id = $(this).parents("div").find("p:first").text();
	$.post("/edit-product/"+id, function(){
		window.location.href="editproduct";
	});
});

$(".delete").on("click", function(){
	var id = $(this).parents("div").find("p:first").text();
	$.post("/delete-product/"+id, function(){
		window.location.href="myproducts";
	});
});
