;;; Sierra Script 1.0 - (do not remove this comment)
(script# 780)
(include sci.sh)
(use Main)
(use CastleRoom)
(use Kq6Procs)
(use Conv)
(use Scaler)
(use PolyPath)
(use Polygon)
(use Feature)
(use LoadMany)
(use StopWalk)
(use DPath)
(use Motion)
(use Actor)
(use System)

(public
	rm780 0
)

(local
	local0
	local1
	local2
	local3
)
(instance rm780 of CastleRoom
	(properties
		noun 3
		picture 780
		style $000a
		vanishingX 183
		vanishingY 98
	)
	
	(method (init)
		(LoadMany 128 7881 789 788)
		(ego init: posn: 20 157 setScale: Scaler 100 70 190 140)
		(self
			addObstacle:
				((Polygon new:)
					type: 2
					init:
						-10
						-10
						329
						-10
						329
						180
						319
						180
						301
						169
						282
						169
						259
						152
						218
						152
						218
						147
						200
						147
						200
						144
						134
						144
						134
						149
						69
						149
						57
						155
						45
						155
						35
						159
						35
						163
						0
						178
						-10
						178
					yourself:
				)
		)
		(features add: chandelierF rugF eachElementDo: #init)
		(super init: &rest)
		(if (!= ((inventory at: 25) owner?) 750)
			(theClown init:)
		)
		(candles init:)
		(door init: stopUpd:)
		(doorJam1 addToPic:)
		(doorJam2 addToPic:)
		(fireplace addToPic:)
		(otherFireplace addToPic:)
		(bed addToPic:)
		(chair init:)
		(fire setCycle: Forward init:)
		((ego scaler?) doit:)
		(self setScript: enterRoom)
		(if (Btst 10) (theMusic fadeTo: 780 -1))
	)
	
	(method (doit &tmp temp0)
		(= temp0 (ego onControl: 1))
		(cond 
			(script 0)
			((& temp0 $4000) (curRoom newRoom: 840))
		)
		(super doit: &rest)
	)
	
	(method (dispose)
		(if local3 ((ScriptID 80 0) weddingRemind: 1))
		(if (not ((ScriptID 80 0) tstFlag: 710 512))
			((ScriptID 80 0) setFlag: 710 512)
		)
		(super dispose:)
		(DisposeScript 964)
	)
	
	(method (warnUser param1)
		(switch param1
			(1 (= local3 1))
		)
	)
)

(instance enterRoom of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (Btst 10)
					(ego setMotion: MoveTo 44 157 self)
				else
					(ego setMotion: MoveTo 64 157 self)
				)
			)
			(1 (door setCycle: BegLoop self))
			(2
				(soundFx2 number: 902 setLoop: 1 play:)
				(door stopUpd:)
				(= register 0)
				(if (cast contains: (ScriptID 80 5))
					((ScriptID 81 0) resetGuard: (ScriptID 80 5) 1)
					((ScriptID 80 5) dispose:)
					(= register 1)
				)
				(if (cast contains: (ScriptID 80 6))
					((ScriptID 81 0) resetGuard: (ScriptID 80 6) 2)
					((ScriptID 80 6) dispose:)
					(= register 1)
				)
				(if (not (if register (Btst 10))) (++ state))
				(= cycles 2)
			)
			(3
				(messager say: 1 0 19 0 self)
			)
			(4
				(cond 
					((not (Btst 10)) (curRoom setScript: callGuards))
					((not ((ScriptID 80 0) tstFlag: 710 512)) (= local2 1) (self setScript: turnClownAround self))
					(else (theGame handsOn:) (self dispose:))
				)
			)
			(5
				(ego
					setMotion: PolyPath (theClown approachX?) (theClown approachY?) self
				)
			)
			(6 (= cycles 10))
			(7
				(if ((ScriptID 80 0) tstFlag: 710 256)
					(if ((ScriptID 80 0) tstFlag: 711 32)
						(roomConv add: -1 1 0 16)
					else
						(roomConv add: -1 1 0 9 1)
					)
					(Bset 155)
					(roomConv
						add: -1 1 0 9 2
						add: -1 1 0 9 3
						add: -1 1 0 9 4
						add: -1 1 0 9 5
						add: -1 1 0 9 6
						add: -1 1 0 9 7
					)
					(if (Btst 52)
						(roomConv add: -1 1 0 4)
					else
						(roomConv add: -1 1 0 10)
					)
				else
					(if ((ScriptID 80 0) tstFlag: 711 32)
						(roomConv add: -1 1 0 16)
					else
						(roomConv add: -1 1 0 8 1)
					)
					(roomConv
						add: -1 1 0 8 2
						add: -1 1 0 8 3
						add: -1 1 0 8 4
						add: -1 1 0 8 5
						add: -1 1 0 8 6
						add: -1 1 0 8 7
						add: -1 1 0 8 8
						add: -1 1 0 8 9
						add: -1 1 0 8 10
						add: -1 1 0 8 11
					)
					(if (Btst 52)
						(roomConv add: -1 1 0 2)
					else
						(roomConv add: -1 1 0 10)
					)
				)
				(roomConv init: self)
			)
			(8
				(chair priority: 1)
				(theClown priority: 0 loop: 2 cel: 2 setCycle: BegLoop self)
			)
			(9
				(= local2 0)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance turnClownAround of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theClown loop: 2 cel: 0 setCycle: CycleTo 2 1 self)
			)
			(1
				(chair priority: 0)
				(theClown priority: 1 setCycle: EndLoop self)
			)
			(2 (self dispose:))
		)
	)
)

