package com.idea3d.spring.api.model

import jakarta.persistence.*

@Entity
@Table(name = "task")
data class Task(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long = 0,
    var name: String,
    var description: String
    /*var id_status: Int? = null,
    var status: String? = null,
    var prioritize: Boolean = false,
    var client: String? = null,
    var id_client: Int? = null,
    var price: Float? = null,
    var cost: Float? = null,
    var thing_photo: String?=null,
    var thing_extension: String?=null,
    var client_photo: String?=null,
    var client_ext:String? = null,
    var date_begin: String? = null,
    var date_end: String? = null*/
)
