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

$(document).on("click", ".edit", function(){
	var user = $(this).parents("tr").find("td:first-child").text();
	var modal = user+"Modal";
	displayModal(modal);	
});

$(document).on("click", ".close", function(){
	var modal = $(this).parent().parent().attr('id');
	console.log(modal);
	hideModal(modal);
});

$(document).on("click", ".delete", function(){
	var id = $(this).parents("tr").find("td:first-child").text();
	$.post("/delete-product/"+id, function(){
		window.location.href="manage-products";
	});
});