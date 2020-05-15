;;; Sierra Script 1.0 - (do not remove this comment)
(script# rmHooker) ;150
(include game.sh)
(use Main)
(use Intrface)
(use LLRoom)
(use MoveCyc)
(use PolyPath)
(use Polygon)
(use Feature)
(use ForCount)
(use LoadMany)
(use Sound)
(use Motion)
(use Invent)
(use User)
(use Actor)
(use System)

(public
	rm150 0
)

(local
	oldSpeed
	hookerCycling
	[egoScrewCycle 25] = [2 0 100 141 2 1 100 141 2 2 100 141 2 3 100 125 2 4 104 96 2 5 98 94 -32768]
)
(procedure (RoomFeatures)
	(table approachVerbs: verbDo verbUse verbZipper verbTaste verbLook init:)
	(light init:)
	(underwear approachVerbs: verbDo verbUse verbZipper verbTaste init:)
	(bed approachVerbs: verbDo verbUse verbZipper verbTaste verbLook init:)
	(clothesline init: approachVerbs: verbDo verbUse verbZipper verbTaste)
	(theSign init:)
	(windowsill init:)
	(herBreast dispose:)
	(herFace dispose:)
	(herCrack dispose:)
	(bedpost dispose:)
)

(procedure (CloseUpFeatures)
	(herBreast init:)
	(herFace init:)
	(herCrack init:)
	(bedpost init:)
	(table dispose:)
	(light dispose:)
	(underwear dispose:)
	(bed dispose:)
	(clothesline dispose:)
	(theSign dispose:)
	(windowsill dispose:)
)

(instance rm150 of LLRoom
	(properties
		picture rmHooker
	)
	
	(method (init)
		(LoadMany VIEW 150 151 152 803)
		(Load PICTURE 155)
		(LoadMany SOUND 150 151 155 148)
		(if
			(or
				(!= (theMusic number?) 148)
				(== (theMusic prevSignal?) -1)
			)
			(theMusic number: 148 loop: -1 vol: 127 flags: mNOPAUSE play:)
		)
		(soundFx loop: 1 vol: 127 flags: mNOPAUSE)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: PBarredAccess
					init:
						144 104 114 104 86 121 149 121 134 139 65 142 65 148
						241 148 237 139 218 139 218 132 228 132 212 115 125 115
						125 109 149 109 149 -1 319 0 319 189 0 189 0 0 144 0
					yourself:
				)
		)
		(switch prevRoomNum
			(140
				(HandsOff)
				(ego init:)
				(self setScript: sFromStairs)
			)
			(160
				(HandsOff)
				(curRoom setScript: sFromWindow)
			)
			(else 
				(ego init: posn: 170 160)
			)
		)
		(ego setPri: -1 actions: rm150EgoActions)
		(theEgoHead actions: rm150EgoActions)
		(super init:)
		(theHooker
			cycleSpeed: howFast
			init:
			approachVerbs: verbDo verbUse verbZipper verbTaste verbTalk verbLook
		)
		(if (Btst 35)
			(theHooker z: 6 setCel: 0 setLoop: 7 setScript: sSmoke)
		else
			(theHooker stopUpd:)
			(hookerHead
				cycleSpeed: howFast
				init:
				approachVerbs: verbDo verbUse verbZipper verbTaste verbTalk
			)
		)
		(if (== ((inventory at: 9) owner?) 150)
			(candy init: approachVerbs: verbDo verbUse verbZipper verbTaste)
		)
		(if (Btst fHookerWindowOpen) (aWindow cel: 2))
		(aWindow
			cycleSpeed: howFast
			approachVerbs: verbDo verbUse verbZipper verbTaste
			init:
		)
		(RoomFeatures)
	)
	
	(method (doit)
		(super doit: &rest)
		(if (and (ego mover?) (== (curRoom curPic?) 155))
			(ego setMotion: 0)
			(sCloseup state: 6 cue:)
		)
		(cond 
			(script)
			((IsObjectOnControl ego cYELLOW) (HandsOff) (self setScript: sDownstairs))
		)
	)
	
	(method (doVerb theVerb theItem)
		(if (!= rmHooker (curRoom curPic?))
			(switch theVerb
				(verbLook
					(Print 150 0)
					(Print 150 1)
					(Print 150 2 #at -1 140)
				)
				(verbDo (Print 150 3))
				(verbTalk (Print 150 4) (Print 150 5))
				(verbZipper (Print 150 6))
				(verbTaste (Print 150 7))
				(else 
					(super doVerb: theVerb theItem)
				)
			)
		else
			(switch theVerb
				(verbLook (Print 150 8))
				(verbTaste (Print 150 7))
				(verbUse
					(switch theItem
						(iRibbon (Print 150 9))
						(else 
							(super doVerb: theVerb theItem)
						)
					)
				)
				(else 
					(super doVerb: theVerb theItem)
				)
			)
		)
	)
)

