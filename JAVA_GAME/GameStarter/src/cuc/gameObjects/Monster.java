package cuc.gameObjects;

import java.awt.event.MouseEvent;
import java.util.Random;

import cuc.GameWorld;
import cuc.states.ActorState;
import cuc.states.MoveState;
import cuc.states.StopState;
import cuc.utils.AmmoPara;
import cuc.utils.Point2D;

/*
 * 创建一个Actor的子类：货郎类。
 * 这是一个NPC，这里定义这个NPC的行为：
 * 无人时随机游走，有人时停下来面向来人
 */

public class Monster extends Actor{
	Avatar avatar;    //书生需要知道阿凡达在哪里
	int count = 0;   //书生运动次数
	public int range;   //书生的反应范围：当阿凡达进入这个范围时做出反应
	
	public Monster(GameWorld game, Point2D pos, Point2D size, int life, AmmoPara ammoPara) {
		super(game, pos, size, life, ammoPara);

		avatar = gameWorld.getAvatar();   //向游戏世界获取阿凡达对象
		range = 200;       //设置反应距离
	}
	//重写状态更新函数，添加独特的行为逻辑
    public void update(){
    	//检查阿凡达是否靠近自己
    	if( Point2D.distance(this.pos, avatar.pos) <= range ){        		
    		if(this.curState.name != "stop"){   
    			//为了面朝阿凡达，首先需要知道阿凡达的大致方向，
    			//则利用Point2D的公开方法getCloestDirection计算
    			
    			int deirection = Point2D.getCloestDirection(curState.animation.animPara.row,new Point2D(avatar.pos.x-pos.x,avatar.pos.y-pos.y));
    			curState.setCurDirection(deirection);  //面朝阿凡达
    			//shoot(avatar.getPos());
    			switchState("stop");   //转向静止状态
    			count--;
    			
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
		//当书生的静止状态结束后，固定一个方向，让书生转入行走状态
		if(state.name == "stop"){
			//巡逻
			// 生成一个在[0, animRow)范围内的随机整数
			//Random random = new Random();				
			//int randomNumber = random.nextInt(animRow);
			if(count%6 == 0||count%6 == 1||count%6 == 2){
				this.curState.setCurDirection(0);		
				this.switchState("move");	//转向行走状态	
			}else if(count%6 == 3||count%6 == 4||count%6 ==5){
				this.curState.setCurDirection(2);		
				this.switchState("move");	//转向行走状态	
			}
			count++;
			
		}else if(state.name == "move" )	{   //如果结束的状态是行走状态，则转向静止状态			
			this.switchState("stop");
		}
	}
	/*public void mouseReleased(MouseEvent e) {
		if( e.isMetaDown() ){  //检测鼠标右键单击			
			shoot(new Point2D(e.getX(),e.getY()));  //朝着鼠标点击的位置开火
		}else if( e.getButton() == MouseEvent.BUTTON1 ){  //鼠标左键点击，则朝着鼠标点击位置运动
			Point2D target = new Point2D(e.getX(),e.getY());
			//为了朝朝着鼠标点击位置运动，首先需要知道那个大致方向，
			//则利用Point2D的公开方法getCloestDirection计算
			int deirection = Point2D.getCloestDirection(curState.animation.animPara.row,new Point2D(target.x-pos.x,target.y-pos.y));
			if( curState.getClass() == StopState.class ){   //如果当前状态为静止状态
				curState.setCurDirection(deirection);  //设置状态的当前方向			
				this.switchState("move");       //请求书生转换状态到MoveState 
			}else if( curState.getClass() == MoveState.class ){   //如果当前状态为行走状态
				curState.setCurDirection(deirection);  //设置状态的当前方向			
				this.switchState("move");       //请求书生转换状态到MoveState 
			}
		}
	}
*/
}