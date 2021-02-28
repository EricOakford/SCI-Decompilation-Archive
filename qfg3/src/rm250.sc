;;; Sierra Script 1.0 - (do not remove this comment)
(script# 250)
(include sci.sh)
(use Main)
(use TellerIcon)
(use OccasionalCycle)
(use Vendor)
(use Bazaar)
(use GloryTalker)
(use PolyPath)
(use Polygon)
(use Feature)
(use LoadMany)
(use Timer)
(use Jump)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm250 0
	beadTalker 1
	fishTalker 2
	ropeTalker 3
)

(local
	theVendor
	local1
	local2
	local3
	local4
	local5
	local6
	local7
	local8
	local9
	local10
	local11
	[local12 2]
	[local14 11] = [0 -1 -59 -74 -4 -16 -47 -55 -72 -13 999]
	[local25 6] = [0 4 5 6 7 999]
	[local31 6] = [0 16 5 -6 -78 999]
	[local37 6] = [0 -6 -79 5 -54 999]
	[local43 5] = [0 -66 68 69 999]
	[local48 3] = [0 67 999]
	[local51 3] = [0 -66 999]
	[local54 2]
	[local56 2]
	[local58 2]
	[local60 2]
	[local62 3]
	local67_2
	local66
	local67_2_2
)
(procedure (localproc_1bb6)
	(if local6
		(switch local5
			(0 (messager say: 6 6 60))
			(1 (messager say: 6 6 61))
			(2 (messager say: 6 6 62))
			(3 (messager say: 6 6 63))
			(4 (messager say: 6 6 64))
			(else  (messager say: 6 6 65))
		)
		(if (> (++ local5) 6) (= local5 0))
		((Timer new:) set: aHarami 8)
	)
)

