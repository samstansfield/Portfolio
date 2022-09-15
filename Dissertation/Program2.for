C This is the main program for searching for eigenvalues of the separated equations.
C Note that output values from program are placed in a file connected to Unit 14. 
C Input is from Unit 15.

      implicit real*8 (A-H,O-Z)
	  Open(Unit=14,file='outfile.dat',status='new',form='formatted')
	  Close(Unit=14, dispose='save')
	  Open(Unit=15,file='infile.dat',status='old',form='formatted')
	  Read(15,*)Emin,Emax,Einc,Zmin,Zmax,Zinc,M,Lmin,Lmax,Acc,F
	  Close(Unit=15,dispose='save')
	  do 11 L=Lmin,Lmax
	  Alpha=DFloat(L)
	  Jmax=IDInt((Zmax-Zmin)/Zinc)
C Note that the integration starts at x=3.  This is because the actual equation used is in terms of x^2 so the long distance
C behavior is exp(-x^4) which is quite tiny at x=3.
	  Call RNGKUT(3,0D0,0.0D0,-1.0D-3,Zmin, Alpha,PF,PDF)
	  DO 12 J=1,Jmax
	  Znew=Zmin+J*Zinc
	  Call RNGKUT(3,0D0,0.0D0,-1.0D-3,M,Energy,Znew, Alpha,F,DF)
C 200 means we hit the zero straight on but precision is low so keep trying, otherwise 100
	  IF(DF)100,200,100
C 1000 means there was a sign change.  2000 means we hit a zero straight on within precision so save result.  200 is keep trying  
 100  IF(PDF/DF)1000,2000,200
C Note that DF is the derivative at the origin.  PDF is the previous DF
 1000 Call Zeroin(Zold, Znew,Energy,Alpha,PDF,DF,M,Acc,F)
      Go To 200
 2000 Open(Unit=14,file='outfile.dat',status='old', form='formatted', access='append')
      If(Abs(Znew) .LT. 1.0D-1)Znew=0.0D0
	  write(14,*)Energy,Znew,Alpha
	  close(Unit=14,dispose='save')
 200  PDF=DF
      Zold=Znew
C Done with this curve, go to a new one.
 12   Continue
C Get the next energy
      Energy = Energy + Einc
	  IF(Energy - Emax) 50,50,11
C Outer loop is on angular momentum eigenvalue
 11   Continue
      End
	  
C This subroutine zeros in on the solution.  Given an energy it refines the value of Z (lambda).  Looking for a zero in the
C derivative at the origin. It uses a combination of "Regula Falsi" and a simple binary search.  This is needed to avoid the lines
C where the derivative is infinite.  Note that sign changes in the derivative do occur where the infinities occur.
C P is the relative weighting of the two methods.  FP is the derivative at the origin.  M should be the number of passes through the 
C integration loop.  If Mout is less than M then an overflow occurred and the integration failed, indicating an irregular solution.
C Acc is the accuracy in "Z" (lambda).
      Subroutine Zeroin(PZ,Z,E,A,FPold,FPnew,M,Acc,P)
      implicit real*8(A-H,O-Z)
	  Zlow=PZ
	  Zhigh=Z
	  FPlow=FPold
	  FPhigh=FPnew
 10   Z1 = (Zhigh+Zlow)/2.0
      Z2 = (Zlow*FPhigh-Zhigh*FPlow)/(FPhigh-FPlow)
	  Zmid=Z1*P+Z2*(1.0-P)
	  Call RNGKUT(3,0D0,0.0D0,-1.0D-3,M,E,Zmid, A,Mout,Fmid,FPmid)
	  IF(Mout . NE. M)go to 20
C 100 move down.  300 Move up.  200 check if done
 50   IF(FPmid/FPhigh)100,200,300
 100  FPlow=FPmid
      Zlow=Zmid
	  Go to 400
 300  FPhigh=FPmid
      Zhigh=Zmid
 400  IF(Abs(Zhigh-Zlow)-Acc)200,200,10
 200  IF(Abs(Zmid) .LT. 1.0D-3)Zmid=0.0
      Open(Unit=14,file='outfile.dat',form='formatted',status='old',access='append')
	  Write(E,Zmid,A)
	  Close(Unit=14,dispose='save')
 20   return
      end
	  
C This subroutine does the Runge-Kutta integration of the differential equation.
      Subroutine RNGKUT(XI,XF,YI,YPI,M,E,Z,A,Mout,Yout,YPout)
	  implicit Real*8 (A-H,O-Z)
C this function gives the second derivative in terms of other quantities
	  FCN(E,Z,A,X,Y,YP)=-(A/X)*YP+(X**6-2*E*x**2-4*Z)*Y
C initialize
      YP=YPI
	  Y=YI
	  X=XI
	  H=(XF-XI)/M
C Integrate	  
	  Do 11 J=1,M
C The following statements define the fourth order Runge-Kutta for a second order ODE.
	  AK1 = H*FCN(E,Z,A,X,Y,YP)
	  AK2 = H*FCN(E,Z,A,X+H/2,Y+H*(YP/2+AK1/8,YP+AK1/2)
      AK3 = H*FCN(E,Z,A,X+H/2,Y+H*(YP/2+AK1/8,YP+AK2/2)
	  AK4 = H*FCN(E,Z,A,X+H,Y+H*(YP+AK3/2),YP+AK3)
	  Y = Y+H*(YP+(AK1+AK2+AK3)/6)
	  YP = YP+(AK1+(2*AK2+AK3)/2+AK4)/6
	  X=XI+J*H
	  Mout=J
C Check for overflow, return if error.  Note that Mout is the last value of J.
      IF(Abs(YP)-1,0D18)11,12,12
 11   Continue
 12   Yout=Y
      YPout=YP
	  Return
	  End
	  
 