;;; Sierra Script 1.0 - (do not remove this comment)
(script# HQINIT) ;1
(include game.sh)
(use Main)
(use Intrface)
(use User)
(use Menu)

(public
	GameStartRoom 0
	InitGlobals 1
)

(procedure (GameStartRoom &tmp whichSkill [str 20])
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
	(= stamCounter STAM_RATE)
	(= healCounter HEAL_RATE)
	(= manaCounter MANA_RATE)
	
	(InitGlobals)
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

	;EO: added debug code
	(if debugging
		; Preset all ego skills
		(for	((= whichSkill 0))
				(< whichSkill NUMSTATS)
				((++ whichSkill))
			(= [egoStats whichSkill] 80)
		)
	
		( = [egoStats EXPER] 1900)
		( = [egoStats HEALTH]  (MaxHealth))
		( = [egoStats STAMINA] (MaxStamina))
		( = [egoStats MANA]    (MaxMana))
		(Format @userName {Unknown Hero})
		(= str 0)
		(= transferRoom 
			(Print
				"Where to, Hero?"
				#button: {Intro}	1
				#button: {New Hero}	2
				#button: {Continue}	3
				#edit:	@str 5
			)
		)
	
		(= Day 0)
	
		(if str
			(= transferRoom (ReadNumber @str))
		else
			(switch transferRoom
				(1 (= transferRoom INTRO))
				(2 (= transferRoom CHARSEL))			; Choose a Character
				(3 (= transferRoom NOTICE) (theGame restore:))
			)
		)
		;end debug code
	else	
		(if (GameIsRestarting)
			(= transferRoom NOTICE2)	;if restarting, go to the Game Select Screen
		else
			(= transferRoom NOTICE)	;otherwise, go to the PIRACY notice screen
		)
	)
	
	(theGame newRoom: SPEED)	;but first, we initialize in the speedCheck room.
)

(procedure (InitGlobals)
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
