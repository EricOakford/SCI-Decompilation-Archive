;;; Sierra Script 1.0 - (do not remove this comment)
(script# 230)
(include game.sh) (include "230.shm")
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
	nearMerchant
	newView
	verbOnHarami
	local4
	local5
	local6
	local7
	local8 =  4103
	local9
	haramiLoop =  2
	local11
	local12
	egoLoop
	newSpell
	newProp
	local16
	local17
	egoCycleSpeed
	egoMoveSpeed
	local20 = [0 1 0 2 0 3 2 1]
	local28 = [2 3 0 3 0 1 2 3]
	local36 = [0 -20 -23 -21 -22 -26 -57 -34 999 0 20 23 24 25 999]
	local51 = [0 -2 1 5 -12 -73 999]
	local58 = [0 3 4 999]
	local62 = [0 50 29 -12 -75 999]
	local68 = [0 28 29 12 30 999]
	[local74 5]
	[local79 5]
	[local92_2 4]
	[local100_2 4]
	local92_2_2 = [0 1 2 3 2 3 0 1]
	local100_2_2 = [0 -2 999]
)
(class Spell of Actor
	(properties
		yStep 10
		view -1
		signal ignrAct
		scaleSignal scalable
		scaleX 96
		scaleY 96
		xStep 15
		origStep 770
	)
	
	(method (init param1 &tmp theLoop)
		(super init: &rest)
		(switch param1
			(20 (= theLoop 2))
			(81 (= theLoop 0))
			(83 (= theLoop 4))
		)
		(self setLoop: theLoop setMotion: MoveTo projX projY self)
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
			(= haramiLoop 1)
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
			(= haramiLoop 1)
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
					setCycle: EndLoop spBurst
				)
			)
			(HandsOn)
		)
	)
)

(instance rm230 of Room
	(properties
		noun N_ROOM
		picture 230
		vanishingY -300
	)
	
	(method (init)
		(ego init: normalize: setScale: 0 noun: 2)
		(if (or Night (Btst fVisitedBazaar))
			(curRoom
				addObstacle:
					((Polygon new:)
						type: PBarredAccess
						init:
							0 156
							0 0
							319 0
							319 115
							302 115
							275 126
							229 111
							178 121
							161 103
							105 97
							82 103
						yourself:
					)
			)
		else
			(cSound fade: 40 10 5 0)
			(curRoom
				addObstacle:
					((Polygon new:)
						type: PBarredAccess
						init:
							65 108
							0 142
							0 0
							319 0
							319 141
							279 135
							240 116
							198 111
							182 120
							170 114
							158 125
							72 118
						yourself:
					)
					((Polygon new:)
						type: PBarredAccess
						init:
							100 189
							95 176
							106 150
							157 154
							151 163
							169 170
							184 167
							208 181
							222 177
							251 176
							260 189
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
			(if (== Day 5)
				(Bset fHaramiRobbedChanger)
			)
			(if
				(and
					(not (Btst fHaramiRobbedChanger))
					(< Day 5)
					(not Night)
					(not (Btst fVisitedBazaar))
				)
				(aHarami init:)
				(LoadMany RES_MESSAGE 230)
				(LoadMany RES_VIEW 233)
				(aMoneyChanger setCycle: OccasionalCycle self 1 20 150)
			else
				(self setRegions: BAZAAR)
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
				(not (Btst fHaramiRobbedChanger))
				(< Day 5)
				(not Night)
				(not (Btst fVisitedBazaar))
			)
			(theGame save: TRUE)
			(HandsOff)
		)
	)
	
	(method (dispose)
		(if local6 (local6 dispose:))
		(if local7 (local7 dispose:))
		(if local5 (local5 dispose:))
		(UnLoad RES_MESSAGE 230)
		(UnLoad RES_VIEW 233)
		(LoadMany FALSE 47 55 40 231 232 233 234 235)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_REST
				(if (Btst fHaramiRobbedChanger)
					((ScriptID TIME 5) init: restTime)
				else
					(messager say: NULL NULL C_CANT_SLEEP)
				)
			)
			(V_LOOK
				(messager say: N_ROOM V_LOOK NULL (if Night 0 else 1))
			)
			(V_DAGGER
				(if (or (Btst fHaramiRobbedChanger) Night)
					(messager say: N_HARAMI_HEADS_OUT V_DOIT C_CANT_THROW)
				else
					(ego drop: iDaggers 1)
					(= verbOnHarami theVerb)
					(= projX ((user curEvent?) x?))
					(= projY ((user curEvent?) y?))
					(ego setScript: castFlameDart 0 theVerb)
				)
			)
			(V_CALM
				(if (or (Btst fHaramiRobbedChanger) Night)
					(messager say: N_HARAMI_HEADS_OUT V_DOIT C_CANT_CAST)
				else
					(= verbOnHarami theVerb)
					(ego setScript: calmCast)
				)
			)
			(V_FLAME
				(if (or (Btst fHaramiRobbedChanger) Night)
					(messager say: N_HARAMI_HEADS_OUT V_DOIT C_CANT_CAST)
				else
					(= projX ((user curEvent?) x?))
					(= projY ((user curEvent?) y?))
					(= verbOnHarami theVerb)
					(ego setScript: castFlameDart 0 theVerb)
				)
			)
			(V_FORCEBOLT
				(if (or (Btst fHaramiRobbedChanger) Night)
					(messager say: N_HARAMI_HEADS_OUT V_DOIT C_CANT_CAST)
				else
					(= projX ((user curEvent?) x?))
					(= projY ((user curEvent?) y?))
					(= verbOnHarami theVerb)
					(ego setScript: castFlameDart 0 theVerb)
				)
			)
			(V_JUGGLE
				(if (or (Btst fHaramiRobbedChanger) Night)
					(messager say: N_HARAMI_HEADS_OUT V_DOIT C_CANT_CAST)
				else
					(ego setScript: (ScriptID CASTJUGGLE 0))
				)
			)
			(V_DETECT
				(if (or (Btst fHaramiRobbedChanger) Night)
					(messager say: N_HARAMI_HEADS_OUT V_DOIT C_CANT_CAST)
				else
					(ego setScript: (ScriptID CASTAREA) 0 V_DETECT)
				)
			)
			(V_FETCH
				(if (or (Btst fHaramiRobbedChanger) Night)
					(messager say: N_HARAMI_HEADS_OUT V_DOIT C_CANT_CAST)
				else
					(messager say: N_HARAMI_HEADS_OUT V_DOIT C_CANT_FETCH)
				)
			)
			(V_OPEN
				(if (or (Btst fHaramiRobbedChanger) Night)
					(messager say: N_HARAMI_HEADS_OUT V_DOIT C_CANT_CAST)
				else
					(ego setScript: (ScriptID CASTOPEN))
				)
			)
			(V_TRIGGER
				(if (or (Btst fHaramiRobbedChanger) Night)
					(messager say: N_HARAMI_HEADS_OUT V_DOIT 65)
				else
					(ego setScript: (ScriptID CASTAREA) 0 V_TRIGGER)
				)
			)
			(V_LIGHTNING
				(if (or (Btst fHaramiRobbedChanger) Night)
					(messager say: N_HARAMI_HEADS_OUT V_DOIT C_CANT_CAST)
				)
			)
			(V_DAZZLE
				(if (or (Btst fHaramiRobbedChanger) Night)
					(messager say: N_HARAMI_HEADS_OUT V_DOIT C_CANT_CAST)
				else
					(ego setScript: (ScriptID CASTAREA) 0 V_DAZZLE)
				)
			)
			(else
				(super doVerb: theVerb)
			)
		)
	)
)

