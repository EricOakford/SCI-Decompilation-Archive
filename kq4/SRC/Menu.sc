;;; Sierra Script 1.0 - (do not remove this comment)
(script# MENU)
(include game.sh) (include menu.sh)
(use Main)
(use Intrface)
(use Gauge)
(use Sound)
(use Invent)
(use User)


(class TheMenuBar of MenuBar
	
	(method (init)
		(AddMenu { \01_}
			{About KQ4`^a:Help`#1}
		)
		(AddMenu { File_}
			{Save`#5:Restore`#7:-!:Restart`#9:Quit`^q}
		)
		(AddMenu { Action_}
			{Pause`^p:Inventory`^i:Retype`#3}
		)
		(AddMenu { Speed_}
			{Speed`^s:-!:Faster`+:Normal`=:Slower`-}
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
	
	(method (handleEvent event &tmp evt i [temp2 4] oldPause [str2 288])
		(switch (= evt (super handleEvent: event))
			(aboutI
				(= oldPause (Sound pause: TRUE))
				(Print
					(Format @str2 MENU 0 version)
					#title {A Ken Williams Production}
					#font smallFont
					#mode teJustCenter
					#at 20 10
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
				(if (not (HaveMem SaveSize))
					(Print MENU 2)
				else
					(theGame save:)
				)
			)
			(restoreI
				(if (not (HaveMem SaveSize))
					(Print MENU 3)
				else
					(theGame restore:)
				)
			)
			(restartI
				(= oldPause (Sound pause: TRUE))
				(if
					(Print MENU 4
						#icon 100 0 0
						#font SYSFONT
						#button { Restart_} TRUE
						#button {Continue} FALSE
					)
					(theGame restart:)
				)
				(Sound pause: oldPause)
			)
			(quitI
				(= oldPause (Sound pause: TRUE))
				(= quit
					(Print MENU 5
						#icon 100 0 0
						#font SYSFONT
						#button {____Quit____} TRUE
						#button { Continue_} FALSE
					)
				)
				(Sound pause: oldPause)
			)
			(pauseI
				(= oldPause (Sound pause: TRUE))
				(Print MENU 6
					#font SYSFONT
					#mode teJustCenter
					#button { Continue_} 0
				)
				(Sound pause: oldPause)
			)
			(invI
				(if (not (HaveMem InvSize))
					(Print MENU 7)
				else
					(= oldPause (Sound pause: TRUE))
					(Inventory showSelf: ego)
					(Sound pause: oldPause)
				)
			)
			(repeatI
				(event claimed: FALSE type: keyDown message: (User echo?))
			)
			(speedI
				(if (not (HaveMem GaugeSize))
					(Print MENU 8)
				else
					(= i
						((Gauge new:)
							description:
								{Use the mouse or the left and right arrow keys to change the speed of moving characters.}
							text: {Animation Speed}
							minimum: 0
							normal: 10
							maximum: 15
							higher: {Faster}
							lower: {Slower}
							doit: (- 16 speed)
						)
					)
					(theGame setSpeed: (- 16 i))
					(DisposeScript GAUGE)
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
				(theGame setSpeed: (++ speed))
			)
			(volumeI
				(if (not (HaveMem GaugeSize))
					(Print MENU 9)
				else
					(= oldPause (DoSound PauseSound 1))
					(= i
						((Gauge new:)
							description:
								{Use the mouse or left and right arrow keys to change the volume.}
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
			)
			(soundI
				(if (= i (DoSound SoundOn))
					(SetMenu soundI p_text {Turn On})
				else
					(SetMenu soundI p_text {Turn Off})
				)
				(DoSound SoundOn (not i))
			)
			(else 
				(if debugMenu
					(debugMenu doit: evt)
				)
			)
		)
	)
)
