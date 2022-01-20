<template:base htmlTitle="${user.login}">
    <%@ include file="/WEB-INF/fragment/userDetail.jspf" %>
        <div class="content-top">
            <div class="content-wrapper">
                <div class="content-post-list">
                    <%@ include file="/WEB-INF/fragment/post.jspf" %>
                </div>
            </div>
        </div>
</template:base>