package com.idea3d.spring.api.web

import com.idea3d.spring.api.bussiness.iMakeBusiness
import com.idea3d.spring.api.model.Make
import com.idea3d.spring.api.utils.Constants
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping(Constants.URL_BASE_MAKE)
class MakeRestController(private val makeBusiness: iMakeBusiness) {

    @GetMapping
    fun listAllMakes(): ResponseEntity<List<Make>> {
        return try {
            val makes = makeBusiness.listMakes()
            ResponseEntity.ok(makes)
        } catch (e: Exception) {
            // Error genérico: se devuelve un estado HTTP 500
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()
        }
    }

    @GetMapping("/{id}")
    fun getMakeById(@PathVariable id: Int): ResponseEntity<Make> {
        return try {
            val make = makeBusiness.findMakeById(id)
            ResponseEntity.ok(make) // Retorna un 200 OK con el objeto encontrado
        } catch (e: NoSuchElementException) {
            // Si no se encuentra: 404 Not Found
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(null)
        } catch (e: Exception) {
            // Otro error: 500 Internal Server Error
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null)
        }
    }

    @PostMapping
    fun createMake(@RequestBody make: Make): ResponseEntity<Make> {
        return try {
            val createdMake = makeBusiness.saveMake(make)
            ResponseEntity.status(HttpStatus.CREATED).body(createdMake) // 201 Created
        } catch (e: Exception) {
            // Error genérico: 500 Internal Server Error
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null)
        }
    }

    @PutMapping("/{id}")
    fun updateMake(@PathVariable id: Int, @RequestBody make: Make): ResponseEntity<Make> {
        return try {
            if (id != make.id) {
                // No coincide el ID con el de la URL: Bad Request 400
                throw ResponseStatusException(HttpStatus.BAD_REQUEST, "El ID de la URL no coincide con el del objeto")
            }
            val updatedMake = makeBusiness.saveMake(make)
            ResponseEntity.ok(updatedMake) // 200 OK
        } catch (e: IllegalArgumentException) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null) // 400 Bad Request
        } catch (e: Exception) {
            // Error genérico: 500 Internal Server Error
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null)
        }
    }

    @DeleteMapping("/{id}")
    fun deleteMake(@PathVariable id: Int): ResponseEntity<Unit> {
        return try {
            makeBusiness.deleteMakeById(id)
            ResponseEntity.noContent().build() // 204 No Content
        } catch (e: NoSuchElementException) {
            // Si el ID no existe: 404 Not Found
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(null)
        } catch (e: Exception) {
            // Otro error: 500 Internal Server Error
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null)
        }
    }
}