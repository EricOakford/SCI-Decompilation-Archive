;;; Sierra Script 1.0 - (do not remove this comment)
(script# 660)
(include sci.sh)
(use Main)
(use rgDead)
(use KQ6Room)
(use Kq6Procs)
(use Scaler)
(use PolyPath)
(use Polygon)
(use Feature)
(use Sound)
(use Motion)
(use Actor)
(use System)

(public
	rm660 0
)

(local
	local0
	[local1 19] = [18 73 2 69 50 0 135 46 1 223 48 1 291 48 1 277 83 3]
	[local20 16] = [26 159 5 69 82 6 136 63 7 224 67 7 291 64 8]
	[local36 13] = [69 46 0 135 43 1 222 45 2 291 46 3]
)
(instance rm660 of KQ6Room
	(properties
		noun 4
		picture 660
		south 650
	)
	
	(method (init)
		(self
			addObstacle:
				((Polygon new:)
					type: 2
					init:
						0
						189
						0
						-10
						319
						-10
						319
						174
						241
						159
						236
						146
						187
						147
						107
						151
						81
						159
						102
						167
						116
						173
						108
						179
						75
						189
					yourself:
				)
		)
		(super init: &rest)
		(ego
			posn: 160 180
			setPri: 12
			setScale: Scaler 100 64 189 74
			actions: egoActions
			init:
		)
		(riverStyx init:)
		(boat addToPic:)
		(charon
			posn: (- (boat x?) 79) (+ (boat y?) 36)
			ignoreActors: 1
			init:
			stopUpd:
		)
		(spiritFeat init:)
		(proc70_1 torch @local1)
		(proc70_1 shimmer @local20)
		(proc70_1 glow @local36)
		(cast eachElementDo: #checkDetail)
		(theGame handsOn:)
	)
)

(instance fx of Sound
	(properties)
)

(instance splashSound of Sound
	(properties
		number 923
	)
)

(instance swimScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: PolyPath 99 182 self)
			)
			(1
				(theMusic number: 653 loop: 1 play:)
				(ego
					normal: 0
					view: 665
					setLoop: 0
					cel: 0
					posn: 89 187
					setCycle: CycleTo 6 1 self
				)
			)
			(2
				(splashSound play:)
				(ego setCycle: EndLoop self)
			)
			(3 (ego dispose:) (= cycles 2))
			(4 (messager say: 3 3 0 1 self))
			(5 (messager say: 3 3 0 2 self))
			(6 (EgoDead 31))
		)
	)
)

(instance missedCharonScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 2))
			(1 (messager say: 6 0 9 1 self))
			(2
				(theMusic number: 601 loop: 1 play:)
				(if
					(>
						(GetDistance
							(ego x?)
							(ego y?)
							(charon x?)
							(charon y?)
						)
						40
					)
					(ego setMotion: PolyPath 114 160 self)
				else
					(= cycles 1)
				)
			)
			(3 (messager say: 6 0 9 2 self))
			(4
				(charon view: 667 setLoop: 0 setCycle: EndLoop self)
				(theGlobalSound number: 662 loop: 1 play:)
			)
			(5
				(charon setLoop: 1 setCycle: Forward)
				(ego
					normal: 0
					view: 749
					setLoop: 3
					cel: 0
					setCycle: EndLoop self
				)
			)
			(6
				(ego setLoop: 5 cel: 0 setCycle: EndLoop)
				(charon setLoop: 0)
				(charon cel: (charon lastCel:) setCycle: BegLoop self)
			)
			(7 (messager say: 6 0 9 3 self))
			(8 (EgoDead 39))
		)
	)
)

