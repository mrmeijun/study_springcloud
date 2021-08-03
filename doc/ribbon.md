### Spring Cloud Ribbon : 负载均衡的服务调用

> Spring Cloud Ribbon 是Spring Cloud Netflix 子项目的核心组件之一，主要给服务间调用及API网关转发提供负载均衡的功能，本文将对其用法进行详细介绍。

#### Ribbon简介

> 在微服务架构中，很多微服务都会部署多个，其它服务去调用改服务的时候，如何保证负载均衡？负载均衡可以增加系统的可用性和扩展性，当我们使用RestTemplate来调用其它服务，Ribbon可以很方便的实现负载均衡功能。

#### RestTemplate的使用

> RestTemplate是一个HTTP客户端，使用它我们可以方便的调用HTTP接口，支持GET、POST、PUT、DELETE等方法。

#### 创建一个user-service模块

user_service 作为被调用服务

- 添加依赖

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
</dependency>
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
</dependency>
```

- 添加配置application.yml

```yaml
server:
  port: 9000

spring:
  application:
    name: user_service

eureka:
  client:
    fetch-registry: true
    register-with-eureka: false
    service-url: http://localhost:10000/eureka/

```

- 创建用户controller

```java
@Slf4j
@RestController("/user")
public class UserController {

    @PostMapping("/create")
    public CommonResult<User> create(@RequestBody User user){
        log.info("创建用户"+user+"成功");
        return new CommonResult<>("操作成功", 200);
    }

}
```

#### 创建一个ribbon-service模块

- 引入依赖

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
<!--指定ribbon对应的版本号，否则导入依赖失败-->
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-ribbon</artifactId>
    <version>2.2.3.RELEASE</version>
</dependency>

<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
</dependency>
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <optional>true</optional>
</dependency>
```

- 添加配置application.yml

```yaml
server:
  port: 9001

spring:
  application:
    name: ribbon_service

eureka:
  client:
    fetch-registry: true
    register-with-eureka: true
    service-url: http://localhost:10000/eureka/
```

#### 使用@LoadBalanced注解赋予RestTemplate负载均衡的能力





