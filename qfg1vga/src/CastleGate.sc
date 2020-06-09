;;; Sierra Script 1.0 - (do not remove this comment)
(script# 37)
(include game.sh) (include "37.shm")
(use Main)
(use Teller)
(use Procs)
(use Talker)
(use Polygon)
(use Feature)
(use LoadMany)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm37 0
	karlTalker 1
)

(local
	[local0 7] = [0 24 -11 -29 -10 -26 999]
	[local7 6] = [0 18 27 -8 15 999]
	[local13 4] = [0 23 -14 999]
	[local17 6] = [0 -21 7 -9 -13 999]
	[local23 5] = [0 20 22 28 999]
	[local28 5] = [0 20 22 16 999]
	[local33 4] = [0 25 -6 999]
	[local37 6] = [0 -13 -9 -6 12 999]
	[local43 4] = [0 26 16 999]
	[local47 6] = [0 17 7 30 -6 999]
	[local53 4] = [0 12 17 999]
	[local57 20]
	[local77 12] = [0 -11 -29 -8 -10 -26 -14 -21 -9 -13 -6 999]
	gateOpen
	local90
	local91
	local92
	local93
	local94
	local95
	crushedByGate
	local97
	local98
	climbFail
	local100
	invitedIntoCastle
	theUseSortedFeatures
	local103
	gEgoMoveSpeed
	gEgoCycleSpeed
)
(instance rm37 of Room
	(properties
		noun N_ROOM
		picture 37
		style HSHUTTER
		north 39
	)
	
	(method (init)
		(= [local57 0] @local0)
		(= [local57 1] @local7)
		(= [local57 2] @local13)
		(= [local57 3] @local17)
		(= [local57 4] @local23)
		(= [local57 5] @local28)
		(= [local57 6] @local33)
		(= [local57 7] @local37)
		(= [local57 8] @local43)
		(= [local57 9] @local47)
		(= [local57 10] @local53)
		(= [local57 11] 999)
		(super init:)
		(= gEgoMoveSpeed (ego moveSpeed?))
		(= gEgoCycleSpeed (ego cycleSpeed?))
		(if Night (LoadMany RES_VIEW 503 517))
		(Load RES_VIEW 37)
		(Load RES_SOUND 90)
		(= theUseSortedFeatures useSortedFeatures)
		(= useSortedFeatures FALSE)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: 2
					init: 41 189 0 189 0 0 141 0 142 149 125 172 71 172
					yourself:
				)
				((Polygon new:)
					type: 2
					init: 274 189 246 168 190 168 178 148 176 0 319 0 319 189
					yourself:
				)
		)
		(if (not (Btst fBeenIn37))
			(Bset fBeenIn37)
			(oneTime init: play:)
		)
		(portSound number: 90 init:)
		(self
			setFeatures: theSky mountain castle gateHouse tower bush wall wWindow
		)
		(NormalEgo)
		(HandsOn)
		(karlTeller init: karl @local0 @local57 @local77)
		(switch prevRoomNum
			(39
				(if Night
					(castleGate init: stopUpd:)
					(ego setScript: egoClimbsDown)
				else
					(portSound play:)
					(castleBars
						x: 162
						y: 98
						setLoop: 7
						setCel: 0
						init:
						stopUpd:
					)
					(= gateOpen TRUE)
					(= local92 4)
					(karl
						loop: 4
						cel: 1
						actions: karlTeller
						illegalBits: 0
						x: 158
						y: 74
						init:
					)
					(HandsOff)
					(ego setScript: egoLeavesCastle)
				)
			)
			(else 
				(if Night
					(castleGate init: stopUpd:)
					0
				else
					(if
					(or (and (Btst fSavedBarnard) (not (Btst GOT_KARL_ATTENTION))) (Btst fSavedElsa))
						(= invitedIntoCastle TRUE)
						(karl init: actions: karlTeller setScript: karlGreets)
						(Bset GOT_KARL_ATTENTION)
					else
						(karl init: actions: karlTeller setScript: karlPatrols)
					)
					(castleBars init: stopUpd:)
				)
				(if (OneOf prevRoomNum 38 39 41)
					(ego loop: 2 posn: 161 175 init:)
				else
					(ego setScript: egoEnters)
				)
			)
		)
	)
	
	(method (doit)
		(cond 
			((ego script?))
			((== (ego edgeHit?) 3) (ego setScript: egoExits))
			((and (Btst fSavedElsa) gateOpen (< (ego y?) 140)) (curRoom newRoom: 600))
		)
		(super doit:)
	)
	
	(method (dispose)
		(= nightPalette 0)
		(castleGate setCycle: 0)
		(karl setCycle: 0)
		(= useSortedFeatures theUseSortedFeatures)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_LOOK)
			(messager say: N_ROOM V_LOOK)
		else
			(super doVerb: theVerb &rest)
		)
	)
)

