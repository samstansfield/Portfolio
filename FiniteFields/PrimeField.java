
/**
 * Write a description of class PrimeFields here.
 *
 * A prime field is a set of integers with arithmetic mod p, where p is a prime.  
 * These will be the set {0,...,p-1}.  Addition, subtraction and multiplication work
 * fine with the java % operator.  Division is a problem though, the % doesn't
 * work correctly.  pxp matrices are created which stand as multiplication and 
 * division tables.   
 */
import java.util.*;
public class PrimeField implements MathFields
{
  
    private int p;
    private int[][] mulMat;
    private int[][] divMat;
    public PrimeField(int p) 
    {  //  This is the constructor for prime order.
        mulMat = new int[p][p];
        divMat = new int[p][p];
        this.p = p;
        for(int i=2; i<=Math.sqrt(p); i++)
        {
            try
            {
                if ((p%i)==0)  //  Check if p is prime.
                {
                    throw new NonPrimeOrderException();  //  Stop the program if p isn't prime.
                }
            }
            catch(NonPrimeOrderException e)
            {
                System.out.println("p must be prime");
                System.exit(0);
            }
        }
        
        for(int j=0; j<p;j++)     //  This loop builds the multiplication and 
                                        //  Division tables.
                                       
        {
            for(int k=0;k<p;k++)
            {
                int r=(j*k)%p;
                mulMat[j][k]=r;    //  j*k = r
                if(j != 0)divMat[r][j]=k;    //  r/j = k
                
                    
            }
        }
    }
    
    public Object Divide(Object A, Object B)
    {
        Integer i = (Integer)A;
        Integer j = (Integer)B;
        {
            if(j != 0)return divMat[i][j];
            else 
            {
                System.out.println("No division by zero.");
                System.exit(0);
                return 0;
            }
        }  
    }
    
    public Object Add(Object A, Object B)
    {
        Integer i = (Integer)A;
        Integer j = (Integer)B;
        return (i+j)%p;
    }
    public Object Subtract(Object A, Object B)
    {
        Integer i = (Integer)A;
        Integer j = (Integer)B;
        return (i-j)%p;
    }
    public Object Multiply(Object A, Object B)
    {
        Integer i = (Integer)A;
        Integer j = (Integer)B;
        return mulMat[i][j];
    }
    
   
   
    
    
    int getp()
    {
        return this.p;
    }
    public void showTables()
    {
        System.out.println("Multiplication");
        for(int i=0;i<p;i++)
        {
            System.out.println();
            for(int j=0;j<p;j++) 
            {
                System.out.print(mulMat[i][j] + " ");
            }
        }
        System.out.println();
        System.out.println("Division");
        for(int i=0;i<p;i++)
        {
            System.out.println();
            for(int j=0;j<p;j++)
            {
                System.out.print(divMat[i][j] + " ");
            }
        }
    }
        
}