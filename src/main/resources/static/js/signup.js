function showPopup() {
	document.getElementById('popup').style.display = 'block';
	document.getElementById('overlay').style.display = 'block';
}

function closePopup() {
	document.getElementById('popup').style.display = 'none';
	document.getElementById('overlay').style.display = 'none';
}

async function signup() {
	const accountType = document.querySelector('input[name="type"]:checked').value;

	let data = {
		email: "",
		password: "",
		accountType: accountType
	};

	if (accountType === 'user') {
		data.firstName = document.querySelector('#user-form input[placeholder="First Name"]').value;
		data.lastName = document.querySelector('#user-form input[placeholder="Last Name"]').value;
		data.email = document.querySelector('#user-form input[placeholder="Email address"]').value;
		data.password = document.querySelector('#user-form input[placeholder="Password"]').value;
	} else {
		data.businessName = document.querySelector('#business-form input[placeholder="Business Name"]').value;
		data.email = document.querySelector('#business-form input[placeholder="Email address"]').value;
		data.password = document.querySelector('#business-form input[placeholder="Password"]').value;
	}

	const response = await fetch('/api/auth/signup', {
		method: 'POST',
		headers: {
			'Content-Type': 'application/json'
		},
		body: JSON.stringify(data)
	});
	console.log(data);
	if (response.ok) {
		showPopup();
		// Automatically redirect after 2 seconds
		setTimeout(() => {
			window.location.href = '/login'; // Redirect to login page after signup
		}, 2000);  // 2-second delay
	} else {
		const message = await response.text();
		alert("Error: " + message);
	}
}

