# spring_security
spring security study
##异常处理
###自定义异常
+ 新建一个异常类继承RuntimeException
+ 新建一个异常处理类，在类上使用注解@ControllerAdvice
+ 在异常处理类中写一个异常处理方法。在方法的@ExceptionHandler注解中写上异常类，例如：@ExceptionHandler(UserNotExistException.class)
+ 在方法的参数中传入异常类，当其他地方抛出此异常时就会调用此异常处理方法，用参入的异常类就可以拿到异常类中的属性

### filter
+ Filter在只在 Servlet 前后起作用。
+ Filters 通常将 请求和响应（request/response） 当做黑盒子，Filter 通常不考虑servlet 的实现。
+ Filter 是在 Servlet 规范中定义的，是 Servlet 容器支持的。不能够使用 Spring 容器资源
### interceptor
可以拦截方法并知道类、方法及参数类型，但是无法知道具体参数
+ 拦截器能够深入到方法前后、异常抛出前后等，因此拦截器的使用具有更大的弹性。允许用户介入（hook into）请求的生命周期，在请求过程中获取信息，Interceptor 通常和请求更加耦合
+ 拦截器既可以用于Web程序，也可以用于Application、Swing程序中
+ 拦截器是在 Spring容器内的，是Spring框架支持的,是一个Spring的组件，归Spring管理，配置在Spring文件中，因此能使用Spring里的任何资源、对象，例如 Service对象、数据源、事务管理等，通过IoC注入到拦截器即可

### aspect

+ 可以拦截方法并知道类、方法及参数类型和参数具体的值
+ 可以配置拦截的url