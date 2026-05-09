const BASE_URL = "";

function showSection(sectionId) {
    document.querySelectorAll(".section").forEach(section => {
        section.classList.add("hidden");
    });

    document.getElementById(sectionId).classList.remove("hidden");
}

// ================= STUDENTS =================

function addStudent() {
    const student = {
        id: Number(document.getElementById("studentId").value),
        name: document.getElementById("studentName").value,
        email: document.getElementById("studentEmail").value,
        course: document.getElementById("studentCourse").value,
        branch: document.getElementById("studentBranch").value,
        cgpa: Number(document.getElementById("studentCgpa").value),
        skills: document.getElementById("studentSkills").value
    };

    fetch(BASE_URL + "/students", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(student)
    })
        .then(response => response.json())
        .then(data => {
            alert("Student added successfully");
            loadStudents();
        })
        .catch(error => alert("Error adding student"));
}

function loadStudents() {
    fetch(BASE_URL + "/students")
        .then(response => response.json())
        .then(data => {
            const container = document.getElementById("studentsData");
            container.innerHTML = "";

            data.forEach(student => {
                container.innerHTML += `
                <div class="card">
                    <h3>${student.name}</h3>
                    <p><b>ID:</b> ${student.id}</p>
                    <p><b>Email:</b> ${student.email}</p>
                    <p><b>Course:</b> ${student.course}</p>
                    <p><b>Branch:</b> ${student.branch}</p>
                    <p><b>CGPA:</b> ${student.cgpa}</p>
                    <p><b>Skills:</b> ${student.skills}</p>
                </div>
            `;
            });
        });
}

// ================= COMPANIES =================

function addCompany() {
    const company = {
        id: Number(document.getElementById("companyId").value),
        name: document.getElementById("companyName").value,
        email: document.getElementById("companyEmail").value,
        location: document.getElementById("companyLocation").value,
        industry: document.getElementById("companyIndustry").value
    };

    fetch(BASE_URL + "/companies", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(company)
    })
        .then(response => response.json())
        .then(data => {
            alert("Company added successfully");
            loadCompanies();
        })
        .catch(error => alert("Error adding company"));
}

function loadCompanies() {
    fetch(BASE_URL + "/companies")
        .then(response => response.json())
        .then(data => {
            const container = document.getElementById("companiesData");
            container.innerHTML = "";

            data.forEach(company => {
                container.innerHTML += `
                <div class="card">
                    <h3>${company.name}</h3>
                    <p><b>ID:</b> ${company.id}</p>
                    <p><b>Email:</b> ${company.email}</p>
                    <p><b>Location:</b> ${company.location}</p>
                    <p><b>Industry:</b> ${company.industry}</p>
                    <p><b>Status:</b> ${company.status}</p>
                    <button class="action-btn" onclick="approveCompany(${company.id})">Approve</button>
                    <button class="reject-btn" onclick="rejectCompany(${company.id})">Reject</button>
                </div>
            `;
            });
        });
}

function approveCompany(id) {
    fetch(BASE_URL + `/companies/${id}/approve`, {
        method: "PUT"
    })
        .then(response => response.json())
        .then(data => {
            alert("Company approved");
            loadCompanies();
        });
}

function rejectCompany(id) {
    fetch(BASE_URL + `/companies/${id}/reject`, {
        method: "PUT"
    })
        .then(response => response.json())
        .then(data => {
            alert("Company rejected");
            loadCompanies();
        });
}

// ================= JOBS =================

function addJob() {
    const companyId = document.getElementById("jobCompanyId").value;

    const job = {
        id: Number(document.getElementById("jobId").value),
        title: document.getElementById("jobTitle").value,
        description: document.getElementById("jobDescription").value,
        salary: Number(document.getElementById("jobSalary").value),
        location: document.getElementById("jobLocation").value,
        requiredSkills: document.getElementById("jobSkills").value
    };

    fetch(BASE_URL + `/jobs/company/${companyId}`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(job)
    })
        .then(response => response.json())
        .then(data => {
            if (data === null) {
                alert("Company is not approved or does not exist");
            } else {
                alert("Job added successfully");
                loadJobs();
            }
        })
        .catch(error => alert("Error adding job"));
}

function loadJobs() {
    fetch(BASE_URL + "/jobs")
        .then(response => response.json())
        .then(data => {
            const container = document.getElementById("jobsData");
            container.innerHTML = "";

            data.forEach(job => {
                container.innerHTML += `
                <div class="card">
                    <h3>${job.title}</h3>
                    <p><b>ID:</b> ${job.id}</p>
                    <p><b>Description:</b> ${job.description}</p>
                    <p><b>Salary:</b> ${job.salary}</p>
                    <p><b>Location:</b> ${job.location}</p>
                    <p><b>Skills:</b> ${job.requiredSkills}</p>
                    <p><b>Company:</b> ${job.company ? job.company.name : "N/A"}</p>
                </div>
            `;
            });
        });
}

// ================= APPLICATIONS =================

function applyForJob() {
    const applicationId = document.getElementById("applicationId").value;
    const studentId = document.getElementById("applicationStudentId").value;
    const jobId = document.getElementById("applicationJobId").value;

    const application = {
        id: Number(applicationId)
    };

    fetch(BASE_URL + `/applications/student/${studentId}/job/${jobId}`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(application)
    })
        .then(response => response.json())
        .then(data => {
            alert("Application submitted successfully");
            loadApplications();
        })
        .catch(error => alert("Error submitting application"));
}

function loadApplications() {
    fetch(BASE_URL + "/applications")
        .then(response => response.json())
        .then(data => {
            const container = document.getElementById("applicationsData");
            container.innerHTML = "";

            data.forEach(app => {
                container.innerHTML += `
                <div class="card">
                    <h3>Application #${app.id}</h3>
                    <p><b>Student:</b> ${app.student ? app.student.name : "N/A"}</p>
                    <p><b>Job:</b> ${app.job ? app.job.title : "N/A"}</p>
                    <p><b>Status:</b> ${app.status}</p>
                    <p><b>Applied Date:</b> ${app.appliedDate}</p>
                </div>
            `;
            });
        });
}

function updateApplicationStatus() {
    const id = document.getElementById("statusApplicationId").value;
    const status = document.getElementById("applicationStatus").value;

    fetch(BASE_URL + `/applications/${id}/status?status=${status}`, {
        method: "PUT"
    })
        .then(response => response.json())
        .then(data => {
            alert("Application status updated");
            loadApplications();
        })
        .catch(error => alert("Error updating status"));
}