package ru.xenya.market;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ru.xenya.market.backend.data.Customer;
import ru.xenya.market.backend.repositories.CustomerRepository;
import ru.xenya.market.backend.repositories.OrderRepository;

@SpringBootApplication
public class MarketApplication {

	public static void main(String[] args) {
		SpringApplication.run(MarketApplication.class, args);
	}

//	@Bean
//	public CommandLineRunner loadData(CustomerRepository customerRepository, OrderRepository orderRepository) {
//		return (args)->{
//			customerRepository.save(new Customer("Bill Gates"));
//			customerRepository.save(new Customer("Mark Zuckerman"));
//			customerRepository.save(new Customer("Paul Marcus"));
//			customerRepository.save(new Customer("Jeff Brightman"));
//		};
//	}
}
