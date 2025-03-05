# Gradle과 JDK가 포함된 컨테이너에서 빌드 & 실행
FROM eclipse-temurin:21-jdk AS builder

WORKDIR /app
COPY . .

# Gradle 실행 권한 부여
RUN chmod +x ./gradlew

# 실행 가능한 JAR 파일 생성
RUN ./gradlew clean bootJar

# 실행 가능한 JAR만 복사
CMD ["java", "-jar", "./build/libs/order-0.0.1-SNAPSHOT.jar"]