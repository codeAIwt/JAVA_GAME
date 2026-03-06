package cuc.utils;
//一个动画大图的关键参数
//目的是尽量避免硬编码
public class AnimPara extends GPara{	
	public String fileName;         //动画大图的文件名
	public int col,row;             //动画大图中包含的行和列数
	/*
	 * 动画大图中的每一行就是一个方向的动画。
	 * 但是，我们能找到的8方向的动画大图，它们的每一行未必是按照0、1、2、3、...7这个顺序排列的。
	 * 所以我们必须指出一个动画资源的每一行是哪个方向的，才能根据方向找到对应的一行。
	 * 比如在8x8的“土匪动画”中，从第一行到第八行的动画分别代表的方向是：
	 * {4,6,2,0,5,3,7,1}
	 * 我们用一个数组保存这个信息
	 */
	public int[] aids;              //动画大图中行朝向的方向
	public AnimPara(String name,String animFile,int col,int row,int[] aids){
		this.name = name;
		this.fileName = animFile;
		this.col = col;
		this.row = row;
		this.aids = aids;
	}
}
