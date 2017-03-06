# spring-config-validation-issue

This small project demonstrates an issue with spring 1.5.2 and spring-config, when `spring-cloud-starter-config` is added to the classpath.

The test `com.example.DemoApplicationTests` only works when `spring-cloud-starter-config` is removed from the `pom.xml`.
