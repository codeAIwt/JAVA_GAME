package cuc;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import cuc.gameObjects.GameObject;
import cuc.gameObjects.Monster;
import cuc.gameObjects.Tree1;
import cuc.gameObjects.Tree2;
import cuc.gameObjects.Vender;
import cuc.states.MoveState;
import cuc.states.StopState;
import cuc.gameObjects.Avatar;
import cuc.gameObjects.Collision;
import cuc.utils.AmmoPara;
import cuc.utils.AnimPara;
import cuc.utils.Animation2D;
import cuc.utils.Point2D;
import cuc.utils.SoundPara;

public class myHouse extends GameWorld {

	public myHouse(GameStarter game) {
		super(game);

		initGameScene1();
	}

	// 下面两张图是游戏的背景图和前景图，是2.5D游戏特有的技术。
	Image xImage; // 游戏场景背景图
	Image yImage;// 游戏场景前景图

	void initGameScene1() {
		// 初始化游戏场景背景图和前景图
		xImage = new ImageIcon(getClass().getClassLoader().getResource("images/房子.jpg")).getImage();
		yImage = new ImageIcon(getClass().getClassLoader().getResource("images/房子_1_2.png")).getImage();

		initAllParas(); // 初始化所有的资源参数对象
		//initAllGameObjects(); // 初始化所有的游戏对象
	}


	//初始化所有的参数对象
	void initAllParas(){
		//初始化所有的参数对象
		//土匪的三个状态动画
		allAnimParas.add(new AnimPara("土匪站立","土匪站立.png",1,8,new int[]{4,6,2,0,5,3,7,1})); 
		allAnimParas.add(new AnimPara("土匪行走","土匪行走.png",8,8,new int[]{4,6,2,0,5,3,7,1})); 
		allAnimParas.add(new AnimPara("土匪跑步","土匪行走.png",8,8,new int[]{4,6,2,0,5,3,7,1})); 
//		//货郎的三个状态动画
//		allAnimParas.add(new AnimPara("货郎站立","货郎站立.png",1,4,new int[]{0,1,2,3})); 
//		allAnimParas.add(new AnimPara("货郎行走","货郎行走.png",4,4,new int[]{0,1,2,3})); 
//		allAnimParas.add(new AnimPara("货郎跑步","货郎行走.png",4,4,new int[]{0,1,2,3})); 
//		//书生的三个状态动画
//		allAnimParas.add(new AnimPara("书生站立","书生站立.png",1,4,new int[]{0,1,2,3})); 
//		allAnimParas.add(new AnimPara("书生行走","书生行走.png",4,4,new int[]{0,1,2,3})); 
//		allAnimParas.add(new AnimPara("书生跑步","书生行走.png",4,4,new int[]{0,1,2,3}));
//		
		//弹药的动画信息
		allAnimParas.add(new AnimPara("飞蛇箭","飞蛇箭.png",8,8,new int[]{0,2,6,4,1,7,3,5})); 
		allAnimParas.add(new AnimPara("火弹","火弹.png",8,1,new int[]{0})); 
				
		//初始化所有的音效信息对象
		allSoundParas.add(new SoundPara("弓箭发射声","弓箭发射.wav"));
		allSoundParas.add(new SoundPara("走路声","走路声.wav"));		
		
		//弹药信息
		allAmmoParas.add(new AmmoPara("飞蛇箭",new Point2D(50,50),20,3000,30,getAnimParaByName("飞蛇箭"),getSoundParaByName("弓箭发射声"),null));
		allAmmoParas.add(new AmmoPara("火弹",new Point2D(50,50),20,3000,30,getAnimParaByName("火弹"),getSoundParaByName("弓箭发射声"),null));
	}

