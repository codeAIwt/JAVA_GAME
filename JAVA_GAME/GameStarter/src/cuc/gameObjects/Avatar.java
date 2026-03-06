package cuc.gameObjects;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import cuc.GameWorld;
import cuc.states.ActorState;
import cuc.states.MoveState;
import cuc.states.StopState;
import cuc.utils.Point2D;
import cuc.utils.AmmoPara;

//特殊的游戏角色：可以被玩家控制，玩家化身阿凡达
public class Avatar extends Actor{
	
	public Avatar(GameWorld gameWorld, Point2D pos, Point2D size,int life,AmmoPara ammoPara) {
		super(gameWorld, pos, size, life,ammoPara);
		// TODO Auto-generated constructor stub
	}


	/*
	 * 状态到期后的回调函数
	 * 行走到期后转向静止
	 * 跑步到期后转向静止
	 */
	public void onStateFinish(ActorState state){
		if(state.getClass() == MoveState.class){  //行走到期后转向静止
			this.switchState("stop");
		}
	}
	/*
	 * 处理玩家的键盘命令：
	 * wasd键：驱动运动，同时支持4方向和8方向动画
	 * space键：开火，弹药方向与阿凡达当前的朝向一致
	 * R键：转向跑步状态
	 */
	public void keyPressed(int key) {
		if( key == KeyEvent.VK_SPACE ){
			shoot(null);  
		}else if( key == KeyEvent.VK_W ){  //按下w键
			if( curState.getClass() == StopState.class ){   //如果当前状态为静止状态
				if(animRow == 4){
					curState.setCurDirection(0);  //设置状态的当前方向为左上方			
					this.switchState("move");       //请求阿凡达转换状态到MoveState
				}else if( animRow == 8){
					curState.setCurDirection(0);  //设置状态的当前方向为正上方			
					this.switchState("move");       //请求阿凡达转换状态到MoveState 
				}
			}else if( curState.getClass() == MoveState.class ){   //如果当前状态为行走状态
				if(animRow == 4){
					curState.setCurDirection(0);  //设置状态的当前方向为左上方			
					this.switchState("move");       //请求阿凡达转换状态到MoveState
				}else if( animRow == 8){
					curState.setCurDirection(0);  //设置状态的当前方向为正上方			
					this.switchState("move");       //请求阿凡达转换状态到MoveState 
				}
			}
		}else if( key == KeyEvent.VK_D ){
			if( curState.getClass() == StopState.class ){   //如果当前状态为静止状态
				if(animRow == 4){
					curState.setCurDirection(1);  //设置状态的当前方向为右上方			
					this.switchState("move");       //请求阿凡达转换状态到MoveState
				}else if( animRow == 8){
					curState.setCurDirection(2);  //设置状态的当前方向为右正方			
					this.switchState("move");       //请求阿凡达转换状态到MoveState 
				}
			}else if( curState.getClass() == MoveState.class ){   //如果当前状态为行走状态
				if(animRow == 4){
					curState.setCurDirection(1);  //设置状态的当前方向为右上方			
					this.switchState("move");       //请求阿凡达转换状态到MoveState
				}else if( animRow == 8){
					curState.setCurDirection(2);  //设置状态的当前方向为右正方			
					this.switchState("move");       //请求阿凡达转换状态到MoveState 
				}
			}
		}else if( key == KeyEvent.VK_S ){
			if( curState.getClass() == StopState.class ){   //如果当前状态为静止状态
				if(animRow == 4){
					curState.setCurDirection(2);  //设置状态的当前方向为右下方			
					this.switchState("move");       //请求阿凡达转换状态到MoveState
				}else if( animRow == 8){
					curState.setCurDirection(4);  //设置状态的当前方向为下正方			
					this.switchState("move");       //请求阿凡达转换状态到MoveState 
				}
			}else if( curState.getClass() == MoveState.class ){   //如果当前状态为行走状态
				if(animRow == 4){
					curState.setCurDirection(2);  //设置状态的当前方向为右下方			
					this.switchState("move");       //请求阿凡达转换状态到MoveState
				}else if( animRow == 8){
					curState.setCurDirection(4);  //设置状态的当前方向为下正方			
					this.switchState("move");       //请求阿凡达转换状态到MoveState 
				}
			}
		}else if( key == KeyEvent.VK_A ){
			if( curState.getClass() == StopState.class ){   //如果当前状态为静止状态
				if(animRow == 4){
					curState.setCurDirection(3);  //设置状态的当前方向为左下方			
					this.switchState("move");       //请求阿凡达转换状态到MoveState
				}else if( animRow == 8){
					curState.setCurDirection(6);  //设置状态的当前方向为左正方			
					this.switchState("move");       //请求阿凡达转换状态到MoveState 
				}
			}else if( curState.getClass() == MoveState.class ){   //如果当前状态为行走状态
				if(animRow == 4){
					curState.setCurDirection(3);  //设置状态的当前方向为左下方			
					this.switchState("move");       //请求阿凡达转换状态到MoveState
				}else if( animRow == 8){
					curState.setCurDirection(6);  //设置状态的当前方向为左正方			
					this.switchState("move");       //请求阿凡达转换状态到MoveState 
				}
			}
		}			
	}
	/*
	 * 处理鼠标事件
	 * 鼠标右键：朝着鼠标点击的位置开火
	 * 鼠标左键：朝着鼠标点击方向运动。同时支持4方向和8方向动画，让动画与运动方向最接近。
	 */
	public void mouseReleased(MouseEvent e) {
		if( e.isMetaDown() ){  //检测鼠标右键单击			
			shoot(new Point2D(e.getX(),e.getY()));  //朝着鼠标点击的位置开火
		}else if( e.getButton() == MouseEvent.BUTTON1 ){  //鼠标左键点击，则朝着鼠标点击位置运动
			Point2D target = new Point2D(e.getX(),e.getY());
			//为了朝朝着鼠标点击位置运动，首先需要知道那个大致方向，
			//则利用Point2D的公开方法getCloestDirection计算
			int deirection = Point2D.getCloestDirection(curState.animation.animPara.row,new Point2D(target.x-pos.x,target.y-pos.y));
			if( curState.getClass() == StopState.class ){   //如果当前状态为静止状态
				curState.setCurDirection(deirection);  //设置状态的当前方向			
				this.switchState("move");       //请求阿凡达转换状态到MoveState 
			}else if( curState.getClass() == MoveState.class ){   //如果当前状态为行走状态
				curState.setCurDirection(deirection);  //设置状态的当前方向			
				this.switchState("move");       //请求阿凡达转换状态到MoveState 
			}
		}
	}	
}
