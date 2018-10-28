package com.example.security.securitydbsample.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.security.securitydbsample.model.CustomUserDetails;
import com.example.security.securitydbsample.model.Users;
import com.example.security.securitydbsample.repository.UsersRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UsersRepository usersRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
				
		Optional<Users> optionalUsers = usersRepository.findByName(username);
		
		optionalUsers
			.orElseThrow(() -> new UsernameNotFoundException("Username not found"));
		return optionalUsers
				.map(CustomUserDetails::new).get();
		}

}
