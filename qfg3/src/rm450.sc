;;; Sierra Script 1.0 - (do not remove this comment)
(script# 450)
(include sci.sh)
(use Main)
(use TellerIcon)
(use EgoDead)
(use OccasionalCycle)
(use PolyPath)
(use Polygon)
(use Feature)
(use LoadMany)
(use Sound)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm450 0
)

(local
	local0
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
	local12
	[local13 20] = [0 -7 -8 -9 -3 -12 -20 -65 -25 -26 -38 -39 -47 -48 -42 -49 -40 -59 -58 999]
	[local33 4]
	[local37 3] = [0 -12 999]
	[local40 5] = [0 -13 -14 -15 999]
	[local45 15] = [0 -45 -1 -18 -19 -11 -5 -68 -67 -66 -10 -22 -44 26 999]
	[local60 2]
	[local62 4] = [0 5 6 999]
	[local66 2]
	[local68 3] = [0 7 999]
	[local71 2]
	[local73 4] = [0 58 59 999]
	[local77 2]
)
(instance rm450 of Rm
	(properties
		noun 10
		picture 450
	)
	
	(method (init)
		(= [local33 0] @local13)
		(= [local33 1] @local40)
		(= [local60 0] @local45)
		(= [local66 0] @local62)
		(= [local71 0] @local68)
		(= [local77 0] @local73)
		(Load rsMESSAGE 450)
		(LoadMany 128 450 451 423)
		(HandsOn)
		(super init:)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: 2
					init: 116 136 126 128 173 128 185 141 183 150 142 154 116 152
					yourself:
				)
				((Polygon new:)
					type: 3
					init:
						267
						189
						267
						163
						290
						163
						290
						142
						275
						142
						253
						130
						239
						122
						164
						111
						160
						101
						135
						101
						135
						113
						67
						125
						42
						125
						42
						149
						77
						149
						77
						163
						108
						163
						108
						189
					yourself:
				)
		)
		((ScriptID 42 0) y: 100)
		(cond 
			((and Night (Btst 50)) (= local11 7))
			((and (Btst 16) (not (Btst 163))) (= local11 8))
			(
				(and
					(not (== global392 4))
					(== global392 1)
					(ego has: 21)
					(ego has: 40)
					(ego has: 3)
					(>= ((inventory at: 21) amount?) 5)
				)
				(= global392 2)
				(= local11 6)
			)
			(
			(and (Btst 29) (not (Btst 38)) (== global392 0)) (= local11 3))
			(
				(and
					(Btst 38)
					(not (Btst 11))
					(ego has: 22)
					(or (== origHeroType 0) (== origHeroType 3))
				)
				(= local11 5)
			)
			((and (not (Btst 65)) (Btst 38)) (= global392 1) (= local11 4))
			((and (not (Btst 74)) (Btst 49) (not Night)) (= local11 1))
			(else (= local11 2))
		)
		(if
			(not
				(if (or (ego has: 46) (Btst 13)) else (Btst 74))
			)
			(drum init: stopUpd:)
		)
		(if (not (Btst 49)) (= local11 0))
		(fire setCycle: Fwd init:)
		(trunkLid init: stopUpd:)
		(crack init:)
		(leopardhide init:)
		(shields init:)
		(cSound number: 913 setLoop: -1 play: 127)
		(if (and (== local11 7) (Btst 50))
			(cSound setLoop: -1 number: 400 play:)
		else
			(cSound setLoop: -1 number: 450 play:)
		)
		(laibonTell
			init: (ScriptID 42 1) @local13 @local33 @local37
		)
		(egoTell init: ego @local45 @local60)
		(cond 
			((== local11 0)
				(= global392 0)
				(rakeeshTell init: (ScriptID 35 1) @local62 @local66)
				(uhuraTell init: native @local68 @local71)
				((ScriptID 34 0)
					x: 196
					y: 10
					textX: -175
					textY: 3
					talkWidth: 140
				)
				((ScriptID 35 0)
					x: 196
					y: 80
					textX: -175
					textY: 3
					talkWidth: 140
				)
				((ScriptID 42 1)
					view: 451
					x: 80
					y: 116
					setPri: 6
					setScale:
					scaleX: 154
					scaleY: 154
					noun: 4
					init:
				)
				(laibonArm
					setScale:
					scaleX: 154
					scaleY: 154
					init:
					setCycle: Snooze self 1 10 200
				)
				(native
					setScale: 160
					setScale:
					scaleX: 156
					scaleY: 156
					init:
					noun: 1
					stopUpd:
				)
				((ScriptID 35 1)
					view: 964
					loop: 3
					cel: 3
					x: 209
					y: 150
					noun: 2
					setScale:
					scaleX: 156
					scaleY: 156
					init:
				)
				(ego
					loop: 1
					setScale:
					scaleX: 156
					scaleY: 156
					setHeading: 315
					x: 105
					y: 150
					noun: 3
					init:
					normalize: 7
				)
				(curRoom setScript: enterFirstTime)
			)
			((OneOf local11 1 2 3 4 5 6) (curRoom setScript: enterRoom))
			((== local11 7)
				((ScriptID 42 1)
					view: 453
					x: 216
					y: 109
					setCycle: Snooze self 1 10 150
					setScript: laibonSnoozes
					init:
				)
				(if (Btst 50)
					(crack dispose:)
					(hole init: stopUpd:)
					(ego
						x: 213
						y: 120
						normalize:
						setScale:
						scaleX: 156
						scaleY: 156
						init:
						code: sneakCheck
					)
				else
					(theGame setScript: enterNormal)
				)
			)
			((== local11 8)
				(yesufuTell init: (ScriptID 39 1) @local73 @local77)
				(curRoom setScript: afterContest)
			)
		)
	)
	
	(method (doit)
		(cond 
			(
			(and (not (ego script?)) (== local11 3) (== local2 5)) (ego setScript: egoExits))
			(
			(and (not (ego script?)) (== local11 4) (== local2 4)) (ego setScript: egoExits))
			(
			(and (not local1) (== local11 5) (== local2 4)) (= local1 1) (messager say: 4 6 46))
			(
				(and
					(not (ego script?))
					(not (curRoom script?))
					(!= local11 7)
					(> (ego y?) 170)
				)
				(curRoom setScript: egoExits)
			)
		)
		(super doit: &rest)
	)
	
	(method (dispose)
		(if (theGame script?) (theGame setScript: 0))
		(cSound stop:)
		(soundFx stop:)
		(UnLoad 143 450)
		(UnLoad 128 450)
		(UnLoad 128 451)
		(UnLoad 128 423)
		((ScriptID 42 1) setCycle: 0)
		(laibonArm setCycle: 0)
		(LoadMany 0 35 13 12 34 33 42 939 39)
		(if gNewList (gNewList eachElementDo: #dispose))
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(75
				(if (ego castSpell: 19)
					(AutoTarget
						((User curEvent?) x?)
						((User curEvent?) y?)
					)
					(ego setScript: (ScriptID 13) 0 trunkLid)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance exitThief of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (messager say: 8 4 0 0 self))
			(1 (curRoom newRoom: 420))
		)
	)
)

(instance laibonSnoozes of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(sFx number: 941 play:)
				(= state -1)
				(= seconds (Random 5 15))
			)
		)
	)
)

