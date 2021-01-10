---
title: java
date: 2019-10-08 10:27:30
comments: false
---
## Java Basics
对于 C/C++ 的使用者来说，{% button /2019/11/21/java-basics/, Java Basics, fa-fw fa-lg %} 是很容易的，他们拥有相同的祖先，拥有近似的语法。相比较 C++ 而言，Java 删去了指针和内存管理等容易出错的特性，因而被戏称为 `C++--`，这带来了相当的好处，但也做了一些牺牲。**对于有 C++ 基础的程序员，经常对比思考 C++ 和 Java 对某种方式的实现差异，或许能带来对该技术更深层次的理解。**。

## Java Object Oriented Programming (OOP)
如果说 Java Basics 里面描述了一门编程语言应该有的样子，那么 {% button /2019/11/27/java-oop/, Java OOP, fa-fw fa-lg %} 无疑才是 Java 的亮点。 **相比于 C++，Java 是一种更纯粹的面向对象程序设计语言。我们应当能体会到：在 Java 中 (几乎) 一切都是对象。**

{% note info %}
尽管 C++ 和 Java 都是混合型语言。C++ 允许多种编程风格主要是因为它需要兼容过程型的 C 语言，但 Java 语言的设计者假设我们只进行面向对象程序设计。也就是说，我们在进行 Java 编程设计之前，最好将思维转换到面向对象的世界来。否则，你可能只是想逃避 C/C++ 程序编写的复杂，如果是这样的话，我推荐你使用 Python，因为确实 OOP 和 Java 不是每个人都需要的。
{% endnote %}

## Java Containers in Depth
容器的作用就是持有对象 (holding object)。几乎所有的 Java 大型程序，都需要与容器打交道，因为我们总是需要持有我们的对象。除了数组这种“固定大小的容器”，**Java 提供了一套相当完整的数据类来让我们组织我们的数据结构。** 学习熟悉这些类库的使用固然重要，但更重要的是挖掘容器背后深层的思想。通过阅读 Java 的源码，我们惊讶的发现，原来 {% button /2019/11/27/java-containers/, Java Containers, fa-fw fa-lg %} 背后是由复杂、优美而高效的数据结构来支撑的。

## Java I/O
对 IO 进行简单的编程是非常容易的，只需要几个类和方法就可以完成输入输出操作，但**高效、可移植的 IO 编程**非常困难，尤其是还需要处理很多不同的字符集，因此也导致了实际应用中 {% button /2019/10/10/java-io/, Java I/O, fa-fw fa-lg %} 成为了一个相当复杂的编程对象。

## Java Concurrency in Practice
{% button /2019/10/21/java-concurrency/, Java Concurrency, fa-fw fa-lg %}

## Java Virtual Machine (JVM)
{% button /2019/11/28/java-virtual-machine/, JVM, fa-fw fa-lg %}

## Networking in Java
如果说 Java I/O 和 Java Concurrency 令你头疼的话，那么现在 {% button /2019/10/22/java-networking/, Java Networking, fa-fw fa-lg %} 将会同时涉及这两种技术，不仅如此，Java 的网络编程还将涉及更多概念，包括网络协议，操作系统等等。但也别太沮丧，**Java 的网络编程仅涉及传输层及其以上层**，Java 将 C 语言实现的底层网络功能做了进一步**限制与封装**，提供了简单易用的 API。对于大部分场景，我们使用 UDP 或 TCP 协议 (或者基于 UDP 或 TCP 的协议) 已经足够，甚至我们可以使用成熟的网络框架 {% button /2019/11/18/netty/, Netty, fa-fw fa-lg %} 来进一步优化我们的工作。

{% note info %}
Java 的跨平台特性使得其不可能深入底层，对于网络套接字的限制我认为是一种明智的做法。如果程序员需要定制 Ping，实现网络嗅探等功能，那么直接使用 C 语言是更好的选择。反而，大多数情况我们都是在传输层以上工作，提供这些类库和框架已然足够。由于 Java 的网络库只是一种高级的封装，因此若想学好网络编程，请务必从操作系统和 C 语言开始。
{% endnote %}

## Java Related
A curated list of awesome Java frameworks, libraries and software.

### IDE
{% button https://www.jetbrains.com/help/idea/getting-started.html, IntelliJ IDEA, home fa-fw fa-lg %}

### Build
{% button https://maven.apache.org/guides/index.html, Maven, home fa-fw fa-lg %}
{% button https://docs.gradle.org/current/userguide/userguide.html, Gradle, home fa-fw fa-lg %}

- [Central Repository](https://search.maven.org/)

### JDK Tools
{% button https://docs.oracle.com/javase/8/docs/technotes/tools/, JDK 8 Tools and Utilities, home fa-fw fa-lg %}
{% button https://docs.oracle.com/en/java/javase/11/tools/, JDK 11 Tools Reference, home fa-fw fa-lg %}

### Logging
Logging Shims
- Apache Commons Logging
- SLF4J (Recommended) 

Logging Frameworks
- Java Util Logging
- Apache Log4J (Not Recommended)
- Logback
- Apache Log4J2

### Messaging

### Networking
Libraries for building network servers.
Dubbo - High-performance RPC framework.
Netty - Framework for building high-performance network applications.

### ORM
APIs that handle the persistence of objects.

### Server
Servers specifically used to deploy applications.
- Apache Tomcat - Robust, all-round server for Servlet and JSP.
- Jetty - Provides a Web server and javax.servlet container, plus support for HTTP/2, WebSocket, OSGi, JMX, JNDI, JAAS and many other integrations.

### Search
Engines that index documents for search and analysis.
- Elasticsearch - Distributed, multitenant-capable, full-text search engine with a RESTful web interface and schema-free JSON documents.

### Security
Libraries that handle security, authentication, authorization or session management.
- Apache Shiro - Performs authentication, authorization, cryptography and session management.


## Resources
### Awesome Lists
- [Awesome Java](https://github.com/akullpp/awesome-java)

### Influential Books
Books that made a big impact and are still worth reading.
- Core Java Volume I -- Fundamentals
- Core Java, Volume II -- Advanced Features
- Effective Java (3rd Edition)
- Java Concurrency in Practice
- Thinking in Java

### Websites
- [Design patterns implemented in Java](https://java-design-patterns.com/)

### 官方
- JSR 133
- [The Java Memory Model](http://www.cs.umd.edu/~pugh/java/memoryModel/)

### 面经
- [投行 Java 面试必问的多线程和并发问题](https://zhuanlan.zhihu.com/p/83978358)
- [Java 面试](https://zhuanlan.zhihu.com/p/84106079)