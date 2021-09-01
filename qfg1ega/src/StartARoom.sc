;;; Sierra Script 1.0 - (do not remove this comment)
(script# STARTAROOM) ;896
(include game.sh)
(use Main)
(use LoadMany)
(use Menu)
(use System)

(public
	StartARoom 0
)

(procedure (StartARoom roomNum &tmp index obj event)
	(theGame setCursor: waitCursor TRUE)
	
	;get rid of all events
	(while ((= event (Event new:)) type?)
		(event dispose:)
	)
	(event dispose:)
	
	;don't draw menu in these rooms
	(if (OneOf roomNum NOTICE NOTICE2 INTRO CHARSEL CHARALLOC SPEED ENDGAME CHARSAVE)
		(TheMenuBar hide: state: FALSE)
	else
		(TheMenuBar draw:)
	)
	
	;clean up dropped inventory
	(for ((= index 1)) (<= index NUM_INVITEMS) ((++ index))
		(= [invDropped index] 0)
	)
	(cls)
	;dispose scripts that are only occasionally used
	(LoadMany FALSE
		DEMO EXTRA DCICON JUMP SMOOPER REVERSE CHASE FOLLOW WANDER RFEATURE WINDOW CAT FILE
		DOOR STRING TALKOBJ KEYCURSOR DEBUG TARGET STATUSBAR
		ARENA SKILLED WARRIOR MONSTER CLOSECOMBAT ENCOUNTER ARENA_MAGIC ARENA_FLAME ARENA_ZAP ARENA_DAZZLE
		ARENA_CALM ARENA_THRUST ARENA_BLOCK ARENA_PARRY ARENA_DODGE vMantrayDefeated ABDULLA RENTAROOM
		vGoblin vSaurus vDragon vMantray vCheetaur vTroll vBrigand GHOSTS CEMETERY MAZEBUG
		232 221 222 223 224 220 216 217 218
	)
	
	;release nodes to prevent fragmentation
	(mouseDownHandler release:)
	(keyDownHandler release:)
	(directionHandler release:)
	
	;make sure it's the correct time
	(FixTime)

	(if (== roomNum ENDGAME)
		(Bclr fInMainGame)
		(FixTime 12 0)
		(= Night FALSE)
		(= currentPalette 0)
	)
	(if (and (not monsterNum) (!= daggerRoom 73))
		(= hitDaggers 0)
	)
)
