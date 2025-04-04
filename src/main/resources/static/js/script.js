function handleScroll() {
	const navbar = document.getElementById("navbar");
	if (window.scrollY > 0) {
		navbar.classList.add("scrolled");
	} else {
		navbar.classList.remove("scrolled");
	}
}

handleScroll();

window.addEventListener("scroll", handleScroll);

document.addEventListener("DOMContentLoaded", function() {
	const chat = document.getElementById("chat");
	const chatClose = document.getElementById("chatClose");

	chat.addEventListener("click", function() {
		chat.classList.remove("collapsed");
		chat.classList.add("expanded");
	});

	chatClose.addEventListener("click", function(event) {
		event.stopPropagation();
		chat.classList.remove("expanded");
		chat.classList.add("collapsed");
	});

	const navContainer = document.getElementById("nav-container");

	navContainer.addEventListener("click", function(event) {
		if (event.target.classList.contains("nav-item")) {
			const currentActive = navContainer.querySelector(".active");
			if (currentActive) {
				currentActive.classList.remove("active");
			}

			event.target.classList.add("active");
		}
	});
});

const slideDownElement = document.querySelector(".about-subtitle");
const slideUpElement = document.querySelector(".about-description");
const slideInLeftElements = document.querySelectorAll(".blog-subtitle");
const slideInRightElements = document.querySelectorAll(".demo-subtitle");

// Create the Intersection Observer
const observer = new IntersectionObserver(
	(entries, observer) => {
		entries.forEach((entry) => {
			if (entry.isIntersecting && !entry.target.dataset.animated) {
				entry.target.dataset.animated = "true"; // Mark element as animated

				if (entry.target === slideDownElement) {
					entry.target.classList.add("slide-up");
					slideDownElement.addEventListener(
						"transitionend",
						() => {
							slideUpElement.classList.add("fade-in");
						},
						{ once: true }
					);
				}
				if (Array.from(slideInLeftElements).includes(entry.target)) {
					entry.target.classList.add("slide-in-left");
				}
				if (Array.from(slideInRightElements).includes(entry.target)) {
					entry.target.classList.add("slide-in-right");
				}

				observer.unobserve(entry.target); // Stop observing after first animation
			}
		});
	},
	{ threshold: 0.1 }
);

// Observe all the target elements
observer.observe(slideUpElement);
observer.observe(slideDownElement);
slideInLeftElements.forEach((element) => observer.observe(element));
slideInRightElements.forEach((element) => observer.observe(element));

function toggleContainerClass() {
	const navbarContainer = document.querySelector(".navbar-container");

	if (window.innerWidth >= 768) {
		navbarContainer.classList.add("container");
	} else {
		navbarContainer.classList.remove("container");
	}
}

window.addEventListener("resize", toggleContainerClass);
window.addEventListener("DOMContentLoaded", toggleContainerClass);

document.getElementById("ham-btn").addEventListener("click", function() {
	const targetDiv = document.getElementById("sidebar");
	targetDiv.classList.toggle("menu-visible");
});

const navSidebar = document.getElementById("sidebar");

navSidebar.addEventListener("click", function(event) {
	if (event.target.classList.contains("nav-item")) {
		const currentActive = navSidebar.querySelector(".active");
		if (currentActive) {
			currentActive.classList.remove("active");
		}

		event.target.classList.add("active");
	}
});



async function demoRequest() {

	const firstName = document.querySelector("input[placeholder='First Name']").value;
	const lastName = document.querySelector("input[placeholder='Last Name']").value;
	const email = document.querySelector("input[placeholder='Email']").value;
	const phone = document.querySelector("input[placeholder='Mobile Number']").value;

	const demoReqestData = {

		firstName: firstName,
		lastName: lastName,
		email: email,
		phone: phone
	};

	try {
		const response = await fetch("api/auth/index", {
			method: "POST",
			headers: { "Content-Type": "application/json" },
			body: JSON.stringify(demoReqestData)
		});
		const result = await response.text();
		alert(result); // Show success message
		//form.reset(); // Reset form after submission

	} catch(error) {
		console.log("Error submitting demo request:", error);
		//alert("Failed to submit demo request. Please try again.");
	}
}
