;;; Sierra Script 1.0 - (do not remove this comment)
(script# rmAlley) ;160
(include game.sh)
(use Main)
(use Intrface)
(use rmHoneymoonSuite)
(use LLRoom)
(use MoveCyc)
(use PolyPath)
(use Polygon)
(use Feature)
(use LoadMany)
(use Motion)
(use Invent)
(use User)
(use Actor)
(use System)

(public
	rm160 0
)

(local
	foundHammerInBin
	toX
	toY
	[egoFallCycle 13] = [4 0 129 72 4 1 126 116 4 2 127 135 -32768]
	[fallDieCycle 37] = [0 0 197 66 0 1 201 68 0 2 206 74 0 3 194 77 0 4 192 75 0 5 196 70 1 0 223 67 1 1 217 71 1 2 224 71 -32768]
)
(procedure (BadMove)
	(HandsOff)
	(Print 160 61)
	(sFallDie start: 3)
	(ego
		normal: FALSE
		y: 66
		view: 164
		cycleSpeed: (+ egoSpeed 6)
		setScript: 0
	)
	(globalSound stop:)
	(curRoom setScript: sFallDie)
)

(instance rm160 of LLRoom
	(properties
		picture rmAlley
		west rmOutsideBar
	)
	
	(method (init)
		(LoadMany SOUND 160 161 163 164 165)
		(LoadMany VIEW 160 161 162 164 812)
		(soundFx loop: 1 vol: 127 flags: mNOPAUSE)
		(if (!= prevRoomNum rmHooker)
			(curRoom
				addObstacle:
					((Polygon new:)
						type: PBarredAccess
						init:
							62 171 58 162 41 162 30 136 58 0 319 0
							319 189 0 189 0 186 254 186 225 160 191 160
							197 171
						yourself:
					)
			)
		)
		(ego init: actions: egoActions)
		(if debugging (ego get: iRibbon get: iHammer))
		(theEgoHead actions: egoActions)
		(switch prevRoomNum
			(rmOutsideBar (ego y: 167))
			(rmInsideBar
				(HandsOff)
				(ego hide: normal: 0)
				(self style: IRISOUT setScript: sComeTo)
			)
			(rmHooker
				(NormalEgo 2)
				(ego x: 162 y: 62 illegalBits: cWHITE)
			)
			(else  (ego x: 160 y: 160))
		)
		(super init:)
		(if
			(or
				(!= (globalSound number?) 800)
				(and
					(== (globalSound number?) 800)
					(or
						(== (globalSound signal?) -1)
						(< (globalSound vol?) 127)
					)
				)
			)
			(globalSound number: 800 loop: -1 flags: 1 play: 127)
		)
		(cond 
			((and (Btst fAlleyWindowBroken) (CheckItemOwner iPills)) (bottle init:))
			((and (ego has: iRibbon) (ego has: iHammer)) (Load SOUND 167))
		)
		(eastWindow setCel: (if (Btst fAlleyWindowBroken) 4 else 0) init:)
		(hotelSign cycleSpeed: 30 init: setCycle: Forward)
		(if (Btst fHookerWindowOpen) (hookerWindow init:))
		(binF init:)
		(balconyF init:)
		(hookerWindowF init:)
		(hotelSignF init:)
		(eastWindowF init:)
		(fenceF init:)
		(if (!= prevRoomNum rmHooker)
			(fenceF approachVerbs: verbDo verbUse verbZipper verbTaste)
			(binF approachVerbs: verbDo verbUse verbZipper verbTaste)
		)
		(lidF init:)
	)
	
	(method (doit)
		(super doit: &rest)
		(cond 
			(script)
			((IsObjectOnControl ego cBLUE) (curRoom newRoom: rmOutsideBar))
			((IsObjectOnControl ego cGREEN) (HandsOff) (curRoom setScript: sFall))
			((ego mover?)
				(ego setScript: 0)
				(cond 
					((== (ego view?) 161)
						(HandsOff)
						(binF approachVerbs: verbDo verbUse verbZipper verbTaste)
						(curRoom setScript: sClimbOut)
					)
					((and (== (ego view?) 162) (> (ego loop?) 1)) (HandsOff) (curRoom setScript: sClimbBack))
					(
					(and (== (ego view?) 162) (== (ego loop?) 1)) (HandsOff) (curRoom setScript: sUntieRailing))
					(
					(and (== (ego view?) 162) (== (ego loop?) 0)) (HandsOff) (curRoom setScript: sUnTie))
				)
			)
		)
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(if (== ((inventory at: iPills) owner?) 160)
					(Print 160 0)
				else
					(Print 160 1)
				)
			)
			(verbUse
				(switch theItem
					(iRibbon (Print 160 2))
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

(instance egoActions of Code	;EO: this was a class, but it is not in the class table
	(properties)
	
	(method (doVerb theVerb theItem)
		(return
			(switch theVerb
				(verbUse
					(switch theItem
						(iRibbon
							(cond 
								((== (ego view?) 162) (Print 160 3))
								((< (ego y?) 100) (HandsOff) (curRoom setScript: sTie))
							)
						)
						(iPocketKnife
							(cond 
								((and (== (ego view?) 162) (> (ego loop?) 1)) (BadMove))
								(
								(and (== (ego view?) 162) (== (ego loop?) 1)) (HandsOff) (curRoom setScript: sUntieRailing) (return TRUE))
								(
								(and (== (ego view?) 162) (== (ego loop?) 0)) (HandsOff) (curRoom setScript: sUnTie) (return TRUE))
							)
						)
					)
				)
				(3
					(if
					(and (== (ego view?) 162) (== (ego loop?) 0))
						(HandsOff)
						(curRoom setScript: sUnTie)
						(return TRUE)
					)
				)
			)
		)
	)
)

(instance sFall of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego
					egoSpeed: (+ egoSpeed howFast)
					normal: 0
					view: 164
					setLoop: 4
					setCel: 0
					setCycle: MoveCycle @egoFallCycle self
					illegalBits: 0
					setPri: (ego priority?)
				)
				(soundFx number: 160 setVol: 127 play:)
			)
			(1
				(ego hide: setPri: -1)
				(soundFx number: 161 play:)
				(curRoom
					setScript: sComeTo
					addObstacle:
						((Polygon new:)
							type: PBarredAccess
							init:
								62 171 58 162 41 162 30 136 58 0 319 0
								319 189 0 189 0 186 254 186 225 160 191 160
								197 171
							yourself:
						)
				)
			)
		)
	)
)

