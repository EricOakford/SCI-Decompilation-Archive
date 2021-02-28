;;; Sierra Script 1.0 - (do not remove this comment)
(script# 420)
(include sci.sh)
(use Main)
(use TellerIcon)
(use Inset)
(use PAvoid)
(use PolyPath)
(use Polygon)
(use Block)
(use Feature)
(use LoadMany)
(use StopWalk)
(use Reverse)
(use Grooper)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm420 0
)

(local
	[local0 3] = [0 -1 999]
	[local3 5]
	local8
)
(procedure (localproc_1e03)
	(crowd
		x: 92
		y: 189
		loop: 3
		ignoreActors: 1
		init:
		addToPic:
	)
	((ScriptID 42 1)
		view: 454
		loop: 1
		x: 160
		y: 117
		setScale: 350
		ignoreActors: 1
		init:
	)
	((ScriptID 34 1)
		view: 971
		loop: 4
		cel: 6
		x: 28
		y: 133
		setScale: 350
		init:
		addToPic:
	)
	(simba
		loop: 4
		cel: 0
		x: 21
		y: 135
		setScale: 350
		init:
		addToPic:
	)
)

(instance rm420 of Rm
	(properties
		noun 3
		picture 420
		vanishingY -60
	)
	
	(method (init)
		(HandsOff)
		(= [local3 0] @local0)
		(= [local3 1] 999)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: 2
					init: 140 161 100 160 78 150 80 137 98 133 121 131 143 133 155 141 155 153
					yourself:
				)
				((Polygon new:)
					type: 2
					init: 319 120 269 103 272 89 262 88 262 103 238 103 243 83 267 74 319 74
					yourself:
				)
				((Polygon new:)
					type: 2
					init:
						145
						68
						146
						84
						97
						84
						94
						76
						98
						64
						211
						65
						219
						70
						226
						82
						200
						83
						167
						83
						167
						68
					yourself:
				)
				((Polygon new:)
					type: 2
					init: 35 102 0 96 0 82 68 76 99 91 93 104 73 104 79 84 72 82 71 98
					yourself:
				)
				((Polygon new:)
					type: 2
					init:
						223
						64
						212
						63
						200
						58
						100
						58
						49
						60
						0
						63
						0
						0
						319
						0
						319
						60
						271
						58
						267
						64
						254
						65
						239
						64
						230
						71
					yourself:
				)
		)
		(guard
			setScale:
			scaleX: 44
			scaleY: 44
			maxScale: 78
			loop: 8
			cel: 2
			stopUpd:
			approachVerbs: 2
			init:
		)
		(egoActions init: ego @local0 @local3)
		(ego
			actions: egoActions
			normalize:
			setScale: 350
			noun: 1
			init:
		)
		(chiefHut
			approachVerbs: (if (== origHeroType 2) 1 else 0)
			init:
		)
		(if Night (fire setCycle: Fwd init:))
		(campfire init:)
		(tree init:)
		(fence init:)
		(mountain init:)
		(uhuraHutLook init:)
		(guestHut init:)
		(guestHutLook init:)
		(leaderDoor approachVerbs: 1 init:)
		(cond 
			(
				(and
					(>= Day (+ gCurrentDay_5 3))
					(not (Btst 29))
					(!= global366 3)
					(not (Btst 74))
				)
				(= global366 2)
			)
			(
			(and (Btst 29) (not (Btst 38)) (!= global366 5)) (= global366 4))
			(
				(and
					(Btst 38)
					(not (Btst 11))
					(not (ego has: 22))
					(== origHeroType 0)
					(!= global366 7)
				)
				(= global366 6)
			)
			(
				(and
					(== origHeroType 0)
					(not (Btst 11))
					(ego has: 22)
					(!= global366 9)
				)
				(= global366 8)
			)
			(
				(and
					(Btst 38)
					(not (Btst 65))
					(== global392 0)
					(!= global366 11)
				)
				(= global366 10)
			)
			(
				(and
					(== global392 1)
					(ego has: 3)
					(ego has: 40)
					(ego has: 21)
					(> ((inventory at: 21) amount?) 4)
					(!= global366 13)
				)
				(= global366 12)
			)
		)
		(if (not (Btst 87)) (curRoom style: 9))
		(super init:)
		(if (!= (cSound number?) 160) (cSound changeTo: 160))
		(cond 
			((not (Btst 87)) (curRoom setScript: enterFirstTime))
			((or (== prevRoomNum 620) (== prevRoomNum 630)) (curRoom setScript: returnSpear))
			(
			(and (== prevRoomNum 470) (Btst 16) (not (Btst 154))) (curRoom setScript: afterMatch))
			((== prevRoomNum 500) (curRoom setScript: afterContest))
			(
				(and
					(or (== heroType 0) (== heroType 3))
					(Btst 11)
					(not (Btst 56))
				)
				(curRoom setScript: startContest)
			)
			(
				(and
					(>= Day 5)
					(or (== timeODay 5) (== timeODay 4))
					(not (Btst 9))
				)
				(curRoom setScript: enterStoryTell)
			)
			((== prevRoomNum 440) (curRoom setScript: enterFromUHut))
			(
				(and
					(== prevRoomNum 450)
					(== origHeroType 2)
					(== Day dayStoleMagicDrum)
					Night
				)
				(curRoom setScript: enterThief)
			)
			((== prevRoomNum 450) (curRoom setScript: enterFromLHut))
			((== prevRoomNum 430) (curRoom setScript: enterFromEHut))
			((== prevRoomNum 460) (curRoom setScript: enterFromSpRoom))
			((== prevRoomNum 470) (curRoom setScript: enterFromWrRoom))
			((== prevRoomNum 480) (curRoom setScript: enterFromPrisCage))
			(else (curRoom setScript: enterRoom))
		)
	)
	
	(method (doit)
		(cond 
			(script 0)
			((== (ego onControl: 1) 8)
				(if (and Night (< timeODay 6))
					(curRoom setScript: enterUhHut)
				else
					(curRoom setScript: cantEnterUhHut)
				)
			)
			((guestHut onMe: ego) (curRoom newRoom: 430))
			((== (ego onControl: 1) 4)
				(if
					(and
						(not Night)
						(>= Day (+ gCurrentDay_5 3))
						(not (& global366 $0001))
					)
					(curRoom setScript: enterLeader)
				else
					(curRoom setScript: cantEnter)
				)
			)
			((and (<= (ego x?) 90) (< (ego y?) 70)) (curRoom setScript: exitWest))
			((< (ego x?) 10) (curRoom setScript: exitWest))
			((>= (ego x?) 310) (curRoom setScript: exitEast))
			((>= (ego y?) 180) (curRoom setScript: exitSouth))
			(
				(and
					(< 90 (ego x?))
					(< (ego x?) 240)
					(< (ego y?) 70)
				)
				(curRoom newRoom: 480)
			)
			((and (> (ego x?) 239) (< (ego y?) 80)) (curRoom newRoom: 470))
			((and (< (ego x?) 78) (< (ego y?) 79)) (curRoom newRoom: 460))
		)
		(super doit:)
	)
	
	(method (dispose)
		(LoadMany 0 970 949 34 35 39 42 53 923)
		(super dispose:)
	)
	
	(method (setInset param1 param2 param3)
		(if inset (inset dispose:))
		(if (and argc param1)
			(param1
				init:
					(if (>= argc 2) param2 else 0)
					self
					(if (>= argc 3) param3 else 0)
					&rest
			)
		)
	)
)

