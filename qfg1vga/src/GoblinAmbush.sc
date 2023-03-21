;;; Sierra Script 1.0 - (do not remove this comment)
(script# 45)
(include game.sh) (include "45.shm")
(use Main)
(use CastDart)
(use ThrowKnife)
(use Target)
(use Procs)
(use PAvoid)
(use PolyPath)
(use Polygon)
(use LoadMany)
(use Timer)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm45 0
)

(local
	local0
	approachingBush
	local2
	[movingBushPtsBlue 8]
	movingBushPts = [
		164 22
		164 34
		117 34
		117 23
		0 32
		0 0
		0 32
		83 114
		101 140
		59 221
		69 248
		59 300
		67 173
		71 251
		81 239
		85 300
		99 256
		141 300
		151 203
		153 292
		165 64
		136 157
		143
		]
	local61
	local62
	local63
	local64
	[goblinScript 10]
	i
	local76 = [10 100 150 200 225 400 625 750 850 950 1050 2 2 2 2 2 2 2 2 2 2]
	lootedGoblin1
	lootedGoblin2
	lootedGoblin3
	lootedGoblin4
	lootedGoblin5
	lootedGoblin6
	lootedGoblin7
	lootedGoblin8
	cueGoblinSearch
)
(procedure (localproc_0152 &tmp temp0 temp1 temp2 temp3)
	(= temp0 (+ (movingBush x?) 25))
	(= temp1 (- (movingBush x?) 25))
	(= temp2 (- (movingBush y?) 9))
	(= temp3 (+ (movingBush y?) 6))
	(= [movingBushPtsBlue 0] temp0)
	(= [movingBushPtsBlue 1] temp2)
	(= [movingBushPtsBlue 2] temp0)
	(= [movingBushPtsBlue 3] temp3)
	(= [movingBushPtsBlue 4] temp1)
	(= [movingBushPtsBlue 5] temp3)
	(= [movingBushPtsBlue 6] temp1)
	(= [movingBushPtsBlue 7] temp2)
	(if (& (movingBush onControl: origin) cBLUE)
		(movingBush ignoreActors: TRUE)
		(movingBushPoly points: @movingBushPtsBlue size: 4)
	else
		(movingBush ignoreActors: FALSE)
		(movingBushPoly points: @movingBushPts size: 4)
	)
)

