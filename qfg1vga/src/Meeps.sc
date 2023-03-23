;;; Sierra Script 1.0 - (do not remove this comment)
(script# 60)
(include game.sh) (include "60.shm")
(use Main)
(use ThrowRock)
(use Teller)
(use Procs)
(use PolyPath)
(use Polygon)
(use Feature)
(use Sound)
(use Jump)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm60 0
)

(local
	meepTellMainBranch = [
		STARTTELL
		C_MEEPS
		-11		;C_ROCKS
		-6		;C_FUR
		C_BRIGANDS
		C_SCROLL
		-3		;C_BABY
		-2		;C_APPLES
		-9		;C_MAGIC
		ENDTELL
		]
	meepTell1 = [
		STARTTELL
		C_GREENFUR
		ENDTELL
		]
	meepTell2 = [
		STARTTELL
		C_HOLES
		ENDTELL
		]
	[meepTellTree 8]
	meepTellKeys = [
		STARTTELL
		-11		;C_ROCKS
		-6		;C_FUR
		ENDTELL
		]
	newApple
	local29
	meepsScared
	local31 =  1000
	local32
	local33
	local34
	local35
	local36
	local37
	local38
	local39
	local40
	[local41 2]
	bossOnScreen
)
(class MeepPeeps of Script
	(properties
		register2 -1
	)
)

(class Apple of Actor
	(properties
		noun N_APPLE
		view 61
		loop 0
		cel 0
	)
)

