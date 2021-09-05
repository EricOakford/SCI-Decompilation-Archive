;;; Sierra Script 1.0 - (do not remove this comment)
(script# 640)
(include game.sh)
(use Main)
(use EgoDead)
(use OccasionalCycle)
(use JumpX)
(use PolyPath)
(use Polygon)
(use Feature)
(use LoadMany)
(use Reverse)
(use Sound)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm640 0
)

(local
	local0
	local1
	local2
	local3
	local4 =  1
	local5
	local6
	local7
	local8
	local9
	local10
)
(instance rm640 of Room
	(properties
		noun 6
		picture 640
		vanishingY -100
	)
	
	(method (init)
		(LoadMany 128 31 2 43)
		(HandsOff)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: 3
					init:
						145
						135
						85
						128
						70
						106
						64
						126
						99
						145
						72
						151
						72
						158
						119
						155
						144
						171
						246
						170
						200
						139
						184
						134
					yourself:
				)
		)
		(skull init:)
		(skulls init:)
		(table init:)
		(bed approachVerbs: 4 2 init:)
		(hole init:)
		(skin init:)
		(ego
			x: 78
			y: 126
			init:
			setScale: 135
			normalize:
			changeGait: 2 0
		)
		(spear init:)
		(chest init:)
		(chief init: approachVerbs: 4 2 stopUpd:)
		(monkey init: approachVerbs: 29 4 2)
		(spell init: setScale: 135 hide:)
		(masK init: approachVerbs: 4)
		(walkHandler addToFront: self)
		(super init: &rest)
		(curRoom setScript: enterHut)
		(theGame save: 1)
	)
	
	(method (dispose)
		(walkHandler delete: self)
		(LoadMany 0 57 13 939)
		(super dispose: &rest)
	)
	
	(method (doVerb theVerb)
		(if
			(and
				(== theVerb 3)
				(or (!= (ego view?) 2) (<= (ego trySkill: 8 140) 0))
				(not local5)
			)
			(HandsOff)
			(= local5 1)
			(= local0 1)
			(= local8 1)
			(if local9
				(ego
					setMotion: PolyPath ((User curEvent?) x?) ((User curEvent?) y?)
				)
				(self setScript: egoWalk)
			else
				(= local9 1)
				(ego
					setMotion: PolyPath ((User curEvent?) x?) ((User curEvent?) y?)
				)
				(self setScript: chiefStir)
			)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance chiefStir of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(chief setCycle: BegLoop self)
				(ego setMotion: 0)
			)
			(1 (messager say: 1 2 8 0 self))
			(2
				(HandsOn)
				(= local5 0)
				(self dispose:)
			)
		)
	)
)

(instance castFetch of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: MoveTo 130 140 self)
			)
			(1 (Face ego chest self))
			(2
				(ego view: 14 setLoop: 1 cel: 0 setCycle: EndLoop self)
			)
			(3
				(spell
					view: 21
					x: (- (ego x?) 5)
					y: (- (ego y?) 30)
					moveSpeed: 1
					cycleSpeed: 1
					setStep: 10 5
					setLoop: 7
					setCycle: Walk
					setMotion: MoveTo 67 137 self
					show:
				)
			)
			(4
				(spell setMotion: MoveTo (ego x?) (- (ego y?) 30) self)
			)
			(5 (spell hide:) (= cycles 3))
			(6
				(ego normalize: changeGait: 2 0)
				(if local10
					(messager say: 3 6 18 0 self)
				else
					(= local10 1)
					(messager say: 3 4 0 0 self)
				)
			)
			(7
				(ego get: 10 1)
				(ego get: 0 8)
				(= local10 1)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance noEscape of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(messager say: 1 2 1 0 self)
			)
			(1
				(ego setMotion: PolyPath 80 129 self)
			)
			(2
				(HandsOn)
				(= local4 0)
				(self dispose:)
			)
		)
	)
)

