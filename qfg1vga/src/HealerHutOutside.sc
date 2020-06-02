;;; Sierra Script 1.0 - (do not remove this comment)
(script# 54)
(include game.sh) (include "54.shm")
(use Main)
(use CastFlame)
(use CastDagger)
(use CastRock)
(use CastDazzle)
(use Target)
(use Procs)
(use Print)
(use PolyPath)
(use Polygon)
(use Feature)
(use LoadMany)
(use Reverse)
(use Timer)
(use Sound)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm54 0
)

(local
	local0
	local1
	local2
	gEgoCycleSpeed
	gEgoMoveSpeed
	local5
	local6
	local7
	local8
	local9
	local10
	local11
)
(procedure (ThrowRock)
	(cond 
		((and (not local0) (Btst fClimbedTree)) (curRoom setScript: sFallOutOfTree))
		((Btst fClimbedTree) (messager say: N_ROOM V_DO C_UPTREE))
		(else (= local2 1) (CastRock 0))
	)
)

(instance roomTimer of Timer
	(properties)
)

(instance ringTimer of Timer
	(properties)
)

(instance rm54 of Room
	(properties
		noun N_ROOM
		picture 54
		style DISSOLVE
		horizon 1
	)
	
	(method (init)
		(self
			addObstacle:
				((Polygon new:)
					type: 2
					init: 120 0 319 0 319 55 160 149 103 120 129 102 120 94
					yourself:
				)
				((Polygon new:)
					type: 2
					init:
						0
						135
						30
						135
						95
						148
						116
						167
						116
						172
						92
						172
						92
						184
						56
						184
						50
						176
						38
						176
						22
						189
						0
						189
					yourself:
				)
				((Polygon new:)
					type: 2
					init: 0 0 91 0 91 112 0 112
					yourself:
				)
				((Polygon new:)
					type: 2
					init: 249 189 218 163 319 146 319 189
					yourself:
				)
				((Polygon new:)
					type: 2
					init: 319 140 190 154 180 151 319 71
					yourself:
				)
		)
		(LoadMany RES_VIEW 55 510)
		(super init:)
		(StatusLine enable:)
		(door
			cycleSpeed: 2
			setPri: 8
			init:
			approachVerbs: 4
			stopUpd:
		)
		(if (not (Btst OBTAINED_RING))
			(theRing init: setCel: pBLACK setPri: pWHITE)
			(ringTimer setReal: theRing (Random 10 20))
		)
		(if (== nestState 0) (nest init: stopUpd:))
		(if (not (Btst PTERESA_LEFT_NEST))
			(bird
				ignoreActors:
				init:
				cycleSpeed: 1
				stopUpd:
				setScript: sFlutter
			)
			(birdFeature init:)
		)
		(switch prevRoomNum
			(37
				(self setScript: sEnterFrom37)
			)
			(53
				(self setScript: sEnterFromWest)
			)
			(55
				(curRoom setScript: sFromInsideHouse)
			)
			(56
				(self setScript: sEnterFromEast)
			)
			(else 
				(self setScript: sEnterFromSouth)
			)
		)
		(theSign init:)
		(hut init: approachVerbs: V_DO)
		(gardenL init: approachVerbs: V_DO)
		(gardenR init: approachVerbs: V_DO)
		(castle init:)
		(tree init: approachVerbs: V_DO)
		(ground init:)
		(pathRight init:)
		(pathLeft init:)
		(theRock init: hide:)
	)
	
	(method (doit)
		(cond 
			((curRoom script?) 0)
			((ego script?) 0)
			((== (ego onControl: 1) 16) (self setScript: sEnter37))
			((ego edgeHit?)
				(switch (ego edgeHit?)
					(4 (self setScript: sExitWest))
					(2 (self setScript: sExitEast))
				)
			)
			((> (ego y?) 185) (self setScript: sExitSouth))
		)
		(super doit:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_ROCK (ThrowRock))
			(V_WALK
				(if (Btst fClimbedTree) (curRoom setScript: sFallOutOfTree))
			)
			(V_DAZZLE
				(cond 
					((Btst fClimbedTree)
						(messager say: N_ROOM 78 11)
						(curRoom setScript: sFallOutOfTree)
					)
					((!= nestState 3)
						(CastDazzle ego)
						(if (not (Btst PTERESA_LEFT_NEST)) (bird setScript: 0 cue:))
					)
				)
			)
			(V_FETCH
				(cond 
					((Btst fClimbedTree)
						(messager say: N_ROOM 82 11)
						(curRoom setScript: sFallOutOfTree)
					)
					((and (== nestState 0) (not (Btst OBTAINED_RING))) (curRoom setScript: sThrowLasso))
					(else (messager say: N_ROOM 82 12))
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
	
	(method (cue)
		(if (not (Btst VISITED_HEALERHUT_OUTSIDE))
			(Bset VISITED_HEALERHUT_OUTSIDE)
			(messager say: N_ROOM 0 19)
		else
			(switch local11
				(1 (messager say: 3 4 2 2))
				(2
					(if (== nestState 0) (messager say: 12 1 29))
				)
				(3 (messager say: N_ROOM 0 15 2))
				(4 (messager say: N_ROOM 0 0 6))
			)
		)
	)
	
	(method (newRoom newRoomNumber)
		(super newRoom: newRoomNumber)
		(ego actions: 0)
		(roomTimer dispose: delete:)
		(if (== nestState 2) (= nestState 3))
	)
)

(instance tree of Feature
	(properties
		x 91
		y 4
		z 90
		noun 12
		nsTop -1
		nsBottom 189
		nsRight 183
		sightAngle 40
		onMeCheck $0004
		approachX 45
		approachY 176
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_ROCK (ThrowRock))
			(V_LOOK
				(= local11 2)
				(messager say: 12 1 0 1 curRoom)
			)
			(V_DO
				(if (Btst fClimbedTree)
					(curRoom setScript: sClimbDown)
				else
					(curRoom setScript: sClimbUpTree)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance castle of Feature
	(properties
		x 120
		y 32
		noun N_CASTLE
		nsTop 15
		nsLeft 80
		nsBottom 50
		nsRight 160
		sightAngle 40
		onMeCheck $0008
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_ROCK (ThrowRock))
			(V_DAGGER
				(if (not (Btst PTERESA_LEFT_NEST)) (bird setScript: 0 cue:))
				(CastDagger 0)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance hut of Feature
	(properties
		x 229
		y 76
		noun N_HUT
		nsLeft 140
		nsBottom 152
		nsRight 319
		sightAngle 40
		onMeCheck $0002
		approachX 196
		approachY 138
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_ROCK (ThrowRock))
			(V_LOOK (messager say: N_HUT V_LOOK 5))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance theWindow of Feature
	(properties
		x 311
		y 82
		noun N_WINDOW
		nsTop 71
		nsLeft 303
		nsBottom 93
		nsRight 319
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_ROCK (ThrowRock))
			(V_LOOK
				(if Night
					(messager say: N_WINDOW V_DO C_NIGHT)
				else
					(messager say: N_WINDOW V_DO C_DAY)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance theSign of Feature
	(properties
		x 205
		y 36
		noun N_SIGN
		nsTop 29
		nsLeft 195
		nsBottom 44
		nsRight 216
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_ROCK (ThrowRock))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance ground of Feature
	(properties
		x 163
		y 139
		noun N_GROUND
		nsTop 90
		nsLeft 63
		nsBottom 189
		nsRight 263
		sightAngle 40
		onMeCheck $0020
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_ROCK (ThrowRock))
			(V_LOOK (messager say: N_GROUND V_LOOK))
			(V_DO
				(cond 
					((and (not local0) (Btst fClimbedTree)) (curRoom setScript: sFallOutOfTree))
					((Btst fClimbedTree) (messager say: N_ROOM V_DO C_UPTREE))
					(else (curRoom setScript: sGetRock))
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance pathRight of Feature
	(properties
		x 268
		y 151
		noun N_PATHRIGHT
		nsTop 132
		nsLeft 218
		nsBottom 171
		nsRight 319
		sightAngle 40
		onMeCheck $0008
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_ROCK (ThrowRock))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance pathLeft of Feature
	(properties
		x 23
		y 118
		noun N_PATHLEFT
		nsTop 103
		nsBottom 134
		nsRight 47
		sightAngle 40
		onMeCheck $0008
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_ROCK (ThrowRock))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance door of Prop
	(properties
		x 195
		y 117
		noun N_DOOR
		approachX 205
		approachY 131
		view 55
		loop 1
		signal $6000
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_ROCK (ThrowRock))
			(V_DO
				(cond 
					((and (not local0) (Btst fClimbedTree)) (curRoom setScript: sFallOutOfTree))
					((Btst fClimbedTree) (messager say: N_ROOM V_DO C_UPTREE))
					(
						(and
							(not (ego mover?))
							(!= (ego x?) approachX)
							(!= (ego y?) approachY)
						)
						(HandsOff)
						(= local5 98)
						(ego setMotion: PolyPath approachX approachY self)
					)
					(Night (messager say: N_DOOR V_DO C_NIGHT))
					((and (Btst fStolePotions) (!= prevRoomNum 55))
						(= local11 1)
						(messager say: N_DOOR V_DO C_BANNED 1 curRoom)
					)
					(else (curRoom setScript: sKnockDoor self))
				)
			)
			(V_LOCKPICK
				(if (TrySkill PICK 10 0)
					(messager say: N_DOOR V_LOCKPICK C_HAVE_PICK)
				else
					(messager say: N_DOOR V_LOCKPICK C_NO_PICK)
				)
			)
			(V_THIEFKIT
				(if (TrySkill PICK 10 0)
					(messager say: N_DOOR V_LOCKPICK C_HAVE_PICK)
				else
					(messager say: N_DOOR V_LOCKPICK C_NO_PICK)
				)
			)
			(V_LOOK (messager say: N_DOOR V_LOOK))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
	
	(method (cue)
		(super cue:)
		(switch (++ local5)
			(1
				(messager say: N_DOOR 72 0 0 self)
			)
			(2
				(closingDoor init: play:)
				(door ignoreActors: 1 setCycle: EndLoop self)
			)
			(3
				(closingDoor stop:)
				(ego illegalBits: 0 setMotion: MoveTo 257 105 self)
			)
			(4 (curRoom newRoom: 55))
			(99
				(= local5 0)
				(self doVerb: 4)
			)
		)
	)
)

(instance magicLasso of Actor
	(properties
		view 520
		signal $4810
		illegalBits $0000
	)
)

(instance theRock of Actor
	(properties
		yStep 4
		view 510
		loop 4
		priority 10
		signal $4810
		illegalBits $0000
		xStep 4
		moveSpeed 1
	)
)

(instance nest of TargActor
	(properties
		x 111
		y 59
		noun N_NEST
		view 55
		priority 12
		signal $4810
		cycleSpeed 1
		targDeltaY 30
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_ROCK
				(cond 
					((Btst fClimbedTree) (curRoom setScript: sFallOutOfTree))
					((== nestState nestInTree) (curRoom setScript: sThrowRock))
					(else (messager say: N_NEST 20 0))
				)
			)
			(V_DO
				(switch nestState
					(nestInTree
						(cond 
							((and (Btst fClimbedTree) (not (Btst OBTAINED_RING))) (curRoom setScript: outOnALimb))
							((Btst fClimbedTree) (messager say: N_NEST V_DO 11))
							(else (messager say: N_NEST V_DO 12))
						)
					)
					(nestOnGround (messager say: N_NEST V_DO 10))
					(nestBurnt
						(if (not (Btst OBTAINED_RING))
							(= local8 1)
							(curRoom setScript: sPickItUp)
						else
							(switch (Random 1 2)
								(1 (messager say: N_NEST V_DO 8))
								(2 (messager say: N_NEST V_DO 9))
							)
						)
					)
				)
			)
			(V_LOOK
				(cond 
					((== nestState nestOnGround) (messager say: N_NEST V_LOOK 17))
					((not (Btst PTERESA_LEFT_NEST)) (messager say: N_NEST V_LOOK 13))
					((== nestState nestInTree) (messager say: N_NEST V_LOOK 18 0 self) (= local9 98))
					((== nestState nestBurnt) (messager say: N_NEST V_LOOK 16))
				)
			)
			(V_DAGGER
				(if (and (not (Btst fClimbedTree)) (== nestState nestInTree))
					(= local1 1)
					(CastDagger self)
				else
					(messager say: N_NEST V_DAGGER 0)
				)
			)
			(V_FLAME
				(cond 
					((Btst fClimbedTree) (curRoom setScript: sFallOutOfTree))
					((!= nestState 3)
						(CastFlame self)
						(if (not (Btst PTERESA_LEFT_NEST)) (bird setScript: 0 cue:))
					)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
	
	(method (cue)
		(super cue:)
		(switch (++ local9)
			(1 (self setCycle: CycleTo 4 1 self))
			(2 (roomTimer setCycle: self 2))
			(3
				(self setCycle: CycleTo 2 -1 self)
			)
			(4
				(= local9 0)
				(roomTimer setCycle: self 2)
			)
			(99
				(if (Btst fClimbedTree)
					(if (not (Btst OBTAINED_RING))
						(messager say: N_NEST V_LOOK 14)
						(Bset LOOKED_IN_PTERESA_NEST)
					else
						(messager say: N_NEST V_LOOK 15)
					)
				)
				(= local9 0)
			)
		)
	)
	
	(method (getHurt)
		(if (or local1 local2)
			(cond 
				((> (+ [egoStats LUCK] [egoStats THROW]) 50) (curRoom setScript: nestDown))
				(local1 (messager say: N_NEST V_DAGGER 7))
			)
		else
			(curRoom setScript: burnUp)
		)
		(Bclr 281)
		(= local2 0)
		(= local1 0)
	)
)

(instance sEnterFromSouth of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (HandsOff) (= ticks 10))
			(1
				(ego
					init:
					posn: 200 246
					actions: egoActions
					setMotion: MoveTo 200 180 self
				)
			)
			(2
				(NormalEgo)
				(HandsOn)
				(= ticks 10)
			)
			(3
				(curRoom cue:)
				(self dispose:)
			)
		)
	)
)

(instance sEnterFromWest of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (HandsOff) (= ticks 10))
			(1
				(ego
					init:
					actions: egoActions
					posn: -25 117
					setMotion: PolyPath 95 129 self
				)
			)
			(2
				(NormalEgo)
				(HandsOn)
				(= ticks 10)
			)
			(3
				(curRoom cue:)
				(self dispose:)
			)
		)
	)
)

(instance sEnterFrom37 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (HandsOff) (= ticks 10))
			(1
				(ego
					init:
					posn: 104 98
					actions: egoActions
					setMotion: PolyPath 90 130 self
				)
			)
			(2
				(NormalEgo)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance sGetRock of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 40])
		(switch (= state newState)
			(0
				(HandsOff)
				(= gEgoCycleSpeed (ego cycleSpeed?))
				(= gEgoMoveSpeed (ego moveSpeed?))
				(= ticks 10)
			)
			(1
				(ego setMotion: PolyPath 172 172 self)
			)
			(2
				(= register (Random 0 1))
				(ego
					view: 510
					setLoop: register
					setCel: 0
					setCycle: EndLoop self
				)
			)
			(3
				(Message MsgGet 54 N_ROOM V_DO C_GETROCKS 1 @temp0)
				(Print addText: @temp0 init:)
				(ego setCycle: BegLoop self)
			)
			(4
				(NormalEgo)
				(if register
					(ego setHeading: 270 self)
				else
					(ego setHeading: 90 self)
				)
			)
			(5
				(HandsOn)
				(ego
					cycleSpeed: gEgoCycleSpeed
					moveSpeed: gEgoMoveSpeed
					get: iRock 10
				)
				(self dispose:)
			)
		)
	)
)

(instance sFlutter of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= ticks (Random 120 300)))
			(1
				(lizSound play:)
				(if (not (bird cel?))
					(bird cycleSpeed: (Random 6 12) setCycle: EndLoop self)
				else
					(bird cycleSpeed: (Random 6 12) setCycle: BegLoop self)
				)
			)
			(2 (self init:))
		)
	)
)

