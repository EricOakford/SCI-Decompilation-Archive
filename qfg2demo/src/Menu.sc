;;; Sierra Script 1.0 - (do not remove this comment)
(script# MENU)
(include game.sh) (include menu.sh)
(use Main)
(use Intrface)
(use Sound)


(class TheMenuBar of MenuBar
	(properties
		name "MenuBar"
	)
	
	(method (init)
		(AddMenu { \01_} {About Trial By Fire`^a})
		(AddMenu { Action_} {Restart Demo`#9:Quit`^q})
		(AddMenu { Sound_} {Volume...`^v:Turn Off`#2=1})
	)
	
	(method (handleEvent event &tmp temp0 [str 150] oldPause i)
		(switch (super handleEvent: event)
			(aboutI
				(= oldPause (Sound pause: TRUE))
				(Print (Format @str MENU 0)
					#title {Quest for Glory 2:__Trial By Fire}
					#mode teJustCenter
					#font smallFont
					#at -1 20
				)
				(Print (Format @str MENU 1)
					#title {And last, but not least...}
					#mode teJustCenter
					#width 180
					#font smallFont
					#at -1 20
				)
				(Sound pause: oldPause)
			)
			(restartI
				(= oldPause (Sound pause: TRUE))
				(if
					(Print MENU 2
						#button {Restart} TRUE
						#button {Continue} FALSE
						#icon vIcons 0 0
					)
					(theGame restart:)
				else
					(Sound pause: oldPause)
				)
			)
			(quitI
				(= oldPause (Sound pause: TRUE))
				(= quit
					(Print MENU 3
						#title {Giving up, huh?}
						#button {Quit} TRUE
						#button {Don't Quit} FALSE
						#icon vIcons 0 1
					)
				)
				(Sound pause: oldPause)
			)
			(volumeI
				(= oldPause (Sound pause: TRUE))
				(= i (+ 1 (DoSound MasterVol)))
				(if
				(= i (GetNumber {Volume (1 - 16)?} i))
					(DoSound MasterVol (- i 1))
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
