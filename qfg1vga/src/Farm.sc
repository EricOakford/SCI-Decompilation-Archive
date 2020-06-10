;;; Sierra Script 1.0 - (do not remove this comment)
(script# 53)
(include game.sh) (include "53.shm")
(use Main)
(use Teller)
(use Procs)
(use Talker)
(use PolyPath)
(use Polygon)
(use Feature)
(use ForCount)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm53 0
	centaurTalker 1
)

(local
	centaurWatchesEgo
	centaurStandsStill
	local2
	local3
	[local4 2]
	local6
	[local7 3]
	local10
	local11
	local12
	[local13 7] = [0 -12 -4 -2 3 1 999]
	[local20 4] = [0 9 -11 999]
	[local24 5] = [0 13 6 5 999]
	[local29 4] = [0 -10 8 999]
	[local33 3] = [0 7 999]
	[local36 10]
	[local46 6] = [0 -12 -4 -2 -11 999]
)
(instance rm53 of Room
	(properties
		noun N_ROOM
		picture 53
		horizon 105
		north 36
	)
	
	(method (init)
		(= [local36 0] @local13)
		(= [local36 1] @local20)
		(= [local36 2] @local24)
		(= [local36 3] @local29)
		(= [local36 4] @local33)
		(= [local36 5] 999)
		(self
			addObstacle:
				((Polygon new:)
					type: PBarredAccess
					init:
						138 10
						127 97
						98 119
						0 119
						0 0
						319 0
						319 119
						183 119
						152 98
						144 10
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init:
						0 128
						111 128
						138 151
						134 171
						16 171
						16 185
						165 185
						305 185
						305 170
						169 170
						161 148
						188 126
						319 126
						319 189
						0 189
					yourself:
				)
		)
		(if (not Night)
			(Load RES_VIEW 53)
			(Load RES_VIEW 54)
			ForwardCounter
		)
		(= style
			(switch prevRoomNum
				(52 WIPERIGHT)
				(54 WIPELEFT)
				(36 WIPEDOWN)
				(else  -32764)
			)
		)
		(super init:)
		(cSound fade:)
		(features
			add:
				vase
				pots
				pineTree
				bush
				unplantedTree
				rake
				shovel
				frontTree
				aTree
				bush2
				wall
				trees1
				trees2
			eachElementDo: #init
			doit:
		)
		(wall approachVerbs: V_DO)
		(if (Btst 323) (Bclr 323) else (Bset 323))
		(if (not Night)
			(if (Btst 323)
				(centaur posn: 250 111)
			else
				(centaur posn: 50 111)
			)
			(centaurTeller init: centaur @local13 @local36 @local46)
			(centaur
				illegalBits: 0
				init:
				actions: centaurTeller
				xStep: 6
				setCycle: Walk
			)
			(tail init: setPri: 7 actions: centaurTeller hide:)
			(head init: setPri: 8 actions: centaurTeller hide:)
		)
		(NormalEgo)
		(ego init:)
		(switch prevRoomNum
			(36
				(= local11 140)
				(= local12 110)
				(ego posn: 140 106 setScript: hereHeComes)
			)
			(52
				(= local11 15)
				(= local12 124)
				(ego posn: -10 124 setScript: hereHeComes)
			)
			(54
				(= local11 300)
				(= local12 124)
				(ego posn: 340 124 setScript: hereHeComes)
			)
			(else 
				(= local11 148)
				(= local12 164)
				(ego loop: 3 posn: 148 170 setScript: hereHeComes)
			)
		)
	)
	
	(method (doit)
		(cond 
			((ego script?) 0)
			((curRoom script?) 0)
			((< (ego x?) 12)
				(curRoom setScript: sExitLeft)
			)
			((> (ego x?) 310)
				(curRoom setScript: sExitRight)
			)
			((and (not local6) (< (ego y?) 134))
				(= local6 1)
				(ego setPri: 8)
			)
			((and local6 (>= (ego y?) 134))
				(= local6 0)
				(ego setPri: -1)
			)
		)
		(if
			(or
				(and
					(> (centaur x?) 249)
					(not (Btst 323))
					(not centaurWatchesEgo)
					(not Night)
				)
				(and
					(< (centaur x?) 201)
					(Btst 323)
					(not centaurWatchesEgo)
					(not Night)
				)
				(and
					(< (ego distanceTo: centaur) 50)
					(< 130 (ego y?))
					(< (ego y?) 150)
					(not centaurWatchesEgo)
					(not Night)
				)
			)
			(centaur setScript: standStill)
		)
		(super doit:)
	)
	
	(method (dispose)
		(= nightPalette 0)
		(Bset fBeenIn53)
		(DisposeScript FORCOUNT)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO
				(if Night
					(messager say: N_ROOM V_DO 0 1)
				else
					(messager say: N_ROOM V_DO)
				)
			)
			(V_LOOK (messager say: N_ROOM V_LOOK))
			(V_ROCK (messager say: N_ROOM V_ROCK))
			(V_FLAME (messager say: N_ROOM V_FLAME))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance wall of Feature
	(properties
		x 150
		y 180
		noun N_WALL
		nsTop 172
		nsLeft 1
		nsBottom 188
		nsRight 318
		approachX 152
		approachY 169
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK (messager say: N_WALL V_DO))
			(V_DO
				(if Night
					(if (TrySkill CLIMB 35 0)
						(ego setScript: climbTheWall)
					else
						(messager say: N_WALL V_DO C_CLIMBFAIL)
					)
				else
					(messager say: N_WALL V_DO)
				)
			)
			(V_ROCK (messager say: N_WALL V_ROCK))
			(V_FLAME (messager say: N_ROOM V_FLAME))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance trees1 of Feature
	(properties
		x 50
		y 25
		noun N_TREES
		nsTop 1
		nsLeft 1
		nsBottom 51
		nsRight 109
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_FLAME)
			(messager say: N_ROOM V_FLAME)
		else
			(super doVerb: theVerb &rest)
		)
	)
)

