# ChangeLog

## [1.8.0]
* Fixed a tricky bug during heads up play when the small blind and the big blind puts a player all-in

## [1.7.0]
* Fixed a tricky bug during heads up play when the small blind puts a player all-in, when that player is the big blind.

## [1.6.1]
* Cleaned up getNextActivePlayer on the Table

## [1.6.0]
* Fixed a bug which allowed a player to raise when it was the only player left for action

## [1.5.0]
* Fixed a bug which prohibited raising by smaller than double the current bet

## [1.4.0]
* Fixed a bug for which allowed a player to fold when it was the only one left in the hand.

## [1.3.0]
* Fixed a bug for determining whether action should occur in rare cases with all-ins

## [1.2.0]
* Fixed a bug where infinite loops were created in rare cases.

## [1.1.0]

* Fixed a bug where the pot was split during an all-in event when it was unnecessary to do so.

## [1.0.1]

* Fixed a bug where the game state would continue in an invalid state.
* Made the jar lowercase.

## [1.0.0]

* Initial Release