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
				id = 1,
				userId = "user123",
				userProfileImage = "https://example.com/images/profile1.png",
				imageLink = "https://example.com/images/make1.png",
				title = "Proyecto 1",
				description = "Descripción del proyecto 1.",
				createdAt = System.currentTimeMillis(),
				updatedAt = System.currentTimeMillis(),
				ranking = 4
			),
			Make(
				id = 2,
				userId = "user456",
				userProfileImage = "https://example.com/images/profile2.png",
				imageLink = "https://example.com/images/make2.png",
				title = "Proyecto 2",
				description = "Descripción del proyecto 2.",
				createdAt = System.currentTimeMillis() - 86400000, // Hace un día
				updatedAt = System.currentTimeMillis(),
				ranking = 3
			),
			Make(
				id = 3,
				userId = "user789",
				userProfileImage = "https://example.com/images/profile3.png",
				imageLink = "https://example.com/images/make3.png",
				title = "Proyecto 3",
				description = null, // Sin descripción
				createdAt = System.currentTimeMillis() - 172800000, // Hace dos días
				updatedAt = System.currentTimeMillis(),
				ranking = 5
			)
		)
	}

}



fun main(args: Array<String>) {
	runApplication<ApiApplication>(*args)
}