(class DeadGoblin of View
	(properties
		whichGoblin 0
	)
	
	(method (init)
		(super init: &rest)
		(self approachVerbs: V_DO)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(switch whichGoblin
					(1 (messager say: N_DEAD_GOBLIN V_LOOK 4))
					(2 (messager say: N_DEAD_GOBLIN V_LOOK 5))
					(3 (messager say: N_DEAD_GOBLIN V_LOOK 6))
					(4 (messager say: N_DEAD_GOBLIN V_LOOK 7))
					(5 (messager say: N_DEAD_GOBLIN V_LOOK 8))
					(6 (messager say: N_DEAD_GOBLIN V_LOOK 9))
					(7 (messager say: N_DEAD_GOBLIN V_LOOK 10))
					(8 (messager say: N_DEAD_GOBLIN V_LOOK 11))
					(else  (messager say: N_DEAD_GOBLIN V_LOOK 12))
				)
			)
			(V_DO
				(cond 
					((and (== whichGoblin 1) (not lootedGoblin1))
						(messager say: N_DEAD_GOBLIN V_DO 4)
						(= lootedGoblin1 TRUE)
						(= cueGoblinSearch 1)
						(ego setScript: cueItScript)
					)
					((and (== whichGoblin 2) (not lootedGoblin2))
						(messager say: N_DEAD_GOBLIN V_DO 5)
						(= lootedGoblin2 TRUE)
						(= cueGoblinSearch 1)
						(ego setScript: cueItScript)
					)
					((and (== whichGoblin 3) (not lootedGoblin3))
						(messager say: N_DEAD_GOBLIN V_DO 6)
						(= lootedGoblin3 TRUE)
					)
					((and (== whichGoblin 4) (not lootedGoblin4))
						(messager say: N_DEAD_GOBLIN V_DO 7)
						(= lootedGoblin4 TRUE)
						(= cueGoblinSearch 2)
						(ego setScript: cueItScript)
					)
					((and (== whichGoblin 5) (not lootedGoblin5))
						(messager say: N_DEAD_GOBLIN V_DO 8)
						(= lootedGoblin5 TRUE)
						(= cueGoblinSearch 3)
						(ego setScript: cueItScript)
					)
					((and (== whichGoblin 6) (not lootedGoblin6))
						(messager say: N_DEAD_GOBLIN V_DO 9)
						(= lootedGoblin6 TRUE)
						(= cueGoblinSearch 4)
						(ego setScript: cueItScript)
					)
					((and (== whichGoblin 7) (not lootedGoblin7))
						(messager say: N_DEAD_GOBLIN V_DO 10)
						(= lootedGoblin7 TRUE)
					)
					((and (== whichGoblin 8) (not lootedGoblin8))
						(messager say: N_DEAD_GOBLIN V_DO 11)
						(= lootedGoblin8 TRUE)
					)
					(else
						(switch whichGoblin
							(3 (messager say: N_DEAD_GOBLIN V_DO 12))
							(7 (messager say: N_DEAD_GOBLIN V_DO 1))
							(8 (messager say: N_DEAD_GOBLIN V_DO 2))
							(else  (messager say: N_DEAD_GOBLIN V_DO 3))
						)
					)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance rm45 of Room
	(properties
		noun N_ROOM
		picture 45
		style DISSOLVE
		horizon 65
		north 33
	)
	
	(method (init &tmp temp0)
		(curRoom
			addObstacle:
				(topBush
					type: PBarredAccess
					init:
						169 63
						169 80
						99 80
						99 63
					yourself:
				)
				(twoRocks
					type: PBarredAccess
					init:
						234 178
						193 178
						193 153
						215 153
						234 164
					yourself:
				)
				(bushRockCombo
					type: PBarredAccess
					init:
						0 127
						74 127
						141 127
						141 145
						77 164
						0 152
					yourself:
				)
		)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: PBarredAccess
					init:
						0 91
						134 91
						148 99
						148 110
						0 110
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init:
						202 63
						201 13
						101 13
						88 13
						88 67
						0 78
						0 0
						319 0
						319 69
						319 115
						294 115
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init:
						0 189
						0 186
						91 186
						91 189
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init:
						261 134
						261 149
						201 149
						201 139
						217 134
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init:
						244 189
						244 174
						319 174
						319 189
					yourself:
				)
		)
		(super init:)
		(LoadMany RES_VIEW 446 vGoblin)
		(NormalEgo)
		(theMusic stop:)
		(goblinMusic play:)
		(if (or (!= prevRoomNum vGoblin) monsterHealth)
			(switch numGoblins
				(0
					(if (Btst fFoundGoblinHideout)
						(= local63 1)
					)
				)
				(1 (= local63 1) (= local62 1))
				(2 (= local63 1) (= local62 1))
				(3 (= local63 1) (= local62 1))
				(else 
					(= local63 1)
					(= local62 1)
				)
			)
		)
		(if (and (not (Btst fAttackedBush)) (not numGoblins))
			(if (OneOf prevRoomNum 33 51)
				(movingBush posn: 140 157 init: setScript: bushAttacks)
			else
				(movingBush posn: 277 95 init: setScript: bushAttacks)
			)
		)
		(goblin1 init: hide:)
		(goblin2 init: hide: setScript: goblin2Leaves)
		(goblin5 init: hide:)
		(if (> howFast slow)
			(goblin6 init:)
			(if (> howFast medium)
				(goblin3 init: hide: setScript: goblin3Leaves)
				(goblin4 init:)
			)
		)
		(switch prevRoomNum
			(33
				(ego
					init:
					posn: 178 (+ (curRoom horizon?) 1)
					setMotion: MoveTo 178 (+ (curRoom horizon?) 20)
				)
			)
			(51
				(self setScript: sEnterFromEast)
			)
			(44
				(self setScript: sEnterFromWest)
			)
			(vGoblin
				(if monsterHealth
					(ChangeGait MOVE_RUN FALSE)
					(ego init: posn: 318 108 setMotion: MoveTo 255 108)
				else
					(Load RES_VIEW 519)
					(self setScript: sVictorious)
					(switch numGoblins
						(0)
						(1
							(deadGoblin1 init: addToPic:)
						)
						(2
							(deadGoblin2 init: addToPic:)
						)
						(3
							(deadGoblin1 init: addToPic:)
							(deadGoblin5 init: addToPic:)
							(deadGoblin6 init: addToPic:)
						)
						(4
							(deadGoblin1 init: addToPic:)
							(deadGoblin4 init: addToPic:)
							(deadGoblin2 init: addToPic:)
						)
						(5
							(deadGoblin1 init: addToPic:)
							(deadGoblin4 init: addToPic:)
							(deadGoblin5 init: addToPic:)
							(deadGoblin2 init: addToPic:)
						)
						(6
							(deadGoblin1 init: addToPic:)
							(deadGoblin4 init: addToPic:)
							(deadGoblin5 init: addToPic:)
							(deadGoblin6 init: addToPic:)
							(deadGoblin2 init: addToPic:)
						)
						(7
							(deadGoblin1 init: addToPic:)
							(deadGoblin4 init: addToPic:)
							(deadGoblin5 init: addToPic:)
							(deadGoblin6 init: addToPic:)
							(deadGoblin7 init: addToPic:)
							(deadGoblin2 init: addToPic:)
						)
						(else 
							(deadGoblin1 init: addToPic:)
							(deadGoblin4 init: addToPic:)
							(deadGoblin5 init: addToPic:)
							(deadGoblin6 init: addToPic:)
							(deadGoblin7 init: addToPic:)
							(deadGoblin8 init: addToPic:)
							(deadGoblin2 init: addToPic:)
							(= numGoblins 7)
						)
					)
				)
			)
			(else 
				(curRoom setScript: sEnterFromSouth)
			)
		)
		(Load RES_SCRIPT PCHASE)
		(Load RES_SCRIPT PAVOID)
		(|= disabledActions ACTION_REST)
	)
	
	(method (doit)
		(cond 
			(script)
			((== (ego edgeHit?) SOUTH)
				(HandsOff)
				(curRoom setScript: sExitSouth)
			)
			((== (ego edgeHit?) EAST)
				(HandsOff)
				(curRoom setScript: sExitEast)
			)
			((== (ego edgeHit?) WEST)
				(HandsOff)
				(curRoom setScript: sExitWest)
			)
		)
		(super doit:)
	)
	
	(method (dispose)
		(= nightPalette 0)
		(Bset fBeenIn45)
		(if (== prevRoomNum vGoblin)
			(= monsterNum 0)
		)
		(DisposeScript PAVOID)
		(DisposeScript PCHASE)
		(super dispose:)
	)
	
	(method (cue)
		(if (IsObject projObj)
			(projObj dispose:)
			(roomTimer setCycle: self 2)
		else
			(curRoom newRoom: vGoblin)
		)
	)
	
	(method (newRoom newRoomNumber)
		(if (ego edgeHit?)
			(Bset fFoundGoblinHideout)
			(goblinMusic stop:)
		)
		(cond 
			((and (!= monsterNum vGoblin) (== newRoomNumber vGoblin))
				(goblinMusic stop:)
				(= monsterNum vGoblin)
				(ego setMotion: 0)
				(Bset fAttackedBush)
				(messager say: N_ROOM NULL C_START_BATTLE 0 self)
			)
			((IsObject projObj)
				(roomTimer setCycle: self 2)
				(projObj dispose:)
			)
			(else
				(roomTimer dispose: delete:)
				(= disabledActions 0)
				(super newRoom: newRoomNumber)
			)
		)
	)
)

