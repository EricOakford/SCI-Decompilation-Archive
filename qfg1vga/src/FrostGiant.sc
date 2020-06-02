;;; Sierra Script 1.0 - (do not remove this comment)
(script# 58)
(include game.sh) (include "58.shm")
(use Main)
(use Teller)
(use Procs)
(use Talker)
(use PolyPath)
(use Polygon)
(use Feature)
(use LoadMany)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm58 0
	brauggiTalker 1
)

(local
	local0
	local1
	local2
	throwProjectile
	local4
	triedToFight
	local6
	[local7 7] = [0 2 8 -1 6 3 999]
	[local14 5] = [0 4 7 5 999]
	[local19 5]
	[local24 3] = [0 -1 999]
)
(instance giantBlocks of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= local1 (Random 4 8))
				(= local0 0)
				(swishSound init:)
				(giant
					setLoop: 1
					cel: 1
					cycleSpeed: 12
					posn: 164 53
					setPri: 12
				)
				(self cue:)
			)
			(1
				(swishSound play:)
				(giant setCycle: CycleTo 4 1 self)
			)
			(2 (= cycles (Random 10 20)))
			(3
				(swishSound play:)
				(giant setCycle: CycleTo 0 1 self)
			)
			(4 (= cycles (Random 10 20)))
			(5
				(if (!= (++ local0) local1)
					(self changeState: 1)
				else
					(= seconds (Random 5 15))
				)
			)
			(6 (self changeState: 0))
		)
	)
)

