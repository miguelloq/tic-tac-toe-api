package com.example.modules.task.route

import com.example.modules.task.model.Task
import com.example.modules.task.model.Task.Priority
import com.example.modules.task.repository.TaskRepository
import io.ktor.http.HttpStatusCode
import io.ktor.serialization.JsonConvertException
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Routing
import io.ktor.server.routing.application
import io.ktor.server.routing.delete
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.route
import org.koin.ktor.ext.inject

fun Routing.taskRoute() = route("/tasks") {
    val taskRepo by application.inject<TaskRepository>()

    get {
        val tasks = taskRepo.allTasks()
        call.respond(tasks)
    }

    get("/byName/{taskName}") {
        val name = call.parameters["taskName"]
        if (name == null) {
            call.respond(HttpStatusCode.BadRequest)
            return@get
        }
        val task = taskRepo.taskByName(name)
        if (task == null) {
            call.respond(HttpStatusCode.NotFound)
            return@get
        }
        call.respond(task)
    }

    get("/byPriority/{priority}") {
        val priorityAsText = call.parameters["priority"]
        if (priorityAsText == null) {
            call.respond(HttpStatusCode.BadRequest)
            return@get
        }
        try {
            val priority = Priority.valueOf(priorityAsText)
            val tasks = taskRepo.tasksByPriority(priority)


            if (tasks.isEmpty()) {
                call.respond(HttpStatusCode.NotFound)
                return@get
            }
            call.respond(tasks)
        } catch (ex: IllegalArgumentException) {
            call.respond(HttpStatusCode.BadRequest)
        }
    }

    post {
        try {
            val task = call.receive<Task>()
            taskRepo.addTask(task,1)
            call.respond(HttpStatusCode.NoContent)
        } catch (ex: IllegalStateException) {
            call.respond(HttpStatusCode.BadRequest)
        } catch (ex: JsonConvertException) {
            call.respond(HttpStatusCode.BadRequest)
        }
    }

    delete("/{taskName}") {
        val name = call.parameters["taskName"]
        if (name == null) {
            call.respond(HttpStatusCode.BadRequest)
            return@delete
        }
        if (taskRepo.removeTask(name)) {
            call.respond(HttpStatusCode.NoContent)
        } else {
            call.respond(HttpStatusCode.NotFound)
        }
    }

}

