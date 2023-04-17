package com.idea3d.spring.api.bussiness

import com.idea3d.spring.api.model.Task

interface iTaskBusiness {

    fun list(): List<Task>
    fun load(idTask:Long): Task
    fun save (task: Task): Task
    fun remove(idTask: Long)

}