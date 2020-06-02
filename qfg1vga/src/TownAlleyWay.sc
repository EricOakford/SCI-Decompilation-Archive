;;; Sierra Script 1.0 - (do not remove this comment)
(script# 333)
(include game.sh) (include "333.shm") (include "811.shm")
(use Main)
(use Teller)
(use Procs)
(use Talker)
(use Polygon)
(use Feature)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm333 0
	beggarTalker 1
)

(local
	local0
	gaveToBeggar
	local2
	talkCount
	local4
	climbCount
	beggarCue
	[local7 7] = [0 -3 -10 -16 4 -11 999]
	[local14 4] = [0 5 18 999]
	[local18 3] = [0 12 999]
	[local21 6] = [0 17 -15 9 4 999]
	[local27 5] = [0 8 -13 14 999]
	[local32 3] = [0 6 999]
	[local35 3] = [0 7 999]
	[local38 12]
	[local50 8] = [0 -3 -10 -16 -11 -15 -13 999]
)

(enum 1	;what is given to beggar
	giveMoney
	giveApples
	giveVegetables
)

(instance rm333 of Room
	(properties
		noun N_ROOM
		picture 333
		style HSHUTTER
	)
	
	(method (init)
		(= [local38 0] @local7)
		(= [local38 1] @local14)
		(= [local38 2] @local18)
		(= [local38 3] @local21)
		(= [local38 4] @local27)
		(= [local38 5] @local32)
		(= [local38 6] @local35)
		(= [local38 7] 999)
		(self
			addObstacle:
				((Polygon new:)
					type: PBarredAccess
					init:
						0 189
						0 0
						319 0
						319 189
						257 189
						213 151
						177 145
						170 137
						165 111
						143 112
						138 119
						143 133
						148 149
						121 179
						108 184
						85 189
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init:
						219 177
						177 177
						172 159
						206 158
					yourself:
				)
		)
		(Load VIEW 333)
		(super init:)
		(StatusLine enable:)
		(self setRegions: 811)
		(NormalEgo)
		(ego posn: 120 187 init: setMotion: MoveTo 120 180)
		(beggarTeller init: beggar @local7 @local38 @local50)
		(beggar actions: beggarTeller init:)
		(features
			add:
				onTheWalls
				theSky
				onBarrels
				onBarrels2
				onHotDogStand
				onBricks
				marksOnWall
			eachElementDo: #init
		)
		(= local0 0)
		(messager say: N_ROOM NULL C_LOOKROOM)
	)
	
	(method (doit)
		(if (and (> (ego y?) 187) (not (ego script?)))
			(ego setScript: goBackToStreet)
		)
		(cond 
			((and (not local0) (ego inRect: 100 135 215 185))
				(beggar setCycle: EndLoop)
				(messager say: N_BEGGAR NULL C_ALMSFORTHEPOOR)
				(= local0 1)
			)
			((and local0 (not (ego inRect: 100 135 215 185)))
				(beggar setCycle: BegLoop)
				(= local0 0)
			)
		)
		(super doit:)
	)
	
	(method (dispose)
		(= nightPalette 0)
		(Bset fBeenIn333)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(messager say: N_ROOM NULL C_LOOKROOM)
			)
			(V_DO
				(messager say: N_ROOM V_DO C_LOOKROOM)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance onTheWalls of Feature
	(properties
		x 75
		y 80
		onMeCheck ISNOTHIDDEN
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_LOOK)
			(messager say: N_ROOM V_LOOK C_LOOKROOM)
		else
			(super doVerb: theVerb &rest)
		)
	)
)

(instance theSky of Feature
	(properties
		x 165
		y 110
		z 98
		noun N_SKY
		nsTop 5
		nsLeft 124
		nsBottom 19
		nsRight 207
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_LOOK)
			(if Night
				(messager say: N_SKY V_LOOK C_NIGHT)
			else
				(messager say: N_SKY V_LOOK)
			)
		else
			(super doVerb: theVerb &rest)
		)
	)
)

(instance onBarrels of Feature
	(properties
		x 106
		y 148
		noun N_BARRELS
		nsTop 133
		nsLeft 90
		nsBottom 164
		nsRight 122
		sightAngle 40
		onMeCheck NEARCHECK
	)
)

(instance onBarrels2 of Feature
	(properties
		x 198
		y 120
		noun N_BARRELS2
		nsTop 104
		nsLeft 184
		nsBottom 136
		nsRight 212
		sightAngle 40
		onMeCheck NEARCHECK
	)
)

(instance onHotDogStand of Feature
	(properties
		x 114
		y 128
		noun N_HOTDOGSTAND
		nsTop 104
		nsLeft 105
		nsBottom 153
		nsRight 124
		sightAngle 40
		onMeCheck FARCHECK
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_LOOK)
			(messager say: N_HOTDOGSTAND V_LOOK)
		else
			(super doVerb: theVerb &rest)
		)
	)
)