(instance rm250 of Rm
	(properties
		noun 19
		picture 250
		horizon -280
	)
	
	(method (init)
		(self setRegions: 51)
		(ego
			noun: 2
			normalize:
			edgeHit: 0
			actions: egoTeller
			init:
			setScale: 0
		)
		(HandsOn)
		(switch prevRoomNum
			(240
				(= style -32759)
				(self setScript: from240)
			)
			(260
				(= style -32759)
				(= local67_2 1)
				(self setScript: from260)
			)
			(else 
				(ego x: 200 y: 110 setHeading: 135)
			)
		)
		(if (or Night (Btst 135))
			(curRoom
				addObstacle:
					((Polygon new:)
						type: 2
						init: 236 189 0 189 0 0 36 0 149 69 55 83
						yourself:
					)
					((Polygon new:)
						type: 2
						init: 319 115 213 135 198 112 315 93 315 45 183 63 116 22 126 0 319 0
						yourself:
					)
			)
		else
			(if (== (cSound prevSignal?) -1) (cSound play:))
			(cSound fade: 80 10 5 0)
			(ego code: dayCode)
			(curRoom
				addObstacle:
					((Polygon new:)
						type: 2
						init: 0 189 0 0 34 0 144 61 154 71 210 109 124 124 238 189
						yourself:
					)
					((Polygon new:)
						type: 2
						init:
							319
							0
							319
							189
							199
							119
							315
							92
							315
							50
							270
							47
							244
							58
							188
							58
							119
							23
							120
							0
						yourself:
					)
			)
		)
		(if (and (not Night) (not (Btst 135)))
			(= [local54 0] @local14)
			(egoTeller init: ego @local14 @local54)
			(= [local56 0] @local25)
			(aBeadMaker
				setPri: 1
				setCycle: OccasionalCycle self 1 65 150
				actions: beadTeller
				noun: 1
				approachVerbs: 2 59 10
				init:
			)
			(beadTeller init: aBeadMaker @local25 @local56)
			(beadVendor
				init:
				goods:
					((List new:)
						add: ((Class_47_1 new: 1)
							price: 95
							denomination: 1
							quantity: 9999
						)
					)
			)
			(= [local58 0] @local31)
			(aFishSeller
				setPri: 9
				setCycle: OccasionalCycle self 1 40 170
				actions: fishTeller
				noun: 3
				approachVerbs: 2 59 10
				init:
			)
			(fishTeller init: aFishSeller @local31 @local58)
			(fishVendor
				init:
				goods:
					((List new:)
						add: ((Class_47_1 new: 3)
							price: 50
							quantity: 9999
							denomination: 1
						)
					)
			)
			(= [local60 0] @local37)
			(aRopeMaker
				setCycle: OccasionalCycle self 1 55 150
				noun: 4
				actions: ropeTeller
				approachVerbs: 2 59 10
				init:
			)
			(ropeTeller init: aRopeMaker @local37 @local60)
			(ropeVendor
				init:
				goods:
					((List new:)
						add:
							((Class_47_1 new: 4)
								price: 100
								denomination: 1
								quantity: (if (Btst 168) 0 else 1)
							)
					)
			)
			(if
			(and (not (Btst 40)) (== local67_2 1) (> Day 4))
				(ego code: cueCode)
			)
		)
		(if (and (not Night) (not (Btst 135)))
			(beadtent init:)
			(rack_of_beads init:)
			(fishingpoles init:)
			(fishbuckets init:)
			(fishstand init:)
			(fishsticks init:)
			(brassworks init:)
			(ropes init:)
			(ropestand init:)
			(ropehook init:)
		)
		(river init:)
		(moreriver init:)
		(if (and (not Night) (not (Btst 135)))
			(fishA init: addToPic:)
			(fishB init: addToPic:)
			(beadA init: addToPic:)
			(ropeA setPri: 9 init: addToPic:)
		)
		(super init: &rest)
	)
	
	(method (doit)
		(super doit: &rest)
		(cond 
			(script)
			((not (if (< 5 (ego x?)) (< (ego x?) 315))) (self setScript: sExit))
			((not (if (< 35 (ego y?)) (< (ego y?) 183))) (self setScript: sExit))
		)
	)
	
	(method (dispose)
		(LoadMany 0 40 47)
		(super dispose:)
	)
	
	(method (doVerb theVerb &tmp [temp0 2])
		(switch theVerb
			(1
				(messager
					say: 19 1 0 (if (or (Btst 135) Night) 0 else 1)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance dayCode of Code
	(properties)
	
	(method (doit)
		(cond 
			(
				(and
					(not Night)
					(ego inRect: 193 42 237 65)
					(not local1)
					(not (Btst 135))
				)
				(curRoom setScript: beadGreet)
				(= local1 1)
			)
			((& (ego onControl: 1) $0004)
				(if
				(and (not Night) (not local9) (not (Btst 135)))
					(curRoom setScript: fishGreet)
					(= local9 1)
				)
			)
			((& (ego onControl: 1) $0008)
				(if
				(and (not Night) (not local10) (not (Btst 135)))
					(curRoom setScript: ropeGreet)
					(= local10 1)
				)
			)
			((not (& (ego onControl: 1) $0004)) (if local9 (= local9 0)))
			(
			(and (not (& (ego onControl: 1) $0008)) local10) (= local10 0))
		)
	)
)

(instance beadGreet of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: PolyPath 233 58 self)
			)
			(1
				(Face ego aBeadMaker)
				(= cycles 15)
			)
			(2 (messager say: 1 6 1 0 self))
			(3 (HandsOn) (self dispose:))
		)
	)
)

(instance haramiLeave of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego code: dayCode)
				(= local67_2_2 0)
				(= local6 0)
				(if (not local66)
					(if local11
						(messager say: 6 6 59 0 self)
					else
						(messager say: 6 6 87 0 self)
					)
				else
					(self cue:)
				)
			)
			(1
				(aHarami
					view: 950
					setScript: 0
					setCycle: Walk
					ignoreActors: 1
					setMotion: PolyPath 310 (aHarami y?) self
				)
			)
			(2
				(HandsOn)
				(aHarami dispose:)
				(self dispose:)
			)
		)
	)
)

(instance ropeGreet of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: PolyPath 247 145 self)
			)
			(1
				(Face ego aRopeMaker)
				(= cycles 15)
			)
			(2
				(if local8
					(switch (mod Day 6)
						(0
							(messager say: 4 6 41 0 self)
						)
						(1
							(messager say: 4 6 42 0 self)
						)
						(2
							(messager say: 4 6 43 0 self)
						)
						(3
							(messager say: 4 6 44 0 self)
						)
						(4
							(messager say: 4 6 45 0 self)
						)
						(5
							(messager say: 4 6 46 0 self)
						)
					)
				else
					(switch (mod Day 6)
						(0
							(messager say: 4 6 30 0 self)
						)
						(1
							(messager say: 4 6 31 0 self)
						)
						(2
							(messager say: 4 6 26 0 self)
						)
						(3
							(messager say: 4 6 32 0 self)
						)
						(4
							(messager say: 4 6 33 0 self)
						)
						(5
							(messager say: 4 6 34 0 self)
						)
					)
				)
			)
			(3
				(if local8
					(self setScript: jumpFlip self)
				else
					(= cycles 1)
				)
			)
			(4 (HandsOn) (self dispose:))
		)
	)
)