(instance afterContest of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				((ScriptID 39 1)
					x: 148
					y: 110
					loop: 2
					setScale:
					scaleX: 156
					scaleY: 156
					noun: 6
					init:
				)
				((ScriptID 42 1)
					view: 451
					x: 80
					y: 116
					setPri: 6
					setScale:
					scaleX: 154
					scaleY: 154
					noun: 4
					init:
				)
				(laibonArm
					setScale:
					scaleX: 154
					scaleY: 154
					init:
					setCycle: Snooze self 1 10 200
				)
				(ego
					x: 250
					y: 180
					view: 0
					normalize:
					setScale:
					scaleX: 156
					scaleY: 156
					noun: 3
					init:
					setMotion: PolyPath 140 130 self
				)
			)
			(1 (= cycles 18))
			(2
				(messager say: 4 6 58 0 self)
			)
			(3
				(if global406
					(messager say: 4 6 64 0 self)
				else
					(messager say: 4 6 62 0 self)
				)
			)
			(4
				(messager say: 4 6 59 0 self)
			)
			(5
				(cond 
					((and (not global406) (Btst 30)) (ego get: 46) (Bset 163) (messager say: 6 6 60 0 self))
					((and (not global406) (not (Btst 30))) (messager say: 6 6 61 0 self))
					(global406 (ego get: 46) (Bset 163) (self cue:))
				)
			)
			(6
				(cond 
					((and (not global406) (Btst 30)) (curRoom newRoom: 420))
					((not global406) (EgoDead 72))
					(else (messager say: 4 6 63 0 self))
				)
			)
			(7 (DontMove) (= seconds 20))
			(8
				(curRoom setScript: egoExits)
			)
		)
	)
)

