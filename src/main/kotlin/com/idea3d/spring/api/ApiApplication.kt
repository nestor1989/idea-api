package com.idea3d.spring.api

import com.idea3d.spring.api.dao.TaskRepository
import com.idea3d.spring.api.model.Task
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ApiApplication: CommandLineRunner{

	@Autowired
	val taskRepository: TaskRepository? = null

	override fun run(vararg args: String?) {
		val task = Task(name = "Una tarea más", description = "re díficl de hacer")
		taskRepository!!.save(task)
	}
}



fun main(args: Array<String>) {
	runApplication<ApiApplication>(*args)
}