(instance rockHitsIt of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theRock setMotion: MoveTo (nest x?) (nest y?) self)
			)
			(1
				(theRock setMotion: MoveTo -10 (- (nest y?) 40) self)
			)
			(2
				(curRoom setScript: nestDown)
				(theRock dispose:)
			)
		)
	)
)

(instance sPickItUp of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (HandsOff) (= ticks 10))
			(1
				(NormalEgo)
				(ego setMotion: PolyPath (+ (nest x?) 15) 164 self)
			)
			(2
				(ego view: 510 setLoop: 1 cel: 0 setCycle: EndLoop self)
			)
			(3
				(if (not (Btst OBTAINED_RING))
					(messager say: N_ROOM 0 0 2)
					(theRing dispose:)
				)
				(if (== (nest loop?) 7)
					(nest setCel: 1)
				else
					(nest hide:)
				)
				(ego setCycle: BegLoop self)
			)
			(4
				(if (not (Btst OBTAINED_RING))
					(ego get: iRing)
					(Bset OBTAINED_RING)
					(SolvePuzzle POINTS_GETGOLDRING 3)
				)
				(NormalEgo)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance burnUp of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= nestState nestBurnt)
				(= ticks 10)
			)
			(1
				(if (not (Btst PTERESA_LEFT_NEST)) (bird setScript: 0 cue:))
				(= ticks 30)
			)
			(2
				(nest
					setPri: 15
					setLoop: 0
					setCel: 2
					moveSpeed: 1
					cycleSpeed: 0
					setMotion: MoveTo 114 164 self
					cue:
				)
			)
			(3
				(= local9 5)
				(nest setCel: 0 cycleSpeed: 6 setCycle: EndLoop self cue:)
			)
			(4
				(nest setLoop: 7 setCel: 255 setPri: -1 addToPic:)
				(= cycles 2)
			)
			(5
				(if (Btst OBTAINED_RING)
					(NormalEgo)
					(HandsOn)
					(self dispose:)
				else
					(theRing
						posn: (nest x?) (nest y?)
						setPri: 15
						setCycle: EndLoop self
					)
					(burntNest init:)
				)
			)
			(6
				(messager say: N_NEST V_LOOK C_NEST_BURNED 0 self)
			)
			(7
				(curRoom setScript: sPickItUp)
			)
		)
	)
)

