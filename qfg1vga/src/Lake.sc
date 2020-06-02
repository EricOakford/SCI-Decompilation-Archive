;;; Sierra Script 1.0 - (do not remove this comment)
(script# 87)
(include game.sh) (include "87.shm")
(use Main)
(use Procs)
(use PolyPath)
(use Polygon)
(use Feature)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm87 0
)

(local
	theGName
	local1
	timesUsedWater
)
(instance rm87 of Room
	(properties
		noun N_ROOM
		picture 87
		style HSHUTTER
		horizon 50
		north 81
	)
	
	(method (init)
		(if (and (Btst fBeenIn87) (not (Btst fSawNessie)))
			(Load RES_SOUND (SoundFX 66))
		)
		(Load RES_VIEW 87)
		(= timesUsedWater 0)
		(self
			addObstacle:
				((Polygon new:)
					type: 2
					init:
						111
						76
						87
						103
						57
						103
						53
						112
						114
						112
						123
						91
						264
						93
						242
						89
						144
						85
						0
						31
						0
						0
						319
						0
						319
						189
						0
						189
						0
						35
					yourself:
				)
		)
		(super init:)
		(self
			setFeatures: water rocks trees deadTree formation falls
		)
		(SolvePuzzle POINTS_VISITLAKE 1)
		(ego init: setPri: 4 posn: 77 59 setScript: comeOnIn)
		(if (== egoGait MOVE_SNEAK)
			(messager say: N_ROOM 0 C_WALKONLY)
		)
		(if (Btst fBeenIn87)
			(switch (Random 1 2)
				(1
					(if (not (Btst fSawNessie))
						(periscope setLoop: 7 init: setScript: nessieScript)
						(Bset fSawNessie)
					)
				)
				(2
					(if (not (Btst fSawDelphineus))
						(flipper init: setScript: flippinOut)
					)
					(Bset fSawDelphineus)
				)
			)
		)
		(reflection
			ignoreActors: 1
			ignoreHorizon: 1
			setPri: 1
			posn: (ego x?) (ego y?)
			init:
		)
	)
	
	(method (doit)
		(if (> (Abs (- gameTime theGName)) 2)
			(= theGName gameTime)
			(Palette PALCycle 237 244 -1 245 252 -1)
		)
		(if
			(and
				(< (ego x?) 104)
				(< (ego y?) 70)
				(not (ego script?))
			)
			(ego edgeHit: 1)
			(curRoom newRoom: 81)
		)
		(super doit:)
	)
	
	(method (dispose)
		(Bset fBeenIn87)
		(super dispose:)
	)
)

(instance rocks of Feature
	(properties
		x 179
		y 65
		noun N_ROCKS
		onMeCheck $0004
	)
)

(instance formation of Feature
	(properties
		x 268
		noun N_FORMATION
		onMeCheck $0020
	)
)

(instance trees of Feature
	(properties
		x 40
		y 38
		noun N_TREES
		onMeCheck $0008
	)
)

(instance deadTree of Feature
	(properties
		x 287
		y 181
		noun N_DEADTREE
		onMeCheck $0010
	)
)

(instance water of Feature
	(properties
		x 268
		y 60
		noun N_WATER
		onMeCheck $0002
		approachX 262
		approachY 70
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO
				(switch (++ timesUsedWater)
					(1 (ego setScript: drinkWater))
					(2 (messager say: N_WATER V_DO 0))
				)
				(if (== timesUsedWater 2) (= timesUsedWater 0))
			)
			(V_FLASK (ego setScript: fillFlask))
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
					(messager say: N_WATER 0 0)
				else
					(super doVerb: theVerb &rest)
				)
			)
		)
	)
)

(instance falls of Feature
	(properties
		x 268
		y 60
		noun N_FALLS
		onMeCheck $4000
		approachX 262
		approachY 93
	)
)

(instance flipper of Prop ;although he's called Flipper in the script, the game refers to him as Delphineus.
	(properties
		x 291
		y 130
		noun N_DELPHINEUS
		view 87
		loop 5
	)
)

(instance reflection of Actor
	(properties
		noun N_REFLECTION
		view 87
	)
	
	(method (doit)
		(self
			loop:
				(cond 
					((== (ego view?) 510) 4)
					(
						(or
							(== (ego loop?) 1)
							(== (ego loop?) 5)
							(== (ego loop?) 7)
						)
						1
					)
					((== (ego loop?) 2) 2)
					((== (ego loop?) 3) 3)
					(else 0)
				)
			cel: (ego cel?)
			x: (ego x?)
			y: (ego y?)
			forceUpd:
		)
		(super doit:)
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_LOOK)
			(messager say: N_REFLECTION V_LOOK 0)
		else
			(water doVerb: theVerb &rest)
		)
	)
)

(instance periscope of Actor ;Actually Nessie; her internal name wasn't changed from the EGA original
	(properties
		x 376
		y 72
		noun N_NESSIE
		view 87
		loop 6
		illegalBits $0000
	)
)

(instance comeOnIn of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: MoveTo 123 76 self)
			)
			(1
				(ego setMotion: MoveTo 114 103 self)
			)
			(2
				(NormalEgo)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance drinkWater of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if (and (!= (ego x?) 262) (!= (ego y?) 92))
					(ego setMotion: PolyPath 262 93 self)
				else
					(self cue:)
				)
			)
			(1
				(ego view: 510 setCel: 0 setLoop: 0 setCycle: EndLoop self)
			)
			(2
				(messager say: N_ROOM 0 0 1)
				(if (Btst DISPEL_LEARNED_RECIPE) (messager say: N_ROOM 0 0 2)) ;messages now display properly
				(self cue:)
			)
			(3 (ego setCycle: BegLoop self))
			(4
				(ego view: 0)
				(NormalEgo)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance fillFlask of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if (and (!= (ego x?) 262) (!= (ego y?) 92))
					(ego setMotion: PolyPath 262 93 self)
				else
					(self cue:)
				)
			)
			(1
				(ego view: 510 setCel: 0 setLoop: 0 setCycle: EndLoop self)
			)
			(2
				(Bset fHaveLakeWater)
				(messager say: N_ROOM NULL NULL 3 self)
			)
			(3 (ego setCycle: BegLoop self))
			(4
				(ego view: 0)
				(NormalEgo)
				(ego get: iFlyingWater use: iFlask 1)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance nessieScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 5))
			(1
				(client
					setLoop: 6
					setCel: 0
					setMotion: MoveTo 319 72 self
				)
				(ShakeScreen 4)
			)
			(2 (= seconds 6))
			(3
				(client
					setLoop: 6
					setCel: 0
					setMotion: MoveTo 376 72 self
				)
				(ShakeScreen 2)
			)
			(4
				(client dispose:)
				(self dispose:)
			)
		)
	)
)

(instance flippinOut of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 5))
			(1
				(flipper cycleSpeed: 8 setCycle: CycleTo 1 1 self)
			)
			(2
				(flipper setCel: 1 setCycle: EndLoop self)
			)
			(3
				(flipper setCel: 0 setCycle: EndLoop self)
			)
			(4 (flipper dispose:))
			(5 (self dispose:))
		)
	)
)

(instance tromp of Sound
	(properties
		number 66
		priority 1
	)
)
