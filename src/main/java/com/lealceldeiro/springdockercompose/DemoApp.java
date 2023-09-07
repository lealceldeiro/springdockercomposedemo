package com.lealceldeiro.springdockercompose;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.annotation.Id;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@SpringBootApplication
public class DemoApp {
    public static void main(String[] args) {
        SpringApplication.run(DemoApp.class, args);
    }
}

@Controller
@ResponseBody
class CustomerHttpController {
    private final CustomerRepository customerRepository;

    CustomerHttpController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @GetMapping("/customers")
    Iterable<Customer> customers(@RequestParam(required = false) String name) {
        return name != null ? customerRepository.findAllByName(name)
                            : customerRepository.findAll();
    }
}

interface CustomerRepository extends CrudRepository<Customer, Integer> {
    Iterable<Customer> findAllByName(String name);
}

record Customer(@Id Integer id, String name) {
}
