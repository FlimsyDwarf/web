<#import "commons.ftlh" as c>

<@c.page>
    <div class="users datatable">
        <div class="caption">User</div>
        <table>
            <thead>
            <tr>
                <th>Id</th>
                <th>Login</th>
                <th>Created</th>
                <th>Disabled</th>
            </tr>
            </thead>
            <tbody>
            <#if users??>
                <#list users as user>
                    <tr>
                        <td class="id">${user.id}</td>
                        <td class="login">${user.login}</td>
                        <td class="creationTime">${user.creationTime}</td>
                        <td class="disabled">
                            <form method="post">
                                <input type="hidden" id="id" name="id" value="${user.id}">
                                <input type="hidden" id="disabled" name="disabled" value="<#if user.disabled> 1 <#else> 0</#if>">
                                <div class="button-field">
                                    <input type="submit" value="<#if user.disabled> Enable <#else> Disable</#if>"/>
                                </div>
                            </form>
                        </td>
                    </tr>
                </#list>
            <#else>
                <tr class="noData">
                    <td colspan="3">
                        No data
                    </td>
                </tr>
            </#if>
            </tbody>
        </table>
    </div>
</@c.page>
