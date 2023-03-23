;;; Sierra Script 1.0 - (do not remove this comment)
(script# 73)
(include game.sh) (include "73.shm")
(use Main)
(use CastDart)
(use ThrowKnife)
(use ThrowRock)
(use CastDazzle)
(use Target)
(use Procs)
(use Talker)
(use PolyPath)
(use Polygon)
(use Feature)
(use Sound)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm73 0
	brutusTalker 1
	brunoTalker 2
)

(local
	local0
	local1
	brunoHere
	startCombat
	brutusDead
	afterCombat
	startThrowing
	newDagger
	egoSide
	armorValue
	maxDags =  55
	distFromTarget
	local12	;unused
	local13
	local14
	local15
	theKiller
	local17	;unused
	saveSpeed
)
(procedure (SetUpRoom)
	;EO: This procedure has been successfully decompiled! Let's test it.
	(cond 
		(
			(= brunoHere
				(if (and
						(Btst fBearGone)
						(not (Btst fSpiedOnThieves))
						(== timeODay TIME_MIDDAY)
						(or (== prevRoomNum 72) (== prevRoomNum 74))
					)
				)
			)
			(= monsterHealth 100)
			(= brutusHealth 100)
			(= monsterNum vBrigand)
			(Load VIEW 65)
			(Load VIEW 73)
		)
		((= brutusDead (if (not (Btst fGotBrutusKey)) (Btst fBeatBrutus)))
			(Load VIEW 73)
		)
		(
			(= startThrowing
				(if
					(and
						(Btst fSpiedOnThieves)
						(or (== prevRoomNum 72) (== prevRoomNum 74))
					)
					(not (Btst fBeatBrutus))
				)
			)
			(Load VIEW 73)
			(= monsterHealth brutusHealth)
			(= monsterNum vBrigand)
		)
		(
			(= startCombat
				(if
					(and (Btst fSpiedOnThieves) (== prevRoomNum 80))
					(not (Btst fBeatBrutus))
				)
			)
			(Load VIEW 73)
			(= monsterHealth brutusHealth)
			(= monsterNum vBrigand)
			(= brigandHead 6)
			(brutus posn: 128 135)
		)
		((= afterCombat (== prevRoomNum vBrigand))
			(Bset fBeatBrutus)
			(Load VIEW 466)
		)
	)
	(cond 
		(brunoHere
			(bruno init:)
			(brutus init:)
		)
		(startThrowing
			(brutus init: setLoop: 2)
		)
		(startCombat
			(brutus init: setLoop: 2)
		)
		(brutusDead
			(brutus
				view: 73
				setLoop: 7
				setCel: 8
				init:
				stopUpd:
			)
			(= local1 1)
			(dags add:)
		)
		(afterCombat
			(if (not (Btst fGotBrutusKey))
				(= monsterNum 0)
				(brutus init:)
			)
		)
		((or (== prevRoomNum 72) (== prevRoomNum 74))
			(dags add:)
			(= local1 1)
		)
		(else
			(HandsOn)
		)
	)
)

(instance dags of Set)

