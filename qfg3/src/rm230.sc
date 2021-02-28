;;; Sierra Script 1.0 - (do not remove this comment)
(script# 230)
(include sci.sh)
(use Main)
(use TellerIcon)
(use OccasionalCycle)
(use Vendor)
(use Bazaar)
(use GloryTalker)
(use n233)
(use PAvoid)
(use PolyPath)
(use Polygon)
(use Feature)
(use LoadMany)
(use StopWalk)
(use Chase)
(use Timer)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm230 0
	tMoneyChangerTalker 1
	wondering 2
	spBurst 3
	aFruitBasket 4
	aMoneyChanger 5
	aFruitMerchant 6
	aLeatherMerchant 7
)

(local
	local0
	theAFruitMerchant
	newView
	theTheVerb
	local4
	local5
	local6
	local7
	local8 =  4103
	local9
	local10 =  2
	local11
	local12
	egoLoop
	newSpell
	newProp
	local16
	local17
	egoCycleSpeed
	egoMoveSpeed
	[local20 8] = [0 1 0 2 0 3 2 1]
	[local28 8] = [2 3 0 3 0 1 2 3]
	[local36 15] = [0 -20 -23 -21 -22 -26 -57 -34 999 0 20 23 24 25 999]
	[local51 7] = [0 -2 1 5 -12 -73 999]
	[local58 4] = [0 3 4 999]
	[local62 6] = [0 50 29 -12 -75 999]
	[local68 6] = [0 28 29 12 30 999]
	[local74 5]
	[local79 5]
	[local92_2 4]
	[local100_2 4]
	[local92_2_2 8] = [0 1 2 3 2 3 0 1]
	[local100_2_2 3] = [0 -2 999]
)
(class Spell of Actor
	(properties
		x 0
		y 0
		z 0
		heading 0
		noun 0
		modNum -1
		nsTop 0
		nsLeft 0
		nsBottom 0
		nsRight 0
		sightAngle 26505
		actions 0
		onMeCheck $6789
		approachX 0
		approachY 0
		approachDist 0
		_approachVerbs 0
		yStep 10
		view -1
		loop 0
		cel 0
		priority 0
		underBits 0
		signal $4000
		lsTop 0
		lsLeft 0
		lsBottom 0
		lsRight 0
		brTop 0
		brLeft 0
		brBottom 0
		brRight 0
		palette 0
		scaleSignal $0001
		scaleX 96
		scaleY 96
		maxScale 128
		cycleSpeed 0
		script 0
		cycler 0
		timer 0
		detailLevel 0
		scaler 0
		illegalBits $8000
		xLast 0
		yLast 0
		xStep 15
		origStep 770
		moveSpeed 0
		blocks 0
		baseSetter 0
		mover 0
		looper 0
		viewer 0
		avoider 0
		code 0
	)
	
	(method (init param1 &tmp temp0)
		(super init: &rest)
		(switch param1
			(20 (= temp0 2))
			(81 (= temp0 0))
			(83 (= temp0 4))
		)
		(self setLoop: temp0 setMotion: MoveTo projX projY self)
	)
	
	(method (doit)
		(super doit: &rest)
		(if
			(and
				(> y (- (aHarami y?) 50))
				(< y (+ (aHarami y?) 1))
				(> x (- (aHarami x?) 21))
				(< x (+ (aHarami x?) 12))
			)
			(self setMotion: 0)
			(= local10 1)
			(curRoom setScript: onHisButt 0 (+ (self loop?) 1))
			(self dispose:)
		)
	)
	
	(method (cue)
		(if
			(and
				(> projY (- (aHarami y?) 50))
				(< projY (+ (aHarami y?) 1))
				(> projX (- (aHarami x?) 21))
				(< projX (+ (aHarami x?) 12))
			)
			(= local10 1)
			(curRoom setScript: onHisButt 0 (+ (self loop?) 1))
		else
			(self dispose:)
			(if local16
				(sfx number: 930 play:)
				(spBurst
					x: projX
					y: projY
					init:
					setLoop: 9
					setCycle: End spBurst
				)
			)
			(HandsOn)
		)
	)
)

