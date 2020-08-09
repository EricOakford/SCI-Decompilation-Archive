;;; Sierra Script 1.0 - (do not remove this comment)
(script# 260)
(include game.sh) (include "260.shm") (include "0.shm")
(use Main)
(use FPRoom)
(use Scaler)
(use Polygon)
(use Feature)
(use MoveFwd)
(use User)
(use Actor)
(use System)

(public
	rm260 0
)

(instance rm260 of FPRoom
	(properties
		noun N_ROOM
		picture 260
		style PLAIN
		horizon 73
		west 230
	)
	
	(method (init)
		(ego init: setScale: Scaler 100 50 175 95 normalize:)
		(theMusic1 number: 260 loop: -1 play:)
		(super init:)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: PBarredAccess
					init:
						0 0
						319 0
						319 104
						251 104
						200 99
						164 86
						141 73
						91 73
						113 93
						34 93
						23 118
						0 118
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init:
						0 181
						251 179
						301 141
						319 135
						319 189
						0 189
					yourself:
				)
		)
		(swing
			init:
			cel: 3
			setPri: 8
			stopUpd:
			ignoreActors:
			approachVerbs: V_DO
		)
		(seesaw
			init:
			setPri: 5
			stopUpd:
			ignoreActors:
			approachVerbs: V_DO V_BAKING_SODA
		)
		(if (and (not (ego has: iLadder)) (not (Btst fSavedSrini)))
			(ladder init: stopUpd: ignoreActors: approachVerbs: V_DO)
		)
		(schoolDoor init: approachVerbs: V_DO stopUpd:)
		(assayOffice init: approachVerbs: V_DO setOnMeCheck: ftrControl cLGREEN)
		(school init: approachVerbs: V_DO setOnMeCheck: ftrControl cMAGENTA)
		(buildings init: approachVerbs: V_DO setOnMeCheck: ftrControl cGREY)
		(street init: approachVerbs: V_DO setOnMeCheck: ftrControl cBLUE)
		(slide init: approachVerbs: V_DO setOnMeCheck: ftrControl cGREEN)
		(seesawBase init: approachVerbs: V_DO setOnMeCheck: ftrControl cCYAN)
		(tree init: approachVerbs: V_DO setOnMeCheck: ftrControl cRED)
		(windowPanes init: approachVerbs: V_DO setOnMeCheck: ftrControl cBROWN)
		(roof init: approachVerbs: V_DO setOnMeCheck: ftrControl cLGREY)
		(clutter init: approachVerbs: V_DO setOnMeCheck: ftrControl cLBLUE)
		(theGame handsOn:)
	)
	
	(method (doit)
		(if script
		else
			(switch ((User alterEgo?) edgeHit?)
				(NORTH
					(curRoom setScript: sNoNorth)
				)
				(SOUTH
					(ego setHeading: 90)
					(curRoom setScript: sNoEast)
				)
			)
		)
		(super doit:)
	)
	
	(method (dispose)
		(theMusic1 fade:)
		(super dispose:)
	)
)

(instance sNoNorth of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setHeading: 180)
				(= cycles 5)
			)
			(1
				(messager say: N_CANT_GO NULL  NULL 0 self 0)
			)
			(2
				(ego setMotion: MoveFwd 10 self)
			)
			(3
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sNoEast of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: MoveFwd 30 self)
			)
			(1
				(ego setHeading: 270)
				(= cycles 5)
			)
			(2
				(messager say: N_CANT_GO NULL  NULL 0 self 0)
			)
			(3
				(ego setMotion: MoveFwd 40 self)
			)
			(4
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance seesaw of Prop
	(properties
		x 252
		y 81
		noun N_SEESAW
		approachX 216
		approachY 108
		view 260
		loop 2
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO
				(if (OneOf global119 9 8 7)
					(messager say: noun theVerb 3)
				else
					(super doVerb: theVerb &rest)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance swing of Prop
	(properties
		x 300
		y 60
		z 40
		noun N_SWING
		approachX 271
		approachY 74
		view 260
		loop 4
		signal skipCheck
	)
)

(instance schoolDoor of View
	(properties
		x 210
		y 60
		noun N_SCHOOL_DOOR
		approachX 199
		approachY 70
		view 260
	)
)

(instance ladder of Prop
	(properties
		x 169
		y 48
		noun N_LADDER
		approachX 166
		approachY 75
		view 260
		loop 3
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO
				(messager say: noun theVerb NULL 0 self)
				(ego get: -1 iLadder)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
	
	(method (cue)
		(self dispose:)
		(theGame points: 1)
	)
)

(instance assayOffice of Feature
	(properties
		x 38
		y 44
		noun N_ASSAY_OFFICE
		sightAngle 40
		approachX 84
		approachY 98
		approachDist 76
	)
	
	(method (doVerb theVerb &tmp temp0)
		(if (OneOf theVerb V_LOOK V_DO)
			(messager say: noun theVerb C_OFFICE_CLOSED)
		else
			(super doVerb: theVerb &rest)
		)
	)
)

(instance school of Feature
	(properties
		x 247
		y 28
		noun N_SCHOOL
		sightAngle 40
		approachX 200
		approachY 84
		approachDist 80
	)
)

(instance buildings of Feature
	(properties
		x 130
		y 21
		noun N_BUILDINGS
		sightAngle 40
		approachX 112
		approachY 61
		approachDist 49
	)
)

(instance street of Feature
	(properties
		x 130
		y 21
		noun N_STREET
		sightAngle 40
		approachX 112
		approachY 61
		approachDist 49
	)
)

(instance slide of Feature
	(properties
		x 130
		y 21
		noun N_SLIDE
		sightAngle 40
		approachX 112
		approachY 61
		approachDist 49
	)
)

(instance seesawBase of Feature
	(properties
		x 130
		y 21
		noun N_SEESAW
		sightAngle 40
		approachX 216
		approachY 108
		approachDist 49
	)
)

(instance tree of Feature
	(properties
		x 130
		y 21
		noun N_TREE
		sightAngle 40
		approachX 216
		approachY 108
		approachDist 49
	)
)

(instance windowPanes of Feature
	(properties
		x 130
		y 21
		noun N_WINDOW
		sightAngle 40
		approachX 200
		approachY 84
		approachDist 49
	)
)

(instance roof of Feature
	(properties
		x 130
		y 21
		noun N_ROOF
		sightAngle 40
		approachX 200
		approachY 84
		approachDist 49
	)
)

(instance clutter of Feature
	(properties
		x 130
		y 21
		noun N_CLUTTER
		sightAngle 40
		approachX 160
		approachY 138
		approachDist 49
	)
)

(instance schoolDoorSound of FPSound
	(properties
		flags mNOPAUSE
	)
)
