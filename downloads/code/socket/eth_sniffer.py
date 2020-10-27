# Packet sniffer in python
# For Linux - Sniffs all incoming and outgoing packets :)
# Silver Moon (m00n.silv3r@gmail.com)

import socket
import sys
from struct import *

ETH_LENGTH = 14


def eth_addr(a):
    """Convert a string of 6 characters of ethernet address into a dash separated hex string
    """

    b = "%.2x:%.2x:%.2x:%.2x:%.2x:%.2x" % (a[0], a[1], a[2], a[3], a[4], a[5])
    return b


def parse_ipv4_packet(packet):
    # Parse IP header
    # take first 20 characters for the ip header
    ip_header = packet[ETH_LENGTH:20+ETH_LENGTH]

    # now unpack them :)
    iph = unpack("!BBHHHBBH4s4s", ip_header)

    version_ihl = iph[0]
    version = version_ihl >> 4
    ihl = version_ihl & 0xF

    iph_length = ihl * 4

    ttl = iph[5]
    protocol = iph[6]
    s_addr = socket.inet_ntoa(iph[8])
    d_addr = socket.inet_ntoa(iph[9])

    print("Internet Protocol Version " + str(version) +
          ", Src Address: " + str(s_addr) +
          ", Dst Address: " + str(d_addr))

    # print("Internet Protocol Version" + str(version) +
    #     ", IP Header Length : " + str(ihl) +
    #     " TTL : " + str(ttl) +
    #     " Protocol : " + str(protocol) +
    #     " Source Address : " + str(s_addr) +
    #     " Destination Address : " + str(d_addr))

    # TCP protocol
    if protocol == 6:
        t = iph_length + ETH_LENGTH
        tcp_header = packet[t:t+20]

        # now unpack them :)
        tcph = unpack("!HHLLBBHHH", tcp_header)

        source_port = tcph[0]
        dest_port = tcph[1]
        sequence = tcph[2]
        acknowledgement = tcph[3]
        doff_reserved = tcph[4]
        tcph_length = doff_reserved >> 4

        print("Transmission Control Protocol, Src Port: " + str(source_port) +
              ", Dst Port: " + str(dest_port) +
              ", Seq: " + str(sequence) +
              ", Ack: " + str(acknowledgement) +
              ", Len: " + str(tcph_length))

        # print("Transmission Control Protocol, Source Port: " + str(source_port) +
        # ", Destination Port: " + str(dest_port) +
        # ", Sequence Number: " + str(sequence) +
        # ", Acknowledgement: " + str(acknowledgement) +
        # ", TCP header length: " + str(tcph_length))

        h_size = ETH_LENGTH + iph_length + tcph_length * 4
        data_size = len(packet) - h_size

        # get data from the packet
        data = packet[h_size:]
        print("Data (" + str(data_size) + " bytes): " + str(data))

    # ICMP Packets
    elif protocol == 1:
        u = iph_length + ETH_LENGTH
        icmph_length = 4
        icmp_header = packet[u:u+4]

        # now unpack them :)
        icmph = unpack("!BBH", icmp_header)

        icmp_type = icmph[0]
        code = icmph[1]
        checksum = icmph[2]

        print("Internet Control Message Protocol, Type: " + str(icmp_type) +
              " Code : " + str(code) +
              " Checksum : " + str(checksum))

        # print("Type : " + str(icmp_type) + " Code : " + str(code) + " Checksum : " + str(checksum))

        h_size = ETH_LENGTH + iph_length + icmph_length
        data_size = len(packet) - h_size

        # get data from the packet
        data = packet[h_size:]

        print("Data (" + str(data_size) + " bytes): " + str(data))

    # UDP packets
    elif protocol == 17:
        u = iph_length + ETH_LENGTH
        udph_length = 8
        udp_header = packet[u:u+8]

        # now unpack them :)
        udph = unpack("!HHHH", udp_header)

        source_port = udph[0]
        dest_port = udph[1]
        length = udph[2]
        checksum = udph[3]

        print("User Datagram Protocol, Src Port : " + str(source_port) +
              ", Dst Port: " + str(dest_port) +
              ", Length: " + str(length) +
              ", Checksum: " + str(checksum))

        h_size = ETH_LENGTH + iph_length + udph_length
        data_size = len(packet) - h_size

        # get data from the packet
        data = packet[h_size:]
        
        print("Data (" + str(data_size) + " bytes): " + str(data))

    # some other IP packet like IGMP
    else:
        print("Protocol other than TCP/UDP/ICMP")

    print("")


def parse_ipv6_packet(packet):
    pass


