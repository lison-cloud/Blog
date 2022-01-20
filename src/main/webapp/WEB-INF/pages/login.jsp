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
                        <!-- <div class="form-language" style="position: relative; float: right;">
                            <%@ include file="/WEB-INF/fragment/language.jspf" %>
                        </div> -->
                    </div>
                    <form action="<c:url value='' ><c:param name='action' value='login' /></c:url>" method="post">
                        <label for="email">Email</label>
                        <input type="email" id="email" name="email" placeholder="Email">
                        <label for="passwd">
                            <fmt:message key="blog.passwd" />
                        </label>
                        <input type="password" id="passwd" name="password" placeholder="Password">
                        <c:if test="${login_failure}">
                            <div class="form-failure-content">
                                Login failure. Try again
                            </div>
                        </c:if>
                        <input type="submit" value="<fmt:message key='blog.login'/>">
                    </form>
                    <div class="form-container-bottom">
                        Not registered?<a
                            href="<c:url value='' ><c:param name='action' value='signin' /></c:url>">Create an
                            Account</a>
                    </div>
                </div>
            </div>
        </div>

    </html>