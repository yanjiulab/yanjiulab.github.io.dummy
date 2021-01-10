---
title: cloud computing
date: 2019-10-07 19:28:27
comments: false
---

# 虚拟化
## 硬件虚拟化 (Hypervisors)
硬件虚拟化 (Hardware Virtualization or Hypervisors) 可以按照虚拟化的程度不同分为两种类型。
- type-1 hypervisor
- type-2 hypervisor

类型|实现
---|---
Type-2 or hosted hypervisors|Xen, VMware ESXi (formerly ESX)
Type-1, native or bare-metal hypervisors|Parallels Desktop for Mac, QEMU, VirtualBox, VMware Player and VMware Workstation

这两种类型之间的区别并不总是很清楚。例如，Linux 的基于内核的虚拟机（KVM）和 FreeBSD 的 bhyve 是内核模块，可以有效地将主机操作系统转换为 type-1 hypervisor。同时，由于 Linux 发行版和 FreeBSD 仍然是通用操作系统，并且应用程序在争夺 VM 资源方面相互竞争，因此 KVM 和 bhyve 也可以归为 type-2 hypervisor。

来源：[Hypervisor/Virtual Machine Monitor (VMM)](https://en.wikipedia.org/wiki/Hypervisor) 

### KVM
KVM 全称是 Kernel-Based Virtual Machine。也就是说 KVM 是基于 Linux 内核实现的。KVM有一个内核模块叫 kvm.ko，只用于管理虚拟 CPU 和内存。

那 IO 的虚拟化，比如存储和网络设备由谁实现呢？这个就交给 Linux 内核和 Qemu 来实现。说白了，作为一个 Hypervisor，KVM 本身只关注虚拟机调度和内存管理这两个方面。IO 外设的任务交给 Linux 内核和 Qemu。

Libvirt
大家在网上看 KVM 相关文章的时候肯定经常会看到 Libvirt 这个东西。Libvirt 是啥？简单说就是 KVM 的管理工具。

其实，Libvirt 除了能管理 KVM 这种 Hypervisor，还能管理 Xen，VirtualBox 等。OpenStack 底层也使用 Libvirt，所以很有必要学习一下。

Libvirt 包含 3 个东西：
- 后台 daemon 程序 libvirtd、
- API 库
- 命令行工具 virsh

libvirtd 是服务程序，接收和处理 API 请求；

API 库使得其他人可以开发基于 Libvirt 的高级工具，比如 virt-manager，这是个图形化的 KVM 管理工具，后面我们也会介绍；

virsh 是我们经常要用的 KVM 命令行工具，后面会有使用的示例。

作为 KVM 和 OpenStack 的实施人员，virsh 和 virt-manager 是一定要会用的。

KVM 虚拟化实现
- CPU 虚拟化：一个 KVM 虚机在宿主机中其实是一个 qemu-kvm 进程，与其他 Linux 进程一样被调度。虚机中的每一个虚拟 vCPU 则对应 qemu-kvm 进程中的一个线程
- 内存虚拟化：VA -- PA -- MA
- 存储虚拟化：KVM 的存储虚拟化是通过存储池（Storage Pool）和卷（Volume）来管理的。
    - 目录型 SP：Volume 是目录中的一个文件
    - LVM 型 SP
    - KVM 还支持 iSCSI，Ceph 等多种类型的 Storage Pool
- 网络虚拟化


## 操作系统虚拟化 (OS-level Virtualization)
> OS-level virtualization refers to an operating system paradigm in which the kernel allows the existence of multiple isolated user space instances. Such instances, called containers (Solaris, Docker), Zones (Solaris), virtual private servers (OpenVZ), partitions, virtual environments (VEs), virtual kernels (DragonFly BSD), or jails (FreeBSD jail or chroot jail), may look like real computers from the point of view of programs running in them. 

## Container
### Docker
- [Docker](/2019/07/05/pro-docker/)

## Kubernetes

### 核心概念
Kubernetes（k8s）是自动化容器操作的开源平台，这些操作包括部署，调度和节点集群间扩展。

## Network Virtualization
### NFV 
#### NFV Concepts
Terminology|Explanation
---|---
Network Function (NF)| Functional building block with a well defined interfaces and well defined functional behavior 
Virtualized Network Function (VNF)| Software implementation of NF that can be deployed in a virtualized infrastructure
VNF Set| Connectivity between VNFs is not specified, e.g., residential gateways
VNF Forwarding Graph| Service chain when network connectivity order is important, e.g., firewall, NAT, load balancer
NFV Infrastructure (NFVI)| Hardware and software required to deploy, mange and execute VNFs including computation, networking, and storage.
NFV Orchestrator| Automates the deployment, operation, management, coordination of VNFs and NFV
NFVI Point of Presence (PoP)| Location of NFVI
NFVI-PoP Network| Internal network
Transport Network| Network connecting a PoP to other PoPs or external networks
VNF Manager| VNF lifecycle management e.g., instantiation, update, scaling, query, monitoring, fault diagnosis, healing, termination
Virtualized Infrastructure Manager| Management of computing, storage, network, software resources
Network Service| A composition of network functions and defined by its functional and behavioral specification
NFV Service| A network services using NFs with at least one VNF.
User Service| Services offered to end users/customers/subscribers.
Deployment Behavior| NFVI resources that a VNF requires, e.g., Number of VMs, memory, disk, images, bandwidth, latency
Operational Behavior| VNF instance topology and lifecycle operations, e.g., start, stop, pause, migration, …
VNF Descriptor| Deployment behavior + Operational behavior

#### NFV Framework Requirements
1. General
2. Portability
3. Performance
4. Elasticity (灵活性、弹性):	Scalable	to	meet	SLAs.	Movable	to	other	servers.
5. Resiliency (弹性): Be	able	to	recreate	after	failure.
Specified	packet	loss	rate,	calls	drops,	time	to	recover,	etc.
6. Security
7. Service Continuity
8. Service Assurance
9. Energy Efficiency Requirements
10. Operational and Management Requirements
11. Transition
12. Service Models


# OpenStack
## OpenStack 架构
- Nova：管理 VM 的生命周期，是 OpenStack 中最核心的服务。
- Neutron：为 OpenStack 提供网络连接服务，负责创建和管理L2、L3 网络，为 VM 提供虚拟网络和物理网络连接。
- Glance：管理 VM 启动镜像，Nova 创建 VM 时将使用 Glance 提供的镜像。
- Cinder：为 VM 提供块存储服务。Cinder 提供的每一个 Volume 在 VM 看来就是一块虚拟硬盘，一般用作数据盘。
- Swift：提供对象存储服务。VM 可以通过 RESTful API 存放对象数据。作为可选的方案，Glance 可以将镜像存放在 Swift 中；Cinder 也可以将 Volume 备份到 Swift 中。
- Keystone：为 OpenStack 的各种服务提供认证和权限管理服务。简单的说，OpenStack 上的每一个操作都必须通过 Keystone 的审核。
- Ceilometer：提供 OpenStac k监控和计量服务，为报警、统计或计费提供数据。
- Horizon：为 OpenStack 用户提供一个 Web 的自服务 Portal。

## 部署拓扑
OpenStack 是一个分布式系统，由若干不同功能的节点（Node）组成：
- 控制节点（Controller Node） 管理 OpenStack，其上运行的服务有 Keystone、Glance、Horizon 以及 Nova 和 Neutron 中管理相关的组件。 控制节点也运行支持 OpenStack 的服务，例如 SQL 数据库（通常是 MySQL）、消息队列（通常是 RabbitMQ）和网络时间服务 NTP。
- 网络节点（Network Node） 其上运行的服务为 Neutron。 为 OpenStack 提供 L2 和 L3 网络。 包括虚拟机网络、DHCP、路由、NAT 等
- 存储节点（Storage Node） 提供块存储（Cinder）或对象存储（Swift）服务。
- 计算节点（Compute Node） 其上运行 Hypervisor（默认使用 KVM）。 同时运行 Neutron 服务的 agent，为虚拟机提供网络支持。

最小双机部署：
1. devstack-controller：控制节点 + 网络节点 + 块存储节点 + 计算节点
2. devstack-compute：计算节点

网络上规划了三个网络：
- Management Network：用于 OpenStack 内部管理用，比如各服务之间通信。 这里使用 eth0
- VM（Tenant）Network：OpenStack 部署的虚拟机所使用的网络。 OpenStack 支持多租户（Tenant），虚机是放在 Tenant 下的，所以叫 Tenant Network。 这里使用 eth1
- External Network：一般来说，Tenant Network 是内部私有网络，只用于 VM 之间通信，与其他非 VM 网络是隔离的。 这里我们规划了一个外部网络（External Network），通过 devstak-controller 的 eth2 连接。 Neutron 通过 L3 服务让 VM 能够访问到 External Network。 对于公有云，External Network 一般指的是 Internet。 对于企业私有云，External Network 则可以是 Intranet 中的某个网络。


## Keystone
- User
- Project

## Glance 

## Nova
对于 Nova，这些服务会部署在两类节点上：计算节点和控制节点。

计算节点上安装了 Hypervisor，上面运行虚拟机。 由此可知：
1. 只有 nova-compute 需要放在计算节点上。
2. 其他子服务则是放在控制节点上的。

## 通用设计准则
- api
- scheduler
- worker
- driverf
- message queue
- database

# Neutron
Neutron 为整个 OpenStack 环境提供网络支持，包括二层交换，三层路由，负载均衡，防火墙和 VPN 等。Neutron 提供了一个灵活的框架，通过配置，无论是开源还是商业软件都可以被用来实现这些功能。

## 逻辑架构
Neutron 由如下组件构成：

Neutron Server
对外提供 OpenStack 网络 API，接收请求，并调用 Plugin 处理请求。

Plugin
处理 Neutron Server 发来的请求，维护 OpenStack 逻辑网络状态， 并调用 Agent 处理请求。

Agent
处理 Plugin 的请求，负责在 network provider 上真正实现各种网络功能。

network provider
提供网络服务的虚拟或物理网络设备，例如 Linux Bridge，Open vSwitch 或者其他支持 Neutron 的物理交换机。

Queue
Neutron Server，Plugin 和 Agent 之间通过 Messaging Queue 通信和调用。

Database
存放 OpenStack 的网络状态信息，包括 Network, Subnet, Port, Router 等。




## 物理部署
方案1：控制节点 + 计算节点
在这个部署方案中，OpenStack 由控制节点和计算节点组成。

- 控制节点 部署的服务包括：neutron server, core plugin 的 agent 和 service plugin 的 agent。
- 计算节点 部署 core plugin 的agent，负责提供二层网络功能。

方案2：控制节点 + 网络节点 + 计算节点

## Neutron Server
归纳起来，Neutron Server 包括两部分：
1. 提供 API 服务。
2. 运行 Plugin。

即 Neutron Server = API + Plugins

## Service Plugin/Agent
Core Plugin/Agent 负责管理核心实体：net, subnet 和 port。而对于更高级的网络服务，则由 Service Plugin/Agent 管理。
- DHCP - dhcp agent 通过 dnsmasq 为 instance 提供 dhcp 服务。
- Routing - l3 agent 可以为 project（租户）创建 router，提供 Neutron subnet 之间的路由服务。路由功能默认通过 IPtables 实现。
- Firewall - l3 agent 可以在 router 上配置防火墙策略，提供网络安全防护。另一个与安全相关的功能是 Security Group，也是通过 IPtables 实现。 Firewall 与 Security Group 的区别在于：
    - Firewall 安全策略位于 router，保护的是某个 project 的所有 network。
    - Security Group 安全策略位于 instance，保护的是单个 instance。
- Load Balance - Neutron 默认通过 HAProxy 为 project 中的多个 instance 提供 load balance 服务。

## 网络类型
### local 网络
1. 位于同一 local network 的 instance 可以通信。
2. 位于不同 local network 的 instance 无法通信。
3. 一个 local network 只能位于一个物理节点，无法跨节点。

### flat 网络
每个 flat network 都会独占一个物理网卡。

## 安全组
安全组有以下特性：
1. 通过宿主机上 iptables 规则控制进出 instance 的流量。
2. 安全组作用在 instance 的 port 上。
3. 安全组的规则都是 allow，不能定义 deny 的规则。
4. instance 可应用多个安全组叠加使用这些安全组中的规则。

## 虚拟防火墙
FWaaS 有三个重要概念：Firewall、Policy 和 Rule。

- Firewall - 租户能够创建和管理的逻辑防火墙资源。Firewall 必须关联某个 Policy，因此必须先创建 Policy。
- Firewall Policy - Policy 是 Rule 的集合，Firewall 会按顺序应用 Policy 中的每一条 Rule。
- Firewall Rule - Rule 是访问控制规则，由源与目的子网 IP、源与目的端口、协议、allow 或 deny 动作组成。例如，我们可以创建一条 Rule，允许外部网络通过 ssh 访问租户网络中的 instance，端口为 22。

安全组的应用对象是虚拟网卡，由 L2 Agent 实现，比如 neutron_openvswitch_agent 和 neutron_linuxbridge_agent。安全组会在计算节点上通过 iptables 规则来控制进出 instance 虚拟网卡的流量。也就是说：安全组保护的是 instance。

FWaaS 的应用对象是 router，可以在安全组之前控制外部过来的流量，但是对于同一个 subnet 内的流量不作限制。也就是说：FWaaS 保护的是 subnet。

同时我们也发现，firewall rule 对进出流量同时生效，不区分方向。

相同点：
1. 底层都是通过 iptables 实现。

不同点：
1. FWaaS 的 iptables 规则应用在 router 上，保护整个租户网络；安全组则应用在虚拟网卡上，保护单个 instance。
2. FWaaS 可以定义 allow 或者 deny 规则；安全组只能定义 allow 规则。

## LBaaS
LBaaS 有三个主要的概念：
Pool Member，Pool 和 Virtual IP

Pool Member
Pool Member 是 layer 4 的实体，拥有 IP 地址并通过监听端口对外提供服务。
例如 Pool Member 可以是一个 web server，IP 为 172.16.100.9 并通过 80 端口提供 HTTP 服务。

Pool
Pool 由一组 Pool Member 组成。
这些 Pool Member 通常提供同一类服务。
例如一个 web server pool，包含：
web1：172.16.100.9：80
web2：172.16.100.10：80

Virtual IP
Virtual IP 也称作 VIP，是定义在 load balancer 上的 IP 地址。
每个 pool member 都有自己的 IP，但对外服务则是通过 VIP。

load balancer 负责监听外部的连接，并将连接分发到 pool member。
外部 client 只知道 VIP，不知道也不需要关心是否有 pool 或者有多少个 pool member。

OpenStack Neutron 目前默认通过 HAProxy 软件来实现 LBaaS。
HAProxy 是一个流行的开源 load balancer。
Neutron 也支持其他一些第三方 load balancer。


1. Load Balance Method 是为新连接选择 member 的方法
2. Session Persistence 是为同一个 client 的后续连接选择 member 的方法

LBaaS 可以创建 monitor，用于监控 Pool Member 健康状态。



# 大型网站系统
## 架构演进
- 用 Java 和单机构建的交易网站
- 单机负载过大 - 数据库与应用分离
- 应用服务器负载过大 - 应用服务器集群化
    - 引入负载均衡
    - 解决 session 问题
        - session sticky
        - session replication
        - session 集中存储
        - cookie based session
- 数据读取压力大 - 读写分离
    - 采用数据库作为读库
    - 使用 search cluster 构建索引
    - 缓存 cache cluster
- 关系型数据库不足 - 分布式存储系统
- 单个（主）数据库瓶颈
    - 专库专用，垂直拆分
    - 水平拆分
- 应用方面的挑战
    - 根据业务特性拆分应用
    - 服务化系统结构。业务之间的功能不再是方法调用，引入远程服务调用
- 消息中间件！！ (Message Oriented Middleware, MOM)

## Java 中间件
- 三个领域的中间件
    - RPC 和对象访问中间件
    - 消息中间件
    - 数据访问中间件
- Java 中间件基础知识
    - JVM
    - 垃圾回收与内存堆布局
    - Java 并发编程的类、接口、方法
        - 线程池
        - synchronized
        - ReentrantLock
        - volatile
        - Atomics
        - wait, notify, notifyAll
        - CountDownLatch
        - CyclicBarrier 
        - Semaphore 利用 acquire and release 控制并发量
        - Exchanger 两个线程间数据交换
        - Future and FutureTask 暂时不需要的数据，不应该傻傻的等待，开一个执行线程，等需要的时候再 get
        - 并发容器 CopyOnWrite 以及 Concurrent* 类
    - 动态代理
    - 反射
    - 网络通信实现 (BIO, NIO, AIO)
        - 同步阻塞IO (BIO)
        - 同步非阻塞IO (NIO)
        - 异步阻塞IO ()
- 分布式系统中的 Java 中间件

## 服务框架
- 网站功能丰富后的应对
- 服务框架的设计与实现
    - 数据库从单机到分布式的挑战和应对方式
    - 服务调用端的设计实现
    - 服务提供端的调用实现
    - 服务升级
- 实战优化

## 数据访问层
- 数据库从单机到分布式的挑战和应对方式 
- 数据访问层的设计与实现
    - 如何对外提供数据访问层的功能
        - 对外提供数据访问层的方式
            - 专有 API
            - 数据层本身作为一个 JDBC 的实现
            - 基于 ORM 或类 ORM 接口的方式
        - 不同方式在合并查询的对比
    - 数据层流程


# 系统设计 (System Design)
- https://www.zhihu.com/question/26312148

# OpenStack
- [OpenStack Neutron 架构指南](https://cloud.tencent.com/developer/article/1083332)
- [OpenStack Neutron概述](https://zhuanlan.zhihu.com/p/31045898)
- [Neutron 理解](https://www.cnblogs.com/sammyliu/p/4622563.html)
- [OpenStack Architecture Design Guide](https://docs.openstack.org/arch-design/index.html)
- [Welcome to Neutron’s documentation!](https://docs.openstack.org/neutron/latest/)
- [Welcome to Glance’s documentation!](https://docs.openstack.org/glance/latest/)

# 云计算

## 云计算模型
每种类型的云计算都提供不同级别的控制、灵活性和管理，因此您可以根据需要选择正确的服务集。 
- 基础设施即服务 (IaaS)
- 平台即服务 (PaaS)
- 软件即服务 (SaaS)

参考资料：
- [IaaS，PaaS，SaaS 的区别](http://www.ruanyifeng.com/blog/2017/07/iaas-paas-saas.html)
- [SaaS vs PaaS vs IaaS: What’s The Difference and How To Choose](https://www.bmc.com/blogs/saas-vs-paas-vs-iaas-whats-the-difference-and-how-to-choose/)
- [What is CaaS?](https://www.redhat.com/en/topics/cloud-computing/what-is-caas)

## 云计算部署模型
- 云
- 混合
- 本地

## VPC
VPC的全称是 Virtual Private Cloud，中文有的叫专有网络，有的叫私有网络，核心就是能让你购买的一台或多台机器（或其它资源），能划分到一个私网中，与其它用户隔离，也可以把自己的多台资源的机器隔离（如果你需要的话）。VPC是随着云计算而产生的一种技术，是为了解决传统网络在云环境中无法满足的功能、安全性、灵活性而生的。

- [What Is a Virtual Private Cloud (VPC)?](https://www.cloudflare.com/learning/cloud/what-is-a-virtual-private-cloud/)

# 分布式存储

## 块存储 vs 文件存储 vs 对象存储
- [File storage, block storage, or object storage?](https://www.redhat.com/en/topics/data-storage/file-block-object-storage)
- [块存储、文件存储、对象存储这三者的本质差别是什么？](https://www.zhihu.com/question/21536660)

# MQ
## RabbitMQ
- 什么是 mq？
采用 AMQP 高级消息队列协议的一种消息队列技术,最大的特点就是消费并不需要确保提供方存在,实现了服务之间的高度解耦

- 为什么需要 mq？
（1）在分布式系统下具备异步,削峰,负载均衡等一系列高级功能;（2）拥有持久化的机制，进程消息，队列中的信息也可以保存下来。（3）实现消费者和生产者之间的解耦。（4）对于高并发场景下，利用消息队列可以使得同步访问变为串行访问达到一定量的限流，利于数据库的操作。（5）可以使用消息队列达到异步下单的效果，排队中，后台进行逻辑下单。

- 使用 rabbitmq 的场景
（1）服务间异步通信
（2）顺序消费
（3）定时任务
（4）请求削峰

- 4、如何确保消息正确地发送至 RabbitMQ？ 如何确保消息接收方消费了消息？
发送方确认模式将信道设置成 confirm 模式（发送方确认模式），则所有在信道上发布的消息都会被指派一个唯一的 ID。一旦消息被投递到目的队列后，或者消息被写入磁盘后（可持久化的消息），信道会发送一个确认给生产者（包含消息唯一 ID）。如果 RabbitMQ 发生内部错误从而导致消息丢失，会发送一条 nack（notacknowledged，未确认）消息。发送方确认模式是异步的，生产者应用程序在等待确认的同时，可以继续发送消息。当确认消息到达生产者应用程序，生产者应用程序的回调方法就会被触发来处理确认消息。

接收方确认机制消费者接收每一条消息后都必须进行确认（消息接收和消息确认是两个不同操作）。只有消费者确认了消息，RabbitMQ 才能安全地把消息从队列中删除。这里并没有用到超时机制，RabbitMQ 仅通过 Consumer 的连接中断来确认是否需要重新发送消息。也就是说，只要连接不中断，RabbitMQ 给了 Consumer 足够长的时间来处理消息。保证数据的最终一致性；下面罗列几种特殊情况（1）如果消费者接收到消息，在确认之前断开了连接或取消订阅，RabbitMQ 会认为消息没有被分发，然后重新分发给下一个订阅的消费者。（可能存在消息重复消费的隐患，需要去重）（1）2如果消费者接收到消息却没有确认消息，连接也未断开，则 RabbitMQ 认为该消费者繁忙，将不会给该消费者分发更多的消息。

5.如何避免消息重复投递或重复消费？在消息生产时，MQ 内部针对每条生产者发送的消息生成一个 inner-msg-id，作为去重的依据（消息投递失败并重传），避免重复的消息进入队列；在消息消费时，要求消息体中必须要有一个 bizId（对于同一业务全局唯一，如支付 ID、订单 ID、帖子 ID 等）作为去重的依据，避免同一条消息被重复消费。6、消息基于什么传输？由于 TCP 连接的创建和销毁开销较大，且并发数受系统资源限制，会造成性能瓶颈。RabbitMQ 使用信道的方式来传输数据。信道是建立在真实的 TCP 连接内的虚拟连接，且每条 TCP 连接上的信道数量没有限制。

7、消息如何分发？
若该队列至少有一个消费者订阅，消息将以循环（round-robin）的方式发送给消费者。每条消息只会分发给一个订阅的消费者（前提是消费者能够正常处理消息并进行确认）。通过路由可实现多消费的功能

8、消息怎么路由？消息提供方->路由->一至多个队列消息发布到交换器时，消息将拥有一个路由键（routing key），在消息创建时设定。通过队列路由键，可以把队列绑定到交换器上。消息到达交换器后，RabbitMQ 会将消息的路由键与队列的路由键进行匹配（针对不同的交换器有不同的路由规则）；常用的交换器主要分为一下三种：fanout：如果交换器收到消息，将会广播到所有绑定的队列上direct：如果路由键完全匹配，消息就被投递到相应的队列topic：可以使来自不同源头的消息能够到达同一个队列。 使用 topic 交换器时，可以使用通配符

9、如何确保消息不丢失？消息持久化，当然前提是队列必须持久化RabbitMQ 确保持久性消息能从服务器重启中恢复的方式是，将它们写入磁盘上的一个持久化日志文件，当发布一条持久性消息到持久交换器上时，Rabbit 会在消息提交到日志文件后才发送响应。一旦消费者从持久队列中消费了一条持久化消息，RabbitMQ 会在持久化日志中把这条消息标记为等待垃圾收集。如果持久化消息在被消费之前 RabbitMQ 重启，那么 Rabbit 会自动重建交换器和队列（以及绑定），并重新发布持久化日志文件中的消息到合适的队列。

10、使用 RabbitMQ 有什么好处？
（1）服务间高度解耦
（2）异步通信性能高
（3）流量削峰

11、RabbitMQ 的集群镜像集群模式你创建的 queue，无论元数据还是 queue 里的消息都会存在于多个实例上，然后每次你写消息到 queue 的时候，都会自动把消息到多个实例的 queue 里进行消息同步。好处在于，你任何一个机器宕机了，没事儿，别的机器都可以用。坏处在于，第一，这个性能开销也太大了吧，消息同步所有机器，导致网络带宽压力和消耗很重！第二，这么玩儿，就没有扩展性可言了，如果某个 queue 负载很重，你加机器，新增的机器也包含了这个 queue 的所有数据，并没有办法线性扩展你的 queue



# 参考
- [VirtualBox vs. VMWare vs. Hyper-V: What’s the Best Virtual Machine?
](https://www.makeuseof.com/tag/virtualbox-vs-vmware-vs-hyper-v/)

## Openstack
- [每天5分钟玩转 OpenStack](https://www.jianshu.com/p/4c06dff6cea8)