(instance goblin1 of Actor
	(properties
		x 37
		y 101
		noun N_GOBLIN1
		yStep 1
		view vGoblin
		loop 2
		priority 3
		signal (| ignrAct ignrHrz fixedLoop fixPriOn)
		cycleSpeed 3
		illegalBits $0000
		xStep 2
		moveSpeed 3
	)
	
	(method (init)
		(= nightPalette 1445)
		(PalVary PALVARYTARGET 1445)
		(AssertPalette 445)
		(super init:)
		(if local61
			;EO: this did not decompile correctly
			(= [goblinScript i] (Clone goblinAttacks))
			(self
				posn: 46 85
				setScript: [goblinScript i] 0
					;(= [goblinScript i] (Clone goblinAttacks))
			)
			(++ i)
		else
			(self setScript: goblin1Leaves)
		)
	)
)

(instance goblin2 of Actor
	(properties
		x 294
		y 75
		noun N_GOBLIN2
		yStep 1
		view vGoblin
		loop 2
		priority 3
		signal (| ignrAct fixedLoop fixPriOn)
		cycleSpeed 3
		illegalBits $0000
		xStep 2
		moveSpeed 3
	)
)

(instance goblin3 of Actor
	(properties
		x 8
		y 79
		noun N_GOBLIN3
		yStep 1
		view vGoblin
		loop 2
		priority 3
		signal (| ignrAct ignrHrz fixedLoop fixPriOn)
		cycleSpeed 3
		illegalBits $0000
		xStep 2
		moveSpeed 3
	)
)

