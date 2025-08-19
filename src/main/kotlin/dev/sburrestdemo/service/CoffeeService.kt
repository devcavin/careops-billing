package dev.sburrestdemo.service

import dev.sburrestdemo.entity.AddCoffeeRequest
import dev.sburrestdemo.entity.Coffee
import dev.sburrestdemo.entity.CoffeeResponse
import dev.sburrestdemo.entity.UpdateCoffeeRequest
import dev.sburrestdemo.entity.toDTO
import dev.sburrestdemo.repository.CoffeeRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class CoffeeService(private val coffeeRepository: CoffeeRepository) {
    fun listOfCoffee() {
        coffeeRepository.findAll()
    }

    fun addCoffee(request: AddCoffeeRequest): CoffeeResponse {
        val savedCoffee = Coffee(
            name = request.name
        )

        return coffeeRepository.save(savedCoffee).toDTO()
    }

    fun byId(id: Long): CoffeeResponse? {
        return coffeeRepository.findByIdOrNull(id)?.toDTO()
    }

    fun removeCoffee(id: Long) {
        return coffeeRepository.deleteById(id)
    }

    fun modifyCoffee(id: Long, request: UpdateCoffeeRequest): CoffeeResponse {
        val updatedCoffee = Coffee(
            name = request.name
        )

        return coffeeRepository.save(updatedCoffee).toDTO()
    }
}