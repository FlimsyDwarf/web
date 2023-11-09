<#-- @ftlvariable name="talks" type="ru.itmo.wp.model.domain.Talk[]" -->
<#-- @ftlvariable name="text" type="java.lang.String" -->
<#-- @ftlvariable name="error" type="java.lang.String" -->
<#-- @ftlvariable name="user" type="ru.itmo.wp.model.domain.User" -->
<#-- @ftlvariable name="users" type="ru.itmo.wp.model.domain.User[]" -->
<#import "commons.ftlh" as c>

<@c.page>
    <div class="form-box">
        <div class="body">
            <form method="post" action="">
                <input type="hidden" name="action" value="send"/>
                <div class="field">
                    <div class="name">
                        <label for="login">Users</label>
                    </div>
                    <select class="value" id="targetUserId" name="targetUserId">
                        <#list users as u>
                            <option value="${u.id}">${u.login}</option>
                        </#list>
                    </select>
                </div>
                <div class="field">
                    <div class="name">
                        <label for="text-area">Message</label>
                    </div>
                    <textarea id="text" name="text" class="value" value="${text!}" style="resize: none"></textarea>
                </div>
                <#if error??>
                    <div class="error">
                        ${error!}
                    </div>
                </#if>
                <div class="button-field">
                    <input type="submit" value="Send">
                </div>
            </form>
        </div>
    </div>
    <div class="datatable" style="margin-top: 5rem">
        <div class="caption">Talk</div>
        <table>
            <thead>
            <tr>
                <th>Id</th>
                <th>Source User Login</th>
                <th>Target User Login</th>
                <th>Text</th>
                <th>Created</th>
            </tr>
            </thead>
            <tbody>
            <#list talks as t>
                <tr>
                    <td>${t.id}</td>
                    <td>${t.sourceUser.login}</td>
                    <td>${t.targetUser.login}</td>
                    <td>${t.text}</td>
                    <td>${t.creationTime}</td>
                </tr>
            </#list>
            </tbody>
        </table>
    </div>
</@c.page>