(instance karl of Actor
	(properties
		x 68
		y 74
		noun 4
		view 37
		priority 10
		cycleSpeed 12
	)
	
	(method (init)
		(= nightPalette 137)
		(PalVary PALVARYTARGET 137)
		(kernel_128 37)
		(self setStep: 2 2 signal: 24592)
		(super init:)
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_LOOK)
			(messager say: N_KARL V_LOOK)
		else
			(super doVerb: theVerb &rest)
		)
	)
)

(instance karlTeller of Teller
	(properties)
	
	(method (doVerb theVerb)
		(if (== theVerb V_TALK)
			(if (not (== (karl loop?) local92))
				(karl setScript: karlStopToTalk)
			)
			(SolvePuzzle POINTS_TALKTOGATEKEEPER 5)
		)
		(super doVerb: theVerb &rest)
		(return TRUE)
	)
)

(instance castleBars of Actor
	(properties
		x 162
		y 150
		noun N_CASTLE_BARS
		view 37
		loop 7
		priority 9
		signal $2810
		cycleSpeed 12
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO
				(cond 
					(gateOpen (messager say: N_KARL V_DO 31) (karl setScript: closeGate))
					(local91 (messager say: N_KARL V_DO 32))
					(else (messager say: N_KARL V_DO 32) (karl setScript: openGate))
				)
			)
			(V_LOOK
				(cond 
					(Night (messager say: N_CASTLE_GATE V_LOOK 34))
					(gateOpen (messager say: N_CASTLE_GATE V_LOOK 31))
					(else (messager say: N_CASTLE_GATE V_LOOK 33))
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance castleGate of Actor
	(properties
		x 162
		y 150
		noun N_CASTLE_GATE
		view 37
		loop 7
		priority 10
		signal $0010
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO
				(if Night
					(if (< [egoStats CLIMB] 10)
						(messager say: N_WALL V_DO 48)
					else
						(= local97 0)
						(ego setScript: egoClimbsWall)
					)
				else
					(messager say: N_CASTLE_GATE V_LOOK)
				)
			)
			(V_LOOK
				(cond 
					(Night (messager say: N_CASTLE_BARS V_LOOK 34))
					(gateOpen (messager say: N_CASTLE_BARS V_LOOK 31))
					(else (messager say: N_CASTLE_BARS V_LOOK 33))
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance theSky of Feature
	(properties
		y 164
		noun N_SKY
		onMeCheck cBLUE
	)
)

(instance mountain of Feature
	(properties
		y 165
		noun N_MOUNTAIN
		onMeCheck cGREEN
	)
)

(instance castle of Feature
	(properties
		y 167
		noun N_CASTLE
		onMeCheck cCYAN
	)
)

(instance gateHouse of Feature
	(properties
		y 71
		noun N_WALL
		onMeCheck cRED
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK (wall doVerb: theVerb &rest))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance tower of Feature
	(properties
		y 62
		noun N_TOWER
		onMeCheck cMAGENTA
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK (messager say: N_TOWER V_LOOK 45))
			(V_DO (wall doVerb: theVerb &rest))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance bush of Feature
	(properties
		y 156
		noun N_BUSH
		onMeCheck cBROWN
	)
)

(instance wall of Feature
	(properties
		y 105
		noun N_WALL
		onMeCheck cLGREY
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK (messager say: N_WALL V_LOOK 49))
			(V_DO
				(if Night
					(if (< [egoStats CLIMB] 10)
						(messager say: N_WALL V_DO 48)
					else
						(= local97 0)
						(ego setScript: egoClimbsWall)
					)
				else
					(messager say: N_WALL V_DO 46)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance wWindow of Feature
	(properties
		y 105
		noun N_WINDOW
		onMeCheck cGREY
	)
)

(instance egoEnters of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego posn: 170 245 init: setMotion: MoveTo 170 180 self)
			)
			(1
				(if
				(and (or (Btst fSavedBarnard) (Btst fSavedElsa)) (not Night))
					(self cue:)
				else
					(HandsOn)
					(self cue:)
				)
			)
			(2 (self dispose:))
		)
	)
)

(instance egoExits of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: MoveTo (ego x?) 245 self)
			)
			(1 (curRoom newRoom: 54))
		)
	)
)

