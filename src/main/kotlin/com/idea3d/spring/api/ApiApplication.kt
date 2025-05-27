package com.idea3d.spring.api

import com.idea3d.spring.api.dao.MakeRepository
import com.idea3d.spring.api.model.Make
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ApiApplication: CommandLineRunner{

	@Autowired
	val makeRepository: MakeRepository? = null

	override fun run(vararg args: String?) {
		val makes = mockMakesList()
		makeRepository!!.save(makes[0])
		makeRepository!!.save(makes[1])
		makeRepository!!.save(makes[2])
	}

	fun mockMakesList(): List<Make> {
		return listOf(
			Make(
				id = 0, // Será autogenerado
				userId = "user123",
				userImageProfile = "https://example.com/images/profile1.png",
				imageLink = "/uploads/images/make1.png",
				title = "Proyecto 1",
				description = "Descripción del proyecto 1.",
				createdAt = System.currentTimeMillis(),
				updatedAt = System.currentTimeMillis()
			),
			Make(
				id = 0, // Será autogenerado
				userId = "user456",
				userImageProfile = "https://example.com/images/profile2.png",
				imageLink = "/uploads/images/make2.png",
				title = "Proyecto 2",
				description = "Descripción del proyecto 2.",
				createdAt = System.currentTimeMillis() - 86400000, // Hace un día
				updatedAt = System.currentTimeMillis()
			),
			Make(
				id = 0, // Será autogenerado
				userId = "user789",
				userImageProfile = "https://example.com/images/profile3.png",
				imageLink = "/uploads/images/make3.png",
				title = "Proyecto 3",
				description = "okkskds", // Sin descripción
				createdAt = System.currentTimeMillis() - 172800000, // Hace dos días
				updatedAt = System.currentTimeMillis()
			)
		)
	}

}



fun main(args: Array<String>) {
	runApplication<ApiApplication>(*args)
}
