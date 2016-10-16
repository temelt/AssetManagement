package com.vektorel.assetman.web.utilities;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.vektorel.assetman.web.entity.Kullanici;
import com.vektorel.assetman.web.service.kullanici.KullaniciService;

public class SpringUserServiceImpl implements UserDetailsService{

	@Autowired
	private KullaniciService kullaniciService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Kullanici k =kullaniciService.getByUsername(username);
		if(k!=null){
			List<GrantedAuthority> roles =new ArrayList<GrantedAuthority>();
			roles.add(new Authority("USER"));
			return new User(k.getUsername(), k.getPassword(), true, true, true, true, roles);
		}
		
		throw new UsernameNotFoundException("Kullanýcý Bulunamadý");
	}
	
	class Authority implements GrantedAuthority{

		/**
		 * 
		 */
		private static final long serialVersionUID = -3510607544393273653L;
		private String authority;
		
		public Authority(String authority) {
			this.setAuthority(authority);
		}
		
		@Override
		public String getAuthority() {
			return authority;
		}

		public void setAuthority(String authority) {
			this.authority = authority;
		}
		
	}

}
