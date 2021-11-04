;;; Sierra Script 1.0 - (do not remove this comment)
(script# 450)
(include game.sh) (include "450.shm")
(use Main)
(use SQRoom)
(use Scaler)
(use Polygon)
(use Feature)
(use Motion)
(use Actor)
(use System)

(public
	rm450 0
)

(local
	local0 =  9
)
(instance rm450 of SQRoom
	(properties
		noun N_ROOM
		picture 4501
	)
	
	(method (init)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: PContainedAccess
					init: 42 116 73 133 163 133 163 138 203
						138 203 130 318 130 318 102 202 103
						186 95 184 85 274 85 282 80 318 80
						318 52 233 52 221 46 145 46 134 53
						14 53 21 75 27 75 27 81 3 98 3 116
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init: 76 65 175 65 175 74 125 82 70 82
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init: 172 94 124 103 69 103 63 97 25 97 21 90 36 78 63 78 63 85 172 85
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init: 160 107 160 118 133 128 73 128 54 118 54 107
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init: 272 82 237 82 223 82 197 78 184 72 184 65 291 65 291 70
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init: 316 107 316 115 283 123 205 123 187 115 187 107
					yourself:
				)
		)
		(super init:)
		(if (!= prevRoomNum 460)
			(theMusic number: 123 flags: 1 loop: -1 play:)
		)
		(theGame handsOff:)
		(comPost init: approachVerbs: V_DO)
		(shuttleDoor init: approachVerbs: V_DO)
		(ship1 init: approachVerbs: V_DO)
		(ship2 init: approachVerbs: V_DO)
		(ship3 init: approachVerbs: V_DO)
		(ship4 init: approachVerbs: V_DO)
		(ship5 init: approachVerbs: V_DO)
		(ship6 init: approachVerbs: V_DO)
		(ship7 init: approachVerbs: V_DO)
		(ship8 init: approachVerbs: V_DO)
		(ship9 init: approachVerbs: V_DO)
		(workStation init: approachVerbs: V_DO)
		(towerSupports init: approachVerbs: V_DO)
		(leftTower init: approachVerbs: V_DO)
		(rightTower init: approachVerbs: V_DO)
		(tubeTop init: approachVerbs: V_DO)
		(tubeBottom init: approachVerbs: V_DO)
		(cond 
			((Btst fInIntro) (curRoom setScript: sFinishIntro))
			(
				(and
					(== prevRoomNum 460)
					(or (== selectedRoom 0) (== selectedRoom 450))
				)
				(ego
					normalize:
					posn: 184 48
					setHeading: 0
					setScaler: Scaler 31 28 140 45
					init:
				)
				(theGame handsOn:)
			)
			((== prevRoomNum 480)
				(ego
					normalize:
					setScaler: Scaler 31 28 140 45
					posn: 238 103
					setHeading: 180
					init:
				)
				(curRoom setScript: sEnterFromShuttle)
			)
			((== prevRoomNum 460) (curRoom setScript: sExitThruComPost))
			(else (curRoom setScript: sEnterRoom))
		)
	)
	
	(method (dispose)
		(ego setScale: 0 normalize:)
		(if (!= newRoomNum 460) (theMusic fade:))
		(super dispose:)
	)
)

