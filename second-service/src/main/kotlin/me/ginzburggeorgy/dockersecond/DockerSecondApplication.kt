package me.ginzburggeorgy.dockersecond

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class DockerSecondApplication

fun main(args: Array<String>) {
	runApplication<DockerSecondApplication>(*args)
}
