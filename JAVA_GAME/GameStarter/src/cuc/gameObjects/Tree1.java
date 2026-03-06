package cuc.gameObjects;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import cuc.GameWorld;
import cuc.colliders.Collider;
import cuc.utils.Point2D;
/*
 * GameObject的一个子类，代表场景中的树木
 */
public class Tree1 extends GameObject{
	Image image;     //形象图
	public Tree1(GameWorld game,Point2D pos,Point2D size,String imageFile){
		this.gameWorld = game;
		this.pos = pos;
		this.size = size;
		//为树木创建一个圆形碰撞盒
		//设置树木的碰撞盒半径
		float r = size.x*0.002f;   
		//设置树木的中心点
		Point2D center = new Point2D((int)(pos.x+r*0.1f),(int)(pos.y-r/2));  
		collider = new Collider(this,center,r);			
		
		image = new ImageIcon(getClass().getClassLoader().getResource("images/"+imageFile)).getImage();	
	}
	public void render(Graphics g){	
		//树木的渲染位置：树木的坐标位置是图片x方向的中点
		g.drawImage(image,pos.x-size.x/2,pos.y-size.y,size.x,size.y,null);
	}
}
