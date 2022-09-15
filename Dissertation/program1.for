C  This is the program for finding the eigenvalues in the oscillator case.  It assumes we know the Energy, this finds the "lambda" 
C values by finding values where the last two coefficents obtained from the recurrence relation are zero.  It does find all solutions for a given energy.
C  
      implicit real*8 (A-H.O-Z)
	  Dimension roots(15)
C read the tolerance from the runstream
	  read(5,*)tol
	  Open(Unit=13,File='Outfile.dat',Form='formatted', status='new')
C outer loop is on some values for Sigma, the strength of the Electric Field (The "Stark" field)
      Do 11 IS=0,4,2
	  Sigma=Dfloat(IS)
C The next loop is over some values for the angular momentum
      Do 11 J=1,5
	  Write(13,23)J,Sigma
 23   Format(1H, 2X, 'J=', 3X,I4,'Sigma=',1PE13.6)
C The next loop is over Energy Eigenvalues
      Do 11 Np=0,5
	  Write(13,24)Np
 24   Format(1H,2x,'Np=',I4)
      If(Np .NE. 0)go to 400
	  Roots(1)=-sigma*J/2.0;
	  NR=1
	  NR1=0
	  Go to 11
 400  NR=0
      Zlow=-100.0
C for the current value of the separation constant, calculate the last two coefficients.  Both must be zero.  The Recur
C is calculated in such a way that if the last one is zero so is the next to last.  This simply won't work in the general case.
      Call Recur(Np,J,Sigma,Zlow,Flow)
C This loop recalculates the coeffiecients until a sign change is found.  Then it finds the root.  Continue until all
C roots are found.
      Do 12, I=1,400
	  Ihigh=-100.0+0.5*I
      Call Recur(Np,J,Sigma,Zhigh,Fhigh)
	  IF(Flow-Fhigh)100,200,300
 100  Call Zeroin(J,Np,Sigma,Zlow,Zhigh,Flow,Fhigh,Tol,Roots,NR)
      Go to 300
 200  If(Flow .EQ. 0.0)Go to 300
      NR=NR+1
	  Roots(NR)=Zhigh
 300  Zlow=Zhigh
      Flow=Fhigh
C Print the results to the output file.
	  NR1=0
 2000 If((Nr-NR1) .LE. 5)Go to 1000
      Write(13,26)(Roots(K),K=NR1+1,NR1+5)
 26   Format(1H, (NR-NR1)((3x,1PE13.6))
      Close(Unit=13,dispose='Save')
	  Stop
	  End
	  
C This is the subroutine that does the binary search for the solutions.

      Subroutine Zeroin(J,NP,Sigma,Zlow,Zhigh,Flow,Fhigh,Tol,Roots,NR)
	  Implicit Real*8 (A-H,O-Z)
	  Dimension Roots(15)
	  Zold=Zlow
	  Znew=Zhigh
 10   Zmid = (Zold+Znew)/2.0
      Call Recur((Np,J,Sigma,Zmid,Fmid)
	  If(Fmid*Fhigh)100,200,300
 100  Flow=Fmid
      Zold=Zmid
	  Go to 400
 300  Fhigh = Fmid
      Znew = Zmid
 400  If(Abs(Znew-Zold) - Tol)200,300,10
 200  NR=NR+1
      Roots(NR)=Zmid
	  return
	  end
	  
C This subroutine finds the last coeffieint for the current series.  If it returns zero in Finval then the series terminates.

      Subroutine Recur(NP,J,Sigma,lambda,Finval)
	  implicit Real*8 (A-H,O-Z)
	  Dimension C(30)
	  Real*8 Lambda, Num
	  D(J,N) = (N+2.0)*(N+J/2.0+1.0)
	  Num(J,N,Lambda,Sigma)=Lambda+Sigma*(J/2.0+2.0*(N+1))
	  C(0)=1.0
	  C(1)=-Sigma-2.0*Lambda/J
	  Do 11 M=2,NP
	  Denom=D(J,M-2)
 11   C(M) = -(NUM(J,M-2,Lambda,Sigma)*C(N-1)+(NP-M+2)*C(M-2))/Denom
      Finval = NUM(J,NP-1,Lambda,Sigma)*C(NP)+C(NP-1)
	  Return
	  End
	  