# Finite Fields

Finite fields are an important mathematical construct.  They are used in some aspects of pure mathematics, like Group Theory.  More importantly they are used in cryptography.  The reason for this is that while finding a power (exponentiation) of an element is easy, it is difficult to impossible to construct a finite logarithm in a finite amount of processing time.

Briefly, a finte field is a finite set of elements that are closed under all four arithmetic operations (Addition, Subtraction, Multiplication and Division).  

Computationally, Addition, Subtractiona are simple but Multiplicatiion and Division are somewhat tricky.  The order (number of elements) of a Finite Field is always p^n where p is a prime number and n is any positive integer.  In cryptography, one often chooses a large prime as a starting point.  There has also been some use of the Finite Field of order 2^6 =64.

