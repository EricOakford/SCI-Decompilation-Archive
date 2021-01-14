;;; Sierra Script 1.0 - (do not remove this comment)
(script# 89)
(include game.sh) (include "89.shm")
(use Main)
(use Procs)
(use PolyPath)
(use Polygon)
(use Feature)
(use Timer)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm89 0
)

(enum 1
	trollVisible
	trollDying
	trollDead
)

(local
	timesSearchedTroll
	caveCue
	[local2 2]
	trollState
	local5
	searchCue
	trollInRoom
)
(instance leTimer of Timer
	(properties)
)

(instance rm89 of Room
	(properties
		noun N_ROOM
		picture 89
		style WIPELEFT
	)
	
	(method (init)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: 2
					init:
						0
						0
						319
						0
						319
						90
						139
						89
						124
						70
						98
						70
						56
						80
						31
						97
						31
						174
						319
						174
						319
						189
						0
						189
					yourself:
				)
				((Polygon new:)
					type: 2
					init: 319 105 319 150 215 149 176 159 71 156 47 152 48 140 56 133 61 105
					yourself:
				)
		)
		(super init:)
		(water init: setScript: sWater)
		(rocks init:)
		(ceiling init:)
		(cave init:)
		(SolvePuzzle POINTS_ENTERSECRETENTRANCE 2)
		(= trollState
			(cond 
				((or (Btst SAID_HIDEN_GOSEKE) (Btst fBeatFred)) 0)
				((== prevRoomNum vTroll) (= monsterNum 0) 2)
				((Btst fBeatFred89)
					(troll
						view: 451
						posn: 94 189
						setLoop: 0
						setCel: 10
						init:
						addToPic:
					)
					3
				)
				(else 1)
			)
		)
		(switch prevRoomNum
			(vTroll
				(ego init: posn: 58 170 setHeading: 270)
				(self setScript: trollDies)
			)
			(84
				(if (!= trollState 0)
					(= trollInRoom TRUE)
					(if (== trollState trollVisible)
						(troll init: posn: 167 95 setLoop: 0)
						(troll
							setCycle: Walk
							cycleSpeed: 8
							moveSpeed: 8
							setMotion: MoveTo 251 95
						)
					)
				)
				(ego init: posn: 345 92)
				(self setScript: sEnterFromEast)
			)
			(else 
				(ego init: posn: 345 168)
				(self setScript: sEnterFromEast)
			)
		)
		(Load SCRIPT PCHASE)
		(Load SCRIPT PAVOID)
		(leTimer setReal: self 6)
		(cSound number: 23 loop: -1 play:)
	)
	
	(method (doit)
		(if
		(and (not (curRoom script?)) (> (ego x?) 300))
			(HandsOff)
			(curRoom setScript: sExitEast)
		)
		(super doit:)
	)
	
	(method (dispose)
		(= nightPalette 0)
		(DisposeScript PAVOID)
		(DisposeScript PCHASE)
		(Bset fBeenIn89)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DAZZLE
				(if trollInRoom
					(messager say: N_ROOM V_DAZZLE C_TROLLHERE)
				else
					(messager say: N_ROOM V_DAZZLE C_NOBODYHERE)
				)
			)
			(V_FLAME
				(if trollInRoom
					(messager say: N_ROOM V_FLAME C_TROLLHERE)
				else
					(messager say: N_ROOM V_DAZZLE C_NOBODYHERE)
				)
			)
			(V_CALM
				(if trollInRoom
					(messager say: N_ROOM V_CALM C_TROLLHERE)
				else
					(messager say: N_ROOM V_DAZZLE C_NOBODYHERE)
				)
			)
			(else  (messager say: N_ROOM 0 C_SPELLUSELESS))
		)
	)
	
	(method (cue)
		(switch (++ caveCue)
			(1
				(if (not (Btst fBeenIn89))
					(messager say: N_ROOM NULL C_FIRSTENTRY)
				)
				(leTimer setReal: self 12)
			)
			(2 (messager say: N_ROOM 0 C_DRIP))
		)
	)
	
	(method (newRoom newRoomNumber)
		(super newRoom: newRoomNumber)
		(leTimer dispose: delete:)
	)
)