(instance karlPatrols of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= local90 0)
				(karl
					loop: 0
					cel: 0
					cycleSpeed: 12
					setCycle: Forward
					illegalBits: 0
					setMotion: MoveTo 223 74 self
				)
				(= local92 4)
			)
			(1 (= ticks 12))
			(2
				(karl loop: 2 cel: 0 cycleSpeed: 6 setCycle: EndLoop self)
			)
			(3 (HandsOn) (= ticks 12))
			(4
				(karl
					loop: 1
					cel: 0
					cycleSpeed: 12
					setCycle: Forward
					setMotion: MoveTo 68 74 self
				)
				(= local92 5)
			)
			(5 (= ticks 24))
			(6
				(karl loop: 2 cel: 2 cycleSpeed: 6 setCycle: BegLoop self)
			)
			(7 (= ticks 12))
			(8 (self changeState: 0))
		)
	)
)

(instance karlGreets of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= local92 4)
				(karl
					loop: 0
					cel: 0
					cycleSpeed: 12
					setCycle: Forward
					illegalBits: 0
					setMotion: MoveTo 161 74 self
				)
			)
			(1
				(HandsOff)
				(karl setMotion: 0 setCycle: 0)
				(= ticks 18)
			)
			(2
				(karl
					loop: local92
					cel: 0
					cycleSpeed: 6
					setCycle: EndLoop self
				)
				(= local90 1)
			)
			(3 (messager say: N_KARL 0 1 1 self))
			(4
				(portSound play:)
				(castleBars
					ignoreActors: 1
					setLoop: 7
					startUpd:
					setMotion: MoveTo 162 98 self
				)
			)
			(5
				(portSound stop:)
				(castleBars stopUpd:)
				(= gateOpen TRUE)
				(= ticks 12)
			)
			(6
				(ego setMotion: MoveTo (ego x?) (- (ego y?) 20) self)
			)
			(7
				(if (Btst fSavedElsa)
					(curRoom newRoom: 600)
				else
					(ego setScript: goTo39)
					(karl setScript: timeout)
					(self dispose:)
				)
			)
		)
	)
)

(instance karlStopToTalk of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(karl setMotion: 0 setCycle: 0)
				(= ticks 24)
			)
			(1
				(karl
					loop: local92
					cel: 0
					cycleSpeed: 6
					setCycle: EndLoop self
				)
			)
			(2 (= ticks 12))
			(3
				(Face ego karl)
				(= local90 1)
				(client setScript: timeout)
			)
		)
	)
)

