package cuc.utils;
/*
 * 四元数，常用于代表一个矩形
 * 其中x1、y1代表左上角点坐标；
 * x2、y2可能代表右下角点坐标，也可能代表矩形的宽和高
 */
public class Quaternion {
	public int x1,y1;
	public int x2,y2;
	public Quaternion(){}
	public Quaternion(int x1,int y1,int x2,int y2){
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}
}