(instance cave of Feature
	(properties
		x 159
		y 1
		z 100
		noun N_CAVE
		nsTop -1
		nsBottom 189
		nsRight 319
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(switch (Random 1 3)
					(1
						(messager say: N_CAVE V_LOOK C_LOOK1)
					)
					(2
						(messager say: N_CAVE V_LOOK C_LOOK2)
					)
					(3
						(messager say: N_CAVE V_LOOK C_LOOK3)
					)
				)
			)
			(V_DO
				(messager say: N_CAVE V_DO)
			)
			(V_TALK
				;changed to correct verb to trigger this message
				(messager say: N_CAVE V_ALTTALK)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance ceiling of Feature
	(properties
		x 159
		y 29
		noun N_CEILING
		nsBottom 59
		nsRight 319
		sightAngle 40
		onMeCheck cBLUE
	)
)

(instance rocks of Feature
	(properties
		x 159
		y 106
		noun N_ROCKS
		nsTop 23
		nsBottom 189
		nsRight 319
		sightAngle 40
		onMeCheck cGREEN
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(switch (Random 1 3)
					(1
						(messager say: N_ROCKS V_LOOK C_LOOK1)
					)
					(2
						(messager say: N_ROCKS V_LOOK C_LOOK2)
					)
					(3
						(messager say: N_ROCKS V_LOOK C_LOOK3)
					)
				)
			)
			(V_DO
				(messager say: N_CEILING V_DO)
			)
			(V_TALK
				(messager say: N_CEILING V_ALTTALK)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance water of Prop
	(properties
		view 89
		signal $4810
	)
)

(instance troll of Actor
	(properties
		noun N_TROLL
		view vTroll
		signal $6000
		illegalBits $0000
	)
	
	(method (init)
		(= nightPalette 1450)
		(PalVary PALVARYTARGET 1450)
		(kernel_128 450)
		(super init:)
	)
	
	(method (doit)
		(super doit:)
		(if
		(and (not (Btst fBeatFred)) (< (ego distanceTo: troll) 25))
			(HandsOff)
			(sEnterFromEast cue:)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(switch trollState
					(trollVisible (messager say: N_TROLL V_LOOK C_TROLLVISIBLE))
					(trollDying (messager say: N_TROLL V_LOOK C_TROLLDYING))
					(trollDead (messager say: N_TROLL V_LOOK C_TROLLDEAD))
					(else  (messager say: N_TROLL V_LOOK C_NOTHINGMORE))
				)
			)
			(V_DO
				(if (!= trollState trollDead)
					(messager say: N_TROLL V_DO C_TROLLMAD)
					(HandsOff)
					(ego setMotion: PolyPath x y self)
				else
					(switch (++ timesSearchedTroll)
						(1 (messager say: N_TROLL V_DO C_CANTGETCLUB))
						(2
							(= searchCue 0)
							(self setScript: egoSearch)
						)
						(3
							(= searchCue 1)
							(self setScript: egoSearch)
						)
						(4
							(= timesSearchedTroll 3)
							(messager say: N_TROLL V_DO C_NOTHINGMORE)
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

(instance trollDies of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Bset fBeatFred89)
				(Bset fBeatFred)
				(HandsOff)
				(troll
					init:
					view: 451
					setLoop: 0
					setCel: 0
					illegalBits: 0
					posn: 94 189
					cycleSpeed: 6
					setPri: 4
					setCycle: EndLoop self
				)
			)
			(1
				(HandsOn)
				(NormalEgo)
				(= trollState trollDead)
				(troll addToPic:)
				(client setScript: 0)
				(self dispose:)
			)
		)
	)
)

(instance egoSearch of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					setMotion: PolyPath (- (troll x?) 33) (- (troll y?) 19) self
				)
			)
			(1
				(Face ego troll self)
			)
			(2
				(ego
					loop: (mod (+ (ego loop?) 4) 2)
					setPri: 15
					view: 510
					setCycle: EndLoop self
				)
			)
			(3
				(switch searchCue
					(0
						(if (Btst fShavedFred)
							(messager say: N_TROLL V_DO C_TROLLHASNOTHING)
						else
							(messager say: N_TROLL V_DO C_TROLLHASBEARD)
						)
					)
					(1
						(if (Btst fShavedFred)
							(messager say: N_TROLL V_DO C_ALREADYGOTBEARD)
						else
							(messager say: N_TROLL V_DO C_SHAVETROLL)
						)
					)
				)
				(ego setPri: 13 setCycle: BegLoop self)
			)
			(4
				(if (and (== searchCue 1) (not (Btst fShavedFred)))
					(Bset fShavedFred)
					(ego get: iTrollBeard)
				)
				(NormalEgo)
				(HandsOn)
			)
		)
	)
)

(instance sExitEast of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: MoveTo 340 (ego y?) self)
			)
			(1
				(if (> (ego y?) 110)
					(curRoom newRoom: 93)
				else
					(curRoom newRoom: 84)
				)
			)
		)
	)
)

(instance sWater of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(switch (Random 1 3)
					(1
						(water
							setPri: 4
							posn: 187 86
							setCel: 0
							cycleSpeed: (Random 2 8)
							setCycle: EndLoop self
						)
					)
					(2
						(water
							setPri: 14
							posn: 268 110
							setCel: 0
							cycleSpeed: (Random 2 8)
							setCycle: EndLoop self
						)
					)
					(3
						(water
							setPri: 4
							posn: 222 83
							setCel: 0
							cycleSpeed: (Random 2 8)
							setCycle: EndLoop self
						)
					)
				)
			)
			(1
				(drip play:)
				(= seconds (Random 2 5))
			)
			(2 (self init:))
		)
	)
)

(instance sEnterFromEast of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (HandsOff) (= ticks 18))
			(1
				(ego setMotion: MoveTo 295 (ego y?) self)
			)
			(2
				(HandsOn)
				(NormalEgo)
				(if (== trollState trollVisible)
					(troll setMotion: 0)
					(messager say: N_TROLL 0 C_TROLLATTACKS 0 self)
				else
					(self cue:)
				)
			)
			(3
				(if (== trollState trollVisible)
					(curRoom newRoom: vTroll)
				else
					(client setScript: 0)
					(self dispose:)
				)
			)
		)
	)
)

(instance drip of Sound
	(properties
		number 116
	)
)