(instance egoActions of Teller
	(properties)
	
	(method (doChild)
		(if (== query -1)
			(cond 
				((ego inRect: 108 79 218 90) (ego addHonor: 3) (= query -2))
				((ego inRect: 250 104 280 116)
					(if
					(and (> Day gCurrentDay_5) Night (< timeODay 6))
						(= local8 1)
						(ego addHonor: 10)
						(= query -6)
					else
						(= query -7)
					)
				)
				(else (= query -3))
			)
		)
		(return 1)
	)
)

(instance enterThief of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego x: 221 y: 75 setMotion: PolyPath 230 90 self)
			)
			(1 (HandsOn) (self dispose:))
		)
	)
)

(instance goTo620 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (== heroType 1)
					(messager say: 18 6 42 0 self)
				else
					(self cue:)
				)
			)
			(1
				(cast eachElementDo: #dispose eachElementDo: #delete)
				(DrawPic 0 dpOPEN_HCENTER)
				(= cycles 3)
			)
			(2
				(messager say: 11 6 32 0 self)
			)
			(3 (curRoom newRoom: 620))
		)
	)
)

(instance goTo350 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(cast eachElementDo: #dispose eachElementDo: #delete)
				(DrawPic 0 dpOPEN_HCENTER)
				(= cycles 3)
			)
			(1
				(messager say: 11 6 31 0 self)
			)
			(2
				(= [egoStats 16] (ego maxHealth:))
				(= [egoStats 17] (ego maxStamina:))
				(= [egoStats 18] (ego maxMana:))
				(curRoom newRoom: 350)
			)
		)
	)
)