(instance rm60 of Room
	(properties
		noun N_ROOM
		picture 60
		style HSHUTTER
	)
	
	(method (init)
		(= [meepTellTree 0] @meepTellMainBranch)
		(= [meepTellTree 1] @meepTell2)
		(= [meepTellTree 2] @meepTell1)
		(= [meepTellTree 3] ENDTELL)
		(self
			addObstacle:
				((Polygon new:)
					type: PBarredAccess
					init:
						319 94
						226 94
						193 83
						115 83
						68 95
						43 104
						43 136
						85 163
						300 163
						300 130
						319 130
						319 189
						0 189
						0 0
						319 0
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init:
						139 88
						169 88
						169 99
						139 99
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init:
						95 106
						132 106
						132 114
						95 114
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init:
						55 120
						89 120
						89 133
						56 133
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init:
						128 116
						164 116
						164 127
						128 127
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init:
						193 118
						227 118
						227 126
						193 126
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init:
						236 99
						272 99
						272 109
						236 109
					yourself:
				)
		)
		(super init:)
		(= local29 2)
		(NormalEgo)
		(ego init: posn: 318 120 setMotion: MoveTo 306 120)
		(bossRock
			init:
			approachVerbs:
				V_DO V_ACORN V_CANDELABRA V_CANDLESTICKS V_CHEETAURCLAW V_DAGGER
				V_FAIRYDUST V_FLASK V_FLOWERS V_WATER V_FRUIT V_GHOSTOIL
				V_GREENFUR V_HEALING V_BRASSKEY V_LEATHER V_LOCKPICK V_MAGICGEM
				V_MANA V_MANDRAKE V_MAGICMIRROR V_MUSHROOM V_MUSICBOX V_PEARLS
				V_PAPER V_RATIONS V_RING V_ROCK V_SEED V_SHIELD
				V_MONEY V_VIGOR V_SWORD V_THIEFKIT V_THIEFLICENSE V_TROLLBEARD
				V_VASE V_VEGETABLES
		)
		(rock1
			init:
			approachVerbs:
 				V_DO V_ACORN V_CANDELABRA V_CANDLESTICKS V_CHEETAURCLAW V_DAGGER
				V_FAIRYDUST V_FLASK V_FLOWERS V_WATER V_FRUIT V_GHOSTOIL
				V_GREENFUR V_HEALING V_BRASSKEY V_LEATHER V_LOCKPICK V_MAGICGEM
				V_MANA V_MANDRAKE V_MAGICMIRROR V_MUSHROOM V_MUSICBOX V_PEARLS
				V_PAPER V_RATIONS V_RING V_ROCK V_SEED V_SHIELD
				V_MONEY V_VIGOR V_SWORD V_THIEFKIT V_THIEFLICENSE V_TROLLBEARD
				V_VASE V_VEGETABLES
		)
		(rock2
			init:
			approachVerbs:
				V_DO V_ACORN V_CANDELABRA V_CANDLESTICKS V_CHEETAURCLAW V_DAGGER
				V_FAIRYDUST V_FLASK V_FLOWERS V_WATER V_FRUIT V_GHOSTOIL
				V_GREENFUR V_HEALING V_BRASSKEY V_LEATHER V_LOCKPICK V_MAGICGEM
				V_MANA V_MANDRAKE V_MAGICMIRROR V_MUSHROOM V_MUSICBOX V_PEARLS
				V_PAPER V_RATIONS V_RING V_ROCK V_SEED V_SHIELD
				V_MONEY V_VIGOR V_SWORD V_THIEFKIT V_THIEFLICENSE V_TROLLBEARD
				V_VASE V_VEGETABLES
		)
		(rock3
			init:
			approachVerbs:
				V_DO V_ACORN V_CANDELABRA V_CANDLESTICKS V_CHEETAURCLAW V_DAGGER
				V_FAIRYDUST V_FLASK V_FLOWERS V_WATER V_FRUIT V_GHOSTOIL
				V_GREENFUR V_HEALING V_BRASSKEY V_LEATHER V_LOCKPICK V_MAGICGEM
				V_MANA V_MANDRAKE V_MAGICMIRROR V_MUSHROOM V_MUSICBOX V_PEARLS
				V_PAPER V_RATIONS V_RING V_ROCK V_SEED V_SHIELD
				V_MONEY V_VIGOR V_SWORD V_THIEFKIT V_THIEFLICENSE V_TROLLBEARD
				V_VASE V_VEGETABLES
		)
		(rock4
			init:
			approachVerbs:
				V_DO V_ACORN V_CANDELABRA V_CANDLESTICKS V_CHEETAURCLAW V_DAGGER
				V_FAIRYDUST V_FLASK V_FLOWERS V_WATER V_FRUIT V_GHOSTOIL
				V_GREENFUR V_HEALING V_BRASSKEY V_LEATHER V_LOCKPICK V_MAGICGEM
				V_MANA V_MANDRAKE V_MAGICMIRROR V_MUSHROOM V_MUSICBOX V_PEARLS
				V_PAPER V_RATIONS V_RING V_ROCK V_SEED V_SHIELD
				V_MONEY V_VIGOR V_SWORD V_THIEFKIT V_THIEFLICENSE V_TROLLBEARD
				V_VASE V_VEGETABLES
		)
		(rock5
			init:
			approachVerbs:
				V_DO V_ACORN V_CANDELABRA V_CANDLESTICKS V_CHEETAURCLAW V_DAGGER
				V_FAIRYDUST V_FLASK V_FLOWERS V_WATER V_FRUIT V_GHOSTOIL
				V_GREENFUR V_HEALING V_BRASSKEY V_LEATHER V_LOCKPICK V_MAGICGEM
				V_MANA V_MANDRAKE V_MAGICMIRROR V_MUSHROOM V_MUSICBOX V_PEARLS
				V_PAPER V_RATIONS V_RING V_ROCK V_SEED V_SHIELD
				V_MONEY V_VIGOR V_SWORD V_THIEFKIT V_THIEFLICENSE V_TROLLBEARD
				V_VASE V_VEGETABLES
		)
		(bossEyes init: setLoop: 3 z: 1000)
		(meepEyes2 init: setLoop: 3 z: 1000)
		(meepEyes3 init: setLoop: 3 z: 1000)
		(meepTeller init: bossMeep @meepTellMainBranch @meepTellTree @meepTellKeys)
		(bossMeep
			init:
			actions: meepTeller
			z: 1000
			approachVerbs:
				V_DO V_ACORN V_CANDELABRA V_CANDLESTICKS V_CHEETAURCLAW V_DAGGER
				V_FAIRYDUST V_FLASK V_FLOWERS V_WATER V_FRUIT V_GHOSTOIL
				V_GREENFUR V_HEALING V_BRASSKEY V_LEATHER V_LOCKPICK V_MAGICGEM
				V_MANA V_MANDRAKE V_MAGICMIRROR V_MUSHROOM V_MUSICBOX V_PEARLS
				V_PAPER V_RATIONS V_RING V_ROCK V_SEED V_SHIELD
				V_MONEY V_VIGOR V_SWORD V_THIEFKIT V_THIEFLICENSE V_TROLLBEARD
				V_VASE V_VEGETABLES
			setScript: meepScript1
		)
		(meep2
			init:
			z: 1000
			approachVerbs:
				V_DO V_ACORN V_CANDELABRA V_CANDLESTICKS V_CHEETAURCLAW V_DAGGER
				V_FAIRYDUST V_FLASK V_FLOWERS V_WATER V_FRUIT V_GHOSTOIL
				V_GREENFUR V_HEALING V_BRASSKEY V_LEATHER V_LOCKPICK V_MAGICGEM
				V_MANA V_MANDRAKE V_MAGICMIRROR V_MUSHROOM V_MUSICBOX V_PEARLS
				V_PAPER V_RATIONS V_RING V_ROCK V_SEED V_SHIELD
				V_MONEY V_VIGOR V_SWORD V_THIEFKIT V_THIEFLICENSE V_TROLLBEARD
				V_VASE V_VEGETABLES
			setScript: meepScript2
		)
		(meep3
			init:
			z: 1000
			approachVerbs:
				V_DO V_ACORN V_CANDELABRA V_CANDLESTICKS V_CHEETAURCLAW V_DAGGER
				V_FAIRYDUST V_FLASK V_FLOWERS V_WATER V_FRUIT V_GHOSTOIL
				V_GREENFUR V_HEALING V_BRASSKEY V_LEATHER V_LOCKPICK V_MAGICGEM
				V_MANA V_MANDRAKE V_MAGICMIRROR V_MUSHROOM V_MUSICBOX V_PEARLS
				V_PAPER V_RATIONS V_RING V_ROCK V_SEED V_SHIELD
				V_MONEY V_VIGOR V_SWORD V_THIEFKIT V_THIEFLICENSE V_TROLLBEARD
				V_VASE V_VEGETABLES
			setScript: meepScript3
		)
		(upperRocks
			init:
;;;			setOnMeCheck: ftrControl cBLUE
		)
		(trees
			init:
;;;			setOnMeCheck: ftrControl cGREEN
		)
		(grass init:)
		(lowerRocks
			init:
;;;			setOnMeCheck: ftrControl cBLUE
		)
		(Load RES_SCRIPT JUMP)
		(Load RES_SCRIPT 160)
		(Lock RES_VIEW 60 1)
	)
	
	(method (doit)
		(super doit:)
		(cond 
			(script)
			((== (ego edgeHit?) EAST)
				(HandsOff)
				(curRoom setScript: sExitEast)
			)
		)
		(if (!= local32 (GetTime SYSTIME1))
			(= local32 (GetTime SYSTIME1))
			(if
				(and
					(== bossOnScreen 2)
					(not local33)
					(or
						(ego mover?)
						(not (-- local31))
						(== (ego script?) getTheFur)
					)
				)
				(bossMeep setScript: sByeBoss)
			)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DAGGER
				(messager say: N_ROOM NULL C_NONEEDTO)
				(= meepsScared TRUE)
			)
			(V_TALK
				(if meepsScared
					(messager say: N_ROOM V_TALK C_SCAREDMEEPS)
				)
			)
			(V_LOOK
				(cond 
					(meepsScared
						(messager say: N_ROOM V_LOOK C_SCAREDMEEPS)
					)
					((not (Random 0 3))
						(messager say: N_ROOM V_LOOK C_SEEROOM)
					)
					(else
						(messager say: N_ROOM V_LOOK C_SEEMEEPS)
					)
				)
			)
			(V_ROCK
				(= meepsScared TRUE)
				(ThrowRock 0)
			)
			(V_DETECT
				(messager say: N_ROOM V_DETECT)
			)
			(V_OPEN
				(messager say: N_ROOM V_OPEN)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
	
	(method (newRoom n)
		(DisposeScript 160)
		(Lock RES_VIEW 60 0)
		(super newRoom: n)
	)
)

(instance upperRocks of Feature
	(properties
		x 150
		y 43
		noun N_ROCKS
		nsBottom 86
		nsRight 301
		sightAngle 40
		onMeCheck cBLUE
	)
)

(instance lowerRocks of Feature
	(properties
		x 159
		y 170
		noun N_ROCKS
		nsTop 140
		nsBottom 189
		nsRight 319
		sightAngle 40
		onMeCheck cBLUE
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DAGGER
				(messager say: N_ROOM NULL C_NONEEDTO)
				(= meepsScared TRUE)
			)
			(V_LOOK
				(messager say: N_ROCKS V_LOOK)
			)
			(V_DO
				(= local31 3)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance grass of Feature
	(properties
		x 168
		y 68
		noun N_GRASS
		nsTop 68
		nsLeft 22
		nsBottom 163
		nsRight 315
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DAGGER
				(messager say: N_ROOM 0 C_NONEEDTO)
				(= meepsScared TRUE)
			)
			(V_LOOK
				(ego setScript: lookAtGrass)
			)
			(V_DO
				(cond 
					((== local37 1)
						(= local37 0)
						(= local38 1)
						(ego setScript: getTheFur)
					)
					((== local35 1)
						(= local35 0)
						(ego setScript: sGetScroll)
					)
					(else
						(super doVerb: theVerb &rest)
					)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance trees of Feature
	(properties
		x 159
		y 94
		noun N_TREES
		nsBottom 189
		nsRight 319
		sightAngle 40
		onMeCheck cGREEN
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DAGGER
				(messager say: N_ROOM NULL C_NONEEDTO)
				(= meepsScared TRUE)
			)
			(V_LOOK
				(messager say: N_TREES V_LOOK)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance fur of View
	(properties
		x 173
		y 105
		noun N_FUR
		view 61
		loop 8
		priority 1
		signal (| ignrAct ignrHrz fixedLoop)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DAGGER
				(messager say: N_ROOM NULL C_NONEEDTO)
				(= meepsScared TRUE)
			)
			(V_LOOK
				(messager say: N_FUR V_LOOK)
			)
			(V_DO
				(ego setScript: getTheFur)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance rock1 of Prop
	(properties
		x 74
		y 231
		z 100
		noun N_ROCKS
		approachX 71
		approachY 117
		view 60
		loop 6
		priority 9
		signal (| ignrAct fixedLoop fixPriOn)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DAGGER
				(messager say: N_ROOM NULL C_NONEEDTO)
				(= meepsScared TRUE)
			)
			(V_TALK
				(if meepsScared
					(messager say: N_ROOM V_TALK C_SCAREDMEEPS)
				else
					(super doVerb: theVerb &rest)
				)
			)
			(V_LOOK
				(if meepsScared
					(messager say: N_ROOM V_LOOK C_SCAREDMEEPS)
				else
					(messager say: N_ROCKS V_LOOK)
				)
			)
			(V_DO
				(HandsOff)
				(ego posn: 71 125)
				(= local29 2)
				(self setScript: sLiftRock)
			)
			(V_SWORD
				(if meepsScared
					(messager say: N_ROCKS V_SWORD)
				else
					(self setScript: sStabRock)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance rock2 of Prop
	(properties
		x 115
		y 214
		z 100
		noun N_ROCKS
		approachX 114
		approachY 100
		view 60
		loop 7
		priority 7
		signal (| ignrAct fixedLoop fixPriOn)
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_DO)
			(HandsOff)
			(ego posn: 113 108)
			(= local29 3)
			(self setScript: sLiftRock)
		else
			(rock1 doVerb: theVerb &rest)
		)
	)
)

(instance rock3 of Prop
	(properties
		x 147
		y 226
		z 100
		noun N_ROCKS
		approachX 145
		approachY 113
		view 60
		loop 7
		priority 8
		signal (| ignrAct fixedLoop fixPriOn)
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_DO)
			(HandsOff)
			(ego posn: 145 120)
			(= local29 3)
			(self setScript: sLiftRock)
		else
			(rock1 doVerb: theVerb &rest)
		)
	)
)

(instance rock4 of Prop
	(properties
		x 211
		y 226
		z 100
		noun N_ROCKS
		approachX 208
		approachY 113
		view 60
		loop 6
		priority 8
		signal (| ignrAct fixedLoop fixPriOn)
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_DO)
			(HandsOff)
			(ego posn: 208 120)
			(= local29 2)
			(self setScript: sLiftRock)
		else
			(rock1 doVerb: theVerb &rest)
		)
	)
)

(instance rock5 of Prop
	(properties
		x 256
		y 207
		z 100
		noun N_ROCKS
		approachX 254
		approachY 94
		view 60
		loop 7
		priority 7
		signal (| ignrAct fixedLoop fixPriOn)
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_DO)
			(HandsOff)
			(ego posn: 254 101)
			(= local29 3)
			(self setScript: sLiftRock)
		else
			(rock1 doVerb: theVerb &rest)
		)
	)
)

(instance bossEyes of Prop
	(properties
		x 155
		y 182
		z 100
		noun N_BOSSEYES
		view 60
		priority 6
		signal (| ignrAct fixedLoop fixPriOn)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(messager say: N_BOSSEYES V_LOOK)
			)
			(else 
				(meep3 doVerb: theVerb &rest)
			)
		)
	)
)