(instance sEnter37 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (HandsOff) (= ticks 10))
			(1
				(ego
					illegalBits: 0
					setPri: 4
					setLoop: 3
					setCycle: Forward
					setMotion: MoveTo (ego x?) 115 self
				)
			)
			(2 (curRoom newRoom: 37))
		)
	)
)

(instance sFromInsideHouse of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (HandsOff) (= ticks 10))
			(1
				(door ignoreActors: 1 setCel: 255)
				(= cycles 2)
			)
			(2
				(ego
					init:
					illegalBits: 0
					actions: egoActions
					posn: 257 105
					setLoop: 5
					setMotion: PolyPath 160 163 self
				)
			)
			(3
				(Load RES_SOUND 91)
				(ego setLoop: -1)
				(door ignoreActors: 0 setCycle: BegLoop self)
			)
			(4
				(slamDoor play:)
				(door stopUpd:)
				(NormalEgo)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance sClimbDown of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (HandsOff) (= ticks 10))
			(1
				(ego
					view: 517
					setLoop: 1
					setCel: 255
					setStep: 3 2
					setCycle: BegLoop self
				)
			)
			(2
				(ego
					setLoop: 0
					posn: 58 84
					setCel: 4
					setCycle: Reverse
					setMotion: MoveTo 50 140 self
				)
				(egoInTree dispose:)
			)
			(3
				(ego setCycle: 0)
				(NormalEgo)
				(HandsOn)
				(ego posn: (tree approachX?) (tree approachY?) loop: 3)
				(Bclr fClimbedTree)
				(self dispose:)
			)
		)
	)
)

