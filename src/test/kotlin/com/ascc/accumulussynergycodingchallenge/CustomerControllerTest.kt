package com.ascc.accumulussynergycodingchallenge

import com.ascc.accumulussynergycodingchallenge.Customer.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import io.mockk.every

@WebMvcTest(CustomerController::class)
class CustomerControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    private lateinit var customerService: CustomerService

    @Test
    fun `should submit toppings`() {
        val email = "test@example.com"
        val toppings = listOf("Pepperoni", "Mushrooms")

        mockMvc.perform(post("/api/customers/submit")
            .param("email", email)
            .param("toppings", *toppings.toTypedArray()))
            .andExpect(status().isOk)
    }

    @Test
    fun `should get toppings summary`() {
        val summary = mapOf("Pepperoni" to 2L, "Mushrooms" to 2L)

        mockMvc.perform(get("/api/customers/toppings"))
            .andExpect(status().isOk)
    }
}