(instance rm58 of Room
	(properties
		noun N_ROOM
		picture 58
		style DISSOLVE
		west 57
	)
	
	(method (init)
		(= [local19 0] @local7)
		(= [local19 1] @local14)
		(= [local19 2] 999)
		(self
			addObstacle:
				((Polygon new:)
					type: 2
					init:
						0
						0
						319
						0
						319
						189
						0
						189
						0
						162
						41
						183
						141
						183
						194
						130
						179
						126
						130
						126
						97
						153
						0
						153
					yourself:
				)
		)
		(LoadMany RES_SOUND 47 9)
		(LoadMany RES_VIEW 5 517 503)
		(if (not (= local4 (Btst OBTAINED_GEM)))
			(LoadMany RES_VIEW 59 502 513 515)
			(LoadMany RES_SOUND 48 59 65 14)
		)
		(super init:)
		(self
			setFeatures:
				cave
				yellowSnow
				sky
				moreSky
				snowRock
				log
				mountain
				snow
				snowyRocks
		)
		(cSound fade:)
		(NormalEgo)
		(ChangeGait MOVE_WALK 0)
		(if local4
			(cSound number: 47)
			(ego posn: 1 158 init: setMotion: MoveTo 25 158)
		else
			(cSound number: 48)
			(frostSound number: 14 init:)
			(ego posn: 1 158 init: setScript: egoInit)
			(caveMouth init:)
			(giantTeller init: giant @local7 @local19 @local24)
		)
		(cSound loop: 1 play: self)
	)
	
	(method (doit)
		(super doit:)
		(if (and (== (ego view?) 5) (not (ego script?)))
			(ego setScript: egoRuns)
		)
	)
	
	(method (dispose)
		(= nightPalette 0)
		(Bset VISITED_FROST_CAVE)
		(super dispose:)
	)
	
	(method (doVerb theVerb &tmp spell)
		(switch theVerb
			(V_LOOK
				(= local6 TRUE)
				(messager say: N_ROOM V_LOOK 0 0 self)
			)
			(V_DETECT
				(if (CastSpell spell) (messager say: N_ROOM V_DETECT 0))
			)
			(V_TRIGGER
				(giant setScript: giantMagic)
			)
			(V_DAZZLE
				(giant setScript: giantMagic)
			)
			(V_CALM
				(giant setScript: giantMagic)
			)
			(V_FLAME
				(giant setScript: giantMagic)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
	
	(method (cue)
		(switch local6
			(1
				(if (not (cast contains: giant))
					(if (Btst OBTAINED_GEM)
						(messager say: N_ROOM V_LOOK 18)
					else
						(messager say: N_ROOM V_LOOK 19)
					)
				)
			)
			(2
				(if (not (ego has: iMagicGem)) (messager say: N_GIANT V_LOOK 9))
			)
			(else 
				(cSound number: 47 loop: -1 play:)
			)
		)
		(= local6 0)
	)
)

(instance cave of Feature
	(properties
		x 165
		y 51
		noun N_CAVE
		nsTop 57
		nsLeft 133
		nsBottom 99
		nsRight 197
		sightAngle 40
	)
)

(instance yellowSnow of Feature
	(properties
		x 77
		y 123
		noun N_YELLOWSNOW
		nsTop 121
		nsLeft 71
		nsBottom 126
		nsRight 83
		sightAngle 40
	)
)

(instance sky of Feature
	(properties
		x 241
		y 93
		z 81
		noun N_SKY
		nsTop 1
		nsLeft 215
		nsBottom 23
		nsRight 267
		sightAngle 40
	)
)

(instance moreSky of Feature
	(properties
		x 97
		y 113
		z 110
		noun N_MORESKY
		nsLeft 80
		nsBottom 7
		nsRight 114
		sightAngle 40
	)
)

(instance snowRock of Feature
	(properties
		x 88
		y 116
		z 21
		noun N_SNOWROCK
		nsTop 78
		nsLeft 56
		nsBottom 112
		nsRight 121
		sightAngle 40
	)
)

(instance log of Feature
	(properties
		x 237
		y 183
		z 19
		noun N_LOG
		nsTop 151
		nsLeft 168
		nsBottom 177
		nsRight 307
		sightAngle 40
	)
)

(instance mountain of Feature
	(properties
		x 273
		y 108
		z 70
		noun N_MOUNTAIN
		nsTop 29
		nsLeft 230
		nsBottom 48
		nsRight 317
		sightAngle 40
	)
)

(instance snow of Feature
	(properties
		x 15
		y 112
		z 76
		noun N_SNOW
		nsTop 17
		nsBottom 56
		nsRight 31
		sightAngle 40
	)
)

(instance snowyRocks of Feature
	(properties
		x 273
		y 95
		noun N_SNOWYROCKS
		nsTop 69
		nsLeft 232
		nsBottom 121
		nsRight 315
		sightAngle 40
	)
)

(instance legs of View
	(properties
		x 164
		y 114
		view 59
		loop 4
		priority 12
		signal $0011
	)
	
	(method (doVerb theVerb)
		(giant doVerb: theVerb &rest)
	)
)

(instance caveMouth of Prop
	(properties
		x 137
		y 36
		view 59
		loop 7
		priority 6
		signal $0010
	)
	
	(method (doVerb theVerb)
		(cave doVerb: theVerb &rest)
	)
)

(instance giant of Actor
	(properties
		x 167
		y 100
		noun N_GIANT
		yStep 3
		view 59
		illegalBits $0000
		xStep 5
	)
	
	(method (init)
		(= nightPalette 159)
		(PalVary PALVARYTARGET 159)
		(kernel_128 59)
		(super init: &rest)
	)
)

(instance giantTeller of Teller
	(properties)
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(V_LOOK
					(messager say: N_GIANT V_LOOK 0 0 curRoom)
					(return TRUE)
				)
				(V_DO
					(giant setScript: Challenge)
					(return TRUE)
				)
				(V_SWORD
					(= triedToFight TRUE)
					(giant setScript: giantFights)
					(return TRUE)
				)
				(V_DAGGER
					(giant setScript: giantFights)
					(return TRUE)
				)
				(V_ROCK
					(= throwProjectile TRUE)
					(giant setScript: giantMagic)
					(return TRUE)
				)
				(V_FRUIT
					(giant setScript: giveFruit)
					(return TRUE)
				)
				(V_TALK
					(super doVerb: theVerb &rest)
				)
				(V_FLAME
					(giant setScript: giantMagic)
					(return TRUE)
				)
				(else 
					(if
						(OneOf
							theVerb
							34
							42
							44
							46
							16
							38
							21
							36
							39
							32
							29
							37
							22
							26
							14
							17
							27
							23
							31
							30
							40
							43
							45
							53
							11
							28
							20
							35
							15
							10
							24
							12
							18
							19
							47
							41
							33
						)
						(messager say: N_GIANT V_LOOK C_NOTHANKS)
						(return TRUE)
					else
						(super doVerb: theVerb &rest)
					)
				)
			)
		)
	)
)

