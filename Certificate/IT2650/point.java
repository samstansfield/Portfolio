package assignments.sierpinski;

/*  This is the basic type used for the sierpinski gasket problem.  It
 * defines a point on the plot and provides some basic methods for 
 * manipulating points.
 * 
 */

public class point
{
	int x,y;
	public point(int x, int y)
	{
		this.x=x;
		this.y=y;
	}
	public point(point obj)
	{
		this.x=obj.x;
		this.y=obj.y;
	}
	
	public  point FindMid(point p1)
	{
		int Xmid=(this.getX()+p1.getX())/2;
		int Ymid=(this.getY()+p1.getY())/2;
		return new point(Xmid,Ymid);
	}
	public int getX()
	{
		return this.x;
	}
	public int getY()
	{
		return this.y;
	}
}