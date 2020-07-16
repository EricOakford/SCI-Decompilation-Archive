;;; Sierra Script 1.0 - (do not remove this comment)
(script# MENU)
(include game.sh) (include menu.sh)
(use Main)
(use Intrface)
(use Sound)
(use Save)


(class TheMenuBar of MenuBar
	(properties
		name {MenuBar}
	)
	
	(method (init)
		(AddMenu { \01_}
			{About Camelot`^h}
		)
		(AddMenu { Action_}
			{Restart Demo`#9:Quit`^q}
		)
		(AddMenu { Sound_}
			{Volume...`^v:Turn Off`#2=1}
		)
	)
	
	(method (handleEvent event &tmp temp0 i [str 300] oldPause oldWindow)
		(= oldWindow systemWindow)
		(= systemWindow SysWindow)
		(switch (super handleEvent: event)
			(aboutI
				(= oldPause (Sound pause: TRUE))
				(Print
					(Format @str MENU 0)
					#title {__Conquests of Camelot Credits__}
					#mode teJustCenter
					#width 200
					#font smallFont
					#at -1 20
				)
				(Print
					(Format @str MENU 1)
					#title {__Conquests of Camelot Credits__}
					#mode teJustCenter
					#width 200
					#font smallFont
					#at -1 20
				)
				(Sound pause: oldPause)
			)
			(restartI
				(if
					(Print MENU 2
						#button {Restart} 1
						#button {Continue} 0
					)
					(theGame restart:)
				)
			)
			(quitI
				(= oldPause (Sound pause: TRUE))
				(= quit
					(Print MENU 3
						#button {Quit} 1
						#button {Continue} 0
					)
				)
				(Sound pause: oldPause)
			)
			(volumeI
				(= oldPause (Sound pause: 1))
				(if
					(!= (= i (GetNumber {Volume (1 - 16)?} (+ 1 (DoSound ChangeVolume)))) -1)
					(if (< (-- i) 0)
						(= i 0)
					)
					(if (> i 15)
						(= i 15)
					)
					(DoSound ChangeVolume i)
				)
				(Sound pause: oldPause)
			)
			(soundI
				(= oldPause (Sound pause: TRUE))
				(if (GetMenu soundI p_value)
					(DoSound SoundOn 0)
					(SetMenu soundI p_value FALSE p_text {Turn on})
				else
					(DoSound SoundOn 1)
					(SetMenu soundI p_value TRUE p_text {Turn off})
				)
				(Sound pause: oldPause)
			)
		)
	)
)
