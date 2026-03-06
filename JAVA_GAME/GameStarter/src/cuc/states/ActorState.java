package cuc.states;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Graphics;
import cuc.gameObjects.Actor;
import cuc.utils.Animation2D;
import cuc.utils.Point2D;

//游戏角色的抽象状态
public class ActorState {
	Actor host;             //反向引用游戏角色
	public String name;       //状态的名字
	long startTime;          //当前状态的开始时刻
	float maxLifeTime;         //状态持续最长时间
	int speed;                 //运动速度
	int speedXY;               //运动速度在xy轴的投影         
	int curDirection;          
	
	public Animation2D animation;    //状态的动画	
	AudioClip sound;        //状态音效
	
	public ActorState(Actor awatar,float maxLifeTime,int speed,Animation2D animation,String soundFile){
		this.host = awatar;		
		this.maxLifeTime = maxLifeTime;
		this.speed = speed;
		this.animation = animation;
		speedXY = (int)(speed/Math.sqrt(2));
		//装载状态音效
		ClassLoader classLoader = this.getClass().getClassLoader(); 
		sound = Applet.newAudioClip(classLoader.getResource("sounds/"+soundFile)); 
	}
	//状态启动函数
	public void start(){
		setStartTime(System.currentTimeMillis());   //设置状态的开始时刻
		if( sound != null){  
			sound.loop();   //播放状态配音
		}		
	}
	//状态停止函数
	public void stop(){
		if( sound != null){
			sound.stop();   //停止状态配音
		}
	}
	//更新函数
	public void update(){
		if( System.currentTimeMillis() - startTime >= maxLifeTime ){ //如果本状态时间到了			
			host.onStateFinish(this);   //回调宿主请求处理
    	}else{
    		animation.animate();  //播放角色动画       	            	
    		move();               //移动	
    	}			
	}	
	
    /*
     * 运动函数
     * 同时支持4方向运动和8方向运动
     */
    void move(){
    	int animRow = animation.animPara.row;   //看看是4方向还是8方向
    	if( animRow == 4 ){
    		if( curDirection == 0 ){   //左上运动
        		host.setPos(new Point2D(host.getPos().x-speedXY,host.getPos().y-speedXY));    		    		
        	}else if( curDirection == 1 ){  //右上运动
        		host.setPos(new Point2D(host.getPos().x+speedXY,host.getPos().y-speedXY));     		
        	}else if( curDirection == 2 ){  //右下运动
        		host.setPos(new Point2D(host.getPos().x+speedXY,host.getPos().y+speedXY)); 
        	}else if( curDirection == 3 ){  //左下运动
        		host.setPos(new Point2D(host.getPos().x-speedXY,host.getPos().y+speedXY)); 
        	}
    	}else if( animRow == 8 ){
    		if( curDirection == 0 ){   //正上
        		host.setPos(new Point2D(host.getPos().x,host.getPos().y-speed));    		    		
        	}else if( curDirection == 1 ){  //右上
        		host.setPos(new Point2D(host.getPos().x+speedXY,host.getPos().y-speedXY));     		
        	}else if( curDirection == 2 ){  //右正
        		host.setPos(new Point2D(host.getPos().x+speed,host.getPos().y)); 
        	}else if( curDirection == 3 ){  //右下
        		host.setPos(new Point2D(host.getPos().x+speedXY,host.getPos().y+speedXY)); 
        	}else if( curDirection == 4 ){  //下正
        		host.setPos(new Point2D(host.getPos().x,host.getPos().y+speedXY)); 
        	}else if( curDirection == 5 ){  //左下
        		host.setPos(new Point2D(host.getPos().x-speedXY,host.getPos().y+speedXY)); 
        	}else if( curDirection == 6 ){  //左正
        		host.setPos(new Point2D(host.getPos().x-speed,host.getPos().y)); 
        	}else if( curDirection == 7 ){  //左上
        		host.setPos(new Point2D(host.getPos().x-speedXY,host.getPos().y-speedXY)); 
        	}
    	}    	
    }
    //倒退函数，用于碰撞其它物体时后退一步
  	public void back(){
  		int animRow = animation.animPara.row;   //看看是4方向还是8方向
    	if( animRow == 4 ){
    		if( curDirection == 0 ){   //左上运动
        		host.setPos(new Point2D(host.getPos().x+speedXY,host.getPos().y+speedXY));    		    		
        	}else if( curDirection == 1 ){  //右上运动
        		host.setPos(new Point2D(host.getPos().x-speedXY,host.getPos().y+speedXY));     		
        	}else if( curDirection == 2 ){  //右下运动
        		host.setPos(new Point2D(host.getPos().x-speedXY,host.getPos().y-speedXY)); 
        	}else if( curDirection == 3 ){  //左下运动
        		host.setPos(new Point2D(host.getPos().x+speedXY,host.getPos().y-speedXY)); 
        	}
    	}else if( animRow == 8 ){
    		if( curDirection == 0 ){   //正上
        		host.setPos(new Point2D(host.getPos().x,host.getPos().y+speed));    		    		
        	}else if( curDirection == 1 ){  //右上
        		host.setPos(new Point2D(host.getPos().x-speedXY,host.getPos().y+speedXY));     		
        	}else if( curDirection == 2 ){  //右正
        		host.setPos(new Point2D(host.getPos().x-speed,host.getPos().y)); 
        	}else if( curDirection == 3 ){  //右下
        		host.setPos(new Point2D(host.getPos().x-speedXY,host.getPos().y-speedXY)); 
        	}else if( curDirection == 4 ){  //下正
        		host.setPos(new Point2D(host.getPos().x,host.getPos().y-speedXY)); 
        	}else if( curDirection == 5 ){  //左下
        		host.setPos(new Point2D(host.getPos().x+speedXY,host.getPos().y-speedXY)); 
        	}else if( curDirection == 6 ){  //左正
        		host.setPos(new Point2D(host.getPos().x+speed,host.getPos().y)); 
        	}else if( curDirection == 7 ){  //左上
        		host.setPos(new Point2D(host.getPos().x+speedXY,host.getPos().y+speedXY)); 
        	}
    	}
  	}
    //阿凡达的渲染函数
  	public void render(Graphics g){	
  		//转交动画对象渲染
  		animation.render(g, host.getPos(),host.getSize());      
    }     	
  	//设置状态开始时刻
  	public void setStartTime(long time){
  		startTime = time;
  	}
  	//设置状态的方向
  	public void setCurDirection(int d){
  		curDirection = d;
  		animation.setCurDirection(d);  //设置动画的当前方向
  	}
  	//获得当前方向
  	public int getCurDirection(){
  		return curDirection;
  	}
  	//播放状态配音
  	public void playSound(){
  		sound.loop();
  	}
  	//停止状态配音
  	public void stopSound(){
  		sound.stop();
  	}
}
