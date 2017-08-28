package com.zieg.petcare.services;

import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import com.zieg.petcare.model.authentication.UserDetailsCustom;
import com.zieg.petcare.model.authentication.Users;
import com.zieg.petcare.repository.UsersRepository;

@Service
@Configurable
public class UserService implements UserDetailsService{

	@Autowired private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired private UsersRepository usersRepository;
	
	@Autowired @Qualifier("jsr303Validator") 
	private Validator validator;
	
	
	@Autowired
	@Resource(name="AuthCustom")
	private AuthenticationManager manager; 
	
	public Users findUserByNameOrEmail(String value){
		return usersRepository.findByUsernameIgnoreCaseOrEmailIgnoreCase(value, value).get();
	}
	
	public Optional<Users> findUserByNameOrEmail(String username, String email){
		return usersRepository.findByUsernameIgnoreCaseOrEmailIgnoreCase(username, email);
	}
	
	public Users findUserByUsername(String username){
		return usersRepository.findByUsernameIgnoreCase(username);
	}
	
	public Users findUserByUsernameAndCodigo(String username, Long codigo){
		return usersRepository.findByUsernameIgnoreCaseAndCodigoNot(username, codigo);
	}
	
	public Users findUserByEmail(String email){
		return usersRepository.findByEmailIgnoreCase(email);
	}
	
	public Users findUserByEmailAndCodigo(String email, Long codigo){
		return usersRepository.findByEmailIgnoreCaseAndCodigoNot(email, codigo);
	}
	
	public Users findOne(Long codigo){
		return usersRepository.findOne(codigo);
	}
	
	public List<Users> findAll(){
		return usersRepository.findAll();
	}
	
	public List<Users> findUsers(){
		
		Users user = getUserPrincipal();
		return usersRepository.findByCodigoNot(user.getCodigo());
	}
	
	public void save(Users user){
		
		if(user.getCodigo() == null)
			user.setSenha(bCryptPasswordEncoder.encode(user.getSenha()));
		
		usersRepository.save(user);
		
		
		
		
	}
	
	public void delete(Users user){
		usersRepository.delete(user);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	
		Optional<Users> users = findUserByNameOrEmail(username, username);
		users.orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado!"));

		UserDetails userLoad = users.map(UserDetailsCustom::new).get(); 
		
		return userLoad;
		
	}
	
	public Users getUpdates(Users user){
		
		String username = user.getUsername();
		String email = user.getEmail();
		
		Users userPrincipal = getUserPrincipal();
		userPrincipal.setUsername(username);
		userPrincipal.setEmail(email);
		
		return userPrincipal;
	}
	
	public void UpdateContextSecurity(Users user){
		
		Authentication request = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getSenha());
		Authentication update = manager.authenticate(request);
		SecurityContextHolder.getContext().setAuthentication(update);
		
	}
	
	public BindingResult isValidUser(Users user, BindingResult result){
		  validator.validate(user, result);
		  return result;
	}
	
	public Users getUserPrincipal(){
		
		String username = getUserNameLoged();
		return new Users((Users) loadUserByUsername(username));
	
	}
	
	public Authentication getUserDetailsAuthentication(){
		return SecurityContextHolder.getContext().getAuthentication();
	}
	
	public String getUserNameLoged(){
		return SecurityContextHolder.getContext()
								    .getAuthentication()
								    .getName();
	}
	
	
	public boolean isUserLogged(){
        try {
        	return SecurityContextHolder.getContext()
            		    			    .getAuthentication()
            						    .isAuthenticated();

        } catch (Exception e) {
            return false;
        }
    }
	
	
}
