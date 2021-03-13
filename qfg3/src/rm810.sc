;;; Sierra Script 1.0 - (do not remove this comment)
(script# 810)
(include game.sh) (include "810.shm")
(use Main)
(use Target)
(use EgoDead)
(use JumpX)
(use Inset)
(use Scaler)
(use PolyPath)
(use Polygon)
(use Feature)
(use ForCount)
(use LoadMany)
(use Reverse)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm810 0
)

(local
	climbedUp
	local1
	egoMoveSpeed
)
(procedure (ShowDeadApemen)
	(sFx number: 931 play:)
	(deader init: setPri: 11 ignoreActors: 1 addToPic:)
	(switch numDeaders
		(0 0)
		(1
			(deader1 init: ignoreActors: 1 addToPic:)
		)
		(2
			(deader1 init: ignoreActors: 1 addToPic:)
			(deader2 init: ignoreActors: 1 addToPic:)
		)
		(else 
			(deader1 init: ignoreActors: 1 addToPic:)
			(deader2 init: ignoreActors: 1 addToPic:)
			(deader3 init: ignoreActors: 1 addToPic:)
		)
	)
	(return (++ numDeaders))
)

(instance rm810 of Room
	(properties
		noun N_ROOM
		picture 810
	)
	
	(method (init)
		(Scaler backY: 145)
		(ego
			x: -22
			y: 168
			init:
			setScale: Scaler 100 50 189 125
			normalize:
		)
		(LoadMany RES_VIEW 810 570 6)
		(HandsOn)
		(super init:)
		(cSound number: 810 setLoop: -1 play: 127)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: PContainedAccess
					init:
						0 173
						81 173
						165 189
						216 148
						185 138
						168 135
						155 127
						133 124
						133 99
						112 99
						112 126
						142 157
						0 157
					yourself:
				)
				((Polygon new:)
					type: PContainedAccess
					init:
						148 81
						212 81
						212 71
						148 71
					yourself:
				)
		)
		(pedestal init:)
		(stairs init:)
		(leftStairs init:)
		(ruins init:)
		(rocks init:)
		(noWayToGo init:)
		(anubisStatue init:)
		(if (and (not (ego has: iOpal)) (not (Btst fLostCityDoorOpen)))
			(opal setPri: 14 init: stopUpd:)
		)
		(if (Btst fLostCityDoorOpen)
			(doorWay
				init:
				loop: 2
				approachVerbs: V_DO V_LOOK
				ignoreActors: FALSE
			)
		else
			(doorWay
				init:
				approachVerbs: V_DO V_LOOK
			)
		)
		(if (and (not (== battleResult battleEGOLOST)) (== prevRoomNum 550))
			(ego x: 60 y: 169 code: quikChek)
			(ShowDeadApemen)
		else
			(self setScript: egoEnters)
		)
		(if (and (== prevRoomNum 550) (== battleResult battleEGOLOST))
			(self setScript: egoIsDead)
		)
	)
	
	(method (dispose)
		(ego code: 0)
		(UnLoad RES_VIEW 810)
		(UnLoad RES_VIEW 570)
		(UnLoad RES_VIEW 6)
		(LoadMany FALSE FORCOUNT INSET CASTFETCH JUMPX)
		(if gNewList
			(gNewList eachElementDo: #dispose)
		)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_FLAME
				(ego setScript: castProj 0 theVerb)
			)
			(V_FORCEBOLT
				(ego setScript: castProj 0 theVerb)
			)
			(V_ROCK	;was 32 (horn), but that's not a throwing item!
				(ego setScript: castProj 0 theVerb)
			)
			(V_DAGGER
				(ego setScript: castProj 0 theVerb)
			)
			(V_REST
				(messager say: N_CUE V_DOIT C_CANT_SLEEP)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
	
	(method (setInset theInset who reg)
		(ego code: 0)
		(theIconBar disable: ICON_CAST ICON_WALK ICON_ACTIONS ICON_DO)
		(if inset
			(inset dispose:)
		)
		(if (and argc theInset)
			(theInset
				init:
					(if (>= argc 2) who else 0)
					self
					(if (>= argc 3) reg else 0)
			)
		)
		(if (Btst fLostCityDoorOpen)
			(opal view: 810 loop: 1 setPri: 14 x: 158 y: 83 init:)
		)
	)
)

(instance egoIsDead of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= seconds 2)
			)
			(1
				(EgoDead)
			)
		)
	)
)

