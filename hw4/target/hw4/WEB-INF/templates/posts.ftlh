<#-- @ftlvariable name="user_id" type="java.lang.Long" -->
<#import "common.ftlh" as c>

<@c.page>
    <#assign u = c.findItemBy(users, "id", user_id)!/>
    <#if u?has_content>
        <div class="username_posts">Posts by ${u.handle}:</div>
        <#list posts?reverse as post>
            <#if user_id == post.user_id>
                <@c.post post "short"/>
            </#if>
        </#list>
    <#else>
        No such user
    </#if>
</@c.page>
