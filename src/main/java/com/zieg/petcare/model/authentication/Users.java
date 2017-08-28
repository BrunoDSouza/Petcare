package com.zieg.petcare.model.authentication;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import javax.persistence.CascadeType;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import com.zieg.petcare.constraint.users.UsersConstraint;
import com.zieg.petcare.model.enums.TipoStatus;


@Entity
@Table(name="users")
@UsersConstraint
public class Users{

	/*Metodos Construtores*/
	public Users() {}
	
	public Users(Users users) {
		this.codigo = users.getCodigo();
		this.username = users.getUsername();
		this.email = users.getEmail();
		this.senha = users.getSenha();
		this.status = users.getStatus();
		this.dtNascimento = users.getDtNascimento();
		this.roles = users.getRoles();
	}
	
	@Id
	@Column(name="idusuario", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	
	@NotBlank
	@Column(name="usuario")
	@Size(message="O tamanho do nome do usuario deve possuir no máximo 255 caracteres!", max = 255)
	private String username;
	
	@NotNull
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Column(name="dt_nascimento")
	private Date dtNascimento;
	
	@NotBlank
	@Email(message="Email é inválido!")
	@Column(name="email")
	private String email;
	
	@NotBlank
	@Size(message="A senha deve possuir no minimo 6 caracteres!", min = 6)
	@Column(name="senha")
	private String senha;
	
	@NotNull
	@Column(name="cod_status")
	private TipoStatus status = TipoStatus.ATIVADO;
	
	@NotNull
	@OneToMany(cascade = {CascadeType.PERSIST,CascadeType.REFRESH, CascadeType.MERGE}, fetch = FetchType.EAGER, orphanRemoval=false)
	@JoinTable(name="users_roles", joinColumns = @JoinColumn(name = "cod_user"), inverseJoinColumns = @JoinColumn(name="cod_role"))
	private Set<Role> roles;


	
	/*Getters and Setters*/
	public Long getCodigo() { return codigo; }
	public void setCodigo(Long codigo) { this.codigo = codigo; }
	public String getUsername() { return username; }
	public void setUsername(String username) { this.username = username; }
	public Date getDtNascimento() {return dtNascimento;}
	public void setDtNascimento(Date dtNascimento) {this.dtNascimento = dtNascimento;}
	public String getEmail() {return email;}
	public void setEmail(String email) {this.email = email;}
	public String getSenha() {return senha;}
	public void setSenha(String senha) {this.senha = senha;}
	public TipoStatus getStatus() { return status; }
	public void setStatus(TipoStatus status) { this.status = status;}
	public Set<Role> getRoles() { return roles; }
	public void setRoles(Set<Role> roles) { this.roles = roles; }
	
	
	public boolean isEnabled(){
		boolean bl = this.status.equals(TipoStatus.ATIVADO); 
		return bl;
	}
	
	public boolean isEmpty(){
		
		return ((codigo == null) && (username == null) &&
				(dtNascimento == null) && (email == null) &&
				(senha == null) && (status == null) && (roles == null));
		
	}
	
	
	
}
