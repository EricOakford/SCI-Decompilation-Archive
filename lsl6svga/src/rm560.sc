;;; Sierra Script 1.0 - (do not remove this comment)
(script# 560)
(include sci.sh)
(use Main)
(use fileScr)
(use EgoDead)
(use LarryRoom)
(use Scaler)
(use PolyPath)
(use Polygon)
(use Feature)
(use Timer)
(use Sound)
(use Motion)
(use Actor)
(use System)

(public
	rm560 0
)

(local
	local0
	local1
	local2
	local3
	local4
)
(instance rm560 of LarryRoom
	(properties
		noun 1
		picture 560
		horizon 0
		south 530
	)
	
	(method (init)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: 3
					init:
						308
						119
						280
						86
						248
						59
						196
						64
						108
						64
						61
						108
						10
						142
						173
						140
						179
						136
						286
						136
					yourself:
				)
				((Polygon new:)
					type: 2
					init: 105 92 134 73 206 73 163 130 137 136 80 124
					yourself:
				)
				((Polygon new:)
					type: 2
					init: 205 90 205 80 247 80 247 90
					yourself:
				)
				((Polygon new:)
					type: 2
					init:
						198
						121
						224
						109
						263
						104
						271
						109
						272
						115
						245
						115
						242
						119
						231
						119
						228
						125
						198
						125
					yourself:
				)
				((Polygon new:)
					type: 2
					init: 241 126 269 126 272 133 242 133
					yourself:
				)
		)
		(super init: &rest)
		(ego
			init:
			normalize:
			setScaler: Scaler 100 82 122 63
			scaleSignal: (| (ego scaleSignal?) $0004)
		)
		(if (OneOf prevRoomNum 700 570)
			(self setScript: fromDumbWaiterScr)
		else
			(self setScript: enterFromDiningRoomScr)
		)
		(dumbWaiter init:)
		(dwButtons init:)
		(fixPriPoly init:)
		(sink init:)
		(screen init:)
		(tacoTruck init:)
		(truckTire init:)
		(lowTire init:)
		(frige init:)
		(cleaver init:)
		(garbage init:)
		(frigeDoor approachVerbs: 4 5 36 init:)
		(projector setCycle: Fwd init:)
		(if (!= global100 560)
			(theMusic number: 560 loop: -1 play:)
		)
		(if (>= (theGame detailLevel:) 3)
			(projectorLight init: setCycle: Fwd)
			(movieSFX number: 564 loop: -1 play:)
		else
			(projector setCycle: 0 setPri: -1)
		)
		(if
		(== ((= local4 (inventory at: 39)) owner?) curRoomNum)
			(= local2 1)
		)
	)
	
	(method (doit)
		(cond 
			(script 0)
			((fixPriPoly onMe: (ego x?) (ego y?))
				(if (or (not local0) (!= (ego priority?) 140))
					(= local0 1)
					(ego setPri: 140 fixPriority: 1)
				)
			)
			(local0 (= local0 0) (ego setPri: -1 fixPriority: 0))
		)
		(super doit: &rest)
	)
	
	(method (doVerb theVerb)
		(return
			(if (== theVerb 6)
				(ego setScript: pissScr)
				(return 1)
			else
				(return (super doVerb: theVerb))
			)
		)
	)
	
	(method (cue)
		(= local2 1)
	)
	
	(method (newRoom n)
		(if
		(and (timers contains: local3) (local3 isKindOf: Timer))
			(local3 dispose:)
		)
		(if (== n 570)
			(theMusic fade: 75 25 10 0)
		else
			(theMusic fade:)
		)
		(ego scaleSignal: (& (ego scaleSignal?) $fffb))
		(fixPriPoly dispose:)
		(theMusic fade:)
		(movieSFX stop: dispose:)
		(super newRoom: n)
	)
	
	(method (edgeToRoom param1)
		(if (== param1 3) (curRoom setScript: exitScr))
		(return 0)
	)
)

(instance intoWaiter of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if (not (Btst 219))
					(messager say: 14 4 0 0 self)
				else
					(= cycles 2)
				)
			)
			(1 (= cycles 2))
			(2
				(theMusic2 number: 573 loop: 1 play:)
				(waiterDoor init: setCycle: End self)
			)
			(3
				(ego setSpeed: 6 setMotion: MoveTo 236 62 self)
			)
			(4 (ego setHeading: 0 self))
			(5
				(waiterDoor hide:)
				(ego
					setSpeed: 6
					setScale: 0
					view: 567
					loop: 4
					posn: 230 34
					cel: 0
					setCycle: End self
				)
			)
			(6
				(theGame changeScore: 9 219)
				(= ticks 60)
			)
			(7 (curRoom newRoom: 570))
		)
	)
)

