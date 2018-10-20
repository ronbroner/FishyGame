import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Enemy extends Entity{

	public Enemy(int x, int y, int width, int height, BufferedImage im) {
		super(x, y, width, height, im);
		// TODO Auto-generated constructor stub
		//xVel = Math.random()*10+1; // speed between 1 and 11
		xVel = 1;
		this.width = this.width*(int)Math.pow(-1, (int)(Math.random()*10));
		if (this.width>0)
		{
			xVel = -xVel;
		}

	}
	
	public void draw (Graphics g)
	{
		
		move(xVel, yVel);
		g.drawImage( image, x, y, width, height, null );	
	}
	
	public void setDirection(int direction)
	{
		this.dir = direction;
		
		if (dir == 1)
		{
			this.width = -Math.abs(this.width);
			xVel = Math.abs(xVel);
		}
		else if (dir == -1)
		{
			this.width = Math.abs(this.width);
			xVel = -Math.abs(xVel);
		}

	}
	
	public void speedLimit(int speed)
	{
		xVel  = ((xVel/Math.abs(xVel)) * speed)*Math.random() + (xVel/Math.abs(xVel));
	}

	public void changeIcon(BufferedImage im)
	{
		this.image = im;
		
	}

}
