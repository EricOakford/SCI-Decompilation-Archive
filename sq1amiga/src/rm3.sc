;;; Sierra Script 1.0 - (do not remove this comment)
(script# 3)
(include game.sh)
(use Main)
(use Intrface)
(use Arcada)
(use SQRoom)
(use Talker)
(use Osc)
(use Polygon)
(use Feature)
(use MoveFwd)
(use LoadMany)
(use StopWalk)
(use DPath)
(use Sound)
(use Motion)
(use User)
(use Actor)
(use System)

(public
	rm3 0
)

(local
	local0
	lookedAtScientist
	local2
	scientistCameIn
	droidHoldingCartridge
	droidMoving
	local6
	local7 =  3
)
(procedure (WrongLevel)
	(switch (Random 0 1)
		(0 (Print 3 29))
		(1 (Print 3 30))
	)
)

(instance rm3 of SQRoom
	(properties
		picture 3
		style FADEOUT
		east 4
		west 6
	)
	
	(method (init)
		(LoadMany VIEW 0 7 401 103)
		(leftDoor init: stopUpd:)
		(rightDoor init: stopUpd:)
		(rightPanelLights setCycle: Forward init:)
		(leftPanelLights setCycle: Forward init:)
		(ego init:)
		(self setRegions: ARCADA)
		(if (OneOf prevRoomNum west east)
			(= style (if (== prevRoomNum west) SCROLLLEFT else SCROLLRIGHT))
			(if (== currentFloor 1)
				(= local0 1)
				(soundFx number: 311 loop: 1 play:)
			)
		)
		(if (!= (theMusic number?) 355)
			(theMusic2 number: 353 loop: -1 flags: 1 play: 40)
			(backSound number: 340 loop: -1 play: 30)
			(if (== currentFloor 1)
				(backSound fade: 127 25 10 0)
				(theMusic2 fade: 127 25 10 0)
			else
				(backSound fade: 50 25 10 0)
				(theMusic2 fade: 50 25 10 0)
			)
		)
		(droid init:)
		(if (== howFast slow)
			(droid stopUpd:)
		)
		(super init:)
		(features
			add: Computer holes lights mainComputer cartridgeSlots
			eachElementDo: #init
		)
		(if (Btst fScientistDead)
			(scientist
				init:
				posn: 224 88
				setLoop: 2
				cel: 0
				show:
				ignoreActors: TRUE
				stopUpd:
			)
		)
		(if (not (ArcadaCheck #rFlag1 rFGettingCart))
			(if (== currentFloor 1)
				(if (Btst fScientistDead)
					(self
						addObstacle:
							((Polygon new:)
								type: PBarredAccess
								init:
									0 0
									319 0
									319 83
									253 83
									252 90
									237 93
									203 91
									189 84
									175 84
									173 78
									166 70
									147 70
									160 79
									163 92
									136 92
									122 81
									118 68
									99 68
									74 76
									63 84
									0 84
								yourself:
							)
							((Polygon new:)
								type: PBarredAccess
								init:
									0 93
									56 93
									31 122
									31 144
									61 171
									156 185
									253 170
									282 147
									281 121
									260 94
									319 92
									319 189
									0 189
								yourself:
							)
					)
				else
					(self
						addObstacle:
							((Polygon new:)
								type: PBarredAccess
								init:
									0 0
									319 0
									319 83
									249 83
									234 74
									205 65
									204 74
									189 84
									175 84
									173 78
									166 70
									147 70
									160 79
									163 92
									136 92
									121 82
									116 68
									99 68
									74 76
									63 84
									0 84
								yourself:
							)
							((Polygon new:)
								type: PBarredAccess
								init:
									0 93
									56 93
									31 122
									31 144
									61 171
									156 185
									253 170
									282 147
									281 121
									258 92
									319 92
									319 189
									0 189
								yourself:
							)
					)
				)
				(switch scientistState
					(0
						(= scientistState 1)
					)
					(1
						(scientist
							init:
							hide:
							ignoreActors: 1
							setScript: scientistComeIn
						)
					)
				)
				(if (== prevRoomNum west) (leftDoor cel: 3 stopUpd:))
				(if (== prevRoomNum east) (rightDoor cel: 3 stopUpd:))
			else
				(ego setPri: 1)
				(self
					addObstacle:
						((Polygon new:)
							type: PBarredAccess
							init: 0 0 319 0 319 133 0 133
							yourself:
						)
						((Polygon new:)
							type: PBarredAccess
							init: 0 157 319 157 319 189 0 189
							yourself:
						)
				)
			)
		)
		(cond 
			((== prevRoomNum 103)
				(ego view: 7 setLoop: 0 cel: 7 posn: 168 92 normal: 0)
				(cond 
					((ArcadaCheck #rFlag1 rFCartReadyToTake)
						(droid posn: 183 64 setPri: 6)
						(self setScript: putCartAway)
					)
					((ArcadaCheck #rFlag1 rFGettingCart)
						(self setScript: goGetCart)
					)
					(else (self setScript: riseFromComputer))
				)
			)
			((== currentFloor 1) (self setScript: enterRoom))
		)
		(if (== currentFloor 1) (Computer sightAngle: 45))
		(if
		(and (== currentFloor 2) (OneOf prevRoomNum west east))
			(HandsOn)
		)
	)
	
	(method (doit &tmp thisControl)
		(super doit:)
		(= thisControl (ego onControl: origin))
		(cond
			((not local0)
				(cond
					((& thisControl cBLUE)
						(soundFx number: 311 loop: 1 play:)
						(= local0 1)
						(leftDoor setCycle: EndLoop leftDoor)
					)
					((& thisControl cCYAN)
						(soundFx number: 311 loop: 1 play:)
						(= local0 1)
						(rightDoor setCycle: EndLoop rightDoor)
					)
				)
			)
			((not (& thisControl (| cBLUE cCYAN)))
				(soundFx number: 311 loop: 1 play:)
				(= local0 0)
				(if (leftDoor cel:)
					(leftDoor setCycle: BegLoop leftDoor)
				)
				(if (rightDoor cel:)
					(rightDoor setCycle: BegLoop rightDoor)
				)
			)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(2
				(if (== currentFloor 1)
					(Print 3 0)
				else
					(Print 3 1)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
	
	(method (newRoom n)
		(ego setPri: -1)
		(if scientistCameIn
			(Bset fScientistDead)
			(= scientistState 3)
		)
		(if (!= (theMusic number?) 355)
			(if (OneOf n west east)
				(backSound fade:)
				(theMusic2 fade:)
			)
			(if (and (== n 103) (not (ArcadaCheck #rFlag1 rFGettingCart)))
				(theMusic2 number: 353 fade: 50 25 10 0)
			)
		)
		(super newRoom: n)
	)
	
	(method (notify)
		(if
		(and (== currentFloor 1) (OneOf prevRoomNum east west))
			(self setScript: enterRoom)
		)
	)
)

(instance enterRoom of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (HandsOff) (= cycles 3))
			(1
				(ego setMotion: MoveFwd 30 self)
			)
			(2 (HandsOn) (self dispose:))
		)
	)
)

(instance scientistComeIn of Script
	(properties)
	
	(method (doit)
		(if
			(and
				(== state 1)
				(not (curRoom script?))
				(or (< (ego x?) 156) (> (ego y?) 132))
			)
			(self cue:)
		)
		(if
		(and (== state 14) (== (backSound prevSignal?) -1))
			(self cue:)
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 3))
			(1 0)
			(2
				(if (> selfDestructTimer 30)
					(HandsOff)
					(ego stopUpd:)
					(backSound number: 301 loop: 1 flags: 1 play:)
					(theMusic2 fade: 30 25 10 0)
					(theMusic fade: 30 25 10 0)
					(= scientistCameIn TRUE)
					(soundFx number: 311 loop: 1 play:)
					(rightDoor setCycle: EndLoop self)
				else
					(self dispose:)
				)
			)
			(3 (Face ego rightDoor self))
			(4 (= cycles 2))
			(5
				(ego moveHead: 0)
				(rightDoor stopUpd:)
				(Print 3 2)
				(scientist
					posn: 313 88
					show:
					setLoop: 4
					setCycle: Forward
					setStep: 3 3
					setMotion: MoveTo 224 88 self
				)
			)
			(6
				(soundFx number: 311 loop: 1 play:)
				(rightDoor setCycle: BegLoop rightDoor)
				(Print 3 3)
				(Face ego scientist)
				(scientist setLoop: 0 cel: 0 setCycle: CycleTo 2 1 self)
			)
			(7
				(soundFx number: 522 loop: 1 play:)
				(scientist setLoop: 0 cel: 3 setCycle: EndLoop self)
			)
			(8
				(theDummy
					init:
					cycleSpeed: (if (<= howFast 1) 8 else 17)
					posn: (scientist x?) (scientist y?)
					setPri: (+ (scientist priority?) 1)
					setCycle: EndLoop self
				)
			)
			(9
				(theDummy dispose:)
				(scientist
					cel: 0
					setLoop: 2
					cycleSpeed: 20
					moveSpeed: 20
					setCycle: EndLoop self
				)
			)
			(10 (= cycles 9))
			(11
				(scientist setLoop: 3 setCycle: EndLoop self)
			)
			(12
				(scientist setLoop: 2 cel: 2 setCycle: BegLoop self)
			)
			(13
				(ego moveHead: TRUE)
				(HandsOn)
				((curRoom obstacles?) dispose:)
				(curRoom
					addObstacle:
						((Polygon new:)
							type: PBarredAccess
							init:
								0 0
								319 0
								319 83
								253 83
								252 90
								237 93
								203 91
								189 84
								175 84
								173 78
								166 70
								147 70
								160 79
								163 92
								136 92
								122 81
								118 68
								99 68
								74 76
								63 84
								0 84
							yourself:
						)
						((Polygon new:)
							type: PBarredAccess
							init:
								0 93
								56 93
								31 122
								31 144
								61 171
								156 185
								253 170
								282 147
								281 121
								260 94
								319 92
								319 189
								0 189
							yourself:
						)
				)
				(= cycles 4)
			)
			(14 0)
			(15
				(scientist stopUpd:)
				(backSound
					number: 340
					loop: -1
					flags: mNOPAUSE
					play: 0
					fade: 127 25 10 0
				)
				(theMusic2 fade: 127 25 10 0)
				(theMusic fade: 127 25 10 0)
				(self dispose:)
			)
		)
	)
)

(instance talkScientist of Script
	(properties)
	
	(method (changeState newState &tmp [str 130])
		(switch (= state newState)
			(0
				(HandsOff)
				(theMusic2 fade: 30 25 10 0)
				(theMusic fade: 30 25 10 0)
				(backSound number: 354 loop: -1 flags: mNOPAUSE play:)
				(Face ego scientist)
				(scientist setLoop: 2 cel: 0 setCycle: EndLoop self)
			)
			(1
				(scientist setLoop: 3 setCycle: Forward)
				(= cycles (if (<= howFast 1) 15 else 30))
			)
			(2
				(Print 3 4)
				(sciTalker
					init: sciBust sciEyes sciMouth
					say:
						{"The Star Generator is in danger! The Arcada is under attack! You'd better get off this scow if you value your life, Wilco."}
						0 0 0
						self
				)
			)
			(3
				(Format @str 3 5
					(switch ((ScriptID ARCADA 0) cartNumber?)
						(1 {Black Holes})
						(2 {Stars})
						(3 {Magnetic Fields})
						(4 {Van Allen Belts})
						(5 {Astral Bodies})
						(6 {Quasars})
						(7 {Binary Systems})
						(8 {Orbits})
						(9 {Galaxies})
						(10 {Solar Flares})
						(11 {Moons})
						(12 {Plantary Formations})
						(13 {Constellations})
						(14 {Quadrants})
						(15 {Asteroids})
						(16 {Pulsars})
						(17 {Gravity})
						(18 {Meteors})
						(19 {Solar Systems})
						(20 {Warp Fields})
					)
				)
				(sciTalker say: @str 0 0 1 self)
				(SolvePuzzle 2 fLearnCartridgeName)
			)
			(4
				(scientist setLoop: 2 setCel: 2 setCycle: BegLoop self)
			)
			(5
				(backSound fade:)
				(= seconds (if (<= howFast 1) 2 else 3))
			)
			(6
				(HandsOn)
				(scientist stopUpd:)
				(Bset fScientistDead)
				(backSound
					number: 340
					loop: -1
					flags: mNOPAUSE
					play: 0
					fade: 127 25 10 0
				)
				(theMusic2 fade: 127 25 10 0)
				(theMusic fade: 127 25 10 0)
				(self dispose:)
			)
		)
	)
)

(instance sitDown of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego view: 7 setLoop: 0 cel: 0 setCycle: EndLoop self)
			)
			(1 (curRoom newRoom: 103))
		)
	)
)

(instance riseFromComputer of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if (Btst fFlag9)
					(ego
						setLoop: 1
						cycleSpeed: 5
						moveSpeed: 5
						cel: 0
						setCycle: Oscillate 1 self
					)
				else
					(= cycles 3)
				)
			)
			(1
				(ego setLoop: 2 cel: 0 setCycle: EndLoop self)
			)
			(2
				(ego
					view: 0
					setLoop: -1
					setCycle: StopWalk 60
					setMotion: MoveTo (ego x?) (+ (ego y?) 10) self
				)
			)
			(3
				(HandsOn)
				(NormalEgo 2 0 60)
				(Bclr fFlag9)
				(self dispose:)
			)
		)
	)
)

