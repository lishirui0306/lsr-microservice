# lsr-microservice
Cloud分布式服务
lsr-common          -- 公共组件
lsr-order-provider  -- 服务调用者
lsr-redis-lock      -- redis分布式锁
lsr-user-consumer   -- 服务消费者

所有服务均注册到consul 上，通过rest调用。

lsr-user-consumer 依赖lsr-common ，lsr-redis-lock
通过注入redis配置类，行进交互。




