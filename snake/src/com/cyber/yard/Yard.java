package com.cyber.yard;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import com.cyber.snake.Snake;

public class Yard extends Frame {

	/**
	 * Author Cyber
	 * Date 2016.5.15 23:00
	 * java贪食蛇，对双向链表的练习
	 */
	
	private static final long serialVersionUID = -488998190066103842L;
	public static final int ROWS = 100;
	public static final int COLS = 100;
	public static final int BLOCK_SIZE =9;

	Image offScreenImage = null;

	Snake s = new Snake();

	public void launch(){
		this.setLocation(300, 300);
		this.setSize(COLS * BLOCK_SIZE,ROWS * BLOCK_SIZE);
		this.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				// TODO 自动生成的方法存根
				System.exit(0);
			}

		});
		this.addKeyListener(new KeyMonitor());
		this.setVisible(true);
		new Thread(new PaintThread()).start();
	}

	//for(int )

	@Override
	public void paint(Graphics g) {
		// TODO 自动生成的方法存根
		Color c = g.getColor();
		g.setColor(Color.gray);
		g.fillRect(0,0,COLS * BLOCK_SIZE,ROWS * BLOCK_SIZE);
		g.setColor(c);
		g.setColor(Color.DARK_GRAY);
		for(int i=1;i<ROWS;i++){
			g.drawLine(0, BLOCK_SIZE * i, COLS * BLOCK_SIZE, BLOCK_SIZE * i);	
		}
		for(int i=1;i<COLS;i++){
			g.drawLine(BLOCK_SIZE * i, 0, BLOCK_SIZE * i, ROWS * BLOCK_SIZE);	
		}
		s.draw(g);
	}

	@Override
	public void update(Graphics g) {
		// TODO 自动生成的方法存根
		if(offScreenImage == null){
			offScreenImage = this.createImage(COLS * BLOCK_SIZE,ROWS * BLOCK_SIZE);
		}

		Graphics gOff = offScreenImage.getGraphics();
		paint(gOff);
		g.drawImage(offScreenImage,0,0,null);

	}

	private class PaintThread implements Runnable{

		@Override
		public void run() {
			// TODO 自动生成的方法存根
			while(true){
				repaint();
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}

			}
		}

	}


	private class KeyMonitor extends KeyAdapter{

		@Override
		public void keyPressed(KeyEvent e) {
			s.keyPressed(e);
		}
	}

	public static void main(String[] args) {
		new Yard().launch();
		// TODO 自动生成的方法存根

	}

}
