package dev.sburrestdemo.repository

import dev.sburrestdemo.entity.Coffee
import org.springframework.data.repository.CrudRepository

interface CoffeeRepository : CrudRepository<Coffee, Long>