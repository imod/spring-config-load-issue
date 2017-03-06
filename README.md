# spring-config-validation-issue

This small project demonstrates an issue with spring boot 1.5.2 and spring-config, when `spring-cloud-starter-config` is added to the classpath.

The test `com.example.DemoApplicationTests` only works when `spring-cloud-starter-config` is removed from the `pom.xml`.

There is an additional strange thing to discover in this setup: if I revove the annotations `@NotEmpty` and `@NotNull` in `com.example.config.MiscConfigInfo` then the app starts properly, but one can see that the same bean is created twice and ad the end the `Map<Integer, Boolean> kmsToWarning` will hold some very strange values. 
There is a simple log entry when an instance of `MiscConfigInfo` is finished with initializtion, I would expect:

```
listener     -->true
kmsToWarning -->{50=false, 10=true, 30=false}
``` 

to be in the logs once, but instead I see it twice and even with duplciated entries in the map(????):

```
listener     -->true
kmsToWarning -->{50=false, 10=true, 50=false, 30=false, 30=false, 10=true}
```

Here is the full log:

```
2017-03-06 14:50:56.101  INFO 64134 --- [           main] s.c.a.AnnotationConfigApplicationContext : Refreshing org.springframework.context.annotation.AnnotationConfigApplicationContext@140c9f39: startup date [Mon Mar 06 14:50:56 CET 2017]; root of context hierarchy
2017-03-06 14:50:56.344  INFO 64134 --- [           main] trationDelegate$BeanPostProcessorChecker : Bean 'configurationPropertiesRebinderAutoConfiguration' of type [org.springframework.cloud.autoconfigure.ConfigurationPropertiesRebinderAutoConfiguration$$EnhancerBySpringCGLIB$$5b3d86f8] is not eligible for getting processed by all BeanPostProcessors (for example: not eligible for auto-proxying)

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::        (v1.5.2.RELEASE)

2017-03-06 14:51:01.750  INFO 64134 --- [           main] c.c.c.ConfigServicePropertySourceLocator : Fetching config from server at: http://localhost:8888
2017-03-06 14:51:01.822  WARN 64134 --- [           main] c.c.c.ConfigServicePropertySourceLocator : Could not locate PropertySource: I/O error on GET request for "http://localhost:8888/application/default": Connection refused (Connection refused); nested exception is java.net.ConnectException: Connection refused (Connection refused)
2017-03-06 14:51:01.827  INFO 64134 --- [           main] com.example.DemoApplicationTests         : No active profile set, falling back to default profiles: default
2017-03-06 14:51:01.832  INFO 64134 --- [           main] o.s.w.c.s.GenericWebApplicationContext   : Refreshing org.springframework.web.context.support.GenericWebApplicationContext@435871cb: startup date [Mon Mar 06 14:51:01 CET 2017]; parent: org.springframework.context.annotation.AnnotationConfigApplicationContext@140c9f39
2017-03-06 14:51:01.855  INFO 64134 --- [           main] o.s.c.a.ConfigurationClassParser         : Properties location [classpath:user.properties] not resolvable: class path resource [user.properties] cannot be opened because it does not exist
2017-03-06 14:51:02.138  WARN 64134 --- [           main] o.s.c.a.ConfigurationClassPostProcessor  : Cannot enhance @Configuration bean definition 'refreshScope' since its singleton instance has been created too early. The typical cause is a non-static @Bean method with a BeanDefinitionRegistryPostProcessor return type: Consider declaring such methods as 'static'.
2017-03-06 14:51:02.221  INFO 64134 --- [           main] o.s.cloud.context.scope.GenericScope     : BeanFactory id=24c557d8-4a75-3ff1-82df-98493466a9a7
2017-03-06 14:51:02.304  INFO 64134 --- [           main] trationDelegate$BeanPostProcessorChecker : Bean 'org.springframework.cloud.autoconfigure.ConfigurationPropertiesRebinderAutoConfiguration' of type [org.springframework.cloud.autoconfigure.ConfigurationPropertiesRebinderAutoConfiguration$$EnhancerBySpringCGLIB$$5b3d86f8] is not eligible for getting processed by all BeanPostProcessors (for example: not eligible for auto-proxying)
listener     -->true
kmsToWarning -->{50=false, 10=true, 30=false}
2017-03-06 14:51:02.762  INFO 64134 --- [           main] s.w.s.m.m.a.RequestMappingHandlerAdapter : Looking for @ControllerAdvice: org.springframework.web.context.support.GenericWebApplicationContext@435871cb: startup date [Mon Mar 06 14:51:01 CET 2017]; parent: org.springframework.context.annotation.AnnotationConfigApplicationContext@140c9f39
2017-03-06 14:51:02.831  INFO 64134 --- [           main] s.w.s.m.m.a.RequestMappingHandlerMapping : Mapped "{[/error]}" onto public org.springframework.http.ResponseEntity<java.util.Map<java.lang.String, java.lang.Object>> org.springframework.boot.autoconfigure.web.BasicErrorController.error(javax.servlet.http.HttpServletRequest)
2017-03-06 14:51:02.832  INFO 64134 --- [           main] s.w.s.m.m.a.RequestMappingHandlerMapping : Mapped "{[/error],produces=[text/html]}" onto public org.springframework.web.servlet.ModelAndView org.springframework.boot.autoconfigure.web.BasicErrorController.errorHtml(javax.servlet.http.HttpServletRequest,javax.servlet.http.HttpServletResponse)
2017-03-06 14:51:02.855  INFO 64134 --- [           main] o.s.w.s.handler.SimpleUrlHandlerMapping  : Mapped URL path [/webjars/**] onto handler of type [class org.springframework.web.servlet.resource.ResourceHttpRequestHandler]
2017-03-06 14:51:02.855  INFO 64134 --- [           main] o.s.w.s.handler.SimpleUrlHandlerMapping  : Mapped URL path [/**] onto handler of type [class org.springframework.web.servlet.resource.ResourceHttpRequestHandler]
2017-03-06 14:51:02.896  INFO 64134 --- [           main] o.s.w.s.handler.SimpleUrlHandlerMapping  : Mapped URL path [/**/favicon.ico] onto handler of type [class org.springframework.web.servlet.resource.ResourceHttpRequestHandler]
listener     -->true
kmsToWarning -->{50=false, 10=true, 50=false, 30=false, 30=false, 10=true}
2017-03-06 14:51:08.194  INFO 64134 --- [           main] com.example.DemoApplicationTests         : Started DemoApplicationTests in 17.429 seconds (JVM running for 18.055)
2017-03-06 14:51:08.228  INFO 64134 --- [       Thread-4] o.s.w.c.s.GenericWebApplicationContext   : Closing org.springframework.web.context.support.GenericWebApplicationContext@435871cb: startup date [Mon Mar 06 14:51:01 CET 2017]; parent: org.springframework.context.annotation.AnnotationConfigApplicationContext@140c9f39

```


