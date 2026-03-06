package cuc.utils;
import java.lang.Math;
//二维向量，可以代表一个二维坐标点，也可以代表一个二维向量
public class Point2D {
	public int x;
	public int y;  
	public Point2D(int x,int f){
		this.x = x;
		this.y = f;
	}	
	
	//增加一个工具函数：求两个坐标点之间的距离
	public static double distance(Point2D p1,Point2D p2){
		int deltaX = p1.x - p2.x;
		int deltaY = p1.y - p2.y;
		return Math.sqrt(deltaX * deltaX + deltaY * deltaY);
	} 
	/*
	 * 再增加一个工具函数
	 * 输入一个任意的方向，求4个方向或8个方向中与之最接近的那个方向
	 * 求余弦公式：
	 * double value = (xl*x2 + yl*y2) / (Math.sqrt(xl*xl + yl*yl)* Math.sqrt(x2*x2 +y2*y2));
	 * 第一个参数animRow是角色动画的方向数，4或8
	 * 第二个参数是一个方向
	 */
	public static int getCloestDirection(int animRow,Point2D targetDir){
		int result = 0;
    	int dx = targetDir.x;
    	int dy = targetDir.y;
    	
    	if( animRow == 8 ){
    		double[] angles = new double[8];    		    	
        	//先求与方向0：(0,-1)的夹角
        	double value0 = (dx*0 + dy*(-1)) / (Math.sqrt(dx*dx + dy*dy)* Math.sqrt(0*0 +(-1)*(-1)));//余弦值
        	angles[0] = Math.toDegrees(Math.acos(value0));    //角度0    	
        	//再求与方向1：(1,-1)的夹角
        	double value1 = (dx*1 + dy*(-1)) / (Math.sqrt(dx*dx + dy*dy)* Math.sqrt(1*1 +(-1)*(-1)));//余弦值
        	angles[1] = Math.toDegrees(Math.acos(value1));    //角度1
        	//再求与方向2：(1,0)的夹角
        	double value2 = (dx*1 + dy*0) / (Math.sqrt(dx*dx + dy*dy)* Math.sqrt(1*1 +0*0));//余弦值
        	angles[2] = Math.toDegrees(Math.acos(value2));    //角度2
        	//再求与方向3：(1,1)的夹角
        	double value3 = (dx*1 + dy*1) / (Math.sqrt(dx*dx + dy*dy)* Math.sqrt(1*1 +1*1));//余弦值
        	angles[3] = Math.toDegrees(Math.acos(value3));    //角度3
        	//再求与方向4：(0,1)的夹角
        	double value4 = (dx*0 + dy*1) / (Math.sqrt(dx*dx + dy*dy)* Math.sqrt(0*0 +1*1));//余弦值
        	angles[4] = Math.toDegrees(Math.acos(value4));    //角度4
        	//再求与方向5：(-1,1)的夹角
        	double value5 = (dx*(-1) + dy*1) / (Math.sqrt(dx*dx + dy*dy)* Math.sqrt((-1)*(-1) +1*1));//余弦值
        	angles[5] = Math.toDegrees(Math.acos(value5));    //角度5
        	//再求与方向6：(-1,0)的夹角
        	double value6 = (dx*(-1) + dy*0) / (Math.sqrt(dx*dx + dy*dy)* Math.sqrt((-1)*(-1) +0*0));//余弦值
        	angles[6] = Math.toDegrees(Math.acos(value6));    //角度6
        	//再求与方向7：(-1,-1)的夹角
        	double value7 = (dx*(-1) + dy*(-1)) / (Math.sqrt(dx*dx + dy*dy)* Math.sqrt((-1)*(-1) +(-1)*(-1)));//余弦值
        	angles[7] = Math.toDegrees(Math.acos(value7));    //角度7
        	double angle = 360;
        	for(int i = 0; i < 8; i++){
        		if(angles[i] < angle){
        			angle = angles[i];
        			result = i;
        		}
        	}
    	}else if( animRow == 4 ){   //如果是4方向动画
    		double[] angles = new double[4]; 
    		//先求与方向0：(-1,-1)的夹角
        	double value0 = (dx*(-1) + dy*(-1)) / (Math.sqrt(dx*dx + dy*dy)* Math.sqrt((-1)*(-1) +(-1)*(-1)));//余弦值
        	angles[0] = Math.toDegrees(Math.acos(value0));    //角度0
        	//再求与方向1：(1,-1)的夹角
        	double value1 = (dx*1 + dy*(-1)) / (Math.sqrt(dx*dx + dy*dy)* Math.sqrt(1*1 +(-1)*(-1)));//余弦值
        	angles[1] = Math.toDegrees(Math.acos(value1));    //角度1
        	//再求与方向2：(1,1)的夹角
        	double value2 = (dx*1 + dy*1) / (Math.sqrt(dx*dx + dy*dy)* Math.sqrt(1*1 +1*1));//余弦值
        	angles[2] = Math.toDegrees(Math.acos(value2));    //角度2
        	//再求与方向3：(-1,1)的夹角
        	double value3 = (dx*(-1) + dy*1) / (Math.sqrt(dx*dx + dy*dy)* Math.sqrt((-1)*(-1) +1*1));//余弦值
        	angles[3] = Math.toDegrees(Math.acos(value3));    //角度3
        	double angle = 360;
        	for(int i = 0; i < 4; i++){
        		if(angles[i] < angle){
        			angle = angles[i];
        			result = i;
        		}
        	}
    	}    	
    	return result;
	}
}
