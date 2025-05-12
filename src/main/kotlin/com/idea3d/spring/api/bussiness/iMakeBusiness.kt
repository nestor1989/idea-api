package com.idea3d.spring.api.bussiness

import com.idea3d.spring.api.model.Make
import com.idea3d.spring.api.model.PaginatedResponse
import org.springframework.web.multipart.MultipartFile

interface iMakeBusiness {
    fun listMakes(page: Int, size: Int): PaginatedResponse<Make>
    fun findMakeById(id: Int): Make
    fun saveMake(make: Make): Make
    fun saveImage(image: MultipartFile): String
    fun deleteMakeById(id: Int)
}