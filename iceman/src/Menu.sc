;;; Sierra Script 1.0 - (do not remove this comment)
(script# MENU)
(include game.sh) (include menu.sh)
(use Main)
(use Intrface)
(use Sound)
(use User)


(class TheMenuBar of MenuBar

	(method (init)
		(AddMenu { \01_}
			{ About Iceman `^a: Help`#1_}
		)
		(AddMenu { File_}
			{ Save Game`#5: Restore Game`#7: --!: Restart Game`#9: Quit`^q_}
		)
		(AddMenu { Action_}
			{ Pause Game`^p: Inventory`^I: Repeat`#3_}
		)
		(AddMenu { Speed_}
			{ Set Speed`^s: --!: Faster`+: Normal`=: Slower`-_}
		)
		(AddMenu { Sound_} { Volume...`^v: Turn Off`#2=1_})
		(SetMenu helpI p_said 'help[/!*]')
		(SetMenu saveI p_said 'save[/game]')
		(SetMenu restoreI p_said 'restore[/game]')
		(SetMenu restartI p_said 'restart[/game]')
		(SetMenu quitI p_said 'quit[/game]')
		(SetMenu pauseI p_said 'pause')
		(SetMenu invI p_said '/inventory')
	)
	
	(method (handleEvent event &tmp i [str 100] oldPause)
		(switch (super handleEvent: event)
			(aboutI
				(Print
					(Format @str MENU 0 version)
					#title {__Iceman Credits__}
					#mode teJustCenter
					#width 160
					#font smallFont
				)
				(Print
					(Format @str MENU 1)
					#title {__Iceman Credits__}
					#mode teJustCenter
					#width 160
					#font smallFont
				)
				(Print
					(Format @str MENU 2)
					#title {__Iceman Credits__}
					#mode teJustCenter
					#width 160
					#font smallFont
				)
				(Print
					(Format @str MENU 3)
					#title {__Iceman Credits__}
					#mode teJustCenter
					#width 160
					#font smallFont
				)
				(Print
					(Format @str MENU 4)
					#title {__Iceman Credits__}
					#mode teJustCenter
					#width 160
					#font smallFont
				)
			)
			(helpI
				(Print MENU 5 #font smallFont)
			)
			(saveI
				(theGame save:)
			)
			(restoreI
				(theGame restore:)
			)
			(restartI
				(if
					(Print MENU 6
						#button {Restart} 1
						#button {Continue} 0
						#icon 7 0 0
					)
					(theGame restart:)
				)
			)
			(quitI
				(= quit
					(Print MENU 7
						#button {Quit} 1
						#button {Continue} 0
						#icon 7 0 0
					)
				)
			)
			(pauseI
				(= oldPause (Sound pause: TRUE))
				(Print MENU 8
					#title {Game Paused}
					#button {Resume} 1
					#icon 7 0 0
				)
				(Sound pause: oldPause)
			)
			(invI
				(if (HaveMem 2048)
					(inventory showSelf: ego)
				else
					(Print MENU 9)
				)
			)
			(repeatI
				(event claimed: FALSE type: keyDown message: (User echo?))
			)
			(speedI
				(if
					(!=
						(= i
							(GetNumber (Format @str {Current speed: %d} speed))
						)
						-1
					)
					(if (< i 1) (= i 1))
					(if (> i 16) (= i 16))
					(theGame setSpeed: i)
				)
			)
			(fasterI
				(if (> speed 1) (theGame setSpeed: (-- speed)))
			)
			(normalI (theGame setSpeed: 6))
			(slowerI
				(if (< speed 16) (theGame setSpeed: (++ speed)))
			)
			(volumeI
				(if
					(!=
						(= i
							(GetNumber {Volume (1 - 16)?} (+ 1 (DoSound ChangeVolume)))
						)
						-1
					)
					(if (< (-- i) 0) (= i 0))
					(if (> i 15) (= i 15))
					(DoSound ChangeVolume i)
				)
			)
			(soundI
				(if (GetMenu soundI p_value)
					(DoSound SoundOn FALSE)
					(SetMenu soundI p_value FALSE p_text {Turn on})
				else
					(DoSound SoundOn TRUE)
					(SetMenu soundI p_value TRUE p_text {Turn off})
				)
			)
		)
	)
)
