;;; Sierra Script 1.0 - (do not remove this comment)
(script# 250)
(include game.sh) (include "250.shm")
(use Main)
(use FPRoom)
(use Scaler)
(use Osc)
(use Polygon)
(use Feature)
(use Door)
(use Sound)
(use Actor)
(use System)

(public
	rm250 0
)

(instance rm250 of FPRoom
	(properties
		noun N_ROOM
		picture 250
		style FADEOUT
		horizon 40
		north 600
		east 270
		west 230
	)
	
	(method (init)
		(self setRegions: rgFreddy)
		(ego init: setScale: Scaler 100 50 175 95 normalize:)
		(switch prevRoomNum
			(600
				(ego edgeHit: 0)
			)
			(270
				(if (< (ego y?) 117) (ego y: 118))
			)
		)
		(super init:)
		(if (!= (theMusic number?) 200)
			(theMusic number: 200 loop: -1 play:)
		)
		(pharmacyDoor init:)
		(sheriffDoor init: approachVerbs: V_DO)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: PBarredAccess
					init:
						0 0
						319 0
						319 118
						298 118
						293 113
						265 113
						264 76
						253 76
						253 114
						209 114
						194 101
						0 104
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init:
						85 112
						157 112
						157 123
						77 123
						77 112
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
		(if
			(and
				(not (ego has: iTinCan))
				(not (ego has: iUnlitBomb))
				(not (ego has: iBomb))
			)
			(hand init: hide: setScript: sClangCan)
		)
		(sheriffOffice
			init:
			approachVerbs: V_DO
			setOnMeCheck: dynamicName cLGREY
		)
		(thinTall
			init:
			approachVerbs: V_DO
			setOnMeCheck: dynamicName cGREY
		)
		(playhouse
			init: approachVerbs: V_DO
			setOnMeCheck: dynamicName cLBLUE
		)
		(pharmacy
			init:
			approachVerbs: V_DO
			setOnMeCheck: dynamicName cLGREEN
		)
		(pharmacyPoles
			init:
			approachVerbs: V_DO
			setOnMeCheck: dynamicName cLCYAN
		)
		(clutter
			init:
			setOnMeCheck: dynamicName cLRED
		)
		(rail
			init:
			setOnMeCheck: dynamicName cLMAGENTA
		)
		(sheriffSign init: approachVerbs: V_DO)
		(tallThinSign init: approachVerbs: V_DO)
		(playhouseSign init: approachVerbs: V_DO)
		(pharmacySign init: approachVerbs: V_DO)
		(playhouseDoor init: approachVerbs: V_DO)
		(sheriffWindow init: approachVerbs: V_DO)
		(pharmacyRight init: approachVerbs: V_DO)
		(pharmacyLeft init: approachVerbs: V_DO)
		(trough init: approachVerbs: V_DO)
	)
	
	(method (newRoom n)
		(if (!= n 230)
			(theMusic fade: 0 30 10 1)
		)
		(super newRoom: n)
	)
)

(instance sClangCan of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= seconds (Random 5 20))
			)
			(1
				(client show:)
				(client setCycle: Oscillate 2 self)
				(canSound play:)
			)
			(2
				(canSound stop:)
				(client hide:)
				(self changeState: 0)
			)
		)
	)
)

(instance hand of Actor
	(properties
		x 60
		y 78
		noun N_HAND
		view 251
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO
				(if
					(<
						(GetDistance (ego x?) (ego y?) (hand x?) (hand y?))
						40
					)
					(canSound stop:)
					(ego get: iTinCan self)
					(self dispose:)
				else
					(messager say: noun theVerb C_GETCLOSER)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance sheriffOffice of Feature
	(properties
		x 44
		y 40
		noun N_SHERIFFOFFICE
	)
)

(instance thinTall of Feature
	(properties
		x 99
		y 29
		noun N_THINTALL
	)
)

(instance playhouse of Feature
	(properties
		x 159
		y 39
		noun N_PLAYHOUSE
	)
)

(instance pharmacy of Feature
	(properties
		x 268
		y 32
		noun N_PHARMACY
	)
)

(instance pharmacyPoles of Feature
	(properties
		x 268
		y 32
		noun N_PHARMACYPOLES
	)
)

(instance clutter of Feature
	(properties
		x 169
		y 167
		noun N_CLUTTER
	)
)

(instance rail of Feature
	(properties
		x 309
		y 104
		noun N_RAIL
	)
)

(instance sheriffSign of Feature
	(properties
		x 44
		y 40
		noun N_SHERIFFSIGN
		nsTop 32
		nsLeft 20
		nsBottom 49
		nsRight 69
	)
)

(instance tallThinSign of Feature
	(properties
		x 99
		y 29
		noun N_TALLTHINSIGN
		nsTop 6
		nsLeft 83
		nsBottom 53
		nsRight 115
	)
)

(instance playhouseSign of Feature
	(properties
		x 159
		y 39
		noun N_PLAYHOUSESIGN
		nsTop 24
		nsLeft 119
		nsBottom 54
		nsRight 199
	)
)

(instance pharmacySign of Feature
	(properties
		x 268
		y 32
		noun N_PHARMACYSIGN
		nsTop 24
		nsLeft 217
		nsBottom 40
		nsRight 319
	)
)

(instance playhouseDoor of Feature
	(properties
		x 158
		y 82
		noun N_PLAYHOUSEDOOR
		nsTop 63
		nsLeft 142
		nsBottom 101
		nsRight 175
	)
)

(instance sheriffWindow of Feature
	(properties
		x 61
		y 76
		noun N_SHERIFFWINDOW
		nsTop 70
		nsLeft 54
		nsBottom 82
		nsRight 69
	)
	
	(method (handleEvent event)
		(if (== (event message?) V_DO)
			(hand hide: setScript: 0)
			(canSound stop:)
		)
		(super handleEvent: event)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO
				(hand hide: setScript: sClangCan)
				(super doVerb: theVerb &rest)
			)
			(V_BOMB_LIT
				(messager say: noun theVerb)
			)
			(else 
				(if (& ((ScriptID 0 12) doit: theVerb) FORCE)
					(messager say: noun V_USEIT)
				else
					(super doVerb: theVerb &rest)
				)
			)
		)
	)
)

(instance pharmacyRight of Feature
	(properties
		x 295
		y 71
		noun N_PHARMACYRIGHT
		nsTop 49
		nsLeft 285
		nsBottom 94
		nsRight 306
	)
)

(instance pharmacyLeft of Feature
	(properties
		x 228
		y 69
		noun N_PHARMACYLEFT
		nsTop 50
		nsLeft 222
		nsBottom 89
		nsRight 235
	)
)

(instance trough of Feature
	(properties
		x 118
		y 114
		noun N_TROUGH
		nsTop 110
		nsLeft 91
		nsBottom 119
		nsRight 146
	)
)

(instance pharmacyDoor of Door
	(properties
		x 259
		y 95
		noun N_PHARMACYDOOR
		approachX 260
		approachY 106
		view 250
		loop 1
		entranceTo 600
		openSnd 146
		closeSnd 147
		openVerb V_DO
		moveToX 260
		moveToY 93
		enterType 0
		exitType 0
	)
)

(instance sheriffDoor of Door
	(properties
		x 42
		y 99
		noun N_SHERIFFDOOR
		view 250
		locked 1
	)
)

(instance canSound of Sound
	(properties
		flags mNOPAUSE
		number 223
		loop -1
	)
)