(instance castOpenOnDoor of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(AutoTarget 125 101)
				(self setScript: (ScriptID CASTOPEN 0) self)
			)
			(1
				(messager say: N_CUE V_DOIT C_CANT_OPEN)
			)
			(2
				(HandsOn)
				(ego normalize:)
				(self dispose:)
			)
		)
	)
)

(instance egoFalls of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					code: 0
					view: 35
					setLoop: 3
					cel: 0
					setCycle: CycleTo 1 1 self
				)
				(= egoMoveSpeed (ego moveSpeed?))
			)
			(1
				(ego
					moveSpeed: 0
					ignoreActors: 1
					illegalBits: 0
					setMotion: JumpX -48 self
				)
			)
			(2
				(ego x: 162 y: 131 z: 0 setCycle: EndLoop self)
			)
			(3
				(sFx number: 920 setLoop: 1 play:)
				(ShakeScreen 10)
				(= cycles 11)
			)
			(4
				(ego
					loop: 5
					cel: 0
					illegalBits: cWHITE
					setCycle: EndLoop self
				)
			)
			(5
				(if (or (cast contains: apeMan) (cast contains: apeMan1))
					(= monsterNum vApeman)
					(= monsterHealth 180)
					(curRoom newRoom: 550)
				)
				(ego code: quikChek moveSpeed: egoMoveSpeed normalize:)
				(= climbedUp 0)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance cantExit of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: 0 code: 0)
				(apeMan1
					setScale: Scaler 100 50 189 125
					x: 0
					y: 168
					init:
					setStep: 4 4
					setCycle: Walk
					ignoreActors: TRUE
					setMotion: MoveTo 30 168 self
				)
			)
			(1
				(= monsterNum vApeman)
				(= monsterHealth 180)
				(curRoom newRoom: 550)
			)
		)
	)
)

(instance apeManCrossLeft of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(apeMan
					x: 322
					y: 79
					setPri: 0
					setScale: Scaler 100 50 189 50
					setCycle: Walk
					init:
					setStep: 4 4
					setMotion: MoveTo 96 79 self
				)
			)
			(1
				(apeMan setHeading: 180)
				(= seconds 5)
			)
			(2
				(apeMan setMotion: MoveTo 322 79 self)
			)
			(3
				(apeMan setScale: 189 setPri: -1 dispose:)
				(self dispose:)
			)
		)
	)
)

(instance castProj of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(self setScript: (ScriptID PROJECTILE 0) self register)
			)
			(1
				(if (cast contains: apeMan)
					(= monsterNum vApeman)
					(= monsterHealth 180)
					(curRoom newRoom: 550)
				)
				(ego normalize:)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance apeManCrossRight of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(apeMan
					setScale: Scaler 100 50 189 125
					x: 41
					y: 134
					init:
					setStep: 4 4
					setCycle: Walk
					setMotion: MoveTo 340 162 self
				)
			)
			(1
				(apeMan dispose:)
				(self dispose:)
			)
		)
	)
)

(instance climbDownPedestal of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: MoveTo 166 70 self)
			)
			(1
				(sFx number: 928 play:)
				(ego view: 7 cel: 11 loop: 4 setCycle: BegLoop self)
			)
			(2
				(ego
					code: quikChek
					view: 7
					y: 93
					setStep: 2 1
					setLoop: 3
					setCycle: Reverse
					setMotion: MoveTo 162 131 self
				)
			)
			(3
				(ego view: 5 normalize:)
				(HandsOn)
				(= climbedUp 0)
				(if (not (cast contains: apeMan))
					(curRoom setScript: apeManCrossLeft)
				else
					(= monsterNum vApeman)
					(= monsterHealth 180)
					(curRoom newRoom: 550)
				)
				(self dispose:)
			)
		)
	)
)

