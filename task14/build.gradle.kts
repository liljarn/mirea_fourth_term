plugins {
    java
    id("org.springframework.boot") version "3.2.4"
    id("io.spring.dependency-management") version "1.1.4"
}

group = "ru.mirea"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    //implementation("org.springframework.boot:spring-boot-starter-log4j2")
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
    implementation("org.springframework.boot:spring-boot-starter-web")
    //implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")
    testImplementation("org.assertj:assertj-core:3.25.3")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.10.2")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.10.2")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.10.2")


    //implementation("org.hibernate:hibernate-entitymanager:5.6.15.Final")
    implementation("org.hibernate.orm:hibernate-core:6.4.4.Final")
    // https://mvnrepository.com/artifact/org.springframework/spring-orm
    implementation("org.springframework:spring-orm:6.1.6")
    implementation("com.zaxxer:HikariCP:5.1.0")

    // https://mvnrepository.com/artifact/org.postgresql/postgresql
    implementation("org.postgresql:postgresql:42.7.3")
    //implementation("org.apache.logging.log4j:log4j-core:2.23.0")
}

tasks.test {
    useJUnitPlatform()
}