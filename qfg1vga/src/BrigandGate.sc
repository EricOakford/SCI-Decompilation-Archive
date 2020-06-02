;;; Sierra Script 1.0 - (do not remove this comment)
(script# 93)
(include game.sh) (include "93.shm")
(use Main)
(use CastFlame)
(use CastDagger)
(use CastRock)
(use CastCalm)
(use CastOpen)
(use CastDazzle)
(use Target)
(use Procs)
(use PolyPath)
(use Polygon)
(use Feature)
(use LoadMany)
(use DPath)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm93 0
)

(local
	local0
	oldMoveSpeed
	oldEgoSpeed
	minotaurMutters =  1
	timesUsedGate
	enteredFromCave
	gateIsUnlocked
	local7
	local8
	local9
	minotaurSleeping
	minotaurBlinded
	local12 =  5
	local13
	searchedMinotaur
	minotaurHurt
	local16
	local17
)
(procedure (localproc_0140 &tmp temp0)
	(return
		(if (< (= temp0 (ego distanceTo: minotaur)) 30)
		else
			(not
				(if
					(or
						minotaurSleeping
						minotaurBlinded
						local13
						(ego inRect: 0 119 70 145)
						(ego inRect: 238 0 330 106)
						(ego inRect: 245 66 319 110)
						(and (== egoGait MOVE_SNEAK) (>= [egoStats STEALTH] 50) (> temp0 60))
						(> temp0 120)
						(== (minotaur script?) minotaurLooks)
					)
				else
					(Btst fMinotaurDead)
				)
			)
		)
	)
)

(procedure (AddObstacles)
	(if (curRoom obstacles?)
		((curRoom obstacles?) dispose:)
		(curRoom obstacles: 0)
	)
	(curRoom
		addObstacle:
			((Polygon new:)
				type: PBarredAccess
				init:
					0 131
					64 131
					64 149
					35 165
					35 175
					3 175
					3 187
					179 187
					179 189
					0 189
				yourself:
			)
			((Polygon new:)
				type: PBarredAccess
				init:
					131 154
					131 167
					69 167
					69 154
				yourself:
			)
	)
)

