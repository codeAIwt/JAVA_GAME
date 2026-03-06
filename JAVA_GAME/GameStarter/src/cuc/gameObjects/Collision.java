package cuc.gameObjects;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import cuc.GameWorld;
import cuc.colliders.Collider;
import cuc.utils.Point2D;

public class Collision extends GameObject{
	Image image;     //形象图
	public Collision(GameWorld game,Point2D pos,Point2D size,String imageFile){
		this.gameWorld = game;
		this.pos = pos;
		this.size = size;
		//建一个圆形碰撞盒
		//设置碰撞盒半径
		float r = size.x*0.4f;   
		//设置中心点
		Point2D center = new Point2D((int)(pos.x+r*0.1f),(int)(pos.y-r/2));  
		collider = new Collider(this,center,r);			
		
		image = new ImageIcon(getClass().getClassLoader().getResource("images/"+imageFile)).getImage();	
	}
	public void render(Graphics g){	
		//渲染位置：坐标位置是图片x方向的中点
		g.drawImage(image,pos.x-size.x/2,pos.y-size.y,size.x,size.y,null);
	}
}
/*private List<Collider> colliders = new ArrayList<>(); // 存储所有碰撞盒  
Image image;

public Collider_images(GameWorld game,Point2D size,String imageFile){ 
	// 假设GameObject有一个接受GameWorld的构造函数  
	    
	this.gameWorld = game;
	this.size = size;
 //像素
	creatCollider(5,140,0.037,199.81, imageFile);//1
	creatCollider(140,440,0.800,138.00 ,imageFile);//2
	creatCollider(290,440,-0.75,712.50,imageFile);//3
	creatCollider(170,290,-2.042,1147.09,imageFile);//4
	creatCollider(170,340,0.441,720.59,imageFile);//5
	creatCollider(340,480,0.178,816.00,imageFile);//6
	creatCollider(480,590,2.000,-60.00,imageFile);//7
	creatCollider(590,710,-0.333,1316.43,imageFile);//8
	creatCollider(710,830,0.250,902.50,imageFile);//9
	creatCollider(830,880,-0.800,1774.00,imageFile);//10
	creatCollider(770,880,1.591,-330.08,imageFile);//11
	creatCollider(770,800,-4.500,4360.00,imageFile);//12
	creatCollider(800,980,-0.333,1026.40,imageFile);//13
	creatCollider(980,1050,0.857,-139.86,imageFile);//14
	creatCollider(1050,1160,0.000,760.00,imageFile);//15
	creatCollider(1160,1200,-0.750,1630.00,imageFile);//16
	creatCollider(1200,1270,0.375,280.00,imageFile);//17
	creatCollider(1270,1450,-0.528,1425.56,imageFile);//18
	creatCollider(1415,1450,2.000,-2240.00,imageFile);//19
	creatCollider(1015,1415,-0.075,696.13,imageFile);//20
	creatCollider(960,1015,0.364,250.54,imageFile);//21
	creatCollider(960,1020,-2.25,2760,imageFile);//22
	creatCollider(1020,1290,-0.315,786.35,imageFile);
	creatCollider(1090,1290,0.200,122.00,imageFile);
	creatCollider(870,1090,0.000,340.00,imageFile);
	creatCollider(540,870,0.455,-55.70,imageFile);
	creatCollider(10,540,0.075,149.25,imageFile);

}
void creatCollider(int startX, int endX, double k, double b,String imageFile) {  
    for (int x = startX; x <= endX; x ++) {  
        int y = (int) (k * x + b); 
        this.pos.x = x; this.pos.y = y; // 确保pos被正确设置  
	    if (pos == null) {  
	        throw new IllegalArgumentException("pos cannot be null");  
	    }  
        // 创建新的Point2D对象  
        float r = 10; // 或根据需要调整半径  
        Point2D center = new Point2D(this.pos.x, this.pos.y);    
        collider = new Collider(this, center, r); 

        colliders.add(collider); // 将碰撞盒添加到列表中 
        // 可能需要将collider添加到某个集合中以供后续使用  
    } 
	image = new ImageIcon(getClass().getClassLoader().getResource("images/"+imageFile)).getImage();	*/