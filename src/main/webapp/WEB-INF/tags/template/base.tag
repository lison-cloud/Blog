<%@ tag body-content="scriptless" trimDirectiveWhitespaces="true" %>
<%@ attribute name="htmlTitle" type="java.lang.String" rtexprvalue="true"
              required="true" %>
<%@ include file="/WEB-INF/fragment/base.jspf" %>

<!DOCTYPE html>
<html>
    <head>
        <title><c:out value="${fn:trim(htmlTitle)}" /></title>
        <link rel="stylesheet"
              href="<c:url value="css/style.css" />" />
    </head>
    <body>
    <%@ include file="/WEB-INF/fragment/header.jspf" %>
        <div class="content">
            <%@ include file="/WEB-INF/fragment/navbar.jspf" %>
                <div class="content-top base-content-width">
                    <div class="content-wrapper">
                        <jsp:doBody />
                    </div>
                </div>
        </div>
        <div class="footer">
            <div class="footer-title base-content-width">
                <h1><a href="<c:url value='' />" class="title-link">Blog</a></h1>
            </div>
        </div>
    </body>
</html>