/**
 * 
 */

$(document).ready(function() {
	var images = ['images/Banner1.png', 'images/banner2.jpg', 'images/banner3.jpg', 'images/banner4.jpg', 'images/banner5.png'];
	var counter = 0;
	var banner = document.getElementById("banner");
	setInterval(function() {
		counter++;
		if (counter >= images.length) {
			counter = 0;
		}
		banner.innerHTML = "<img src ='" + images[counter] + "' class = 'active'>";
	}, 4000);
})