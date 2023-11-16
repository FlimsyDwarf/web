<#-- @ftlvariable name="text" type="java.lang.String" -->
<#-- @ftlvariable name="title" type="java.lang.String" -->
<#import "commons.ftlh" as c>

<@c.page>
    <div class="article form-box">
        <div class="body">
            <form method="post" action="">
                <input type="hidden" name="action" value="create"/>
                <div class="field">
                    <div class="name">
                        <label for="title">Title</label>
                    </div>
                    <div class="value">
                        <input id="title" name="title" value="${title!}"/>
                    </div>
                </div>
                <div class="field">
                    <div class="name">
                        <label for="text-area">Text</label>
                    </div>
                    <div class="value">
                        <textarea id="text" name="text" class="value" value="${text!}" style="resize: none"></textarea>
                    </div>
                </div>
                <div class="error"></div>
                <div class="button-field">
                    <input type="submit" value="Create">
                </div>
            </form>
        </div>
    </div>

    <script>
        $(function () {
            $(".article form").submit(function () {
                const title = $(this).find("input[name='title']").val();
                const text = $(this).find("textarea[name='text']").val();
                const $error = $(this).find(".error");

                ajax({
                    action: "create",
                    title: title,
                    text: text
                }, $error);

                return false;
            });
        })
    </script>
</@c.page>
