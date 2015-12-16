
  
Create ROLE dev_ADMIN IDENTIFIED by rocLPassword;		-- Insufficent privilleges
Grant create table to dev_ADMIN;
Grant delete table to dev_ADMIN;
Grant alter table to dev_ADMIN;
REVOKE Grant create table FROM dev_ADMIN;

Create role server_ADMIN identified by serverPassword;
Grant select on table Lobbies to server_ADMIN;


--Grant update on Match to automaticServer;

-- ******* Constraint I was unable to implement due to insufficent privilleges
CREATE FUNCTION Check_LobbyAge (
    SongTocheck VARCHAR(25)
)
RETURNS VARCHAR(10)
AS
BEGIN
    DECLARE AGE int
    SELECT AGE = Age_Rating FROM Soundtrack where Song_Name = SongTocheck
    IF EXISTS (SELECT age FROM Player_Profile WHERE age < AGE )
        return 'True'
    return 'False'
END;

ALTER TABLE Match
    WITH CHECK ADD CONSTRAINT CK_CheckAge
    CHECK (Check_LobbyAge(Song_used) = 'False');  
