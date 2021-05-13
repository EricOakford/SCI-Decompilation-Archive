;;; Sierra Script 1.0 - (do not remove this comment)
(script# 330)
(include sci.sh)
(use Main)
(use LBRoom)
(use Inset)
(use Talker)
(use PolyPath)
(use Polygon)
(use Feature)
(use MoveFwd)
(use LoadMany)
(use Sound)
(use Motion)
(use Actor)
(use System)

(public
	rm330 0
	Laura 2
	Steve 12
)

(local
	local0 =  1
)
(instance rm330 of LBRoom
	(properties
		noun 7
		picture 330
		horizon 125
		north 335
		south 250
		vanishingX 400
		vanishingY 78
	)
	
	(method (init)
		(LoadMany 128 1410 1411 330 331 213)
		(LoadMany 132 332 330 40)
		(LoadMany 129 415 410)
		(ego
			normalize: (if (ego wearingGown?) 831 else 830)
			init:
		)
		(switch prevRoomNum
			(north
				(ego posn: 158 146 signal: (| (ego signal?) $4000))
				(if
					(not
						(if (and (== currentAct 2) (TriggerEvent 8))
							(not (Btst 133))
						)
					)
					(theMusic number: 330 flags: 1 loop: 1 play:)
				)
				(if
					(and
						(== currentAct 2)
						(TriggerEvent 8)
						(not (Btst 133))
					)
					(steve init: loop: 1 setCycle: Walk)
					(ego setScale: 179)
					(curRoom setScript: sKissAndHug)
				else
					(theGame handsOn:)
					(ego setScale: 179)
				)
			)
			(else 
				(theMusic number: 330 flags: 1 loop: 1 play:)
				(ego posn: 178 136)
				(ego setScale: 179)
				(if (ego wearingGown?)
					(taxi posn: 196 161 init: stopUpd:)
					(curRoom setScript: sTaxiLeave)
					(theGame handsOff:)
				else
					(theGame handsOn:)
				)
			)
		)
		(if (> currentAct 1)
			(Palette palSET_INTENSITY 0 255 60)
		)
		(super init:)
		(theMusic2 number: 333 flags: 1 loop: -1 play:)
		(self
			addObstacle:
				((Polygon new:)
					type: 3
					init:
						0
						143
						0
						169
						62
						162
						106
						158
						178
						152
						196
						144
						288
						140
						300
						137
						319
						137
						319
						127
						277
						128
						270
						134
						239
						134
						212
						116
						166
						116
						150
						116
						154
						131
						110
						140
						62
						144
						32
						139
						0
						141
					yourself:
				)
		)
		(larch init:)
		(lawn1 init:)
		(lawn2 init:)
		(car1 init:)
		(car2 init:)
		(clouds setOnMeCheck: 1 128 init:)
		(dome setOnMeCheck: 1 8 init:)
		(entrance setOnMeCheck: 1 32 init:)
		(bigWindow setOnMeCheck: 1 2 init:)
		(sidewalk setOnMeCheck: 1 4 init:)
		(steps setOnMeCheck: 1 16384 init:)
		(if (< currentAct 2) (taxi init: stopUpd:))
		(street setOnMeCheck: 1 64 init:)
		(fountain1 init: setCycle: Fwd)
		(fountain2 init: setCycle: Fwd)
	)
	
	(method (doit)
		(super doit:)
		(cond 
			(script)
			((IsObjectOnControl ego 256) (curRoom setScript: sHitEdgeScreen))
		)
	)
	
	(method (dispose)
		(carSound dispose:)
		(theMusic fade: 0 30 12 1)
		(theMusic2 fade: 0 30 12 1)
		(super dispose: &rest)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(if local0
					(messager say: 7 1 1)
					(= local0 0)
				else
					(messager say: 7 1 2)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance sHitEdgeScreen of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(messager say: 4 3 0 0 self 91)
			)
			(1
				(if (> (ego heading?) 180)
					(ego setHeading: 90)
				else
					(ego setHeading: 270)
				)
				(ego setMotion: MoveFwd 10 self)
			)
			(2
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sTaxiLeave of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(taxi setMotion: MoveTo 369 125 self)
			)
			(1
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sInTaxi of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: PolyPath 146 151 self)
			)
			(1
				(carSound play:)
				(= ticks 30)
			)
			(2
				(ego setMotion: MoveTo 146 163 self)
			)
			(3 (ego hide:) (= cycles 2))
			(4 (curRoom newRoom: 250))
		)
	)
)

(instance sKissAndHug of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: PolyPath 58 150 self)
			)
			(1
				(Face ego steve)
				(Face steve ego)
				(= seconds 4)
			)
			(2
				(theGame points: 1 133)
				(curRoom picture: 780 setInset: closeUp self)
			)
			(3
				(cast eachElementDo: #hide)
				(= cycles 2)
			)
			(4
				(curRoom drawPic: 330)
				(Palette palSET_INTENSITY 0 255 60)
				(cast eachElementDo: #show)
				(steve setLoop: 0)
				(ego setMotion: PolyPath 158 146 self)
			)
			(5
				(DrawPic 780 dpOPEN_FADEPALETTE)
				(cast eachElementDo: #hide)
				(= cycles 2)
			)
			(6
				(Palette palSET_INTENSITY 0 255 100)
				(curRoom newRoom: 350)
				(self dispose:)
			)
		)
	)
)

