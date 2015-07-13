package tankgame1;

import java.util.Vector;

class tank{
	boolean islive=true;
	//坐标
	int x=0;
	int y=0;
	int color;//坦克的颜色
	int direction=0;//坦克的方向
	int speed=2;//坦克的速度
	public int getColor() {
		return color;
	}
	public void setColor(int color) {
		this.color = color;
	}
	public int getDirection() {
		return direction;
	}
	public void setDirection(int direction) {
		this.direction = direction;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public tank(int x,int y){
		this.x=x;
		this.y=y;
		
	}
	
}
//自己的坦克
class mytank extends tank{
    shot s1=null;
    Vector<shot>  ss=new Vector<shot>();
	public mytank(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}
	public void up(){
		y-=this.speed;
		this.direction=0;
	}
	public void shotenmey(int x,int y,int dir){
		switch (this.direction) {
		case 0:s1=new shot(x, y-15,0);
		       ss.add(s1);
	            break;
		case 1:s1=new shot(x+15, y,1);
		       ss.add(s1);
		        break;
		case 2:s1=new shot(x, y+15, 2);
		        ss.add(s1);
		break;
		case 3:s1=new shot(x-15, y, 3);
		       ss.add(s1);
		default:
			break;
		}
		Thread t1=new Thread(s1);
		t1.start();
	}
	public void down(){
		y+=this.speed;
		this.direction=2;
	}
	public void right(){
		x+=this.speed;
		this.direction=1;
	}
	public void left(){
		x-=this.speed;
		this.direction=3;
	}
}
//敌人的坦克
class enmytank extends tank implements Runnable{
	 boolean touched=false;
	 //可以访问面板上所有的坦克矢量
	        Vector<enmytank> etc=new Vector<enmytank>();
			Vector<shot> enmy_shot=new Vector<shot>();
	public enmytank(int x, int y) {
		super(x, y);
		}
	//获取面板上的坦克数量
     public void Set_etc(Vector<enmytank> tt)
     {
    	 this.etc=tt;
     }
     //判断是否碰到其他坦克
     public boolean Istouched()
     {

    	 switch (this.direction) {
		case 0:
			for(int i=0;i<etc.size();i++)
			{
				enmytank ta=etc.get(i);
				if(ta!=this)
				{
					if(ta.direction==0||ta.direction==2)
						//判断左端点和右端点是否在其他坦克内
						if(this.y-15<ta.y+15&&this.y-15>ta.y-15&&((this.x-10>ta.x-10&&this.x-10<ta.x+10)||(this.x+10>ta.x-10&&this.x+10<ta.x+10)))
						{
							System.out.println("坦克向上运动时碰撞");
							return true;
							}
			
					else {
						if(this.y-15<ta.y+10&&this.y-15>ta.y+10&&((this.x-10>ta.x-15&&this.x-10<ta.x+15)||(this.x+10>ta.x-15&&this.x+10<ta.x+15)))
						{
							System.out.println("坦克向上运动时碰撞");
							return true;
							}
					}
				}
			}
			break;
		case 1:
			for(int i=0;i<etc.size();i++)
			{
				enmytank ta=etc.get(i);
				if(ta!=this)
				{
					if(ta.direction==0||ta.direction==2)
						//其他坦克向上或向下时判断是否相碰
						if(this.x+15<ta.x+10&&this.x+15>ta.x-10&&((this.y-10>ta.y-15&&this.y-10<ta.y+15)||(this.y+10>ta.y-15&&this.y+10<ta.x+15)))
							{
							System.out.println("坦克向右运动时碰撞");
							return true;
							}
			
					else {
						if(this.x+15<ta.x+15&&this.x+15>ta.x-15&&((this.y-10>ta.y-10&&this.y-10<ta.y+10)||(this.y+10>ta.y-10&&this.y+10<ta.y+10)))
							{
							System.out.println("坦克向右运动时碰撞");
							return true;
							}
							
					}
				}
			}
			break;
		case 2:
			for(int i=0;i<etc.size();i++)
			{
				enmytank ta=etc.get(i);
				if(ta!=this)
				{
					if(ta.direction==0||ta.direction==2)
						//判断左端点和右端点是否在其他坦克内
						if(this.y+15<ta.y+15&&this.y+15>ta.y-15&&((this.x-10>ta.x-10&&this.x-10<ta.x+10)||(this.x+10>ta.x-10&&this.x+10<ta.x+10)))
						{
							System.out.println("坦克向下运动时碰撞");
							return true;
						}
			
					else {
						if(this.y+15<ta.y+10&&this.y+15>ta.y+10&&(this.x-10>ta.x-15&&this.x-10<ta.x+15)||(this.x+10>ta.x-15&&this.x+10<ta.x+15))
						{
							System.out.println("坦克向下运动时碰撞");
							return true;
							}
					}
				}
			}
			break;
		case 3:
			for(int i=0;i<etc.size();i++)
			{
				enmytank ta=etc.get(i);
				if(ta!=this)
				{
					if(ta.direction==0||ta.direction==2)
						//其他坦克向上或向下时判断是否相碰
						if(this.x-15<ta.x+10&&this.x-15>ta.x-10&&((this.y-10>ta.y-15&&this.y-10<ta.y+15)||(this.y+10>ta.y-15&&this.y+10<ta.x+15)))
						{
							System.out.println("坦克向左运动时碰撞");
							return true;
							}
			
					else {
						if(this.x-15<ta.x+15&&this.x-15>ta.x-15&&((this.y-10>ta.y-10&&this.y-10<ta.y+10)||(this.y+10>ta.y-10&&this.y+10<ta.y+10)))
						{
							System.out.println("坦克向左运动时碰撞");
							return true;
							}
					}
				}
			}
			break;
		}
    	 return false;
     }
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true)
		{
			switch(this.direction)
			{
			case 0:
				for(int i=0;i<30;i++)
				{
				try {
					Thread.sleep(30);
				} catch (Exception e) {
					// TODO: handle exception
				}
				if(y>30&&!Istouched())
				y-=speed;
			System.out.println(Istouched());
				}
			break;
			case 1:
				for(int i=0;i<30;i++)
				{
				try {
				Thread.sleep(50);
			} catch (Exception e) {
				// TODO: handle exception
			}
				if(x<370&&!Istouched())
				x+=speed;
				}
			break;
			case 2:
				for(int i=0;i<30;i++)
				{
				try {
				Thread.sleep(50);
			} catch (Exception e) {
				// TODO: handle exception
			}
				if(y<270&&!Istouched())
				y+=speed;
				}
			break;
			case 3:
				for(int i=0;i<30;i++)
				{
				try {
					Thread.sleep(50);
				} catch (Exception e) {
					// TODO: handle exception
				}
				if(x>15&&!Istouched())
				x-=speed;
				}
			break;
			}
			this.direction=(int) (Math.random()*4);
			if(islive==false)
				break;
			//判断每辆坦克的子弹是否打完
			
				if(this.islive==true)
				{
					for(int j=0;j<this.enmy_shot.size();j++)
					{
						shot s=this.enmy_shot.get(j);
						if(s.islive==false)
							this.enmy_shot.remove(s);
					}
					if(this.enmy_shot.size()<5)
					{
						shot sh=new shot(this.x, this.y, this.direction);
						this.enmy_shot.add(sh);
						Thread t_sh=new Thread(sh);
		    			t_sh.start();	
					
					}
				}
			
		}
	}
	
}
class shot implements Runnable{
	int x,y,direct;
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	int speed=5;
	//
	public boolean islive=true;

	public shot(int x,int y,int dir){
		this.x=x;
		this.y=y;
		this.direct=dir;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true){
		try {
			Thread.sleep(50);
		} catch (Exception e) {
			// TODO: handle exception
		}
		switch (direct) {
		case 0:this.y-=this.speed;
			   break;
		case 1:this.x+=this.speed;
                break;
		case 2:this.y+=this.speed;
		break;
		case 3:this.x-=this.speed;
		default:
			break;
		}
	//	System.out.println(this.x+" "+this.y);
		if(x<0||x>400||y<0||y>300)
		{
		this.islive=false;
	       break;
		}
		}
	}
}
//爆炸效果图
class bomb{
	int x,y;
	int life=12;
	public bomb(int x,int y)
	{
		this.x=x;
		this.y=y;
	}
	public void lifedown()
	{
		this.life--;
	}
	
}

