package com.example.cms.security;

import com.example.cms.customexception.GenericNotFoundException;
import com.example.cms.entity.Role;
import com.example.cms.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;
	 com.example.cms.entity.User user;
	
	@Override
	public UserDetails loadUserByUsername(String userName) {
		user = userRepository.findByUserName(userName);
		if(user != null) {
			return new User(user.getUserName(), user.getPassword(), getAuthorities(user.getRoles()));
		}
		throw new GenericNotFoundException("User not found");
	}
	
	private Collection<? extends GrantedAuthority> getAuthorities(Collection<Role> roles) {
		return user.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role.getRoleName().name()))
				.collect(Collectors.toList());
	}

}
