package com.ascc.accumulussynergycodingchallenge.Customer

import jakarta.persistence.*

@Entity
@Table(uniqueConstraints = [UniqueConstraint(columnNames = ["email"])]) //enforce unique emails for data integrity
data class Customer(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    @Column(unique = true)
    val email: String,
    @ElementCollection
    var toppings: List<String>
)
