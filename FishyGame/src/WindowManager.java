import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public  class WindowManager extends JPanel  {
	UI start;
	DisplayPanel drawing;
	int open = 0;
	int dead = 0;
	Statistics stats;
	
	public WindowManager()
	{
		stats = new Statistics();
	}
	
	public void Startup() {
		
		JFrame window = new JFrame();
		while (true)
		{
		UI start = new UI(stats);
		DisplayPanel drawing = new DisplayPanel(stats);
		start.requestFocus();
		//drawing.requestFocus();
		window.setResizable(false);
		window.setFocusable(true);
		window.setSize(600, 480);
		window.setMinimumSize(new Dimension(100,100));
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//window.add(drawing);
		window.add(start);
		window.setVisible(true);
		window.addKeyListener(drawing);
		

			//System.out.println("1");
			start.requestFocus();
			start.setVisible(true);
			window.add(start);
			while (open == 0)
			{
				//System.out.println("2");
				if (dead == 1)
				{
					start.gameOver();
				}
				
				if (dead == 2)
				{
					start.gameWon();
				}
				open = start.getAction();
				this.repaint();
				if (open == 1)
				{
					//drawing = new DisplayPanel();
					start.setVisible(false);
					drawing.requestFocus();
					drawing.setVisible(true);
					window.add(drawing);
					//drawing.initDraw(true);
					window.addKeyListener(drawing);
					//while (dead == 0)
					//{
						dead = 0;
						//System.out.println(dead);
						/*if (dead == 1)
						{
							System.out.println("DEAD");
							drawing.setVisible(false);
							open = 0;
						}*/
						//drawing.setVisible(false);
						//}
				}
			}

			while (dead == 0)
			{
				//System.out.println("3");
				repaint();
				dead = drawing.getStatus();
				if (dead == 1)
				{
					stats.reset();
					drawing.setVisible(false);
					//drawing.initDraw(false);
					//start.requestFocus();
					open = 0;
				}
				else if (dead == 2)
				{
					stats.reset();
					drawing.setVisible(false);
					open = 0;
				}
			}
		
			
		}
	}
	
}