(instance goTo280 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(cast eachElementDo: #dispose eachElementDo: #delete)
				(DrawPic 0 dpOPEN_HCENTER)
				(= cycles 3)
			)
			(1
				(messager say: 11 6 30 0 self)
			)
			(2
				(= [egoStats 16] (ego maxHealth:))
				(= [egoStats 17] (ego maxStamina:))
				(= [egoStats 18] (ego maxMana:))
				(curRoom newRoom: 280)
			)
		)
	)
)

(instance goTo340 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(cast eachElementDo: #dispose eachElementDo: #delete)
				(DrawPic 0 dpOPEN_HCENTER)
				(= cycles 3)
			)
			(1
				(messager say: 11 6 29 0 self)
			)
			(2
				(= [egoStats 16] (ego maxHealth:))
				(= [egoStats 17] (ego maxStamina:))
				(= [egoStats 18] (ego maxMana:))
				(curRoom newRoom: 340)
			)
		)
	)
)

(instance enterUhHut of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: PolyPath 267 103 self)
			)
			(1 (curRoom newRoom: 440))
		)
	)
)

(instance enterFromPrisCage of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego x: 215 y: 68 setMotion: PolyPath 225 75 self)
			)
			(1 (HandsOn) (self dispose:))
		)
	)
)

(instance cantEnterUhHut of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(messager say: 11 6 41 0 self)
			)
			(1
				(ego
					setMotion: PolyPath (- (ego x?) 5) (+ (ego y?) 5) self
				)
			)
			(2 (HandsOn) (self dispose:))
		)
	)
)

(instance cantEnter of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: 0)
				(messager say: 11 6 34 0 self)
			)
			(1
				(ego setMotion: PolyPath (ego x?) (+ (ego y?) 10) self)
			)
			(2 (HandsOn) (self dispose:))
		)
	)
)

(instance enterLeader of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: 0)
				(++ global366)
				(messager say: 11 6 33 0 self)
			)
			(1 (curRoom newRoom: 450))
		)
	)
)

(instance exitWest of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: MoveTo (- (ego x?) 15) (ego y?) self)
			)
			(1 (curRoom newRoom: 460))
		)
	)
)

(instance exitEast of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: MoveTo (+ (ego x?) 15) (ego y?) self)
			)
			(1 (curRoom newRoom: 470))
		)
	)
)

(instance exitSouth of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: MoveTo (ego x?) (+ (ego y?) 15) self)
			)
			(1 (curRoom newRoom: 410))
		)
	)
)

(instance enterRoom of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego x: 190 y: 210 setMotion: PolyPath 190 170 self)
			)
			(1 (HandsOn) (self dispose:))
		)
	)
)

(instance enterFromUHut of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego
					x: 265
					y: 105
					normalize:
					setMotion: MoveTo 270 115 self
				)
			)
			(1 (HandsOn) (self dispose:))
		)
	)
)

(instance enterFromLHut of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if (Btst 125)
					(Bclr 125)
					(self setScript: enterFromPrisCage self)
				else
					(ego
						x: 154
						y: 78
						normalize:
						setMotion: MoveTo 154 90 self
					)
				)
			)
			(1 (HandsOn) (self dispose:))
		)
	)
)