(instance sToHooker of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego illegalBits: 0 setMotion: PolyPath 162 62 self)
			)
			(1 (ego setHeading: 0 self))
			(2
				(globalSound fade:)
				(curRoom newRoom: rmHooker)
			)
		)
	)
)

(instance sUntieRailing of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (ego setCycle: BegLoop self))
			(1
				(ego setLoop: 0 setCel: 255)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance sUnTie of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (ego setCycle: BegLoop self))
			(1
				(ego
					view: 800
					setLoop: -1
					setCycle: Walk
					setMotion: PolyPath 174 67 self
				)
			)
			(2
				(theMusic fade:)
				(NormalEgo 2)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance sClimbOut of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= toX ((User curEvent?) x?))
				(= toY ((User curEvent?) y?))
				(ego view: 161 setLoop: 1 setCel: 1 hide:)
				(= cycles 1)
			)
			(1
				(ego egoSpeed: (* 2 egoSpeed) show: setCycle: BegLoop self)
				(soundFx number: 161 play:)
			)
			(2
				(ego
					view: 161
					z: 0
					setLoop: 0
					setCel: 3
					setCycle: BegLoop self
				)
			)
			(3
				(soundFx number: 163 play:)
				(binF approachVerbs: verbDo verbUse verbZipper verbTaste)
				(ego normal: TRUE)
				(NormalEgo 3)
				(HandsOn)
				(if
					(and
						(IsObject (CueObj client?))
						((CueObj client?) approachX?)
						(!= (CueObj client?) binF)
					)
					(ego
						setMotion:
							PolyPath
							((CueObj client?) approachX?)
							(+ (ego z?) ((CueObj client?) approachY?))
							CueObj
					)
				else
					(ego setMotion: PolyPath toX toY)
				)
				(self dispose:)
			)
		)
	)
)

