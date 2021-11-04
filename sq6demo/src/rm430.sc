;;; Sierra Script 1.0 - (do not remove this comment)
(script# 430)
(include game.sh) (include "430.shm")
(use Main)
(use SQRoom)
(use Scaler)
(use Polygon)
(use Feature)
(use Motion)
(use Actor)
(use System)

(public
	rm430 0
)

(instance rm430 of SQRoom
	(properties
		noun N_ROOM
		picture 430
	)
	
	(method (init)
		(theMusic number: 102 flags: 1 loop: -1 play:)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: PContainedAccess
					init:
						196 98
						127 99
						110 102
						89 104
						67 105
						55 105
						57 112
						68 110
						73 116
						65 119
						42 122
						6 126
						6 137
						316 137
						315 130
						295 128
						271 120
						247 108
						219 109
						218 101
						256 102
						255 99
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init:
						106 104
						165 107
						165 114
						114 114
						110 118
						83 117
						73 107
						87 106
					yourself:
				)
		)
		(super init:)
		(switch prevRoomNum
			(460
				(curRoom setScript: sEgoGoes)
			)
			(else 
				(curRoom setScript: sEgoComes)
			)
		)
		(comPost init: approachVerbs: V_DO)
		(replicatorFeature2 init: approachVerbs: V_DO)
		(windowR init: approachVerbs: V_DO)
		(windowL init: approachVerbs: V_DO)
		(comPad init:)
		(hangyThing init:)
		(light init:)
		(pillar2 init: approachVerbs: V_DO)
		(pillar3 init: approachVerbs: V_DO)
		(pillar4 init: approachVerbs: V_DO)
		(pillar1 init: approachVerbs: V_DO)
		(viewScreen init: approachVerbs: V_DO)
		(table1 init:)
		(seat1 init:)
		(table2 init:)
		(seat2 init:)
		(table3 init:)
		(seat3 init:)
		(table4 init:)
		(seat4 init:)
		(hang2 init:)
		(light2 init:)
		(light3 init:)
		(theChow init: hide: approachVerbs: V_DO)
		(if (Btst fChowReady)
			(theChow show:)
		)
	)
	
	(method (dispose)
		(if (!= newRoomNum 460)
			(theMusic fade:)
		)
		(super dispose:)
	)
	
	(method (cue)
		(if (and (Btst fChowReady) (not (theChow isNotHidden:)))
			(theChow show:)
		)
	)
)

(instance sEgoComes of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 2))
			(1
				(theMusic2 number: 941 loop: 1 play:)
				(ego
					view: 364
					setCel: 0
					setLoop: 0
					init:
					posn: 50 127
					setSpeed: 9
					setCycle: EndLoop self
				)
			)
			(2
				(ego
					normalize: 900
					setScaler: Scaler 100 43 120 95
					setLoop: 2
				)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sEgoGoes of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego
					normalize: 900
					setLoop: 3
					setScaler: Scaler 100 43 120 95
					init:
				)
				(= cycles 1)
			)
			(1
				(if (or (== selectedRoom curRoomNum) (== selectedRoom 0))
					(theGame handsOn:)
					(self dispose:)
				else
					(ego setHeading: 180)
					(= seconds 2)
				)
			)
			(2
				(theMusic2 number: 926 loop: 1 play:)
				(ego
					view: 3630
					setCel: 0
					setLoop: 0
					init:
					posn: 50 127
					setSpeed: 9
					setCycle: EndLoop self
				)
			)
			(3
				(curRoom newRoom: selectedRoom)
				(self dispose:)
			)
		)
	)
)

(instance theChow of View
	(properties
		noun N_CHOW
		sightAngle 20
		approachX 281
		approachY 127
		x 297
		y 92
		priority 112
		fixPriority 1
		view 25
		loop 1
		signal (| ignrAct canUpdate) ;$4001
	)
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(V_DO
					(ego get: iBjornChow)
					(Bset fGotChow)
					(Bclr fChowReady)
					(self dispose:)
					(return TRUE)
				)
				(else 
					(super doVerb: theVerb &rest)
				)
			)
		)
	)
)