(instance timeout of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if (and local91 (> (ego y?) 172))
			(client setScript: karlStopToTalk)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 12))
			(1
				(= local91 0)
				(if local93
					(= local93 0)
					(self changeState: 0)
				else
					(self cue:)
				)
			)
			(2
				(if gateOpen
					(messager say: N_KARL 0 2)
					(= local94 1)
					(= seconds 5)
				else
					(client setScript: karlResumesPatrol)
				)
			)
			(3
				(if local93
					(= local93 0)
					(self changeState: 0)
				else
					(= local94 1)
					(HandsOff)
					(ego setMotion: 0)
					(client setScript: karlResumesPatrol)
				)
			)
		)
	)
)

(instance karlResumesPatrol of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if gateOpen
					(castleBars setScript: closeGate)
				else
					(self cue:)
				)
			)
			(1
				(= local95 local92)
				(karl
					loop: local95
					cel: 1
					cycleSpeed: 6
					setCycle: BegLoop self
				)
			)
			(2
				(if (== local95 4)
					(karl
						loop: 0
						cel: 0
						cycleSpeed: 12
						setCycle: Forward
						illegalBits: 0
						setMotion: MoveTo 277 74 self
					)
					(= local92 4)
				else
					(karl
						loop: 1
						cel: 0
						cycleSpeed: 12
						setCycle: Forward
						setMotion: MoveTo 43 74 self
					)
					(= local92 5)
				)
				(= local90 0)
				(HandsOn)
			)
			(3 (= ticks 12))
			(4
				(if (== local95 4)
					(karl loop: 2 cel: 0 cycleSpeed: 6 setCycle: EndLoop self)
				else
					(karl loop: 2 cel: 2 cycleSpeed: 6 setCycle: BegLoop self)
				)
			)
			(5 (= ticks 6))
			(6
				(if (== local95 4)
					(karl
						loop: 1
						cel: 0
						cycleSpeed: 12
						setCycle: Forward
						setMotion: MoveTo 43 74 self
					)
					(= local92 5)
				else
					(client setScript: karlPatrols)
				)
			)
			(7 (= ticks 12))
			(8
				(karl loop: 2 cel: 2 cycleSpeed: 6 setCycle: BegLoop self)
			)
			(9 (= ticks 6))
			(10
				(client setScript: karlPatrols)
			)
		)
	)
)

(instance openGate of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if (timeout seconds?) (timeout seconds: 12))
				(if (< (karl x?) 160)
					(karl loop: 4 cel: 1 cycleSpeed: 6 setCycle: BegLoop self)
				else
					(karl loop: 5 cel: 1 cycleSpeed: 6 setCycle: BegLoop self)
				)
			)
			(1
				(if (< (karl x?) 160)
					(karl
						loop: 0
						cel: 0
						cycleSpeed: 12
						setCycle: Forward
						illegalBits: 0
						setMotion: MoveTo 160 74 self
					)
					(= local92 4)
				else
					(karl
						loop: 1
						cel: 0
						cycleSpeed: 12
						setCycle: Forward
						setMotion: MoveTo 160 74 self
					)
					(= local92 5)
				)
			)
			(2
				(karl
					loop: local92
					cel: 0
					cycleSpeed: 6
					setCycle: EndLoop self
				)
			)
			(3 (= ticks 12))
			(4
				(portSound play:)
				(castleBars
					ignoreActors: 1
					startUpd:
					setLoop: 7
					setMotion: MoveTo 162 98 self
				)
			)
			(5
				(portSound stop:)
				(curRoom horizon: 120)
				(= gateOpen 1)
				(castleBars setLoop: 7 stopUpd:)
				(HandsOn)
				(client setScript: timeout)
			)
		)
	)
)

(instance closeGateSafe of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(curRoom horizon: 0)
				(= cycles 8)
			)
			(1
				(portSound play:)
				(castleBars
					setLoop: 7
					ignoreActors: 0
					setMotion: MoveTo 162 150 self
				)
			)
			(2
				(castleBars stopUpd:)
				(portSound stop:)
				(= gateOpen FALSE)
				(HandsOn)
				(karl setScript: karlResumesPatrol)
				(self dispose:)
			)
		)
	)
)

