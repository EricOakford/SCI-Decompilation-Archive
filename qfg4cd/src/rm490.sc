;;; Sierra Script 1.0 - (do not remove this comment)
(script# 490)
(include sci.sh)
(use Main)
(use GloryRm)
(use TellerIcon)
(use EgoDead)
(use GloryTalker)
(use Intrface)
(use RandCyc)
(use Feature)
(use ForCount)
(use Sound)
(use Motion)
(use Actor)
(use System)

(public
	rm490 0
	babaTalker 1
)

(local
	local0
	local1
	local2
	local3
	local4
	local5
	theGGGTheObj_2CycleSpeed
)
(procedure (localproc_0100)
	(switch arcadeLevel
		(1 11)
		(2 8)
		(3 5)
	)
)

(instance rm490 of GloryRm
	(properties
		picture 490
	)
	
	(method (init)
		(if debugging
			(switch (GetNumber {event num? (1, 2 or 3)})
				(1 (Bclr 166))
				(2
					(ego get: 28)
					((inventory at: 28)
						maskCel: (| ((inventory at: 28) maskCel?) $0008)
					)
				)
				(3 (Bset 179))
			)
		)
		(= theGGGTheObj_2CycleSpeed egoGait)
		(ego init: setScale: 0)
		(theBat init: setPri: 159)
		(cond 
			((Btst 179) (= local2 3) (self setScript: s3rdEntrance))
			(
				(and
					(ego has: 28)
					(& ((inventory at: 28) maskCel?) $0008)
				)
				(= local2 2)
				(self setScript: s2ndEntrance)
			)
			(else (= local2 1) (self setScript: sFirstEntrance))
		)
		(theMusic number: 490 setLoop: -1 play:)
		(super init: &rest)
		(candle1 setCycle: RandCycle init:)
		(candle2 setCycle: RandCycle init:)
		(candle3 setCycle: RandCycle init:)
		(candle4 setCycle: RandCycle init:)
		(candle5 setCycle: RandCycle init:)
		(candle6 setCycle: RandCycle init:)
		(candle7 setCycle: RandCycle init:)
		(theBall setCycle: RandCycle init:)
		(fireplace setCycle: RandCycle init:)
		(theFire setCycle: RandCycle init:)
		(bubbles setCycle: Fwd init:)
		(gloryHand init:)
		(crystalBall init:)
		(candles init:)
		(tableFoot init:)
		(table init:)
		(doorSkull init:)
		(demonSkull init:)
		(bucket init:)
		(skeleton init:)
		(backScratcher init:)
		(theDoor init:)
		(candles2 init:)
		(candles3 init:)
		(cauldron init:)
		(fFirePlace init:)
		(bottle init:)
		(humanSkull init:)
		(cobwebs init:)
		(topShelf init:)
		(bookShelf init:)
		(jarShelf init:)
		(belowShelf init:)
		(boneNecklace init:)
		(RemapColors 3 253 75)
	)
	
	(method (newRoom n)
		(theGame handsOff:)
		(ego changeGait: theGGGTheObj_2CycleSpeed)
		(theMusic fade:)
		(Bset 166)
		(super newRoom: n)
	)
)

(instance sFirstEntrance of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego posn: 80 128 normalize: 4)
				(= ticks 180)
			)
			(1
				(if (== heroType 3)
					(messager say: 1 6 1 0 self)
				else
					(self cue:)
				)
			)
			(2 (messager say: 1 6 2 0 self))
			(3
				(spider init: setCycle: End self)
			)
			(4
				(spider signal: (& (spider signal?) $fffe))
				(= ticks 60)
			)
			(5
				(theBucket
					init:
					posn: 91 118
					view: 493
					loop: 1
					cel: 0
					setCycle: CT 8 1 self
				)
			)
			(6
				(ego hide:)
				(theBucket setCycle: CT 11 1 self)
			)
			(7
				(theBucket signal: (& (theBucket signal?) $fffe))
				(baba init: setCycle: End self)
				(soundFX number: 971 play:)
			)
			(8
				(baba signal: (& (baba signal?) $fffe))
				(= ticks 60)
			)
			(9 (messager say: 2 6 3 0 self))
			(10 (= ticks 60))
			(11
				(RemapColors 2 253 140)
				(baba
					signal: (| (baba signal?) $0001)
					setLoop: 2
					setCel: 0
					setCycle: End self
				)
			)
			(12
				(baba setLoop: 3 setCel: 0 setCycle: End self)
				(soundFX number: 983 play:)
			)
			(13
				(theBucket
					signal: (| (theBucket signal?) $0001)
					setCycle: End self
				)
				(baba setLoop: 1 setCycle: ForwardCounter 2)
			)
			(14
				(theBucket signal: (& (theBucket signal?) $fffe))
				(= ticks 180)
			)
			(15
				(baba signal: (& (baba signal?) $fffe))
				(messager say: 2 6 4 0 self)
			)
			(16
				(self setScript: flapWings self)
			)
			(17
				(messager say: 2 6 7 0 self)
			)
			(18
				(self setScript: flapWings self)
			)
			(19
				(messager say: 2 6 6 0 self)
			)
			(20
				(heroTeller init: theBucket 490 4 128 3)
				(babaTeller init: baba 490 4 168 3)
				(user canInput: 1)
				(theIconBar enable: 1 3)
				(if (not (theIconBar curInvIcon?))
					(theIconBar disable: 6)
				)
				(theIconBar advanceCurIcon:)
				(= seconds (localproc_0100))
			)
			(21
				(theGame handsOff:)
				(if (and (not local0) (not local1))
					(messager say: 2 6 9 0 self)
				else
					(messager say: 2 6 8 0 self)
				)
			)
			(22
				(theGame handsOff:)
				(EgoDead 10 490 978 1 912)
			)
			(23
				(theGame handsOff:)
				(baba
					signal: (| (baba signal?) $0001)
					setCycle: ForwardCounter 3 self
				)
			)
			(24
				(theGame handsOff:)
				(baba signal: (& (baba signal?) $fffe))
				(self setScript: flapWings self)
			)
			(25
				(theGame handsOff:)
				(messager say: 2 6 16 0 self)
			)
			(26
				(theGame handsOff:)
				(self setScript: flapWings self)
			)
			(27
				(theGame handsOff:)
				(messager say: 2 6 17 0 self)
			)
			(28
				(self setScript: flapWings self)
			)
			(29
				(messager say: 2 6 18 0 self)
			)
			(30
				(RemapColors 2 253 140)
				(baba
					signal: (| (baba signal?) $0001)
					setLoop: 2
					setCel: 0
					setCycle: End self
				)
			)
			(31
				(soundFX number: 936 play:)
				(baba setLoop: 3 setCel: 0 setCycle: End self)
			)
			(32 (curRoom newRoom: 480))
		)
	)
)

