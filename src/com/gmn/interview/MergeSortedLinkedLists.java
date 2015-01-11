package com.gmn.interview;

import java.util.Random;


// Merge n sorted linked lists
public class MergeSortedLinkedLists {

	
	public Node[] randomizeLinkedLists(int n) {
		Node[] heads = new Node[n];
		Node[] tails = new Node[n];
		int value = 0;
		for( int ctr = 0; ctr < 100; ctr++ ) {
			int listNo = randInt(0, n-1);
			if( heads[listNo] == null ) {
				heads[listNo] = new Node(value++);
				tails[listNo] = heads[listNo];
			} else {
				Node newNode = new Node(value++);
				tails[listNo].setNext(newNode);
				tails[listNo] = newNode;
			}
		}
		
		// Ensure each list has at least one, add em if not there
		for( int ctr = 0; ctr < n; ctr++ ) {
			if( heads[ctr] == null ) {
				heads[ctr]= new Node(value++);
			}
		}
		System.out.println("Returning heads");
		return heads;
	}
	
	public void showLists(Node[] lists) {
		for( Node list : lists ) {
			printList(list);
		}
	}
	
	public void printList(Node head) {
		StringBuilder sb = new StringBuilder();
		Node currNode = head;
		boolean first = true;
		while(currNode.getNext() != null) {
			if( first ) {
				first = false;
			} else {
				sb.append("->");
			}
			sb.append(currNode.getValue());
			currNode = currNode.getNext();
		}
		System.out.println(sb);
	}
	
	public static int randInt(int min, int max) {

	    // NOTE: Usually this should be a field rather than a method
	    // variable so that it is not re-seeded every call.
	    Random rand = new Random();

	    // nextInt is normally exclusive of the top value,
	    // so add 1 to make it inclusive
	    int randomNum = rand.nextInt((max - min) + 1) + min;

	    return randomNum;
	}
	
	public Node mergeLists(Node[] lists) {
		Node mergedHead = null;
		Node mergedTail = null;
		Node[] currNodes = new Node[lists.length];
		for( int ctr = 0; ctr < lists.length; ctr++ ) {
			currNodes[ctr] = lists[ctr];
		}
		
		while(true) {
			Node lowest = null;
			int lowestIdx = -1;
			for( int ctr = 0; ctr < lists.length; ctr++ ) {
				if( currNodes[ctr] == null ) {
					continue;
				}
				if( lowest == null ) {
					lowest = currNodes[ctr];
					lowestIdx = ctr;
				} else if( currNodes[ctr].getValue() < lowest.getValue() ) {
					lowest = currNodes[ctr];
					lowestIdx = ctr;
				}
			}
			if( lowest == null ) {
				break;
			}
			currNodes[lowestIdx] = lowest.getNext();
			
			if( mergedHead == null ) {
				mergedHead = new Node(lowest.getValue());
				mergedTail = mergedHead;
			} else {
				Node cloned = new Node(lowest.getValue());
				mergedTail.setNext(cloned);
				mergedTail = cloned;
			}
		}
		return mergedHead;
	}
	
	
	public static void main(String[] args) {
		MergeSortedLinkedLists merger = new MergeSortedLinkedLists();
		Node[] theLists = merger.randomizeLinkedLists(5);
		merger.showLists(theLists);
		Node lowestList = merger.mergeLists(theLists);
		System.out.println("Merged lists: ");
		merger.printList(lowestList);
	}
	
}

class Node {
	int value;
	Node next = null;
	
	public Node() {
	}
	
	public Node(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
	
	public void setValue(int value) {
		this.value = value;
	}
	
	public Node getNext() {
		return next;
	}
	
	public void setNext(Node next) {
		this.next = next;
	}
}