(instance useSinkScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego
					view: 565
					setLoop: 1
					setCel: 0
					posn: 30 62
					setSpeed: 6
					setScale: 0
					setCycle: Fwd
				)
				(theMusic2 number: 634 loop: -1 play:)
				(= ticks 300)
			)
			(1
				(theMusic2 stop:)
				(ego
					posn: (sink approachX?) (sink approachY?)
					normalize: 900 8
					setCel: 1
					setScaler: Scaler 100 82 122 63
				)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance wetWashclothScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= cycles 2)
			)
			(1
				(ego
					setScale: 0
					setSpeed: 8
					view: 567
					setLoop: 1
					setCel: 0
					posn: 30 62
					setCycle: End self
				)
			)
			(2
				(= register (ego cel?))
				(theMusic2 number: 634 loop: -1 play:)
				(ego view: 565 setLoop: 0 setCel: 0 setCycle: Fwd)
				(= ticks 180)
			)
			(3
				(theMusic2 stop:)
				(ego
					view: 567
					setLoop: 1
					setCel: register
					setCycle: Beg self
				)
			)
			(4 (= ticks 20))
			(5
				((inventory at: 39) cue:)
				(ego
					posn: (sink approachX?) (sink approachY?)
					normalize: 900 8
					setScaler: Scaler 100 82 122 63
					setCel: 1
				)
				(= cycles 2)
			)
			(6
				(theGame changeScore: 6 250)
				(messager say: 3 35 0 0 self)
			)
			(7
				(theGame handsOn:)
				((theIconBar curIcon?) select:)
				(self dispose:)
			)
		)
	)
)

(instance inflateBeaverScr of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(messager say: 7 48 0 1 self)
			)
			(1 (= ticks 10))
			(2
				(ego
					view: 561
					loop: 1
					cel: 0
					setSpeed: 14
					setCycle: End self
				)
			)
			(3 (= ticks 30))
			(4
				(theMusic2 number: 563 loop: 1 play:)
				(ego loop: 2 cel: 0 setCycle: CT 8 1 self)
			)
			(5 (= ticks 30))
			(6
				(ego loop: 3 cel: 0 setCycle: End self)
			)
			(7 (= ticks 20))
			(8
				(theGame changeScore: 14 223)
				((= temp0 (inventory at: 2))
					noun: 16
					message: 49
					view: 51
					setCursor: 51 0 0
					yourself:
				)
				(if (not (& (temp0 signal?) $0004))
					(temp0 signal: (| (temp0 signal?) $0004))
					(DeleteScreenItem temp0)
				)
				(inventory show:)
				(= cycles 2)
			)
			(9
				(messager say: 7 48 0 2 self)
			)
			(10
				(ego normalize: 900 8 1 cel: 5)
				(theGame handsOn:)
				((theIconBar curIcon?) select:)
				(self dispose:)
			)
		)
	)
)