(instance sExitWest of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (HandsOff) (= ticks 10))
			(1
				(ego setMotion: MoveTo -25 (ego y?) self)
			)
			(2 (curRoom newRoom: 53))
		)
	)
)

(instance sExitSouth of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (HandsOff) (= ticks 10))
			(1
				(ego setMotion: MoveTo (ego x?) 246 self)
			)
			(2 (curRoom newRoom: 65))
		)
	)
)

(instance sExitEast of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (HandsOff) (= ticks 10))
			(1
				(ego setMotion: MoveTo 349 134 self)
			)
			(2 (curRoom newRoom: 56))
		)
	)
)

(instance sEnterFromEast of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (HandsOff) (= ticks 10))
			(1
				(ego
					init:
					posn: 349 134
					actions: egoActions
					setMotion: PolyPath 160 163 self
				)
			)
			(2
				(NormalEgo)
				(HandsOn)
				(= ticks 10)
			)
			(3
				(curRoom cue:)
				(self dispose:)
			)
		)
	)
)

(instance outOnALimb of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (HandsOff) (= ticks 10))
			(1
				(ego
					view: 55
					setLoop: 8
					setCel: 0
					setPri: 14
					setStep: 1 1
				)
				(= register 10)
				(= cycles 2)
			)
			(2
				(ego
					setCel: 0
					setCycle: EndLoop self
					setMotion: MoveTo (+ (ego x?) 3) (ego y?) self
				)
			)
			(3)
			(4
				(if (not (-- register))
					(= cycles 2)
				else
					(= state 1)
					(= ticks 20)
				)
			)
			(5
				(if (TrySkill AGIL 30)
					(ego setCycle: Forward setMotion: MoveTo 100 63 self)
				else
					(= local10 1)
					(curRoom setScript: sFallOutOfTree)
				)
			)
			(6
				(ego setCycle: 0)
				(= ticks 20)
			)
			(7
				(messager say: N_ROOM 0 0 4)
				(ego setLoop: 9 setCel: 0 setCycle: EndLoop self)
			)
			(8 (= ticks 20))
			(9
				(= local11 4)
				(messager say: N_ROOM 0 0 5 curRoom)
				(Bset OBTAINED_RING)
				(ego setCycle: BegLoop self)
				(SolvePuzzle POINTS_GETGOLDRING 3)
			)
			(10 (ego get: iRing) (= ticks 20))
			(11
				(ego
					setLoop: 8
					setCycle: Reverse
					setMotion: MoveTo 64 64 self
				)
			)
			(12
				(messager say: N_ROOM 0 0 7)
				(ego setCycle: 0)
				(self dispose:)
				(ego setScript: sClimbDown)
			)
		)
	)
)

