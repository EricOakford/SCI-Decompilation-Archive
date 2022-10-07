;;; Sierra Script 1.0 - (do not remove this comment)
(script# 200)
(include sci.sh)
(use Main)
(use KQ6Room)
(use Kq6Procs)
(use Inset)
(use Scaler)
(use PolyPath)
(use Polygon)
(use Feature)
(use MoveFwd)
(use StopWalk)
(use Reverse)
(use Motion)
(use Actor)
(use System)

(public
	rm200 0
)

(local
	local0
	local1
	local2
	[local3 10] = [176 147 238 130 250 130 250 135 189 151]
	[local13 16] = [235 133 243 133 243 139 227 139 189 151 177 146 222 129 235 129]
	local29
	local30
)
(procedure (localproc_095a)
	(if (Btst 11) (Bclr 11) else (Bset 11))
	((curRoom obstacles?) delete: plankPoly)
	(curRoom
		addObstacle:
			(if (Btst 11)
				(chest setPri: -1)
				(plankPoly type: 2 points: @local13 size: 8 yourself:)
			else
				(chest setPri: 1)
				(plankPoly type: 2 points: @local3 size: 5 yourself:)
			)
	)
)

(instance rm200 of KQ6Room
	(properties
		noun 3
		picture 200
		horizon 10
	)
	
	(method (init)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: 2
					init:
						150
						108
						114
						115
						102
						134
						62
						152
						-50
						189
						-50
						-200
						319
						-200
						319
						189
						215
						189
						263
						143
						271
						117
						222
						107
						210
						99
						205
						87
						211
						70
						235
						60
						267
						54
						250
						51
						192
						67
						187
						80
						191
						102
					yourself:
				)
				((Polygon new:)
					type: 2
					init: 214 117 232 117 243 120 243 125 193 125 193 123
					yourself:
				)
				((Polygon new:)
					type: 2
					init: 144 183 144 179 181 169 179 183
					yourself:
				)
		)
		(theIconBar enable:)
		(if (== prevRoomNum 155) (= style 6))
		(super init: &rest)
		(ego ignoreActors: actions: egoDoVerb)
		(genericFeatures init:)
		((ScriptID 10 4) onMeCheck: 32 init:)
		(if (curRoom script?) ((curRoom script?) caller: self))
		(if (OneOf prevRoomNum 105 106 108 99)
			(ego init:)
			(Palette palSET_INTENSITY 0 256 100)
			(self setScript: (ScriptID 201))
			(= currentAct 1)
		else
			(switch prevRoomNum
				(210
					(ego init:)
					(curRoom setScript: enterRoomScr)
				)
				(155
					(ego init: view: 203)
					(theIconBar disable: 6)
					(theGame setCursor: theCursor)
					(self setScript: (ScriptID 202))
				)
				(100
					(ego
						init:
						posn: 175 130
						loop: 2
						setScale: Scaler 100 50 112 57
					)
					(theGame handsOn:)
					(theIconBar curIcon: (theIconBar at: 0))
					(theGame setCursor: ((theIconBar curIcon?) cursor?))
				)
			)
		)
		(bush1 init:)
		(bush2 init:)
		(bush3 init:)
		(shipsails init:)
		(wave init:)
		(if (> (theGame _detailLevel?) 1)
			(wave setScript: waveScr)
			(if (> (theGame _detailLevel?) 2)
				(bush1 setScript: (Clone (ScriptID 13 0)))
				(bush2 setScript: (Clone (ScriptID 13 0)))
				(shipsails setScript: (Clone (ScriptID 13 0)))
				(bush3 setScript: (ScriptID 13 0))
			)
		)
		(if (== currentAct 0) (= currentAct 1))
		(if (== ((inventory at: 39) owner?) 200)
			(royalRing init:)
		)
		(plank init:)
		(shipWheel init:)
		(= local0 0)
		(if (== prevRoomNum 155)
			(theMusic fade:)
		else
			(theMusic number: 915 loop: -1 play:)
		)
		(theGlobalSound number: 916 loop: -1 play:)
		(walkHandler addToFront: self)
		(directionHandler addToFront: self)
	)
	
	(method (doit &tmp temp0)
		(super doit:)
		(cond 
			(
				(and
					(& (= temp0 (ego onControl: 1)) $0100)
					(not local29)
				)
				(= local29 1)
				(plank setPri: 15)
			)
			((and (not (& temp0 $0100)) local29) (= local29 0) (plank setPri: 3))
		)
		(cond 
			(script 0)
			((& temp0 $4000) (curRoom setScript: exitRoomScr))
			((& temp0 $0004)
				(cond 
					((!= local0 1)
						(soundFx number: 922 loop: -1 play:)
						(ego view: 308)
						(if (not ((ego cycler?) isKindOf: StopWalk))
							(ego setCycle: StopWalk -1)
						)
						(if (== local0 0)
							(cond 
								((< (ego heading?) 135) (ego loop: 0))
								((> (ego heading?) 225) (ego loop: 1))
								(else (ego loop: 2))
							)
						)
						(= local0 1)
					)
					((and (ego isStopped:) (not local2) local1) (messager say: 10 3 12 1) (= local2 1))
				)
			)
			((& temp0 $0200)
				(cond 
					((!= local0 2)
						(if (== local0 0) (soundFx number: 922 loop: -1 play:))
						(ego view: 3081)
						(if
							(and
								(not ((ego cycler?) isKindOf: StopWalk))
								(not ((ego cycler?) isKindOf: Reverse))
							)
							(ego setCycle: StopWalk -1)
						)
						(= local0 2)
					)
					((and (ego isStopped:) (not local2) local1)
						(if local30
							(self setScript: egoStruggleScr)
							(= local2 1)
							(= local30 0)
						else
							(messager say: 10 3 12 1)
							(= local2 1)
						)
					)
				)
			)
			((& temp0 $0008)
				(if (!= local0 3)
					(if local1
						(theGame handsOff:)
						(ego
							setLoop: (ego loop?)
							setMotion: 0
							normal: 0
							setSpeed: 8
							setCycle: Reverse
						)
						(repeat
							(ego y: (- (ego y?) 1))
							(breakif (not (& (ego onControl: 1) $0008)))
						)
						(= local30 1)
					else
						(self setScript: deathByWaterScr)
					)
					(= local0 3)
				)
			)
			((!= local0 0)
				(soundFx fade: 0 10 15 1)
				(ego view: 900 setCycle: Walk)
				(= local0 0)
			)
		)
	)
	
	(method (dispose)
		(theMusic fade:)
		(theGlobalSound fade:)
		(walkHandler delete: self)
		(directionHandler delete: self)
		(super dispose:)
		(DisposeScript 951)
		(DisposeScript 969)
		(DisposeScript 923)
		(DisposeScript 13)
	)
	
	(method (handleEvent event)
		(if (& (event type?) $1040)
			(if
				(and
					(== (theIconBar at: 0) (theIconBar curIcon?))
					(== local0 0)
				)
				(= local1 1)
				(= local2 0)
			else
				(= local1 0)
			)
		else
			(super handleEvent: event)
		)
		(event claimed?)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(2
				(messager say: noun theVerb 5)
				1
			)
			(else  (super doVerb: theVerb))
		)
	)
	
	(method (cue)
		(ego setScale: Scaler 100 50 112 57)
	)
)