(instance egoWalk of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(chief startUpd: setCycle: EndLoop self)
			)
			(1 (= seconds 1))
			(2
				(chief
					x: 249
					y: 176
					cel: 0
					setLoop: 1
					cycleSpeed: 10
					setCycle: CycleTo 5 1 self
				)
			)
			(3
				(Palette PALIntensity 0 255 1000)
				(= cycles 2)
			)
			(4
				(Palette PALIntensity 0 255 100)
				(= cycles 1)
			)
			(5
				(spell dispose:)
				(ego view: 43 setLoop: 2 setCycle: EndLoop self)
			)
			(6
				(switch local8
					(1 (EgoDead 8))
					(2 (EgoDead 9))
					(3 (EgoDead 10))
					(4 (EgoDead 2))
					(5 (EgoDead 6))
					(6 (EgoDead 7))
					(7 (EgoDead 14))
				)
			)
		)
	)
)

(instance enterHut of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: PolyPath 80 129 self)
			)
			(1
				(ego code: monkeyCheck)
				(= local0 1)
				(= local4 0)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance monkeyWay of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(monkey cue:)
				(ego code: normCode)
				(= cycles 1)
			)
			(1
				(sFx number: 929 setLoop: -1 play:)
				(= seconds 10)
			)
			(2
				(if local2
					(self dispose:)
				else
					(HandsOff)
					(= local8 2)
					(curRoom setScript: egoWalk)
				)
			)
		)
	)
)

(instance monkeyOut of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(monkeyWay dispose:)
				(sFx number: 481 play:)
				(messager say: 7 6 15 0 self)
			)
			(1
				(monkey
					setLoop: 1
					setCycle: EndLoop
					cycleSpeed: 4
					moveSpeed: 2
					setMotion: JumpX (+ (monkey x?) 25) 12 self
				)
			)
			(2
				(monkey setCycle: 0 setMotion: JumpX -45 self)
			)
			(3
				(monkey
					setPri: 15
					cycleSpeed: 2
					setLoop: 3
					setCycle: Forward
					setMotion: MoveTo (- (ego x?) 10) 91 self
				)
			)
			(4
				(monkey setPri: 2 setMotion: MoveTo 54 75 self)
			)
			(5
				(ego addHonor: 20)
				(monkey dispose:)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance castOpenMonkey of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(monkeyWay dispose:)
				(ego setMotion: PolyPath 192 153 self)
			)
			(1 (Face ego monkey self))
			(2
				(ego view: 15 setLoop: 3 setCycle: EndLoop self)
			)
			(3
				(monkey
					setLoop: 1
					setCycle: EndLoop
					cycleSpeed: 4
					moveSpeed: 2
					setMotion: JumpX (+ (monkey x?) 25) 12 self
				)
			)
			(4
				(monkey setCycle: 0 setMotion: JumpX -45 self)
			)
			(5
				(monkey
					setPri: 15
					cycleSpeed: 2
					setLoop: 3
					setCycle: Forward
					setMotion: MoveTo (monkey x?) 91 self
				)
			)
			(6
				(monkey setPri: 2 setMotion: MoveTo 54 75 self)
			)
			(7
				(ego changeGait: 2 0 normalize:)
				(ego addHonor: 20)
				(monkey dispose:)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance turnChief of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (chief setCycle: EndLoop self))
			(1 (= seconds 2))
			(2
				(chief setCycle: CycleTo 0 -1 self)
			)
			(3 (self dispose:))
		)
	)
)

(instance castOpenOnChest of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: PolyPath 130 140 self)
			)
			(1 (Face ego chest self))
			(2
				(ego view: 15 setLoop: 1 setCycle: EndLoop self)
			)
			(3
				(ego changeGait: 2 0 normalize:)
				(cond 
					((not local6)
						(= local6 1)
						(if (not local3)
							(chief setScript: turnChief)
							(messager say: 3 6 5)
						else
							(= cycles 1)
						)
					)
					((not local3) (= local8 5) (chest cel: 1) (curRoom setScript: egoBack))
					(else (chest cel: 1) (= local7 1))
				)
				(= cycles 5)
			)
			(4 (HandsOn) (self dispose:))
		)
	)
)