(instance enterFromEHut of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego x: 74 y: 99 normalize: setMotion: MoveTo 60 110 self)
			)
			(1 (HandsOn) (self dispose:))
		)
	)
)

(instance enterFromWrRoom of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego
					x: 280
					y: 74
					normalize:
					setMotion: PolyPath 240 84 self
				)
			)
			(1 (HandsOn) (self dispose:))
		)
	)
)

(instance enterFromSpRoom of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego x: 65 y: 74 normalize: setMotion: MoveTo 85 82 self)
			)
			(1 (HandsOn) (self dispose:))
		)
	)
)

(instance enterFirstTime of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Bset 87)
				(= gCurrentDay_5 Day)
				((ScriptID 34 1)
					view: 969
					loop: 0
					cel: 0
					x: 144
					y: 133
					setScale: 300
					ignoreActors: 1
					init:
				)
				(simba x: 158 y: 135 setScale: 300 ignoreActors: 1 init:)
				(= gGOwnerMoveSpeed (ego moveSpeed?))
				(ego
					normalize:
					x: 215
					y: 220
					setScale: 300
					setSpeed: 6
					setMotion: MoveTo 173 145 self
					ignoreActors: 1
				)
				((ScriptID 35 1)
					view: 967
					setLoop: 3
					x: 254
					y: 220
					setCycle: Walk
					setScale: 300
					setLoop: lionGroop
					ignoreActors: 1
					setMotion: PolyPath 195 138 self
					init:
				)
			)
			(1 0)
			(2
				((ScriptID 35 1) view: 962 setLoop: 1)
				(= ticks 90)
			)
			(3 (messager say: 8 6 8 0 self))
			(4
				(ego setSpeed: gGOwnerMoveSpeed)
				((ScriptID 34 1)
					view: 971
					setCycle: Walk
					setAvoider: PAvoider
					setMotion: PolyPath 144 115 self
				)
			)
			(5 (curRoom newRoom: 450))
		)
	)
)

(instance enterStoryTell of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(cSound changeTo: 420 hold:)
				(curRoom
					addObstacle:
						((Polygon new:)
							type: 2
							init: 32 189 72 128 122 109 217 109 236 122 235 189
							yourself:
						)
				)
				(crowd init: addToPic:)
				((ScriptID 53 1)
					x: 85
					y: 136
					setScale:
					scaleX: 66
					scaleY: 66
					init:
				)
				((ScriptID 34 1)
					view: 971
					loop: 4
					cel: 1
					x: 160
					y: 119
					setScale:
					scaleX: 61
					scaleY: 61
					init:
					addToPic:
				)
				(simba
					loop: 4
					cel: 3
					x: 141
					y: 120
					setScale:
					scaleX: 64
					scaleY: 64
					init:
					addToPic:
				)
				(extra setScale: scaleX: 66 scaleY: 66 init: addToPic:)
				(extra2 setScale: scaleX: 66 scaleY: 66 init: addToPic:)
				(fire setCycle: Fwd init:)
				(switch prevRoomNum
					(410 (ego x: 240 y: 210))
					(430 (ego x: 74 y: 99))
					(440 (ego x: 265 y: 105))
					(450 (ego x: 154 y: 75))
					(460 (ego x: -5 y: 160))
					(470 (ego x: 320 y: 160))
					(480 (ego x: 215 y: 68))
					(else  (ego x: 240 y: 210))
				)
				(ego
					setScale: 290
					setAvoider: PAvoider
					setMotion: PolyPath 240 140 self
				)
			)
			(1
				(Face ego (ScriptID 53 1))
				(= cycles 15)
			)
			(2
				(Bset 9)
				(messager say: 9 6 9 0 self)
			)
			(3
				((ScriptID 53 1) setCycle: End self)
			)
			(4
				(messager say: 9 6 11 0 self)
			)
			(5
				((ScriptID 53 1) cel: 0 loop: 1 setCycle: End self)
			)
			(6
				(messager say: 9 6 12 0 self)
			)
			(7
				((ScriptID 53 1) cel: 0 loop: 0 setCycle: End self)
			)
			(8
				((ScriptID 53 1) cel: 0 loop: 1 setCycle: End self)
			)
			(9
				(messager say: 9 6 13 0 self)
			)
			(10
				((ScriptID 53 1) cel: 0 loop: 1 setCycle: End self)
			)
			(11
				(messager say: 9 6 14 0 self)
			)
			(12
				((ScriptID 53 1) cel: 0 loop: 2 setCycle: End self)
			)
			(13
				(messager say: 9 6 15 0 self)
			)
			(14
				(ego solvePuzzle: 262 2)
				(Bset 118)
				(curRoom newRoom: 430)
			)
		)
	)
)

