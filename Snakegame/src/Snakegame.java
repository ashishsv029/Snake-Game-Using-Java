import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import java.sql.*;
public class Snakegame extends Applet implements ActionListener,Runnable,KeyListener{
	//Connection con;
	//Statement st;
	//PreparedStatement ps;
	//ResultSet rs;
	Thread t;
	Image img,img1,img2,img3;
	boolean cd,cu,cl,cr,oldcd,oldcr,oldcl,oldcu;
	static int x=200,y=200;
	static int tempx,tempy; 
	int start=1,movc,fx=550,fy=100,count=0;
	static String str="#######",strcount,s3="#";//str="%¤¤¤¤¤" real snake
	int a=str.length(),once=1;
	int score=0,key=1,onkey=0;
	TextField t1,t2;
	Label l1,l2;
	static int arr[][]=new int[1000][2];
	static int highsc=00;
Button b1,b2,b3,b4,b5,b6,b7;
	
public void init()
{
	//setLayout(new BorderLayout());
	setFocusable(true);
	//requestFocusInWindow();
	setSize(500,500);
	img=getImage(getCodeBase(),"jack.jpg");
	img1=getImage(getCodeBase(),"mywall.jpg");
	img2=getImage(getCodeBase(),"mywall.jpg");
	img3=getImage(getCodeBase(),"mywall.jpg");
	setBackground(Color.BLACK);
	b5=new Button("start");
	b1=new Button("down");
	
	b2=new Button("left");
	b3=new Button("right");
	b4=new Button("up");
	b6=new Button("pause");
	b7=new Button("play");
	l1=new Label("SCORE");
	l2=new Label("HIGH SCORE");
	t1=new TextField(5);
	t2=new TextField(5);
	add(b5);
	//add(b1,BorderLayout.EAST);
	t1.setBounds(100,100,100,20);
	add(b1);
	
	add(b2);	
	add(b3);
	add(b4);
	add(b6);
	add(b7);
	setForeground(Color.WHITE);
	add(l1);
	setForeground(Color.BLACK);
	add(t1);
	setForeground(Color.WHITE);
	add(l2);
	setForeground(Color.BLACK);
	add(t2);
	b1.addActionListener(this);
	b2.addActionListener(this);
	b3.addActionListener(this);
	b4.addActionListener(this);
	b5.addActionListener(this);
	b6.addActionListener(this);
	b7.addActionListener(this);
	addKeyListener(this);
	/*try
	{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system", "1234");
		ps=con.prepareStatement("insert into score values(?)");
	}catch(Exception e) {e.printStackTrace();}*/
}
public void keyReleased(KeyEvent rr) {}
public void keyTyped(KeyEvent rrt) {}
public void keyPressed(KeyEvent ke)
{
	t=new Thread(this);
 
	if(ke.getKeyCode()==ke.VK_DOWN&&key==1)
	{
		showStatus("yea");
		System.out.println("ashishkei..................");
		onkey=1;
		cd=true;cl=cr=cu=false;
			movc=1;
	}
	if(ke.getKeyCode()==ke.VK_UP&&key==1)
	{
		onkey=1;
		cu=true;cl=cr=cd=false;
		movc=4;
	}
	if(ke.getKeyCode()==ke.VK_LEFT&&key==1)
	{
		cl=true;cr=cd=cu=false;onkey=1;
		movc=0;
	}
	if(ke.getKeyCode()==ke.VK_RIGHT&&key==1)
	{
		cr=true;cu=cd=cl=false;onkey=1;
		movc=2;
	}
	t.start();
}
public void actionPerformed(ActionEvent e)
{
	String ss=e.getActionCommand();
	t=new Thread(this);
	if((ss.equals("down")||ss.equals("start"))&&key==1) {
		onkey=1;
	cd=true;cl=cr=cu=false;
		movc=1;
		
		
	}
	else if(ss.equals("left")&&key==1)
	{
		cl=true;cr=cd=cu=false;onkey=1;
		movc=0;
		
	}
	else if(ss.equals("right")&&key==1)
	{
		cr=true;cu=cd=cl=false;onkey=1;
		movc=2;
		
	}
	else if(ss.equals("up")&&key==1)
	{onkey=1;
		cu=true;cl=cr=cd=false;
		movc=4;
	
	}
	else if(ss.equals("pause"))
	{
		System.out.println("ljj");
		oldcd=cd;
		oldcr=cr;
		oldcl=cl;
		oldcu=cu;
		cd=cr=cu=cl=false;
		t.suspend();
	}
	else if(ss.equals("play"))
	{
		cd=oldcd;
		cr=oldcr;
		cu=oldcu;
		cl=oldcl;
		t.resume();
	}
	
	t.start();
}
synchronized public void run()
{
	while(cd==true&&key==1) {
	try {Thread.sleep(100);}catch(InterruptedException rt) {}
	repaint();
	}
	while(cu==true&&key==1) {
		try {Thread.sleep(100);}catch(InterruptedException rt) {}
		repaint();
		}
	while(cl==true&&key==1) {
		try {Thread.sleep(100);}catch(InterruptedException rt) {}
		repaint();
		}
	while(cr==true&&key==1) {
		try {Thread.sleep(100);}catch(InterruptedException rt) {}
		repaint();
		}
}

public void paint(Graphics g)
{
	
	g.drawImage(img, 0, 0, 1400, 800, this);
	g.drawImage(img1, 0, 0, 50, 800, this);
	g.drawImage(img2, 0, 600, 1400, 70, this);
	g.drawImage(img3, 1320, 0, 50, 800, this);
	g.setColor(Color.GREEN);
	g.setFont(new Font("TimesRoman",Font.PLAIN,20));
	t2.setText(Integer.toString(highsc));
	g.drawString(s3,fx,fy);
	if(arr[0][0]==fx&&arr[0][1]==fy)
	{
		score=score+10;
		if(score>highsc) {
			highsc=score;
			try {
	    		//rs=st.executeQuery("select max(sc) as hs from score");
	    		//if(rs.getInt(1)<Integer.parseInt(t1.getText()))
	    		//{
	    			//ps.setInt(1,Integer.parseInt(t1.getText()) );
	    			t2.setText(t1.getText());
	    		}catch(Exception e) {}
			//t2.setText(Integer.toString(highsc));
					if(once==1) {try {Thread.sleep(500);}catch(InterruptedException e) {}
			g.setFont(new Font("Algerian",Font.PLAIN,150));
			g.drawString("NEW HIGH SCORE", 350, 400);once=0;}}
		t1.setText(Integer.toString(score));
		Random rand=new Random();
		int y=rand.nextInt(100+1+150)-150;//range 100 to -150
		System.out.println("mark="+y);
		int limitx=fx+y;
		int limity=fy+y;
		if(limity<0)
		{
			y=y+600;
		}
		if(limitx<0)
		{
			x=x+600;
		}
		if(limitx>1200)
		{
			x=x-600;
		}
		if(limity>700)
		{
			y=y-600;
		}
		int r=y%10;//this is to mark pixel in multiples of 5 i.e if no generated is 148 then it is converted into 150/140
		 r=10-r;
		y=y+r;
       str=str+"###";
       
       System.out.println("incr snake"+str);
		count=count+20;
		 strcount=Integer.toString(count);
	
		fx=fx+y;//instead of generating random nos can also use constants 100/200etc
		fy=fy+y;
		
	}
	g.setColor(Color.RED);
	if(movc==1) 
	{
     int v=0;
     if(start==1)
     {
    	 for(int i=0;i<str.length();i++) 
    	 {
    		 System.out.println(tempx+" "+tempy);
    		 if(i==0) 
    		 {
    			 tempx=x;
    			 tempy=y;
    			 y=y+10;
    			// System.out.println(x+" "+y);
    			 g.drawString(Character.toString(str.charAt(i)), x,y);
    			 arr[v][0]=x;
    			 arr[v][1]=y;
    			 v++;    	
    		 }
    		 else 
    		 {
    			 g.drawString(Character.toString(str.charAt(i)), tempx,tempy);
    			 arr[v][0]=tempx;
    			 arr[v][1]=tempy;
    			 tempx=tempx+5;
    			 v++;
    		 }
    	}
    	 start=0;
	}
    else
    {
    	
    	 for(int i=0;i<str.length();i++)
    	 {
    		 if(i==0)
    		 {
    			 tempx=arr[i][0];
    			 tempy=arr[i][1];
    			 //System.out.println("tempx"+tempx+"tempy"+tempy);
    			 arr[i][0]=arr[i][0];
    			 arr[i][1]=arr[i][1]+10;
    			 g.drawString(Character.toString(str.charAt(i)),arr[i][0] , arr[i][1]);
    		 }
    		 else
    		 { 
    			int ttempx=arr[i][0];
    			 int ttempy=arr[i][1];
    			 //System.out.println("tempxinelse"+tempx+"tempyinelse"+tempy);
    			 g.drawString(Character.toString(str.charAt(i)),tempx , tempy);
    			 arr[i][0]=tempx;
    			 arr[i][1]=tempy;
    			 tempx=ttempx;
    			 tempy=ttempy;
    			 
    		 }
    		 
    	 }
    	
     }
	}//end of down
	if(movc==4) 
	{
     int v=0;
     if(start==1)
     {
    	 for(int i=0;i<str.length();i++) 
    	 {
    		 //System.out.println(tempx+" "+tempy);
    		 if(i==0) 
    		 {
    			 tempx=x;
    			 tempy=y;
    			 y=y-10;
    			// System.out.println(x+" "+y);
    			 g.drawString(Character.toString(str.charAt(i)), x,y);
    			 arr[v][0]=x;
    			 arr[v][1]=y;
    			 v++;    	
    		 }
    		 else 
    		 {
    			 g.drawString(Character.toString(str.charAt(i)), tempx,tempy);
    			 arr[v][0]=tempx;
    			 arr[v][1]=tempy;
    			 tempx=tempx+5;
    			 v++;
    		 }
    	}
    	 start=0;
	}
    else
    {
    	
    	 for(int i=0;i<str.length();i++)
    	 {
    		 if(i==0)
    		 {
    			 tempx=arr[i][0];
    			 tempy=arr[i][1];
    			 //System.out.println("tempx"+tempx+"tempy"+tempy);
    			 arr[i][0]=arr[i][0];
    			 arr[i][1]=arr[i][1]-10;
    			 g.drawString(Character.toString(str.charAt(i)),arr[i][0] , arr[i][1]);
    		 }
    		 else
    		 { 
    			int ttempx=arr[i][0];
    			 int ttempy=arr[i][1];
    			 //System.out.println("tempxinelse"+tempx+"tempyinelse"+tempy);
    			 g.drawString(Character.toString(str.charAt(i)),tempx , tempy);
    			 arr[i][0]=tempx;
    			 arr[i][1]=tempy;
    			 tempx=ttempx;
    			 tempy=ttempy;
    			 
    		 }
    		 
    	 }
    	
     }
	}//end of down
     if(movc==0)
     {
    	 for(int i=0;i<str.length();i++)
    	 {
    		if(i==0)
    		{
    			tempx=arr[i][0];
    			tempy=arr[i][1];
    			arr[i][0]=arr[i][0]-5;
    			arr[i][1]=arr[i][1];
    			g.drawString(Character.toString(str.charAt(i)),arr[i][0], arr[i][1]);
    		}
    		else
    		{
    			int ttempx=arr[i][0];
    			int ttempy=arr[i][1];
    			g.drawString(Character.toString(str.charAt(i)), tempx, tempy);
    			arr[i][0]=tempx;
    			arr[i][1]=tempy;
    			tempx=ttempx;
    			tempy=ttempy;
    		}
    	 }
     }

     if(movc==2)
     {
    	 for(int i=0;i<str.length();i++)
    	 {
    		if(i==0)
    		{
    			tempx=arr[i][0];
    			tempy=arr[i][1];
    			arr[i][0]=arr[i][0]+5;
    			arr[i][1]=arr[i][1];
    			g.drawString(Character.toString(str.charAt(i)),arr[i][0], arr[i][1]);
    		}
    		else
    		{
    			int ttempx=arr[i][0];
    			int ttempy=arr[i][1];
    			g.drawString(Character.toString(str.charAt(i)), tempx, tempy);
    			arr[i][0]=tempx;
    			arr[i][1]=tempy;
    			tempx=ttempx;
    			tempy=ttempy;
    		}	 
    	 }
     }
    if(arr[0][0]<=45||arr[0][0]>=1300||arr[0][1]>=600)
 	{
    	if(onkey==1&&arr[0][0]>0&&arr[0][1]>0) 
    	{
    		
 		showStatus("snake died");
 		g.setColor(Color.GREEN);
 		g.setFont(new Font("Algerian",Font.PLAIN,150));
 		g.drawString("GAME LOST",  350, 400);
 		//g.drawString("Score="+Integer.toString(score), , );
 	    cd=cu=cl=cr=false;
 	    key=0;
 	    
 	    t.stop();
 	    } 
 	}
	/*for(int i=0;i<str.length();i++)
	{
		for(int j=0;j<2;j++)
			System.out.println("yuvalues"+arr[i][j]);
	}*/
}
}
