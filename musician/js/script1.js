const calendarGrid = document.getElementById("calendar-grid");
const calendarTitle = document.getElementById("calendar-title");
const prevMonthButton = document.getElementById("prev-month");
const nextMonthButton = document.getElementById("next-month");

let currentDate = new Date();

function renderCalendar(date) {
  const year = date.getFullYear();
  const month = date.getMonth();

  
  calendarTitle.textContent = `${date.toLocaleString("default", { month: "long" })} ${year}`;

  
  calendarGrid.innerHTML = `
    <div class="day-name">Mo</div>
    <div class="day-name">Tu</div>
    <div class="day-name">We</div>
    <div class="day-name">Th</div>
    <div class="day-name">Fr</div>
    <div class="day-name">Sa</div>
    <div class="day-name">Su</div>
  `;

  
  const firstDayOfMonth = new Date(year, month, 1);
  const lastDayOfMonth = new Date(year, month + 1, 0);
  const firstWeekday = (firstDayOfMonth.getDay() + 6) % 7; 
  const lastDate = lastDayOfMonth.getDate();

  
  const prevMonthLastDate = new Date(year, month, 0).getDate();
  for (let i = firstWeekday; i > 0; i--) {
    const day = document.createElement("div");
    day.className = "day faded";
    day.textContent = prevMonthLastDate - i + 1;
    calendarGrid.appendChild(day);
  }

  
  for (let dayNum = 1; dayNum <= lastDate; dayNum++) {
    const day = document.createElement("div");
    day.className = "day";
    day.textContent = dayNum;

    
    const today = new Date();
    if (
      dayNum === today.getDate() &&
      month === today.getMonth() &&
      year === today.getFullYear()
    ) {
      day.classList.add("today");
    }

    calendarGrid.appendChild(day);
  }

  
  const totalCells = firstWeekday + lastDate;
  const nextMonthDays = 7 - (totalCells % 7);
  if (nextMonthDays < 7) {
    for (let i = 1; i <= nextMonthDays; i++) {
      const day = document.createElement("div");
      day.className = "day faded";
      day.textContent = i;
      calendarGrid.appendChild(day);
    }
  }
}


prevMonthButton.addEventListener("click", () => {
  currentDate.setMonth(currentDate.getMonth() - 1);
  renderCalendar(currentDate);
});

nextMonthButton.addEventListener("click", () => {
  currentDate.setMonth(currentDate.getMonth() + 1);
  renderCalendar(currentDate);
});


function startAutoUpdate() {
  let lastCheckedDate = new Date();
  setInterval(() => {
    const now = new Date();

    
    if (
      now.getDate() !== lastCheckedDate.getDate() ||
      now.getMonth() !== lastCheckedDate.getMonth() ||
      now.getFullYear() !== lastCheckedDate.getFullYear()
    ) {
      currentDate = new Date(); 
      renderCalendar(currentDate); 
      lastCheckedDate = now; 
    }
  }, 1000); 
}

renderCalendar(currentDate); 
startAutoUpdate(); 