(instance sandPoly of Polygon
	(properties
		type $0000
	)
	
	(method (init)
		(super
			init: 92 58 151 58 157 65 95 93 100 115 139 132 170 132 196 143 93 143
		)
	)
)

(instance sand of Feature
	(properties
		y 190
		noun 14
	)
	
	(method (init)
		(super init: &rest)
		(= onMeCheck (sandPoly init: yourself:))
		(self setOnMeCheck: 2 onMeCheck)
		(= sightAngle 26505)
	)
)

(instance chestInset of Inset
	(properties
		view 202
		x 170
		y 98
		priority 15
		disposeNotOnMe 1
		noun 8
	)
	
	(method (init)
		(super init: &rest)
		(if (== ((inventory at: 9) owner?) curRoomNum)
			(coin init:)
		)
		(sand init:)
		(theGame handsOn:)
		(theIconBar disable: 0 3 4 5 6)
	)
	
	(method (dispose)
		(if (cast contains: 9) (coin dispose:))
		(sand dispose:)
		(super dispose:)
		(theIconBar enable: 0 3 6)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(if (== ((inventory at: 9) owner?) 200)
					(messager say: noun theVerb 10)
				else
					(messager say: noun theVerb 11)
				)
			)
			(5
				(if (== ((inventory at: 9) owner?) 200)
					(messager say: noun theVerb 10)
				else
					(messager say: noun theVerb 11)
				)
			)
			(40 (messager say: 5 theVerb))
			(else 
				(if (!= theVerb 2) (= theVerb 0))
				(super doVerb: theVerb &rest)
			)
		)
	)
	
	(method (onMe event &tmp temp0)
		(return
			(if
				(InRect
					(+ (insetView nsLeft?) 5)
					(+ (insetView nsTop?) 5)
					(- (insetView nsRight?) 5)
					(- (insetView nsBottom?) 5)
					event
				)
				(return 1)
			else
				(return 0)
			)
		)
	)
)