(instance sComeTo of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 3))
			(1 (Print 160 4) (= seconds 3))
			(2 (Print 160 5) (= seconds 3))
			(3
				(ego
					show:
					view: 161
					loop: 1
					cel: 2
					x: 117
					y: 171
					z: 34
					yStep: 2
					cycleSpeed: (+ egoSpeed 4)
					setCycle: BegLoop self
					setPri: -1
				)
			)
			(4
				(Print 160 6)
				(binF approachVerbs: verbNone)
				(fenceF approachVerbs: verbDo verbUse verbZipper verbTaste)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance sJumpIn of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Print 160 7)
				(binF approachVerbs: 0)
				(= cycles 2)
			)
			(1
				(ego
					view: 161
					setLoop: 0
					setCel: 0
					cycleSpeed: (* egoSpeed 2)
					setCycle: CycleTo 2 1 self
				)
			)
			(2
				(soundFx number: 161 play:)
				(ego setCycle: EndLoop self)
			)
			(3
				(ego z: 34 setLoop: 1 setCel: 0)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance sGetHammer of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego egoSpeed: setCel: 0 setLoop: 1 setCycle: EndLoop self)
			)
			(1
				(ego get: iHammer)
				(SolvePuzzle fGetHammer 3)
				(= cycles 10)
			)
			(2
				(Print 160 8)
				(ego loop: 1 cel: 0)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance sTie of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego illegalBits: 0 setMotion: PolyPath 185 61 self)
			)
			(1
				(Print 160 9)
				(ego
					view: 162
					setLoop: 0
					setCel: 0
					cycleSpeed: (+ egoSpeed 6)
					setCycle: EndLoop self
				)
			)
			(2 (HandsOn) (self dispose:))
		)
	)
)

(instance sTieToRail of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego egoSpeed: setLoop: 1 setCel: 0 setCycle: EndLoop self)
			)
			(1
				(Print 160 10)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance sJumpRail of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theMusic number: 165 loop: -1 vol: 127 flags: 1 play:)
				(ego
					egoSpeed:
					setLoop: 2
					setCel: 0
					posn: 198 64
					setCycle: EndLoop self
				)
			)
			(1
				(ego setLoop: 3 setCel: 0 posn: 226 60 setCycle: EndLoop self)
			)
			(2
				(Print 160 11)
				(if (CheckItemOwner iPills) (Print 160 12))
				(ego setScript: sWiggle)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance sClimbBack of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego
					egoSpeed:
					setScript: 0
					posn: 230 63
					setLoop: 5
					setCel: 0
					setCycle: EndLoop self
				)
			)
			(1
				(ego posn: 197 63 setLoop: 6 setCel: 0 setCycle: EndLoop self)
			)
			(2
				(ego x: 185 y: 61 setLoop: 1 setCel: 255)
				(theMusic fade:)
				(= cycles 1)
			)
			(3
				(Print 160 13)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance sSmashWindow of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Print 160 14)
				(ego
					egoSpeed:
					setScript: 0
					setLoop: 4
					setCel: 0
					setCycle: EndLoop self
				)
			)
			(1
				(soundFx number: 167 play:)
				(eastWindow setCycle: EndLoop eastWindow)
				(ego setCycle: BegLoop self)
			)
			(2
				(ego setLoop: 3 setCel: 255)
				(= cycles 1)
			)
			(3
				(ego setScript: sWiggle)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance sFallDie of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego illegalBits: 0 setMotion: MoveTo 187 66 self)
			)
			(1
				(ego
					normal: 0
					view: 164
					setLoop: 0
					setCel: 0
					egoSpeed: (+ 1 egoSpeed)
					setCycle: MoveCycle @fallDieCycle self
				)
			)
			(2 (Print 160 15) (= cycles 1))
			(3
				(theMusic loop: 1 setVol: 127 number: 160 play:)
				(ego
					setLoop: 2
					setCel: 0
					x: 222
					setStep: 3 6
					setCycle: EndLoop self
					setMotion: MoveTo (ego x?) 145 self
				)
			)
			(4)
			(5
				(theMusic number: 164 play:)
				(ego
					setLoop: 3
					setCel: 0
					cycleSpeed: egoSpeed
					x: 219
					y: 167
					setCycle: EndLoop self
				)
			)
			(6 (= seconds 3))
			(7
				(Print 160 16)
				(= seconds 3)
			)
			(8
				(globalSound number: 192 setLoop: -1 flags: 1 play:)
				(soundFx
					number: 191
					setLoop: 1
					setVol: 127
					flags: 1
					play:
				)
				(ego
					view: 812
					setLoop: 1
					setCel: 0
					cycleSpeed: (* egoSpeed 2)
					setCycle: EndLoop self
				)
			)
			(9 (curRoom newRoom: rmWorkshop))
		)
	)
)