(instance startContest of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(cSound changeTo: 420 hold:)
				(localproc_1e03)
				(ego
					changeGait: 1
					ignoreActors: 1
					setCycle: StopWalk 5
					x: 187
					y: 117
				)
				((ScriptID 39 1)
					view: 983
					heading: 180
					loop: 2
					cel: 4
					x: 208
					y: 117
					setScale: 350
					init:
				)
				(= cycles 1)
			)
			(1
				(ego setHeading: 180)
				(= cycles 10)
			)
			(2
				(messager say: 10 6 16 0 self)
			)
			(3
				(ego setMotion: PolyPath 187 220 self)
				((ScriptID 39 1)
					origStep: 2053
					setCycle: StopWalk -1
					setMotion: PolyPath 208 220
				)
			)
			(4 (curRoom newRoom: 500))
		)
	)
)

(instance afterContest of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(cSound changeTo: 420 hold:)
				(Bset 56)
				(localproc_1e03)
				(= gGOwnerMoveSpeed (ego moveSpeed?))
				(ego
					view: 1
					changeGait: 1
					setCycle: StopWalk 5
					setSpeed: 6
					x: 190
					y: 220
					ignoreActors: 1
					setHeading: 0
					setMotion: PolyPath 190 130 self
				)
				((ScriptID 39 1)
					view: 983
					x: 220
					y: 220
					origStep: 2053
					setScale: 350
					setCycle: StopWalk -1
					init:
					setMotion: PolyPath 220 130
				)
			)
			(1 (= cycles 15))
			(2
				(messager say: 10 6 17 0 self)
			)
			(3
				(if global406
					(ego
						setMotion: PolyPath 177 117 self
						solvePuzzle: 264 10 9
					)
				else
					(ego solvePuzzle: 263 5 9)
					((ScriptID 39 1) setMotion: PolyPath 177 117 self)
				)
			)
			(4
				(if global406
					(ego setHeading: 270)
				else
					((ScriptID 39 1) setHeading: 270)
				)
				(= cycles 15)
			)
			(5
				(messager say: 10 6 18 0 self)
			)
			(6
				(if global406
					(ego view: 13 loop: 0 cel: 0 setCycle: End self)
				else
					((ScriptID 39 1)
						view: 427
						loop: 0
						cel: 0
						setCycle: End self
					)
				)
			)
			(7
				(ego setSpeed: gGOwnerMoveSpeed)
				(messager say: 10 6 19 0 self)
			)
			(8 (curRoom newRoom: 460))
		)
	)
)

(instance afterMatch of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(cSound changeTo: 420 hold:)
				(Bset 154)
				(localproc_1e03)
				(ego setHeading: 180 x: 152 y: 114)
				((ScriptID 39 1)
					setHeading: 180
					setScale: 350
					x: 194
					y: 114
					init:
				)
				((ScriptID 42 1)
					setLoop: (if global406 3 else 2)
					x: 171
					y: 118
				)
				(= cycles 1)
			)
			(1 (= cycles 15))
			(2
				(messager say: 10 6 20 0 self)
			)
			(3
				(messager say: 10 6 22 0 self)
			)
			(4
				(if global406
					(messager say: 10 6 23 0 self)
				else
					(messager say: 10 6 24 0 self)
				)
			)
			(5
				((ScriptID 42 1) cycleSpeed: 6 setCycle: End self)
			)
			(6
				(if global406
					(ego view: 13 setCycle: End self)
				else
					((ScriptID 39 1) view: 427 setCycle: End self)
				)
			)
			(7 (curRoom newRoom: 450))
		)
	)
)