(instance rm230 of Rm
	(properties
		noun 7
		picture 230
		vanishingY -300
	)
	
	(method (init)
		(ego init: normalize: setScale: 0 noun: 2)
		(if (or Night (Btst 135))
			(curRoom
				addObstacle:
					((Polygon new:)
						type: 2
						init:
							0
							156
							0
							0
							319
							0
							319
							115
							302
							115
							275
							126
							229
							111
							178
							121
							161
							103
							105
							97
							82
							103
						yourself:
					)
			)
		else
			(cSound fade: 40 10 5 0)
			(curRoom
				addObstacle:
					((Polygon new:)
						type: 2
						init:
							65
							108
							0
							142
							0
							0
							319
							0
							319
							141
							279
							135
							240
							116
							198
							111
							182
							120
							170
							114
							158
							125
							72
							118
						yourself:
					)
					((Polygon new:)
						type: 2
						init:
							100
							189
							95
							176
							106
							150
							157
							154
							151
							163
							169
							170
							184
							167
							208
							181
							222
							177
							251
							176
							260
							189
						yourself:
					)
			)
			(= [local74 0] @local36)
			(= [local79 0] @local51)
			(= [local79 1] @local58)
			(= [local100_2 0] @local62)
			(= [local92_2 0] @local68)
			(aFruitBasket init:)
			(aLeatherMerchant
				setPri: 3
				setCycle: OccasionalCycle self 1 30 110
				init:
			)
			(aMoneyChanger setPri: 1 init:)
			(aFruitMerchant
				init:
				setCycle: OccasionalCycle self 1 25 81
			)
			(if (== Day 5) (Bset 22))
			(if
				(and
					(not (Btst 22))
					(< Day 5)
					(not Night)
					(not (Btst 135))
				)
				(aHarami init:)
				(LoadMany 143 230)
				(LoadMany 128 233)
				(aMoneyChanger setCycle: OccasionalCycle self 1 20 150)
			else
				(self setRegions: 51)
			)
			(leatherA init: addToPic:)
			(leatherB init: addToPic:)
			(fruitA init: addToPic:)
			(bananas init:)
			(fruit init:)
			(hides init:)
			(belts init:)
			(skinrugs init:)
			(zebrarug init:)
			(giraffe init:)
			(leopardskin init:)
			(money_sign init:)
			(hideracks init:)
			(watermelon init:)
			(basket init:)
			(pitcher init:)
			(roundtray init:)
			(peppers init:)
			(rectangletray init:)
			(redplate init:)
			(brassvase init:)
			(shoes init:)
		)
		(super init: &rest)
		(self setScript: from240)
		(if
			(and
				(not (Btst 22))
				(< Day 5)
				(not Night)
				(not (Btst 135))
			)
			(theGame save: 1)
			(HandsOff)
		)
	)
	
	(method (dispose)
		(if local6 (local6 dispose:))
		(if local7 (local7 dispose:))
		(if local5 (local5 dispose:))
		(UnLoad 143 230)
		(UnLoad 128 233)
		(LoadMany 0 47 55 40 231 232 233 234 235)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(65
				(if (Btst 22)
					((ScriptID 7 5) init: global455)
				else
					(messager say: 0 0 84)
				)
			)
			(1
				(messager say: 7 1 0 (if Night 0 else 1))
			)
			(20
				(if (or (Btst 22) Night)
					(messager say: 25 6 66)
				else
					(ego drop: 10 1)
					(= theTheVerb theVerb)
					(= projX ((user curEvent?) x?))
					(= projY ((user curEvent?) y?))
					(ego setScript: castFlameDart 0 theVerb)
				)
			)
			(80
				(if (or (Btst 22) Night)
					(messager say: 25 6 65)
				else
					(= theTheVerb theVerb)
					(ego setScript: calmCast)
				)
			)
			(81
				(if (or (Btst 22) Night)
					(messager say: 25 6 65)
				else
					(= projX ((user curEvent?) x?))
					(= projY ((user curEvent?) y?))
					(= theTheVerb theVerb)
					(ego setScript: castFlameDart 0 theVerb)
				)
			)
			(83
				(if (or (Btst 22) Night)
					(messager say: 25 6 65)
				else
					(= projX ((user curEvent?) x?))
					(= projY ((user curEvent?) y?))
					(= theTheVerb theVerb)
					(ego setScript: castFlameDart 0 theVerb)
				)
			)
			(86
				(if (or (Btst 22) Night)
					(messager say: 25 6 65)
				else
					(ego setScript: (ScriptID 62 0))
				)
			)
			(76
				(if (or (Btst 22) Night)
					(messager say: 25 6 65)
				else
					(ego setScript: (ScriptID 12) 0 76)
				)
			)
			(82
				(if (or (Btst 22) Night)
					(messager say: 25 6 65)
				else
					(messager say: 25 6 85)
				)
			)
			(75
				(if (or (Btst 22) Night)
					(messager say: 25 6 65)
				else
					(ego setScript: (ScriptID 13))
				)
			)
			(77
				(if (or (Btst 22) Night)
					(messager say: 25 6 65)
				else
					(ego setScript: (ScriptID 12) 0 77)
				)
			)
			(88
				(if (or (Btst 22) Night) (messager say: 25 6 65))
			)
			(78
				(if (or (Btst 22) Night)
					(messager say: 25 6 65)
				else
					(ego setScript: (ScriptID 12) 0 78)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance haramiHeadsOut of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(messager say: 25 6 80 0 self)
			)
			(1
				(Bset 159)
				(Bset 119)
				(if (!= arcadeDifficulty 3)
					(= egoCycleSpeed (ego cycleSpeed?))
					(= egoMoveSpeed (ego moveSpeed?))
					(ego moveSpeed: 6 cycleSpeed: 6)
				)
				(aFruitMerchant setCycle: 0 stopUpd:)
				(aLeatherMerchant setCycle: 0 stopUpd:)
				(aHarami setCycle: Beg self)
			)
			(2
				(aMoneyChanger setLoop: 0 setCycle: Fwd)
				(= cycles 5)
			)
			(3
				(aHarami
					view: 953
					loop: 0
					cycleSpeed: (ego cycleSpeed?)
					moveSpeed: (ego moveSpeed?)
					origStep: (if (== arcadeDifficulty 3) 3076 else 2563)
					setStep: (if (== arcadeDifficulty 3) 12 else 10) 3
					setCycle: Walk
					setMotion: PolyPath 55 120 self
				)
				(cSound number: 230 setLoop: 1 nextSong: 340 play:)
			)
			(4
				(messager say: 1 6 60 0 self)
			)
			(5
				(HandsOn)
				(if local9
					(self cue:)
				else
					(aHarami setMotion: PolyPath 170 140 self)
				)
			)
			(6
				(aHarami
					cycleSpeed: (ego cycleSpeed?)
					moveSpeed: (ego moveSpeed?)
					setMotion: PolyPath 350 160 self
				)
			)
			(7
				(if (== egoGait 1)
					(Bset 23)
					(= theTheVerb 0)
					(ego solvePuzzle: 216 8 addHonor: 40)
				else
					(Bset 28)
					(ego addHonor: -100)
					(Bset 19)
				)
				(Bset 22)
				(aHarami hide:)
				(aMoneyChanger setCycle: End)
				(Bclr 119)
				(= cycles 3)
			)
			(8
				(curRoom setScript: wondering)
			)
		)
	)
)