(instance s2ndEntrance of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= register (ego cycleSpeed?))
				(ego
					posn: 80 128
					normalize:
					changeGait: 1
					setSpeed: 6
					setMotion: MoveTo 110 143 self
				)
			)
			(1
				(ego normalize: 4 setSpeed: register setLoop: 4)
				(= cycles 3)
			)
			(2
				(if (== heroType 3)
					(messager say: 1 6 40 0 self)
				else
					(self cue:)
				)
			)
			(3
				(messager say: 1 6 19 0 self)
			)
			(4 (= ticks 100))
			(5
				(baba init: setCycle: End self)
				(soundFX number: 971 play:)
			)
			(6
				(baba signal: (& (baba signal?) $fffe))
				(= ticks 90)
			)
			(7
				(messager say: 2 6 20 0 self)
			)
			(8
				(RemapColors 2 253 140)
				(baba
					signal: (| (baba signal?) $0001)
					setLoop: 2
					setCel: 0
					setCycle: End self
				)
			)
			(9
				(baba setLoop: 3 setCel: 0 setCycle: End self)
				(soundFX number: 936 play:)
			)
			(10
				(baba signal: (& (baba signal?) $fffe))
				(if reversalTimer
					(= state (+ state 7))
					(messager say: 2 6 22 0 self)
				else
					(= register (ego cycleSpeed?))
					(ego
						view: 491
						setLoop: 0
						setCel: 0
						cycleSpeed: 6
						setCycle: End self
					)
				)
			)
			(11
				(baba
					signal: (| (baba signal?) $0001)
					setLoop: 1
					setCycle: ForwardCounter 3
				)
				(= ticks 180)
			)
			(12
				(messager say: 2 6 21 0 self)
			)
			(13
				(baba setLoop: 2 setCel: 0 setCycle: End self)
			)
			(14
				(baba setLoop: 3 setCel: 0 setCycle: End self)
				(soundFX number: 936 play:)
			)
			(15
				(baba setLoop: 1 setCycle: ForwardCounter 3 self)
			)
			(16
				(baba signal: (& (baba signal?) $fffe))
				(ego setCycle: Beg self)
			)
			(17
				(ego normalize: 4 setSpeed: register setLoop: 4)
				(= ticks 60)
			)
			(18
				(messager say: 2 6 23 0 self)
			)
			(19
				(user canInput: 1)
				(theIconBar enable: 1 3 7)
				(if (not (theIconBar curInvIcon?))
					(theIconBar disable: 6)
				)
				(theIconBar advanceCurIcon:)
				(heroTeller init: ego 490 4 128 5)
				(babaTeller init: baba 490 4 168 5)
				(self dispose:)
			)
		)
	)
)