(instance rm150EgoActions of Code	;EO: This was a class, but it is not listed in the class table
	(properties)
	
	(method (doVerb theVerb theItem)
		(asm
			lsp      theVerb
			dup     
			ldi      2
			eq?     
			bnt      code_0467
			pushi    #view
			pushi    0
			lag      ego
			send     4
			push    
			ldi      803
			eq?     
			bnt      code_044b
			pushi    #view
			pushi    0
			lag      ego
			send     4
			push    
			ldi      803
			eq?     
			bnt      code_043a
			pushi    #loop
			pushi    0
			lag      ego
			send     4
			push    
			ldi      0
			eq?     
code_043a:
			not     
			bnt      code_044b
			pushi    2
			pushi    150
			pushi    10
			calle    Print,  4
			jmp      code_061f
code_044b:
			pushi    #view
			pushi    0
			lag      ego
			send     4
			push    
			ldi      803
			eq?     
			bnt      code_061f
			pushi    2
			pushi    150
			pushi    11
			calle    Print,  4
			jmp      code_061f
code_0467:
			dup     
			ldi      3
			eq?     
			bnt      code_0503
			pushi    #view
			pushi    0
			lag      ego
			send     4
			push    
			ldi      803
			eq?     
			bnt      code_04ad
			pushi    #view
			pushi    0
			lag      ego
			send     4
			push    
			ldi      803
			eq?     
			bnt      code_0497
			pushi    #loop
			pushi    0
			lag      ego
			send     4
			push    
			ldi      0
			eq?     
code_0497:
			not     
			bnt      code_04ad
			pushi    #setScript
			pushi    1
			lofsa    sGetDressed
			push    
			lag      curRoom
			send     6
			ldi      1
			ret     
			jmp      code_061f
code_04ad:
			pushi    1
			pushi    43
			callb    Btst,  2
			bnt      code_04db
			pushi    1
			pushi    43
			callb    Bclr,  2
			pushi    #put
			pushi    2
			pushi    8
			pushi    0
			lag      ego
			send     8
			pushi    2
			pushi    87
			pushi    1
			callb    SolvePuzzle,  4
			pushi    2
			pushi    150
			pushi    12
			calle    Print,  4
			jmp      code_061f
code_04db:
			pushi    1
			pushi    35
			callb    Btst,  2
			bnt      code_04f1
			pushi    2
			pushi    150
			pushi    13
			calle    Print,  4
			jmp      code_061f
code_04f1:
			pushi    #setScript
			pushi    1
			lofsa    sGetUndressed
			push    
			lag      curRoom
			send     6
			ldi      1
			ret     
			jmp      code_061f
code_0503:
			dup     
			ldi      10
			eq?     
			bnt      code_0571
			pushi    #view
			pushi    0
			lag      ego
			send     4
			push    
			ldi      803
			eq?     
			bnt      code_0549
			pushi    #view
			pushi    0
			lag      ego
			send     4
			push    
			ldi      803
			eq?     
			bnt      code_0533
			pushi    #loop
			pushi    0
			lag      ego
			send     4
			push    
			ldi      0
			eq?     
code_0533:
			not     
			bnt      code_0549
			pushi    #setScript
			pushi    1
			lofsa    sScrew
			push    
			lag      curRoom
			send     6
			ldi      1
			ret     
			jmp      code_061f
code_0549:
			pushi    1
			pushi    35
			callb    Btst,  2
			bnt      code_055f
			pushi    2
			pushi    150
			pushi    13
			calle    Print,  4
			jmp      code_061f
code_055f:
			pushi    #setScript
			pushi    1
			lofsa    sGetUndressed
			push    
			lag      curRoom
			send     6
			ldi      1
			ret     
			jmp      code_061f
code_0571:
			dup     
			ldi      4
			eq?     
			bnt      code_061f
			lsp      theItem
			dup     
			ldi      8
			eq?     
			bnt      code_061e
			pushi    1
			pushi    43
			callb    Btst,  2
			bnt      code_0597
			pushi    2
			pushi    150
			pushi    14
			calle    Print,  4
			jmp      code_061e
code_0597:
			pushi    1
			pushi    35
			callb    Btst,  2
			bnt      code_05ad
			pushi    2
			pushi    150
			pushi    15
			calle    Print,  4
			jmp      code_061e
code_05ad:
			pushi    #view
			pushi    0
			lag      ego
			send     4
			push    
			ldi      803
			eq?     
			bnt      code_05d7
			pushi    #loop
			pushi    0
			lag      ego
			send     4
			push    
			ldi      0
			eq?     
			bnt      code_05d7
			pushi    2
			pushi    150
			pushi    16
			calle    Print,  4
			jmp      code_061e
code_05d7:
			pushi    #view
			pushi    0
			lag      ego
			send     4
			push    
			ldi      803
			eq?     
			not     
			bnt      code_05f4
			pushi    2
			pushi    150
			pushi    17
			calle    Print,  4
			jmp      code_061e
code_05f4:
			pushi    2
			pushi    150
			pushi    18
			calle    Print,  4
			pushi    1
			pushi    43
			callb    Bset,  2
			pushi    #state
			pushi    1
			pushi    1
			pushi    #at
			pushi    1
			pushi    8
			class    Inventory
			send     6
			send     6
			pushi    2
			pushi    96
			pushi    10
			callb    SolvePuzzle,  4
			ldi      1
			ret     
code_061e:
			toss    
code_061f:
			toss    
			ret     
		)
	)
)

