package cuc;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import cuc.utils.ShoppingApp;


/*
 * 游戏主类：带main函数。
 * 游戏窗口：是Swing框架中的窗口类JFrame的子类
 * 包含着一个游戏世界
 * 接收用户输入，并转发
 */

public class GameStarter extends JFrame implements Runnable, KeyListener,MouseListener {
	
	GameWorld gameWorld;   //主类包含了一个游戏世界对象
	
	Thread animThread;    //代表动画线程对象	

	//下面两个属性是游戏双缓冲技术所需
	Image offScreen;      //次画面
	Graphics offScreenGraphics;   //次画面上的图形工具对象
	
	public GameStarter(){     
		//设置窗口的位置和大小
    	this.setBounds(100, 100, 1520, 1200);
    	//主类是个窗口，也就是一个键盘事件源，需要添加键盘事件的倾听者。
    	//因为主类本身已经实现了键盘倾听者接口，所以主类本身就是一个键盘事件倾听者
    	this.addKeyListener(this);
    	this.addMouseListener(this);
    	//给窗口关闭按钮增加关闭功能
    	this.addWindowListener(new WindowAdapter(){
    		public void windowClosing(WindowEvent we){
    			System.exit(0);
    		}
    	});       	
    	  
    	gameWorld = new GameWorld(this);	//初始化游戏世界  
    	
    	animThread = new Thread(this);   //创建线程对象
    	animThread.start();              //启动线程，即执行run函数     	
    	this.setVisible(true);          //显示窗口
    }
	//主类的main函数
    public static void main(String[] args) {	
    	//创建主类对象（自己）
    	new GameStarter();   
	} 
    //一个GUI显示对话
    
    
    //JFrame窗口的钩子函数，在这里进行图形渲染
    public void paint(Graphics g){    	
    	if(offScreenGraphics == null){
    		//创建次画面，大小与游戏窗口一样大
    		offScreen = createImage(this.getSize().width,this.getSize().height);
    		//获得次画面上的图形对象
    		offScreenGraphics = offScreen.getGraphics();
        }   
    	
    	//首先对次画面清屏，不然会留下残留
    	offScreenGraphics.setColor(Color.white); //设置白色画刷
    	offScreenGraphics.fillRect(0, 0, getWidth(), getHeight());  
    	    	
    	//将游戏世界渲染到次画面上    	
    	gameWorld.render(offScreenGraphics);    
    	
    	//将次画面贴到主画面上
    	g.drawImage(offScreen,0,0,this);
    	
    }
	//实现了Runnable接口的接口函数run。
	//充当游戏的动画线程
    @Override
	public void run() {
		// TODO Auto-generated method stub
		while( animThread != null ){    //游戏动画循环	
			//通知游戏世界进行仿真、状态更新
			gameWorld.update();
			
			//游戏动画暂停
	    	try {
	    		//线程休眠（暂停）40毫秒，如此游戏动画就是25帧/秒
				Thread.sleep(40);  
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	//请求系统重画，即再次调用paint方法
			repaint();
		}	
	}
	//处理用户键盘按下
	@Override
	public void keyPressed(KeyEvent arg0) {
		int key = arg0.getKeyCode();   //取得键盘事件的键值
		//键盘按下事件转发给游戏世界处理
		if( key == KeyEvent.VK_1 && ShoppingApp.isPurchased){
			gameWorld = new myHouse(this);
		}else if(key ==KeyEvent.VK_2 && ShoppingApp.isPurchased){
			gameWorld = new GameWorld(this);
		}
		if (gameWorld != null) { 
			gameWorld.keyPressed(key);
			gameWorld.update();
		}
	}
	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub		
	}
	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub		
	}
	//鼠标事件处理接口方法
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		gameWorld.mouseReleased(e);
	}	

}