(instance goblin4 of TargActor
	(properties
		x 289
		y 183
		noun N_GOBLIN4
		yStep 1
		view vGoblin
		loop 2
		priority 3
		signal (| ignrAct fixedLoop fixPriOn)
		cycleSpeed 3
		illegalBits $0000
		xStep 2
		moveSpeed 3
	)
	
	(method (init)
		(super init:)
		(if local62
			(= [goblinScript i] (Clone goblinAttacks))
			(self
				show:
				setScript: [goblinScript i]0
					;(= [goblinScript i] (Clone goblinAttacks))
			)
			(++ i)
		else
			(self setScript: goblin4Leaves)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DAGGER
				(ThrowKnife self curRoom)
				(= monsterHealth 55)
			)
			(V_FLAME
				(CastDart self curRoom)
				(= monsterHealth 50)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance goblin5 of TargActor
	(properties
		y 189
		noun N_GOBLIN5
		yStep 1
		view vGoblin
		loop 3
		priority 14
		signal (| ignrAct ignrHrz fixPriOn)
		cycleSpeed 3
		illegalBits $0000
		xStep 2
		moveSpeed 3
	)
	
	(method (init)
		(super init:)
		(if local63
			(= [goblinScript i] (Clone goblinAttacks))
			(self
				setScript: [goblinScript i] 0
					;(= [goblinScript i] (Clone goblinAttacks))
			)
			(++ i)
		else
			(self setScript: goblin5Leaves)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DAGGER
				(ThrowKnife self curRoom)
				(= monsterHealth 50)
			)
			(V_FLAME
				(CastDart self curRoom)
				(= monsterHealth 45)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance goblin6 of Actor
	(properties
		x 284
		y 176
		noun N_GOBLIN6
		yStep 1
		view vGoblin
		loop 3
		priority 3
		signal (| ignrAct fixPriOn)
		cycleSpeed 3
		illegalBits $0000
		xStep 2
		moveSpeed 3
	)
	
	(method (init)
		(super init:)
		(if local64
			(= [goblinScript i] (Clone goblinAttacks))
			(self
				show:
				setScript: [goblinScript i] 0
					;(= [goblinScript i] (Clone goblinAttacks))
			)
			(++ i)
		else
			(self hide: setHeading: 270 setScript: goblin6Leaves)
		)
	)
)

(instance movingBush of Actor
	(properties
		noun N_MOVING_BUSH
		yStep 1
		view 448
		loop 4
		cycleSpeed 3
		xStep 2
		moveSpeed 3
	)
	
	(method (doit)
		(super doit:)
		(if
			(and
				approachingBush
				(< (ego distanceTo: self) 30)
				(not (curRoom script?))
			)
			(= approachingBush FALSE)
			(messager say: N_MOVING_BUSH NULL C_GOBLIN_STEPS_OUT 1 curRoom)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO
				(HandsOff)
				(self setScript: 0 setMotion: 0 setCel: 0 setLoop: 4)
				(= approachingBush TRUE)
				(ego setMotion: PolyPath x y)
			)
			(V_SWORD
				(goblinMusic stop:)
				(ego setMotion: 0)
				(messager say: N_MOVING_BUSH V_SWORD NULL 0 self)
			)
			(V_DAGGER
				(goblinMusic stop:)
				(ego setMotion: 0)
				(messager say: N_MOVING_BUSH V_SWORD NULL 0 self)
			)
			(V_FLAME
				(goblinMusic stop:)
				(ego setMotion: 0)
				(messager say: N_MOVING_BUSH V_SWORD NULL 0 self)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
	
	(method (cue)
		(super cue:)
		(curRoom cue:)
	)
)

(instance deadGoblin1 of DeadGoblin
	(properties
		x 98
		y 92
		approachX 95
		approachY 113
		view 446
		cel 1
		priority 6
		signal (| ignrAct ignrHrz fixedLoop fixPriOn)
		whichGoblin 1
	)
)

(instance deadGoblin2 of DeadGoblin
	(properties
		x 122
		y 156
		approachX 143
		approachY 158
		view 446
		cel 2
		priority 10
		signal (| ignrAct ignrHrz fixedLoop fixPriOn)
		whichGoblin 2
	)
	
	(method (init)
		(super init:)
		(bushRockCombo dispose:)
		(bushRockCombo
			type: PBarredAccess
			init:
				124 130
				124 114
				175 114
				175 125
				170 125
				170 137
				151 137
				151 151
				125 151
				125 160
				0 160
				0 130
			yourself:
		)
		(deadGoblin3 init: addToPic:)
	)
)

(instance deadGoblin3 of DeadGoblin
	(properties
		x 139
		y 132
		approachX 177
		approachY 131
		view 446
		cel 3
		priority 8
		signal (| ignrAct ignrHrz fixedLoop fixPriOn)
		whichGoblin 3
	)
)

(instance deadGoblin4 of DeadGoblin
	(properties
		x 160
		y 96
		approachX 159
		approachY 99
		view 446
		loop 1
		cel 3
		priority 4
		signal (| ignrAct ignrHrz fixedLoop fixPriOn)
		whichGoblin 4
	)
	
	(method (init)
		(super init:)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: PBarredAccess
					init:
						179 75
						179 98
						136 98
						125 90
						125 75
					yourself:
				)
		)
	)
)

(instance deadGoblin5 of DeadGoblin
	(properties
		x 221
		y 131
		approachX 190
		approachY 132
		view 446
		loop 1
		priority 4
		signal (| ignrAct ignrHrz fixedLoop fixPriOn)
		whichGoblin 5
	)
	
	(method (init)
		(super init:)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: PBarredAccess
					init:
						261 134
						261 149
						201 149
						201 129
						194 129
						194 108
						194 103
						222 103
						222 108
						244 108
						244 122
						232 122
						232 134
					yourself:
				)
		)
	)
)

(instance deadGoblin6 of DeadGoblin
	(properties
		x 26
		y 139
		approachX 27
		approachY 117
		view 446
		loop 1
		cel 1
		priority 9
		signal (| ignrAct ignrHrz fixedLoop fixPriOn)
		whichGoblin 6
	)
	
	(method (init)
		(super init:)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: PBarredAccess
					init:
						0 117
						41 117
						41 127
						74 127
						74 140
						46 140
						46 147
						0 147
					yourself:
				)
		)
	)
)

