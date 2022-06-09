# OOPs

## Object Oriented Programming Shenanigans

A tile-based game with movement system
inspired by [Crypt of the Necrodancer](https://youtu.be/u_avgU1u6yM).

## Run instructions

Simply compile the project with Maven or import it into an IDE and run
the `DesktopLauncher` class.

## Changelog

### As of May 5th 2022, we have implemented the following features:

* the skeleton of the application:
  - window
  - basic launcher classes
  - resource and level loading
  - architecture designed for future functionality creation (interfaces and abstract classes for game entities)
* basic movement system and user input handling
* basic UI widgets
  - a passive HUD for displaying the turn number and the score
* a simple main menu using libGDX's Screen API

### As of May 26th 2022, we have added the following features:

* new entities:
  - rocks which the player cannot enter
  - lakes in which the player drowns
  - intelligent enemies who follow the player and engage in melee
  - projectiles used by player to engage at a safer distance
  - an easter egg :)
* random level generation
* pathfinding for enemy entities
* animation controller system that allows to customize movement and sprite sequences in any animation
* a simple texture manager to be able to reuse sprites


### As of June 09th 2022, we have added the following features:

* new entities:
  - Shooter entitie which shoots and follows player
  - SnakeNest entietie which randomly spawns enemies
* new sprites, animations, sounds and music 
* extended main menu with seed input


### HOW TO PLAY 
Use WASD to move, arrows to shoot and spacebar to wait.



