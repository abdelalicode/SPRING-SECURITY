package com.secure.secureAPP;

import com.secure.secureAPP.auth.AuthenticationService;
import com.secure.secureAPP.auth.RegisterRequest;
import com.secure.secureAPP.user.Role;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import static com.secure.secureAPP.user.Role.ADMIN;
import static com.secure.secureAPP.user.Role.EMPLOYEE;

@SpringBootApplication
public class SecureAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecureAppApplication.class, args);
	}


	@Bean

	public CommandLineRunner commandLineRunner(
			AuthenticationService authenticationService
	) {
		return args -> {
			var admin = RegisterRequest.builder()
					.firstName("admin")
					.lastName("admin")
					.email("admin@admin.admin")
					.password("admin123")
					.role(ADMIN)
					.build();
			System.out.println("Admin Token: " + authenticationService.register(admin).getToken());


			var employee = RegisterRequest.builder()
					.firstName("employee")
					.lastName("employee")
					.email("employee@employee.employee")
					.password("employee123")
					.role(EMPLOYEE)
					.build();
			System.out.println("Employee Token: " + authenticationService.register(employee).getToken());
		};
	}

}
