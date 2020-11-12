/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.CustomerService;

import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Dinujaya
 */

@CrossOrigin
@RestController
public class CustomerController {
    
       @Autowired
    private CustomerRepository customerRepository;
    
  @GetMapping("/customers")
  List<Customer> all() {
    return customerRepository.findAll();
  }
  
      @PostMapping(path = "/customers")
    public Customer createEmployee(@RequestBody Customer customer) {
        
         System.out.println(customer);
        return customerRepository.save(customer);
    }
  
  
    @GetMapping(path = "/customers/{id}")
    public List<Customer> getAdmin(@PathVariable Integer id) {
        return customerRepository.findByAdminId(id);
    }
    
    String Email;
    String status;
    Customer customer;
    
    
    
    
    @PostMapping(path = "/login")
    public HashMap<String, String> loginEmployee(@RequestParam("email") String email,@RequestParam("password") String password){
    
         System.out.println(email);
         System.out.println(password);
        
        customer = customerRepository.loginAdmin(email, password);
        
        System.out.println(customer);
    if( customer.getEmail() == null ? email == null : customer.getEmail().equals(email)){
        
        status =  "success";
        HashMap<String, String> map;
             map = new HashMap<>();
        
        map.put("message", "successfully ");
        map.put("status", "true");
        map.put("error", "no");
        map.put("id", String.valueOf(customer.getId()));
        map.put("email", customer.getEmail());
        map.put("name", customer.getName());
        
        System.out.println(map);
        
    return map;
    }
    
    else {  
           System.out.println("Error");
            HashMap<String, String> map2;
            map2 = new HashMap<>();
        
        map2.put("message", "login Fail.");
        map2.put("status", "false");
        map2.put("error", "no");
        
       status = "login fail";
     return map2;
    }
    
   }
    
}