(instance callGuards of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(self setScript: callGuardsConvScr self)
			)
			(1
				(if (Btst 154)
					(roomConv add: -1 1 0 18 1 add: -1 1 0 18 2 init: self)
				else
					(roomConv add: -1 1 0 1 1 add: -1 1 0 1 2 init: self)
				)
			)
			(2
				(self setScript: callGuardsConvScr self)
			)
			(3
				(if (Btst 154)
					(roomConv add: -1 1 0 18 3 init: self)
				else
					(roomConv add: -1 1 0 1 3 init: self)
				)
			)
			(4
				(self setScript: callGuardsConvScr self)
			)
			(5
				(if (Btst 154)
					(roomConv
						add: -1 1 0 18 4
						add: -1 1 0 18 5
						add: -1 1 0 18 6
						init: self
					)
				else
					(roomConv
						add: -1 1 0 1 4
						add: -1 1 0 1 5
						add: -1 1 0 1 6
						init: self
					)
				)
			)
			(6
				(Bset 154)
				(curRoom spotEgo: (ScriptID 80 5))
			)
		)
	)
)

(instance callGuardsConvScr of Script
	(properties)
	
	(method (dispose)
		(= start (+ state 1))
		(super dispose: &rest)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(chair hide:)
				(theClown
					view: 7881
					loop: 0
					cel: 0
					posn: 242 154 0
					setCycle: EndLoop self
				)
			)
			(1
				(chair
					view: 7881
					loop: 3
					cel: 0
					posn: 266 149
					setPri: 14
					show:
					stopUpd:
				)
				(theClown
					loop: 1
					cel: 0
					posn: 227 152 0
					setCycle: CycleTo 4 1 self
				)
			)
			(2 (self dispose:))
			(3
				(theClown setCycle: EndLoop self)
			)
			(4
				(theClown
					loop: 2
					cel: 6
					posn: 221 151
					setCycle: CycleTo 3 -1 self
				)
			)
			(5 (self dispose:))
			(6
				(theClown loop: 2 cel: 3 setCycle: BegLoop self)
			)
			(7 (= cycles 10))
			(8
				(soundFx2 number: 901 loop: 1 play:)
				(door setCycle: EndLoop self)
			)
			(9
				(soundFx2 stop:)
				((ScriptID 80 5)
					setScale:
					scaleX: 110
					scaleY: 110
					posn: 13 158
					init:
					setMotion: MoveTo 39 158 self
				)
			)
			(10 (self dispose:))
		)
	)
)

(instance jolloScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theClown loop: 2 cel: 0 setCycle: CycleTo 2 1 self)
			)
			(1 (theClown doVerb: register))
			(2
				(chair priority: 1)
				(theClown
					loop: 2
					cel: 2
					priority: 0
					posn: 232 144
					setCycle: BegLoop self
				)
			)
			(3
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance showLamp of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego
					loop:
						(switch register
							(43 2)
							(57 1)
							(58 2)
							(59 0)
							(60 2)
							(96 1)
							(56 0)
						)
				)
				(ego
					normal: 0
					view: 789
					setScale:
					scaleX: 104
					scaleY: 104
					cel: 0
					cycleSpeed: 8
				)
				(= cycles (theClown cycleSpeed?))
			)
			(1
				(if (not ((ScriptID 80 0) tstFlag: 709 16))
					(chair priority: 0)
					(theClown loop: 5 cel: 0 posn: 225 142 priority: 1)
				)
				(= cycles (theClown cycleSpeed?))
			)
			(2
				(if ((ScriptID 80 0) tstFlag: 709 16)
					(if (OneOf register 43 57)
						(roomConv add: -1 4 register 12)
					else
						(= cycles 1)
					)
				else
					((ScriptID 80 0) setFlag: 709 16)
					(switch register
						(43
							(roomConv add: -1 4 register 11 0)
						)
						(57
							(self setScript: gaveNewLamp self register)
						)
						(else 
							(theGame givePoints: 3)
							(self setScript: gaveReplicaLamp self register)
						)
					)
				)
				(if (roomConv size?) (roomConv init: self))
			)
			(3
				(theGame handsOn:)
				(ego
					reset: 0
					posn: (theClown approachX?) (theClown approachY?)
				)
				(self dispose:)
			)
		)
	)
)