(instance meepEyes2 of Prop
	(properties
		x 73
		y 216
		z 100
		noun N_MEEP
		view 60
	)
	
	(method (doVerb theVerb)
		(bossEyes doVerb: theVerb &rest)
	)
)

(instance meepEyes3 of Prop
	(properties
		x 73
		y 216
		z 100
		view 60
	)
	
	(method (doVerb theVerb)
		(bossEyes doVerb: theVerb &rest)
	)
)

(instance meep2 of Prop
	(properties
		x 73
		y 231
		z 100
		noun N_MEEP
		view 60
	)
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(V_TALK
					(if bossOnScreen
						(messager say: N_MEEP V_TALK C_ASKBOSS)
						(return TRUE)
					else
						(curRoom setScript: sBossOut)
					)
				)
				(V_LOOK
					(messager say: N_MEEP V_LOOK NULL 2)
				)
				(V_DO
					(cond 
						((== (ego x?) (rock1 approachX?))
							(rock1 doVerb: theVerb &rest)
						)
						((== (ego x?) (rock5 approachX?))
							(rock5 doVerb: theVerb &rest)
						)
						(else
							(messager say: N_MEEP V_DO)
						)
					)
				)
				(V_DAGGER
					(messager say: N_ROOM NULL C_NONEEDTO)
					(= meepsScared TRUE)
				)
				(else 
					(super doVerb: theVerb &rest)
				)
			)
		)
	)
)

