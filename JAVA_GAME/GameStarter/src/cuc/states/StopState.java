package cuc.states;

import cuc.gameObjects.Actor;
import cuc.utils.Animation2D;

public class StopState extends ActorState {

	public StopState(Actor awatar, float maxLifeTime, int speed, Animation2D animation, String soundFile) {
		super(awatar, maxLifeTime, speed, animation, soundFile);
		// TODO Auto-generated constructor stub
		name = "stop";   //设置名字
		curDirection = 0;  //设置初始方向
	}		
}