(instance s3rdEntrance of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= register (ego cycleSpeed?))
				(ego
					posn: 80 128
					normalize:
					changeGait: 1
					setSpeed: 6
					setMotion: MoveTo 110 143 self
				)
			)
			(1
				(ego normalize: 4 setSpeed: register setLoop: 4)
				(= cycles 3)
			)
			(2
				(if (== heroType 3)
					(messager say: 1 6 41 0 self)
				else
					(self cue:)
				)
			)
			(3
				(messager say: 1 6 27 0 self)
			)
			(4 (= ticks 60))
			(5
				(baba init: setCycle: End self)
				(soundFX number: 971 play:)
			)
			(6
				(baba signal: (& (baba signal?) $fffe))
				(= ticks 60)
			)
			(7
				(messager say: 2 6 28 0 self)
			)
			(8
				(theIconBar enable: 1 3 7)
				(user canInput: 1)
				(if (not (theIconBar curInvIcon?))
					(theIconBar disable: 6)
				)
				(theIconBar advanceCurIcon:)
				(heroTeller init: ego 490 4 128 7)
				(babaTeller init: baba 490 4 168 7)
				(self dispose:)
			)
		)
	)
)

(instance sGivePie of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego drop: 28 1)
				(Bset 179)
				(theGame handsOff:)
				(baba
					signal: (| (baba signal?) $0001)
					setLoop: 2 1
					setCel: 0
					setCycle: End self
				)
			)
			(1
				(baba setLoop: 3 setCel: 0 setCycle: End self)
				(soundFX number: 936 play:)
			)
			(2
				(baba setLoop: 1)
				(thePie init:)
				(heroTeller dispose:)
				(babaTeller dispose:)
				(heroTeller init: ego 490 4 128 6)
				(babaTeller init: baba 490 4 168 6)
				(= ticks 120)
			)
			(3
				(messager say: 2 46 47 0 self)
			)
			(4
				(baba setCycle: ForwardCounter 3 self)
			)
			(5
				(baba signal: (& (baba signal?) $fffe))
				(messager say: 2 6 24 0 self)
			)
			(6
				(self setScript: flapWings self)
			)
			(7
				(messager say: 2 6 25 0 self)
			)
			(8
				(user canInput: 1)
				(theIconBar enable: 1 3)
				(if (not (theIconBar curInvIcon?))
					(theIconBar disable: 6)
				)
				(theIconBar advanceCurIcon:)
				(self dispose:)
			)
		)
	)
)