(instance meep3 of Prop
	(properties
		x 73
		y 231
		z 100
		noun N_MEEP
		view 60
	)
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(V_DAGGER
					(messager say: N_ROOM NULL C_NONEEDTO)
					(= meepsScared TRUE)
				)
				(V_TALK
					(if bossOnScreen
						(messager say: N_MEEP V_TALK C_ASKBOSS)
						(return TRUE)
					else
						(curRoom setScript: sBossOut)
					)
				)
				(V_LOOK
					(messager say: N_MEEP V_LOOK NULL 2)
				)
				(V_DO
					(cond 
						((== (ego x?) (rock2 approachX?))
							(rock2 doVerb: theVerb &rest)
						)
						((== (ego x?) (rock3 approachX?))
							(rock3 doVerb: theVerb &rest)
						)
						((== (ego x?) (rock4 approachX?))
							(rock4 doVerb: theVerb &rest)
						)
						(else
							(messager say: N_MEEP V_DO)
						)
					)
				)
				(else 
					(super doVerb: theVerb &rest)
				)
			)
		)
	)
)

(instance scroll of Actor
	(properties
		noun N_SCROLL
		view 61
		loop 5
		signal (| ignrAct ignrHrz fixedLoop)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DAGGER
				(messager say: N_ROOM NULL C_NONEEDTO)
				(= meepsScared TRUE)
			)
			(V_LOOK
				(messager say: N_SCROLL V_LOOK)
			)
			(V_DO
				(ego setScript: sGetScroll)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
	
	(method (cue)
		(super cue:)
		(self setCycle: 0 setCel: 0 stopUpd:)
	)
)

(instance apple2 of Actor
	(properties
		y 160
		noun N_MEEP
		view 61
		loop 6
		priority 1
		signal (| ignrAct fixedLoop fixPriOn)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(messager say: N_MEEP V_CONVERSATION C_APPLES)
			)
			(V_DAGGER
				(messager say: N_ROOM NULL C_NONEEDTO)
				(= meepsScared TRUE)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
	
	(method (cue)
		(super cue:)
		(self setCycle: 0 ignoreActors: TRUE addToPic:)
	)
)

(instance baby of Actor
	(properties
		y 100
		noun N_BABY
		view 61
		loop 7
		signal (| ignrAct ignrHrz fixedLoop)
	)
)

(instance bossRock of Actor
	(properties
		x 155
		y 198
		z 100
		approachX 155
		approachY 87
		view 60
		loop 5
		priority 5
		signal (| ignrAct fixedLoop fixPriOn)
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_DO)
			(if bossOnScreen
				(ego setMotion: 0)
				(bossMeep setScript: sByeBoss)
			else
				(HandsOff)
				(= local29 0)
				(ego posn: 154 92)
				(self setScript: sLiftRock)
			)
		else
			(rock1 doVerb: theVerb &rest)
		)
	)
)

