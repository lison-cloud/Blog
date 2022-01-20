<template:base htmlTitle="${post.title}">
    <div class="post">
        <c:choose>
            <c:when test="${post==null}">
                <p>Some error happened. No post...</p>
            </c:when>
            <c:otherwise>
                <div class="post-content">
                    <div class="post-content-meta">
                        <div class="meta-info">
                            <span class="meta-info-user">
                                <a href="<c:url value=''>
                                        <c:param name='action' value='userProfile' />
                                        <c:param name='userLogin' value='${post.userLogin}' />
                                        </c:url>" class="user-login-link">
                                    <c:out value="${post.userLogin}" />
                                </a>
                            </span>
                            <span class="meta-info-time">
                                <c:out value="${post.publishedAt}" />
                            </span>
                        </div>
                    </div>
                    <div class="post-content-title">
                        <h2 class="post-title">
                            <c:out value="${post.title}" />
                        </h2>
                    </div>
                    <div class="post-content-body">
                        <p>
                            <c:out value="${post.content}" />
                        </p>
                    </div>
                    <div class="post-content-footer">
                        <div class="post-content-tags">
                            <%@ include file="/WEB-INF/fragment/tag.jspf" %>
                        </div>
                        <div class="post-content-category">
                            <b>Category:</b>
                            <span class="post-category">
                                <a href="<c:url value=''>
                                                            <c:param name='action' value='relatedPost' />
                                                            <c:param name='category' value='${post.category.slug}' />
                                                            </c:url>">
                                    <c:out value="${post.category.title}" />
                                </a>
                            </span>
                        </div>
                    </div>
                </div>
            </c:otherwise>
        </c:choose>
    </div>
    <div class="content-comment">
        <div class="content-comment-info">
            <fmt:message key="blog.comments" />:${fn:length(post.comments)}
        </div>
        <div class="content-comment-list">
            <c:choose>
                <c:when test="${fn:length(post.comments)==0}">
                    <p>No comments yet...</p>
                </c:when>
                <c:otherwise>
                    <c:forEach items="${post.comments}" var="comment">
                        <div class="comment">
                            <div class="comment-meta">
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
                            </div>
                            <div class="comment-text">
                                <c:out value="${comment.text}" />
                            </div>
                        </div>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</template:base>