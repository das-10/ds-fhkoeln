package de.thkoeln.bankaccount.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class Aplication {
	
	private static final Logger log = LoggerFactory.getLogger(Aplication.class);
	
	public static void main(String[] args){
		log.info("bankaccount persistence started");
		SpringApplication.run(Aplication.class);
	}
	
	@Bean
	public CommandLineRunner demo(CustomerRepository repository) {
		return (args) -> {
			// save a couple of customers
			Customer peter= repository.save(new Customer("Peter"));
			Customer michael= repository.save(new Customer("Michael"));
			Customer klaus = repository.save(new Customer("Klaus"));
			
			//Account peterAccount = repository.save(new Account(0186437, 50));
			
			// fetch all customers
			log.info("Customers found with findAll():");
			log.info("-------------------------------");
			for (Customer customer : repository.findAll()) {
				log.info(customer.toString());
			}
            log.info("");

			// fetch an individual customer by ID
			Customer customer = repository.findOne(1L);
			log.info("Customer found with findOne(1L):");
			log.info("--------------------------------");
			log.info(customer.toString());
            log.info("");

			// fetch customers by last name
			log.info("Customer found with findByLastName('Bauer'):");
			log.info("--------------------------------------------");
//			for (Customer bauer : repository.findByLastName("Bauer")) {
//				log.info(bauer.toString());
//			}
            log.info("");
		};
	}

}