(instance fishGreet of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: PolyPath 160 78 self)
			)
			(1
				(Face ego aFishSeller)
				(= cycles 15)
			)
			(2
				(switch (mod Day 6)
					(0
						(messager say: 3 6 30 0 self)
					)
					(1
						(messager say: 3 6 31 0 self)
					)
					(2
						(messager say: 3 6 26 0 self)
					)
					(3
						(messager say: 3 6 32 0 self)
					)
					(4
						(messager say: 3 6 33 0 self)
					)
					(5
						(messager say: 3 6 34 0 self)
					)
				)
			)
			(3 (HandsOn) (self dispose:))
		)
	)
)

(instance from240 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego x: 30 y: 0 setMotion: PolyPath 155 50 self)
			)
			(1 (ego setHeading: 180 self))
			(2 (HandsOn) (self dispose:))
		)
	)
)

(instance from260 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					x: 270
					y: 200
					setHeading: 345
					setMotion: PolyPath 230 172 self
				)
			)
			(1 (ego setHeading: 0 self))
			(2 (HandsOn) (self dispose:))
		)
	)
)

(instance sExit of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= register 0)
				(cond 
					((<= (ego x?) 5)
						(= register 240)
						(ego setMotion: PolyPath -30 (ego y?) self)
					)
					((<= (ego y?) 35)
						(= register 240)
						(ego setMotion: PolyPath (- (ego x?) 30) -30 self)
					)
					((>= (ego x?) 315)
						(if (< (ego y?) 90)
							(messager say: 5 6 86)
							(ego setMotion: PolyPath (- (ego x?) 30) (ego y?) self)
						else
							(= register 260)
							(ego setMotion: PolyPath 340 (ego y?) self)
						)
					)
					((>= (ego y?) 183)
						(= register 260)
						(ego setMotion: PolyPath (+ (ego x?) 35) 250 self)
					)
				)
			)
			(1
				(if (!= register 0) (curRoom newRoom: register))
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance sMeetHarami of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego code: dayCode setMotion: 0)
				(= [local62 0] @local43)
				(= [local62 1] @local48)
				(haramiTeller init: aHarami @local43 @local62 @local51)
				(aHarami
					view: 950
					loop: 0
					x: 300
					y: (ego y?)
					noun: 6
					init:
					ignoreActors: 1
					setStep: 4 2
					setCycle: Walk
					setMotion: MoveTo (+ (ego x?) 25) (ego y?) self
				)
			)
			(1
				(Face ego aHarami)
				(aHarami
					view: 954
					cel: 0
					loop: 0
					cycleSpeed: 9
					ignoreActors: 0
					setCycle: End self
				)
				(Bset 40)
			)
			(2
				(messager say: 6 6 30 0 self)
			)
			(3
				(= local6 1)
				(= local67_2_2 1)
				((Timer new:) set: aHarami 5)
				(HandsOn)
				(ego code: walkCode)
				(self dispose:)
			)
		)
	)
)