(instance rm93 of Room
	(properties
		noun N_ROOM
		picture 93
		style DISSOLVE
		horizon 88
	)
	
	(method (init)
		(NormalEgo)
		(HandsOff)
		(AddObstacles)
		(if (Btst fBrigGateOpen)
			(curRoom
				addObstacle:
					((Polygon new:)
						type: PBarredAccess
						init:
							0 0
							319 0
							319 163
							299 163
							277 126
							219 114
							219 107
							278 107
							278 103
							180 103
							167 48
							154 103
							77 103
							77 116
							63 126
							0 126
						yourself:
					)
			)
			(= gateIsUnlocked TRUE)
		else
			(curRoom
				addObstacle:
					((Polygon new:)
						type: PBarredAccess
						init:
							0 0
							319 0
							319 163
							303 163
							276 127
							221 117
							221 107
							256 107
							256 103
							80 103
							80 114
							63 126
							0 126
						yourself:
					)
			)
		)
		(LoadMany VIEW 93 550 5 523 510 538 426 524 538)
		(Load SOUND 23)
		(Load SCRIPT DPATH)
		(cSound priority: 1 number: 23 loop: -1 play:)
		(super init:)
		(self
			setFeatures:
				onRock
				onRock2
				onDebris
				leftFort
				rightFort
				eastCliff
				westCliff
				aFunnyBush
				ground
		)
		(gateSign setPri: 5 init: addToPic:)
		(bell setPri: 6 init: stopUpd:)
		(bush init:)
		(if (Btst fBrigGateOpen)
			(gate
				init:
				setCel: 3
				setPri: 6
				approachVerbs: V_DO V_LOCKPICK V_THIEFKIT
				ignoreActors:
				stopUpd:
			)
		else
			(gate init: setCel: 0 setPri: 6 approachVerbs: 4 stopUpd:)
		)
		(if (not (Btst fMinotaurDead))
			(= monsterNum vMinotaur)
			(= monsterHealth 186)
			(Load VIEW vMinotaur)
		)
		(= local13 1)
		(switch prevRoomNum
			(89
				(= enteredFromCave TRUE)
				(if (not (Btst fMinotaurDead))
					(= local8 1)
					(= local7 0)
					(minotaur init: setScript: patrol)
				)
				(self setScript: sEnterFromWest)
			)
			(94
				(if (not (Btst fMinotaurDead))
					(= local8 0)
					(= local7 1)
					(minotaur setLoop: 1 init: setScript: patrol)
				)
				(if (Btst fBrigGateOpen)
					(ego posn: 170 98 init: setScript: egoEntersFromNorth)
				else
					(curRoom setScript: sClimbDown)
				)
			)
			(vMinotaur
				(Load VIEW 519)
				(Load VIEW 426)
				(Bset fMinotaurDead)
				(minotaur
					init:
					view: 426
					setLoop: 0
					setCel: 0
					setPri: 8
					posn: 230 145
					approachX: 225
					approachY: 144
					approachVerbs: V_DO
				)
				(curRoom
					addObstacle:
						((Polygon new:)
							type: PBarredAccess
							init:
								265 128
								265 141
								253 141
								253 149
								191 149
								191 115
								207 115
								220 118
							yourself:
						)
				)
				(gate startUpd:)
				(ego posn: 230 158 init: setScript: egoEntersFromCombat)
			)
			(else 
				(if (not (Btst fMinotaurDead))
					(= local8 0)
					(= local7 1)
					(minotaur init: setScript: patrol)
				)
				(self setScript: sEnterFromSouth)
			)
		)
	)
	
	(method (doit)
		(cond 
			(script)
			(
				(and
					(not (== (minotaur script?) minotaurLooks))
					(ego mover?)
					(not (bush cycler?))
					(& (ego onControl: origin) cBLUE)
				)
				(bush setCycle: EndLoop bush)
			)
			((or (== (ego edgeHit?) SOUTH) (== (ego edgeHit?) EAST))
				(HandsOff)
				(curRoom setScript: sExitSouth)
			)
			((== (ego edgeHit?) WEST)
				(if
					(or
						(== (minotaur script?) intoCombat)
						(== (minotaur script?) lookCombat)
						(== (minotaur script?) minotaurLooks)
						(== (curRoom script?) dartAtMinotaur)
					)
					(ego setMotion: 0)
					(curRoom setScript: noEscape)
				else
					(curRoom setScript: sExitWest)
				)
			)
			((== (ego edgeHit?) NORTH)
				(self setScript: exitNorth)
			)
		)
		(super doit:)
	)
	
	(method (dispose)
		(= nightPalette 0)
		(cSound stop:)
		(DisposeScript DPATH)
		(super dispose:)
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(V_DAGGER
				(cond 
					(
						(or
							(ego inRect: 47 141 90 163)
							(ego inRect: 0 119 70 145)
							(ego inRect: 238 0 330 106)
						)
						(messager say: N_ROOM V_DAGGER)
					)
					((Btst fMinotaurDead)
						(CastDagger 0)
					)
					(else
						(if (< (ego x?) 160)
							(minotaur targDeltaX: -25)
						else
							(minotaur targDeltaX: 25)
						)
						(CastDagger minotaur)
					)
				)
			)
			(V_LOOK
				(if (Btst fMinotaurDead)
					(messager say: N_ROOM V_LOOK NULL 1)
				else
					(messager say: N_ROOM V_LOOK NULL 0)
				)
			)
			(V_OPEN
				(cond 
					((or gateIsUnlocked (Btst fBrigGateOpen))
						(messager say: N_ROOM V_OPEN)
					)
					((== (minotaur script?) patrol)
						(minotaur setScript: intoCombat)
					)
					(else
						(gate setScript: openMess)
					)
				)
			)
			(V_DETECT
				(messager say: N_ROOM V_DETECT)
			)
			(V_DAZZLE
				(cond 
					((or (Btst fMinotaurDead) minotaurSleeping local13 minotaurBlinded)
						(messager say: N_ROOM V_DAZZLE)
					)
					((CastDazzle ego minotaurDazzled)
						(minotaur setScript: minotaurDazzled)
					)
				)
			)
			(V_FLAME
				(cond 
					(
						(or
							(ego inRect: 47 141 90 163)
							(ego inRect: 0 119 70 145)
							(ego inRect: 238 0 330 106)
						)
						(messager say: N_ROOM V_FLAME)
					)
					((Btst fMinotaurDead)
						(CastFlame 0)
					)
					(else
						(curRoom setScript: dartAtMinotaur)
					)
				)
			)
			(V_CALM
				(cond 
					(
						(or
							(Btst fMinotaurDead)
							minotaurSleeping
							(== (minotaur script?) minotaurDazzled)
						)
						(messager say: N_ROOM V_CALM)
					)
					((and (not minotaurHurt) (CastCalm ego))
						(minotaur setScript: minotaurCalmed)
					)
					(else
						(minotaur setScript: minotaurLooks)
					)
				)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
	
	(method (cue)
		(if (not (Btst fBeenIn93))
			(messager say: N_ROOM NULL C_FIRSTENTRY)
		)
		(if (Btst fSavedElsa)
			(EgoDead C_DIE_RETURN_FORTRESS C_DIE_RETURN_FORTRESS_TITLE)
		)
	)
	
	(method (newRoom n)
		(Bset fBeenIn93)
		(if (== n vMinotaur)
			(= monsterNum vMinotaur)
		else
			(= monsterNum 0)
			(= monsterHealth 0)
		)
		(super newRoom: n)
	)
)

(instance egoActions of Actions
	
	(method (doVerb theVerb)
		(return
			(if (== theVerb V_LOOK)
				(messager say: N_ROOM V_LOOK C_LOOKEGO)
			else
				(return 0)
			)
		)
	)
)

(instance onDebris of Feature
	(properties
		x 289
		y 60
		noun N_DEBRIS
		onMeCheck cGREY
	)
)

(instance onRock of Feature
	(properties
		x 90
		y 172
		noun N_ROCK1
		onMeCheck cGREEN
	)
)

(instance onRock2 of Feature
	(properties
		x 90
		y 172
		noun N_ROCK2
		onMeCheck cLCYAN
	)
)

(instance ground of Feature
	(properties
		x 177
		y 140
		noun N_GROUND
		onMeCheck cLGREY
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(V_LOOK
				(ego setScript: pickUpARock)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance eastCliff of Feature
	(properties
		x 303
		y 80
		noun N_EASTCLIFF
		onMeCheck cRED
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(V_DO
				(if (TrySkill CLIMB 65)
					(curRoom setScript: climbOverRocks)
				else
					(messager say: N_LOW_CLIMB V_LOOK)
				)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance westCliff of Feature
	(properties
		x 28
		y 69
		onMeCheck cCYAN
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(V_DO
				(curRoom setScript: climbWestCliff)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance leftFort of Feature
	(properties
		x 86
		y 52
		onMeCheck cMAGENTA
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(V_LOOK
				(messager say: N_FORT V_LOOK)
			)
			(V_DO
				(= local16 100)
				(if (TrySkill CLIMB 90)
					(curRoom setScript: sClimbOverWall)
				else
					(curRoom setScript: climbFails)
				)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance rightFort of Feature
	(properties
		x 224
		y 56
		onMeCheck cBROWN
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(V_LOOK
				(messager say: N_FORT V_LOOK)
			)
			(V_DO
				(= local16 235)
				(if (TrySkill CLIMB 90)
					(curRoom setScript: sClimbOverWall)
				else
					(curRoom setScript: climbFails)
				)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance aFunnyBush of Feature
	(properties
		x 224
		y 56
		noun N_BUSH
		onMeCheck cLBLUE
	)
	
	(method (doVerb theVerb theItem)
		(bush doVerb: theVerb theItem &rest)
	)
)

(instance gate of Prop
	(properties
		x 126
		z -100
		noun N_GATE
		approachX 174
		approachY 105
		view 93
		signal (| ignrAct ignrHrz fixedLoop fixPriOn)
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(V_DO
				(cond 
					((and (!= (ego x?) approachX) (!= (ego y?) approachY))
						(ego setMotion: PolyPath approachX approachY self)
					)
					((Btst fBrigGateOpen)
						(ego setScript: closeGate)
					)
					(gateIsUnlocked
						(curRoom setScript: openGate)
					)
					(else
						(switch (++ timesUsedGate)
							(1
								(messager say: N_GATE V_DO C_WHYKNOCK)
							)
							(2
								(messager say: N_GATE V_DO C_GATEFASTENED)
							)
							(else 
								(curRoom setScript: forceGate)
							)
						)
					)
				)
			)
			(V_LOOK
				(if (Btst fBrigGateOpen)
					(messager say: N_GATE V_LOOK C_GATEOPEN)
				else
					(messager say: N_GATE V_LOOK)
				)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance bell of Prop
	(properties
		x 208
		y 77
		noun N_BELL
		approachX 200
		approachY 116
		_approachVerbs 9
		view 93
		loop 1
		priority 6
		signal (| ignrAct fixedLoop fixPriOn)
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(V_DO
				(bell setScript: ringBell)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance gateSign of Prop
	(properties
		x 206
		y 59
		view 93
		loop 2
		signal (| ignrAct fixedLoop fixPriOn)
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(V_LOOK
				(if (ego inRect: 150 92 250 140)
					(messager say: N_GATESIGN V_LOOK C_READSIGN)
				else
					(messager say: N_GATESIGN V_LOOK C_CANTREADSIGN)
				)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance bush of Prop
	(properties
		x 38
		y 157
		noun N_BUSH
		view 93
		loop 3
		priority 10
		signal (| ignrAct fixedLoop fixPriOn)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(if enteredFromCave
					(messager say: N_BUSH V_LOOK C_ENTERFROMCAVE)
				else
					(messager say: N_BUSH V_LOOK C_ENTERFROMAMBUSH)
				)
			)
		)
	)
	
	(method (cue)
		(super cue:)
		(if
			(and
				(not (Btst fMinotaurDead))
				(!= (minotaur script?) minotaurLooks)
				(or (!= egoGait MOVE_SNEAK) (<= [egoStats STEALTH] 50))
			)
			(if minotaurSleeping
				(minotaur setScript: minotaurRises)
			else
				(minotaur setScript: minotaurLooks)
			)
		)
	)
)

(instance archer1 of Prop
	(properties
		x 280
		y 38
		view 93
		loop 4
		priority 4
		signal (| ignrAct fixedLoop fixPriOn)
		cycleSpeed 8
	)
)

(instance archer2 of Prop
	(properties
		x 242
		y 39
		view 93
		loop 4
		priority 4
		signal (| ignrAct fixedLoop fixPriOn)
		cycleSpeed 8
	)
)

(instance archer3 of Prop
	(properties
		x 86
		y 38
		view 93
		loop 5
		priority 4
		signal (| ignrAct fixedLoop fixPriOn)
		cycleSpeed 8
	)
)

(instance archer4 of Prop
	(properties
		x 27
		y 39
		view 93
		loop 5
		priority 4
		signal (| ignrAct fixedLoop fixPriOn)
		cycleSpeed 8
	)
)

(instance minotaur of TargActor
	(properties
		x 150
		y 170
		noun N_MINOTAUR
		view vMinotaur
		priority 13
		signal (| ignrAct fixedLoop fixPriOn)
		cycleSpeed 10
		illegalBits $0000
		xStep 4
		targDeltaX 25
		targDeltaY -15
	)
	
	(method (init)
		(= nightPalette 1425)
		(PalVary PALVARYTARGET 1425)
		(kernel_128 vMinotaur)
		(super init:)
	)
	
	(method (doit &tmp targActorScript)
		(= targActorScript (self script?))
		(cond 
			(local17)
			((Btst fMinotaurDead))
			(minotaurBlinded)
			(minotaurHurt
				(if minotaurSleeping
					(self setScript: minotaurGotHurt)
				else
					(self setScript: intoCombat)
				)
			)
			((== targActorScript minotaurRises))
			((== targActorScript minotaurLooks))
			((localproc_0140)
				(if minotaurSleeping
					(self setScript: minotaurRises)
				else
					(self setScript: minotaurLooks)
				)
			)
			(
				(or
					(== targActorScript patrol)
					(== targActorScript minotaurDazzled)
					(== targActorScript minotaurCalmed)
					minotaurSleeping
				)
			)
			(else (self setScript: patrol))
		)
		(super doit:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_FLAME
				(cond 
					(
						(or
							(ego inRect: 47 141 90 163)
							(ego inRect: 0 119 70 145)
							(ego inRect: 238 0 330 106)
						)
						(messager say: N_MINOTAUR V_FLAME)
					)
					((Btst fMinotaurDead)
						(CastFlame 0)
					)
					(else
						(HandsOff)
						(curRoom setScript: dartAtMinotaur)
					)
				)
			)
			(V_LOOK
				(if (Btst fMinotaurDead)
					(messager say: N_MINOTAUR V_LOOK C_MINOTAURDEFEATED)
				else
					(messager say: N_MINOTAUR V_LOOK C_MINOTAURPATROLLING)
				)
			)
			(V_DO
				(cond 
					(minotaurSleeping
						(messager say: N_MINOTAUR V_DO C_MINOTAURAWAKES 0 self)
					)
					((Btst fMinotaurDead)
						(if searchedMinotaur
							(messager say: N_MINOTAUR V_DO C_ALREADYLOOTED)
						else
							(self setScript: searchMinotaur)
						)
					)
					(else
						(messager say: N_MINOTAUR V_DO C_GATEOPEN)
					)
				)
			)
			(V_DAGGER
				(if (Btst fMinotaurDead)
					(messager say: N_MINOTAUR V_DAGGER C_WHYBOTHER)
				else
					(messager say: N_MINOTAUR V_DAGGER C_GATEFASTENED 0 self)
					(if minotaurSleeping
						(self setScript: minotaurGotHurt)
					else
						(self setScript: intoCombat)
					)
				)
			)
			(V_SWORD
				(if (Btst fMinotaurDead)
					(messager say: N_MINOTAUR V_SWORD C_WHYBOTHER)
				else
					(messager say: N_MINOTAUR V_SWORD C_HERESYOURCHANCE 0 self)
					(if minotaurSleeping
						(self setScript: minotaurGotHurt)
					else
						(self setScript: intoCombat)
					)
				)
			)
			(V_ROCK
				(CastRock minotaur)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
	
	(method (getHurt damage)
		(HandsOff)
		(= minotaurHurt TRUE)
		(= monsterHealth (- monsterHealth damage))
		(super getHurt: damage)
	)
)

(instance searchMinotaur of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				0
				(HandsOff)
				(= searchedMinotaur TRUE)
				(if
					(and
						(== curRoomNum daggerRoom)
						(or missedDaggers hitDaggers [invDropped iDagger])
					)
					(messager say: N_MINOTAUR V_DO C_GETDAGGERS 0 self)
				else
					(self cue:)
				)
			)
			(1
				1
				(if
					(and
						(== curRoomNum daggerRoom)
						(or missedDaggers hitDaggers [invDropped iDagger])
					)
					(ego get: iDagger (+ missedDaggers hitDaggers [invDropped iDagger]))
					(= [invDropped iDagger]
						(= hitDaggers (= missedDaggers (= daggerRoom 0)))
					)
				)
				(self cue:)
			)
			(2
				2
				(messager say: N_MINOTAUR V_DO C_SEARCHMINOTAUR 0 self)
			)
			(3
				3
				(ego get: iSilver 50)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance pickUpARock of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				0
				(HandsOff)
				(self cue:)
			)
			(1
				1
				(cond 
					(
						(and
							(< 138 (ego x?))
							(< (ego x?) 217)
							(< 103 (ego y?))
							(< (ego y?) 173)
						)
						(self cue:)
					)
					((< (ego x?) 160)
						(ego setMotion: PolyPath 73 130 self)
					)
					(else
						(ego setMotion: PolyPath 170 130 self)
					)
				)
			)
			(2
				2
				(ego get: iRock 10)
				(if (OneOf (ego loop?) 0 2 3 4 6)
					(ego setLoop: 0)
				else
					(ego setLoop: 1)
				)
				(ego view: 510 setCel: 0 setCycle: EndLoop self)
			)
			(3
				3
				(ego setCycle: BegLoop self)
			)
			(4
				4
				(HandsOn)
				(NormalEgo)
				(self dispose:)
			)
		)
	)
)

(instance openGate of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				0
				(AddObstacles)
				(gate
					ignoreActors:
					cycleSpeed: 8
					setPri: 4
					setCycle: EndLoop self
				)
			)
			(1
				1
				(if (== prevRoomNum vMinotaur)
					(curRoom
						addObstacle:
							((Polygon new:)
								type: PBarredAccess
								init:
									0 0
									319 0
									319 163
									299 163
									277 126
									219 114
									219 107
									278 107
									278 103
									180 103
									167 48
									154 103
									77 103
									77 116
									63 126
									0 126
								yourself:
							)
							((Polygon new:)
								type: PBarredAccess
								init:
									265 128
									265 141
									253 141
									253 149
									191 149
									191 115
									207 115
									220 118
								yourself:
							)
					)
				else
					(curRoom
						addObstacle:
							((Polygon new:)
								type: PBarredAccess
								init:
									0 0
									319 0
									319 163
									299 163
									277 126
									219 114
									219 107
									278 107
									278 103
									180 103
									167 48
									154 103
									77 103
									77 116
									63 126
									0 126
								yourself:
							)
					)
				)
				(Bset fBrigGateOpen)
				(= gateIsUnlocked TRUE)
				(self dispose:)
			)
		)
	)
)

(instance closeGate of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(AddObstacles)
				(gate
					ignoreActors: TRUE
					setCel: 3
					cycleSpeed: 8
					setCycle: BegLoop self
				)
			)
			(1
				(if (== prevRoomNum vMinotaur)
					(curRoom
						addObstacle:
							((Polygon new:)
								type: PBarredAccess
								init:
									0 0
									319 0
									319 163
									303 163
									276 127
									221 117
									221 107
									256 107
									256 103
									80 103
									80 114
									63 126
									0 126
								yourself:
							)
							((Polygon new:)
								type: PBarredAccess
								init:
									265 128
									265 141
									253 141
									253 149
									191 149
									191 115
									207 115
									220 118
								yourself:
							)
					)
				else
					(curRoom
						addObstacle:
							((Polygon new:)
								type: PBarredAccess
								init:
									0 0
									319 0
									319 163
									303 163
									276 127
									221 117
									221 107
									256 107
									256 103
									80 103
									80 114
									63 126
									0 126
								yourself:
							)
					)
				)
				(Bclr fBrigGateOpen)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance openMess of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= local13 1)
				(CastOpen ego self)
			)
			(1
				(= local13 1)
				(messager say: N_ROOM NULL C_HASPOPENED 0 self)
				(= gateIsUnlocked 1)
			)
			(2
				(lockSound dispose:)
				(= seconds 3)
			)
			(3
				(= local13 0)
				(HandsOn)
				(NormalEgo)
				(self dispose:)
			)
		)
	)
)

(instance minotaurCalmed of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= local13 1)
				(HandsOff)
				(cond 
					((> (minotaur x?) 118)
						(minotaur
							view: vMinotaur
							setLoop: 1
							moveSpeed: 6
							cycleSpeed: 6
							setStep: 6 2
							setCycle: Forward
							setMotion: MoveTo 112 (minotaur y?) self
						)
					)
					((< (minotaur x?) 106)
						(minotaur
							view: vMinotaur
							setLoop: 0
							moveSpeed: 6
							cycleSpeed: 6
							setStep: 6 2
							setCycle: Forward
							setMotion: MoveTo 112 (minotaur y?) self
						)
					)
					(else
						(minotaur
							posn: 112 (minotaur y?)
							setMotion: 0
							setCycle: 0
						)
						(self cue:)
					)
				)
			)
			(1
				(minotaur view: 426 setLoop: 1 setCel: 0)
				(= ticks 30)
			)
			(2
				(minotaur cycleSpeed: 15 setCycle: EndLoop self)
			)
			(3
				(minotaur
					setLoop: 2
					cel: 0
					cycleSpeed: 15
					setCycle: EndLoop self
				)
			)
			(4
				(= minotaurSleeping TRUE)
				(= local13 0)
				(minotaur stopUpd:)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance minotaurRises of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= minotaurSleeping FALSE)
				(minotaur view: 426 setLoop: 1 setCel: 9)
				(= ticks 30)
			)
			(1
				(minotaur cycleSpeed: 9 setCycle: CycleTo 2 -1)
				(= ticks 60)
			)
			(2
				(client setScript: minotaurLooks)
			)
		)
	)
)

(instance minotaurGotHurt of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= local17 1)
				(HandsOff)
				(= minotaurSleeping FALSE)
				(minotaur view: 426 setLoop: 1 setCel: 9)
				(= ticks 30)
			)
			(1
				(minotaur cycleSpeed: 9 setCycle: CycleTo 2 -1)
				(= ticks 60)
			)
			(2
				(client setScript: intoCombat)
			)
		)
	)
)