(instance closingDoor of Sound
	(properties
		number 89
	)
)

(instance lizSound of Sound
	(properties
		number 74
	)
)

(instance slamDoor of Sound
	(properties
		number 91
	)
)

(instance knockSound of Sound
	(properties
		number 78
	)
)

(instance fallSound of Sound
	(properties
		number 9
	)
)

(instance egoActions of Actions
	(properties)
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(V_ROCK (ThrowRock))
				(else 
					(if (and (== theVerb V_WALK) (Btst fClimbedTree))
						(curRoom setScript: sFallOutOfTree)
						(return TRUE)
					else
						(return FALSE)
					)
				)
			)
		)
	)
)

(instance burntNest of Feature
	(properties
		x 112
		y 158
		nsTop 140
		nsLeft 98
		nsBottom 171
		nsRight 130
		sightAngle 40
		approachX 112
		approachY 158
	)
	
	(method (doVerb theVerb)
		(nest doVerb: theVerb &rest)
	)
)

(instance sKnockDoor of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setLoop: 6)
				(= ticks 10)
			)
			(1
				(if (> (++ register) 2)
					(self dispose:)
				else
					(knockSound play: self)
				)
			)
			(2 (= state 0) (= ticks 10))
		)
	)
)

(instance gardenL of Feature
	(properties
		x 152
		y 119
		nsTop 100
		nsLeft 119
		nsBottom 139
		nsRight 186
		sightAngle 40
		onMeCheck $4000
		approachX 196
		approachY 138
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_ROCK (ThrowRock))
			(V_LOOK (messager say: N_HUT V_LOOK 6))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance gardenR of Feature
	(properties
		x 246
		y 140
		nsTop 122
		nsLeft 208
		nsBottom 158
		nsRight 284
		sightAngle 40
		onMeCheck $4000
		approachX 196
		approachY 138
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_ROCK (ThrowRock))
			(V_LOOK (messager say: N_HUT V_LOOK 6))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance birdFeature of Feature
	(properties
		x 113
		y 54
		nsTop 37
		nsLeft 96
		nsBottom 71
		nsRight 131
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_ROCK (ThrowRock))
			(V_FLAME (bird setScript: 0 cue:))
			(V_DAGGER
				(bird setScript: 0 cue:)
				(CastDagger 0)
			)
			(V_LOOK
				(if (< mouseY 60)
					(castle doVerb: theVerb &rest)
				else
					(messager say: N_NEST V_LOOK 13 2)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance theRing of Prop
	(properties
		x 112
		y 57
		view 55
		loop 5
		signal $4810
	)
	
	(method (doVerb theVerb)
		(nest doVerb: theVerb &rest)
	)
	
	(method (cue)
		(super cue:)
		(if
			(and
				(== nestState nestInTree)
				(not (Btst OBTAINED_RING))
				(not local7)
			)
			(= local7 1)
			(self show: setCel: 0 setCycle: EndLoop self)
		else
			(self hide:)
			(= local7 0)
			(ringTimer setReal: self (Random 8 16))
		)
	)
)

(instance sFallOutOfTree of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(Load RES_SOUND 9)
				(Load RES_SOUND 10)
				(= gEgoCycleSpeed (ego cycleSpeed?))
				(= gEgoMoveSpeed (ego moveSpeed?))
				(= local0 1)
				(= ticks 10)
			)
			(1
				(Bclr fClimbedTree)
				(ego
					view: 517
					setLoop: 2
					cycleSpeed: 8
					setCel: 0
					setCycle: EndLoop self
				)
			)
			(2
				(fallSound number: 9 play:)
				(ego
					posn: (+ (ego x?) 30) (ego y?)
					setLoop: 4
					setCycle: Forward
					setStep: 4 4
					cycleSpeed: 1
					moveSpeed: 1
					setMotion: MoveTo 127 160 self
				)
			)
			(3
				(ego setLoop: 6 setCel: 0 setCycle: EndLoop self)
			)
			(4
				(ShakeScreen 2)
				(fallSound number: 10 play:)
				(= seconds 4)
			)
			(5
				(if (not (TakeDamage 8))
					(EgoDead 68 69 5 2 517)
				else
					(switch (Random 1 5)
						(1 (messager say: N_ROOM 0 5))
						(2 (messager say: N_ROOM 0 6))
						(3 (messager say: N_ROOM 0 25))
						(4 (messager say: N_ROOM 0 21))
						(5 (messager say: N_ROOM 0 20))
					)
					(ego view: 517 setLoop: 8 setCel: 6)
					(= ticks 30)
				)
			)
			(6
				(ego setCel: 7)
				(egoInTree dispose:)
				(= ticks 30)
			)
			(7
				(if local10 (= local10 0) (messager say: N_ROOM 0 23))
				(NormalEgo)
				(HandsOn)
				(ego
					cycleSpeed: gEgoCycleSpeed
					moveSpeed: gEgoMoveSpeed
					loop: 2
				)
				(self dispose:)
			)
		)
	)
)

