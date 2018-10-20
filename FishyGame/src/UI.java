import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class UI extends JPanel implements ActionListener{

	BufferedImage img, bones;
	JButton start, settings;
	boolean gameOver = false;
	boolean gameWon = false;
	int y = 458;
	JLabel title, youDied, high, winMessage;
	int highScore = 0;
	Statistics stats;
	
	public UI(Statistics stats)
	{
		this.stats = stats;
		
		try {
		    img = ImageIO.read(new File("background1.png"));
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
		try {
		    bones = ImageIO.read(new File("fishbone(1).png"));
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
		this.setLayout(null);
		
		title = new JLabel("Pond Life");
		title.setFont(new Font("Verdana",1,50));
		//title.setPreferredSize(new Dimension(200, 200));
		
		highScore = stats.getHighScore();
		high = new JLabel("High Score: " + stats.getHighScore());
		high.setFont(new Font("Verdana",1,20));
		
		
		youDied = new JLabel("YOUR FISH WAS EATEN: GAME OVER");
		youDied.setFont(new Font("Verdana",1,20));
		
		winMessage = new JLabel("YOU ATE EVERYTHING --- YOU WIN....?");
		winMessage.setFont(new Font("Verdana",1,20));
		

		
		start = new JButton("START");
		settings = new JButton("SETTINGS");
		start.addActionListener(this);
		settings.addActionListener(this);
		
		
		//title.setSize(new Dimension(300,200));
		//start.setSize(new Dimension(100,50));
		//settings.setSize(new Dimension(100,50));
		
		
	
		//start.setLocation(new Point(250,250));
		//settings.setLocation(new Point(350,250));
		//title.setLocation(new Point(300,50));
		start.setBounds(180,350,100,50);
		settings.setBounds(330,350,100, 50);
		title.setBounds(170, 0, 300, 100);
		youDied.setBounds(100,230,450,100);
		high.setBounds(220,50,200,100);
		winMessage.setBounds(100, 200, 500, 100);
		
		
		add(high);
		add(title);
		add(start);
		add(settings);
		


	}
	
	
	
	public void gameOver()
	{
		gameOver = true;
	}
	
	public void gameWon()
	{
		gameWon = true;
	}
	
	public int getAction(){
		if (start.getModel().isPressed()){
			return 1;
		}
		return 0;
	}
	@Override
    protected void paintComponent(Graphics g) 
    {
		super.paintComponent(g);
			g.drawImage(img,0,0,this.getWidth(), this.getHeight(), null);
			if (gameOver)
			{
				g.drawImage(bones, 7*this.getWidth()/20, y, 200, 100, null);
				if (y>150)
				{
					y--;
				}

				youDied.setForeground(Color.RED);
				//youDied.setLocation(new Point(100,100));
				add(youDied);
				start.setText("PLAY AGAIN");
			}
			
			if (gameWon)
			{
				try {
				    img = ImageIO.read(new File("dry.jpg"));
				} catch (IOException e) {
					System.out.println(e.getMessage());
				}
				
				winMessage.setForeground(new Color(0,130,30));
				add(winMessage);
				start.setText("PLAY AGAIN");
			}
			
			
			
			
					
			repaint();
		
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