(instance gaveNewLamp of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(messager say: 4 register 11 1 self)
			)
			(1
				(self setScript: badLampConvScr self)
			)
			(2
				(messager say: 4 register 11 2 self oneOnly: 0)
			)
			(3 (self dispose:))
		)
	)
)

(instance gaveReplicaLamp of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(messager say: 4 register 11 1 self)
			)
			(1
				(self setScript: goodLampConvScr self)
			)
			(2
				(messager say: 4 register 11 2 self oneOnly: 0)
			)
			(3
				(self setScript: goodLampConvScr self)
			)
			(4 (self dispose:))
		)
	)
)

(instance goodLampConvScr of Script
	(properties)
	
	(method (dispose)
		(= start (+ state 1))
		(super dispose: &rest)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (ego setCycle: CycleTo 1 1 self))
			(1
				(chair priority: 0)
				(theClown
					view: 788
					loop: 5
					cel: 0
					priority: 1
					setCycle: EndLoop self
				)
			)
			(2
				(ego cel: 3)
				(theClown setCycle: CycleTo 2 -1 self)
			)
			(3
				(ego setCycle: EndLoop)
				(theClown loop: 9 setCycle: EndLoop self)
			)
			(4
				(ego
					reset: 0
					posn: (theClown approachX?) (theClown approachY?)
				)
				(= seconds 3)
			)
			(5
				(theClown loop: 6 setCycle: CycleTo 5 1 self)
			)
			(6
				(theClown loop: 5 cel: 0)
				(self dispose:)
			)
			(7
				(theClown
					view: 717
					setCycle: StopWalk -1
					posn: 218 151 0
					setPri: -1
					setScale:
						Scaler
						(curRoom maxScaleSize?)
						(curRoom minScaleSize?)
						(curRoom maxScaleY?)
						(curRoom minScaleY?)
					cycleSpeed: 6
					moveSpeed: 6
					setStep: 5 3
					setMotion: DPath 205 157 42 158 self
				)
			)
			(8
				(soundFx2 number: 901 loop: 1 play:)
				(door setCycle: EndLoop self)
			)
			(9
				(soundFx2 stop:)
				(theClown setMotion: MoveTo 12 158 self)
			)
			(10
				(theClown hide:)
				(door setCycle: BegLoop self)
			)
			(11
				(soundFx2 number: 902 loop: 1 play:)
				(theClown setScript: followTimer)
				(theGame handsOn:)
				(ego put: 25 750)
				(self dispose:)
			)
		)
	)
)

(instance followTimer of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				((ScriptID 80 0) setFlag: 711 -32768)
				(= seconds 11)
			)
			(1
				((ScriptID 80 0) clrFlag: 711 -32768)
				(theClown dispose:)
			)
		)
	)
)

(instance badLampConvScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (ego setCycle: CycleTo 1 1 self))
			(1
				(ego setCycle: BegLoop)
				(theClown view: 788 loop: 4 cel: 0 setCycle: EndLoop self)
			)
			(2
				(ego
					reset: 0
					posn: (theClown approachX?) (theClown approachY?)
				)
				(theClown cel: 0)
				(= cycles (theClown cycleSpeed?))
			)
			(3 (self dispose:))
		)
	)
)

(instance lookThruKeyhole of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				((ScriptID 82 1)
					noun: 5
					actions: keyHoleActions
					init: 797 0 0 92 54
				)
				(= cycles 2)
			)
			(1 (messager say: 5 1 14))
		)
	)
)

(instance doorJam1 of View
	(properties
		x 35
		y 134
		noun 5
		sightAngle 40
		approachX 44
		approachY 157
		view 788
		loop 1
		cel 1
		signal $5010
	)
	
	(method (init)
		(super init: &rest)
		(self approachVerbs: 5)
	)
	
	(method (doVerb)
		(door noun: 5)
		(door doVerb: &rest)
	)
)