(instance rm73 of Room
	(properties
		noun N_ROOM
		picture 73
		style IRISOUT
	)
	
	(method (init)
		(Load RES_SOUND 31 29)
		(Load RES_SCRIPT 101)
		(super init: &rest)
		(if targetDaggers
			(phonyDagger init: stopUpd:)
		)
		(features add: target wall buildings trees appleTree)
		;uPGRADE
;;;		(target init:)
;;;		(wall init:)
;;;		(buildings init:)
;;;		(trees
;;;			init:
;;;			setOnMeCheck: ftrControl cBLUE
;;;		)
;;;		(appleTree
;;;			init:
;;;			setOnMeCheck: ftrControl cBLUE
;;;		)
		
		(= armorValue 0)
		(cond 
			((ego has: iChainmail)
				(= armorValue CHAIN_VALUE)
			)
			((ego has: iLeather)
				(= armorValue LEATHER_VALUE)
			)
		)
		(brutus targDeltaX:
			(switch egoSide
				(1 12)
				(-1 -12)
			)
		)
		(SetUpRoom)
		(switch prevRoomNum
			(72
				(self
					addObstacle:
						((Polygon new:)
							type: PBarredAccess
							init:
								0 0
								319 0
								319 189
								25 189
								6 135
								26 126
								0 126
							yourself:
						)
				)
				(self setScript: sEnterFromWest)
			)
			(74
				(self
					addObstacle:
						((Polygon new:)
							type: PBarredAccess
							init:
								284 113
								284 189
								0 189
								0 0
								319 0
								319 113
							yourself:
						)
				)
				(self setScript: sEnterFromEast)
			)
			(vBrigand
				(self
					addObstacle:
						((Polygon new:)
							type: PBarredAccess
							init:
								108 124
								122 138
								106 153
								106 189
								0 189
								0 0
								319 0
								319 189
								170 189
								175 171
								202 139
								212 115
								144 122
								152 139
								130 139
								115 124
							yourself:
						)
				)
				(brutus init: setLoop: 2 setScript: brutusDies)
			)
			(else 
				(self
					addObstacle:
						((Polygon new:)
							type: PBarredAccess
							init:
								108 124
								122 138
								106 153
								106 189
								0 189
								0 0
								319 0
								319 189
								170 189
								175 171
								202 139
								212 115
								144 122
								152 139
								130 139
								115 124
							yourself:
						)
				)
				(self setScript: sEnterFromSouth)
			)
		)
	)
	
	(method (doit)
		(cond 
			(script)
			((== (ego edgeHit?) EAST)
				(HandsOff)
				(curRoom setScript: sExitEast)
			)
			((== (ego edgeHit?) WEST)
				(HandsOff)
				(curRoom setScript: sExitWest)
			)
			((== (ego edgeHit?) SOUTH)
				(curRoom setScript: sExitSouth)
			)
		)
		(super doit:)
	)
	
	(method (dispose &tmp i)
		(= nightPalette 0)
		(dags eachElementDo: #dispose 81 release:)
		(for ((= i 0)) (< i targetDaggers) ((++ i))
			(if
				(and
					(cast contains: [newDagger i])
					(IsObject [newDagger i])
				)
				([newDagger i]
					dispose:
					delete:
				)
			)
		)
		(dags dispose:)
		(if (!= newRoomNum vBrigand)
			(= monsterNum 0)
			(= brigandHead 0)
		)
		(super dispose:)
	)
	
	(method (doVerb theVerb &tmp rockTarg)
		(switch theVerb
			(V_DO
				(if targetDaggers
					(messager say: N_ROOM V_DO NULL)
				)
			)
			(V_ROCK
				(= rockTarg 0)
				(if (and (cast contains: brutus) (not (Btst fBeatBrutus)))
					(Face ego brutus)
					(Face brutus ego)
					(= rockTarg brutus)
				)
				(ThrowRock rockTarg)
			)
			(V_DAGGER
				(ThrowKnife 0)
			)
			(V_DETECT
				(messager say: N_ROOM V_DETECT 0)
			)
			(V_DAZZLE
				(if (or (Btst fBeatBrutus) (== egoSide 0))
					(CastDazz)
					(messager say: N_BRUTUS V_DAZZLE 0)
				else
					(messager say: N_ROOM V_DAZZLE 0)
				)
			)
			(V_FLAME
				(if (or (Btst fBeatBrutus) (not (cast contains: brutus)))
					(CastDart 0)
				else
					(Face brutus ego)
					(CastDart brutus)
				)
			)
			(V_CALM
				(if (or (Btst fBeatBrutus) (not (cast contains: brutus)))
					(messager say: N_ROOM V_CALM 0)
				else
					(messager say: N_ROOM V_CALM C_CANTCALM)
				)
			)
			(V_OPEN
				(messager say: N_ROOM V_OPEN 0)
			)
			(V_ZAP
				;EO: The original programmers mistakenly assigned this to Trigger. Now it's Zap.
				(cond 
					((or (Btst fBeatBrutus) (not (cast contains: brutus)))
						(= zapPower (+ 5 (/ [egoStats ZAP] 10)))
					)
					((or (ego has: iDagger) (ego has: iSword))
						(messager say: N_ROOM V_TRIGGER)
					)
					(else
						(messager say: N_ROOM V_TRIGGER C_NOWEAPON)
					)
				)
			)
			(V_FETCH
				(if (or (Btst fBeatBrutus) (not (cast contains: brutus)))
					(messager say: N_ROOM V_FETCH 0)
				else
					(messager say: N_ROOM V_FETCH C_BRUTUSHERE)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance target of Feature
	(properties
		x 158
		y 88
		noun N_TARGET
		nsTop 59
		nsLeft 130
		nsBottom 108
		nsRight 180
		sightAngle 40
		approachX 140
		approachY 122
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(if targetDaggers
					(messager say: N_TARGET V_LOOK C_TARGETHASDAGS)
				else
					(messager say: N_TARGET V_LOOK C_NODAGSONTARGET)
				)
			)
			(V_DO
				(cond 
					((!= egoSide 0)
						(messager say: N_TARGET V_DAGGER NULL)
					)
					(targetDaggers
						(curRoom setScript: getYourDags 0 0)
					)
					(else
						(curRoom setScript: getYourDags 0 1)
					)
				)
			)
			(V_DAGGER
				(if (or (== prevRoomNum 72) (== prevRoomNum 74))
					(messager say: N_TARGET V_DAGGER NULL)
				else
					(curRoom setScript: sThrowDagger)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance wall of Feature
	(properties
		x 159
		y 57
		noun N_WALL
		nsTop 13
		nsBottom 101
		nsRight 318
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO
				(if Night
					(if (TrySkill CLIMB 35 0)
						(curRoom setScript: sClimbWall)
					else
						(messager say: N_WALL V_DO C_CLIMBFAIL)
					)
				else
					(messager say: N_WALL V_DO C_DAY)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance buildings of Feature
	(properties
		x 153
		y 6
		noun N_BUILDINGS
		nsLeft 73
		nsBottom 12
		nsRight 233
		sightAngle 40
	)
)

(instance trees of Feature
	(properties
		x 153
		y 180
		noun N_TREES
		sightAngle 40
		onMeCheck cBLUE
	)
)

(instance knife1 of Actor
	(properties
		noun N_TARGET
		view 65
	)
	
	(method (doVerb theVerb)
		(target doVerb: theVerb &rest)
	)
)

(instance knife2 of Actor
	(properties
		noun N_TARGET
		view 65
	)
	
	(method (doVerb theVerb)
		(target doVerb: theVerb &rest)
	)
)

(class Dagger of View
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(if targetDaggers
					(messager say: N_TARGET V_LOOK C_TARGETHASDAGS)
				else
					(messager say: N_TARGET V_LOOK C_NODAGSONTARGET)
				)
			)
			(V_DO
				(curRoom setScript: getYourDags 0 0)
			)
			(V_DAGGER
				(if (or (== prevRoomNum 72) (== prevRoomNum 74))
					(messager say: N_TARGET V_DAGGER 0)
				else
					(curRoom setScript: sThrowDagger)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance bruno of Actor
	(properties
		x 119
		y 123
		noun N_BRUNO
		view 65
		loop 4
	)
)

(instance killTheBum of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(brutus
					view: 73
					setCycle: Walk
					setCel: 0
					setScript: 0
					setMotion: PolyPath (brutus x?) (- (brutus y?) 2)
				)
				(knife1
					illegalBits: 0
					setLoop: 7
					ignoreActors: 1
					ignoreHorizon:
					xStep: 6
					yStep: 7
					init:
					setPri: (- (ego priority?) 1)
					setCycle: Forward
					posn: (ego x?) (ego y?)
					setMotion: MoveTo (brutus x?) (- (brutus y?) 22) self
				)
			)
			(1
				(knife1 hide: dispose:)
				(brutus
					setLoop: 8
					setCel: 0
					posn: (brutus x?) (+ (brutus y?) 2)
					setCycle: EndLoop self
				)
			)
			(2
				(brutus stopUpd:)
				(dags add:)
				(= local13 0)
				(Bset fBeatBrutus)
				(= seconds 2)
			)
			(3
				(HandsOn)
				(NormalEgo)
				(self dispose:)
			)
		)
	)
)

(instance climbOverWall of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(messager say: N_WALL V_DO C_CLIMBSUCCESS 0 self)
			)
			(1
				(curRoom newRoom: 300)
			)
		)
	)
)

(instance brunoTalker of Talker
	(properties
		x 10
		y 10
		view 1074
		talkWidth 260
		textX 15
		textY 110
	)
	
	(method (init)
		(= font userFont)
		(= nightPalette 2074)
		(PalVary PALVARYTARGET 2074)
		(AssertPalette 1074)
		(super init: brunoBust brunoEyes brunoTalkerMouth &rest)
	)
)

(instance brunoBust of Prop
	(properties
		view 1074
	)
)

(instance brunoTalkerMouth of Prop
	(properties
		nsTop 47
		nsLeft 43
		view 1074
		loop 1
	)
)

(instance brunoEyes of Prop
	(properties
		nsTop 26
		nsLeft 43
		view 1074
		loop 3
	)
)

(instance throwSound of Sound
	(properties
		number 31
	)
)

(instance hitSound of Sound
	(properties
		number 29
	)
)

(instance sExitEast of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: MoveTo 350 (ego y?) self)
			)
			(1
				(curRoom newRoom: 74)
			)
		)
	)
)

