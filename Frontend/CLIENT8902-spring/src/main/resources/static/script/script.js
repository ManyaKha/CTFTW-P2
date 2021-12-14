$( "#advanced_btn" ).click(function() {     
   $('#advanced_search').toggle();
});

/**
 * Displays the modal that is given as the argument.
 * @param {string} modalId is the id of the modal.
 */
var displayModal = (modalId) => {
  var modal = document.getElementById(modalId);
  modal.style.display = "block";
};

/**
 * Hides the modal that is given as the argument.
 * @param {string} modalId is the id of the modal.
 */
var hideModal = (modalId) => {
  var modal = document.getElementById(modalId);
  modal.style.display = "none";
};

$(".edit").on("click", function(){
	var id = $(this).parents("div").find("p:first").text();
	var modal = id+"Modal";
	displayModal(modal);	
});

$(".delete").on("click", function(){
	var id = $(this).parents("div").find("p:first").text();
	$.post("/delete-product/"+id, function(){
		window.location.href="myproducts";
	});
});



$(".close").on("click", function(){
	var modal = $(this).parent().parent().attr('id');
	hideModal(modal);
});