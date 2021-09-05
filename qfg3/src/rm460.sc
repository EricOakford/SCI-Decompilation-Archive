;;; Sierra Script 1.0 - (do not remove this comment)
(script# 460)
(include game.sh)
(use Main)
(use TellerIcon)
(use PolyPath)
(use Polygon)
(use Feature)
(use Track)
(use LoadMany)
(use StopWalk)
(use Jump)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm460 0
	target 1
)

(local
	local0
	local1
	local2
	local3
	local4
	local5
	targetX
	targetY
	local8
	[newSpear 6]
	local15
	[newDagger 10]
	[newRackSpear 6]
	[local32 12] = [92 128 88 131 84 134 250 131 258 135 260 138]
	local44
	[local45 5] = [0 -26 -27 -21 999]
	[local50 4]
	local96_3_2
	[local97_3_2 4] = [0 20 -37 999]
	[local96_2_2_2 7] = [0 -18 -23 24 25 -28 999]
	[local97_2_2_2 3] = [0 -23 999]
	[local96_2 3] = [0 22 999]
	[local97_2 4]
	local96_3
	local97_3
	local94_2_2
	local95_2_2
	local96_2_2
	theArcadeDifficulty
	local94_2_2_2
	local95_2_2_2
	local96_4
	local97_4
	local94_2
	local95_2
	local96_2_3
	local97_2_3
	local94_3
	local95_3
	local96_3_2_2
	local97_3_2_2
	local94_2_2_2_2
	local95_2_2_2_2
	local96_2_2_2_2
	local97_2_2_2_2
)
(procedure (localproc_1c78 &tmp temp0 temp1 temp2 temp3 temp4)
	(= temp2 0)
	(= temp0
		(switch theArcadeDifficulty
			(1 150)
			(2 175)
			(3 200)
		)
	)
	(ego trySkill: 10 (- temp0 1))
	(if
	(or (< [egoStats 10] temp0) (< [egoStats 0] temp0))
		(if
			(<
				(= temp1
					(-
						100
						(/
							(+ (- temp0 [egoStats 10]) (- temp0 [egoStats 0]))
							2
						)
					)
				)
				10
			)
			(= temp1 10)
		)
		(if (> temp1 90) (= temp1 90))
		(cond 
			((>= temp1 (Random 1 100))
				(if (< targetX 171)
					(= targetX
						(Random (+ (target nsLeft?) 4) (+ (target nsLeft?) 6))
					)
				else
					(= targetX
						(Random
							(- (target nsRight?) 7)
							(- (target nsRight?) 5)
						)
					)
				)
				(= targetY
					(Random
						(+ (target nsTop?) 3)
						(- (target nsBottom?) 8)
					)
				)
			)
			((or (< targetX 171) (== local96_3_2_2 -1))
				(= targetX
					(Random (targArea nsLeft?) (- (target nsLeft?) 1))
				)
			)
			(else
				(= targetX
					(Random (+ (target nsRight?) 1) (targArea nsRight?))
				)
			)
		)
	else
		(if
			(and
				local95_3
				(= temp2
					(- (= temp2 local95_3) (/ (- [egoStats 0] temp0) 10))
				)
			)
			(= targetX (+ targetX (* local96_3_2_2 temp2)))
		)
		(if
			(<=
				(= temp4 (/ (- (+ temp0 100) [egoStats 10]) 10))
				2
			)
			(= temp4 2)
		)
		(= targetX
			(+ targetX (- temp4 (Random 0 (+ temp4 temp4))))
		)
		(= targetY
			(+ targetY (- temp4 (Random 0 (+ temp4 temp4))))
		)
	)
	(localproc_1e43)
)

(procedure (localproc_1e2e &tmp temp0)
	(repeat
		(if (= temp0 (localproc_20a3)) (break))
	)
)

(procedure (localproc_1e43)
	(cond 
		((< targetX 110) (= targetX 110))
		((> targetX 226) (= targetY 226))
	)
	(cond 
		((< targetY 44) (= local5 0) (= targetY 65))
		((and (< 64 targetY) (< targetY 80)) (= local5 0))
		((>= targetY 80) (= local5 0) (= targetY 80))
		(else (= local5 1))
	)
)

(procedure (localproc_1ea1)
	(if local0
		(HandsOff)
		(localproc_1f3a)
		(if (!= local1 3) (messager say: 26 61 61))
		(HandsOn)
	)
	(= local44 0)
	(if (competeVSUhura script?)
		((competeVSUhura script?) dispose:)
	)
	(if ((ScriptID 34 1) script?)
		(((ScriptID 34 1) script?) dispose:)
		((ScriptID 34 1)
			view: 971
			setCycle: StopWalk 969
			setHeading: 270
		)
	)
	((theIconBar at: 3) cursor: 942)
)

(procedure (localproc_1f3a &tmp temp0)
	(= temp0 0)
	(while (< temp0 local0)
		(if (IsObject [newSpear temp0])
			([newSpear temp0] dispose:)
		)
		((= [newRackSpear temp0] (rackSpear new:))
			loop: (if (< temp0 3) 4 else 3)
			cel: (Random 0 3)
			x: [local32 (* temp0 2)]
			y: [local32 (+ (* temp0 2) 1)]
			init:
			stopUpd:
		)
		(++ temp0)
	)
	(= local0 0)
)

