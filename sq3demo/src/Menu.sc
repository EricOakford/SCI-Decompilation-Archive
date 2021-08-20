;;; Sierra Script 1.0 - (do not remove this comment)
(script# MENU)
(include game.sh) (include menu.sh)
(use Main)
(use Intrface)
(use Gauge)


(class TheMenuBar of MenuBar
	
	(method (init)
		(AddMenu { \01_}
			{About Demo`^a :Help`#1 :VaporCalc`^c_}
		)
		(AddMenu { File_}
			{Pause Demo`^p :Restart Demo`#9 :Quit`^q_}
		)
		(AddMenu { Speed_}
			{Change...`^s :--! :Faster`+ :Normal`= :Slower`-_}
		)
		(AddMenu { Sound_}
			{Volume...`^v :Sound Off`#2=1_}
		)
		(SetMenu restartI p_said 'restart')
		(SetMenu quitI p_said 'quit')
		(SetMenu pauseI p_said 'pause')
		(SetMenu normalI p_said 'normal')
		(SetMenu fasterI p_said 'faster')
		(SetMenu slowerI p_said 'slower')
	)
	
	(method (handleEvent event &tmp oldPause i [str 351])
		(switch (super handleEvent: event)
			(aboutI
				(Print
					(Format @str MENU 0 version)
					#font 3
					#mode teJustCenter
					#title {Space Quest I I I}
				)
			)
			(helpI
				(Print MENU 1
					#font 3
				)
			)
			(vaporCalcI
				(= vaporCalcCued TRUE)
			)
			(pauseI
				(Print MENU 2
					#title {This demo is paused.}
					#icon 602 1 0
					#font 300
					#button {Ok. I'm back.} 1
				)
			)
			(restartI
				(if
					(Print MENU 3
						#title {Restart}
						#icon 602 1 0
						#font 300
						#button {Restart} 1
						#button {Oops} 0
					)
					(theGame restart:)
				)
			)
			(quitI
				(= quit
					(Print MENU 4
						#title {Bailing Out?}
						#icon 602 1 0
						#font 300
						#button {Quit} 1
						#button {Oops} 0
					)
				)
			)
			(speedI
				(= i
					((Gauge new:)
						description:
							{Use the mouse or right and left arrow keys to select the speed at which characters move.}
						text: {Animation Speed}
						minimum: 0
						normal: 11
						maximum: 15
						higher: {Faster}
						lower: {Slower}
						doit: (- 16 speed)
					)
				)
				(theGame setSpeed: (- 16 i))
				(DisposeScript GAUGE)
			)
			(fasterI
				(if (> speed (^ $0001 (= i debugging)))
					(theGame setSpeed: (-- speed))
				)
			)
			(normalI
				(theGame setSpeed: 5)
			)
			(slowerI
				(theGame setSpeed: (++ speed))
			)
			(volumeI
				(= oldPause (DoSound PauseSound 1))
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
					(DoSound SoundOn 0)
					(SetMenu soundI p_value FALSE p_text {Turn on})
				else
					(DoSound SoundOn 1)
					(SetMenu soundI p_value TRUE p_text {Turn off})
				)
			)
		)
	)
)