(instance sExitSouth of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: MoveTo (ego x?) 245 self)
			)
			(1
				(NormalEgo)
				(curRoom newRoom: 80)
			)
		)
	)
)

(instance sExitWest of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: MoveTo -25 (ego y?) self)
			)
			(1
				(curRoom newRoom: 72)
			)
		)
	)
)

(instance sEnterFromWest of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					init:
					posn: -25 144
					setMotion: MoveTo 18 144
					self
				)
				(= egoSide -1)
			)
			(1
				(HandsOn)
				(NormalEgo)
				(cond 
					(brunoHere
						(curRoom setScript: brigsMeet)
					)
					((and (< timeODay TIME_SUNSET) (or startThrowing (Btst fBrutusWaits)))
						(curRoom setScript: brutusThrows)
					)
				)
				(self dispose:)
			)
		)
	)
)

(instance sEnterFromEast of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego init: posn: 347 148 setMotion: MoveTo 296 148 self)
				(= egoSide 1)
			)
			(1
				(HandsOn)
				(NormalEgo)
				(cond 
					(brunoHere
						(curRoom setScript: brigsMeet)
					)
					((and (< timeODay TIME_SUNSET) (or startThrowing (Btst fBrutusWaits)))
						(curRoom setScript: brutusThrows)
					)
				)
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
				(ego init: posn: 137 246 setMotion: MoveTo 137 184 self)
				(= egoSide 0)
			)
			(1
				(HandsOn)
				(NormalEgo)
				(if startCombat
					(curRoom setScript: brutusLives)
				)
				(self dispose:)
			)
		)
	)
)

