package cuc.gameObjects;

import java.awt.Graphics;

import cuc.GameWorld;
import cuc.colliders.Collider;
import cuc.utils.Point2D;
/*
 * 游戏世界中所有对象的祖宗类
 * 有位置和大小，能够参与碰撞
 * 这里的方法大多都是钩子函数，留给子类实现
 */
public class GameObject {
	public GameWorld gameWorld;      //反向引用着游戏世界    
	public Point2D pos;              //游戏对象在游戏世界中的二维坐标
	public Point2D size;             //游戏对象在游戏世界里的宽和高
	
	Collider collider;      //游戏对象的碰撞盒
	//钩子函数：空函数，留给子类实现
	public void update(){
	  	
	}  
	//钩子函数：空函数，留给子类实现
	public void render(Graphics g){		
				
	}
	//判断是否与另一个游戏对象发生碰撞
	public boolean collide(GameObject go){	
		//交给碰撞盒处理
		return collider.collide(go.getCollider());
	}
	//对碰撞的反应
	public void onCollision(GameObject go){
		
	}
	public Point2D getPos(){
		return pos;
	}
	public void setPos(Point2D p){
		pos = p;
	}
	public Point2D getSize(){
		return size;
	}
	public void setSize(Point2D s){
		size = s;
	}
	//获取碰撞盒
	public Collider getCollider(){
		return collider;
	}
	public void setCollider(Collider c){
		collider = c;
	}
	
}
