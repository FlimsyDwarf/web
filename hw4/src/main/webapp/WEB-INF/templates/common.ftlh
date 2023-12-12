<#-- @ftlvariable name="logged_user_id" type="java.lang.Long" -->
<#-- @ftlvariable name="uri" type="java.lang.String" -->
<#macro page>
    <!DOCTYPE html>
    <html lang="ru">
    <head>
        <meta charset="UTF-8">
        <title>Codeforces</title>
        <link rel="stylesheet" href="/css/normalize.css">
        <link rel="stylesheet" href="/css/style.css">
        <link rel="stylesheet" href="/css/user_info.css">
        <link rel="stylesheet" href="/css/article.css">
        <link rel="stylesheet" href="/css/users_table.css">
        <link rel="icon" href="/favicon.ico">
    </head>
    <body>
    <@header/>
    <div class="middle">
        <@sidebar/>
        <main>
            <#nested/>
        </main>
    </div>
    <@footer/>
    </body>
    </html>
</#macro>

<#macro header>
    <header>
        <a href="#" class="logo"><img alt="Codeforces" title="Codeforces"
                                      src="/img/codeforces-logo-with-telegram.png"></a>
        <div class="languages">
            <a href="#"><img alt="In English" title="In English"
                             src="/img/gb.png"></a>
            <a href="#"><img alt="In Russian" title="In Russian"
                             src="/img/ru.png"></a>
        </div>
        <div class="enter-or-register-box">
            <#if logged_user_id??>
                <#assign u = findItemBy(users, "id", logged_user_id)!/>
                <#if u?has_content>
                    <@userlink user true/>
                    |
                    <a href="/logout">Logout</a>
                <#else>
                    <a href="/enter">Enter</a>
                    |
                    <a href="/register">Register</a>
                </#if>
            <#else>
                <a href="/enter">Enter</a>
                |
                <a href="/register">Register</a>
            </#if>
        </div>
        <nav>
            <#assign items=[
            {"href": "/index", "name": "Home"},
            {"href": "/users", "name": "users"},
            {"href": "/misc/help", "name": "Help"},
            {"href": "/contests", "name": "Contests"}
            ]/>
            <ul>
                <#list items as item>
                    <li>
                        <#if uri == item.href>
                            <a class="underscored" href="${item.href}">${item.name}</a>
                        <#else>
                            <a href="${item.href}">${item.name}</a>
                        </#if>
                    </li>
                </#list>
            </ul>
        </nav>
    </header>
</#macro>

<#macro sidebar>
    <aside>
        <#list posts as post>
            <#if post?counter == 4>
                <#break>
            </#if>
            <section>
                <div class="header">Post #${post.id}</div>
                <div class="body">${post.shortText}</div>
                <div class="footer"><a href="/post?post_id=${post.id}">View all</a></div>
            </section>
        </#list>
    </aside>
</#macro>

<#macro footer>
    <footer>
        2010-2021 &copy; by Mike Mirzayanov
    </footer>
</#macro>

<#macro userlink user nameOnly>
<#-- @ftlvariable name="user" type="ru.itmo.web.hw4.model.User" -->
    <#if nameOnly == true>
        <a href="/user?handle=${user.handle}">${user.name}</a>
    <#else>
        <a href="/user?handle=${user.handle}" style="color: ${user.color}; text-decoration: none; font-weight: bold">${user.handle}</a>
    </#if>
</#macro>

<#macro post post type>
<#-- @ftlvariable name="post" type="ru.itmo.web.hw4.model.Post" -->
    <#if post?? && post?has_content>
        <article>
            <div class="title">${post.title}</div>
            <div class="information">By ${findItemBy(users, "id", post.user_id).handle}</div>
            <div class="body">
                <#if type == "short">
                    ${post.text?truncate(350, "...")}
                <#else>
                    ${post.text}
                </#if>
            </div>
            <ul class="attachment">
            </ul>
            <div class="footer">
                <div class="left">
                    <img src="img/voteup.png" title="Vote Up" alt="Vote Up"/>
                    <span class="positive-score">+173</span>
                    <img src="img/votedown.png" title="Vote Down" alt="Vote Down"/>
                </div>
                <div class="right">
                    <img src="img/date_16x16.png" title="Publish Time" alt="Publish Time"/>
                    2 days ago
                    <img src="img/comments_16x16.png" title="Comments" alt="Comments"/>
                    <a href="#">68</a>
                </div>
            </div>
        </article>
    <#else>
        No such post
    </#if>
</#macro>

<#function findItemBy items key value>
    <#list items as item>
        <#if item[key] == value>
            <#return item/>
        </#if>
    </#list>
</#function>

<#function findIndexBy items key value>
    <#list items as item>
        <#if item[key] == value>
            <#return item?index/>
        </#if>
    </#list>
</#function>

<#function getPosts posts user_id>
    <#assign counter=0/>
    <#list posts as post>
        <#if post.user_id == user_id>
            <#assign counter = counter + 1/>
        </#if>
    </#list>
    <#return counter/>
</#function>