(instance bird of Actor
	(properties
		x 112
		y 151
		z 100
		noun N_BIRD
		view 55
		loop 6
		priority 13
		signal $4810
		illegalBits $0000
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_ROCK
				(curRoom setScript: sThrowRock)
			)
			(V_DAGGER
				(self setScript: 0 cue:)
				(Bset 281)
				(= local1 1)
				(CastDagger 0)
			)
			(V_LOOK (messager say: N_BIRD V_LOOK))
			(V_DO (messager say: N_BIRD V_DO))
			(else 
				(nest doVerb: theVerb &rest)
			)
		)
	)
	
	(method (cue)
		(super cue:)
		(switch (++ local6)
			(1
				(bird
					cycleSpeed: 6
					setLoop: 3
					setCel: 0
					z: 0
					posn: 133 41
					setPri: 15
				)
				(roomTimer setCycle: self 2)
			)
			(2
				(lizSound loop: -1 play:)
				(bird setCycle: EndLoop self)
			)
			(3
				(lizSound stop:)
				(Bset PTERESA_LEFT_NEST)
				(= local6 0)
				(birdFeature dispose:)
				(self dispose:)
			)
		)
	)
)

(instance nestDown of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (HandsOff) (= ticks 10))
			(1
				(nest
					setLoop: 4
					setStep: 5 5
					setCycle: Forward
					cycleSpeed: 3
					illegalBits: 0
					ignoreActors: 1
					moveSpeed: 1
					setMotion: MoveTo 114 164 self
				)
			)
			(2
				(nest setLoop: 7 setCel: 0 setCycle: EndLoop self)
				(= nestState 1)
			)
			(3
				(if (Btst OBTAINED_RING)
					(messager say: N_ROOM 0 24)
					(NormalEgo)
					(HandsOn)
					(self dispose:)
				else
					(theRing
						posn: (nest x?) (nest y?)
						setPri: 15
						setCycle: EndLoop self
						ignoreActors: 1
					)
					(messager say: N_ROOM 0 22)
				)
			)
			(4
				(curRoom setScript: sPickItUp)
			)
		)
	)
)

