package com.idea3d.spring.api.bussiness

import com.idea3d.spring.api.dao.MakeRepository
import com.idea3d.spring.api.model.Make
import org.springframework.stereotype.Service
import java.util.*

@Service
class MakeBusinessImpl(private val makeRepository: MakeRepository) : iMakeBusiness {

    override fun listMakes(): List<Make> {
        return makeRepository.findAll()
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