import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Entity {
	protected int x, y, width, height;
	protected double xVel, yVel;
	protected int dir; // 1 = right -1 = left
	protected BufferedImage image;
	
	
	public Entity (int x, int y, int width, int height, BufferedImage im)
	{
		this.x = x+width;
		this.y = y;
		this.width = -width;
		this.height = height;
		image = im;
		dir = 1; 
	}
	
	public void draw (Graphics g)
	{
		  if (x>600+Math.abs(width))  //HARD CODE WRAP AROUND FIX LATER
			  x=0;
		  if (x<-Math.abs(width))
			  x=600;
		  if (y<0)
			  y = 0;
		  if (y > 400+Math.abs(height)/2)
			  y = 400+Math.abs(height)/2;
		  
		 // if (xVel>5)
		//	  xVel = 5;
		 // if (yVel>5)
		//	  yVel = 5;

		 try {
			Thread.sleep(1); // make fish move slower by increasing buffer time
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  Resist();
		  move(xVel, yVel);
		  g.drawImage( image, x, y, width, height, null ); 
		  g.setColor(Color.RED);

	}
	
	public void move (double xDir, double yDir)
	{
		x = (int) (x + xDir);
		y = (int) (y + yDir);
	}
	
	public void AlterVelocity(double x, double y)
	{
		xVel = xVel + x;
		yVel = yVel + y;
		if (x>0 && dir == -1)  // 1 = right -1 = left
		{
			width = -1*Math.abs(width);
			this.x = this.x+Math.abs(width);
			dir = 1;
		}
		else if (x<0 && dir == 1)
		{
			width = Math.abs(width);
			this.x = this.x-Math.abs(width);
			dir = -1;
		}
	}
	
	private void Resist()
	{
		//if (xVel != 0)
			xVel = xVel*0.99;
		//if (yVel != 0)
			yVel = 0.99*yVel;
			
			if (Math.abs(xVel)<0.95)
				xVel = 0;
			if (Math.abs(yVel)<0.95)
				yVel = 0;
	}
	
	public int getSize()
	{
		return Math.abs(width*height);
	}
	
	public void grow(){
		width*=1.1;
		height*=1.1;
	}
	
	public boolean Intersects(Entity other)
	{
		boolean intersects = false;
		
		int w1 = Math.abs(this.width);
		int h1 = Math.abs(this.height);
		int w2 = Math.abs(other.width);
		int h2 = Math.abs(other.height);
		
		
		int x1 = this.x;
		int y1 = this.y;
		int x2 = other.x;
		int y2 = other.y;
		
		//if (other.dir == 1 && this.dir == -1)
	//		x2 = x2-w2;
		if (this.dir == 1 && other.dir == -1)
			x1 = x1-2*w1;
		//if (this.dir == 1 && other.dir == 1)
		//{
	//		x2 = x2-w2;
//			x1 = x1-w1;
	//	}
		//if (other.dir == -1)
	//		x2 = x2+w2;
		
	

		
		if ((x1+w1>=x2) && (x1+w1<=x2+w2*0.9))
		{
			if ((y1+h1>=y2) && (y1+h1 <=y2+h2*0.9))
			{
				intersects = true;
				//System.out.println("DEAD ON BOTTOM RIGHT: " +(x2)+" < "+(x1+w1) + " < " + (x2+w2) + "    " + (y2)+" < "+(y1+h1) + " < " + (y2+h2));
			}
			else if ((y1>=y2) && (y1 <=y2+h2*0.9))
			{
				intersects = true;
				//System.out.println("DEAD TOP RIGHT: " +(x2)+" < "+(x1+w1) + " < " + (x2+w2) + "    " + (y2)+" < "+(y1) + " < " + (y2+h2));
			}
			else if ((y1+h1>y2+0.9*h2) && (y1<y2))
			{
				intersects = true;
			}
		}
		else if ((x1>=x2) && (x1<=x2+0.9*w2))
		{
			if ((y1+h1>=y2) && (y1+h1 <=y2+h2*0.9))
			{
				intersects = true;
				//System.out.println("DEAD BOTTOM LEFT: " +(x2)+" < "+(x1) + " < " + (x2+w2) + "    " + (y2)+" < "+(y1+h1) + " < " + (y2+h2));
			}
			else if ((y1>=y2) && (y1 <=y2+h2))
			{
				intersects = true;
				//System.out.println("DEAD TOP LEFT: " +(x2)+" < "+(x1) + " < " + (x2+w2) + "    " + (y2)+" < "+(y1) + " < " + (y2+h2));
			}
			else if ((y1+h1>y2+0.9*h2) && (y1<y2))
			{
				intersects = true;
			}
		}
		
		else if ((x1+w1>x2) && (x1<x2))
		{
			if ((y1+h1>=y2) && (y1+h1 <=y2+0.9*h2))
			{
				intersects = true;
				
			}
			else if ((y1>=y2) && (y1 <=y2+h2))
			{
				intersects = true;
			
			}
		}
		
		/*Rectangle player, oponent;
		
		player = new Rectangle (this.x, this.y, this.width, this.height);
		oponent = new Rectangle (other.x, other.y, other.width, other.height);
		if (player.intersects(oponent))
			intersects = true;*/
		

		return intersects;
		
	}
	
	
	
}
