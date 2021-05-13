;;; Sierra Script 1.0 - (do not remove this comment)
(script# 210)
(include sci.sh)
(use Main)
(use LbDoor)
(use LBRoom)
(use ExitFeature)
(use Inset)
(use Scaler)
(use PolyPath)
(use Polygon)
(use Feature)
(use LoadMany)
(use Path)
(use Sound)
(use Motion)
(use Actor)
(use System)

(public
	rm210 0
)

(local
	[local0 5] = [140 165 350 165 -32768]
	[local5 5] = [150 167 519 167 -32768]
	[local10 5] = [127 172 370 172 -32768]
)
(instance rm210 of LBRoom
	(properties
		noun 16
		picture 210
		north 230
		south 280
		vanishingX 150
		vanishingY 112
	)
	
	(method (init)
		(LoadMany 128 214 210 284 212 213 852 803)
		(LoadMany 132 190 40 252)
		(self setRegions: 91)
		(ego
			normalize: 803
			setScale: Scaler 100 100 190 112
			init:
		)
		(switch prevRoomNum
			(north
				(ego edgeHit: 0 setHeading: 180 normalize: 803)
			)
			(south
				(curRoom setScript: sOverControl)
			)
			(else 
				(ego x: 150 y: 187)
				(taxi init: posn: 190 195)
				(curRoom setScript: sOutCab)
			)
		)
		(super init:)
		(self
			addObstacle:
				((Polygon new:)
					type: 2
					init:
						319
						0
						319
						176
						199
						176
						88
						184
						80
						183
						73
						180
						48
						174
						39
						173
						32
						165
						11
						152
						6
						167
						25
						170
						31
						180
						0
						180
						0
						0
					yourself:
				)
				((Polygon new:)
					type: 2
					init: 0 189 0 187 154 187 154 182 177 182 177 187 194 187 194 189
					yourself:
				)
		)
		(if (ego has: 0)
			(self
				addObstacle:
					((Polygon new:)
						type: 2
						init: 224 177 235 182 216 185 205 179
						yourself:
					)
			)
			(luigi approachVerbs: 4 2 6 10 init: setCycle: Fwd)
			(theMusic number: 190 loop: -1 flags: 1 play:)
		)
		(frontDoor init:)
		(taxiSign approachVerbs: 4 addToPic:)
		(if (> (theGame detailLevel:) 2)
			(man1
				init:
				setLoop: 0
				setCycle: Walk
				setMotion: MoveTo 198 176 man1
			)
			(person2
				init:
				setLoop: 2
				setStep: 1 1
				setCycle: Walk
				setMotion: MoveTo -20 183 person2
			)
			(person3
				init:
				setLoop: 3
				setStep: 1 1
				setCycle: Walk
				setMotion: MoveTo 208 179 person3
			)
			(person4
				init:
				setLoop: 5
				setCycle: Walk
				setMotion: person4Path person4
			)
		)
		(person6
			init:
			setLoop: 7
			setStep: 2 2
			setCycle: Walk
			setMotion: person6Path person6
		)
		(person8
			init:
			setLoop: 11
			setCycle: Walk
			setMotion: MoveTo 202 176 person8
		)
		(car2
			init:
			setLoop: 6
			cel: (Random 0 4)
			moveSpeed: 4
			setMotion: car2Path car2
		)
		(greyBuilding setOnMeCheck: 1 8192 init:)
		(nextBuilding setOnMeCheck: 1 16384 init:)
		(gothicEntrance setOnMeCheck: 1 4096 init:)
		(newsBuilding setOnMeCheck: 1 2048 init:)
		(tree setOnMeCheck: 1 1024 init:)
		(southExitFeature init:)
	)
	
	(method (dispose)
		(theMusic fade:)
		(DisposeScript 983)
		(super dispose:)
	)
)

(instance sOverControl of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego x: 134 y: 185)
				(= cycles 1)
			)
			(1
				(ego setMotion: PolyPath 134 180 self)
			)
			(2
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sOutCab of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= cycles 1)
			)
			(1
				((ScriptID 91 1) pause:)
				(theMusic2 send: 2 224 2000)
				(ego posn: 147 185)
				(taxi setLoop: 4 setMotion: MoveTo 105 227 self)
				(theMusic2 fade:)
			)
			(2
				((ScriptID 91 1) pause: 0)
				(theGame handsOn:)
				(taxi dispose:)
				(self dispose:)
			)
		)
	)
)

