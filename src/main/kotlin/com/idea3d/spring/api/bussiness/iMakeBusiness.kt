package com.idea3d.spring.api.bussiness

import com.idea3d.spring.api.model.Make
import com.idea3d.spring.api.model.PaginatedResponse

interface iMakeBusiness {
    fun listMakes(page: Int, size: Int): PaginatedResponse<Make>
    fun findMakeById(id: Int): Make
    fun saveMake(make: Make): Make
    fun deleteMakeById(id: Int)
}