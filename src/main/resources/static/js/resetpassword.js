
async function resetPassword() {
	const urlParams = new URLSearchParams(window.location.search);
	const token = urlParams.get('token');
	const newPassword = document.getElementById('newPassword').value;
	const confirmPassword = document.getElementById('confirmPassword').value;

	if (newPassword !== confirmPassword) {
		showPopup("Passwords do not match!");
		return;
	}
	try {
		const response = await fetch("/api/auth/resetpassword", {
			method: "POST",
			headers: { "Content-Type": "application/x-www-form-urlencoded" },
			body: `token=${token}&newPassword=${newPassword}&confirmPassword=${confirmPassword}`
		});

		if (response.ok) {
			const responseData = await response.text();
			console.log(responseData);
			showPopup(responseData);
			// Automatically redirect after 2 seconds
			setTimeout(() => {
				window.location.href = "/login"; // Redirect on success
			}, 2000);
		} else {
			const message = await response.text();
			showPopup(message);
			//alert("Error: " + message);
		}
	} catch (error) {
		console.error("Request failed:", error);
		alert("Something went wrong. Please try again.");

	}
}


// Function to show the popup with a custom message
function showPopup(message) {
	const popup = document.getElementById("popupMessage");
	const popupText = document.getElementById("popupText");
	const overlay = document.getElementById("popupOverlay");
	const closeBtn = document.getElementById("closePopupBtn");

	popupText.textContent = message; // Set the popup message text
	popup.style.display = "block"; // Show the popup
	overlay.style.display = "block"; // Show the overlay background

	// Close the popup when the user clicks the close button
	closeBtn.onclick = function() {
		closePopup();
	};

	// Close the popup if the user clicks outside the popup area (on the overlay)
	overlay.onclick = function(event) {
		if (event.target === overlay) {
			closePopup();
		}
	};
}
// Function to close the popup
function closePopup() {
	const popup = document.getElementById("popupMessage");
	const overlay = document.getElementById("popupOverlay");

	if (popup && overlay) {
		popup.style.display = "none";
		overlay.style.display = "none"; // Hide the overlay as well
	}
}
