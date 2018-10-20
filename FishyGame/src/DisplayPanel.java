import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class DisplayPanel extends JPanel implements KeyListener{
	
	private BufferedImage player, enemy, shark, back;
	Entity MyFish;
	Statistics stats;
	ArrayList<Enemy> smallEnemies = new ArrayList<Enemy>();
	ArrayList<Enemy> midEnemies = new ArrayList<Enemy>();
	ArrayList<Enemy> bigEnemies = new ArrayList<Enemy>();
	Enemy small, mid, big;
	double sizeFactor = Math.random();
	private double points;
	int status = 0;
	boolean shouldDraw = true;

    public DisplayPanel(Statistics stats) {
    	
    	points = 0;
    	this.stats = stats;
       try {                
          player = ImageIO.read(new File("fish.png"));
       } catch (IOException ex) {
            System.out.println("ERROR READING IMAGE");
            ex.getStackTrace();
       }
       
       try {                
           enemy = ImageIO.read(new File("fish_enemy.png"));
        } catch (IOException ex) {
             System.out.println("ERROR READING IMAGE");
             ex.getStackTrace();
        }
       
       try {                
           shark = ImageIO.read(new File("shark.jpg"));
        } catch (IOException ex) {
             System.out.println("ERROR READING IMAGE");
             ex.getStackTrace();
        }
       
       try {
		    back = ImageIO.read(new File("background1.png"));
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
       
       stats = new Statistics();
       
       MyFish = new Entity(0,0,18,10, player);
       for (int i=0; i<10; i++)
       {
    	  sizeFactor = Math.random()+0.1;
    	  Enemy e = new Enemy((int)(600*Math.random()), (int)(600*Math.random()), (int)(50*sizeFactor), (int)(25*sizeFactor), enemy);
    	  e.speedLimit(2);
    	  smallEnemies.add(e);
       }
       for (int i=0; i<7; i++)
       {
    	  sizeFactor = Math.random();
    	  midEnemies.add(new Enemy((int)(600*Math.random()), (int)(600*Math.random()), (int)(50*sizeFactor)+50, (int)(25*sizeFactor)+25, enemy));
       }
       for (int i=0; i<3; i++)
       {
    	  sizeFactor = Math.random();
    	  bigEnemies.add(new Enemy((int)(600*Math.random()), (int)(600*Math.random()), (int)(150*sizeFactor)+50, (int)(75*sizeFactor)+25, enemy));
       }
       
    }

	@Override
	public void keyPressed(KeyEvent k) {
		int code = k.getKeyCode();
		
		if(code == KeyEvent.VK_LEFT){
			MyFish.AlterVelocity(-1.08, 0);
		}
		
		else if(code == KeyEvent.VK_RIGHT){
			MyFish.AlterVelocity(1.1, 0);
		}
		if(code == KeyEvent.VK_UP){
			MyFish.AlterVelocity(0, -1.08);
		}
		
		else if(code == KeyEvent.VK_DOWN){
			MyFish.AlterVelocity(0, 1.1);
		}
		else if(code == KeyEvent.VK_SPACE){
			System.out.println("SPACE");
		}
//		else if(code == KeyEvent.VK_SPACE){
//			shootFlag = true;
//		}
		
	}

	@Override
	public void keyReleased(KeyEvent k) {
		
	}

	@Override
	public void keyTyped(KeyEvent k) {
		int code = k.getKeyCode();
		
		if(code == KeyEvent.VK_LEFT){
			MyFish.AlterVelocity(-1.08, 0);
		}
		
		else if(code == KeyEvent.VK_RIGHT){
			MyFish.AlterVelocity(1.1, 0);
		}
		if(code == KeyEvent.VK_UP){
			MyFish.AlterVelocity(0, -1.08);
		}
		
		else if(code == KeyEvent.VK_DOWN){
			MyFish.AlterVelocity(0, 1.1);
		}
		else if(code == KeyEvent.VK_SPACE){
			System.out.println("SPACE");
		}
		
	}
	
	public int getStatus()
	{
		return status;
	}
	
	public void initDraw(boolean draw)
	{
		shouldDraw = draw;
	}

	
	@Override
    protected void paintComponent(Graphics g) 
    {
        super.paintComponent(g);
     if (shouldDraw)
     {
        g.drawImage(back,0,0,this.getWidth(), this.getHeight(), null);
        
        for (int i=0; i<smallEnemies.size(); i++)
        {
        	Enemy e = smallEnemies.get(i);
        	if (e.x > 600+Math.abs(e.width) || e.x < -2*Math.abs(e.width)) // Removes enemies that move offscreen
        	{
        		smallEnemies.remove(i);
        	}
        	
           	if (i<smallEnemies.size() && MyFish.Intersects(smallEnemies.get(i)))
        	{
           		small = smallEnemies.get(i);
        		if ( MyFish.getSize() > small.getSize())
        		{
        		 smallEnemies.remove(i);
        		 MyFish.grow();
        		 stats.addPoints(MyFish.getSize(),small.getSize());
        		}
        		else
        		{
   
        			status = 1;
        		}
        	}
        }
        
        for (int i=0; i<midEnemies.size(); i++)
        {
        	Enemy e = midEnemies.get(i);
        	if (e.x > 600+Math.abs(e.width) || e.x < -2*Math.abs(e.width)) // Removes enemies that move offscreen
        	{
        		midEnemies.remove(i);
        	}
        	
        	if (i<midEnemies.size() && MyFish.Intersects(midEnemies.get(i)))
        	{
        		mid = midEnemies.get(i);
        		if ( MyFish.getSize() > mid.getSize())
        		{
        		 midEnemies.remove(i);
        		 MyFish.grow();
        		 stats.addPoints(MyFish.getSize(),mid.getSize());
        		}
        		else
        		{

        			status = 1;
        		}
        	}
        }
        
        for (int i=0; i<bigEnemies.size(); i++)
        {
        	Enemy e = bigEnemies.get(i);
        	if (e.x > 600+Math.abs(e.width) || e.x < -2*Math.abs(e.width)) // Removes enemies that move offscreen
        	{
        		bigEnemies.remove(i);
        	}
        	
        	if (i<bigEnemies.size() && MyFish.Intersects(bigEnemies.get(i)))
        	{
        		big = bigEnemies.get(i);
        		if ( MyFish.getSize() > big.getSize())
        		{
        		 bigEnemies.remove(i);
        		 MyFish.grow();
        		 stats.addPoints(MyFish.getSize(),big.getSize());
        		}
        		else
        		{
        			
        			status = 1;
        		}
        	}
        }
        
        if (smallEnemies.size()<10) // adds to enemies to game
        {
        	sizeFactor = Math.random()+0.1;
        	Enemy e1 = new Enemy(600, (int)(600*Math.random()), (int)(50*sizeFactor), (int)(25*sizeFactor), enemy);
        	e1.setDirection(-1);
        	e1.speedLimit(2);
        	smallEnemies.add(e1);
        	Enemy e2 = new Enemy(0-(int)(100*sizeFactor), (int)(600*Math.random()), (int)(50*sizeFactor), (int)(25*sizeFactor), enemy);
        	e2.setDirection(1);
        	e2.speedLimit(2);
        	smallEnemies.add(e2);
        }
        
        
        if (midEnemies.size()<7) // adds to enemies to game
        {
        	sizeFactor = Math.random();
        	Enemy e1 = new Enemy(600, (int)(600*Math.random()), (int)(50*sizeFactor)+50, (int)(25*sizeFactor)+25, enemy);
        	e1.setDirection(-1);
        	e1.speedLimit(2);
        	midEnemies.add(e1);
        	Enemy e2 = new Enemy(0-(int)(50*sizeFactor+50), (int)(600*Math.random()), (int)(50*sizeFactor)+50, (int)(25*sizeFactor)+25, enemy);
        	e2.setDirection(1);
        	e2.speedLimit(2);
        	midEnemies.add(e2);
        }
        
        if (bigEnemies.size()<3) // adds to enemies to game
        {
        	sizeFactor = Math.random();
        	Enemy e1 = new Enemy(600, (int)(600*Math.random()), (int)(150*sizeFactor)+100, (int)(75*sizeFactor)+50, enemy);
        	if (Math.abs(e1.width) >= 235)
        	{
        		e1.changeIcon(shark);
        	}
        	e1.setDirection(-1);
        	e1.speedLimit(1);
        	bigEnemies.add(e1);
        	Enemy e2 = new Enemy(0-((int)(150*sizeFactor)+100), (int)(600*Math.random()), (int)(150*sizeFactor)+100, (int)(75*sizeFactor)+50, enemy);
        	if (Math.abs(e2.width) >=235)
        	{
        		e2.changeIcon(shark);
        	}
        	e2.setDirection(1);
        	e2.speedLimit(1);
        	bigEnemies.add(e2);
        }
        
        
        if (MyFish.getSize() > 1000)
        {
        	status = 2;
        }
        
      
        MyFish.draw(g);
    	try {
			Thread.sleep(20); // make fish move slower by increasing buffer time
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
        for (int i=0; i<smallEnemies.size(); i++)
        {
        	smallEnemies.get(i).draw(g);
        }
        
        for (int i=0; i<midEnemies.size(); i++)
        {
        	midEnemies.get(i).draw(g);
        }
        
        for (int i=0; i<bigEnemies.size(); i++)
        {
        	bigEnemies.get(i).draw(g);
        }
        g.setFont(new Font("default", Font.BOLD, 16));
        g.drawString("SCORE: " + stats.getScore(), 30,20);
        
        repaint();
     }  
    }

}
