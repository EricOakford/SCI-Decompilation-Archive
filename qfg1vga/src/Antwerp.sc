;;; Sierra Script 1.0 - (do not remove this comment)
(script# 84)
(include game.sh) (include "84.shm")
(use Main)
(use CastFlame)
(use CastCalm)
(use CastDazzle)
(use Target)
(use Procs)
(use PolyPath)
(use Polygon)
(use Feature)
(use Sound)
(use Jump)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm84 0
)

(local
	local0
	local1
	gEgoLooper
	local3
	antwerpY
	antwerpX
	local6
	local7
	local8 =  85
	local9 =  79
	gEgoMoveSpeed
	gEgoCycleSpeed
)
(procedure (ScareAntwerp)
	(if (== (antwerp status?) 1)
		(antwerp status: 0 setScript: sFlyOut)
	)
)

(instance rm84 of Room
	(properties
		picture 84
		style DISSOLVE
	)
	
	(method (init)
		(if (or (== prevRoomNum 97) (== prevRoomNum 89))
			(Bset fTrollDoorOpen)
		)
		(if (not (Btst fTrollDoorOpen))
			(self
				addObstacle:
					(rockPoly
						type: 2
						init:
							71
							85
							102
							89
							102
							107
							191
							155
							256
							168
							319
							168
							319
							189
							0
							189
							0
							0
							184
							0
							184
							13
						yourself:
					)
					((Polygon new:)
						type: 2
						init: 270 59 233 46 194 46 194 25 210 12 210 0 319 0 319 63
						yourself:
					)
			)
			(theRock init: stopUpd:)
		else
			(self
				addObstacle:
					((Polygon new:)
						type: 2
						init: 123 64 0 64 0 0 185 -1 185 12 123 47
						yourself:
					)
					((Polygon new:)
						type: 2
						init:
							0
							67
							73
							67
							73
							82
							83
							87
							102
							87
							102
							106
							92
							106
							92
							114
							125
							114
							125
							132
							166
							132
							166
							138
							198
							155
							319
							155
							319
							189
							0
							189
						yourself:
					)
					((Polygon new:)
						type: 2
						init: 270 59 233 46 194 46 194 25 210 12 210 0 319 0 319 63
						yourself:
					)
			)
			(theRock init: setCel: 255 setPri: 1 stopUpd:)
			(entrance init:)
		)
		(super init:)
		(cSound fade:)
		(NormalEgo)
		(ego init: ignoreActors: TRUE)
		(rocksUR init:)
		(ground init:)
		(mountainRocks init:)
		(bushesLL init:)
		(tree init:)
		(bushesUR init:)
		(bushesU init:)
		(switch prevRoomNum
			(85
				(curRoom setScript: sEnterFromEast)
			)
			(97
				(antwerp posn: 186 87)
				(curRoom setScript: sEnterFromCave)
			)
			(89
				(antwerp posn: 186 87)
				(curRoom setScript: sEnterFromCave)
			)
			(else 
				(ego posn: 204 -2)
				(curRoom setScript: sEnterFromNorth)
			)
		)
		(Load RES_SCRIPT JUMP)
		(if (not (if (Btst fAntwerpSplit) else (Btst fAntwerpInSky)))
			(= local1 1)
			(antwerp
				init:
				setLoop: 0
				loop: 3
				status: 1
				setScript: sRandBounce
			)
			(cSound stop:)
			(antSound init:)
			(antHits init:)
		else
			(antwerp status: 0)
		)
	)
	
	(method (doit)
		(super doit:)
		(cond 
			(script)
			((and (Btst fTrollDoorOpen) (< (ego x?) 71))
				(self setScript: sExitThruDoor)
			)
			((< (ego y?) 28) (self setScript: sExitNorth))
			((== (ego edgeHit?) 2) (curRoom setScript: sExitEast))
			((< (ego y?) 43) (ego setPri: 4))
			(else (ego setPri: -1))
		)
	)
	
	(method (dispose)
		(mouseDownHandler delete: self)
		(Bset VISITED_ANTWERP)
		(cond 
			((!= newRoomNum 89)
				(Bclr SAID_HIDEN_GOSEKE)
				(if (not (if (Btst fBeatFred) else (Btst fBeatFred89)))
					(Bclr fTrollDoorOpen)
					(Bclr fTrollDoorUnlocked)
				)
			)
			((not (Btst fBeenIn94))
				(Bset fBrigsUnaware)
			)
		)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(cond 
					((Btst fAntwerpSplit) (messager say: N_ROOM V_LOOK C_ANTWERPSPLIT))
					((Btst fAntwerpInSky) (messager say: N_ROOM V_LOOK C_ANTWERPSKY))
					(else (messager say: N_ROOM V_LOOK C_SEEGRASS))
				)
			)
			(V_DETECT (messager say: N_ROOM V_DETECT))
			(V_DAZZLE
				(self setScript: sDazzleAnAntwerp)
			)
			(V_CALM
				(self setScript: sCalmAnAntwerp)
			)
			(V_OPEN
				(if (Btst fTrollDoorUnlocked)
					(messager say: N_ROOM V_OPEN)
				else
					(self setScript: sMagicRock)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
	
	(method (newRoom newRoomNumber)
		(= nightPalette 0)
		(DisposeScript JUMP)
		(antwerp setCycle: 0 setMotion: 0 setScript: 0)
		(super newRoom: newRoomNumber)
	)
)

(instance entrance of Feature
	(properties
		x 57
		y 139
		z 100
		nsTop 14
		nsLeft 45
		nsBottom 65
		nsRight 70
		sightAngle 40
		onMeCheck $0002
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(cond 
					((Btst fTrollDoorOpen) (messager say: 5 1 6))
					((ego inRect: 30 52 115 94) (messager say: 5 1 5))
					(else (messager say: 5 1))
				)
			)
			(V_DO (theRock doVerb: V_DO))
			(V_DAGGER (ScareAntwerp))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance mountainRocks of Feature
	(properties
		x 159
		y 94
		noun N_MOUNTAINROCKS
		nsBottom 189
		nsRight 319
		sightAngle 40
		onMeCheck $0002
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK (messager say: N_MOUNTAINROCKS V_LOOK))
			(V_DAGGER (ScareAntwerp))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance bushesLL of Feature
	(properties
		x 73
		y 153
		noun N_BUSHESLL
		nsTop 118
		nsBottom 189
		nsRight 146
		sightAngle 40
		onMeCheck $0004
	)
)

(instance tree of Feature
	(properties
		x 263
		y 133
		noun N_TREE
		nsTop 78
		nsLeft 207
		nsBottom 189
		nsRight 319
		sightAngle 40
		onMeCheck $0004
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_DAGGER)
			(ScareAntwerp)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance bushesUR of Feature
	(properties
		x 268
		y 29
		noun N_BUSHESUR
		nsTop 1
		nsLeft 218
		nsBottom 58
		nsRight 319
		sightAngle 40
		onMeCheck $0004
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_DAGGER)
			(ScareAntwerp)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance bushesU of Feature
	(properties
		x 164
		y 6
		noun N_BUSHESU
		nsLeft 155
		nsBottom 13
		nsRight 174
		sightAngle 40
		onMeCheck $0004
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_DAGGER)
			(ScareAntwerp)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance rocksUR of Feature
	(properties
		x 257
		y 36
		noun N_ROCKSUR
		nsTop 12
		nsLeft 195
		nsBottom 60
		nsRight 319
		sightAngle 40
		onMeCheck $0008
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_DAGGER)
			(ScareAntwerp)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance ground of Feature
	(properties
		x 159
		y 1
		z -94
		nsBottom 189
		nsRight 319
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(if (Btst fAntwerpInSky)
					(messager say: N_GROUND V_LOOK C_ANTWERPSKY)
				else
					(messager say: N_GROUND V_LOOK C_SEEGRASS)
				)
			)
			(V_DAGGER (ScareAntwerp))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance theRock of Actor
	(properties
		x 62
		y 103
		z 100
		noun N_ROCKDOOR
		view 84
		priority 3
		signal $4810
		cycleSpeed 8
		illegalBits $0000
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO
				(HandsOff)
				(cond 
					(
					(or (!= (ego x?) local8) (!= (ego y?) local9)) (ego setMotion: PolyPath local8 local9))
					((!= (ego loop?) 7) (ego setHeading: 315))
					(
						(and
							(not (Btst fAntwerpInSky))
							(not (Btst fAntwerpSplit))
							(== (antwerp x?) 110)
							(== local1 1)
						)
						(antwerp
							setMotion: MoveTo (+ (antwerp x?) 20) (antwerp y?)
						)
					)
					((not (Btst fTrollDoorUnlocked)) (messager say: N_ROCKDOOR V_DO C_LOCKED))
					((not (TrySkill STR 40 0)) (messager say: N_ROCKDOOR V_DO C_TOOWEAK))
					((not cel) (ego setScript: openRock))
					(else (messager say: N_ROCKDOOR V_DO C_ALREADYOPENED))
				)
				(HandsOn)
			)
			(V_LOOK
				(cond 
					((not (ego inRect: 30 52 115 94)) (messager say: N_ROCKDOOR V_LOOK C_SEENOLOCKS))
					((Btst fTrollDoorOpen) (messager say: N_ROCKDOOR V_LOOK 6))
					(else (messager say: N_ROCKDOOR V_LOOK C_SEETHELOCK))
				)
			)
			(V_LOCKPICK
				(cond 
					((not (ego inRect: 30 52 115 94)) (messager say: N_ROCKDOOR V_LOCKPICK C_SEENOLOCKS))
					((Btst fTrollDoorUnlocked) (messager say: N_ROCKDOOR V_LOCKPICK C_UNLOCKED))
					((not (CanPickLocks)) (messager say: N_ROCKDOOR V_LOCKPICK C_NOPICK))
					((TrySkill PICK 85 lockPickBonus) (messager say: N_ROCKDOOR V_LOCKPICK C_LOCKPICKSUCCESS) (Bset fTrollDoorUnlocked))
					((ego has: iThiefKit) (messager say: N_ROCKDOOR V_LOCKPICK C_TOOLFAIL))
					(else (messager say: N_ROCKDOOR V_LOCKPICK C_PICKFAIL))
				)
			)
			(V_THIEFKIT
				(cond 
					((not (ego inRect: 30 52 115 94)) (messager say: N_ROCKDOOR V_LOCKPICK C_SEENOLOCKS))
					((Btst fTrollDoorUnlocked) (messager say: N_ROCKDOOR V_LOCKPICK C_UNLOCKED))
					((not (CanPickLocks)) (messager say: N_ROCKDOOR V_LOCKPICK C_NOPICK))
					((TrySkill PICK 85 lockPickBonus) (messager say: N_ROCKDOOR V_LOCKPICK C_LOCKPICKSUCCESS) (Bset fTrollDoorUnlocked))
					(else (messager say: N_ROCKDOOR V_LOCKPICK C_TOOLFAIL))
				)
			)
			(V_BRASSKEY
				(cond 
					((not (ego inRect: 30 52 115 94)) (messager say: N_ROCKDOOR V_LOCKPICK C_SEENOLOCKS))
					((Btst fTrollDoorUnlocked) (messager say: N_ROCKDOOR V_LOCKPICK C_UNLOCKED))
					((Btst OBTAINED_BRUTUS_KEY) (messager say: N_ROCKDOOR V_BRASSKEY C_LOCKED) (Bset fTrollDoorUnlocked))
					(else (messager say: N_ROCKDOOR V_BRASSKEY C_WRONGKEY))
				)
			)
			(V_TALK
				(if (and (Btst SPIED_THIEVES) (not (Btst SAID_HIDEN_GOSEKE)) (not (Btst fBeatFred)))
					(Bset SAID_HIDEN_GOSEKE)
					(SolvePuzzle POINTS_GIVECAVEPASSWORD 5)
					(messager say: N_ROCKDOOR V_ALTTALK)
				else
					(messager say: N_ROCKDOOR V_ALTTALK C_NOANSWER)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance antwerp of TargActor
	(properties
		x 110
		y 81
		yStep 4
		view 590
		loop 2
		signal $6000
		cycleSpeed 4
		illegalBits $0000
		xStep 4
		moveSpeed 4
	)
	
	(method (init)
		(= nightPalette 1590)
		(PalVary PALVARYTARGET 1590)
		(kernel_128 590)
		(super init:)
	)
	
	(method (doit)
		(super doit:)
		(= local6 (- y 10))
		(= local7 (+ y 10))
		(if
			(or
				(and
					(== (self cel?) 0)
					(== (self loop?) 0)
					(not (== (antwerp status?) 0))
				)
				(and
					(== (self cel?) 0)
					(== (self loop?) 1)
					(not (== (antwerp status?) 0))
				)
			)
			(antSound loop: 1 play:)
		)
		(if
			(and
				(== (antwerp status?) 1)
				(< (ego distanceTo: self) 50)
			)
			(cond 
				(
					(and
						(< (ego x?) (- x 15))
						(> (ego y?) local6)
						(< (ego y?) local7)
					)
					(self status: 3 setScript: sPushEgoLeft)
				)
				(
					(and
						(> (ego x?) (+ x 15))
						(> (ego y?) local6)
						(< (ego y?) local7)
					)
					(self status: 3 setScript: sPushEgoRight)
				)
				(else
					(= gEgoCycleSpeed (ego cycleSpeed?))
					(self
						status: 2
						cycleSpeed: 6
						setCycle: Forward
						setPri: -1
						setScript: sAvoidEgo
					)
				)
			)
		)
	)
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(V_LOOK
					(cond 
						((Btst fAntwerpSplit) (messager say: N_ANTWERP V_LOOK C_BAREHANDED))
						((Btst fAntwerpInSky) (messager say: N_ANTWERP V_LOOK C_FLOWNCOOP))
						(else (messager say: N_ANTWERP 1 4))
					)
				)
				(V_DO
					(messager say: N_ANTWERP V_DO C_BAREHANDED)
					(ego setMotion: MoveTo (antwerp x?) (antwerp y?))
				)
				(V_SWORD
					(HandsOff)
					(antwerp status: 4)
					(curRoom setScript: sFightSword)
				)
				(V_DAGGER
					(HandsOff)
					(antwerp status: 4)
					(curRoom setScript: sFightDagger)
				)
				(V_FLAME
					(HandsOff)
					(Face ego antwerp)
					(RedrawCast)
					(if (== (antwerp status?) 1)
						(CastFlame antwerp)
					)
					(return TRUE)
				)
				(else  (super doVerb: theVerb))
			)
		)
	)
	
	(method (getHurt)
		(self status: 0 setScript: sFlyOut)
	)
)

(instance sRandBounce of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (== (antwerp status?) 4)
					(= ticks (Random 60 190))
				else
					(antSound play:)
					(if (< (antwerp x?) (ego x?))
						(antwerp loop: 0 setCel: 0 setCycle: EndLoop self)
					else
						(antwerp loop: 1 setCel: 255 setCycle: EndLoop self)
					)
				)
			)
			(1
				(if (== (antwerp status?) 4)
					(antwerp cycleSpeed: (Random 1 6))
				)
				(self init:)
			)
		)
	)
)

