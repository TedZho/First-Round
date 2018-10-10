package com.h2rd.refactoring.usermanagement;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
public class User {

    String name;
    String email;
    List<String> roles;
    
    public User() {};

    public User(String name, String email, List<String> roles) {
		this.name = name;
		this.email = email;
		this.roles = roles;
	}

	public String getName() {
        return name;
    }
    
	public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public List<String> getRoles() {
        return roles;
    }
    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
    
    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((roles == null) ? 0 : roles.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", email=" + email + ", roles=" + roles + "]";
	}
	
	
}
