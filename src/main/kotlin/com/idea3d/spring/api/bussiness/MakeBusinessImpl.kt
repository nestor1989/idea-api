package com.idea3d.spring.api.bussiness

import com.idea3d.spring.api.dao.MakeRepository
import com.idea3d.spring.api.model.Make
import com.idea3d.spring.api.model.PaginatedResponse
import com.idea3d.spring.api.model.Pagination
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import java.util.*

@Service
class MakeBusinessImpl(private val makeRepository: MakeRepository) : iMakeBusiness {

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

    override fun deleteMakeById(id: Int) {
        if (!makeRepository.existsById(id)) {
            throw NoSuchElementException("Cannot delete. Make not found with ID $id")
        }
        makeRepository.deleteById(id)
    }
}