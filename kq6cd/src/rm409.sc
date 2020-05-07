;;; Sierra Script 1.0 - (do not remove this comment)
(script# 409)
(include sci.sh)
(use Main)
(use rLab)
(use n404)
(use NewRoomCue)
(use Kq6Procs)
(use RandCyc)
(use PolyPath)
(use Polygon)
(use Motion)
(use Actor)
(use System)

(public
	rm409 0
)

(local
	local0
	local1
)
(instance rm409 of KQ6Room
	(properties
		modNum 400
		noun 2
		picture 400
		style $000a
		horizon 135
		south 400
		walkOffEdge 1
		autoLoad 0
	)
	
	(method (init)
		(if (rLab hiddenDoorOpen?)
			(= local0 54)
			(curRoom
				addObstacle:
					((Polygon new:)
						type: 2
						init:
							232
							144
							83
							144
							26
							185
							130
							185
							130
							189
							0
							189
							0
							0
							319
							4
							319
							171
							238
							171
							242
							154
						yourself:
					)
					((Polygon new:)
						type: 2
						init: 319 189 192 189 192 184 319 184
						yourself:
					)
			)
		else
			(if (rLab seenSecretLatch?)
				(= local0 53)
			else
				(= local0 55)
			)
			(curRoom
				addObstacle:
					((Polygon new:)
						type: 2
						init:
							0
							189
							0
							0
							319
							0
							319
							189
							190
							189
							190
							185
							266
							185
							237
							161
							235
							143
							86
							143
							39
							185
							130
							185
							130
							189
						yourself:
					)
			)
		)
		(super init: &rest)
		(if (== ((ScriptID 30 0) holeCoords?) curRoomNum)
			(proc404_1)
		)
		((ScriptID 30 0) initCrypt: 1)
		(tapestry init:)
		(openDoor init:)
		(door init:)
		(myTorch init:)
		(self setScript: walkIn)
	)
	
	(method (doit)
		(cond 
			((curRoom script?))
			((ego inRect: 287 167 319 189)
				((ScriptID 30 0) prevEdgeHit: 2)
				(curRoom setScript: walkOut)
			)
			((== (ego edgeHit?) 3)
				((ScriptID 30 0) prevEdgeHit: 3)
				(curRoom setScript: walkOut)
			)
		)
		(super doit: &rest)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(if (rLab hiddenDoorOpen?)
					(messager say: 2 1 54 1 0 400)
					1
				else
					(messager say: 2 1 53 1 0 400)
					1
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance tapestry of Prop
	(properties
		x 246
		y 189
		z 100
		noun 18
		modNum 400
		view 400
		loop 5
		priority 5
		signal $6810
	)
	
	(method (init)
		(self stopUpd:)
		(super init:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(0
				(if (rLab seenSecretLatch?)
					0
				else
					(messager say: 19 0 55 1 0 400)
				)
			)
			(5
				(if (rLab hiddenDoorOpen?)
					(curRoom setScript: lookAtTapestry)
				else
					(curRoom setScript: liftTapestry)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance door of Prop
	(properties
		x 278
		y 189
		z 73
		noun 19
		modNum 400
		approachX 260
		approachY 170
		view 402
		loop 3
		priority 13
		signal $4010
	)
	
	(method (init)
		(self approachVerbs: 5)
		(if (rLab hiddenDoorOpen?)
			(self cel: 5 stopUpd:)
		else
			(self cel: 0 stopUpd:)
		)
		(super init:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(25 (proc404_0 2))
			(1
				(messager say: 19 1 local0 1 0 400)
			)
			(5
				(messager say: 19 5 local0 1 0 400)
			)
			(2
				(cond 
					((not (rLab seenSecretLatch?)) (messager say: 19 2 55 1 0 400))
					(
						(and
							(rLab seenSecretLatch?)
							(not (rLab hiddenDoorOpen?))
						)
						(messager say: 19 2 53 1 0 400)
					)
					((and (rLab hiddenDoorOpen?) (not (Btst 1))) (messager say: 19 2 56 1 0 400))
					((Btst 1) (messager say: 19 2 57 1 0 400))
				)
			)
			(else 
				(cond 
					((not (rLab seenSecretLatch?)) (messager say: 19 0 55 1 0 400))
					(
						(and
							(rLab seenSecretLatch?)
							(not (rLab hiddenDoorOpen?))
						)
						(messager say: 19 0 53 1 0 400)
					)
					(else (messager say: 19 0 54 1 0 400))
				)
			)
		)
	)
)

(instance openDoor of View
	(properties
		x 278
		y 189
		z 73
		noun 19
		modNum 400
		approachX 260
		approachY 170
		view 402
		loop 2
		priority 13
		signal $4010
	)
	
	(method (init)
		(self approachVerbs: 5)
		(self stopUpd:)
		(super init:)
	)
	
	(method (doVerb theVerb)
		(door doVerb: theVerb &rest)
	)
)

(instance myTorch of View
	(properties
		x 77
		y 141
		z 71
		noun 9
		modNum 400
		view 400
		loop 8
		priority 14
		signal $0011
	)
	
	(method (init)
		(self stopUpd:)
		(super init:)
		(myFlame init:)
		(myFlick init:)
	)
)

(instance myFlame of Prop
	(properties
		x 84
		y 141
		z 95
		noun 9
		modNum 400
		view 400
		loop 2
	)
	
	(method (init)
		(self setCycle: Fwd checkDetail:)
		(super init:)
	)
)

(instance myFlick of Prop
	(properties
		x 82
		y 50
		modNum 400
		onMeCheck $0000
		view 400
		loop 6
		signal $4011
		cycleSpeed 9
	)
	
	(method (init)
		(self setCycle: RandCycle checkDetail:)
		(super init:)
	)
)

(instance mino of Actor
	(properties
		x 315
		y 171
		yStep 3
		view 443
		signal $4000
		xStep 5
	)
)

(instance walkIn of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(switch ((ScriptID 30 0) prevEdgeHit?)
					(4
						(ego posn: 282 164 init: setMotion: PolyPath 247 164 self)
					)
					(1
						(ego posn: 158 225 init: setMotion: PolyPath 158 187 self)
					)
					(else 
						(ego posn: 160 160 loop: 2 init:)
						(= ticks 6)
					)
				)
			)
			(1 (= cycles 6))
			(2
				(if (and (== prevRoomNum 440) (not (Btst 1)))
					(Bset 142)
					(messager say: 1 0 50 1 self 400)
				else
					(self cue:)
				)
			)
			(3
				(theGame handsOn:)
				(theIconBar enable: 6)
				(self dispose:)
			)
		)
	)
)

(instance walkOut of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(switch ((ScriptID 30 0) prevEdgeHit?)
					(3
						(ego setMotion: PolyPath (ego x?) 250 self)
					)
					(2
						(ego setMotion: PolyPath 315 (ego y?) self)
					)
				)
			)
			(1
				(switch ((ScriptID 30 0) prevEdgeHit?)
					(3 (curRoom newRoom: 400))
					(2 (curRoom newRoom: 440))
				)
			)
		)
	)
)

(instance lookAtTapestry of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: PolyPath 235 149 self)
			)
			(1
				(ego setHeading: 90)
				(= cycles 6)
			)
			(2
				(messager say: 18 5 54 1 self 400)
			)
			(3
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance liftTapestry of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: PolyPath 229 146 self)
			)
			(1
				(ego setHeading: 90)
				(= cycles 6)
			)
			(2
				(if (rLab seenSecretLatch?)
					(= local1 1)
					(= local0 53)
					(messager say: 18 5 53 1 self 400)
				else
					(self cue:)
				)
			)
			(3
				(ego
					view: 401
					setLoop: 3
					cel: 0
					normal: 0
					illegalBits: 0
					ignoreActors: 1
					cycleSpeed: 12
					posn: (+ (ego x?) 6) (+ (ego y?) 3)
					setCycle: CT 3 1 self
				)
			)
			(4
				(ego cel: 4)
				(tapestry startUpd: cel: 1)
				(= cycles 2)
			)
			(5
				(ego cel: 5)
				(tapestry cel: 2)
				(= cycles 2)
			)
			(6
				(if local1
					(ego
						setLoop: 4
						cel: 0
						posn: (+ (ego x?) 3) (ego y?)
						setCycle: CT 5 1 self
					)
				else
					(self cue:)
				)
			)
			(7
				(if local1 (ego cel: 6) (= cycles 6) else (self cue:))
			)
			(8
				(if local1 (ego cel: 5) (= cycles 6) else (self cue:))
			)
			(9
				(if local1 (ego cel: 4) (= cycles 6) else (self cue:))
			)
			(10
				(if local1 (ego cel: 5) (= cycles 6) else (self cue:))
			)
			(11
				(if local1
					(messager say: 18 5 53 2 self 400)
				else
					(messager say: 18 5 55 1 self 400)
				)
			)
			(12
				(if local1 (ego cel: 4) (= cycles 6) else (self cue:))
			)
			(13
				(if local1 (ego cel: 5) (= cycles 6) else (self cue:))
			)
			(14
				(if local1 (ego cel: 4) (= cycles 6) else (self cue:))
			)
			(15
				(if local1
					(soundFx2 number: 408 setLoop: 1 play:)
					(ego cel: 5)
					(= cycles 6)
				else
					(self cue:)
				)
			)
			(16
				(if local1
					(messager say: 18 5 53 3 self 400)
				else
					(self cue:)
				)
			)
			(17
				(if local1
					(ego cel: 6)
					(tapestry setCycle: Beg self)
				else
					(self cue:)
				)
			)
			(18
				(if local1
					(soundFx2 number: 909 setLoop: 1 play:)
					(door setCycle: End self)
					(rLab hiddenDoorOpen: 1)
					((curRoom obstacles?) dispose:)
					(= local0 54)
				else
					(self cue:)
				)
			)
			(19
				(if local1
					(door dispose:)
					(curRoom
						addObstacle:
							((Polygon new:)
								type: 2
								init:
									232
									144
									83
									144
									26
									185
									130
									185
									130
									189
									0
									189
									0
									0
									319
									4
									319
									171
									238
									171
									242
									154
								yourself:
							)
							((Polygon new:)
								type: 2
								init: 319 189 192 189 192 184 319 184
								yourself:
							)
					)
					(messager say: 18 5 53 4 self 400)
				else
					(self cue:)
				)
			)
			(20
				(if local1
					(theGame givePoints: 1)
					(self cue:)
				else
					(tapestry cel: 1)
					(ego setLoop: 3 cel: 5)
					(= cycles 2)
				)
			)
			(21
				(if local1
					(self cue:)
				else
					(tapestry cel: 0)
					(ego cel: 4)
					(= cycles 2)
				)
			)
			(22
				(if local1 (self cue:) else (ego setCycle: Beg self))
			)
			(23
				(theGame handsOn:)
				(tapestry stopUpd:)
				(if local1
					(ego posn: (- (ego x?) 9) (- (ego y?) 3) reset: 0)
				else
					(ego posn: (- (ego x?) 6) (- (ego y?) 3) reset: 0)
				)
				(self dispose:)
			)
		)
	)
)
