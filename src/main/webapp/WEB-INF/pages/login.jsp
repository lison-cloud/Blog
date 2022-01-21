<%@ include file="/WEB-INF/fragment/base.jspf" %>

    <!DOCTYPE html>
    <html>


    <head>
        <title>
            <fmt:message key="blog.login" />
        </title>
        <link rel="stylesheet" href="css/login.css">
    </head>

    <body>
        <div class="wrapper">
            <div class="container">
                <div class="form-container">
                    <div class="form-container-title">
                        <span class="form-title">
                            <fmt:message key="blog.login" />
                        </span>
                    </div>
                    <form action="<c:url value='' ><c:param name='action' value='login' /></c:url>" method="post">
                        <label for="email"><fmt:message key="blog.email" /></label>
                        <input type="email" id="email" name="email" placeholder="Email" required>
                        <label for="passwd">
                            <fmt:message key="blog.passwd" />
                        </label>
                        <input type="password" id="passwd" name="password" placeholder="<fmt:message key='blog.passwd' />" required>
                        <c:if test="${login_failure}">
                            <div class="form-failure-content">
                                <c:out value="${failure_message}" />
                            </div>
                        </c:if>
                        <input type="submit" value="<fmt:message key='blog.login'/>">
                    </form>
                    <div class="form-container-bottom">
                        <fmt:message key="blog.notregistered" />
                        <a href="<c:url value='' ><c:param name='action' value='signin' /></c:url>">
                            <fmt:message key="blog.accountcreate" />
                        </a>
                    </div>
                </div>
            </div>
        </div>

    </html>