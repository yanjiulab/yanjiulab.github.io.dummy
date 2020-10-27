#!/usr/bin/python
"""
使用方法：
sudo python circle4-dual.py <controller-ip> <openflow-port>

例如：
示例1 sudo python circle4-dual.py 127.0.0.1 6653
示例2 sudo python circle4-dual.py 10.205.36.41 10000
"""
import sys

from mininet.net import Mininet
from mininet.topo import Topo
from mininet.log import lg, setLogLevel
from mininet.cli import CLI
from mininet.node import RemoteController

CORES = {
  'ONE': {'dpid': '000000000000010%s'},
  'TWO': {'dpid': '000000000000020%s'},
  'THR': {'dpid': '000000000000030%s'},
  'FOU': {'dpid': '000000000000040%s'},
  }

FANOUT = 4
    
class I2Topo(Topo):

  def __init__(self, enable_all = True):
    "Create Internet2 topology."

    # Add default members to class.
    super(I2Topo, self).__init__()

    # Add core switches
    self.cores = {}
    for switch in CORES:
      self.cores[switch] = self.addSwitch(switch, dpid=(CORES[switch]['dpid'] % '0'))
      #self.cores[switch] = self.addSwitch(switch, dpid=(CORES[switch]['dpid'] % '0'), failMode='standalone', stp=True)

    # Add hosts and connect them to their core switch
    for switch,id in CORES.items():
      for count in range(1, FANOUT + 1):
        # Add hosts
        host = 'h_%s_%s' % (switch, count)
        ip = '10.0.%s.%s' % (id['dpid'][13], count)
        mac = CORES[switch]['dpid'][4:] % count
        h = self.addHost(host, ip=ip, mac=mac)
        # Connect hosts to core switches
        self.addLink(h, self.cores[switch])

    # Connect core switches
    self.addLink(self.cores['ONE'], self.cores['TWO'])
    self.addLink(self.cores['TWO'], self.cores['THR'])
    self.addLink(self.cores['THR'], self.cores['FOU'])
    self.addLink(self.cores['FOU'], self.cores['ONE'])
    self.addLink(self.cores['ONE'], self.cores['TWO'])

if __name__ == '__main__':
    argc = len(sys.argv)
    if argc == 1:
        ip = '127.0.0.1'
        port = 6653
    elif argc  == 2:
        ip = sys.argv[1]
        port = 6653
    elif argc == 3:
        ip = sys.argv[1]
        port = int(sys.argv[2])
    else:
        print("argument unavailable")
        
    topo = I2Topo()
    c = RemoteController('c', ip=ip, port=port)
    net = Mininet(topo=topo, autoSetMacs=True, xterms=False, controller=None)
    net.addController(c)
    net.start()