(instance sMagicRock of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= gEgoCycleSpeed (ego cycleSpeed?))
				(theGame setCursor: waitCursor 1)
				(ego setMotion: PolyPath 140 86 self)
			)
			(1
				(theGame setCursor: waitCursor 1)
				(cond 
					(
						(and
							(IsObject antwerp)
							(== (antwerp script?) sAvoidEgo)
						)
					)
					((and (== (ego x?) 140) (== (ego y?) 86)) (= ticks 1))
					(else (ego setMotion: PolyPath 140 86 self))
				)
			)
			(2
				(theGame setCursor: waitCursor 1)
				(ego setMotion: PolyPath 109 86 self)
			)
			(3
				(theGame setCursor: waitCursor 1)
				(ego setHeading: 270)
				(= ticks 180)
			)
			(4
				(theGame setCursor: waitCursor 1)
				(ego view: 521 loop: 0 cycleSpeed: 6 setCycle: EndLoop self)
			)
			(5
				(Bset fTrollDoorUnlocked)
				(ego cycleSpeed: gEgoCycleSpeed moveSpeed: gEgoCycleSpeed)
				(if (< [egoStats OPEN] 50)
					(messager say: N_ROOM 0 C_UNLOCKED 0 sMagicRock)
				else
					(theRock
						setCycle: 0
						startUpd:
						setPri: 1
						setCycle: EndLoop self
					)
					((curRoom obstacles?) delete: rockPoly)
					(rockPoly dispose:)
					(curRoom
						addObstacle:
							((Polygon new:)
								type: 2
								init: 123 64 0 64 0 0 185 -1 185 12 123 47
								yourself:
							)
							((Polygon new:)
								type: 2
								init:
									0
									67
									73
									67
									73
									82
									83
									87
									102
									87
									102
									106
									92
									106
									92
									114
									125
									114
									125
									132
									166
									132
									166
									138
									198
									155
									319
									155
									319
									189
									0
									189
								yourself:
							)
					)
					(SolvePuzzle POINTS_FINDSECRETENTRANCE 10)
					(Bset fTrollDoorOpen)
				)
			)
			(6
				(theRock stopUpd:)
				(NormalEgo 1)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance openRock of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(theRock setPri: 1 setCycle: EndLoop self)
				((curRoom obstacles?) delete: rockPoly)
				(rockPoly dispose:)
				(curRoom
					addObstacle:
						((Polygon new:)
							type: 2
							init: 123 64 0 64 0 0 185 -1 185 12 123 47
							yourself:
						)
						((Polygon new:)
							type: 2
							init:
								0
								67
								73
								67
								73
								82
								83
								87
								102
								87
								102
								106
								92
								106
								92
								114
								125
								114
								125
								132
								166
								132
								166
								138
								198
								155
								319
								155
								319
								189
								0
								189
							yourself:
						)
				)
				(SolvePuzzle POINTS_FINDSECRETENTRANCE 10)
				(Bset fTrollDoorOpen)
			)
			(1
				(theRock stopUpd:)
				(NormalEgo 1)
				(HandsOn)
			)
		)
	)
)

