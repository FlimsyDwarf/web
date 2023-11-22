<#-- @ftlvariable name="enterForm" type="ru.itmo.wp.form.UserCredentials" -->
<#import "/spring.ftl" as spring>
<#import "commons.ftlh" as c>

<@c.page>
    <div class="notice form-box">
        <div class="header">New Notice</div>
        <div class="body">
            <form method="post" action="">
                <div class="field">
                    <div class="name">
                        <label for="content">Content</label>
                    </div>
                    <textarea id="content" name="content" class="value" content="${notice.content!}"  style="resize: none"></textarea>
                    <@c.error "notice.content"/>
                </div>
                <div class="button-field">
                    <input type="submit" value="Add"/>
                </div>
            </form>
        </div>
    </div>
</@c.page>
