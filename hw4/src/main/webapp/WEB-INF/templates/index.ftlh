<#-- @ftlvariable name="post_id" type="java.lang.Long" -->
<#import "./common.ftlh" as c>

<@c.page>
    <#list posts?reverse as post>
        <@c.post post "short"/>
    </#list>
</@c.page>
