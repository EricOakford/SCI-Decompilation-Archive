;;; Sierra Script 1.0 - (do not remove this comment)
(script# 190)
(include game.sh)
(use Main)
(use LLRoom)
(use Door)
(use Polygon)
(use Feature)
(use Motion)
(use Invent)
(use Actor)
(use System)

(public
	rm190 0
)

(instance rm190 of LLRoom
	(properties
		lookStr {You are outside PPC's general headquarters, nestled in the smog beneath the Hollywood hills. Gawd, how you love being in show biz!}
		picture 190
		north 160
	)
	
	(method (init)
		(self setRegions: rgHollywood)
		(ego init: normalize: 552 setStep: 2 1)
		(HandsOn)
		(super init:)
		(if
			(and
				(ego has: iMichelleResume)
				(ego has: iLanaResume)
				(ego has: iChiChiResume)
				(ego has: iGoldCard)
			)
			(limo init: approachVerbs: verbDo)
		)
		(theMusic2 number: 193 setLoop: -1 play:)
		(fountain setCycle: Forward init: approachVerbs: verbDo verbZipper)
		(waves setCycle: Forward init: approachVerbs: verbDo)
		(statue init:)
		(logo init:)
		(HollywoodSign init:) ;EO: was sign, but it's the same name as a system procedure
		(tree init:)
		(buildings1 init:)
		(buildings2 init:)
		(windows1 init:)
		(windows2 init:)
		(doorATP init:)
		(debugFeature init:)
		(curRoom
			addObstacle:
				(if (cast contains: limo)
					((Polygon new:)
						type: PBarredAccess
						init:
							0 0
							179 0
							179 132
							76 132
							64 125
							5 125
							5 133
							61 133
							76 136
							65 143
							24 145
							4 150
							4 158
							147 158
							202 175
							218 187
							309 187
							309 167
							276 163
							273 156
							315 146
							315 122
							253 136
							243 143
							225 143
							216 134
							192 134
							192 0
							319 0
							319 189
							0 189
						yourself:
					)
				else
					((Polygon new:)
						type: PBarredAccess
						init:
							0 0
							179 0
							179 133
							71 133
							64 125
							5 125
							5 140
							23 140
							23 146
							5 150
							5 158
							151 158
							202 175
							218 187
							313 187
							309 167
							276 163
							273 156
							315 146
							315 122
							253 137
							243 143
							225 143
							216 134
							192 134
							192 0
							319 0
							319 189
							0 189
						yourself:
					)
				)
		)
		(StartTimer 2 1 self)
	)
	
	(method (cue)
		(door init: approachVerbs: verbDo)
	)
	
	(method (newRoom n)
		(if (== n 200)
			(theMusic fade:)
		else
			(theMusic fade: 127 25 10 0)
		)
		(theMusic2 fade:)
		(super newRoom: n)
	)
)

(instance sEnterLimo of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setHeading: 180 self)
			)
			(1
				(ego moveSpeed: 10 setMotion: MoveTo (ego x?) 137 self)
				(= ticks 10)
			)
			(2
				(ego hide:)
				(theMusic2 number: 191 setLoop: 1 play:)
				(= ticks 50)
			)
			(3)
			(4
				(theMusic2 number: 192 setLoop: 1 play:)
				(= ticks 100)
			)
			(5 (= ticks 20))
			(6 (curRoom newRoom: 200))
		)
	)
)

