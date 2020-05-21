;;; Sierra Script 1.0 - (do not remove this comment)
(script# 230)
(include game.sh) (include "230.shm")
(use Main)
(use Swing)
(use FPRoom)
(use Scaler)
(use PolyPath)
(use Polygon)
(use Feature)
(use Door)
(use Sound)
(use Motion)
(use Actor)
(use System)

(public
	rm230 0
)

(local
	local0 =  1
)
(instance rm230 of FPRoom
	(properties
		noun 15
		picture 230
		horizon 40
		north 670
		east 250
		west 220
	)
	
	(method (init)
		(self setRegions: rgFreddy)
		(switch prevRoomNum
			(west
				(= style SCROLLLEFT)
				(theGame handsOn:)
			)
			(670
				(ego edgeHit: 0)
			)
			(else 
				(ego x: 160 y: 160)
				(= style FADEOUT)
			)
		)
		(ego init: setScale: Scaler 100 50 175 95 normalize:)
		(super init:)
		(if (!= (theMusic number?) 200)
			(theMusic number: 200 loop: -1 play:)
		)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: PBarredAccess
					init:
						0 0
						319 0
						319 122
						200 122
						193 113
						141 113
						135 128
						70 125
						64 113
						49 113
						49 86
						35 86
						35 122
						0 122
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init:
						0 166
						319 166
						319 189
						0 189
					yourself:
				)
		)
		(barWindow ignoreActors: addToPic: approachVerbs: V_DO)
		(saloonDoor init:)
		(momsDoor init: approachVerbs: V_DO)
		(saloonWindow init: approachVerbs: V_DO)
		(saloonWindow2 init: approachVerbs: V_DO)
		(saloon init: approachVerbs: V_DO)
		(cafe init: approachVerbs: V_DO)
		(momsWindow init: approachVerbs: V_DO)
		(barrel init: approachVerbs: V_DO)
		(hotelWindow init: approachVerbs: V_DO)
		(hotelWindow2 init: approachVerbs: V_DO)
		(hotelWindow3 init: approachVerbs: V_DO)
		(hotelWindow4 init: approachVerbs: V_DO)
		(hotelWindow5 init: approachVerbs: V_DO)
		(hotelDoor init: approachVerbs: V_DO)
		(clutter
			init:
			setOnMeCheck: dynamicName cGREEN
		)
		(sidewalk
			init:
			setOnMeCheck: dynamicName cLCYAN
		)
		(bunting
			init:
			setOnMeCheck: dynamicName cLGREEN
		)
		(cactus
			init:
			setOnMeCheck: dynamicName cGREY
		)
		(rrsign
			init:
			setOnMeCheck: dynamicName cLBLUE
		)
		(post
			init:
			setOnMeCheck: dynamicName cCYAN
		)
		(hotelPorch
			init:
			approachVerbs: V_DO
			setOnMeCheck: dynamicName cRED
		)
		(hotelBalcony
			init:
			approachVerbs: V_DO
			setOnMeCheck: dynamicName cMAGENTA
		)
		(momsPorch
			init:
			approachVerbs: V_DO
			setOnMeCheck: dynamicName cBROWN
		)
		(saloonPorch
			init:
			approachVerbs: V_DO
			setOnMeCheck: dynamicName cLGREY
		)
	)
	
	(method (doit)
		(super doit:)
		(cond 
			(script)
			(
				(and
					(< (ego y?) 113)
					(< 30 (ego x?))
					(< (ego x?) 55)
					(or (< (ego heading?) 46) (> (ego heading?) 314))
					local0
				)
				(= local0 0)
				(saloonDoor doVerb: V_DO)
			)
		)
	)
	
	(method (newRoom n)
		(if (!= n 250)
			(theMusic fade: 0 30 10 1)
		)
		(super newRoom: n)
	)
)

(instance sCloseSaloonDoors of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(saloonDoor setPri: 6 setCel: 3 setCycle: CycleTo 6 1 self)
			)
			(1
				(ego
					posn: (saloonDoor moveToX?) (saloonDoor moveToY?)
					setMotion: PolyPath (saloonDoor approachX?) (saloonDoor approachY?) self
				)
			)
			(2
				(doorSound number: 911 play:)
				(saloonDoor setPri: 5 setCycle: Swing 1 1 1 self)
			)
			(3
				(saloonDoor state: 3 cue:)
				(self dispose:)
			)
		)
	)
)

(instance sOpenSaloonDoors of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego
					setMotion: PolyPath (saloonDoor moveToX?) (saloonDoor moveToY?) self
				)
			)
			(1
				(saloonDoor setPri: 7 setCycle: Swing 1 1 1 self)
			)
			(2
				(saloonDoor cue:)
				(self dispose:)
			)
		)
	)
)

(instance barWindow of View
	(properties
		x 103
		y 90
		noun N_SALOONWINDOW
		view 230
		loop 2
	)
)