(instance spEffect of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(aHarami setMotion: 0)
				(if local16 (sfx number: 930 play:))
				(if (!= register 5) (= temp0 9) else (= temp0 10))
				((= newProp (Prop new:))
					view: 21
					loop: temp0
					x: (aHarami x?)
					y: (- (aHarami y?) 30)
					cycleSpeed: 1
					init:
					setPri: 15
					setCycle: End self
				)
			)
			(1
				(newProp dispose:)
				(Palette palSET_INTENSITY 0 255 1000)
				(Palette palSET_INTENSITY 0 255 100)
				(self dispose:)
			)
		)
	)
)

(instance castFlameDart of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(Face ego aHarami self)
				(if (== register 20) (Bset 25) else (Bset 26))
			)
			(1
				(= egoLoop (ego loop?))
				(ego
					view: (if (!= register 20) 14 else 9)
					loop: [local20 egoLoop]
					cel: 0
					setCycle: CT 2 1 self
				)
				(if (!= register 20)
					(sfx number: 13 play:)
					(= local16 1)
				else
					(sfx number: 916 play:)
				)
			)
			(2
				(ego setCycle: End self)
				((= newSpell (Spell new:))
					view: (if (!= register 20) 21 else 46)
					x: (+ (ego x?) 20)
					y: (- (ego y?) 35)
					origStep: 6922
					init: register
					setCycle: Fwd
				)
			)
			(3
				(ego normalize: egoLoop addHonor: -50)
				(= cycles (+ (ego cycleSpeed?) 5))
			)
			(4 (self dispose:))
		)
	)
)

(instance knockFruitOver of Script
	(properties)
	
	(method (doit)
		(super doit: &rest)
		(if
			(and
				(not (Btst 80))
				(== local9 1)
				(< 135 (aHarami x?))
				(< (aHarami x?) 223)
			)
			(aHarami setCycle: 0 stopUpd:)
			(Bset 80)
			(= local10 2)
			(= theTheVerb 0)
			(ego solvePuzzle: 218 5 13)
			(curRoom setScript: onHisButt)
		)
	)
	
	(method (changeState newState &tmp temp0 temp1)
		(switch (= state newState)
			(0
				(HandsOff)
				(if (< (= temp0 (- (ego moveSpeed?) 1)) 0)
					(= temp0 0)
				)
				(if (< (= temp1 (- (ego moveSpeed?) 1)) 0)
					(= temp1 0)
				)
				(if (!= egoGait 1)
					(ego
						view: 1
						moveSpeed: temp0
						cycleSpeed: temp1
						setStep: 6 4
						setCycle: StopWalk 5
					)
				)
				(if (< (ego x?) 108)
					(= local11 -1)
					(ego setMotion: PolyPath 88 175 self)
				else
					(= local11 1)
					(ego setMotion: PolyPath 135 160 self)
				)
			)
			(1
				(ego view: 14)
				(Bset 24)
				(sfx number: 922 play:)
				(if (== local11 -1)
					(ego setLoop: 2 cel: 0 setCycle: End)
				else
					(ego setLoop: 3 cel: 6 x: 134 y: 158 setCycle: End)
				)
				(aFruitBasket setCycle: CT 2 1 self)
				(= local9 1)
			)
			(2
				(aFruitBasket setCycle: CT 4 1 self)
			)
			(3
				((= newView (View new:))
					view: 231
					loop: 1
					cel: 0
					x: 113
					y: 162
					signal: 16385
					noun: 33
					init:
					setPri: 1
				)
				(= cycles 3)
			)
			(4
				(if (== local11 -1)
					(ego setCycle: Beg self)
				else
					(ego setCycle: CT 6 -1 self)
				)
			)
			(5
				(aFruitBasket noun: 34 setPri: 14)
				(aFruitMerchant setLoop: 2 setCycle: Fwd)
				(if (== local11 -1)
					(ego loop: 6 cel: 2)
				else
					(ego loop: 7 cel: 2 x: 135 y: 160)
				)
				(ego view: 0 xStep: 3 yStep: 2 normalize: addHonor: 40)
				(= ticks 4)
			)
			(6
				(if (< (onHisButt state?) 0) (Bclr 123) (HandsOn))
				(aFruitBasket stopUpd:)
				(newView stopUpd:)
				(self dispose:)
			)
		)
	)
)

(instance onHisButt of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(switch theTheVerb
					(0 0)
					(20 (ego solvePuzzle: 217 5 4))
					(else  (ego solvePuzzle: 215 7))
				)
				(Bclr 119)
				(if (and (!= register 0) (!= register 3))
					(self setScript: spEffect self register)
				else
					(= cycles 1)
				)
			)
			(1
				(aHarami
					view: 953
					setLoop: local10
					cel: 0
					x: (- (aHarami x?) 25)
					cycleSpeed: (if (== (ego script?) calmCast) 15 else 12)
					moveSpeed: (aHarami cycleSpeed?)
					setCycle: End self
					setMotion: MoveTo (+ (aHarami x?) 15) (+ (aHarami y?) 2)
				)
			)
			(2
				(aMoneyChanger setCycle: End self)
			)
			(3
				(aHarami ignoreActors: 1 stopUpd:)
				(= seconds 2)
			)
			(4
				(if (aFruitMerchant cycler?)
					(aFruitMerchant setCycle: End self)
				else
					(self cue:)
				)
			)
			(5
				(aGuard1 init: setCycle: Walk)
				(if (< (aHarami x?) 270)
					(aGuard1
						setMotion: PolyPath (+ (aHarami x?) 50) (+ (aHarami y?) 15) self
					)
				else
					(aGuard1
						setMotion: PolyPath 300 (+ (aHarami y?) 15) self
					)
				)
			)
			(6
				(messager say: 5 6 60 0 self)
			)
			(7
				(Bset 22)
				(Bclr 80)
				(globalSound fade:)
				(soundFx fade:)
				(curRoom newRoom: 340)
			)
		)
	)
)