(instance egoBack of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego
					setCycle: Reverse
					setLoop: 1
					setMotion: PolyPath (+ (ego x?) 15) (ego y?) self
				)
			)
			(1
				(ego normalize:)
				(curRoom setScript: egoWalk)
			)
		)
	)
)

(instance gripSpear of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: PolyPath 181 135 self)
			)
			(1
				(ego view: 31 loop: 0 setCycle: CycleTo 2 1 self)
			)
			(2
				(ego setCycle: BegLoop self)
				(spear dispose:)
			)
			(3
				(ego
					view: 2
					setCycle: Walk
					changeGait: 2 0
					get: 45
					addHonor: -50
					solvePuzzle: 304 10 4
				)
				(messager say: 1 6 13 0 self)
			)
			(4 (HandsOn) (self dispose:))
		)
	)
)

(instance egoTouch of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego view: 31 loop: 0 setCycle: EndLoop self)
				(soundFx number: 641 setLoop: 1 play:)
				(curRoom setScript: egoWalk)
			)
			(1
				(ego changeGait: 2 0 normalize:)
				(= cycles 1)
			)
			(2
				(ego
					setCycle: Reverse
					setLoop: 0
					setMotion: MoveTo (- (ego x?) 25) (+ (ego y?) 5) self
				)
			)
			(3
				(ego normalize:)
				(= local8 3)
				(self dispose:)
			)
		)
	)
)

(instance openChest of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: PolyPath 78 152 self)
			)
			(1 (messager say: 3 6 2 0 self))
			(2 (self dispose:))
		)
	)
)

(instance getChest of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: PolyPath 78 152 self)
			)
			(1
				(ego
					view: 4
					loop: 1
					cel: 0
					setCycle: CycleTo (if local10 1 else 3) 1 self
				)
				(if (not local7) (chest cel: 1))
			)
			(2
				(if (not local3)
					(= local8 5)
					(curRoom setScript: egoBack)
				else
					(self cue:)
				)
			)
			(3 (ego setCycle: CycleTo 0 -1 self))
			(4
				(ego normalize: changeGait: 2 0)
				(if local10
					(messager say: 3 6 18 0 self)
				else
					(messager say: 3 4 0 0 self)
				)
			)
			(5
				(ego get: 10 1)
				(ego get: 0 8)
				(= local10 1)
				(self dispose:)
			)
		)
	)
)

(instance pickLock of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: PolyPath 78 152 self)
			)
			(1
				(Face ego chest)
				(= cycles (+ (ego cycleSpeed?) 5))
			)
			(2
				(cond 
					((not local6)
						(ego useSkill: 9 200)
						(= local6 1)
						(if (not local3)
							(chief setScript: turnChief)
							(messager say: 3 6 3)
						else
							(messager say: 3 6 20)
						)
					)
					((not local3) (= local8 5) (curRoom setScript: egoBack))
					(else (chest cel: 1) (= local7 1))
				)
				(= cycles 5)
			)
			(3 (HandsOn) (self dispose:))
		)
	)
)

(instance oilChest of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: PolyPath 78 152 self)
			)
			(1
				(ego view: 4 loop: 1 cel: 0 setCycle: CycleTo 3 1 self)
			)
			(2 (ego setCycle: CycleTo 0 -1 self))
			(3
				(ego changeGait: 2 0 normalize:)
				(messager say: 3 6 6 0 self)
			)
			(4 (self dispose:))
		)
	)
)

(instance monkeyCheck of Code
	(properties)
	
	(method (doit)
		(cond 
			((and (& (ego onControl:) $0010) (not local4))
				(= local4 1)
				(if (ego has: 45)
					(curRoom newRoom: 630)
				else
					(curRoom setScript: noEscape)
				)
			)
			(
			(and (> (ego x?) 110) (& local0 $0001) (not local2)) (= local0 2) (monkey setScript: monkeyWay))
		)
	)
)

(instance normCode of Code
	(properties)
	
	(method (doit)
		(if (and (& (ego onControl:) $0010) (not local4))
			(= local4 1)
			(if (ego has: 45)
				(curRoom newRoom: 630)
			else
				(curRoom setScript: noEscape)
			)
		)
	)
)

