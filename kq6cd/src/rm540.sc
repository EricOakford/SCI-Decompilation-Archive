;;; Sierra Script 1.0 - (do not remove this comment)
(script# 540)
(include sci.sh)
(use Main)
(use KQ6Room)
(use Kq6Procs)
(use Conv)
(use Scaler)
(use PolyPath)
(use Polygon)
(use Feature)
(use Reverse)
(use Sound)
(use Motion)
(use Actor)
(use System)

(public
	rm540 0
)

(instance rm540 of KQ6Room
	(properties
		noun 2
		picture 540
		south 510
	)
	
	(method (init &tmp temp0)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: 2
					init:
						268
						189
						314
						118
						307
						100
						253
						92
						190
						93
						156
						96
						87
						97
						89
						103
						62
						105
						43
						112
						29
						125
						41
						149
						102
						157
						58
						187
						0
						182
						0
						0
						319
						0
						319
						189
					yourself:
				)
				((Polygon new:)
					type: 2
					init:
						281
						115
						250
						120
						211
						122
						146
						120
						114
						117
						110
						104
						131
						101
						206
						100
						273
						107
					yourself:
				)
		)
		(super init: &rest)
		(theMusic number: 540 flags: 1 loop: -1 play:)
		(theGame handsOff:)
		(castle init:)
		(hedge init:)
		(beastPath init:)
		(fountain init:)
		(gateFeat init:)
		(spout init: setCycle: Forward)
		(spray init: setCycle: Forward)
		(gate init:)
		(ego
			init:
			reset: 3
			posn: 125 187
			setScale: Scaler 100 68 190 80
		)
		(if (ego scaler?) ((ego scaler?) doit:))
		(cond 
			((== prevRoomNum 250)
				(beauty
					init:
					setScale: Scaler 100 68 190 100
					setStep: 4 3
					setCycle: Walk
				)
				(beast init:)
				((ScriptID 0 5) dispose:)
				(weasel init: setCycle: Walk)
				(glint init: hide:)
				(self setScript: beautyScript)
			)
			((not (Btst 46))
				(beast init:)
				(self setScript: beastScript)
				(ego setMotion: MoveTo 116 131)
			)
			(else (theGame handsOn:) (ego setMotion: MoveTo 116 131))
		)
	)
	
	(method (dispose)
		(super dispose:)
		(DisposeScript 969)
	)
	
	(method (newRoom n)
		(theMusic fade:)
		(super newRoom: n &rest)
	)
)

(instance gateFx of Sound
	(properties)
)

(instance beast of Actor
	(properties
		x 72
		y 62
		view 533
		loop 2
		signal $6000
		illegalBits $0000
	)
)

(instance beauty of Actor
	(properties
		x 158
		y 189
		view 252
		loop 3
		signal $6000
	)
)

(instance weasel of Actor
	(properties
		x 301
		y 163
		view 545
		loop 1
	)
)

(instance glint of Prop
	(properties
		view 902
		signal $4000
	)
)

(instance spout of Prop
	(properties
		x 199
		y 89
		view 540
		cel 2
		priority 8
		signal $4010
	)
	
	(method (doVerb)
		(fountain doVerb: &rest)
	)
)

(instance spray of Prop
	(properties
		x 198
		y 89
		view 540
		loop 1
		cel 2
		priority 8
		signal $0010
	)
	
	(method (doVerb)
		(fountain doVerb: &rest)
	)
)

(instance gate of Prop
	(properties
		x 72
		y 62
		view 5402
		signal $4000
	)
	
	(method (doVerb theVerb)
		(gateFeat doVerb: theVerb &rest)
	)
)