(instance frostSound of Sound
	(properties
		number 14
		priority 2
	)
)

(instance swishSound of Sound
	(properties
		number 59
	)
)

(instance egoInit of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(client setPri: 14 setMotion: PolyPath 144 142 self)
			)
			(1
				(giant
					setPri: 14
					setLoop: 0
					init:
					actions: giantTeller
					hide:
					setScript: giantInit
				)
				(self cue:)
			)
			(2
				(Face client giant)
				(client setScript: 0)
			)
		)
	)
)

(instance giantInit of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(caveMouth setCycle: EndLoop self)
			)
			(1 (= seconds 1))
			(2
				(giant show: setCycle: EndLoop self)
			)
			(3
				(giant setCel: 3 setCycle: CycleTo 7 1 self)
			)
			(4
				(caveMouth setCycle: BegLoop)
				(frostSound number: 59 play:)
				(giant
					posn: 164 53
					setLoop: 1
					cycleSpeed: 18
					setPri: 13
					cel: 0
					setCycle: CycleTo 3 1 self
				)
				(legs actions: giantTeller init:)
			)
			(5
				(if (== howFast 0)
					(self cue:)
				else
					(frostSound play:)
					(giant setCel: 3 setCycle: CycleTo 7 1 self)
				)
			)
			(6
				(if (not (Btst VISITED_FROST_CAVE)) (messager say: N_GIANT V_CONVERSATION C_NORTHLANDS 0 self))
				(HandsOn)
			)
			(7
				(caveMouth dispose:)
				(client setScript: giantBlocks)
			)
		)
	)
)

(instance giantExits of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(messager say: N_ROOM 0 C_GIANTLEAVES 1 self)
			)
			(1
				(legs dispose:)
				(giant
					setLoop: 3
					setCel: -1
					cel: 0
					posn: 172 112
					setCycle: 0
					cycleSpeed: 12
					setPri: 14
					illegalBits: 0
				)
				(caveMouth init: setCycle: EndLoop self)
			)
			(2
				(caveMouth stopUpd:)
				(giant setCycle: CycleTo 2 1 self)
			)
			(3
				(giant
					setPri: (- (caveMouth priority?) 1)
					setCel: 3
					hide:
				)
				(caveMouth setCycle: BegLoop self)
			)
			(4
				(giant dispose:)
				(caveMouth dispose:)
				(cSound number: 47 play:)
				(= local4 1)
				(self dispose:)
				(HandsOn)
			)
		)
	)
)

(instance doneDeal of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(messager say: N_ROOM 0 0 0 self)
			)
			(1
				(ego get: iMagicGem 1)
				(Bset OBTAINED_GEM)
				(SolvePuzzle POINTS_GETGLOWINGGEM 8)
				(giant setScript: giantExits)
			)
		)
	)
)

(instance ShowOff of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(frostSound number: 59 play:)
				(giant
					cycleSpeed: 12
					posn: (- (giant x?) 6) (+ (giant y?) 29)
					setLoop: 2
					cel: 0
					setCycle: EndLoop self
				)
			)
			(1
				(if (== howFast 0)
					(self cue:)
				else
					(giant setCycle: EndLoop self)
				)
			)
			(2
				(frostSound stop:)
				(client setScript: 0)
			)
		)
	)
)

