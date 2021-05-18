package me.ginzburggeorgy.gamesinfoservice.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Company not found by Id")
class EntryNotFoundException : RuntimeException()