(instance castle of Feature
	(properties
		noun 6
		onMeCheck $0008
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(if (Btst 43)
					(messager say: noun theVerb 4 1)
				else
					(messager say: noun theVerb 3 1)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance hedge of Feature
	(properties
		noun 7
		onMeCheck $0002
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(5
				(if (Btst 43)
					(messager say: noun theVerb 4 1)
				else
					(messager say: noun theVerb 3 1)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance fountain of Feature
	(properties
		x 198
		y 127
		noun 5
		onMeCheck $0010
		approachX 196
		approachY 115
	)
	
	(method (doVerb theVerb)
		(if (OneOf theVerb 57 58 59 60 96 56)
			(if (Btst 77)
				(messager say: noun theVerb 8)
			else
				(messager say: noun theVerb 9)
			)
		else
			(switch theVerb
				(5
					(theGame handsOff:)
					(curRoom setScript: getWater 0 0)
				)
				(43
					(cond 
						((& global161 $0001) (messager say: noun 43 5))
						((not (Btst 43)) (messager say: noun theVerb 3 1))
						((not (Btst 77)) (messager say: 5 43 9 0))
						(
							(or
								(not (& global161 $0004))
								(not (& global161 $0002))
							)
							(messager say: noun theVerb 10)
						)
						(else (theGame handsOff:) (curRoom setScript: getWater 0 43))
					)
				)
				(44
					(cond 
						((not (Btst 43)) (messager say: noun 43 3 1))
						((Btst 77) (messager say: noun 56 8))
						(else (messager say: noun 56 9))
					)
				)
				(24
					(if (not (Btst 43))
						(messager say: noun 43 3 1)
					else
						(messager say: noun 26 0)
					)
				)
				(0
					(if (Btst 43)
						(messager say: noun theVerb 4 1)
					else
						(messager say: noun theVerb 3 1)
					)
				)
				(else 
					(super doVerb: theVerb &rest)
				)
			)
		)
	)
)

(instance beastPath of Feature
	(properties
		noun 4
		onMeCheck $0020
	)
)

(instance gateFeat of Feature
	(properties
		noun 8
		onMeCheck $0004
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(5
				(if (Btst 43)
					(messager say: noun theVerb 4 1)
				else
					(messager say: noun theVerb 3 1)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance beastScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Load rsVIEW 543)
				(gate hide:)
				(beast
					posn: 72 62
					view: 533
					setLoop: 0
					ignoreActors: 1
					setCycle: EndLoop self
				)
			)
			(1
				(beast
					view: 5406
					posn: 96 98
					setLoop: 0
					cel: 0
					setCycle: EndLoop self
				)
				(gateFx number: 542 play:)
			)
			(2
				(gateFx number: 543 play:)
				(gate show:)
				(ego setMotion: MoveTo 107 130 self)
				(beast
					view: 542
					setScale: Scaler 100 68 190 100
					setCycle: Walk
					setStep: 4 3
					setLoop: 2
					y: (+ (beast y?) 3)
					setMotion: MoveTo 69 129 self
				)
			)
			(3 0)
			(4
				(beast view: 543 setLoop: 3 cel: 0)
				(= cycles 2)
			)
			(5 (Face ego beast self))
			(6 (= cycles 2))
			(7
				(myConv
					add: -1 1 0 1 1
					add: -1 1 0 1 2
					add: -1 1 0 1 3
					add: -1 1 0 1 4
					add: -1 1 0 1 5
					init: self
				)
			)
			(8 (beast setCycle: EndLoop self))
			(9
				(myConv add: -1 1 0 1 6 add: -1 1 0 1 7 init: self)
			)
			(10 (beast setCycle: BegLoop self))
			(11
				(myConv
					add: -1 1 0 1 8
					add: -1 1 0 1 9
					add: -1 1 0 1 10
					add: -1 1 0 1 11
					add: -1 1 0 1 12
					add: -1 1 0 1 13
					add: -1 1 0 1 14
					add: -1 1 0 1 15
					add: -1 1 0 1 16
					init: self
				)
			)
			(12
				(myConv
					add: -1 1 0 1 17
					add: -1 1 0 1 18
					add: -1 1 0 1 19
					init: self
				)
			)
			(13
				(ego hide:)
				(beast setLoop: 2 setCycle: EndLoop self)
			)
			(14
				(myConv
					add: -1 1 0 1 20
					add: -1 1 0 1 21
					add: -1 1 0 1 22
					init: self
				)
			)
			(15
				(ego show:)
				(beast view: 542 setLoop: 0 setCycle: Walk)
				(= cycles 2)
			)
			(16
				(myConv add: -1 1 0 1 23 init: self)
			)
			(17
				(beast setLoop: 3 setMotion: MoveTo 96 98 self)
				(ego setHeading: 315)
			)
			(18
				(ego setHeading: 0)
				(gate hide:)
				(beast
					view: 532
					setScale: 0
					setLoop: 1
					cel: 0
					setCycle: EndLoop self
				)
				(gateFx number: 542 play:)
			)
			(19
				(beast setLoop: 2 cel: 0 setCycle: EndLoop self)
			)
			(20
				(gateFx number: 543 play:)
				(gate show:)
				(beast
					setPri: (- (gate priority?) 1)
					posn: 72 62
					setLoop: 3
					cel: 0
					setCycle: EndLoop self
				)
			)
			(21
				(theGame handsOn:)
				((ScriptID 0 5) setReal: (inventory at: 37) 0 3 0)
				(Bset 46)
				(theGame givePoints: 1)
				(ego get: 37)
				(beast dispose:)
				(self dispose:)
			)
		)
	)
)

(instance beautyScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 2))
			(1
				(gate hide:)
				(beast
					posn: 72 62
					view: 533
					setLoop: 0
					ignoreActors: 1
					setCycle: EndLoop self
				)
				(beauty setScript: beautyWalkScript self)
			)
			(2
				(beast
					view: 5406
					posn: 96 98
					setLoop: 1
					cel: 0
					setCycle: EndLoop self
				)
				(gateFx number: 542 play:)
			)
			(3
				(gateFx number: 543 play:)
				(gate show:)
				(beast
					view: 542
					setScale: Scaler 100 68 190 100
					setCycle: Walk
					setStep: 4 3
					setLoop: -1
					y: (+ (beast y?) 3)
					setMotion: MoveTo 114 134 self
				)
				(UnLoad 128 533)
				(weasel setScript: weaselScript)
			)
			(4)
			(5
				(beast setLoop: 4 cel: 0)
				(= cycles 2)
			)
			(6
				(myConv
					add: -1 1 0 2 1
					add: -1 1 0 2 2
					add: -1 1 0 2 3
					add: -1 1 0 2 4
					add: -1 1 0 2 5
					init: self
				)
			)
			(7
				(beast view: 544 setLoop: 2 cel: 0 setCycle: EndLoop self)
				(gateFx number: 546 play:)
				(Bset 113)
			)
			(8 (messager say: 1 0 2 6 self))
			(9
				(beauty view: 5405 setLoop: 0 cel: 0 setCycle: EndLoop self)
				(UnLoad 128 544)
			)
			(10
				(UnLoad 128 252)
				(messager say: 1 0 2 7 self)
			)
			(11
				(messager say: 1 0 2 8 self)
			)
			(12
				(beauty setLoop: 1 cel: 0 setCycle: EndLoop self)
				(gateFx number: 546 play:)
				(Bset 43)
			)
			(13
				(beast
					view: 547
					setLoop: 4
					cel: 0
					posn: (- (beast x?) 1) (+ (beast y?) 2)
				)
				(theMusic stop:)
				(theMusic number: 544 loop: -1 play:)
				(= seconds 2)
			)
			(14
				(beauty view: 546 setLoop: 4 cel: 2)
				(= cycles 2)
			)
			(15
				(myConv add: -1 1 0 2 9 add: -1 1 0 2 10 init: self)
			)
			(16
				(ego
					setMotion: MoveTo (+ (beauty x?) 30) (beauty y?) self
				)
			)
			(17 (Face ego beauty self))
			(18
				(messager say: 1 0 2 11 self)
			)
			(19
				(messager say: 1 0 2 12 self)
			)
			(20
				(ego get: 5 hide:)
				(if (not (Btst 93)) (Bset 112))
				(beauty
					view: 549
					setLoop: 2
					cel: 0
					posn: (+ (beauty x?) 30) (+ (beauty y?) 1)
					setCycle: EndLoop self
				)
			)
			(21
				(beauty
					view: 546
					posn: (- (beauty x?) 30) (- (beauty y?) 1)
					setLoop: 4
					cel: 0
				)
				(UnLoad 128 549)
				(ego show:)
				(= seconds 1)
			)
			(22
				(myConv add: -1 1 0 2 13 add: -1 1 0 2 14 init: self)
			)
			(23
				(ego
					ignoreActors: 1
					ignoreHorizon: 1
					setPri: (- (beauty priority?) 1)
					setMotion: MoveTo (+ (beast x?) 32) (beast y?) self
				)
			)
			(24
				(beast
					view: 5403
					setLoop: 0
					cel: 0
					posn: (+ (beast x?) 18) (+ (beast y?) 1)
				)
				(ego
					normal: 0
					view: 541
					setLoop: 1
					cel: 0
					posn: (- (ego x?) 10) (+ (ego y?) 4)
				)
				(beauty cel: 3)
				(= cycles 1)
			)
			(25
				(beast cel: 1)
				(= cycles 1)
			)
			(26
				(beauty cel: 1)
				(beast setCycle: EndLoop self)
				(ego setCycle: EndLoop self)
			)
			(27 0)
			(28
				(beast
					view: 548
					setLoop: 0
					cel: 0
					posn: (- (beast x?) 16) (+ (beast y?) 1)
				)
				(ego
					get: 24
					posn: (+ (ego x?) 10) (- (ego y?) 4)
					reset:
					setPri: (- (beauty priority?) 1)
				)
				(= cycles 3)
			)
			(29
				(UnLoad 128 541)
				(UnLoad 128 5403)
				(myConv add: -1 1 0 2 15 init: self)
			)
			(30
				(ego
					setCycle: Reverse
					setLoop: 1
					setMotion: PolyPath (+ (ego x?) 20) (ego y?) self
				)
			)
			(31
				(ego reset: 1)
				(beast setCycle: EndLoop self)
				(beauty view: 548 setLoop: 1 cel: 0 setCycle: EndLoop self)
			)
			(32 0)
			(33
				(messager say: 1 0 2 16 self)
			)
			(34
				(beauty hide:)
				(beast
					view: 5441
					setLoop: 0
					cel: 0
					posn: (+ (beast x?) 22) (beast y?)
					setCycle: EndLoop self
				)
			)
			(35
				(beast setLoop: 1 cel: 0 setCycle: EndLoop self)
			)
			(36
				(ego setHeading: 315)
				(beast
					view: 547
					setLoop: -1
					setCycle: Walk
					setLoop: 3
					setPri:
					posn: (- (beast x?) 9) (- (beast y?) 1)
					setMotion: MoveTo 84 102 self
				)
				(beauty
					view: 546
					setLoop: 3
					posn: (+ (beast x?) 17) (+ (beast y?) 1)
					setPri: (- (beast priority?) 1)
					show:
					setCycle: Walk
					setMotion: PolyPath 108 104
				)
				(UnLoad 128 548)
			)
			(37
				(gate hide:)
				(beast
					view: 534
					setLoop: 0
					cel: 0
					posn: 96 98
					setScale: 0
					setPri: -1
					setCycle: EndLoop self
				)
				(beauty setPri: -1)
				(gateFx number: 542 play:)
				(weaselScript register: 1)
			)
			(38
				(beauty setMotion: MoveTo 87 97 self)
			)
			(39
				(beauty setMotion: MoveTo 98 94 self)
			)
			(40
				(beast view: 5407 setLoop: 0 cel: 0 setCycle: EndLoop self)
			)
			(41
				(gateFx number: 543 play:)
				(beauty dispose:)
				(beast
					view: 5408
					setLoop: 0
					cel: 0
					posn: 72 62
					setCycle: EndLoop self
				)
			)
			(42
				(gate show:)
				(beast dispose:)
				(theGame givePoints: 2)
				(NextAct)
				(self dispose:)
			)
		)
	)
)