(instance onBricks of Feature
	(properties
		x 158
		y 74
		noun N_BRICKS
		nsTop 44
		nsLeft 129
		nsBottom 107
		nsRight 187
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(messager say: N_BRICKS V_LOOK)
			)
			(V_DO
				(cond 
					((TrySkill CLIMB 35 0)
						(ego setScript: toTheCentaur)
					)
					((not [egoStats CLIMB])
						(messager say: N_ROOM NULL C_NOCLIMB)
						(++ climbCount)
					)
					((and (< 3 climbCount) (< climbCount 10))
						(messager say: N_ROOM NULL C_NOMORECLIMBING)
						(= climbCount 10)
					)
					(else
						(messager say: N_ROOM NULL C_CLIMBFAIL)
					)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance marksOnWall of Feature
	(properties
		x 120
		y 103
		noun N_MARKS
		nsTop 100
		nsLeft 112
		nsBottom 107
		nsRight 128
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(if (<= (ego y?) 117)
					(messager say: N_MARKS V_LOOK C_SEEMARKS)
				else
					(messager say: N_MARKS V_LOOK C_TOOFAR)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance beggar of Prop
	(properties
		x 184
		y 171
		noun N_BEGGAR
		view 333
		signal $4000
	)
)

(instance beggarTeller of Teller
	(properties)
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(V_LOOK
					(if gaveToBeggar
						(messager say: N_BEGGAR V_LOOK C_BOWLHASMONEY)
					else
						(messager say: N_BEGGAR V_LOOK C_BOWLEMPTY)
					)
					(return TRUE)
				)
				(V_TALK
					(cond 
						((not gaveToBeggar)
							(messager say: N_BEGGAR V_TALK C_BOWLHASMONEY)
							(return 1)
						)
						((> talkCount 5)
							(= local4 0)
							(messager say: N_BEGGAR V_TALK C_BACKTOWORK)
							(return TRUE)
						)
						(else
							(++ talkCount)
							(SolvePuzzle f333TalkToBeggar 1)
							(= local4 1)
							(super doVerb: theVerb &rest)
						)
					)
				)
				(V_DO
					(messager say: N_BEGGAR V_DO)
					(return TRUE)
				)
				(V_MONEY
					(SolvePuzzle f333GiveAlms 1)
					(if
						(or
							(> ((inventory at: iSilver) amount?) 0)
							(> ((inventory at: iGold) amount?) 0)
						)
						(= beggarCue giveMoney)
						(ego setScript: cueItScript)
						(messager say: N_BEGGAR V_MONEY)
						(= gaveToBeggar TRUE)
						(= local2 1)
						(= talkCount 0)
					else
						(messager say: N_BEGGAR V_MONEY C_NOFUNDS)
					)
					(return 1)
				)
				(V_FRUIT
					(messager say: N_BEGGAR V_FRUIT)
					(= beggarCue giveApples)
					(ego setScript: cueItScript)
				)
				(V_VEGETABLES
					(messager say: N_BEGGAR V_VEGETABLES)
					(= beggarCue giveVegetables)
					(ego setScript: cueItScript)
				)
				(V_DAGGER
					(messager say: N_STREET V_ROCK NULL 1 0 STREET)
				)
				(V_SWORD
					(messager say: N_STREET V_ROCK NULL 1 0 STREET)
				)
				(V_ROCK
					(messager say: N_STREET V_ROCK NULL 1 0 STREET)
				)
				(else 
					(super doVerb: theVerb &rest)
				)
			)
		)
	)
)

(instance toTheCentaur of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: MoveTo 161 112 self)
			)
			(1
				(ego
					view: 517
					setLoop: 0
					setCel: 0
					posn: 163 76
					setCycle: Walk
					setMotion: MoveTo 159 62 self
				)
			)
			(2
				(ego
					setLoop: 1
					setCel: 0
					posn: 161 44
					setCycle: CycleTo 7 1 self
				)
			)
			(3
				(curRoom newRoom: 53)
			)
		)
	)
)

(instance goBackToStreet of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (HandsOff) (= seconds 1))
			(1
				(if (and gaveToBeggar (not (Btst fBeenIn333)))
					(messager say: N_BEGGAR NULL C_DONTDRINKBREATH 1 self)
				else
					(self cue:)
				)
			)
			(2 (curRoom newRoom: 330))
		)
	)
)

(instance cueItScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= ticks 60))
			(1
				(switch beggarCue
					(giveMoney
						(ego use: iSilver 1)
					)
					(giveApples
						(ego use: iFruit 1)
					)
					(giveVegetables
						(ego use: iVegetables 1)
					)
				)
				(self cue:)
			)
			(2
				(self dispose:)
			)
		)
	)
)

(instance beggarTalker of Talker
	(properties
		x 5
		y 2
		view 1334
		talkWidth 260
		textX 15
		textY 112
	)
	
	(method (init)
		(= nightPalette 2334)
		(PalVary PALVARYTARGET 2334)
		(kernel_128 1334)
		(= font userFont)
		(super init: beggarBust beggarEye beggarMouth &rest)
	)
)

(instance beggarBust of Prop
	(properties
		view 1334
	)
)

(instance beggarMouth of Prop
	(properties
		nsTop 42
		nsLeft 52
		view 1334
		loop 1
	)
)

(instance beggarEye of Prop
	(properties
		nsTop 26
		nsLeft 79
		view 1334
		loop 2
	)
)
