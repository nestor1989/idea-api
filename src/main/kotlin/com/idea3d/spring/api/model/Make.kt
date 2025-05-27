package com.idea3d.spring.api.model

import jakarta.persistence.*

@Entity
data class Make(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int,
    val userId: String,
    val userImageProfile: String,
    val imageLink: String,
    val title: String,
    val description: String,
    val createdAt: Long?,
    val updatedAt: Long?,
    val ranking: Int = 0,

    )
