;;; Sierra Script 1.0 - (do not remove this comment)
(script# 820)
(include game.sh)
(use Main)
(use LLRoom)
(use Door)
(use Polygon)
(use Feature)
(use LoadMany)
(use System)

(public
	rm820 0
)

(local
	local0
)
(instance rm820 of LLRoom
	(properties
		lookStr {"This lobby is quite impressive," you think, "especially for such a small building."}
		picture 820
		south 800
		west 840
	)
	
	(method (init)
		(LoadMany VIEW 820)
		(LoadMany SOUND 800 801 802)
		(HandsOn)
		(ego init: x: 280 normalize:)
		(theMusic setVol: (* (/ (theMusic vol?) 3) 2))
		(keypad init: approachVerbs: verbDo verbLook)
		(door init:)
		(krapWindow init:)
		(plant init:)
		(outerdesk init:)
		(tree init:)
		(backcounter init:)
		(forecounter init:)
		(lamp init:)
		(lights init:)
		(chair init: approachVerbs: verbLook verbDo)
		(super init:)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: PBarredAccess
					init:
						0 0
						319 0
						319 189
						315 189
						315 130
						280 129
						255 129
						187 128
						184 119
						306 119
						306 117
						80 117
						73 121
						71 120
						49 142
						56 145
						8 145
						8 156
						49 156
						2 175
						2 187
						185 186
						221 172
						269 182
						268 189
						0 189
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init:
						62 154
						67 143
						67 137
						116 137
						135 125
						172 125
						169 140
						160 150
						107 154
					yourself:
				)
		)
		(ego observeControl: cBLUE)
		(mouseDownHandler add: self)
		(keyDownHandler add: self)
	)
	
	(method (dispose)
		(mouseDownHandler delete: self)
		(keyDownHandler delete: self)
		(super dispose:)
	)
	
	(method (notify doorCode)
		(if (OneOf doorCode -19582 -2)
			(door locked: FALSE)
			(TimePrint 820 0)
		else
			(TimePrint 820 1)
		)
		(HandsOn)
	)
)

(instance door of Door
	(properties
		x 18
		y 154
		description {the door}
		approachX 58
		approachY 152
		view 820
		entranceTo 840
		locked 1
		moveToX 20
		moveToY 146
		enterType 0
		exitType 0
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(if (not (self locked?))
					(TimePrint 820 2)
					(ego ignoreControl: cBLUE)
				)
				(super doVerb: theVerb theItem &rest)
			)
			(verbLook
				(TimePrint 820 3)
				(TimePrint 820 4
					#mode teJustCenter
				)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance krapWindow of Feature
	(properties
		x 197
		y 73
		nsTop 43
		nsLeft 75
		nsBottom 102
		nsRight 315
		description {the plate glass window}
		sightAngle 0
		lookStr {Through the window you see the night skyline of beautiful downtown Philadelphia.}
	)
)

(instance outerdesk of Feature
	(properties
		x 114
		y 130
		nsTop 119
		nsLeft 78
		nsBottom 142
		nsRight 150
		description {the receptionist's desk}
		sightAngle 40
		lookStr {This is the receptionist's desk. Luckily for you, she seems to be absent right now.}
	)
)

(instance plant of Feature
	(properties
		x 221
		y 167
		nsTop 157
		nsLeft 203
		nsBottom 178
		nsRight 240
		description {the plant}
		sightAngle 40
		lookStr {A potted plant adds so much to office ambience.}
	)
)

(instance keypad of Feature
	(properties
		x 51
		y 108
		nsTop 101
		nsLeft 46
		nsBottom 115
		nsRight 57
		description {the keypad}
		sightAngle 40
		approachX 58
		approachY 142
		lookStr {This keypad controls access to John Krapper's private office door.}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				((ScriptID DIALER 0) init: 0 rm820)
			)
			(verbDo
				((ScriptID DIALER 0) init: 0 rm820)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance tree of Feature
	(properties
		x 268
		y 74
		nsTop 41
		nsLeft 248
		nsBottom 108
		nsRight 289
		description {the potted tree}
		sightAngle 40
		lookStr {There must be more light in here in the daytime.}
	)
)

(instance forecounter of Feature
	(properties
		x 200
		y 182
		nsTop 176
		nsLeft 156
		nsBottom 189
		nsRight 245
		description {the planter}
		sightAngle 40
		lookStr {This planter is near the front door.}
	)
)

(instance backcounter of Feature
	(properties
		x 254
		y 115
		nsTop 107
		nsLeft 189
		nsBottom 124
		nsRight 319
		description {the rear counter}
		sightAngle 40
		lookStr {This counter almost looks like a window seat.}
	)
)

(instance lamp of Feature
	(properties
		x 142
		y 110
		nsTop 103
		nsLeft 127
		nsBottom 118
		nsRight 158
		description {the lamp}
		sightAngle 40
		lookStr {You really like this lamp's style.}
	)
)

(instance lights of Feature
	(properties
		x 87
		y 20
		nsTop 1
		nsLeft 54
		nsBottom 39
		nsRight 121
		description {the lights}
		sightAngle 40
		lookStr {Subtle wall washer floodlights cast a lovely glow over the supergraphics on the lobby wall.}
	)
)

(instance chair of Feature
	(properties
		x 104
		y 124
		heading 180
		nsTop 106
		nsLeft 97
		nsBottom 124
		nsRight 111
		description {the chair}
		sightAngle 40
		approachX 104
		approachY 120
		lookStr {The receptionist's chair is empty, but still warm to your touch. She's only been gone a short while, and may soon return.}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(TimePrint lookStr)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)
