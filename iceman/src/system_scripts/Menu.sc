;;; Sierra Script 1.0 - (do not remove this comment)
(script# 997)
(include sci.sh)
(use Main)
(use Intrface)
(use Sound)
(use User)


(class TheMenuBar of MenuBar
	(properties
		state $0000
	)
	
	(method (init)
		(AddMenu { \01_} { About Iceman `^a: Help`#1_})
		(AddMenu
			{ File_}
			{ Save Game`#5: Restore Game`#7: --!: Restart Game`#9: Quit`^q_}
		)
		(AddMenu
			{ Action_}
			{ Pause Game`^p: Inventory`^I: Repeat`#3_}
		)
		(AddMenu
			{ Speed_}
			{ Set Speed`^s: --!: Faster`+: Normal`=: Slower`-_}
		)
		(AddMenu { Sound_} { Volume...`^v: Turn Off`#2=1_})
		(SetMenu 258 109 'help[/!*]')
		(SetMenu 513 109 'save[/game]')
		(SetMenu 514 109 'restore[/game]')
		(SetMenu 516 109 'restart[/game]')
		(SetMenu 517 109 'quit[/game]')
		(SetMenu 769 109 'pause')
		(SetMenu 770 109 '/inventory')
	)
	
	(method (handleEvent pEvent &tmp temp0 [temp1 100] temp101)
		(switch (super handleEvent: pEvent)
			(257
				(Print
					(Format @temp1 997 0 version)
					#title
					{__Iceman Credits__}
					#mode
					1
					#width
					160
					#font
					smallFont
				)
				(Print
					(Format @temp1 997 1)
					#title
					{__Iceman Credits__}
					#mode
					1
					#width
					160
					#font
					smallFont
				)
				(Print
					(Format @temp1 997 2)
					#title
					{__Iceman Credits__}
					#mode
					1
					#width
					160
					#font
					smallFont
				)
				(Print
					(Format @temp1 997 3)
					#title
					{__Iceman Credits__}
					#mode
					1
					#width
					160
					#font
					smallFont
				)
				(Print
					(Format @temp1 997 4)
					#title
					{__Iceman Credits__}
					#mode
					1
					#width
					160
					#font
					smallFont
				)
			)
			(258
				(Print 997 5 #font smallFont)
			)
			(513 (theGame save:))
			(514 (theGame restore:))
			(516
				(if
					(Print
						997
						6
						#button
						{Restart}
						1
						#button
						{Continue}
						0
						#icon
						7
						0
						0
					)
					(theGame restart:)
				)
			)
			(517
				(= quit
					(Print
						997
						7
						#button
						{Quit}
						1
						#button
						{Continue}
						0
						#icon
						7
						0
						0
					)
				)
			)
			(769
				(= temp101 (Sound pause: 1))
				(Print
					997
					8
					#title
					{Game Paused}
					#button
					{Resume}
					1
					#icon
					7
					0
					0
				)
				(Sound pause: temp101)
			)
			(770
				(if (IsHeapFree 2048)
					(inventory showSelf: ego)
				else
					(Print 997 9)
				)
			)
			(771
				(pEvent claimed: 0 type: 4 message: (User echo?))
			)
			(1025
				(if
					(!=
						(= temp0
							(GetNumber (Format @temp1 {Current speed: %d} speed))
						)
						-1
					)
					(if (< temp0 1) (= temp0 1))
					(if (> temp0 16) (= temp0 16))
					(theGame setSpeed: temp0)
				)
			)
			(1027
				(if (> speed 1) (theGame setSpeed: (-- speed)))
			)
			(1028 (theGame setSpeed: 6))
			(1029
				(if (< speed 16) (theGame setSpeed: (++ speed)))
			)
			(1281
				(if
					(!=
						(= temp0
							(GetNumber {Volume (1 - 16)?} (+ 1 (DoSound sndVOLUME)))
						)
						-1
					)
					(if (< (-- temp0) 0) (= temp0 0))
					(if (> temp0 15) (= temp0 15))
					(DoSound sndVOLUME temp0)
				)
			)
			(1282
				(if (GetMenu 1282 113)
					(DoSound sndSET_SOUND 0)
					(SetMenu 1282 113 0 110 {Turn on})
				else
					(DoSound sndSET_SOUND 1)
					(SetMenu 1282 113 1 110 {Turn off})
				)
			)
		)
	)
)
