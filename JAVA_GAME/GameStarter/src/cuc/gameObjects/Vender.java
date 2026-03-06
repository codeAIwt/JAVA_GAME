package cuc.gameObjects;

import java.util.Random;

import cuc.GameWorld;
import cuc.states.ActorState;
import cuc.utils.AmmoPara;
import cuc.utils.MyGui;
import cuc.utils.Point2D;
import cuc.utils.ShoppingApp;

/*
 * 创建一个Actor的子类：货郎类。
 * 这是一个NPC，这里定义这个NPC的行为：
 * 无人时随机游走，有人时停下来面向来人
 */

public class Vender extends Actor{
	Avatar avatar;    //货郎需要知道阿凡达在哪里
	
	public int range;   //货郎的反应范围：当阿凡达进入这个范围时做出反应
	public Vender(GameWorld game, Point2D pos, Point2D size, int life, AmmoPara ammoPara) {
		super(game, pos, size, life, ammoPara);

		avatar = gameWorld.getAvatar();   //向游戏世界获取阿凡达对象
		range = 200;       //设置反应距离
	}
	//重写状态更新函数，添加货郎独特的行为逻辑
    public void update(){
    	 // 添加一个标志来跟踪是否已经创建了 MyGui  
        boolean myGuiCreated = false; 
    	//检查阿凡达是否靠近自己
    	if( Point2D.distance(this.pos, avatar.pos) <= range ){        		
    		if(this.curState.name != "stop"){   
    			//为了面朝阿凡达，首先需要知道阿凡达的大致方向，
    			//则利用Point2D的公开方法getCloestDirection计算
    			int deirection = Point2D.getCloestDirection(curState.animation.animPara.row,new Point2D(avatar.pos.x-pos.x,avatar.pos.y-pos.y));
    			curState.setCurDirection(deirection);  //面朝阿凡达
    			
    			switchState("stop");   //转向静止状态
    			// 如果还没有创建 MyGui，则创建它   
    	    	if (!myGuiCreated && Point2D.distance(this.pos, avatar.pos) <= range/2 && ShoppingApp.isPurchased == false) {  
    	            MyGui.showIfAllowed();  
    	            myGuiCreated = true; // 设置标志为已创建 
    	        }
    	    	
    			
    		} 
          
    	}
    	//转发给当前状态进行更新
    	curState.update();    	
    }  
   
    
    /*
	 * 重写这个状态到期后的回调函数
	 * 让游戏角色负责决定下一个状态（而不是由状态自己决定）
	 */
	public void onStateFinish(ActorState state){		
		//当货郎的静止状态结束后，随机选择一个方向，让货郎转入行走状态
		if(state.name == "stop"){
			// 生成一个在[0, animRow)范围内的随机整数
			Random random = new Random();				
			int randomNumber = random.nextInt(animRow);
			//转向随机的新方向	
			this.curState.setCurDirection( randomNumber);		
			this.switchState("move");	//转向行走状态		
		}else if(state.name == "move" )	{   //如果结束的状态是行走状态，则转向静止状态			
			this.switchState("stop");
		}
	}
}
