{-# LANGUAGE StandaloneDeriving #-}

module Main where

import Test.HUnit
import Test.Framework as TF (defaultMain, testGroup, Test)
import Test.Framework.Providers.HUnit (testCase)
import Test.Framework.Providers.QuickCheck2 (testProperty)

import qualified System.Console.ANSI as Console
import qualified Data.List as List

import HaskellGame.Datatypes
import HaskellGame.Utils
import HaskellGame.Interaction

import Debug.Trace as DT

deriving instance Show Scene
deriving instance Show Map

deriving instance Eq Scene
deriving instance Eq Map
deriving instance Eq Object
deriving instance Eq Tile
deriving instance Eq Player

deriving instance Ord Monster

{- Displayimng entities -}

displayP p
 = "Player="
   ++"{"++show (hitpoints p)
   ++","++show (experience p)
   ++","++show (stats p)
   ++","++show (skills p)
   ++","++show (pos p)
   
displayM (Dragon h a p)
 = "Dragon="++show h++","++show a++","++show p
displayM (Zombie h a p)
 = "Zombie="++show h++","++show a++","++show p


{- HUnit Tests -}

test_no_collide_walls =
  let theMap = "###" ++
               "#.#" ++
               "###"
      thePlayer = Player 0 0 [] [] (1, 1)
      theScene = (Scene (createMap 3 3 theMap) thePlayer [] [] [])
  in
    theScene @=? (handleInput 'j' theScene)


test_no_collide_objects =
  let theMap = "###" ++
               "#.#" ++
               "#.#" ++
               "###"
      thePlayer = Player 0 0 [] [] (1, 1)
      theScene = (Scene (createMap 3 4 theMap) thePlayer [Chest (1,2)] [] [])
  in
    theScene @=? (handleInput 'k' theScene)

test_movement =
  let theMap = "###" ++
               "#.#" ++
               "#.#" ++
               "###"
      thePlayer = Player 0 0 [] [] (1, 1)
      theScene = (Scene (createMap 3 4 theMap) thePlayer [] [] [])
      expectedScene = (Scene (createMap 3 4 theMap) (thePlayer { pos = (1, 2) }) [] [] [])
  in
    expectedScene @=? (handleInput 'k' theScene)

{- Test that attacking works as expected -}



-- Check that attacking nothing works
test_attack_nothing =
  let theMap = "###" ++
               "#.#" ++
               "###"
      thePlayer = Player 0 0 [] [] (1, 1)
      theScene = (Scene (createMap 3 3 theMap) thePlayer [] [] [])
      expectedScene = theScene { messages = [(Console.Red, "You flail wildly at empty space! Your attack connects with nothing.")] }
  in
    expectedScene @=? (handleInput 'a' theScene)

-- Check that the correct amount of damage is done, and the correct
-- messages are reported when a player attacks a monster
test_attack_one =
  let theMap = "###" ++
               "#.#" ++
               "#.#" ++
               "###"
      thePlayer = Player 10 0 [ ("Strength", 5), ("Toughness", 1) ] [ ("Fisticuffs", 1) ] (1, 1)
      theMonster = Dragon 20 2 (1, 2)
      theScene = (Scene (createMap 3 4 theMap) thePlayer [] [theMonster] [])
      expectedPlayer = thePlayer { hitpoints = (hitpoints thePlayer) - 1 }
      expectedMonster = Dragon 17 2 (1, 2)
      expectedMessages = [(Console.Red, "D hits P for 1 damage!"),
                          (Console.Red, "P hits D for 3 damage!")]
      newScene = doAttack theScene -- handleInput 'a' theScene
  in do
    displayP expectedPlayer @=? (displayP $ player newScene)
    displayM expectedMonster @=? (displayM $ head $ monsters newScene)
    expectedMessages @=? messages newScene

-- Check that attacking multiple monsters works as expected
test_attack_several =
  let theMap = "#####" ++
               "#...#" ++
               "#...#" ++
               "#####"
      thePlayer = Player 10 0 [ ("Strength", 5), ("Toughness", 1) ] [ ("Fisticuffs", 1) ] (2, 1)
      theMonsters = [Dragon 20 2 (1, 1), Dragon 20 2 (3, 1)]
      theScene = (Scene (createMap 5 4 theMap) thePlayer [] theMonsters [])
      expectedPlayer = thePlayer { hitpoints = (hitpoints thePlayer) - 2 }
      expectedMonsters = [Dragon 17 2 (1, 1), Dragon 17 2 (3, 1)]
      expectedMessages = [(Console.Red, "D hits P for 1 damage!"),
                          (Console.Red, "P hits D for 3 damage!"),
                          (Console.Red, "D hits P for 1 damage!"),
                          (Console.Red, "P hits D for 3 damage!")]
      newScene = doAttack theScene --m handleInput 'a' theScene
  in do
    displayP expectedPlayer @=? (displayP $ player newScene)
    (List.map displayM $ List.sort expectedMonsters) 
      @=? (List.map displayM $ List.sort $ monsters newScene) --they could be reordered so sort both first
    expectedMessages @=? (messages newScene)

-- Check that attacking monsters only attacks those in range
test_attack_range =
  let theMap = "#####" ++
               "#...#" ++
               "#...#" ++
               "#####"
      thePlayer = Player 10 0 [ ("Strength", 5), ("Toughness", 1) ] [ ("Fisticuffs", 1) ] (2, 1)
      theMonsters = [Dragon 20 2 (1, 1), Dragon 20 2 (1, 3)]
      theScene = (Scene (createMap 5 4 theMap) thePlayer [] theMonsters [])
      expectedPlayer = thePlayer { hitpoints = (hitpoints thePlayer) - 1 }
      expectedMonsters = [Dragon 17 2 (1, 1), Dragon 20 2 (1, 3)]
      expectedMessages = [(Console.Red, "D hits P for 1 damage!"),
                          (Console.Red, "P hits D for 3 damage!")]
      newScene = handleInput 'a' theScene
  in do
    displayP expectedPlayer @=? (displayP $ player newScene)
    (List.map displayM $ List.sort expectedMonsters) 
      @=? (List.map displayM $ List.sort $ monsters newScene) --they could be reordered so sort both first
    expectedMessages @=? (messages newScene)


{- QuickCheck Tests -}

prop_takesome_take :: Int -> Bool
prop_takesome_take n =
  let xs = [0..9]
  in takesome n xs == take n xs

prop_dropsome_drop :: Int -> Bool
prop_dropsome_drop n =
  let xs = [0..9]
  in dropsome n xs == drop n xs

main = defaultMain tests

tests :: [TF.Test]
tests = [
          testGroup "Test Collisions" [
            testCase "Wall Collision" test_no_collide_walls,
            testCase "Object Collision" test_no_collide_objects,
            testCase "Player Movement" test_movement
          ],

          testGroup "takesome and dropsome" [
            testProperty "takesome works like Prelude.take" prop_takesome_take,
            testProperty "dropsome works like Prelude.drop" prop_dropsome_drop
          ],
          
          testGroup "\nTest Monster Battling [40 marks]" [
            testCase "\nATTACKING nothing" test_attack_nothing,
            testCase "\nATTACKING one monster" test_attack_one,
            testCase "\nATTACKING several monsters" test_attack_several,
            testCase "\nATTACKING monsters out of range" test_attack_range
          ]

        ]
