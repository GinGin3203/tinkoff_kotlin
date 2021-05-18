package me.ginzburggeorgy.gamesinfoservice.api

import me.ginzburggeorgy.gamesinfoservice.exceptions.EntryNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice
class ErrorController : ResponseEntityExceptionHandler() {
    @ExceptionHandler(value = [(EntryNotFoundException::class)])
    fun handleUserAlreadyExists(ex: EntryNotFoundException, request: WebRequest): ResponseEntity<String> {
        val message = ex.message ?: "Not Found"
        return ResponseEntity(message, HttpStatus.NOT_FOUND)
    }
}