(instance limo of View
	(properties
		x 10
		y 1138
		z 1000
		description {your limousine}
		approachX 29
		approachY 136
		lookStr {At last! You finally get to ride in a real limo.}
		view 190
		priority 9
		signal fixPriOn
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbDo
				(curRoom setScript: sEnterLimo)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance door of Door
	(properties
		x 175
		y 132
		heading 180
		description {the front door of PornProdCorp}
		approachX 185
		approachY 134
		lookStr {This door leads back into the PPC lobby.}
		view 192
		loop 2
		entranceTo 160
		moveToX 185
		moveToY 128
		enterType 1
		exitType 0
	)
)

(instance doorATP of PicView
	(properties
		x 185
		y 130
		view 190
		loop 1
	)
)

(instance fountain of Prop
	(properties
		x 92
		y 160
		description {the fountain}
		sightAngle 40
		lookStr {Passing the tinkling water makes you want to pass tinkling water.}
		view 192
		priority 15
		signal fixPriOn
		detailLevel 2
	)
	
	(method (doVerb theVerb theItem &tmp [str 30])
		(switch theVerb
			(verbDo
				(TimePrint 190 0)
			)
			(verbUse
				(Format @str 190 1 ((Inventory at: theItem) description?))
				(TimePrint @str)
			)
			(verbZipper
				(TimePrint 190 0)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance waves of Prop
	(properties
		x 89
		y 189
		sightAngle 40
		view 192
		loop 1
		priority 15
		signal fixPriOn
		detailLevel 3
	)
	
	(method (doVerb theVerb theItem)
		(fountain doVerb: theVerb theItem &rest)
	)
)

(instance statue of Feature
	(properties
		x 95
		y 171
		z 75
		nsTop 64
		nsLeft 73
		nsBottom 129
		nsRight 117
		description {Chesty}
		sightAngle 40
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(SolvePuzzle 1 90)
				(TimePrint 190 2)
			)
			(verbDo
				(TimePrint 190 3)
			)
			(verbTalk
				(TimePrint 190 4)
			)
			(verbZipper
				(TimePrint 190 5)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance logo of Feature
	(properties
		x 193
		y 20
		nsTop 3
		nsLeft 180
		nsBottom 37
		nsRight 207
		description {that PornProdCorp logo}
		sightAngle 40
		lookStr {You presume PPC chose that logo because the tongue is the universal symbol of good taste.}
	)
)

(instance tree of Feature
	(properties
		x 263
		y 169
		z 100
		nsTop 4
		nsLeft 235
		nsBottom 134
		nsRight 292
		description {the palm tree}
		sightAngle 40
		onMeCheck cYELLOW
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(TimePrint 190 6)
				(TimePrint 190 7
					#at -1 185
				)
			)
			(verbDo 
				(TimePrint 190 8)
			)
			(verbTalk
				(TimePrint 190 9)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance HollywoodSign of Feature
	(properties
		x 92
		y 80
		nsBottom 46
		nsRight 61
		description {the Hollywood sign}
		sightAngle 40
		lookStr {You love working here because everyone in this city tries so hard to treat you fairly.}
	)
)

(instance buildings1 of Feature
	(properties
		x 28
		y 92
		nsTop 71
		nsBottom 113
		nsRight 57
		description {the buildings}
		sightAngle 40
		lookStr {You wonder how many disco parties are going on in those office buildings over there!}
	)
)

(instance buildings2 of Feature
	(properties
		x 299
		y 87
		nsTop 69
		nsLeft 279
		nsBottom 105
		nsRight 319
		description {the buildings}
		sightAngle 40
		lookStr {You wonder how many disco parties are going on in those office buildings over there!}
	)
)

(instance windows1 of Feature
	(properties
		x 105
		y 106
		nsTop 97
		nsLeft 81
		nsBottom 116
		nsRight 129
		description {the windows}
		sightAngle 40
		lookStr {You just love voyeurism, don't you?}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(TimePrint 190 10)
			)
			(verbTalk
				(TimePrint 190 11)
				(TimePrint 190 12
					#at -1 185
				)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance windows2 of Feature
	(properties
		x 238
		y 100
		nsTop 89
		nsLeft 216
		nsBottom 112
		nsRight 260
		description {the windows}
		sightAngle 40
		lookStr {You just love voyeurism, don't you?}
	)
	
	(method (doVerb theVerb theItem)
		(windows1 doVerb: theVerb theItem &rest)
	)
)

(instance debugFeature of Feature
	(properties
		x 29
		y 33
		nsTop 31
		nsLeft 27
		nsBottom 35
		nsRight 31
		sightAngle 40
	)
	
	(method (init)
		(mouseDownHandler addToEnd: self)
		(super init:)
	)
	
	(method (dispose)
		(mouseDownHandler delete: self)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(if
			(and
				(== (event type?) mouseDown)
				(== theCursor 1)
				(== (event modifiers?) (| ctrlDown altDown))
			)
			(= debugging TRUE)
		else
			(curRoom handleEvent: event)
		)
	)
)