(instance sExitNorth of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: MoveTo 204 -2 self)
			)
			(1 (curRoom newRoom: 78))
		)
	)
)

(instance sEnterFromNorth of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (HandsOff) (= cycles 2))
			(1
				(ego setPri: 4 setMotion: MoveTo 169 52 self)
			)
			(2
				(HandsOn)
				(NormalEgo)
				(self dispose:)
			)
		)
	)
)

(instance sEnterFromCave of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setPri: 1 posn: 30 65 setMotion: MoveTo 96 65 self)
			)
			(1
				(NormalEgo)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance sExitThruDoor of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setPri: 1 setMotion: MoveTo 30 (ego y?) self)
			)
			(1 (curRoom newRoom: 89))
		)
	)
)

(instance sExitEast of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: MoveTo 345 (ego y?) self)
			)
			(1 (curRoom newRoom: 85))
		)
	)
)

(instance sEnterFromEast of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					posn: 345 (ego y?)
					setMotion: MoveTo 295 (ego y?) self
				)
			)
			(1
				(HandsOn)
				(NormalEgo)
				(self dispose:)
			)
		)
	)
)

(instance sPushEgoRight of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(antwerp setLoop: 0 setCycle: BegLoop self)
				(ego setMotion: 0 setHeading: 270 self)
				(= gEgoLooper (ego looper?))
				(= antwerpX (antwerp x?))
				(= antwerpY (antwerp y?))
				(= gEgoCycleSpeed (ego cycleSpeed?))
				(= gEgoMoveSpeed (ego moveSpeed?))
			)
			(1)
			(2
				(antwerp
					setPri: (+ (ego priority?) 1)
					setCel: 1
					posn: (+ antwerpX 8) (- antwerpY 13)
				)
				(= ticks 10)
			)
			(3
				(ego view: 513 setLoop: 3 setCel: 4 looper: 0)
				(antwerp
					setCel: 2
					posn: (+ antwerpX 22) (- antwerpY 19)
				)
				(= ticks 10)
			)
			(4
				(antwerp
					setCel: 3
					posn: (+ antwerpX 31) (- antwerpY 12)
				)
				(ego
					view: 513
					setLoop: 3
					moveSpeed: 1
					cycleSpeed: 12
					setMotion: MoveTo (+ (ego x?) 20) (ego y?)
					setCycle: EndLoop
				)
				(= ticks 10)
			)
			(5
				(antwerp setCel: 4 posn: (+ antwerpX 40) (+ antwerpY 3))
				(= ticks 10)
			)
			(6
				(antwerp
					setCel: 255
					setCycle: BegLoop
					setMotion: JumpTo antwerpX antwerpY self
					setPri: -1
				)
			)
			(7
				(if (not (TakeDamage 10))
					(ego
						view: 529
						setLoop: 0
						cel: 0
						cycleSpeed: 3
						setCycle: EndLoop self
					)
				else
					(HandsOn)
					(NormalEgo)
					(ego
						loop: 1
						cycleSpeed: gEgoCycleSpeed
						moveSpeed: gEgoMoveSpeed
						looper: gEgoLooper
					)
					(client status: 1 setScript: sRandBounce)
				)
			)
			(8 (EgoDead 136 137))
		)
	)
)

