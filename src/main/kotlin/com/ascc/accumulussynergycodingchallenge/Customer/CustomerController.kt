package com.ascc.accumulussynergycodingchallenge.Customer

import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/customers")
class CustomerController(private val customerService: CustomerService) {

    @PostMapping("/submit")
    fun submitToppings(@RequestParam email: String, @RequestParam toppings: List<String>) {
        customerService.saveCustomer(email, toppings)
    }

    @GetMapping("/toppings")
    fun getToppingsSummary(): Map<String, Long> {
        return customerService.getToppingsSummary()
    }
}