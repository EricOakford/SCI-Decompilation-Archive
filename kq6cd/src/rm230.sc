;;; Sierra Script 1.0 - (do not remove this comment)
(script# 230)
(include sci.sh)
(use Main)
(use KQ6Print)
(use KQ6Room)
(use CartoonScript)
(use Kq6Procs)
(use Inset)
(use Scaler)
(use PolyPath)
(use Polygon)
(use Feature)
(use DPath)
(use Motion)
(use Actor)
(use System)

(public
	rm230 0
)

(local
	local0
	local1
)
(instance rm230 of KQ6Room
	(properties
		noun 3
		picture 230
		horizon 0
		north 220
	)
	
	(method (init)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: 2
					init:
						0
						0
						319
						0
						319
						81
						250
						81
						250
						63
						232
						62
						232
						73
						202
						79
						165
						97
						112
						126
						105
						133
						62
						148
						47
						148
						0
						156
					yourself:
				)
				((Polygon new:)
					type: 2
					init: 171 115 250 115 236 126 182 126
					yourself:
				)
				((Polygon new:)
					type: 2
					init: 161 182 168 187 192 180 319 165 319 189 0 189 0 170 64 182
					yourself:
				)
				((Polygon new:)
					type: 1
					init: 325 90 325 158 317 158 317 90
					yourself:
				)
		)
		(super init: &rest)
		(ego init: setScale: Scaler 100 58 140 58)
		(self setScript: enterRoomScr)
		(castleWall init:)
		(vine init:)
		(bush init:)
		(if (>= (theGame _detailLevel?) 2)
			(bush setScript: (ScriptID 13 0))
		)
		(if (Btst 23)
			(magicDoor init:)
			(if (Btst 24) (magicDoor view: 233 loop: 8 cel: 0))
		)
		(if (== ((inventory at: 18) owner?) curRoomNum)
			(holeOnWall init:)
		)
		(genericFeatures init:)
		((ScriptID 10 4) onMeCheck: 136 init:)
		(theGlobalSound number: 917 loop: -1 play:)
	)
	
	(method (doit &tmp temp0)
		(= temp0 (ego onControl: 1))
		(cond 
			(script 0)
			((& temp0 $4000) (curRoom setScript: exitRoomScr))
			(
			(and (& temp0 $0080) (not local0) (ego isStopped:)) (= local0 1) (messager say: noun 3 12))
			(
			(and local0 (& temp0 $0080) (not (ego isStopped:))) (= local0 0))
		)
		(super doit: &rest)
	)
	
	(method (dispose)
		(theGlobalSound fade:)
		(super dispose:)
		(DisposeScript 964)
		(DisposeScript 13)
		(DisposeScript 231)
	)
	
	(method (notify)
		(curRoom setScript: enchantDoorScr)
	)
	
	(method (edgeToRoom param1)
		(return
			(if (== param1 2)
				(ego x: (- (ego x?) 1))
				(messager say: 3 3 15 1)
				(return 0)
			else
				(super edgeToRoom: param1 &rest)
			)
		)
	)
)

(instance holeInset of Inset
	(properties
		view 487
		x 133
		y 101
		disposeNotOnMe 1
		noun 12
	)
	
	(method (init)
		(theGlobalSound fade:)
		(super init: &rest)
		(wallView init:)
		(theGame handsOn:)
		(theIconBar disable: 0 1 3 4 5 6)
		(insetView signal: (| (insetView signal?) $1000))
		(if (not (guardWalkByScr register?))
			(self setScript: guardWalkByScr)
		)
		(theMusic number: 704 loop: -1 play:)
	)
	
	(method (dispose)
		(theMusic fade:)
		(theIconBar enable: 6)
		(theGame handsOff:)
		(super dispose:)
		(theGlobalSound number: 917 loop: -1 play:)
	)
)

(instance enterRoomScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego
					setScale:
					scaleX: 83
					scaleY: 83
					setPri: 3
					setSpeed: 6
					posn: 223 88
					setMotion: MoveTo 238 88 self
				)
			)
			(1
				(ego setMotion: MoveTo 250 84 self)
			)
			(2
				(ego setLoop: 2 setMotion: MoveTo 244 71 self)
			)
			(3
				(ego
					setScale: Scaler 100 58 140 58
					setPri: -1
					setLoop: -1
					setMotion: MoveTo 228 77 self
				)
			)
			(4
				(ego reset: 2)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance exitRoomScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setSpeed: 6 setMotion: MoveTo 244 71 self)
			)
			(1
				(ego
					setScale:
					scaleX: 83
					scaleY: 83
					setPri: 3
					setLoop: 3
					setScale:
					setMotion: MoveTo 250 84 self
				)
			)
			(2
				(ego setLoop: -1 setMotion: DPath 238 88 223 88 self)
			)
			(3
				(curRoom newRoom: (curRoom north?))
			)
		)
	)
)

