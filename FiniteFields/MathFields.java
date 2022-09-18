
/**
 * 
 *
 * This is intended to be used as a type for the invocation of a future "Matrix" class which will 
 * do algebra with matrices.  Original plan is for matrices over finite fields but could use Real
 * or Complex as well by implementing this.  If one is careful it could also be used for division
 * rings.  (The Quaternions form a division ring so one could work with matrices of Quaternions.)
 */
public interface MathFields
{
    public Object Add(Object A, Object B);  //  returns A+B
    public Object Subtract(Object A, Object B);  //  returns A-B
    public Object Multiply(Object A, Object B);   //  returns A*B
    public Object Divide(Object A, Object B) throws DivisionByZeroException;  //  returns A/B
}