(instance buyTransportScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego
					illegalBits: 0
					ignoreActors: 1
					setMotion: MoveTo 106 147 self
				)
			)
			(1 (= cycles 2))
			(2 (messager say: 2 7 0 1 self))
			(3
				(theGame givePoints: 3)
				(ego
					normal: 0
					put: 7 -1
					view: 661
					setLoop: 0
					cel: 0
					posn: 110 150
					setCycle: EndLoop self
				)
				(charon setCycle: EndLoop)
			)
			(4
				(fx number: 661 loop: 1 play:)
				(ego
					reset: 7
					setPri: 12
					posn: (- (ego x?) 2) (- (ego y?) 1)
				)
				(charon setCycle: BegLoop self)
			)
			(5 (= seconds 1))
			(6 (messager say: 2 7 0 2 self))
			(7 (= seconds 1))
			(8
				(ego setMotion: MoveTo 124 144 self)
			)
			(9
				(ego
					normal: 0
					view: 661
					setLoop: 1
					cel: 0
					setCycle: EndLoop self
				)
			)
			(10 (= ticks 15))
			(11
				(boat dispose:)
				(charon view: 668 setLoop: 0 cel: 0 setCycle: EndLoop self)
				(UnLoad 128 663)
			)
			(12
				(ego setPri: -1 dispose:)
				(charon dispose:)
				(if global169
					(DrawPic 660 15)
				else
					(curRoom drawPic: 660)
				)
				(boat
					signal: 16384
					ignoreActors: 1
					view: 662
					setLoop: 0
					cel: 0
					setScale: Scaler 100 50 137 59
					init:
				)
				(UnLoad 128 668)
				(UnLoad 128 661)
				(= ticks 10)
			)
			(13 (boat cel: 1) (= ticks 10))
			(14
				(boat setLoop: 1 cel: 0)
				(= ticks 10)
			)
			(15 (boat cel: 1) (= ticks 10))
			(16
				(boat view: 6621 setLoop: 0 cel: 0)
				(= ticks 10)
			)
			(17
				(boat
					ignoreActors: 1
					ignoreHorizon: 1
					setPri: 3
					setMotion: MoveTo 345 63 self
				)
			)
			(18 (curRoom newRoom: 670))
		)
	)
)

(instance getWaterScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: MoveTo 119 184 self)
			)
			(1
				(ego
					normal: 0
					view: 666
					setLoop: 0
					cel: 0
					posn: 110 189
					setCycle: CycleTo 3 1 self
				)
			)
			(2
				(fx number: 924 loop: 1 play:)
				(ego setCycle: EndLoop self)
			)
			(3 (= cycles 2))
			(4
				(messager say: 3 44 (if (Btst 68) 4 else 3) 0 self)
			)
			(5
				(ego reset: 1 setPri: 12 posn: 119 184)
				(= cycles 1)
			)
			(6
				(UnLoad 128 666)
				(Bset 58)
				(theGame givePoints: 1)
				(self dispose:)
				(theGame handsOn:)
			)
		)
	)
)

(instance playFlute of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(self setScript: (ScriptID 85 0) self)
			)
			(1
				(messager say: 8 31 0 0 self)
			)
			(2
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance playNightingale of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(self setScript: (ScriptID 93 0) self)
			)
			(1
				(messager say: 8 37 0 0 self)
			)
			(2
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance tryToBoardScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if
					(>
						(GetDistance
							(ego x?)
							(ego y?)
							(charon x?)
							(charon y?)
						)
						20
					)
					(ego setMotion: MoveTo 112 149 self)
				else
					(= cycles 1)
				)
			)
			(1 (Face ego charon self))
			(2
				(charonHead
					posn: (- (charon x?) 5) (- (charon y?) 43)
					init:
					setCycle: Forward
				)
				(= cycles 15)
			)
			(3
				(charonHead dispose:)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance talkCharonScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if
					(>
						(GetDistance
							(ego x?)
							(ego y?)
							(charon x?)
							(charon y?)
						)
						20
					)
					(ego setMotion: MoveTo 112 149 self)
				else
					(= cycles 1)
				)
			)
			(1 (Face ego charon self))
			(2
				(messager say: 2 register 0 1 self)
			)
			(3
				(if (!= (charon cel?) 3)
					(charon setCycle: EndLoop self)
				else
					(= cycles 1)
				)
			)
			(4
				(charonHead
					posn: (- (charon x?) 5) (- (charon y?) 43)
					init:
					setCycle: Forward
				)
				(= cycles 15)
			)
			(5
				(if (OneOf register 48 2 37)
					(messager say: 2 register 0 2 self)
				else
					(= cycles 1)
				)
			)
			(6
				(charonHead dispose:)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance charonHead of Prop
	(properties
		view 663
		loop 2
		priority 12
		signal $4010
	)
)

