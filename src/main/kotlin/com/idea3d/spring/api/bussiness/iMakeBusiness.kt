package com.idea3d.spring.api.bussiness

import com.idea3d.spring.api.model.Make

interface iMakeBusiness {
    fun listMakes(): List<Make>
    fun findMakeById(id: Int): Make
    fun saveMake(make: Make): Make
    fun deleteMakeById(id: Int)
}