(instance sStabRock of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= meepsScared TRUE)
				(ego
					setMotion: PolyPath
						(+ (client approachX?) 25)
						(+ (client approachY?) 10)
						self
				)
			)
			(1
				(ego setHeading: 270 self)
			)
			(2
				(ego view: 518 setCel: 0 setLoop: 2 setCycle: EndLoop self)
			)
			(3
				(ego setLoop: 3 setCycle: Forward)
				(= seconds 4)
			)
			(4
				(ego setLoop: 2 setCel: 6 setCycle: BegLoop self)
			)
			(5
				(NormalEgo)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance sLiftRock of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setHeading: 180)
				(= ticks 20)
			)
			(1
				(if (& (client signal?) actorHidden)
					(-- state)
				)
				(= ticks 20)
			)
			(2
				(= register (ego cycleSpeed?))
				(ego
					view: 62
					setLoop: 1
					setCel: 0
					cycleSpeed: 10
					setCycle: EndLoop self
				)
			)
			(3
				(client hide:)
				(ego setLoop: local29 setCel: 0 setCycle: EndLoop self)
			)
			(4
				(ego setLoop: 1 setCel: 1 setCycle: BegLoop self)
				(client show:)
			)
			(5
				(NormalEgo)
				(ego
					posn: (client approachX?) (client approachY?)
					setLoop: 2
					cycleSpeed: register
				)
				(= cycles 2)
			)
			(6
				(HandsOn)
				(ego setLoop: -1)
				(= local29 2)
				(self dispose:)
			)
		)
	)
)

(instance sThrowStuff of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= ticks 300)
			)
			(1
				(self cue:)
			;	(bossMeep setCycle: EndLoop self)
			)
			;changed to allow for the Green Meep to give the Detect scroll.
			;Now to fix the graphical glitch that was created...
			(2
				(bossMeep setPri: pMAGENTA setCycle: BegLoop self)
			)
			(3
				(= ticks 25)
			)
			(4
				(if (not (-- register))
					(= cycles 2)
				else
					(bossMeep setLoop: 0 setCel: 0)
					(cond 
						((and (not local35) (not local36))
							(self changeState: 40)
						)
						((and (not local40) (not (Random 0 1)))
							(self changeState: 20)
						)
						(else
							(self changeState: 10)
						)
					)
				)
			)
			(5
				(bossMeep setLoop: 2 setCel: 1 setCycle: CycleTo 4 1 self)
			)
			(6
				(bossMeep setPri: 6 setCycle: EndLoop self)
			)
			(7
				(HandsOn)
				(= local33 0)
				(self dispose:)
			)
			(10
				(bossMeep setCel: 1)
				(= local34 1)
				((= newApple (Apple new:))
					init:
					setLoop: 6
					ignoreActors: TRUE
					setCel: 0
					posn: 159 96
					setPri: 1
				)
				(= cycles 5)
			)
			(11
				(bossMeep setCel: 2)
				(newApple setCel: 1 posn: 165 87 setCycle: Forward)
				(= cycles 2)
			)
			(12
				(newApple
					setCycle: EndLoop
					setMotion: JumpTo (Random 148 230) (Random 110 114) self
				)
				(bossMeep setCel: 1)
			)
			(13
				(if (> (newApple x?) 205)
					(newApple
						setMotion: JumpTo (Random 230 292) (Random 138 142) self
					)
				else
					(newApple
						setMotion: JumpTo (Random 127 216) (Random 139 157) self
					)
				)
			)
			(14
				(newApple setCycle: 0 setMotion: 0)
				(self changeState: 4)
			)
			(20
				(= local40 1)
				(bossMeep setCel: 1)
				(baby init: setPri: 8 posn: 166 88)
				(= ticks 4)
			)
			(21
				(bossMeep setCel: 2)
				(baby setStep: 8 8 setMotion: MoveTo 164 57 self)
			)
			(22
				(baby setPri: -1 setMotion: MoveTo 174 119 self)
			)
			(23
				(baby setCycle: Forward setScript: sBaby)
				(bossMeep setLoop: 2 setCel: 1 setCycle: CycleTo 4 1 self)
			)
			(24
				(bossMeep setPri: 6 setCycle: EndLoop self)
			)
			(25
				(HandsOn)
				(= local33 0)
				(self dispose:)
			)
			(30
				(= local34 1)
				(bossMeep setCel: 1)
				(apple2 init: posn: 159 96 setPri: 1 setCel: 0)
				(= cycles 5)
			)
			(31
				(bossMeep setCel: 2)
				(apple2
					posn: 165 87
					setCycle: Forward
					setStep: 6 6
					moveSpeed: 0
					setMotion: MoveTo 169 24 self
				)
			)
			(32
				(apple2 setMotion: MoveTo 166 117 self)
			)
			(33
				(apple2
					setMotion: JumpTo (Random 171 233) (Random 106 119) apple2
				)
			)
			(34
				(self changeState: 5)
			)
			(40
				(bossMeep setCel: 1)
				(scroll init: posn: 161 85 setPri: 1 setCel: 1)
				(= local35 1)
				(= cycles 5)
			)
			(41
				(bossMeep setCel: 2)
				(scroll
					posn: 170 81
					setCycle: Forward
					setStep: 6 6
					moveSpeed: 0
					setMotion: JumpTo 247 139 scroll
				)
				(= ticks 20)
			)
			(42
				(scroll setCycle: 0)
				(bossMeep setLoop: 2 setCel: 1 setCycle: CycleTo 4 1 self)
			)
			(43
				(bossMeep setPri: 6 setCycle: EndLoop self)
			)
			(44
				(HandsOn)
				(= local33 0)
				(self dispose:)
			)
		)
	)
)

