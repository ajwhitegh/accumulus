package com.ascc.accumulussynergycodingchallenge

import com.ascc.accumulussynergycodingchallenge.Customer.*
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class CustomerServiceTest {

    private lateinit var customerRepository: CustomerRepository
    private lateinit var customerService: CustomerService

    @BeforeEach
    fun setUp() {
        customerRepository = mockk()
        customerService = CustomerService(customerRepository)
    }

    @Test
    fun `should save new customer`() {
        val email = "test@example.com"
        val toppings = listOf("Pepperoni", "Mushrooms")
        val customer = Customer(email = email, toppings = toppings)

        every { customerRepository.findByEmail(email) } returns null
        every { customerRepository.save(any()) } returns customer

        customerService.saveCustomer(email, toppings)

        verify { customerRepository.save(any()) }
    }

    @Test
    fun `should update existing customer`() {
        val email = "test@example.com"
        val toppings = listOf("Pepperoni", "Mushrooms")
        val existingCustomer = Customer(email = email, toppings = listOf("Cheese"))

        every { customerRepository.findByEmail(email) } returns existingCustomer
        every { customerRepository.save(any()) } returns existingCustomer

        customerService.saveCustomer(email, toppings)

        verify { customerRepository.save(existingCustomer) }
        assertEquals(toppings, existingCustomer.toppings)
    }

    @Test
    fun `should return toppings summary`() {
        val customers = listOf(
            Customer(email = "test1@example.com", toppings = listOf("Pepperoni", "Mushrooms")),
            Customer(email = "test2@example.com", toppings = listOf("Pepperoni")),
            Customer(email = "test3@example.com", toppings = listOf("Mushrooms"))
        )

        every { customerRepository.findAll() } returns customers

        val summary = customerService.getToppingsSummary()

        assertEquals(2, summary["Pepperoni"])
        assertEquals(2, summary["Mushrooms"])
    }
}