(instance returnSpear of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(cSound changeTo: 420 hold:)
				(curRoom vanishingY: -80)
				(ego solvePuzzle: 265 20 addHonor: 50 drop: 45)
				(crowd loop: 2 x: 146 y: 189 init: addToPic:)
				((ScriptID 42 1)
					view: 454
					loop: 1
					cel: 0
					setScale: 300
					x: 22
					y: 142
					ignoreActors: 1
					init:
				)
				((ScriptID 34 1)
					view: 970
					loop: 4
					cel: 1
					x: 67
					y: 132
					setScale: 300
					init:
				)
				((ScriptID 35 1)
					view: 432
					loop: 2
					cel: 0
					setScale: 300
					x: 165
					y: 119
					init:
					addToPic:
				)
				(simba x: 141 y: 120 setScale: 300 init: addToPic:)
				((ScriptID 53 1)
					loop: 3
					cel: 0
					setScale: 300
					x: 130
					y: 117
					init:
					addToPic:
				)
				(ego
					changeGait: 0
					setCycle: StopWalk 5
					setHeading: 0
					setScale: 300
					x: 46
					y: 168
				)
				((ScriptID 39 1)
					view: 989
					loop: 0
					cel: 0
					x: 9
					y: 165
					setScale: 300
					ignoreActors: 1
					init:
				)
				(extra setScale: scaleX: 69 scaleY: 69 init: addToPic:)
				(extra2 setScale: scaleX: 69 scaleY: 69 init: addToPic:)
				(= cycles 1)
			)
			(1 (= ticks 180))
			(2
				(messager say: 10 6 26 0 self)
			)
			(3
				(ego setMotion: PolyPath 52 139 self)
			)
			(4
				(ego view: 32 loop: 1 cel: 0 setCycle: End self)
			)
			(5
				(ego
					view: 0
					setCycle: StopWalk 5
					setMotion: MoveTo 30 142 self
				)
			)
			(6 (= ticks 30))
			(7
				(ego
					setLoop: 1
					setCycle: Rev
					setMotion: MoveTo 52 142 self
				)
			)
			(8
				(ego view: 5 setCycle: 0 setLoop: 1)
				((ScriptID 42 1) loop: 5 setCycle: End self)
			)
			(9 (= ticks 30))
			(10
				(messager say: 10 6 27 0 self)
			)
			(11
				(if
					(or
						(== heroType 1)
						(and (not (Btst 74)) (== heroType 2))
					)
					(ego get: 46)
					(messager say: 10 6 28 0 self)
				else
					(= cycles 1)
				)
			)
			(12 (= ticks 60))
			(13
				(cond 
					((not (Btst 13)) (curRoom setScript: goTo620))
					(
						(and
							(== heroType 0)
							(>= [egoStats 14] 150)
							(not (Btst 19))
						)
						(client setScript: goTo280)
					)
					((ego has: 36) (curRoom setScript: goTo350))
					(else (client setScript: goTo340))
				)
			)
		)
	)
)

(instance showHole of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(globalSound number: 926 setLoop: 1 play: 127)
				(messager say: 12 12 40 0 self)
			)
			(1
				((hutHole insetView?) setCel: 1)
				(messager say: 11 6 38 0 self)
			)
			(2 (self dispose:))
		)
	)
)

(instance campfire of Feature
	(properties
		x 116
		y 145
		noun 13
		nsTop 134
		nsLeft 88
		nsBottom 157
		nsRight 145
		sightAngle 180
	)
)

(instance tree of Feature
	(properties
		x 235
		y 33
		noun 14
		nsTop 16
		nsLeft 202
		nsBottom 51
		nsRight 268
		sightAngle 180
	)
)

(instance fence of Feature
	(properties
		x 159
		y 52
		noun 15
		nsTop 47
		nsBottom 58
		nsRight 319
		sightAngle 180
	)
)

(instance mountain of Feature
	(properties
		x 159
		y 31
		noun 16
		nsTop 26
		nsBottom 37
		nsRight 319
		sightAngle 180
	)
)