(instance sWarningDeath of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(messager say: 2 6 32 0 self)
			)
			(1 (EgoDead 63 490 978 1 912))
		)
	)
)

(instance sEventBye of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= cycles 5)
			)
			(1
				(messager say: 2 6 register 0 self)
			)
			(2
				(RemapColors 2 253 140)
				(baba
					signal: (| (baba signal?) $0001)
					setLoop: 2
					setCel: 0
					setCycle: End self
				)
			)
			(3
				(baba setLoop: 3 setCel: 0 setCycle: End self)
				(soundFX number: 936 play:)
			)
			(4 (curRoom newRoom: 480))
		)
	)
)

(instance sGiveFood of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(switch register
					(27 (ego drop: 12 1))
					(47 (ego drop: 29 1))
					(40 (ego drop: 22 1))
					(26 (ego drop: 11 1))
				)
				(messager say: 2 register 29 0 self)
			)
			(1
				(baba
					signal: (| (baba signal?) $0001)
					setLoop: 1
					setCycle: ForwardCounter 3 self
				)
			)
			(2
				(baba signal: (& (baba signal?) $fffe))
				(messager say: 2 6 52 0 self)
				(heroTeller dispose:)
				(babaTeller dispose:)
				(heroTeller init: ego 490 4 128 8)
				(babaTeller init: baba 490 4 168 8)
			)
			(3
				(theIconBar enable: 1 3 7)
				(user canInput: 1)
				(if (not (theIconBar curInvIcon?))
					(theIconBar disable: 6)
				)
				(theIconBar advanceCurIcon:)
				(self dispose:)
			)
		)
	)
)

(instance flapWings of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(baba setLoop: 5)
				(if (not (cast contains: babaHead))
					(babaHead init:)
				else
					(babaHead show:)
				)
				(babaHead setCycle: End)
				(theBat
					signal: (| (theBat signal?) $0001)
					setLoop: 2
					setCel: 0
					setCycle: End self
				)
			)
			(1
				(theBat
					setLoop: 3
					setCel: 0
					setCycle: ForwardCounter 3 self
				)
			)
			(2
				(theBat setLoop: 4 setCel: 0 setCycle: End self)
				(babaHead setCycle: Beg self)
			)
			(3 0)
			(4
				(theBat signal: (& (theBat signal?) $fffe))
				(baba setLoop: 1)
				(babaHead hide:)
				(self dispose:)
			)
		)
	)
)

