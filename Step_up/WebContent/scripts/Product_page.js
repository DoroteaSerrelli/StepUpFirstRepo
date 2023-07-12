/**
 * 
 */
// @charset "UTF-8"


$(document).ready(function() {
  $("#show-more-btn").click(function() {
    $("#detailed-description").slideToggle();
     if ($(this).text().trim() === "Mostra di più") {
      $(this).text("Mostra meno");
    } else {
      $(this).text("Mostra di più");
    }
  });
});