(instance climbVineScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego
					normal: 0
					setSpeed: 6
					view: 231
					posn: 204 82
					loop: 0
					cel: 0
				)
				(= cycles 2)
			)
			(1 (ego setCycle: EndLoop self))
			(2
				(ego reset: 1 posn: 200 81)
				(= ticks 10)
			)
			(3
				(messager say: 13 5 0 0 self)
			)
			(4
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance paintWallScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				((inventory at: 3) cue:)
				(messager say: 4 44 8 1 self)
			)
			(1
				(ego
					normal: 0
					posn: 136 113
					view: 233
					loop: 0
					cycleSpeed: 10
					setScale: 0
					setCycle: EndLoop self
				)
			)
			(2
				(soundFx2 number: 230 y: 1 play:)
				(ego loop: 1 cel: 0 setCycle: CycleTo 2 1 self)
			)
			(3 (= cycles 2))
			(4
				(soundFx2 number: 230 loop: 1 play:)
				(ego setCycle: CycleTo 4 1 self)
			)
			(5 (= cycles 2))
			(6
				(soundFx2 number: 230 loop: 1 play:)
				(ego setCycle: EndLoop self)
			)
			(7
				(if (not (-- register))
					(ego cel: 2)
					(= state (- state 3))
				)
				(= cycles 2)
			)
			(8
				(soundFx2 number: 230 y: 1 play:)
				(ego loop: 2 cel: 0 setCycle: CycleTo 4 1 self)
			)
			(9
				(soundFx2 number: 230 loop: 1 play:)
				(ego setCycle: CycleTo 8 1 self)
			)
			(10
				(soundFx2 number: 230 loop: 1 play:)
				(ego setCycle: EndLoop self)
			)
			(11
				(if (not (-- register))
					(ego cel: 4)
					(= state (- state 3))
				)
				(= cycles 2)
			)
			(12
				(ego loop: 3 cel: 0 setCycle: EndLoop self)
			)
			(13
				(soundFx2 number: 230 y: 1 play:)
				(ego loop: 4 cel: 0 setCycle: CycleTo 2 1 self)
			)
			(14 (= cycles 2))
			(15
				(soundFx2 number: 230 loop: 1 play:)
				(ego setCycle: CycleTo 4 1 self)
			)
			(16 (= cycles 2))
			(17
				(soundFx2 number: 230 loop: 1 play:)
				(ego setCycle: EndLoop self)
			)
			(18
				(if (not (-- register))
					(ego cel: 2)
					(= state (- state 3))
				)
				(= cycles 2)
			)
			(19
				(ego loop: 5 cel: 0 setCycle: EndLoop self)
			)
			(20
				(magicDoor init:)
				(ego
					reset: 7
					posn: 148 107
					setScale: Scaler 100 58 140 58
				)
				(= cycles 2)
			)
			(21
				(theGame givePoints: 1)
				(= ticks 20)
			)
			(22
				(messager say: 4 44 8 2 self)
			)
			(23
				(Bset 23)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance enchantDoorScr of CartoonScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if (or (!= (ego x?) 147) (!= (ego y?) 115))
					(ego setMotion: PolyPath 147 115 self)
				else
					(= cycles 2)
				)
			)
			(1
				(messager say: 1 0 13 1 self)
			)
			(2 (= cycles 2))
			(3
				(KQ6Print
					posn: -1 10
					font: userFont
					say: 0 1 0 13 2
					modeless: 1
					ticks: 20
					init:
				)
				(= cycles 2)
			)
			(4
				(ego
					normal: 0
					setSpeed: 6
					view: 234
					loop: 0
					cel: 0
					setScale: 0
					setCycle: EndLoop self
				)
			)
			(5 (= register 60) (self cue:))
			(6 (= ticks register))
			(7
				(ego cel: 0 loop: 1 setCycle: EndLoop self)
				(= register (- register 15))
				(if (!= (++ local1) 4) (= state (- state 2)))
			)
			(8 (= ticks 45))
			(9
				(ego cycleSpeed: 9 loop: 2 cel: 0 setCycle: EndLoop self)
			)
			(10 (= ticks 35))
			(11
				(magicDoor loop: 1 cel: 0 setCycle: EndLoop self)
			)
			(12
				(magicDoor loop: 2 cel: 0 setCycle: EndLoop self)
			)
			(13
				(magicDoor loop: 3 setCycle: EndLoop self)
				(ego reset: 7 setScale: Scaler 100 58 140 58)
			)
			(14
				(soundFx2 number: 231 loop: 1 play:)
				(magicDoor loop: 4 setCycle: EndLoop self)
			)
			(15
				(magicDoor view: 233 loop: 8 cel: 0)
				(= cycles 1)
			)
			(16
				(if modelessDialog
					(modelessDialog dispose:)
					(= cycles 1)
				else
					(self cue:)
				)
			)
			(17 (= cycles 20))
			(18
				(messager say: 1 0 13 3 self)
			)
			(19
				(Bset 24)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance openDoorScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: PolyPath 148 111 self)
			)
			(1 (ego setHeading: 270 self))
			(2 (= cycles 2))
			(3
				(messager say: 6 5 11 1 self)
			)
			(4
				(ego
					setSpeed: 6
					setPri: 14
					normal: 0
					view: 233
					loop: 9
					cel: 0
					posn: 136 113
					setScale: 0
				)
				(= cycles 2)
			)
			(5
				(soundFx2 number: 901 loop: 1 play: self)
				(ego setCycle: CycleTo 2 1 self)
				(magicDoor hide:)
			)
			(6
				(magicDoor setPri: -1 show: cel: 1)
				(ego cel: 3)
				(= cycles 2)
			)
			(7)
			(8
				(messager say: 6 5 11 2 self)
			)
			(9
				(theGame givePoints: 2)
				(ego setCycle: EndLoop self)
			)
			(10 (curRoom newRoom: 710))
		)
	)
)