(instance climbUpPedestal of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: PolyPath 162 131 self)
			)
			(1
				(ego view: 7 setLoop: 3 setCycle: ForwardCounter 2 self)
				(sFx number: 928 play:)
			)
			(2
				(ego
					setCycle: Forward
					cycleSpeed: 5
					setStep: 2 1
					setMotion: MoveTo 166 93 self
				)
			)
			(3
				(ego loop: 4 cel: 0 y: 70 setCycle: EndLoop self)
			)
			(4
				(ego view: 13 setHeading: 180 setCycle: EndLoop)
				(= seconds 3)
			)
			(5
				(ego code: fallChek setStep: 3 2 normalize:)
				(= climbedUp 1)
				(HandsOn)
				(if (not (cast contains: apeMan))
					(apeMan
						x: 41
						y: 144
						setScale: 189
						init:
						setStep: 4 4
						setScript: apeManCrossRight
					)
				)
				(self dispose:)
			)
		)
	)
)

(instance egoEnters of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(apeMan
					setScale: Scaler 100 50 189 125
					init:
					setStep: 4 4
					setCycle: Walk
					setMotion: MoveTo 340 162 self
				)
			)
			(1
				(apeMan dispose:)
				(ego x: -20 y: 168 setMotion: PolyPath 60 169 self)
			)
			(2
				(if (Btst fSenseDanger)
					(messager say: N_CUE V_DOIT C_SENSE_DANGER 0 self)
				else
					(self cue:)
				)
			)
			(3
				(ego code: quikChek)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance hoarkOpal of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					get: iOpal
					solvePuzzle: fGetAnubisEyes 5 (| puzzleWIZARD puzzleTHIEF)
					setMotion: MoveTo 181 71 self
				)
			)
			(1
				(opal dispose:)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance apeMan1 of Actor
	(properties
		view 570
	)
)

(instance apeMan of TargActor
	(properties
		x 41
		y 134
		view 570
	)
	
	(method (getHurt)
		(= monsterNum vApeman)
		(= monsterHealth 180)
		(curRoom newRoom: 550)
		(super getHurt:)
	)
)

(instance doorWay of View
	(properties
		x 116
		y 94
		noun N_DOORWAY
		nsTop 87
		nsLeft 114
		nsBottom 121
		nsRight 132
		sightAngle 40
		approachX 124
		approachY 137
		_approachVerbs 4
		view 811
		loop 1
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO
				(if (== climbedUp 0)
					(if (not (Btst fLostCityDoorOpen))
						(curRoom setInset: anubisInset)
					else
						(super doVerb: theVerb)
					)
				else
					(messager say: N_CUE V_DOIT C_CLIMB_DOWN_FIRST)
				)
			)
			(V_LOOK
				(if (== climbedUp 0)
					(curRoom setInset: anubisInset)
				else
					(messager say: N_CUE V_DOIT C_CANT_SEE)
				)
			)
			(V_OPEN
				(if (not (ego script?))
					(ego setScript: castOpenOnDoor)
				)
			)
			(V_OPAL
				(if (== climbedUp 0)
					(curRoom setInset: anubisInset)
				else
					(messager say: N_CUE V_DOIT C_CLIMB_DOWN_FIRST)
				)
			)
			(else
				(super doVerb: theVerb)
			)
		)
	)
)

(instance opal of View
	(properties
		x 185
		y 56
		view 811
		signal ignrAct
	)
)

(instance deader of View
	(properties
		x 46
		y 189
		view 572
		loop 1
	)
)

(instance deader1 of View
	(properties
		x 114
		y 86
		view 572
		loop 1
		cel 1
		scaleSignal scalable
		scaleX 81
		scaleY 81
	)
)

(instance deader2 of View
	(properties
		x 257
		y 75
		view 572
		loop 1
		cel 2
		scaleSignal scalable
		scaleX 110
		scaleY 110
	)
)

(instance deader3 of View
	(properties
		x 227
		y 172
		view 572
		loop 1
		cel 4
		scaleSignal scalable
		scaleX 111
		scaleY 111
	)
)

