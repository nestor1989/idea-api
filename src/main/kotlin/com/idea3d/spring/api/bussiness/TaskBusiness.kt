package com.idea3d.spring.api.bussiness

import com.idea3d.spring.api.dao.TaskRepository
import com.idea3d.spring.api.exceptions.BusinessException
import com.idea3d.spring.api.exceptions.NotFoundException
import com.idea3d.spring.api.model.Task
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.Optional

@Service
class TaskBusiness: iTaskBusiness {

    @Autowired
    val taskRepository: TaskRepository? = null

    @Throws(BusinessException::class)
    override fun list(): List<Task> {
        try {
            return taskRepository!!.findAll()
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }

    @Throws(BusinessException::class, NotFoundException::class)
    override fun load(idTask: Long): Task {
        val op: Optional<Task>

        try {
            op = taskRepository!!.findById(idTask)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }

        if(!op.isPresent){
            throw NotFoundException("No se encontró la tarea solicitada")
        }

        return op.get()
    }

    @Throws(BusinessException::class)
    override fun save(task: Task): Task {
        try {
            return taskRepository!!.save(task)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }

    @Throws(BusinessException::class, NotFoundException::class)
    override fun remove(idTask: Long) {
        val op: Optional<Task>

        try {
            op = taskRepository!!.findById(idTask)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }

        if(!op.isPresent){
            throw NotFoundException("No se encontró la tarea solicitada")
        }

        try {
            taskRepository!!.deleteById(idTask)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }
}