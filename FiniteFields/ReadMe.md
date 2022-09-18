# Finite Fields

Finite fields are an important mathematical construct.  They are used in some aspects of pure mathematics, like Group Theory.  More importantly they are used in cryptography.  The reason for this is that while finding a power (exponentiation) of an element is easy, it is difficult to impossible to calculate a finite logarithm in a reasonable amount of processing time.

Briefly, a finite field is a finite set of elements that are closed under all four arithmetic operations (Addition, Subtraction, Multiplication and Division).  

Computationally, Addition, Subtraction are simple but Multiplicatiion and Division are somewhat tricky.  The order (number of elements) of a Finite Field is always p^n where p is a prime number and n is any positive integer.  The p^n elements are vectors of dimension n over a field of order p.  In cryptography, one often chooses a large prime as a starting point.  There has also been some use of the Finite Field of order 2^6 =64.  The construction of non-prime order fields does require the use of an irreducible polynomial of degree n+1 (irreducible in the sense that it cannot be solved over the field of order p).  The construction of the multiplication and division tables do rely on this polynomial, although fields of the same order resulting from different polynomials are isomorphic.  All of the (non-zero) elements of the field are powers of a single element called the generator so that the field itself is cyclic under multiplication.