(instance noEscape of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(messager say: N_ROOM NULL C_NOESCAPE)
			)
		)
	)
)

(instance dartAtMinotaur of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= local17 1)
				(HandsOff)
				(ego setMotion: 0 setScript: 0)
				(cond 
					(minotaurSleeping
						(minotaur targDeltaX: 0)
					)
					((< (ego x?) 160)
						(minotaur targDeltaX: -25)
					)
					(else
						(minotaur targDeltaX: 25)
					)
				)
				(CastFlame minotaur self)
			)
			(1
				(HandsOff)
				(if (< (ego x?) 160)
					(minotaur targDeltaX: -25)
				else
					(minotaur targDeltaX: 25)
				)
				(minotaur setScript: intoCombat)
			)
		)
	)
)

(instance intoCombat of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= local17 1)
				(HandsOff)
				(NormalEgo)
				(ego setMotion: 0 setScript: 0)
				(if (== (curRoom script?) forceGate)
					(ego cycleSpeed: oldEgoSpeed moveSpeed: oldEgoSpeed)
				)
				(curRoom setScript: 0)
				(Face ego minotaur)
				(minotaur
					view: vMinotaur
					setLoop: 2
					setCel: 0
					setMotion: 0
					cycleSpeed: 8
					setStep: 4 2
					setCycle: EndLoop self
				)
			)
			(1
				(= local7 2)
				(if (< (ego x?) (minotaur x?))
					(minotaur setCycle: BegLoop self)
				else
					(= ticks 25)
				)
			)
			(2
				(if (< (ego y?) (minotaur y?))
					(minotaur setLoop: 3 setCel: 2)
					(= local7 3)
				)
				(= ticks 25)
			)
			(3
				(HandsOff)
				(= ticks 120)
			)
			(4
				(if (IsObject gClient)
					(-- state)
					(= ticks 5)
					(gClient dispose:)
				else
					(curRoom newRoom: vMinotaur)
				)
			)
		)
	)
)

