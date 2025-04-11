package com.idea3d.spring.api.dao

import com.idea3d.spring.api.model.Make
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MakeRepository : JpaRepository<Make, Int>
