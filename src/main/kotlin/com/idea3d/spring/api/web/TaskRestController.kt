package com.idea3d.spring.api.web

import com.idea3d.spring.api.bussiness.iTaskBusiness
import com.idea3d.spring.api.model.Task
import com.idea3d.spring.api.utils.Constants
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(Constants.URL_BASE_TASK)
class TaskRestController {

    @Autowired
    val taskBusiness:iTaskBusiness? = null

    @GetMapping("")
    fun list(): ResponseEntity<List<Task>>{
        return try {
            ResponseEntity(taskBusiness!!.list(), HttpStatus.OK )
        }catch (e:Exception){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @GetMapping("/{id}")
    fun load(@PathVariable("id") idTask: Long): ResponseEntity<Task> {
        return try {
            ResponseEntity(taskBusiness!!.load(idTask), HttpStatus.OK)
        } catch (e: Exception) {
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        } catch (e: Exception) {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @PostMapping("")
    fun insert(@RequestBody task: Task): ResponseEntity<Any>{
        return try {
            taskBusiness!!.save(task)
            val responseHeader = HttpHeaders()
            responseHeader.set("location", Constants.URL_BASE_TASK + "/" + task.id)
            ResponseEntity(responseHeader, HttpStatus.CREATED)
        }catch (e:Exception){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @PutMapping("")
    fun update(@RequestBody task: Task): ResponseEntity<Any>{
        return try {
            taskBusiness!!.save(task)
            ResponseEntity(HttpStatus.OK)
        }catch (e:Exception){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable("id") idTask: Long):ResponseEntity<Any>{
        return try {
            taskBusiness!!.remove(idTask)
            ResponseEntity(HttpStatus.OK)
        } catch (e: Exception) {
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        } catch (e: Exception) {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }


}