(instance lookCombat of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= local17 1)
				(HandsOff)
				(NormalEgo)
				(ego setMotion: 0 setScript: 0)
				(if (== (curRoom script?) forceGate)
					(ego cycleSpeed: oldEgoSpeed moveSpeed: oldEgoSpeed)
				)
				(curRoom setScript: 0)
				(Face ego minotaur)
				(if (< (ego y?) (minotaur y?))
					(minotaur setLoop: 3 setCel: 2)
					(= local7 3)
				)
				(= ticks 25)
			)
			(1
				(HandsOff)
				(= ticks 120)
			)
			(2
				(if (IsObject gClient)
					(-- state)
					(= ticks 5)
					(gClient dispose:)
				else
					(curRoom newRoom: vMinotaur)
				)
			)
		)
	)
)

(instance minotaurDazzled of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= minotaurBlinded 1)
				(if minotaurSleeping
					(minotaur setLoop: 1 setCel: 9 setCycle: CycleTo 2 -1)
				)
			)
			(1
				(minotaur
					view: vMinotaur
					setCycle: 0
					setMotion: 0
					setLoop: 4
					setCel: 1
				)
				(= seconds 12)
			)
			(2
				(= minotaurBlinded FALSE)
				(client setScript: patrol)
			)
		)
	)
)

