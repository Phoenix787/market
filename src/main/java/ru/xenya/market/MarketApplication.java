package ru.xenya.market;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ru.xenya.market.backend.data.entity.User;
import ru.xenya.market.backend.repositories.UserRepository;

@SpringBootApplication
public class MarketApplication {

	public static void main(String[] args) {
		SpringApplication.run(MarketApplication.class, args);
	}

//	@Bean
//	public CommandLineRunner loadData(UserRepository customerRepository) {
//		return (args)->{
//			customerRepository.save(new User("bill@gates.com", "2222323232dfasdfdsf23232","Bill", "Gates", "admin", false));
//
//		};
//	}
}
