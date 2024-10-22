package edu.sdccd.cisc190;
import javax.swing.*;//animation
import java.awt.*;//shape and color
import java.awt.event.*;
import java.awt.image.*;
import java.text.RuleBasedCollator;
import java.awt.geom.Ellipse2D;
import java.awt.Rectangle;
public class EndOfYearProject extends JPanel implements KeyListener,Runnable
{
    private int x;
    private int y;
    private int a1, a2, av1, av2;
    private int b, bv;
    private Rectangle r1, e1, e2, e3, e4, e5;
    private JFrame frame;
    private Thread t;
    private boolean gameOn;
    private Font f;
    private int fails;
    private Image marioImage;
    private Image goombaImage;
    private boolean left = false;
    private boolean up = false;
    private boolean down = false;
    private boolean right = false;
    private int m = 72;
    private int n = 115;
    private int L = 40;
    private int[] xPoints = {m, m+3*L, m+3*L, m+4*L, m+4*L, m+11*L, m+11*L, m+16*L, m+16*L, m+13*L, m+13*L, m+12*L, m+12*L, m+5*L, m+5*L, m};
    private int[] yPoints = {n, n, n+6*L, n+6*L, n+L, n+L, n, n, n+7*L, n+7*L, n+L, n+L, n+6*L, n+6*L, n+7*L, n+7*L};
    private int numPoints = 16;
    private boolean repeat = true;
    private boolean fwrd = true;
    private int level;
    private int m2 = 120;
    private int n2 = 100;
    private int L2 = 40;
    private int[] x2Points = {m2, m2+3*L, m2+3*L, m2+14*L, m2+14*L, m2+11*L, m2+11*L, m2};
    private int[] y2Points = {n2, n2, n2+2*L, n2+2*L, n2+8*L, n2+8*L, n2+6*L, n2+6*L};
    private int coins;
    private int x2;
    private int y2;
    private boolean coin1held = false;
    private boolean coin1stored = false;
    private boolean coin2held = false;
    private boolean coin2stored = false;
    private boolean upArea = true;
    public EndOfYearProject()
    {
        frame=new JFrame();
        x=100;
        y=350;
        a1 = 512;
        a2 = 252;
        b = 171;
        fails = 0;
        level = 1;
        gameOn=true;

        r1 = new Rectangle(x,y,20,20);
        e1 = new Rectangle(a1,b,20,20);
        f=new Font("TIMES NEW ROMAN",Font.PLAIN,50);

        x2 = 170;
        y2 = 130;
        av1 = 120;
        av2 = 660;
        bv = 190;
        coins = 0;

        frame.addKeyListener(this);//looks for keyboard buttons
        frame.add(this);
        frame.setSize(800,500);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        t=new Thread(this);
        t.start();
    }
    public void paintComponent(Graphics g)//just draws everything. it is drawn in order of the code. draw background first
    {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        //fill background
        Color customColor = new Color(175, 169, 236);
        g2d.setPaint(customColor);
        g2d.fillRect(0,0,800,500);
        g2d.setPaint(Color.black);
        g2d.fillRect(0,0,800,50);

        //draw scoreboard
        g2d.setColor(Color.white);
        g2d.setFont(f);
        g2d.drawString("Level: "+level, 3, 43);
        g2d.drawString("Fails: "+fails, 620, 43);
        if(level==1)
        {

            //border
            g2d.setColor(Color.BLACK);
            Polygon border = new Polygon(xPoints, yPoints, numPoints);
            g2d.draw(border);
            g2d.setColor(Color.WHITE);
            g2d.fill(border);

            customColor = new Color(144,238,144);
            g2d.setColor(customColor);
            g2d.fillRect(m, n, 120, 280);
            g2d.fillRect(m+520, n, 120, 280);

            //grid
            customColor = new Color(222, 219, 250);
            g2d.setColor(customColor);
            g2d.fillRect(232, 355, 40, 40);
            g2d.fillRect(232, 275, 40, 40);
            g2d.fillRect(232, 195, 40, 40);

            g2d.fillRect(272, 315, 40, 40);
            g2d.fillRect(272, 235, 40, 40);
            g2d.fillRect(272, 155, 40, 40);

            g2d.fillRect(312, 275, 40, 40);
            g2d.fillRect(312, 195, 40, 40);

            g2d.fillRect(352, 315, 40, 40);
            g2d.fillRect(352, 235, 40, 40);
            g2d.fillRect(352, 155, 40, 40);

            g2d.fillRect(392, 275, 40, 40);
            g2d.fillRect(392, 195, 40, 40);

            g2d.fillRect(432, 315, 40, 40);
            g2d.fillRect(432, 235, 40, 40);
            g2d.fillRect(432, 155, 40, 40);

            g2d.fillRect(472, 275, 40, 40);
            g2d.fillRect(472, 195, 40, 40);

            g2d.fillRect(512, 315, 40, 40);
            g2d.fillRect(512, 235, 40, 40);
            g2d.fillRect(512, 155, 40, 40);

            g2d.fillRect(552, 115, 40, 40);

            //where mario goes
            g2d.setColor(Color.RED);
            g2d.fillRect(x,y,20,20);

            //where goombas goes
            g2d.setColor(Color.BLUE);
            g2d.fillOval(a1,b,20,20);
            g2d.fillOval(a2,b+36,20,20);
            g2d.fillOval(a1,b+72,20,20);
            g2d.fillOval(a2,b+108,20,20);
            g2d.fillOval(a1,b+144,20,20);
        }
        else if(level==2)
        {

			/*g2d.setColor(Color.BLACK);
			g2d.setFont(f);
			g2d.drawString("Good Job", 3, 50);
			g2d.drawString("You finished level "+(level-1)+"!", 3, 100);*/

            //border
            g2d.setColor(Color.BLACK);
            Polygon border2 = new Polygon(x2Points, y2Points, 8);
            g2d.draw(border2);
            g2d.setColor(Color.WHITE);
            g2d.fill(border2);

            customColor = new Color(144,238,144);
            g2d.setColor(customColor);
            g2d.fillRect(m2, n2, 120, 80);
            g2d.fillRect(m2+440, n2+240, 120, 80);

            //scoreboard addition
            g2d.setColor(Color.WHITE);
            g2d.setFont(f);
            g2d.drawString("Coins: "+coins+"/2", 300, 43);

            //grid
            customColor = new Color(222, 219, 250);
            g2d.setColor(customColor);
            for(int i=1; i<=28; i++)
            {
                if(i>21)
                {
                    g2d.fillRect(120+(80*(i-22)), 300, 40, 40);
                }
                else if(i>14)
                {
                    g2d.fillRect(160+(80*(i-15)), 260, 40, 40);
                }
                else if(i>7)
                {
                    g2d.fillRect(120+(80*(i-8)), 220, 40, 40);
                }
                else if(i>0)
                {
                    g2d.fillRect(160+(80*(i-1)), 180, 40, 40);
                }
            }

            //( where mario goes)
            g2d.setColor(Color.RED);
            g2d.fillRect(x2,y2,20,20);

            //( where goombas goes)
            g2d.setColor(Color.BLUE);
            g2d.fillOval(av1,bv,20,20);
            g2d.fillOval(av1,bv+40,20,20);
            g2d.fillOval(av2,bv+80,20,20);
            g2d.fillOval(av2,bv+120,20,20);

            //coins
            g2d.setColor(Color.YELLOW);
            if(!coin1held&&!coin1stored)
            {
                g2d.fillOval(400,210,15,15);
            }
            if(!coin2held&&!coin2stored)
            {
                g2d.fillOval(400,290,15,15);
            }

        }
        if(level==3)
        {
            g2d.setColor(Color.BLACK);
            g2d.setFont(f);
            g2d.drawString("                        Good Job", 5, 200);
            g2d.drawString("              You finished level "+(level-1)+"!", 3, 250);
        }

    }
    public void run()
    {
        while(true)
        {
            if(gameOn)
            {
                int c = 2;
                int r = 3;
                Rectangle c1;
                Rectangle c2;
                Polygon border;
                if(level==1)
                {
                    r1 = new Rectangle(x,y,20,20);
                    e1 = new Rectangle(a1,b,20,20);
                    e2 = new Rectangle(a2,b+36,20,20);
                    e3 = new Rectangle(a1,b+72,20,20);
                    e4 = new Rectangle(a2,b+108,20,20);
                    e5 = new Rectangle(a1,b+144,20,20);

                    if (r1.intersects(e1)||r1.intersects(e2)||r1.intersects(e3)||r1.intersects(e4)||r1.intersects(e5))
                        reset();


                    border = new Polygon(xPoints, yPoints, numPoints);
                    //System.out.println(x);

                    if(repeat)
                    {
                        fwrd = !(a2>512);
                    }
                    if(fwrd)
                    {
                        a2+=r;
                        a1-=r;
                    }
                    else
                    {
                        a2-=r;
                        a1+=r;
                        repeat = false;
                        if(a2<252)
                        {
                            repeat=true;
                        }
                    }
                    if(x>m+520)
                    {
                        level++;
                    }

                    if(left&&border.contains(new Rectangle(x-c, y, 20, 20)))
                    {
                        x-=c;
                    }
                    if(right&&border.contains(new Rectangle(x+c, y, 20, 20)))
                    {
                        x+=c;
                    }
                    if(down&&border.contains(new Rectangle(x, y+c, 20, 20)))
                    {
                        y+=c;
                    }
                    if(up&&border.contains(new Rectangle(x, y-c, 20, 20)))
                    {
                        y-=c;
                    }

                }
                else if(level==2)
                {
                    boolean previousUp = upArea;
                    r1 = new Rectangle(x2,y2,20,20);
                    e1 = new Rectangle(av1,bv,20,20);
                    e2 = new Rectangle(av1,bv+40,20,20);
                    e3 = new Rectangle(av2,bv+80,20,20);
                    e4 = new Rectangle(av2,bv+120,20,20);
                    if(!coin1held)
                    {
                        c1 = new Rectangle(400,210,15,15);
                        if(r1.intersects(c1))
                        {
                            coin1held = true;
                            coins++;
                        }
                    }
                    if(!coin2held)
                    {
                        c2 = new Rectangle(400,290,15,15);
                        if(r1.intersects(c2))
                        {
                            coin2held = true;
                            coins++;
                        }
                    }

                    Rectangle goal = new Rectangle(m2+440, n2+240, 120, 80);
                    Rectangle start = new Rectangle(m2, n2, 120, 80);
                    if(r1.intersects(goal))
                    {
                        if(coins==2)
                        {
                            level++;
                        }
                        upArea = false;
                        if(coin1held)
                        {
                            coin1stored=true;
                        }
                        if(coin2held)
                        {
                            coin2stored=true;
                        }

                    }
                    if(r1.intersects(start))
                    {
                        upArea = true;
                        if(coin1held)
                        {
                            coin1stored=true;
                        }
                        if(coin2held)
                        {
                            coin2stored=true;
                        }
                    }

                    if(r1.intersects(e1)||r1.intersects(e2)||r1.intersects(e3)||r1.intersects(e4))
                    {
                        if(coin1held&&!coin1stored)
                        {
                            coin1held=false;
                            coins--;
                        }
                        if(coin2held&&!coin2stored)
                        {
                            coin2held=false;
                            coins--;
                        }
                        reset();
                    }

                    border = new Polygon(x2Points, y2Points, 8);
                    if(left&&border.contains(new Rectangle(x2-c, y2, 20, 20)))
                    {
                        x2-=c;
                    }
                    if(right&&border.contains(new Rectangle(x2+c, y2, 20, 20)))
                    {
                        x2+=c;
                    }
                    if(down&&border.contains(new Rectangle(x2, y2+c, 20, 20)))
                    {
                        y2+=c;
                    }
                    if(up&&border.contains(new Rectangle(x2, y2-c, 20, 20)))
                    {
                        y2-=c;
                    }

                    if(repeat)
                    {
                        fwrd = !(av2<120);
                    }
                    if(fwrd)
                    {
                        av2-=r;
                        av1+=r;
                    }
                    else
                    {
                        av2+=r;
                        av1-=r;
                        repeat = false;
                        if(av2>660)
                        {
                            repeat=true;
                        }
                    }

                }
                try
                {
                    t.sleep(10);
                }
                catch(InterruptedException e)
                {
                }
                repaint();
            }
        }
    }

    public void keyPressed(KeyEvent ke)
    {
        //look up keycodes online.  39 is right arrow key
        System.out.println(ke.getKeyCode());
        int key = ke.getKeyCode();
        if (key==37)
            left = true;
        if (key==39)
            right = true;
        if (key==38)
            up = true;
        if (key==40)
            down = true;
    }

    public void keyReleased(KeyEvent ke)
    {
        int key = ke.getKeyCode();
        if (key==37)
            left = false;
        if (key==39)
            right = false;
        if (key==38)
            up = false;
        if (key==40)
            down = false;
    }
    public void keyTyped(KeyEvent ke)
    {
    }

    public void reset()
    {
        if(level==1)
        {
            x=100;
            y=350;
        }
        else if(level==2)
        {
            if(upArea)
            {
                x2=170;
                y2=130;
            }
            else if(!upArea)
            {
                x2=610;
                y2=370;

            }
        }
        fails++;
    }

    public static void main(String[] args)
    {
        EndOfYearProject app = new EndOfYearProject();
    }
    //hi
}

