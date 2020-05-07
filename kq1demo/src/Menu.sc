;;; Sierra Script 1.0 - (do not remove this comment)
(script# MENU)
(include game.sh) (include menu.sh)
(use Main)
(use Intrface)
(use DCIcon)
(use Gauge)
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
		(AddMenu { \01_} {About KQ1`^a})
		(AddMenu { Action_} {Restart Demo`#9:Quit`^q})
		(AddMenu { Sound_} {Volume...`^v:Turn Off`#2=1})
	)
	
	(method (handleEvent event &tmp i [str 250] oldPause nextRoom)
		(switch (super handleEvent: event)
			(aboutI
				(= oldPause (Sound pause: TRUE))
				(if (< numColors 8) (aWin color: vBLACK back: vWHITE))
				(Print (Format @str MENU 0)
					#title { About KQ1 Demo:_}
					#mode teJustCenter
					#width 160
					#font 999
					#at -1 20
					#window aWin
				)
				(Print
					(Format @str MENU 1)
					#title { About KQ1 Demo:_}
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
						#button {Restart} TRUE
						#button {Continue} FALSE
						#icon movingIcon
					)
					(theGame restart:)
				)
			)
			(quitI
				(= oldPause (Sound pause: TRUE))
				(= quit
					(Print MENU 3
						#button {Quit} TRUE
						#button {Don't Quit} FALSE
						#icon movingIcon 30 1
					)
				)
				(Sound pause: oldPause)
			)
			(volumeI
				(= i
					((Gauge new:)
						description:
							{Use the mouse or right and left arrow keys to set the sound volume.}
						text: {Sound Volume}
						minimum: 0
						normal: 7
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
					(SetMenu soundI p_value FALSE p_text {Turn On})
				else
					(DoSound SoundOn 1)
					(SetMenu soundI p_value TRUE p_text {Turn Off})
				)
			)
			(areaI
				(if
					(= nextRoom
						(Print MENU 4
							#button {Begin} BEGIN
							#button {Dragon} DRAGON
							#button {Bird} BIRD
							#button {Giant} GIANT
							#button {Leps.} LEPRECHAUNS
						)
					)
					(= startingRoom nextRoom)
					(curRoom newRoom: SPEED)
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
		(self cycleSpeed: (if (>  howFast 2) 10 else 6))
	)
)
