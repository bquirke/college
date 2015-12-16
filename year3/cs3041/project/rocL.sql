--TEST

CREATE TABLE 	Player_Profile ( 
		Username VARCHAR(20) NOT NULL,
		Name VARCHAR(20) NOT NULL,
		Nationality VARCHAR(20) NOT NULL, 
		age INT NOT NULL,
		hours_logged INT NOT NULL, 
		PRIMARY KEY(Username));
	
INSERT INTO Player_Profile VALUES('FlowerPlucker13', 'Bryan Quirke',
		'Irish', 21, 48);
		
INSERT INTO Player_Profile VALUES('flyinBadger', 'Derek McGee',
		'Scottish', 15, 178);

INSERT INTO Player_Profile VALUES('johnnyDeppWho', 'Orlando Bloom',
		'American', 35, 2);
		
INSERT INTO Player_Profile VALUES('mcSquared', 'Alfonso Einstein',
		'German', 85, 12);
		
INSERT INTO Player_Profile VALUES('shakeNbake', 'John C Reily',
		'American', 41, 17);
		
INSERT INTO Player_Profile VALUES('oracle', 'Larry Ellison',
		'American', 71, 150);
		

INSERT INTO Player_Profile VALUES('iLuvDonuts', 'Keith Burn',
		'German', 24, 46);
		
INSERT INTO Player_Profile VALUES('F11', 'Mike Ross',
		'American', 34, 2);
		
INSERT INTO Player_Profile VALUES('gladiator2', 'Russell Crowe',
		'Austrailian', 53, 50);
		 

CREATE TABLE Game_Mode (
		GameMd VARCHAR(20) NOT NULL, 
		Players_Online INT NOT NULL,
		Rank_level VARCHAR(20) NOT NULL,
		Dedicated_servers INT NOT NULL,
		Team_size INT NOT NULL,
		PRIMARY KEY(GameMd),
		CHECK(Rank_Level IN ('Rookie', 'Semi-Pro', 'Pro', 'Veteran', 'Expert')));
	

INSERT INTO Game_Mode VALUES('Duel',7001,'Pro',25, 1);
INSERT INTO Game_Mode VALUES('Doubles',84501,'Rookie',80, 2);
INSERT INTO Game_Mode VALUES('Standard',100000,'Semi-Pro',100, 3);
INSERT INTO Game_Mode VALUES('Chaos',4561,'Pro',12, 4);
INSERT INTO Game_Mode VALUES('Mutator Mashup',20500,'Rookie',140, 3);


CREATE TABLE Arena (
		Arena_Name VARCHAR(20) NOT NULL,
		Turf_Type VARCHAR(20) NOT NULL,
		TimeOfDay VARCHAR(10) NOT NULL,
		Design VARCHAR(25) NOT NULL,
		Amount_Boosters INT NOT NULL,
		PRIMARY KEY(Arena_Name),
		CHECK(TimeOfDay IN ('Morning', 'Midday', 'Midnight', 'Dusk', 'Dawn')));

INSERT INTO Arena VALUES('DFH Stadium', 'Grass', 'Midday', 'Conventional Stadium',18);

INSERT INTO Arena VALUES('Urban Central', 'Tarmac', 'Midnight', 'Basketball Court',	18);

INSERT INTO Arena VALUES('Mannfield', 'Grass', 'Midday', 'Conventional Stadium', 18);

INSERT INTO Arena VALUES('Beckwith Park', 'Grass', 'Dawn', 'Pitch set in Forest', 18);

INSERT INTO Arena VALUES('Utopia Coliseum', 'Grass and Clay', 'Midday', 'Roman Coliseum', 18);

INSERT INTO Arena VALUES('WasteLand', 'Sand', 'Dusk', 'Mad Max-esque Arena', 18);


CREATE TABLE Soundtrack (
		Song_Name VARCHAR(25) NOT NULL,
		Artist VARCHAR(25) NOT NULL,
		Producer VARCHAR(25) NOT NULL,
		Duration VARCHAR(10) NOT NULL,
		Age_Rating INT NOT NULL,
		PRIMARY KEY(Song_Name));

INSERT INTO Soundtrack VALUES('Rocket League Theme', 'Mike Ault', 'Mike Ault', '1:28', 12);

INSERT INTO Soundtrack VALUES('Angel Wings', 'Mike Ault', 'Mike Ault', '6:08', 12);

INSERT INTO Soundtrack VALUES('Darkness', 'Christian de la Torre', 'Mike Ault', '7:30', 16);

INSERT INTO Soundtrack VALUES('Seeing Whats Next', 'Hollywood Principle', 'Simon Hebbler', '3:19', 12);

INSERT INTO Soundtrack VALUES('FACK', 'Eminem', 'Dr. Dre', '3:26', 18);


CREATE TABLE Car (
		Car_id INT NOT NULL,
		Owner VARCHAR(20) NOT NULL,
		Body_Type VARCHAR(25) NOT NULL,
		Decal VARCHAR(20),
		Flag VARCHAR(20),
		Hat VARCHAR(20),
		Wheel_Type VARCHAR(20) NOT NULL,
		PRIMARY KEY(Car_id));
		

