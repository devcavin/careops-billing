package dev.sburrestdemo.entity

import jakarta.persistence.*
import jakarta.validation.constraints.*

@Entity
data class Coffee(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    @Column(nullable = false)
    var name: String
)

data class AddCoffeeRequest(
    @field:NotBlank(message = "Name cannot be blank")
    val name: String
)

data class CoffeeResponse(
    val id: Long,
    val name: String
)

data class UpdateCoffeeRequest(
    @field:NotBlank(message = "Name is required")
    val name: String
)

fun Coffee.toDTO(): CoffeeResponse {
    return CoffeeResponse(
        id = this.id,
        name = this.name
    )
}
