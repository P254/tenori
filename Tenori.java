import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import java.util.Random;
public class Tenori extends JApplet implements ActionListener, Runnable
{
	//JButton button[][]=new JButton[5][2];
	JToggleButton button[][]=new JToggleButton[15][15];
	JToggleButton button2[][]=new JToggleButton[3][1];
	JPanel panel=new JPanel();
	JPanel beats=new JPanel();
	JPanel east=new JPanel();
	JToggleButton reverse=new JToggleButton("REVERSE");
	Container container;
	int count=0;
	AudioClip soundClip[]=new AudioClip[15];
	AudioClip soundClip1[]= new AudioClip[3];
	Thread runner;
	boolean notStopped=true, buttonOn=false;
	JSlider slider= new JSlider(125,500);
	JButton offButton=new JButton("OFF");

	int counter =0;
	public void init()
	{
		east.add(offButton);
		east.add(reverse);
		soundClip[0]=getAudioClip(getDocumentBase(),"C4.wav");
		soundClip[1]=getAudioClip(getDocumentBase(),"D4.wav");
		soundClip[2]=getAudioClip(getDocumentBase(),"E4.wav");
		soundClip[3]=getAudioClip(getDocumentBase(),"F4.wav");
		soundClip[4]=getAudioClip(getDocumentBase(),"G4.wav");
		soundClip[5]=getAudioClip(getDocumentBase(),"A4.wav");
		soundClip[6]=getAudioClip(getDocumentBase(),"B4.wav");
		soundClip[7]=getAudioClip(getDocumentBase(),"C5.wav");
		soundClip[8]=getAudioClip(getDocumentBase(),"D5.wav");
		soundClip[9]=getAudioClip(getDocumentBase(),"E5.wav");
		soundClip[10]=getAudioClip(getDocumentBase(),"F5.wav");
		soundClip[11]=getAudioClip(getDocumentBase(),"G5.wav");
		soundClip[12]=getAudioClip(getDocumentBase(),"A5.wav");
		soundClip[13]=getAudioClip(getDocumentBase(),"B5.wav");
		soundClip[14]=getAudioClip(getDocumentBase(),"C6.wav");
		soundClip1[0]=getAudioClip(getDocumentBase(),"beat1.wav");
		soundClip1[1]=getAudioClip(getDocumentBase(),"beat2.wav");
		soundClip1[2]=getAudioClip(getDocumentBase(),"beat3.wav");
		container=getContentPane();
		panel.setLayout(new GridLayout(15,15));
		beats.setLayout(new GridLayout(3,1));
		for (int a=0;a<3;a++)
		for (int b=0;b<1;b++)
		{
			button2[a][b]=new JToggleButton();
			beats.add(button2[a][b]);
		}
		for (int x=0;x<15;x++)
		for(int y=0;y<15;y++)
		{

			button[x][y]=new JToggleButton();
			panel.add(button[x][y]);
		}

		offButton.addActionListener(this);
		setSize(600,300);
		container.add(beats,BorderLayout.WEST);
		container.add(panel,BorderLayout.CENTER);
		container.add(slider,BorderLayout.SOUTH);
		container.add(east,BorderLayout.EAST);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource()==offButton)
		{

			for (int x=0;x<15;x++)
			{
				for (int y=0;y<15;y++)
				{
					button[x][y].setSelected(false);
				}
			}
			for (int a=0;a<2;a++)
			{
				for (int b=0;b<1;b++)
				{
					button2[a][b].setSelected(false);
				}
			}
			offButton.setSelected(false);
		}

	}

	public void start()
	{
		if(runner==null)
		{
			runner=new Thread(this);
			runner.start();
		}
	}

	public void run()
	{
		do
		{
			goApp();
		}while(notStopped);

	}



	public void goApp()
	{
		Random rand = new Random();
		try
		{

				System.out.println("Hi");
				if (counter == button[1].length-1)
				{
					counter =0;
				}
				else
				{
					count++;
				}

				for (int y=0;y<15;y++)
				{
						for (int x=0;x<15;x++)
						{
							button[x][y].setBackground(Color.YELLOW);
							if (button[x][y].isSelected())
							{
									soundClip[x].play();
							}
						}

						new Thread().sleep(1000-(slider.getValue()));

						for (int b=0;b<15;b++)
						{
							for (int a=0;a<15;a++)
								button[a][b].setBackground(null);
						}
					}


		/*		else if (!reverse.isSelected())
				{
					System.out.println("Not reversed");
					if (counter == button[1].length-1)
						{
							counter =0;
						}
					else
						{
							count++;
						}

					for (int y=14;y>=0;y--)
						{

						for (int x=14;x>=0;x--)
								{
									button[x][y].setBackground(Color.YELLOW);
									if (button[x][y].isSelected())
									{
										soundClip[x].play();
									}
								}

								new Thread().sleep(1000-(slider.getValue()));

								for (int b=14;b>=0;b--)
								{
									for (int a=14;a>=0;a--)
									button[a][b].setBackground(null);
								}
				}*/






			for (int a=0;a<3;a++)
			{
				for (int b=0;b<1;b++)
				{
					if (button2[a][b].isSelected())
					{
						soundClip1[a].play();
					}
				}
				new Thread().sleep(1000-(slider.getValue()));

			}


		}
		catch(InterruptedException e)
		{
		}

	}
}