(instance deadGoblin7 of DeadGoblin
	(properties
		x 196
		y 184
		approachX 172
		approachY 184
		view 446
		loop 1
		cel 2
		priority 13
		signal (| ignrAct ignrHrz fixedLoop fixPriOn)
		whichGoblin 7
	)
	
	(method (init)
		(super init:)
		(twoRocks dispose:)
		(twoRocks
			type: PBarredAccess
			init:
				234 179
				216 179
				216 186
				166 186
				166 160
				189 160
				189 153
				215 153
				215 164
				234 164
			yourself:
		)
	)
)

(instance deadGoblin8 of DeadGoblin
	(properties
		x 30
		y 109
		view 446
		priority 7
		signal (| ignrAct ignrHrz fixedLoop fixPriOn)
		whichGoblin 8
	)
)

(instance sVictorious of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= register (ego cycleSpeed?))
				(ego init: posn: 185 125 setHeading: 180 self)
			)
			(1
				(ego
					view: 519
					setLoop: 2
					setCel: 0
					cycleSpeed: 10
					setCycle: EndLoop self
				)
			)
			(2
				(if (== numGoblins 1)
					(messager say: N_ROOM NULL NULL)
				)
				(NormalEgo)
				(ego loop: 2 cycleSpeed: register)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance goblin1Leaves of Script
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds (Random 5 40)))
			(1
				(if (> (ego y?) 112)
					(client setMotion: MoveTo 38 86 self)
				else
					(-- state)
					(= cycles 2)
				)
			)
			(2 (= seconds 3))
			(3
				(client
					setLoop: -1
					setCycle: Walk
					setMotion: MoveTo -10 86 self
				)
			)
			(4
				(client dispose:)
				(self dispose:)
			)
		)
	)
)

