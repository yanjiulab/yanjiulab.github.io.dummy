#!/usr/bin/env python3
#coding=utf-8 

import os
import time

def findprocess():
	p = os.popen('lsof -i:6653')
	x = p.read()
	if x == "":
		return False
	if x.split()[-1] == "(ESTABLISHED)":
		return True
	else:
		return False

establishflag = 0
stpflag = 0

os.system('ifconfig br0 down')
os.system('ifconfig br0 up')
while(1):
	if(not findprocess()):
		if stpflag == 0:
			os.system('ovs-vsctl set bridge br0 stp_enable=true')
			#print('\n----------------------stp_enabled!----------------------')
			stpflag = 1
			establishflag = 0
		time.sleep(2)	
		
	else:
		if establishflag == 0:
			#print("\n---------------------------controller matched!------------------------")
			establishflag = 1
			os.system('ovs-vsctl set bridge br0 stp_enable=false')
			#print('\n----------------------stp_disabled!-------------------------')
			stpflag = 0				
		time.sleep(10)				
		pass