(instance Challenge of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(self setScript: ShowOff self)
			)
			(1 (messager say: N_GIANT V_CONVERSATION C_FIGHTING 1 self))
			(2
				(HandsOn)
				(giant setScript: giantBlocks)
			)
		)
	)
)

(instance WalkToGiant of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego ignoreActors: 1 illegalBits: 0)
				(if (< (ego y?) (+ (giant y?) 10))
					(ego setMotion: PolyPath 144 142 self)
				else
					(self cue:)
				)
			)
			(1
				(ego ignoreActors: 0 illegalBits: -32768)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance giantFights of Script
	(properties)
	
	(method (changeState newState &tmp temp0 temp1 egoX egoY temp4 temp5 temp6 temp7)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego ignoreActors: 1 illegalBits: 0)
				(if (and (!= (ego x?) 163) (!= (ego y?) 127))
					(ego setMotion: MoveTo 163 127 self)
				else
					(self cue:)
				)
			)
			(1 (self cue:))
			(2
				(= temp0 (if triedToFight 0 else 2))
				(ego
					illegalBits: 0
					ignoreActors:
					view: 502
					setLoop: temp0
					cel: 0
					setCycle: EndLoop self
				)
			)
			(3
				(frostSound number: 59 play:)
				(giant
					setLoop: 2
					cel: 0
					posn: (- (giant x?) 6) (+ (giant y?) 29)
					cycleSpeed: 12
					setCycle: CycleTo 5 1 self
				)
			)
			(4
				(ego
					view: 513
					posn: (ego x?) 144
					setLoop: 1
					setCel: -1
					cel: 0
					illegalBits: 0
				)
				(= egoX (ego x?))
				(= egoY (ego y?))
				(= temp4 144)
				(= temp5 142)
				(= temp6 (/ (- temp4 egoX) (- (ego lastCel:) 5)))
				(= temp7 (/ (- temp5 egoY) (- (ego lastCel:) 5)))
				(giant setCycle: EndLoop self)
				(ego xStep: temp6 yStep: temp7 setCycle: CycleTo 5 1)
			)
			(5
				(frostSound play:)
				(if (Btst WHACKED_BY_GIANT) (ego setCel: 5 setCycle: CycleTo 8 1))
				(giant setCycle: BegLoop self)
			)
			(6
				(frostSound stop:)
				(= ticks 120)
			)
			(7
				(if (or (Btst WHACKED_BY_GIANT) (not (TakeDamage 20)))
					(EgoDead 86 87 3 3 59)
				else
					(Bset WHACKED_BY_GIANT)
					(self cue:)
				)
			)
			(8
				(giant
					setLoop: 1
					setCel: -1
					posn: (+ (giant x?) 6) (- (giant y?) 29)
					cel: 0
					setPri: 11
					setCycle: EndLoop self
				)
			)
			(9
				(ego
					view: 515
					posn: 143 140
					setLoop: 1
					setCel: 5
					setCycle: CycleTo 0 -1 self
				)
			)
			(10
				(NormalEgo)
				(ego loop: 3 posn: 144 142 setPri: 13 forceUpd:)
				(self setScript: ShowOff self)
			)
			(11
				(messager say: N_ROOM 0 0 4)
				(HandsOn)
				(giant setScript: giantBlocks)
				(self dispose:)
			)
		)
	)
)

