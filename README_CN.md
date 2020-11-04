# JetCache框架

## 1. 简介
JetCache是一个基于Java的缓存系统封装，提供统一的API和注解来简化缓存的使用。 JetCache提供了比SpringCache更加强大的注解，可以原生的支持TTL、两级缓存、分布式自动刷新，还提供了`Cache`接口用于手工缓存操作。 当前有四个实现，`RedisCache`、`TairCache`（此部分未在github开源）、`CaffeineCache`(in memory)和一个简易的`LinkedHashMapCache`(in memory)，要添加新的实现也是非常简单的。
### 1.1 全部特性

- 通过统一的API访问Cache系统
- 通过注解实现声明式的方法缓存，支持TTL和两级缓存
- 通过注解创建并配置`Cache`实例
- 针对所有`Cache`实例和方法缓存的自动统计
- Key的生成策略和Value的序列化策略是可以配置的
- 分布式缓存自动刷新，分布式锁 (2.2+)
- 异步Cache API (2.2+，使用Redis的lettuce客户端时)
- Spring Boot支持
## 2. 体验总结

- 框架使用注解实现方法缓存，相比Spring Cache多了TTL和两级缓存（还可以直接对类的成员变量进行缓存，demo中没有体现）
- 提供对缓存进行自动统计

![image.png](https://cdn.nlark.com/yuque/0/2020/png/2162474/1604475526839-7c4e63cf-560b-4d75-8c68-7e60e9c15940.png#align=left&display=inline&height=154&margin=%5Bobject%20Object%5D&name=image.png&originHeight=154&originWidth=1371&size=28807&status=done&style=none&width=1371)

- 自带的支持ava，kryo序列化器，早期版本自带fastjson序列化器，但是兼容性有问题就移除了。因此我自行配置了fastjson序列化器
- 设置本地缓存上限，防止占用过多内存。经过测试本地缓存抛弃原则是将最久没有使用的抛弃。
- 最大的缺陷就是不支持多个节点之间的本地缓存同步。A节点更新数据后B节点无法立即得到更新后的数据。
## 参考资料
[JetCache Wiki](https://github.com/alibaba/jetcache/wiki/Home_CN)  
[SpringBoot 缓存技术实战](https://juejin.im/post/6844904151730618375#heading-5)