(instance cleanFilterScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(theGame changeScore: 10 220)
				(ego
					setScale: 0
					view: 568
					setLoop: 0
					setCel: 0
					setSpeed: 10
					setCycle: End self
				)
			)
			(1 (= cycles 2))
			(2
				(ego setLoop: 1 setCel: 0 setCycle: End self)
			)
			(3
				(ego setLoop: 2 setCel: 0 setCycle: Fwd)
				(= ticks 240)
			)
			(4 (ego setCycle: End self))
			(5 (= cycles 2))
			(6
				(ego setLoop: 3 setCel: 0 setCycle: End self)
			)
			(7
				(suckFilter init: setCycle: End self)
			)
			(8
				(suckFilter dispose:)
				(ego
					normalize: 900 8
					setScaler: Scaler 100 82 122 63
					setCel: 3
					put: 13
				)
				(= cycles 2)
			)
			(9
				(ego setSpeed: 6 setMotion: MoveTo 73 116 self)
				(dishWasherSFX number: 562 loop: -1 play:)
				(dishwasher init: setCycle: Fwd)
				(= ticks 360)
			)
			(10 (ego setHeading: 315))
			(11
				((inventory at: 13) cue:)
				(dishwasher setCycle: End self)
			)
			(12
				(dishWasherSFX stop: dispose:)
				(dishwasher dispose:)
				(shinnyFilter init: setCycle: End self)
				(ego setHeading: 0)
			)
			(13
				(ego setMotion: MoveTo 96 80 self)
			)
			(14 (= cycles 2))
			(15
				(ego
					view: 568
					setLoop: 5
					setCel: 0
					setCycle: 0
					setScale: 0
					setPri: 33
				)
				(shinnyFilter dispose:)
				(= cycles 2)
			)
			(16
				(ego get: 13 setCycle: End self)
			)
			(17
				(ego
					normalize: 900 8
					setCel: 2
					setScaler: Scaler 100 82 122 63
				)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance openFrigeScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego
					view: 901
					loop: 0
					cel: 0
					cycleSpeed: 10
					setCycle: CT 3 1 self
				)
			)
			(1
				(ego setCycle: End self)
				(theMusic2 number: 566 loop: 1 play:)
				(frigeDoor setCycle: End self)
			)
			(2 0)
			(3
				(ego normalize: 900 8 1 cel: 0)
				(if
				(and (timers contains: local3) (local3 isKindOf: Timer))
					(local3 dispose:)
				)
				(= local1 1)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance digInTrashScr of Script
	(properties)
	
	(method (dispose)
		(= register 0)
		(super dispose:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(theMusic2 number: 568 loop: 1 play:)
				(ego
					view: 567
					setLoop: 3
					cel: 0
					setSpeed: 10
					setCycle: End self
				)
			)
			(1
				(ego normalize: 900 8 1 cel: 6)
				(= cycles 2)
			)
			(2
				(messager
					say: 9 4 (cond 
						((Btst 277) 8)
						((not (Bset 81)) 7)
						(else 6)
					) 0 self
				)
			)
			(3
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance tireDeathScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego
					view: 566
					setLoop: 0
					cel: 0
					posn: 205 123
					setSpeed: 12
					setScale: 0
					setPri: 200
				)
				(= ticks 30)
			)
			(1
				(theMusic2 number: 563 loop: 1 play:)
				(ego setCycle: CT 3 1 self)
			)
			(2
				(ego setSpeed: 5 yStep: 2 setMotion: MoveTo 250 1 self)
				(= ticks 40)
			)
			(3
				(ego yStep: 3 setSpeed: 4 cel: (+ (ego cel?) 1))
				(= ticks 40)
			)
			(4
				(ego yStep: 4 setSpeed: 3 cel: (+ (ego cel?) 1))
				(= ticks 30)
			)
			(5
				(ego moveSpeed: 2 cycleSpeed: 10)
				(ego cel: (+ (ego cel?) 1))
				(= ticks 20)
			)
			(6
				(ego moveSpeed: 1)
				(ego setCycle: End)
			)
			(7 (ego hide:) (= cycles 2))
			(8 (= ticks 120))
			(9
				(theMusic pause:)
				(EgoDead 14 self)
			)
			(10
				(theMusic pause: 0)
				(ego
					show:
					normalize: 900 8 1
					cel: 1
					posn: (truckTire approachX?) (truckTire approachY?)
				)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance takeLardScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= cycles 2)
			)
			(1
				(theMusic2 number: 568 loop: 1 play:)
				(ego
					view: 567
					setLoop: 3
					setCel: 0
					setSpeed: 10
					setCycle: End self
				)
			)
			(2
				(theGame changeScore: 6 277)
				(ego normalize: 900 8 1 cel: 6 get: 24)
				(= cycles 2)
			)
			(3
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance touchPissScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setHeading: 270 self)
			)
			(1 (= cycles 2))
			(2
				(ego view: 901 setCycle: 0 loop: 5 cel: 0)
				(= ticks 20)
			)
			(3 (ego setCycle: CT 3 1 self))
			(4
				(messager say: 15 0 0 0 self)
			)
			(5 (ego setCycle: End self))
			(6
				(ego normalize: 900 8 1 cel: 1)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance pissPuddle of Prop
	(properties
		noun 15
		approachX 190
		approachY 106
		x 180
		y 100
		view 560
		loop 4
		cycleSpeed 20
	)
	
	(method (init)
		(super init: &rest)
		(self ignoreActors: 1)
	)
	
	(method (doVerb)
		(self setScript: touchPissScr)
	)
)

