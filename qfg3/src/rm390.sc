;;; Sierra Script 1.0 - (do not remove this comment)
(script# 390)
(include game.sh) (include "390.shm")
(use Main)
(use OccasionalCycle)
(use Scaler)
(use PolyPath)
(use Polygon)
(use Feature)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm390 0
)

(local
	poolResult
)

(enum ;pool results
	drinkWater
	fillWaterskin
	batheOrchid
)

(instance rm390 of Room
	(properties
		noun N_ROOM
		picture 390
		horizon 65
	)
	
	(method (init)
		(HandsOff)
		(theIconBar disable: ICON_CONTROL)
		(ego
			x: 190
			y: 250
			setScale: Scaler 100 65 128 50
			actions: egoActions
			init:
			normalize:
		)
		(super init:)
		(cSound number: 390 setLoop: -1 play: 127)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: PBarredAccess
					init:
						0 189
						0 155
						140 179
						143 189
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init:
						319 147
						290 149
						229 133
						148 138
						122 127
						81 115
						78 104
						58 92
						100 89
						214 86
						283 87
						277 91
						248 97
						286 107
						319 104
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init:
						319 62
						319 88
						306 86
						289 81
						280 72
						229 69
						232 62
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init:
						164 76
						148 81
						100 85
						64 83
						52 89
						0 99
						0 67
						162 67
					yourself:
				)
		)
		(soundFx number: 391 setLoop: -1 play: 127)
		(peacePool init:)
		(mountains init:)
		(trees init:)
		(rocks init:)
		(fountain setCycle: Forward init:)
		(leftRip setCycle: Forward init:)
		(rightRip setCycle: Forward init:)
		(theMoon init: setPri: 1)
		(if (not Night)
			(cheetahBody init: approachVerbs: V_DO addToPic:)
			(cheetahHead init: approachVerbs: V_DO)
			(cheetahTail
				setCycle: OccasionalCycle self 1 20 200
				init:
				approachVerbs: V_DO
			)
		)
		(ego setScript: egoEnters)
		(if (and (not Night) (not (Btst fSawImpala)))
			(curRoom setScript: impalaDrinks)
		)
	)
	
	(method (dispose)
		(soundFx stop:)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_FLAME
				(messager say: N_CUE V_DOIT C_NONVIOLENCE)
			)
			(V_LIGHTNING
				(messager say: N_CUE V_DOIT C_NONVIOLENCE)
			)
			(V_FORCEBOLT
				(messager say: N_CUE V_DOIT C_NONVIOLENCE)
			)
			(V_DAGGER
				(messager say: N_CUE V_DOIT C_NONVIOLENCE)
			)
			(V_SWORD
				(messager say: N_CUE V_DOIT C_NONVIOLENCE)
			)
			(V_FINE_DAGGER
				(messager say: N_CUE V_DOIT C_NONVIOLENCE)
			)
			(V_MAGIC_SPEAR
				(messager say: N_CUE V_DOIT C_NONVIOLENCE)
			)
			(V_DETECT
				(if (not (curRoom script?))
					(curRoom setScript: (ScriptID CASTAREA 0) self V_DETECT)
				else
					(messager say: N_CUE V_DOIT C_NOTNOW)
				)
			)
			(V_SLEEP
				(if (not (curRoom script?))
					(ego code: 0)
					(self setScript: goToBed)
				)
			)
			(else
				(super doVerb: theVerb)
			)
		)
	)
	
	(method (cue)
		(self setScript: runeAppears)
	)
)

(instance runeAppears of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(messager say: N_CUE V_DETECT NULL 0 self)
			)
			(1
				(rune init: setPri: 14 cycleSpeed: 4 setCycle: Forward)
				(HandsOn)
				(soundFx number: 391 setLoop: -1 play:)
				(self dispose:)
			)
		)
	)
)

(instance goToBed of Script

	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: PolyPath 179 168 self)
			)
			(1
				(ego view: 35 loop: 0 cel: 0 x: 198 setCycle: EndLoop self)
			)
			(2
				(if (= temp0 (PalVary PALVARYINFO))
					(if (< temp0 64)
						(PalVary PALVARYNEWTIME 3)
						(= seconds 5)
					else
						(self cue:)
					)
				else
					(PalVary PALVARYSTART 310 3)
					(Bset fEgoIsAsleep)
					(= seconds 5)
				)
			)
			(3
				(theMoon moveSpeed: 4 setMotion: MoveTo 335 15 self)
			)
			(4
				(PalVary PALVARYREVERSE 3)
				(Bclr fEgoIsAsleep)
				(= seconds 4)
			)
			(5
				((ScriptID TIME 7) init: 5 40)
				(ego setCycle: BegLoop self)
			)
			(6
				(ego
					normalize: 6
					cel: 6
					x: 179
					changeGait: MOVE_WALK
					code: outCheck
				)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance getWater of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: PolyPath 92 119 self)
			)
			(1
				(ego view: 4 loop: 0 cel: 0 setCycle: EndLoop)
				(= seconds 3)
			)
			(2
				(switch poolResult
					(drinkWater
						(messager say: N_PEACE_POOL V_DO)
						(= [egoStats STAMINA] (ego maxStamina:))
					)
					(fillWaterskin
						(messager say: N_PEACE_POOL V_WATERSKIN)
						(ego
							get: iPeaceWater
							drop: iWaterskin 1
							solvePuzzle: fGetWater 3
						)
					)
					(batheOrchid
						(if Night
							(if (not (Btst fOrchidGlows))
								(messager say: N_PEACE_POOL V_ORCHID C_ORCHID_NIGHT)
								((inventory at: iOrchid) state: 1 cel: 7)
								(Cursor cel: 7)
							)
							(ego solvePuzzle: fOrchidGlows 6 puzzleWIZARD)
						else
							(messager say: N_PEACE_POOL V_ORCHID C_ORCHID_DAY)
						)
					)
				)
				(ego setCycle: BegLoop self)
			)
			(3
				(ego normalize:)
				(soundFx number: 391 setLoop: -1 play:)
				(= poolResult NULL)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance egoEnters of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego x: 190 y: 250 init: setMotion: MoveTo 190 180 self)
			)
			(1
				(messager say: N_CUE V_DOIT C_ENTRANCE 0 self)
			)
			(2
				(if Night
					(messager say: N_CUE V_DOIT C_NIGHT 0 self)
				else
					(self cue:)
				)
			)
			(3
				(ego code: outCheck)
				(theIconBar enable: ICON_CONTROL)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance impalaDrinks of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Bset fSawImpala)
				(impala init:)
				(= seconds 6)
			)
			(1
				(impala loop: 1 setCycle: EndLoop)
				(= seconds 7)
			)
			(2
				(impala
					setLoop: 3
					setCycle: Forward
					setMotion: MoveTo 330 90 self
				)
			)
			(3
				(impala dispose:)
				(self dispose:)
			)
		)
	)
)

