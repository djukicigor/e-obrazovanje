package vp.spring.rcs.model.user;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class SecurityAuthority {
	@Id
	@GeneratedValue
	private int id;
	
	String name;

	@JsonIgnore
	@OneToMany(mappedBy = "authority", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private Set<SecurityUserAuthority> userAuthorities = new HashSet<SecurityUserAuthority>();
	
	public SecurityAuthority(String name) {
		super();
		this.name = name;
		
	}

	public SecurityAuthority() {
		super();
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<SecurityUserAuthority> getUserAuthorities() {
		return userAuthorities;
	}

	public void setUserAuthorities(Set<SecurityUserAuthority> userAuthorities) {
		this.userAuthorities = userAuthorities;
	}
}
