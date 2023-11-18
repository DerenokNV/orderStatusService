plugins {
	java
	id("org.springframework.boot") version "3.1.5"
	id("io.spring.dependency-management") version "1.1.3"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

dependencies {
	//implementation("org.springframework.boot:spring-boot-starter")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.kafka:spring-kafka")
	compileOnly("org.projectlombok:lombok")
	annotationProcessor("org.projectlombok:lombok")

	//implementation("com.fasterxml.jackson.core:jackson-databind:2.10.1")

	testImplementation("org.springframework.boot:spring-boot-starter-test")
	//testImplementation("org.springframework.kafka:spring-kafka-test")
}

/*tasks.withType<Test> {
	useJUnitPlatform()
}*/

tasks.bootBuildImage {
	builder.set("paketobuildpacks/builder-jammy-base:latest")
}