(instance jumpFlip of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(aRopeMaker
					setLoop: 0
					cel: 0
					ignoreActors: 1
					setCycle: CT 1 1 self
				)
			)
			(1
				(aRopeMaker
					setPri: 8
					setLoop: 1
					cel: 3
					x: 259
					y: 126
					cycleSpeed: 12
					setCycle: CT 7 1 self
				)
			)
			(2
				(aRopeMaker loop: 2 cel: 0 y: 112)
				(= cycles 2)
			)
			(3
				(aRopeMaker
					setCel: -1
					cel: 1
					y: 86
					setCycle: CT 5 1
					setMotion: JumpTo 259 44 self
				)
			)
			(4
				(aRopeMaker
					setCycle: CT 8 1 self
					setMotion: JumpTo 259 53 self
				)
			)
			(5 0)
			(6
				(aRopeMaker setLoop: 3 setCel: 7 y: 84)
				(= ticks 60)
			)
			(7
				(aRopeMaker setLoop: 3 cel: 0)
				(= cycles 6)
			)
			(8
				(aRopeMaker cel: 1 setCycle: End self)
			)
			(9
				(aRopeMaker cel: 1 setCycle: End self)
			)
			(10
				(aRopeMaker cel: 1 setCycle: End)
				(= seconds 3)
			)
			(11
				(aRopeMaker cel: 1 setCycle: End self)
			)
			(12
				(aRopeMaker
					setLoop: 1
					cel: 0
					x: 260
					y: 84
					setCycle: CT 2 1 self
				)
			)
			(13
				(aRopeMaker
					setLoop: 7
					cel: 0
					x: 264
					y: 82
					setCycle: End self
				)
			)
			(14
				(aRopeMaker
					setLoop: 2
					cycleSpeed: 17
					setCel: 0
					x: 259
					y: 60
				)
				(= ticks 10)
			)
			(15
				(aRopeMaker
					cel: 1
					x: 261
					y: 36
					setCycle: CT 5 1
					setMotion: JumpTo 261 21 self
				)
			)
			(16
				(aRopeMaker
					setCycle: CT 8 1 self
					setMotion: JumpTo 261 40 self
				)
			)
			(17 0)
			(18 (= ticks 8))
			(19
				(aRopeMaker setLoop: 3 cel: 0 x: 259 y: 83 setCycle: End)
				(= ticks 110)
			)
			(20
				(aRopeMaker
					setLoop: 1
					cel: 0
					x: 259
					y: 83
					setCycle: CT 3 1 self
				)
			)
			(21
				(aRopeMaker setMotion: JumpTo 259 126 self)
			)
			(22
				(aRopeMaker cel: 4)
				(= cycles 5)
			)
			(23
				(aRopeMaker setPri: -1 setLoop: 0 cel: 0 cycleSpeed: 8)
				(= seconds 3)
			)
			(24
				(aRopeMaker setCycle: OccasionalCycle self 1 55 150)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance cueCode of Code
	(properties)
	
	(method (doit)
		(if (< (ego y?) 100) (curRoom setScript: sMeetHarami))
	)
)

(instance walkCode of Code
	(properties)
	
	(method (doit)
		(if
			(and
				(or (ego mover?) (> local11 2))
				(not local66)
				local67_2_2
			)
			(curRoom setScript: haramiLeave)
		)
	)
)

(instance aHarami of Actor
	(properties
		x 186
		y 96
		view 955
		cel 3
		cycleSpeed 5
	)
	
	(method (cue)
		(if (and (> local11 2) (not (curRoom script?)))
			(curRoom setScript: haramiLeave)
		else
			(localproc_1bb6)
		)
	)
)

(instance aBeadMaker of Actor
	(properties
		x 220
		y 59
		heading 180
		approachX 233
		approachY 58
		view 246
		signal $4000
		cycleSpeed 3
		detailLevel 3
	)
)

(instance aFishSeller of Actor
	(properties
		x 106
		y 85
		heading 90
		approachX 160
		approachY 78
		view 268
		cycleSpeed 9
		detailLevel 3
	)
)

(instance aRopeMaker of Actor
	(properties
		x 259
		y 126
		heading 270
		approachX 247
		approachY 145
		view 262
		cycleSpeed 9
		detailLevel 3
	)
)

(instance beadTalker of GloryTalker
	(properties
		x 200
		y 90
		view 247
		loop 1
		talkWidth 140
		back 57
		textX -178
		textY 3
		backColor 8
	)
	
	(method (init)
		(super init: beadBust beadEyes beadMouth &rest)
	)
)

(instance beadMouth of Prop
	(properties
		nsTop 52
		nsLeft 26
		view 247
	)
)

(instance beadEyes of Prop
	(properties
		nsTop 41
		nsLeft 21
		view 247
		loop 2
	)
)

(instance beadBust of View
	(properties
		nsTop 26
		nsLeft 22
		view 247
		loop 3
	)
)

(instance fishTalker of GloryTalker
	(properties
		x 5
		y 90
		view 269
		loop 1
		talkWidth 140
		back 57
		textX 143
		textY 3
		backColor 13
	)
	
	(method (init)
		(super init: fishBust fishEyes fishMouth &rest)
	)
)