(procedure (localproc_1faf &tmp temp0 temp1)
	(switch theArcadeDifficulty
		(1 (= temp0 150) (= temp1 150))
		(2 (= temp0 175) (= temp1 175))
		(3 (= temp0 200) (= temp1 200))
	)
	(return
		(if
		(and (>= [egoStats 10] temp0) (>= [egoStats 0] temp1))
			(return 1)
		else
			(return 0)
		)
	)
)

(procedure (localproc_200b &tmp temp0)
	(= temp0 3)
	(while (<= temp0 local96_3)
		(if (IsObject [newSpear temp0])
			([newSpear temp0] dispose:)
		)
		((= [newRackSpear temp0] (rackSpear new:))
			loop: 3
			cel: (Random 0 3)
			x: [local32 (* temp0 2)]
			y: [local32 (+ (* temp0 2) 1)]
			init:
		)
		(++ temp0)
	)
)

(procedure (localproc_206a)
	(theIconBar enable: 3 curIcon: (theIconBar at: 3))
	(User canInput: 1)
	(theGame setCursor: ((theIconBar curIcon?) cursor?) 1)
)

(procedure (localproc_20a3)
	(return (- (Random 0 2) 1))
)

(procedure (localproc_2111)
	(cond 
		(
		(and (not (& local96_2_3 $0001)) (< [egoStats 0] 150))
			(messager say: 2 6 6)
			(= local96_2_3 (| local96_2_3 $0001))
		)
		(
		(and (not (& local96_2_3 $0002)) (< [egoStats 10] 150))
			(messager say: 2 6 1)
			(= local96_2_3 (| local96_2_3 $0002))
		)
		(
		(and (not (& local96_2_3 $0004)) (< [egoStats 0] 150))
			(messager say: 2 6 7)
			(= local96_2_3 (| local96_2_3 $0004))
		)
		(
		(and (not (& local96_2_3 $0008)) (< [egoStats 10] 150))
			(messager say: 2 6 2)
			(= local96_2_3 (| local96_2_3 $0008))
		)
		(
			(and
				(not (& local96_2_3 $0010))
				(< [egoStats 0] 175)
				(== theArcadeDifficulty 3)
			)
			(messager say: 2 6 8)
			(= local96_2_3 (| local96_2_3 $0010))
		)
		(
			(and
				(not (& local96_2_3 $0020))
				(< [egoStats 10] 150)
				(!= theArcadeDifficulty 1)
			)
			(messager say: 2 6 3)
			(= local96_2_3 (| local96_2_3 $0020))
		)
		(
			(and
				(not (& local96_2_3 $0040))
				(< [egoStats 10] 170)
				(!= theArcadeDifficulty 1)
			)
			(messager say: 2 6 4)
			(= local96_2_3 (| local96_2_3 $0040))
		)
		(
			(and
				(not (& local96_2_3 $0080))
				(< [egoStats 10] 200)
				(== theArcadeDifficulty 3)
			)
			(messager say: 2 6 5)
			(= local96_2_3 (| local96_2_3 $0080))
		)
		(
			(and
				(not (& local96_2_3 $0100))
				(< [egoStats 10] 200)
				(== theArcadeDifficulty 3)
			)
			(messager say: 2 6 31)
			(= local96_2_3 (| local96_2_3 $0100))
		)
		(
			(and
				(not (& local96_2_3 $0200))
				(== theArcadeDifficulty 3)
			)
			(messager say: 2 6 34)
			(= local96_2_3 (| local96_2_3 $0200))
		)
		(
			(and
				(not (& local96_2_3 $0400))
				(!= theArcadeDifficulty 1)
			)
			(messager say: 2 6 33)
			(= local96_2_3 (| local96_2_3 $0400))
		)
		((not local94_2_2_2_2) (= local94_2_2_2_2 1) (messager say: 2 6 30))
		((not (Random 0 5)) (messager say: 2 6 30))
	)
)

(procedure (localproc_2351)
	(cond 
		(
		(and (not (& local96_2_3 $0001)) (< [egoStats 0] 125))
			(messager say: 1 6 6)
			(= local96_2_3 (| local96_2_3 $0001))
		)
		(
			(and
				(not (& local96_2_3 $0002))
				(< [egoStats 0] 150)
				(!= theArcadeDifficulty 1)
			)
			(messager say: 1 6 7)
			(= local96_2_3 (| local96_2_3 $0002))
		)
		(
			(and
				(not (& local96_2_3 $0004))
				(< [egoStats 0] 175)
				(== theArcadeDifficulty 3)
			)
			(messager say: 1 6 8)
			(= local96_2_3 (| local96_2_3 $0004))
		)
		(
		(and (not (& local96_2_3 $0008)) (< [egoStats 10] 100))
			(messager say: 1 6 1)
			(= local96_2_3 (| local96_2_3 $0008))
		)
		(
		(and (not (& local96_2_3 $0010)) (< [egoStats 10] 150))
			(messager say: 1 6 2)
			(= local96_2_3 (| local96_2_3 $0010))
		)
		(
			(and
				(not (& local96_2_3 $0020))
				(< [egoStats 10] 145)
				(!= theArcadeDifficulty 1)
			)
			(messager say: 1 6 3)
			(= local96_2_3 (| local96_2_3 $0020))
		)
		(
			(and
				(not (& local96_2_3 $0040))
				(< [egoStats 10] 170)
				(!= theArcadeDifficulty 1)
			)
			(messager say: 1 6 4)
			(= local96_2_3 (| local96_2_3 $0040))
		)
		(
			(and
				(not (& local96_2_3 $0080))
				(< [egoStats 10] 195)
				(== theArcadeDifficulty 3)
			)
			(messager say: 1 6 5)
			(= local96_2_3 (| local96_2_3 $0080))
		)
	)
)

