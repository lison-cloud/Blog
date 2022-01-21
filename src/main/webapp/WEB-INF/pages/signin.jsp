<%@ include file="/WEB-INF/fragment/base.jspf" %>

    <!DOCTYPE html>
    <html>


    <head>

        <title>
            <fmt:message key="blog.signin" />
        </title>
        <link rel="stylesheet" href="css/login.css">
    </head>

    <body>
        <div class="wrapper">
            <div class="container">
                <div class="form-container">
                    <div class="form-container-title">
                        <span class="form-title">
                            <fmt:message key="blog.signin" />
                        </span>
                    </div>
                    <form action="<c:url value='' ><c:param name='action' value='signin' /></c:url>" method="post">
                        <label for="login"><fmt:message key="blog.email" /></label>
                        <input type="text" id="email" name="email" placeholder="Email" required>
                        <label for="login"><fmt:message key="blog.userLogin" /></label>
                        <input type="text" id="login" name="login" placeholder="Login" required>
                        <label for="passwd">
                            <fmt:message key="blog.passwd" />
                        </label>
                        <input type="password" id="passwd" name="password" placeholder="<fmt:message key='blog.passwd' />" required>
                        <c:if test="${signin_failure}">
                            <div class="form-failure-content">
                                <c:out value="${failure_message}" />
                            </div>
                        </c:if>
                        <input type="submit" value="<fmt:message key='blog.signin' />">
                    </form>
                </div>
            </div>
        </div>

    </html>