;;; Sierra Script 1.0 - (do not remove this comment)
(script# MENU)
(include game.sh) (include menu.sh)
(use Main)
(use Intrface)
(use Gauge)
(use Sound)
(use User)

(public
	ToggleSound 1
)

(local
	oldPause
)
(procedure (ToggleSound &tmp i)
	(= i (DoSound SoundOn))
	(DoSound SoundOn (not i))
	(if i
		(SetMenu soundI p_text {Turn On})
	else
		(SetMenu soundI p_text {Turn Off})
	)
)

(class TheMenuBar of MenuBar
	
	(method (init)
		(AddMenu { \01_}
			{About LSL3`^a:Help`#1}
		)
		(AddMenu { File_}
			{Save Game`#5:Restore Game`#7:Auto Save`#4:--! :Restart Game`#9:Quit`^q}
		)
		(AddMenu { Action_}
			{Pause Game`^p:Inventory`^I:Retype`#3:--! :Colors`^c:--! :Boss Key`^b :Expletive`^x}
		)
		(AddMenu { Speed_}
			{Change...`^s:--!:Faster`+:Normal`=:Slower`-}
		)
		(AddMenu { Sound_}
			{Volume...`^v:Turn Off`#2=1}
		)
		(SetMenu
			soundI p_text (if (DoSound SoundOn) {Turn Off} else {Turn On})
		)
		(if (< (Graph GDetect) 9)
			;if CGA, disable the colors option
			(SetMenu colorsI #state FALSE)
		else
			(SetMenu colorsI p_said '/color')
		)
		(SetMenu saveI p_said 'save[/game]')
		(SetMenu restoreI p_said 'restore[/game]')
		(SetMenu restartI p_said 'restart[/game]')
		(SetMenu quitI p_said 'done[/game]')
		(SetMenu pauseI p_said 'delay[/game]')
		(SetMenu invI p_said 'all')
	)
	
	(method (handleEvent event &tmp temp0 i newTextColor newBackColor [str 220])
		(switch (= temp0 (super handleEvent: event))
			(aboutI
				(Print MENU 0
					#font smallFont
					#mode teJustCenter
					#title {An Al Lowe Production}
					#icon 51 0 0
				)
				(Print
					(Format @str MENU 1 version)
					#font smallFont
					#mode teJustCenter
					#title {The Cast of Thousands}
					#at -1 30
					#width 234
				)
				(Format @filthStr MENU 2
					(switch filthLevel
						(4 {Totally Raunchiest})
						(3 {Really Filthy})
						(2 {Pretty Dirty})
						(1 {Rather Risque})
						(else  {Mother Goose})
					)
				)
				(Print
					(cond 
						(gameHours
							(Format @str MENU 3
								@filthStr
								gameHours (if (== gameHours 1) {} else {s})
								gameMinutes (if (== gameMinutes 1) {} else {s})
								gameSeconds (if (== gameSeconds 1) {} else {s})
								score (if (== score 1) {} else {s})
							)
						)
						(score
							(Format @str MENU 4
								@filthStr
								gameMinutes (if (== gameMinutes 1) {} else {s})
								gameSeconds (if (== gameSeconds 1) {} else {s})
								score (if (== score 1) {} else {s})
							)
						)
						(else
							(Format @str MENU 5
								@filthStr
								gameMinutes (if (== gameMinutes 1) {} else {s})
								gameSeconds (if (== gameSeconds 1) {} else {s})
							)
						)
					)
					#font smallFont
					#mode teJustCenter
					#title {Get a Life!}
				)
			)
			(helpI
				(Print MENU 6
					#font smallFont
					#at 10 -1
					#width 290
				)
				(Print MENU 7
					#font smallFont
				)
			)
			(saveI
				(if (Btst fSaveDisabled)
					(Print MENU 8
						#title {Not now, I have a headache!}
					)
				else
					(theGame save:)
					(= autoSaveTimer 0)
					(= secondsBetweenReminders 0)
				)
			)
			(restoreI
				(theGame restore:)
			)
			(autoSaveI
				(if
					(>
						0
						(= minutesBetweenReminders
							(GetNumber {Minutes Between Reminders:} minutesBetweenReminders)
						)
					)
					(= minutesBetweenReminders 0)
				)
			)
			(restartI
				(if
					(Print MENU 9
						#title {Restart}
						#icon 57 0 playingAsPatti
						#font bigFont
						#button {Restart} 1
						#button { Oops_} 0
					)
					(theGame restart:)
				)
			)
			(quitI
				(= quit
					(Print
						(Format @str MENU 10
							(Random (+ score 5) (+ score 50))
						)
						#title {Quit}
						#icon 55 0 playingAsPatti
						#font bigFont
						#button { Quit_} 1
						#button { Oops_} 0
					)
				)
			)
			(pauseI
				(= oldPause (Sound pause: TRUE))
				(Print MENU 11
					#title {This game is paused.}
					#icon 50 0 playingAsPatti
					#font bigFont
					#button {Stop your whining!} 1
				)
				(Sound pause: oldPause)
			)
			(invI
				(if (HaveMem InvSize)
					(inventory showSelf: ego)
				)
			)
			(repeatI
				(event claimed: FALSE type: keyDown message: (User echo?))
			)
			(colorsI
				(= newTextColor 16)
				(while (and (u> newTextColor 15) (!= newTextColor -1))
					(= newTextColor (GetNumber {New Text Color: (0-15)}))
				)
				(if (!= newTextColor -1)
					(= newBackColor 16)
					(while
						(and
							(!= newBackColor -1)
							(or (u> newBackColor vWHITE) (== newBackColor newTextColor))
						)
						(= newBackColor (GetNumber {New Background Color: (0-15)}))
					)
					(if (!= newBackColor -1)
						(= myTextColor newTextColor)
						(= myBackColor newBackColor)
					)
				)
				(systemWindow color: myTextColor back: myBackColor)
			)
			(bossI
				(curRoom newRoom: BOSSKEY)
			)
			(expletiveI
				(GetInput (Format @str expletive) 38
					{Enter your favorite expletive:}
				)
				(if (> (StrLen @str) 4) (Format expletive @str))
			)
			(speedI
				(if (HaveMem GaugeSize)
					(= i
						((Gauge new:)
							description:
								{Use the mouse or the left and right arrow keys to select the speed of moving characters.}
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
				(if (> speed (^ $0001 (= i debugging)))
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
				(if (HaveMem GaugeSize)
					(= i
						((Gauge new:)
							description:
								{Use the mouse or the left and right arrow keys to adjust the volume.}
							text: {Sound Volume}
							normal: 15
							higher: {Louder}
							lower: {Softer}
							doit: (DoSound ChangeVolume i)
						)
					)
					(DoSound ChangeVolume i)
					(DisposeScript GAUGE)
				)
			)
			(soundI
				(ToggleSound)
			)
		)
	)
)
