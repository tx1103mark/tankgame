/**坦克游戏，可以控制坦克上下左右移动
 可以发射子弹最多5颗
 可以分关
 可以控制游戏的继续和暂停
 可以记录玩家成绩
 可以播放音频
坦克互相运动不重叠**/
package tankgame1;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;


import java.util.*;
public class first_tank extends JFrame{
     
	public static void main(String[] args) {
		// TODO Auto-generated method stub
              new first_tank();
	}
	mypanel m=null;
public first_tank(){
	m=new mypanel(200,100);
	Thread t2=new Thread(m);
	t2.start();
	this.add(m);
	this.setVisible(true);
	this.setSize(400, 300);
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.addKeyListener(m);
}
}

class mypanel extends JPanel implements KeyListener,Runnable{
    mytank my1=null;
    shot shot1=null;
    int size=3;
    //敌人坦克的数量存储
   Vector<enmytank> env=new Vector<enmytank>();
  //爆炸对象矢量
   Vector<bomb> bombs=new Vector<bomb>();
   Image image1=null;
   Image image2=null;
   Image image3=null;
    public mypanel(int x,int y){
    	my1=new mytank(x,y);
    	//初始化敌人的tank
    	 for(int i=0;i<size;i++)
    	 {
    		enmytank en=new enmytank(100*i+50,10);
    			env.add(en);
    			en.Set_etc(env);
    			Thread t_enmy=new Thread(en);
    			t_enmy.start();
    			//画敌人tank的子弹
    			shot s1=new shot(en.x,en.y,2);
    			en.enmy_shot.add(s1);
    			Thread t_shotThread=new Thread(s1);
    			t_shotThread.start();	
    			
    	 }
    	 //爆炸图片初始化
    	 image1=Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/test1.png"));
     	 image2=Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/test.png"));
     	 image3=Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/test2.png"));
    }
    
