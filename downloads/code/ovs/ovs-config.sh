#!/bin/sh -e 

ovs-vsctl del-br br0
ovs-vsctl add-br br0

ovs-vsctl set bridge br0 fail-mode=standalone
ovs-vsctl set bridge br0 stp_enable=true
ovs-vsctl set bridge br0 other-config:disable-in-band=false

ovs-vsctl add-port br0 enp1s0
ovs-vsctl add-port br0 enp2s0
ovs-vsctl add-port br0 enp3s0
ovs-vsctl add-port br0 enp6s0

ovs-vsctl set-controller br0 tcp:192.168.10.1:6653

ifconfig br0 192.168.13.1 netmask 255.255.0.0
ifconfig br0 up

exit 0
