package vp.spring.rcs.model.user;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Mapping table between User and Authority
 * @author Goran
 *
 */
@Entity
public class SecurityUserAuthority {
	@Id
	@GeneratedValue
	private int id;
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	private SecurityUser user;
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	private SecurityAuthority authority;
	
	public SecurityUserAuthority(int id, SecurityUser user_id, SecurityAuthority authority_id) {
		super();
		this.id = id;
		this.user = user_id;
		this.authority  = authority_id;
		
	}

	public SecurityUserAuthority() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public SecurityUser getUser() {
		return user;
	}

	public void setUser(SecurityUser user) {
		this.user = user;
	}

	public SecurityAuthority getAuthority() {
		return authority;
	}

	public void setAuthority(SecurityAuthority authority) {
		this.authority = authority;
	}
}
