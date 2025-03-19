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
                <button onclick="toggleStatus('${dateInput}')">Book</button>
                <button onclick="cancelSlot('${dateInput}')">Cancel</button>
            </td>
            <td>
                <input type="text" id="message-${dateInput}" placeholder="Enter message">
                <button onclick="saveMessage('${dateInput}')">Save</button>
                <button onclick="deleteMessage('${dateInput}')">Delete</button>
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

    function saveMessage(date) {
        const messageInput = document.getElementById(`message-${date}`);
        if (messageInput) {
            messageInput.readOnly = true;
            messageInput.style.border = "none";
            messageInput.style.background = "transparent";
        }
    }

    function deleteMessage(date) {
        const messageInput = document.getElementById(`message-${date}`);
        if (messageInput) {
            messageInput.readOnly = false;
            messageInput.value = "";
            messageInput.style.border = "1px solid black";
            messageInput.style.background = "white";
        }
    }