(instance pissScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: PolyPath 147 70 self)
			)
			(1 (= ticks 60))
			(2
				(zipperSFX play: self)
				(= ticks 60)
			)
			(3 0)
			(4
				(pissSFX play:)
				(= ticks 120)
			)
			(5
				(pissPuddle
					init:
					approachVerbs: 4 1 2 5 6 -1
					setCycle: End self
				)
			)
			(6
				(pissSFX stop: dispose:)
				(= ticks 60)
			)
			(7 (zipperSFX play: self))
			(8
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance exitScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego
					setMotion: MoveTo (- (ego x?) 15) (+ (ego y?) 50) self
				)
			)
			(1 (curRoom newRoom: 530))
		)
	)
)

(instance fromDumbWaiterScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego
					setCycle: 0
					setSpeed: 6
					view: 567
					setLoop: 4
					setScale: 0
					posn: 230 34
				)
				(ego cel: (ego lastCel:))
				(if (Btst 104)
					(Bclr 104)
					(waiterDoor setLoop: 2 setPri: 35)
					(= ticks 180)
				else
					(ego setPri: 35)
					(= state (+ state 2))
					(= cycles 2)
				)
				(waiterDoor cel: (waiterDoor lastCel:) init:)
			)
			(1
				(theMusic2 number: 573 loop: 1 play:)
				(waiterDoor setCycle: Beg self)
			)
			(2
				(waiterDoor
					setLoop: 1
					cel: (waiterDoor lastCel:)
					setPri: 33
				)
				(= cycles 2)
			)
			(3 (ego setCycle: Beg self))
			(4
				(ego
					normalize: 900 8
					cel: 3
					posn: 236 62
					setScaler: Scaler 100 82 122 62
				)
				(= ticks 20)
			)
			(5
				(ego setMotion: MoveTo 246 61 self)
			)
			(6 (ego setHeading: 0 self))
			(7 (= ticks 45))
			(8
				(theMusic2 number: 574 loop: 1 play:)
				(waiterDoor setCycle: Beg self)
			)
			(9
				(waiterDoor dispose:)
				(= cycles 2)
			)
			(10 (ego setHeading: 180 self))
			(11
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance enterFromDiningRoomScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego posn: 47 187 setMotion: MoveTo 77 134 self)
			)
			(1
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance knockOnWaiterScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(self setScript: (ScriptID 96 0) self 1)
			)
			(1
				(messager say: 13 4 0 0 self)
			)
			(2
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance getWashclothScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setHeading: 90 self)
			)
			(1
				(ego
					setSpeed: 8
					view: 901
					loop: 0
					cel: 0
					setCycle: CT 3 1 self
				)
			)
			(2
				(if local2
					((ScriptID 85 4)
						setReal: ((inventory at: 39) noun: 52 message: 37 yourself:) 0 15
					)
					(theGame changeScore: 3 222)
				)
				(ego get: 39 setCycle: End self)
			)
			(3
				(ego normalize: 900 8 1 cel: 0)
				(= cycles 2)
			)
			(4
				(if local2
					(messager say: 8 5 3 0 self)
				else
					(messager say: 8 5 4 0 self)
				)
			)
			(5
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance chillWashclothScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setHeading: 90 self)
			)
			(1
				(ego
					setSpeed: 8
					view: 901
					loop: 0
					cel: 0
					setCycle: CT 3 1 self
				)
			)
			(2
				(theGame changeScore: 6 221)
				(ego put: 39 curRoomNum setCycle: End self)
			)
			(3
				(ego normalize: 900 8 1 cel: 0)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance closeFrigeScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego
					view: 901
					loop: 0
					cel: 0
					cycleSpeed: 10
					setCycle: CT 3 1 self
				)
			)
			(1
				(ego setCycle: End)
				(frigeDoor setCycle: Beg self)
			)
			(2
				(theMusic2 number: 567 loop: 1 play:)
				(= cycles 2)
			)
			(3
				(ego normalize: 900 8 1 cel: 0)
				(frigeDoor cel: 0)
				(if
					(and
						(== (local4 owner?) curRoomNum)
						(!= (local4 message?) 37)
					)
					(= local3 (Timer setReal: curRoom 60))
				)
				(= local1 0)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance frigeDoor of Prop
	(properties
		approachX 268
		approachY 77
		x 280
		y 77
		view 560
		cycleSpeed 10
	)
	
	(method (init)
		(self setPri: 60)
		(super init:)
	)
	
	(method (doVerb theVerb)
		(frige doVerb: theVerb)
	)
	
	(method (onMe theObjOrX)
		(return
			(if (super onMe: theObjOrX)
				(if (== (theObjOrX message?) 4)
					(if local1
						(= approachX 274)
						(= approachY 83)
						(= sightAngle 0)
					else
						(= approachX 266)
						(= approachY 76)
						(= sightAngle 40)
					)
				else
					(= approachX 268)
					(= approachY 77)
					(= sightAngle 40)
				)
				(return 1)
			else
				(return 0)
			)
		)
	)
)

