<template:base htmlTitle="${user.login}">
    <%@ include file="/WEB-INF/fragment/userDetail.jspf" %>
        <div class="content-top">
            <c:forEach items="${posts}" var="post">
                <c:choose>
                    <c:when test="${fn:length(post.comments)==0}">
                        <p>No comments yet...</p>
                    </c:when>
                    <c:otherwise>
                        <c:forEach items="${post.comments}" var="comment">
                            <div class="comment-post"
                                style="padding: 5px 10px;margin-bottom: 10px;background-color: #ffffff;">
                                <div class="post-content-title">
                                    <h3 class="post-title">
                                        <a href="<c:url value=''>
                                                            <c:param name='action' value='postDetail' />
                                                            <c:param name='postSlug' value='${post.slug}' />
                                                            </c:url>" class="post-title-link">
                                            <c:out value="${post.title}" />
                                        </a>
                                    </h3>
                                </div>
                                <div class="comment">
                                    <div class="meta-info">
                                        <span class="meta-info-user">
                                            <a href="<c:url value=''>
                                                                    <c:param name='action' value='userProfile' />
                                                                    <c:param name='userLogin' value='${comment.userLogin}' />
                                                                    </c:url>" class="user-login-link">
                                                <c:out value="${comment.userLogin}" />
                                            </a>
                                        </span>
                                        <span class="meta-info-time">
                                            <c:out value="${comment.publishedAt}" />
                                        </span>
                                    </div>
                                    <div class="comment-text">
                                        <c:out value="${comment.text}" />
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </div>
</template:base>