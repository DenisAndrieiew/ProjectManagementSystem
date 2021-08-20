<div class="navbar">
    <a href="${pageContext.request.contextPath}/">Home</a>
    <div class="dropdown">
        <button class="dropbtn">Developers
            <i></i>
        </button>
        <div class="dropdown-content">
            <a href="${pageContext.request.contextPath}/developers">Show developers</a>
            <a href="${pageContext.request.contextPath}/developers/new">Add developer</a>
        </div>
    </div>
    <div class="dropdown">
        <button class="dropbtn">Projects
            <i></i>
        </button>
        <div class="dropdown-content">
            <a href="${pageContext.request.contextPath}/projects">Show Projects</a>
            <a href="${pageContext.request.contextPath}/projects/new">Add Project</a>
        </div>
    </div>
    <div class="dropdown">
            <button class="dropbtn">Companies
                <i></i>
            </button>
            <div class="dropdown-content">
                <a href="/companies">Show Companies</a>
                <a href="/companies/new">Add Companies</a>
            </div>
        </div>
        <div class="dropdown">
            <button class="dropbtn">Customers
                <i></i>
            </button>
            <div class="dropdown-content">
                <a href="/customers">Show Customers</a>
                <a href="/customers/new">Add Customers</a>
            </div>
        </div>
</div>