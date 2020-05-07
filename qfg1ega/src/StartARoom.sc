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
	(while ((= event (Event new:)) type?)
		(event dispose:)
	)
	(event dispose:)
	(if (OneOf roomNum NOTICE NOTICE2 INTRO CHARSEL CHARALLOC SPEED ENDGAME CHARSAVE)
		(TheMenuBar hide: state: FALSE)
	else
		(TheMenuBar draw:)
	)
	(= index 1)
	(while (<= index NUM_INVITEMS)
		(= [invDropped index] 0)
		(++ index)
	)
	(cls)
	(LoadMany FALSE
		DEMO EXTRA DCICON JUMP SMOOPER REVERSE CHASE FOLLOW WANDER RFEATURE WINDOW CAT FILE
		DOORS STRING TALKOBJ KEYCURSOR DEBUG TARGET STATUSBAR
		ARENA SKILLED WARRIOR MONSTER CLOSECOMBAT ENCOUNTER ARENA_MAGIC ARENA_FLAME ARENA_ZAP ARENA_DAZZLE
		ARENA_CALM ARENA_THRUST ARENA_BLOCK ARENA_PARRY ARENA_DODGE vMantrayDefeated ABDULLA RENTAROOM
		vGoblin vSaurus vSaurusRex vMantray vCheetaur vTroll vBrigand GHOSTS CEMETERY MAZEBUG
		232 221 222 223 224 220 216 217 218
	)
	(mouseDownHandler release:)
	(keyDownHandler release:)
	(directionHandler release:)
	(FixTime)
	(if (== roomNum ENDGAME)
		(Bclr fInMainGame)
		(FixTime 12 0)
		(= Night FALSE)
		(= currentPalette 0)
	)
	(if
	(and (not monsterNum) (!= daggerRoom 73))
		(= hitDaggers 0)
	)
)
