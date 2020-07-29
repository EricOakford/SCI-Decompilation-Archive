;;; Sierra Script 1.0 - (do not remove this comment)
(script# 850)
(include game.sh)
(use Main)
(use LLRoom)
(use Door)
(use Polygon)
(use Feature)
(use LoadMany)
(use Sound)
(use Motion)
(use Invent)
(use Actor)
(use System)

(public
	rm850 0
)

(local
	local0
)
(instance rm850 of LLRoom
	(properties
		lookStr {John Krapper's bathroom lives up to expectations. Gold fixtures are scattered everywhere. There are even a pair of gold statuettes on either side of the "crapper."}
		picture 850
		east 840
	)
	
	(method (init)
		(LoadMany VIEW 850 851 842)
		(LoadMany SOUND 851 852 853 800 801 802)
		(HandsOn)
		(ego init: normalize: (if (Btst fCoveredInToner) 842 else 570))
		(if (Btst fCoveredInToner) (ego actions: ActionsKRAP))
		(ego posn: 270 140 setHeading: 270 edgeHit: 0)
		(super init:)
		(officeDoor init:)
		(sDoor init: approachVerbs: verbDo ignoreActors: stopUpd:)
		(shower init: approachVerbs: verbDo ignoreActors: stopUpd:)
		(showerSide init: approachVerbs: verbDo ignoreActors: stopUpd:)
		(toilet init: approachVerbs: verbDo verbZipper verbLook verbUse)
		(myWindow init:)
		(curtain1 init:)
		(curtain2 init:)
		(sink1 init:)
		(sink2 init:)
		(skylight init:)
		(rightStatue init:)
		(leftStatue init:)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: PBarredAccess
					init:
						0 0
						319 0
						319 123
						272 130
						247 111
						240 111
						240 128
						83 128
						77 112
						63 112
						52 121
						59 128
						59 138
						51 145
						32 152
						6 146
						4 186
						54 186
						61 177
						89 177
						112 165
						145 165
						139 177
						173 177
						172 165
						207 165
						217 177
						249 178
						256 187
						317 187
						314 162
						283 136
						318 131
						319 189
						0 189
					yourself:
				)
		)
		(mouseDownHandler add: self)
		(keyDownHandler add: self)
	)
	
	(method (dispose)
		(mouseDownHandler delete: self)
		(keyDownHandler delete: self)
		(ActionsKRAP dispose:)
		(super dispose:)
	)
)

