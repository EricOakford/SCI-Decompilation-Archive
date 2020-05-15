;;; Sierra Script 1.0 - (do not remove this comment)
(script# rmHoneymoonSuite) ;390
(include game.sh)
(use Main)
(use Intrface)
(use LLRoom)
(use RandCyc)
(use MoveCyc)
(use PolyPath)
(use Polygon)
(use Feature)
(use LoadMany)
(use Jump)
(use Motion)
(use User)
(use Actor)
(use System)

(public
	rm390 0
)

(local
	wearingLubber
	pouredWine
	didForeplay
	knifeTimer
	radioTimer
	winePrice
	[egoTiedCycles 33] = [3 0 45 92 3 1 45 92 3 2 50 85 3 3 53 83 3 4 59 74 3 5 70 79 3 6 76 81 3 7 98 99 -32768]
)
(instance rm390 of LLRoom
	(properties
		picture rmHoneymoonSuite
	)
	
	(method (init &tmp temp0)
		(= temp0 JUMP)
		(if debugging (Bset fFawnInRoom) (ego get: iPocketKnife))
		(if (> debugging 1) (Bset fOrderedWine))
		(if (> debugging 2) (= pouredWine TRUE))
		(curRoom
			addObstacle:
				((Polygon new:)
					type: PBarredAccess
					init:
						235 96 85 96 85 109 157 109 157 128 137 138 57 156
						38 177 70 182 210 182 210 171 221 162 254 154 240 136
						214 114 241 114 248 95 248 0 319 0 319 189 0 189 0 0
						235 0
					yourself:
				)
		)
		(LoadMany VIEW 390 391 392 808)
		(LoadMany SOUND 390 395 140 801 802)
		(theMusic loop: -1 vol: 127 flags: mNOPAUSE)
		(globalSound number: 395 loop: 1 vol: 127 flags: mNOPAUSE)
		(soundFx setLoop: 1 flags: mNOPAUSE)
		(ego
			x: 241
			y: 88
			init:
			illegalBits: 0
			setHeading: 180
			actions: egoActions
		)
		(theEgoHead actions: egoActions)
		(HandsOff)
		(curRoom setScript: sEnter)
		(super init:)
		(door init: cycleSpeed: howFast approachVerbs: verbDo verbUse verbZipper verbTaste)
		(fawn
			init:
			cycleSpeed: howFast
			moveSpeed: howFast
			approachVerbs: verbLook verbDo verbUse verbZipper verbTaste verbTalk
		)
		(ribbon init: approachVerbs: verbDo verbUse verbZipper verbTaste verbLook)
		(if (Btst fRadioOn)
			(theMusic number: 390 play: 127)
			(if (not (Btst fOrderedWine))
				(= radioTimer
					(Random (* (+ 1 howFast) 200) (* (+ 1 howFast) 300))
				)
			)
		)
		(if (Btst fOrderedWine)
			(Load VIEW 393 803)
			(LoadMany SOUND 171 393 394 396 167 111)
			(wine init: approachVerbs: verbLook verbDo verbUse verbZipper verbTaste)
		)
		(theBed init:)
		(theWindow init:)
		(theRadio init: approachVerbs: verbDo verbUse verbZipper verbTaste)
		(painting init:)
		(iceBucket init: approachVerbs: verbLook verbDo verbUse verbZipper verbTaste)
		(theChair init:)
		(painting2 init:)
		(sculpture init:)
		(flower init: approachVerbs: verbDo verbUse verbZipper verbTaste verbLook)
	)
	
	(method (doit)
		(super doit: &rest)
		(cond 
			(script)
			(knifeTimer
				(if (== (-- knifeTimer) 1)
					(Print 390 0)
					(ShowDeathIcon 391 7)
					(Format @str1 390 1)
					(EgoDead 390 2)
				)
			)
			(radioTimer
				(if (> radioTimer 1) (-- radioTimer))
				(if (and (== radioTimer 1) (not (curRoom script?)))
					(= radioTimer
						(Random (* (+ howFast 1) 600) (* (+ howFast 2) 600))
					)
					(HandsOff)
					(curRoom setScript: sDoCommercial)
				)
			)
		)
	)
	
	(method (dispose)
		(DisposeScript JUMP)
		(super dispose:)
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(if (Btst fFawnInRoom)
					(Print 390 3)
					(Print 390 4)
					(Print 390 5 #at -1 140)
				else
					(Print 390 6)
				)
			)
			(verbTalk
				(if (== (ego view?) 391)
					(Print 390 7)
					(Print 390 8)
				else
					(super doVerb: theVerb theItem)
				)
			)
			(else 
				(if (== (ego view?) 391)
					(Print 390 9)
				else
					(super doVerb: theVerb theItem)
				)
			)
		)
	)
)

