// Profile type selection
window.addEventListener('load', () => {
    const publicProfile = document.querySelector('.pub');
    const privateProfile = document.querySelector('.pvt');

    publicProfile.addEventListener('click', () => {
        privateProfile.classList.remove('active-profile');
        publicProfile.classList.add('active-profile');
    });

    privateProfile.addEventListener('click', () => {
        publicProfile.classList.remove('active-profile');
        privateProfile.classList.add('active-profile');
    });
});

// Recommendation section (read more)
document.addEventListener("DOMContentLoaded", function() {
    const recommendationTexts = document.querySelectorAll('.recommendation-text');
    const wordLimit = 50;

    recommendationTexts.forEach(function(recommendationText) {
        const paragraph = recommendationText.querySelector('p');
        const seeMoreLink = recommendationText.querySelector('.see-more');
        const educationDetails = recommendationText.closest('.education-details');

        const universityName = educationDetails.querySelector('.university-name').innerText;
        const workedAt = educationDetails.querySelector('.worked-at').innerText;
        const recommendationDate = educationDetails.querySelector('.recommendation-date').innerText;
        const profileImgSrc = educationDetails.previousElementSibling.querySelector('img').src;

        const words = paragraph.innerText.split(' ');

        if (words.length > wordLimit) {
            const visibleText = words.slice(0, wordLimit).join(' ') + '...';
            paragraph.innerText = visibleText;
            seeMoreLink.style.display = 'inline';
            seeMoreLink.setAttribute('data-full-text', words.join(' '));
        }

        seeMoreLink.addEventListener('click', function(event) {
            event.preventDefault();
            const fullText = this.getAttribute('data-full-text');

            document.querySelector('.popup-education-details .university-name').innerText = universityName;
            document.querySelector('.popup-education-details .worked-at').innerText = workedAt;
            document.querySelector('.popup-education-details .recommendation-date').innerText = recommendationDate;
            document.querySelector('#moreContent').innerText = fullText;
            document.querySelector('.popup-profile-div img').src = profileImgSrc;

            document.querySelector('.popup-card').style.display = 'block';
            document.querySelector('.overlay').style.display = 'block';
        });
    });

    document.getElementById('closeBtn').addEventListener('click', function() {
        document.querySelector('.popup-card').style.display = 'none';
        document.querySelector('.overlay').style.display = 'none';
    });
});

// Edit Button to show the relevant form
document.addEventListener("DOMContentLoaded", function() {
    document.querySelectorAll('.edit-btn').forEach(function(editButton) {
        editButton.addEventListener('click', function() {
            const section = this.closest('section');
            const secondCard = section.querySelector('.hidden');

            if (secondCard) {
                // Show the popup and overlay
                const popupContent = document.querySelector('.popup-content');
                popupContent.innerHTML = secondCard.innerHTML;

                document.querySelector('.edit-popup-card').style.display = 'block';
                document.querySelector('.edit-overlay').style.display = 'block';
            }
        });
    });

    document.addEventListener('click', function(event) {
        if (event.target.matches('.btn-close')) {
            document.querySelector('.edit-popup-card').style.display = 'none';
            document.querySelector('.edit-overlay').style.display = 'none';
        }
    });
});

// Enable edit view
document.querySelectorAll('.enable-edit-btn').forEach(editButton => {
    editButton.addEventListener('click', function () {
        const accordionItem = this.closest('.accordion-item');
        
        const editIcons = accordionItem.querySelectorAll('.fa-pen-to-square, .fa-eye');
        const cancelButton = accordionItem.querySelector('.disable-edit-btn');

        editIcons.forEach(icon => {
            icon.style.display = 'block';
        });

        this.style.display = 'none';
        cancelButton.style.display = 'inline-block';
    });
});

// Disable edit view
document.querySelectorAll('.disable-edit-btn').forEach(cancelButton => {
    cancelButton.addEventListener('click', function () {
        const accordionItem = this.closest('.accordion-item');
        
        const editIcons = accordionItem.querySelectorAll('.fa-pen-to-square, .fa-eye');
        const editButton = accordionItem.querySelector('.enable-edit-btn');
        
        editIcons.forEach(icon => {
            icon.style.display = 'none';
        });

        this.style.display = 'none';
        editButton.style.display = 'inline-block';
    });
});

// Hide Recommendation
document.querySelectorAll('.visible-btn').forEach(visibleButton => {
    visibleButton.addEventListener('click', function () {
        const experienceTitle = this.closest('.experience-title');
        
        const notVisibleButton = experienceTitle.querySelector('.not-visible-btn');
        
        notVisibleButton.style.display = 'inline-block';
        this.style.display = 'none';
    });
});

// Unhide Recommendation
document.querySelectorAll('.not-visible-btn').forEach(notVisibleButton => {
    notVisibleButton.addEventListener('click', function () {
        const experienceTitle = this.closest('.experience-title');
        
        const visibleButton = experienceTitle.querySelector('.visible-btn');
        
        visibleButton.style.display = 'inline-block';
        this.style.display = 'none';
    });
});


// Navbar
function handleNavClick(event) {
    event.preventDefault();
    
    const navItems = document.querySelectorAll('.custom-nav-item');
    navItems.forEach(item => {
        item.classList.remove('active');
        const icon = item.querySelector('i.icon');
        const text = item.querySelector('span');
        if (icon && text) {
            icon.style.color = '';
            text.style.color = '';
        }
    });
    
    const clickedItem = event.currentTarget;
    clickedItem.classList.add('active');
    
    const activeIcon = clickedItem.querySelector('i.icon');
    const activeText = clickedItem.querySelector('span');
    if (activeIcon && activeText) {
        activeIcon.style.color = 'white';
        activeText.style.color = 'white';
    }
}

document.querySelectorAll('.custom-nav-item').forEach(item => {
    item.addEventListener('click', handleNavClick);
});

const homeLink = document.querySelector('.custom-nav-item');
if (homeLink) {
    homeLink.classList.add('active');
    const homeIcon = homeLink.querySelector('i.icon');
    const homeText = homeLink.querySelector('span');
    if (homeIcon && homeText) {
        homeIcon.style.color = 'white';
        homeText.style.color = 'white'
    }
}