(instance haramiHeadsOut of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(messager say: N_HARAMI_HEADS_OUT V_DOIT C_DISPUTE 0 self)
			)
			(1
				(Bset fMetHarami)
				(Bset fSpeedDisabled)
				(if (!= arcadeDifficulty 3)
					(= egoCycleSpeed (ego cycleSpeed?))
					(= egoMoveSpeed (ego moveSpeed?))
					(ego moveSpeed: 6 cycleSpeed: 6)
				)
				(aFruitMerchant setCycle: 0 stopUpd:)
				(aLeatherMerchant setCycle: 0 stopUpd:)
				(aHarami setCycle: BegLoop self)
			)
			(2
				(aMoneyChanger setLoop: 0 setCycle: Forward)
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
				(messager say: N_MONEY_CHANGER V_DOIT C_THIEF_RUNS 0 self)
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
				(if (== egoGait MOVE_RUN)
					(Bset fRanAfterHarami)
					(= verbOnHarami 0)
					(ego solvePuzzle: fChaseHarami 8 addHonor: 40)
				else
					(Bset fDidntCatchHarami)
					(ego addHonor: -100)
					(Bset fCantBePaladin)
				)
				(Bset fHaramiRobbedChanger)
				(aHarami hide:)
				(aMoneyChanger setCycle: EndLoop)
				(Bclr fSpeedDisabled)
				(= cycles 3)
			)
			(8
				(curRoom setScript: wondering)
			)
		)
	)
)

(instance spEffect of Script
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(aHarami setMotion: 0)
				(if local16
					(sfx number: 930 play:)
				)
				(if (!= register 5)
					(= temp0 9)
				else
					(= temp0 10)
				)
				((= newProp (Prop new:))
					view: 21
					loop: temp0
					x: (aHarami x?)
					y: (- (aHarami y?) 30)
					cycleSpeed: 1
					init:
					setPri: 15
					setCycle: EndLoop self
				)
			)
			(1
				(newProp dispose:)
				(Palette PALIntensity 0 255 1000)
				(Palette PALIntensity 0 255 100)
				(self dispose:)
			)
		)
	)
)