(instance sClimbWall of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(Load RES_VIEW 517)
				(if modelessDialog
					(modelessDialog dispose:)
				)
				(NormalEgo)
				(if (== egoSide 0)
					(ego setMotion: PolyPath 120 124 self)
				else
					(= ticks 20)
				)
			)
			(1
				(messager say: N_WALL V_DO C_CLIMBSUCCESS 0 self)
			)
			(2
				(switch egoSide
					(0
						(ego setMotion: MoveTo 98 122 self)
					)
					(-1
						(ego setMotion: MoveTo 26 123 self)
					)
					(1
						(ego setMotion: MoveTo 293 107 self)
					)
				)
			)
			(3
				(switch egoSide
					(0 (ego setPri: 3))
					(-1 (ego setPri: 1))
					(1 (ego setPri: 1))
				)
				(ego setHeading: 360)
				(= ticks 30)
			)
			(4
				(ego view: 517 setLoop: 0 setCel: 0)
				(switch egoSide
					(0
						(ego posn: 99 94)
						(= ticks 10)
					)
					(-1
						(= state 16)
						(ego
							posn: 26 94
							setCycle: Forward
							setMotion: MoveTo 26 44 self
						)
					)
					(1
						(= state 16)
						(ego
							posn: 293 78
							setCycle: Forward
							setMotion: MoveTo 293 34 self
						)
					)
				)
			)
			(5
				(ego setCel: 1 posn: 99 85)
				(= ticks 10)
			)
			(6
				(ego setCel: 2 posn: 98 76)
				(= ticks 10)
			)
			(7
				(ego setCel: 3 posn: 99 67)
				(= ticks 10)
			)
			(8
				(ego setCel: 4 posn: 99 56)
				(= ticks 10)
			)
			(9
				(ego setCel: 5)
				(= ticks 10)
			)
			(10
				(ego setCel: 6 posn: 97 50)
				(= ticks 10)
			)
			(11
				(ego setLoop: 1 setCel: 0 posn: 99 37)
				(= ticks 10)
			)
			(12 (ego setCycle: EndLoop self))
			(13
				(ego setLoop: 8 setCel: 6 setPri: 1 posn: 93 39)
				(= ticks 10)
			)
			(14 (ego setCycle: BegLoop self))
			(15 (= ticks 20))
			(16
				(ego posn: 1000 1000)
				(= ticks 10)
			)
			(17
				(Bset fBeenIn300)
				(ego setCycle: 0)
				(curRoom newRoom: 300)
			)
		)
	)
)

