<#import "commons.ftlh" as c>

<@c.page>
    <#if post??>
        <@c.post post=post/>
        <#if user??>
            <div class="form-box">
                <div class="header">Write comment</div>
                <div class="body">
                    <form method="post" action="">
                        <div class="field">
                            <div class="name">
                                <label for="text">Text</label>
                            </div>
                            <div class="value">
                                <textarea id="text" name="text" style="resize: none">${comment.text!}</textarea>
                            </div>
                            <@c.error "comment.text"/>
                        </div>
                        <div class="button-field">
                            <input type="submit" value="Write">
                        </div>
                    </form>
                </div>
            </div>
        </#if>
        <#list post.comments as comment>
            <@c.comment comment=comment/>
        </#list>
    <#else>
        <div>
            No Such Post
        </div>
    </#if>
</@c.page>
