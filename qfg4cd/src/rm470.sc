;;; Sierra Script 1.0 - (do not remove this comment)
(script# 470)
(include sci.sh)
(use Main)
(use GloryRm)
(use TellerIcon)
(use GloryTalker)
(use Intrface)
(use Feature)
(use Sound)
(use Motion)
(use Actor)
(use System)

(public
	rm470 0
	gTalker 1
	fTalker 2
)

(local
	local0
	local1
	local2
)
(instance rm470 of GloryRm
	(properties
		noun 20
		picture 470
	)
	
	(method (init)
		(ego posn: 1000 1000 init: hide:)
		(if debugging
			(= local0 (GetNumber {Event #?}))
		else
			(= local0
				(cond 
					(
						(and
							(Btst 273)
							(Btst 272)
							(Btst 274)
							(Btst 275)
							(Btst 277)
							(Btst 110)
							(not (Btst 278))
							(== prevRoomNum 460)
						)
						13
					)
					(
						(and
							(Btst 273)
							(Btst 272)
							(Btst 274)
							(Btst 275)
							(Btst 277)
							(Btst 110)
							(== prevRoomNum 475)
						)
						12
					)
					(
						(and
							(Btst 273)
							(Btst 272)
							(Btst 274)
							(Btst 275)
							(Btst 110)
							(not (Btst 277))
							(not (Btst 278))
							(== prevRoomNum 460)
						)
						11
					)
					(
						(and
							(Btst 273)
							(Btst 272)
							(Btst 274)
							(Btst 275)
							(Btst 276)
							(== prevRoomNum 475)
						)
						10
					)
					(
						(and
							(Btst 273)
							(Btst 272)
							(Btst 274)
							(Btst 275)
							(not (Btst 110))
							(== prevRoomNum 460)
						)
						9
					)
					(
						(and
							(Btst 273)
							(Btst 272)
							(Btst 274)
							(Btst 275)
							(== prevRoomNum 475)
						)
						8
					)
					(
						(and
							(Btst 273)
							(Btst 272)
							(Btst 274)
							(not (Btst 275))
							(== prevRoomNum 460)
						)
						7
					)
					(
						(and
							(Btst 273)
							(Btst 272)
							(Btst 274)
							(== prevRoomNum 475)
						)
						6
					)
					(
						(and
							(Btst 273)
							(Btst 272)
							(not (Btst 115))
							(== prevRoomNum 460)
						)
						5
					)
					((and (Btst 273) (== prevRoomNum 475)) 4)
					(
					(and (Btst 272) (not (Btst 273)) (== prevRoomNum 460)) 3)
					((and (Btst 272) (== prevRoomNum 475)) 2)
					((and (not (Btst 272)) (== prevRoomNum 460)) 1)
					(else 13)
				)
			)
		)
		(cond 
			((== local0 7) (if (not (Btst 115)) (= local0 5)))
			((and (== local0 8) (not (Btst 115))) (= local0 6))
		)
		(walkHandler addToFront: self)
		(pCandles2 init: cycleSpeed: 12 setCycle: Fwd)
		(pHands init:)
		(pBracelet init: cycleSpeed: 12 setCycle: Fwd)
		(pOHands init:)
		(pShirt2 init: cycleSpeed: 12 setCycle: Fwd)
		(pShirt init: cycleSpeed: 12 setCycle: Fwd)
		(pEarring2 init: cycleSpeed: 12 setCycle: Fwd)
		(pEarring init: cycleSpeed: 12 setCycle: Fwd)
		(pEyes init: setScript: sDoTheEyes)
		(pEyes2 init: setScript: sDoSecondEyes)
		(pReflect init: cycleSpeed: 12 setCycle: Fwd)
		(super init: &rest)
		(theMusic number: 470 setLoop: -1 play:)
		(fGarlic1 init:)
		(fGarlic2 init:)
		(fDavy init:)
		(fFortuneTeller init:)
		(fRing init:)
		(fBracelet init:)
		(fCards init:)
		(fHero init:)
		(fCandle1 init:)
		(fCandle2 init:)
		(fCurtain1 init:)
		(fCurtain2 init:)
		(fPeppers init:)
		(fChair init:)
		(fPlant init:)
		(fPot init:)
		(fTable init:)
		(curRoom setScript: sInTheRoom)
	)
	
	(method (dispose)
		(walkHandler delete: self)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(3
					(curRoom setScript: sBackTo460)
					(return 1)
				)
				(80
					(messager say: 20 80 0 0)
					(return 1)
				)
				(87
					(messager say: 20 87 0 0)
					(return 1)
				)
				(1
					(messager say: 20 1 0 0)
					(return 1)
				)
				(else  (super doVerb: theVerb))
			)
		)
	)
)

(instance sInTheRoom of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= seconds 4)
			)
			(1
				(switch local0
					(1
						(Bset 272)
						(davyTeller init: fDavy 470 2 139 4)
						(fortuneTeller init: fFortuneTeller 470 2 177 4)
						(egoTeller init: fHero 470 2 128 4)
						(messager say: 1 6 22 0 self)
					)
					(2
						(davyTeller init: fDavy 470 2 139 4)
						(fortuneTeller init: fFortuneTeller 470 2 177 4)
						(egoTeller init: fHero 470 2 128 4)
						(messager say: 1 6 47 0 self)
					)
					(3
						(Bset 273)
						(davyTeller init: fDavy 470 2 139 9)
						(fortuneTeller init: fFortuneTeller 470 2 177 9)
						(egoTeller init: fHero 470 2 128 9)
						(self cue:)
					)
					(4
						(davyTeller init: fDavy 470 2 139 9)
						(fortuneTeller init: fFortuneTeller 470 2 177 9)
						(egoTeller init: fHero 470 2 128 9)
						(messager say: 1 6 80 0 self)
					)
					(5
						(Bset 274)
						(davyTeller init: fDavy 470 2 139 10)
						(fortuneTeller init: fFortuneTeller 470 2 177 10)
						(egoTeller init: fHero 470 2 128 10)
						(self cue:)
					)
					(6
						(davyTeller init: fDavy 470 2 139 10)
						(fortuneTeller init: fFortuneTeller 470 2 177 10)
						(egoTeller init: fHero 470 2 128 10)
						(messager say: 1 6 96 0 self)
					)
					(7
						(Bset 275)
						(davyTeller init: fDavy 470 2 139 11)
						(fortuneTeller init: fFortuneTeller 470 2 177 11)
						(egoTeller init: fHero 470 2 128 11)
						(messager say: 1 6 102 0 self)
					)
					(8
						(davyTeller init: fDavy 470 2 139 11)
						(fortuneTeller init: fFortuneTeller 470 2 177 11)
						(egoTeller init: fHero 470 2 128 11)
						(messager say: 1 6 103 0 self)
					)
					(9
						(Bset 276)
						(davyTeller init: fDavy 470 2 139 12)
						(fortuneTeller init: fFortuneTeller 470 2 177 12)
						(egoTeller init: fHero 470 2 128 12)
						(DailyMsg 1 6 3 self 470)
					)
					(10
						(davyTeller init: fDavy 470 2 139 12)
						(fortuneTeller init: fFortuneTeller 470 2 177 12)
						(egoTeller init: fHero 470 2 128 12)
						(messager say: 1 6 111 0 self)
					)
					(11
						(Bset 277)
						(davyTeller init: fDavy 470 2 139 13)
						(fortuneTeller init: fFortuneTeller 470 2 177 13)
						(egoTeller init: fHero 470 2 128 13)
						(messager say: 1 6 114 0 self)
					)
					(12
						(davyTeller init: fDavy 470 2 139 13)
						(fortuneTeller init: fFortuneTeller 470 2 177 13)
						(egoTeller init: fHero 470 2 128 13)
						(messager say: 1 6 115 0 self)
					)
					(13
						(Bset 278)
						(davyTeller init: fDavy 470 2 139 14)
						(fortuneTeller init: fFortuneTeller 470 2 177 14)
						(egoTeller init: fHero 470 2 128 14)
						(DailyMsg 1 6 3 self 470)
					)
					(else 
						(DailyMsg 1 6 3 self 470)
					)
				)
			)
			(2
				(if (== prevRoomNum 460)
					(if (OneOf local0 3 5)
						(cond 
							(
								(and
									(not (Btst 279))
									(== heroType 2)
									(not (Btst 513))
								)
								(Bset 279)
								(messager say: 1 6 4 0 self)
							)
							(
							(and (not (Btst 280)) (Btst 135) (not (Btst 138))) (Bset 280) (messager say: 1 6 5 0 self))
							(
								(and
									(not (Btst 281))
									(== heroType 12)
									(not (Btst 269))
								)
								(Bset 281)
								(messager say: 1 6 6 0 self)
							)
							(
								(and
									(not (Btst 282))
									(== heroType 3)
									(Btst 116)
									(not (Btst 228))
								)
								(Bset 282)
								(messager say: 1 6 7 0 self)
							)
							(
							(and (not (Btst 283)) (Btst 129) (not (Btst 130))) (Bset 283) (messager say: 1 6 8 0 self))
							(
								(and
									(not (Btst 284))
									(Btst 166)
									(Btst 179)
									(not (ego has: 30))
								)
								(Bset 284)
								(if (== heroType 12)
									(messager say: 1 6 15 0 self)
								else
									(messager say: 1 6 16 0 self)
								)
							)
							(
							(and (not (Btst 285)) (Btst 513) (not (Btst 514))) (Bset 285) (messager say: 1 6 17 0 self))
							(
								(and
									(not (Btst 286))
									(Btst 117)
									(== heroType 3)
									(not (Btst 62))
								)
								(Bset 286)
								(messager say: 1 6 18 0 self)
							)
							(
								(and
									(not (Btst 287))
									(== heroType 12)
									(Btst 156)
									(not (Btst 270))
								)
								(Bset 287)
								(messager say: 1 6 19 0 self)
							)
							(
							(and (not (Btst 288)) (Btst 139) (not (Btst 143))) (Bset 288) (messager say: 1 6 20 0 self))
							(
							(and (not (Btst 289)) (Btst 141) (not (Btst 143))) (Bset 289) (messager say: 1 6 21 0 self))
							(else (DailyMsg 1 6 3 self 470))
						)
					else
						(self cue:)
					)
				else
					(self cue:)
				)
			)
			(3
				(switch local0
					(1
						(if (> [egoStats 12] 0)
							(ego solvePuzzle: 495 6 2 2)
							(ego learn: 37 100)
							(messager say: 1 6 23 0 self)
						else
							(ego get: 37)
							(messager say: 1 6 24 0 self)
						)
					)
					(11
						(ego show:)
						(curRoom newRoom: 475)
					)
					(else  (self cue:))
				)
			)
			(4
				(if (== local0 1) (sndGets play:))
				(self cue:)
			)
			(5
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sBackTo460 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(messager say: 2 128 63 0 self)
			)
			(1
				(if (== local0 2)
					(messager say: 2 128 46 0 self)
				else
					(= ticks 1)
				)
			)
			(2
				(switch local0
					(1
						(messager say: 1 6 25 0 self)
					)
					(2
						(messager say: 1 6 48 0 self)
					)
					(3
						(messager say: 1 6 79 0 self)
					)
					(4
						(messager say: 1 6 81 0 self)
					)
					(5 (DailyMsg 1 6 84 self 470))
					(6 (DailyMsg 1 6 84 self 470))
					(7
						(messager say: 1 6 85 0 self)
					)
					(8
						(messager say: 1 6 85 0 self)
					)
					(else 
						(DailyMsg 1 6 84 self 470)
					)
				)
			)
			(3
				(if (or (== local0 1) (== local0 2))
					(messager say: 8 6 49 0 self)
				else
					(= ticks 1)
				)
			)
			(4
				(ego show:)
				(curRoom newRoom: 460)
			)
		)
	)
)