(instance brutusWaits of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Bset fBrutusWaits)
				(= ticks 300)
			)
			(1
				(Bclr fBrutusWaits)
				(= seconds brunoTimer)
			)
			(2
				(if (not (Btst fBeatBrutus))
					(curRoom setScript: brutusThrows)
				else
					(self dispose:)
				)
			)
		)
	)
)

(instance brutusLives of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(brutus
					setCycle: Walk
					ignoreActors: TRUE
					setMotion: MoveTo (ego x?) (- (ego y?) 20) self
				)
			)
			(1
				(messager say: N_ROOM NULL C_BRUTUSATTACKS 0 self)
			)
			(2
				(curRoom newRoom: vBrigand)
			)
		)
	)
)

(instance egoLoses of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= cycles 2)
			)
			(1
				(if register
					(= register 0)
					(= seconds (Random 3 6))
				else
					(HandsOff)
					(= cycles 2)
				)
			)
			(2
				(HandsOff)
				(ego setMotion: 0)
				(Face ego brutus)
				(bruno setCycle: 0 setMotion: 0)
				(if (not (Btst fBeatBrutus))
					(brutus
						setScript: 0
						setLoop: (if (> (ego x?) 160) 5 else 4)
						cel: 0
						setCycle: CycleTo 4 1 self
					)
				else
					(HandsOn)
					(self dispose:)
				)
			)
			(3
				(knife1
					illegalBits: 0
					setLoop: 7
					ignoreActors:
					ignoreHorizon:
					xStep: 6
					yStep: 7
					init:
					setPri: (- (ego priority?) 1)
					setCycle: Forward
					posn: (+ (bruno x?) (* egoSide 41)) (- (bruno y?) 24)
					setMotion: MoveTo (ego x?) (- (ego y?) 22)
				)
				(brutus setCycle: CycleTo 5 1 self)
			)
			(4
				(if (and (cast contains: bruno) (< (ego x?) 160))
					(bruno setLoop: 5 setCycle: EndLoop self)
					(knife2
						illegalBits: 0
						setLoop: 7
						ignoreActors: TRUE
						ignoreHorizon:
						xStep: 6
						yStep: 7
						setPri: (- (ego priority?) 1)
						init:
						setCycle: Forward
						posn: (+ (bruno x?) (* 41 egoSide)) (- (bruno y?) 27)
						moveSpeed: 1
						setStep: 5 5
						setMotion: MoveTo (ego x?) (- (ego y?) 25) self
					)
				else
					(brutus setCycle: EndLoop)
					(knife2
						illegalBits: 0
						setLoop: 7
						ignoreActors:
						ignoreHorizon:
						xStep: 6
						yStep: 7
						setPri: (- (ego priority?) 1)
						init:
						setCycle: Forward
						posn: (+ (brutus x?) (* 41 egoSide)) (- (brutus y?) 27)
						setMotion: MoveTo (ego x?) (- (ego y?) 25) self
					)
				)
				(ego setLoop: 1)
			)
			(5
				(knife1 dispose:)
				(knife2 dispose:)
				(if (== theKiller bruno)
					(bruno setLoop: 5 setCel: 0 setCycle: EndLoop)
				)
				(if (and (not (Btst fBeatBrutus)) (== theKiller brutus))
					(brutus setLoop: 6 setCel: 0 setCycle: EndLoop)
				)
				(ego view: 516 setLoop: 3 setMotion: 0 setCycle: EndLoop self)
			)
			(6
				(= seconds 4)
			)
			(7
				(EgoDead 45 46 2 5 516)
			)
		)
	)
)