(instance sGetPills of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego get: iPills)
				(SolvePuzzle fGetPills 8)
				(= cycles 1)
			)
			(1
				(Print 160 17)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance sWiggle of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds (Random 2 4)))
			(1
				(ego egoSpeed: setLoop: 7 setCycle: Forward)
				(= seconds (Random 1 2))
			)
			(2
				(ego setLoop: 3 setCel: 255 setCycle: 0)
				(self init:)
			)
		)
	)
)

(instance eastWindow of Prop
	(properties
		x 259
		y 39
		description {the east window}
		view 160
		loop 3
	)
	
	(method (doVerb theVerb theItem)
		(eastWindowF doVerb: theVerb theItem)
	)
	
	(method (cue)
		(super cue:)
		(bottle init:)
	)
)

(instance hotelSign of Prop
	(properties
		x 15
		y 100
		z 100
		description {the hotel sign}
		sightAngle 40
		view 160
		loop 1
		priority 10
		signal fixPriOn
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(ego
					setHeading: (GetAngle (ego x?) (ego y?) (self x?) (self y?)) self
				)
			)
			(verbDo (Print 160 20))
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
	
	(method (cue)
		(super cue:)
		(Print 160 18)
		(Print 160 19)
	)
)

(instance bottle of View
	(properties
		view 160
		priority 12
		signal fixPriOn
	)
	
	(method (init)
		(super init:)
		(self x: (eastWindow x?))
		(self y: (eastWindow y?))
	)
)

(instance hookerWindow of View
	(properties
		x 174
		y 23
		description {the west window}
		sightAngle 40
		view 160
		loop 2
		signal ignrAct
	)
	
	(method (doVerb theVerb theItem)
		(hookerWindowF doVerb: theVerb theItem)
	)
)