(instance goblin2Leaves of Script
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds (Random 5 40)))
			(1
				(if (> (ego y?) 112)
					(client show: setMotion: MoveTo 283 75 self)
				else
					(-- state)
					(= cycles 2)
				)
			)
			(2 (= seconds 3))
			(3
				(client
					setCycle: Walk
					setLoop: -1
					setMotion: MoveTo 350 75 self
				)
			)
			(4
				(client dispose:)
				(self dispose:)
			)
		)
	)
)

(instance goblin3Leaves of Script
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds (Random 5 40)))
			(1
				(if (> (ego y?) 112)
					(client
						show:
						illegalBits: 0
						ignoreActors: 1
						setMotion: MoveTo 8 59 self
					)
				else
					(-- state)
					(= cycles 2)
				)
			)
			(2 (= seconds 3))
			(3
				(client setMotion: MoveTo 8 79 self)
			)
			(4
				(client dispose:)
				(self dispose:)
			)
		)
	)
)

(instance goblin4Leaves of Script
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds (Random 5 40)))
			(1
				(client setMotion: MoveTo 289 164 self)
			)
			(2 (= seconds 3))
			(3
				(client setMotion: MoveTo 289 183 self)
			)
			(4
				(client dispose:)
				(self dispose:)
			)
		)
	)
)

(instance goblin5Leaves of Script
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds (Random 5 40)))
			(1
				(client
					show:
					setCycle: Walk
					setMotion: MoveTo 45 189 self
				)
			)
			(2 (= seconds 3))
			(3
				(client setMotion: MoveTo -18 189 self)
			)
			(4
				(client dispose:)
				(self dispose:)
			)
		)
	)
)

