
	package com.banking.config;

	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.context.annotation.Bean;
	import org.springframework.context.annotation.Configuration;
	import org.springframework.http.HttpMethod;
	import org.springframework.security.authentication.AuthenticationManager;
	import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
	import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
	import org.springframework.security.config.annotation.web.builders.HttpSecurity;
	import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
	import org.springframework.security.crypto.password.PasswordEncoder;
	import org.springframework.security.web.SecurityFilterChain;

	import com.banking.service.Implementation.CustomerUserDetailsService;



	@Configuration
	@EnableMethodSecurity
	public class SpringSecurityConfiguration {
		
		@Autowired
		private CustomerUserDetailsService customerUserDetailsService;
		
		@Bean
	    public AuthenticationManager  authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception
	    {
			return authenticationConfiguration.getAuthenticationManager();
	    }
		
		@Bean
		public static PasswordEncoder passwordEncoder()
		{
				return new BCryptPasswordEncoder();
		}
		
		
		@Bean 
		SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
		{
			http.csrf().disable()
			    .authorizeHttpRequests((authorize) ->
			     authorize
			    // .requestMatchers(HttpMethod.GET,"/employee/**").permitAll()	
			   //  .requestMatchers("http://localhost:4200/").permitAll()
			     .requestMatchers("/api/v1/account/**").permitAll()		     					
			     .requestMatchers("/api/v1/transaction/**").permitAll()
			     .requestMatchers("/api/auth/**").permitAll()
			     .anyRequest().authenticated()
			     
			    		);
			return http.build();
		}


	}




