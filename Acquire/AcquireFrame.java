import java.awt.*;
import javax.swing.*;
import java.lang.*;
import java.awt.event.*;
import java.lang.*;
import java.util.ArrayList;
public class AcquireFrame extends JFrame implements ActionListener
{

	private Bank b;
	private Board board;
	private JComboBox whichStock;
	private JComboBox howMany;
	private JButton buy;
	private JButton done;
	private JButton merge, sell, trade;
	private int moneyVal;
	private ArrayList<JButton> buttons;
	private ArrayList<JButton> playerTiles;
	private JPanel panel;
	private JTextField money;
	private Tile current;
   	public AcquireFrame()
   	{
   		super("Acquire");
   	  	setSize(1900,1100);
   	  	setVisible(true);
   	  	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   	  	setLayout(null);
   	  	setResizable(false);
		moneyVal=6000;
        b=new Bank();
        playerTiles = new ArrayList<JButton>();
        buttons = new ArrayList<JButton>();
        board = new Board();
        
        money=new JTextField(50);
        Font font = new Font("Courier", Font.BOLD,14);
        money.setFont(font);
        money.setText(" MONEY:$" +moneyVal);
        money.setEditable(false);
        money.setBounds(1150,20,120,60);
        money.setForeground(Color.black);
        money.setVisible(true);
        add(money);

        String[] stockName={"Zeta","Sackson","Hydra","Fusion","America","Phoenix","Quantum"};
        whichStock=new JComboBox(stockName);
        whichStock.setBounds(700,570,100,50);
        for(int i=0;i<stockName.length;i++)
        {
        	whichStock.setActionCommand(stockName[i]);
        	whichStock.addActionListener(this);
        }

        add(whichStock);
        String[] stockNum={"1","2","3"};
        howMany=new JComboBox(stockNum);
        howMany.setBounds(820,570,100,50);
        howMany.setVisible(true);
        add(howMany);
        whichStock.setEnabled(false);
        howMany.setEnabled(false);

        buy = new JButton("BUY");
        buy.setBounds(960,570,100,50);
        buy.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e)
        	{
        		Corporation selected = b.findCorp((String)(whichStock.getSelectedItem()));
        		if(selected.isOnBoard())
        		{
        			int num = Integer.parseInt((String)(howMany.getSelectedItem()));
        			int decrement = selected.getPrice()*num;
        			moneyVal = moneyVal - decrement;
        			updateMoney();
        			refreshOutput(getGraphics());
        			addOutput("You bought " + num + " stockcard(s) of " + selected.getName());
        		//	afterClickEnable();        		    
        		}
        		else
        		{
        			refreshOutput(getGraphics());
        			addOutput(selected.getName() + " has not been put on the board yet. Choose another or just don't.");
        		}
        	
        	}
        });
        add(buy);
        buy.setEnabled(false);

		done=new JButton("DONE");
        done.setBounds(1100,570,100,50);
        done.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e)
        	{
        		whichStock.setEnabled(false);
        		howMany.setEnabled(false);
        		buy.setEnabled(false);
        		((JButton)(e.getSource())).setEnabled(false);
        		playerMove();
        	}
        });
        add(done);
        done.setEnabled(false);

        merge=new JButton("MERGE");
        merge.setBounds(700,640,100,50);
       /* merge.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e)
        	{
        		
        	}
        });*/
        add(merge);
        merge.setEnabled(false);

        sell=new JButton("SELL");
        sell.setBounds(820,640,100,50);
        add(sell);
        sell.setEnabled(false);
        
        trade=new JButton("TRADE");
        trade.setBounds(940,640,100,50);
        add(trade);
        trade.setEnabled(false);

        playerTiles=new ArrayList<JButton>();
        ArrayList<Tile>temp=b.getPlayerTiles();
        int x=700;
        int y=20;
        for(int i=0;i<6;i++)
        {
        	JButton button=new JButton(temp.get(i).getName());
        	button.setActionCommand(temp.get(i).getName());
        	playerTiles.add(button);
        	button.addActionListener(new ActionListener(){
        		public void actionPerformed(ActionEvent e)
        		{
        			afterClickDisable();
        			refreshOutput(getGraphics());
        			current = board.findTile(e.getActionCommand());
        			if(board.isConnected(e.getActionCommand()))
        			{
        				if(board.getConnected(e.getActionCommand()).getColor()==Color.gray)
        				{
        					paintChoose(getGraphics());
        					return;
        				}
        				else
        				{
        					board.changeTile(e.getActionCommand(), board.getConnected(e.getActionCommand()).getColor());
        					Corporation c = b.findCorp(board.getConnected(e.getActionCommand()).getColor());
        					c.addTile();
        					while(board.getConGray(e.getActionCommand())!=null)
        					{
        						board.changeTile(board.getConGray(e.getActionCommand()).getName(),c.getColor());
   								c.addTile();
   								
        					}
        				}
        			}
        			else
        				board.changeTile(e.getActionCommand(), Color.gray);
        	        board.paint(getGraphics());
        	        JButton but = (JButton)e.getSource();
        	        String n = b.getRand().getName();
        	        but.setLabel(n);
        	        but.setActionCommand(n);
        	        revalidate();
        	        
        	        
       	            if(b.noneOnBoard())
        	       		 playerMove();
        	        else
        	        {
        	        	refreshOutput(getGraphics());
        	        	addOutput("You can buy up to 3 stock cards.\nChoose stock and quantity to buy and press buy. Press done when you are finished.");
        	        	whichStock.setEnabled(true);
        	        	howMany.setEnabled(true);
        	        	buy.setEnabled(true);
        	        	done.setEnabled(true);
        	        }
        	        
        	        
        	        
        		 }

        		}
        	);
        	button.setBounds(x,y,60,60);
        	button.setVisible(true);
        	button.setForeground(Color.white);
        	button.setBackground(Color.gray);
        	//p1.addTiles(temp.get(i));
        	add(button);
        	x+=65;


        }
        

   	}
   	
   	public void playerMove()
   	{
   		Tile p2=b.getRand();
       	Tile p3=b.getRand();
        Tile p4=b.getRand();
        	    
        addTile(p2);
        addTile(p3);
        addTile(p4);
        	    
        String rString = "Player 2 placed "+p2.getName() +"\nPlayer 3 placed "+p3.getName()+ "\nPlayer 4 placed "+p4.getName() + "\nPlace a tile";
        refreshOutput(getGraphics());
        addOutput(rString);
      	afterClickEnable();
   	}
   	
   	public void afterClickDisable()
   	{
   		for(int i = 0; i < playerTiles.size(); i++)
   			playerTiles.get(i).setEnabled(false);
   		revalidate();
   	}
   	public void afterClickEnable()
   	{
   		for(int i = 0; i < playerTiles.size(); i++)
   			playerTiles.get(i).setEnabled(true);
   		revalidate();
   	}
   	public int getMoneyVal()
   	{
   		return moneyVal;
   	}
   	
   	public void updateMoney()
   	{
   		money.setText("MONEY:$ "+moneyVal);
   		revalidate();
   	}


   	public void addTile(Tile t)
   	{
   		if(board.isConnected(t.getName()))
   		{
   			if(board.getConnected(t.getName()).getColor()==Color.gray)
	   		{
	   			
	   			if(b.getAvailable()==null||b.getAvailable().isEmpty())
	   			{
	   				board.changeTile(t.getName(), Color.gray);
	   			}
	   			else
	   			{int ranC = (int)(Math.random()*b.getAvailable().size());
	   			Corporation c = b.getAvailable().get(ranC);
	   			c.addTile();
	   			c.setIsOnBoard(true);
	   			board.changeTile(t.getName(), c.getColor());
	   			while(board.getConGray(t.getName())!=null)
	   			{
	   				board.changeTile(board.getConGray(t.getName()).getName(),c.getColor());
	   				c.addTile();
	   				
	   			}
	   			}
   			}
   			else
   			{
   				board.changeTile(t.getName(), board.getConnected(t.getName()).getColor());
        		Corporation c = b.findCorp(board.getConnected(t.getName()).getColor());
        		c.addTile();
        		while(board.getConGray(t.getName())!=null)
        		{
        			board.changeTile(board.getConGray(t.getName()).getName(),c.getColor());
   					c.addTile();
        		}
   			}
   		}
   		else
   			board.changeTile(t.getName(), Color.gray);
   		board.paint(getGraphics());
   		
   		
   	}


   	public Bank getBank()
   	{
   		return b;
   	}

	public void paint(Graphics g)
	{
       board.paint(g);
       b.paint(g);
       paintOutput(g);
	}
	
	public void paintOutput(Graphics g)
	{
       g.setColor(Color.black);
       g.drawRect(20,550, 650,200);
	}
	
	public void addOutput(String text)
	{
		drawString(getGraphics(), text, 30, 560);
	}
	
	public void refreshOutput(Graphics g)
	{
		g.setColor(Color.white);
		g.fillRect(25, 555, 640, 190);
	}
	
	public void playerTurn()
	{
		addOutput("Place a tile to start your turn.\n");
	}
	
	public void paintChoose(Graphics g)
	{
		refreshOutput(getGraphics());
		g.setColor(Color.black);
		g.drawRect(700, 420, 650, 150);
		ArrayList<Corporation> available = b.getAvailable();
		if(available.isEmpty())
		{
			drawString(g, "All corporations are on board.", 710, 430);
			board.changeTile(current.getName(), Color.gray);
			whichStock.setEnabled(true);
			howMany.setEnabled(true);
			buy.setEnabled(true);
			done.setEnabled(true);
		}
		else
		{
			drawString(g, "Choose a corporation.", 710, 430);
			int x = 710; int y = 440;
			for(int i = 0; i < available.size(); i++)
			{
				JButton temp = new JButton(available.get(i).getName());
				temp.setActionCommand(available.get(i).getName());
				buttons.add(temp);
				temp.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e)
					{
						Corporation c= b.findCorp(e.getActionCommand());
						refreshOutput(getGraphics());
						c.setIsOnBoard(true);
						c.addTile();
						board.changeTile(current.getName(), c.getColor());
						while(board.getConGray(current.getName())!=null)
						{
							board.changeTile(board.getConGray(current.getName()).getName(), c.getColor());
							c.addTile();
						}
						board.paint(getGraphics());
						addOutput(c.getName() + " is on the board!\nYou have been given one stock card of " + c.getName());
						afterChoose(getGraphics());
						whichStock.setEnabled(true);
			howMany.setEnabled(true);
				buy.setEnabled(true);
			done.setEnabled(true);
					}
				});
				temp.setBounds(x, y, 85, 85);
				add(temp);
				x += 90;
			}
		}
	}
	
	public void afterChoose(Graphics g)
	{
		for(int i = 0; i < buttons.size(); i++)
			buttons.get(i).setVisible(false);
		revalidate();
		buttons.clear();
		g.setColor(Color.white);
		g.fillRect(700, 420, 680, 170);
	}

	public void actionPerformed(ActionEvent e)
	{
         JComboBox comboBox=(JComboBox)e.getSource();
         Object select=comboBox.getSelectedItem();
         Graphics g=getGraphics();
         Corporation temp=b.findCorp(select.toString());
         g.setColor(Color.white);
		 g.fillRect(1150, 270, 120, 120);
		 Rectangle rect = new Rectangle(1150,270,120,120);
         g.setColor(Color.black);
         g.drawRect(1150,270, 120, 120);
		 drawString(g, "" +temp.getName()+"\nPrice:$"+temp.getPrice()+"\nMajority:$"+temp.getMaj()+"\nMinority:$"+temp.getMin(),1155,275);
		 
	}

	public void update(Graphics g)
	{
      paint(g);
	}

	public void drawString(Graphics g, String text, int x, int y)
   {
    for (String line : text.split("\n"))
        g.drawString(line, x, y += g.getFontMetrics().getHeight());
   }

	public void drawCenteredString(Graphics g, String text, Rectangle rect, Font font)
	{
    	FontMetrics metrics = g.getFontMetrics(font);
    	int x = rect.x + (rect.width - metrics.stringWidth(text)) / 2;
    	int y = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();
    	g.setFont(font);
    	g.drawString(text, x, y);
	}


}
 class Test
{

    public static void main (String[]args)
    {

         AcquireFrame a=new AcquireFrame();
    	System.out.println(a);



    }


}