(instance dishwasher of Prop
	(properties
		x 33
		y 55
		view 565
		loop 8
	)
)

(instance belt of Prop
	(properties
		x 39
		y 72
		view 565
		loop 3
		cel 5
	)
)

(instance projector of Prop
	(properties
		noun 5
		x 108
		y 24
		priority 199
		fixPriority 1
		view 562
		detailLevel 3
	)
)

(instance suckFilter of Prop
	(properties
		x 39
		y 72
		view 565
		loop 3
	)
)

(instance shinnyFilter of Prop
	(properties
		x 40
		y 60
		priority 50
		fixPriority 1
		view 568
		loop 6
	)
	
	(method (init)
		(super init: &rest)
		(self ignoreActors: 1)
	)
)

(instance waiterDoor of Prop
	(properties
		x 228
		y 34
		view 564
		loop 1
		signal $4021
	)
)

(instance waiterDoor2 of Prop
	(properties
		x 238
		y 45
		view 564
		loop 1
		signal $4021
	)
)

(instance projectorLight of Prop
	(properties
		x 90
		y 28
		priority 198
		fixPriority 1
		view 562
		loop 1
		cel 1
	)
)

(instance dwButtons of Feature
	(properties
		noun 14
		sightAngle 40
		approachX 246
		approachY 61
		x 246
		y 57
	)
	
	(method (init)
		(super init: &rest)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 250 36 248 44 241 44 243 36
					yourself:
				)
			approachVerbs: 4
		)
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 4)
			(curRoom setScript: intoWaiter)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance cleaver of Feature
	(properties
		noun 11
		sightAngle 40
		x 228
		y 84
	)
	
	(method (init)
		(super init: &rest)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 231 56 241 56 236 73 223 73
					yourself:
				)
		)
	)
)

(instance frige of Feature
	(properties
		noun 8
		sightAngle 40
		approachX 268
		approachY 77
		x 275
		y 77
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					init: 263 66 266 17 301 28 317 45 305 114
					yourself:
				)
			approachVerbs: 4 5 36
		)
		(super init: &rest)
	)
	
	(method (doVerb theVerb)
		(cond 
			((== theVerb 4)
				(if local1
					(curRoom setScript: closeFrigeScr)
				else
					(curRoom setScript: openFrigeScr)
				)
			)
			((== theVerb 5)
				(cond 
					((not local1) (messager say: noun theVerb 2))
					((== (local4 owner?) curRoomNum) (curRoom setScript: getWashclothScr))
					(else (messager say: noun theVerb 5))
				)
			)
			((== theVerb 36)
				(if local1
					(curRoom setScript: chillWashclothScr)
				else
					(messager say: noun theVerb 2)
				)
			)
			((OneOf theVerb 37 35 2) (messager say: noun theVerb))
			(local1 (messager say: noun 0 1))
			(else (messager say: noun 0 2))
		)
	)
	
	(method (onMe theObjOrX)
		(return
			(if (super onMe: theObjOrX)
				(if (== (theObjOrX message?) 4)
					(if local1
						(= approachX 274)
						(= approachY 83)
						(= sightAngle 0)
					else
						(= approachX 266)
						(= approachY 76)
						(= sightAngle 40)
					)
				else
					(= approachX 268)
					(= approachY 77)
					(= sightAngle 40)
				)
				(return 1)
			else
				(return 0)
			)
		)
	)
)

(instance dumbWaiter of Feature
	(properties
		noun 13
		nsLeft 228
		nsTop 28
		nsRight 240
		nsBottom 45
		sightAngle 40
		approachX 233
		approachY 61
		x 233
		y 60
	)
	
	(method (init)
		(super init: &rest)
		(self approachVerbs: 4)
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 4)
			(curRoom setScript: knockOnWaiterScr)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance dishWasher of Feature
	(properties
		noun 2
		approachX 64
		approachY 113
	)
	
	(method (init)
		(super init: &rest)
		(self
			setPolygon:
				((Polygon new:)
					init: 39 82 39 68 71 53 83 54 86 66 53 92 39 83
					yourself:
				)
			approachVerbs: 32
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(32
				(curRoom setScript: cleanFilterScr)
			)
			(1 (messager say: noun theVerb))
			(2 (messager say: noun theVerb))
			(else  (sink doVerb: theVerb))
		)
	)
)