(instance sEnterFromShuttle of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 1))
			(1 (proc0_7 0 6 self))
			(2
				(shuttleDoor loop: 1 cel: 8 setCycle: BegLoop self)
			)
			(3
				(shuttleDoor loop: 0 cel: 9 setCycle: BegLoop self)
			)
			(4
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sFinishIntro of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 1))
			(1 (proc0_7 0 6 self))
			(2
				(ego
					normalize:
					loop: 2
					posn: 3 63
					setMotion: MoveTo 70 63 self
					setScaler: Scaler 31 28 140 45
					init:
				)
			)
			(3
				(Bclr fInIntro)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sEnterRoom of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 1))
			(1
				(theMusic2 number: 941 loop: 1 play:)
				(ego
					view: 364
					loop: 0
					cel: 0
					posn: 184 48
					setPri: 1
					setSpeed: local0
					setScaler: Scaler 31 28 140 45
					setCycle: EndLoop self
					init:
				)
			)
			(2
				(ego normalize: loop: 2)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sExitThruComPost of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego
					normalize:
					loop: 3
					setSpeed: local0
					setScaler: Scaler 31 28 140 45
					init:
				)
				(= ticks 40)
			)
			(1
				(ego setHeading: 180)
				(= ticks 90)
			)
			(2
				(theMusic2 number: 926 loop: 1 play:)
				(ego
					view: 3630
					loop: 0
					cel: 0
					posn: 184 48
					setPri: 1
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

(instance sExitToShuttle of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setHeading: 0 self)
			)
			(1 (= cycles 3))
			(2
				(shuttleDoor setCycle: EndLoop self)
			)
			(3
				(shuttleDoor loop: 1 cel: 0 setCycle: EndLoop self)
			)
			(4 (proc0_7 1 6 self))
			(5
				(curRoom newRoom: 480)
				(self dispose:)
			)
		)
	)
)

(instance comPost of Feature
	(properties
		noun N_COMPOST
		nsLeft 180
		nsTop 27
		nsRight 189
		nsBottom 43
		approachX 184
		approachY 48
		x 184
		y 47
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

(instance shuttleDoor of Prop
	(properties
		noun N_SHUTTLE_DOOR
		approachX 238
		approachY 103
		x 239
		y 101
		view 450
		signal $5021
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO
				(self setScript: sExitToShuttle)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance ship1 of Feature
	(properties
		noun N_SHIP1
		approachX 13
		approachY 96
		x 12
		y 95
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: PTotalAccess
					init: 0 32 7 31 8 38 12 38 21 68 20 75 23 77 23 81 5 93 0 92
					yourself:
				)
		)
		(super init: &rest)
	)
)

(instance ship2 of Feature
	(properties
		noun N_SHIP2
		approachX 140
		approachY 82
		x 140
		y 81
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: PTotalAccess
					init:
						84 73 141 75 153 75 153 72 168 70
						170 68 157 61 152 57 147 55 138 55
						120 52 89 49 77 50 77 52 87 53 87
						56 80 57 78 59 79 60 87 61 89 68
					yourself:
				)
		)
		(super init: &rest)
	)
)

(instance ship3 of Feature
	(properties
		noun N_SHIP3
		approachX 140
		approachY 103
		x 140
		y 102
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: PTotalAccess
					init:
						77 73 113 73 140 77 154 83 161 85 165
						88 161 90 143 96 72 96 67 91 68 88 60
						83 65 79 73 77 76 76 75 74
					yourself:
				)
		)
		(super init: &rest)
	)
)

(instance ship4 of Feature
	(properties
		noun N_SHIP4
		approachX 135
		approachY 128
		x 135
		y 127
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: PTotalAccess
					init:
						72 93 77 89 108 89 130 96 156 109
						151 114 138 120 129 120 130 125
						110 123 79 123 72 119 60 103 68 95
					yourself:
				)
		)
		(super init: &rest)
	)
)

(instance ship5 of Feature
	(properties
		noun N_SHIP5
		approachX 132
		approachY 133
		x 132
		y 134
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: PTotalAccess
					init: 60 138 156 138 158 135 150 131 129 125 109 124 79 124 54 108 40 107
					yourself:
				)
		)
		(super init: &rest)
	)
)

(instance ship6 of Feature
	(properties
		noun N_SHIP6
		approachX 203
		approachY 83
		x 203
		y 82
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: PTotalAccess
					init:
						190 67 190 63 197 56 204 56 208 58 213 58
						219 54 225 53 236 53 242 53 244 48 247 54
						263 54 270 51 286 57 279 63 269 59 270 63
						270 73 223 72 212 68 207 75 202 75 195 72
						191 70
					yourself:
				)
		)
		(super init: &rest)
	)
)