(instance sGetUndressed of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if
				(and (== (ego view?) 803) (== (ego loop?) 0))
					(++ state)
					(= cycles 1)
				else
					(ego setMotion: PolyPath 100 141 self)
				)
			)
			(1
				(ego egoSpeed: setHeading: 0 self)
			)
			(2
				(ego
					view: 803
					setLoop: (if
					(and (== (ego view?) 803) (== (ego loop?) 0))
						1
					else
						0
					)
					setCel: 0
					cycleSpeed: howFast
					setCycle: EndLoop self
				)
			)
			(3
				(HandsOn)
				(User canControl: FALSE)
				(theIconBar disable: ICON_WALK)
				(self dispose:)
			)
		)
	)
)

(instance sGetDressed of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego cycleSpeed: (* 2 howFast) setCycle: BegLoop self)
			)
			(1
				(ego setLoop: 0 setCel: 255 setCycle: BegLoop self)
			)
			(2 (= cycles 60))
			(3
				(Print 150 19)
				(HandsOn)
				(NormalEgo 3)
				(self dispose:)
			)
		)
	)
)

(instance sFromStairs of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego posn: 141 111 setMotion: MoveTo 124 106 self)
			)
			(1
				(ego setMotion: MoveTo 118 112 self)
			)
			(2 (ego setHeading: 180 self))
			(3 (HandsOn) (self dispose:))
		)
	)
)

(instance sDownstairs of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: MoveTo 145 111 self)
			)
			(1 (curRoom newRoom: 140))
		)
	)
)

(instance sCloseup of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(eyesProp setScript: sBlink)
				(herMouth setCycle: Forward)
				(= seconds (Random 3 6))
			)
			(1
				(herMouth setCycle: EndLoop)
				(= seconds (Random 2 4))
			)
			(2
				(herMouth setCycle: Forward)
				(= cycles (Random 3 6))
			)
			(3
				(herMouth setCycle: EndLoop)
				(= seconds 2)
			)
			(4
				(herMouth setCycle: Forward)
				(= cycles (Random 3 6))
			)
			(5
				(if (Btst fMouthSmellsBad) (Print 150 20) else (Print 150 21))
				(= seconds 2)
			)
			(6
				(herMouth setCycle: EndLoop self)
			)
			(7 (HandsOff) (= cycles 1))
			(8
				(eyesProp z: 1000 setScript: 0 dispose:)
				(herMouth z: 1000 dispose:)
				(RoomFeatures)
				(curRoom drawPic: rmHooker)
				(cast eachElementDo: #show)
				((ScriptID 0 25) loop: 0 cursor: 100)
				(Animate (cast elements?) FALSE)
				(NormalEgo)
				(EgoHeadMove)
				(= cycles 1)
			)
			(9 (HandsOn) (self dispose:))
		)
	)
)

(instance sBlink of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles (Random 25 250)))
			(1
				(eyesProp setCycle: BegLoop self)
			)
			(2 (self init:))
		)
	)
)

