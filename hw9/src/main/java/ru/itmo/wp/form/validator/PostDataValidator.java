package ru.itmo.wp.form.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.itmo.wp.form.PostData;

@Component
public class PostDataValidator implements Validator {

    public boolean supports(Class<?> clazz) {
        return PostData.class.equals(clazz);
    }

    public void validate(Object target, Errors errors) {
        if (!errors.hasErrors()) {
            PostData postData = (PostData) target;
            for (String tag : postData.getTags().split("\\s+")) {
                if (!tag.matches("[a-zA-Z]+")) {
                    errors.rejectValue("tags", "tags.invalid-tag", "invalid tag");
                }
            }
        }
    }
}