(instance sDoTheEyes of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds (Random 1 3)))
			(1
				(= local1 (Random 1 4))
				(if (OneOf local1 2 3)
					(pEyes setPri: 152 setCel: 0 setCycle: End self)
				else
					(self cue:)
				)
			)
			(2
				(if (OneOf local1 3 4)
					(pHands setCycle: End self)
				else
					(self cue:)
				)
			)
			(3 (self changeState: 0))
		)
	)
)

(instance sDoSecondEyes of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds (Random 1 3)))
			(1
				(= local2 (Random 1 4))
				(if (OneOf local2 2 3)
					(pEyes2 setPri: 152 setCel: 0 setCycle: End self)
				else
					(self cue:)
				)
			)
			(2
				(if (OneOf local2 3 4)
					(pOHands setCycle: End self)
				else
					(self cue:)
				)
			)
			(3 (self changeState: 0))
		)
	)
)

(instance pCandle of Prop
	(properties
		x 114
		y 130
		view 470
		signal $1001
		detailLevel 2
	)
)

(instance pReflect of Prop
	(properties
		x 197
		y 120
		view 470
		loop 1
		cel 3
		signal $1001
		detailLevel 2
	)
	
	(method (init)
		(super init:)
		(= actions egoTeller)
	)
)

(instance pEyes2 of Prop
	(properties
		x 159
		y 40
		view 471
		loop 1
		cel 3
		signal $1001
		detailLevel 2
	)
	
	(method (doVerb theVerb)
		(fortuneTeller doVerb: theVerb)
	)
)

