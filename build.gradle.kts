plugins {
    id("java")
}

group = "ru.pablo"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    implementation("org.springframework.boot:spring-boot-starter-web:3.2.0")
    implementation("org.springframework.boot:spring-boot-starter-security:3.2.0")
    implementation("org.springframework.boot:spring-boot-starter-data-redis:3.2.0")
    implementation("org.springframework.session:spring-session-data-redis:3.2.0")


    implementation("org.postgresql:postgresql:42.6.0")

}

tasks.test {
    useJUnitPlatform()
}