(instance chief of Actor
	(properties
		x 258
		y 173
		z 30
		noun 14
		approachX 212
		approachY 146
		view 641
		signal $1000
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1 (messager say: noun 1 0))
			(4
				(= local8 4)
				(ego
					setLoop: 1
					setMotion: PolyPath (- (ego x?) 65) (ego y?)
				)
				(curRoom setScript: egoWalk)
			)
			(2
				(= local8 6)
				(ego
					setLoop: 1
					setMotion: PolyPath (- (ego x?) 65) (ego y?)
				)
				(curRoom setScript: egoWalk)
			)
			(20
				(= local8 7)
				(curRoom setScript: egoWalk)
			)
			(82
				(= local8 4)
				(curRoom setScript: egoWalk)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance monkey of Actor
	(properties
		x 137
		y 91
		noun 15
		approachX 137
		approachY 133
		view 645
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(29
				(= local2 1)
				(if (not local1)
					(= local1 1)
					(ego solvePuzzle: 302 5 4)
					(messager say: 7 6 16)
					(if (& local0 $0002) (monkey setCycle: 0))
				)
			)
			(4
				(= local2 1)
				(if (not (Btst 90))
					(Bset 90)
					(ego solvePuzzle: 303 5 4)
					(curRoom setScript: monkeyOut)
				else
					(super doVerb: theVerb)
				)
			)
			(75
				(= local2 1)
				(if (not (Btst 90))
					(Bset 90)
					(curRoom setScript: castOpenMonkey)
				else
					(super doVerb: theVerb)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
	
	(method (cue)
		(self setCycle: OccasionalCycle self 1 10 50)
	)
)

(instance spell of Actor
	(properties
		x 240
		y 128
		view 21
		signal $4000
	)
)

(instance spear of Prop
	(properties
		x 193
		y 133
		noun 5
		approachX 196
		approachY 128
		view 640
		loop 1
		signal $4000
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(curRoom setScript: gripSpear)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance chest of View
	(properties
		x 75
		y 138
		noun 3
		view 640
		priority 10
		signal $4010
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(if (not local6)
					(curRoom setScript: openChest)
				else
					(curRoom setScript: getChest)
				)
			)
			(75
				(if local7
					(messager say: 1 6 11)
				else
					(curRoom setScript: castOpenOnChest)
				)
			)
			(17
				(if local6
					(messager say: 1 6 19)
				else
					(curRoom setScript: pickLock)
				)
			)
			(35
				(if local7
					(messager say: 1 6 12)
				else
					(= local3 1)
					(curRoom setScript: oilChest)
				)
			)
			(82
				(if local7
					(curRoom setScript: castFetch)
				else
					(messager say: 1 6 21)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance masK of Feature
	(properties
		x 229
		y 95
		noun 2
		onMeCheck $0008
		approachX 206
		approachY 125
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(HandsOff)
				(ego setScript: egoTouch)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance skull of Feature
	(properties
		x 49
		y 80
		noun 8
		nsTop 66
		nsLeft 37
		nsBottom 95
		nsRight 61
	)
)

(instance skulls of Feature
	(properties
		x 90
		y 45
		noun 9
		nsTop 39
		nsLeft 80
		nsBottom 52
		nsRight 101
	)
)

(instance table of Feature
	(properties
		x 96
		y 161
		noun 10
		nsTop 153
		nsLeft 45
		nsBottom 169
		nsRight 148
	)
)

(instance bed of Feature
	(properties
		x 246
		y 145
		noun 11
		nsTop 134
		nsLeft 212
		nsBottom 156
		nsRight 281
		approachX 212
		approachY 146
	)
	
	(method (doVerb theVerb)
		(if (or (== theVerb 4) (== theVerb 2))
			(chief doVerb: theVerb)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance hole of Feature
	(properties
		x 182
		y 107
		noun 12
		nsTop 97
		nsLeft 165
		nsBottom 118
		nsRight 199
	)
)

(instance skin of Feature
	(properties
		x 218
		y 67
		noun 13
		nsTop 47
		nsLeft 182
		nsBottom 88
		nsRight 254
	)
)

(instance sFx of Sound
	(properties)
)
