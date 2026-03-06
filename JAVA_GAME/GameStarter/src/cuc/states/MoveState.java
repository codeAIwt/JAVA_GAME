package cuc.states;

import java.util.ArrayList;

import cuc.gameObjects.Actor;
import cuc.gameObjects.GameObject;
import cuc.utils.Animation2D;
/*
 * 运动状态：
 * 其特殊之处在于：在move方法中添加了碰撞检测逻辑
 */
public class MoveState extends ActorState {

	public MoveState(Actor awatar, float maxLifeTime, int speed, Animation2D animation, String soundFile) {
		super(awatar, maxLifeTime, speed, animation, soundFile);
		// TODO Auto-generated constructor stub
		name = "move";   //设置名字
	}
		
	//重写父类的move方法，添加碰撞检测逻辑，目的是防止阿凡达穿过障碍物
	public void move(){			
		//先从游戏世界中获取所有的游戏对象
		ArrayList<GameObject> allGameObjects = host.gameWorld.getAllGameObjects();
		for(int i = 0; i < allGameObjects.size(); i++){  //遍历游戏世界中的对象
			GameObject go = allGameObjects.get(i);
			if( this.host != go   //排除自己碰撞自己
				&& this.host.collide(go) ){  //碰上了
				this.back();    //后退一步，离开双方碰撞盒的碰撞范围，不然会走不动
				host.onStateFinish(this);   //回调宿主请求处理				
				return;
			}			
		}
		//如果执行到这里，说明没有碰撞障碍物，则继续行走
		super.move();
	}
}
