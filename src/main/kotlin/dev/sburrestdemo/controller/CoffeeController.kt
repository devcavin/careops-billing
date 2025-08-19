package dev.sburrestdemo.controller

import dev.sburrestdemo.entity.AddCoffeeRequest
import dev.sburrestdemo.entity.CoffeeResponse
import dev.sburrestdemo.entity.UpdateCoffeeRequest
import dev.sburrestdemo.service.CoffeeService
import jakarta.validation.*
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/coffees")
class CoffeeController(private val coffeeService: CoffeeService) {

    // GET a list of coffee
    @GetMapping
    fun coffeeList() {
        return coffeeService.listOfCoffee()
    }

    // POST coffee
    @PostMapping @ResponseStatus(HttpStatus.CREATED)
    fun createCoffee(@Valid @RequestBody request: AddCoffeeRequest): CoffeeResponse {
        return coffeeService.addCoffee(request)
    }

    @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteCoffee(@Valid @PathVariable id: Long) {
        return coffeeService.removeCoffee(id)
    }

    @GetMapping("/{id}")
    fun coffeeById(@Valid @PathVariable id: Long): CoffeeResponse? {
        return coffeeService.byId(id)
    }

    @PatchMapping("/{id}")
    fun updateCoffee(@Valid @PathVariable id: Long, @RequestBody request: UpdateCoffeeRequest): CoffeeResponse {
        return coffeeService.modifyCoffee(id, request)
    }
}