package net.kdigital.web_project.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import net.kdigital.web_project.dto.CustomerDTO;
import net.kdigital.web_project.dto.LoginUserDetails;
import net.kdigital.web_project.entity.CustomerEntity;
import net.kdigital.web_project.repository.CustomerRepository;

@RequiredArgsConstructor
@Service
public class LoginCustomerDetailsService implements UserDetailsService {

	private final CustomerRepository customerRepository;

	@Override
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {

		CustomerEntity customerEntity = customerRepository.findByUserId(userId)
				.orElseThrow(() -> {
					throw new UsernameNotFoundException("err 발생");
				});
		CustomerDTO customerDTO = CustomerDTO.toDTO(customerEntity);

		return new LoginUserDetails(customerDTO);
	}

}