(instance ship7 of Feature
	(properties
		noun N_SHIP7
		approachX 217
		approachY 103
		x 217
		y 100
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: PTotalAccess
					init:
						281 64 267 64 259 68 249 68 236 69 231 73
						222 74 207 79 199 82 194 84 194 85 186 87
						197 88 202 92 214 94 245 94 274 94 273 83
						279 83 283 79 281 74 275 73
					yourself:
				)
		)
		(super init: &rest)
	)
)

(instance ship8 of Feature
	(properties
		noun N_SHIP8
		approachX 256
		approachY 128
		x 256
		y 127
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: PTotalAccess
					init:
						313 110 302 111 292 117 226 117 203 115
						192 107 205 101 220 97 249 96 275 93 290 95
						295 96 297 102 310 102 315 105
					yourself:
				)
		)
		(super init: &rest)
	)
)

(instance ship9 of Feature
	(properties
		noun N_SHIP9
		approachX 223
		approachY 131
		x 223
		y 132
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: PTotalAccess
					init:
						319 117 300 117 293 115 274 115 274 119
						254 119 242 114 232 114 232 120 224 121
						217 124 208 132 217 138 292 138 306 130
						319 127
					yourself:
				)
		)
		(super init: &rest)
	)
)

(instance workStation of Feature
	(properties
		noun N_WORKSTATION
		y 140
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: PTotalAccess
					init:
						0 111 50 111 47 107 47 100 57 100
						64 105 76 112 74 117 71 120 67 122
						53 114 61 138 0 138
					yourself:
				)
		)
		(super init: &rest)
	)
)

(instance towerSupports of Feature
	(properties
		noun N_TOWER_SUPPORTS
		y 96
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: PTotalAccess
					init:
						295 95 294 95 294 38 285 36 285 85
						284 85 284 36 283 36 288 27 293 27
						295 28 313 28 317 37 316 38 315 94
						314 94 314 36 295 36
					yourself:
				)
				((Polygon new:)
					type: PTotalAccess
					init:
						28 94 28 8 31 0 58 0 59 3 59 85 58 85
						58 6 51 12 51 95 50 95 50 12 35 12 29 6
						29 94
					yourself:
				)
		)
		(super init: &rest)
	)
)

(instance leftTower of Feature
	(properties
		noun N_LEFT_TOWER
		approachX 39
		approachY 96
		x 39
		y 95
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: PTotalAccess
					init:
						37 13 37 45 35 45 34 85 25 91
						34 91 34 94 45 94 49 91 57 91
						61 87 55 85 55 45 49 45 49 13
					yourself:
				)
		)
		(super init: &rest)
	)
)

(instance rightTower of Feature
	(properties
		noun N_RIGHT_TOWER
		approachX 306
		approachY 98
		x 306
		y 97
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: PTotalAccess
					init:
						293 0 293 29 289 38 289 85 283 87
						287 91 295 91 300 95 311 95 311 92
						318 91 309 84 309 43 307 27 307 0
					yourself:
				)
		)
		(super init: &rest)
	)
)

(instance tubeTop of Feature
	(properties
		noun N_TUBE_TOP
		approachX 184
		approachY 48
		x 184
		y 47
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: PTotalAccess
					init: 180 25 188 25 188 18 186 15 182 15 180 18
					yourself:
				)
		)
		(super init: &rest)
	)
)

(instance tubeBottom of Feature
	(properties
		noun N_TUBE_BOTTOM
		approachX 184
		approachY 48
		x 184
		y 47
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: PTotalAccess
					init: 179 49 180 50 187 50 191 49 189 47 180 47
					yourself:
				)
		)
		(super init: &rest)
	)
)