(instance seekNkill of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(HandsOff)
				(Face ego aHarami self)
				(if (== register 20) (Bset 25) else (Bset 26))
			)
			(1
				(= egoLoop (ego loop?))
				(ego
					view: (if (!= register 20) 14 else 9)
					loop: [local20 egoLoop]
					cel: 0
					setCycle: CT 2 1 self
				)
			)
			(2
				(switch register
					(20 (= temp0 2))
					(81 (= temp0 0))
					(83 (= temp0 4))
				)
				(ego setCycle: End self)
				((= newSpell (Actor new:))
					view: (if (!= register 20) 21 else 46)
					x: (+ (ego x?) 20)
					y: (- (ego y?) 35)
					z: 30
					moveSpeed: 0
					cycleSpeed: 0
					origStep: 6922
					scaleSignal: 1
					scaleX: 96
					scaleY: 96
					init:
					setLoop: temp0
					setCycle: Fwd
					setMotion: Chase aHarami 1 self
				)
			)
			(3
				(ego normalize: egoLoop)
				(= local10 1)
				(curRoom setScript: onHisButt 0 (+ (newSpell loop?) 1))
				(newSpell dispose:)
				(self dispose:)
			)
		)
	)
)

(instance calmCast of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: 0)
				(Face ego aHarami self)
				(Bset 27)
				(= egoLoop (ego loop?))
			)
			(1
				(ego
					view: 15
					loop: [local28 egoLoop]
					cel: 0
					addHonor: 50
					setCycle: End self
				)
				(sfx number: 900 play:)
			)
			(2
				(if
				(and (ego castSpell: 24) (< (aHarami x?) 300))
					(= local10 1)
					(curRoom setScript: onHisButt)
				)
				(if (< (onHisButt state?) 0) (HandsOn))
				(ego normalize: egoLoop)
				(self dispose:)
			)
		)
	)
)

(instance from240 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego x: -10 y: 200 setMotion: PolyPath 50 175 self)
				(if
					(and
						(not (Btst 22))
						(< Day 5)
						(not Night)
						(not (Btst 135))
					)
					(aHarami setCycle: End)
				)
			)
			(1
				(if
					(and
						(not (Btst 22))
						(< Day 5)
						(not Night)
						(not (Btst 135))
					)
					(Bset 122)
					(Face ego aHarami self)
				else
					(self cue:)
				)
			)
			(2
				(if
					(and
						(not (Btst 22))
						(< Day 5)
						(not Night)
						(not (Btst 135))
					)
					(ego code: moneyCode)
					(curRoom setScript: haramiHeadsOut)
				else
					(HandsOn)
					(if (or Night (Btst 135))
						(ego code: nightCode)
					else
						(ego code: bazCode)
					)
					(egoTell init: ego @local36 @local74)
					(moneyTell
						init: aMoneyChanger @local51 @local79 @local100_2_2
					)
					(leatherTell init: aLeatherMerchant @local68 @local92_2)
					(fruitTell init: aFruitMerchant @local62 @local100_2)
					(self dispose:)
				)
			)
		)
	)
)

(instance sExit of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(cond 
					((<= (ego x?) 5) (ego setMotion: MoveTo -30 200 self))
					((>= (ego x?) 315) (ego setMotion: MoveTo 335 177 self))
					(else (ego setMotion: PolyPath (ego x?) 250 self))
				)
			)
			(1
				(if
					(and
						(not
							(if (or (Btst 23) (Btst 24) (Btst 25) (Btst 26))
							else
								(Btst 27)
							)
						)
						(== egoGait 1)
					)
					(Bset 23)
				)
				(curRoom newRoom: 240)
			)
		)
	)
)

(instance wondering of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOn)
				(if (or (== theTheVerb 0) (== theTheVerb 20))
					0
				else
					(ego solvePuzzle: 214 4)
				)
				(if (Btst 24)
					(ego code: fruitCode)
				else
					(ego code: bazCode)
				)
				(egoTell init: ego @local36 @local74)
				(moneyTell
					init: aMoneyChanger @local51 @local79 @local100_2_2
				)
				(moneyTell
					init: aMoneyChanger @local51 @local79 @local100_2_2
				)
				(leatherTell init: aLeatherMerchant @local68 @local92_2)
				(fruitTell init: aFruitMerchant @local62 @local100_2)
				(aFruitBasket stopUpd:)
				(aMoneyChanger stopUpd:)
				(= seconds 14)
			)
			(1
				(HandsOff)
				(ego setMotion: 0 code: 0)
				(= cycles 3)
			)
			(2
				((Actor new:)
					view: 190
					loop: 1
					cel: 0
					x: 360
					y: 163
					xStep: 3
					yStep: 3
					init:
					setAvoider: PAvoider
					setCycle: Walk
					setMotion: PolyPath 285 163 self
				)
			)
			(3
				(messager say: 5 6 68 0 self)
			)
			(4
				(globalSound fade:)
				(soundFx fade:)
				(if (!= arcadeDifficulty 3)
					(ego moveSpeed: egoMoveSpeed cycleSpeed: egoCycleSpeed)
				)
				(curRoom newRoom: 340)
			)
		)
	)
)

