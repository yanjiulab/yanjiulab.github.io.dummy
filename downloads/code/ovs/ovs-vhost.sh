#!/bin/bash
# Clear configurations
#ip netns del host
#ovs-vsctl del-port br0 veth0

# Create network namespace `host`.
ip netns add host
echo "------------------ IP network namespace ---------------"
ip netns list

# Create a port pair `h1-eth0<->veth0`
ip link add h1-eth0 type veth peer name veth0
#echo "------------------ Links in global namespace ---------------"
#ip link list

# Configurate veth0
ovs-vsctl add-port br0 veth0
ip link set veth0 up

# Configurate h1-eht0
# Move h1-eth0 to host namespace
ip link set h1-eth0 netns host 
echo "------------------ Links in host ---------------"
ip netns exec host ip link list

# Config IP address
ip netns exec host ip addr add 192.168.13.2/16 dev h1-eth0
echo "------------------ h1-etho IP address ---------------"
ip netns exec host ip addr show

# Config IP route table
ip netns exec host ip route add default via 192.168.13.2
echo "------------------ h1-etho route table ---------------"
ip netns exec host ip route show

# Up h1-eth0 and lo
ip netns exec host ip link set h1-eth0 up
ip netns exec host ip link set lo up

# Test lo
#echo "------------------ Ping test in h1-etho ---------------"
#ip netns exec host ping -c3 192.168.13.2

# Test to ovs
#echo "------------------ Ping test in h1-etho ---------------"
#ip netns exec host ping -c3 192.168.13.1

