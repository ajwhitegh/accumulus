package com.ascc.accumulussynergycodingchallenge.Customer

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CustomerService(private val customerRepository: CustomerRepository) {

    @Transactional
    fun saveCustomer(email: String, toppings: List<String>) {
        val existingCustomer = customerRepository.findByEmail(email)
        if (existingCustomer != null) {
            existingCustomer.toppings = toppings
            customerRepository.save(existingCustomer)
        } else {
            val newCustomer = Customer(email = email, toppings = toppings)
            customerRepository.save(newCustomer)
        }
    }

    fun getToppingsSummary(): Map<String, Long> {
        val allCustomers = customerRepository.findAll()
        return allCustomers.flatMap { it.toppings }
            .groupingBy { it }
            .eachCount()
            .mapValues { it.value.toLong() }
    }
}