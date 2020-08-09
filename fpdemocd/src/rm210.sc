;;; Sierra Script 1.0 - (do not remove this comment)
(script# 210)
(include game.sh) (include "210.shm")
(use Main)
(use OccasionalCycle)
(use FPRoom)
(use Scaler)
(use Polygon)
(use Feature)
(use LoadMany)
(use Motion)
(use Actor)
(use System)

(public
	rm210 0
)

(instance rm210 of FPRoom
	(properties
		noun N_ROOM
		picture 210
		east 220
		west 200
	)
	
	(method (init)
		(LoadMany RES_VIEW 211)
		(switch prevRoomNum
			(west
				(= style SCROLLLEFT)
				(theGame handsOn:)
			)
			(east
				(= east 0)
				(= style SCROLLRIGHT)
				(theGame handsOn:)
			)
			(else 
				(= style PLAIN)
				(ego x: 281 y: 121)
				(theGame handsOn:)
			)
		)
		(ego init: setScale: Scaler 100 50 175 95 normalize:)
		(super init:)
		(theMusic2 number: 2210 loop: -1 play:)
		(animal1 setCycle: OccasionalCycle 1 30 120 2 6 init:)
		(animal2 setCycle: OccasionalCycle 1 30 120 2 6 init:)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: PBarredAccess
					init:
						0 81
						319 143
						319 189
						0 189
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init:
						0 0
						319 0
						319 110
						275 113
						0 67
					yourself:
				)
		)
		(tracks init: approachVerbs: V_DO setOnMeCheck: ftrControl cRED)
		(bridge init: approachVerbs: V_DO setOnMeCheck: ftrControl cGREEN)
		(rapids init: setOnMeCheck: ftrControl cBLUE)
		(corral init: setOnMeCheck: ftrControl cMAGENTA)
		(river setCycle: Forward init:)
		(cliff init: setOnMeCheck: ftrControl cCYAN)
	)
	
	(method (doit)
		(super doit:)
		(cond 
			(script)
			((== (ego edgeHit?) EAST)
				(curRoom setScript: sExitEast)
			)
		)
	)
	
	(method (newRoom)
		(theMusic2 fade:)
		(super newRoom: &rest)
	)
)

(instance sExitEast of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: MoveTo (+ (ego x?) 20) (ego y?) self)
			)
			(1
				(curRoom newRoom: 220)
			)
		)
	)
)

(instance animal1 of Prop
	(properties
		x 281
		y 101
		z 62
		noun N_ANIMAL1
		view 213
		detailLevel 2
	)
	
	(method (doit)
		(super doit:)
		(if (and (== cel 1) (== (heepSound prevSignal?) -1))
			(heepSound number: 2114 play:)
		)
	)
	
	(method (doVerb theVerb)
		(if (OneOf theVerb V_DO V_LOOK V_TALK)
			(messager say: noun theVerb C_HORSES)
		else
			(super doVerb: theVerb &rest)
		)
	)
)

(instance animal2 of Prop
	(properties
		x 300
		y 101
		z 44
		noun N_ANIMAL2
		view 213
		detailLevel 2
	)
	
	(method (doVerb theVerb)
		(if (OneOf theVerb V_DO V_LOOK V_TALK)
			(messager say: noun theVerb C_HORSES)
		else
			(super doVerb: theVerb &rest)
		)
	)
)

(instance river of Prop
	(properties
		x 132
		y 171
		noun N_RIVER
		view 211
		cel 2
		priority 1
		signal (| ignrAct fixPriOn)
		cycleSpeed 20
		detailLevel 2
	)
)

(instance tracks of Feature
	(properties
		x 212
		y 174
		noun N_TRACKS
		sightAngle 40
		approachX 212
		approachY 174
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO
				(messager say: N_TRACKS V_DO C_FEEL_TRACK)
			)
			(else
				(super doVerb: theVerb)
			)
		)
	)
)

(instance bridge of Feature
	(properties
		x 136
		y 89
		noun N_BRIDGE
		sightAngle 40
		approachX 136
		approachY 89
	)
)

(instance rapids of Feature
	(properties
		x 136
		y 160
		noun N_RIVER
		sightAngle 40
	)
)

(instance cliff of Feature
	(properties
		x 136
		y 160
		noun N_CLIFF
		sightAngle 40
	)
)

(instance corral of Feature
	(properties
		x 160
		y 100
		noun N_CORRAL
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(if (OneOf theVerb V_DO V_LOOK)
			(messager say: noun theVerb C_CORRAL)
		else
			(super doVerb: theVerb &rest)
		)
	)
)

(instance heepSound of FPSound
	(properties
		flags mNOPAUSE
		number 2114
	)
)