(instance sCloseUp of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 2))
			(1
				(theMusic number: 332 flags: 1 loop: -1 play:)
				(messager say: 6 0 0 0 self 330)
				(theMusic2 fade: 0 12 30 0)
			)
			(2
				(DrawPic 415 -32758)
				(theMusic number: 334 flags: 1 loop: 1 play:)
				(= seconds 4)
			)
			(3
				(DrawPic 410 -32758)
				(= ticks 30)
			)
			(4
				(messager say: 12 0 0 0 self 330)
			)
			(5
				(theMusic2 fade: 127 12 30 0)
				(closeUp dispose:)
				(self dispose:)
			)
		)
	)
)

(instance taxi of Actor
	(properties
		x 139
		y 146
		noun 5
		view 213
		cel 2
		priority 15
		signal $4010
		moveSpeed 0
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4 (curRoom setScript: sInTaxi))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance steve of Actor
	(properties
		x 156
		y 142
		view 331
		signal $4000
	)
	
	(method (doit)
		(= cel (ego cel?))
		(= x (- (ego x?) 2))
		(= y (- (ego y?) 4))
		(super doit: &rest)
	)
)

(instance fountain1 of Prop
	(properties
		x 77
		y 137
		noun 4
		view 330
		signal $4000
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(6 (messager say: 4 6 3))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance fountain2 of Prop
	(properties
		x 263
		y 126
		noun 4
		view 330
		loop 1
		signal $4000
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(6 (messager say: 4 6 3))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance steveLips of Prop
	(properties
		x 127
		y 57
		view 1410
		loop 4
		priority 15
		signal $4000
	)
)

(instance Laura of Talker
	(properties
		x 0
		y 0
		view 1411
		loop 3
		disposeWhenDone 0
		talkWidth 250
		back 15
		textX 15
		textY 120
	)
	
	(method (init)
		(= font userFont)
		(super init: lauraBust lauraEyes lauraMouth &rest)
	)
)

(instance lauraBust of Prop
	(properties
		view 1411
		loop 1
	)
)

(instance lauraEyes of Prop
	(properties
		nsTop 63
		nsLeft 188
		view 1411
		loop 2
		signal $4000
	)
)

(instance lauraMouth of Prop
	(properties
		nsTop 73
		nsLeft 178
		view 1411
		signal $4000
	)
)

(instance Steve of Talker
	(properties
		x 0
		y 0
		view 1410
		loop 3
		disposeWhenDone 0
		talkWidth 250
		back 15
		textX 15
		textY 105
	)
	
	(method (init)
		(= font userFont)
		(super init: steveBust steveEyes steveMouth &rest)
	)
)

(instance steveBust of Prop
	(properties
		view 1410
		loop 1
	)
)

(instance steveEyes of Prop
	(properties
		nsTop 49
		nsLeft 140
		view 1410
		loop 2
		signal $4000
	)
)

(instance steveMouth of Prop
	(properties
		nsTop 57
		nsLeft 127
		view 1410
		signal $4000
	)
)

(instance lawn1 of Feature
	(properties
		x 27
		y 130
		noun 8
		nsTop 127
		nsBottom 134
		nsRight 54
		sightAngle 40
	)
)

(instance lawn2 of Feature
	(properties
		x 297
		y 122
		noun 8
		nsTop 119
		nsLeft 275
		nsBottom 125
		nsRight 319
		sightAngle 40
	)
)

(instance car1 of Feature
	(properties
		y 3
		noun 2
		nsTop 148
		nsBottom 174
		nsRight 69
	)
)

(instance car2 of Feature
	(properties
		y 3
		noun 2
		nsTop 126
		nsLeft 204
		nsBottom 143
		nsRight 276
	)
)

(instance larch of Feature
	(properties
		y 3
		noun 3
		nsTop 78
		nsLeft 44
		nsBottom 110
		nsRight 92
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(6 (messager say: 3 6 3))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance clouds of Feature
	(properties
		y 3
		noun 11
	)
)

(instance dome of Feature
	(properties
		y 3
		noun 13
	)
)

(instance entrance of Feature
	(properties
		y 3
		noun 14
	)
)

(instance bigWindow of Feature
	(properties
		y 3
		noun 15
	)
)

(instance sidewalk of Feature
	(properties
		y 3
		noun 1
	)
)

(instance steps of Feature
	(properties
		y 3
		noun 9
	)
)

(instance street of Feature
	(properties
		y 3
		noun 10
	)
)

(instance closeUp of Inset
	(properties
		picture 410
		style $800a
		hideTheCast 1
	)
	
	(method (init)
		(super init: &rest)
		(self setScript: sCloseUp)
	)
)

(instance carSound of Sound
	(properties
		flags $0001
		number 40
	)
)
