package cuc.colliders;

import cuc.gameObjects.GameObject;
import cuc.utils.Point2D;

/*
 * 碰撞盒类，附着在游戏对象上，代表着游戏对象的可碰撞的形状
 * 在这里，我们先实现一个计算最简单的圆圈形状的碰撞盒，
 * 只需要计算两个圆圈碰撞盒的中心距离，并与两个碰撞体的半径之和对比即可
*/
public class Collider {
	GameObject host;   //碰撞盒的宿主对象
	Point2D center;    //碰撞盒的中心点
	float radius;      //撞盒的半径
	//构造函数，传入的参数为宿主对象、中心点和半径
	public Collider(GameObject host,Point2D center,float radius){
		this.host = host;		
		this.center = center;   
		this.radius = radius;   	
	}
	//调整碰撞盒半径
	public void setRadius(float r){
		radius = r;
	}
	//判断是否与另一个碰撞盒发生碰撞
	public boolean collide(Collider another){
		//因为碰撞盒的宿主会运动，所以需要再次计算碰撞盒的中心，让碰撞盒跟上宿主对象		
		center = host.pos;
		//计算两者之间的距离
		double distance = Math.sqrt((center.x-another.center.x)*(center.x-another.center.x)
						+(center.y-another.center.y)*(center.y-another.center.y));
		if(distance <= radius+another.radius){  //如果两者的距离小于两者的半径之和
			return true;
		}else{
			return false;
		}
	}
	
}