(instance sThrowRock of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= local2 1)
				(= ticks 10)
			)
			(1
				(if (and (!= (ego y?) 155) (!= (ego x?) 217))
					(ego setMotion: PolyPath 217 155 self)
				else
					(= cycles 2)
				)
			)
			(2 (ego setHeading: 270 self))
			(3
				(if (not (Btst PTERESA_LEFT_NEST)) (bird setScript: 0 cue:))
				(ego view: 510 setLoop: 2 setCel: 0 setCycle: CycleTo 7 1 self)
			)
			(4
				(theRock show: posn: (+ (ego x?) 1) (- (ego y?) 41))
				(= ticks 5)
			)
			(5
				(ego setCel: 8)
				(theRock posn: (- (ego x?) 14) (- (ego y?) 39))
				(= ticks 5)
			)
			(6
				(ego setCel: 9)
				(theRock posn: (- (ego x?) 35) (- (ego y?) 55))
				(= ticks 5)
			)
			(7
				(cond 
					((> (+ [egoStats LUCK] [egoStats THROW]) 50) (theRock setScript: rockHitsIt))
					((> [egoStats THROW] 25) (theRock setScript: rockHitsIt))
					(else
						(TrySkill THROW)
						(theRock setCycle: Forward setMotion: MoveTo 0 0)
					)
				)
				(= seconds 3)
			)
			(8
				(NormalEgo)
				(HandsOn)
				(ego use: iRock 1 loop: 1)
				(= local2 0)
				(self dispose:)
			)
		)
	)
)