(instance sPushEgoLeft of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(antwerp setLoop: 1 setCycle: BegLoop self)
				(ego setMotion: 0 setHeading: 90 self)
				(= gEgoLooper (ego looper?))
				(= antwerpX (antwerp x?))
				(= antwerpY (antwerp y?))
				(= gEgoCycleSpeed (ego cycleSpeed?))
				(= gEgoMoveSpeed (ego moveSpeed?))
			)
			(1)
			(2
				(antwerp
					setPri: (+ (ego priority?) 1)
					setCel: 1
					posn: (- antwerpX 8) (- antwerpY 13)
				)
				(= ticks 10)
			)
			(3
				(ego view: 513 setLoop: 2 setCel: 4 looper: 0)
				(antwerp
					setCel: 2
					posn: (- antwerpX 22) (- antwerpY 19)
				)
				(= ticks 10)
			)
			(4
				(antwerp
					setCel: 3
					posn: (- antwerpX 31) (- antwerpY 12)
				)
				(ego
					view: 513
					setLoop: 2
					moveSpeed: 1
					cycleSpeed: 12
					setMotion: MoveTo (- (ego x?) 20) (ego y?)
					setCycle: EndLoop
				)
				(= ticks 10)
			)
			(5
				(antwerp setCel: 4 posn: (- antwerpX 40) (+ antwerpY 3))
				(= ticks 10)
			)
			(6
				(antwerp
					setCel: 255
					setCycle: BegLoop
					setMotion: JumpTo antwerpX antwerpY self
					setPri: -1
				)
			)
			(7
				(if (not (TakeDamage 10))
					(ego
						view: 529
						setLoop: 0
						cel: 0
						cycleSpeed: 3
						setCycle: EndLoop self
					)
				else
					(HandsOn)
					(ego
						loop: 0
						cycleSpeed: gEgoCycleSpeed
						moveSpeed: gEgoMoveSpeed
						looper: gEgoLooper
					)
					(NormalEgo)
					(client status: 1 setScript: sRandBounce)
				)
			)
			(8 (EgoDead 136 137))
		)
	)
)

