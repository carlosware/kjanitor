/*
 * This file was generated by the Gradle 'init' task.
 *
 * This generated file contains a sample Java application project to get you started.
 * For more details take a look at the 'Building Java & JVM projects' chapter in the Gradle
 * User Manual available at https://docs.gradle.org/6.7/userguide/building_java_projects.html
 */
plugins {
    // Apply the application plugin to add support for building a CLI application in Java.
    application
    id("com.github.johnrengelman.shadow") version "6.1.0"
}

repositories {
    // Use JCenter for resolving dependencies.
    jcenter()
    mavenCentral()
}

dependencies {
    annotationProcessor("org.immutables:value:2.8.2")
    annotationProcessor("com.google.code.findbugs:jsr305:3.0.2")
    implementation("com.google.code.findbugs:jsr305:3.0.2")
    implementation("com.beust:jcommander:1.78")
    implementation("com.anaptecs.jeaf.owalibs:org.apache.log4j:4.3.1")
    implementation("org.immutables:value:2.8.2")
    implementation("com.fasterxml.jackson:jackson-bom:2.11.3")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.11.3")
    implementation("com.fasterxml.jackson.core:jackson-annotations:2.11.3")
    implementation("org.apache.kafka:kafka-clients:2.7.0")

    // Use JUnit test framework.
    testImplementation("junit:junit:4.13")
    testImplementation("info.batey.kafka:kafka-unit:1.0")
}

application {
    // Define the main class for the application.
    mainClassName = "com.carlosware.kjanitor.KJanitorApp"
}