(instance minotaurSleeps of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if (< (minotaur x?) 112)
					(minotaur
						view: vMinotaur
						setLoop: 0
						cycleSpeed: 10
						moveSpeed: 6
						setCycle: Walk
						setMotion: MoveTo 112 169 self
					)
				else
					(minotaur
						view: vMinotaur
						setLoop: 1
						cycleSpeed: 10
						moveSpeed: 6
						setCycle: Walk
						setMotion: MoveTo 112 169 self
					)
				)
			)
			(1
				(minotaur
					view: 426
					setLoop: 1
					cel: 0
					cycleSpeed: 14
					setCycle: EndLoop self
				)
			)
			(2
				(= minotaurSleeping TRUE)
				(minotaur
					view: 426
					setLoop: 2
					cel: 0
					cycleSpeed: 14
					setCycle: EndLoop self
				)
			)
			(3
				(= local13 0)
				(minotaur stopUpd:)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance egoEntersFromNorth of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: MoveTo 170 115 self)
			)
			(1
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance egoEntersFromCombat of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= ticks 60)
				(minotaur setPri: 8 addToPic:)
			)
			(1
				(ego setPri: 11 setMotion: MoveTo 181 158 self)
			)
			(2
				(ego view: 519 setLoop: 0 setCycle: EndLoop self)
			)
			(3
				(messager say: N_ROOM NULL C_IMBAD)
				(HandsOn)
				(ego posn: 181 155)
				(NormalEgo 2)
				(self dispose:)
			)
		)
	)
)