(instance theMoon of Actor
	(properties
		x -10
		y 15
		noun 10
		view 393
		signal (| ignrAct ignrHrz)
	)
)

(instance impala of Actor
	(properties
		x 255
		y 92
		noun N_IMPALA
		view 392
		signal ignrAct
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_LOOK)
			(super doVerb: theVerb)
		else
			(curRoom doVerb: theVerb)
		)
	)
)

(instance cheetahBody of View
	(properties
		x 116
		y 62
		noun N_CHEETAH
		approachX 111
		approachY 85
		view 391
		loop 1
		priority 4
		signal (| ignrAct fixPriOn notUpd stopUpdOn)
	)
)

(instance cheetahHead of Prop
	(properties
		x 114
		y 62
		noun N_CHEETAH
		approachX 111
		approachY 85
		view 391
		priority 4
		signal fixPriOn
	)
	
	(method (doit)
		(cond 
			((< (ego x?) 106)
				(self cel: 0)
			)
			((< (ego x?) 212)
				(self cel: 1)
			)
			(else
				(self cel: 2)
			)
		)
		(super doit: &rest)
	)
)

(instance cheetahTail of Prop
	(properties
		x 104
		y 58
		noun N_CHEETAH
		approachX 111
		approachY 85
		view 391
		loop 2
		priority 4
		signal fixPriOn
		detailLevel 3
	)
)

(instance fountain of Prop
	(properties
		x 163
		y 113
		noun N_PEACE_POOL
		view 390
		loop 2
		signal ignrAct
		detailLevel 3
	)
	
	(method (doVerb theVerb)
		(peacePool doVerb: theVerb)
	)
)

(instance rune of Prop
	(properties
		x 81
		y 49
		noun N_RUNE
		view 393
		loop 3
		signal ignrAct
	)
)

(instance leftRip of Prop
	(properties
		x 129
		y 106
		noun N_PEACE_POOL
		view 390
		signal ignrAct
		detailLevel 3
	)
	
	(method (doVerb theVerb)
		(peacePool doVerb: theVerb)
	)
)

(instance rightRip of Prop
	(properties
		x 193
		y 105
		noun N_PEACE_POOL
		view 390
		loop 1
		signal ignrAct
		detailLevel 3
	)
	
	(method (doVerb theVerb)
		(peacePool doVerb: theVerb)
	)
)

(instance mountains of Feature
	(properties
		x 164
		y 30
		noun N_MOUNTAINS
		nsTop 24
		nsLeft 126
		nsBottom 37
		nsRight 203
		sightAngle 180
	)
)

(instance rocks of Feature
	(properties
		x 84
		y 58
		noun N_ROCKS
		nsTop 41
		nsLeft 60
		nsBottom 76
		nsRight 109
		sightAngle 180
	)
)

(instance trees of Feature
	(properties
		x 32
		y 88
		noun N_TREES
		nsBottom 177
		nsRight 65
		sightAngle 180
	)
)

(instance peacePool of Feature
	(properties
		x 180
		y 18
		noun N_PEACE_POOL
		sightAngle 40
		onMeCheck cGREY
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_WATERSKIN
				(= poolResult fillWaterskin)
				(ego setScript: getWater)
			)
			(V_ORCHID
				(= poolResult batheOrchid)
				(ego setScript: getWater)
			)
			(V_DO
				(= poolResult drinkWater)
				(ego setScript: getWater)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance outCheck of Code

	(method (doit &tmp whatDay temp1 whatTime)
		(if (GameIsRestarting)
			(soundFx number: 391 setLoop: -1 play:)
		)
		(= whatDay Day)
		(if (or (!= timeODay TIME_MIDNIGHT) (> Clock 500))
			(++ whatDay)
		)
		(if
			(or
				(and (< (= whatTime Clock) 601) (> whatTime -1))
				(and (> whatTime 2999) (< whatTime 3601))
			)
			(if (and (< whatTime 601) (> whatTime -1))
				(theMoon x: (+ 180 (/ whatTime 5)))
			else
				(theMoon x: (+ 60 (/ (- whatTime 3000) 5)))
			)
		else
			(theMoon x: -50)
		)
		(if (ego edgeHit?)
			(curRoom newRoom: 160)
		)
	)
)

(instance egoActions of Actions

	(method (doVerb theVerb)
		(curRoom doVerb: theVerb)
	)
)