(instance guestHut of Feature
	(properties
		x 72
		y 92
		nsTop 81
		nsLeft 68
		nsBottom 103
		nsRight 77
		sightAngle 40
		approachX 58
		approachY 106
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 1)
			(guestHutLook doVerb: 1 &rest)
		else
			(super doVerb: theVerb &rest)
		)
	)
)

(instance leaderDoor of Feature
	(properties
		x 160
		y 54
		sightAngle 40
		onMeCheck $0004
		approachX 120
		approachY 84
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 1)
			(chiefHut doVerb: 1 &rest)
		else
			(super doVerb: theVerb &rest)
		)
	)
)

(instance chiefHut of Feature
	(properties
		x 162
		y 68
		noun 5
		nsTop 54
		nsLeft 107
		nsBottom 82
		nsRight 218
		sightAngle 40
		approachX 225
		approachY 81
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(cond 
					((or (curRoom script?) (!= origHeroType 2)) (super doVerb: 1 &rest))
					((Btst 50) (messager say: 5 1 37))
					(else (messager say: 5 1 35 0) (curRoom setInset: hutHole))
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance uhuraHutLook of Feature
	(properties
		x 283
		y 92
		noun 6
		nsTop 78
		nsLeft 248
		nsBottom 107
		nsRight 318
		sightAngle 40
		approachX 282
		approachY 111
	)
)

(instance guestHutLook of Feature
	(properties
		x 47
		y 87
		noun 4
		nsTop 74
		nsLeft 2
		nsBottom 100
		nsRight 93
		sightAngle 40
		approachX 58
		approachY 106
	)
)

(instance crowd of View
	(properties
		x 146
		y 190
		view 420
		loop 1
		signal $4000
	)
)

(instance fire of Prop
	(properties
		x 117
		y 147
		noun 13
		view 420
		priority 11
		signal $0010
	)
)

(instance simba of Actor
	(properties
		view 425
	)
)

(instance guard of Actor
	(properties
		x 140
		y 85
		noun 2
		view 482
		signal $4000
	)
)

(instance extra of Actor
	(properties
		x 191
		y 122
		view 207
		loop 5
		cel 3
	)
)

(instance extra2 of Actor
	(properties
		x 209
		y 132
		view 205
		loop 5
		cel 1
	)
)

(instance simbaCage of Cage
	(properties
		top 140
		left 240
		bottom 170
		right 280
	)
)

(instance hutHole of Inset
	(properties
		view 420
		loop 4
		x 200
		y 85
		disposeNotOnMe 1
		noun 17
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(20
				(if (not Night)
					(messager say: 12 12 39)
				else
					(self setScript: showHole)
				)
			)
			(12 (self doVerb: 20))
			(3
				(if (== ((self insetView?) cel?) 1)
					(Bset 50)
					(if (not (Btst 266)) (ego addHonor: -50))
					(ego solvePuzzle: 266 8 4)
					(self dispose:)
					(curRoom newRoom: 450)
				else
					(super doVerb: theVerb &rest)
				)
			)
			(1
				(if (== ((self insetView?) cel?) 1)
					(messager say: 17 1 38)
				else
					(messager say: 17 1 36)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance lionGroop of Grooper
	(properties)
	
	(method (doit)
		(cond 
			((not ((ScriptID 35 1) mover?)) ((ScriptID 35 1) view: 962))
			(
				(and
					(< 15 ((ScriptID 35 1) heading?))
					(< ((ScriptID 35 1) heading?) 75)
				)
				((ScriptID 35 1) view: 967)
			)
			(
				(and
					(< 105 ((ScriptID 35 1) heading?))
					(< ((ScriptID 35 1) heading?) 165)
				)
				((ScriptID 35 1) view: 967)
			)
			(
				(and
					(< 195 ((ScriptID 35 1) heading?))
					(< ((ScriptID 35 1) heading?) 255)
				)
				((ScriptID 35 1) view: 967)
			)
			(
				(and
					(< 285 ((ScriptID 35 1) heading?))
					(< ((ScriptID 35 1) heading?) 345)
				)
				((ScriptID 35 1) view: 967)
			)
		)
		(super doit: &rest)
	)
)