(instance castFlameDart of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(Face ego aHarami self)
				(if (== register V_DAGGER)
					(Bset fDaggeredHarami)
				else
					(Bset fFlamedHarami)
				)
			)
			(1
				(= egoLoop (ego loop?))
				(ego
					view: (if (!= register 20) 14 else 9)
					loop: [local20 egoLoop]
					cel: 0
					setCycle: CycleTo 2 1 self
				)
				(if (!= register V_DAGGER)
					(sfx number: 13 play:)
					(= local16 1)
				else
					(sfx number: 916 play:)
				)
			)
			(2
				(ego setCycle: EndLoop self)
				((= newSpell (Spell new:))
					view: (if (!= register V_DAGGER) 21 else 46)
					x: (+ (ego x?) 20)
					y: (- (ego y?) 35)
					origStep: 6922
					init: register
					setCycle: Forward
				)
			)
			(3
				(ego normalize: egoLoop addHonor: -50)
				(= cycles (+ (ego cycleSpeed?) 5))
			)
			(4
				(self dispose:)
			)
		)
	)
)

(instance knockFruitOver of Script
	
	(method (doit)
		(super doit: &rest)
		(if
			(and
				(not (Btst fKnockedOverFruit))
				(== local9 1)
				(< 135 (aHarami x?))
				(< (aHarami x?) 223)
			)
			(aHarami setCycle: 0 stopUpd:)
			(Bset fKnockedOverFruit)
			(= haramiLoop 2)
			(= verbOnHarami 0)
			(ego solvePuzzle: fHaramiSlipsOnFruit 5 (| puzzleFIGHTER puzzlePALADIN puzzleTHIEF))
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
				(if (!= egoGait MOVE_RUN)
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
				(Bset fTrippedHarami)
				(sfx number: 922 play:)
				(if (== local11 -1)
					(ego setLoop: 2 cel: 0 setCycle: EndLoop)
				else
					(ego setLoop: 3 cel: 6 x: 134 y: 158 setCycle: EndLoop)
				)
				(aFruitBasket setCycle: CycleTo 2 1 self)
				(= local9 1)
			)
			(2
				(aFruitBasket setCycle: CycleTo 4 1 self)
			)
			(3
				((= newView (View new:))
					view: 231
					loop: 1
					cel: 0
					x: 113
					y: 162
					signal: ignrAct
					noun: 33
					init:
					setPri: 1
				)
				(= cycles 3)
			)
			(4
				(if (== local11 -1)
					(ego setCycle: BegLoop self)
				else
					(ego setCycle: CycleTo 6 -1 self)
				)
			)
			(5
				(aFruitBasket noun: 34 setPri: 14)
				(aFruitMerchant setLoop: 2 setCycle: Forward)
				(if (== local11 -1)
					(ego loop: 6 cel: 2)
				else
					(ego loop: 7 cel: 2 x: 135 y: 160)
				)
				(ego view: 0 xStep: 3 yStep: 2 normalize: addHonor: 40)
				(= ticks 4)
			)
			(6
				(if (< (onHisButt state?) 0)
					(Bclr fKnockingOverFruit)
					(HandsOn)
				)
				(aFruitBasket stopUpd:)
				(newView stopUpd:)
				(self dispose:)
			)
		)
	)
)

(instance onHisButt of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(switch verbOnHarami
					(0 0)
					(V_DAGGER
						(ego solvePuzzle: fHaramiHitByDagger 5 puzzleTHIEF)
					)
					(else
						(ego solvePuzzle: fStopHaramiWithMagic 7)
					)
				)
				(Bclr fSpeedDisabled)
				(if (and (!= register 0) (!= register 3))
					(self setScript: spEffect self register)
				else
					(= cycles 1)
				)
			)
			(1
				(aHarami
					view: 953
					setLoop: haramiLoop
					cel: 0
					x: (- (aHarami x?) 25)
					cycleSpeed: (if (== (ego script?) calmCast) 15 else 12)
					moveSpeed: (aHarami cycleSpeed?)
					setCycle: EndLoop self
					setMotion: MoveTo (+ (aHarami x?) 15) (+ (aHarami y?) 2)
				)
			)
			(2
				(aMoneyChanger setCycle: EndLoop self)
			)
			(3
				(aHarami ignoreActors: TRUE stopUpd:)
				(= seconds 2)
			)
			(4
				(if (aFruitMerchant cycler?)
					(aFruitMerchant setCycle: EndLoop self)
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
				(Bset fHaramiRobbedChanger)
				(Bclr fKnockedOverFruit)
				(globalSound fade:)
				(soundFx fade:)
				(curRoom newRoom: 340)
			)
		)
	)
)