(instance fishMouth of Prop
	(properties
		nsTop 59
		nsLeft 43
		view 269
	)
)

(instance fishEyes of Prop
	(properties
		nsTop 42
		nsLeft 40
		view 269
		loop 2
	)
)

(instance fishBust of View
	(properties
		nsTop 30
		nsLeft 38
		view 269
		loop 3
	)
)

(instance ropeTalker of GloryTalker
	(properties
		x 5
		y 90
		view 263
		loop 1
		talkWidth 140
		back 57
		textX 145
		textY 3
		backColor 13
	)
	
	(method (init)
		(super init: ropeBust ropeEyes ropeMouth &rest)
	)
)

(instance ropeMouth of Prop
	(properties
		nsTop 60
		nsLeft 46
		view 263
	)
)

(instance ropeEyes of Prop
	(properties
		nsTop 43
		nsLeft 49
		view 263
		loop 2
	)
)

(instance ropeBust of View
	(properties
		nsTop 29
		nsLeft 51
		view 263
		loop 3
	)
)

(instance egoTeller of Teller
	(properties)
	
	(method (respond)
		(return
			(if (not local4)
				(super respond:)
			else
				(= local4 0)
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
	
	(method (showDialog)
		(= local2 (proc51_1))
		(= local3 (ego distanceTo: local2))
		(switch local2
			(aFishSeller
				(if (> local3 65) (messager say: 5 6 80) (return -999))
			)
			(aBeadMaker
				(if (> local3 35) (messager say: 5 6 80) (return -999))
			)
			(else 
				(if (> local3 45) (messager say: 5 6 80) (return -999))
			)
		)
		(if
			(!=
				(ego heading?)
				(GetAngle (ego x?) (ego y?) (local2 x?) (local2 y?))
			)
			(Face ego local2)
		)
		((Timer new:) setCycle: self (+ (ego cycleSpeed?) 10))
		(= iconValue 0)
		(return -999)
	)
	
	(method (doChild &tmp temp0 temp1)
		(return
			(switch query
				(-13
					(ego solvePuzzle: 229 8)
					(return query)
				)
				(-1
					(cond 
						((cast contains: aHarami) (= query 70))
						((== local2 aBeadMaker) (= local11 3) (= query 8))
						((== local2 aFishSeller) (= local11 3) (= query 14))
						((== local2 aRopeMaker) (= local11 3) (= query 81))
					)
				)
				(-59
					(cond 
						((cast contains: aHarami)
							(= local66 1)
							(= local6 0)
							(messager say: 2 5 71)
							(curRoom setScript: haramiLeave)
							(return 0)
						)
						((== local2 aBeadMaker) (= local11 3) (= query 9))
						((== local2 aFishSeller) (= local11 3) (= query 15))
						((== local2 aRopeMaker) (= local11 3) (= query 82))
					)
				)
				(-74
					(cond 
						((cast contains: aHarami) (ego solvePuzzle: 229 8) (= query 13))
						((== local2 aBeadMaker) (= local11 3) (= query 10))
						((== local2 aFishSeller) (= local11 3) (= query 11))
						((== local2 aRopeMaker)
							(= local11 3)
							(messager say: 2 5 12)
							(curRoom setScript: jumpFlip)
							(= local8 1)
							(return 0)
						)
					)
				)
				(-4
					(= local11 3)
					(if (== ((inventory at: 0) message?) 59)
						(messager say: 1 6 77)
						(return 0)
					else
						(beadVendor purchase:)
					)
					(return 0)
				)
				(-16
					(= local11 3)
					(if (== ((inventory at: 0) message?) 59)
						(messager say: 3 6 77)
						(return 0)
					else
						(fishVendor purchase:)
						(return 0)
					)
				)
				(-47
					(= local11 3)
					(if (== ((inventory at: 0) message?) 59)
						(messager say: 4 6 77)
						(return 0)
					else
						(if (Btst 168)
							(messager say: 5 6 88)
						else
							(ropeVendor purchase:)
						)
						(return 0)
					)
				)
				(-55
					(= local11 3)
					(if
						(or
							(<
								(= temp0
									(+ (* ((inventory at: 0) amount?) 100) commons)
								)
								1000
							)
							(== ((inventory at: 0) message?) 59)
						)
						(messager say: 5 6 83)
						(return 0)
					else
						(= temp0 (/ commons 100))
						(= commons (mod commons 100))
						((inventory at: 0)
							amount: (+ (- ((inventory at: 0) amount?) 50) temp0)
						)
					)
					(Bset 149)
					(if (> (= [egoStats 2] (+ [egoStats 2] 30)) 300)
						(= [egoStats 2] 300)
					)
					(return 1)
				)
				(-72
					(= local66 1)
					(= local6 0)
					(messager say: 2 5 72)
					(if (not (Btst 230)) (ego addHonor: 30))
					(ego solvePuzzle: 230 4)
					(curRoom setScript: haramiLeave)
					(return 0)
				)
			)
		)
	)
	
	(method (cue)
		(= query
			(super
				showDialog:
					-1
					-59
					-74
					(== heroType 2)
					-55
					(if (and local7 (== local2 aRopeMaker))
						(not (Btst 149))
					else
						0
					)
					-4
					(== local2 aBeadMaker)
					-16
					(== local2 aFishSeller)
					-47
					(== local2 aRopeMaker)
					-72
					(cast contains: aHarami)
					-13
					(if (== heroType 2) (cast contains: aHarami) else 0)
			)
		)
		(= local4 1)
		(if iconValue (= query iconValue))
		(egoTeller respond:)
	)
)

(instance beadTeller of Teller
	(properties)
	
	(method (doVerb theVerb)
		(switch theVerb
			(59
				(= local11 3)
				(messager say: 1 6 77)
			)
			(10 (beadVendor purchase:))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance fishTeller of Teller
	(properties)
	
	(method (doChild)
		(switch query
			(-6
				(switch (mod Day 6)
					(0 (= query 17))
					(1 (= query 18))
					(2 (= query 19))
					(3 (= query 20))
					(4 (= query 21))
					(5 (= query 22))
				)
			)
			(-78
				(switch (mod Day 6)
					(0 (= query 23))
					(1 (= query 24))
					(2 (= query 25))
					(3 (= query 27))
					(4 (= query 28))
					(5 (= query 29))
				)
			)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(59
				(= local11 3)
				(messager say: 3 6 77)
			)
			(10 (fishVendor purchase:))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance ropeTeller of Teller
	(properties)
	
	(method (showDialog)
		(super showDialog: -54 local8)
	)
	
	(method (doChild)
		(return
			(switch query
				(-6
					(switch (mod Day 6)
						(0 (= query 17))
						(1 (= query 18))
						(2 (= query 19))
						(3 (= query 20))
						(4 (= query 21))
						(5 (= query 22))
					)
				)
				(-79
					(switch (mod Day 6)
						(0 (= query 48))
						(1 (= query 49))
						(2 (= query 50))
						(3 (= query 51))
						(4 (= query 52))
						(5 (= query 53))
					)
				)
				(-54 (= local7 1) (return 1))
			)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(59
				(= local11 3)
				(messager say: 4 6 77)
			)
			(10
				(if (Btst 168)
					(messager say: 5 6 88)
				else
					(ropeVendor purchase:)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance haramiTeller of Teller
	(properties)
	
	(method (showDialog)
		(++ local11)
		(super showDialog: &rest)
	)
)

(instance riverWater of View
	(properties
		x 16
		y 170
		view 250
	)
	
	(method (init)
		(if (or Night (Btst 135)) (super init: &rest))
	)
)

(instance fishA of View
	(properties
		x 62
		y 172
		view 250
		priority 7
		signal $5010
	)
	
	(method (onMe)
		(return 0)
	)
)

(instance fishB of View
	(properties
		x 159
		y 120
		view 250
		cel 1
		priority 7
		signal $5010
	)
	
	(method (onMe)
		(return 0)
	)
)

(instance beadA of View
	(properties
		x 181
		y 7
		view 250
		loop 1
		signal $5010
	)
	
	(method (onMe)
		(return 0)
	)
)

(instance ropeA of View
	(properties
		x 238
		y 88
		view 250
		loop 1
		cel 1
		priority 10
		signal $5010
	)
	
	(method (onMe)
		(return 0)
	)
)

(instance beadVendor of Vendor
	(properties
		noun 1
	)
	
	(method (bargain &tmp theEgoStats)
		(= theEgoStats [egoStats 13])
		(= [egoStats 13] 0)
		(super bargain: &rest)
		(= [egoStats 13] theEgoStats)
	)
	
	(method (transact param1 param2)
		(= theVendor self)
		(ego get: 20 param2 solvePuzzle: 231 2)
		(Buy param1 param2)
		(messager say: 1 6 2 0 self)
	)
	
	(method (doBargain)
		(messager say: 1 6 3 0 self)
	)
)

(instance fishVendor of Vendor
	(properties
		noun 3
	)
	
	(method (transact param1 param2)
		(= theVendor self)
		(ego get: 17 param2)
		(Buy param1 param2)
		(messager say: 3 6 2 0 self)
	)
	
	(method (doBargain param1)
		(switch param1
			(1
				(messager say: 3 6 35 0 self)
			)
			(2
				(messager say: 3 6 38 0 self)
			)
			(3
				(messager say: 3 6 39 0 self)
			)
			(4
				(messager say: 3 6 40 0 self)
			)
			(5
				(messager say: 3 6 36 0 self)
			)
			(6
				(messager say: 3 6 37 0 self)
			)
			(else  (self cue:))
		)
	)
)

(instance ropeVendor of Vendor
	(properties
		noun 4
	)
	
	(method (transact param1 param2)
		(= theVendor self)
		(ego get: 26 param2 solvePuzzle: 228 8 4)
		(Buy param1 param2)
		(Bset 168)
		(messager say: 4 6 2 0 self)
	)
	
	(method (doBargain param1)
		(switch param1
			(1
				(messager say: 4 6 35 0 self)
			)
			(2
				(messager say: 4 6 38 0 self)
			)
			(3
				(messager say: 4 6 39 0 self)
			)
			(4
				(messager say: 4 6 40 0 self)
			)
			(5
				(messager say: 4 6 36 0 self)
			)
			(6
				(messager say: 4 6 37 0 self)
			)
			(else  (self cue:))
		)
	)
)

(instance beadtent of Feature
	(properties
		x 195
		y 22
		noun 7
		nsLeft 154
		nsBottom 44
		nsRight 236
		sightAngle 180
	)
)

(instance rack_of_beads of Feature
	(properties
		x 213
		y 20
		noun 8
		nsTop 11
		nsLeft 201
		nsBottom 29
		nsRight 226
		sightAngle 180
	)
)

(instance fishingpoles of Feature
	(properties
		x 60
		y 92
		noun 9
		nsTop 77
		nsLeft 15
		nsBottom 107
		nsRight 105
		sightAngle 180
	)
)

(instance fishbuckets of Feature
	(properties
		x 143
		y 109
		noun 10
		nsTop 104
		nsLeft 131
		nsBottom 114
		nsRight 156
		sightAngle 180
	)
)

(instance fishstand of Feature
	(properties
		x 142
		y 65
		noun 11
		nsTop 64
		nsLeft 115
		nsBottom 90
		nsRight 169
		sightAngle 180
	)
)

(instance fishsticks of Feature
	(properties
		x 175
		y 65
		noun 12
		nsTop 64
		nsLeft 169
		nsBottom 113
		nsRight 182
		sightAngle 180
	)
)

(instance brassworks of Feature
	(properties
		x 259
		y 22
		noun 13
		nsTop 1
		nsLeft 235
		nsBottom 44
		nsRight 283
		sightAngle 180
	)
)

(instance ropes of Feature
	(properties
		x 272
		y 137
		noun 14
		nsTop 123
		nsLeft 235
		nsBottom 152
		nsRight 309
		sightAngle 180
	)
)

(instance ropestand of Feature
	(properties
		x 308
		y 116
		noun 15
		nsTop 89
		nsLeft 299
		nsBottom 143
		nsRight 318
		sightAngle 180
	)
)

(instance ropehook of Feature
	(properties
		x 217
		y 118
		noun 16
		nsTop 114
		nsLeft 209
		nsBottom 122
		nsRight 225
		sightAngle 180
	)
)

(instance river of Feature
	(properties
		x 31
		y 149
		noun 17
		nsTop 109
		nsBottom 189
		nsRight 62
		sightAngle 180
	)
)

(instance moreriver of Feature
	(properties
		x 86
		y 170
		noun 18
		nsTop 152
		nsLeft 61
		nsBottom 189
		nsRight 111
		sightAngle 180
	)
)
