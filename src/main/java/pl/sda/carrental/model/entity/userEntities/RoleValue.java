package pl.sda.carrental.model.entity.userEntities;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import pl.sda.carrental.service.RoleValueValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = RoleValueValidator.class)
public @interface RoleValue {
    String message() default "Nieprawidłowa rola użytkownika";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}