def parse_arp_packet(packet):
    # Parse ARP header
    # take first 20 characters for the ip header
    arp_packet = packet[ETH_LENGTH:28+ETH_LENGTH]

    # now unpack them :)
    arp = unpack("!HHBBH6s4s6s4s", arp_packet)

    h_type = arp[0]
    p_type = arp[1]
    h_len = arp[2]
    p_len = arp[3]
    op_code = arp[4]
    s_h_addr = eth_addr(packet[8:14])
    s_p_addr = socket.inet_ntoa(arp[6])
    t_h_addr = eth_addr(packet[18:24])
    t_p_addr = socket.inet_ntoa(arp[8])

    if op_code == 1:
        print("Address Resolution Protocol (request)", end='')
    else:
        print("Address Resolution Protocol (reply)", end='')

    print(", Hardware Type: " + str(h_type) +
          ", Protocol Type: " + str(hex(p_type)) +
          ", Hardware Length: " + str(h_len) +
          ", Protocol Length: " + str(p_len) +
          ", Sender hardware address (SHA): " + s_h_addr +
          ", Sender protocol address (SPA): " + str(s_p_addr) +
          ", Target hardware address (THA): " + t_h_addr +
          ", Target protocol address (TPA): " + str(t_p_addr))

    print("")


def parse_8021q_packet(packet):
    # Parse 802.1Q tagged header (32 bits)
    vlan = packet[ETH_LENGTH:28+ETH_LENGTH]

    # now unpack them :)
    vlan = unpack("!HHBBH6s4s6s4s", vlan)


def process_packet(packet):
    """Process Ethernet packets. The default type is Ethernet DIX/ Ethernet II, 
    which is the most widely used Ethernet protocol in IP networks.

    The supported protocols are:
    - IPv4 (0x0800 -> eth[2](2048) -> ntohs(0x0800) -> 0x0008 -> 8)
    - IPv6 (0x86dd -> eth[2](34525) -> ntohs(0x86dd) -> 0xdd86 -> 56710)
    - ARP (0x0806 -> eth[2](2054) -> ntohs(0x0806) -> 0x0608 -> 1544)
    - 802.1Q (0x8100 -> eth[2](33024) -> ntohs(0x8100) -> 0x0081 -> 129)
    """
    # Parse Ethernet header
    eth_header = packet[:ETH_LENGTH]
    eth = unpack("!6s6sH", eth_header)
    eth_protocol = socket.ntohs(eth[2])

    # print("Ethernet II， Dst MAC: " + eth_addr(packet[0:6]) +
    #                 "， Src MAC: " + eth_addr(packet[6:12]) +
    #                 "， Type: " + str(eth_protocol) + " (" + hex(eth[2]) +")" )

    # Parse IPv4 packets, IPv4 Protocol number = 8
    if eth_protocol == 8:
        pass
        # parse_ipv4_packet(packet)

    # Parse IPv6 packets, IPv6 Protocol number = 56710
    elif eth_protocol == 56710:
        print("Protocol", hex(eth[2]), eth[2], hex(eth_protocol), eth_protocol)
        parse_ipv6_packet(packet)

    # Parse ARP packets, ARP Protocol number = 1544
    elif eth_protocol == 1544:
        parse_arp_packet(packet)

    # Parse 802.1Q tagged packets, 802.1Q Protocol number = 129
    elif eth_protocol == 129:
        parse_8021q_packet(packet)

    # Unknown packets
    else:
        print("Unknown protocol...")
        print("Protocol number: ", hex(eth[2]), eth[2], hex(
            eth_protocol), eth_protocol)


def main():
    """
    Create a AF_PACKET type raw socket (thats basically packet level)

    The complete list of protocols is found in /usr/include/linux/if_ether.h.
    These are the defined Ethernet Protocol ID"s.
    #define ETH_P_ALL	0x0003		/* Every packet (be careful!!!) */
    #define ETH_P_IP	0x0800		/* Internet Protocol packet	*/
    #define ETH_P_X25	0x0805		/* CCITT X.25			*/
    #define ETH_P_ARP	0x0806		/* Address Resolution packet	*/
    #define ETH_P_RARP      0x8035		/* Reverse Addr Res packet	*/
    #define ETH_P_8021Q	0x8100          /* 802.1Q VLAN Extended Header  */
    #define ETH_P_IPX	0x8137		/* IPX over DIX			*/
    #define ETH_P_IPV6	0x86DD		/* IPv6 over bluebook		*/
    """
    try:
        s = socket.socket(socket.AF_PACKET, socket.SOCK_RAW,
                          socket.ntohs(0x0003))
    except OSError as e:
        print("Socket could not be created. Error Code : " + e)
        sys.exit()

    # receive a packet
    while True:
        # Receive a packet
        packet = s.recvfrom(65565)

        # Packet string from tuple
        packet = packet[0]

        # Now process the packet
        process_packet(packet)

    s.close()
    print("Finish...")


if __name__ == "__main__":
    main()