(instance closeGate of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(castleGate ignoreActors: 1 cycleSpeed: 18 startUpd:)
				(if
					(and
						(< 125 (ego x?))
						(< (ego x?) 195)
						(< 0 (ego y?))
						(< (ego y?) 160)
					)
					(ego setMotion: MoveTo 160 155 self)
				else
					(client setScript: closeGateSafe)
				)
			)
			(1
				(NormalEgo)
				(HandsOff)
				(= ticks 12)
			)
			(2
				(portSound play:)
				(ego
					view: 529
					loop: 1
					cel: 0
					posn: (- (ego x?) 5) (ego y?)
					cycleSpeed: 36
					setCycle: EndLoop self
				)
				(castleBars setLoop: 7 setMotion: MoveTo 162 150)
			)
			(3
				(= gateOpen FALSE)
				(if crushedByGate
					(messager say: N_ROOM 0 36 1 self)
				else
					(messager say: N_ROOM 0 38 1 self)
				)
			)
			(4
				(castleBars setLoop: 7 setMotion: MoveTo 162 98 self)
			)
			(5
				(portSound stop:)
				(= gateOpen TRUE)
				(= seconds 3)
			)
			(6
				(if crushedByGate
					(EgoDead 43 44 0 0)
				else
					(ego
						posn: (+ (ego x?) 1) (ego y?)
						loop: 1
						cycleSpeed: 6
						setCycle: BegLoop self
					)
					(= crushedByGate TRUE)
				)
			)
			(7
				(messager say: N_ROOM 0 37 1 self)
				(NormalEgo 2)
				(HandsOn)
			)
			(8
				(ego moveSpeed: gEgoMoveSpeed cycleSpeed: gEgoCycleSpeed)
				(= seconds 3)
			)
			(9 (self dispose:))
		)
	)
)

(instance egoClimbsWall of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= gEgoMoveSpeed (ego moveSpeed?))
				(= gEgoCycleSpeed (ego cycleSpeed?))
				(HandsOff)
				(ego
					signal: 8192
					setCycle: Walk
					setMotion: MoveTo 85 171 self
				)
				(curRoom north: 0)
			)
			(1
				(ego
					view: 517
					setLoop: 0
					cel: 0
					setPri: 13
					xStep: 0
					yStep: 8
					cycleSpeed: 12
					moveSpeed: 12
					posn: 85 125
					setCycle: Forward
					setMotion: MoveTo (ego x?) 59 self
				)
			)
			(2
				(ego
					view: 517
					setLoop: 1
					cel: 0
					setPri: 13
					posn: 85 50
					xStep: 0
					yStep: 4
					cycleSpeed: 18
					moveSpeed: 18
					setCycle: EndLoop self
				)
			)
			(3 (= ticks 18))
			(4
				(if local97
					(ego
						view: 517
						setLoop: 2
						cel: 0
						setPri: 13
						moveSpeed: gEgoMoveSpeed
						cycleSpeed: gEgoCycleSpeed
						illegalBits: 0
						xStep: 8
						yStep: 16
						ignoreActors: 1
						setCycle: EndLoop self
					)
				else
					(ego moveSpeed: gEgoMoveSpeed cycleSpeed: gEgoCycleSpeed)
					(curRoom newRoom: 39)
				)
			)
			(5
				(ego
					setLoop: 4
					posn: 106 55
					setMotion: MoveTo 151 166 self
				)
			)
			(6
				(ego
					cel: 0
					moveSpeed: gEgoMoveSpeed
					cycleSpeed: gEgoCycleSpeed
					setCycle: EndLoop self
				)
			)
			(7
				(if (not (TakeDamage 10))
					(EgoDead 68 69 2 5 517)
				else
					(ego
						view: 503
						setLoop: 4
						cel: 0
						x: (+ (ego x?) 19)
						y: (+ (ego y?) 18)
						setPri: 13
						illegalBits: -32768
						setCycle: 0
					)
					(switch climbFail
						(0
							(messager say: N_ROOM 0 39 1 self)
						)
						(1
							(messager say: N_ROOM 0 40 1 self)
						)
						(2
							(messager say: N_ROOM 0 41 1 self)
						)
						(3
							(messager say: N_ROOM 0 42 1 self)
						)
						(4
							(messager say: N_ROOM 0 43 1 self)
						)
						(else 
							(messager say: N_ROOM 0 44 1 self)
						)
					)
					(++ climbFail)
				)
			)
			(8
				(ego
					moveSpeed: gEgoMoveSpeed
					cycleSpeed: gEgoCycleSpeed
					setCycle: EndLoop self
				)
			)
			(9
				(HandsOn)
				(NormalEgo)
				(self dispose:)
			)
		)
	)
)

