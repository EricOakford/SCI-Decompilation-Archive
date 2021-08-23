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
	giantGone
	fightWithSword
	cueWhat
	giantTellMainBranch = [
		STARTTELL
		C_BRAUGGI
		C_NORTHLANDS
		-1		;C_BARGAIN
		C_HUNGER
		C_FIGHTING
		ENDTELL
		]
	giantTell1 = [
		STARTTELL
		C_FRUIT
		C_MEAD
		C_GEM
		ENDTELL
		]
	[giantTellTree 5]
	giantTellKeys = [
		STARTTELL
		-1		;C_BARGAIN
		ENDTELL
		]
)

(enum 1	;cueWhat
	cueGiant
	cueGem
)

(instance giantBlocks of Script
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
		(= [giantTellTree 0] @giantTellMainBranch)
		(= [giantTellTree 1] @giantTell1)
		(= [giantTellTree 2] ENDTELL)
		(self
			addObstacle:
				((Polygon new:)
					type: PBarredAccess
					init:
						0 0
						319 0
						319 189
						0 189
						0 162
						41 183
						141 183
						194 130
						179 126
						130 126
						97 153
						0 153
					yourself:
				)
		)
		(LoadMany RES_SOUND 47 9)
		(LoadMany RES_VIEW 5 517 503)
		(if (not (= giantGone (Btst fGotGem)))
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
		;UPGRADE
;;;		(cave init:)
;;;		(yellowSnow init:)
;;;		(sky init:)
;;;		(moreSky init:)
;;;		(snowRock init:)
;;;		(log init:)
;;;		(mountain init:)
;;;		(snow init:)
;;;		(snowyRocks init:)
		
		(cSound fade:)
		(NormalEgo)
		(ChangeGait MOVE_WALK FALSE)
		(if giantGone
			(cSound number: 47)
			(ego posn: 1 158 init: setMotion: MoveTo 25 158)
		else
			(cSound number: 48)
			(frostSound number: 14 init:)
			(ego posn: 1 158 init: setScript: egoInit)
			(caveMouth init:)
			(giantTeller init: giant @giantTellMainBranch @giantTellTree @giantTellKeys)
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
		(Bset fBeenIn58)
		(super dispose:)
	)
	
	(method (doVerb theVerb &tmp spell)
		(switch theVerb
			(V_LOOK
				(= cueWhat 1)
				(messager say: N_ROOM V_LOOK NULL 0 self)
			)
			(V_DETECT
				(if (CastSpell spell)
					(messager say: N_ROOM V_DETECT NULL)
				)
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
		(switch cueWhat
			(cueGiant
				(if (not (cast contains: giant))
					(if (Btst fGotGem)
						(messager say: N_ROOM V_LOOK C_GIANTGONE)
					else
						(messager say: N_ROOM V_LOOK C_GIANTBORED)
					)
				)
			)
			(cueGem
				(if (not (ego has: iMagicGem))
					(messager say: N_GIANT V_LOOK C_LOOKGEM)
				)
			)
			(else 
				(cSound number: 47 loop: -1 play:)
			)
		)
		(= cueWhat 0)
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
		signal (| fixPriOn stopUpdOn)
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
		signal fixPriOn
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
		(AssertPalette 59)
		(super init: &rest)
	)
)

(instance giantTeller of Teller
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(V_LOOK
					(messager say: N_GIANT V_LOOK NULL 0 curRoom)
					(return TRUE)
				)
				(V_DO
					(giant setScript: Challenge)
					(return TRUE)
				)
				(V_SWORD
					(= fightWithSword TRUE)
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
						(OneOf theVerb
							34 42 44 46 16 38 21
							36 39 32 29 37 22 26
							14 17 27 23 31 30 40
							43 45 53 11 28 20 35
							15 10 24 12 18 19 47
							41 33
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
				(if (== howFast slow)
					(self cue:)
				else
					(frostSound play:)
					(giant setCel: 3 setCycle: CycleTo 7 1 self)
				)
			)
			(6
				(if (not (Btst fBeenIn58))
					(messager say: N_GIANT V_CONVERSATION C_NORTHLANDS 0 self)
				)
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
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(messager say: N_ROOM NULL C_GIANTLEAVES 1 self)
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
				(= giantGone TRUE)
				(self dispose:)
				(HandsOn)
			)
		)
	)
)

(instance doneDeal of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(messager say: N_ROOM NULL NULL 0 self)
			)
			(1
				(ego get: iMagicGem 1)
				(Bset fGotGem)
				(SolvePuzzle f58GetGem 8)
				(giant setScript: giantExits)
			)
		)
	)
)

