plugins {
	java
	id("org.springframework.boot") version "3.5.3"
	id("io.spring.dependency-management") version "1.1.7"
	 id("org.flywaydb.flyway") version "9.22.3" 
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(24)
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("io.github.cdimascio:dotenv-java:3.0.0")
	implementation("software.amazon.awssdk:s3:2.25.30")
	implementation("software.amazon.awssdk:auth:2.25.30")
	implementation("software.amazon.awssdk:regions:2.25.30")
	implementation("net.sf.jasperreports:jasperreports:7.0.3")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("io.jsonwebtoken:jjwt-api:0.11.5")
    runtimeOnly("io.jsonwebtoken:jjwt-impl:0.11.5")
    runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.11.5") 
	implementation("org.modelmapper:modelmapper:3.1.1")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	implementation("org.flywaydb:flyway-core:10.11.0")
	implementation("org.flywaydb:flyway-mysql") 
    runtimeOnly("com.mysql:mysql-connector-j") 
	runtimeOnly("org.postgresql:postgresql")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
