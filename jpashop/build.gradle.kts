plugins {
id 'org.springframework.boot' version '2.4.1'
id 'io.spring.dependency-management' version '1.0.10.RELEASE'
id 'java'
}
group = 'jpabook'
version = '0.0.1-SNAPSHOT'
sourceCompatibility = '11'
configurations {
compileOnly {
extendsFrom annotationProcessor
}
}
repositories {
mavenCentral()
}
dependencies {
implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
implementation 'org.springframework.boot:spring-boot-starter-validation'
implementation 'org.springframework.boot:spring-boot-starter-thymeleaf'
implementation 'org.springframework.boot:spring-boot-starter-web'
implementation 'org.springframework.boot:spring-boot-devtools'

implementation 'com.github.gavlyukovskiy:p6spy-spring-boot-starter:1.5.6'


compileOnly 'org.projectlombok:lombok'
runtimeOnly 'com.h2database:h2'
annotationProcessor 'org.projectlombok:lombok'
testImplementation 'org.springframework.boot:spring-boot-starter-test'
//JUnit4 추가
testImplementation("org.junit.vintage:junit-vintage-engine") {
exclude group: "org.hamcrest", module: "hamcrest-core"
}
}
test {
useJUnitPlatform()
}
//JUnit4 추가
testImplementation("org.junit.vintage:junit-vintage-engine") {
 exclude group: "org.hamcrest", module: "hamcrest-core"
}