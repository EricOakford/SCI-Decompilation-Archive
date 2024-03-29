;;; Sierra Script 1.0 - (do not remove this comment)
(script# MENU)
(include game.sh) (include menu.sh)
(use Main)
(use Intrface)
(use DCIcon)
(use Gauge)
(use Sound)
(use User)


(local
	oldPause
)
(class TheMenuBar of MenuBar
	
	(method (init)
		(AddMenu { \01_}
			{About LSL2`^a:Help`#1}
		)
		(AddMenu { File_}
			{Save Game`#5:Restore Game`#7:--! :Restart Game`#9:Quit`^q}
		)
		(AddMenu { Action_}
			{Pause Game`^p:Inventory`^I:Retype`#3:--! :Boss Key`^b:Filth Level`^f:Trite Phrase`^t}
		)
		(AddMenu { Speed_}
			{Change...`^s:--!:Faster`+:Normal`=:Slower`-}
		)
		(AddMenu { Sound_}
			{Volume...`^v:Turn Off`#2=1}
		)
		(SetMenu soundI
			p_text (if (DoSound SoundOn) {Turn Off} else {Turn On})
		)
		(SetMenu saveI p_said 'rescue[/game]')
		(SetMenu restoreI p_said 'restore[/game]')
		(SetMenu restartI p_said 'restart[/game]')
		(SetMenu quitI p_said 'done[/game]')
		(SetMenu pauseI p_said 'pause[/game]')
		(SetMenu invI p_said 'all')
	)
	
	(method (handleEvent event &tmp evt i [str2 100])
		(switch (= evt (super handleEvent: event))
			(aboutI
				(Print MENU 0
					#font smallFont
					#mode teJustCenter
					#title {An Al Lowe Production}
					#icon vAuthors 0 0
				)
				(Print
					(Format @str MENU 1 version)
					#font smallFont
					#mode teJustCenter
					#at -1 20
					#width 234
				)
				(Print
					(if gameHours
						(Format @str MENU 2 gameHours gameMinutes gameSeconds)
					else
						(Format @str MENU 3 gameMinutes gameSeconds)
					)
					#font smallFont
					#mode teJustCenter
				)
			)
			(helpI
				(Print MENU 4
					#font smallFont
					#at 15 -1
					#width 280
				)
				(Print MENU 5
					#font smallFont
				)
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
						#title {Restart}
						#icon vBEDismay 0 0
						#font bigFont
						#button { Oops_} 0
						#button {Restart} 1
					)
					(theGame restart:)
				)
			)
			(quitI
				(= quit
					(Print MENU 7
						#title {Quit}
						#icon vBEChagrin 0 0
						#font bigFont
						#button {Quit} 1
						#button {Oops} 0
					)
				)
			)
			(pauseI
				(= oldPause (Sound pause: TRUE))
				(Print MENU 8
					#title {This game is paused.}
					#icon pauseIcon 0 0
					#font bigFont
					#button {Ok. I'm back.} 1
				)
				(Sound pause: oldPause)
			)
			(invI
				(if (not (HaveMem InvSize))
					(Print MENU 9)
				else
					(inventory showSelf: ego)
				)
			)
			(repeatI
				(event claimed: FALSE type: keyDown message: (User echo?))
			)
			(bossI
				(curRoom newRoom: BOSSKEY)
			)
			(filthI
				(if (< (MemoryInfo FreeHeap) GaugeSize)
					(NotNow)
				else
					(cond 
						(
							(==
								(= filthLevel
									((Gauge new:)
										description:
											{Use the mouse to suit your taste.}
										text: {Filth-O-Meter}
										higher: {Dirtier}
										lower: {Cleaner}
										doit: filthLevel
									)
								)
								0
							)
							(Print MENU 10)
						)
						((<= filthLevel 4) (Print MENU 11))
						((> filthLevel 14) (Print MENU 12))
						(else (Print MENU 13))
					)
					(DisposeScript GAUGE)
				)
			)
			(triteI
				(GetInput
					(Format @str tritePhrase)
					38
					{Enter your favorite trite phrase:}
				)
				(if (> (StrLen @str) 4) (Format tritePhrase @str))
			)
			(speedI
				(if (not (HaveMem GaugeSize))
					(Print MENU 14)
				else
					(= i
						((Gauge new:)
							description:
								{Use the mouse to select the speed of moving characters.}
							text: {Animation Speed}
							normal: 10
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
				(if (> speed (^ 1 (= i debugging)))	;**	This lets Al haul ass!
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
					(Print MENU 15)
				else
					(= i
						((Gauge new:)
							description:
								{Use the mouse to adjust the volume.}
							text: {Sound Volume}
							normal: 15
							higher: {Louder}
							lower: {Softer}
							doit: (DoSound ChangeVolume)
						)
					)
					(DoSound ChangeVolume i)
					(DisposeScript GAUGE)
				)
			)
			(soundI
				(= i (DoSound SoundOn))
				(DoSound SoundOn (not i))
				(if i
					(SetMenu soundI p_text {Turn On})
					(Print MENU 16)
				else
					(SetMenu soundI p_text {Turn Off})
					(Print MENU 17)
				)
			)
			(else 
				(if debugMenu
					(debugMenu doit: evt)
				)
			)
		)
	)
)

(instance pauseIcon of DCIcon
	(properties
		view vEgoPause
		cycleSpeed 5
	)
)
