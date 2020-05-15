;;; Sierra Script 1.0 - (do not remove this comment)
(script# rmToilet) ;130
(include game.sh)
(use Main)
(use Intrface)
(use LLRoom)
(use PolyPath)
(use Polygon)
(use Feature)
(use ForCount)
(use LoadMany)
(use Sound)
(use Motion)
(use User)
(use Actor)
(use System)

(public
	rm130 0
)

(local
	graffitiCount
	[local1 2]
	sawRingInSink
)
(instance rm130 of LLRoom
	(properties
		picture rmToilet
	)
	
	(method (init &tmp theForwardCounter)
		(= theForwardCounter ForwardCounter)
		(LoadMany SOUND 130 132 133 134 135 102 801 802 136)
		(soundFx loop: 1 vol: 127 flags: 1)
		(LoadMany VIEW 130 131 132)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: PBarredAccess
					init:
						125 113 95 120 117 165 190 148 190 143 182 143
						182 137 223 137 224 109 194 108 184 114 160 114
						155 110 130 111 130 0 319 0 319 189 0 189 0 0 125 0
					yourself:
				)
		)
		(ego
			posn: 138 113
			observeControl: cWHITE
			init:
			actions: egoActions
		)
		(theEgoHead actions: egoActions)
		(if (CheckItemOwner iRing)
			(theSparkle
				cycleSpeed: howFast
				init:
				approachVerbs: verbDo verbUse verbZipper verbTaste verbTalk
			)
		)
		(toilet init: approachVerbs: verbDo verbUse verbZipper verbTaste)
		(sink init: approachVerbs: verbDo verbUse verbZipper verbTaste verbTalk)
		(graffiti init:)
		(super init:)
		(globalSound number: 136 loop: 1 vol: 127 flags: mNOPAUSE)
		(door
			init:
			setCel: 6
			setScript: sCloseDoor
			approachVerbs: verbDo verbUse verbZipper verbTaste
		)
		(theHandle init: approachVerbs: verbDo verbUse verbZipper verbTaste)
		(faucet init: setScript: sDoDrips)
	)
	
	(method (doit)
		(super doit: &rest)
		(if
			(and
				(== (ego view?) 131)
				(or (== (ego loop?) 0) (== (ego loop?) 1))
				(ego mover?)
			)
			(curRoom setScript: 0)
			(Print 130 0 #at -1 20)
			(Bset fToiletPaperOnShoe)
			(NormalEgo 2)
			(if (!= (CueObj client?) toilet)
				(ego
					setMotion:
						PolyPath
						((CueObj client?) approachX?)
						(+ (ego z?) ((CueObj client?) approachY?))
						CueObj
				)
			else
				(ego
					setMotion: PolyPath ((User curEvent?) x?) ((User curEvent?) y?)
				)
			)
		)
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(Print 130 1)
				(if (CheckItemOwner iRing) (Print 130 2))
			)
			(else 
				(super doVerb: theVerb theItem)
			)
		)
	)
	
	(method (newRoom n)
		(globalSound fade:)
		(super newRoom: n)
	)
)

(instance egoActions of Code	;EO: this was a class, but it is not in the class table
	(properties)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbZipper (toilet doVerb: verbZipper))
		)
	)
)

(instance sTakeCrap of Script
	(properties)
	
	(method (doit)
		(super doit: &rest)
		(if register
			(-- register)
		else
			(= register (Random 30 50))
			(ego setCel: 0 setCycle: ForwardCounter (Random 1 3))
		)
		(if (== (ego cel?) 1) (soundFx number: 132 play:))
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego egoSpeed: view: 131 normal: 0 setLoop: 0 setCel: 2)
				(= register 5)
				(= cycles 3)
			)
			(1
				(Print 130 3 #at -1 20)
				(ego setCycle: EndLoop)
				(= register (Random 20 40))
				(SolvePuzzle fPoopedInCan 1)
				(= seconds 8)
			)
			(2
				(Print 130 4 #at -1 20)
				(= seconds 5)
			)
			(3
				(fartNoise play:)
				(= seconds 4)
			)
			(4 (Print 130 5 #at -1 20))
		)
	)
)

(instance sTakePiss of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego
					egoSpeed:
					view: 131
					setLoop: 3
					setCel: 0
					setCycle: EndLoop self
				)
			)
			(1
				(ego setLoop: 4 setCel: 0 setCycle: Forward)
				(= cycles 8)
			)
			(2
				(soundFx number: 102 setLoop: 1 play: self)
				(Print 130 6 #at -1 20 #dispose)
			)
			(3 (= seconds 3))
			(4
				(if modelessDialog (modelessDialog dispose:))
				(ego setLoop: 3 setCel: 255 setCycle: BegLoop self)
			)
			(5
				(NormalEgo 3)
				(HandsOn)
				(if
					(>
						(GetDistance
							(ego x?)
							(ego y?)
							(toilet approachX?)
							(toilet approachY?)
						)
						5
					)
					(Print 130 7 #at -1 20)
				)
				(self dispose:)
			)
		)
	)
)