(instance sink of Feature
	(properties
		noun 3
		sightAngle 40
		approachX 64
		approachY 113
		x 28
		y 112
	)
	
	(method (init)
		(super init: &rest)
		(self
			setPolygon:
				((Polygon new:)
					init: 28 101 21 90 24 75 33 73 37 85 45 93 29 102
					yourself:
				)
			approachVerbs: 35 4
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(32
				(dishWasher doVerb: theVerb)
			)
			(35
				(curRoom setScript: wetWashclothScr)
			)
			(4
				(curRoom setScript: useSinkScr)
			)
			(else  (super doVerb: theVerb))
		)
	)
	
	(method (onMe theObjOrX)
		(return
			(if (super onMe: theObjOrX)
				(if (== (theObjOrX message?) 32)
					(self approachX: 45 approachY: 118)
				else
					(self approachX: 64 approachY: 113)
				)
				(return 1)
			else
				(return 0)
			)
		)
	)
)

(instance screen of Feature
	(properties
		noun 4
	)
	
	(method (init)
		(self
			setPolygon: ((Polygon new:)
				init: 10 74 1 0 90 0 87 31 89 38
				yourself:
			)
		)
		(super init: &rest)
	)
)

(instance tacoTruck of Feature
	(properties
		noun 6
		sightAngle 40
		approachX 184
		approachY 121
		x 143
		y 85
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					init:
						94
						122
						91
						91
						104
						78
						94
						46
						100
						41
						112
						43
						125
						41
						137
						30
						131
						17
						200
						16
						189
						91
						152
						133
					yourself:
				)
		)
		(super init: &rest)
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 48)
			(truckTire doVerb: theVerb)
		else
			(super doVerb: theVerb)
		)
	)
	
	(method (onMe theObjOrX)
		(return
			(if (super onMe: theObjOrX)
				(if (== (theObjOrX message?) 48)
					(self approachVerbs: 48)
				else
					(= _approachVerbs 0)
				)
				1
			else
				0
			)
		)
	)
)

(instance lowTire of Feature
	(properties
		noun 10
		sightAngle 40
		approachX 195
		approachY 97
		x 184
		y 90
	)
	
	(method (init)
		(super init: &rest)
		(self
			setPolygon:
				((Polygon new:)
					init: 189 72 191 90 179 96 177 89 175 81
					yourself:
				)
			approachVerbs: 4 1 2 5 6 48
		)
	)
)

(instance truckTire of Feature
	(properties
		noun 7
		sightAngle 40
		x 184
		y 121
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					init: 159 96 167 109 161 123 149 125 149 106
					yourself:
				)
			approachVerbs: 48 4
		)
		(super init: &rest)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(48
				(curRoom setScript: inflateBeaverScr)
			)
			(4
				(curRoom setScript: tireDeathScr)
			)
			(else  (super doVerb: theVerb))
		)
	)
	
	(method (onMe theObjOrX)
		(return
			(if (super onMe: theObjOrX)
				(if (== (theObjOrX message?) 4)
					(= approachX 162)
					(= approachY 128)
				else
					(= approachX 184)
					(= approachY 121)
				)
				(return 1)
			else
				0
			)
		)
	)
)

(instance garbage of Feature
	(properties
		noun 9
		sightAngle 40
		approachX 248
		approachY 136
		x 256
		y 130
	)
	
	(method (init)
		(super init: &rest)
		(self
			approachVerbs: 4 5 1
			setPolygon:
				((Polygon new:)
					init: 204 108 209 102 231 92 255 96 267 107 257 138 220 138 208 124
					yourself:
				)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(curRoom setScript: digInTrashScr)
			)
			(1
				(messager
					say:
						noun
						theVerb
						(cond 
							((Btst 277) 8)
							((not (Btst 81)) 7)
							(else 6)
						)
				)
			)
			(5
				(cond 
					((Btst 277) (messager say: noun theVerb 8))
					((Btst 81) (curRoom setScript: takeLardScr))
					(else (messager say: noun theVerb 7))
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance fixPriPoly of Polygon
	(properties)
	
	(method (init)
		(super init: 158 125 170 75 207 75 191 125)
	)
)

(instance movieSFX of Sound
	(properties
		flags $0001
	)
)

(instance dishWasherSFX of Sound
	(properties
		flags $0001
	)
)

(instance zipperSFX of Sound
	(properties
		flags $0001
		number 35
	)
)

(instance pissSFX of Sound
	(properties
		flags $0001
		number 30
		loop -1
	)
)