(instance eventFour of Script
	(properties)
	
	(method (doit)
		(if (or local0 (== state 4))
			(= local0 0)
			(self changeState: (= state 2))
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOn)
				(ego setHeading: 315 self)
			)
			(1
				(ego normalize:)
				(= seconds 5)
			)
			(2 (= cycles 250))
			(3
				(messager say: 4 6 16 0 self)
				(ego addHonor: -10)
			)
			(4 0)
		)
	)
)

(instance enterRoom of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				((ScriptID 42 1)
					x: 80
					y: 116
					setPri: 6
					setScale:
					scaleX: 154
					scaleY: 154
					noun: 4
					init:
				)
				(laibonArm
					setScale:
					scaleX: 154
					scaleY: 154
					init:
					setCycle: Snooze self 1 10 200
				)
				(ego
					normalize:
					setScale:
					scaleX: 156
					scaleY: 156
					x: 260
					y: 185
					noun: 3
					setMotion: PolyPath 240 140 self
					init:
				)
			)
			(1
				(ego setMotion: PolyPath 142 128 self)
			)
			(2
				(if (OneOf local11 4 5 6)
					(theGame setScript: eventFour)
				else
					(theGame setScript: eventsTwoAndThree)
				)
				(self dispose:)
			)
		)
	)
)

(instance eventsTwoAndThree of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOn)
				(ego code: outCheck setHeading: 315)
				(= seconds 5)
			)
			(1 (++ local9))
			(2
				(ego normalize:)
				(= seconds 30)
			)
			(3
				(cond 
					((== local11 3) (messager say: 4 6 25) (= seconds 10))
					((== local11 1) (self cue:))
					((== local10 0) (messager say: 4 6 16) (= seconds 10))
				)
			)
			(4
				(if (== local10 0)
					(messager say: 4 6 17 0 self)
				else
					(self cue:)
				)
			)
			(5
				(messager say: 4 6 21 0 self)
			)
			(6
				(curRoom setScript: egoExits)
				(self dispose:)
			)
		)
	)
)

(instance egoExits of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(theGame setScript: 0)
				(cond 
					((and (== local2 5) (== local11 3)) (messager say: 4 6 25 0 self))
					(
						(and
							(== local2 4)
							(== local11 4)
							(== origHeroType 0)
							(not (Btst 16))
						)
						(messager say: 4 6 43 0 self)
					)
					(
						(and
							(== origHeroType 0)
							(ego has: 21)
							(ego has: 40)
							(ego has: 3)
							(Btst 16)
							(== global392 2)
						)
						(messager say: 4 6 40)
						(self setScript: egoBuysJohari self)
					)
					(
						(and
							(!= origHeroType 0)
							(ego has: 21)
							(ego has: 40)
							(ego has: 3)
							(== global392 2)
						)
						(messager say: 4 6 40)
						(self setScript: egoBuysJohari self)
					)
					((and (== local11 5) (not (Btst 11))) (self setScript: egoGivesHorn))
					(else (= cycles 1))
				)
			)
			(1
				(if (and (== local2 4) (== local11 4))
					(messager say: 4 6 26 0 self)
				else
					(= cycles 1)
				)
			)
			(2
				(ego setMotion: PolyPath 290 220 self)
			)
			(3 (curRoom newRoom: 420))
		)
	)
)

(instance egoBows of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego addHonor: 5)
				(ego view: 32 loop: 1 setCycle: End self)
			)
			(1
				(ego normalize: 7)
				(= cycles 6)
			)
			(2
				(= local12 1)
				(switch register
					(-1
						(messager say: 3 5 1 0 self)
					)
					(-18
						(messager say: 3 5 18 0 self)
					)
					(-19
						(messager say: 3 5 19 0 self)
					)
					(else  (= cycles 1))
				)
			)
			(3 (self dispose:))
		)
	)
)