(instance goGetCart of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(theMusic2 number: 302 loop: -1 play:)
				(= droidMoving TRUE)
				(droid setCycle: CycleTo 1 -1 setMotion: MoveTo 108 21 self)
			)
			(1
				(droid
					setLoop: 4
					setPri: (+ (ego priority?) 1)
					setCycle: CycleTo 4 1 self
				)
			)
			(2
				(soundFx number: 303 loop: 1 play:)
				(droid setCycle: EndLoop self)
			)
			(3
				(droid setMotion: DPath 102 51 132 64 183 64 self)
			)
			(4
				(theMusic2 number: 353 loop: -1 play:)
				(droid setCycle: CycleTo 9 -1 self)
			)
			(5 (curRoom newRoom: 103))
		)
	)
)

(instance putCartAway of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				((ScriptID ARCADA 0)
					rFlag1: (& ((ScriptID ARCADA 0) rFlag1?) (~ rFCartReadyToTake))
				)
				(droid
					setLoop: 4
					cel: 8
					setMotion: DPath 132 64 102 51 108 21 self
				)
				(= droidMoving 1)
			)
			(1
				(droid setCycle: CycleTo 5 -1 self)
			)
			(2
				(soundFx number: 303 loop: 1 play:)
				(droid setCycle: BegLoop self)
			)
			(3
				(droid
					setLoop: 3
					cel: 1
					setCycle: CycleTo 5 1
					setPri: -1
					setMotion: MoveTo 170 37 self
				)
				(= droidMoving FALSE)
			)
			(4
				(droid cel: 5 setCycle: CycleTo 3 -1 self)
			)
			(5
				(client setScript: riseFromComputer)
			)
		)
	)
)

