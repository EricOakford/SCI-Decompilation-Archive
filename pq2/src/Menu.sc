;;; Sierra Script 1.0 - (do not remove this comment)
(script# MENU)
(include game.sh) (include menu.sh)
(use Main)
(use rm13)
(use rm33)
(use Intrface)
(use Sound)
(use User)


(class TheMenuBar of MenuBar
	(properties
		name "MenuBar"
	)
	
	(method (init)
		(AddMenu { \01_}
			{About PQ2 `^a:Help`#1}
		)
		(AddMenu { File_}
			{Save Game`#5:Restore Game`#7:--! :Restart Game`#9:Quit`^q}
		)
		(AddMenu { Action_}
			{Pause Game`^p:Inventory`^I:Repeat`#3:--! :Load Gun`#6:Draw/Holster Gun`#8:Fire Gun`#0:--! :Enter/Exit Car`#4}
		)
		(AddMenu { Speed_}
			{Set Speed`^s:--!:Faster`+:Normal`=:Slower`-}
		)
		(AddMenu { Sound_}
			{Volume...`^v:Turn Off`#2=1}
		)
		(SetMenu saveI p_said 'save[/game]')
		(SetMenu restoreI p_said 'restore[/game]')
		(SetMenu restartI p_said 'restart[/game]')
		(SetMenu quitI p_said 'quit[/game]')
		(SetMenu pauseI p_said 'pause[/game]')
		(SetMenu invI p_said 'inventory')
		(SetMenu normalI p_said 'normal[/speed]')
		(SetMenu loadGunI p_said 'load,load[/9mm]')
		(SetMenu drawGunI p_said 'draw,gunbelt[/9mm]')
		(SetMenu fireGunI p_said 'fire[/9mm]')
		(= global105 carI)
	)
	
	(method (handleEvent event &tmp [temp0 3] i [str 320] oldPause)
		(switch (super handleEvent: event)
			(aboutI
				(Print
					(Format @str 997 0 version)
					#title {__Police Quest II Credits__}
					#mode teJustCenter
					#width 160
					#font smallFont
					#icon 999 2 0
				)
				(Print
					(Format @str 997 1)
					#title {And last, but not least...}
					#mode teJustCenter
					#width 180
					#font smallFont
					#icon 999 2 0
				)
			)
			(helpI
				(Print 997 2 #font smallFont)
			)
			(saveI
				(if (HaveMem SaveSize)
					(Load SCRIPT SAVE)
					(Load SCRIPT DSELECTOR)
					(theGame save:)
				else
					(Print 997 3)
				)
			)
			(restoreI
				(if (HaveMem SaveSize)
					(Load SCRIPT SAVE) ;990
					(Load SCRIPT DSELECTOR)
					(theGame restore:)
				else
					(Print 997 3)
				)
			)
			(restartI
				(if
					(Print 997 4
						#button {Restart} 1
						#button {Continue} 0
						#icon 999 0 0
					)
					(theGame restart:)
				)
			)
			(quitI
				(= quit
					(Print 997 5
						#button {Quit} 1
						#button {Continue} 0
						#icon 999 0 0
					)
				)
			)
			(pauseI
				(= oldPause (Sound pause: 1))
				(Print 997 6
					#title {Game Paused}
					#icon 999 1 0
					#button {Let's roll.} 1
				)
				(Sound pause: oldPause)
			)
			(invI
				(inventory
					carrying: {You are carrying...}
					empty:
						{You have only your diving equipment.\n
						You left everything else in the van.}
					showSelf: ego
				)
			)
			(repeatI
				(event
					claimed: FALSE
					type: keyDown
					message: (User echo?)
				)
			)
			(loadGunI
				(event
					claimed: FALSE
					type: keyDown
					message: `#6
				)
			)
			(drawGunI
				(event
					claimed: FALSE
					type: keyDown
					message: `#8
				)
			)
			(fireGunI
				(event
					claimed: FALSE
					type: keyDown
					message: `#a	;F10
				)
			)
			(carI
				(cond 
					((== curRoomNum carWork)
						(ExitWorkCar)
					)
					((== curRoomNum carPersonal)
						(ExitPersonalCar)
					)
					((not isHandsOff)
						(= global132 1)
					)
				)
			)
			(speedI
				(if (!= (= i (GetNumber {Speed (1 - 16)?} speed)) -1)
					(if (< i 1)
						(= i 1)
					)
					(if (> i 16)
						(= i 16)
					)
					(theGame setSpeed: i)
				)
			)
			(fasterI
				(if (> speed 1)
					(theGame setSpeed: (-- speed))
				)
			)
			(normalI
				(theGame setSpeed: 6)
			)
			(slowerI
				(if (< speed 20)
					(theGame setSpeed: (++ speed))
				)
			)
			(volumeI
				(if
					(!=
						(= i
							(GetNumber {Volume (1 - 16)?} (+ 1 (DoSound ChangeVolume)))
						)
						-1
					)
					(if (< (-- i) 0)
						(= i 0)
					)
					(if (> i 15)
						(= i 15)
					)
					(DoSound ChangeVolume i)
				)
			)
			(soundI
				(if (GetMenu soundI p_state)
					(DoSound SoundOn FALSE)
					(SetMenu soundI
						p_state FALSE
						p_text {Turn on}
					)
				else
					(DoSound SoundOn TRUE)
					(SetMenu soundI
						p_state TRUE
						p_text {Turn off}
					)
				)
			)
		)
	)
)
