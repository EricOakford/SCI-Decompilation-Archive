;;; Sierra Script 1.0 - (do not remove this comment)
(script# MENU)
(include game.sh) (include menu.sh)
(use Main)
(use Intrface)
(use StopWalk)
(use DCIcon)
(use Gauge)
(use Motion)
(use User)
(use System)


(procedure (SetInputText event)
	(if (> argc 1)
		(Format (User inputLineAddr?) &rest)
	)
	(event claimed: FALSE type: keyDown message: (User echo?))
)

(class TheMenuBar of MenuBar
	(properties
		name "MenuBar"
	)
	
	(method (init)
		(AddMenu { \01_} { About KQI`^a: Help`#1_})
		(AddMenu
			{ File_}
			{ Save Game`#5: Restore Game`#7: --!: Restart Game`#9: Quit`^q_}
		)
		(AddMenu
			{ Action_}
			{ Duck`#4: Jump`#6: --!: Pause Game`^p: Inventory`^I: Retype`#3_}
		)
		(AddMenu
			{ Speed_}
			{ Change...`^s: --!: Faster`+: Normal`=: Slower`-_}
		)
		(AddMenu
			{ Sound_}
			(if (DoSound SoundOn)
				{ Volume...`^v: Turn sound Off`#2=1_}
			else
				{ Volume...`^v: Turn sound On`#2=0_}
			)
		)
		(SetMenu helpI p_said 'aid[<!*][/!*]')
		(SetMenu saveI p_said 'save[/game]')
		(SetMenu restoreI p_said 'restore[/game]')
		(SetMenu restartI p_said 'restart[/game]')
		(SetMenu quitI p_said 'quit[/game]')
		(SetMenu pauseI p_said 'pause[/game]')
		(SetMenu duckI p_said 'duck')
		(SetMenu jumpI p_said 'jump[<!*][/!*]')
		(SetMenu invI p_said 'inventory')
		(SetMenu normalI p_said 'normal')
	)
	
	(method (handleEvent event &tmp temp0 i temp2 [str 300])
		(switch (super handleEvent: event (User blocks?))
			(aboutI
				(Print (Format @str MENU 0 version)
					#mode teJustCenter
					#width 180
					#font smallFont
					#at -1 20
				)
				(Print (Format @str MENU 1)
					#mode teJustCenter
					#width 180
					#font smallFont
					#at -1 20
				)
				(Print (Format @str MENU 2)
					#mode teJustCenter
					#width 180
					#font smallFont
					#at -1 20
				)
			)
			(helpI
				(Print MENU 3 #font smallFont)
			)
			(saveI
				(theGame save:)
			)
			(restoreI
				(theGame restore:)
			)
			(restartI
				(if
					(Print MENU 4
						#title {Restart}
						#font SYSFONT
						#icon movingIcon
						#button {Restart} TRUE
						#button {Oops} FALSE
					)
					(theGame restart:)
				)
			)
			(quitI
				(= quit
					(Print MENU 5
						#title {Quit}
						#font SYSFONT
						#icon movingIcon
						#button {Quit} TRUE
						#button {Oops} FALSE
					)
				)
			)
			(duckI
				(cond 
					(egoInWater
						(Print MENU 6)
					)
					((GetMenu duckI p_value)
						((curRoom script?) cue:)
					)
					((curRoom script?)
						(CantDo)
					)
					((Btst fInvisible)
						(Print MENU 7)
					)
					(else
						(curRoom setScript: duckScript)
					)
				)
			)
			(jumpI
				(cond 
					((or (== curRoomNum 12) (== curRoomNum 49))
						(event claimed: FALSE type: saidEvent)
						(Parse {jump} event)
						(curRoom handleEvent: event)
						(if (not (event claimed?))
							(curRoom setScript: jumpEgo)
							(event claimed: TRUE)
						)
					)
					(egoInWater
						(Print MENU 8)
					)
					((curRoom script?)
						(CantDo)
					)
					(else
						(curRoom setScript: jumpEgo)
					)
				)
			)
			(pauseI
				(Print MENU 9
					#title {This game is paused.}
					#font SYSFONT
					#icon movingIcon
					#button {Let's continue.} TRUE
				)
			)
			(invI
				(if (u< (MemoryInfo FreeHeap) InvSize)
					(Print MENU 10)
				else
					(inventory showSelf: ego)
				)
			)
			(repeatI
				(SetInputText event)
			)
			(speedI
				(= i 6)
				(if
				(!= (= i (GetNumber {Speed (1 - 16)?} speed)) -1)
					(theGame setSpeed: (proc0_17 16 i 1))
				)
			)
			(fasterI
				(if (> speed 1) (theGame setSpeed: (-- speed)))
			)
			(normalI
				(theGame setSpeed:
					(switch howFast
						(0 1)
						(1 3)
						(else  5)
					)
				)
			)
			(slowerI
				(if (< speed 16)
					(theGame setSpeed: (++ speed))
				)
			)
			(volumeI
				(= i
					((Gauge new:)
						description:
							{Use the mouse or right and left arrow keys to set the sound volume.}
						text: {Sound Volume}
						minimum: 0
						normal: 12
						maximum: 15
						higher: {Louder}
						lower: {Softer}
						doit: (DoSound ChangeVolume)
					)
				)
				(DoSound ChangeVolume i)
				(DisposeScript GAUGE)
			)
			(soundI
				(if (GetMenu soundI p_value)
					(DoSound SoundOn FALSE)
					(SetMenu soundI p_value FALSE p_text { Turn sound On})
				else
					(DoSound SoundOn TRUE)
					(SetMenu soundI p_value TRUE p_text { Turn sound Off})
				)
			)
		)
	)
)

(instance movingIcon of DCIcon
	(properties
		view 699
		loop 2
	)
	
	(method (init)
		(super init:)
		(self cycleSpeed: (if (> howFast 2) 10 else 6))
	)
)

(instance duckScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(User canControl: FALSE)
				(ego
					view: (if (Btst fLittleEgo) 23 else 16)
					cel: 0
					setCycle: EndLoop
					setMotion: 0
				)
				(SetMenu duckI p_value TRUE p_text { Stand} p_said 'stand')
			)
			(1
				(ego setCycle: BegLoop self)
			)
			(2
				(ego
					view:
						(cond 
							((Btst fInvisible)
								(if (Btst fLittleEgo) 38 else 36)
							)
							((Btst fLittleEgo)
								4
							)
							(else 0)
						)
					setCycle: StopWalk
						(cond 
							((Btst fInvisible)
								(if (Btst fLittleEgo) 39 else 37)
							)
							((Btst fLittleEgo) 7)
							(else 2)
						)
					setMotion: 0
				)
				(User canControl: TRUE)
				(SetMenu duckI p_value FALSE p_text { Duck} p_said 'duck')
				(self dispose:)
			)
		)
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?)
				(return)
			)
			((super handleEvent: event))
			((== (event type?) saidEvent)
				(cond 
					((Said 'stand,(get,take<up)')
						(self changeState: 1)
					)
					((Said 'duck')
						(Print MENU 11)
					)
					((Said 'look,check')
						(event claimed: FALSE)
					)
					((Said 'talk,speak')
						(event claimed: FALSE)
					)
					(else
						(Print MENU 12)
						(event claimed: TRUE)
					)
				)
			)
		)
	)
)

(instance jumpEgo of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if (Btst fWearingRing)
					(if (Btst fInvisible)
						(Print MENU 13)
					else
						(Print MENU 14)
					)
					(Bclr fInvisible)
					(Bclr fWearingRing)
					(SetItemOwner iMagicRing)
				)
				(ego
					view: (if (Btst fLittleEgo) 17 else 15)
					setLoop: 0
					cel: 0
					setCycle: EndLoop self
				)
			)
			(1
				(ego setCycle: BegLoop self)
			)
			(2
				(ego
					view:
						(cond 
							((Btst fInvisible)
								(if (Btst fLittleEgo) 38 else 36))
							((Btst fLittleEgo) 4)
							(else 0)
						)
					setCycle: StopWalk
						(cond 
							((Btst fInvisible)
								(if (Btst fLittleEgo) 39 else 37))
							((Btst fLittleEgo) 7)
							(else 2)
						)
					setMotion: 0
					setLoop: -1
					loop: 2
				)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)