(instance shower of Actor
	(properties
		x 25
		y 144
		description {the shower}
		sightAngle 40
		approachX 73
		approachY 139
		lookStr {What appears to be a lovely glass shower rests against the wall.}
		view 850
		priority 8
		signal (| ignrAct fixPriOn)
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(3
				(if (Btst fCoveredInToner)
					(ego setScript: TakeAShowerScript)
				else
					(TimePrint 850 0)
					(TimePrint 850 1 #at -1 185)
				)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance showerSide of Actor
	(properties
		x 26
		y 144
		description {the shower}
		sightAngle 40
		approachX 73
		approachY 139
		lookStr {Why, look! A lovely glass shower stands against the wall.}
		view 850
		cel 1
	)
	
	(method (doVerb theVerb theItem)
		(shower doVerb: theVerb theItem &rest)
	)
)

(instance officeDoor of Door
	(properties
		x 298
		y 136
		description {the office door}
		approachX 264
		approachY 136
		lookStr {This door leads back to John Krapper's office.}
		view 850
		loop 1
		priority 10
		signal (| ignrAct fixPriOn)
		entranceTo 840
		moveToX 306
		moveToY 127
		enterType 0
		exitType 0
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(ego ignoreControl: cBLUE)
				(super doVerb: theVerb theItem &rest)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
	
	(method (close)
		(ego observeControl: 2)
		(super close:)
	)
)

(instance sDoor of Actor
	(properties
		x 26
		y 144
		description {the shower}
		approachX 73
		approachY 139
		view 850
		loop 2
	)
	
	(method (doVerb theVerb theItem)
		(shower doVerb: theVerb theItem &rest)
	)
)

(instance toilet of Feature
	(properties
		x 155
		y 183
		nsTop 178
		nsLeft 134
		nsBottom 189
		nsRight 177
		description {Krapper's crapper}
		sightAngle 40
		approachX 161
		approachY 180
		lookStr {Made of hand-carved, gold-flecked marble with a gold-plated handle and ermine-covered seat, it's obvious to you this guy really knows his "K-RAP!"}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(TimePrint 850 2)
				(flushSound play:)
			)
			(verbZipper
				(TimePrint 850 2)
				(flushSound play:)
			)
			(verbLook
				(super doVerb: theVerb theItem &rest)
			)
			(verbUse
				(switch theItem
					(iPhotocopiedEvidence
						(TimePrint 850 3)
					)
					(else
						(TimePrint 850 4)
					)
				)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance myWindow of Feature
	(properties
		x 148
		y 80
		nsTop 46
		nsLeft 78
		nsBottom 114
		nsRight 219
		description {the window}
		sightAngle 40
		lookStr {A giant plate-glass window looks out over the lovely glow of downtown Philadelphia at night.}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance curtain1 of Feature
	(properties
		x 68
		y 79
		nsTop 48
		nsLeft 59
		nsBottom 111
		nsRight 78
		description {the curtains}
		sightAngle 40
		lookStr {These curtains are only for show; they do not close.}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance curtain2 of Feature
	(properties
		x 231
		y 77
		nsTop 43
		nsLeft 221
		nsBottom 112
		nsRight 242
		description {the curtains}
		sightAngle 40
		lookStr {These curtains are only for show; they do not close.}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance sink1 of Feature
	(properties
		x 117
		y 110
		nsTop 96
		nsLeft 91
		nsBottom 125
		nsRight 143
		description {the left sink}
		sightAngle 40
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(TimePrint 850 5)
			)
			(verbDo
				(if (Btst fCoveredInToner)
					(TimePrint 850 6)
				else
					(TimePrint 850 7)
				)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance sink2 of Feature
	(properties
		x 204
		y 110
		nsTop 96
		nsLeft 176
		nsBottom 124
		nsRight 233
		description {the right sink}
		sightAngle 40
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(sink1 doVerb: theVerb theItem &rest)
			)
			(verbDo
				(sink1 doVerb: theVerb theItem &rest)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance skylight of Feature
	(properties
		x 155
		y 14
		nsTop 4
		nsLeft 91
		nsBottom 25
		nsRight 219
		description {the skylight}
		sightAngle 40
		lookStr {The ceiling of John Krapper's bathroom is crowned by a lovely skylight.}
	)
)

(instance rightStatue of Feature
	(properties
		x 118
		y 145
		nsTop 127
		nsLeft 107
		nsBottom 163
		nsRight 129
		description {the statuette}
		lookStr {This statuette was presented to K-RAP Radio for excellence in broadcasting!}
	)
)

(instance leftStatue of Feature
	(properties
		x 197
		y 148
		nsTop 130
		nsLeft 187
		nsBottom 167
		nsRight 207
		description {the statuette}
		lookStr {This statuette was presented to K-RAP Radio for excellence in broadcasting!}
	)
)

(instance clothes of View
	(properties
		x 40
		y 141
		view 850
		cel 2
	)
)

(instance flushSound of Sound
	(properties
		number 851
		priority 11
	)
)

(instance runWaterSound of Sound
	(properties
		number 852
		priority 11
	)
)

(instance TakeAShowerScript of Script

	(method (changeState newState &tmp i invSave)
		(switch (= state newState)
			(0
				(HandsOff)
				(SolvePuzzle 7)
				(TimePrint 850 8)
				(sDoor setCycle: EndLoop self)
			)
			(1
				(ego setMotion: MoveTo 29 139 self)
				(shower startUpd:)
				(showerSide startUpd:)
			)
			(2
				(ego cycleSpeed: 12 view: 851 loop: 2 setCycle: EndLoop self)
			)
			(3
				(sDoor cel: 0)
				(ego setLoop: 3 setCycle: EndLoop self)
				(clothes init:)
			)
			(4
				(TimePrint 850 9)
				(theMusic2 number: 853 play: hold: 10)
				(sDoor setPri: 1 setMotion: MoveTo 26 226 self)
				(ego setPri: 0 setMotion: MoveTo 29 226 self moveSpeed: 6)
				(shower setPri: 1 setMotion: MoveTo 25 226 self)
				(showerSide setPri: 1 setMotion: MoveTo 26 226 self)
			)
			(5
				(= i 0)
				(while (< i (Inventory size?))
					(if ((= invSave (Inventory at: i)) ownedBy: 24)
						(invSave owner: 1000)
					)
					(++ i)
				)
				(TimePrint 850 10)
				(TimePrint 850 11)
				(curRoom newRoom: 860)
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
				(verbLook
					(if (== (ego view?) 842)
						(Say ego 850 12)
						(return TRUE)
					else
						(TimePrint 850 13)
						(return TRUE)
					)
				)
				(else
					(return FALSE)
				)
			)
		)
	)
)
