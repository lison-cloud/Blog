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
                        <!-- <div class="form-language" style="position: relative; float: right;">
                            <%@ include file="/WEB-INF/fragment/language.jspf" %>
                        </div> -->
                    </div>
                    <form action="<c:url value='' ><c:param name='action' value='signin' /></c:url>" method="post">
                        <label for="login">E-mail</label>
                        <input type="text" id="email" name="email" placeholder="Email">
                        <label for="login">Login</label>
                        <input type="text" id="login" name="login" placeholder="Login">
                        <label for="passwd">
                            <fmt:message key="blog.passwd" />
                        </label>
                        <input type="password" id="passwd" name="password" placeholder="Password">
                        <c:if test="${signin_failure}">
                            <div class="form-failure-content">
                                Login failure. Try again
                            </div>
                        </c:if>
                        <input type="submit" value="<fmt:message key='blog.signin' />">
                    </form>
                </div>
            </div>
        </div>

    </html>