#include <string>
/*
	The shape class is very general.  The only instance variable is the area.
	Could also include circumferece similarly.
*/

class Shape 
{
	private:
		double area;
	protected:
		Shape();
		virtual ~Shape();
		virtual double calculateArea();
		double getArea() const;
		void setArea(double A);
		virtual void print();
};

class Rectangle : public Shape
{
	private:
		double length, width;
	public:
		Rectangle():Shape(){};
		Rectangle(double length, double width);
		virtual ~Rectangle(){};
		double getLength() const;
		double getWidth() const;
		double calculateArea();
		void setLength(double length);
		void setWidth(double width);
		void print();
};

class Triangle : public Shape
{
	private:
		double base, height;  //  the base and the height are not enough information to determine the triangle.
	public:
		Triangle():Shape(){};
		Triangle(double base, double height);
		virtual ~Triangle(){};
		double getBase() const;
		double getHeight() const;
		double calculateArea();
		void setBase(double base);
		void setHeight(double height);
		void print();
};
