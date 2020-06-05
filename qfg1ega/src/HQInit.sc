;;; Sierra Script 1.0 - (do not remove this comment)
(script# HQINIT) ;1
(include game.sh)
(use Main)
(use User)
(use Menu)

(public
	SetGameInit 0
	SetGraphicsSoundInit 1
)

(procedure (SetGameInit &tmp [temp0 21])
	(User alterEgo: ego)
	(User prompt: {} blocks: 0 y: 160)
	(= showStyle HSHUTTER)
	(= debugging TRUE)	;EO: is set to TRUE to enable debug features
	(TheMenuBar init:)
	(= thievesPassword (Random 0 6))
	(= possibleScore 500)
	(= userFont 300)
	(= smallFont 999)
	(= bigFont 300)
	(= stamCounter 20)
	(= healCounter 15)
	(= manaCounter 5)
	(SetGraphicsSoundInit)
	(FixTime 11)
	(DoSound ChangeVolume 15)
	(HandsOn)
	(if (HaveMouse)
		(theGame setCursor: normalCursor TRUE)
	else
		(theGame setCursor: normalCursor TRUE 304 174)
	)
	(Bset fSaveAllowed)
	(Joystick JoyRepeat 0)
	(if (GameIsRestarting)
			(= startingRoom NOTICE2)	;if restarting, go to the Game Select Screen
	else
		(= startingRoom NOTICE)	;otherwise, go to the PIRACY notice screen
	)
	
	(theGame newRoom: SPEED)	;but first, we initialize in the speedCheck room.
)

(procedure (SetGraphicsSoundInit)
	(= numVoices (DoSound NumVoices))
	(if (< (= numColors (Graph GDetect)) 8)
		;CGA
		(= statusBarView vMonoStatusBar)
		(= combatColor vWHITE)
		(= sameColor vBLACK)
		(= changeColor vRED)
	else
		;EGA
		(= statusBarView vStatusBar)
		(= combatColor vLCYAN)
		(= sameColor vLBLUE)
		(= changeColor vLRED)
	)
)
