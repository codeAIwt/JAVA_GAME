package cuc.gameObjects;

import java.awt.Graphics;
import java.util.ArrayList;
import cuc.GameWorld;
import cuc.colliders.Collider;
import cuc.states.ActorState;
import cuc.utils.AmmoPara;
import cuc.utils.Point2D;

//游戏角色，带状态机的游戏对象
public class Actor extends GameObject{	 	
	public int life;       //生命值	
	public AmmoPara ammoPara;    //当前武器的参数
	
	int animRow;           //多少行的动画，4或者8
	int count;      //记录死亡次数
	int temp = life;
	private float timeUntilRevive = 5.0f; // 5秒后复活  
	//所有的状态对象预先初始化并存放到这里，不再需要临时创建
	ArrayList<ActorState> allStates = new ArrayList<ActorState>();
    ActorState curState;     //当前状态        

    	
	public Actor(GameWorld game, Point2D pos, Point2D size, int life,AmmoPara ammoPara) {
		this.gameWorld = game;
    	this.pos = new Point2D(pos.x,pos.y);
    	this.size = new Point2D(size.x,size.y);
    	this.life = life;    	
    	this.ammoPara = ammoPara;
    	//为角色创建一个碰撞盒    	
    	//设置碰撞盒半径
    	float r = size.x*0.3f;  
    	//设置阿凡达的碰撞盒中心点
    	Point2D center = new Point2D((int)(pos.x),(int)(pos.y-r/2));
    	collider = new Collider(this,center,r);
	}	

	//状态更新函数
    public void update(){
    	//转发给当前状态进行更新
    	curState.update();    	
    }    
    
	void shoot(Point2D target){	
		//发射点位于角色的中心
		Point2D startPoint = new Point2D(pos.x,pos.y-20);
		Ammo ammo = new Ammo(this,ammoPara,startPoint,target);
		ammo.fire();      //让弹药开火		
	}
	//状态转换函数，参数为下一个状态对象的名字
	public void switchState(String nextState){		
		ActorState next = getStateByName(nextState);	//根据名字获取状态对象	
		next.setCurDirection(curState.getCurDirection()); //下一个状态的方向继承自上一个状态
		curState.stop();   //当前状态停止
		curState = next;   //当前状态切换到洗一个
		curState.start();   //当前状态启动		
	}
	/*
	 * 状态到期后的回调函数
	 * 让游戏角色负责决定下一个状态（而不是由状态自己决定）
	 */
	public void onStateFinish(ActorState state){}
	
	//阿凡达的渲染函数
	public void render(Graphics g){		
		curState.render(g);		
    }  
	//根据名字获取状态对象
	public ActorState getStateByName(String name){
		for(int i = 0; i < allStates.size(); i++){
			if( allStates.get(i).name == name ){
				return allStates.get(i);
			}
		}
		System.out.println("没有找到状态："+name);
		return null;
	}
	//对碰撞的反应
	public void onCollision(GameObject go){
		if(go.getClass() == Ammo.class){   //如果碰到我的是一个弹药
			Ammo a = (Ammo)go;
			
			life -= a.getDamage();    //受到伤害后的生命值
			if( life <=0 ){
				dead();   //死亡
				if(canRevive()){
					update1(1);
					//reLife();
				}
			}
		}
	}
	

	  
	// 更新方法，通常会在游戏引擎的每一帧中被调用  
	public void update1(float deltaTime) {   
	  
	    // 更新复活倒计时  
	    if (timeUntilRevive > 0) {  
	        timeUntilRevive -= deltaTime; // 从剩余时间中减去自上次更新以来的时间  
	  
	        if (timeUntilRevive <= 0) {  
	            // 时间到，执行复活逻辑  
	            revive();  
	        }  
	    }  
	  
	    // ... 其他更新逻辑  
	}  
	  
	private void revive() {  
	    // 复活逻辑  
	    gameWorld.addGameObject(this);  
	    resetState(); // 重置游戏对象的状态，如生命值、位置等  
	    timeUntilRevive = 0; // 如果不需要再次复活，重置计数器  
	    // 或者，如果你想要限制复活次数，可以在这里减少复活次数计数器  
	}  
	  
	private void resetState() {  
	    // 重置生命值
	    life = temp;  
	}


	//游戏角色死亡函数
	public void dead(){		
		gameWorld.removeGameObject(this);  //从游戏世界中移除
	}
	
	private boolean canRevive() {  
	    // 这里可以添加逻辑来检查是否可以复活，例如复活次数限制  
	    return count < 4;  
	}  
	
	public void resetReviveCount() {  
	    count = 0;  
	}
	
	//游戏角色复活函数
	/*public void reLife(){
		if (canRevive()) {  
	        count++;  
	        gameWorld.addGameObject(this);  
	        // 可以在这里添加其他复活时的初始化代码  
	    }  
	}*/
	
	//添加一个状态对象
	public void addState(ActorState state){
		this.allStates.add(state);
		animRow = state.animation.animPara.row;
	}	
	
	//提供获取与设置坐标的接口
	public Point2D getPos(){
		return pos;
	}
	public void setPos(Point2D p){
		pos = p;
	}
		
	//设置当前状态
	public void setCurState(ActorState next){
		curState = next;
	}
	public ActorState getCurState(){
		return curState;
	}
	
	public void setAmmoPara(AmmoPara para){
		ammoPara = para;
	}
}