(procedure (localproc_2535 param1)
	(cond 
		(
		(and (not (& local94_3 $0001)) (< [egoStats 0] 100))
			(messager say: 1 6 14 0 param1)
			(= local94_3 (| local94_3 $0001))
		)
		(
			(and
				(not (& local94_3 $0002))
				(< [egoStats 0] 125)
				(!= theArcadeDifficulty 1)
			)
			(messager say: 1 6 15 0 param1)
			(= local94_3 (| local94_3 $0002))
		)
		(
			(and
				(not (& local94_3 $0004))
				(< [egoStats 0] 150)
				(== theArcadeDifficulty 3)
			)
			(messager say: 1 6 16 0 param1)
			(= local94_3 (| local94_3 $0004))
		)
		(
		(and (not (& local94_3 $0008)) (< [egoStats 10] 100))
			(messager say: 1 6 9 0 param1)
			(= local94_3 (| local94_3 $0008))
		)
		(
		(and (not (& local94_3 $0010)) (< [egoStats 10] 125))
			(messager say: 1 6 10 0 param1)
			(= local94_3 (| local94_3 $0010))
		)
		(
			(and
				(not (& local94_3 $0020))
				(< [egoStats 10] 150)
				(!= theArcadeDifficulty 1)
			)
			(messager say: 1 6 11 0 param1)
			(= local94_3 (| local94_3 $0020))
		)
		(
			(and
				(not (& local94_3 $0040))
				(< [egoStats 10] 175)
				(!= theArcadeDifficulty 1)
			)
			(messager say: 1 6 12 0 param1)
			(= local94_3 (| local94_3 $0040))
		)
		(
			(and
				(not (& local94_3 $0080))
				(< [egoStats 10] 185)
				(== theArcadeDifficulty 3)
			)
			(messager say: 1 6 13 0 param1)
			(= local94_3 (| local94_3 $0080))
		)
		(else (param1 cue:))
	)
)

(instance rm460 of Room
	(properties
		noun 5
		picture 460
	)
	
	(method (init &tmp temp0 temp1)
		(= local97_2_2_2_2 0)
		(= local3 1)
		(= [local50 0] @local45)
		(= [local50 1] 999)
		(egoActions init: ego @local45 @local50)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: 2
					init:
						0
						189
						0
						0
						319
						0
						319
						164
						278
						160
						260
						146
						232
						146
						232
						126
						101
						126
						39
						188
					yourself:
				)
		)
		(HandsOff)
		(target setLoop: 0 ignoreActors: 1 init:)
		(barrier init:)
		(leftBush init:)
		(leftRocks init:)
		(rightRocks init:)
		(leftTreeTop init:)
		(leftTreeBot init:)
		(rightTrees init:)
		(plant1 init:)
		(plant2 init:)
		(plant3 init:)
		(plant4 init:)
		(plant5 init:)
		(plant6 init:)
		(targArea init:)
		(= local95_2_2_2_2 0)
		(= local96_2_2_2_2 0)
		(= local94_2_2_2_2 0)
		(= temp1 0)
		(while (< temp1 6)
			((= [newRackSpear temp1] (rackSpear new:))
				loop: (if (< temp1 3) 4 else 3)
				cel: (Random 0 3)
				x: [local32 (* temp1 2)]
				y: [local32 (+ (* temp1 2) 1)]
				init:
			)
			(++ temp1)
		)
		(spearRack approachVerbs: 4 init:)
		(flag init:)
		(if (Btst 56)
			(LoadMany 128 980 981 989)
		else
			(LoadMany 128 46 971 972 969)
		)
		(super init:)
		(ego
			normalize:
			setScale:
			scaleX: 128
			scaleY: 128
			actions: egoActions
			noun: 3
			init:
			hide:
		)
		(cond 
			((and (Btst 56) (not Night) (not (Btst 16)))
				(= local1 3)
				(ego x: 103 y: 148 setHeading: 15 show:)
				((ScriptID 39 1)
					view: 984
					x: 242
					y: 144
					loop: 4
					cel: 0
					setScale:
					scaleX: 128
					scaleY: 128
					init:
				)
				(ego setScript: startContest)
			)
			((== prevRoomNum 480) (ego setScript: enterFromEast))
			(else (curRoom setScript: enterRoom))
		)
	)
	
	(method (doit)
		(cond 
			(script 0)
			((ego script?) 0)
			((and (ego mover?) local44) (localproc_1ea1))
			((>= (ego y?) 183) (curRoom setScript: walkEgoOut))
			((>= (ego x?) 315) (curRoom setScript: exitTo480))
		)
		(super doit: &rest)
	)
	
	(method (dispose)
		(if (!= (cSound number?) 160) (cSound changeTo: 160))
		((theIconBar at: 3) cursor: 942)
		(UnLoad 128 46)
		(UnLoad 128 9)
		(UnLoad 128 10)
		(UnLoad 128 971)
		(UnLoad 128 972)
		(UnLoad 128 969)
		(UnLoad 128 980)
		(UnLoad 128 981)
		(UnLoad 128 989)
		(LoadMany 0 34 39 991 955 53)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(65 (messager say: 27 6 66))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance egoActions of Teller
	(properties)
	
	(method (showDialog)
		(super
			showDialog:
				-26
				(cast contains: (ScriptID 34 1))
				-27
				(cast contains: (ScriptID 34 1))
				-21
				(if
				(and (cast contains: (ScriptID 34 1)) (== local1 2))
					(not local94_2_2)
				else
					0
				)
		)
	)
	
	(method (doChild)
		(return
			(switch query
				(-26
					(if (not (Btst 155)) (Bset 155) (ego addHonor: 4))
					(return 1)
				)
				(-27
					(ego addHonor: 2)
					(localproc_1ea1)
					(curRoom setScript: uhuraLeave)
					(return 0)
				)
				(-21
					(uhuraActions query: -37)
					(uhuraActions doChild:)
				)
			)
		)
	)
	
	(method (doVerb theVerb &tmp temp0)
		(switch theVerb
			(2 (super doVerb: theVerb))
			(else  (ego doVerb: theVerb))
		)
	)
)

