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
			{About Card`^a}
		)
		(AddMenu { Action_}
			{Restart Card`#9:Quit`^q}
		)
		(AddMenu { Sound_}
			{Volume`^v:Turn Off`#2=1}
		)
	)
	
	(method (handleEvent event &tmp i [str 250] oldPause temp252)
		(switch (super handleEvent: event)
			(aboutI
				(= oldPause (Sound pause: TRUE))
				(if (< numColors 8)
					(aWin color: vBLACK back: vWHITE)
				)
				(Print (Format @str MENU 0)
					#title { About Christmas Card:_}
					#mode teJustCenter
					#width 180
					#font 999
					#at -1 20
					#window aWin
				)
				(Print (Format @str MENU 1)
					#title { About Christmas Card:_}
					#mode teJustCenter
					#width 180
					#font 999
					#at -1 20
					#window aWin
				)
				(Print (Format @str MENU 2)
					#title { About Christmas Card:_}
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
					(Print MENU 3
						#button {Restart} TRUE
						#button {Continue} FALSE
						#icon vStaticIcons 0 0
					)
					(theGame restart:)
				)
			)
			(quitI
				(= oldPause (Sound pause: TRUE))
				(= quit
					(Print MENU 4
						#button {Quit} TRUE
						#button {Don't Quit} FALSE
						#icon vStaticIcons 0 1
						#mode teJustCenter
					)
				)
				(Sound pause: oldPause)
			)
			(volumeI
				(if
					(!=
						(= i
							(GetNumber {Volume (1 - 16)?} (+ 1 (DoSound MasterVol)))
						)
						-1
					)
					(if (< (-- i) 0) (= i 0))
					(if (> i 15) (= i 15))
					(DoSound MasterVol i)
				)
			)
			(soundI
				(if (GetMenu soundI p_value)
					(DoSound SoundOn FALSE)
					(SetMenu soundI p_value FALSE p_text {Turn On})
				else
					(DoSound SoundOn TRUE)
					(SetMenu soundI p_value TRUE p_text {Turn Off})
				)
			)
		)
	)
)