(instance egoBuysJohari of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego drop: 21 5 drop: 40 1 drop: 3 1)
				(= global366 11)
				(ego solvePuzzle: 272 3)
				(ego solvePuzzle: 274 3)
				(ego solvePuzzle: 273 3)
				(theIconBar advanceCurIcon:)
				(ego setMotion: PolyPath 89 132 self)
			)
			(1
				(laibonArm hide:)
				((ScriptID 42 1) cel: 0 loop: 2 setCycle: End self)
			)
			(2
				(messager say: 4 6 29 0 self)
			)
			(3
				((ScriptID 42 1) setCycle: Beg self)
			)
			(4
				(laibonArm show:)
				(= global392 4)
				((ScriptID 42 1) loop: 0)
				(if (!= client egoExits) (HandsOn))
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
				(ego setMotion: PolyPath 232 140 self)
			)
			(1
				(HandsOn)
				(globalSound number: 311 setLoop: 1 play:)
				(messager say: 7 17)
				(= local7 1)
				(self dispose:)
			)
		)
	)
)

(instance egoOilsChest of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: PolyPath 232 140 self)
			)
			(1
				(HandsOn)
				(messager say: 7 35)
				(= local5 1)
				(self dispose:)
			)
		)
	)
)

(instance castOpenOnChest of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(HandsOff)
				(cond 
					((not local5)
						(globalSound number: 311 setLoop: 1 play:)
						(if (not local7)
							(= local7 1)
							(messager say: 7 75 78)
						else
							(messager say: 7 75 54)
							(sFx number: 640 play:)
							(trunkLid setCel: 1)
							(curRoom setScript: wakeUpChuckSpear 0 56)
							(self dispose:)
						)
					)
					((not local7)
						(= local7 1)
						(globalSound number: 311 setLoop: 1 play:)
						(messager say: 7 75 74)
					)
					(else
						(messager say: 7 75 53)
						(trunkLid setCel: 1)
						(ego get: 2)
						(= commons (+ commons 700))
						((inventory at: 0)
							amount: (+ ((inventory at: 0) amount?) 60)
						)
						((inventory at: 0) message: 10)
					)
				)
			)
			(1
				(HandsOn)
				(ego normalize:)
				(self dispose:)
			)
		)
	)
)

(instance castOpenOnCrack of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(HandsOff)
				(AutoTarget 220 78)
				(cond 
					((== (= temp0 (ego castSpell: 19)) 1)
						(messager say: 9 6 35)
						(self setScript: (ScriptID 13 0) self)
					)
					((== temp0 -1) (self setScript: (ScriptID 13 0) self))
				)
			)
			(1
				(if (== temp0 0)
					(HandsOn)
					(ego normalize:)
					(self dispose:)
				)
				(crack dispose:)
				(hole init: stopUpd:)
				(HandsOn)
				(ego normalize:)
				(self dispose:)
			)
		)
	)
)

(instance enterFirstTime of Script
	(properties)
	
	(method (doit)
		(if (ego script?) 0 else (super doit:))
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 0 (HandsOff) (= cycles 3))
			(1
				1
				(messager say: 1 6 1)
				(= cycles 1)
			)
			(2
				2
				((ScriptID 35 1) cycleSpeed: 10 setCycle: Beg self)
			)
			(3
				3
				((ScriptID 35 1) setCycle: End self)
			)
			(4
				4
				((ScriptID 35 1) stopUpd:)
				(laibonArm cycleSpeed: 8 setCycle: End self)
			)
			(5 (messager say:))
			(6
				6
				(DontMove)
				(theIconBar curIcon: (theIconBar at: 4))
				(theGame setCursor: ((theIconBar curIcon?) cursor?))
				(= ticks 600)
			)
			(7
				7
				(messager say: 2 6 2 0 self)
			)
			(8
				8
				(messager say: 4 6 3 0 self)
			)
			(9 9 (= ticks 960))
			(10
				10
				(ego cycleSpeed: 6)
				(HandsOff)
				(laibonArm setCycle: End self)
			)
			(11
				11
				(messager say: 2 6 4)
				(laibonArm setCycle: Beg self)
			)
			(12
				12
				(Bset 49)
				(cast eachElementDo: #dispose)
				(curRoom drawPic: 0 9)
				(= cycles 2)
			)
			(13
				13
				(messager say: 9 6 71 0 self)
			)
			(14 14 (curRoom newRoom: 440))
		)
	)
)

(instance laibonPissed of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if (curRoom script?) ((curRoom script?) dispose:))
				(laibonArm hide:)
				((ScriptID 42 1)
					view: 451
					loop: 2
					cel: 0
					setCycle: End self
				)
			)
			(1
				(ego
					solvePuzzle: 276 -5
					normalize:
					setMotion: PolyPath 290 190 self
				)
			)
			(2
				(HandsOff)
				(curRoom newRoom: 420)
			)
		)
	)
)