(instance lookHoleScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(messager say: 5 1 0 0 self)
			)
			(1
				(ego
					normal: 0
					setSpeed: 6
					view: 232
					loop: 4
					cel: 0
					posn: 89 140
					setCycle: EndLoop self
				)
			)
			(2
				(holeInset init: self curRoom)
			)
			(3 (= cycles 3))
			(4
				(ego loop: 4 setCycle: BegLoop self)
			)
			(5
				(ego posn: 97 140 reset: 7)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance placeHoleScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego put: 18 curRoomNum)
				(theGame handsOff:)
				(ego
					normal: 0
					posn: 84 140
					setSpeed: 6
					view: 232
					loop: 1
					cel: 0
					setCycle: CycleTo 5 1 self
				)
			)
			(1
				(holeOnWall init:)
				(ego setCycle: EndLoop self)
			)
			(2
				(ego posn: 94 139 reset: 7)
				(= cycles 2)
			)
			(3
				(messager say: 4 25 0 0 self)
			)
			(4
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance removeHoleScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego get: 18)
				(ego
					normal: 0
					posn: 84 140
					setSpeed: 6
					view: 232
					loop: 1
					cel: 6
					setCycle: CycleTo 5 -1 self
				)
			)
			(1
				(holeOnWall dispose:)
				(ego setCycle: BegLoop self)
			)
			(2 (= cycles 2))
			(3
				(ego posn: 94 139 reset: 7)
				(= cycles 2)
			)
			(4 (messager say: 5 5 0 0 self))
			(5
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance guardWalkByScr of Script
	(properties)
	
	(method (dispose)
		(super dispose:)
		(if (cast contains: guardDog) (guardDog dispose:))
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= register 1)
				(= seconds (Random 5 15))
			)
			(1
				(guardDog init: setMotion: MoveTo 190 137 self)
			)
			(2 (self dispose:))
		)
	)
)

(instance bush of Prop
	(properties
		x 18
		y 134
		noun 9
		view 230
		priority 15
		signal $5010
	)
)

