<#-- @ftlvariable name="users" type="ru.itmo.wp.model.domain.User[]" -->
<#-- @ftlvariable name="user" type="ru.itmo.wp.model.domain.User" -->
<#import "commons.ftlh" as c>

<@c.page>
    <div class="users datatable">
        <div class="caption">User</div>
        <table>
            <thead>
            <tr>
                <th>Id</th>
                <th>Login</th>
                <#if user?? && user.admin>
                    <th>Admin</th>
                </#if>
            </tr>
            </thead>
            <tbody>
            <template>
                <tr>
                    <td class="user-id"></td>
                    <td class="user-login"></td>
                    <#if user?? && user.admin>
                        <td class="user-admin">
                            <form method="post" action="">
                                <input type="hidden" name="user-id"/>
                                <div class="button-field">
                                    <input type="submit" name="admin">
                                </div>
                            </form>
                        </td>
                    </#if>
                </tr>
            </template>
            <tr class="noData">
                <td colspan="3">
                    No data
                </td>
            </tr>
            </tbody>
        </table>
        <div class="error"></div>
    </div>

    <script>
        $(function () {
            const $noData = $(".users .noData");
            const $error = $(this).find(".error");
            $noData.find("td").text("Loading...");

            ajax({action: "findAll"}, $error, function (response) {
                response["users"].forEach(function (user) {
                    $noData.hide();
                    const $tr = $($(".users template").prop("content")).clone();
                    const $userId = $tr.find(".user-id");
                    $userId.text(user["id"]);
                    $tr.find(".user-login").text(user["login"]);
                    $tr.find(".user-admin p").text(user["admin"]);
                    $tr.find("tr").attr("data-userId", user["id"]);
                    $tr.find(".user-admin form input[name='user-id']").attr("value", user["id"]);
                    const $button = $tr.find(".user-admin form input[name='admin']");
                    $button.val(user["admin"] ? "Disable" : "Enable")


                    $tr.find(".user-admin").submit(function () {
                        const userId = $userId.text();
                        const admin = $(this).find("input[name='admin']").val();
                        ajax(
                            {action: "changeAdmin", userId: userId, admin: admin},
                            $error,
                            function (response) {
                                $button.val(response["admin"] ? "Disable" : "Enable");
                            });
                        return false;
                    });
                    $(".users tbody").append($tr);
                });
            });
        })
    </script>
</@c.page>
