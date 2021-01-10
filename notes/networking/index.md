---
title: networking
date: 2019-10-07 19:56:25
comments: false
---
网络 (networks) 是一个复杂的东西，因此网络 (networking) 同时又是一门复杂的技术，涉及到通信技术，算法，协议，编程语言，操作系统，服务器，网络设备等方方面面，不仅需要**软硬结合**的功力，还涉及不少**理论和工程**方面的知识。学习网络技术，也是一种旅行吧。

从 1969 年美国国防部的 ARPA 网开始，到如今「大物移云」的时代，多种网络技术、架构、协议轮番登场，有的如今仍然存在，有的却因为这样或者那样的原因退出了历史的舞台中，网络经历了演变，并正在演变...

<!-- more -->
{% note info %}
- 非链接表示：给出基础概念，用作复习关键词。
- 链接表示：有一篇博文对应。
    - 完成 90% + 的会从草稿移入已发布。
    - 未完成的在草稿箱中待完善。
    - 计划中表示对该技术有兴趣，但暂时缺乏动力编写。
{% endnote %}

# 计算机网络
## 基本概念
- 计算机网络发展
- 分组交换和电路交换
- [网络体系结构与标准](/2018/12/19/network-architecture-and-standardization)
- 网络的服务质量保证 (QoS)

## 物理层 (Physical Layer)
- 传输链路
- 数据编码
- 无线传输

## 局域网 (Local Area Networks / The Link Layer)
- [局域网](/2018/12/29/network-lan/)
- [完成 70%] [以太网 - Ethernet](/2018/12/29/network-ethernet/)
- [计划中] [Shared Media LANs]()

## 网络互联 (TCP/IP Internet-working / The Network Layer)
- [完成 90%+] [Addressing in TCP/IP Networks](/2019/01/01/network-addressing)
- [完成 20%] [Routed Protocols - Internet Protocol Version 4 (IPv4)](/2019/07/01/network-ipv4/)
- [完成 20%] [Routed Protocols - Internet Protocol Version 6 (IPv6)](/2019/08/30/network-ipv6/)
- [完成 50%] [IP Routing in TCP/IP Networks](/2019/07/01/network-ip-routing/)
- [计划中] [ICMP: Internet Control Message Protocol]()

## 传输层 (Transport Layer)
- [完成 90%] [传输控制协议 - TCP]()
- [计划中] [用户数据报协议 - UDP]()
- [完成 20%] [给你安全感的 TLS]()
- [计划中] [给你快速度的 QUIC]()

## 应用层 (Application Layer)
- [完成 20%] [The Web and HTTP(S)]()
- [计划中] [Electronic Mail in the Internet]()

# 套接字编程 (Socket Programming)
本部分内容由网络工程师的角度转化为程序员的角度，讨论的内容也由整个网络缩小到**网络中的终端**。在前面的部分，我们讨论了物理层，通过 LAN 认识了数据链路层，通过 TCP/IP 网络互联了解了网络层、传输层、应用层及其相关协议。

我们熟悉的 Web 和 HTTP 协议、邮件、即时聊天软件、在线视频点播等应用，大都构筑在传输层之上的，这些软件的开发者，不需要深入了解应用层以下的网络内容，也可以写出可用的软件，这得益于 **TCP/IP 网络的分层架构**以及**操作系统提供的套接字 (Socket) 编程接口**，通过 Socket，我们可以专注于业务应用，而将复杂的网络数据包处理过程交给操作系统中的网络协议栈来帮助我们完成。

提到 Socket，你可能会以为它仅仅是一个传输层到应用层的桥梁，虽然大多数情况下，我们使用 TCP 流套接字 (Stream Sockets) 或者 UDP 数据报套接字 (Datagram Sockets) 来开发我们的程序，但却不仅仅如此，不同类型的套接字可能深入到不同的协议层，除了上述提到的套接字，还包括其他流套接字、原始套接字（深入到 IP 层）、数据链路套接字，甚至还有内核之间的套接字等。因此，可以说 Socket 编程是一个广泛的问题，其可能涉及到从数据链路层到应用层的所有知识。

通常，我们使用 C 语言来学习 Socket 编程，这是必要的，因为底层的套接字只有 C 语言可以实现（其他语言如 Python 仅仅是包装了一层 C 函数，提供了 Python 的 API 而已），因此使用 C 语言可以方便的编写一些网络工具。但对于大规模的应用开发，使用 C 语言有些力不从心，考虑到一般情况下传输层协议采用 TCP 或 UDP 协议，因此可以使用 Java 语言（Java 为了跨平台特性，只提供 TCP 和 UDP 的套接字接口）来进行开发。

在本部分内容中，所有的 Socket 编程均使用 C 语言，并且在 Linux 平台下开发，尽管是 Linux 操作系统，但为了保持较好的移植性也尽可能使用 Unix 兼容的 POSIX 接口，在必要的时候会使用 Linux 接口。

考虑到 Socket 编程涉及到一些操作系统知识，因此本部分的内容建立在 {% button /notes/os/linux/, OS - Linux, home fa-fw fa-lg %} 之上。

- [完成 20%-] [Socket Programming in C](/2019/06/10/socket-programming-in-c/) 

# 软件定义网络 (SDN)
软件定义网络 (Software-Defined Networking, SDN) 如今的到了飞速的发展，并逐渐用于一些网络场景。以下是一些简单的链接。

## Emulator: Mininet
- [Basics: Get Started, Sample Workflow and Walkthrough](/2018/10/31/mininet-basics)

## Controllers
### Floodlight
- [Documentation](https://floodlight.atlassian.net/wiki/spaces/floodlightcontroller/overview)
    - [Getting Started](https://floodlight.atlassian.net/wiki/spaces/floodlightcontroller/pages/1343542/Getting+Started)
    - [User Documentation](https://floodlight.atlassian.net/wiki/spaces/floodlightcontroller/pages/1343557/User+Documentation)
    - [For Developers](https://floodlight.atlassian.net/wiki/spaces/floodlightcontroller/pages/1343556/For+Developers)

### ONOS

### OpenDayLight


## Hypervisor
- FlowVisor
- OVX

## Switches
- OVS

## Protocols
### OpenFlow 
- [OpenFlow Specification](/2019/02/23/openflow-specification/)

## FAQ
- Q：SDN 中 处理 ARP 的逻辑  
A：https://www.sdnlab.com/8513.html

## External Links
- [杨泽卫老师推荐](https://www.zhihu.com/question/27336289/answer/92617122?from=profile_answer_card)
- [SDN Reading list](https://sites.google.com/site/sdnreadinglist/) 需要梯子

# 参考
- [Computer Networks: Principles, Technologies and Protocols for Network Design](http://www.olifer.co.uk/Main_menu_eng.htm)
- Computer Networking A Top-Down Approach 6th Edition By Kurose and Ross
- Computer Networks 5th Edition By Andrew S. Tanenbaum
- [Computer network, wikipedia](https://en.wikipedia.org/wiki/Computer_network)
- [OSI model](https://en.wikipedia.org/wiki/OSI_model)
- [Lists of network protocols](https://en.wikipedia.org/wiki/Lists_of_network_protocols)