(instance sFlood of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(soundFx number: 134 loop: 1 play:)
				(= cycles 20)
			)
			(1
				(Print 130 8 #at -1 20)
				(Print 130 9 #at -1 20)
				(soundFx number: 135 loop: -1 play:)
				(overflow
					init:
					cycleSpeed: (+ 3 howFast)
					setCycle: EndLoop self
				)
				(ego egoSpeed: setHeading: 180)
			)
			(2
				(overflow setLoop: 2 setCycle: Forward)
				(flood init:)
				(= cycles 2)
			)
			(3
				(++ register)
				((flood new:)
					init:
					cel: register
					y: (- (flood y?) (* 7 register))
					stopUpd:
				)
				(if (< register 8) (-- state))
				(= cycles 5)
			)
			(4
				(overflow dispose:)
				(Print 130 10)
				(Print 130 11 #at -1 140)
				(ego
					view: 131
					loop: 5
					x: (+ (ego x?) 10)
					y: (- (ego y?) 20)
					cycleSpeed: 4
					setCycle: Forward
				)
				(= cycles 1)
			)
			(5
				(++ register)
				((flood new:)
					init:
					cel: register
					y: (- (flood y?) (* 7 register))
					stopUpd:
				)
				(if (< register 16) (-- state))
				(= cycles 5)
			)
			(6 (= seconds 3))
			(7
				(ShowDeathIcon 131 7)
				(Format @str1 130 12)
				(EgoDead 130 13)
			)
		)
	)
)

(instance sGetRing of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego
					egoSpeed:
					view: 131
					setLoop: 1
					setCel: 0
					z: 10
					setCycle: EndLoop self
				)
			)
			(1
				(ego loop: 2 setCycle: Forward)
				(= seconds 3)
			)
			(2
				(SolvePuzzle fGetRing 3)
				(ego get: 4 setCel: 255 setLoop: 1 setCycle: BegLoop self)
			)
			(3
				(Print 130 14 #at -1 20)
				(ego z: 0)
				(NormalEgo 0)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance sSparkle of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(client setCycle: EndLoop)
				(= seconds (Random 5 20))
			)
			(1 (self init:))
		)
	)
)

(instance sCloseDoor of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(door setCycle: CycleTo 5 -1 self)
			)
			(1
				(soundFx number: 802 play:)
				(door setCycle: BegLoop self)
			)
			(2 (self dispose:))
		)
	)
)

(instance sDoDrips of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds (Random 2 4)))
			(1 (faucet setCycle: BegLoop self))
			(2
				(globalSound play:)
				(self init:)
			)
		)
	)
)

(instance door of Prop
	(properties
		x 114
		y 112
		description {the door}
		approachX 141
		approachY 112
		view 130
		signal (| ignrAct fixPriOn)
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(HandsOff)
				(soundFx number: 801 play:)
				(self setCycle: EndLoop self)
			)
			(else 
				(super doVerb: theVerb theItem)
			)
		)
	)
	
	(method (cue)
		(super cue:)
		(curRoom newRoom: rmHallway)
	)
)

(instance theSparkle of Prop
	(properties
		x 206
		y 127
		description {the ring}
		approachX 190
		approachY 144
		view 131
		loop 6
		priority 12
		signal (| ignrAct fixPriOn)
	)
	
	(method (doVerb theVerb theItem)
		(sink doVerb: theVerb theItem)
	)
)