(instance plankPoly of Polygon
	(properties)
)

(instance egoDoVerb of Actions
	(properties)
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(12
					(curRoom setScript: 130)
					(return 1)
				)
				(else  (return 0))
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
				(ego setMotion: PolyPath 252 58 self)
			)
			(1
				(ego
					ignoreActors:
					setPri: 1
					setLoop: 3
					setScale:
					illegalBits: 0
					setMotion: MoveTo 261 86 self
				)
			)
			(2 (curRoom newRoom: 210))
		)
	)
)

(instance enterRoomScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego
					setPri: 1
					setLoop: 2
					posn: 261 86
					scaleX: 64
					scaleY: 64
					scaleSignal: 1
					show:
					setMotion: MoveTo 252 58 self
				)
			)
			(1
				(ego
					setPri: -1
					setLoop: -1
					setScale: Scaler 100 50 112 57
					setMotion: PolyPath 204 103 self
				)
			)
			(2
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance getCoinScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theIconBar enable: 6)
				(theGame handsOff:)
				(ego get: 9)
				(theGame givePoints: 1)
				(coin dispose:)
				(= cycles 2)
			)
			(1
				(messager say: 7 5)
				(= cycles 2)
			)
			(2
				(chestInset dispose:)
				(self dispose:)
			)
		)
	)
)

(instance egoStruggleScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= register (Random 2 4))
				(= ticks 30)
			)
			(1
				(if (== (ego loop?) 2)
					(ego setLoop: 1)
				else
					(ego setLoop: 2)
				)
				(if (not (-- register)) (-- state))
				(= ticks 30)
			)
			(2
				(ego normal: 1 setCycle: StopWalk -1 setLoop: -1)
				(= cycles 2)
			)
			(3
				(messager say: 10 3 12 1 self)
			)
			(4
				(ego setSpeed: (ego currentSpeed?))
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance egoIntoSafeWater of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: 0 view: 308)
				(messager say: 10 3 12 1 self)
			)
			(1 (self dispose:))
		)
	)
)

(instance deathByWaterScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(messager say: 10 3 15 0 self)
			)
			(1
				(soundFx stop: number: 921 loop: 1 play:)
				(ego
					view: 269
					setLoop: 0
					cel: 0
					normal: 0
					cycleSpeed: 6
					setCycle: EndLoop self
					heading: 200
					setMotion: MoveFwd 200
				)
			)
			(2 (curRoom newRoom: 135))
		)
	)
)

