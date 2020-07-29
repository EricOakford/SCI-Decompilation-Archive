;;; Sierra Script 1.0 - (do not remove this comment)
(script# 880)
(include game.sh)
(use Main)
(use LLRoom)
(use Door)
(use RandCyc)
(use Polygon)
(use Feature)
(use LoadMany)
(use Motion)
(use Actor)
(use System)

(public
	rm880 0
)

(local
	doorUnlocked
	local1
)
(instance rm880 of LLRoom
	(properties
		lookStr {The studio floor of the K-RAP building is far underground, filled with soundproof studios and playback speakers. A large fountain, complete with giant bronze sculptures, fills the center of the room.}
		picture 880
		east 890
		west 870
	)
	
	(method (init)
		(LoadMany VIEW 872 880 882 883)
		(LoadMany SOUND 800 801 802)
		(ego
			init:
			normalize: 872
			actions: ActionsKRAP
			illegalBits: cBLUE
		)
		(switch prevRoomNum
			(west
				(HandsOn)
				(= style SCROLLLEFT)
			)
			(east
				(ego posn: 250 127 setHeading: 270 edgeHit: 0)
			)
			(else 
				(HandsOn)
				(ego posn: 160 160 edgeHit: 0)
			)
		)
		(super init:)
		(doorA init: approachVerbs: verbDo verbLook)
		(doorB init: approachVerbs: verbDo verbLook)
		(doorC init: approachVerbs: verbDo verbLook)
		(windowA init:)
		(windowB init:)
		(windowC init:)
		(keypadA init: approachVerbs: verbDo verbLook)
		(keypadB init: approachVerbs: verbDo verbLook)
		(keypadC init: approachVerbs: verbDo verbLook)
		(PChammer init: setCycle: RandCycle cycleSpeed: 12)
		(movingSignA init: setCycle: Forward cycleSpeed: 12)
		(cast eachElementDo: #checkDetail)
		(mikeStand init:)
		(statue init:)
		(recSignA init:)
		(recSignB init:)
		(addToPics doit:)
		(self
			addObstacle:
				((Polygon new:)
					type: PBarredAccess
					init:
						0 0
						319 0
						319 189
						0 189
						0 133
						85 138
						141 148
						188 166
						216 187
						316 187
						316 171
						268 132
						251 120
						238 116
						210 116
						207 124
						163 124
						175 109
						139 76
						68 76
						65 72
						54 82
						15 116
						0 116
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init:
						91 109
						97 105
						120 105
						124 112
						117 120
						97 120
					yourself:
				)
		)
		(mouseDownHandler add: self)
		(keyDownHandler add: self)
	)
	
	(method (doit)
		(super doit:)
		(cond 
			(script)
			((IsObjectOnControl ego cBLUE)
				(if doorUnlocked
					(ego edgeHit: NORTH)
					(curRoom newRoom: (curRoom east?))
				else
					(TimePrint 880 0)
				)
			)
		)
	)
	
	(method (dispose)
		(mouseDownHandler delete: self)
		(keyDownHandler delete: self)
		(ActionsKRAP dispose:)
		(super dispose:)
	)
	
	(method (notify num)
		(if (and (OneOf num KRAPCombination -2) (== local1 1))
			(doorB locked: FALSE)
			(= local1 0)
			(TimePrint 880 1)
		else
			(TimePrint 880 2)
		)
		(HandsOn)
	)
)

(instance doorA of Door
	(properties
		x 47
		y 77
		description {the door to Control Room A}
		sightAngle 40
		approachX 64
		approachY 80
		lookStr {This door leads to Control Room A. You can see a man working inside.}
		view 880
		loop 1
		locked TRUE
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(TimePrint 880 3)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance doorB of Door
	(properties
		x 200
		y 111
		description {the Control Room B door}
		sightAngle 40
		approachX 218
		approachY 130
		lookStr {This door leads to Control Room B, which appears to be empty.}
		view 880
		loop 2
		entranceTo 890
		locked 1
		moveToX 220
		moveToY 116
		enterType 0
		exitType 0
	)
)

(instance doorC of Feature
	(properties
		x 265
		y 94
		nsTop 57
		nsLeft 257
		nsBottom 131
		nsRight 274
		description {the Control Room C door}
		sightAngle 40
		approachX 260
		approachY 100
		lookStr {This door leads to Control Room C. It appears to be unoccupied at the moment.}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(TimePrint 880 3)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance keypadA of Feature
	(properties
		x 60
		y 39
		nsTop 31
		nsLeft 55
		nsBottom 47
		nsRight 65
		description {the keypad}
		sightAngle 40
		approachX 64
		approachY 80
		lookStr {Each of the doors has a keypad to limit access to only those with authorization. This keypad controls access to Control Room A.}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				((ScriptID DIALER 0) init: 0 rm880)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance keypadB of Feature
	(properties
		x 239
		y 81
		nsTop 74
		nsLeft 232
		nsBottom 89
		nsRight 246
		description {the keypad}
		sightAngle 40
		approachX 238
		approachY 115
		lookStr {Each of the doors has a keypad to limit access to only those with authorization. This keypad controls access to Control Room B.}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(= local1 1)
				((ScriptID DIALER 0) init: 0 rm880)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance keypadC of Feature
	(properties
		x 254
		y 85
		nsTop 74
		nsLeft 248
		nsBottom 95
		nsRight 257
		description {the keypad}
		sightAngle 40
		approachX 260
		approachY 100
		lookStr {Each of the doors has a keypad to limit access to only those with authorization. This keypad controls access to Control Room C.}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				((ScriptID DIALER 0) init: 0 rm880)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance windowA of Feature
	(properties
		x 122
		y 41
		nsTop 29
		nsLeft 116
		nsBottom 53
		nsRight 128
		description {the window of Control Room A}
		sightAngle 40
		lookStr {Through this window you can see Control Room A.}
	)
)

(instance windowB of Feature
	(properties
		x 161
		y 59
		nsTop 39
		nsLeft 146
		nsBottom 79
		nsRight 176
		description {the window of Control Room B}
		sightAngle 40
		lookStr {Through this window you can see Control Room B. It appears to be currently unoccupied.}
	)
)

(instance windowC of Feature
	(properties
		x 303
		y 111
		nsTop 74
		nsLeft 287
		nsBottom 148
		nsRight 319
		description {the window of Control Room C}
		sightAngle 40
		lookStr {Through this window you can see Control Room C. It appears to be currently unoccupied.}
	)
)

(instance statue of Feature
	(properties
		x 99
		y 106
		nsTop 24
		nsBottom 189
		nsRight 198
		description {the statue}
		sightAngle 40
		onMeCheck $4000
		lookStr {How impressive! K-RAP has a fountain filled with a huge statue of the Queen of Rock 'n' Roll--Connie Stevens!}
	)
)

(instance recSignA of Feature
	(properties
		x 100
		y 26
		nsTop 24
		nsLeft 73
		nsBottom 29
		nsRight 128
		description {the recording sign}
		sightAngle 40
		lookStr {This sign is illuminated when Control Room A is recording.}
	)
)

(instance recSignB of Feature
	(properties
		x 162
		y 36
		nsTop 24
		nsLeft 144
		nsBottom 48
		nsRight 181
		description {the recording sign}
		sightAngle 40
		lookStr {This sign is illuminated when Control Room B is recording.}
	)
)

(instance movingSignA of Prop
	(properties
		x 75
		y 28
		description {the recording sign}
		lookStr {Control Room A's recording indicator lamp is presently on.}
		view 880
		loop 4
		priority 15
		signal fixPriOn
	)
)

(instance PChammer of Actor
	(properties
		x 90
		y 67
		description {P. C. Hammer}
		view 883
		detailLevel 3
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(TimePrint 880 4)
				(TimePrint 880 5)
			)
			(verbTalk
				(TimePrint 880 6)
			)
			(verbDo
				(TimePrint 880 7)
			)
			(verbZipper
				(TimePrint 880 8)
				(TimePrint 880 9 #at -1 185)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance mikeStand of PicView
	(properties
		x 175
		y 121
		description {the microphone stand}
		view 880
		signal ignrAct
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(TimePrint 880 10)
			)
			(verbDo
				(TimePrint 880 11)
				(TimePrint 880 12 #at -1 185)
			)
			(verbZipper
				(TimePrint 880 13)
				(TimePrint 880 14 #at -1 185)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance ActionsKRAP of Actions
	
	(method (doit)
		(return FALSE)
	)
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(verbDo
					(TimePrint 880 15)
					(return TRUE)
				)
				(verbZipper
					(TimePrint 880 16)
					(return TRUE)
				)
				(else
					(return FALSE)
				)
			)
		)
	)
)
