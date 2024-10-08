package com.ascc.accumulussynergycodingchallenge.Customer

import org.springframework.data.jpa.repository.JpaRepository

interface CustomerRepository : JpaRepository<Customer, Long> {
    fun findByEmail(email: String): Customer?
}