(instance sScrew of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(theMusic number: 150 play:)
				(ego
					setLoop: 2
					setCel: 0
					setPri: (+ (theHooker priority?) 1)
					setCycle: MoveCycle @egoScrewCycle self
				)
			)
			(1
				(= oldSpeed speed)
				(= speed 0)
				(theHooker z: 1000 hide:)
				(ego z: 1000 setPri: -1)
				(hookerHead dispose:)
				(censor init: setCycle: Forward viewer: humpCycler)
				(= seconds (Random 7 13))
			)
			(2
				(censor setLoop: 1)
				(= seconds (Random 4 7))
			)
			(3
				(censor setLoop: 0)
				(= seconds (Random 7 13))
			)
			(4
				(censor viewer: 0 setCycle: Forward cycleSpeed: 0)
				(= seconds 3)
			)
			(5
				(censor setCycle: EndLoop cycleSpeed: 3)
				(= seconds 3)
			)
			(6
				(censor setCycle: CycleTo 4 1 cycleSpeed: 3)
				(= seconds 3)
			)
			(7
				(= speed oldSpeed)
				(censor z: 1000 dispose:)
				(theHooker z: 6 show: setCel: 0 setLoop: 7)
				(ego
					egoSpeed:
					x: 100
					y: 141
					z: 0
					cycleSpeed: 4
					setCel: 255
					setCycle: BegLoop self
				)
			)
			(8
				(theMusic number: 148 play:)
				(ego setLoop: 1 setCel: 255 setCycle: BegLoop)
				(= seconds 3)
			)
			(9
				(ego setLoop: 0 setCel: 255 setCycle: BegLoop)
				(= seconds 3)
			)
			(10
				(Bset fScoredHooker)
				(SolvePuzzle fHookerPoints 11)
				(NormalEgo 3)
				(theHooker setScript: sSmoke)
				(= cycles 60)
			)
			(11
				(Print 150 22)
				(if (Btst fWearingLubber) (Print 150 23) else (Bset fDiedOfTheClap))
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance sExitWindow of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego
					egoSpeed:
					x: 241
					y: 124
					view: 151
					setLoop: 3
					setCel: 0
					cycleSpeed: egoSpeed
					setCycle: EndLoop self
				)
			)
			(1
				(theMusic fade:)
				(ego userSpeed:)
				(curRoom newRoom: rmAlley)
			)
		)
	)
)

(instance sFromWindow of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(fakeEgo cycleSpeed: egoSpeed init: setCycle: BegLoop self)
			)
			(1
				(fakeEgo z: 1000 dispose:)
				(ego init: x: 228 y: 132 loop: 0 setHeading: 270)
				(EgoHeadMove)
				(= cycles 10)
			)
			(2
				(NormalEgo 1)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance sGetCandy of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego
					egoSpeed:
					view: 808
					loop: 0
					cel: 0
					setCycle: EndLoop self
				)
			)
			(1
				(candy dispose:)
				(SolvePuzzle fGotCandy 2)
				(ego get: iCandy setCycle: BegLoop self)
			)
			(2
				(NormalEgo 0)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance sOpenWindow of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego
					egoSpeed:
					normal: 0
					view: 151
					setLoop: 2
					setCel: 0
					setCycle: EndLoop self
				)
			)
			(1
				(soundFx number: 151 play:)
				(Bset fHookerWindowOpen)
				(aWindow setCycle: EndLoop)
				(ego setCycle: BegLoop self)
			)
			(2
				(NormalEgo 0)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance sSmoke of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds (Random 3 10)))
			(1 (client setCycle: EndLoop self))
			(2 (self init:))
		)
	)
)

(instance hookerHead of Prop
	(properties
		z 14
		approachX 100
		approachY 141
		view 152
		loop 5
		priority 9
		signal (| ignrAct fixPriOn)
	)
	
	(method (init)
		(self
			x: (theHooker x?)
			y: (theHooker y?)
			cycleSpeed: 6
			setCycle: ForwardCounter 6 self
		)
		(self cue:)
		(super init:)
	)
	
	(method (doit)
		(super doit: &rest)
		(cond 
			((and (< (ego x?) 126) (> (ego y?) 136)) (if (> loop 3) (self setLoop: (- loop 3))))
			((< loop 4) (self setLoop: (+ loop 3)))
		)
		(cond 
			(
				(and
					(== cel 7)
					(!= (curRoom curPic?) 155)
					(not hookerCycling)
				)
				(= hookerCycling TRUE)
				(gumSound number: 155 play:)
			)
			((!= cel 7) (= hookerCycling FALSE))
		)
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbZipper (Print 150 6))
			(else 
				(theHooker doVerb: theVerb theItem)
			)
		)
	)
	
	(method (cue)
		(super cue:)
		(if (or (== loop 2) (== loop 5))
			(self
				setLoop: (+ (self loop?) 1)
				setCel: 0
				setCycle: EndLoop self
			)
		else
			(self
				setLoop: (if (> (self loop?) 3) 5 else 2)
				setCycle: ForwardCounter (Random 5 15) self
			)
		)
	)
)