(instance brigsMeet of Script
	(method (changeState newState)
		(if client
			(switch (= state newState)
				(0
					(Bset fSpiedOnThieves)
					(HandsOff)
					(brutus setLoop: 4 setCycle: Forward)
					(= ticks 60)
				)
				(1
					(cast eachElementDo: #stopUpd)
					(= ticks 30)
				)
				(2
					(messager say: N_ROOM NULL C_BRIGSMEET 0 self)
				)
				(3
					(messager say: N_ROOM NULL C_BACKDOOR 0 self)
				)
				(4
					(messager say: N_ROOM NULL C_PASSWORD 0 self)
				)
				(5
					(SolvePuzzle f73OverhearBruno 12)
					(cast eachElementDo: #startUpd)
					(brutus setLoop: 3 setCel: 0 setCycle: 0)
					(bruno
						illegalBits: 0
						ignoreActors: 1
						setLoop: -1
						setCycle: Walk
						setMotion: MoveTo 170 (bruno y?) self
					)
				)
				(6
					(brutus setLoop: 0)
					(= ticks 30)
					(bruno setMotion: MoveTo (- (bruno x?) 10) 230 self)
				)
				(7 (brutus setLoop: 2))
				(8
					(bruno dispose:)
					(HandsOn)
					(NormalEgo)
					(= brunoTimer 300)
					(brutus setScript: brutusWaits)
					(self dispose:)
				)
			)
		)
	)
)

(instance brutus of TargActor
	(properties
		x 131
		y 135
		noun N_BRUTUS
		view 73
		loop 4
		signal (| ignrAct ignrHrz)
	)
	
	(method (doVerb theVerb &tmp rockTarg)
		(switch theVerb
			(V_LOOK
				(if (Btst fBeatBrutus)
					(messager say: N_BRUTUS V_LOOK NULL)
				else
					(messager say: N_BRUTUS V_LOOK C_BRUTUSHERE)
				)
			)
			(V_TALK
				(brutus setMotion: 0)
				(if (not (Btst fBeatBrutus))
					(brutus setScript: brutusThrows)
				else
					(messager say: N_BRUNO V_LOOK)
				)
			)
			(V_DO
				(if (Btst fBeatBrutus)
					(if (ego inRect: 90 92 230 188)
						(curRoom setScript: egoSearch)
					else
						(messager say: N_BRUTUS V_DO C_NOBODYHERE)
					)
				else
					(messager say: N_BRUTUS V_DO C_BRUTUSHERE)
				)
			)
			(V_ROCK
				(= rockTarg 0)
				(if (and (cast contains: brutus) (not (Btst fBeatBrutus)))
					(Face ego brutus)
					(Face brutus ego)
					(= rockTarg brutus)
				)
				(Face brutus ego)
				(ThrowRock rockTarg)
			)
			(V_DAGGER
				(if (not (Btst fBeatBrutus))
					(Bset fDaggerInBrutus)
					(ThrowKnife brutus)
				else
					;The MSG file used the wrong noun, so it needs to be used here.
					(messager say: N_BRUNO V_DAGGER)
				)
			)
			(V_DETECT
				(messager say: N_BRUTUS V_DETECT NULL)
			)
			(V_FLAME
				(if (or (not (cast contains: brutus)) (Btst fBeatBrutus))
					(CastDart 0)
				else
					(= theKiller brutus)
					(Face brutus ego)
					(CastDart brutus)
					(messager say: N_BRUTUS V_FLAME)
				)
			)
			(V_CALM
				(if (and (cast contains: brutus) (not (Btst fBeatBrutus)))
					(messager say: N_BRUTUS V_CALM NULL 2)
				else
					(messager say: N_BRUTUS V_CALM NULL 1)
				)
			)
			(V_OPEN
				(messager say: N_BRUNO V_OPEN NULL)
			)
			(V_ZAP	;EO: The original programmers mistakenly assigned this to Trigger. Now it's Zap.
				(cond 
					((or (not (cast contains: brutus)) (Btst fBeatBrutus))
						(= zapPower (+ 5 (/ [egoStats ZAP] 10)))
					)
					((or (ego has: iDagger) (ego has: iSword))
						(messager say: N_BRUTUS V_TRIGGER NULL)
					)
					(else
						(messager say: N_BRUTUS V_TRIGGER C_NOWEAPON)
					)
				)
			)
			(V_FETCH
				(if (or (not (cast contains: brutus)) (Btst fBeatBrutus))
					(messager say: N_BRUTUS V_FETCH C_NOBODYHERE)
				else
					(messager say: N_BRUTUS V_FETCH C_BRUTUSHERE)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
	
	(method (getHurt)
		(if (not (Btst fBeatBrutus))
			(= zapPower 0)
			(= monsterNum 0)
			(Bset fBeatBrutus)
			(brutus setScript: brutusDies)
		else
			(messager say: N_BRUTUS V_DAZZLE)
			;UPGRADE FROM MAC: Replace with message "Brutus is already dead."
		)
		(= brutusHealth monsterHealth)
	)
)

(instance brutusDies of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if local15
					(knife1 dispose:)
					(knife2 dispose:)
				)
				(if (== prevRoomNum vBrigand)
					(ego init: posn: 146 161 setHeading: 360)
					(brutus view: 73 setCel: 0 loop: 7 setCycle: EndLoop self)
				else
					(brutus view: 73 setCel: 0 setLoop: 7 setCycle: EndLoop self)
				)
			)
			(1
				(= ticks 60)
			)
			(2
				(= local13 0)
				(brutus stopUpd:)
				(dags add:)
				(HandsOn)
				(NormalEgo)
				(self dispose:)
			)
		)
	)
)

(instance phonyDagger of View
	(properties
		x 161
		y 83
		view 65
		loop 6
		signal (| ignrAct ignrHrz fixedLoop)
	)
)

(instance appleTree of Feature
	(properties
		x 58
		y 259
		z 100
		nsTop 130
		nsLeft 10
		nsBottom 189
		nsRight 107
		sightAngle 40
		onMeCheck cBLUE
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(messager say: N_TREES V_LOOK C_APPLETREE)
			)
			(V_DO
				(messager say: N_TREES V_DO C_APPLETREE)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance egoSearch of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if (ego inRect: 100 121 148 146)
					(ego
						ignoreActors: TRUE
						illegalBits: 0
						setMotion: MoveTo 170 140 self
					)
				else
					(= ticks 10)
				)
			)
			(1
				(ego
					ignoreActors: TRUE
					illegalBits: 0
					setMotion: MoveTo 145 120 self
				)
			)
			(2
				(Face ego brutus self)
			)
			(3
				(ego
					loop: (mod (+ (ego loop?) 4) 2)
					view: 510
					setCycle: EndLoop self
				)
			)
			(4
				(cond 
					((Btst fDaggerInBrutus)
						(= register 1)
						(Bclr fDaggerInBrutus) 
						(messager say: N_ROOM NULL C_GET1DAGGER)
					)
					((Btst fGotBrutusKey)
						(messager say: N_ROOM V_SEARCH C_BRUTUSATTACKS)
					)
					(else
						(= register 2)
						(messager say: N_ROOM V_SEARCH C_FINDKEY)
						(Bset fGotBrutusKey)
					)
				)
				(= seconds 2)
			)
			(5
				(cond 
					((== register 1)
						(ego get: iDagger 1)
					)
					((== register 2)
						(ego get: iBrassKey)
					)
				)
				(= register 0)
				(NormalEgo)
				(ego
					illegalBits: 0
					ignoreActors: TRUE
					setMotion: MoveTo 160 127 self
				)
			)
			(6
				(ego setHeading: 270 self)
			)
			(7
				(= cycles 2)
			)
			(8
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance brutusTalker of Talker
	(properties
		x 10
		y 10
		view 1073
		talkWidth 260
		textX 15
		textY 110
	)
	
	(method (init)
		(= nightPalette 2073)
		(= font userFont)
		(PalVary PALVARYTARGET 2073)
		(AssertPalette 1073)
		(super
			init: brutusBust brutusEyes brutusTalkerMouth &rest
		)
	)
)

(instance brutusBust of Prop
	(properties
		view 1073
	)
)

(instance brutusTalkerMouth of Prop
	(properties
		nsTop 51
		nsLeft 31
		view 1073
		loop 1
	)
)

(instance brutusEyes of Prop
	(properties
		nsTop 24
		nsLeft 28
		view 1073
		loop 2
	)
)

(instance sThrowDagger of Script
	(method (changeState newState &tmp temp0 temp1 temp2 temp3)
		(switch (= state newState)
			(0
				(HandsOff)
				(= saveSpeed (ego cycleSpeed?))
				(ego illegalBits: 0 ignoreActors: 1)
				(if (!= (ego x?) 212)
					(ego
						setMotion: PolyPath 212 (if (> distFromTarget 125) distFromTarget else 151) self
					)
				else
					(= cycles 2)
				)
			)
			(1
				(ego view: 510 cycleSpeed: 6 setLoop: 2 cel: 0)
				(= ticks 30)
			)
			(2
				(ego use: iDagger 1)
				(++ targetDaggers)
				(switch (Random 0 5)
					(1 (SkillUsed THROW 1))
					(2 (SkillUsed WEAPON 1))
				)
				(= temp3 (/ (- 120 [egoStats THROW]) 10))
				(= temp1 (- (Random 0 temp3) (/ temp3 2)))
				(= temp2
					(+ (= temp2 (- (Random 0 temp3) (/ temp3 2))) 20)
				)
				(ego setCycle: EndLoop self)
				(knife1
					illegalBits: 0
					setLoop: 7
					ignoreActors:
					ignoreHorizon:
					xStep: 6
					yStep: 7
					init:
					setPri: (- (ego priority?) 1)
					setCycle: Forward
					posn: (+ (ego x?) 30) (- (ego y?) 24)
					setMotion:
						MoveTo
						(+ (target x?) temp1)
						(+ (- (target y?) 22) temp2)
						self
				)
			)
			(3
				(throwSound play: 80)
			)
			(4
				(if (< targetDaggers maxDags)
					(hitSound play: 80)
					(= temp0 (Random 0 3))
					((= newDagger (Dagger new:))
						view: 65
						loop: 7
						cel: temp0
						posn: (knife1 x?) (knife1 y?)
						init:
						stopUpd:
					)
					(dags add: newDagger)
				)
				(knife1 dispose:)
				(NormalEgo)
				(ego loop: 3)
				(ego cycleSpeed: saveSpeed)
				(HandsOn)
				(TrySkill THROW 0 20)
				(self dispose:)
			)
		)
	)
)

(instance brutusThrows of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: 0)
				(Face ego brutus)
				(brutus
					setLoop: (if (> (ego x?) 160) 5 else 6)	;chaged 4 to 6 to fix a graphic bug
					setCel: 0
					setCycle: CycleTo 4 1 self
				)
			)
			(1
				(brutus setCel: 5)
				(self cue:)
			)
			(2
				(if local14
					(self cue:)
				else
					(brutus setCel: 5 setCycle: EndLoop self)
					(= local13 1)
				)
			)
			(3
				(if (not local14)
					(= local15 1)
					(User canControl: FALSE)
					(HandsOff)
					(ego setMotion: 0)
				)
				(knife1
					illegalBits: 0
					setLoop: 7
					ignoreActors:
					ignoreHorizon:
					xStep: 6
					yStep: 7
					init:
					setPri: (- (ego priority?) 1)
					setCycle: Forward
					setStep: 5 5
					posn: (+ (brutus x?) (* egoSide 32)) (- (brutus y?) 20)
					setMotion: MoveTo (ego x?) (- (ego y?) 22) self
				)
			)
			(4
				(if local15
					(= local15 0)
					(knife1 dispose:)
					(if (not (TakeDamage (- 10 armorValue)))
						(self changeState: 7)
					)
				)
				(knife1 setCycle: 0)
				(ego
					view: 513
					setLoop: (if (== egoSide 1) 3 else 2)
					setMotion: 0
					setCycle: EndLoop self
				)
			)
			(5
				(NormalEgo)
				(Face ego brutus)
				(= local13 0)
				(self changeState: 7)
			)
			(6 (self changeState: 0))
			(7
				(HandsOff)
				(ego
					view: 516
					setLoop: (if (== egoSide 1) 1 else 0)
					setCel: 0
					setMotion: 0
					setCycle: EndLoop self
				)
			)
			(8 (= seconds 3))
			(9 (EgoDead 45 46 2 5 516))
		)
	)
)

