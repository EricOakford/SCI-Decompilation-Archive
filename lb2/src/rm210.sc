;;; Sierra Script 1.0 - (do not remove this comment)
(script# 210)
(include game.sh) (include "210.shm")
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
	person4Pts = [
		140 165
		350 165
		PATHEND
		]
	person6Pts = [
		150 167
		519 167
		PATHEND
		]
	car2Pts = [
		127 172
		370 172
		PATHEND
		]
)
(instance rm210 of LBRoom
	(properties
		noun N_ROOM
		picture 210
		north 230
		south 280
		vanishingX 150
		vanishingY 112
	)
	
	(method (init)
		(LoadMany RES_VIEW 214 210 284 212 213 852 803)
		(LoadMany RES_SOUND 190 40 252)
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
					type: PBarredAccess
					init:
						319 0
						319 176
						199 176
						88 184
						80 183
						73 180
						48 174
						39 173
						32 165
						11 152
						6 167
						25 170
						31 180
						0 180
						0 0
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init: 
						0 189
						0 187
						154 187
						154 182
						177 182
						177 187
						194 187
						194 189
					yourself:
				)
		)
		(if (ego has: iCoupon)
			(self
				addObstacle:
					((Polygon new:)
						type: PBarredAccess
						init:
							224 177
							235 182
							216 185
							205 179
						yourself:
					)
			)
			(luigi approachVerbs: V_DO V_TALK V_ASK V_COUPON init: setCycle: Forward)
			(theMusic number: 190 loop: -1 flags: mNOPAUSE play:)
		)
		(frontDoor init:)
		(taxiSign approachVerbs: V_DO addToPic:)
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
		(greyBuilding setOnMeCheck: ftrControl cLMAGENTA init:)
		(nextBuilding setOnMeCheck: ftrControl cYELLOW init:)
		(gothicEntrance setOnMeCheck: ftrControl cLRED init:)
		(newsBuilding setOnMeCheck: ftrControl cLCYAN init:)
		(tree setOnMeCheck: ftrControl cLGREEN init:)
		(southExitFeature init:)
	)
	
	(method (dispose)
		(theMusic fade:)
		(DisposeScript PATH)
		(super dispose:)
	)
)

(instance sOverControl of Script
	
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
			(3
				(= state -1)
				(= cycles 1)
			)
		)
	)
)

(instance sMovePerson2 of Script
	
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
			(3
				(= state -1)
				(= cycles 1)
			)
		)
	)
)

(instance sMovePerson3 of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(person3
					loop: 4
					posn: 209 176
					setMotion: MoveTo 170 172 self
				)
			)
			(1
				(= seconds (Random 1 6))
			)
			(2
				(person3
					loop: 3
					posn: -10 185
					setMotion: MoveTo 208 176 self
				)
			)
			(3
				(= state -1)
				(= cycles 1)
			)
		)
	)
)

(instance sMovePerson8 of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(person8
					loop: 12
					posn: 203 175
					setMotion: MoveTo 158 173 self
				)
			)
			(1
				(= seconds (Random 5 10))
			)
			(2
				(person8
					posn: -5 189
					loop: 11
					setMotion: MoveTo 202 176 self
				)
			)
			(3
				(= state -1)
				(= cycles 1)
			)
		)
	)
)

(instance sHailTaxi of Script

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
					setCycle: EndLoop self
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
				(noise number: 40 flags: mNOPAUSE play: self)
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
			(10
				(curRoom newRoom: 250)
			)
		)
	)
)