(instance theHooker of Person
	(properties
		x 69
		y 112
		z 6
		description {the hooker}
		approachX 100
		approachY 141
		view 152
		priority 9
		signal (| ignrAct fixPriOn)
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(cond 
					((Btst fScoredHooker) (Print 150 24))
					((== (ego view?) 803) (Print 150 25))
					(else
						(ego setMotion: 0)
						(cast eachElementDo: #hide)
						(herMouth cycleSpeed: egoSpeed init: z: 0)
						(eyesProp cycleSpeed: (+ howFast 1) init: z: 11)
						(CloseUpFeatures)
						(curRoom drawPic: 155 setScript: sCloseup)
						((ScriptID 0 25) loop: 15 cursor: 106)
						(Animate (cast elements?) 0)
					)
				)
			)
			(verbDo
				(cond 
					(
					(and (== (ego view?) 803) (== (ego loop?) 0)) (Print 150 16))
					((not (== (ego view?) 803)) (Print 150 17))
					(else (curRoom setScript: sScrew))
				)
			)
			(verbTalk
				(if (Btst fScoredHooker)
					(Print 150 26)
					(Print 150 27)
				else
					(Print 150 28)
				)
			)
			(verbTaste
				(Print 150 29)
				(Print 150 30 #at -1 140)
			)
			(verbZipper
				(cond 
					(
					(and (== (ego view?) 803) (== (ego loop?) 0)) (Print 150 16))
					((not (== (ego view?) 803)) (Print 150 17))
					(else (curRoom setScript: sScrew))
				)
			)
			(verbUse
				(switch theItem
					(iWallet (Print 150 31))
					(iWatch (Print 150 32))
					(iRing (Print 150 33))
					(iRemoteControl (Print 150 34))
					(iLubber (Print 150 35))
					(iCandy (Print 150 36))
					(iDiscoPass (Print 150 37))
					(iWine (Print 150 38))	;EO: Not possible to have the wine here legitimately
					(iMagazine (Print 150 39))
					(iPills (Print 150 40))
					(iRibbon (Print 150 41))
					(iPocketKnife (Print 150 42))
					(iGraffiti (Print 150 43))
					(else  (Print 150 44))
				)
			)
			(else 
				(super doVerb: theVerb theItem)
			)
		)
	)
)

(instance candy of View
	(properties
		x 239
		y 124
		description {the box of candy}
		approachX 228
		approachY 132
		view 150
		loop 1
		priority 9
		signal fixPriOn
	)
)

(instance aWindow of Prop
	(properties
		x 250
		y 110
		z 20
		description {the window}
		approachX 228
		approachY 132
		lookStr {There is a fire escape outside the window.}
		view 150
		cel 1
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(cond 
					((== (ego view?) 803) (Print 150 45))
					((Btst fHookerWindowOpen) (HandsOff) (curRoom setScript: sExitWindow))
					(else (HandsOff) (curRoom setScript: sOpenWindow))
				)
			)
			(else 
				(super doVerb: theVerb theItem)
			)
		)
	)
)

(instance eyesProp of Prop
	(properties
		x 147
		y 75
		z 11
		description {her eyes}
		view 155
		signal (| ignrAct ignrHrz)
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook (Print 150 46))
			(else 
				(herFace doVerb: theVerb theItem)
			)
		)
	)
)

(instance herMouth of Prop
	(properties
		x 146
		y 83
		description {her mouth}
		view 155
		loop 1
		cel 2
		signal (| ignrAct ignrHrz)
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook (Print 150 47))
			(else 
				(herFace doVerb: theVerb theItem)
			)
		)
	)
)

(instance censor of Actor
	(properties
		x 89
		y 115
		view 151
		priority 9
		signal (| ignrAct fixPriOn)
	)
)

(instance fakeEgo of Prop
	(properties
		x 241
		y 124
		view 151
		loop 3
		cel 6
	)
)

