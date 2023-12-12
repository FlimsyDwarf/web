<#-- @ftlvariable name="user_id" type="java.lang.Long" -->
<#-- @ftlvariable name="handle" type="java.lang.String" -->
<#import "./common.ftlh" as c>

<@c.page>
    <div class="user_table">
        <#if handle??>
            <#assign user_index =c.findIndexBy(users, "handle", handle)!/>
        </#if>
        <#if user_id??>
            <#assign user_index =c.findIndexBy(users, "id", user_id)!/>
        </#if>
        <#if user_index?has_content>
            <#assign u=users[user_index]>
            <div class="user_name">
                <@c.userlink u false/>
                <img alt="user image" title="user image" src="/img/user.jpg">
            </div>
            <ul>
                <#assign user_info={
                    'id': u.id,
                    'handle': u.handle,
                    'name': u.name,
                    'posts': c.getPosts(posts, u.id)
                }>
                <#list user_info as key, value>
                    <li>
                    <#if key == 'posts'>
                        <a href="/posts?user_id=${u.id}">${key}: ${value}</a>
                    <#else>
                        ${key}: ${value}
                    </#if>
                    </li>
                </#list>
            </ul>
            <div class="arrows">
                <#assign prev_user = users[user_index - 1]!/>
                <#assign next_user = users[user_index + 1]!/>

                <#if prev_user?has_content>
                    <a href="/user?handle=${prev_user.handle}">&leftarrow;</a>
                <#else>
                    <span>&leftarrow;</span>
                </#if>
                <#if next_user?has_content>
                    <a href="/user?handle=${next_user.handle}">&rightarrow;</a>
                <#else>
                    <span>&rightarrow;</span>
                </#if>
            </div>
        <#else>
            No such user
        </#if>
    </div>
</@c.page>
