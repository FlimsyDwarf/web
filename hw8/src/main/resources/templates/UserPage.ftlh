<#import "commons.ftlh" as c>

<@c.page>
<#--    <div class="login">-->
<#--        Id: ${user.id!};-->
<#--        Login: ${user.login!};-->
<#--        Creation Time: ${user.creationTime!};-->
<#--    </div>-->
    <div class="user datatable">
        <#if user??>
            <div class="caption">User</div>
            <table>
                <thead>
                <tr>
                    <th>Id</th>
                    <th>Login</th>
                    <th>Created</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td class="id">${user.id!}</td>
                    <td class="login">${user.login!}</td>
                    <td class="creationTime">${user.creationTime!}</td>
                </tr>
                </tbody>
            </table>
        <#else>
            <div style="font-weight: bold; color: rgb(59,89,152); text-align: center">
                No such user
            </div>
        </#if>
<#--        <@c.error "user.id"/>-->
    </div>
</@c.page>