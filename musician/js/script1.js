
const slotTable = document.getElementById("slotTable");

function bookSlot() {
    const dateInput = document.getElementById("dateInput").value;
    if (!dateInput) {
        alert("Please select a date!");
        return;
    }

    const existingRow = document.getElementById(dateInput);
    if (existingRow) {
        alert("Slot already exists!");
        return;
    }

    const newRow = document.createElement("tr");
    newRow.id = dateInput;
    newRow.innerHTML = `
        <td>${dateInput}</td>
        <td class="status-available">Available</td>
        <td>
            <button2 onclick="toggleStatus('${dateInput}')">Book</button2>
            <button2 onclick="cancelSlot('${dateInput}')">Cancel</button2>
        </td>
    `;
    slotTable.appendChild(newRow);
}

function toggleStatus(date) {
    const row = document.getElementById(date);
    const statusCell = row.children[1];

    if (statusCell.textContent === "Available") {
        statusCell.textContent = "Booked";
        statusCell.className = "status-booked";
    } else {
        statusCell.textContent = "Available";
        statusCell.className = "status-available";
    }
}

function cancelSlot(date) {
    const row = document.getElementById(date);
    row.remove();
}