(instance replacePlankScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(localproc_095a)
				(ego
					normal: 0
					posn: 242 139
					setSpeed: 6
					view: 201
					loop: 1
					cel: 8
					setScale: 0
					setCycle: CycleTo 5 -1 self
				)
			)
			(1
				(plank cel: 0 hide:)
				(ego loop: 2 cel: 5 setCycle: CycleTo 3 -1 self)
				(soundFx2 number: 200 loop: 1 play:)
			)
			(2
				(plank show: stopUpd:)
				(= cycles 1)
			)
			(3 (ego setCycle: BegLoop self))
			(4
				(chest dispose:)
				(ego
					posn: (plank approachX?) (plank approachY?)
					reset: 2
					setScale: Scaler 100 50 112 57
				)
				(= cycles 2)
			)
			(5 (messager say: 4 5 7 0 self))
			(6
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance displacePlankScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego
					setSpeed: 6
					posn: 242 139
					view: 201
					normal: 0
					loop: 2
					cel: 0
					setScale: 0
					setCycle: CycleTo 3 1 self
				)
			)
			(1
				(chest init:)
				(soundFx2 number: 200 loop: 1 play:)
				(= cycles 2)
			)
			(2
				(plank cel: 1 hide:)
				(= cycles 1)
			)
			(3 (ego setCycle: CycleTo 4 1 self))
			(4
				(plank show: stopUpd:)
				(ego setCycle: EndLoop self)
			)
			(5 (= cycles 2))
			(6
				(ego loop: 1 cel: 5 setCycle: EndLoop self)
			)
			(7
				(ego
					reset: 5
					posn: (plank approachX?) (plank approachY?)
					setScale: Scaler 100 50 112 57
				)
				(localproc_095a)
				(= cycles 2)
			)
			(8 (messager say: 4 5 6 0 self))
			(9
				(if (not (Bset 92)) (theGame givePoints: 1))
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance objectGlitter of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds (Random 2 7)))
			(1
				(= state -1)
				(client cel: 0 setCycle: EndLoop self)
			)
		)
	)
)