INSERT INTO Car VALUES(20000, 'FlowerPlucker13', 'Backfire','Flames','Camo Flag', 'Sombrero','Lucci');

INSERT INTO Car VALUES(20001, 'flyinBadger', 'Breakout','Wings','Scotland Flag', NULL, 'Mountaineer');

INSERT INTO Car VALUES(20002, 'mcSquared','Paladin' ,'Racing Strip', NULL, 'Wizard Hat', 'Vortex');

INSERT INTO Car VALUES(20003, 'shakeNbake', 'Road Hog', NULL,'Safety', 'Hard Hat' , 'Sleek');

INSERT INTO Car VALUES(20004, 'oracle', 'Gizmo', NULL,'SQL Flag', 'Halo' , 'Californian');


CREATE TABLE Match (
		Match_Id INT NOT NULL,
		Venue_picked VARCHAR(20) NOT NULL,
		game_mode_picked VARCHAR(20) NOT NULL,
		Final_score VARCHAR(5) NOT NULL,
		MVP VARCHAR(20) NOT NULL,
		Song_used VARCHAR(25),
		PRIMARY KEY(Match_Id));


INSERT INTO Match VALUES(3000, 'Urban Central','Doubles', '3-2', 'mcSquared', 'Seeing Whats Next');

INSERT INTO Match VALUES(3001, 'Urban Central','Mutator Mashup', '10-2', 'oracle', 'Darkness');

INSERT INTO Match VALUES(3002, 'WasteLand','Chaos', '1-0', 'shakeNbake', 'Rocket League Theme');

INSERT INTO Match VALUES(3003, 'Urban Central','Doubles', '3-0', 'mcSquared', NUll);

INSERT INTO Match VALUES(3004, 'DFH Stadium','Standard', '7-4', 'flyinBadger', 'Darkness');



Create TABLE Match_Players(
		game_id INT NOT NULL,
		player_no INT NOT NULL,
		player_name VARCHAR(20) NOT NULL,
		FOREIGN KEY(game_id) REFERENCES Match(Match_Id),
		FOREIGN KEY(player_name) REFERENCES Player_Profile(Username));
		
INSERT INTO Match_Players VALUES(3000,1,'mcSquared');
INSERT INTO Match_Players VALUES(3000,2,'oracle');
INSERT INTO Match_Players VALUES(3000,3,'shakeNbake');
INSERT INTO Match_Players VALUES(3000,4,'FlowerPlucker13');

INSERT INTO Match_Players VALUES(3001,1,'flyinBadger');
INSERT INTO Match_Players VALUES(3001,2,'oracle');
INSERT INTO Match_Players VALUES(3001,3,'F11');
INSERT INTO Match_Players VALUES(3001,4,'gladiator2');
INSERT INTO Match_Players VALUES(3001,5,'iLuvDonuts');
INSERT INTO Match_Players VALUES(3001,6,'johnnyDeppWho');



-- Foreign Keys
Alter Table Match ADD FOREIGN KEY(Song_used) REFERENCES Soundtrack(Song_Name);

Alter Table Match ADD FOREIGN KEY(Venue_picked) REFERENCES Arena(Arena_Name);

Alter Table Match ADD FOREIGN KEY(game_mode_picked) REFERENCES Game_Mode(GameMd);

Alter Table Match ADD FOREIGN KEY(MVP) REFERENCES Player_Profile(Username);

Alter Table Car ADD FOREIGN KEY(Owner) REFERENCES Player_Profile(Username);




-- Constraints
Alter table Player_Profile ADD CHECK(age >3 AND age<110); --ageism

Alter Table Game_Mode ADD CHECK(Team_size >= 1 AND Team_size <=4);

Alter Table Arena ADD CHECK(Amount_Boosters >12 AND  Amount_Boosters <= 18);

Alter Table Match ADD CHECK(Match_id > 999 AND Match_Id < 10000);

Alter Table Car ADD CHECK(Car_id >=20000 AND Car_id < 99999); 

Alter Table Soundtrack ADD CHECK(Age_Rating >= 3 AND Age_Rating <= 110); -- ageism.....again


-- SECRUITY
Create view Lobbies AS Select
  Username, age, nationality,game_id From 
    player_profile, match_players, Match Where (Match_Id = game_id) AND (username = player_name);



-- TRIGGERS    
Create OR Replace TRIGGER Player_Deletion
After DELETE On Player_Profile
For each row  
when (OLD.username IS NOT NULL)
Declare 
	deleted_username VARCHAR(20);
BEGIN
	deleted_username := :OLD.username;
	
	UPDATE MATCH SET MVP = NULL where MVP = deleted_username;
	UPDATE Car SET Owner = NULL where Owner = deleted_username;
	UPDATE Match_Players SET player_name = NULL where player_name = deleted_username;
	
END Player_Deletion;
.
RUN;



   



