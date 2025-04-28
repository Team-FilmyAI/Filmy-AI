async function loginUser() {
	const email = document.querySelector('#email').value;  // Changed to #email
	const password = document.querySelector('#password').value;  // Changed to #password

	const loginData = {
		email: email,
		password: password
	};

	try {
		const response = await fetch("api/auth/login", {
			method: "POST",
			headers: { "Content-Type": "application/json" },
			body: JSON.stringify(loginData)
		});

		if (response.ok) {
			const responseData = await response.text(); // If you return a success message
			console.log(responseData); // "Login successful"
			// Show success message in the popup
			showPopup("Login successful!");
			// Automatically redirect after 2 seconds
			setTimeout(() => {
				window.location.href = "/profile"; // Redirect on success
			}, 2000);  // 2-second delay
			
		} else {
			const message = await response.text();
			showPopup(message);
			//alert("Error: " + message);
		}
	} catch (error) {
		showPopup(error.message);
		//alert("Error: " + error.message); // Fixed the error handling
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