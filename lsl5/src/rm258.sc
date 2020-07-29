;;; Sierra Script 1.0 - (do not remove this comment)
(script# 258)
(include game.sh)
(use Main)
(use LLRoom)
(use Feature)
(use Motion)
(use Invent)
(use User)
(use Actor)

(public
	rm258 0
)

(local
	cardState
	local1
	skipCP
	exitCount
	[local4 4]
	[local8 4]
	[local12 4]
	i
	[str 10]
	[local27 8] = [9 0 3 9 2 1 9 9]
	[local35 16] = [900 16053 1010 12415 1120 -31513 1240 -29008 130 -1897 220 14065 340 -5944 440 -9748]
	[local51 16] = [920 -21641 1030 -7348 1150 23802 1200 -3371 100 -15834 210 -2231 310 26084 410 -22485]
	[local67 16] = [950 18734 1050 -9228 1140 -25374 1230 -28538 120 -27849 240 31525 330 -2964 430 29519]
	[local83 16] = [930 -7324 1040 -18826 1110 12320 1250 -24920 140 -7872 230 -27867 320 -25448 400 -20441]
)
(procedure (localproc_032a param1 param2 &tmp temp0 temp1 [temp2 10])
	(= temp0 (* (Random 0 7) 2))
	(switch param2
		(1
			(= [local8 param1] [local83 temp0])
			(= [local4 param1] [local83 (+ temp0 1)])
		)
		(2
			(= [local8 param1] [local67 temp0])
			(= [local4 param1] [local67 (+ temp0 1)])
		)
		(4
			(= [local8 param1] [local51 temp0])
			(= [local4 param1] [local51 (+ temp0 1)])
		)
		(5
			(= [local8 param1] [local35 temp0])
			(= [local4 param1] [local35 (+ temp0 1)])
		)
	)
	(if
	(or (< [local8 param1] 900) (>= [local8 param1] 1200))
		(StrCpy @temp2 {PM})
	else
		(StrCpy @temp2 {AM})
	)
	(Format
		@str
		258
		3
		(/ [local8 param1] 100)
		(mod [local8 param1] 100)
		@temp2
	)
)

(instance rm258 of LLRoom
	(properties
		picture 258
	)
	
	(method (init)
		(super init:)
		(cond 
			((Btst fBoardingPassReady)
				(card y: 97)
				(boardPass init:)
				(InFirstPerson 1)
				(HandsOn)
			)
			((and (Btst fFlightAvailable) (not (ego has: iBoardingPass)))
				(InFirstPerson 1)
				(slot init:)
				(dispenser init:)
				(if (not (ego has: iGoldCard)) (slot doVerb: verbDo 7))
			)
			(else
				(HandsOff)
				(Display {Out of boarding passes at this time. Please try again in another city!}
					p_at 144 75
					p_color myDisplayColor
					p_width 110
					p_font smallFont
				)
				(if (not (ego has: iGoldCard))
					(ego get: iGoldCard)
				)
				(StartTimer 15 2 curRoom)
			)
		)
	)
	
	(method (handleEvent event)
		(if local1
			(event claimed: TRUE)
			(card init:)
		else
			(super handleEvent: event)
		)
	)
	
	(method (doVerb theVerb theItem)
		(return
			(switch theVerb
				(verbLook
					(TimePrint 258 0)
				)
				(verbDo
					(if (== theItem iGoldCard)
						(TimePrint 258 1)
					else
						(super doVerb: theVerb theItem &rest)
					)
				)
				(verbWalk
					(if (== ((theIconBar at: ICON_WALK) cursor?) 6)
						(cond 
							((or (Btst fBoardingPassReady) (ego has: iGoldCard))
								(curRoom newRoom: 250)
							)
							((cast contains: card)
								(curRoom drawPic: (curRoom picture?))
								(switch (++ exitCount)
									(1
										(Display {Hey! Take your AeroDork gold card!}
											p_at 144 75
											p_color myDisplayColor
											p_width 110
											p_font smallFont
										)
									)
									(2
										(Display
											{Look, you twit. If you wander off and leave that card here I'll see to it that your luggage goes non-stop to Muncie, Indiana!}
											p_at 144 75
											p_color myDisplayColor
											p_width 110
											p_font smallFont
										)
									)
									(else 
										(Display {Fine. I'm ignoring you.}
											p_at 144 75
											p_color myDisplayColor
											p_width 110
											p_font smallFont
										)
									)
								)
							)
							(else
								(bar1 dispose:)
								(bar2 dispose:)
								(bar3 dispose:)
								(card init:)
							)
						)
					else
						(return FALSE)
					)
				)
				(else 
					(super doVerb: theVerb &rest)
				)
			)
		)
	)
	
	(method (cue)
		(self newRoom: 250)
	)
	
	(method (newRoom)
		(if (cast contains: card)
			(TimePrint 258 2)
		else
			(InFirstPerson 0)
			(super newRoom: &rest)
		)
	)
	
	(method (notify param1 &tmp [temp0 4])
		(switch param1
			(-1 (card init:))
			(-2
				(= skipCP 1)
				(boardPass init:)
			)
			([local4 i]
				(boardPass init:)
			)
			(else 
				(= local1 1)
				(curRoom drawPic: (curRoom picture?))
				(Display
					{You have entered an incorrect destination for this airport at this hour. But we still appreciate your selection of AeroDork Airlines.}
					p_at 144 75
					p_color myDisplayColor
					p_width 110
					p_font smallFont
				)
			)
		)
	)
)

(instance boardPass of Actor
	(properties
		x 164
		y 158
		description {the boarding pass}
		view 258
		loop 1
	)
	
	(method (init)
		(super init:)
		(HandsOff)
		(curRoom drawPic: (curRoom picture?) 100)
		(if skipCP
			(Display
				{Oh, it's you, Mr. Hughes! Of course you may have a boarding pass! Right away, Sir!}
				p_at 144 75
				p_color myDisplayColor
				p_width 110
				p_font smallFont
			)
		else
			(Display {Please take your boarding pass now.}
				p_at 144 75
				p_color myDisplayColor
				p_width 110
				p_font smallFont
			)
		)
		(if (not (Bset fBoardingPassReady))
			(theMusic2 number: 257 setLoop: 0 play:)
			(self setPri: 3 setMotion: MoveTo x 178 self)
		else
			(self posn: 164 178 setCel: 255 setPri: 13)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbLook
				(TimePrint 258 4)
			)
			(verbDo
				(SolvePuzzle 4)
				(ego get: iBoardingPass)
				(card init:)
				(Bclr fBoardingPassReady)
				(self dispose:)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
	
	(method (cue)
		(theMusic2 stop:)
		(if (not cel)
			(self setPri: -1 setCycle: EndLoop self)
		else
			((Inventory at: iBoardingPass) state: [local27 [local12 i]])
			(= seatNum (+ (* (Random 2 10) 100) (Random 65 70)))
			(HandsOn)
		)
	)
)

(instance card of Actor
	(properties
		x 83
		y 107
		description {your AeroDork card}
		view 258
		priority 3
		signal fixPriOn
		moveSpeed 10
	)
	
	(method (init)
		(super init:)
		(if (== y 97)
			(curRoom drawPic: (curRoom picture?) 100)
			(Display {Please take your AeroDork gold card now.}
				p_at 144 75
				p_color myDisplayColor
				p_width 110
				p_font smallFont
			)
			(theMusic2 number: 258 setLoop: 0 play:)
			(self setMotion: MoveTo x 107)
		else
			(StartTimer 10 1 self)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbLook
				(TimePrint 258 5)
			)
			(verbDo
				(HandsOff)
				(curRoom drawPic: (curRoom picture?) 100)
				(Display {Thank you for flying AeroDork!}
					p_at 144 75
					p_color myDisplayColor
					p_width 110
					p_font smallFont
				)
				(ego get: iGoldCard)
				(StartTimer 5 0 curRoom)
				(self dispose:)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
	
	(method (cue &tmp temp0)
		(switch (++ cardState)
			(1
				(theMusic2 number: 258 setLoop: 0 play:)
				(self setMotion: MoveTo x 97 self)
			)
			(2
				(User canInput: TRUE)
				(ego put: 7)
				(= [local12 0] 0)
				(= [local12 1] 0)
				(= [local12 2] 0)
				(Display {Welcome, Cliff Taurus! Where to today?}
					p_at 144 75
					p_color myDisplayColor
					p_width 110
					p_font smallFont
				)
				(= temp0 0)
				(if (not (Btst fAfterNewYork))
					(localproc_032a temp0 5)
					(Display {New York}
						p_at 144 102
						p_color myDisplayColor
						p_width 120
						p_font smallFont
					)
					(Display @str
						p_at 215 102
						p_color myDisplayColor
						p_width 105
						p_font smallFont
					)
					(= [local12 temp0] 5)
					(++ temp0)
				)
				(if (not (Btst fAfterAtlanticCity))
					(localproc_032a temp0 4)
					(Display {Atlantic City}
						p_at 144 (+ 102 (* 10 temp0))
						p_color myDisplayColor
						p_width 120
						p_font smallFont
					)
					(Display @str
						p_at 215 (+ 102 (* 10 temp0))
						p_color myDisplayColor
						p_width 105
						p_font smallFont
					)
					(= [local12 temp0] 4)
					(++ temp0)
				)
				(if (not (Btst fAfterMiami))
					(localproc_032a temp0 2)
					(Display {Miami}
						p_at 144 (+ 102 (* 10 temp0))
						p_color myDisplayColor
						p_width 120
						p_font smallFont
					)
					(Display @str
						p_at 215 (+ 102 (* 10 temp0))
						p_color myDisplayColor
						p_width 105
						p_font smallFont
					)
					(= [local12 temp0] 2)
					(++ temp0)
				)
				(if (not temp0)
					(localproc_032a temp0 1)
					(Display {Los Angeles}
						p_at 144 102
						p_color myDisplayColor
						p_width 120
						p_font smallFont
					)
					(Display @str
						p_at 210 102
						p_color myDisplayColor
						p_width 100
						p_font smallFont
					)
					(= [local12 temp0] 1)
					(++ temp0)
				)
				(if temp0
					(bar1 init:)
					(if (> temp0 1)
						(bar2 init:)
						(if (> temp0 2) (bar3 init:))
					)
				)
				(theIconBar curIcon: (theIconBar at: ICON_DO))
				(theGame setCursor: 2)
				(self dispose:)
			)
		)
	)
)

(instance slot of Feature
	(properties
		x 80
		y 19
		z -100
		nsTop 91
		nsLeft 63
		nsBottom 148
		nsRight 98
		description {the card slot}
		sightAngle 40
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(TimePrint 258 6)
			)
			(verbUse
				(switch theItem
					(iGoldCard
						(User canInput: FALSE)
						(card init:)
					)
					(else 
						(super doVerb: theVerb theItem &rest)
					)
				)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance dispenser of Feature
	(properties
		x 162
		y 178
		nsTop 171
		nsLeft 120
		nsBottom 186
		nsRight 204
		description {the boarding pass slot}
		sightAngle 40
		lookStr {Boarding passes are emitted from this slot.}
	)
)

(instance ATMName of Feature
	(properties
		x 164
		y 27
		nsTop 15
		nsLeft 57
		nsBottom 40
		nsRight 271
		description {the nameplate}
		sightAngle 40
		lookStr {The nameplate says, "AeroDork Airlines."}
	)
)

(instance screen of Feature
	(properties
		x 194
		y 110
		nsTop 63
		nsLeft 130
		nsBottom 157
		nsRight 259
		description {the screen}
		sightAngle 40
		lookStr {The AeroDork ATM has a touch-sensitive screen.}
	)
)

(instance bar1 of Feature
	(properties
		x 174
		y 105
		nsTop 100
		nsLeft 143
		nsBottom 110
		nsRight 205
		description {the destination}
		sightAngle 40
	)
	
	(method (doVerb theVerb theItem &tmp [temp0 80])
		(switch theVerb
			(verbLook
				(Format @temp0 258 7
					(switch [local12 0]
						(1 {Los Angeles})
						(2 {Miami})
						(4 {Atlantic City})
						(5 {New York})
					)
				)
				(TimePrint @temp0)
			)
			(3
				(= i 0)
				(curRoom drawPic: (curRoom picture?) 100)
				(Display 258 8
					p_at 144 75
					p_color myDisplayColor
					p_width 110
					p_font smallFont
				)
				(TimePrint 258 9 80 {A hint from Al Lowe})
				((ScriptID 20 0) init: 0)
				(bar1 dispose:)
				(bar2 dispose:)
				(bar3 dispose:)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance bar2 of Feature
	(properties
		x 174
		y 115
		nsTop 110
		nsLeft 143
		nsBottom 120
		nsRight 205
		description {the destination}
		sightAngle 40
	)
	
	(method (doVerb theVerb theItem param3 &tmp [temp0 80])
		(asm
			lsp      theVerb
			dup     
			ldi      2
			eq?     
			bnt      code_0c35
			pushi    4
			lea      @temp0
			push    
			pushi    258
			pushi    7
			ldi      1
			lsli     local12
			dup     
			eq?     
			bnt      code_0c01
			lofsa    {Los Angeles}
			jmp      code_0c25
code_0c01:
			dup     
			ldi      2
			eq?     
			bnt      code_0c0e
			lofsa    {Miami}
			jmp      code_0c25
code_0c0e:
			dup     
			ldi      4
			eq?     
			bnt      code_0c1b
			lofsa    {Atlantic City}
			jmp      code_0c25
code_0c1b:
			dup     
			ldi      5
			eq?     
			bnt      code_0c25
			lofsa    {New York}
code_0c25:
			toss    
			push    
			callk    Format,  8
			pushi    1
			lea      @temp0
			push    
			callb    TimePrint,  2
			jmp      code_0cb5
code_0c35:
			dup     
			ldi      3
			eq?     
			bnt      code_0ca8
			ldi      1
			sal      i
			pushi    #drawPic
			pushi    2
			pushi    #picture
			pushi    0
			lag      curRoom
			send     4
			push    
			pushi    100
			lag      curRoom
			send     8
			pushi    11
			pushi    258
			pushi    8
			pushi    100
			pushi    144
			pushi    75
			pushi    102
			lsg      myDisplayColor
			pushi    106
			pushi    110
			pushi    105
			lsg      smallFont
			callk    Display,  22
			pushi    4
			pushi    258
			pushi    9
			pushi    80
			lofsa    {A hint from Al Lowe}
			push    
			callb    TimePrint,  8
			pushi    #init
			pushi    1
			pushi    0
			pushi    2
			pushi    20
			pushi    0
			callk    ScriptID,  4
			send     6
			pushi    #dispose
			pushi    0
			lofsa    bar1
			send     4
			pushi    #dispose
			pushi    0
			lofsa    bar2
			send     4
			pushi    #dispose
			pushi    0
			lofsa    bar3
			send     4
			jmp      code_0cb5
code_0ca8:
			pushi    #doVerb
			pushi    2
			lsp      theVerb
			lsp      theItem
			&rest    param3
			super    Feature,  8
code_0cb5:
			toss    
			ret     
		)
	)
)

(instance bar3 of Feature
	(properties
		x 174
		y 125
		nsTop 120
		nsLeft 143
		nsBottom 130
		nsRight 205
		description {the destination}
		sightAngle 40
	)
	
	(method (doVerb theVerb theItem &tmp [temp0 80])
		(switch theVerb
			(verbLook
				(Format @temp0 258 7
					(switch [local12 2]
						(1 {Los Angeles})
						(2 {Miami})
						(4 {Atlantic City})
						(5 {New York})
					)
				)
				(TimePrint @temp0)
			)
			(3
				(= i 2)
				(curRoom drawPic: (curRoom picture?) 100)
				(Display 258 10
					p_at 144 75
					p_color myDisplayColor
					p_width 110
					p_font smallFont
				)
				(TimePrint 258 9 #title {A hint from Al Lowe})
				((ScriptID 20 0) init: 0)
				(bar1 dispose:)
				(bar2 dispose:)
				(bar3 dispose:)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)
