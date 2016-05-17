package com.cyber.snake;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import com.cyber.Dir.Dir;
import com.cyber.yard.Yard;

public class Snake {
	//蛇的属性，有头结点，尾结点
	private Node head = null;
	private Node tail = null;
	//生成一个node结点，初始化蛇时使用
	private Node n = new Node(20,30,Dir.D);
	//蛇的构造方法,里面清楚的写到head和tail都是这个node

	public Snake(){
		//new Node(20,40,Dir.L);
		head = n;
		tail = n;

	}
	//在移动时添加到尾部的移动方法，按不同的方向，向尾部添加
	private void addToTail(){
		Node node = null;
		switch(tail.dir){
		case L:
			node = new Node(tail.row,tail.col +1,tail.dir);
			break;
		case U :
			node = new Node(tail.row+1,tail.col,tail.dir);
			break;
		case R :
			node = new Node(tail.row,tail.col -1,tail.dir);
			break;
		case D : 
			node = new Node(tail.row-1,tail.col,tail.dir);
			break;
		}

		tail.next = node;
		node.prev = tail;
		tail = node;
	}
	//在头部加节数的方法，都是安方向来的
	private void addToHead(){
		Node node = null;
		switch(head.dir){
		case L :
			node = new Node(head.row,head.col - 1,head.dir);
			break;
		case U :
			node = new Node(head.row - 1,head.col,head.dir);
			break;
		case R :
			node = new Node(head.row,head.col + 1,head.dir);
			break;
		case D :
			node = new Node(head.row + 1,head.col,head.dir);
			break;
		}

		node.next = head;
		head.prev = node;
		head = node;
	}
	//蛇的画法，通过链表的循环，把蛇的结点的所有位置画出来。
    //蛇的移动要重画。所以每次画完以后，都要移动一下，方便repaint().
	public void draw(Graphics g){
		move();
		for(Node n = head;n!=null;n=n.next){
			n.draw(g);
		}
	
	}

	//move时是通过在头部添加一节，在尾部删除一节来进行的，使用了双向链表
	private void move() {
		// TODO 自动生成的方法存根
		addToHead();
		deleteFromTail();
	}
    //在尾部进行删除的方法，就是把上一个作为尾部，再把这个尾部的下一节设为null解决。
	private void deleteFromTail() {
		// TODO 自动生成的方法存根
		tail = tail.prev;
		tail.next = null;
	}
	//最关键的node结点，里面有每一个node的位置，方向属性，
	private class Node{
		int w = Yard.BLOCK_SIZE ;
		int h = Yard.BLOCK_SIZE ;
		int row,col;
		Dir dir = null;
		Node next = null;
		Node prev = null;

		public Node(int row, int col,Dir dir) {
		
			this.row = row;
			this.col = col;
			this.dir = dir;

		}
		//draw方法,从左上角点的位置算起。加上宽，高
		private void draw(Graphics g){
			Color c = g.getColor();
			g.setColor(Color.BLACK);
			g.fillRect(Yard.BLOCK_SIZE * col, Yard.BLOCK_SIZE * row, w, h);
			g.setColor(c);
		}
	}
	//按键监听事件
	public void keyPressed(KeyEvent e) {
		// TODO 自动生成的方法存根
		int key = e.getKeyCode();
		switch(key){ 
		case KeyEvent.VK_LEFT :
			head.dir = Dir.L;
			break;
		case KeyEvent.VK_UP :
			head.dir = Dir.U ;
			break;
		case KeyEvent.VK_RIGHT :
			head.dir = Dir.R ;
			break;
		case KeyEvent.VK_DOWN :
			head.dir = Dir.D ;
			break;
		}

	}

}