(instance sGetSandwich of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= cycles 1)
			)
			(1
				(ego put: iCoupon)
				((ScriptID 21 1) doit: 769)
				(messager say: N_LUIGI V_COUPON NULL 1 self)
				(luigi posn: 221 179 setCycle: 0)
			)
			(2
				(messager say: N_LUIGI V_COUPON NULL 2 self)
			)
			(3
				(luigi
					loop: 1
					posn: 217 180
					cycleSpeed: 10
					setCycle: EndLoop self
				)
			)
			(4
				(messager say: N_LUIGI V_COUPON NULL 3 self)
			)
			(5
				(curRoom setInset: sandwichI self)
			)
			(6
				(luigi loop: 0 posn: 221 179 setCycle: Forward)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sGetSandwichInset of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				((ScriptID 21 0) doit: 772)
				(sandwichI dispose:)
				(luigi loop: 0 posn: 217 180 setCycle: Forward)
				(= cycles 2)
			)
			(1
				(ego get: iSandwich)
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
		signal ignrAct
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
			(V_COUPON
				(curRoom setScript: sGetSandwich)
			)
			(V_TALK
				(cond 
					((and (not (ego has: iCoupon)) (not (Btst fTalkedToLuigi)))
						(Bset fTalkedToLuigi)
						(messager say: N_LUIGI V_TALK C_FIRST_TALK 0)
					)
					((not (ego has: iCoupon))
						(messager say: N_LUIGI V_TALK C_TALK_AGAIN 0)
					)
					((ego has: iCoupon)
						(messager say: N_LUIGI V_TALK C_GOT_COUPON 0)
					)
				)
			)
			(V_ASK
				(cond 
					(
						(and
							(<= 256 (= temp0 (curRoom setInset: (ScriptID 20 0))))
							(<= temp0 409)
						)
						(messager say: N_LUIGI V_ASK 4)
					)
					((and (<= 512 temp0) (<= temp0 665))
						(messager say: N_LUIGI V_ASK 5)
					)
					((== temp0 772)
						(messager say: N_LUIGI V_ASK 8)
					)
					((and (<= 768 temp0) (<= temp0 921))
						(messager say: N_LUIGI V_ASK 6)
					)
					((and (<= 1024 temp0) (<= temp0 1177))
						(messager say: N_LUIGI V_ASK 7)
					)
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
		noun N_CART
		view 214
		loop 3
		signal (| ignrAct stopUpdOn)
	)
)

(instance sandwichI of Inset
	(properties
		view 214
		loop 4
		x 141
		y 90
		disposeNotOnMe TRUE
		noun N_SANDWICH
	)
	
	(method (init)
		(super init: &rest)
		(theGame handsOn:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO
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
		noun N_FRONT_DOOR
		approachX 36
		approachY 175
		view 210
		entranceTo 230
		moveToX 22
		moveToY 169
		enterType doorWalkEgo
		exitType doorWalkEgo
	)
)

(instance taxiSign of View
	(properties
		x 165
		y 185
		noun N_TAXI_SIGN
		approachX 150
		approachY 187
		view 284
		cel 2
		signal (| ignrAct stopUpdOn)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO
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
		noun N_TAXI
		view 852
		loop 4
	)
)

(instance man1 of Actor
	(properties
		x 117
		y 188
		noun N_PERSON
		view 212
		signal ignrAct
	)
	
	(method (cue)
		(man1 setScript: sMoveMan1)
	)
)

(instance person2 of Actor
	(properties
		x 140
		y 180
		noun N_PERSON
		view 212
		loop 2
		signal ignrAct
	)
	
	(method (cue)
		(person2 setScript: sMovePerson2)
	)
)

(instance person3 of Actor
	(properties
		x -10
		y 183
		noun N_PERSON
		view 212
		loop 3
		signal ignrAct
	)
	
	(method (cue)
		(person3 setScript: sMovePerson3)
	)
)

(instance person4 of Actor
	(properties
		x 140
		y 165
		noun N_PERSON
		view 212
		loop 5
		priority 11
		signal (| ignrAct fixPriOn)
	)
	
	(method (cue)
		(person4 posn: 140 165 setMotion: person4Path self)
	)
)

(instance person4Path of Path
	
	(method (at n)
		(return [person4Pts n])
	)
)

(instance person6 of Actor
	(properties
		x 150
		y 167
		noun N_PERSON
		view 212
		loop 7
		priority 11
		signal (| ignrAct fixPriOn)
	)
	
	(method (cue)
		(person6 posn: 150 167 setMotion: person6Path self)
	)
)

(instance person6Path of Path

	(method (at n)
		(return [person6Pts n])
	)
)

(instance person8 of Actor
	(properties
		x -5
		y 189
		noun N_PERSON
		view 212
		loop 11
		signal ignrAct
	)
	
	(method (cue)
		(person8 setScript: sMovePerson8)
	)
)

(instance car of Actor
	(properties
		x 154
		y 189
		noun N_TAXI
		view 213
		cel 1
		priority 14
		signal (| ignrAct fixPriOn)
	)
	
	(method (cue)
		(car dispose:)
	)
)

(instance car2 of Actor
	(properties
		x 127
		y 172
		noun N_TAXI
		view 213
		loop 7
		cel 3
		signal ignrAct
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

	(method (at n)
		(return [car2Pts n])
	)
)

(instance greyBuilding of Feature
	(properties
		y 100
		noun N_GREY_BUILDING
	)
)

(instance nextBuilding of Feature
	(properties
		y 180
		noun N_NEXT_BUILDING
	)
)

(instance gothicEntrance of Feature
	(properties
		y 100
		noun N_FRONT_DOOR
	)
)

(instance newsBuilding of Feature
	(properties
		y 100
		noun N_NEWS_BUILDING
	)
)

(instance tree of Feature
	(properties
		y 190
		noun N_TREE
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
		noun N_SOUTH_EXIT
		)
)

(instance noise of Sound
	(properties
		flags mNOPAUSE
	)
)