(instance wakeUpChuckSpear of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 10))
			(1
				(HandsOff)
				(ego
					code: 0
					changeGait: 1
					setMotion: PolyPath 143 123 self
				)
				((ScriptID 42 1)
					view: 453
					loop: 1
					cycleSpeed: 10
					setCycle: End
				)
			)
			(2
				(ego setMotion: PolyPath 120 124 self)
			)
			(3
				(globalSound number: 916 setLoop: 1 play:)
				((ScriptID 42 1)
					view: 453
					loop: 2
					cel: 0
					cycleSpeed: 3
					setCycle: CT 4 1 self
				)
			)
			(4
				((ScriptID 42 1) setCel: 5)
				(spear setStep: 22 2 setMotion: MoveTo 120 81 self init:)
			)
			(5
				(spear dispose:)
				(globalSound number: 920 setLoop: 1 play: self)
			)
			(6
				(globalSound number: 912 play:)
				(ego view: 43 loop: 1 setCycle: End self)
			)
			(7 (EgoDead register 0 455 End))
		)
	)
)

(instance enterNormal of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				((ScriptID 42 1)
					setCycle: Snooze self 1 10 200
					noun: 4
					init:
				)
				(ego
					x: 234
					y: 172
					setScale:
					init:
					setMotion: PolyPath 143 124 self
				)
			)
			(1
				(ego setHeading: 315 normalize: 7)
				(HandsOn)
				(if (== local11 7) (ego code: sneakCheck))
				(self dispose:)
			)
		)
	)
)

(instance checkOutChest of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: PolyPath 232 140 self)
			)
			(1
				(if (== local7 0)
					(messager say: 7 4 52)
					(HandsOn)
					(self dispose:)
				)
				(= cycles 1)
			)
			(2
				(if (== local5 0)
					(messager say: 7 4 54)
					(++ local6)
					(sFx number: 640 play:)
					(if (== local6 2)
						(trunkLid setCel: 1)
						(curRoom setScript: wakeUpChuckSpear 0 56)
					)
					(HandsOn)
					(self dispose:)
				)
				(= cycles 1)
			)
			(3
				(trunkLid setCel: 1)
				(= cycles 6)
			)
			(4
				(messager say: 7 4 53 0 self)
			)
			(5
				(ego get: 2)
				(= commons (+ commons 700))
				((inventory at: 0)
					amount: (+ ((inventory at: 0) amount?) 60)
				)
				((inventory at: 0) message: 10)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance hoarkDrum of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: PolyPath 114 122 self)
			)
			(1 (messager say: 5 4 0 0 self))
			(2
				(if (<= (ego trySkill: 8 125) 0)
					(curRoom setScript: wakeUpChuckSpear 0 34)
				else
					(if (Btst 50) (Bset 74))
					(ego get: 46)
					(Bset 163)
					(drum dispose:)
					(= dayStoleMagicDrum Day)
					(ego solvePuzzle: 275 8 4)
					(HandsOn)
					(self dispose:)
				)
			)
		)
	)
)

(instance egoGivesHorn of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: PolyPath 89 132 self)
			)
			(1
				(laibonArm hide:)
				((ScriptID 42 1) loop: 2 setCycle: End)
				(= seconds 3)
			)
			(2
				((ScriptID 42 1) setCycle: Beg self)
			)
			(3
				(ego drop: 22 1)
				(Bset 11)
				(ego solvePuzzle: 271 3 9)
				((ScriptID 42 1) loop: 0)
				(laibonArm show: setCycle: Snooze self 1 20 290)
				(= cycles 1)
			)
			(4
				(messager say: 4 6 27 0 self)
			)
			(5
				(messager say: 4 6 28 0 self)
			)
			(6
				(ego setMotion: PolyPath 290 220 self)
			)
			(7
				(cast eachElementDo: #dispose)
				(curRoom drawPic: 0 9)
				(= cycles 3)
			)
			(8
				((ScriptID 7 4) init: 6 0)
				(= [egoStats 18] (ego maxMana:))
				(= [egoStats 17] (ego maxStamina:))
				(= [egoStats 16] (ego maxHealth:))
				(messager say: 9 6 27 0 self)
			)
			(9 (curRoom newRoom: 420))
		)
	)
)

(instance getDrum of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(messager say: 3 5 register 0 self)
			)
			(1
				(if (not (ego has: 46)) (ego get: 46) (Bset 163))
				(curRoom newRoom: 420)
			)
		)
	)
)