(instance uhuraActions of Teller
	(properties)
	
	(method (showDialog)
		(super
			showDialog:
				-18
				(if (== local1 1) (not local96_3_2) else 0)
				20
				(!= local1 1)
				-37
				(not local94_2_2)
				-28
				[egoStats 10]
		)
	)
	
	(method (doChild)
		(return
			(switch query
				(-37
					(= local94_2_2 1)
					(= local97_3 0)
					(localproc_1f3a)
					((ScriptID 34 1) setScript: competeVSUhura)
					(return 0)
				)
				(-18
					(messager say: 2 5 18)
					(curRoom setScript: uhuraThrow)
					(return 0)
				)
				(-23 (super doChild: &rest))
				(-28 (= local2 1) (return 1))
			)
		)
	)
	
	(method (doVerb theVerb &tmp temp0)
		(switch theVerb
			(2 (super doVerb: theVerb))
			(else 
				((ScriptID 34 1) doVerb: theVerb)
			)
		)
	)
)

(instance enterFromEast of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego x: 330 y: 170 show: setMotion: PolyPath 300 170 self)
			)
			(1 (HandsOn) (self dispose:))
		)
	)
)

(instance yesufuThrows of Script
	(properties)
	
	(method (doit)
		(cond 
			((not (target script?)) 0)
			((!= state 0) 0)
			((and (== (target x?) 202) (== local4 -1))
				(= targetX 155)
				(= targetY (+ (target y?) (Random 0 2)))
				(self cue:)
			)
			((and (== (target x?) 140) (== local4 1))
				(= targetX 158)
				(= targetY (+ (target y?) (Random 0 2)))
				(self cue:)
			)
		)
		(super doit: &rest)
	)
	
	(method (changeState newState &tmp temp0 temp1 temp2)
		(switch (= state newState)
			(0
				(= local97_2_2_2_2 0)
				(if (not (target script?))
					(= targetX (target x?))
					(= targetY (target y?))
					(= targetX (+ targetX (- 7 (Random 0 14))))
					(= targetY (+ targetY (- 6 (Random 0 12))))
					(= cycles 1)
				)
			)
			(1
				(= local97_4 (+ local96_4 3))
				(switch theArcadeDifficulty
					(1 (= temp2 150))
					(2 (= temp2 175))
					(3 (= temp2 200))
				)
				(= local94_2 0)
				([newRackSpear local97_4] dispose:)
				((ScriptID 39 1)
					setCel: 0
					loop: 2
					setScale:
					scaleX: 128
					scaleY: 128
					setCycle: EndLoop self
				)
			)
			(2
				((ScriptID 39 1) loop: 3 cel: 0 setCycle: CycleTo 3 1 self)
				(= local97_2_2_2_2 0)
			)
			(3
				((ScriptID 39 1) setCycle: EndLoop self)
				(= local95_2_2_2_2
					(+
						local95_2_2_2_2
						(Abs (- (target x?) targetX))
						(Abs (- (target y?) targetY))
					)
				)
				((= local97_2_2_2_2 (= [newSpear local97_4] (spear new:)))
					x: 200
					y: 79
					setLoop: 1
					setScale:
					scaleX: 128
					scaleY: 128
					setCycle: CycleTo 4 1 self
					setPri: (if (< targetX (+ (target nsLeft?) 2)) 3 else 5)
					init:
					setMotion: JumpTo targetX targetY self
				)
			)
			(4 1)
			(5
				(if (> (local97_2_2_2_2 x?) (+ (target x?) 4))
					(local97_2_2_2_2 x: (- (local97_2_2_2_2 x?) 3))
				)
				(if (< (local97_2_2_2_2 x?) (- (target x?) 4))
					(local97_2_2_2_2 x: (+ (local97_2_2_2_2 x?) 3))
				)
				(= cycles 1)
			)
			(6
				(globalSound number: 461 setLoop: 1 play: 127)
				((ScriptID 39 1)
					loop: 4
					setCel: 0
					setScale:
					scaleX: 128
					scaleY: 128
				)
				(= temp0 (- ([newSpear local97_4] x?) (target x?)))
				(= temp1 (- ([newSpear local97_4] y?) (target y?)))
				(if (target script?)
					(if (target onMe: [newSpear local97_4])
						([newSpear local97_4]
							setMotion: (trackSpear new:) target temp0 temp1
							setPri: (if local5 5 else 2)
						)
					else
						([newSpear local97_4] setPri: 2)
					)
				)
				([newSpear local97_4] setCycle: 0)
				(= cycles 2)
			)
			(7 (self dispose:))
		)
	)
)

