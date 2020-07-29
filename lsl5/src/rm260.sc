;;; Sierra Script 1.0 - (do not remove this comment)
(script# 260)
(include game.sh)
(use Main)
(use LLRoom)
(use Polygon)
(use Feature)
(use LoadMany)
(use Sound)
(use Motion)
(use Actor)
(use System)

(public
	rm260 0
)

(local
	batteryCharging
	slotNum
)
(instance rm260 of LLRoom
	(properties
		lookStr {You are in the west wing of the lavish AeroDork Airline terminal building.}
		picture 260
		east 270
	)
	
	(method (init)
		(ego init: illegalBits: cWHITE normalize:)
		(switch prevRoomNum
			(east (= style SCROLLRIGHT))
			(else 
				(ego posn: 292 134 setHeading: 270 edgeHit: 0)
			)
		)
		(super init:)
		(LoadMany VIEW 161 162 263 264 560 265)
		(switch currentCity
			(LOS_ANGELES
				(ad1 loop: 0 cel: 0 init:)
				(ad2 loop: 0 cel: 1 init:)
				(ad3 loop: 0 cel: 2 init:)
				(ad4 loop: 0 cel: 3 init:)
			)
			(NEW_YORK
				(ad1 loop: 1 cel: 0 init:)
				(ad2 loop: 1 cel: 1 init:)
				(ad3 loop: 1 cel: 2 init:)
				(ad4 loop: 1 cel: 3 init:)
			)
			(ATLANTIC_CITY
				(slots init:)
				(slot1 init: approachVerbs: verbDo)
				(slot2 init: approachVerbs: verbDo)
				(slot3 init: approachVerbs: verbDo)
				(slot4 init: approachVerbs: verbDo)
				(handle1
					init:
					approachVerbs: verbDo
					approachX: (slot1 approachX?)
					approachY: (slot1 approachY?)
				)
				(handle2
					init:
					approachVerbs: verbDo
					approachX: (slot2 approachX?)
					approachY: (slot2 approachY?)
				)
				(handle3
					init:
					approachVerbs: verbDo
					approachX: (slot3 approachX?)
					approachY: (slot3 approachY?)
				)
				(handle4
					init:
					approachVerbs: verbDo
					approachX: (slot4 approachX?)
					approachY: (slot4 approachY?)
				)
				(ad1 loop: 2 cel: 0 init:)
				(ad2 loop: 2 cel: 1 init:)
				(ad3 loop: 2 cel: 2 init:)
				(ad4 loop: 2 cel: 3 init:)
			)
			(MIAMI
				(cigaretteMachine init: approachVerbs: verbDo)
				(ad1 loop: 3 cel: 0 init:)
				(ad2 loop: 3 cel: 1 init:)
				(ad3 loop: 3 cel: 2 init:)
				(ad4 loop: 3 cel: 3 init:)
			)
		)
		(addToPics doit:)
		(switch currentCity
			(MIAMI
				(self
					addObstacle:
						((Polygon new:)
							type: PBarredAccess
							init:
								0 0
								319 0
								319 112
								306 112
								303 119
								280 122
								267 120
								259 113
								55 114
								50 119
								97 119
								103 126
								98 131
								66 131
								51 149
								19 149
								5 164
								5 187
								319 187
								319 189
								0 189
							yourself:
						)
				)
			)
			(ATLANTIC_CITY
				(self
					addObstacle:
						((Polygon new:)
							type: PBarredAccess
							init:
								0 0
								319 0
								319 112
								306 112
								303 119
								280 122
								267 120
								253 113
								223 113
								223 120
								100 123
								94 121
								85 119
								82 114
								53 113
								4 162
								7 187
								319 187
								319 189
								0 189
							yourself:
						)
						((Polygon new:)
							type: PBarredAccess
							init:
								73 119
								81 119
								97 123
								101 130
								54 130
								50 125
								52 119
							yourself:
						)
				)
			)
			(else 
				(self
					addObstacle:
						((Polygon new:)
							type: PBarredAccess
							init:
								0 0
								319 0
								319 112
								306 112
								303 119
								280 122
								267 120
								259 113
								54 113
								4 164
								4 187
								319 187
								319 189
								0 189
							yourself:
						)
						((Polygon new:)
							type: PBarredAccess
							init:
								95 120
								105 122
								105 126
								89 130
								63 130
								50 126
								51 122
								62 120
							yourself:
						)
				)
			)
		)
		(self
			addObstacle:
				((Polygon new:)
					type: PBarredAccess
					init:
						108 180
						128 154
						279 154
						301 181
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init:
						107 157
						113 160
						114 165
						107 171
						93 171
						80 170
						75 162
						85 157
					yourself:
				)
		)
		(ashtray init:)
		(chairs init:)
		(post init:)
		(plant init:)
		(plantSouth init:)
		(door init:)
		(outlet init: approachVerbs: verbUse verbDo)
		(HandsOn)
		(StartTimer (Random 40 120) 0 self)
	)
	
	(method (doit)
		(super doit:)
		(if
			(and
				(ego mover?)
				(== (ego view?) 550)
				(< (ego loop?) 8)
			)
			(switch (ego cel?)
				(1
					(if (!= (stepSound number?) 260)
						(stepSound number: 260 play:)
					)
				)
				(4
					(if (!= (stepSound number?) 261)
						(stepSound number: 261 play:)
					)
				)
			)
		)
	)
	
	(method (doVerb theVerb &tmp [str 100])
		(switch theVerb
			(verbLook
				(Format @str 260 0
					(switch currentCity
						(LOS_ANGELES {Los Angeles})
						(NEW_YORK {New York})
						(ATLANTIC_CITY {Atlantic City})
						(MIAMI {Miami})
					)
				)
				(TimePrint @str)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
	
	(method (cue)
		(announcement
			number: (Random 273 275)
			flags: 1
			setLoop: 1
			play:
		)
		(StartTimer (Random 20 40) 2 self)
	)
	
	(method (newRoom n)
		(globalTimer dispose: delete:)
		(super newRoom: n)
	)
)

(instance cigaretteMachine of PicView
	(properties
		x 39
		y 142
		description {the cigarette machine}
		approachX 64
		approachY 142
		view 265
		priority 9
		signal (| ignrAct fixPriOn)
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(TimePrint 260 1)
			)
			(verbDo
				(HandsOff)
				(curRoom setScript: sGetChange)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance chairs of Feature
	(properties
		x 203
		y 152
		nsTop 131
		nsLeft 117
		nsBottom 173
		nsRight 289
		description {the chairs}
		sightAngle 40
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(TimePrint 260 2)
			)
			(verbDo
				(TimePrint 260 3)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance post of Feature
	(properties
		x 75
		y 63
		nsLeft 59
		nsBottom 126
		nsRight 91
		description {the column}
		sightAngle 40
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(TimePrint 260 4)
			)
			(verbDo
				(TimePrint 260 5)
			)
			(verbTalk
				(Say ego 260 6)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance plant of Feature
	(properties
		x 285
		y 90
		nsTop 70
		nsLeft 259
		nsBottom 111
		nsRight 312
		description {the plant}
		sightAngle 40
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(TimePrint 260 7)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance door of Feature
	(properties
		x 227
		y 75
		nsTop 43
		nsLeft 205
		nsBottom 108
		nsRight 249
		description {the door}
		sightAngle 40
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(TimePrint 260 8)
			)
			(verbDo
				(TimePrint 260 9)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance sGetChange of Script
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				(Load VIEW 161)
				(ego setHeading: 270 self)
			)
			(1 (= ticks 20))
			(2
				(ego
					view: 161
					setLoop: 0
					setCel: 0
					cycleSpeed: 8
					setCycle: CycleTo 2 1 self
				)
			)
			(3 (= ticks 30))
			(4 (ego setCycle: BegLoop self))
			(5
				(TimePrint 260 10)
				(if
					(and
						(not (Btst fGotChange))
						(not (Btst fAfterMiami))
						(not (ego has: iChange))
					)
					(ego get: iChange)
					(SolvePuzzle 5 fGotChange)
					(TimePrint 260 11)
					(= numQuarters 2)
				else
					(TimePrint 260 12)
				)
				(ego setLoop: 1 normalize:)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance outlet of Feature
	(properties
		x 5
		y 161
		z 30
		nsTop 118
		nsBottom 143
		nsRight 16
		description {the outlet}
		sightAngle 40
		approachX 32
		approachY 145
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(curRoom setScript: (ScriptID CHARGER 2) 0 1)
			)
			(verbUse
				(if (== theItem iCharger)
					(curRoom setScript: (ScriptID 22 0))
				else
					(TimePrint 260 13)
				)
			)
			(verbLook
				(if batteryCharging
					(TimePrint 260 14)
				else
					(TimePrint 260 15)
				)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance plantSouth of Feature
	(properties
		x 34
		y 173
		nsTop 158
		nsBottom 189
		nsRight 69
		description {the plant}
		sightAngle 40
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(TimePrint 260 7)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance camera of View
	(properties
		x 27
		y 146
		view 161
		loop 4
		signal ignrAct
	)
)

(instance ad1 of PicView
	(properties
		x 11
		y 22
		description {the sign}
		view 263
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(switch currentCity
					(LOS_ANGELES (TimePrint 260 16))
					(NEW_YORK (TimePrint 260 17))
					(ATLANTIC_CITY (TimePrint 260 18))
					(MIAMI (TimePrint 260 19))
				)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance ad2 of PicView
	(properties
		x 146
		y 9
		description {the sign}
		view 263
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(switch currentCity
					(LOS_ANGELES (TimePrint 260 20))
					(NEW_YORK (TimePrint 260 21))
					(ATLANTIC_CITY (TimePrint 260 22))
					(MIAMI (TimePrint 260 23))
				)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance ad3 of PicView
	(properties
		x 235
		y 10
		description {the sign}
		view 263
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(switch currentCity
					(LOS_ANGELES
						(TimePrint 260 24)
					)
					(NEW_YORK
						(SolvePuzzle 1 fLearnNewYorkLimoNumber)
						(TimePrint 260 25)
					)
					(ATLANTIC_CITY
						(TimePrint 260 26)
					)
					(MIAMI
						(TimePrint 260 27)
					)
				)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance ad4 of PicView
	(properties
		x 311
		y 13
		description {the sign}
		view 263
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(switch currentCity
					(LOS_ANGELES (TimePrint 260 28))
					(NEW_YORK (TimePrint 260 29))
					(ATLANTIC_CITY (TimePrint 260 30))
					(MIAMI (TimePrint 260 31))
				)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance ashtray of Feature
	(properties
		x 94
		y 149
		nsTop 133
		nsLeft 85
		nsBottom 165
		nsRight 104
		description {the ashtray}
		sightAngle 40
	)
	
	(method (doVerb theVerb theItem &tmp [temp0 30])
		(switch theVerb
			(verbLook
				(TimePrint 260 32)
			)
			(verbDo
				(TimePrint 260 33)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance stepSound of Sound)

(instance announcement of Sound)

(instance handle1 of PicView
	(properties
		x 122
		y 100
		z 14
		description {the slot machine}
		view 264
		loop 1
		priority 0
		signal fixPriOn
	)
	
	(method (doVerb)
		(= slotNum 1)
		(slot1 doVerb: &rest)
	)
)

(instance handle2 of Prop
	(properties
		x 151
		y 86
		description {the slot machine}
		view 264
		loop 1
		priority 11
		signal ignrAct
	)
	
	(method (doVerb)
		(= slotNum 2)
		(slot1 doVerb: &rest)
	)
)

(instance handle3 of PicView
	(properties
		x 180
		y 86
		description {the slot machine}
		view 264
		loop 1
		priority 6
		signal fixPriOn
	)
	
	(method (doVerb)
		(= slotNum 3)
		(slot1 doVerb: &rest)
	)
)

(instance handle4 of PicView
	(properties
		x 207
		y 85
		description {the slot machine}
		view 264
		loop 1
	)
	
	(method (doVerb)
		(= slotNum 4)
		(slot1 doVerb: &rest)
	)
)

(instance slots of PicView
	(properties
		x 166
		y 22
		z -100
		description {the slot machine}
		view 264
		priority 4
		signal $4010
	)
	
	(method (onMe)
		(return 0)
	)
)

(instance slot1 of Feature
	(properties
		x 112
		y 99
		nsTop 77
		nsLeft 100
		nsBottom 121
		nsRight 124
		description {the slot machine}
		sightAngle 40
		approachX 117
		approachY 120
	)
	
	(method (doVerb theVerb theItem)
		(if (not slotNum)
			(= slotNum 1)
		)
		(switch theVerb
			(verbLook
				(TimePrint 260 34)
			)
			(verbDo
				(curRoom setScript: sSlots)
			)
			(verbUse
				(if (== theItem iChange)
					(TimePrint 260 35)
				else
					(super doVerb: theVerb theItem &rest)
				)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance slot2 of Feature
	(properties
		x 142
		y 98
		nsTop 77
		nsLeft 128
		nsBottom 120
		nsRight 156
		description {the slot machine}
		sightAngle 40
		approachX 146
		approachY 122
	)
	
	(method (doVerb)
		(= slotNum 2)
		(slot1 doVerb: &rest)
	)
)

(instance slot3 of Feature
	(properties
		x 172
		y 99
		nsTop 77
		nsLeft 160
		nsBottom 121
		nsRight 184
		description {the slot machine}
		sightAngle 40
		approachX 176
		approachY 122
	)
	
	(method (doVerb)
		(= slotNum 3)
		(slot1 doVerb: &rest)
	)
)

(instance slot4 of Feature
	(properties
		x 199
		y 98
		nsTop 76
		nsLeft 187
		nsBottom 121
		nsRight 211
		description {the slot machine}
		sightAngle 40
		approachX 201
		approachY 122
	)
	
	(method (doVerb)
		(= slotNum 4)
		(slot1 doVerb: &rest)
	)
)

(instance sSlots of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				(HandsOff)
				(= register 0)
				(ego setLoop: 3)
				(= cycles 2)
			)
			(1
				(ego
					view: 261
					setLoop: 0
					cycleSpeed: 12
					setCel: 0
					setCycle: CycleTo 3 1 self
				)
			)
			(2
				(if (and (not (Btst 36)) (== slotNum 2))
					(handle2 setCel: 255 cycleSpeed: 8 hide:)
					(ego setCycle: EndLoop self)
				else
					(= ticks 90)
				)
			)
			(3
				(if (and (not (Btst fPlayedSlots)) (== slotNum 2))
					(handle2 show: setCycle: BegLoop)
					(theMusic2 number: 262 loop: 1 play: self)
				else
					(ego setCycle: BegLoop self)
				)
			)
			(4
				(cond 
					((or (!= slotNum 2) (Btst fPlayedSlots))
						(TimePrint 260 36 #at -1 185 #dispose self)
					)
					((not (Btst fPlayedSlots))
						(theMusic number: 263 loop: 1 play: self)
						(= register 1)
					)
					(else (= ticks 10))
				)
			)
			(5 (= ticks 30))
			(6
				(theMusic stop:)
				(if (and register (not (Btst fPlayedSlots)))
					(SolvePuzzle 5 fPlayedSlots)
					(TimePrint 260 37)
					(ego get: iChange)
					(= numQuarters 1)
				)
				(HandsOn)
				(ego setLoop: 3 normalize:)
				(= slotNum 0)
				(self dispose:)
			)
		)
	)
)