(instance egoActions of Code	;EO: Was a class, but not in class table
	(properties)
	
	(method (doVerb theVerb theItem)
		(return
			(if (not (== (ego view?) 391))
				(return
					(switch theVerb
						(verbUse
							(switch theItem
								(iLubber
									(if wearingLubber
										(Print 390 10)
									else
										(Print 390 11)
										(= wearingLubber TRUE)
									)
								)
							)
						)
						(verbZipper
							(cond 
								((not (Btst fOrderedWine)) (Print 390 12))
								((not pouredWine) (Print 390 13))
								((not didForeplay) (Print 390 14))
								((and (== (ego x?) 153) (== (ego y?) 132))
									(HandsOff)
									(curRoom setScript: sGetHim)
									(return TRUE)
								)
							)
						)
					)
				)
			else
				(return
					(switch theVerb
						(verbDo (Print 390 15))
						(verbTalk (Print 390 7) (Print 390 8))
						(verbUse
							(switch theItem
								(iPocketKnife
									(HandsOff)
									(curRoom setScript: sCutLoose)
								)
								(else  (Print 390 9))
							)
						)
						(else  (Print 390 9))
					)
				)
			)
		)
	)
)

(instance sEnter of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 1))
			(1
				(door setCycle: EndLoop self)
				(soundFx number: 801 play:)
			)
			(2
				(ego setMotion: PolyPath 238 103 self)
			)
			(3
				(door setCycle: BegLoop)
				(soundFx number: 802 play:)
				(ego illegalBits: cWHITE setMotion: PolyPath 211 106 self)
			)
			(4 (= cycles 10))
			(5
				(if (and (Btst fOrderedWine) (not (Btst fSeenDeliveryBoyGag)))
					(Bset fSeenDeliveryBoyGag)
					(Print 390 16 #at -1 20)
					(Print 390 17)
				else
					(Print 390 18 #at -1 20)
				)
				(fawn setScript: sFawn)
				(door stopUpd:)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance sGetHim of Script
	(properties)
	
	(method (doit)
		(super doit: &rest)
		(if
		(and (== (theMusic prevSignal?) -1) state (Btst fRadioOn))
			(theMusic number: 390 loop: -1 vol: 127 play:)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= radioTimer 0)
				(Print 390 19)
				(ego egoSpeed: setMotion: PolyPath 89 100 self)
				(fawn
					setScript: 0
					cycleSpeed: egoSpeed
					moveSpeed: egoSpeed
					setLoop: 0
					setCel: 3
					setCycle: BegLoop fawn
				)
			)
			(1 (ego setHeading: 180 self))
			(2
				(Print 390 20 #at -1 19 #width 280)
				(theMusic pause: FALSE number: 396 loop: 1 play:)
				(fawn hide: setMotion: 0)
				(ego
					egoSpeed:
					view: 391
					setLoop: 6
					setCel: 0
					x: 72
					y: 116
					setPri: 9
				)
				(= seconds 4)
			)
			(3
				(Print 390 21 #at -1 20)
				(= seconds 4)
			)
			(4
				(Print 390 22 #at -1 20)
				(= seconds 4)
			)
			(5
				(Print 390 23 #at -1 20)
				(= seconds 4)
			)
			(6
				(Print 390 24 #at -1 20)
				(= seconds 4)
			)
			(7
				(Print 390 25 #at -1 20)
				(= seconds 2)
			)
			(8
				(soundFx number: 171 loop: -1 play:)
				(ego egoSpeed: 0 setLoop: 0 setCel: 0 setCycle: RandCycle)
				(= seconds 6)
			)
			(9
				(soundFx stop:)
				(if (not (Btst fRadioOn)) (theMusic fade:))
				(if (> dollars 10)
					(= winePrice (- dollars 10))
					(= dollars 10)
				else
					(= winePrice (- dollars 1))
					(= dollars 1)
				)
				(ego
					egoSpeed:
					x: 45
					y: 92
					setLoop: 1
					setCel: 0
					setCycle: 0
				)
				(fawn show: view: 612 setCycle: Walk setLoop: -1)
				(= seconds 3)
			)
			(10
				(Print 390 26)
				(Printf 390 27 winePrice)
				(= cycles (* (+ 1 howFast) 5))
			)
			(11
				(fawn setMotion: PolyPath 230 96 self)
			)
			(12
				(Print 390 28)
				(door setCycle: EndLoop self)
				(soundFx number: 801 setLoop: 1 play:)
			)
			(13
				(Print 390 29)
				(= seconds 3)
			)
			(14
				(Print 390 30)
				(fawn setMotion: PolyPath 241 88 self)
			)
			(15
				(Bclr fFawnInRoom)
				(soundFx number: 802 play:)
				(door setCycle: BegLoop self)
			)
			(16 (= seconds 2))
			(17
				(fawn dispose:)
				(Print 390 31)
				(= seconds 2)
			)
			(18
				(Print 390 32)
				(if (not (ego has: iPocketKnife))
					(= knifeTimer (* (+ 1 howFast) 300))
				)
				(HandsOn)
				(User canControl: FALSE)
				(theIconBar disable: ICON_WALK)
				(self dispose:)
			)
		)
	)
)

(instance sCutLoose of Script
	(properties)
	
	(method (doit)
		(super doit: &rest)
		(if
		(and (== (theMusic prevSignal?) -1) (Btst fRadioOn))
			(theMusic number: 390 loop: -1 vol: 127 play:)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Print 390 33)
				(ego egoSpeed: (+ egoSpeed 1) setCycle: EndLoop self)
			)
			(1
				(ego setLoop: 2 setCel: 0)
				(soundFx number: 393 loop: 1 play:)
				(theObject
					init:
					cycleSpeed: egoSpeed
					moveSpeed: egoSpeed
					view: 391
					loop: 5
					x: 79
					y: 90
					setStep: 15 15
					setPri: 14
					setCycle: Forward
					setMotion: MoveTo 74 44 self
				)
			)
			(2
				(theObject setMotion: MoveTo 69 90 self)
			)
			(3
				(theObject setLoop: 4 x: 73 y: 92)
				(= seconds 3)
			)
			(4
				(soundFx number: 394 loop: 1 play:)
				(theObject dispose:)
				(ego setCycle: CycleTo 2 1 self)
			)
			(5
				(ribbon setCel: 1 stopUpd:)
				(ego setCycle: EndLoop self)
			)
			(6
				(SolvePuzzle fCutRibbon 10)
				(= seconds 3)
			)
			(7
				(ego setLoop: 3 setCel: 0 setCycle: MoveCycle @egoTiedCycles self)
			)
			(8
				(ego
					setPri: -1
					view: 803
					setLoop: 1
					setCel: 255
					setCycle: BegLoop self
				)
				(if wearingLubber
					(Print 390 34)
				)
			)
			(9
				(ego setLoop: 0 setCel: 255 setCycle: BegLoop self)
			)
			(10
				(NormalEgo 0)
				(= seconds 3)
			)
			(11
				(Printf 390 35 dollars (if (== dollars 1) {} else {s}))
				(ribbon approachX: 89)
				(ribbon approachY: 100)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance sExit of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if modelessDialog (modelessDialog dispose:))
				(door setCycle: EndLoop self)
				(soundFx number: 801 play:)
			)
			(1
				(ego illegalBits: 0 setMotion: PolyPath 241 88 self)
			)
			(2
				(soundFx number: 802 play:)
				(door setCycle: BegLoop self)
			)
			(3
				(theMusic fade:)
				(curRoom newRoom: rmElevators)
			)
		)
	)
)

(instance sGetRibbon of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego
					egoSpeed:
					view: 808
					loop: 1
					cel: 0
					setCycle: EndLoop self
				)
			)
			(1
				(Print 390 36)
				(ego get: iRibbon)
				(ribbon dispose:)
				(SolvePuzzle fGetRibbon 3)
				(ego setCycle: BegLoop self)
			)
			(2
				(NormalEgo 1)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance sDoRadio of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(SolvePuzzle fTurnedOnRadio 1)
				(ego
					egoSpeed:
					view: 808
					loop: 1
					cel: 0
					setCycle: EndLoop self
				)
			)
			(1
				(if register
					(soundFx number: 140 loop: 1 play:)
					(if (== (theMusic number?) 390)
						(theMusic pause: 0)
					else
						(theMusic number: 390 play:)
					)
					(Print 390 37)
					(Print 390 38)
					(if (not (Btst fOrderedWine))
						(= radioTimer
							(Random (* (+ 1 howFast) 200) (* (+ 1 howFast) 300))
						)
					)
				else
					(theMusic pause: TRUE)
					(= radioTimer 0)
					(soundFx number: 140 loop: 1 play:)
					(Print 390 39)
				)
				(ego setCycle: BegLoop self)
			)
			(2
				(NormalEgo 2)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance sFawn of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(fawn setCycle: EndLoop self)
				(= seconds (Random 3 5))
			)
			(1
				(fawn setCel: 0 setLoop: (Random 1 2) setCycle: EndLoop)
				(= seconds (Random 5 10))
			)
			(2 (= start 1) (self init:))
		)
	)
)

