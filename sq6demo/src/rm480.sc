;;; Sierra Script 1.0 - (do not remove this comment)
(script# 480)
(include game.sh) (include "480.shm")
(use Main)
(use SQRoom)
(use Inset)
(use Polygon)
(use Feature)
(use Motion)
(use Actor)
(use System)

(public
	rm480 0
)

(local
	local0
)
(instance rm480 of SQRoom
	(properties
		noun N_ROOM
		picture 480
	)
	
	(method (init)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: PContainedAccess
					init: 96 107 56 107 56 112 89 112 32 137 288 137 238 105 122 105
					yourself:
				)
		)
		(shuttleBay init:)
		(super init:)
		(sound1 number: 240 loop: -1 play:)
		(ego
			normalize:
			setHeading: 90
			posn: 62 111
			setScript: sEnterRoom
			init:
		)
		(shuttleDoor init: approachVerbs: V_DO)
		(theGloveBox init: approachVerbs: V_DO)
		(cockpitCup init: approachVerbs: V_DO)
		(seat_1 init: approachVerbs: V_DO)
		(seat_2 init: approachVerbs: V_DO)
		(seat_3 init: approachVerbs: V_DO)
		(seat_4 init: approachVerbs: V_DO)
		(exitSign init: approachVerbs: V_DO)
		(tank init: approachVerbs: V_DO)
		(outsideWindow init: approachVerbs: V_DO)
		(cup init: approachVerbs: V_DO)
		(trash init: approachVerbs: V_DO)
		(cockpit init: approachVerbs: V_DO)
		(cockpitSeat1 init: approachVerbs: V_DO)
		(cockpitSeat2 init: approachVerbs: V_DO)
		(lights init: approachVerbs: V_DO)
		(wire init: approachVerbs: V_DO)
		(pipe init: approachVerbs: V_DO)
		(closet init: approachVerbs: V_DO)
		(compartment1 init: approachVerbs: V_DO)
		(compartment2 init: approachVerbs: V_DO)
		(compartmentCover init: approachVerbs: V_DO)
		(compartment3 init: approachVerbs: V_DO)
		(morePipes init: approachVerbs: V_DO)
		(seeThroughSeat1 init:)
		(seeThroughSeat2 init:)
		(seeThroughSeat3 init:)
		(seeThroughSeat4 init:)
		(exitRoom init:)
	)
	
	(method (doit)
		(cond 
			((curRoom script?))
			((ego script?))
			((exitRoom onMe: ego) (curRoom setScript: sExitRoom))
		)
		(super doit:)
	)
	
	(method (dispose)
		(sound1 fade:)
		(super dispose:)
	)
)

(instance sEnterRoom of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if (== prevRoomNum 450)
					(proc0_7 0 6 self)
				else
					(= cycles 1)
				)
			)
			(1
				(ego setMotion: MoveTo 111 111 self)
			)
			(2
				(ego setSpeed: egoSpeed)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sExitRoom of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: MoveTo 62 111 self)
			)
			(1 (proc0_7 1 6 self))
			(2
				(ego setSpeed: egoSpeed)
				(curRoom newRoom: 450)
				(self dispose:)
			)
		)
	)
)

(instance shuttleBay of View
	(properties
		x 109
		y 38
		view 4900
		signal (| skipCheck setBaseRect canUpdate) ;$1021
	)
)

(instance cockpitCup of Feature
	(properties
		noun N_CUP
		nsLeft 164
		nsTop 77
		nsRight 166
		nsBottom 80
		sightAngle 40
		approachX 164
		approachY 99
		x 164
		y 99
	)
)

(instance shuttleDoor of Feature
	(properties
		noun N_SHUTTLE_DOOR
		nsLeft 74
		nsTop 41
		nsRight 107
		nsBottom 93
		sightAngle 40
		approachX 97
		approachY 112
		x 85
		y 109
	)
)