(instance table of Feature
	(properties
		x 239
		y 126
		nsTop 116
		nsLeft 229
		nsBottom 136
		nsRight 249
		description {the table}
		sightAngle 40
		approachX 228
		approachY 132
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(if (not (CheckItemOwner iCandy))
					(Print 150 48)
				else
					(Print 150 49)
				)
			)
			(verbDo
				(cond 
					((not (CheckItemOwner iCandy)) (Print 150 50))
					((== (ego view?) 803) (Print 150 45))
					(else (HandsOff) (curRoom setScript: sGetCandy))
				)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance light of Feature
	(properties
		x 176
		y 131
		z 68
		nsTop 55
		nsLeft 160
		nsBottom 72
		nsRight 192
		description {the light}
		sightAngle 40
		lookStr {The lamp casts a yellow pallor over the hooker's parlor.}
	)
)

(instance underwear of Feature
	(properties
		x 207
		y 160
		z 48
		nsTop 98
		nsLeft 195
		nsBottom 127
		nsRight 220
		description {her underwear}
		sightAngle 40
		approachX 207
		approachY 148
		lookStr {The clothesline is barely strong enough to hold its present load. Poor girl, you'd think at these prices she could afford a laundromat.}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo (Print 150 51))
			(verbTaste (Print 150 52))
			(verbUse
				(switch theItem
					(iPocketKnife (clothesline doVerb: verbDo))
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

(instance bed of Feature
	(properties
		x 97
		y 110
		nsTop 106
		nsLeft 57
		nsBottom 137
		nsRight 137
		description {the bed}
		sightAngle 40
		approachX 100
		approachY 141
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbTaste (Print 150 53))
			(else 
				(theHooker doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance clothesline of Feature
	(properties
		x 207
		y 155
		z 53
		description {the clothesline}
		sightAngle 40
		onMeCheck $1000
		approachX 207
		approachY 148
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(Print 150 54)
				(Print 150 55 #at -1 140)
			)
			(verbUse
				(switch theItem
					(iPocketKnife (Print 150 56))
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

(instance theSign of Feature
	(properties
		x 40
		y 79
		nsTop 68
		nsLeft 27
		nsBottom 91
		nsRight 53
		description {the small sign over the hooker's bed}
		sightAngle 40
		approachX 40
		approachY 79
		lookStr {The sign over the hooker's bed reads, "Substantial penalty for early withdrawal!"}
	)
)

(instance windowsill of Feature
	(properties
		x 157
		y 170
		nsTop 152
		nsLeft 11
		nsBottom 189
		nsRight 303
		description {the window sill}
		sightAngle 40
		lookStr {Someone has yet to kick the habit!}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo (Print 150 57))
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance herBreast of Feature
	(properties
		x 156
		y 158
		nsTop 139
		nsLeft 105
		nsBottom 177
		nsRight 207
		description {her breast}
		sightAngle 40
		lookStr {"Is that all you came up here to do, Honey? LOOK!?"}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbZipper (Print 150 58))
			(verbTaste (Print 150 59))
			(verbDo (Print 150 60))
			(verbTalk (Print 150 61))
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance herFace of Feature
	(properties
		x 146
		y 74
		nsTop 49
		nsLeft 122
		nsBottom 99
		nsRight 171
		description {her face}
		sightAngle 40
		lookStr {"You think I'm pretty, doncha Honey?" gums the hooker.}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo (Print 150 62))
			(verbTalk (Print 150 4) (Print 150 5))
			(verbZipper (Print 150 63))
			(verbTaste (Print 150 64))
			(verbUse
				(theHooker doVerb: theVerb theItem &rest)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance herCrack of Feature
	(properties
		x 65
		y 20
		nsLeft 29
		nsBottom 41
		nsRight 102
		description {the crack}
		sightAngle 40
		lookStr {Is that why you came up here? To look at a crack?}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo (Print 150 65))
			(verbTalk (Print 150 66))
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance bedpost of Feature
	(properties
		x 301
		y 82
		nsLeft 283
		nsBottom 165
		nsRight 319
		description {the bedpost}
		sightAngle 40
		lookStr {That's where she leaves her chewing gum overnight.}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo (Print 150 67))
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance gumSound of Sound
	(properties
		flags mNOPAUSE
		prevSignal -1
	)
)

(instance humpCycler of Code
	(properties)
	
	(method (doit)
		(if (not (Random 0 30))
			(censor cycleSpeed: (Random 0 5))
		)
	)
)