(instance babySqueak of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= cycles (Random 3 8))
				(meepSound loop: 1 number: 74 play:)
			)
			(1
				(self changeState: 0)
			)
		)
	)
)

(instance sBaby of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(self setScript: babySqueak)
				(= seconds (Random 3 5))
			)
			(1
				(baby
					setStep: 2 2
					setCycle: Forward
					setMotion: PolyPath 129 87 self
				)
			)
			(2
				(= ticks 10)
			)
			(3
				(baby setMotion: PolyPath 158 89 self)
			)
			(4
				(baby dispose:)
				(self dispose:)
			)
		)
	)
)

(instance sExitEast of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: MoveTo (+ (ego x?) 20) (ego y?) self)
			)
			(1
				(DisposeScript JUMP)
				(curRoom newRoom: 61)
			)
		)
	)
)

(instance sPullFur of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= local33 1)
				(= ticks 320)
			)
			(1
				(bossMeep
					setLoop: 4
					setCel: 0
					cycleSpeed: 10
					setCycle: CycleTo 8 1 self
				)
			)
			(2
				(fur init:)
				(bossMeep setCycle: EndLoop self)
			)
			(3
				(bossMeep setLoop: 2 setCel: 255)
				(HandsOn)
				(meepTeller
					stuffArray: (WordAt (meepTeller arrays?) 0) 0
				)
				(= local37 1)
				(= local33 0)
				(self dispose:)
			)
		)
	)
)

(instance getTheFur of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= seconds 3)
			)
			(1
				(ego setMotion: PolyPath 183 105 self)
			)
			(2
				(ego
					view: 510
					setLoop: 1
					setCel: 0
					setPri: (+ (fur priority?) 1)
					setCycle: EndLoop self
				)
			)
			(3
				(messager say: N_ROOM NULL C_GETFUR 1 self)
			)
			(4
				(SolvePuzzle f60GetFur 5)
				(ego get: iGreenFur)
				(fur dispose:)
				(self cue:)
			)
			(5
				(ego setPri: -1 setCycle: BegLoop self)
			)
			(6
				(NormalEgo)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance lookAtGrass of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(messager say: N_GRASS V_LOOK NULL 1 self)
			)
			(1
				(if (and (== local37 1) (not local38))
					(messager say: N_GRASS V_LOOK C_FURONGROUND 1 self)
				else
					(self cue:)
				)
			)
			(2
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance bossMeep of Actor
	(properties
		x 155
		y 201
		z 100
		noun N_MEEP
		approachX 155
		approachY 87
		view 60
		loop 2
		priority 5
		signal (| ignrAct fixedLoop fixPriOn)
	)
)

(instance meepSound of Sound
	(properties
		number 55
		priority 30
	)
)

(instance meepScript1 of Script

	(method (dispose)
		(= state 0)
		(super dispose: &rest)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if bossOnScreen
					(self dispose:)
				else
					(= ticks (Random 100 300))
				)
			)
			(1
				(cond 
					(bossOnScreen
						(self dispose:)
					)
					((or meepsScared (<= (ego distanceTo: bossRock) 130))
						(self init:)
					)
					(else
						(meepSound loop: 1 number: (+ (Random 0 2) 55) play:)
						(bossMeep
							z: 100
							setCel: 0
							cycleSpeed: (Random 5 10)
							setCycle: EndLoop self
						)
						(bossRock z: 1000)
					)
				)
			)
			(2
				(bossEyes setCycle: Forward z: 100)
				(if bossOnScreen
					(= cycles 2)
				else
					(= ticks (Random 20 40))
				)
			)
			(3
				(if bossOnScreen
					(= register 3)
				else
					(= register (Random 5 10))
				)
				(bossEyes z: 1000)
				(bossMeep cycleSpeed: register setCycle: BegLoop self)
			)
			(4
				(bossRock z: 100)
				(bossMeep z: 1000)
				(if bossOnScreen
					(= cycles 2)
				else
					(= ticks 20)
				)
			)
			(5
				(self init:)
			)
		)
	)
)

(instance meepScript3 of MeepPeeps
	(method (dispose)
		(= state 0)
		(super dispose: &rest)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= register (Random 1 3))
				(= cycles 2)
			)
			(1
				(if bossOnScreen
					(self dispose:)
				else
					(switch register
						(1
							(meep3
								setLoop: 1
								posn: 114 217
								setPri: (rock2 priority?)
							)
							(meepEyes3 posn: 114 198)
						)
						(2
							(meep3
								setLoop: 0
								posn: 210 226
								setPri: (rock4 priority?)
							)
							(meepEyes3 posn: 210 211)
						)
						(3
							(meep3
								setLoop: 1
								posn: 146 230
								setPri: (rock3 priority?)
							)
							(meepEyes3 posn: 146 211)
						)
					)
					(= ticks (Random 75 200))
				)
			)
			(2
				(switch register
					(1
						(if
							(or
								(<= (ego distanceTo: rock2) 130)
								(rock2 script?)
								meepsScared
							)
							(self init:)
						else
							(meep3
								approachX: (rock2 approachX?)
								approachY: (rock2 approachY?)
							)
							(rock2 hide:)
							(self cue:)
						)
					)
					(2
						(if
							(or
								(<= (ego distanceTo: rock4) 130)
								(rock4 script?)
								meepsScared
							)
							(self init:)
						else
							(meep3
								approachX: (rock4 approachX?)
								approachY: (rock4 approachY?)
							)
							(rock4 hide:)
							(self cue:)
						)
					)
					(3
						(if
							(or
								(<= (ego distanceTo: rock3) 130)
								(rock3 script?)
								meepsScared
							)
							(self init:)
						else
							(meep3
								approachX: (rock3 approachX?)
								approachY: (rock3 approachY?)
							)
							(rock3 hide:)
							(self cue:)
						)
					)
				)
			)
			(3
				(if bossOnScreen
					(= register2 3)
				else
					(= register2 (Random 5 10))
				)
				(meepSound loop: 1 number: (+ (Random 0 2) 55) play:)
				(meep3
					z: 100
					setCel: 0
					cycleSpeed: register2
					setCycle: EndLoop self
				)
			)
			(4
				(meepEyes3
					setCycle: Forward
					setPri: (+ (meep3 priority?) 1)
					z: 100
				)
				(if bossOnScreen
					(= cycles 2)
				else
					(= ticks (Random 20 40))
				)
			)
			(5
				(if bossOnScreen
					(= register2 3)
				else
					(= register2 (Random 5 10))
				)
				(meepEyes3 z: 1000)
				(meep3 cycleSpeed: register2 setCycle: BegLoop self)
			)
			(6
				(switch register
					(1 (rock2 show:))
					(2 (rock4 show:))
					(3 (rock3 show:))
				)
				(meep3 z: 1000)
				(self init:)
			)
		)
	)
)