(instance theGloveBox of Feature
	(properties
		noun N_GLOVEBOX
		nsLeft 165
		nsTop 74
		nsRight 179
		nsBottom 82
		sightAngle 40
		approachX 185
		approachY 101
		x 185
		y 100
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO (curRoom setInset: gloveBox))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance seat_1 of Feature
	(properties
		noun N_PASSENGER_SEATS
		sightAngle 40
		approachX 64
		approachY 139
		x 64
		y 140
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: PTotalAccess
					init:
						7 139 7 132 12 128 18 128 19 126 16 125
						16 111 20 108 47 108 51 111 51 123 47 126
						46 128 56 128 59 131 59 139
					yourself:
				)
		)
		(super init: &rest)
	)
)

(instance seat_2 of Feature
	(properties
		noun N_PASSENGER_SEATS
		sightAngle 40
		approachX 128
		approachY 139
		x 128
		y 140
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: PTotalAccess
					init:
						99 139 151 139 151 131 148 128 138 128 137
						126 140 125 143 122 143 110 140 108 112 108
						108 112 108 125 111 126 111 128 105 128 99 132
					yourself:
				)
		)
		(super init: &rest)
	)
)

(instance seat_3 of Feature
	(properties
		noun N_PASSENGER_SEATS
		sightAngle 40
		approachX 211
		approachY 139
		x 211
		y 140
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: PTotalAccess
					init:
						188 139 241 139 240 132 235 128 228 128
						227 126 231 124 231 111 226 108 199 108
						196 110 196 123 199 126 204 126 204 128
						191 128 188 131
					yourself:
				)
		)
		(super init: &rest)
	)
)

(instance seat_4 of Feature
	(properties
		noun N_PASSENGER_SEATS
		sightAngle 40
		approachX 266
		approachY 139
		x 266
		y 140
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: PTotalAccess
					init:
						265 138 311 138 311 108 304 108 277 108
						274 110 274 123 279 128 269 128 266 131
					yourself:
				)
		)
		(super init: &rest)
	)
)

(instance exitSign of Feature
	(properties
		noun N_EXIT_SIGN
		sightAngle 40
		approachX 86
		approachY 109
		x 85
		y 109
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: PTotalAccess
					init: 81 28 81 32 94 37 97 33 84 27
					yourself:
				)
		)
		(super init: &rest)
	)
)

(instance tank of Feature
	(properties
		noun 11
		sightAngle 40
		approachX 63
		approachY 122
		x 62
		y 122
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 60 66 64 66 66 64 66 35 65 32 60 32 57 35 57 63
					yourself:
				)
		)
		(super init: &rest)
	)
)

(instance outsideWindow of Feature
	(properties
		noun 12
		sightAngle 40
		approachX 21
		approachY 135
		x 20
		y 135
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 0 6 36 20 36 62 0 103
					yourself:
				)
		)
		(super init: &rest)
	)
)

(instance cup of Feature
	(properties
		noun 13
		sightAngle 40
		approachX 108
		approachY 103
		x 108
		y 102
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 105 104 110 105 112 102 109 100 105 100
					yourself:
				)
		)
		(super init: &rest)
	)
)

(instance trash of Feature
	(properties
		noun 19
		sightAngle 40
		approachX 217
		approachY 103
		x 217
		y 102
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 212 104 222 103 227 99 225 96 220 100 211 100
					yourself:
				)
		)
		(super init: &rest)
	)
)

(instance cockpit of Feature
	(properties
		noun 14
		sightAngle 40
		approachX 172
		approachY 103
		x 172
		y 98
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init:
						145
						101
						202
						101
						239
						75
						239
						43
						236
						38
						229
						35
						191
						35
						189
						38
						155
						38
						155
						35
						116
						35
						108
						37
						106
						43
						106
						75
					yourself:
				)
		)
		(super init: &rest)
	)
)

