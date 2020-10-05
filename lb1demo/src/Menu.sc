;;; Sierra Script 1.0 - (do not remove this comment)
(script# MENU)
(include game.sh) (include menu.sh)
(use Main)
(use Intrface)
(use Sound)
(use Invent)
(use User)


(class TheMenuBar of MenuBar
	
	(method (init)
		(= menuIsOn TRUE)
		(AddMenu { \01_}
			{About Bequest`^a:Help`#1}
		)
		(AddMenu { File_}
			{Save`#5:Restore`#7:-!:Restart`#9:Quit`^q}
		)
		(AddMenu { Action_}
			{Pause`^p:Inventory`^i:Retype`#3}
		)
		(AddMenu { Speed_}
			{Faster`+:Normal`=:Slower`-}
		)
		(AddMenu { Sound_}
			{Volume`^v:-!:Turn Off=1`#2}
		)
		(SetMenu soundI
			p_text (if (DoSound SoundOn) {Turn Off} else {Turn On})
		)
		(SetMenu saveI p_said 'save[/game]')
		(SetMenu restoreI p_said 'restore[/game]')
		(SetMenu restartI p_said 'restart[/game]')
		(SetMenu quitI p_said 'quit[/game]')
		(SetMenu pauseI p_said 'pause[/game]')
		(SetMenu invI p_said 'inventory')
	)
	
	(method (handleEvent event &tmp menuItem i [temp2 4] oldPause [str 288])
		(switch (= menuItem (super handleEvent: event))
			(aboutI
				(= oldPause (Sound pause: TRUE))
				(Print (Format @str MENU 0 version)
					#title {A Sierra Production}
					#font smallFont
					#mode teJustCenter
					#at 20 22
					#width 260
				)
				(Sound pause: oldPause)
			)
			(helpI
				(= oldPause (Sound pause: TRUE))
				(Print MENU 1 #font smallFont)
				(Sound pause: oldPause)
			)
			(saveI
				(theGame save:)
			)
			(restoreI
				(theGame restore:)
			)
			(restartI
				(= oldPause (Sound pause: TRUE))
				(if
					(Print MENU 2
						#font SYSFONT
						#button { Restart_} 1
						#button {Continue} 0
					)
					(theGame restart:)
				)
				(Sound pause: oldPause)
			)
			(quitI
				(= oldPause (Sound pause: TRUE))
				(= quit
					(Print MENU 3
						#font SYSFONT
						#button {____Quit____} 1
						#button { Continue_} 0
					)
				)
				(Sound pause: oldPause)
			)
			(pauseI
				(= oldPause (Sound pause: TRUE))
				(Print MENU 4
					#font SYSFONT
					#mode teJustCenter
					#button { Continue_} 0
				)
				(Sound pause: oldPause)
			)
			(invI
				(if (not (HaveMem InvSize))
					(Print MENU 5)
				else
					(= oldPause (Sound pause: TRUE))
					(Inventory showSelf: ego)
					(Sound pause: oldPause)
				)
			)
			(repeatI
				(event claimed: FALSE type: keyDown message: (User echo?))
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
				(theGame setSpeed: (++ speed))
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
				(if (= i (DoSound SoundOn))
					(SetMenu soundI p_text {Turn On})
				else
					(SetMenu soundI p_text {Turn Off})
				)
				(DoSound SoundOn (not i))
			)
		)
	)
)
