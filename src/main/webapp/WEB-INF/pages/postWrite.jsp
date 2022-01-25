<template:base htmlTitle="Write Post">
    <div class="post-write-area">
        <div class="post-content-meta" style="padding: 0 0 10px;">
            <div class="meta-info">
                <span class="meta-info-user">
                    <a href="<c:url value=''>
                        <c:param name='action' value='userProfile' />
                        <c:param name='userLogin' value='${post.userLogin}' />
                        </c:url>" class="user-login-link">
                        <c:out value="${login}" />
                    </a>
                </span>
                <!-- <span class="meta-info-time">
                    Today 15:34
                </span> -->
            </div>
        </div>
        <form class="post-write" action="<c:url value=''>
            <c:param name='action' value='sendPost' />
            </c:url>" method="post">
            <div style="padding: 0 0 10px;">
                <label for="type">
                    <fmt:message key="blog.type" />:
                </label>
                <select id="type" name="type">
                    <option value="draft">
                        <fmt:message key="blog.draft" />
                    </option>
                    <option value="publish">
                        <fmt:message key="blog.publish" />
                    </option>
                </select>
            </div>
            <div style="padding: 0 0 10px;">
                <label for="category">
                    <fmt:message key="blog.category" />:
                </label>
                <select id="category" name="category">
                    <option value="develop">
                        <fmt:message key="blog.develop" />
                    </option>
                    <option value="admin">
                        <fmt:message key="blog.admin" />
                    </option>
                    <option value="design">
                        <fmt:message key="blog.design" />
                    </option>
                </select>
            </div>
            <div class="post-slug" style="width: 100%;">
                <input type="text" name="slug" id="" placeholder="<fmt:message key='blog.slug' />">
            </div>
            <div class="post-title" style="width: 100%;">
                <input type="text" name="title" id="" placeholder="<fmt:message key='blog.title' />" required>
            </div>
            <div class="post-body" style="width: 100%;">
                <textarea name="content"><fmt:message key="blog.text" />...</textarea>
            </div>
            <div class="post-tags" style="width: 100%;">
                <input type="text" name="tag" placeholder="<fmt:message key='blog.tag' />">
            </div>
            <div class="post-bottom" style="padding: 10px 0;">
                <input type="submit" value="<fmt:message key='blog.save' />" style="padding: 10px 5px;">
            </div>
        </form>
    </div>
</template:base>