(instance sAvoidEgo of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if (not (-- register))
			(Face ego antwerp)
			(= register 5)
		)
	)
	
	(method (changeState newState)
		(asm
			lap      newState
			aTop     state
			push    
			dup     
			ldi      0
			eq?     
			bnt      code_1824
			pushi    0
			callb    HandsOff,  0
			pushi    #setCursor
			pushi    2
			lsg      waitCursor
			pushi    1
			lag      theGame
			send     8
			pushi    #setMotion
			pushi    1
			pushi    0
			lag      ego
			send     6
			ldi      1
			aTop     register
			lsl      local1
			dup     
			eq?     
			bnt      code_1742
			pushi    #y
			pushi    0
			lag      ego
			send     4
			push    
			lal      local6
			lt?     
			bnt      code_1728
			pushi    #setMotion
			pushi    4
			class    PolyPath
			push    
			pushi    169
			pushi    128
			pushSelf
			lofsa    antwerp
			send     12
			ldi      4
			sal      local1
			jmp      code_1820
code_1728:
			pushi    #setMotion
			pushi    4
			class    PolyPath
			push    
			pushi    170
			pushi    56
			pushSelf
			lofsa    antwerp
			send     12
			ldi      2
			sal      local1
			jmp      code_1820
code_1742:
			dup     
			ldi      2
			eq?     
			bnt      code_178e
			pushi    #x
			pushi    0
			lag      ego
			send     4
			push    
			pushi    #x
			pushi    0
			lofsa    antwerp
			send     4
			lt?     
			bnt      code_1775
			pushi    #setMotion
			pushi    4
			class    PolyPath
			push    
			pushi    272
			pushi    82
			pushSelf
			lofsa    antwerp
			send     12
			ldi      3
			sal      local1
			jmp      code_1820
code_1775:
			pushi    #setMotion
			pushi    4
			class    PolyPath
			push    
			pushi    110
			pushi    81
			pushSelf
			lofsa    antwerp
			send     12
			ldi      1
			sal      local1
			jmp      code_1820
code_178e:
			dup     
			ldi      3
			eq?     
			bnt      code_17d7
			pushi    #y
			pushi    0
			lag      ego
			send     4
			push    
			lal      local6
			lt?     
			bnt      code_17bd
			pushi    #setMotion
			pushi    4
			class    PolyPath
			push    
			pushi    169
			pushi    128
			pushSelf
			lofsa    antwerp
			send     12
			ldi      4
			sal      local1
			jmp      code_1820
code_17bd:
			pushi    #setMotion
			pushi    4
			class    PolyPath
			push    
			pushi    170
			pushi    56
			pushSelf
			lofsa    antwerp
			send     12
			ldi      2
			sal      local1
			jmp      code_1820
code_17d7:
			dup     
			ldi      4
			eq?     
			bnt      code_1820
			pushi    #x
			pushi    0
			lag      ego
			send     4
			push    
			pushi    #x
			pushi    0
			lofsa    antwerp
			send     4
			lt?     
			bnt      code_180a
			pushi    #setMotion
			pushi    4
			class    PolyPath
			push    
			pushi    272
			pushi    82
			pushSelf
			lofsa    antwerp
			send     12
			ldi      3
			sal      local1
			jmp      code_1820
code_180a:
			pushi    #setMotion
			pushi    4
			class    PolyPath
			push    
			pushi    110
			pushi    81
			pushSelf
			lofsa    antwerp
			send     12
			ldi      1
			sal      local1
code_1820:
			toss    
			jmp      code_187b
code_1824:
			dup     
			ldi      1
			eq?     
			bnt      code_187b
			pushi    #setCursor
			pushi    2
			lsg      waitCursor
			pushi    1
			lag      theGame
			send     8
			pushi    #status
			pushi    1
			pushi    1
			pushi    244
			pushi    1
			lsl      gEgoCycleSpeed
			pushi    146
			pushi    1
			lofsa    sRandBounce
			push    
			pToa     client
			send     18
			pushi    #script
			pushi    0
			lag      curRoom
			send     4
			push    
			lofsa    sMagicRock
			eq?     
			bnt      code_1877
			pushi    #state
			pushi    0
			lofsa    sMagicRock
			send     4
			push    
			ldi      3
			lt?     
			bnt      code_1877
			pushi    #cue
			pushi    0
			lofsa    sMagicRock
			send     4
			jmp      code_187b
code_1877:
			pushi    0
			callb    HandsOn,  0
code_187b:
			toss    
			ret     
		)
	)
)