(instance sMoveMan1 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(man1
					setLoop: 1
					posn: 194 175
					setMotion: MoveTo 163 173 self
				)
			)
			(1 (= seconds (Random 3 12)))
			(2
				(man1
					posn: -10 188
					setLoop: 0
					setCycle: Walk
					setMotion: MoveTo 198 176 self
				)
			)
			(3 (= state -1) (= cycles 1))
		)
	)
)

(instance sMovePerson2 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds (Random 5 10)))
			(1
				(person2
					setLoop: 8
					setStep: 2 2
					posn: 166 172
					setMotion: MoveTo 194 176 self
				)
			)
			(2
				(person2 loop: 9 setMotion: MoveTo -20 189 self)
			)
			(3 (= state -1) (= cycles 1))
		)
	)
)

(instance sMovePerson3 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(person3
					loop: 4
					posn: 209 176
					setMotion: MoveTo 170 172 self
				)
			)
			(1 (= seconds (Random 1 6)))
			(2
				(person3
					loop: 3
					posn: -10 185
					setMotion: MoveTo 208 176 self
				)
			)
			(3 (= state -1) (= cycles 1))
		)
	)
)

(instance sMovePerson8 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(person8
					loop: 12
					posn: 203 175
					setMotion: MoveTo 158 173 self
				)
			)
			(1 (= seconds (Random 5 10)))
			(2
				(person8
					posn: -5 189
					loop: 11
					setMotion: MoveTo 202 176 self
				)
			)
			(3 (= state -1) (= cycles 1))
		)
	)
)

(instance sHailTaxi of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theMusic2 number: 252 loop: -1 flags: 1 play: 20)
				(theGame handsOff:)
				((ScriptID 91 1) pause:)
				(= cycles 1)
			)
			(1
				(ego
					view: 852
					loop: 1
					posn: (- (ego x?) 2) (- (ego y?) 1)
					setScale: Scaler 102 0 190 112
					setCycle: End self
				)
				(noise number: 97 play:)
			)
			(2
				(taxi init: setScale: Scaler 116 0 190 112 posn: 378 181)
				(theMusic2 send: 2 224 2000 fade: 127 5 5 0)
				(= cycles 1)
			)
			(3
				(taxi
					setLoop: 4
					xStep: 4
					setMotion: MoveTo (+ (taxiSign x?) 25) (+ (taxiSign y?) 10) self
				)
			)
			(4
				(theMusic2 send: 2 224 1600)
				(= cycles 1)
			)
			(5
				(theMusic2 send: 2 224 1200)
				(= cycles 1)
			)
			(6
				(theMusic2 send: 2 224 800)
				(= cycles 1)
			)
			(7
				(theMusic2 send: 2 224 400)
				(= cycles 1)
			)
			(8
				(theMusic2 send: 2 224 0)
				(noise number: 40 flags: 1 play: self)
				(if (cast contains: luigi)
					(luigi setScript: 0 addToPic:)
				)
			)
			(9
				(ego
					view: 803
					setLoop: 4
					setMotion: MoveTo (+ (ego x?) 17) (ego y?) self
				)
			)
			(10 (curRoom newRoom: 250))
		)
	)
)

(instance sGetSandwich of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= cycles 1)
			)
			(1
				(ego put: 0)
				((ScriptID 21 1) doit: 769)
				(messager say: 3 10 0 1 self)
				(luigi posn: 221 179 setCycle: 0)
			)
			(2
				(messager say: 3 10 0 2 self)
			)
			(3
				(luigi
					loop: 1
					posn: 217 180
					cycleSpeed: 10
					setCycle: End self
				)
			)
			(4
				(messager say: 3 10 0 3 self)
			)
			(5
				(curRoom setInset: sandwichI self)
			)
			(6
				(luigi loop: 0 posn: 221 179 setCycle: Fwd)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sGetSandwichInset of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				((ScriptID 21 0) doit: 772)
				(sandwichI dispose:)
				(luigi loop: 0 posn: 217 180 setCycle: Fwd)
				(= cycles 2)
			)
			(1
				(ego get: 3)
				(self dispose:)
			)
		)
	)
)

