package cuc.utils;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

/*
 * 动画类
 * 封装了二维动画的功能
 */
public class Animation2D {
	public AnimPara animPara;      //动画大图的关键信息	
	
	Image animImage;   //游戏角色的动画合集图像，简称动画大图		
	int aWidth,aHeight;   //动画小图的宽和高	
	int curFrame;	        //当前的动画帧
	int curFramePlayNum;    //当前动画帧已经播放的次数
	int afpn;              //一个动画帧播放的最大次数
	/*
	 * 如果游戏角色有8个方向，分别用一个整数代表：
	 * 正上方：0
	 * 右上方：1
	 * 右正方：2
	 * 右下方：3
	 * 下正方：4
	 * 左下方：5
	 * 左正方：6
	 * 左上方：7
	 */
	int curDirection;   //动画的当前方向	
	
	public Animation2D(AnimPara animInfo){
		this.animPara = animInfo;
				
		afpn = 1;		
		animImage = new ImageIcon(getClass().getClassLoader().getResource("images/"+animInfo.fileName)).getImage();		
		aWidth = animImage.getWidth(null)/animInfo.col;
		aHeight = animImage.getHeight(null)/animInfo.row;		
	}
	//播放角色动画函数
    public void animate(){
    	curFramePlayNum++;   
    	if( curFramePlayNum > afpn ){
    		curFrame = (curFrame+1)%animPara.col;   //换成下一帧动画
    		curFramePlayNum = 0;
    	} 
    }   
    //渲染当前动画帧。参数pos和size是动画帧渲染到屏幕上的位置和宽高
    //值得注意的是：参数pos是游戏角色的脚下位置，所以渲染其形象需要找到左上角(pos.x-size.x/2,pos.y-size.y)
    public void render(Graphics g,Point2D pos,Point2D size){
    	Quaternion curAnimFrame = getCurAnimFrame();
    	g.drawImage( animImage,
			         pos.x-size.x/2,pos.y-size.y,pos.x+size.x/2,pos.y,
			         curAnimFrame.x1,curAnimFrame.y1,curAnimFrame.x2,curAnimFrame.y2,
		             null);  
    }
    //得到当前要渲染的动画帧在动画大图中的位置
    public Quaternion getCurAnimFrame(){
    	Quaternion result = new Quaternion();
    	result.x1 = curFrame*aWidth;
    	result.x2 = curFrame*aWidth+aWidth;
    	//根据当前方向curDirection和aids，找到当前帧左上角的y坐标
    	int index = findIndexFromCurDirection();   
    	result.y1 = index * aHeight;
    	result.y2 = index * aHeight + aHeight;
    	return result;
    }    
   
    //在aids数组中找到当前方向所在的数组序号
    int findIndexFromCurDirection(){
    	for(int i = 0; i < animPara.aids.length; i++){
    		if( animPara.aids[i] == curDirection ){
    			return i;
    		}
    	}
    	//System.out.println("当前方向没有找到动画："+animInfo.fileName+" "+curDirection);
    	return 0;
    }
    public void setCurDirection(int d){
    	curDirection = d;
    }
}
