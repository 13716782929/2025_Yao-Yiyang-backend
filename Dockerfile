# 使用OpenJDK基础镜像
FROM openjdk:17-jdk-slim

# 设置工作目录
WORKDIR /app

# 复制Gradle包装器和构建文件
COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .
COPY src src

# 授权gradlew为可执行文件
RUN chmod +x ./gradlew

# 使用Gradle构建项目
RUN ./gradlew build -x test

# 将构建的jar包复制到镜像中
COPY build/libs/*.jar app.jar

# 暴露端口
EXPOSE 8080

# 运行应用程序
ENTRYPOINT ["java", "-jar", "app.jar"]