(instance sPourWine of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(= pouredWine TRUE)
				(ego
					egoSpeed:
					normal: 0
					view: 393
					setLoop: 0
					setCel: 0
					setCycle: CycleTo 1 1 self
				)
			)
			(1
				(ego setCycle: EndLoop self)
				(wine dispose:)
			)
			(2
				(ego
					setLoop: 1
					setCycle: Walk
					setMotion: PolyPath 153 132 self
				)
			)
			(3
				(ego setLoop: 3 setCel: 0 setCycle: EndLoop self)
			)
			(4
				(ego setLoop: 4 setCel: 0 setCycle: EndLoop self)
			)
			(5
				(Print 390 40 #at -1 19 #width 280)
				(ego setLoop: 5 setCycle: Forward)
				(= seconds 3)
			)
			(6
				(ego setLoop: 6 setCel: 0 setCycle: EndLoop self)
			)
			(7
				(ego setLoop: 9 setCel: 0 setCycle: EndLoop self)
			)
			(8 (= cycles 20))
			(9
				(fawn
					setScript: 0
					setLoop: 3
					setCel: 0
					setCycle: EndLoop self
				)
			)
			(10
				(ego setLoop: 10 setCel: 0 setCycle: EndLoop self)
			)
			(11
				(soundFx number: 111 loop: -1 play:)
				(ego setLoop: 11 setCycle: Forward)
				(fawn setCycle: CycleTo 5 -1)
				(= seconds 3)
			)
			(12
				(soundFx stop:)
				(ego setLoop: 12 setCel: 0 setCycle: EndLoop self)
			)
			(13 (= cycles 10))
			(14
				(= temp0 991)
				(ego setLoop: 13 setCel: 0 setCycle: EndLoop self)
				(fawn setCycle: BegLoop)
			)
			(15
				(theObject
					init:
					cycleSpeed: egoSpeed
					moveSpeed: egoSpeed
					view: 393
					loop: 14
					cel: 1
					x: 191
					y: 96
					yStep: 5
					setPri: 7
					setCycle: Forward
					setMotion: JumpTo 228 96 self
				)
				(ego setLoop: 2)
			)
			(16
				(soundFx number: 167 loop: 1 play:)
				(ego setCycle: Walk setMotion: PolyPath 54 160 self)
			)
			(17
				(ego setLoop: 0 setCel: 5 setCycle: CycleTo 1 -1 self)
			)
			(18
				(wine init:)
				(ego setCycle: BegLoop self)
			)
			(19
				(theObject dispose:)
				(NormalEgo loopE)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance sDoCommercial of Script
	(properties)
	
	(method (doit)
		(super doit: &rest)
		(if
		(and (== state 1) (== (globalSound prevSignal?) -1))
			(= cycles 1)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theMusic vol: 0 pause: TRUE changeState:)
				(Print 390 41 #at -1 20 #dispose)
				(= seconds 6)
			)
			(1
				(globalSound play:)
				(if modelessDialog (modelessDialog dispose:))
				(Print 390 42 #at -1 20 #dispose)
			)
			(2
				(if modelessDialog (modelessDialog dispose:))
				(theMusic pause: FALSE fade: 127 10 5 0)
				(Print 390 43 #at -1 20 #dispose)
				(= seconds 4)
				(SolvePuzzle fHeardAjaxAd 1)
			)
			(3
				(if modelessDialog (modelessDialog dispose:))
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance door of Prop
	(properties
		x 257
		y 19
		description {the door}
		sightAngle 40
		approachX 230
		approachY 96
		view 390
		loop 2
		priority 6
		signal (| ignrAct fixPriOn)
	)
	
	(method (doVerb theVerb theItem)
		(if (== (ego view?) 391)
			(Print 390 9)
		else
			(switch theVerb
				(verbDo
					(HandsOff)
					(curRoom setScript: sExit)
				)
				(else 
					(super doVerb: theVerb theItem)
				)
			)
		)
	)
)

(instance ribbon of View
	(properties
		x 64
		y 102
		description {the ribbon}
		sightAngle 40
		approachX 116
		approachY 143
		view 390
		priority 8
		signal (| ignrAct fixPriOn stopUpdOn)
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(cond 
					((== cel 1) (Print 390 44))
					((== (ego view?) 391) (Print 390 45))
					(else (Print 390 46))
				)
			)
			(verbDo
				(cond 
					((== (ego view?) 391) (Print 390 47))
					((self cel?) (HandsOff) (curRoom setScript: sGetRibbon))
					(else (super doVerb: theVerb theItem))
				)
			)
			(verbUse
				(switch theItem
					(iPocketKnife
						(if (== (ego view?) 391)
							(HandsOff)
							(curRoom setScript: sCutLoose)
						else
							(super doVerb: theVerb theItem)
						)
					)
					(else 
						(if (== (ego view?) 391)
							(Print 390 9)
						else
							(super doVerb: theVerb theItem)
						)
					)
				)
			)
			(verbTalk
				(if (== (ego view?) 391)
					(Print 390 7)
					(Print 390 48)
				else
					(super doVerb: theVerb theItem)
				)
			)
			(verbTaste (Print 390 49))
			(else 
				(if (== (ego view?) 391)
					(Print 390 9)
				else
					(super doVerb: theVerb theItem)
				)
			)
		)
	)
)

(instance wine of View
	(properties
		x 31
		y 160
		z 31
		description {the wine}
		sightAngle 40
		approachX 54
		approachY 160
		view 390
		loop 1
		priority 12
		signal (| ignrAct fixPriOn)
	)
	
	(method (doVerb theVerb theItem)
		(if (== (ego view?) 391)
			(Print 390 9)
		else
			(switch theVerb
				(verbLook (Print 390 50))
				(verbDo
					(if (not (Btst 12))
						(Print 390 51)
					else
						(HandsOff)
						(curRoom setScript: sPourWine)
					)
				)
				(verbUse
					(switch theItem
						(iHammer
							(Print 390 52)
						)
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

(instance fawn of Person
	(properties
		x 137
		y 132
		description {Fawn}
		sightAngle 40
		approachX 153
		approachY 132
		view 392
		signal ignrAct
		illegalBits $0000
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(cond 
					(pouredWine (Print 390 53))
					((Btst 24) (Print 390 54))
					(else (Print 390 55))
				)
			)
			(verbTalk
				(cond 
					((not (Btst fOrderedWine)) (if (Random 0 1) (Print 390 56) else (Print 390 57)))
					((not pouredWine) (Print 390 13))
					(else (Print 390 58) (Print 390 59 #at -1 140))
				)
			)
			(verbDo
				(cond 
					((not (Btst fOrderedWine)) (Print 390 60))
					((not pouredWine) (Print 390 13))
					(else (Print 390 61) (Print 390 62 #at -1 140) (= didForeplay TRUE))
				)
			)
			(verbZipper
				(cond 
					((not (Btst fOrderedWine)) (Print 390 12))
					((not pouredWine) (Print 390 13))
					((not didForeplay) (Print 390 63))
					(else (HandsOff) (curRoom setScript: sGetHim))
				)
			)
			(verbTaste (Print 390 64))
			(else 
				(super doVerb: theVerb theItem)
			)
		)
	)
	
	(method (cue)
		(super cue:)
		(self
			setLoop: -1
			view: 612
			setCycle: Walk
			setMotion: PolyPath 104 100
		)
	)
)

(instance theObject of Actor
	(properties
		view 391
		signal (| ignrAct fixedLoop fixPriOn)
		illegalBits $0000
		xStep 1
	)
)

(instance theRadio of Feature
	(properties
		x 65
		y 98
		z 27
		nsTop 62
		nsLeft 53
		nsBottom 81
		nsRight 78
		description {the radio}
		sightAngle 40
		approachX 85
		approachY 96
		lookStr {It's pretty complicated. There's a knob that says "Power."}
	)
	
	(method (doVerb theVerb theItem)
		(if (== (ego view?) 391)
			(Print 390 9)
		else
			(switch theVerb
				(verbDo
					(HandsOff)
					(if (Btst fRadioOn)
						(Bclr fRadioOn)
						(curRoom setScript: sDoRadio 0 0)
					else
						(Bset fRadioOn)
						(curRoom setScript: sDoRadio 0 1)
					)
				)
				(else 
					(super doVerb: theVerb theItem &rest)
				)
			)
		)
	)
)

(instance theBed of Feature
	(properties
		x 62
		y 100
		nsTop 92
		nsLeft 11
		nsBottom 139
		nsRight 113
		description {the bed}
		sightAngle 40
		lookStr {You love the thought of a heart-shaped bed!}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo (Print 390 65))
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance theWindow of Feature
	(properties
		x 15
		y 112
		z 59
		nsTop 15
		nsBottom 92
		nsRight 30
		description {the window}
		sightAngle 40
		lookStr {Out the window the lights of Lost Wages spread before you like some cheap trick.}
	)
	
	(method (doVerb theVerb theItem)
		(if (== (ego view?) 391)
			(Print 390 9)
		else
			(switch theVerb
				(verbDo (Print 390 66))
				(verbUse (Print 390 67))
				(else 
					(super doVerb: theVerb theItem &rest)
				)
			)
		)
	)
)

(instance painting of Feature
	(properties
		x 145
		y 93
		z 49
		nsTop 18
		nsLeft 105
		nsBottom 71
		nsRight 185
		description {the painting}
		sightAngle 40
		lookStr {To you, it looks like dozens of spermatozoa attacking a Whitman's sampler!}
	)
)

(instance iceBucket of Feature
	(properties
		x 30
		y 166
		z 37
		nsTop 120
		nsLeft 24
		nsBottom 139
		nsRight 37
		description {the ice bucket}
		sightAngle 40
		approachX 54
		approachY 160
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(if (cast contains: wine)
					(Print 390 68)
				else
					(Print 390 69)
				)
			)
			(verbDo
				(if (cast contains: wine)
					(wine doVerb: theVerb theItem)
				else
					(Print 390 70)
				)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance vase of Feature
	(properties
		x 123
		y 164
		nsTop 140
		nsLeft 113
		nsBottom 189
		nsRight 134
		description {the vase}
		sightAngle 40
		lookStr {How sweet. Fawn has placed the rose you gave her in that vase. What a romantic she is!}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo (Print 390 71))
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance theChair of Feature
	(properties
		x 259
		y 163
		nsTop 138
		nsLeft 232
		nsBottom 189
		nsRight 286
		description {chair}
		sightAngle 40
		lookStr {You can just picture Fawn using that chair in the morning.}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo (Print 390 72))
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance painting2 of Feature
	(properties
		x 288
		y 120
		z 60
		nsTop 20
		nsLeft 258
		nsBottom 101
		nsRight 319
		description {the painting}
		sightAngle 40
		lookStr {How clever! A painting of the opposite side of the room. Many hotels would just install a mirror.}
	)
)

(instance sculpture of Feature
	(properties
		x 258
		y 180
		nsTop 43
		nsLeft 197
		nsBottom 189
		nsRight 319
		description {the sculpture}
		sightAngle 40
		onMeCheck SKIPCHECK
		lookStr {Wait a minute! What are those sculptures doing?!}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance flower of Feature
	(properties
		x 263
		y 135
		z 49
		nsTop 75
		nsLeft 256
		nsBottom 97
		nsRight 271
		description {the rose}
		sightAngle 40
		approachX 235
		approachY 132
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(if (Btst fFawnInRoom)
					(Print 390 73)
					(Print 390 74)
				else
					(Print 390 75)
				)
			)
			(verbDo
				(if (Btst fFawnInRoom)
					(Print 390 76)
				else (Print 390 77)
				)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)