(instance egoClimbsDown of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= gEgoMoveSpeed (ego moveSpeed?))
				(= gEgoCycleSpeed (ego cycleSpeed?))
				(HandsOff)
				(curRoom north: 0)
				(ego
					view: 517
					setLoop: 8
					cel: 0
					setPri: 14
					posn: 85 56
					cycleSpeed: 10
					signal: (| (ego signal?) $2000)
					init:
					setCycle: EndLoop self
				)
			)
			(1
				(ego
					setLoop: 0
					cel: 0
					posn: 85 68
					setPri: 14
					illegalBits: 0
					cycleSpeed: gEgoCycleSpeed
					setCycle: Forward
					setMotion: MoveTo 85 115 self
				)
			)
			(2
				(ego
					setLoop: 2
					cel: 0
					posn: 87 149
					cycleSpeed: 2
					setCycle: EndLoop self
				)
			)
			(3
				(ego
					setLoop: 4
					cel: 0
					posn: 106 150
					setStep: 6 12
					cycleSpeed: gEgoCycleSpeed
					setCycle: Forward
					setMotion: MoveTo 114 177 self
				)
			)
			(4
				(ego setLoop: 6 cel: 0 setCycle: EndLoop self)
			)
			(5 (= ticks 18))
			(6
				(ego
					view: 503
					loop: 4
					cel: 0
					posn: 113 163
					setCycle: EndLoop self
				)
			)
			(7
				(NormalEgo 2 0)
				(theGame setCursor: waitCursor)
				(ego cycleSpeed: 6 setMotion: MoveTo (ego x?) 180 self)
			)
			(8 (= ticks 6))
			(9
				(ego moveSpeed: gEgoMoveSpeed cycleSpeed: gEgoCycleSpeed)
				(messager say: N_ROOM 0 35 1 self)
			)
			(10
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance egoLeavesCastle of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego init: posn: 161 159 setMotion: MoveTo 161 164 self)
				(castleGate
					startUpd:
					ignoreActors: 1
					setMotion: MoveTo 162 150
				)
			)
			(1
				(HandsOn)
				(castleGate setMotion: 0 stopUpd: ignoreActors: 0)
				(karl setScript: karlResumesPatrol)
				(self dispose:)
			)
		)
	)
)

(instance goTo39 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: MoveTo 163 136 self)
			)
			(1 (curRoom newRoom: 39))
		)
	)
)

(instance oneTime of Sound
	(properties
		number 94
	)
)

(instance portSound of Sound
	(properties
		number 90
		priority 8
	)
)

(instance karlTalker of Talker
	(properties
		x 10
		y 10
		view 1036
		talkWidth 260
		textX 15
		textY 110
	)
	
	(method (init)
		(= nightPalette 2036)
		(PalVary PALVARYTARGET 2036)
		(kernel_128 1036)
		(= font userFont)
		(super init: karlBust karlEye karlMouth &rest)
	)
)

(instance karlBust of Prop
	(properties
		view 1036
	)
)

(instance karlMouth of Prop
	(properties
		nsTop 49
		nsLeft 44
		view 1036
		loop 1
	)
)

(instance karlEye of Prop
	(properties
		nsTop 35
		nsLeft 46
		view 1036
		loop 2
	)
)