(instance seekNkill of Script
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(HandsOff)
				(Face ego aHarami self)
				(if (== register V_DAGGER)
					(Bset fDaggeredHarami)
				else
					(Bset fFlamedHarami)
				)
			)
			(1
				(= egoLoop (ego loop?))
				(ego
					view: (if (!= register 20) 14 else 9)
					loop: [local20 egoLoop]
					cel: 0
					setCycle: CycleTo 2 1 self
				)
			)
			(2
				(switch register
					(V_DAGGER
						(= temp0 2)
					)
					(V_FLAME
						(= temp0 0)
					)
					(V_FORCEBOLT
						(= temp0 4)
					)
				)
				(ego setCycle: EndLoop self)
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
					setCycle: Forward
					setMotion: Chase aHarami 1 self
				)
			)
			(3
				(ego normalize: egoLoop)
				(= haramiLoop 1)
				(curRoom setScript: onHisButt 0 (+ (newSpell loop?) 1))
				(newSpell dispose:)
				(self dispose:)
			)
		)
	)
)

(instance calmCast of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: 0)
				(Face ego aHarami self)
				(Bset fUsedCalmOnHarami)
				(= egoLoop (ego loop?))
			)
			(1
				(ego
					view: 15
					loop: [local28 egoLoop]
					cel: 0
					addHonor: 50
					setCycle: EndLoop self
				)
				(sfx number: 900 play:)
			)
			(2
				(if (and (ego castSpell: CALM) (< (aHarami x?) 300))
					(= haramiLoop 1)
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
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego x: -10 y: 200 setMotion: PolyPath 50 175 self)
				(if
					(and
						(not (Btst fHaramiRobbedChanger))
						(< Day 5)
						(not Night)
						(not (Btst fVisitedBazaar))
					)
					(aHarami setCycle: EndLoop)
				)
			)
			(1
				(if
					(and
						(not (Btst fHaramiRobbedChanger))
						(< Day 5)
						(not Night)
						(not (Btst fVisitedBazaar))
					)
					(Bset fWitnessedRobbery)
					(Face ego aHarami self)
				else
					(self cue:)
				)
			)
			(2
				(if
					(and
						(not (Btst fHaramiRobbedChanger))
						(< Day 5)
						(not Night)
						(not (Btst fVisitedBazaar))
					)
					(ego code: moneyCode)
					(curRoom setScript: haramiHeadsOut)
				else
					(HandsOn)
					(if (or Night (Btst fVisitedBazaar))
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
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(cond 
					((<= (ego x?) 5)
						(ego setMotion: MoveTo -30 200 self)
					)
					((>= (ego x?) 315)
						(ego setMotion: MoveTo 335 177 self)
					)
					(else
						(ego setMotion: PolyPath (ego x?) 250 self)
					)
				)
			)
			(1
				(if
					(and
						(not
							(if (or (Btst fRanAfterHarami) (Btst fTrippedHarami) (Btst fDaggeredHarami) (Btst fFlamedHarami))
							else
								(Btst fUsedCalmOnHarami)
							)
						)
						(== egoGait MOVE_RUN)
					)
					(Bset fRanAfterHarami)
				)
				(curRoom newRoom: 240)
			)
		)
	)
)

