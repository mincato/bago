package ar.com.bago.rest.security;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.DirContextAdapter;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.ldap.userdetails.UserDetailsContextMapper;
import org.springframework.stereotype.Component;

@Component
public class LdapUserDetailsContextMapper implements UserDetailsContextMapper{

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Override
	public UserDetails mapUserFromContext(DirContextOperations context, String username,
			Collection<? extends GrantedAuthority> authorities) {
		return userDetailsService.loadUserByUsername(username);
	}

	@Override
	public void mapUserToContext(UserDetails arg0, DirContextAdapter arg1) {
		// TODO Auto-generated method stub
		
	}

}