(instance startContest of Script
	(properties)
	
	(method (doit)
		(if local95_2_2_2 (= local95_2_2_2 0) (self cue:))
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(User canControl: 0)
				(= ticks 1)
			)
			(1
				(cSound changeTo: 490)
				(= ticks 30)
			)
			(2
				(messager say: 4 6 39 0 self)
			)
			(3
				(messager say: 4 6 46 0 self)
			)
			(4
				(= theArcadeDifficulty arcadeDifficulty)
				(self setScript: throwMode self)
			)
			(5
				(localproc_206a)
				(User canControl: 0)
			)
			(6 (= cycles 1))
			(7
				(self setScript: yesufuThrows self)
			)
			(8
				(if (< (++ local96_4) 3)
					(self changeState: (- state 3))
				else
					(= local96_4 0)
					(= cycles 1)
				)
			)
			(9
				(= local0 6)
				(localproc_1f3a)
				(if (> local96_2_2_2_2 local95_2_2_2_2)
					(messager say: 4 6 50 0 self)
				else
					(messager say: 4 6 51 0 self)
				)
				(target setScript: moveTarget)
				(= local95_2_2
					(= local95_2 (= local96_2_2 (= local0 0)))
				)
			)
			(10
				(localproc_206a)
				(User canControl: 0)
			)
			(11 (= cycles 1))
			(12
				(self setScript: yesufuThrows self)
			)
			(13
				(if (< (++ local96_4) 3)
					(self changeState: (- state 3))
				else
					(= cycles 1)
				)
			)
			(14
				(if (< local96_2_2_2_2 local95_2_2_2_2)
					(Bset 78)
					(messager say: 4 6 55 0 self)
				else
					(messager say: 4 6 54 0 self)
				)
			)
			(15
				(if (< local96_2_2_2_2 local95_2_2_2_2)
					(ego view: 32 loop: 0 cel: 0 setCycle: EndLoop self)
					(cSound setLoop: 1 number: 462 play:)
					(ego solvePuzzle: 278 5 9)
				else
					(= cycles 1)
				)
			)
			(16
				(localproc_1ea1)
				(curRoom newRoom: 470)
			)
		)
	)
)

(instance competeVSUhura of Script
	(properties)
	
	(method (doit)
		(if local97_3 (= local97_3 0) (self cue:))
		(super doit:)
	)
	
	(method (changeState newState &tmp temp0 temp1)
		(switch (= state newState)
			(0
				(messager say: 2 6 39 0 self)
			)
			(1
				(messager say: 2 6 40 0 self)
			)
			(2
				(localproc_1f3a)
				(switch theArcadeDifficulty
					(1 (= temp1 180))
					(2 (= temp1 215))
					(3 (= temp1 240))
				)
				(= local96_2_2_2_2 (= local95_2_2 (= local96_2_2 0)))
			)
			(3
				(= local3 0)
				(HandsOff)
				(= local96_3 3)
				(if (and (not local94_2_2_2) (>= local95_2_2 5))
					(= temp0 1)
				else
					(= temp0 2)
				)
				(self setScript: uhuraThrowSp self temp0)
			)
			(4 (= cycles 3))
			(5
				(++ local96_3)
				(if (and (not local94_2_2_2) (>= local95_2_2 4))
					(= temp0 1)
				else
					(= temp0 2)
				)
				(self setScript: uhuraThrowSp self temp0)
			)
			(6 (= cycles 3))
			(7
				(++ local96_3)
				(if (and (not local94_2_2_2) (>= local95_2_2 3))
					(= temp0 1)
				else
					(= temp0 2)
				)
				(self setScript: uhuraThrowSp self temp0)
			)
			(8 (= cycles 1))
			(9
				(HandsOn)
				(= local3 1)
				(localproc_200b)
				(if (< local95_2_2_2_2 local96_2_2_2_2)
					(= local95_2_2_2_2 0)
					(messager say: 2 6 43 0 self)
				else
					(= local95_2_2_2_2 0)
					(messager say: 2 6 44 0 self)
				)
				(Bset 64)
			)
			(10
				(messager say: 2 6 45 0 self)
			)
			(11
				((ScriptID 34 1)
					view: 971
					setCycle: StopWalk 969
					setHeading: 270
				)
			)
			(12
				(ego solvePuzzle: 277 3 9)
				(curRoom setScript: uhuraLeave)
				(self dispose:)
			)
		)
	)
)

(instance uhuraLeave of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(localproc_200b)
				(messager say: 3 5 27 0 self)
			)
			(1
				((ScriptID 34 1)
					view: 971
					setCycle: StopWalk 969
					setMotion: PolyPath ((ScriptID 34 1) x?) 235 self
					setCycle: StopWalk 969
				)
			)
			(2
				(cSound changeTo: 160)
				(= local96_2_3 0)
				(= local1 0)
				((ScriptID 34 1) dispose:)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance uhuraEnterAgain of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(cSound changeTo: 460)
				(= local1 2)
				(= local96_2_3 0)
				(if (not register)
					(messager say: 2 6 35 0 self)
				else
					(messager say: 2 6 56 0 self)
				)
			)
			(1
				(self setScript: uhuraEnter self)
			)
			(2
				(messager say: 2 6 36 0 self)
			)
			(3 (self dispose:))
		)
	)
)

