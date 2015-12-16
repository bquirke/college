numeral(0).
numeral(s(X)) :- numeral(X).
%numeral(X) :- numeral(X).
numeral(X + Y) :- numeral(X), numeral(Y).
numeral(p(X)) :- numeral(X).
numeral(X-Y) :- numeral(X), numeral(Y).

% Exercise 1
add2(0,X,X).
add2(s(X),Y,s(Z)) :- add2(X,Y,Z).

add2(X + Y, W, Z) :- add2(X,Y,R1), add2(R1,W,Z), numeral(Z). 
add2(X,Y+W,Z) :- add2(Y,W,R1), add2(X,R1,Z), numeral(Z).

add2(X1 + X2, Y1 + Y2, Z) :- add2(X1,X2,RX), add2(Y1,Y2,RY), 
	add2(RX,RY,Z), numeral(Z).
	
% Exercise 2

add2(s(X),Y,s(Z)) :- add2(X,Y,Z), Z \=p(_).
add2(s(X), Y,Z) :- add2(X,Y,p(Z)).

add2(p(X), Y,p(Z)) :- add2(X,Y,Z), Z \=s(_).
add2(p(X), Y,Z) :- add2(X,Y,s(Z)).

%Exercise 4
add2(-X,Y,Z) :- minus(X, NEG_X), add2(NEG_X, Y, Z).
add2(X,-Y, Z):- minus(Y,NEG_Y), add2(X,NEG_Y,Z).

%Exercise 6
add2(X-Y,W,Z) :- minus(Y,NEG_Y),add2(X,NEG_Y,R1),add2(R1,W,Z).
add2(-(X-Y),W,Z) :- subtract(X,Y,R1),minus(R1,NEG_R1),add2(NEG_R1,W,Z),numeral(Z).


%Exercise 3

minus(0, 0).
minus(s(X), p(Y)) :- minus(X,Y), Y\=s(_).
minus(s(X),Y) :- minus(X, s(Y)).

minus(p(X),s(Y)) :- minus(X,Y), Y\=p(_).
minus(p(X),Y) :- minus(X, p(Y)).

%Exercise 5
subtract(0,X,X).
subtract(X,Y,Z) :- minus(Y,NEG_Y),add2(X,NEG_Y,Z).
subtract(X,-Y,Z) :- add2(X,Y,Z). 

%Exercise 6
subtract(X,(Y-W),Z) :- subtract(Y,W,R1),subtract(X,R1,Z), numeral(Z).

