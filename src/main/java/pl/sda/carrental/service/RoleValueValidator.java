package pl.sda.carrental.service;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import pl.sda.carrental.model.entity.userEntities.Role;
import pl.sda.carrental.model.entity.userEntities.RoleValue;

public class RoleValueValidator implements ConstraintValidator<RoleValue, Role> {
    
    @Override
    public void initialize(RoleValue constraintAnnotation) {
    }
    
    @Override
    public boolean isValid(Role value, ConstraintValidatorContext context) {
        return value != null && (value.getRoleName().equals("ADMIN") || value.getRoleName().equals("EMPLOYEE") || value.getRoleName().equals("CUSTOMER"));
    }
}