(instance getYourDags of Script
	(method (changeState newState &tmp [temp0 3] i)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego ignoreActors: TRUE illegalBits: 0)
				(if (or (< (ego y?) 138) (ego inRect: 100 121 148 146))
					(ego setMotion: MoveTo 170 140 self)
				else
					(= ticks 10)
				)
			)
			(1
				(ego setMotion: MoveTo 160 122 self)
			)
			(2 (ego setHeading: 300 self))
			(3 (= ticks 10))
			(4
				(if register
					(messager say: N_TARGET V_DO)
					(HandsOn)
					(self dispose:)
				else
					(ego get: iDagger targetDaggers)
					(= cycles 2)
				)
			)
			(5
				(if (cast contains: phonyDagger)
					(phonyDagger dispose:)
				)
				(if (< local0 2)
					(messager say: N_ROOM NULL C_GET1DAGGER 0 self)
				else
					(messager say: N_ROOM NULL C_GETMULTIPLEDAGGERS 0 self)
				)
				(= targetDaggers 0)
			)
			(6
				(for ((= i 0)) (< i targetDaggers) ((++ i))
					(if
						(and
							(cast contains: [newDagger i])
							(IsObject [newDagger i])
						)
						([newDagger i] dispose: delete:)
					)
				)
				(dags eachElementDo: #dispose 81 release:)
				(= seconds 2)
			)
			(7
				(dags add:)
				(ego
					setMotion: PolyPath 212 (if (> distFromTarget 125) distFromTarget else 151) self
				)
			)
			(8
				(NormalEgo)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)