(instance cockpitSeat1 of Feature
	(properties
		noun 15
		sightAngle 40
		approachX 147
		approachY 103
		x 147
		y 102
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init:
						155
						98
						162
						93
						162
						91
						162
						87
						167
						82
						166
						80
						161
						79
						161
						70
						151
						68
						151
						66
						154
						66
						155
						56
						144
						56
						143
						58
						143
						65
						146
						65
						146
						68
						136
						71
						136
						78
						132
						81
						132
						85
						136
						86
						136
						93
						144
						98
					yourself:
				)
		)
		(super init: &rest)
	)
)

(instance cockpitSeat2 of Feature
	(properties
		noun 15
		sightAngle 40
		approachX 197
		approachY 103
		x 197
		y 102
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init:
						190
						99
						202
						99
						209
						93
						209
						71
						199
						67
						202
						65
						202
						56
						191
						56
						190
						65
						194
						68
						185
						70
						184
						79
						178
						79
						179
						83
						184
						87
						183
						92
					yourself:
				)
		)
		(super init: &rest)
	)
)

(instance lights of Feature
	(properties
		noun 16
		sightAngle 40
		approachX 173
		approachY 111
		x 169
		y 120
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 140 0 156 39 190 38 205 0
					yourself:
				)
		)
		(super init: &rest)
	)
)

(instance wire of Feature
	(properties
		noun 17
		sightAngle 40
		approachX 208
		approachY 125
		x 211
		y 129
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 205 5 209 10 210 20 209 27 210 32 214 25 213 19 213 9 207 2
					yourself:
				)
		)
		(super init: &rest)
	)
)

(instance pipe of Feature
	(properties
		noun 18
		sightAngle 40
		approachX 274
		approachY 135
		x 289
		y 140
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init:
						238
						0
						239
						20
						246
						39
						258
						51
						270
						56
						289
						61
						306
						63
						310
						70
						310
						88
						308
						88
						308
						100
						305
						100
						305
						110
						311
						110
						311
						114
						309
						114
						309
						116
						306
						116
						306
						119
						298
						119
						298
						116
						296
						113
						294
						113
						294
						116
						291
						116
						291
						119
						289
						120
						289
						138
						319
						138
						319
						0
						297
						0
						299
						7
						310
						13
						309
						28
						307
						30
						306
						37
						312
						38
						311
						42
						307
						42
						307
						46
						296
						46
						296
						47
						289
						47
						288
						49
						277
						47
						262
						39
						254
						23
						251
						7
						251
						0
					yourself:
				)
		)
		(super init: &rest)
	)
)

(instance closet of Feature
	(properties
		noun 21
		sightAngle 40
		approachX 237
		approachY 108
		x 252
		y 106
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 247 102 257 105 257 51 253 49 247 50
					yourself:
				)
		)
		(super init: &rest)
	)
)

(instance compartment1 of Feature
	(properties
		noun 22
		sightAngle 40
		approachX 258
		approachY 120
		x 273
		y 116
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 267 38 284 32 284 61 267 62
					yourself:
				)
		)
		(super init: &rest)
	)
)

(instance compartment2 of Feature
	(properties
		noun 23
		sightAngle 40
		approachX 258
		approachY 120
		x 273
		y 116
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 267 66 267 84 284 88 283 65
					yourself:
				)
		)
		(super init: &rest)
	)
)

(instance compartmentCover of View
	(properties
		noun 23
		sightAngle 40
		approachX 258
		approachY 120
		x 267
		y 66
		view 480
		loop 2
	)
)

(instance compartment3 of Feature
	(properties
		noun 24
		sightAngle 40
		approachX 258
		approachY 120
		x 273
		y 116
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 284 93 284 118 266 113 266 89
					yourself:
				)
		)
		(super init: &rest)
	)
)

(instance morePipes of Feature
	(properties
		noun 26
		sightAngle 40
		approachX 249
		approachY 113
		x 262
		y 115
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 232 33 241 40 304 10 297 0 282 0
					yourself:
				)
		)
		(super init: &rest)
	)
)