(instance climbWestCliff of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= local13 1)
				(= register 3)
				(ego setPri: 6 setMotion: PolyPath 77 103 self)
			)
			(1
				(ego
					setLoop: 1
					setCycle: Forward
					setMotion: MoveTo 76 95 self
				)
			)
			(2
				(ego setMotion: MoveTo 85 102 self)
				(if (-- register)
					(= state 0)
				)
			)
			(3
				(messager say: N_ROOM NULL C_LOOSEDIRT)
				(NormalEgo)
				(HandsOn)
				(= local13 0)
				(self dispose:)
			)
		)
	)
)

(instance ringBell of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= local13 1)
				(bell setCycle: Forward)
				(messager say: N_ROOM NULL C_RINGIT)
				(= seconds 5)
			)
			(1
				(bell setCel: 0)
				(archer1 init: setCycle: EndLoop)
				(archer2 init: setCycle: EndLoop)
				(archer3 init: setCycle: EndLoop)
				(archer4 init: setCycle: EndLoop)
				(messager say: N_ROOM NULL C_UHOH)
				(= seconds 4)
			)
			(2
				(EgoDead C_DIE_RINGBELL C_DIE_RINGBELL_TITLE)
			)
		)
	)
)

(instance lockSound of Sound
	(properties
		number 35
		priority 3
	)
)

(instance sEnterFromSouth of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= local13 1)
				(ego init: posn: 320 250 setMotion: MoveTo 252 182 self)
			)
			(1
				(HandsOn)
				(rm93 cue:)
				(= ticks 250)
			)
			(2
				(= local13 0)
				(self dispose:)
			)
		)
	)
)

(instance sExitSouth of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if (curRoom obstacles?)
					((curRoom obstacles?) dispose:)
					(curRoom obstacles: 0)
				)
				(ego setMotion: MoveTo (ego x?) 245 self)
			)
			(1
				(= ticks 90)
			)
			(2
				(HandsOn)
				(curRoom newRoom: 91)
			)
		)
	)
)