(instance luigi of Prop
	(properties
		x 221
		y 179
		noun 3
		approachX 189
		approachY 181
		view 214
		signal $4000
	)
	
	(method (init)
		(cart
			posn: (- (self x?) 13) (+ (self y?) 5)
			init:
			stopUpd:
		)
		(super init:)
	)
	
	(method (dispose)
		(cart dispose:)
		(super dispose:)
	)
	
	(method (doVerb theVerb &tmp temp0)
		(switch theVerb
			(10
				(curRoom setScript: sGetSandwich)
			)
			(2
				(cond 
					((and (not (ego has: 0)) (not (Btst 28))) (Bset 28) (messager say: 3 2 1 0))
					((not (ego has: 0)) (messager say: 3 2 2 0))
					((ego has: 0) (messager say: 3 2 3 0))
				)
			)
			(6
				(cond 
					(
						(and
							(<= 256 (= temp0 (curRoom setInset: (ScriptID 20 0))))
							(<= temp0 409)
						)
						(messager say: 3 6 4)
					)
					((and (<= 512 temp0) (<= temp0 665)) (messager say: 3 6 5))
					((== temp0 772) (messager say: 3 6 8))
					((and (<= 768 temp0) (<= temp0 921)) (messager say: 3 6 6))
					((and (<= 1024 temp0) (<= temp0 1177)) (messager say: 3 6 7))
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance cart of View
	(properties
		noun 4
		view 214
		loop 3
		signal $4001
	)
)

(instance sandwichI of Inset
	(properties
		view 214
		loop 4
		x 141
		y 90
		disposeNotOnMe 1
		noun 2
	)
	
	(method (init)
		(super init: &rest)
		(theGame handsOn:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(curRoom setScript: sGetSandwichInset)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance frontDoor of LbDoor
	(properties
		x 39
		y 167
		noun 5
		approachX 36
		approachY 175
		view 210
		entranceTo 230
		moveToX 22
		moveToY 169
		enterType 0
		exitType 0
	)
)

(instance taxiSign of View
	(properties
		x 165
		y 185
		noun 6
		approachX 150
		approachY 187
		view 284
		cel 2
		signal $4001
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(curRoom setScript: sHailTaxi)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance taxi of Actor
	(properties
		x 375
		y 181
		noun 13
		view 852
		loop 4
	)
)

(instance man1 of Actor
	(properties
		x 117
		y 188
		noun 9
		view 212
		signal $4000
	)
	
	(method (cue)
		(man1 setScript: sMoveMan1)
	)
)

(instance person2 of Actor
	(properties
		x 140
		y 180
		noun 9
		view 212
		loop 2
		signal $4000
	)
	
	(method (cue)
		(person2 setScript: sMovePerson2)
	)
)

(instance person3 of Actor
	(properties
		x -10
		y 183
		noun 9
		view 212
		loop 3
		signal $4000
	)
	
	(method (cue)
		(person3 setScript: sMovePerson3)
	)
)

(instance person4 of Actor
	(properties
		x 140
		y 165
		noun 9
		view 212
		loop 5
		priority 11
		signal $4010
	)
	
	(method (cue)
		(person4 posn: 140 165 setMotion: person4Path self)
	)
)

(instance person4Path of Path
	(properties)
	
	(method (at param1)
		(return [local0 param1])
	)
)

(instance person6 of Actor
	(properties
		x 150
		y 167
		noun 9
		view 212
		loop 7
		priority 11
		signal $4010
	)
	
	(method (cue)
		(person6 posn: 150 167 setMotion: person6Path self)
	)
)

(instance person6Path of Path
	(properties)
	
	(method (at param1)
		(return [local5 param1])
	)
)

(instance person8 of Actor
	(properties
		x -5
		y 189
		noun 9
		view 212
		loop 11
		signal $4000
	)
	
	(method (cue)
		(person8 setScript: sMovePerson8)
	)
)

(instance car of Actor
	(properties
		x 154
		y 189
		noun 13
		view 213
		cel 1
		priority 14
		signal $4010
	)
	
	(method (cue)
		(car dispose:)
	)
)

(instance car2 of Actor
	(properties
		x 127
		y 172
		noun 13
		view 213
		loop 7
		cel 3
		signal $4000
	)
	
	(method (cue)
		(car2
			posn: 127 172
			cel: (Random 0 4)
			setMotion: car2Path self
		)
	)
)

(instance car2Path of Path
	(properties)
	
	(method (at param1)
		(return [local10 param1])
	)
)

(instance greyBuilding of Feature
	(properties
		y 100
		noun 10
	)
)

(instance nextBuilding of Feature
	(properties
		y 180
		noun 7
	)
)

(instance gothicEntrance of Feature
	(properties
		y 100
		noun 5
	)
)

(instance newsBuilding of Feature
	(properties
		y 100
		noun 15
	)
)

(instance tree of Feature
	(properties
		y 190
		noun 19
	)
)

(instance southExitFeature of ExitFeature
	(properties
		nsTop 184
		nsLeft 195
		nsBottom 189
		nsRight 289
		cursor 11
		exitDir 3
		noun 18
	)
)

(instance noise of Sound
	(properties
		flags $0001
	)
)
