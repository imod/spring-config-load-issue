# spring-config load issue

This small project demonstrates an issue with spring boot 1.5.2 and spring-config, when `spring-cloud-starter-config` is added to the classpath.

The test `com.example.DemoApplicationTests` shows that `com.example.config.MiscConfigInfo.afterPropertiesSet()` is called twice as soon as  `spring-cloud-starter-config` is added to the `pom.xml`.


Here is the full log (search for `--> MiscConfigInfo.afterPropertiesSet() called`):

```
  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::        (v1.5.2.RELEASE)

2017-03-07 14:35:24.619  INFO 11307 --- [           main] c.c.c.ConfigServicePropertySourceLocator : Fetching config from server at: http://localhost:8888
2017-03-07 14:35:24.701  WARN 11307 --- [           main] c.c.c.ConfigServicePropertySourceLocator : Could not locate PropertySource: I/O error on GET request for "http://localhost:8888/application/default": Connection refused (Connection refused); nested exception is java.net.ConnectException: Connection refused (Connection refused)
2017-03-07 14:35:24.707  INFO 11307 --- [           main] com.example.DemoApplicationTests         : No active profile set, falling back to default profiles: default
2017-03-07 14:35:24.711  INFO 11307 --- [           main] o.s.w.c.s.GenericWebApplicationContext   : Refreshing org.springframework.web.context.support.GenericWebApplicationContext@12bfd80d: startup date [Tue Mar 07 14:35:24 CET 2017]; parent: org.springframework.context.annotation.AnnotationConfigApplicationContext@7671cb68
2017-03-07 14:35:25.027  WARN 11307 --- [           main] o.s.c.a.ConfigurationClassPostProcessor  : Cannot enhance @Configuration bean definition 'refreshScope' since its singleton instance has been created too early. The typical cause is a non-static @Bean method with a BeanDefinitionRegistryPostProcessor return type: Consider declaring such methods as 'static'.
2017-03-07 14:35:25.114  INFO 11307 --- [           main] o.s.cloud.context.scope.GenericScope     : BeanFactory id=606b6425-6287-317d-96bc-426111e2d29e
2017-03-07 14:35:25.216  INFO 11307 --- [           main] trationDelegate$BeanPostProcessorChecker : Bean 'org.springframework.cloud.autoconfigure.ConfigurationPropertiesRebinderAutoConfiguration' of type [org.springframework.cloud.autoconfigure.ConfigurationPropertiesRebinderAutoConfiguration$$EnhancerBySpringCGLIB$$fcf29476] is not eligible for getting processed by all BeanPostProcessors (for example: not eligible for auto-proxying)
--> MiscConfigInfo.afterPropertiesSet() called
2017-03-07 14:35:25.743  INFO 11307 --- [           main] s.w.s.m.m.a.RequestMappingHandlerAdapter : Looking for @ControllerAdvice: org.springframework.web.context.support.GenericWebApplicationContext@12bfd80d: startup date [Tue Mar 07 14:35:24 CET 2017]; parent: org.springframework.context.annotation.AnnotationConfigApplicationContext@7671cb68
2017-03-07 14:35:25.814  INFO 11307 --- [           main] s.w.s.m.m.a.RequestMappingHandlerMapping : Mapped "{[/error]}" onto public org.springframework.http.ResponseEntity<java.util.Map<java.lang.String, java.lang.Object>> org.springframework.boot.autoconfigure.web.BasicErrorController.error(javax.servlet.http.HttpServletRequest)
2017-03-07 14:35:25.815  INFO 11307 --- [           main] s.w.s.m.m.a.RequestMappingHandlerMapping : Mapped "{[/error],produces=[text/html]}" onto public org.springframework.web.servlet.ModelAndView org.springframework.boot.autoconfigure.web.BasicErrorController.errorHtml(javax.servlet.http.HttpServletRequest,javax.servlet.http.HttpServletResponse)
2017-03-07 14:35:25.838  INFO 11307 --- [           main] o.s.w.s.handler.SimpleUrlHandlerMapping  : Mapped URL path [/webjars/**] onto handler of type [class org.springframework.web.servlet.resource.ResourceHttpRequestHandler]
2017-03-07 14:35:25.838  INFO 11307 --- [           main] o.s.w.s.handler.SimpleUrlHandlerMapping  : Mapped URL path [/**] onto handler of type [class org.springframework.web.servlet.resource.ResourceHttpRequestHandler]
2017-03-07 14:35:25.875  INFO 11307 --- [           main] o.s.w.s.handler.SimpleUrlHandlerMapping  : Mapped URL path [/**/favicon.ico] onto handler of type [class org.springframework.web.servlet.resource.ResourceHttpRequestHandler]
--> MiscConfigInfo.afterPropertiesSet() called
2017-03-07 14:35:31.140  INFO 11307 --- [           main] com.example.DemoApplicationTests         : Started DemoApplicationTests in 17.508 seconds (JVM running for 18.092)
2017-03-07 14:35:31.176  INFO 11307 --- [       Thread-4] o.s.w.c.s.GenericWebApplicationContext   : Closing org.springframework.web.context.support.GenericWebApplicationContext@12bfd80d: startup date [Tue Mar 07 14:35:24 CET 2017]; parent: org.springframework.context.annotation.AnnotationConfigApplicationContext@7671cb68
```


