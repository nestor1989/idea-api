package com.idea3d.spring.api.bussiness

import com.idea3d.spring.api.dao.MakeRepository
import com.idea3d.spring.api.model.Make
import com.idea3d.spring.api.model.PaginatedResponse
import com.idea3d.spring.api.model.Pagination
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.io.Resource
import org.springframework.core.io.UrlResource
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.nio.file.StandardCopyOption
import java.util.*


@Service
class MakeBusinessImpl(private val makeRepository: MakeRepository) : iMakeBusiness {
    @Value("D:\\SpringBoot\\images")
    lateinit var uploadDir: String
    override fun listMakes(page: Int, size: Int): PaginatedResponse<Make> {
        val pageable = PageRequest.of(page-1, size)
        val pageResult = makeRepository.findAll(pageable)

        val pagination = Pagination(
            currentPage = page,
            pageSize = pageResult.size,
            totalItems = pageResult.totalElements,
            totalPages = pageResult.totalPages
        )

        println("Resultados: ${pageResult.content}")

        return PaginatedResponse(
            pagination = pagination,
            results = pageResult.content
        )
    }

    override fun findMakeById(id: Int): Make {
        return makeRepository.findById(id).orElseThrow { NoSuchElementException("Make not found with ID $id") }
    }

    override fun saveMake(make: Make): Make {
        return makeRepository.save(make)
    }
    override fun saveImage(image: MultipartFile): UrlResource {
        val uploadPath: Path = Paths.get(uploadDir)
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath)
        }

        val fileName: String? = image.originalFilename
        val filePath: Path = uploadPath.resolve(fileName!!)
        Files.copy(image.inputStream, filePath, StandardCopyOption.REPLACE_EXISTING)

        val finalPath: Path = Paths.get(uploadDir).resolve(filePath.toString())
        val resource = UrlResource(finalPath.toUri())

        return resource
    }




    override fun deleteMakeById(id: Int) {
        if (!makeRepository.existsById(id)) {
            throw NoSuchElementException("Cannot delete. Make not found with ID $id")
        }
        makeRepository.deleteById(id)
    }
}