(instance meepTeller of Teller
	(method (showDialog &tmp temp0)
		(if
			(==
				(= temp0
					(super
						showDialog:
							12 local35
							-3 local40
							-2 local34
							-9 (if (not (Btst fLearnedDetect)) (not local35))
							C_GREENFUR (not local37)
					)
				)
				-3
			)
			(= temp0 (Abs temp0))
		)
		(if
			(== temp0 -2)
			(= temp0 (Abs temp0))
		)
		(if
			(and
				(== temp0 7)
				(not local37)
				(not local38)
				(not (Btst fGotFur))
			)
			(Bset fGotFur)
			(bossMeep setScript: sPullFur)
		)
		(if (== temp0 -9)
			(bossMeep setScript: sThrowStuff)
			(= temp0 (Abs temp0))
		)
		(if (== temp0 -999)
			(HandsOff)
			(bossMeep setScript: sByeBoss)
		)
		(return temp0)
	)
	
	(method (doChild)
		(return
			(if (== query -6)
				(if local37 (return query) else (super doChild: query))
			else
				(super doChild: query)
			)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DAGGER
				(messager say: N_ROOM NULL C_NONEEDTO)
				(= meepsScared TRUE)
			)
			(V_TALK
				(if bossOnScreen
					(SolvePuzzle f60TalkToMeep 1)
					(super doVerb: theVerb)
				else
					(bossMeep approachVerbs: NULL)
					(curRoom setScript: sBossOut)
				)
			)
			(V_LOOK
				(if meepsScared
					(messager say: N_ROOM V_LOOK C_SCAREDMEEPS)
				else
					(messager say: N_MEEP V_LOOK)
				)
			)
			(V_DO
				(bossMeep setScript: sThrowStuff)
			)
			(else 
				(bossRock doVerb: theVerb &rest)
			)
		)
		(return TRUE)
	)
)

(instance sByeBoss of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= bossOnScreen FALSE)
				(if (not (== (ego script?) getTheFur))
					(ego setMotion: 0)
				)
				(bossMeep setCycle: CycleTo 4 -1 self)
			)
			(1
				(bossMeep setPri: 5 setCycle: CycleTo 1 -1 self)
			)
			(2
				(= ticks 10)
			)
			(3
				(bossRock z: 1000)
				(bossMeep
					setLoop: 9
					setCel: 0
					cycleSpeed: 8
					setCycle: EndLoop self
				)
			)
			(4
				(if (not (== (ego script?) getTheFur)) (HandsOn))
				(bossRock
					setLoop: 5
					posn: 155 198 100
					setCel: 0
					setPri: 5
					z: 100
				)
				(bossMeep
					view: 60
					setLoop: 2
					setPri: 5
					posn: 155 201 100
					z: 1000
					actions: 0
					approachVerbs:
						V_DO V_ACORN V_CANDELABRA V_CANDLESTICKS V_CHEETAURCLAW V_DAGGER
						V_FAIRYDUST V_FLASK V_FLOWERS V_WATER V_FRUIT V_GHOSTOIL
						V_GREENFUR V_HEALING V_BRASSKEY V_LEATHER V_LOCKPICK V_MAGICGEM
						V_MANA V_MANDRAKE V_MAGICMIRROR V_MUSHROOM V_MUSICBOX V_PEARLS
						V_PAPER V_RATIONS V_RING V_ROCK V_SEED V_SHIELD
						V_MONEY V_VIGOR V_SWORD V_THIEFKIT V_THIEFLICENSE V_TROLLBEARD
						V_VASE V_VEGETABLES
					setScript: meepScript1
				)
				(meep2 setScript: meepScript2)
				(meep3 setScript: meepScript3)
				(self dispose:)
			)
		)
	)
)

