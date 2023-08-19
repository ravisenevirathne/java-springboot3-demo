package com.ravi;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SpringBootApplication
@RestController
@RequestMapping("api/v1/customers")
public class Main {

    private final CustomerRepository customerRepository;

    public Main(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    public static void main(String[] args) {

        SpringApplication.run(Main.class, args);
    }

    @GetMapping
    public List<Customer> getCustomers(){
        return customerRepository.findAll();
    }

    record NewCustomerRequest(
            String name,
            String email,
            Integer age

    ){
    }

    record UpdateCustomerRequest(
            Integer id,
            String name,
            String email,
            Integer age

    ){
    }

    @PostMapping
    public void addCustomer(@RequestBody NewCustomerRequest request){
        Customer customer = new Customer();
        customer.setName(request.name);
        customer.setAge(request.age);
        customer.setEmail(request.email);
        customerRepository.save(customer);
    }


    @DeleteMapping("{customerId}")
    public void deleteCustomer(@PathVariable("customerId") Integer id){
        customerRepository.deleteById(id);
    }

    @PutMapping("{customerId}")
    public void updateCustomer(@PathVariable("customerId") Integer id,@RequestBody UpdateCustomerRequest updaterequest){
       // customerRepository.findById(id);
        Customer customer_update = new Customer();
        customer_update.setId(id);
        customer_update.setName(updaterequest.name);
        customer_update.setAge(updaterequest.age);
        customer_update.setEmail(updaterequest.email);
        customerRepository.save(customer_update);
        //customer.setId(id);
        //customerRepository.save(customer);
    }

    @GetMapping("/greet")
    public GreetResponse greet(){
        GreetResponse response = new GreetResponse(
             "Hello",
            List.of("Java","Go","JScript"),
            new Person("Alex",28,1000)
        );
        return response;
    }

    record Person (String name, int age, double savings){}

    record GreetResponse(
        String greet,
        List<String> ProgLang,
        Person person){}

}