(instance beautyWalkScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(beauty setMotion: MoveTo 157 139 self)
			)
			(1
				(beauty setLoop: 1)
				(= ticks 1)
			)
			(2 (self dispose:))
		)
	)
)

(instance weaselScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(weasel setMotion: MoveTo 259 165 self)
			)
			(1
				(weasel cel: 0)
				(= cycles 1)
			)
			(2 (= seconds 5))
			(3
				(glint
					show:
					posn: (- (weasel x?) 14) (weasel y?) 5
					setCycle: EndLoop self
				)
			)
			(4 (glint setCycle: BegLoop self))
			(5
				(if (not register)
					(self start: 2 init:)
				else
					(glint hide:)
					(= cycles 1)
				)
			)
			(6
				(weasel setMotion: MoveTo 175 183 self)
			)
			(7
				(glint
					show:
					posn: (- (weasel x?) 13) (weasel y?) 5
					setCycle: EndLoop self
				)
			)
			(8 (glint setCycle: BegLoop self))
			(9
				(glint dispose:)
				(weasel setMotion: MoveTo 90 210 self)
			)
			(10
				(theGame handsOn:)
				(weasel dispose:)
			)
		)
	)
)

(instance getWater of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if register
					(ego setMotion: PolyPath 163 122 self)
				else
					(ego setMotion: PolyPath 165 121 self)
				)
			)
			(1
				(ego
					normal: 0
					setCel: 0
					cycleSpeed: 9
					view: 5404
					setLoop: (if register 5 else 2)
					setCycle: EndLoop self
				)
			)
			(2 (ego reset: 6) (= cycles 4))
			(3
				(if register
					(if (not (Btst 14)) (theGame givePoints: 1))
					(= global161 (| global161 $0001))
					(messager say: 5 43 6 0 self)
				else
					(messager say: 5 5 0 0 self)
				)
			)
			(4
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance myConv of Conversation
	(properties)
)
