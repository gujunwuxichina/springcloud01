package com.gujun.servicezuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/*
    在Spring Cloud微服务中，一种常见的负载均衡方式是，客户端的请求首先经过负载均衡（zuul、Nginx)，再到达服务网关（zuul集群），
    然后再到达具体的服务；服务统一注册到高可用的服务注册中心集群，服务的所有配置文件由配置服务管理(后有)，配置服务的配置文件放在git仓库，方便开发人员随时更改配置；

    Zuul介绍:
    zuul主要功能是路由转发和过滤器，路由功能是微服务的一部分，如/api/usr转发到user服务，/api/shop转发到shop服务；
    zuul默认和Ribbon结合实现了负载均衡的功能；

    服务过滤：

 */

/*
    （Springboot书介绍）
    路由网关——Zuul:
        网关功能对于分布式应用十分重要，可以将请求转发到真实的服务器上，进而保护真实服务器的IP地址；
        也可以作为一种负载均衡的手段；
        还能提供过滤器，过滤器作用可以判断请求是否为有效请求，若无效则阻止请求，避免发送到真实服务器，降低服务器压力；
     在Spring Cloud的组件中，Zuul是支持API网关开发的组件，Zuul来自NetFlix的开源网关；

     过滤器：
        有时还需要网关除了转发请求外，还需要提供更多功能，如:检测用户登录、黑名单、防止恶意刷新攻击等；
        如果过滤器判断请求失败，则不会转发到微服务，以保护微服务；
        Zuul中存在一个抽象类ZuulFilter,方法：
            shouldFilter(),返回boolean,若为true,则执行该过滤器的run();
            run(),过滤器逻辑，是过滤器的核心；
            filterType(),过滤器类型，返回字符串：
                pre,请求执行之前filter;
                route,处理请求，进行路由；
                post,请求处理完成后执行的filter;
                error,出现错误时执行的filter;
            filterOrder(),指定过滤器顺序，返回的int越小优先级越高；

 */

@SpringBootApplication
@EnableEurekaClient
/*
    开启zuul功能，查看@EnableZuulProxy注解的源码，可以发现其已经引入断路机制，防止在请求不到时，会进行断路，以避免网关发生请求无法
    释放的场景，导致微服务瘫痪；
 */
@EnableZuulProxy
public class ServicezuulApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServicezuulApplication.class, args);
    }

}