(instance egoFell of Script
	(properties)
	
	(method (changeState newState &tmp egoLoop_2 temp1)
		(switch (= state newState)
			(0
				(= egoLoop_2 (ego loop?))
				(ego
					view: 6
					cel: 0
					setMotion: 0
					setLoop: [local92_2_2 egoLoop_2]
					setCycle: End self
				)
			)
			(1
				(if (mod (ego loop?) 2)
					(= temp1 -30)
					(= egoLoop_2 5)
				else
					(= temp1 30)
					(= egoLoop_2 4)
				)
				(ego
					view: 35
					x: (+ (ego x?) temp1)
					cel: 0
					setLoop: egoLoop_2
					setCycle: End self
				)
			)
			(2
				(messager say: 25 6 76 0 self)
			)
			(3
				(if (mod (ego loop?) 2)
					(= temp1 16)
				else
					(= temp1 -16)
				)
				(ego x: (+ (ego x?) temp1) normalize: changeGait: 0 0)
				(= cycles 5)
			)
			(4 (self dispose:))
		)
	)
)

(instance bazCode of Code
	(properties)
	
	(method (doit)
		(cond 
			(
				(and
					(< (ego distanceTo: aMoneyChanger) 40)
					(& local8 $0001)
				)
				(ego setMotion: 0)
				(if (not (Btst 41))
					(Bset 41)
					(= local8 (& local8 $fffe))
					(messager say: 1 6 5)
				else
					(= local8 (& local8 $fffe))
					(messager say: 1 6 20)
				)
			)
			(
				(and
					(< (ego distanceTo: aFruitMerchant) 50)
					(& local8 $0002)
				)
				(= local8 (& local8 $fffd))
				(aFruitMerchant newGreeting:)
			)
			(
				(and
					(< (ego distanceTo: aLeatherMerchant) 20)
					(& local8 $0004)
				)
				(ego setMotion: 0)
				(= local8 (& local8 $fffb))
				(messager say: 3 6 27)
			)
			((not (if (< 5 (ego x?)) (< (ego x?) 315)))
				(if
					(or
						(== (curRoom script?) wondering)
						(not (curRoom script?))
					)
					(curRoom setScript: sExit)
				)
			)
			(
				(and
					(> (ego y?) 183)
					(or
						(== (curRoom script?) wondering)
						(not (curRoom script?))
					)
				)
				(curRoom setScript: sExit)
			)
		)
	)
)

(instance nightCode of Code
	(properties)
	
	(method (doit)
		(cond 
			(
				(and
					(not (if (< 5 (ego x?)) (< (ego x?) 315)))
					(not (curRoom script?))
				)
				(curRoom setScript: sExit)
			)
			(
			(and (> (ego y?) 183) (not (curRoom script?))) (curRoom setScript: sExit))
		)
	)
)

(instance fruitCode of Code
	(properties)
	
	(method (doit)
		(cond 
			(
				(and
					(== egoGait 1)
					(ego mover?)
					(& (ego onControl:) $0008)
					(not (ego script?))
				)
				(ego setScript: egoFell)
			)
			(
				(and
					(< (ego distanceTo: aMoneyChanger) 40)
					(& local8 $0001)
				)
				(ego setMotion: 0)
				(if (not (Btst 41))
					(Bset 41)
					(= local8 (& local8 $fffe))
					(messager say: 1 6 5)
				else
					(= local8 (& local8 $fffe))
					(messager say: 1 6 20)
				)
			)
			(
				(and
					(< (ego distanceTo: aFruitMerchant) 50)
					(& local8 $0002)
				)
				(ego setMotion: 0)
				(= local8 (& local8 $fffd))
				(aFruitMerchant setCycle: Beg)
				(messager say: 4 6 77 0 wondering)
			)
			(
				(and
					(< (ego distanceTo: aLeatherMerchant) 20)
					(& local8 $0004)
				)
				(ego setMotion: 0)
				(= local8 (& local8 $fffb))
				(messager say: 3 6 27)
			)
			((not (if (< 5 (ego x?)) (< (ego x?) 315)))
				(if
					(or
						(== (curRoom script?) wondering)
						(not (curRoom script?))
					)
					(curRoom setScript: sExit)
				)
			)
			(
				(and
					(> (ego y?) 183)
					(or
						(== (curRoom script?) wondering)
						(not (curRoom script?))
					)
				)
				(curRoom setScript: sExit)
			)
		)
	)
)

(instance moneyCode of Code
	(properties)
	
	(method (doit)
		(if
			(and
				(< (ego distanceTo: aMoneyChanger) 40)
				(not local4)
			)
			(= local4 1)
			(ego setMotion: 0)
			(messager say: 1 6 60)
		)
	)
)

(instance aHarami of Actor
	(properties
		x 42
		y 106
		noun 26
		view 954
		loop 2
		priority 9
		signal $1010
	)
	
	(method (doVerb theVerb)
		(cond 
			(
			(and (== arcadeDifficulty 1) (not (ego script?)))
				(cond 
					(
					(or (== theVerb 20) (== theVerb 81) (== theVerb 83))
						(if (< x 260)
							(if (== theVerb 20) (ego drop: 10 1))
							(ego setScript: seekNkill 0 theVerb)
						else
							(= projX ((user curEvent?) x?))
							(= projY ((user curEvent?) y?))
							(= theTheVerb theVerb)
							(if (== theVerb 20) (ego drop: 10 1))
							(ego setScript: castFlameDart 0 theVerb)
						)
					)
					((== theVerb 82) (curRoom doVerb: theVerb))
					(else (super doVerb: theVerb))
				)
			)
			(
			(or (== theVerb 20) (== theVerb 81) (== theVerb 83))
				(= theTheVerb theVerb)
				(= projX ((user curEvent?) x?))
				(= projY ((user curEvent?) y?))
				(if (== theVerb 20) (ego drop: 10 1))
				(ego setScript: castFlameDart 0 theVerb)
			)
			((== theVerb 82) (curRoom doVerb: theVerb))
			(else (super doVerb: theVerb))
		)
	)
)