(instance ShowOff of Script
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
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(self setScript: ShowOff self)
			)
			(1
				(messager say: N_GIANT V_CONVERSATION C_FIGHTING 1 self)
			)
			(2
				(HandsOn)
				(giant setScript: giantBlocks)
			)
		)
	)
)

(instance WalkToGiant of Script
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
				(ego
					ignoreActors: FALSE
					illegalBits: cWHITE
				)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance giantFights of Script
	(method (changeState newState &tmp theLoop temp1 theX theY temp4 temp5 theXStep theYStep)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					ignoreActors: TRUE
					illegalBits: FALSE
				)
				(if (and (!= (ego x?) 163) (!= (ego y?) 127))
					(ego setMotion: MoveTo 163 127 self)
				else
					(self cue:)
				)
			)
			(1 (self cue:))
			(2
				(= theLoop (if fightWithSword 0 else 2))
				(ego
					illegalBits: 0
					ignoreActors:
					view: 502
					setLoop: theLoop
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
				(= theX (ego x?))
				(= theY (ego y?))
				(= temp4 144)
				(= temp5 142)
				(= theXStep (/ (- temp4 theX) (- (ego lastCel:) 5)))
				(= theYStep (/ (- temp5 theY) (- (ego lastCel:) 5)))
				(giant setCycle: EndLoop self)
				(ego xStep: theXStep yStep: theYStep setCycle: CycleTo 5 1)
			)
			(5
				(frostSound play:)
				(if (Btst fHitByGiant) (ego setCel: 5 setCycle: CycleTo 8 1))
				(giant setCycle: BegLoop self)
			)
			(6
				(frostSound stop:)
				(= ticks 120)
			)
			(7
				(if (or (Btst fHitByGiant) (not (TakeDamage 20)))
					(EgoDead 86 87 3 3 59)
				else
					(Bset fHitByGiant)
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
				(messager say: N_ROOM NULL NULL 4)
				(HandsOn)
				(giant setScript: giantBlocks)
				(self dispose:)
			)
		)
	)
)

(instance giveFruit of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (ego has: iFruit)
					(+= numApples (ego use: iFruit 50))
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
						((>= numApples 50)
							(giant setScript: doneDeal)
						)
						((>= numApples 40)
							(messager say: N_ROOM NULL C_ALMOSTENOUGH 1 self)
						)
						((== numApples 0)
							(messager say: N_ROOM NULL C_NOFRUIT 1 self)
						)
						(else
							(messager say: N_ROOM NULL C_NOTENOUGH 1 self)
						)
					)
				else
					(messager say: N_ROOM NULL C_NOFRUIT 1 self)
				)
			)
			((HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance giantMagic of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego ignoreActors: TRUE illegalBits: 0)
				(if (and (!= (ego x?) 163) (!= (ego y?) 127))
					(ego setMotion: MoveTo 163 127 self)
				else
					(self cue:)
				)
			)
			(1
				(if throwProjectile
					(messager say: N_ROOM NULL C_THROWSOMETHING 1 self)
				else
					(messager say: N_ROOM NULL C_CASTSPELL 1 self)
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
					((and (> (ego y?) 180) (> (ego x?) 128))
						(ego posn: (- (ego x?) 12) (- (ego y?) 10))
					)
					((> (ego y?) 148)
						(ego posn: (ego x?) (ego y?))
					)
				)
				(ego
					setLoop: 1
					cel: 0
					posn: (+ (ego x?) 9) (+ (ego y?) 10)
					illegalBits: cWHITE
				)
				(self cue:)
			)
			(5
				(ego setCycle: EndLoop self)
			)
			(6
				(messager say: N_ROOM NULL C_NORUNNING 1 self)
				(ChangeGait MOVE_WALK FALSE)
				;(TakeDamage 2)
			)
			(7
				;UPGRADE: Ego can now die from the fall
				(if (not (TakeDamage 2))
					(EgoDead C_DIE_FALL C_DIE_FALL_TITLE 0 1 3)
				)
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
		(AssertPalette 1059)
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