(instance patrol of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(minotaur
					moveSpeed: 6
					cycleSpeed: 6
					setCycle: 0
					setStep: 6 2
				)
				(if minotaurSleeping
					(self changeState: 5)
				else
					(= local13 0)
					(switch local7
						(0 (self changeState: 10))
						(1 (self changeState: 14))
						(2
							(if local8
								(self changeState: 17)
							else
								(self changeState: 13)
							)
						)
						(3
							(if local8
								(self changeState: 3)
							else
								(self changeState: 1)
							)
						)
					)
				)
			)
			(1
				(= local8 0)
				(= local7 1)
				(minotaur view: vMinotaur setLoop: 3 setCel: 0)
				(= ticks 5)
			)
			(2 (self changeState: 14))
			(3
				(= local8 1)
				(= local7 0)
				(minotaur view: vMinotaur setLoop: 3 setCel: 1)
				(= ticks 5)
			)
			(4 (self changeState: 10))
			(5
				(minotaur cycleSpeed: 12 setCycle: BegLoop self)
			)
			(6
				(= minotaurSleeping FALSE)
				(= local7 2)
				(self changeState: 0)
			)
			(10
				(= local8 1)
				(= local7 0)
				(minotaur
					view: vMinotaur
					setLoop: 0
					setCel: 0
					setCycle: Forward
					setMotion: MoveTo 286 (minotaur y?) self
				)
			)
			(11
				(= local8 0)
				(minotaur setLoop: 2 setCel: 3)
				(= ticks 5)
			)
			(12
				(minotaur setLoop: 2 setCel: 2)
				(= ticks 5)
			)
			(13
				(= local7 1)
				(minotaur view: vMinotaur setLoop: 2 setCel: 1)
				(= ticks 5)
			)
			(14
				(= local8 0)
				(= local7 1)
				(minotaur
					view: vMinotaur
					setLoop: 1
					setCel: 0
					setCycle: Forward
					setMotion: MoveTo 25 (minotaur y?) self
				)
			)
			(15
				(= local8 1)
				(minotaur setLoop: 2 setCel: 1)
				(= ticks 5)
			)
			(16
				(minotaur setLoop: 2 setCel: 2)
				(= ticks 5)
			)
			(17
				(= local7 0)
				(minotaur view: vMinotaur setLoop: 2 setCel: 3)
				(= ticks 5)
			)
			(18
				(self changeState: 10)
			)
		)
	)
)

(instance minotaurLooks of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if
					(not
						(if
							(or
								(ego inRect: 0 119 70 145)
								(ego inRect: 238 0 330 106)
							)
						else
							(ego inRect: 245 66 319 110)
						)
					)
					(HandsOff)
					(NormalEgo)
					(ego setMotion: 0 setScript: 0)
					(if (== (curRoom script?) forceGate)
						(ego cycleSpeed: oldEgoSpeed moveSpeed: oldEgoSpeed)
					)
					(curRoom setScript: 0)
					(Face ego minotaur)
				)
				(minotaur
					view: vMinotaur
					setLoop: 2
					setCel: 0
					setMotion: 0
					cycleSpeed: 8
					setStep: 4 2
					setCycle: EndLoop self
				)
			)
			(1
				(= local7 2)
				(if (< (ego x?) (minotaur x?))
					(minotaur setCycle: BegLoop self)
				else
					(= ticks 25)
				)
			)
			(2
				(if (< (ego y?) (minotaur y?))
					(minotaur setLoop: 3 setCel: 2)
					(= local7 3)
				)
				(= ticks 25)
			)
			(3
				(if
					(or
						(ego inRect: 0 119 70 145)
						(ego inRect: 238 0 330 106)
						(ego inRect: 245 66 319 110)
					)
					(switch minotaurMutters
						(1 (messager say: N_ROOM NULL C_MUTTER1))
						(2 (messager say: N_ROOM NULL C_MUTTER2))
						(3 (messager say: N_ROOM NULL C_MUTTER3))
						(4 (messager say: N_ROOM NULL C_MUTTER4))
						(5 (messager say: N_ROOM NULL C_MUTTER5))
						(6 (messager say: N_ROOM NULL C_MUTTER6))
					)
					(if (== minotaurMutters 6)
						(= minotaurMutters 1)
					else
						(++ minotaurMutters)
					)
					(HandsOn)
					(client setScript: patrol)
				else
					(= ticks 90)
				)
			)
			(4
				(client setScript: lookCombat)
			)
		)
	)
)

(instance sEnterFromWest of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= local13 1)
				(ego
					init:
					posn: -10 129
					illegalBits: 0
					ignoreActors: 1
					setMotion: MoveTo 28 129 self
				)
			)
			(1
				(rm93 cue:)
				(= ticks 30)
			)
			(2
				(if
				(and (not (== egoGait MOVE_SNEAK)) (< [egoStats STEALTH] 50))
					(bush setCycle: EndLoop bush)
				)
				(= local13 0)
				(NormalEgo)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance sExitWest of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: MoveTo -35 129 self)
				(if (curRoom obstacles?)
					((curRoom obstacles?) dispose:)
					(curRoom obstacles: 0)
				)
			)
			(1
				(if (bush cycler?)
					(bush setCycle: 0)
				)
				(if (minotaur mover?)
					(minotaur setMotion: 0 setCycle: 0)
				)
				(if (minotaur script?)
					(minotaur setScript: 0)
				)
				(if (ego script?)
					(ego setScript: 0)
				)
				(= ticks 90)
			)
			(2
				(HandsOn)
				(curRoom newRoom: 89)
			)
		)
	)
)

(instance exitNorth of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: PolyPath 170 80 self)
				(if (curRoom obstacles?)
					((curRoom obstacles?) dispose:)
					(curRoom obstacles: 0)
				)
			)
			(1
				(if (minotaur mover?)
					(minotaur setMotion: 0 setCycle: 0)
				)
				(if (minotaur script?)
					(minotaur setScript: 0)
				)
				(if (ego script?)
					(ego setScript: 0)
				)
				(= ticks 90)
			)
			(2
				(HandsOn)
				(curRoom newRoom: 94)
			)
		)
	)
)

