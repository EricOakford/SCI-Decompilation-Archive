;;; Sierra Script 1.0 - (do not remove this comment)
(script# MENU)
(include game.sh) (include menu.sh)
(use Main)
(use Intrface)
(use Gauge)


(class TheMenuBar of MenuBar
	
	(method (init)
		(AddMenu { \01_}
			{About CARD`^a}
		)
		(AddMenu { Game_}
			{Quit`@q}
		)
		(AddMenu { Sound_}
			{Volume`^v:-!:turn sound off=1`#2}
		)
		(self draw:)
	)
	
	(method (handleEvent event &tmp i oldPause)
		(switch (super handleEvent: event)
			(aboutI
				(= oldPause (DoSound PauseSound TRUE))
				(Print MENU 0
					#mode teJustCenter
					#icon 6 2 0
					#font 3
				)
				(DoSound PauseSound oldPause)
			)
			(quitI
				(= oldPause (DoSound PauseSound TRUE))
				(switch
					(Print MENU 1
						#button {__Quit__} 1
						#button {Continue} 2
						#mode teJustCenter
						#icon 6 2 0
					)
					(1
						(= quit TRUE)
					)
					(2
						(DoSound PauseSound oldPause)
						(return)
					)
				)
				(DoSound PauseSound oldPause)
			)
			(volumeI
				(= oldPause (DoSound PauseSound TRUE))
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
				(DoSound PauseSound oldPause)
				(DoSound ChangeVolume i)
				(DisposeScript GAUGE)
			)
			(soundI
				(if (GetMenu soundI p_value)
					(SetMenu soundI p_value FALSE p_text {Turn sound on})
				else
					(SetMenu soundI p_value TRUE p_text {Turn sound off})
				)
				(DoSound SoundOn (GetMenu soundI p_value))
			)
		)
	)
)