(instance torch of Prop
	(properties
		noun 7
		view 660
		loop 8
		priority 5
		signal $4010
		detailLevel 1
	)
)

(instance shimmer of Prop
	(properties
		noun 3
		view 660
		loop 5
		priority 1
		signal $4010
		detailLevel 1
	)
	
	(method (doVerb theVerb)
		(riverStyx doVerb: theVerb &rest)
	)
)

(instance glow of Prop
	(properties
		noun 4
		view 664
		signal $4010
		detailLevel 1
	)
)

(instance spiritFeat of Feature
	(properties
		noun 5
	)
	
	(method (init)
		(= onMeCheck
			(= local0
				((Polygon new:)
					type: 0
					init:
						132
						117
						138
						121
						151
						124
						169
						115
						186
						122
						193
						130
						185
						139
						177
						141
						119
						142
						119
						131
						127
						119
					yourself:
				)
			)
		)
		(super init: &rest)
	)
)

(instance boat of Actor
	(properties
		x 156
		y 108
		noun 1
		view 663
		illegalBits $0000
	)
	
	(method (init)
		(super init: &rest)
		(walkHandler addToFront: self)
	)
	
	(method (dispose)
		(super dispose:)
		(walkHandler delete: self)
	)
	
	(method (handleEvent event)
		(cond 
			((& (event type?) evMOVE) (super handleEvent: event))
			((spiritFeat onMe: event) (spiritFeat handleEvent: event))
			(else (super handleEvent: event))
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(3
				(theGame handsOff:)
				(curRoom setScript: tryToBoardScript)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance charon of Actor
	(properties
		noun 2
		view 663
		loop 1
	)
	
	(method (init)
		(super init: &rest)
		(cond 
			((== ((ScriptID 0 6) client?) (ScriptID 70 0)) 0)
			((Btst 121)
				(theGame handsOff:)
				(curRoom setScript: missedCharonScript)
			)
			(else ((ScriptID 0 6) setReal: (ScriptID 70 0) 0 2 0))
		)
		(walkHandler addToFront: self)
	)
	
	(method (doit)
		(super doit:)
		(cond 
			((curRoom script?) 0)
			((Btst 121)
				(theGame handsOff:)
				(curRoom setScript: missedCharonScript)
			)
			(
				(and
					(< (self distanceTo: ego) 40)
					(!= cel 3)
					(not cycler)
				)
				(self setCycle: EndLoop)
			)
			(
			(and (> (self distanceTo: ego) 40) cel (not cycler)) (self setCycle: BegLoop))
		)
	)
	
	(method (dispose)
		(super dispose:)
		(walkHandler delete: self)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(7
				(theGame handsOff:)
				((ScriptID 0 6) dispose:)
				(curRoom setScript: buyTransportScr)
			)
			(3
				(theGame handsOff:)
				(curRoom setScript: tryToBoardScript)
			)
			(31
				(theGame handsOff:)
				(curRoom setScript: playFlute)
			)
			(37
				(theGame handsOff:)
				(curRoom setScript: playNightingale)
			)
			(else 
				(if (OneOf theVerb 2 5 1 13 28 8 30 48 50 16 35)
					(theGame handsOff:)
					(curRoom setScript: talkCharonScript 0 theVerb)
				else
					(theGame handsOff:)
					(curRoom setScript: talkCharonScript 0 0)
				)
			)
		)
	)
)

(instance riverStyx of Feature
	(properties
		noun 3
		onMeCheck $0002
	)
	
	(method (init)
		(super init: &rest)
		(walkHandler addToFront: self)
	)
	
	(method (dispose)
		(super dispose:)
		(walkHandler delete: self)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(44
				(if (not (Btst 58))
					(theGame handsOff:)
					(curRoom setScript: getWaterScr)
				else
					(messager say: noun theVerb 5)
				)
			)
			(3
				(theGame handsOff:)
				(curRoom setScript: swimScr)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance egoActions of Actions
	(properties)
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(31
					(theGame handsOff:)
					(curRoom setScript: playFlute)
				)
				(37
					(theGame handsOff:)
					(curRoom setScript: playNightingale)
				)
				(else  (return 0))
			)
		)
	)
)
