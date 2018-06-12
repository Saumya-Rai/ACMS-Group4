#!/usr/bin/python
import json

forest=[]
node={}
	
def createNode(cs, fn, src, dest, sd, sa, ad, pd):
	node['callSign']=cs
	node['flightNo']=fn
	node['source']=src
	node['destination']=dest
	node['sch_dep']=sd
	node['sch_arr']=sa
	node['avg_delay']=ad
	node['prop_delay']=pd
	return node;

def createTree():
	tree=[]
	forest.append[tree]
	return tree;
	
def addNode(index, node):
	tempTree = forest[index]
	tempTree.append(node)
	return;
	
def getIndexOfTree(callsign):
	for i in range(0,len(forest)):
		tempTree = forest[i]
		node = tempTree[0]
		if node['callSign'] == callsign:
			return i;
	return -1;
	
	