(instance heroTeller of Teller
	(properties
		loopMenu 0
	)
	
	(method (respond)
		(return
			(cond 
				(local3
					(self clean:)
					(if (Btst 179)
						(curRoom setScript: sEventBye 0 26)
					else
						(curRoom setScript: sWarningDeath)
					)
				)
				(local4 (self clean:) (curRoom setScript: sEventBye 0 33))
				(else (super respond: &rest) (return 1))
			)
		)
	)
	
	(method (sayMessage)
		(if (not (Btst 166))
			(= local0 1)
			(if (!= iconValue 15)
				(sFirstEntrance seconds: (localproc_0100))
			else
				(sFirstEntrance state: (+ (sFirstEntrance state?) 3))
			)
		)
		(if (== local2 2)
			(= local3 1)
			(switch iconValue
				(56
					(ego solvePuzzle: 496 6 2 learn: 36 100)
				)
				(43
					(ego solvePuzzle: 496 6 2 learn: 36 100)
				)
				(55 (ego get: 31) (Bset 180))
				(47 (= local3 0))
				(72 (Bset 396) (ego get: 54))
				(57 (Bset 396) (ego get: 54))
			)
		)
		(if (== local2 3)
			(= local4 1)
			(switch iconValue
				(43
					(ego solvePuzzle: 496 6 2 learn: 36 100)
				)
				(56
					(ego solvePuzzle: 496 6 2 learn: 36 100)
				)
				(36 (ego get: 31) (Bset 180))
				(72 (Bset 396) (ego get: 54))
				(74 (Bset 396) (ego get: 54))
			)
		)
		(super sayMessage: &rest)
	)
	
	(method (showCases)
		(super
			showCases:
				43
				(if (not [egoStats 36]) [egoStats 12] else 0)
				56
				(if (not [egoStats 36]) [egoStats 12] else 0)
				36
				(cond 
					((or (== local2 1) (== local2 2)))
					((== local2 3) (not (Btst 180)))
				)
				72
				(if (not (ego has: 54))
					(if (not (Btst 396))
						(cond 
							((== local2 1))
							((== local2 2))
							((== local2 3) (Btst 110))
						)
					)
				else
					0
				)
				74
				(if (not (ego has: 54))
					(if (not (Btst 396))
						(cond 
							((== local2 1))
							((== local2 2))
							((== local2 3) (not (Btst 110)))
						)
					)
				else
					0
				)
				57
				(if (not (ego has: 54)) (not (Btst 396)) else 0)
		)
	)
)

(instance babaTeller of Teller
	(properties
		title 1
		loopMenu 0
	)
	
	(method (init)
		(super init: &rest)
		(= talker babaTalker)
	)
	
	(method (doVerb theVerb)
		(return
			(cond 
				(
				(and (== local2 3) (OneOf theVerb 27 47 40 26)) (curRoom setScript: sGiveFood 0 theVerb) (return 1))
				((and (== local2 3) (> theVerb 13))
					(switch (- (++ local5) 1)
						(0 (messager say: 2 6 30))
						(1 (messager say: 2 6 31))
						(2
							(curRoom setScript: sWarningDeath)
						)
					)
				)
				(else
					(switch theVerb
						(46
							(if (& ((inventory at: 28) maskCel?) $0008)
								(curRoom setScript: sGivePie)
								(return 1)
							else
								(messager say: 2 46 73)
							)
						)
						(else  (super doVerb: theVerb))
					)
				)
			)
		)
	)
	
	(method (sayMessage)
		(= local1 1)
		(super sayMessage: &rest)
	)
)

(instance baba of Prop
	(properties
		noun 2
		x 211
		y 132
		view 495
		signal $4001
	)
	
	(method (init)
		(self setPri: 160)
		(super init: &rest)
	)
)

(instance babaHead of Prop
	(properties
		view 495
		loop 4
		signal $4001
	)
	
	(method (init)
		(super init: &rest)
		(self
			ignoreActors:
			setPri: (+ (baba priority?) 1)
			x: (baba x?)
			y: (baba y?)
			z: 0
		)
	)
	
	(method (doVerb theVerb)
		(baba doVerb: theVerb)
	)
)

(instance spider of Prop
	(properties
		noun 32
		x 118
		y 15
		view 492
		signal $4001
	)
	
	(method (init)
		(self setPri: 180)
		(super init: &rest)
	)
)

(instance theBucket of Prop
	(properties
		x 66
		y 95
		z 60
		view 490
		loop 11
		signal $4001
	)
)

(instance theBat of Prop
	(properties
		noun 31
		x 291
		y 41
		view 492
		loop 2
		signal $4000
	)
)

(instance thePie of View
	(properties
		noun 33
		x 72
		y 137
		z 1
		view 491
		loop 1
		signal $4000
	)
)

(instance candle1 of Prop
	(properties
		x 37
		y 128
		view 490
		cel 7
		signal $4001
		detailLevel 2
	)
	
	(method (doVerb theVerb)
		(candles doVerb: theVerb)
	)
)

