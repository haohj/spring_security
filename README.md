# spring_security
spring security study
##异常处理
###自定义异常

### filter

可以拦截方法，但无法知道执行的类、方法及方法的具体参数

### interceptor

可以拦截方法并知道类、方法及参数类型，但是无法知道具体参数

### aspect

+ 可以拦截方法并知道类、方法及参数类型和参数具体的值
+ 可以配置拦截的url