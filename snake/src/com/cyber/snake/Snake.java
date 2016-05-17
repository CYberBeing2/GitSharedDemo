package com.cyber.snake;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import com.cyber.Dir.Dir;
import com.cyber.yard.Yard;

public class Snake {
	//�ߵ����ԣ���ͷ��㣬β���
	private Node head = null;
	private Node tail = null;
	//����һ��node��㣬��ʼ����ʱʹ��
	private Node n = new Node(20,30,Dir.D);
	//�ߵĹ��췽��,���������д��head��tail�������node

	public Snake(){
		//new Node(20,40,Dir.L);
		head = n;
		tail = n;

	}
	//���ƶ�ʱ��ӵ�β�����ƶ�����������ͬ�ķ�����β�����
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
	//��ͷ���ӽ����ķ��������ǰ���������
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
	//�ߵĻ�����ͨ�������ѭ�������ߵĽ�������λ�û�������
    //�ߵ��ƶ�Ҫ�ػ�������ÿ�λ����Ժ󣬶�Ҫ�ƶ�һ�£�����repaint().
	public void draw(Graphics g){
		move();
		for(Node n = head;n!=null;n=n.next){
			n.draw(g);
		}
	
	}

	//moveʱ��ͨ����ͷ�����һ�ڣ���β��ɾ��һ�������еģ�ʹ����˫������
	private void move() {
		// TODO �Զ����ɵķ������
		addToHead();
		deleteFromTail();
	}
    //��β������ɾ���ķ��������ǰ���һ����Ϊβ�����ٰ����β������һ����Ϊnull�����
	private void deleteFromTail() {
		// TODO �Զ����ɵķ������
		tail = tail.prev;
		tail.next = null;
	}
	//��ؼ���node��㣬������ÿһ��node��λ�ã��������ԣ�
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
		//draw����,�����Ͻǵ��λ�����𡣼��Ͽ���
		private void draw(Graphics g){
			Color c = g.getColor();
			g.setColor(Color.BLACK);
			g.fillRect(Yard.BLOCK_SIZE * col, Yard.BLOCK_SIZE * row, w, h);
			g.setColor(c);
		}
	}
	//���������¼�
	public void keyPressed(KeyEvent e) {
		// TODO �Զ����ɵķ������
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
