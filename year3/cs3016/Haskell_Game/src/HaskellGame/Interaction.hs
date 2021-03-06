module HaskellGame.Interaction where

import Prelude (
                Num(..), Eq(..), Show(..),
                Bool(..), Char(), Int(),
                (||), (.), otherwise, not, fst, snd
               )

import qualified System.Console.ANSI as Console
import qualified Data.List as List
import Data.List ((++), (!!), elem, any, filter, delete, null)

import HaskellGame.Datatypes
import HaskellGame.Graphics
import HaskellGame.Battle

{-
  Check if the player's new position would collide with something.
  Return True if there would be a collision.
  Return False if there would be no collision.
-}

detectCollision :: Scene -> Point -> Bool
detectCollision theScene (x, y) =
  let tile = ((contents (map theScene)) !! y) !! x
      objectPositions = List.map position (objects theScene)
      monsterPositions = List.map position (monsters theScene)
  in notWalkable tile || (any (== (x, y)) (objectPositions ++ monsterPositions))
  where
    notWalkable Grass = False
    notWalkable _     = True

{- Handle a key press from the player -}

handleInput :: Char -> Scene -> Scene
handleInput c theScene
  | c `elem` ['i', 'j', 'k', 'l'] = movePlayer c theScene
  | c == 'a'                      = doAttack theScene
  | otherwise                     = theScene
  where
    movePlayer :: Char -> Scene -> Scene
    movePlayer keyPressed oldScene =
      let (x, y) = position (player oldScene)
          newPosition = case keyPressed of
                          'i' -> (x, (y-1))
                          'j' -> ((x-1), y)
                          'k' -> (x, (y+1))
                          'l' -> ((x+1), y)
                          _   -> (x, y)
          newPlayer = (player oldScene) { pos = newPosition }
          isCollision = detectCollision oldScene newPosition
      in if isCollision then oldScene
         else oldScene {player = newPlayer}

missedMessage :: [Message]
missedMessage = [(Console.Red, "You flail wildly at empty space! Your attack connects with nothing.")]

hitMessage :: Monster -> Int -> Player -> Int -> [Message]
hitMessage monster monsterDamage player playerDamage =
  [(Console.Red, show monster ++ " hits " ++ show player  ++ " for " ++ show playerDamage  ++ " damage!"),
   (Console.Red, show player  ++ " hits " ++ show monster ++ " for " ++ show monsterDamage ++ " damage!")]

-- -->

multipleHitMsgs :: [Monster] -> [Player] -> [Monster] -> [Message]
multipleHitMsgs [] (h:[]) _ = []
multipleHitMsgs (hm:mons) (hp:nhp:plys) (hc:combs) =
	let
		monsterDmg = (health hc) - (health hm)
		playerDmg = (health hp) - (health nhp)
		theMsgs = hitMessage hm monsterDmg hp playerDmg
	in theMsgs ++ (multipleHitMsgs (mons) (nhp:plys) (combs))
   
doFight :: Int -> Player -> [Monster] -> [(Player, Monster)]
doFight 0 _ [] = []
doFight n p (x:xs) =
  let theFight = fight (p,x) 
  in theFight:(doFight (n-1) (fst(theFight)) xs)

doAttack :: Scene -> Scene
doAttack (Scene theMap p ob mons msgs) =
	let
		distList = List.map (distance p) mons										
		monsPos = List.findIndices (==1) distList						
																															
		
	in if monsPos == [] then
		let returnMsgs = msgs ++ missedMessage										
  	in (Scene theMap p ob mons returnMsgs)										
  	
  else
		let
			combatants = List.map (mons !!) monsPos							
			combListLength = List.length combatants									
			fightResult = doFight combListLength p combatants
			updatedCombatants = List.last (fightResult)							
			updatedPlayer = fst(updatedCombatants)
			updatedMonster = snd(updatedCombatants)
			inBattlePlayers = List.map (fst) fightResult						
			allPlayers = p:inBattlePlayers
			inBattleMons = List.map (snd) fightResult								
			noBattleMons = mons List.\\ combatants									
			afterBattleMons = inBattleMons ++ noBattleMons					
			playerDmg = (health p - health updatedPlayer)
			monsDmg = (health (List.last combatants) - health updatedMonster)
			battleMessage = hitMessage updatedMonster monsDmg updatedPlayer playerDmg
			newMsgs = multipleHitMsgs inBattleMons allPlayers combatants
		 
		in (Scene theMap updatedPlayer ob afterBattleMons (msgs++newMsgs))			