(instance goblin6Leaves of Script
	(method (changeState newState)
		(switch (= state newState)
			((= seconds (Random 5 40)))
			(
				(client
					show:
					setPri: -1
					setCycle: Walk
					setMotion: MoveTo 239 165 self
				)
			)
			((= seconds 3))
			((client setMotion: MoveTo 306 177 self)
			)
			((client dispose:)
				(self dispose:)
			)
		)
	)
)

(instance sEnterFromSouth of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego init: posn: 160 245 setMotion: MoveTo 160 183 self)
			)
			(1
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance sExitSouth of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: MoveTo (ego x?) 245 self)
			)
			(1
				(curRoom newRoom: 62)
			)
		)
	)
)

(instance goblinAttacks of Script
	(method (doit)
		(super doit:)
		(if (and register (< (ego distanceTo: client) 35))
			(curRoom cue:)
			(self dispose:)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(client
					setPri: -1
					setAvoider: PAvoider
					setLoop: -1
					setCycle: Walk
					ignoreActors: FALSE
				)
				(= ticks (Random 200 400))
			)
			(1
				(client show: setMotion: PolyPath (ego x?) (ego y?))
				(= ticks 120)
			)
			(2
				(= register 1)
				(= state 0)
				(= cycles 2)
			)
		)
	)
)

(instance sEnterFromWest of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(cond 
					((< (ego y?) 90) (= register 85))
					((< (ego y?) 115) (= register 114))
					(else (= register 161))
				)
				(ego
					init:
					posn: -25 register
					setMotion: MoveTo 20 register self
				)
			)
			(1
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance sExitWest of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: MoveTo (- (ego x?) 20) (ego y?) self)
			)
			(1
				(curRoom newRoom: 44)
			)
		)
	)
)

(instance sExitEast of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: MoveTo (+ (ego x?) 20) (ego y?) self)
			)
			(1
				(curRoom newRoom: 51)
			)
		)
	)
)

(instance sEnterFromEast of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego init: posn: 345 126 setMotion: MoveTo 300 126 self)
			)
			(1
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance goblinMusic of Sound
	(properties
		flags $ffff
		number 76
		priority 3
		loop SND_DONE
	)
)

(instance movingBushPoly of Polygon
	(properties
		type PBarredAccess
	)
)

(instance topBush of Polygon
	(properties
		type PTotalAccess
	)
)

(instance twoRocks of Polygon
	(properties
		type PTotalAccess
	)
)

(instance bushRockCombo of Polygon
	(properties
		type PTotalAccess
	)
)

(instance bushAttacks of Script
	(method (doit)
		(super doit:)
		(if
			(and
				register
				(or
					(< (ego distanceTo: client) 45)
					(< (goblin5 distanceTo: client) 50)
					(< (goblin1 distanceTo: client) 50)
					(< (goblin4 distanceTo: client) 50)
				)
			)
			(self changeState: 3)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 2))
			(1
				(localproc_0152)
				(curRoom addObstacle: movingBushPoly)
				(= seconds (Random 5 10))
			)
			(2
				(if (> (ego distanceTo: client) 65)
					(client
						setCycle: Walk
						setLoop: -1
						setAvoider: PAvoider
						setMotion: PolyPath (ego x?) (ego y?) self
					)
					(movingBushPoly points: @movingBushPts size: 4)
					(= register 1)
				else
					(-- state)
					(= ticks 30)
				)
			)
			(3
				(localproc_0152)
				(client setLoop: 4 setCel: 0 setMotion: 0)
				(= state 1)
				(= register 0)
				(= ticks (Random 90 300))
			)
		)
	)
)

(instance roomTimer of Timer)

(instance cueItScript of Script
	(method (changeState newState)
		(switch (= state newState)
			(0 (= ticks 60))
			(1
				(switch cueGoblinSearch
					(1 (ego get: iSilver 5))
					(2 (ego get: iSilver 35))
					(3 (ego get: iSilver 8))
					(4 (ego get: iSilver 4))
				)
				(self cue:)
			)
			(2 (self dispose:))
		)
	)
)