(instance sThrowLasso of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (HandsOff) (= ticks 10))
			(1
				(if (and (!= (ego y?) 155) (!= (ego x?) 217))
					(ego setMotion: PolyPath 217 155 self)
				else
					(= cycles 2)
				)
			)
			(2 (ego setHeading: 270 self))
			(3
				(ego view: 520 setLoop: 0 setCel: 0)
				(= cycles 2)
			)
			(4 (ego setCycle: EndLoop self))
			(5
				(if (not (Btst PTERESA_LEFT_NEST)) (bird setScript: 0 cue:))
				(theRing dispose:)
				(magicLasso
					init:
					posn: 207 109
					setLoop: 5
					setStep: 4 4
					setCycle: Forward
					setPri: 15
					setMotion: MoveTo 113 57 self
				)
			)
			(6
				(magicLasso setStep: 6 4 setMotion: MoveTo 207 109 self)
				(nest
					setPri: 14
					setStep: 6 4
					setMotion: MoveTo 207 115 self
				)
			)
			(7)
			(8
				(magicLasso hide:)
				(nest hide:)
				(ego setLoop: 2 cel: 0 setCycle: EndLoop self)
			)
			(9
				(if (Btst OBTAINED_RING)
					(messager say: N_ROOM 0 14)
				else
					(= local11 3)
					(messager say: N_ROOM 0 15 1 curRoom)
					(Bset OBTAINED_RING)
					(theRing dispose:)
					(SolvePuzzle POINTS_GETGOLDRING 3)
				)
				(magicLasso dispose:)
				(ego
					view: 510
					setLoop: 1
					setCel: 0
					posn: (+ (ego x?) 2) (ego y?)
					setCycle: EndLoop self
				)
			)
			(10
				(ego get: iRing)
				(nest
					setLoop: 7
					setCel: 255
					posn: (- (ego x?) 14) (+ (ego y?) 6)
					setPri: -1
					show:
					stopUpd:
				)
				(ego setCycle: BegLoop self)
			)
			(11
				(= nestState nestOnGround)
				(HandsOn)
				(NormalEgo)
				(self dispose:)
			)
		)
	)
)

(instance sClimbUpTree of Script
	(properties)
	
	(method (dispose)
		(ego cycleSpeed: gEgoCycleSpeed moveSpeed: gEgoMoveSpeed)
		(super dispose:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= gEgoCycleSpeed (ego cycleSpeed?))
				(= gEgoMoveSpeed (ego moveSpeed?))
				(= ticks 10)
			)
			(1
				(ego
					view: 517
					setLoop: 0
					setCel: 0
					setPri: 15
					posn: 50 140
					setCycle: Forward
				)
				(if (TrySkill CLIMB 30 0)
					(= state (+ state 3))
					(ego setMotion: MoveTo 58 84 self)
				else
					(ego setMotion: MoveTo 58 115 self)
				)
			)
			(2
				(ego setCel: 255 setMotion: MoveTo 50 140 self)
			)
			(3
				(NormalEgo)
				(ego posn: (tree approachX?) (tree approachY?) loop: 3)
				(= cycles 2)
			)
			(4
				(messager say: N_ROOM 0 0 3)
				(SkillUsed CLIMB 1)
				(HandsOn)
				(self dispose:)
			)
			(5
				(ego setLoop: 1 setCel: 0 posn: 64 64 setCycle: EndLoop self)
			)
			(6
				(HandsOn)
				(ego view: 4 setPri: pWHITE setLoop: 2)
				(User canControl: FALSE)
				(Bset fClimbedTree)
				(if (not (Btst PTERESA_LEFT_NEST)) (bird setScript: 0 cue:))
				(egoInTree init:)
				(= ticks 60)
			)
			(7 (self dispose:))
		)
	)
)

(instance egoInTree of Feature
	(properties
		x 65
		y 142
		z 100
		nsTop 13
		nsLeft 47
		nsBottom 71
		nsRight 83
	)
	
	(method (init)
		(super init: &rest)
		(directionHandler add: self)
	)
	
	(method (dispose)
		(directionHandler delete: self)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(if
			(and
				(not (== (event message?) nullEvt))
				(== (theIconBar curIcon?) (theIconBar walkIconItem?))
				(& (event type?) direction)
			)
			(curRoom setScript: sFallOutOfTree)
			(event claimed: TRUE)
			(return)
		else
			(super handleEvent: event)
		)
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_WALK)
			(curRoom setScript: sFallOutOfTree)
		else
			(tree doVerb: theVerb &rest)
		)
	)
)
