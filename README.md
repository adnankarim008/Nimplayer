# Nimplayer
This project is the first in a series of three, with the ultimate objective of designing and implementing a
simple variant of the game of Nim in Java. Nim is a two-player game, and the rules of the version used
here are as follows:
============================================================================
PART 1 The Basic Game
============================================================================
• The game begins with a number of objects (e.g., stones placed on a table).
• Each player takes turns removing stones from the table.
• On each turn, a player must remove at least one stone. In addition, there is an upper bound on the
number of stones that can be removed in a single turn. For example, if this upper bound is 3, a
player has the choice of removing 1, 2 or 3 stones on each turn.
• The game ends when there are no more stones remaining. The player who removes the last stone,
loses. The remaining player wins.
• Both the initial number of stones, and the upper bound on the number that can be removed, can be
varied from game to game, and must be chosen before a game commences.

Example
• 12 stones on the table.
• Player 1 removes 3 stones. 9 stones remain.
• Player 2 removes 1 stone. 8 stones remain.
• Player 1 removes 1 stone. 7 stones remain.
• Player 2 removes 2 stones. 5 stones remain.
• Player 1 removes 3 stones. 2 stones remain.
• Player 2 removes 1 stone. 1 stone remains.
• Player 1 removes 1 stone. 0 stones remain.
• Player 2 wins.

============================================================================
PART 2.1 : Sort the players with more specific rules
============================================================================

1. rank all users (default, i.e., descending order)
$rankings
0% | 00 games | Darth Vader
0% | 00 games | Han Solo
0% | 00 games | Luke Skywalker
$
2. rank all users in descending order
$rankings desc
0% | 00 games | Darth Vader
0% | 00 games | Han Solo
0% | 00 games | Luke Skywalker
$
3. rank all users in ascending order
$rankings asc
0% | 00 games | Darth Vader
0% | 00 games | Han Solo
0% | 00 games | Luke Skywalker

============================================================================
PART 2.2 : Invalid input handling via Exceptions
============================================================================
1.
$createplayer lskywalker,Skywalker,Luke
‘createplayer’ is not a valid command.
$

2.
$addplayer lskywalker
Incorrect number of arguments supplied to command.
$

3.
7 stones left: * * * * * * *
Han’s turn - remove how many?
4
Invalid move. You must remove between 1 and 3 stones.
7 stones left: * * * * * * *
Han’s turn - remove how many?

============================================================================
PART 2.2 : The AI (Artificial Intelligence) player
============================================================================
$addaiplayer artoo,D2,R2
$

$startgame 10,3,lskywalker,artoo
Initial stone count: 10
c The University of Melbourne 2020 4
Maximum stone removal: 3
Player 1: Luke Skywalker
Player 2: R2 D2
10 stones left: * * * * * * * * * *
Luke’s turn - remove how many?
3
7 stones left: * * * * * * *
R2’s turn - remove how many?
5 stones left: * * * * *
Luke’s turn - remove how many?
3
2 stones left: * *
R2’s turn - remove how many?
1 stones left: *
Luke’s turn - remove how many?
1
Game Over
R2 D2 wins!
$