(instance saloonWindow of Feature
	(properties
		x 76
		y 78
		noun N_SALOONWINDOW
		nsTop 67
		nsLeft 63
		nsBottom 91
		nsRight 89
	)
)

(instance saloonWindow2 of Feature
	(properties
		x 103
		y 79
		noun N_SALOONWINDOW
		nsTop 67
		nsLeft 90
		nsBottom 91
		nsRight 116
	)
)

(instance saloon of Feature
	(properties
		x 65
		y 22
		noun N_SALOON
		nsTop -1
		nsLeft 34
		nsBottom 46
		nsRight 97
	)
)

(instance cafe of Feature
	(properties
		x 163
		y 25
		noun N_CAFE
		nsTop 5
		nsLeft 128
		nsBottom 46
		nsRight 198
	)
)

(instance momsWindow of Feature
	(properties
		x 181
		y 74
		noun N_MOMSWINDOW
		nsTop 65
		nsLeft 170
		nsBottom 84
		nsRight 193
	)
)

(instance barrel of Feature
	(properties
		x 119
		y 114
		noun N_BARREL
		nsTop 104
		nsLeft 110
		nsBottom 124
		nsRight 129
	)
)

(instance hotelWindow of Feature
	(properties
		x 236
		y 30
		noun N_HOTELWINDOW
		nsTop 17
		nsLeft 229
		nsBottom 43
		nsRight 244
	)
)

(instance hotelWindow2 of Feature
	(properties
		x 271
		y 29
		noun N_HOTELWINDOW
		nsTop 15
		nsLeft 263
		nsBottom 43
		nsRight 280
	)
)

(instance hotelWindow3 of Feature
	(properties
		x 311
		y 30
		noun N_HOTELWINDOW
		nsTop 17
		nsLeft 304
		nsBottom 43
		nsRight 319
	)
)

(instance hotelWindow4 of Feature
	(properties
		x 241
		y 79
		noun N_HOTELWINDOW
		nsTop 65
		nsLeft 212
		nsBottom 93
		nsRight 270
	)
)

(instance hotelWindow5 of Feature
	(properties
		x 316
		y 79
		noun N_HOTELWINDOW
		nsTop 65
		nsLeft 313
		nsBottom 93
		nsRight 319
	)
)

(instance hotelDoor of Feature
	(properties
		x 291
		y 82
		noun N_HOTELDOOR
		nsTop 66
		nsLeft 276
		nsBottom 99
		nsRight 306
	)
)

(instance clutter of Feature
	(properties
		x 160
		y 180
		noun N_CLUTTER
	)
)

(instance sidewalk of Feature
	(properties
		x 160
		y 180
		noun N_SIDEWALK
	)
)

(instance bunting of Feature
	(properties
		x 160
		y 180
		noun N_BUNTING
	)
)

(instance cactus of Feature
	(properties
		x 160
		y 180
		noun N_CACTUS
	)
)

(instance rrsign of Feature
	(properties
		x 160
		y 180
		noun N_RRSIGN
	)
)

(instance post of Feature
	(properties
		x 100
		y 90
		noun N_POST
	)
)

(instance hotelPorch of Feature
	(properties
		x 190
		y 80
		noun N_HOTELPORCH
	)
)

(instance hotelBalcony of Feature
	(properties
		x 190
		y 60
		noun N_HOTELBALCONY
	)
)

(instance momsPorch of Feature
	(properties
		x 160
		y 80
		noun N_MOMSPORCH
	)
)

(instance saloonPorch of Feature
	(properties
		x 80
		y 80
		noun N_SALOONPORCH
	)
)

(instance momsDoor of Door
	(properties
		x 140
		y 93
		noun N_MOMSDOOR
		approachX 147
		approachY 106
		view 230
		loop 1
	)
)

(instance saloonDoor of Door
	(properties
		x 40
		y 94
		noun N_SALOONDOOR
		approachX 42
		approachY 112
		view 230
		cel 3
		priority 5
		signal fixPriOn
		entranceTo 670
		openSnd 911
		closeSnd 911
		openVerb V_DO
		moveToX 40
		moveToY 101
		enterType 0
		exitType 0
	)
	
	(method (init)
		(= openScript sOpenSaloonDoors)
		(= closeScript sCloseSaloonDoors)
		(super init:)
		(= cel 3)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO
				(curRoom north: 670)
				(super doVerb: theVerb)
			)
			(else
				(super doVerb: theVerb)
			)
		)
	)
	
	(method (open)
		(if (user controls?)
			(theGame handsOff:)
		)
		(= state 1)
		(self setCycle: CycleTo 0 -1 self)
		(if (self openSnd?)
			(doorSound number: 911 play:)
		)
	)
	
	(method (createPoly)
		(super createPoly: 28 103 28 100 57 100 57 103)
	)
)

(instance doorSound of Sound)
