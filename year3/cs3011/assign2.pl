%%%% Part 1

sen --> x(Count), [2], y(Count).

x(0) --> [].
x(succ(Count)) --> [0], x(Count). 
x(Count) --> [1], x(Count).

y(0) --> [].
y(succ(Count))--> [1], y(Count).
y(Count) --> [0], y(Count).

%%%%%Part2

/*neighbourhood --> neighbour1, neighbour2, neighbour3.

neighbour1 --> col1, nat1, pet1. 
neighbour2 --> col2, nat2, pet2.
neighbour3 --> col3,nat3, pet3.*/

n --> col(C1),
			nat(N1),
			pet(P1),
			col(C2),
			nat(N2),
			pet(P2), {C2 \= C1}, {N2 \= N1}, {P2 \= P1},
			col(C3),
			nat(N3),
			pet(P3), {C3 \= C2}, {C3 \= C1}, {N3 \= N2}, {N3 \= N1}, {P3 \= P2}, {P3 \= P1}.

col(red) --> [red].
col(blue) --> [blue].
col(green) --> [green].

nat(english) --> [english].
nat(japanese) --> [japanese].
nat(spanish) --> [spanish].

pet(snail) --> [snail].
pet(jaguar) --> [jaguar].
pet(zebra) --> [zebra].


%%%%% Part 3

reverse([]) --> [].
reverse([L|Ls]) --> reverse(Ls), [L].

mkList(N,L) :-
	numlist(1,N,X),
	reverse(X,L).

s(Sum) --> {Sum > 0},
					 {mkList(Sum,List)},
					 findVal(List,0,Sum,Ret),
					 {Ret =:= Sum}.

findVal(L,A,Sum,Ret) --> {member(Val,L)},
											 [Val],
											 {Anew is A + Val},
											 {Anew =< Sum},
											 findVal(L,Anew,Sum,Ret).

findVal(_,A,_,A) --> [].