(instance anubisStatue of Feature
	(properties
		x 193
		y 57
		noun N_STATUE ;N_PEDESTAL	:EO: Corrected noun
		nsTop 46
		nsLeft 174
		nsBottom 69
		nsRight 212
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(if (cast contains: opal)
					(messager say: N_STATUE V_LOOK)
				else
					(messager say: N_CUE V_DOIT C_EYELESS)
				)
			)
			(V_DO
				(if
					(and
						(== climbedUp TRUE)
						(not (Btst fLostCityDoorOpen))
						(not (ego has: iOpal))
					)
					(ego setScript: hoarkOpal)
				)
			)
			(V_FETCH
				(if (not (ego has: iOpal))
					(if (== climbedUp TRUE)
						(messager say: N_CUE V_DOIT C_DONT_NEED_FETCH)
					else
						(AutoTarget 186 53)
						(curRoom setScript: (ScriptID CASTFETCH 0) 0 anubisStatue)
					)
				)
			)
			(-82
				(opal dispose:)
				(ego
					get: iOpal
					solvePuzzle: fGetAnubisEyes 5 (| puzzleWIZARD puzzleTHIEF))
			)
			(else
				(super doVerb: theVerb)
			)
		)
	)
)

(instance pedestal of Feature
	(properties
		x 168
		y 94
		noun N_PEDESTAL
		nsTop 71
		nsLeft 150
		nsBottom 118
		nsRight 186
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO
				(if (== climbedUp 0)
					(ego setScript: climbUpPedestal)
				else
					(ego setScript: climbDownPedestal)
				)
			)
			(else
				(super doVerb: theVerb)
			)
		)
	)
)

(instance stairs of Feature
	(properties
		x 233
		y 104
		noun N_STAIRS
		nsTop 75
		nsLeft 215
		nsBottom 133
		nsRight 252
		sightAngle 180
	)
)

(instance leftStairs of Feature
	(properties
		x 73
		y 102
		noun N_LEFT_STAIRS
		nsTop 82
		nsLeft 53
		nsBottom 122
		nsRight 93
		sightAngle 180
	)
)

(instance ruins of Feature
	(properties
		x 123
		y 47
		noun N_RUINS
		nsTop 31
		nsLeft 87
		nsBottom 63
		nsRight 160
		sightAngle 180
	)
)

(instance rocks of Feature
	(properties
		x 43
		y 122
		noun N_ROCKS
		nsTop 87
		nsLeft 3
		nsBottom 158
		nsRight 83
		sightAngle 180
	)
)

(instance noWayToGo of Feature
	(properties
		x 280
		y 151
		noun N_NO_WAY_TO_GO
		nsTop 114
		nsLeft 242
		nsBottom 189
		nsRight 319
		sightAngle 180
	)
)

(instance sFx of Sound)

(instance fallChek of Code
	
	(method (doit)
		(if (or (ego inRect: 156 73 218 76) (< (ego x?) 156))
			(ego setScript: egoFalls)
		)
	)
)

(instance quikChek of Code
	
	(method (doit)
		(cond 
			((ego inRect: 0 152 36 200)
				(ego setScript: cantExit)
			)
			((and (Btst fLostCityDoorOpen) (ego inRect: 115 118 134 120))
				(= monsterNum vApeman)
				(= monsterHealth 180)
				(curRoom newRoom: 820)
			)
		)
	)
)

(instance anubisInset of Inset
	(properties
		view 810
		x 160
		y 135
		disposeNotOnMe TRUE
		noun 11
	)
	
	(method (dispose)
		(ego code: quikChek)
		(theIconBar enable: ICON_CAST ICON_WALK ICON_ACTIONS ICON_DO)
		(if (Btst fLostCityDoorOpen)
			(opal dispose:)
		)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_OPAL
				(ego
					drop: iOpal
					solvePuzzle: fOpenLostCityDoor 8
				)
				(opal view: 810 loop: 1 setPri: 14 x: 158 y: 83 init:)
				(Animate (cast elements?) TRUE)
				(doorWay
					loop: 2
					ignoreActors: TRUE
					approachY: 110
				)
				(Bset fLostCityDoorOpen)
				(self dispose:)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)