(instance uhuraThrowSp of Script
	(properties)
	
	(method (changeState newState &tmp targetX_2 targetY_2)
		(switch (= state newState)
			(0
				([newRackSpear local96_3] dispose:)
				((ScriptID 34 1)
					view: 973
					setCel: 0
					loop: 0
					setCycle: EndLoop self
				)
			)
			(1
				((ScriptID 34 1) loop: 2 cel: 0 setCycle: CycleTo 3 1 self)
			)
			(2
				((ScriptID 34 1) setCycle: EndLoop self)
				(= targetX_2 (target x?))
				(= targetY_2 (target y?))
				(= targetX_2 (+ targetX_2 (- 9 (Random 0 18))))
				(= targetY_2 (+ targetY_2 (- 9 (Random 0 18))))
				(= local95_2_2_2_2
					(+
						local95_2_2_2_2
						(Abs (- (target x?) targetX_2))
						(Abs (- (target y?) targetY_2))
					)
				)
				((= [newSpear local96_3] (spear new:))
					x: 200
					y: 79
					setLoop: 1
					setScale:
					scaleX: 128
					scaleY: 128
					setCycle: CycleTo 4 1
					setPri: (if (< targetX_2 (+ (target nsLeft?) 2)) 3 else 5)
					setMotion: JumpTo targetX_2 targetY_2 self
					init:
				)
			)
			(3
				(Animate (cast elements?) 1)
				(= cycles 5)
			)
			(4
				(if
					(and
						(< 168 ([newSpear local96_3] x?))
						(< ([newSpear local96_3] x?) 174)
						(< 52 ([newSpear local96_3] y?))
						(< ([newSpear local96_3] y?) 58)
					)
					(= local96_2_2 (+ local96_2_2 2))
				else
					(= local96_2_2 (+ local96_2_2 1))
				)
				(globalSound number: 461 setLoop: 1 play: 127)
			)
			(5 (self dispose:))
		)
	)
)

(instance uhuraThrow of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= local96_3_2 1)
				(= local96_3 4)
				(self setScript: uhuraThrowSp self)
			)
			(1
				(if (== arcadeDifficulty 1)
					((ScriptID 34 1) view: 971 setCycle: StopWalk 969)
					(self dispose:)
				else
					(messager say: 2 6 38 0 self)
				)
			)
			(2
				(= local96_3 5)
				(self setScript: uhuraThrowSp self)
			)
			(3
				((ScriptID 34 1) view: 971 setCycle: StopWalk 969)
				(self dispose:)
			)
		)
	)
)

(instance throwMode of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				((theIconBar at: 3) cursor: 947)
				(= local44 1)
				(= theArcadeDifficulty arcadeDifficulty)
				(ego setHeading: 15)
				(= cycles 1)
			)
			(1 (HandsOn) (self dispose:))
		)
	)
)

(instance uhuraEnter of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(cSound changeTo: 460)
				(HandsOff)
				(ego setHeading: 90)
				(Bset 53)
				((ScriptID 34 1)
					x: 230
					y: 200
					setCycle: StopWalk 969
					setScale:
					scaleX: 128
					scaleY: 128
					noun: 2
					init:
					setMotion: MoveTo 242 144 self
				)
			)
			(1
				((ScriptID 34 1) setHeading: 270)
				(= cycles 18)
			)
			(2
				(if (== local1 1)
					(= [local97_2 0] @local96_2_2_2)
					(= [local97_2 1] @local96_2)
					(uhuraActions
						init: (ScriptID 34 1) @local96_2_2_2 @local97_2 @local97_2_2_2
					)
					(messager say: 2 6 17 0 self)
				else
					(= [local97_2 0] @local97_3_2)
					(uhuraActions
						init: (ScriptID 34 1) @local97_3_2 @local97_2
					)
					(= cycles 1)
				)
			)
			(3
				(if (and (== local1 1) (== origHeroType 0))
					(messager say: 2 6 19 0 self)
				else
					(= cycles 1)
				)
			)
			(4 (HandsOn) (self dispose:))
		)
	)
)