(instance giveFruit of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (ego has: iFruit)
					(= numApples (+ numApples (ego use: iFruit 50)))
					((inventory at: iFruit) amount: 1)
				)
				(HandsOff)
				(if (< (ego y?) (+ (giant y?) 10))
					(ego setMotion: PolyPath 144 142 self)
				else
					(self cue:)
				)
			)
			(1
				(if (ego has: iFruit)
					(ego use: iFruit)
					(cond 
						((>= numApples 50) (giant setScript: doneDeal))
						((>= numApples 40) (messager say: N_ROOM 0 C_ALMOSTENOUGH 1 self))
						((== numApples 0) (messager say: N_ROOM 0 C_NOFRUIT 1 self))
						(else (messager say: N_ROOM 0 C_NOTENOUGH 1 self))
					)
				else
					(messager say: N_ROOM 0 C_NOFRUIT 1 self)
				)
			)
			((HandsOn) (self dispose:))
		)
	)
)

(instance giantMagic of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego ignoreActors: 1 illegalBits: 0)
				(if (and (!= (ego x?) 163) (!= (ego y?) 127))
					(ego setMotion: MoveTo 163 127 self)
				else
					(self cue:)
				)
			)
			(1
				(if throwProjectile
					(messager say: N_ROOM 0 C_THROWSOMETHING 1 self)
				else
					(messager say: N_ROOM 0 C_CASTSPELL 1 self)
				)
			)
			(2
				(giant
					setLoop: 2
					posn: (- (giant x?) 6) (+ (giant y?) 29)
					setCel: 0
				)
				(= ticks 120)
			)
			(3
				(giant setCel: 1)
				(frostSound number: 65 play: self)
				(= cycles 2)
			)
			(4
				(giant setCel: 2)
				(= cycles 2)
			)
			(5
				(giant setCel: 3 stopUpd:)
				(frostSound number: 14 play:)
				(ego
					view: 513
					posn: (+ (ego x?) 20) (ego y?)
					setLoop: 0
					setCel: 0
					setCycle: EndLoop self
				)
			)
			(6 (EgoDead 116 117 0 0 59))
		)
	)
)

(instance egoRuns of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setCycle: 0 setMotion: 0)
				(= ticks 10)
			)
			(1
				(ego
					illegalBits: 0
					ignoreActors:
					view: 5
					setPri: 14
					setCycle: Walk
				)
				(self cue:)
			)
			(2
				(frostSound number: 9 play:)
				(ego
					view: 3
					posn: (ego x?) (ego y?)
					setLoop: 0
					setCel: -1
					cel: 0
					setCycle: EndLoop self
				)
			)
			(3
				(frostSound stop:)
				(= cycles 8)
			)
			(4
				(cond 
					((and (> (ego y?) 180) (> (ego x?) 128)) (ego posn: (- (ego x?) 12) (- (ego y?) 10)))
					((> (ego y?) 148) (ego posn: (ego x?) (ego y?)))
				)
				(ego
					setLoop: 1
					cel: 0
					posn: (+ (ego x?) 9) (+ (ego y?) 10)
					illegalBits: -32768
				)
				(self cue:)
			)
			(5 (ego setCycle: EndLoop self))
			(6
				(messager say: N_ROOM 0 C_NORUNNING 1 self)
				(ChangeGait MOVE_WALK 0)
				(TakeDamage 2)
			)
			(7
				(HandsOn)
				(ChangeTheCursor 1)
				(NormalEgo)
				(ego setPri: pYELLOW)
				(self dispose:)
			)
		)
	)
)

(instance brauggiTalker of Talker
	(properties
		x 10
		y 10
		view 1059
		talkWidth 260
		textX 15
		textY 110
	)
	
	(method (init)
		(= nightPalette 2059)
		(PalVary PALVARYTARGET 2059)
		(kernel_128 1059)
		(= font userFont)
		(super init: brauggiBust brauggiEye brauggiMouth &rest)
	)
)

(instance brauggiBust of Prop
	(properties
		view 1059
		loop 1
	)
)

(instance brauggiMouth of Prop
	(properties
		nsTop 53
		nsLeft 38
		view 1059
		loop 2
	)
)

(instance brauggiEye of Prop
	(properties
		nsTop 29
		nsLeft 45
		view 1059
		loop 4
	)
)