(instance magicDoor of Prop
	(properties
		x 136
		y 113
		noun 6
		sightAngle 15
		approachX 147
		approachY 115
		view 235
		priority 1
		signal $4011
	)
	
	(method (init)
		(self approachVerbs: 28)
		(super init: &rest)
	)
	
	(method (doVerb theVerb)
		(cond 
			((== theVerb 5)
				(if (Btst 24)
					(curRoom setScript: openDoorScr)
				else
					(messager say: noun 5 10)
				)
			)
			((OneOf theVerb 1 2) (messager say: noun theVerb (if (Btst 24) 11 else 10)))
			((== theVerb 28)
				(if (Btst 24)
					(messager say: noun theVerb 11)
				else
					(KQ6Print say: 0 6 28 10 1 init:)
					(curRoom setScript: (ScriptID 190))
				)
			)
			((== theVerb 29) (messager say: 4 theVerb 5))
			((== theVerb 44) (messager say: noun theVerb))
			(else (messager say: noun 0 (if (Btst 24) 11 else 10)))
		)
	)
	
	(method (onMe event &tmp temp0)
		(if
			(and
				(= temp0 (super onMe: event))
				(or
					(and (== (event message?) JOY_DOWN) (Btst 24))
					(and (== (event message?) 28) (not (Btst 24)))
				)
			)
			(= _approachVerbs (approachCode doit: (event message?)))
		)
		(return temp0)
	)
)

(instance guardDog of Actor
	(properties
		x 78
		y 137
		noun 15
		view 726
		priority 13
		signal $4010
	)
	
	(method (init)
		(super init: &rest)
		(self setScale: setCycle: Walk)
	)
)

(instance holeOnWall of View
	(properties
		x 75
		y 141
		z 47
		noun 5
		approachX 92
		approachY 140
		view 482
		loop 1
		signal $0011
	)
	
	(method (init)
		(super init: &rest)
		(self approachVerbs: 5 1)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(curRoom setScript: lookHoleScr)
			)
			(5
				(curRoom setScript: removeHoleScr)
			)
			(else  (super doVerb: theVerb))
		)
	)
	
	(method (onMe event &tmp temp0)
		(if (= temp0 (super onMe: event &rest))
			(switch (event message?)
				(JOY_DOWN (== approachX 97))
				(JOY_UP (== approachY 94))
			)
		)
		(return temp0)
	)
)

(instance wallView of View
	(properties
		x 141
		y 96
		noun 12
		view 230
		loop 1
		priority 12
		signal $0011
	)
)

(instance castleWall of Feature
	(properties
		noun 4
		sightAngle 45
		onMeCheck $0002
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(44
				(cond 
					((Btst 23) (messager say: noun theVerb 5))
					((not (Btst 22))
						(if (or (Btst 58) (Btst 68))
							(messager say: noun theVerb 6)
						else
							(messager say: noun theVerb 9)
						)
					)
					((not (ego has: 3)) (messager say: noun 44 7))
					(else (curRoom setScript: paintWallScr))
				)
			)
			(29
				(cond 
					((Btst 23) (messager say: noun theVerb 5))
					((Btst 22) (self doVerb: 44 &rest))
					(else (messager say: noun theVerb 6))
				)
			)
			(25
				(curRoom setScript: placeHoleScr)
			)
			(1
				(messager
					say:
						noun
						theVerb
						(cond 
							((not (Btst 23)) 0)
							((Btst 24) 11)
							(else 10)
						)
				)
			)
			(28
				(if (not (Btst 23))
					(messager say: noun theVerb 4)
				else
					(magicDoor doVerb: theVerb)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
	
	(method (onMe event &tmp temp0)
		(if (= temp0 (super onMe: event &rest))
			(if
				(or
					(== (event message?) 25)
					(and
						(OneOf (event message?) 44 29)
						(Btst 22)
						(ego has: 3)
						(ego has: 46)
					)
				)
				(self _approachVerbs: (| $8000 (self _approachVerbs?)))
				(if (== (event message?) 25)
					(= approachX 94)
					(= approachY 140)
					(= x 70)
					(= y 118)
					(return temp0)
				)
			else
				(self _approachVerbs: (& $7fff (self _approachVerbs?)))
			)
		)
		(if (!= approachX 147)
			(= approachX 147)
			(= approachY 115)
			(= x 136)
			(= y 105)
		)
		(return temp0)
	)
)

(instance genericFeatures of Feature
	(properties)
	
	(method (onMe event)
		(= noun
			(switch (OnControl 4 (event x?) (event y?))
				(4 9)
				(16 8)
				(32 11)
				(else  0)
			)
		)
	)
)

(instance vine of Feature
	(properties
		noun 13
		onMeCheck $0100
		approachX 200
		approachY 81
		_approachVerbs 8
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(5
				(curRoom setScript: climbVineScr)
			)
			(else  (super doVerb: theVerb))
		)
	)
)