	public void paint(Graphics g){
		super.paint(g);
		g.fillRect(0, 0, 400, 300);
		//画自己的坦克
		if(my1.islive)
		drawtank(my1.getX(), my1.getY(),g, 1,my1.direction);
		//画敌人的坦克
		for(int i=0;i<env.size();i++)
		{
			enmytank enmy=env.get(i);
			if(enmy.islive)
			{
		         drawtank(enmy.getX(), enmy.getY(),g, 0,enmy.direction);
		        // System.out.println(enmy.x+" "+enmy.y);
		         for(int j=0;j<enmy.enmy_shot.size();j++)
		         {
		        	 shot s=enmy.enmy_shot.get(j);
		     		if(s!=null&&s.islive==true)
		     			g.fillRect(s.getX(), s.getY(), 2, 2);
		     		if(s.islive==false)
		     			enmy.enmy_shot.remove(s);
		   //  		System.out.println(s.x+" "+s.islive+" "+s.y);
		         }
		        	 
			}
			
		}
		//画自己的子弹
		for(int i=0;i<this.my1.ss.size();i++){ 
			shot s=this.my1.ss.get(i);
		if(s!=null&&s.islive==true)
			g.fillRect(s.getX(), s.getY(), 2, 2);
		//System.out.println(s.getX()+" "+s.getY());
		if(s.islive==false)
			this.my1.ss.remove(s);
		
		}
		//画出爆炸效果
		for(int i=0;i<bombs.size();i++)
		{
			bomb bo=bombs.get(i);
			if(bo.life>6)
				g.drawImage(image1, bo.x, bo.y, 30,30,this);
			else if(bo.life>3)
				g.drawImage(image2, bo.x, bo.y,30,30,this);
			else g.drawImage(image3, bo.x, bo.y,30,30, this);
			bo.lifedown();
			if(bo.life==0)
				bombs.remove(bo);
			//System.out.println("爆炸的数量"+bombs.size());
		}
		
	}
	public void drawtank(int x,int y,Graphics g,int type,int dir){
		
		g.setColor(Color.black);
		switch (type) {
		case 0:
			g.setColor(Color.blue);
			break;
		case 1:
			g.setColor(Color.yellow);
			break;
		}
		switch (dir) {
		//坦克方向向上
		case 0:
		g.fill3DRect(x-10, y-15, 5, 30,false);
		g.fill3DRect(x+5, y-15, 5, 30,false);
		g.fill3DRect(x-5, y-10, 10, 20,false);
		g.fillOval(x-5, y-5, 10, 10);
		g.drawLine(x, y, x, y-15);
		break;
		//坦克方向向右
		case 1:
			g.fill3DRect(x-15, y-10, 30, 5,false);
			g.fill3DRect(x-15, y+5, 30, 5,false);
			g.fill3DRect(x-10, y-5, 20,10,false);
			g.fillOval(x-5, y-5, 10, 10);
			g.drawLine(x, y, x+15, y);
			break;	
	    // 坦克方向向下
		case 2:
			g.fill3DRect(x-10, y-15, 5, 30,false);
			g.fill3DRect(x+5, y-15, 5, 30,false);
			g.fill3DRect(x-5, y-10, 10, 20,false);
			g.fillOval(x-5, y-5, 10, 10);
			g.drawLine(x, y, x, y+15);
			break;
		//坦克方向向左
		case 3:
			g.fill3DRect(x-15, y-10, 30, 5,false);
			g.fill3DRect(x-15, y+5, 30, 5,false);
			g.fill3DRect(x-10, y-5, 20, 10,false);
			g.fillOval(x-5, y-5, 10, 10);
			g.drawLine(x, y, x-15, y);
			break;	
		default:
			break;
		}	
	}
	//攻击坦克
	public void hittank(shot s,tank en){
		switch(en.direction){
		case 0:
		case 2:if(en.getX()-5<s.getX()&&s.getX()<en.getX()+5&&s.getY()>en.getY()-10&&s.getY()<en.getY()+10)
		{
			s.islive=false;
			en.islive=false;
			bomb b1=new bomb(en.x, en.y);
			this.bombs.add(b1);
		}
		case 1:
		case 3:if(en.getX()-15<s.getX()&&s.getX()<en.getX()+15&&s.getY()>en.getY()-10&&s.getY()<en.getY()+10)
		{
			s.islive=false;
			en.islive=false;
			bomb b1=new bomb(en.x, en.y);
			this.bombs.add(b1);
		}
		}
	}
	@Override
	public void keyTyped(KeyEvent e) {
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		//向上移
				switch (e.getKeyChar()) {
				case 'w':
					this.my1.up();
					break;
				case 's':
					 this.my1.down();
					 break;
				case 'a':
					 this.my1.left();
					 break;
				case 'd':
					 this.my1.right();
					 break;
				case 'j':
					if(this.my1.ss.size()<5)
				this.my1.shotenmey(this.my1.x, this.my1.y, this.my1.direction);
					break;
				}
				
				this.repaint();
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
	//	System.out.println("xiangxia");
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
		try {
			Thread.sleep(100);
		} catch (Exception e) {
			// TODO: handle exception
		}
		repaint();
		//判断每颗子弹是否击中坦克
		for(int i=0;i<this.my1.ss.size();i++)
		{
		     shot s=my1.ss.get(i);
		     if(s.islive)
		     {
			      for(int j=0;j<this.env.size();j++)
			      {
				    enmytank enmy=env.get(j);
				            if(enmy.islive)
	                           hittank(s, enmy);
	              }
		     }
	     }
		//判断是否击中自己
		for(int j=0;j<this.env.size();j++)
		{
			enmytank e=this.env.get(j);
			if(e.islive)
				for(int i=0;i<e.enmy_shot.size();i++)
				{
					shot s=e.enmy_shot.get(i);
					if(s.islive)
					hittank(s, my1);
				}
		}
	
	  }	
	}
	
}