(instance bridePrice of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(messager say: 4 5 40 0 self)
			)
			(1
				(if (and (== origHeroType 0) (not (Btst 16)))
					(messager say: 4 6 41 0 self)
				else
					(= cycles 1)
				)
			)
			(2 (self dispose:))
		)
	)
)

(instance moveEgoBack of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego
					setMotion: PolyPath (- (ego x?) 10) (- (ego y?) 20) self
				)
			)
			(1 (self dispose:))
		)
	)
)

(instance spear of Actor
	(properties
		x 163
		y 81
		view 453
		loop 3
		signal $0800
	)
)

(instance native of Prop
	(properties
		x 250
		y 140
		view 970
		loop 4
		cel 3
		signal $4000
	)
)

(instance fire of Prop
	(properties
		x 148
		y 140
		noun 11
		view 423
		cel 12
		signal $4000
	)
)

(instance hole of View
	(properties
		x 200
		y 64
		noun 12
		view 450
		signal $4000
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 4)
			(curRoom setScript: exitThief)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance drum of View
	(properties
		x 105
		y 74
		noun 5
		view 450
		cel 1
		signal $4000
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(if (== local11 7)
					(curRoom setScript: hoarkDrum)
				else
					(messager say: 4 6 12)
					((ScriptID 42 1) setScript: laibonPissed)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance trunkLid of Prop
	(properties
		x 277
		y 126
		noun 7
		view 450
		loop 1
		signal $4000
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(cond 
					((!= local11 7) (messager say: 7 4 73))
					((not (== cel 1)) (curRoom setScript: checkOutChest))
				)
			)
			(-75
				(cond 
					((!= local11 7) (messager say: 9 6 73))
					((not (== cel 1)) (curRoom setScript: castOpenOnChest))
				)
			)
			(17
				(if
				(and (> (ego trySkill: 9 75) 0) (== local7 0))
					(curRoom setScript: pickLock)
				else
					(messager say: 9 6 74)
				)
			)
			(35
				(curRoom setScript: egoOilsChest)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance laibonArm of Prop
	(properties
		x 59
		y 77
		view 451
		loop 1
		priority 7
		signal $0010
	)
)

(instance crack of Feature
	(properties
		x 216
		y 85
		noun 8
		nsTop 68
		nsLeft 206
		nsBottom 103
		nsRight 227
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(if (not (cast contains: hole))
					(curRoom doVerb: theVerb)
				else
					(super doVerb: theVerb)
				)
			)
			(75
				(curRoom setScript: castOpenOnCrack)
			)
			(4
				(if Night (hole init: stopUpd:))
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance leopardhide of Feature
	(properties
		x 226
		y 54
		noun 13
		nsTop 35
		nsLeft 193
		nsBottom 73
		nsRight 259
		sightAngle 180
	)
)

(instance shields of Feature
	(properties
		x 146
		y 66
		noun 14
		nsTop 33
		nsLeft 124
		nsBottom 99
		nsRight 169
		sightAngle 180
	)
)

(instance laibonTell of Teller
	(properties)
	
	(method (showDialog)
		(super
			showDialog:
				-20
				(if (> local11 0) (> local8 0) else 0)
				-59
				(== local11 8)
				-58
				(== local11 8)
				-12
				(if (and (< 0 local11) (< local11 4))
					(== local8 0)
				else
					0
				)
				-9
				(< local11 4)
				-39
				(if (< 3 local11) (< local11 6) else 0)
				-47
				(== global392 2)
				-49
				(== global392 2)
				-40
				(if (< 3 local11) (< local11 6) else 0)
				-25
				(== local11 3)
				-26
				(if (< 3 local11) (< local11 6) else 0)
				-42
				(if
				(and (== origHeroType 0) (< 3 local11) (< local11 6))
					(not (Btst 16))
				else
					0
				)
				-7
				(<= local11 1)
				-3
				(< local11 4)
				-38
				(if (< 3 local11) (< local11 6) else 0)
				-48
				(== global392 4)
				-65
				(Btst 74)
				-8
				(< local11 3)
		)
	)
	
	(method (doChild)
		(= local0 1)
		(return
			(switch query
				(-20
					(if (== local3 255)
						(messager say: 4 6 37)
						(curRoom setScript: egoExits)
						(return 0)
					else
						((ScriptID 42 1) setScript: laibonPissed)
					)
				)
				(-13
					(++ local2)
					(= local3 (| local3 $0020))
					(return 1)
				)
				(-12
					(++ local2)
					(= local3 (| local3 $0002))
					(++ local8)
					(ego solvePuzzle: 269 2)
					(super doChild: query)
				)
				(-9
					(++ local2)
					(= local3 (| local3 $0010))
					(return 1)
				)
				(-39 (++ local2) (return 1))
				(-15
					(++ local2)
					(= local3 (| local3 $0080))
					(return 1)
				)
				(-49
					(cond 
						((and (== origHeroType 0) (not (Btst 16))) (return (= query -30)))
						(
							(and
								(== origHeroType 0)
								(ego has: 21)
								(ego has: 3)
								(ego has: 40)
								(== global392 2)
								(Btst 16)
							)
							(ego setScript: egoBuysJohari)
							(return 0)
						)
						(
							(and
								(!= origHeroType 0)
								(ego has: 21)
								(ego has: 3)
								(ego has: 40)
								(== global392 2)
							)
							(ego setScript: egoBuysJohari)
						)
					)
				)
				(-14
					(++ local2)
					(= local3 (| local3 $0040))
					(return 1)
				)
				(-68
					(return query)
					(curRoom newRoom: 420)
				)
				(-40
					(++ local2)
					(ego setScript: bridePrice)
					(return 0)
				)
				(-26 (++ local2) (return 1))
				(-42 (++ local2) (return 1))
				(-7
					(= local3 (| local3 $0004))
					(return 1)
				)
				(-3
					(++ local2)
					(= local3 (| local3 $0001))
					(ego solvePuzzle: 270 2)
					(return query)
				)
				(-38 (++ local2) (return 1))
				(-8
					(= local3 (| local3 $0008))
					(return 1)
				)
				(else 
					(++ local10)
					(return query)
				)
			)
		)
	)
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(32
					(if (== local11 5)
						(curRoom setScript: egoGivesHorn)
					else
						(messager say: 9 6 75)
					)
					(return 1)
				)
				(31
					(cond 
						(
							(and
								(== global392 2)
								(ego has: 3)
								(ego has: 40)
								(!= origHeroType 0)
							)
							(curRoom setScript: egoBuysJohari)
						)
						(
							(and
								(== global392 2)
								(ego has: 3)
								(ego has: 40)
								(Btst 16)
								(== origHeroType 0)
							)
							(curRoom setScript: egoBuysJohari)
						)
						(
							(and
								(== global392 2)
								(ego has: 3)
								(ego has: 40)
								(not (Btst 16))
								(== origHeroType 0)
							)
							(messager say: 4 6 30)
						)
						(else (messager say: 4 6 77))
					)
					(return 1)
				)
				(51
					(cond 
						(
							(and
								(== global392 2)
								(ego has: 3)
								(ego has: 21)
								(!= origHeroType 0)
							)
							(curRoom setScript: egoBuysJohari)
						)
						(
							(and
								(== global392 2)
								(ego has: 3)
								(ego has: 21)
								(Btst 16)
								(== origHeroType 0)
							)
							(curRoom setScript: egoBuysJohari)
						)
						(
							(and
								(== global392 2)
								(ego has: 3)
								(ego has: 21)
								(not (Btst 16))
								(== origHeroType 0)
							)
							(messager say: 4 6 30)
						)
						(else (messager say: 4 6 77))
					)
					(return 1)
				)
				(13
					(cond 
						(
							(and
								(== global392 2)
								(ego has: 40)
								(ego has: 21)
								(!= origHeroType 0)
							)
							(curRoom setScript: egoBuysJohari)
						)
						(
							(and
								(== global392 2)
								(ego has: 40)
								(ego has: 21)
								(Btst 16)
								(== origHeroType 0)
							)
							(curRoom setScript: egoBuysJohari)
						)
						(
							(and
								(== global392 2)
								(ego has: 40)
								(ego has: 21)
								(not (Btst 16))
								(== origHeroType 0)
							)
							(messager say: 4 6 30)
						)
						(else (messager say: 4 6 77))
					)
					(return 1)
				)
				(4
					(if Night
						(curRoom setScript: wakeUpChuckSpear 0 55)
					else
						(super doVerb: theVerb)
					)
				)
				(else  (super doVerb: theVerb))
			)
		)
	)
)

(instance egoTell of Teller
	(properties)
	
	(method (showDialog)
		(asm
			pushi    652
			pushi    26
			pushi    65491
			lsl      local11
			ldi      5
			eq?     
			push    
			pushi    65535
			lal      local12
			not     
			bnt      code_2421
			lsl      local11
			ldi      0
			eq?     
			bt       code_2421
			lsl      local11
			ldi      5
			eq?     
code_2421:
			push    
			pushi    65518
			lal      local12
			not     
			bnt      code_2443
			lal      local9
			not     
			bnt      code_2443
			pushi    0
			lal      local11
			lt?     
			bnt      code_243e
			pprev   
			ldi      5
			lt?     
			bt       code_2443
code_243e:
			lsl      local11
			ldi      6
			eq?     
code_2443:
			push    
			pushi    65517
			lal      local12
			not     
			bnt      code_244d
			lal      local9
code_244d:
			push    
			pushi    65525
			lsl      local11
			ldi      3
			lt?     
			push    
			pushi    65531
			lsl      local11
			ldi      3
			lt?     
			push    
			pushi    65526
			lsl      local11
			ldi      0
			eq?     
			push    
			pushi    65514
			pushi    0
			lal      local11
			lt?     
			bnt      code_2473
			pprev   
			ldi      3
			lt?     
code_2473:
			push    
			pushi    65492
			pushi    2
			lal      local11
			lt?     
			bnt      code_2481
			pprev   
			ldi      5
			lt?     
code_2481:
			push    
			pushi    65469
			lsl      local11
			ldi      8
			eq?     
			bnt      code_248f
			lag      global406
code_248f:
			push    
			pushi    65468
			lsl      local11
			ldi      8
			eq?     
			bnt      code_249d
			lag      global406
code_249d:
			push    
			pushi    65470
			lsl      local11
			ldi      8
			eq?     
			bnt      code_24ae
			lag      global406
			bt       code_24b4
code_24ae:
			pushi    1
			pushi    30
			callb    Btst,  2
code_24b4:
			push    
			pushi    26
			lsl      local11
			ldi      4
			eq?     
			push    
			super    Teller,  56
			ret     
		)
	)
	
	(method (doChild)
		(= local0 1)
		(return
			(switch query
				(-45
					(curRoom setScript: 0)
					(curRoom setScript: egoGivesHorn)
					(return query)
				)
				(-10
					(ego addHonor: -10)
					(return 1)
				)
				(-22
					(ego addHonor: 2)
					(curRoom setScript: egoExits)
					(return 1)
				)
				(-44
					(if (== local11 4) (= local2 4) else (= local2 5))
					(ego addHonor: 2)
					(messager say: 3 5 44)
					(ego setScript: egoExits)
					(return 0)
				)
				(-1
					(ego setScript: egoBows 0 query)
					(= local12 0)
					(++ local10)
					(return 0)
				)
				(-18
					(ego setScript: egoBows 0 query)
					(= local12 0)
					(++ local10)
					(return 0)
				)
				(-19
					(ego setScript: egoBows 0 query)
					(= local12 0)
					(++ local10)
					(return 0)
				)
				(-11 (++ local10))
				(-5
					(ego addHonor: 20)
					(++ local10)
				)
				(-67
					(ego setScript: getDrum 0 (- 0 query))
					(return 0)
				)
				(-68
					(ego setScript: getDrum 0 (- 0 query))
					(return 0)
				)
				(else 
					(++ local10)
					(return query)
				)
			)
		)
	)
)

(instance rakeeshTell of Teller
	(properties)
)

(instance uhuraTell of Teller
	(properties)
)

(instance yesufuTell of Teller
	(properties)
)

(instance sFx of Sound
	(properties)
)

(instance Snooze of OccasionalCycle
	(properties)
	
	(method (cycleDone)
		(= cycleDir (- 0 cycleDir))
		(= waitCycles (Random lowerEnd upperEnd))
		(= cycleCnt (GetTime))
	)
)

(instance sneakCheck of Code
	(properties)
	
	(method (doit)
		(if (!= (ego view?) 2)
			(curRoom setScript: wakeUpChuckSpear 0 36)
			(ego code: 0)
		)
		(if (ego inRect: 0 175 320 190)
			(cond 
				((and (not (ego script?)) local4) (EgoDead 76))
				((not (ego script?))
					(messager say: 9 6 33)
					(= local4 1)
					(ego setScript: moveEgoBack)
				)
			)
		)
	)
)

(instance outCheck of Code
	(properties)
	
	(method (doit)
		(if (ego inRect: 200 175 250 190)
			(curRoom newRoom: 420)
		)
	)
)