(instance candle2 of Prop
	(properties
		x 34
		y 130
		view 490
		loop 2
		cel 3
		signal $4001
		detailLevel 2
	)
	
	(method (doVerb theVerb)
		(candles doVerb: theVerb)
	)
)

(instance candle3 of Prop
	(properties
		x 26
		y 131
		view 490
		loop 3
		cel 5
		signal $4001
		detailLevel 2
	)
	
	(method (doVerb theVerb)
		(candles doVerb: theVerb)
	)
)

(instance candle4 of Prop
	(properties
		x 194
		y 90
		priority 13
		fixPriority 1
		view 490
		loop 8
		cel 5
		signal $4001
		detailLevel 2
	)
	
	(method (doVerb theVerb)
		(candles2 doVerb: theVerb)
	)
)

(instance candle5 of Prop
	(properties
		x 226
		y 59
		view 490
		loop 10
		cel 9
		signal $4001
		detailLevel 2
	)
	
	(method (doVerb theVerb)
		(candles3 doVerb: theVerb)
	)
)

(instance candle6 of Prop
	(properties
		x 247
		y 55
		view 490
		loop 12
		cel 7
		signal $4001
		detailLevel 2
	)
	
	(method (doVerb theVerb)
		(candles3 doVerb: theVerb)
	)
)

(instance candle7 of Prop
	(properties
		x 283
		y 41
		view 490
		loop 13
		cel 7
		signal $4001
		detailLevel 2
	)
	
	(method (doVerb theVerb)
		(candles3 doVerb: theVerb)
	)
)

(instance theBall of Prop
	(properties
		x 58
		y 134
		view 490
		loop 1
		cel 6
		signal $4001
		detailLevel 2
	)
	
	(method (doVerb theVerb)
		(crystalBall doVerb: theVerb)
	)
)

(instance fireplace of Prop
	(properties
		x 245
		y 127
		view 490
		loop 4
		cel 5
		signal $4001
		detailLevel 2
	)
	
	(method (doVerb theVerb)
		(fFirePlace doVerb: theVerb)
	)
)

(instance theFire of Prop
	(properties
		x 292
		y 135
		view 490
		loop 6
		cel 6
		signal $4001
		detailLevel 2
	)
	
	(method (doVerb theVerb)
		(fFirePlace doVerb: theVerb)
	)
)

(instance bubbles of Prop
	(properties
		x 291
		y 106
		view 490
		loop 9
		signal $4001
		detailLevel 2
	)
	
	(method (doVerb theVerb)
		(cauldron doVerb: theVerb)
	)
)

(instance babaTalker of GloryTalker
	(properties
		x 0
		y 16
		talkWidth 130
		view 496
		textX 160
		textY 10
	)
	
	(method (init)
		(super init: babaMouth 0 babaEyes babaFrame &rest)
	)
)

(instance babaFrame of View
	(properties
		y 16
		view 496
	)
)

(instance babaMouth of Prop
	(properties
		x 23
		y 63
		view 496
		loop 1
	)
)

(instance babaEyes of Prop
	(properties
		x 22
		y 56
		view 496
		loop 2
	)
)

(instance gloryHand of Feature
	(properties
		noun 10
		nsLeft 28
		nsTop 135
		nsRight 45
		nsBottom 147
		sightAngle 180
		x 36
		y 141
	)
)

(instance crystalBall of Feature
	(properties
		noun 11
		nsLeft 44
		nsTop 121
		nsRight 61
		nsBottom 136
		sightAngle 180
		x 52
		y 137
	)
)

(instance candles of Feature
	(properties
		noun 12
		nsLeft 24
		nsTop 116
		nsRight 44
		nsBottom 135
		sightAngle 180
		x 34
		y 137
	)
)

(instance tableFoot of Feature
	(properties
		noun 13
		nsLeft 42
		nsTop 146
		nsRight 74
		nsBottom 170
		sightAngle 180
		x 58
		y 158
	)
)