(instance throwSpear of Script
	(properties)
	
	(method (changeState newState &tmp temp0 temp1 temp2)
		(switch (= state newState)
			(0
				(Load RES_VIEW 10)
				(= local97_2_3 1)
				(= local97_3_2_2 0)
				(if (!= local1 3)
					(HandsOff)
				else
					(theIconBar disable: 3)
					(theGame setCursor: waitCursor 1)
				)
				(if (== [egoStats 10] 0)
					(messager say: 26 61 57)
					(localproc_1ea1)
					(HandsOn)
					(self dispose:)
				else
					([newRackSpear local0] dispose:)
					(ego view: 10 loop: 0 cel: 0 setCycle: CycleTo 4 1 self)
				)
			)
			(1
				(ego setCycle: EndLoop)
				(switch theArcadeDifficulty
					(1 (= temp0 150))
					(2 (= temp0 175))
					(3 (= temp0 200))
				)
				(localproc_1c78)
				((= [newSpear local0] (spear new:))
					init:
					ignoreActors: 1
					illegalBits: 0
					setLoop: 5
					setPri: (if (< targetX (+ (target nsLeft?) 2)) 3 else 5)
					setCycle: CycleTo 2 1
					cycleSpeed: 6
					setScale:
					scaleX: 128
					scaleY: 128
					setStep: 30 20
					setMotion: MoveTo targetX targetY self
				)
			)
			(2
				(if
					(and
						(< (+ (target nsLeft?) 2) targetX)
						(< targetX (- (target nsRight?) 2))
						(< (target nsTop?) targetY)
						(< targetY (- (target nsBottom?) 5))
					)
					(if (target mover?)
						(= temp1 (- targetX (target x?)))
						(= temp2 (- targetY (target y?)))
						([newSpear local0]
							setMotion: (trackSpear new:) target temp1 temp2
							setPri: 5
						)
					)
					(= register 0)
					(globalSound number: 461 setLoop: 1 play: 127)
				else
					(= register 1)
				)
				([newSpear local0] setCycle: EndLoop self)
			)
			(3
				(= local96_2_2_2_2
					(+
						local96_2_2_2_2
						(Abs (- (target x?) targetX))
						(Abs (- (target y?) targetY))
					)
				)
				(cond 
					(register
						(cond 
							(
								(and
									(cast contains: (ScriptID 34 1))
									local2
									(== local1 1)
									[egoStats 10]
								)
								(= local97_2_3 1)
								(localproc_2111)
							)
							((and (== local1 0) [egoStats 10]) (= local97_2_3 1) (localproc_2351))
						)
						([newSpear local0] setPri: 3)
					)
					(
						(and
							(== local1 1)
							local2
							(cast contains: (ScriptID 34 1))
						)
						(messager say: 2 6 29)
					)
				)
				(= local97_2_3 0)
				(if (== (++ local0) 3)
					(= ticks 120)
				else
					(= ticks 60)
				)
			)
			(4
				(if (and (!= local1 3) (== local0 3))
					(= local97_3 1)
					(messager say: 26 61 58)
					(localproc_1f3a)
				)
				(ego normalize: 3 setHeading: 15)
				(if
					(and
						(Btst 53)
						(== origHeroType 0)
						(not (Btst 64))
						(not (cast contains: (ScriptID 34 1)))
						(localproc_1faf)
					)
					(self setScript: uhuraEnterAgain self register)
				else
					(= cycles 1)
				)
			)
			(5
				(= local95_2_2_2 1)
				(if (or (not local3) (== local1 3)) 0 else (HandsOn))
				(UnLoad 128 10)
				(self dispose:)
			)
		)
	)
)

(instance moveTarget of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= local4 1)
				(target setMotion: MoveTo 215 55 self)
			)
			(1
				(= local4 -1)
				(target setMotion: MoveTo 120 55 self)
			)
			(2
				(self changeState: (- state 2) &rest)
			)
		)
	)
)

(instance exitTo480 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: PolyPath (+ (ego x?) 30) (ego y?) self)
			)
			(1 (curRoom newRoom: 480))
		)
	)
)

(instance walkEgoOut of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: PolyPath (ego x?) (+ (ego y?) 65) self)
			)
			(1 (curRoom newRoom: 420))
		)
	)
)

(instance enterRoom of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego x: 160 y: 200 show: setMotion: PolyPath 160 180 self)
			)
			(1 (HandsOn) (self dispose:))
		)
	)
)

(instance throwDagger of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Load RES_VIEW 9)
				(= theArcadeDifficulty arcadeDifficulty)
				(if (== [egoStats 10] 0)
					(messager say: 26 61 57)
					(self dispose:)
				else
					(HandsOff)
					(ego setMotion: PolyPath 103 148 self)
				)
			)
			(1
				(ego view: 9 loop: 2 cel: 0 setCycle: CycleTo 3 1 self)
			)
			(2
				(ego setCycle: EndLoop)
				(localproc_1c78)
				((= [newDagger local15] (dagger new:))
					setCycle: Forward
					cycleSpeed: 2
					moveSpeed: 2
					setScale: 115
					origStep: 2570
					setMotion: MoveTo targetX targetY self
					init:
				)
				(ego drop: 10 1)
			)
			(3
				(if
					(not
						(if (and local5 (< 157 targetX)) (< targetX 185))
					)
					([newDagger local15] hide:)
					(localproc_2535 self)
				else
					(globalSound number: 461 setLoop: 1 play: 127)
					(= cycles 1)
				)
			)
			(4
				(if (not (& ([newDagger local15] signal?) $0080))
					([newDagger local15] setCycle: 0 cel: 3)
				)
				(++ local15)
				(HandsOn)
				(if (== local15 10)
					(= local15 0)
					(while (< local15 10)
						([newDagger local15] dispose:)
						(++ local15)
					)
					(ego get: 10 10)
					(= local15 0)
					(messager say: 26 61 59)
				)
				(ego normalize: 6)
				(UnLoad 128 9)
				(self dispose:)
			)
		)
	)
)

(instance spearRack of Feature
	(properties
		x 90
		y 120
		nsTop 85
		nsLeft 50
		nsBottom 152
		nsRight 100
		sightAngle 40
		approachX 103
		approachY 148
	)
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(4
					(if (and (not (Btst 53)) (not Night))
						(= local1 1)
						(curRoom setScript: uhuraEnter)
					else
						(curRoom setScript: throwMode)
					)
					(return 1)
				)
				(1 (curRoom doVerb: 1 &rest))
				(else  (super doVerb: theVerb))
			)
		)
	)
)

(instance targArea of Feature
	(properties
		x 159
		y 40
		nsTop 34
		nsLeft 73
		nsBottom 88
		nsRight 246
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(4
					(cond 
						((curRoom script?) 0)
						(local44
							(= targetX mouseX)
							(= targetY (- mouseY 10))
							(curRoom setScript: throwSpear)
							(return 1)
						)
					)
				)
				(20
					(cond 
						((curRoom script?) 0)
						((not local44)
							(= targetX mouseX)
							(= targetY (- mouseY 10))
							(curRoom setScript: throwDagger)
						)
						(else (messager say: 26 61 60))
					)
				)
				(1 (curRoom doVerb: 1))
				(else 
					(super doVerb: theVerb &rest)
				)
			)
		)
	)
)

