# The Goose Game Kata Android Version

## Credits

The original version of this kata can be found [at this address](https://github.com/xpeppers/goose-game-kata) and is kindly offered
by [Matteo Vaccari](https://github.com/xpmatteo), you can find the original
slides [here](https://www.slideshare.net/pierodibello/il-dilettevole-giuoco-delloca-coding-dojo).

This version is adapted in order to be used during a workshop about Android testing. The UI in Jetpack Compose and the general architecture is already given to
the user to help him focusing only on the testing phase.

## Intro

Goose game is a game where two or more players move pieces around a track by rolling a die. The aim of the game is to reach square number sixty-three before any
of the other players and avoid obstacles. ([wikipedia](https://en.wikipedia.org/wiki/Game_of_the_Goose))

## General requirements

- You should use Android Studio and Kotlin with JetPack Compose to perform this exercise. Otherwise, I strongly suggest to go to the original version of the
  kata :-)
- You should release your work under an OSI-approved open-source license of your choice.
- You should deliver the sources of your application, with a README that explains how to compile and run it.

**IMPORTANT:** Implement the requirements focusing on writing the best code you can produce.

## Features

### 1. Add players

As a player, I want to add me to the game so that I can play.

**Scenarios:**

1. Add Player
   ```cucumber
   Go to AddPlayerScreen.
   
   If there is no participant
   the user writes user's name ("Pippo") and presses "Add Player"
   the system responds: "Player list: Pippo"
   the user writes user's name ("Pluto") and presses "Add Player"
   the system responds: "Player list: Pippo, Pluto"
   ```

2. Duplicated Player
   ```cucumber
   Go to AddPlayerScreen.
   
   If there is already a participant "Pippo"
   the user writes user's name ("Pippo") and presses "Add Player"
   the system responds: "Pippo: already existing player"
   ```

### 2. The game throws the dice

As a player, I want the game throws the dice for me and generate random numbers between 1 to 6

**Scenarios:**

1. Dice roll
   ```cucumber
   If there is one participant "Pippo" on space "4"
   assuming that the dice get 1 and 2
   when the user presses: "Roll Dice"
   the system responds: "Pippo rolls 1, 2."
   when the user presses "Roll Dice" again, it's Pluto's turn
   assuming that the dice get 3 and 4
   the system responds: "Pluto rolls 3, 4"
   It's not required the user to actually move in the board. We want only the UI change in the text.
   ```

### 3. Move a player

As a player, I want to move the marker on the board to make the game progress

**Scenarios:**

1. Start
   ```cucumber
   If there are two participants "Pippo" and "Pluto" on space "Start"
   Pippo rolls the dice and he hits: "4, 2"
   the system responds: "Pippo rolls 4, 2. Pippo moves from Start to 6"
   and Pippo's marker is moved in the board according to the viewModel changes
   Pluto rolls the dice and he hits: "2, 2"
   the system responds: "Pluto rolls 2, 2. Pluto moves from Start to 4"
   and Pippo's marker is moved in the board according to the viewModel changes
   Pippo rolls the dice and he hits "2, 3"
   the system responds: "Pippo rolls 2, 3. Pippo moves from 6 to 11"
   and Pippo's marker is moved in the board according to the viewModel changes
   ```

### 4. Win

As a player, I win the game if I land on space "63"

**Scenarios:**

1. Victory
   ```cucumber
   If there is one participant "Pippo" on space "60"
   Pippo rolls the dice and he hits "1, 2"
   the system responds: "Pippo rolls 1, 2. Pippo moves from 60 to 63. Pippo Wins!!"
   and Pippo's marker is moved in the board according to the viewModel changes
   Optional: the "Pippo Wins!!" text is in a Dialog. You need also to unit test the Dialog opening and appearance
   ```

2. Winning with the exact dice shooting
   ```cucumber
   If there is one participant "Pippo" on space "60"
   Pippo rolls the dice and he hits "3, 2"
   the system responds: "Pippo rolls 3, 2. Pippo moves from 60 to 63. Pippo bounces! Pippo returns to 61"
   and Pippo's marker is moved in the board according to the viewModel changes
   ```

### 5. Space "6" is "The Bridge"

As a player, when I get to the space "The Bridge", I jump to the space "12"

**Scenarios:**

1. Get to "The Bridge"
   ```cucumber
   If there is one participant "Pippo" on space "4"
   assuming that the dice get 1 and 1
   when the presses: "Roll Dice"
   the system responds: "Pippo rolls 1, 1. Pippo moves from 4 to The Bridge. Pippo jumps to 12"
   ```
