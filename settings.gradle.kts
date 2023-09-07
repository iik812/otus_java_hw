pluginManagement {
    val jgitver: String by settings
    val dependencyManagement: String by settings
    val springframeworkBoot: String by settings
    val johnrengelmanShadow: String by settings
    val jib: String by settings
    val protobufVer: String by settings

    plugins {
        id("fr.brouillard.oss.gradle.jgitver") version jgitver
        id("io.spring.dependency-management") version dependencyManagement
        id("org.springframework.boot") version springframeworkBoot
        id("com.github.johnrengelman.shadow") version johnrengelmanShadow
        id("com.google.cloud.tools.jib") version jib
        id("com.google.protobuf") version protobufVer
    }
}
include("hw01-gradle")
include("hw02-generics")
include("hw03-annotations")
include("hw04-gc:demo")
include("hw04-gc")
include("hw05-bc")
include("hw06-oop")
include("hw07-patterns")
include("hw08-io")
include("hw09-jdbc")
include("hw10-jpql:homework")
include("hw11-cache:homework")
include("hw12-webServer")
include("hw13-di:homework")
include("hw14-springJdbc")
include("hw15-concurrentCollections:QueueDemo")
include ("hw18-webflux-chat:datastore-service")