(instance forceGate of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ChangeGait 0 0)
				(ego setMotion: MoveTo 157 142 self)
			)
			(1 (= cycles 2))
			(2
				(messager say: N_ROOM NULL C_FORCEGATE)
				(if (not (Btst fMinotaurDead))
					(= seconds 3)
				else
					(= ticks 20)
				)
			)
			(3
				(= oldEgoSpeed (ego cycleSpeed?))
				(= oldMoveSpeed (ego moveSpeed?))
				(ego
					view: 550
					setLoop: 0
					setCel: 0
					cycleSpeed: 8
					setCycle: Forward
				)
				(= ticks 120)
			)
			(4
				(ego view: 0 setLoop: -1 setHeading: 360 self)
			)
			(5 (= ticks 20))
			(6
				(ego
					view: 5
					setLoop: 3
					cycleSpeed: 1
					moveSpeed: 1
					setMotion: MoveTo 157 109 self
				)
			)
			(7
				(ego
					view: 523
					setPri: 8
					setLoop: 0
					setCel: 0
					posn: 156 94
					cycleSpeed: 12
					setCycle: EndLoop self
				)
			)
			(8
				(ShakeScreen 1 shakeSRight)
				(ego moveSpeed: 6 setMotion: MoveTo 156 104 self)
			)
			(9
				(if minotaurSleeping
					(minotaur setScript: minotaurRises)
				)
				(= ticks 20)
			)
			(10
				(ego view: 538 setLoop: 0 setCel: 0 setCycle: EndLoop self)
			)
			(11 (= ticks 20))
			(12
				(ego view: 538 setLoop: 1 setCel: 0 setCycle: EndLoop self)
			)
			(13
				(NormalEgo)
				(ego
					setHeading: 180
					moveSpeed: oldEgoSpeed
					cycleSpeed: oldMoveSpeed
				)
				(= ticks 20)
			)
			(14
				(messager say: N_ROOM NULL C_THATFELTGOOD)
				(if minotaurSleeping
					(minotaur setScript: minotaurRises)
				)
				(if (TrySkill STR 60)
					(self setScript: openGate self)
				else
					(= cycles 2)
				)
			)
			(15
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance sClimbOverWall of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				0
				(HandsOff)
				(ego
					setMotion: PolyPath local16 103 self
					signal: (| (ego signal?) ignrHrz)
				)
			)
			(1
				1
				(ego
					view: 517
					posn: local16 71
					setLoop: 0
					cel: 0
					setPri: 15
					setCycle: Forward
					setMotion: DPath local16 71 local16 41 self
				)
			)
			(2
				(= monsterNum (= monsterHealth 0))
				(curRoom newRoom: 94)
			)
		)
	)
)

(instance climbFails of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				0
				(HandsOff)
				(ego
					setMotion: PolyPath local16 103 self
					signal: (| (ego signal?) ignrHrz)
				)
			)
			(1
				1
				(ego
					view: 517
					posn: local16 71
					setLoop: 0
					cel: 0
					setPri: 6
					setCycle: Forward
					setMotion: DPath local16 71 local16 51 self
				)
			)
			(2
				2
				(ego setCycle: 0 setMotion: 0)
				(= ticks 30)
			)
			(3
				3
				(ego
					setLoop: 0
					setCel: 0
					setMotion: MoveTo local16 71 self
				)
			)
			(4
				(ego posn: local16 103)
				(NormalEgo 3)
				(messager say: N_FORT V_DO NULL 1 self)
			)
			(5 (HandsOn))
		)
	)
)

(instance climbOverRocks of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= oldEgoSpeed (ego cycleSpeed?))
				(= oldMoveSpeed (ego moveSpeed?))
				(ego setMotion: PolyPath 277 126 self)
			)
			(1
				(ego setPri: 9 posn: 278 124)
				(= ticks 5)
			)
			(2
				(messager say: N_ROOM NULL C_CLIMBROCKS)
				(ego view: 517 setLoop: 1 setCel: 3 posn: 279 120)
				(= ticks 10)
			)
			(3
				(ego setCel: 4 posn: 286 105)
				(= ticks 10)
			)
			(4
				(ego setCel: 5 posn: 291 89)
				(= ticks 10)
			)
			(5
				(ego setCel: 2 posn: 303 63)
				(= ticks 10)
			)
			(6
				(ego
					setCel: 0
					posn: 298 54
					cycleSpeed: 12
					setCycle: EndLoop self
				)
			)
			(7
				(= monsterNum (= monsterHealth 0))
				(ego cycleSpeed: oldEgoSpeed moveSpeed: oldMoveSpeed)
				(HandsOn)
				(curRoom newRoom: 94)
			)
		)
	)
)

(instance sClimbDown of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= oldEgoSpeed (ego cycleSpeed?))
				(= oldMoveSpeed (ego moveSpeed?))
				(ego
					init:
					view: 517
					setLoop: 1
					setCel: 255
					posn: 298 54
					cycleSpeed: 12
					setPri: 8
				)
				(= cycles 2)
			)
			(1 (ego setCycle: BegLoop self))
			(2 (= ticks 15))
			(3
				(ego setCel: 2 posn: 303 63)
				(= ticks 15)
			)
			(4
				(ego setCel: 5 posn: 291 89)
				(= ticks 15)
			)
			(5
				(ego setCel: 4 posn: 286 105)
				(= ticks 15)
			)
			(6
				(ego setCel: 3 posn: 279 120)
				(= ticks 15)
			)
			(7
				(NormalEgo)
				(HandsOn)
				(ego
					posn: 277 126
					setPri: -1
					cycleSpeed: oldEgoSpeed
					moveSpeed: oldMoveSpeed
				)
				(self dispose:)
			)
		)
	)
)
