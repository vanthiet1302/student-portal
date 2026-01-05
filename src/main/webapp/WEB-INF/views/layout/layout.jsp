<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 01-Jan-26
  Time: 1:58 PM
  To change this template use File | Settings | File Templates.
--%>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>

<body>
<jsp:include page="/WEB-INF/views/common/navbar.jsp"/>

<div class="app-container">
    <jsp:include page="/WEB-INF/views/common/sidebar.jsp"/>

    <main id="main-content" class="app-content">
        <jsp:include page="${content}"/>
        <jsp:include page="/WEB-INF/views/common/footer.jsp"/>
    </main>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/sidebar.js"></script>
</body>
</html>

