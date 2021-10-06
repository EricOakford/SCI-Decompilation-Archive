;;; Sierra Script 1.0 - (do not remove this comment)
(script# MENU)
(include game.sh) (include menu.sh)
(use Main)
(use Intrface)
(use Sound)
(use User)


(class TheMenuBar of MenuBar
	
	(method (init)
		(AddMenu { \01_}
			{About game`^a :Help`#1 :VaporCalc`^c_}
		)
		(AddMenu { File_}
			{Save Game`#5 :Restore Game`#7 :--! :Restart Game`#9 :Quit`^q_}
		)
		(AddMenu { Action_}
			{Pause Game`^p :Inventory`^I :Retype`#3 :--! :Boss Key`^b_}
		)
		(AddMenu { Speed_}
			{Change...`^s :--! :Faster`+ :Normal`= :Slower`-_}
		)
		(AddMenu { Sound_}
			{Volume...`^v :Sound Off`#2=1_}
		)
		(SetMenu saveI p_said 'save')
		(SetMenu restoreI p_said 'restore')
		(SetMenu restartI p_said 'restart')
		(SetMenu quitI p_said 'quit')
		(SetMenu pauseI p_said 'pause')
		(SetMenu invI p_said 'inventory')
		(SetMenu normalI p_said 'normal')
		(SetMenu fasterI p_said 'faster')
		(SetMenu slowerI p_said 'slower')
		(SetMenu helpI p_said 'aid')
	)
	
	(method (handleEvent event &tmp oldPause i [str 401])
		(switch (super handleEvent: event (User blocks?))
			(aboutI
				(Print
					(Format @str MENU 0 version)
					#font 3
					#mode teJustCenter
					#title {Space Quest \0B}
				)
			)
			(helpI
				(Print MENU 1
					#font 3
				)
			)
			(vaporCalcI
				(if (or (== curRoomNum 900) (== curRoomNum 1))
					(event claimed: FALSE)
				else
					(= vaporCalcCued TRUE)
				)
			)
			(saveI
				(if saveDisabled
					(Print MENU 2)
				else
					(theGame save:)
				)
			)
			(restoreI
				(theGame restore:)
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
				(if (!= curRoomNum 290)
					(= quit
						(Print MENU 4
							#title {Bailing Out?}
							#icon 602 1 0
							#font 300
							#button {Quit} 1
							#button {Oops} 0
						)
					)
				else
					(event claimed: FALSE)
				)
			)
			(pauseI
				(= oldPause (Sound pause: TRUE))
				(Print MENU 5
					#title {This game is paused.}
					#icon 602 1 0
					#font 300
					#button {Ok. I'm back.} 1
				)
				(DoSound PauseSound oldPause)
			)
			(invI
				(inventory showSelf: ego)
			)
			(repeatI
				(event claimed: FALSE type: keyDown message: (User echo?))
			)
			(bossI
				(Print MENU 6)
				(Print
					(Format @str
						{In fact, you don't want your boss to know that
						you've been playing Space Quest ]I[ for %d hours,
						%d minutes and %d seconds.}
						gameHours gameMinutes gameSeconds
					)
				)
				(Print MENU 7)
			)
			(speedI
				(if (!= (= i (GetNumber {Speed (1 - 16)?} speed)) -1)
					(if (< i 1)
						(= i 1)
					)
					(if (> i 16)
						(= i 16)
					)
					(theGame setSpeed: i)
				)
			)
			(fasterI
				(if (> speed 1)
					(theGame setSpeed: (-- speed))
				)
			)
			(normalI
				(theGame setSpeed: 5)
			)
			(slowerI
				(if (< speed 16)
					(theGame setSpeed: (++ speed))
				)
			)
			(volumeI
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
			)
			(soundI
				(if (GetMenu soundI p_value)
					(DoSound SoundOn FALSE)
					(SetMenu soundI p_value FALSE p_text {Turn on})
				else
					(DoSound SoundOn TRUE)
					(SetMenu soundI p_value TRUE p_text {Turn off})
				)
			)
		)
	)
)