(instance trees2 of Feature
	(properties
		x 150
		y 86
		noun N_TREES
		nsTop 1
		nsLeft 173
		nsBottom 51
		nsRight 319
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_FLAME)
			(messager say: N_ROOM V_FLAME)
		else
			(super doVerb: theVerb &rest)
		)
	)
)

(instance rake of Feature
	(properties
		x 241
		y 152
		noun N_RAKE
		nsTop 146
		nsLeft 221
		nsBottom 158
		nsRight 264
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_FLAME)
			(messager say: N_ROOM V_FLAME)
		else
			(super doVerb: theVerb &rest)
		)
	)
)

(instance vase of Feature
	(properties
		x 81
		y 136
		noun N_VASE
		nsTop 121
		nsLeft 67
		nsBottom 151
		nsRight 95
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_FLAME)
			(messager say: N_ROOM V_FLAME)
		else
			(super doVerb: theVerb &rest)
		)
	)
)

(instance pots of Feature
	(properties
		x 207
		y 141
		noun N_POTS
		nsTop 135
		nsLeft 187
		nsBottom 148
		nsRight 228
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_FLAME)
			(messager say: N_ROOM V_FLAME)
		else
			(super doVerb: theVerb &rest)
		)
	)
)

(instance pineTree of Feature
	(properties
		x 41
		y 99
		z 36
		noun N_PINETREE
		nsTop 40
		nsLeft 28
		nsBottom 87
		nsRight 54
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_FLAME)
			(messager say: N_ROOM V_FLAME)
		else
			(super doVerb: theVerb &rest)
		)
	)
)

(instance bush of Feature
	(properties
		x 90
		y 90
		noun N_BUSH
		nsTop 81
		nsLeft 78
		nsBottom 99
		nsRight 103
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_FLAME)
			(messager say: N_ROOM V_FLAME)
		else
			(super doVerb: theVerb &rest)
		)
	)
)