(instance doorJam2 of View
	(properties
		x 35
		y 134
		noun 5
		approachX 44
		approachY 157
		view 788
		loop 1
		cel 3
		priority 12
		signal $5010
	)
	
	(method (init)
		(super init: &rest)
		(self approachVerbs: 5)
	)
	
	(method (doVerb)
		(door noun: 5)
		(door doVerb: &rest)
	)
)

(instance door of Prop
	(properties
		x 28
		y 154
		z 20
		noun 5
		approachX 50
		approachY 157
		view 788
		cel 5
		signal $5000
	)
	
	(method (init)
		(super init: &rest)
		(self approachVerbs: 5)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(5
				(theGame handsOff:)
				(soundFx2 number: 901 loop: 1 play:)
				(door setCycle: EndLoop self)
			)
			(1
				(if (not local1)
					(++ local1)
					(= _approachVerbs
						(| _approachVerbs (approachCode doit: 1))
					)
					(messager say: noun theVerb 13 0)
				else
					(curRoom setScript: (ScriptID 82) 0 lookThruKeyhole)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
	
	(method (cue)
		(soundFx2 stop:)
		(proc80_2 4)
	)
)

(instance bed of View
	(properties
		x 122
		y 135
		view 788
		loop 1
		signal $5010
	)
	
	(method (onMe event &tmp temp0 temp1)
		(= temp0 (event x?))
		(= temp1 (event y?))
		(if
			(and
				(super onMe: temp0 temp1)
				(= temp0 (- temp0 nsLeft))
				(= temp1 (- temp1 nsTop))
			)
			(if (and (> temp0 82) (= noun 12)) else (= noun 7))
		)
	)
)

(instance fireplace of View
	(properties
		x 252
		y 134
		view 788
		loop 1
		cel 2
		signal $5010
	)
	
	(method (onMe event &tmp temp0 temp1)
		(= temp0 (event x?))
		(= temp1 (event y?))
		(if
			(and
				(super onMe: temp0 temp1)
				(= temp0 (- temp0 nsLeft))
				(= temp1 (- temp1 nsTop))
			)
			(if
				(and
					(< temp0 61)
					(or
						(and
							(<= 14 temp0)
							(<= temp0 35)
							(<= 2 temp1)
							(<= temp1 27)
							(= noun 13)
						)
						(and
							(<= 39 temp0)
							(<= temp0 50)
							(<= 17 temp1)
							(<= temp1 32)
							(= noun 14)
						)
						(= noun 10)
					)
				)
			else
				(= noun 9)
			)
		)
	)
)

(instance theClown of Actor
	(properties
		x 232
		y 144
		z -10
		noun 4
		approachX 201
		approachY 151
		view 788
		loop 2
		signal $5010
		cycleSpeed 13
	)
	
	(method (init)
		(super init: &rest)
		(self approachVerbs: 43 56 57 2)
	)
	
	(method (doVerb theVerb)
		(cond 
			((OneOf theVerb 1 5) (super doVerb: theVerb &rest))
			((and (not script) (not local2)) (theGame handsOff:) (self setScript: jolloScr 0 theVerb))
			((OneOf theVerb 43 56 57 58 59 60 96)
				(= theVerb (Max 43 (Min 57 theVerb)))
				(script setScript: showLamp jolloScr theVerb)
			)
			(
				(and
					((ScriptID 80 0) tstFlag: 710 256)
					(not (Btst 155))
					(== theVerb 2)
				)
				(Bset 155)
				(messager say: noun theVerb 15 0 jolloScr)
			)
			(else
				(if script (jolloScr cycles: 10))
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance chair of View
	(properties
		x 226
		y 150
		noun 11
		view 788
		loop 7
		priority 1
		signal $5010
	)
)

(instance otherFireplace of View
	(properties
		x 294
		y 124
		view 788
		loop 1
		cel 4
		priority 12
		signal $4010
	)
)

(instance candles of Prop
	(properties
		x 165
		y 84
		noun 15
		view 788
		loop 8
	)
	
	(method (init)
		(self setCycle: Forward)
		(super init: &rest)
	)
)

(instance chandelierF of Feature
	(properties
		noun 15
		onMeCheck $0004
	)
)

(instance rugF of Feature
	(properties
		noun 8
		onMeCheck $0002
	)
)

(instance fire of Prop
	(properties
		x 274
		y 140
		noun 9
		sightAngle 40
		view 788
		loop 10
		cycleSpeed 8
	)
)

(instance roomConv of Conversation
	(properties)
)

(instance keyHoleActions of Actions
	(properties)
	
	(method (doVerb theVerb &tmp temp0)
		(= temp0 1)
		(switch theVerb
			(1
				(messager say: 5 theVerb 14 2)
			)
			(else  (= temp0 0))
		)
		(return temp0)
	)
)