(instance sFlyOut of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(messager say: N_ANTWERP 0 C_BOUNCEDOFF 1 self)
			)
			(1
				(Bset fAntwerpInSky)
				(= antwerpX (antwerp x?))
				(= antwerpY (antwerp y?))
				(Face ego antwerp)
				(= ticks 40)
			)
			(2
				(antwerp
					setLoop: (if (< antwerpX (ego x?)) 0 else 1)
					cycleSpeed: 16
					setCel: 255
					setCycle: BegLoop self
				)
			)
			(3 (= ticks 10))
			(4
				(antwerp
					cycleSpeed: 2
					setCycle: CycleTo 2 1
					setPri: 2
					setStep: 8 8
					setMotion: MoveTo (+ antwerpX 25) -10 self
				)
			)
			(5 (= seconds 3))
			(6
				(messager say: N_ROOM 0 C_ANTWERPSCARED)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance sFightSword of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= gEgoCycleSpeed (ego cycleSpeed?))
				(= gEgoMoveSpeed (ego moveSpeed?))
				(Load RES_VIEW 501)
				(Load RES_VIEW 502)
				(if (< (ego y?) (antwerp y?))
					(antwerp setCycle: Forward setMotion: PolyPath 169 128)
					(ego setMotion: PolyPath 230 75 self)
					(= local1 4)
				else
					(antwerp setCycle: Forward setMotion: PolyPath 170 56)
					(ego setMotion: PolyPath 166 120 self)
					(= local1 2)
				)
			)
			(1 (ego setHeading: 270 self))
			(2
				(messager say: N_ROOM 0 C_LOOSENUP)
				(ego
					view: 502
					setLoop: 1
					setCel: 0
					cycleSpeed: 10
					setCycle: EndLoop self
				)
			)
			(3 (self cue:))
			(4
				(switch (++ register)
					(0 (= local0 1))
					(1 (= local0 5))
					(2 (= local0 3))
					(3 (= local0 9))
					(4 (= local0 5))
					(5 (= local0 3))
					(6 (= local0 9))
					(7 (= local0 5))
				)
				(ego
					view: 501
					setLoop: local0
					setCel: 0
					setCycle: EndLoop self
				)
			)
			(5
				(if (!= register 7) (= state (- state 2)))
				(ego setCycle: BegLoop self)
			)
			(6
				(messager say: N_ROOM 0 C_APPROACH)
				(if (== (ego x?) 230) (NormalEgo 1) else (NormalEgo 5))
				(Face ego antwerp)
				(ego
					cycleSpeed: gEgoCycleSpeed
					moveSpeed: gEgoMoveSpeed
					setCycle: Walk
					setMotion: PolyPath 181 88 self
				)
				(antwerp setMotion: PolyPath 110 81 self)
				(= local1 1)
			)
			(7)
			(8
				(= antwerpX (antwerp x?))
				(= antwerpY (antwerp y?))
				(= register 3)
				(= cycles 2)
			)
			(9
				(antwerp cycleSpeed: 3 setCycle: BegLoop self)
				(ego view: 501 setLoop: 3 setCel: 0 cycleSpeed: 8)
			)
			(10 (= ticks 20))
			(11
				(antwerp setCel: 1)
				(ego setCel: 1)
				(= ticks 5)
			)
			(12
				(antwerp
					setCel: 2
					setStep: 10 10
					setMotion: MoveTo (antwerp x?) -30
				)
				(ego setCel: 2)
				(= ticks 5)
			)
			(13 (ego setCycle: EndLoop self))
			(14 (ego setCycle: BegLoop self))
			(15
				(if (-- register)
					(antwerp setMotion: MoveTo antwerpX antwerpY self)
				else
					(messager say: N_ROOM 0 C_HOLYMACKAREL 1 self)
					(Bset fAntwerpInSky)
					(antwerp status: 0)
					(= state 17)
				)
			)
			(16
				(antwerp setCycle: Forward cycleSpeed: (Random 3 5))
				(= state 8)
				(= seconds 2)
			)
			(17
				(ego view: 502 loop: 1 cel: 5 setCycle: BegLoop self)
			)
			(18
				(HandsOn)
				(NormalEgo)
				(ego cycleSpeed: gEgoCycleSpeed moveSpeed: gEgoMoveSpeed)
				(self dispose:)
			)
		)
	)
)