(instance pEyes of Prop
	(properties
		x 92
		y 25
		view 471
		cel 3
		signal $1001
		detailLevel 2
	)
	
	(method (doVerb theVerb)
		(davyTeller doVerb: theVerb)
	)
)

(instance pEarring of Prop
	(properties
		x 147
		y 60
		view 471
		loop 2
		cel 2
		signal $1001
		detailLevel 2
	)
	
	(method (doVerb theVerb)
		(fortuneTeller doVerb: theVerb)
	)
)

(instance pEarring2 of Prop
	(properties
		x 170
		y 62
		view 471
		loop 3
		cel 2
		signal $1001
		detailLevel 2
	)
	
	(method (doVerb theVerb)
		(fortuneTeller doVerb: theVerb)
	)
)

(instance pShirt of Prop
	(properties
		x 85
		y 74
		view 471
		loop 4
		signal $1001
		detailLevel 2
	)
	
	(method (doVerb theVerb)
		(davyTeller doVerb: theVerb)
	)
)

(instance pShirt2 of Prop
	(properties
		x 89
		y 90
		view 471
		loop 5
		cel 3
		signal $1001
		detailLevel 2
	)
	
	(method (doVerb theVerb)
		(davyTeller doVerb: theVerb)
	)
)

(instance pOHands of Prop
	(properties
		x 92
		y 130
		view 471
		loop 6
		cel 3
		signal $1001
		detailLevel 2
	)
	
	(method (doVerb theVerb)
		(davyTeller doVerb: theVerb)
	)
)