(instance unplantedTree of Feature
	(properties
		x 18
		y 130
		noun N_UNPLANTED_TREE
		nsTop 113
		nsLeft 5
		nsBottom 148
		nsRight 32
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_FLAME)
			(messager say: N_ROOM V_FLAME)
		else
			(super doVerb: theVerb &rest)
		)
	)
)

(instance shovel of Feature
	(properties
		x 46
		y 130
		noun N_SHOVEL
		nsTop 115
		nsLeft 35
		nsBottom 145
		nsRight 58
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK (messager say: N_SHOVEL V_LOOK))
			(V_FLAME (messager say: N_ROOM V_FLAME))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance frontTree of Feature
	(properties
		x 260
		y 114
		noun N_FRONT_TREE
		nsTop 83
		nsLeft 237
		nsBottom 146
		nsRight 284
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_FLAME)
			(messager say: N_ROOM V_FLAME)
		else
			(super doVerb: theVerb &rest)
		)
	)
)

(instance aTree of Feature
	(properties
		x 192
		y 67
		noun N_BARREN_TREE
		nsTop 36
		nsLeft 170
		nsBottom 99
		nsRight 214
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_FLAME)
			(messager say: N_ROOM V_FLAME)
		else
			(super doVerb: theVerb &rest)
		)
	)
)

(instance bush2 of Feature
	(properties
		x 232
		y 77
		noun N_BUSH2
		nsTop 57
		nsLeft 217
		nsBottom 98
		nsRight 247
		sightAngle 40
	)
)

(instance head of Prop
	(properties
		noun N_CENTAUR
		view 54
	)
	
	(method (doVerb theVerb)
		(centaur doVerb: theVerb &rest)
	)
)

(instance tail of Prop
	(properties
		noun N_CENTAUR
		view 54
		loop 2
	)
	
	(method (doVerb theVerb)
		(centaur doVerb: theVerb &rest)
	)
)

(instance centaur of Actor
	(properties
		noun N_CENTAUR
		view 53
		loop 5
	)
)

(instance centaurTeller of Teller
	(properties)
	
	(method (doChild)
		(return
			(if (== query -10)
				(SolvePuzzle POINTS_TALKTOFARMERABOUTLEADER 3)
				(return TRUE)
			else
				(super doChild: query)
			)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_MONEY
				(if (not centaurWatchesEgo)
					(= centaurStandsStill TRUE)
					(centaur setScript: standStill)
				else
					(messager say: N_CENTAUR V_MONEY)
				)
			)
			(V_SWORD (messager say: N_CENTAUR V_SWORD))
			(V_DAGGER (messager say: N_CENTAUR V_SWORD))
			(V_ROCK (messager say: N_CENTAUR V_ROCK)) ;Changed to correct message
			(V_LOOK
				(if centaurWatchesEgo
					(messager say: N_CENTAUR V_LOOK C_WATCHING 1)
				else
					(messager say: N_CENTAUR V_LOOK C_WORKING)
				)
			)
			(V_TALK
				(if (not centaurWatchesEgo)
					(= centaurStandsStill TRUE)
					(centaur setScript: standStill)
				else
					(SolvePuzzle POINTS_TALKTOFARMER 1)
					(super doVerb: theVerb &rest)
				)
			)
			(V_FLAME (messager say: N_ROOM V_FLAME))
			(else  (super doVerb: theVerb))
		)
		(return TRUE)
	)
)

