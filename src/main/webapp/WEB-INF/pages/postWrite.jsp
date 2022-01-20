<template:base htmlTitle="Write Post">
    <div class="post-write-area" style="
                    display: flex;
                    flex-direction: column;
                    width: 100%;
                    min-height: 640px;
                    background-color: #ffffff;
                    padding: 20px 16px 0;">
        <div class="post-content-meta" style="padding: 0 0 10px;">
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
                    Today 15:34
                </span>
            </div>
        </div>
        <form action="<c:url value='' ><c:param name='action' value='sendPost' /></c:url>" method="post"
            style="flex: 1 0 100%;">
            <div class="post-title" style="width: 100%;">
                <input type="text" name="postTitle" id="" placeholder="Title" style="
                            height: 50px;
                            padding: 5px 10px;
                            width: 100%;
                            border: 2px solid black;
                            border-radius: 4px;
                            background-color: #f1f1f1;
                            font-size: 16px;
                            font-weight: 500;
                            ">
            </div>
            <div class="post-body" style="width: 100%;">
                <textarea name="postBody" id="" style="
                                height: 500px;
                                padding: 10px;
                                width: 100%;
                                border: 2px solid black;
                                border-radius: 4px;
                                background-color: #f1f1f1;
                                font-size: 16px;
                                resize: none;
                                ">

                            </textarea>
            </div>
            <div class="post-bottom" style="padding: 10px 0;">
                <input type="submit" value="Submit" style="padding: 10px 5px;">
            </div>
        </form>
    </div>
</template:base>