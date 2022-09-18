
/**
 * Write a description of class FiniteFieldElement here.
 *
 * It's important to see what is included by extending FiniteVector.  There are 
 * many examples of finite vectors which are not elements of a finite field.  
 * (It's more like:  they are in some finite field but we don't need that so why construct
 * it?)  Even in the process of building a finite field we need to use a vector which is not 
 * a field element.  Not much is needed.  The constructor builds a complete set of field elements
 * as vectors over a prime field.
 */
public class FiniteFieldElement<PrimeField> extends FiniteVector<PrimeField>
{
    // instance variables - replace the example below with your own
    private int p,n;
    ArrayList<FiniteVector> fieldList = new ArrayList<FiniteVector>();
    /**
     * Constructor for objects of class FiniteFieldElement
     */
    public FiniteFieldElement()
    {
       
    }

    
}