(instance theDummy of Prop
	(properties
		description {scientist}
		sightAngle 45
		lookStr {The old scientist doesn't appear healthy. In fact, his small intestine is about to unwind onto the floor.}
		view 401
		loop 1
		signal (| ignrAct fixPriOn)
		cycleSpeed 2
	)
	
	(method (doVerb theVerb theItem)
		(if (== currentFloor 1)
			(switch theVerb
				(verbUse
					(switch theItem
						(iBuckazoid
							(Print 3 6)
						)
						(else 
							(super doVerb: theVerb theItem)
						)
					)
				)
				(else 
					(super doVerb: theVerb &rest)
				)
			)
		else
			(WrongLevel)
		)
	)
)

(instance leftDoor of Prop
	(properties
		x 15
		y 76
		description {electric door}
		lookStr {This is a door. Perhaps it leads elsewhere.}
		view 103
		loop 1
		cycleSpeed 4
	)
	
	(method (doVerb theVerb)
		(if (== currentFloor 1)
			(super doVerb: theVerb &rest)
		else
			(WrongLevel)
		)
	)
	
	(method (cue)
		(ego setPri: -1)
		(self setPri: -1 stopUpd:)
	)
)

(instance rightDoor of Prop
	(properties
		x 298
		y 77
		description {electric door}
		lookStr {It's an automatic door.}
		view 103
		loop 2
		cycleSpeed 4
	)
	
	(method (doVerb theVerb)
		(if (== currentFloor 1)
			(super doVerb: theVerb &rest)
		else
			(WrongLevel)
		)
	)
	
	(method (cue)
		(ego setPri: -1)
		(self setPri: -1 stopUpd:)
	)
)

(instance rightPanelLights of Prop
	(properties
		x 245
		y 48
		description {lights}
		lookStr {These panel lights are here just for decoration and don't actually have anything to do with the Data Archive Unit.}
		view 103
		loop 6
		signal ignrAct
		cycleSpeed 8
		detailLevel 2
	)
	
	(method (doVerb theVerb)
		(if (== currentFloor 1)
			(super doVerb: theVerb &rest)
		else
			(WrongLevel)
		)
	)
)

(instance leftPanelLights of Prop
	(properties
		x 104
		y 39
		description {lights}
		lookStr {These lights have something to do with the Data Archive Unit, though you don't really know what. They do seem to indicate that the unit is still operational.}
		view 103
		loop 7
		signal ignrAct
		cycleSpeed 12
		detailLevel 2
	)
	
	(method (doVerb theVerb)
		(if (== currentFloor 1)
			(super doVerb: theVerb &rest)
		else
			(WrongLevel)
		)
	)
)

(instance scientist of Actor
	(properties
		description {scientist}
		sightAngle 45
		approachX 236
		approachY 103
		view 401
		cycleSpeed 7
		moveSpeed 7
	)
	
	(method (init)
		(super init: &rest)
		(if (== currentFloor 1) (self approachVerbs: verbLook verbTalk verbDo verbUse))
	)
	
	(method (doVerb theVerb theItem)
		(if (== currentFloor 1)
			(switch theVerb
				(verbLook
					(cond 
						((Btst fScientistDead) (Print 3 7))
						(lookedAtScientist (Print 3 8))
						(else (Print 3 9) (= lookedAtScientist TRUE))
					)
				)
				(verbDo
					(if (Btst fScientistDead)
						(Print 3 10)
					else
						(curRoom setScript: talkScientist)
					)
				)
				(verbUse
					(switch theItem
						(iBuckazoid
							(Print 3 6)
						)
						(iCartridge
							(Print 3 11)
						)
						(iKeyCard
							(Print 3 12)
						)
						(iWidget
							(Print 3 13)
						)
						(iGadget
							(Print 3 14)
						)
						(else 
							(super doVerb: theVerb theItem)
						)
					)
				)
				(verbTalk
					(if (Btst fScientistDead)
						(Print 3 15)
					else
						(curRoom setScript: talkScientist)
					)
				)
				(verbSmell
					(if (Btst fScientistDead)
						(Print 3 16)
					else
						(Print 3 17)
					)
				)
				(verbTaste
					(Print 3 18)
				)
				(else 
					(super doVerb: theVerb theItem &rest)
				)
			)
		else
			(WrongLevel)
		)
	)
)

(instance droid of Actor
	(properties
		x 170
		y 37
		description {data retrieval droid}
		lookStr {data retrieval droid lookStr}
		view 103
		loop 3
		cel 3
		signal (| ignrHrz fixedLoop)
		cycleSpeed 2
		moveSpeed 2
	)
	
	(method (doit &tmp temp0)
		(super doit:)
		(if (== howFast slow)
			(return)
		else
			(if (or mover droidMoving)
				(= local6 2)
			)
			(if (and (not mover) (== local6 2) (not droidMoving))
				(= local7 0)
				(= local6 (Random 1 0))
			)
			(if (<= (-- local7) 0)
				(= local7 3)
				(= temp0 0)
				(switch local6
					(2
						(if (> z 0)
							(= temp0 (- z 1))
						)
						(if (< z 0)
							(= temp0 (+ z 1))
						)
					)
					(1
						(if (> (= temp0 (+ z 1)) 2)
							(= local6 0)
						)
					)
					(else
						(if (< (= temp0 (- z 1)) -2)
							(= local6 1)
						)
					)
				)
				(self z: temp0)
			)
		)
	)
	
	(method (doVerb theVerb theItem)
		(if (== currentFloor 1)
			(switch theVerb
				(verbLook
					(Printf 3 19
						(if droidHoldingCartridge
							{holding a cartridge}
						else
							{empty}
						)
					)
				)
				(verbDo
					(Print 3 20)
				)
				(verbTalk
					(Print 3 21)
				)
				(verbSmell
					(Print 3 22)
				)
				(verbTaste
					(Print 3 22)
				)
				(verbUse
					(switch theItem
						(iBuckazoid
							(Print 3 23)
						)
						(iCartridge
							(Print 3 24)
						)
						(iKeyCard
							(Print 3 25)
						)
						(iWidget
							(Print 3 26)
						)
						(iGadget
							(Print 3 27)
						)
						(else 
							(super doVerb: theVerb &rest)
						)
					)
				)
				(else 
					(super doVerb: theVerb &rest)
				)
			)
		else
			(WrongLevel)
		)
	)
)

(instance Computer of Feature
	(properties
		x 160
		y 63
		nsTop 41
		nsLeft 124
		nsBottom 86
		nsRight 197
		description {Data retreval console}
		sightAngle 45
		onMeCheck FARCHECK
		approachX 168
		approachY 92
		lookStr {This is the operation console of the Data Archive Unit. There is a CRT and chair.}
	)
	
	(method (init)
		(super init: &rest)
		(if (== currentFloor 1) (self approachVerbs: verbDo verbLook))
	)
	
	(method (doVerb theVerb)
		(if (== currentFloor 1)
			(switch theVerb
				(verbDo
					(curRoom setScript: sitDown)
				)
				(else 
					(super doVerb: theVerb &rest)
				)
			)
		else
			(WrongLevel)
		)
	)
)

(instance cartridgeSlots of Feature
	(properties
		description {data cartridges}
		sightAngle 45
		onMeCheck $0010
		lookStr {The data cartridges are stored in these secure storage modules.}
	)
	
	(method (doVerb theVerb)
		(if (== currentFloor 1)
			(self
				x: ((User curEvent?) x?)
				y: ((User curEvent?) y?)
			)
			(super doVerb: theVerb &rest)
		else
			(WrongLevel)
		)
	)
)

(instance mainComputer of Feature
	(properties
		description {main computer}
		sightAngle 45
		onMeCheck $0040
		lookStr {The Data Archive System secures its sophisticated electronic information in cartridges housed in these storage modules.}
	)
	
	(method (doVerb theVerb)
		(if (== currentFloor 1)
			(self
				x: ((User curEvent?) x?)
				y: ((User curEvent?) y?)
			)
			(super doVerb: theVerb &rest)
		else
			(WrongLevel)
		)
	)
)

(instance lights of Feature
	(properties
		description {lights}
		sightAngle 45
		onMeCheck $0080
		lookStr {These things hanging from the ceiling, which look like blowfish-on-a-rope, are actually lighting fixtures for the operation console.}
	)
	
	(method (doVerb theVerb)
		(if (== currentFloor 1)
			(self
				x: ((User curEvent?) x?)
				y: ((User curEvent?) y?)
			)
			(super doVerb: theVerb &rest)
		else
			(WrongLevel)
		)
	)
)

(instance holes of Feature
	(properties
		description {holes}
		sightAngle 45
		onMeCheck $0100
		lookStr {Through the metallic lattice you glimpse still another hallway below.}
	)
	
	(method (doVerb theVerb)
		(self
			x: ((User curEvent?) x?)
			y: ((User curEvent?) y?)
		)
		(switch theVerb
			(verbLook
				(if (== currentFloor 1)
					(super doVerb: theVerb &rest)
				else
					(Print 3 28)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance sciTalker of Talker
	(properties
		x 5
		y 29
		nsTop 99
		nsLeft 212
		view 503
	)
)

(instance sciBust of View
	(properties
		view 503
		cel 1
	)
)

(instance sciEyes of Prop
	(properties
		nsTop 27
		nsLeft 16
		view 503
		loop 1
		cycleSpeed 12
	)
)

(instance sciMouth of Prop
	(properties
		nsTop 44
		nsLeft 24
		view 503
		loop 2
		cycleSpeed 50
	)
)

(instance backSound of Sound)