	//初始化所有的游戏对象
	//注意：这是以前面参数对象准备好了为基础，在初始游戏对象时直接用name引用参数对象
	void initAllGameObjects(){
		//创建阿凡达对象，并为阿凡达添加状态
		Avatar avatar = new Avatar(this,new Point2D(400,400),new Point2D(192,228),100,getAmmoParaByName("飞蛇箭"));
		//添加站立状态
		AnimPara stopAI = this.getAnimParaByName("土匪站立");  //得到土匪站立的动画信息
		Animation2D stopAnim = new Animation2D(stopAI);        //创建土匪站立动画对象				
		StopState stop = new StopState(avatar,1000000000,0,stopAnim,"");  //创建土匪站立状态
		avatar.addState(stop);      //给阿凡达对象添加站立状态
		//添加行走状态
		AnimPara moveAI = this.getAnimParaByName("土匪行走");  //得到土匪行走的动画信息
		Animation2D moveAnim = new Animation2D(moveAI);        //创建土匪行走动画对象				
		MoveState move = new MoveState(avatar,2000,8,moveAnim,"走路声.wav");  //创建土匪行走状态
		avatar.addState(move);      //给阿凡达对象添加行走状态
		
		avatar.setCurState(stop);   //设置阿凡达的当前状态
		stop.start();
		this.addGameObject(avatar);
		
//		//创建一个书生角色
//		Monster monster = new Monster(this,new Point2D(650,1030),new Point2D(64,76),100,getAmmoParaByName("火弹"));
//		//添加站立状态
//		AnimPara stopAI2 = this.getAnimParaByName("书生站立");  //得到书生站立的动画信息
//		Animation2D stopAnim2 = new Animation2D(stopAI2);        //创建书生站立动画对象
//		//创建书生站立状态，设置书生静止状态最多持续3秒钟
//		StopState stop2 = new StopState(monster,3000,0,stopAnim2,"");  
//		monster.addState(stop2);      //给书生对象添加站立状态
//		//添加行走状态
//		AnimPara moveAI2 = this.getAnimParaByName("书生行走");  //得到书生行走的动画信息
//		Animation2D moveAnim2 = new Animation2D(moveAI2);        //创建书生行走动画对象	
//		//创建书生行走状态，设置行走最多2秒
//		MoveState move2 = new MoveState(monster,2000,4,moveAnim2,"走路声.wav");  
//		monster.addState(move2);      //给书生对象添加行走状态
//		
//		monster.setCurState(stop2);   //设置阿凡达的当前状态
//		stop2.start();
//		this.addGameObject(monster);
//		
//		//创建一个货郎角色
//		Vender vender = new Vender(this,new Point2D(700,700),new Point2D(64,76),100,getAmmoParaByName("火弹"));
//		//添加站立状态
//		AnimPara stopAI1 = this.getAnimParaByName("货郎站立");  //得到货郎站立的动画信息
//		Animation2D stopAnim1 = new Animation2D(stopAI1);        //创建货郎站立动画对象
//		//创建货郎站立状态，设置货郎静止状态最多持续3秒钟
//		StopState stop1 = new StopState(vender,3000,0,stopAnim1,"");  
//		vender.addState(stop1);      //给货郎对象添加站立状态
//		//添加行走状态
//		AnimPara moveAI1 = this.getAnimParaByName("货郎行走");  //得到货郎行走的动画信息
//		Animation2D moveAnim1 = new Animation2D(moveAI1);        //创建货郎行走动画对象	
//		//创建货郎行走状态，设置货郎行走最多2秒
//		MoveState move1 = new MoveState(vender,2000,4,moveAnim1,"走路声.wav");  
//		vender.addState(move1);      //给货郎对象添加行走状态
//		
//		vender.setCurState(stop1);   //设置阿凡达的当前状态
//		stop1.start();
//		this.addGameObject(vender); 
//		//创建碰撞区域
//		this.addGameObject(new Collision(this,new Point2D(5,310),new Point2D(20,50),"picture_2.png"));
//		this.addGameObject(new Collision(this,new Point2D(90,400),new Point2D(100,50),"picture_2.png"));
//		this.addGameObject(new Collision(this,new Point2D(120,480),new Point2D(200,300),"picture_2.png"));
//		this.addGameObject(new Collision(this,new Point2D(85,500),new Point2D(200,300),"picture_2.png"));
//		this.addGameObject(new Collision(this,new Point2D(60,740),new Point2D(100,150),"picture_2.png"));
//		this.addGameObject(new Collision(this,new Point2D(70,780),new Point2D(100,100),"picture_2.png"));
//		this.addGameObject(new Collision(this,new Point2D(80,700),new Point2D(200,300),"picture_2.png"));
//		this.addGameObject(new Collision(this,new Point2D(125,1100),new Point2D(300,500),"picture_2.png"));
//		this.addGameObject(new Collision(this,new Point2D(325,1070),new Point2D(200,300),"picture_2.png"));
//		this.addGameObject(new Collision(this,new Point2D(440,1160),new Point2D(100,100),"picture_2.png"));
//		this.addGameObject(new Collision(this,new Point2D(550,1200),new Point2D(20,30),"picture_2.png"));
//    	this.addGameObject(new Collision(this,new Point2D(660,1200),new Point2D(5,10),"picture_2.png"));
//    	this.addGameObject(new Collision(this,new Point2D(930,1200),new Point2D(20,30),"picture_2.png"));
//    	this.addGameObject(new Collision(this,new Point2D(960,875),new Point2D(100,100),"picture_2.png"));
//    	this.addGameObject(new Collision(this,new Point2D(1050,1100),new Point2D(500,500),"picture_2.png"));
//    	this.addGameObject(new Collision(this,new Point2D(1250,960),new Point2D(100,100),"picture_2.png"));
//    	this.addGameObject(new  Collision(this,new Point2D(1520,880),new Point2D(100,100),"picture_2.png"));
//    	this.addGameObject(new  Collision(this,new Point2D(1520,640),new Point2D(1,1),"picture_2.png"));
//    	this.addGameObject(new  Collision(this,new Point2D(1520,390),new Point2D(700,500),"picture_2.png"));
//    	this.addGameObject(new  Collision(this,new Point2D(1360,930),new Point2D(300,300),"picture_2.png"));
//    	this.addGameObject(new  Collision(this,new Point2D(1060,580),new Point2D(150,150),"picture_2.png"));
//    	this.addGameObject(new  Collision(this,new Point2D(1240,570),new Point2D(200,200),"picture_2.png"));
//    	this.addGameObject(new  Collision(this,new Point2D(1200,250),new Point2D(500,200),"picture_2.png"));
//    	this.addGameObject(new  Collision(this,new Point2D(1070,250),new Point2D(500,200),"picture_2.png"));
//    	this.addGameObject(new  Collision(this,new Point2D(920,200),new Point2D(500,300),"picture_2.png"));
//    	this.addGameObject(new  Collision(this,new Point2D(780,190),new Point2D(500,500),"picture_2.png"));
//    	this.addGameObject(new  Collision(this,new Point2D(630,150),new Point2D(100,100),"picture_2.png"));
//    	this.addGameObject(new  Collision(this,new Point2D(500,150),new Point2D(50,50),"picture_2.png"));
//    	this.addGameObject(new  Collision(this,new Point2D(400,120),new Point2D(80,50),"picture_2.png"));
//    	this.addGameObject(new  Collision(this,new Point2D(250,100),new Point2D(30,50),"picture_2.png"));
//    	this.addGameObject(new  Collision(this,new Point2D(130,100),new Point2D(100,80),"picture_2.png"));
//    	this.addGameObject(new  Collision(this,new Point2D(5,90),new Point2D(100,80),"picture_2.png"));
//    	
//    	//创建一棵树并加入到游戏世界中
//
//    	this.addGameObject(new Tree1(this,new Point2D(700,400),new Point2D(200,300),"tree6.png"));
//    	this.addGameObject(new Tree2(this,new Point2D(800,550),new Point2D(100,100),"picture_2.png"));
//
		}	
	// 游戏世界的渲染方法
		public void render(Graphics g) {
			// 先画场景的背景图
			g.drawImage(xImage, 0, 0, game.getWidth(), game.getHeight(), null);
			// 通知游戏世界中的游戏对象进行渲染
			for (int i = 0; i < gameObjects.size(); i++) {
				gameObjects.get(i).render(g);
			}
			// 后画场景的遮挡图
			g.drawImage(yImage, 0, 0, game.getWidth(), game.getHeight(), null);
		}

