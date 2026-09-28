const API = "http://localhost:8080/api";


/* =========================
   NAVIGATION
========================= */

function showSection(sectionId) {

    document.querySelectorAll(".section").forEach(section => {
        section.classList.remove("active");
    });

    document.getElementById(sectionId).classList.add("active");


    if (sectionId === "home") {
        loadDashboard();
    }

    if (sectionId === "items") {
        loadItems();
    }
}


/* =========================
   DASHBOARD
========================= */

async function loadDashboard() {

    try {

        const drives =
            await fetch(`${API}/drives`)
            .then(response => response.json());


        const donors =
            await fetch(`${API}/donors`)
            .then(response => response.json());


        const recipients =
            await fetch(`${API}/recipients`)
            .then(response => response.json());


        const items =
            await fetch(`${API}/items`)
            .then(response => response.json());


        document.getElementById("driveCount").textContent =
            drives.length;


        document.getElementById("donorCount").textContent =
            donors.length;


        document.getElementById("recipientCount").textContent =
            recipients.length;


        document.getElementById("itemCount").textContent =
            items.length;


    } catch (error) {

        console.error(
            "Dashboard Error:",
            error
        );

    }
}


/* =========================
   CREATE DRIVE
========================= */

document.getElementById("driveForm")
.addEventListener("submit", async function(event) {

    event.preventDefault();


    const data = {

        name:
            document.getElementById("driveName").value,

        startDate:
            document.getElementById("startDate").value,

        endDate:
            document.getElementById("endDate").value
    };


    try {

        const response =
            await fetch(`${API}/drives`, {

                method: "POST",

                headers: {
                    "Content-Type": "application/json"
                },

                body: JSON.stringify(data)

            });


        const result =
            await response.json();


        if (!response.ok) {

            throw new Error(
                result.error ||
                "Failed to create drive"
            );
        }


        document.getElementById(
            "driveMessage"
        ).textContent =
            "Donation drive created successfully!";


        document.getElementById(
            "driveForm"
        ).reset();


        loadDashboard();


    } catch (error) {

        document.getElementById(
            "driveMessage"
        ).textContent =
            error.message;
    }

});


/* =========================
   ADD DONOR
========================= */

document.getElementById("donorForm")
.addEventListener("submit", async function(event) {

    event.preventDefault();


    const data = {

        name:
            document.getElementById("donorName").value,

        email:
            document.getElementById("donorEmail").value,

        phone:
            document.getElementById("donorPhone").value
    };


    try {

        const response =
            await fetch(`${API}/donors`, {

                method: "POST",

                headers: {
                    "Content-Type": "application/json"
                },

                body: JSON.stringify(data)

            });


        const result =
            await response.json();


        if (!response.ok) {

            throw new Error(
                result.error ||
                "Failed to add donor"
            );
        }


        document.getElementById(
            "donorMessage"
        ).textContent =
            "Donor added successfully!";


        document.getElementById(
            "donorForm"
        ).reset();


        loadDashboard();


    } catch (error) {

        document.getElementById(
            "donorMessage"
        ).textContent =
            error.message;
    }

});


/* =========================
   ADD RECIPIENT
========================= */

document.getElementById("recipientForm")
.addEventListener("submit", async function(event) {

    event.preventDefault();


    const data = {

        organizationName:
            document.getElementById(
                "organizationName"
            ).value,

        contactPerson:
            document.getElementById(
                "contactPerson"
            ).value,

        phone:
            document.getElementById(
                "recipientPhone"
            ).value,

        address:
            document.getElementById(
                "address"
            ).value
    };


    try {

        const response =
            await fetch(`${API}/recipients`, {

                method: "POST",

                headers: {
                    "Content-Type": "application/json"
                },

                body: JSON.stringify(data)

            });


        const result =
            await response.json();


        if (!response.ok) {

            throw new Error(
                result.error ||
                "Failed to add recipient"
            );
        }


        document.getElementById(
            "recipientMessage"
        ).textContent =
            "Recipient added successfully!";


        document.getElementById(
            "recipientForm"
        ).reset();


        loadDashboard();


    } catch (error) {

        document.getElementById(
            "recipientMessage"
        ).textContent =
            error.message;
    }

});


/* =========================
   ADD DONATION ITEM
========================= */

document.getElementById("itemForm")
.addEventListener("submit", async function(event) {

    event.preventDefault();


    const category =
        document.getElementById(
            "category"
        ).value;


    const itemCondition =
        document.getElementById(
            "condition"
        ).value;


    const quantity =
        document.getElementById(
            "quantity"
        ).value;


    const driveId =
        document.getElementById(
            "driveId"
        ).value;


    const donorId =
        document.getElementById(
            "donorId"
        ).value;


    const data = {

        category: category,

        itemCondition: itemCondition,

        quantity: Number(quantity)
    };


    try {

        const response =
            await fetch(
                `${API}/items?driveId=${driveId}&donorId=${donorId}`,
                {

                    method: "POST",

                    headers: {
                        "Content-Type": "application/json"
                    },

                    body: JSON.stringify(data)

                }
            );


        const result =
            await response.json();


        if (!response.ok) {

            throw new Error(
                result.error ||
                "Failed to add donation"
            );
        }


        document.getElementById(
            "itemMessage"
        ).textContent =
            "Donation added successfully!";


        document.getElementById(
            "itemForm"
        ).reset();


        loadDashboard();


    } catch (error) {

        document.getElementById(
            "itemMessage"
        ).textContent =
            error.message;
    }

});


/* =========================
   LOAD ITEMS
========================= */

async function loadItems() {

    try {

        const response =
            await fetch(`${API}/items`);


        const items =
            await response.json();


        const container =
            document.getElementById(
                "itemsList"
            );


        container.innerHTML = "";


        if (items.length === 0) {

            container.innerHTML =
                "<p>No donated items found.</p>";

            return;
        }


        items.forEach(item => {

            const card =
                document.createElement("div");


            card.className =
                "item-card";


            card.innerHTML = `

                <h3>
                    ${item.category}
                </h3>

                <p>
                    <strong>Item ID:</strong>
                    ${item.id}
                </p>

                <p>
                    <strong>Condition:</strong>
                    ${item.itemCondition}
                </p>

                <p>
                    <strong>Quantity:</strong>
                    ${item.quantity}
                </p>

                <p>
                    <strong>Distributed:</strong>
                    ${item.distributed
                        ? "Yes"
                        : "No"}
                </p>

                <p>
                    <strong>Donor:</strong>
                    ${item.donor?.name || "N/A"}
                </p>

                <p>
                    <strong>Drive:</strong>
                    ${item.drive?.name || "N/A"}
                </p>

                <p>
                    <strong>Recipient:</strong>
                    ${
                        item.recipient
                        ?.organizationName
                        || "Not Distributed"
                    }
                </p>

            `;


            container.appendChild(card);

        });


    } catch (error) {

        console.error(
            "Items Loading Error:",
            error
        );


        document.getElementById(
            "itemsList"
        ).innerHTML =
            "<p>Unable to load items.</p>";
    }

}


/* =========================
   PAGE LOAD
========================= */

window.onload = function() {

    loadDashboard();

};