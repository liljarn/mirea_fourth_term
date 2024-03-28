plugins {
    id("java")
}

group = "ru.edu.mirea"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // https://mvnrepository.com/artifact/org.projectlombok/lombok
    compileOnly("org.projectlombok:lombok:1.18.30")
    // https://mvnrepository.com/artifact/org.assertj/assertj-core
    testImplementation("org.assertj:assertj-core:3.25.3")
    // https://mvnrepository.com/artifact/org.junit.jupiter/junit-jupiter-api
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.10.2")
    // https://mvnrepository.com/artifact/org.junit.jupiter/junit-jupiter-engine
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.10.2")
    // https://mvnrepository.com/artifact/org.junit.jupiter/junit-jupiter-params
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.10.2")
    // https://mvnrepository.com/artifact/org.apache.logging.log4j/log4j-core
    implementation("org.apache.logging.log4j:log4j-core:2.23.0")
}

tasks.jar {
    manifest {
        attributes(
            mapOf(
                "Main-Class" to "ru.edu.mirea.task9.Hello"
            )
        )
    }
    finalizedBy("copyJarToDockerDirectory");
}

tasks.register<Copy>("copyJarToDockerDirectory") {
    from("build/libs/MireaTerm4-1.0-SNAPSHOT.jar")
    into("${rootDir}/src/main/java/ru/edu/mirea/task9/")
    finalizedBy("buildDockerImage");
}

tasks.register<Exec>("buildDockerImage") {
    dependsOn("copyJarToDockerDirectory")
    commandLine("docker", "build", "-t", "mirea", "./src/main/java/ru/edu/mirea/task9/")
}

tasks.test {
    useJUnitPlatform()
}