		// 游戏世界的状态更新函数，或者说是游戏仿真函数
		// 规定着游戏中的对象经过一帧后的状态变化，比如空间坐标。
		public void update() {
			// 通知游戏世界中的游戏对象进行状态更新
			for (int i = 0; i < gameObjects.size(); i++) {
				gameObjects.get(i).update();
			}
		}
		
		public void keyPressed(int key) {
			// 将键盘事件转发给玩家化身对象
			getAvatar().keyPressed(key);
		}

		public void mouseReleased(MouseEvent e) {
			getAvatar().mouseReleased(e);
		}

		public AnimPara getAnimParaByName(String name) {
			for (int i = 0; i < allAnimParas.size(); i++) {
				if (allAnimParas.get(i).name == name) {
					return allAnimParas.get(i);
				}
			}
			System.out.println("没有找到动画：" + name);
			return null;
		}

		public SoundPara getSoundParaByName(String name) {
			for (int i = 0; i < allSoundParas.size(); i++) {
				if (allSoundParas.get(i).name == name) {
					return allSoundParas.get(i);
				}
			}
			System.out.println("没有找到音效：" + name);
			return null;
		}

		public AmmoPara getAmmoParaByName(String name) {
			for (int i = 0; i < allAmmoParas.size(); i++) {
				if (allAmmoParas.get(i).name == name) {
					return allAmmoParas.get(i);
				}
			}
			System.out.println("没有找到弹药：" + name);
			return null;
		}

		// 向游戏世界中添加一个游戏对象
		public void addGameObject(GameObject g) {
			gameObjects.add(g);
		}

		// 删除一个游戏对象
		public void removeGameObject(GameObject g) {
			gameObjects.remove(g);
		}

		public ArrayList<GameObject> getAllGameObjects() {
			return gameObjects;
		}

		// 找到阿凡达对象
		public Avatar getAvatar() {
			for (int i = 0; i < gameObjects.size(); i++) {
				if (gameObjects.get(i).getClass() == Avatar.class) {
					return (Avatar) gameObjects.get(i);
				}
			}
			System.out.println("没有找到阿凡达！");
			return null;
		}
		// 找到怪物对象
			public Avatar getMonster() {
				for (int i = 0; i < gameObjects.size(); i++) {
					if (gameObjects.get(i).getClass() == Avatar.class) {
						return (Avatar) gameObjects.get(i);
					}
				}
				System.out.println("没有找到怪物！");
				return null;
			}
}