(instance sBossOut of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= bossOnScreen TRUE)
				(= cycles 2)
			)
			(1
				(if
					(and
						(== (meepScript3 state?) 0)
						(== (meepScript2 state?) 0)
						(== (meepScript1 state?) 0)
					)
					(= cycles 2)
				else
					(-- state)
					(= cycles 2)
				)
			)
			(2
				(Load RES_VIEW 1060)
				(meepSound number: 75 loop: -1 play:)
				(= seconds 2)
			)
			(3
				(meep2 stopUpd:)
				(meep3 stopUpd:)
				(messager say: N_ROOM NULL C_ASKBOSS 1 self)
			)
			(4
				(if (& (ego onControl: origin) cYELLOW)
					(messager say: N_ROOM NULL C_SLIPAWAY 1 self)
				else
					(= cycles 2)
				)
			)
			(5
				(if (& (ego onControl: origin) cYELLOW)
					(ego setMotion: PolyPath 306 120 self)
				else
					(= cycles 2)
				)
			)
			(6
				(ego setHeading: 270 self)
			)
			(7
				(= cycles 2)
			)
			(8
				(bossMeep
					view: 61
					actions: meepTeller
					setLoop: 0
					setCel: 1
					setPri: 5
					z: 100
				)
				(bossRock
					view: 60
					setLoop: 5
					setCel: 1
					posn: 162 183
					setPri: 6
					z: 100
				)
				(= cycles 2)
			)
			(9
				(bossMeep setCel: 2)
				(bossRock
					setCycle: Forward
					cycleSpeed: 5
					setStep: 8 8
					setMotion: MoveTo 172 140 self
				)
			)
			(10
				(= ticks 5)
			)
			(11
				(bossRock setMotion: MoveTo 183 189 self)
			)
			(12
				(meepSound loop: 1 number: 58 play:)
				(bossRock setCycle: 0 setCel: 0 posn: 181 200)
				(bossMeep setLoop: 2 setCel: 1 setCycle: CycleTo 4 1 self)
			)
			(13
				(bossMeep setPri: 6 setCycle: EndLoop self)
			)
			(14
				(if (Btst fMetMeepBoss)
					(messager say: N_ROOM NULL C_WELCOMEBACK 1 self)
				else
					(messager say: N_ROOM NULL C_FIRSTMEET 1 self)
					(Bset 305)
				)
			)
			(15
				(= local31 20)
				(= bossOnScreen 2)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance meepScript2 of MeepPeeps
	(method (dispose)
		(= state 0)
		(super dispose: &rest)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= register (Random 1 2))
				(= cycles 2)
			)
			(1
				(if bossOnScreen
					(self dispose:)
				else
					(switch register
						(1
							(meep2 setLoop: 0 posn: 73 231 setPri: (rock1 priority?))
							(meepEyes2 posn: 73 216)
						)
						(2
							(meep2
								setLoop: 1
								posn: 255 210
								setPri: (- (rock5 priority?) 1)
							)
							(meepEyes2 posn: 255 191)
						)
					)
					(= ticks (Random 75 200))
				)
			)
			(2
				(cond 
					((== register 2)
						(= cycles 2)
					)
					(
						(or
							(<= (ego distanceTo: rock1) 130)
							(rock1 script?)
							meepsScared
						)
						(self init:)
					)
					(else
						(++ state)
						(meep2
							approachX: (rock1 approachX?)
							approachY: (rock1 approachY?)
						)
						(if bossOnScreen
							(= register2 3)
						else
							(= register2 (Random 5 10))
						)
						(meepSound loop: 1 number: (+ (Random 0 2) 55) play:)
						(meep2
							z: 100
							setCel: 0
							cycleSpeed: register2
							setCycle: EndLoop self
						)
						(rock1 z: 1000)
					)
				)
			)
			(3
				(if
					(or
						(<= (ego distanceTo: rock5) 130)
						(rock5 script?)
						meepsScared
					)
					(self init:)
				else
					(meep2
						approachX: (rock5 approachX?)
						approachY: (rock5 approachY?)
					)
					(if bossOnScreen
						(= register2 3)
					else
						(= register2 (Random 5 10))
					)
					(meepSound loop: 1 number: (+ (Random 0 2) 55) play:)
					(meep2
						z: 100
						setCel: 0
						cycleSpeed: register2
						setCycle: EndLoop self
					)
					(rock5 z: 1000)
				)
			)
			(4
				(meepEyes2
					setCycle: Forward
					setPri: (+ (meep2 priority?) 1)
					z: 100
				)
				(if bossOnScreen
					(= cycles 2)
				else
					(= ticks (Random 20 40))
				)
			)
			(5
				(if bossOnScreen
					(= register2 3)
				else
					(= register2 (Random 5 10))
				)
				(meepEyes2 z: 1000)
				(meep2 cycleSpeed: register2 setCycle: BegLoop self)
			)
			(6
				(switch register
					(1 (rock1 z: 100))
					(2 (rock5 z: 100))
				)
				(meep2 z: 1000)
				(self init:)
			)
		)
	)
)

(instance sGetScroll of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= local36 1)
				(= local35 0)
				(ego
					setMotion: PolyPath (+ (scroll x?) 20) (scroll y?) self
				)
			)
			(1
				(ego view: 510 setLoop: 1 setCel: 0 setCycle: EndLoop self)
			)
			(2
				(scroll dispose:)
				(ego setCycle: BegLoop self)
			)
			(3
				(if (not [egoStats MAGIC])
					(messager say: N_ROOM NULL C_NOMAGIC 1)
					(NormalEgo)
					(HandsOn)
					(self dispose:)
				else
					(messager say: N_ROOM NULL C_LEARNDETECT 1 self)
				)
			)
			(4
				(ego learn: DETMAGIC 10)
				(SolvePuzzle f60LearnDetect 4 MAGIC_USER)	;Now only magic users can get this
				(Bset fLearnedDetect)
				(HandsOn)
				(NormalEgo)
				(self dispose:)
			)
		)
	)
)