(instance rackSpear of View
	(properties
		noun 10
		approachX 103
		approachY 148
		view 460
		cel 1
		signal $6800
	)
	
	(method (init)
		(self approachVerbs: 4)
		(super init:)
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 1)
			(super doVerb: 1 &rest)
		else
			(spearRack doVerb: theVerb &rest)
		)
	)
)

(instance barrier of Feature
	(properties
		x 169
		y 108
		noun 12
		nsTop 106
		nsLeft 82
		nsBottom 110
		nsRight 256
		sightAngle 180
	)
)

(instance leftBush of Feature
	(properties
		x 14
		y 169
		noun 13
		nsTop 158
		nsBottom 181
		nsRight 29
		sightAngle 180
	)
)

(instance leftRocks of Feature
	(properties
		x 35
		y 145
		noun 14
		nsTop 129
		nsBottom 162
		nsRight 70
		sightAngle 180
	)
)

(instance rightRocks of Feature
	(properties
		x 294
		y 135
		noun 15
		nsTop 117
		nsLeft 270
		nsBottom 154
		nsRight 319
		sightAngle 180
	)
)

(instance leftTreeTop of Feature
	(properties
		x 65
		y 20
		noun 16
		nsBottom 41
		nsRight 130
		sightAngle 180
	)
)

(instance leftTreeBot of Feature
	(properties
		x 58
		y 67
		noun 17
		nsTop 48
		nsLeft 14
		nsBottom 87
		nsRight 103
		sightAngle 180
	)
)

(instance rightTrees of Feature
	(properties
		x 273
		y 40
		noun 18
		nsLeft 228
		nsBottom 80
		nsRight 319
		sightAngle 180
	)
)

(instance plant1 of Feature
	(properties
		x 78
		y 173
		noun 19
		nsTop 165
		nsLeft 62
		nsBottom 181
		nsRight 95
		sightAngle 180
	)
)

(instance plant2 of Feature
	(properties
		x 183
		y 135
		noun 20
		nsTop 127
		nsLeft 167
		nsBottom 144
		nsRight 200
		sightAngle 180
	)
)

(instance plant3 of Feature
	(properties
		x 224
		y 130
		noun 21
		nsTop 124
		nsLeft 201
		nsBottom 136
		nsRight 247
		sightAngle 180
	)
)

(instance plant4 of Feature
	(properties
		x 213
		y 172
		noun 22
		nsTop 166
		nsLeft 202
		nsBottom 178
		nsRight 225
		sightAngle 180
	)
)

(instance plant5 of Feature
	(properties
		x 123
		y 95
		noun 23
		nsTop 89
		nsLeft 111
		nsBottom 101
		nsRight 135
		sightAngle 180
	)
)

(instance plant6 of Feature
	(properties
		x 235
		y 88
		noun 24
		nsTop 81
		nsLeft 219
		nsBottom 95
		nsRight 252
		sightAngle 180
	)
)

(instance flag of Prop
	(properties
		x 33
		y 98
		noun 8
		view 460
		loop 5
	)
	
	(method (doit &tmp temp0)
		(if (!= arcadeDifficulty 1)
			(if (and (not (self cycler?)) (== loop 5))
				(= local95_3 10)
				(if (Random 0 1)
					(= local96_3_2_2 1)
				else
					(= local96_3_2_2 -1)
				)
				(self
					loop: (if (> local96_3_2_2 0) 1 else 2)
					setCycle: Forward
				)
			)
			(if (< (= temp0 (Random 0 3600)) 10)
				(= local96_3_2_2 1)
				(= loop 1)
			)
			(if (> temp0 3590) (= local96_3_2_2 -1) (= loop 2))
		else
			(if local95_3 (= local96_3_2_2 (= local95_3 0)))
			(if (!= loop 5) (self setCycle: 0 loop: 5))
		)
		(super doit: &rest)
	)
)

(instance target of Actor
	(properties
		x 171
		y 55
		noun 9
		view 460
		priority 4
		signal $6010
		xStep 1
		moveSpeed 0
	)
	
	(method (doVerb theVerb &tmp temp0)
		(switch theVerb
			(4
				(if
					(and
						(!= ((theIconBar at: 3) cursor?) 3)
						(IsObject [newDagger 0])
					)
					(= temp0 0)
					(while (< temp0 local15)
						([newDagger temp0] dispose:)
						(ego get: 10)
						(++ temp0)
					)
					(messager say: 26 61 59)
					(= local15 0)
				else
					(targArea doVerb: theVerb &rest)
				)
			)
			(20
				(targArea doVerb: theVerb &rest)
			)
			(13 (messager say: 27 4 62))
			(33 (messager say: 27 4 63))
			(12 (messager say: 27 4 64))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance spear of Actor
	(properties
		x 128
		y 92
		noun 10
		view 46
		loop 5
		signal $6000
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 1)
			(super doVerb: theVerb &rest)
		else
			(targArea doVerb: theVerb &rest)
		)
	)
)

(instance dagger of Actor
	(properties
		x 95
		y 110
		noun 11
		view 46
		loop 2
		priority 5
		signal $6810
	)
)

(instance spearPath of JumpTo
	(properties
		waitApogeeX 0
		waitApogeeY 0
	)
)

(instance trackSpear of Track
	(properties)
	
	(method (doit)
		(client
			x: (+ (who x?) xOffset)
			y: (+ (who y?) yOffset)
			z: (+ (who z?) zOffset)
		)
	)
)