(instance overflow of Prop
	(properties
		x 171
		y 97
		view 130
		loop 1
		signal ignrAct
	)
)

(instance flood of View
	(properties
		x 170
		y 146
		view 132
		priority 1
		signal (| ignrAct fixPriOn)
	)
)

(instance faucet of Prop
	(properties
		x 209
		y 129
		description {the sink}
		sightAngle 40
		view 130
		loop 3
		priority 10
		signal (| ignrAct fixPriOn)
	)
	
	(method (cue)
		(super cue:)
		(if (CheckItemOwner iRing)
			(= sawRingInSink TRUE)
			(Print 130 15 #at -1 20)
			(theSparkle setCycle: BegLoop)
		else
			(Print 130 16 #at -1 20)
			(Print 130 17 #at -1 140)
		)
	)
)

(instance toilet of Feature
	(properties
		x 182
		y 91
		nsTop 76
		nsLeft 164
		nsBottom 107
		nsRight 200
		description {the toilet}
		sightAngle 40
		approachX 171
		approachY 111
		lookStr {It's not a pretty sight!}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(if
					(not
						(if (== (ego view?) 131)
							(if (== (ego loop?) 0) else (== (ego loop?) 1))
						)
					)
					(curRoom setScript: sTakeCrap)
				else
					(Print 130 18 #at -1 20)
				)
			)
			(verbTalk (Print 130 19))
			(verbUse
				(switch theItem
					(iBreathSpray (Print 130 20))
					(iWallet (Print 130 21))
					(else 
						(super doVerb: theVerb theItem &rest)
					)
				)
			)
			(verbZipper
				(HandsOff)
				(curRoom setScript: sTakePiss)
			)
			(verbTaste (Print 130 22))
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance sink of Feature
	(properties
		x 202
		y 146
		z 20
		nsTop 119
		nsLeft 185
		nsBottom 137
		nsRight 220
		description {the sink}
		sightAngle 40
		approachX 190
		approachY 144
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(if
					(not
						(if (== (ego view?) 131)
							(if (== (ego loop?) 0) else (== (ego loop?) 1))
						)
					)
					(ego
						setHeading: (GetAngle (ego x?) (ego y?) (self x?) (self y?)) faucet
					)
				else
					(faucet cue:)
				)
			)
			(verbDo
				(cond 
					(
						(and
							(== (ego view?) 131)
							(or (== (ego loop?) 0) (== (ego loop?) 1))
						)
						(Print 130 18)
					)
					(
						(or
							(not (CheckItemOwner iRing))
							(and (CheckItemOwner iRing) (not sawRingInSink))
						)
						(Print 130 23 #at -1 20)
					)
					(else (HandsOff) (curRoom setScript: sGetRing))
				)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance graffiti of Feature
	(properties
		x 193
		y 58
		nsTop 27
		nsLeft 146
		nsBottom 90
		nsRight 240
		description {the walls}
		sightAngle 40
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo (Print 130 24 #at -1 20))
			(verbLook
				(switch graffitiCount
					(0 (Print 130 25 #at -1 140))
					(1 (Print 130 26 #at -1 140))
					(2 (Print 130 27 #at -1 140))
					(3 (Print 130 28 #at -1 140))
					(else 
						(SolvePuzzle fGetPassword 2)
						(Print 130 29 #at -1 140)
						(if (CheckItemOwner iGraffiti) (Print 130 30) (ego get: iGraffiti))
					)
				)
				(++ graffitiCount)
			)
			(verbTaste (Print 130 31 #at -1 20))
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance theHandle of Feature
	(properties
		x 193
		y 109
		z 28
		nsTop 78
		nsLeft 185
		nsBottom 85
		nsRight 201
		description {the flusher}
		sightAngle 40
		approachX 187
		approachY 113
		lookStr {This toilet has been provided with a handle for those customers of Lefty's sophisticated enough to understand its operation.}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(HandsOff)
				(curRoom setScript: sFlood)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance fartNoise of Sound
	(properties
		flags mNOPAUSE
		number 133
	)
)
