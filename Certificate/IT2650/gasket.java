package assignments.sierpinski;

import java.awt.*;
import javax.swing.*;

/*  This is the sierpinski gasket program.  It plots the points for the
 * sierpinski gasket.  The gasket is an object with a fractional 
 * dimension between 1 and 2.  The dimension is about 1.26.
 * Note that the JFrame window that is used is 512+10+10 which gives
 * a triangle with edge 512 with borders of width 10.  The top border is
 * slightly wider.
 * 
 * This program uses the "Chaos Game" algorithm for constructing the Sierpinski Gasket.
 * The steps are:
 * 1) Start with the vertices of the triangle.
 * 2) Randomly select any point in the interior.  Set that as current position
 * 3) Randomly select any vertex.
 * 4) Move half way from the current position to the vertex.
 * 5) Plot the current point, set it as the current position.
 * 6) Go to step 3, repeat until done.
 * 
 */
public class gasket extends JFrame 
{
	
	private static point[] points = new point[50000];
	
	private static final int SIZE=532;
	private static final String HEADING="Sierpinski's Gasket";
	
	private static point[] corners=new point[3];
	
	public gasket() 
	{
		setSize(SIZE,SIZE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle(HEADING);
		
	}
	
	public void paint(Graphics g)
	{
		super.paint(g);
		for(int n=0;n<50000;n++)
		{
			g.drawLine(points[n].getX(), points[n].getY(), 
					points[n].getX(), points[n].getY());
		}		
	}
	
	public static void main(String[] args) 
	{
		
		gasket drawing = new gasket();
		point currentPoint=new point(0,0);
		point targetPoint=new point(0,0);
		drawing.setVisible(true);
		/*
		 * First fix the corners.
		 */
		corners[0]=new point(10,SIZE-10);
		corners[1]=new point(SIZE-10,SIZE-10);
		corners[2]=new point(SIZE/2,SIZE-(int) ((SIZE-20.0)*Math.sqrt(3.0)/2.0));
		/*
		 * Initialize for the main loop.
		 */
		double x=Math.random();
		int j=(int)(3.0*x);
		currentPoint=new point(corners[j]);
		j=(int) (3.0*Math.random());
		targetPoint=new point(corners[j]);
		/*
		 * Collect all the points.
		 */
		for (int i=0;i<3;i++)
			points[i]=new point(corners[i]);
		for (int i=3;i<50000;i++)
		{
			points[i]=new point(currentPoint.FindMid(targetPoint));
			currentPoint=new point(points[i]);
			j=(int)(3.0*Math.random());
			targetPoint=new point(corners[j]);
		}
		/*
		 * Draw all the points.
		 */
		drawing.repaint();
	}

}
