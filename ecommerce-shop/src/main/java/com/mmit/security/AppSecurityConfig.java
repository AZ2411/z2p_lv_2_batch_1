package com.mmit.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class AppSecurityConfig {
	@Bean
	public PasswordEncoder passEnco() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
	@Bean
	public MyUserDetailService myUserDetailService() {
		return new MyUserDetailService();
	}
	@Bean
	public DaoAuthenticationProvider autProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setPasswordEncoder(passEnco());
		provider.setUserDetailsService(myUserDetailService());
		
		return provider;
	}
	
	@Bean
	public SecurityFilterChain filterChange(HttpSecurity http) throws Exception {
		http
		.csrf().disable()
		.authorizeRequests()
		.mvcMatchers(
				
				"/", 
				"/shop",
				"/cart/detail",
				"/products/detail",
				"/shop/products/{id}",
				"/register",
				"/login",
				"/css/**",
				"/app/**",
				"/fonts/**",
				"/images/**",
				"/js/**",
				"/scss/**",
				"/uploads/**",
				"/admin/css/**",
				"/admin/img/**",
				"/admin/js/**",
				"/admin/scss/**",
				"/admin/vendor/**",
				"/admin/vendor/bootstrap/**",
				"/admin/vendor/chart/**",
				"/admin/vendor/datatables/**",
				"/admin/vendor/fontawesome-free/**",
				"/admin/vendor/jquery/**",
				"/admin/vendor/jquery-easing/**",
				"/admin/homePage",
				"/webjars/bootstrap/css/bootstrap.min.css",
				"/admin/users/save",
				"/admin/assets/**",
				"/admin/css/**",
				"/admin/js/**",
				"/admin/ordersupdate/**"
				
				
				
				
				
				
				).permitAll()
		.antMatchers(
				"/products/edit/**",
				"/products/delete/**",
				"/admin/productsAdd",
				"/admin/productsSave",
				"/admin/productsTable",
				"/admin/productsDelete/**",
				"/admin/productsupdate/**",
				"/admin/categoriesTable",
				"/admin/categoriesAdd",
				"/admin/categoriesDelete/**",
				"/admin/categoriesupdate/**"
				
				
				
				).hasAnyRole("admin","merchant")
		.antMatchers(
				"/admin/usersTable",
				"/admin/usersDelete/**",
				"/admin/usersupdate/**"
				
				
				).hasRole("admin")
		.anyRequest()
		.authenticated()
		.and()
		.formLogin()
			.loginPage("/login")
			.permitAll()
		.and()
		.logout()
		.logoutSuccessUrl("/")
		.permitAll()
		.and()
		.exceptionHandling()
		.accessDeniedPage("/403");
		
		
		return http.build();
	}
}