(instance comPost of Feature
	(properties
		noun N_COMPOST
		sightAngle 20
		approachX 50
		approachY 127
		x 72
		y 38
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: PTotalAccess
					init:
						26 79
						26 88
						40 88
						41 79
					yourself:
				)
		)
		(super init:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO
				(curRoom newRoom: 460)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance windowR of Feature
	(properties
		noun N_WINDOW_R
		sightAngle 20
		approachX 252
		approachY 99
		x 252
		y 99
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: PTotalAccess
					init:
						207 0
						197 15
						195 35
						199 51
						216 67
						242 78
						259 79
						259 45
						257 44
						256 31
						259 28
						259 0
					yourself:
				)
				((Polygon new:)
					type: PContainedAccess
					init:
						273 0
						273 30
						313 21
						305 5
						274 0
						275 17
						272 20
					yourself:
				)
		)
		(super init:)
	)
)

(instance replicatorFeature2 of Feature
	(properties
		noun N_REPLICATOR
		sightAngle 40
		approachX 281
		approachY 127
		x 292
		y 90
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: PTotalAccess
					init:
						278 65
						278 91
						276 95
						301 100
						306 92
						306 61
					yourself:
				)
		)
		(super init:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO
				(curRoom setInset: (ScriptID 800 0) curRoom 0 1)
				(theChow hide:)
				(return TRUE)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
		(return
			(super init:)
		)
	)
)

(instance comPad of Feature
	(properties
		noun N_COMPAD
		sightAngle 40
		x 27
		y 125
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: PTotalAccess
					init:
						42 120
						65 124
						74 126
						63 130
						39 132
						18 127
						27 122
					yourself:
				)
		)
		(super init:)
	)
)

(instance hangyThing of Feature
	(properties
		noun N_HANGY_THING
		sightAngle 40
		x 153
		y 7
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: PTotalAccess
					init: 138 0 137 26 144 28 153 27 153 0
					yourself:
				)
		)
		(super init:)
	)
)

(instance pillar1 of Feature
	(properties
		noun N_PILLAR1
		sightAngle 40
		approachX 3
		approachY 71
		x 3
		y 71
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: PTotalAccess
					init: 0 5 4 7 4 15 5 17 5 34 3 36 4 124 0 124
					yourself:
				)
		)
		(super init:)
	)
)

(instance light of Feature
	(properties
		noun N_LIGHT
		sightAngle 40
		x 50
		y 10
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: PTotalAccess
					init: 0 3 49 6 98 13 121 24 123 27
						120 29 119 32 98 36 62 38 61 32
						39 25 5 19 4 15 3 6 0 6
					yourself:
				)
		)
		(super init:)
	)
)

(instance pillar2 of Feature
	(properties
		noun N_PILLAR2
		sightAngle 40
		approachX 130
		approachY 7
		x 130
		y 7
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: PTotalAccess
					init: 119 0 122 95 129 95 130 0
					yourself:
				)
		)
		(super init:)
	)
)

(instance pillar3 of Feature
	(properties
		noun N_PILLAR3
		sightAngle 40
		approachX 176
		approachY 7
		x 176
		y 7
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: PTotalAccess
					init: 166 0 167 94 176 93 176 0
					yourself:
				)
		)
		(super init:)
	)
)

(instance pillar4 of Feature
	(properties
		noun N_PILLAR4
		sightAngle 40
		approachX 262
		approachY 112
		x 274
		y 7
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: PTotalAccess
					init: 259 0 257 112 266 113 269 32 273 30
					yourself:
				)
		)
		(super init:)
	)
)

(instance windowL of Feature
	(properties
		noun N_WINDOW_L
		sightAngle 40
		x 71
		y 7
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: PTotalAccess
					init: 62 38 64 45 117 53 120 52 120 31 91 38 71 38
					yourself:
				)
				((Polygon new:)
					type: PTotalAccess
					init: 70 0 94 5 119 14 119 0
					yourself:
				)
				((Polygon new:)
					type: PTotalAccess
					init: 130 0 130 55 130 57 141 57 153 39 153 28 136 28 136 0
					yourself:
				)
		)
		(super init:)
	)
)