(instance openChestScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= cycles 1)
			)
			(1
				(ego
					setSpeed: 6
					normal: 0
					posn: (chest x?) (chest y?)
					view: 201
					loop: 3
					cel: 0
					setScale: 0
					setCycle: EndLoop self
				)
			)
			(2 (= ticks 12))
			(3
				(soundFx2 number: 904 loop: 1 play: self)
			)
			(4 (= ticks 12))
			(5
				(chestInset init: self curRoom)
			)
			(6 (= cycles 2))
			(7
				(theGame handsOff:)
				(= ticks 1)
			)
			(8
				(soundFx2 number: 905 loop: 1 play: self)
			)
			(9 (= ticks 12))
			(10 (ego setCycle: BegLoop self))
			(11
				(ego
					posn: 240 132
					reset: 2
					setScale: Scaler 100 50 112 57
				)
				(= cycles 2)
			)
			(12
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance chest of Prop
	(properties
		x 237
		y 137
		noun 5
		sightAngle 45
		approachX 235
		approachY 132
		view 200
		loop 4
		cel 2
		signal $4001
	)
	
	(method (init)
		(super init: &rest)
		(self approachVerbs: 5)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(5
				(self setScript: openChestScr)
			)
			(1
				(messager say: noun theVerb 9)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance takeRingScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if (!= (ego loop?) 1)
					(ego setHeading: 315 self)
				else
					(self cue:)
				)
			)
			(1 (= cycles 2))
			(2
				(ego
					normal: 0
					setSpeed: 8
					view: 201
					loop: 4
					cel: 0
					setCycle: EndLoop self
				)
				(royalRing dispose:)
			)
			(3
				(theGame givePoints: 1)
				(ego reset: 7 get: 39)
				(= cycles 2)
			)
			(4 (messager say: 9 5 0 0 self))
			(5
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance royalRing of Prop
	(properties
		x 104
		y 134
		noun 9
		sightAngle 45
		approachX 134
		approachY 136
		view 202
		loop 2
	)
	
	(method (init)
		(self
			cel: 0
			setCycle: EndLoop
			setScript: (Clone objectGlitter)
			approachVerbs: 5
		)
		(if (not (Btst 48)) (= loop 3))
		(super init: &rest)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(5
				(curRoom setScript: takeRingScr)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance coin of Prop
	(properties
		x 188
		y 191
		z 70
		noun 7
		sightAngle 360
		view 202
		loop 1
		cel 8
	)
	
	(method (init)
		(self
			cel: 0
			setCycle: EndLoop
			setScript: (Clone objectGlitter)
			sightAngle: 360
			setPri: 15
		)
		(super init: &rest)
		(= sightAngle 26505)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(5
				(curRoom setScript: getCoinScr)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance shipsails of Prop
	(properties
		x 56
		y 46
		noun 11
		view 200
		cel 2
		signal $1001
		cycleSpeed 2
	)
)

(instance bush1 of Prop
	(properties
		x 98
		y 99
		noun 15
		view 200
		loop 1
		cel 4
		signal $1001
		cycleSpeed 2
	)
)

(instance bush2 of Prop
	(properties
		x 291
		y 85
		noun 15
		view 200
		loop 2
		cel 2
		signal $1001
		cycleSpeed 3
	)
)

(instance bush3 of Prop
	(properties
		x 162
		y 20
		noun 15
		view 200
		loop 3
		cel 2
		signal $1001
		cycleSpeed 3
	)
)

(instance plank of View
	(properties
		x 242
		y 139
		noun 4
		sightAngle 45
		approachX 244
		approachY 132
		view 200
		loop 4
		signal $5001
	)
	
	(method (init)
		(super init: &rest)
		(self
			approachVerbs: 5
			cel: (if (Btst 11) (chest init:) 1 else 0)
		)
		(curRoom
			addObstacle:
				(if (Btst 11)
					(chest setPri: -1)
					(plankPoly type: 2 points: @local13 size: 8 yourself:)
				else
					(chest setPri: 1)
					(plankPoly type: 2 points: @local3 size: 5 yourself:)
				)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(5
				(if (Btst 11)
					(curRoom setScript: replacePlankScr)
				else
					(curRoom setScript: displacePlankScr)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance genericFeatures of Feature
	(properties)
	
	(method (onMe event &tmp temp0)
		(= temp0 (OnControl 4 (event x?) (event y?)))
		(genericFeatures x: (event x?) y: (event y?))
		(= noun
			(cond 
				((== temp0 128) 15)
				(
					(or
						(== temp0 256)
						(and (== temp0 2048) (> (event y?) 91))
					)
					14
				)
				((== temp0 64) 12)
				((== temp0 16) 11)
				((and (== temp0 512) (< (event y?) 130)) 16)
				((== temp0 2048) 17)
				((== temp0 16) 11)
				((OneOf temp0 8 4 512) 10)
				((OneOf temp0 1024 16384) 19)
				(else 0)
			)
		)
	)
)

(instance waveScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(wave cel: 0 setCycle: EndLoop self)
			)
			(1 (wave setCycle: BegLoop self))
			(2
				(wave hide:)
				(= seconds (Random 3 8))
			)
			(3
				(wave show:)
				(= state -1)
				(self cue:)
			)
		)
	)
)

(instance wave of Prop
	(properties
		x 129
		y 158
		noun 10
		view 204
		priority 1
		signal $5010
	)
)

(instance shipWheel of Feature
	(properties
		x 160
		y 176
		noun 18
	)
	
	(method (onMe event)
		(if
			(or
				(InRect 145 175 160 181 (event x?) (event y?))
				(InRect 162 169 175 177 (event x?) (event y?))
				(InRect 171 164 177 169 (event x?) (event y?))
			)
		else
			(InRect 156 170 162 175 (event x?) (event y?))
		)
	)
)
