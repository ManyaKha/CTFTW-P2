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