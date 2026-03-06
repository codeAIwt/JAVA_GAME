package cuc.utils;
//弹药的参数对象，用于初始化弹药，目的是尽量避免硬编码
public class AmmoPara extends GPara{
	public Point2D size;  //宽高
	public int speed;    //速度
	public int lifeTime;  //存活时间
	public int damage;    //伤害值
	public AnimPara animPara;     //动画信息对象
	public SoundPara launchSoundPara;   //弹药发射音效信息
	public SoundPara explosionSoundPara;   //弹药爆炸音效信息
	
	public AmmoPara(String name,Point2D size,int speed,int lifeTime,int damage,AnimPara animPara,SoundPara launchSoundPara,SoundPara explosionSoundPara){
		this.name = name;
		this.size = new Point2D(size.x,size.y);
		this.speed = speed;
		this.lifeTime = lifeTime;
		this.damage = damage;
		this.animPara = animPara;
		this.launchSoundPara = launchSoundPara;		
		this.explosionSoundPara = explosionSoundPara;
	}
}