(instance sFightDagger of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theIconBar advanceCurIcon:)
				(HandsOff)
				(= gEgoCycleSpeed (ego cycleSpeed?))
				(= gEgoMoveSpeed (ego moveSpeed?))
				(Load RES_VIEW 502)
				(Load RES_VIEW 512)
				(if (< (ego y?) (antwerp y?))
					(antwerp setCycle: Forward setMotion: PolyPath 169 128)
					(ego setMotion: PolyPath 230 75 self)
					(= local1 4)
				else
					(antwerp setCycle: Forward setMotion: PolyPath 170 56)
					(ego setMotion: PolyPath 171 118 self)
					(= local1 2)
				)
			)
			(1 (ego setHeading: 270 self))
			(2
				(messager say: N_ROOM 0 C_LOOSENUP)
				(ego
					view: 502
					setLoop: 2
					setCel: 0
					cycleSpeed: 10
					setCycle: EndLoop self
				)
			)
			(3 (self cue:))
			(4
				(switch (++ register)
					(0 (= local0 5))
					(1 (= local0 9))
					(2 (= local0 6))
					(3 (= local0 9))
					(4 (= local0 7))
					(5 (= local0 9))
					(6 (= local0 8))
					(7 (= local0 9))
				)
				(ego
					view: 512
					setLoop: local0
					setCel: 0
					setCycle: EndLoop self
				)
			)
			(5
				(if (!= register 7) (= state (- state 2)))
				(ego setCycle: BegLoop self)
			)
			(6
				(messager say: N_ROOM 0 C_APPROACH)
				(if (== (ego x?) 230) (NormalEgo 1) else (NormalEgo 5))
				(Face ego antwerp)
				(ego
					cycleSpeed: gEgoCycleSpeed
					moveSpeed: gEgoMoveSpeed
					setCycle: Walk
					setMotion: PolyPath 181 88 self
				)
				(antwerp setMotion: PolyPath 110 81 self)
				(= local1 1)
			)
			(7)
			(8
				(= antwerpX (antwerp x?))
				(= antwerpY (antwerp y?))
				(= register 3)
				(= cycles 2)
			)
			(9
				(antwerp cycleSpeed: 3 setCycle: BegLoop self)
				(ego view: 512 setLoop: 5 setCel: 0 cycleSpeed: 8)
			)
			(10 (= ticks 20))
			(11
				(antwerp setCel: 1)
				(ego setCel: 1)
				(= ticks 5)
			)
			(12
				(antwerp
					setCel: 2
					setStep: 10 10
					setMotion: MoveTo (antwerp x?) -30
				)
				(ego setCel: 2)
				(= ticks 5)
			)
			(13 (ego setCycle: CycleTo 4 1 self))
			(14 (ego setCycle: BegLoop self))
			(15
				(cond 
					((-- register) (antwerp setMotion: MoveTo antwerpX antwerpY self))
					((not (TakeDamage 10)) (EgoDead 136 137))
					(else
						(NormalEgo)
						(ego
							loop: 1
							cycleSpeed: gEgoCycleSpeed
							moveSpeed: gEgoMoveSpeed
						)
						(messager say: N_ROOM 0 C_HOLYMACKAREL 1 self)
						(Bset fAntwerpInSky)
						(HandsOn)
						(antwerp status: 0)
						(self dispose:)
					)
				)
			)
			(16
				(antwerp setCycle: Forward cycleSpeed: (Random 3 5))
				(= state 8)
				(= seconds 3)
			)
		)
	)
)

(instance sDazzleAnAntwerp of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(CastDazzle ego self)
			)
			(1
				(if (== (antwerp status?) 0)
					(HandsOn)
					(self dispose:)
				else
					(messager say: N_ROOM 0 C_CANTDAZZLE 0 self)
				)
			)
			(2
				(HandsOn)
				(antwerp setScript: sRandBounce)
				(self dispose:)
			)
		)
	)
)

(instance sCalmAnAntwerp of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(CastCalm ego self)
			)
			(1
				(if (== (antwerp status?) 0)
					(HandsOn)
					(self dispose:)
				else
					(messager say: N_ROOM 0 C_CANTCALM 0 self)
				)
			)
			(2
				(HandsOn)
				(antwerp setScript: sRandBounce)
				(self dispose:)
			)
		)
	)
)

(instance antSound of Sound
	(properties
		number 4
		priority 5
	)
)

(instance antHits of Sound
	(properties
		number 54
		priority 6
	)
)

(instance rockPoly of Polygon
	(properties
		type $0002
	)
)