(instance aGuard1 of Actor
	(properties
		x 360
		y 163
		noun 5
		yStep 3
		view 190
		loop 1
	)
)

(instance aFruitMerchant of MerchantActor
	(properties
		x 143
		y 183
		noun 4
		approachX 149
		approachY 157
		view 234
		loop 3
		priority 14
		signal $4010
		cycleSpeed 11
	)
	
	(method (init)
		(super init: &rest)
		(self approachVerbs: 2 4 59 10)
	)
	
	(method (newGreeting)
		(switch (mod Day 6)
			(0 (messager say: noun 6 44))
			(1 (messager say: noun 6 45))
			(2 (messager say: noun 6 46))
			(3 (messager say: noun 6 47))
			(4 (messager say: noun 6 48))
			(5 (messager say: noun 6 49))
		)
	)
)

(instance aLeatherMerchant of MerchantActor
	(properties
		x 146
		y 121
		z 20
		noun 3
		approachDist 20
		view 236
		loop 2
		signal $5000
		cycleSpeed 13
	)
	
	(method (init)
		(super init: &rest)
		(self approachVerbs: 2 4 59 10)
	)
)

(instance aFruitBasket of Prop
	(properties
		x 122
		y 163
		noun 29
		approachX 106
		approachY 151
		view 231
		signal $5000
		cycleSpeed 3
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(if (and (not (Btst 22)) (< Day 5))
					(Bset 123)
					(ego setScript: knockFruitOver)
				else
					(messager say: 25 6 78)
				)
			)
			(1 (super doVerb: theVerb))
			(else 
				(curRoom doVerb: theVerb)
			)
		)
	)
)

(instance aMoneyChanger of Actor
	(properties
		x 21
		y 103
		noun 1
		approachDist 40
		view 232
		priority 1
		signal $4010
		cycleSpeed 9
	)
	
	(method (init)
		(super init: &rest)
		(self approachVerbs: 2 4 59 10)
	)
)

(instance spBurst of Prop
	(properties
		view 21
		priority 15
		signal $4010
		cycleSpeed 0
	)
	
	(method (cue)
		(self dispose:)
	)
)

(instance egoTell of Teller
	(properties)
	
	(method (respond)
		(return
			(if (not local0)
				(super respond:)
			else
				(= local0 0)
				(cond 
					((not query) (return 1))
					((== query -999) (return 1))
					((== query 999) (self doParent:) (return 0))
					((and (< query 0) (not (self doChild: query))) (return 1))
				)
				(if (< query 0) (= query (- query)))
				(messager say: (client noun?) 5 query 0)
				(return 1)
			)
		)
	)
	
	(method (showDialog &tmp temp0)
		(if
		(== (= theAFruitMerchant (proc51_1)) aFruitBasket)
			(= theAFruitMerchant aFruitMerchant)
		)
		(= temp0 (ego distanceTo: theAFruitMerchant))
		(switch theAFruitMerchant
			(aMoneyChanger
				(if (> temp0 40) (messager say: 2 5 83) (return -999))
			)
			(aFruitMerchant
				(if (> temp0 60) (messager say: 2 5 83) (return -999))
			)
			(else 
				(if (> temp0 30) (messager say: 2 5 83) (return -999))
			)
		)
		(if
			(!=
				(ego heading?)
				(GetAngle
					(ego x?)
					(ego y?)
					(theAFruitMerchant x?)
					(theAFruitMerchant y?)
				)
			)
			(Face ego theAFruitMerchant)
		)
		((Timer new:) setCycle: self (+ (ego cycleSpeed?) 10))
		(= iconValue 0)
		(return -999)
	)
	
	(method (doChild)
		(return
			(switch query
				(-21
					(Bset 171)
					(ExchangeMoney)
					(return 0)
				)
				(-22 (Bset 171) (= query 22))
				(-20
					(cond 
						((== theAFruitMerchant aMoneyChanger) (= query 61))
						((== theAFruitMerchant aFruitMerchant) (= query 58))
						((== theAFruitMerchant aLeatherMerchant) (= query 31))
					)
				)
				(-23
					(cond 
						((== theAFruitMerchant aMoneyChanger)
							(= local8 (& local8 $efff))
							(ego addHonor: 2)
							(= query 23)
						)
						((== theAFruitMerchant aFruitMerchant) (= query 59))
						((== theAFruitMerchant aLeatherMerchant) (= query 32))
					)
				)
				(-57
					(if numDinars
						(fruitTell doVerb: 59)
					else
						(fruitTell doVerb: 10)
					)
					(return 0)
				)
				(-34
					(if numDinars
						(leatherTell doVerb: 59)
					else
						(leatherTell doVerb: 10)
					)
					(return 0)
				)
				(-26 (= query 26))
			)
		)
	)
	
	(method (cue)
		(= query
			(super
				showDialog:
					-21
					(if
					(and (== aMoneyChanger theAFruitMerchant) (Btst 41))
						(not (Btst 171))
					else
						0
					)
					-22
					(if
					(and (== aMoneyChanger theAFruitMerchant) (Btst 41))
						(not (Btst 171))
					else
						0
					)
					-23
					(if (!= aMoneyChanger theAFruitMerchant)
					else
						(& local8 $1000)
					)
					-26
					(== aMoneyChanger theAFruitMerchant)
					-57
					(== aFruitMerchant theAFruitMerchant)
					-34
					(== aLeatherMerchant theAFruitMerchant)
			)
		)
		(= local0 1)
		(if iconValue (= query iconValue))
		(egoTell respond:)
	)
)