(instance table of Feature
	(properties
		noun 14
		nsLeft 24
		nsTop 126
		nsRight 95
		nsBottom 147
		sightAngle 180
		x 59
		y 136
	)
)

(instance doorSkull of Feature
	(properties
		noun 15
		nsLeft 70
		nsTop 75
		nsRight 86
		nsBottom 95
		sightAngle 180
		x 78
		y 91
	)
)

(instance demonSkull of Feature
	(properties
		noun 16
		nsLeft 57
		nsTop 31
		nsRight 92
		nsBottom 65
		sightAngle 180
		x 74
		y 48
	)
)

(instance bucket of Feature
	(properties
		noun 17
		nsLeft 54
		nsTop 12
		nsRight 82
		nsBottom 31
		sightAngle 180
		x 68
		y 21
	)
	
	(method (doVerb theVerb)
		(theBucket doVerb: theVerb)
	)
)

(instance skeleton of Feature
	(properties
		noun 18
		nsTop 145
		nsRight 129
		nsBottom 189
		sightAngle 180
		x 64
		y 157
	)
)

(instance backScratcher of Feature
	(properties
		noun 19
		nsLeft 127
		nsTop 122
		nsRight 145
		nsBottom 127
		sightAngle 180
		x 136
		y 124
	)
)

(instance theDoor of Feature
	(properties
		noun 20
		nsLeft 48
		nsTop 60
		nsRight 98
		nsBottom 120
		sightAngle 180
		x 73
		y 90
	)
)

(instance candles2 of Feature
	(properties
		noun 12
		nsLeft 185
		nsTop 75
		nsRight 203
		nsBottom 91
		sightAngle 180
		x 194
		y 83
	)
)

(instance candles3 of Feature
	(properties
		noun 12
		nsLeft 225
		nsTop 40
		nsRight 301
		nsBottom 53
		sightAngle 180
		x 263
		y 46
	)
)

(instance cauldron of Feature
	(properties
		noun 21
		nsLeft 265
		nsTop 90
		nsRight 300
		nsBottom 128
		sightAngle 180
		x 282
		y 109
	)
)

(instance fFirePlace of Feature
	(properties
		noun 22
		nsLeft 250
		nsTop 77
		nsRight 303
		nsBottom 134
		sightAngle 180
		x 276
		y 105
	)
)

(instance bottle of Feature
	(properties
		noun 23
		nsLeft 235
		nsTop 138
		nsRight 254
		nsBottom 180
		sightAngle 180
		x 244
		y 175
	)
)

(instance humanSkull of Feature
	(properties
		noun 24
		nsLeft 267
		nsTop 132
		nsRight 319
		nsBottom 189
		sightAngle 180
		x 293
		y 160
	)
)

(instance cobwebs of Feature
	(properties
		noun 25
		nsLeft 192
		nsTop 160
		nsRight 268
		nsBottom 189
		sightAngle 180
		x 230
		y 174
	)
)

(instance topShelf of Feature
	(properties
		noun 26
		nsLeft 114
		nsTop 54
		nsRight 146
		nsBottom 72
		sightAngle 180
		x 130
		y 63
	)
)

(instance bookShelf of Feature
	(properties
		noun 27
		nsLeft 113
		nsTop 71
		nsRight 145
		nsBottom 85
		sightAngle 180
		x 129
		y 78
	)
)

(instance jarShelf of Feature
	(properties
		noun 28
		nsLeft 113
		nsTop 85
		nsRight 146
		nsBottom 97
		sightAngle 180
		x 129
		y 91
	)
)

(instance belowShelf of Feature
	(properties
		noun 29
		nsLeft 112
		nsTop 97
		nsRight 147
		nsBottom 117
		sightAngle 180
		x 129
		y 107
	)
)

(instance boneNecklace of Feature
	(properties
		noun 30
		nsTop 77
		nsRight 42
		nsBottom 138
		sightAngle 180
		x 21
		y 107
	)
)

(instance soundFX of Sound
	(properties)
)