(instance seeThroughSeat1 of Feature
	(properties
		noun N_ROOM
		nsLeft 24
		nsTop 125
		nsRight 40
		nsBottom 128
		y 141
	)
)

(instance seeThroughSeat2 of Feature
	(properties
		noun N_ROOM
		nsLeft 116
		nsTop 125
		nsRight 132
		nsBottom 128
		y 141
	)
)

(instance seeThroughSeat3 of Feature
	(properties
		noun N_ROOM
		nsLeft 207
		nsTop 125
		nsRight 223
		nsBottom 128
		y 141
	)
)

(instance seeThroughSeat4 of Feature
	(properties
		noun N_ROOM
		nsLeft 284
		nsTop 125
		nsRight 288
		nsBottom 128
		y 141
	)
)

(instance gloveBox of Inset
	(properties
		view 499
		x 113
		y 25
		disposeNotOnMe 1
		noun N_GLOVEBOX_INSET
	)
	
	(method (init)
		(theIconBar disable: 4 5 6 setupExit: 1)
		(if (not (& ((theIconBar at: 7) signal?) $0004))
			(= local0 1)
			(theIconBar disable: 7)
		)
		(super init: &rest)
		(boxStuff init:)
		(boxLid init: setPri: (+ (gloveBox priority?) 10))
		(if (not (ego has: 4))
			(tape init: setPri: (+ (gloveBox priority?) 2))
		)
		(if (and (not (ego has: 7)) (not (ego has: 6)))
			(pliers init: setPri: (+ (gloveBox priority?) 4))
		)
	)
	
	(method (doit &tmp userCurEvent)
		(= userCurEvent (user curEvent?))
		(if (not ((theIconBar plane?) onMe: userCurEvent))
			(cond 
				((not (self onMe: userCurEvent))
					(if (!= theCursor exitCursor)
						(theGame setCursor: exitCursor 1)
					)
				)
				(
				(!= theCursor ((theIconBar curIcon?) getCursor:)) (theGame setCursor: ((theIconBar curIcon?) getCursor:)))
			)
		)
		(if script (script doit:))
	)
	
	(method (dispose)
		(theIconBar setupExit: 0)
		(if (== (boxLid cel?) 1)
			(self setScript: sCloseBox)
		else
			(theIconBar enable: 0 4 5 6)
			(if local0 (theIconBar enable: 7))
			(theGame setCursor: ((theIconBar curIcon?) getCursor:))
			(super dispose:)
		)
	)
)

(instance boxLid of Prop
	(properties
		noun N_BOX_LID
		x 17
		y 54
		z 20
		view 499
		loop 1
		signal $1021
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO
				(if (== (self cel?) 0)
					(self setCel: 1)
				else
					(self setCel: 0)
				)
			)
			(V_TALK
				(if (== (self cel?) 0)
					(messager say: noun theVerb C_BOX_CLOSED)
				else
					(messager say: noun theVerb C_BOX_OPEN)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance tape of View
	(properties
		noun N_TAPE
		x 27
		y 43
		view 499
		loop 3
		signal $1021
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(messager say: noun theVerb)
				(ego get: iDuctTape)
				(self dispose:)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance pliers of View
	(properties
		noun N_PLIERS
		x 52
		y 47
		view 499
		loop 4
		signal $1021
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(messager say: noun theVerb)
				(ego get: iPliers)
				(self dispose:)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance sCloseBox of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(boxLid cel: 0)
				(= ticks 20)
			)
			(1
				(gloveBox dispose:)
				(self dispose:)
			)
		)
	)
)

(instance boxStuff of Feature
	(properties
		noun N_BOX_STUFF
		y 30
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: PTotalAccess
					init: 23 38 97 38 100 57 98 58 21 58 19 57
					yourself:
				)
		)
		(super init: &rest)
	)
)

(instance exitRoom of Feature
	(properties
		noun N_ROOM
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: PNearestAccess
					init: 53 104 53 115 78 115 78 104
					yourself:
				)
		)
	)
)