(instance moneyTell of Teller
	(properties)
	
	(method (showDialog)
		(super
			showDialog: 5 (not (Btst 41)) -12 (Btst 41) -73 (Btst 41)
		)
	)
	
	(method (doChild)
		(return
			(switch query
				(-12
					(switch (mod Day 6)
						(0 (messager say: 1 5 13))
						(1 (messager say: 1 5 14))
						(2 (messager say: 1 5 15))
						(3 (messager say: 1 5 16))
						(4 (messager say: 1 5 17))
						(5 (messager say: 1 5 18))
					)
					(return 0)
				)
				(-73
					(switch (mod Day 5)
						(0 (messager say: 1 5 6))
						(1 (messager say: 1 5 7))
						(2 (messager say: 1 5 8))
						(3 (messager say: 1 5 9))
						(4 (messager say: 1 5 10))
					)
					(return 0)
				)
				(else  (super doChild: query))
			)
		)
	)
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(59
					(Bset 171)
					(ExchangeMoney)
					(return 1)
				)
				(10
					(if (< commons 100)
						(messager say: 1 6 72)
					else
						(ExchangeMoney)
					)
					(return 1)
				)
				(else  (super doVerb: theVerb))
			)
		)
	)
)

(instance fruitTell of Teller
	(properties)
	
	(method (doChild)
		(return
			(switch query
				(-12
					(switch (mod Day 6)
						(0 (messager say: 4 5 13))
						(1 (messager say: 4 5 14))
						(2 (messager say: 4 5 15))
						(3 (messager say: 4 5 16))
						(4 (messager say: 4 5 17))
						(5 (messager say: 4 5 18))
					)
					(return 0)
				)
				(-75
					(switch (mod Day 6)
						(0 (messager say: 4 5 51))
						(1 (messager say: 4 5 52))
						(2 (messager say: 4 5 53))
						(3 (messager say: 4 5 54))
						(4 (messager say: 4 5 55))
						(5 (messager say: 4 5 56))
					)
					(return 0)
				)
				(else  (super doChild: query))
			)
		)
	)
	
	(method (doVerb theVerb &tmp [temp0 20])
		(return
			(switch theVerb
				(59 (messager say: 4 6 63))
				(10
					(if (not local6)
						((ScriptID 232 0)
							goods:
								((List new:)
									add: ((Class_47_1 new: 27)
										price: 50
										denomination: 1
										quantity: 40
									)
								)
						)
						(= local6 ((ScriptID 232 0) goods?))
					else
						((ScriptID 232 0) goods: local6)
					)
					((ScriptID 232 0) init: purchase: dispose:)
					(return 1)
				)
				(else  (super doVerb: theVerb))
			)
		)
	)
)

(instance leatherTell of Teller
	(properties)
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(59 (messager say: 3 6 63))
				(10
					(if (not local7)
						((ScriptID 235 0)
							goods:
								((List new:)
									add:
										((Class_47_1 new: 32)
											price: 200
											denomination: 1
											quantity: 70
										)
										((Class_47_1 new: 31) price: 5 quantity: 6)
								)
						)
						(= local7 ((ScriptID 235 0) goods?))
					else
						((ScriptID 235 0) goods: local7)
					)
					((ScriptID 235 0) init: purchase: dispose:)
					(return 1)
				)
				(else  (super doVerb: theVerb))
			)
		)
	)
)

(instance tMoneyChangerTalker of GloryTalker
	(properties
		x 200
		y 2
		view 233
		loop 1
		talkWidth 260
		back 57
		textX -175
		textY 150
		backColor 48
	)
	
	(method (init)
		(super
			init: moneyChangerBust moneyChangerEyes moneyChangerMouth &rest
		)
	)
)

(instance moneyChangerBust of Prop
	(properties
		view 233
		loop 1
		cel 1
		signal $4000
	)
)

(instance moneyChangerEyes of Prop
	(properties
		nsTop 47
		nsLeft 34
		view 233
		loop 2
		priority 15
		signal $4010
	)
)

(instance moneyChangerMouth of Prop
	(properties
		nsTop 58
		nsLeft 35
		view 233
		signal $4000
	)
)

(instance moneyChangerBrow of Prop
	(properties
		nsTop 26
		nsLeft 28
		view 233
		loop 3
		signal $4000
	)
)

(instance leatherA of View
	(properties
		x 132
		y 91
		z -30
		view 230
		signal $4010
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(20 (curRoom doVerb: theVerb))
			(80 (curRoom doVerb: theVerb))
			(81 (curRoom doVerb: theVerb))
			(83 (curRoom doVerb: theVerb))
			(else 
				(curRoom doVerb: theVerb)
			)
		)
	)
)

(instance leatherB of View
	(properties
		x 253
		y 104
		z -30
		view 230
		cel 1
		signal $4010
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(20 (curRoom doVerb: theVerb))
			(80 (curRoom doVerb: theVerb))
			(81 (curRoom doVerb: theVerb))
			(83 (curRoom doVerb: theVerb))
			(else 
				(curRoom doVerb: theVerb)
			)
		)
	)
)

(instance fruitA of View
	(properties
		x 157
		y 158
		z -30
		noun 30
		view 230
		loop 1
		signal $5010
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1 (super doVerb: theVerb))
			(else 
				(curRoom doVerb: theVerb)
			)
		)
	)
)

