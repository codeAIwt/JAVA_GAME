package cuc.gameObjects;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Graphics;
import java.util.ArrayList;

import cuc.colliders.Collider;
import cuc.utils.AmmoPara;
import cuc.utils.AnimPara;
import cuc.utils.Animation2D;
import cuc.utils.Point2D;
import cuc.utils.SoundPara;

/*
 * 弹药类
 * 有动画形象
 * 能够自己运动，运动的方式有直线运动等
 * 方向如何确定？
 * 1、发射者面朝哪里弹药就超哪里发射；
 * 2、有目标，目标在哪里就向哪里运动
 * 撞到其它游戏对象会消失
 */
public class Ammo  extends GameObject{
	AmmoPara para;   //弹药的参数对象
	
	Actor shooter;   //发射者		
	long startTime;   //开始时刻
	Point2D target;   //目标
	double targetAngle;   //目标与初始位置的夹角
	int direction;	
	
	Animation2D animation;   //弹药的动画			
	AudioClip launch;        //发射声
	AudioClip explosion;    //爆炸声
	
	public Ammo(Actor shooter,AmmoPara aPara,Point2D pos,Point2D target){
		this.shooter = shooter;
		gameWorld = shooter.gameWorld;
		para = aPara;
		this.pos = pos;	
		this.size = aPara.size;
		animation = new Animation2D(aPara.animPara); 
		//根据target的方向，找到与8个方向中最接近的那个方向
		
		if( aPara.launchSoundPara != null ){
			launch = Applet.newAudioClip(this.getClass().getClassLoader().getResource("sounds/"+aPara.launchSoundPara.fileName));
		}
		if(aPara.explosionSoundPara != null){
			explosion = Applet.newAudioClip(this.getClass().getClassLoader().getResource("sounds/"+aPara.explosionSoundPara.fileName));
		}		
		this.target = target;		
		//为弹药创建一个圆形碰撞盒
		//设置弹药的碰撞盒半径为图片宽度的四分之一
		float r = size.x/4;   
		//设置弹药的中心点位于子弹的坐标位置
		Point2D center = pos;  
		collider = new Collider(this,center,r);	
	}
	
	public void update(){
		//判断是否过了生命期		
		if( System.currentTimeMillis() - startTime >= para.lifeTime ){
			dead();			
		}else{
			//从游戏世界中获取所有的游戏对象
			ArrayList<GameObject> allGameObjects = gameWorld.getAllGameObjects();
			for(int i = 0; i < allGameObjects.size(); i++){
				GameObject go = allGameObjects.get(i);
				if( go.getClass() != Ammo.class && go.getClass() != Avatar.class && this.collide(go)){
					if(explosion != null){
						explosion.play();     //播放爆炸音效		
					}								
					go.onCollision(this);  //通知go被打到了
					gameWorld.removeGameObject(this);   //自己从游戏世界中取消					
					return;
				}				
			}
			
			if(target != null ){
				move2Target();   //朝着目标运动
			}else{
				move4Direction();
			}
		}		
	}
	public void animate(){
		animation.animate();
	}
	public void render(Graphics g){
		animation.render(g, pos, size);		
	}
	//弹药发射函数
	public void fire(){
		startTime = System.currentTimeMillis();  //记录开始时刻
		launch.play();              //播放发射音效
		if(target != null){    //如果存在目标点
			int dx = target.x - pos.x;
		    int dy = target.y - pos.y;
		    targetAngle = Math.atan2(dy, dx);  //算出目标夹角
		    //为了朝着鼠标点击位置运动，首先需要知道那个大致方向，
			//则利用Point2D的公开方法getCloestDirection计算
		    direction = Point2D.getCloestDirection(animation.animPara.row,new Point2D(dx,dy));
		    animation.setCurDirection(direction);		    
		}else{
			direction = shooter.getCurState().getCurDirection();
		}		
		gameWorld.addGameObject(this);   //将弹药对象加入到游戏世界中	
	}
	public void dead(){
		gameWorld.removeGameObject(this);
	}
	//朝着一个目标点运动
	void move2Target(){		    	    
	    int vx = (int)(para.speed*Math.cos(targetAngle));
	    int vy = (int)(para.speed*Math.sin(targetAngle));
	    
	    pos.x = pos.x + vx;
	    pos.y = pos.y + vy;	     
	}
	//沿着4方向运动的函数
    void move4Direction(){    	
    	if( direction == 0 ){   //左上运动
    		pos = new Point2D(pos.x-para.speed,pos.y-para.speed);    		    		
    	}else if( direction == 1 ){  //右上运动
    		pos = new Point2D(pos.x+para.speed,pos.y-para.speed);     		
    	}else if( direction == 2 ){  //右下运动
    		pos = new Point2D(pos.x+para.speed,pos.y+para.speed);  
    	}else if( direction == 3 ){  //左下运动
    		pos = new Point2D(pos.x-para.speed,pos.y+para.speed);  
    	}
    }
    public void setPara(AmmoPara para){
    	this.para = para;
    }
    //设置动画信息对象
    public void setAnimInfo(AnimPara ai){
    	para.animPara = ai;
    	this.animation = new Animation2D(ai);
    }    
    //设置发射音效对象
    public void setLaunchInfo(SoundPara si){
    	para.launchSoundPara = si;
    	//装载音效    	
    	launch = Applet.newAudioClip(this.getClass().getClassLoader().getResource("sounds/"+para.launchSoundPara.fileName));     	
    }
    //设置发射音效对象
    public void setExplosionInfo(SoundPara si){
    	para.explosionSoundPara = si;
    	//装载音效    	
    	explosion = Applet.newAudioClip(this.getClass().getClassLoader().getResource("sounds/"+para.explosionSoundPara.fileName));     	
    }
    public int getDamage(){
    	return para.damage;
    }
}