(instance standStill of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= centaurWatchesEgo TRUE)
				(if (< (centaur cel?) 4)
					(centaur setCycle: EndLoop self)
				else
					(self cue:)
				)
			)
			(1
				(tail
					setLoop: (if (Btst 323) 3 else 2)
					posn:
						(if (Btst 323)
							(+ (centaur x?) 16)
						else
							(- (centaur x?) 16)
						)
						(- (centaur y?) 31)
					show:
				)
				(head
					posn:
						(if (Btst 323)
							(- (centaur x?) 13)
						else
							(+ (centaur x?) 13)
						)
						(- (centaur y?) 27)
					setLoop: (if (Btst 323) 0 else 1)
					show:
				)
				(centaur
					view: 54
					posn: (centaur x?) (centaur y?)
					setLoop: (if (Btst 323) 5 else 4)
					show:
					setMotion: 0
				)
				(self cue:)
			)
			(2
				(tail setCycle: ForwardCounter 2 self)
			)
			(3
				(centaur setCycle: 0)
				(if centaurStandsStill
					(if (not local2)
						(= local2 1)
						(messager say: N_ROOM 0 C_ALERTED)
					else
						(messager say: N_ROOM)
					)
					(self cue:)
				else
					(if (not local3) (= local3 1) (messager say: N_ROOM 0 C_APPROACHED))
					(self cue:)
				)
			)
			(4
				(tail cycleSpeed: 18 setCycle: Forward)
				(= ticks 60)
			)
			(5 (= ticks (Random 30 180)))
			(6
				(if (not (centaur cycler?))
					(= local10
						(GetAngle (centaur x?) 111 (ego x?) (ego y?))
					)
					(cond 
						((< (ego x?) 10) (self changeState: 7))
						((< (ego x?) 80) (self changeState: 8))
						((and (< 80 (ego x?)) (< (ego x?) 160)) (self changeState: 9))
						((< (ego x?) 240) (self changeState: 10))
						(else (self changeState: 11))
					)
				)
			)
			(7
				(head cel: (if (== (centaur loop?) 5) 0 else 4))
				(self changeState: 5)
			)
			(8
				(head cel: (if (== (centaur loop?) 5) 1 else 3))
				(self changeState: 5)
			)
			(9
				(head cel: 2)
				(self changeState: 5)
			)
			(10
				(head cel: (if (== (centaur loop?) 5) 3 else 1))
				(self changeState: 5)
			)
			(11
				(head cel: (if (== (centaur loop?) 5) 4 else 0))
				(self changeState: 5)
			)
		)
	)
)

(instance sExitLeft of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: MoveTo -40 (ego y?) self)
			)
			(1 (curRoom newRoom: 52))
		)
	)
)

(instance sExitRight of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: MoveTo 340 (ego y?) self)
			)
			(1 (curRoom newRoom: 54))
		)
	)
)

(instance climbTheWall of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: PolyPath 152 169 self)
			)
			(1 (curRoom newRoom: 334))
		)
	)
)

(instance hereHeComes of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if (not Night)
					(centaur setMotion: MoveTo 200 111 self)
				else
					(self cue:)
				)
			)
			(1
				(if (not Night)
					(centaur setScript: standStill)
				)
				(ego setMotion: MoveTo local11 local12 self)
			)
			(2
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance talkToYou of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if centaurStandsStill
					(if (not local2)
						(= local2 1)
						(messager say: N_ROOM NULL C_ALERTED)
					else
						(messager say: N_ROOM)
					)
					(self cue:)
				else
					(if (and (not local3) (OneOf (ego loop?) 0 3 6))
						(= local3 1)
						(messager say: N_ROOM 0 C_APPROACHED)
					)
					(self cue:)
				)
			)
			(1 (self dispose:))
		)
	)
)

(instance centaurTalker of Talker
	(properties
		x 10
		y 10
		view 1053
		talkWidth 260
		textX 15
		textY 110
	)
	
	(method (init)
		(= nightPalette 2053)
		(PalVary PALVARYTARGET 2053)
		(kernel_128 1053)
		(= font userFont)
		(super init: centaurBust centaurEye centaurMouth &rest)
	)
)

(instance centaurBust of Prop
	(properties
		view 1053
	)
)

(instance centaurMouth of Prop
	(properties
		nsTop 50
		nsLeft 32
		view 1053
		loop 1
	)
)

(instance centaurEye of Prop
	(properties
		nsTop 33
		nsLeft 32
		view 1053
		loop 2
	)
)