(instance wondering of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOn)
				(if (or (== verbOnHarami 0) (== verbOnHarami V_DAGGER))
					0
				else
					(ego solvePuzzle: fLetHaramiEscape 4)
				)
				(if (Btst fTrippedHarami)
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
				(messager say: N_GUARD V_DOIT C_GUARD_COMES_IN 0 self)
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
	
	(method (changeState newState &tmp egoLoop_2 temp1)
		(switch (= state newState)
			(0
				(= egoLoop_2 (ego loop?))
				(ego
					view: 6
					cel: 0
					setMotion: 0
					setLoop: [local92_2_2 egoLoop_2]
					setCycle: EndLoop self
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
					setCycle: EndLoop self
				)
			)
			(2
				(messager say: N_HARAMI_HEADS_OUT V_DOIT C_EGO_FELL 0 self)
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
	
	(method (doit)
		(cond 
			(
				(and
					(< (ego distanceTo: aMoneyChanger) 40)
					(& local8 $0001)
				)
				(ego setMotion: 0)
				(if (not (Btst fMetMoneyChanger))
					(Bset fMetMoneyChanger)
					(&= local8 $fffe)
					(messager say: N_MONEY_CHANGER V_DOIT C_FIRST_MEET_MONEY_CHANGER)
				else
					(&= local8 $fffe)
					(messager say: N_MONEY_CHANGER V_DOIT C_MEET_AGAIN_MONEY_CHANGER)
				)
			)
			(
				(and
					(< (ego distanceTo: aFruitMerchant) 50)
					(& local8 $0002)
				)
				(&= local8 $fffd)
				(aFruitMerchant newGreeting:)
			)
			(
				(and
					(< (ego distanceTo: aLeatherMerchant) 20)
					(& local8 $0004)
				)
				(ego setMotion: 0)
				(&= local8 $fffb)
				(messager say: N_LEATHER_VENDOR V_DOIT C_LEATHER_GREET)
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
	
	(method (doit)
		(cond 
			(
				(and
					(not (if (< 5 (ego x?)) (< (ego x?) 315)))
					(not (curRoom script?))
				)
				(curRoom setScript: sExit)
			)
			((and (> (ego y?) 183) (not (curRoom script?)))
				(curRoom setScript: sExit)
			)
		)
	)
)

(instance fruitCode of Code
	
	(method (doit)
		(cond 
			(
				(and
					(== egoGait MOVE_RUN)
					(ego mover?)
					(& (ego onControl:) cCYAN)
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
				(if (not (Btst fMetMoneyChanger))
					(Bset fMetMoneyChanger)
					(&= local8 $fffe)
					(messager say: N_MONEY_CHANGER V_DOIT C_FIRST_MEET_MONEY_CHANGER)
				else
					(&= local8 $fffe)
					(messager say: N_MONEY_CHANGER V_DOIT C_MEET_AGAIN_MONEY_CHANGER)
				)
			)
			(
				(and
					(< (ego distanceTo: aFruitMerchant) 50)
					(& local8 $0002)
				)
				(ego setMotion: 0)
				(&= local8 $fffd)
				(aFruitMerchant setCycle: BegLoop)
				(messager say: N_FRUIT_VENDOR V_DOIT C_FRUIT_MERCHANT_MAD 0 wondering)
			)
			(
				(and
					(< (ego distanceTo: aLeatherMerchant) 20)
					(& local8 $0004)
				)
				(ego setMotion: 0)
				(&= local8 $fffb)
				(messager say: N_LEATHER_VENDOR V_DOIT C_LEATHER_GREET)
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
	
	(method (doit)
		(if
			(and
				(< (ego distanceTo: aMoneyChanger) 40)
				(not local4)
			)
			(= local4 1)
			(ego setMotion: 0)
			(messager say: N_MONEY_CHANGER V_DOIT C_THIEF_RUNS)
		)
	)
)

(instance aHarami of Actor
	(properties
		x 42
		y 106
		noun N_HARAMI
		view 954
		loop 2
		priority 9
		signal (| ignrHrz fixPriOn)
	)
	
	(method (doVerb theVerb)
		(cond 
			((and (== arcadeDifficulty 1) (not (ego script?)))
				(cond 
					((or (== theVerb V_DAGGER) (== theVerb V_FLAME) (== theVerb V_FORCEBOLT))
						(if (< x 260)
							(if (== theVerb V_DAGGER)
								(ego drop: iDaggers 1)
							)
							(ego setScript: seekNkill 0 theVerb)
						else
							(= projX ((user curEvent?) x?))
							(= projY ((user curEvent?) y?))
							(= verbOnHarami theVerb)
							(if (== theVerb V_DAGGER)
								(ego drop: iDaggers 1)
							)
							(ego setScript: castFlameDart 0 theVerb)
						)
					)
					((== theVerb V_FETCH)
						(curRoom doVerb: theVerb)
					)
					(else
						(super doVerb: theVerb)
					)
				)
			)
			(
			(or (== theVerb V_DAGGER) (== theVerb V_FLAME) (== theVerb V_FORCEBOLT))
				(= verbOnHarami theVerb)
				(= projX ((user curEvent?) x?))
				(= projY ((user curEvent?) y?))
				(if (== theVerb V_DAGGER)
					(ego drop: iDaggers 1)
				)
				(ego setScript: castFlameDart 0 theVerb)
			)
			((== theVerb V_FETCH)
				(curRoom doVerb: theVerb)
			)
			(else
				(super doVerb: theVerb)
			)
		)
	)
)

(instance aGuard1 of Actor
	(properties
		x 360
		y 163
		noun N_GUARD
		yStep 3
		view 190
		loop 1
	)
)

(instance aFruitMerchant of MerchantActor
	(properties
		x 143
		y 183
		noun N_FRUIT_VENDOR
		approachX 149
		approachY 157
		view 234
		loop 3
		priority 14
		signal (| ignrAct fixPriOn)
		cycleSpeed 11
	)
	
	(method (init)
		(super init: &rest)
		(self approachVerbs: V_TALK V_DO V_DINARS V_ROYALS)
	)
	
	(method (newGreeting)
		(switch (mod Day 6)
			(0 (messager say: noun V_DOIT C_FRUIT_GREET1))
			(1 (messager say: noun V_DOIT C_FRUIT_GREET2))
			(2 (messager say: noun V_DOIT C_FRUIT_GREET3))
			(3 (messager say: noun V_DOIT C_FRUIT_GREET4))
			(4 (messager say: noun V_DOIT C_FRUIT_GREET5))
			(5 (messager say: noun V_DOIT C_FRUIT_GREET6))
		)
	)
)

(instance aLeatherMerchant of MerchantActor
	(properties
		x 146
		y 121
		z 20
		noun N_LEATHER_VENDOR
		approachDist 20
		view 236
		loop 2
		signal $5000
		cycleSpeed 13
	)
	
	(method (init)
		(super init: &rest)
		(self approachVerbs: V_TALK V_DO V_DINARS V_ROYALS)
	)
)

(instance aFruitBasket of Prop
	(properties
		x 122
		y 163
		noun N_FRUIT_BASKET
		approachX 106
		approachY 151
		view 231
		signal (| ignrAct ignrHrz)
		cycleSpeed 3
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO
				(if (and (not (Btst fHaramiRobbedChanger)) (< Day 5))
					(Bset fKnockingOverFruit)
					(ego setScript: knockFruitOver)
				else
					(messager say: N_HARAMI_HEADS_OUT V_DOIT C_SEARCH_FRUIT)
				)
			)
			(V_LOOK
				(super doVerb: theVerb)
			)
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
		noun N_MONEY_CHANGER
		approachDist 40
		view 232
		priority 1
		signal (| ignrAct fixPriOn)
		cycleSpeed 9
	)
	
	(method (init)
		(super init: &rest)
		(self approachVerbs: V_TALK V_DO V_DINARS V_ROYALS)
	)
)

(instance spBurst of Prop
	(properties
		view 21
		priority 15
		signal (| ignrAct fixPriOn)
		cycleSpeed 0
	)
	
	(method (cue)
		(self dispose:)
	)
)

(instance egoTell of Teller
	
	(method (respond)
		(return
			(if (not local0)
				(super respond:)
			else
				(= local0 0)
				(cond 
					((not query)
						(return TRUE)
					)
					((== query -999)
						(return TRUE)
					)
					((== query 999)
						(self doParent:)
						(return FALSE)
					)
					((and (< query 0) (not (self doChild: query)))
						(return TRUE)
					)
				)
				(if (< query 0)
					(= query (- query))
				)
				(messager say: (client noun?) V_TELL query 0)
				(return TRUE)
			)
		)
	)
	
	(method (showDialog &tmp temp0)
		(if (== (= nearMerchant (proc51_1)) aFruitBasket)
			(= nearMerchant aFruitMerchant)
		)
		(= temp0 (ego distanceTo: nearMerchant))
		(switch nearMerchant
			(aMoneyChanger
				(if (> temp0 40)
					(messager say: N_EGO_TELL V_TELL C_TALK_TO_WHOM)
					(return -999)
				)
			)
			(aFruitMerchant
				(if (> temp0 60)
					(messager say: N_EGO_TELL V_TELL C_TALK_TO_WHOM)
					(return -999)
				)
			)
			(else 
				(if (> temp0 30)
					(messager say: N_EGO_TELL V_TELL C_TALK_TO_WHOM)
					(return -999)
				)
			)
		)
		(if
			(!=
				(ego heading?)
				(GetAngle
					(ego x?)
					(ego y?)
					(nearMerchant x?)
					(nearMerchant y?)
				)
			)
			(Face ego nearMerchant)
		)
		((Timer new:) setCycle: self (+ (ego cycleSpeed?) 10))
		(= iconValue 0)
		(return -999)
	)
	
	(method (doChild)
		(return
			(switch query
				(-21
					(Bset fDidMoneyExchange)
					(ExchangeMoney)
					(return FALSE)
				)
				(-22
					(Bset fDidMoneyExchange)
					(= query 22)
				)
				(-20
					(cond 
						((== nearMerchant aMoneyChanger)
							(= query 61)
						)
						((== nearMerchant aFruitMerchant)
							(= query 58)
						)
						((== nearMerchant aLeatherMerchant)
							(= query 31)
						)
					)
				)
				(-23
					(cond 
						((== nearMerchant aMoneyChanger)
							(&= local8 $efff)
							(ego addHonor: 2)
							(= query 23)
						)
						((== nearMerchant aFruitMerchant)
							(= query 59)
						)
						((== nearMerchant aLeatherMerchant)
							(= query 32)
						)
					)
				)
				(-57
					(if numDinars
						(fruitTell doVerb: V_DINARS)
					else
						(fruitTell doVerb: V_ROYALS)
					)
					(return FALSE)
				)
				(-34
					(if numDinars
						(leatherTell doVerb: V_DINARS)
					else
						(leatherTell doVerb: V_ROYALS)
					)
					(return FALSE)
				)
				(-26
					(= query 26)
				)
			)
		)
	)
	
	(method (cue)
		(= query
			(super
				showDialog:
					-21
					(if (and (== aMoneyChanger nearMerchant) (Btst fMetMoneyChanger))
						(not (Btst fDidMoneyExchange))
					else
						0
					)
					-22
					(if (and (== aMoneyChanger nearMerchant) (Btst fMetMoneyChanger))
						(not (Btst fDidMoneyExchange))
					else
						0
					)
					-23
					(if (!= aMoneyChanger nearMerchant)
					else
						(& local8 $1000)
					)
					-26
					(== aMoneyChanger nearMerchant)
					-57
					(== aFruitMerchant nearMerchant)
					-34
					(== aLeatherMerchant nearMerchant)
			)
		)
		(= local0 1)
		(if iconValue (= query iconValue))
		(egoTell respond:)
	)
)

(instance moneyTell of Teller
	
	(method (showDialog)
		(super
			showDialog: 5 (not (Btst fMetMoneyChanger)) -12 (Btst fMetMoneyChanger) -73 (Btst fMetMoneyChanger)
		)
	)
	
	(method (doChild)
		(return
			(switch query
				(-12
					(switch (mod Day 6)
						(0 (messager say: N_MONEY_CHANGER V_TELL C_RUMOR1))
						(1 (messager say: N_MONEY_CHANGER V_TELL C_RUMOR2))
						(2 (messager say: N_MONEY_CHANGER V_TELL C_RUMOR3))
						(3 (messager say: N_MONEY_CHANGER V_TELL C_RUMOR4))
						(4 (messager say: N_MONEY_CHANGER V_TELL C_RUMOR5))
						(5 (messager say: N_MONEY_CHANGER V_TELL C_RUMOR6))
					)
					(return FALSE)
				)
				(-73
					(switch (mod Day 5)
						(0 (messager say: N_MONEY_CHANGER V_TELL C_MONEY_SELF1))
						(1 (messager say: N_MONEY_CHANGER V_TELL C_MONEY_SELF2))
						(2 (messager say: N_MONEY_CHANGER V_TELL C_MONEY_SELF3))
						(3 (messager say: N_MONEY_CHANGER V_TELL C_MONEY_SELF4))
						(4 (messager say: N_MONEY_CHANGER V_TELL C_MONEY_SELF5))
					)
					(return FALSE)
				)
				(else
					(super doChild: query)
				)
			)
		)
	)
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(V_DINARS
					(Bset fDidMoneyExchange)
					(ExchangeMoney)
					(return TRUE)
				)
				(V_ROYALS
					(if (< commons 100)
						(messager say: N_MONEY_CHANGER V_DOIT C_NOT_ENOUGH_COMMONS)
					else
						(ExchangeMoney)
					)
					(return TRUE)
				)
				(else
					(super doVerb: theVerb)
				)
			)
		)
	)
)

(instance fruitTell of Teller
	
	(method (doChild)
		(return
			(switch query
				(-12
					(switch (mod Day 6)
						(0 (messager say: N_FRUIT_VENDOR V_TELL C_RUMOR1))
						(1 (messager say: N_FRUIT_VENDOR V_TELL C_RUMOR2))
						(2 (messager say: N_FRUIT_VENDOR V_TELL C_RUMOR3))
						(3 (messager say: N_FRUIT_VENDOR V_TELL C_RUMOR4))
						(4 (messager say: N_FRUIT_VENDOR V_TELL C_RUMOR5))
						(5 (messager say: N_FRUIT_VENDOR V_TELL C_RUMOR6))
					)
					(return 0)
				)
				(-75
					(switch (mod Day 6)
						(0 (messager say: N_FRUIT_VENDOR V_TELL C_FRUIT_SELF1))
						(1 (messager say: N_FRUIT_VENDOR V_TELL C_FRUIT_SELF2))
						(2 (messager say: N_FRUIT_VENDOR V_TELL C_FRUIT_SELF3))
						(3 (messager say: N_FRUIT_VENDOR V_TELL C_FRUIT_SELF4))
						(4 (messager say: N_FRUIT_VENDOR V_TELL C_FRUIT_SELF5))
						(5 (messager say: N_FRUIT_VENDOR V_TELL C_FRUIT_SELF6))
					)
					(return FALSE)
				)
				(else
					(super doChild: query)
				)
			)
		)
	)
	
	(method (doVerb theVerb &tmp [temp0 20])
		(return
			(switch theVerb
				(V_DINARS
					(messager say: N_FRUIT_VENDOR V_DOIT C_WRONG_MONEY)
				)
				(V_ROYALS
					(if (not local6)
						((ScriptID 232 0)
							goods:
								((List new:)
									add: ((Ware new: N_FRUIT_WARE)
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
					(return TRUE)
				)
				(else
					(super doVerb: theVerb)
				)
			)
		)
	)
)

(instance leatherTell of Teller
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(V_DINARS
					(messager say: N_LEATHER_VENDOR V_DOIT C_WRONG_MONEY)
				)
				(V_ROYALS
					(if (not local7)
						((ScriptID 235 0)
							goods:
								((List new:)
									add:
										((Ware new: N_WATERSKIN_WARE)
											price: 200
											denomination: 1
											quantity: 70
										)
										((Ware new: N_ZEBRA_SKIN_WARE)
											price: 5
											quantity: 6
										)
								)
						)
						(= local7 ((ScriptID 235 0) goods?))
					else
						((ScriptID 235 0) goods: local7)
					)
					((ScriptID 235 0) init: purchase: dispose:)
					(return TRUE)
				)
				(else
					(super doVerb: theVerb)
				)
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
		signal ignrAct
	)
)

(instance moneyChangerEyes of Prop
	(properties
		nsTop 47
		nsLeft 34
		view 233
		loop 2
		priority 15
		signal (| ignrAct fixPriOn)
	)
)

(instance moneyChangerMouth of Prop
	(properties
		nsTop 58
		nsLeft 35
		view 233
		signal ignrAct
	)
)

(instance moneyChangerBrow of Prop
	(properties
		nsTop 26
		nsLeft 28
		view 233
		loop 3
		signal ignrAct
	)
)

(instance leatherA of View
	(properties
		x 132
		y 91
		z -30
		view 230
		signal (| ignrAct fixPriOn)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DAGGER
				(curRoom doVerb: theVerb)
			)
			(V_CALM
				(curRoom doVerb: theVerb)
			)
			(V_FLAME
				(curRoom doVerb: theVerb)
			)
			(V_FORCEBOLT
				(curRoom doVerb: theVerb)
			)
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
		signal (| ignrAct fixPriOn)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DAGGER
				(curRoom doVerb: theVerb)
			)
			(V_CALM
				(curRoom doVerb: theVerb)
			)
			(V_FLAME
				(curRoom doVerb: theVerb)
			)
			(V_FORCEBOLT
				(curRoom doVerb: theVerb)
			)
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
		noun N_FRUIT_A
		view 230
		loop 1
		signal (| ignrAct skipCheck fixPriOn)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(super doVerb: theVerb)
			)
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
		noun N_BANANAS
		nsTop 172
		nsLeft 164
		nsBottom 189
		nsRight 192
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(super doVerb: theVerb)
			)
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
		noun N_FRUIT
		nsTop 167
		nsLeft 106
		nsBottom 181
		nsRight 130
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(super doVerb: theVerb)
			)
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
		noun N_HIDES
		nsTop 41
		nsLeft 57
		nsBottom 85
		nsRight 71
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(super doVerb: theVerb)
			)
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
		noun N_BELTS
		nsTop 23
		nsLeft 171
		nsBottom 63
		nsRight 192
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(super doVerb: theVerb)
			)
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
		noun N_SKIN_RUGS
		nsTop 90
		nsLeft 78
		nsBottom 111
		nsRight 109
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(super doVerb: theVerb)
			)
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
		noun N_ZEBRA_RUG
		nsTop 90
		nsLeft 109
		nsBottom 115
		nsRight 162
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(super doVerb: theVerb)
			)
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
		noun N_GIRAFFE
		nsTop 50
		nsLeft 112
		nsBottom 81
		nsRight 164
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(super doVerb: theVerb)
			)
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
		noun N_LEOPARD_SKIN
		nsTop 16
		nsLeft 78
		nsBottom 60
		nsRight 101
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(super doVerb: theVerb)
			)
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
		noun N_MONEY_SIGN
		nsTop 30
		nsBottom 45
		nsRight 34
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(super doVerb: theVerb)
			)
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
		noun N_HIDE_RACKS
		nsTop 62
		nsLeft 197
		nsBottom 107
		nsRight 245
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(super doVerb: theVerb)
			)
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
		noun N_WATERMELON
		nsTop 180
		nsLeft 222
		nsBottom 189
		nsRight 252
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(super doVerb: theVerb)
			)
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
		noun N_BASKET
		nsTop 47
		nsLeft 228
		nsBottom 68
		nsRight 256
		sightAngle 180
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(super doVerb: theVerb)
			)
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
		noun N_PITCHER
		nsTop 86
		nsLeft 254
		nsBottom 107
		nsRight 270
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(super doVerb: theVerb)
			)
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
		noun N_ROUND_TRAY
		nsTop 42
		nsLeft 265
		nsBottom 67
		nsRight 277
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(super doVerb: theVerb)
			)
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
		noun N_PEPPERS
		nsTop 45
		nsLeft 278
		nsBottom 76
		nsRight 290
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(super doVerb: theVerb)
			)
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
		noun N_RECTANGLE_TRAY
		nsTop 39
		nsLeft 288
		nsBottom 67
		nsRight 304
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(super doVerb: theVerb)
			)
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
		noun N_RED_PLATE
		nsTop 109
		nsLeft 251
		nsBottom 119
		nsRight 273
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(super doVerb: theVerb)
			)
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
		noun N_BRASS_VASE
		nsTop 92
		nsLeft 276
		nsBottom 125
		nsRight 292
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(super doVerb: theVerb)
			)
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
		noun N_SHOES
		nsTop 120
		nsLeft 293
		nsBottom 133
		nsRight 309
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(super doVerb: theVerb)
			)
			(else 
				(curRoom doVerb: theVerb)
			)
		)
	)
)

(instance sfx of Sound)