(instance bananas of Feature
	(properties
		x 178
		y 180
		noun 6
		nsTop 172
		nsLeft 164
		nsBottom 189
		nsRight 192
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1 (super doVerb: theVerb))
			(else 
				(curRoom doVerb: theVerb)
			)
		)
	)
)

(instance fruit of Feature
	(properties
		x 118
		y 204
		z 30
		noun 28
		nsTop 167
		nsLeft 106
		nsBottom 181
		nsRight 130
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1 (super doVerb: theVerb))
			(else 
				(curRoom doVerb: theVerb)
			)
		)
	)
)

(instance hides of Feature
	(properties
		x 64
		y 123
		z 60
		noun 8
		nsTop 41
		nsLeft 57
		nsBottom 85
		nsRight 71
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1 (super doVerb: theVerb))
			(else 
				(curRoom doVerb: theVerb)
			)
		)
	)
)

(instance belts of Feature
	(properties
		x 181
		y 103
		z 60
		noun 9
		nsTop 23
		nsLeft 171
		nsBottom 63
		nsRight 192
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1 (super doVerb: theVerb))
			(else 
				(curRoom doVerb: theVerb)
			)
		)
	)
)

(instance skinrugs of Feature
	(properties
		x 93
		y 100
		noun 10
		nsTop 90
		nsLeft 78
		nsBottom 111
		nsRight 109
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1 (super doVerb: theVerb))
			(else 
				(curRoom doVerb: theVerb)
			)
		)
	)
)

(instance zebrarug of Feature
	(properties
		x 135
		y 102
		noun 11
		nsTop 90
		nsLeft 109
		nsBottom 115
		nsRight 162
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1 (super doVerb: theVerb))
			(else 
				(curRoom doVerb: theVerb)
			)
		)
	)
)

(instance giraffe of Feature
	(properties
		x 138
		y 115
		z 50
		noun 12
		nsTop 50
		nsLeft 112
		nsBottom 81
		nsRight 164
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1 (super doVerb: theVerb))
			(else 
				(curRoom doVerb: theVerb)
			)
		)
	)
)

(instance leopardskin of Feature
	(properties
		x 89
		y 98
		z 60
		noun 13
		nsTop 16
		nsLeft 78
		nsBottom 60
		nsRight 101
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1 (super doVerb: theVerb))
			(else 
				(curRoom doVerb: theVerb)
			)
		)
	)
)

(instance money_sign of Feature
	(properties
		x 17
		y 37
		noun 14
		nsTop 30
		nsBottom 45
		nsRight 34
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1 (super doVerb: theVerb))
			(else 
				(curRoom doVerb: theVerb)
			)
		)
	)
)

(instance hideracks of Feature
	(properties
		x 221
		y 114
		z 30
		noun 15
		nsTop 62
		nsLeft 197
		nsBottom 107
		nsRight 245
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1 (super doVerb: theVerb))
			(else 
				(curRoom doVerb: theVerb)
			)
		)
	)
)

(instance watermelon of Feature
	(properties
		x 237
		y 184
		noun 16
		nsTop 180
		nsLeft 222
		nsBottom 189
		nsRight 252
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1 (super doVerb: theVerb))
			(else 
				(curRoom doVerb: theVerb)
			)
		)
	)
)

(instance basket of Feature
	(properties
		x 242
		y 117
		z 60
		noun 17
		nsTop 47
		nsLeft 228
		nsBottom 68
		nsRight 256
		sightAngle 180
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1 (super doVerb: theVerb))
			(else 
				(curRoom doVerb: theVerb)
			)
		)
	)
)

(instance pitcher of Feature
	(properties
		x 262
		y 116
		z 20
		noun 18
		nsTop 86
		nsLeft 254
		nsBottom 107
		nsRight 270
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1 (super doVerb: theVerb))
			(else 
				(curRoom doVerb: theVerb)
			)
		)
	)
)

(instance roundtray of Feature
	(properties
		x 271
		y 114
		z 60
		noun 19
		nsTop 42
		nsLeft 265
		nsBottom 67
		nsRight 277
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1 (super doVerb: theVerb))
			(else 
				(curRoom doVerb: theVerb)
			)
		)
	)
)

(instance peppers of Feature
	(properties
		x 284
		y 110
		z 50
		noun 20
		nsTop 45
		nsLeft 278
		nsBottom 76
		nsRight 290
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1 (super doVerb: theVerb))
			(else 
				(curRoom doVerb: theVerb)
			)
		)
	)
)

(instance rectangletray of Feature
	(properties
		x 296
		y 113
		z 60
		noun 21
		nsTop 39
		nsLeft 288
		nsBottom 67
		nsRight 304
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1 (super doVerb: theVerb))
			(else 
				(curRoom doVerb: theVerb)
			)
		)
	)
)

(instance redplate of Feature
	(properties
		x 262
		y 114
		noun 22
		nsTop 109
		nsLeft 251
		nsBottom 119
		nsRight 273
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1 (super doVerb: theVerb))
			(else 
				(curRoom doVerb: theVerb)
			)
		)
	)
)

(instance brassvase of Feature
	(properties
		x 284
		y 108
		noun 23
		nsTop 92
		nsLeft 276
		nsBottom 125
		nsRight 292
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1 (super doVerb: theVerb))
			(else 
				(curRoom doVerb: theVerb)
			)
		)
	)
)

(instance shoes of Feature
	(properties
		x 301
		y 126
		noun 24
		nsTop 120
		nsLeft 293
		nsBottom 133
		nsRight 309
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1 (super doVerb: theVerb))
			(else 
				(curRoom doVerb: theVerb)
			)
		)
	)
)

(instance sfx of Sound
	(properties)
)
