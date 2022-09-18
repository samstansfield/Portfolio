/*  This class is for working with "Finite Fields".  These are mathematical objects which have 
     * all of the usual properties of a "Field", that is comutativity, associativity and 
     * distributivity of the operations multiplication and addition.  Finite fields in particular 
     * have a finite number of elements (the order).  They must be of "prime power order", 
     * ie, a power of a prime number.  They are used in a variety of places in mathematics and in
     * cryptography.  The amazing part is that the non-zero elements are closed under division.  
     * The first step in the construction of a general finite field is to construct a prime field.  
     * The prime fields have prime order.  Elements of a prime field are integers mod p.  {0,1,...,p-1}
     */
/**
 * 
 * To construct an Fq requires first constructing a complete set of vectors over 
 * Fp of dimension n.  Addition in the general field is vector addition of the field
 * elements. There are q=p^n such vectors
 * 
 * To construct the multiplication table one includes an irreducible 
 * polynomial of degree n.  This polynomial has to be irreducible over Fp.  
 * (not factorizable over Fp).  One uses the polynomial to reduce the order when 
 * multiplying.  Any irreducible polynomial will work.  Different choices will lead to 
 * different multiplication tables but the resulting fields are still isomorphic.
 * 
 * It is a fact that the non-zero elements are cyclic under multiplication, this means that
 * we can write each such element as a^r where a is a suitable chosen element and 0<r<q-2.  
 * Not all choices of a will work if q-1 is composite.  This is tricky computationally.
 * 
 * The main goal of the constructors is to produce the multiplication and division tables.
 * Addition of field elements is the same as Vector addition.
 * 
 * One must be careful with definitions in this project.  What I want to be able to do is create a 
 * Matrix<MathFields> class.  This class will actually work with any division ring (like the 
 * Quaternions).  One must implement the MathFields imterface for the ring in question.
 */
  
 public class FiniteField<FiniteFieldElement> implements MathFields
 {
    private FiniteVector[][] multArray;  //  Multiplication table.
    private FiniteVector[][] divArray;  // Division table.
    private FiniteVector polynomial;
    private int p,q,n;
    
    
    /**
     * Constructors for objects of class FiniteField.  The first is for "prime" fields.  The 
     * second is for general fields.  Constructing the general field requires first constructing
     * a prime field.  
     * 
     * A finite field is three things.  A base field, a set of vectors over the base field, a rule for
     * multiplying vectors.  An element of a finite field is a finite vector.
     */
    public FiniteField(int p)
    {
        n = 0;
        return PrimeField(p);
    }
    /*  This constructor does not require the input of a polynomial. 
     *  It uses a set of polynomials of the form:  x^n=(x+1) mod p, which are irreducible for any p.
     *  The solutions to this equation are always either complex or irrational for any prime p.
     */
    public FiniteField(int p, int n)
    {
        
    }
    /*
     * Note that the above consructor does not use a Conway polynomial.  This programs does not compute
     * them.  This constructor will accept the input of a polynomial as a vector of integers of length n.
     *
    public FiniteField(int p, int n, polynomial)
    {
        // initialise instance variables
       
    }

    /**
     * The multiplication and division methods just look up the answer in the tables.
     */
    public FiniteVector Multiply(FiniteVector a, FiniteVector b)
    {
        // put your code here
        
    }
    public FiniteVector Divide(FiniteVector a, FiniteVector b) 
                                                        throws DivisionByZeroException
    {
        // put your code here
        
    }
    /*
     * Java does not allow for the indices of an array to be an array.  The following two methods
     * convert between a FiniteVector and an array index.  This might be done with a multi dimensional array 
     * but that could be tricky because we don't know how many indices it would have a-priori.
     * They are private because they should only be accessed from the buildMultTable method.
     * 
     * Here's how it works.  If the vector is in the form:  sum(Vnx^r) where x is the solution of the 
     * irreducible polynomial.  r<=n then the index is sum(Vnp^r) if all the Vn are mod p then this will
     * produce an integer between 0 and q-1.  Getting the index is then a matter of multiplying and adding.
     * Getting the vector from the index is a matter of remaindering and division.
     */
    private int getIndex(FiniteVector V)  
    {
        
        
    }
    private FiniteVector getVector(int index)
    {
        
        
    }
    public int getp()
    {
        return p;
    }
    public int getq()
    {
        return p^n;
    }
    public int getn()
    {
        return n;
    }
    public void buildMultTable(FiniteVector polynomial)  //  Builds the multiplication and division tables.
    {
    }
}
