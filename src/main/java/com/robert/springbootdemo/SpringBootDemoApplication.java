package com.robert.springbootdemo;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@SpringBootApplication
@RestController
@RequestMapping("api/v1/customers")
public class SpringBootDemoApplication {

	private final CustomerRepository customerRepository;

	public SpringBootDemoApplication(CustomerRepository customerRepository){
		this.customerRepository = customerRepository;
	}
	public static void main(String[] args) {
		SpringApplication.run(SpringBootDemoApplication.class, args);
	}

	@GetMapping
	public ResponseEntity<List<Customer>> getCustomers() {
		List<Customer> customers = customerRepository.findAll();
		return ResponseEntity.ok(customers);
	}

	@PostMapping
	public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {
		customerRepository.save(customer);
		return ResponseEntity.ok(customer);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Customer> deleteCustomer(@PathVariable Integer id) {
		Customer deletedCustomer = customerRepository.findById(id).get();
		customerRepository.delete(deletedCustomer);
		return ResponseEntity.ok(deletedCustomer);
	}

	@PutMapping("{id}")
	public ResponseEntity<Customer> updateMethodName(@PathVariable Integer id, @RequestBody Customer updatedCustomer) {
		updatedCustomer.setId(id);
		customerRepository.save(updatedCustomer);
		return ResponseEntity.ok(updatedCustomer);
	}
	

}
