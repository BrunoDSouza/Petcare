package com.zieg.petcare.constraint.users;

import java.util.Calendar;
import java.util.Date;
import java.util.Set;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.zieg.petcare.model.authentication.Users;
import com.zieg.petcare.model.authentication.Role;
import com.zieg.petcare.services.UserService;

public class UserValidator implements ConstraintValidator<UsersConstraint, Users> {

	private static final Calendar current = Calendar.getInstance();
	
	@Autowired UserService Userservice;
	
	@Override
	public void initialize(UsersConstraint constraintAnnotation) {}

	@Override
	public boolean isValid(Users user, ConstraintValidatorContext context) {

		Long codigo = user.getCodigo();
		String username = user.getUsername();
		String email = user.getEmail();
		Date dtNascimento = user.getDtNascimento();
		Set<Role> roles = user.getRoles();
		
		boolean isValidName = duplicateUserName(username, codigo, context);
		boolean isValidEmail = duplicateEmail(email, codigo, context);
		boolean isValidRoles = hasRoles(roles, context);
		boolean isValidData = isValidData(dtNascimento, context);
		
		return ( isValidName && isValidEmail && isValidRoles && isValidData);
	}

	
	private boolean duplicateUserName(String username, Long codigo, ConstraintValidatorContext context){
		
		Users user = codigo != null ? Userservice.findUserByUsernameAndCodigo(username, codigo)
									: Userservice.findUserByUsername(username);
		
		if(user != null){
			context.disableDefaultConstraintViolation();;
			context.buildConstraintViolationWithTemplate("Nome de usuário já existente, por favor tente outro!")
				   .addPropertyNode("username").addConstraintViolation();
			
			return false;
		}
		
		return true;
	}
	
	private boolean duplicateEmail(String email, Long codigo, ConstraintValidatorContext context){
		
		Users user = codigo != null ? Userservice.findUserByEmailAndCodigo(email, codigo)
									: Userservice.findUserByEmail(email);
		
		if(user != null){
			context.disableDefaultConstraintViolation();;
			context.buildConstraintViolationWithTemplate("Email já cadastrado, por favor tente outro!")
				   .addPropertyNode("email").addConstraintViolation();
			
			return false;
		}
		
		return true;
	}
	
	
	private boolean isValidData(Date date, ConstraintValidatorContext context){
		
		if(date == null) return true;
	
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		
		if(calendar.get(Calendar.YEAR) < 1900 || calendar.get(Calendar.YEAR) >= current.get(Calendar.YEAR)){
			context.disableDefaultConstraintViolation();;
			context.buildConstraintViolationWithTemplate("O ano da data deve ser maior que 1900 e menor que o ano atual!")
				   .addPropertyNode("dtNascimento").addConstraintViolation();
			return false;
		}
		
		return true;
		
		
	}
	
	private boolean hasRoles(Set<Role> roles, ConstraintValidatorContext context){
		
		if(roles.isEmpty()){
			context.disableDefaultConstraintViolation();;
			context.buildConstraintViolationWithTemplate("Grupo é obrigatório!")
				   .addPropertyNode("roles").addConstraintViolation();
			return false;
			
		}
		
		return true;
		
	}
	
	
}
