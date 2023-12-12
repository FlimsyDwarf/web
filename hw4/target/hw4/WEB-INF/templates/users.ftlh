<#import "./common.ftlh" as c>

<@c.page>
    <div class="datatable">
        <table>
            <thead>
            <tr>
                <th>Id</th>
                <th>Handle</th>
                <th>Name</th>
            </tr>
            </thead>
            <tbody>
            <#list users as u>
    <#--            <li><a href="user?user_id=${u.id}">${u.name}</a> </li>-->
                <tr>
                    <td>${u.id}</td>
                    <td><@c.userlink u false/></td>
                    <td>${u.name}</td>
                </tr>
            </#list>
            </tbody>
        </table>
    </div>
</@c.page>