(instance pBracelet of Prop
	(properties
		x 156
		y 115
		view 471
		loop 7
		cel 2
		signal $1001
		detailLevel 2
	)
	
	(method (doVerb theVerb)
		(fortuneTeller doVerb: theVerb)
	)
)

(instance pHands of Prop
	(properties
		x 156
		y 115
		view 471
		loop 8
		signal $5001
		detailLevel 2
	)
	
	(method (doVerb theVerb)
		(fortuneTeller doVerb: theVerb)
	)
)

(instance pCandles2 of Prop
	(properties
		x 78
		y 149
		view 471
		loop 10
		cel 2
		signal $1001
		detailLevel 2
	)
)

(instance fGarlic1 of Feature
	(properties
		noun 21
		nsLeft 22
		nsTop 1
		nsRight 40
		nsBottom 72
		sightAngle 40
		x 31
		y 36
	)
)

(instance fGarlic2 of Feature
	(properties
		noun 21
		nsLeft 241
		nsTop 51
		nsRight 263
		nsBottom 93
		sightAngle 40
		x 252
		y 72
	)
)

(instance fDavy of Feature
	(properties
		noun 22
		nsLeft 65
		nsTop 16
		nsRight 104
		nsBottom 125
		sightAngle 40
		x 84
		y 70
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 1)
			(messager say: 22 1 0 0)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance fFortuneTeller of Feature
	(properties
		noun 1
		nsLeft 113
		nsTop 27
		nsRight 179
		nsBottom 108
		sightAngle 40
		x 146
		y 67
	)
	
	(method (doVerb theVerb &tmp temp0)
		(if (== theVerb 15)
			(cond 
				(
				(and (not ((inventory at: 0) amount?)) (<= kopeks 99)) (messager say: 1 6 9))
				(
				(and (!= prevRoomNum 475) (!= Day gCurrentDay_11))
					(if (not (Btst 278))
						(if (and (not (Btst 110)) (Btst 300))
							(messager say: 1 15 11)
						else
							(= gCurrentDay_11 Day)
							(ego solvePuzzle: 443 2)
							(if (>= kopeks 100)
								(= kopeks (- kopeks 100))
							else
								((inventory at: 0)
									amount: (- ((inventory at: 0) amount?) 1)
								)
							)
							(ego show:)
							(curRoom newRoom: 475)
						)
					else
						(messager say: 1 15 10)
					)
				)
				(else (messager say: 1 6 82))
			)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance fRing of Feature
	(properties
		noun 23
		nsLeft 133
		nsTop 116
		nsRight 142
		nsBottom 120
		sightAngle 40
		x 137
		y 138
		z 20
	)
)

(instance fBracelet of Feature
	(properties
		noun 24
		nsLeft 156
		nsTop 102
		nsRight 173
		nsBottom 114
		sightAngle 40
		x 164
		y 128
		z 20
	)
)

(instance fCards of Feature
	(properties
		noun 25
		nsLeft 125
		nsTop 121
		nsRight 163
		nsBottom 127
		sightAngle 40
		x 144
		y 189
		z 65
	)
)

(instance fHero of Feature
	(properties
		noun 2
		modNum 28
		nsLeft 184
		nsTop 35
		nsRight 236
		nsBottom 137
		sightAngle 40
		x 210
		y 86
	)
	
	(method (doVerb theVerb)
		(return
			(if (== theVerb 1)
				(messager say: 34 1 0 0)
				(return 1)
			else
				(super doVerb: theVerb)
			)
		)
	)
)

(instance fCandle1 of Feature
	(properties
		noun 26
		nsLeft 106
		nsTop 110
		nsRight 120
		nsBottom 128
		sightAngle 40
		x 113
		y 139
		z 20
	)
)

(instance fCandle2 of Feature
	(properties
		noun 26
		nsLeft 77
		nsTop 130
		nsRight 102
		nsBottom 149
		sightAngle 40
		x 89
		y 139
	)
)

(instance fCurtain1 of Feature
	(properties
		noun 27
		nsTop 38
		nsRight 21
		nsBottom 59
		sightAngle 40
		x 10
		y 48
	)
)

(instance fCurtain2 of Feature
	(properties
		noun 27
		nsLeft 43
		nsTop 1
		nsRight 138
		nsBottom 42
		sightAngle 40
		x 90
		y 21
	)
)

(instance fPeppers of Feature
	(properties
		noun 28
		nsLeft 220
		nsTop 1
		nsRight 319
		nsBottom 70
		sightAngle 40
		x 269
		y 35
	)
)

(instance fChair of Feature
	(properties
		noun 29
		nsLeft 225
		nsTop 101
		nsRight 308
		nsBottom 152
		sightAngle 40
		x 266
		y 126
	)
)

(instance fPlant of Feature
	(properties
		noun 30
		nsLeft 27
		nsTop 74
		nsRight 63
		nsBottom 123
		sightAngle 40
		x 45
		y 98
	)
)

(instance fPot of Feature
	(properties
		noun 31
		nsLeft 21
		nsTop 124
		nsRight 68
		nsBottom 154
		sightAngle 40
		x 44
		y 139
	)
)

(instance fTable of Feature
	(properties
		noun 32
		nsLeft 15
		nsTop 112
		nsRight 182
		nsBottom 140
		sightAngle 40
		x 98
		y 126
	)
)

(instance fortuneTeller of Teller
	(properties)
	
	(method (init)
		(super init: &rest)
		(= talker fTalker)
	)
	
	(method (sayMessage)
		(switch iconValue
			(131 (Bset 144))
			(1 (Bset 291))
			(2 (Bset 313))
			(132 (Bset 314))
		)
		(super sayMessage: &rest)
	)
	
	(method (showCases)
		(super
			showCases:
				1
				(if (Btst 150) (not (Btst 291)) else 0)
				2
				(if (and (Btst 116) (not (Btst 228)))
					(not (Btst 292))
				else
					0
				)
				132
				(if (and (Btst 237) (not (Btst 312)))
					(not (Btst 314))
				else
					0
				)
		)
	)
)

(instance davyTeller of Teller
	(properties)
	
	(method (init)
		(super init: &rest)
		(= talker gTalker)
	)
	
	(method (sayMessage)
		(if (== iconValue 78) (Bset 186))
		(super sayMessage: &rest)
	)
)

(instance egoTeller of Teller
	(properties)
	
	(method (sayMessage)
		(switch iconValue
			(2
				(Bset 292)
				(super sayMessage:)
			)
			(8
				(Bset 293)
				(super sayMessage:)
			)
			(12
				(Bset 294)
				(super sayMessage:)
			)
			(13
				(Bset 295)
				(super sayMessage:)
			)
			(14
				(Bset 296)
				(super sayMessage:)
			)
			(46
				(self clean:)
				(curRoom setScript: sBackTo460)
			)
			(63
				(Bclr 51)
				(= gTeller 0)
				(curRoom setScript: sBackTo460)
			)
			(else 
				(super sayMessage: &rest)
			)
		)
	)
	
	(method (showCases)
		(super
			showCases:
				8
				(if (and (Btst 159) global348)
					(not (Btst 293))
				else
					0
				)
				12
				(if (and (> dreamNum 0) (not (>= dreamNum 10)))
					(not (Btst 294))
				else
					0
				)
				13
				(if (Btst 153) (not (Btst 295)) else 0)
				14
				(if (and (Btst 166) (not (Btst 179)))
					(not (Btst 296))
				else
					0
				)
		)
	)
)

(instance sndGets of Sound
	(properties
		number 934
	)
)

(instance gTalker of GloryTalker
	(properties
		x 0
		y 17
		talkWidth 250
		view 467
		textX 5
		textY 110
	)
	
	(method (init)
		(super init: gMouth 0 gEyes gFrame &rest)
	)
)

(instance gFrame of View
	(properties
		view 467
	)
)

(instance gMouth of Prop
	(properties
		x 78
		y 38
		view 467
		loop 1
	)
)

(instance gEyes of Prop
	(properties
		x 72
		y 25
		view 467
		loop 2
	)
)

(instance fTalker of GloryTalker
	(properties
		x 130
		y 0
		talkWidth 319
		view 468
		textX 100
		textY 130
	)
	
	(method (init)
		(super init: fMouth 0 fEyes fFrame &rest)
	)
)

(instance fFrame of View
	(properties
		view 468
	)
)

(instance fMouth of Prop
	(properties
		x 145
		y 52
		view 468
		loop 1
	)
)

(instance fEyes of Prop
	(properties
		x 136
		y 39
		view 468
		loop 2
	)
)