(instance binF of Feature
	(properties
		x 122
		y 149
		nsTop 131
		nsLeft 74
		nsBottom 168
		nsRight 170
		description {the dumpster}
		sightAngle 40
		approachX 117
		approachY 171
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(if (== (ego view?) 161)
					(if (not (ego has: iHammer))
						(= foundHammerInBin 1)
						(Print 160 21)
					else
						(Print 160 22)
					)
				else
					(Print 160 23)
				)
			)
			(verbDo
				(cond 
					((< (ego y?) 100) (Print 160 24))
					((not (== (ego view?) 161)) (HandsOff) (curRoom setScript: sJumpIn))
					((ego has: 14) (Print 160 22))
					((not foundHammerInBin) (= foundHammerInBin 1) (Print 160 21))
					(else (HandsOff) (curRoom setScript: sGetHammer))
				)
			)
			(verbUse
				(if (< (ego y?) 100)
					(Print 160 25)
				else
					(switch theItem
						(iHammer (Print 160 26))
						(iBreathSpray (Print 160 27))
						(iWallet (Print 160 28))
						(iLubber
							(ego put: theItem 0)
							(if (Btst fWearingLubber)
								(Bclr fWearingLubber)
								(SolvePuzzle fRemoveLubber 1)
								(Print 160 29)
							else
								(Print 160 30)
								(ego put: theItem 0)
							)
						)
						(else 
							(if (IsObject (Inventory at: theItem))
								(ego put: theItem 0)
								(Printf 160 31 ((Inventory at: theItem) description?))
							else
								(Print 160 32)
							)
						)
					)
				)
			)
			(verbTaste (Print 160 6))
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance balconyF of Feature
	(properties
		x 147
		y 77
		z 17
		nsTop 46
		nsLeft 94
		nsBottom 80
		nsRight 201
		description {the fire escape}
		sightAngle 40
		lookStr {A rickety fire escape, complete with safety ladder, is loosely attached to the building. A mild yellow glow comes from the window behind it.}
	)
	
	(method (doVerb theVerb theItem)
		(if (and (< (ego y?) 100) (== theVerb verbLook))
			(Print 160 33)
		else
			(switch theVerb
				(verbDo
					(cond 
						((and (== (ego view?) 162) (> (ego loop?) 1))
							(HandsOff)
							(ego setScript: 0)
							(curRoom setScript: sClimbBack)
						)
						(
						(and (== (ego view?) 162) (== (ego loop?) 1)) (HandsOff) (curRoom setScript: sUntieRailing))
						((< (ego y?) 100) (Print 160 34))
						(else (Print 160 35))
					)
				)
				(verbUse
					(switch theItem
						(iRibbon
							(cond 
								(
								(and (== (ego view?) 162) (== (ego loop?) 1)) (Print 160 36))
								(
								(and (== (ego view?) 162) (== (ego loop?) 0)) (HandsOff) (curRoom setScript: sTieToRail))
								((and (== (ego view?) 162) (> (ego loop?) 1)) (Print 160 37))
								((< (ego y?) 100) (Print 160 38))
								(else (Print 160 39) (Print 160 40 #at -1 140))
							)
						)
						(iPocketKnife
							(cond 
								(
								(and (== (ego view?) 162) (== (ego loop?) 0)) (HandsOff) (curRoom setScript: sUnTie))
								(
								(and (== (ego view?) 162) (== (ego loop?) 1)) (HandsOff) (curRoom setScript: sUntieRailing))
								((and (== (ego view?) 162) (> (ego loop?) 1)) (BadMove))
								(else (super doVerb: theVerb theItem &rest))
							)
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
)

(instance hookerWindowF of Feature
	(properties
		x 162
		y 50
		nsTop 1
		nsLeft 142
		nsBottom 43
		nsRight 183
		description {the west window}
		sightAngle 40
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(if (< (ego y?) 100)
					(Print 160 41)
				else
					(Print 160 42)
				)
			)
			(verbDo
				(if (< (ego y?) 100)
					(if
						(or
							(and (== (ego view?) 162) (== (ego loop?) 1))
							(and (== (ego view?) 162) (> (ego loop?) 1))
							(and (== (ego view?) 162) (== (ego loop?) 0))
						)
						(Print 160 43)
					else
						(HandsOff)
						(curRoom setScript: sToHooker)
					)
				else
					(Print 160 44)
				)
			)
			(verbUse
				(switch theItem
					(iHammer
						(if (< (ego y?) 100)
							(Print 160 45)
						else
							(Print 160 46)
							(Print 160 47 #at -1 140)
						)
					)
					(else 
						(super doVerb: theVerb theItem)
					)
				)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance hotelSignF of Feature
	(properties
		x 19
		y 157
		z 121
		nsTop 1
		nsBottom 72
		nsRight 39
		description {the hotel sign}
		sightAngle 40
	)
	
	(method (doVerb theVerb theItem)
		(hotelSign doVerb: theVerb theItem)
	)
)

(instance eastWindowF of Feature
	(properties
		x 264
		y 157
		z 131
		nsTop 6
		nsLeft 244
		nsBottom 47
		nsRight 284
		description {the east window}
		sightAngle 40
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(if (< (ego y?) 100)
					(if (CheckItemOwner iPills)
						(if (and (== (ego view?) 162) (> (ego loop?) 1))
							(Print 160 48)
						else
							(Print 160 49)
						)
					else
						(Print 160 50)
					)
				else
					(hookerWindowF doVerb: theVerb theItem)
				)
			)
			(verbDo
				(cond 
					((not (< (ego y?) 100)) (Print 160 51))
					((or (!= (ego view?) 162) (== (ego loop?) 0)) (HandsOff) (curRoom setScript: sFallDie))
					((== (ego loop?) 1) (HandsOff) (curRoom setScript: sJumpRail))
					((not (Btst fAlleyWindowBroken)) (Print 160 52) (Print 160 53 #at -1 140))
					((!= ((inventory at: iPills) owner?) 160) (Print 160 54))
					(else
						(HandsOff)
						(curRoom setScript: sGetPills)
						(bottle z: 1000 dispose:)
					)
				)
			)
			(verbUse
				(switch theItem
					(iHammer
						(if (and (== (ego view?) 162) (> (ego loop?) 1))
							(if (Btst fAlleyWindowBroken)
								(Print 160 55)
							else
								(Bset fAlleyWindowBroken)
								(HandsOff)
								(curRoom setScript: sSmashWindow)
							)
						else
							(Print 160 56)
						)
					)
					(iPocketKnife (Print 160 57))
					(iRing (Print 160 58))
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

(instance fenceF of Feature
	(properties
		x 275
		y 143
		nsTop 104
		nsLeft 233
		nsBottom 182
		nsRight 318
		description {the fence}
		sightAngle 40
		approachX 237
		approachY 172
		lookStr {You see alleys stretching off to infinity.}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo (Print 160 59))
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance lidF of Feature
	(properties
		x 124
		y 160
		z 45
		nsTop 101
		nsLeft 84
		nsBottom 130
		nsRight 165
		description {the lid}
		sightAngle 40
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo (Print 160 60))
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)