(instance viewScreen of Feature
	(properties
		noun N_VIEWSCREEN
		sightAngle 40
		approachX 98
		approachY 90
		x 98
		y 90
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: PTotalAccess
					init: 73 59 74 76 98 76 98 62
					yourself:
				)
		)
		(super init:)
	)
)

(instance table1 of Feature
	(properties
		noun N_TABLE
		sightAngle 40
		x 78
		y 95
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: PTotalAccess
					init: 78 87 79 90 139 91 138 87 110 86 78 87
					yourself:
				)
		)
		(super init:)
	)
)

(instance seat1 of Feature
	(properties
		noun N_SEAT
		sightAngle 40
		x 104
		y 95
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: PTotalAccess
					init: 84 98 84 102 104 102 104 97
					yourself:
				)
				((Polygon new:)
					type: PTotalAccess
					init: 99 95 98 97 114 97 115 95
					yourself:
				)
				((Polygon new:)
					type: PTotalAccess
					init: 140 96 139 99 159 99 158 95
					yourself:
				)
		)
		(super init:)
	)
)

(instance table2 of Feature
	(properties
		noun N_TABLE
		sightAngle 40
		x 189
		y 85
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: PTotalAccess
					init: 163 82 163 85 189 85 189 82
					yourself:
				)
		)
		(super init:)
	)
)

(instance seat2 of Feature
	(properties
		noun N_SEAT
		sightAngle 40
		x 104
		y 95
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: PTotalAccess
					init: 151 87 151 90 162 90 161 87
					yourself:
				)
				((Polygon new:)
					type: PTotalAccess
					init: 171 87 171 90 182 90 182 87
					yourself:
				)
				((Polygon new:)
					type: PTotalAccess
					init: 190 87 190 90 200 89 200 87
					yourself:
				)
		)
		(super init:)
	)
)

(instance table3 of Feature
	(properties
		noun N_TABLE
		sightAngle 40
		x 244
		y 85
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: PTotalAccess
					init: 220 84 221 86 245 85 244 83
					yourself:
				)
		)
		(super init:)
	)
)

(instance seat3 of Feature
	(properties
		noun N_SEAT
		sightAngle 40
		x 219
		y 95
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: PTotalAccess
					init: 208 87 208 89 218 90 219 87
					yourself:
				)
				((Polygon new:)
					type: PTotalAccess
					init: 227 89 238 89 238 86
					yourself:
				)
		)
		(super init:)
	)
)

(instance table4 of Feature
	(properties
		noun N_TABLE
		sightAngle 40
		x 259
		y 95
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: PTotalAccess
					init: 241 92 259 91 259 88
					yourself:
				)
		)
		(super init:)
	)
)

(instance seat4 of Feature
	(properties
		noun N_SEAT
		sightAngle 40
		x 234
		y 95
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: PTotalAccess
					init: 221 96 222 98 235 98 234 96
					yourself:
				)
				((Polygon new:)
					type: PTotalAccess
					init: 243 95 244 97 257 97 255 95
					yourself:
				)
				((Polygon new:)
					type: PTotalAccess
					init: 248 98 248 100 257 100 257 97
					yourself:
				)
		)
		(super init:)
	)
)

(instance hang2 of Feature
	(properties
		noun N_HANGY_THING
		sightAngle 40
		x 215
		y 2
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: PTotalAccess
					init: 207 0 207 41 211 43 216 41 218 0
					yourself:
				)
		)
		(super init:)
	)
)

(instance light2 of Feature
	(properties
		noun N_LIGHT
		sightAngle 40
		x 194
		y 7
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: PTotalAccess
					init: 166 0 166 5 179 6 194 5 194 0
					yourself:
				)
		)
		(super init:)
	)
)

(instance light3 of Feature
	(properties
		noun N_LIGHT
		sightAngle 40
		x 319
		y 7
	)
	
	(method (init)
		(self
			setPolygon: ((Polygon new:)
				type: PTotalAccess
				init: 276 0 319 5 319 0
				yourself:
			)
		)
		(super init:)
	)
)
