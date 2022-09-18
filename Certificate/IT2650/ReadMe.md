# IT 2650 Java Programming

This course is a basic introduction to Java Programming.  When I took it it was called ITMP 2650, that was in the Fall of 2010.  Most of the programs are not all that interesting but I include here the final project from the course.  The goal is to use "Java Swing" to output a Sierpinski Gasket.

The Sierpinski Gasket is produced from an infinite procedure.  The idea is, take an equilateral triangle, cut it into four triangles by attaching the midpoints of the line segments.  Then eliminate the triangle in the "middle".  Repeat for each remaining triangle.  The object that remains after an infinity of steps is the Sierpinski gasket.  It has an uncountable number of points (more like the real line than the integers), has zero Lebesque measure in the plane and has dimension 1.26.  

This was not the algorithm used in the construction, however.  This program uses the "Chaos Game" algorithm for constructing the Sierpinski Gasket.  The steps are:

<pre>
  1) Start with the vertices of the triangle.
  2) Randomly select any point in the interior.  Set that as current position
  3) Randomly select any vertex.
  4) Move half way from the current position to the vertex.
  5) Plot the current point, set it as the current position.
  6) Go to step 3, repeat until done.
</pre>
