<#-- @ftlvariable name="articles" type="ru.itmo.wp.model.domain.Article[]" -->
<#import "commons.ftlh" as c>

<@c.page>
    <div class="articles datatable">
        <div class="caption">My articles</div>
        <table>
            <thead>
            <tr>
                <th>Id</th>
                <th>Title</th>
                <th>Hide/Show</th>
            </tr>
            </thead>
            <tbody>
            <#list articles as article>
                <tr>
                    <td class="article-id">${article.id}</td>
                    <td class="article-title">${article.title}</td>
                    <td class="article-hidden">
                        <form method="post" action="changeHidden">
                            <input type="hidden" name="id" value="${article.id}"/>
                            <div class="button-field">
                                <input type="submit" name="hidden"
                                       value="<#if article.hidden>Show<#else>Hide</#if>"/>
                            </div>
                        </form>
                    </td>
                </tr>
            </#list>
            <template>
                <tr>
                    <td class="article-id"></td>
                    <td class="article-title"></td>
                    <td class="article-hidden"></td>
                </tr>
            </template>
            </tbody>
        </table>
        <div class="error"></div>
    </div>

    <script>
        $(function () {
            let $error = $(this).find(".error");
            $(".articles .article-hidden").submit(function () {
                let $button = $(this).find("input[name='hidden']");
                const hidden = $button.val();
                const articleId = $(this).find("input[name='id']").val();

                ajax(
                    {action: "changeHidden", articleId: articleId, hidden: hidden},
                    $error,
                    function (response) {
                        notify(response["message"]);
                        $button.val(response["hidden"]);
                    });
                return false;
            });
        })
    </script>
</@c.page>
