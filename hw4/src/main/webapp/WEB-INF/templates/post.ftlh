<#-- @ftlvariable name="post_id" type="java.lang.Long" -->
<#import "common.ftlh" as c>

<@c.page>
    <#if post_id??>
    <#assign post = c.findItemBy(posts, "id", post_id)!/>
    <@c.post post "long"/>
    <#else>
        No such post
    </#if>
</@c.page>