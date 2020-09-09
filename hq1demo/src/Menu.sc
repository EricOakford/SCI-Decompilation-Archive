;;; Sierra Script 1.0 - (do not remove this comment)
(script# MENU)
(include game.sh) (include menu.sh)
(use Main)
(use Intrface)
(use Sound)
(use Save)


(instance aWin of SysWindow
	(properties
		back vLCYAN
	)
)

(class TheMenuBar of MenuBar
	(properties
		name "MenuBar"
	)
	
	(method (init)
		(AddMenu { \01_}
			{About HQ`^a}
		)
		(AddMenu { Action_}
			{Restart Demo`#9:Quit`^q}
		)
		(AddMenu { Sound_}
			{Volume...`^v:Turn Off`#2=1}
		)
	)
	
	(method (handleEvent event &tmp i [str 150] oldPause)
		(switch (super handleEvent: event)
			(aboutI
				(= oldPause (Sound pause: TRUE))
				(if (< numColors 8) (aWin color: vBLACK back: vWHITE))
				(Print
					(Format @str MENU 0)
					#title {__Hero's Quest I Credits__}
					#mode teJustCenter
					#width 160
					#font 999
					#at -1 20
					#window aWin
				)
				(Print
					(Format @str MENU 1)
					#title {And last, but not least...}
					#mode teJustCenter
					#width 180
					#font 999
					#at -1 20
					#window aWin
				)
				(Sound pause: oldPause)
			)
			(restartI
				(if
					(Print MENU 2
						#button {Restart} 1
						#button {Continue} 0
						#icon vIcons 1 3
					)
					(theGame restart:)
				)
			)
			(quitI
				(= oldPause (Sound pause: TRUE))
				(= quit
					(Print MENU 3
						#title {Giving up, huh?}
						#button {Quit} 1
						#button {Don't Quit} 0
						#icon vIcons 1 4
					)
				)
				(Sound pause: oldPause)
			)
			(volumeI
				(= oldPause (Sound pause: TRUE))
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
				(Sound pause: oldPause)
			)
			(soundI
				(= oldPause (Sound pause: TRUE))
				(if (GetMenu soundI p_value)
					(DoSound SoundOn FALSE)
					(SetMenu soundI p_value FALSE p_text {Turn on})
				else
					(DoSound SoundOn TRUE)
					(SetMenu soundI p_value TRUE p_text {Turn off})
				)
				(Sound pause: oldPause)
			)
		)
	)
)
