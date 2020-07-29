;;; Sierra Script 1.0 - (do not remove this comment)
(script# 780)
(include game.sh)
(use Main)
(use LLRoom)
(use Polygon)
(use Feature)
(use MoveFwd)
(use Motion)
(use Actor)
(use System)

(public
	rm780 0
)

(instance rm780 of LLRoom
	(properties
		lookStr {Now you've done it! You're swimming along under the beautiful Tramp Boardwalk, having a wonderful time. The question is: how far from shore are you?}
		picture 780
		north 760
		west 700
	)
	
	(method (init)
		(ego
			init:
			normalize: 781
			setCycle: Forward
			actions: swimActions
		)
		(if (!= prevRoomNum 700)
			(HandsOn)
			(ego posn: 160 175 setHeading: 0 edgeHit: 0)
		)
		(super init:)
		(sky init:)
		(boardwalk init:)
		(post1 init:)
		(post2 init:)
		(post3 init:)
		(post4 init:)
		(wave1 setCycle: Forward init:)
		(wave2 setCycle: Forward init:)
		(wave3 setCycle: Forward init:)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: PBarredAccess
					init:
						0 0
						319 0
						319 155
						175 155
						175 158
						115 158
						115 155
						0 155
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init:
						8 180
						8 166
						157 166
						157 180
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init:
						162 180
						162 166
						242 166
						242 180
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init:
						266 180
						266 166
						319 166
						319 180
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init:
						0 189
						0 187
						319 187
						319 189
					yourself:
				)
		)
	)
	
	(method (doit)
		(super doit:)
		(cond 
			(script)
			((IsObjectOnControl ego cGREEN)
				(self setScript: sScroll 0 2)
			)
			((and (IsObjectOnControl ego cCYAN) global159)
				(self setScript: sScroll 0 4)
			)
		)
	)
)

(instance sScroll of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: MoveFwd 40 self)
			)
			(1
				(if (== register 4)
					(-- global159)
					(ego x: (+ (ego x?) 320))
					(curRoom drawPic: (curRoom picture?) 11)
				else
					(++ global159)
					(ego x: (- (ego x?) 320))
					(curRoom drawPic: (curRoom picture?) 12)
				)
				(= cycles 1)
			)
			(2 (HandsOn) (self dispose:))
		)
	)
)

(instance wave1 of Prop
	(properties
		x 146
		y 145
		view 780
		priority 1
		signal (| ignrAct fixPriOn)
		detailLevel 3
	)
	
	(method (doVerb theVerb theItem)
		(curRoom doVerb: theVerb theItem)
	)
)

(instance wave2 of Prop
	(properties
		x 115
		y 180
		view 780
		loop 1
		priority 1
		signal (| ignrAct fixPriOn)
		detailLevel 3
	)
	
	(method (doVerb theVerb theItem)
		(curRoom doVerb: theVerb theItem)
	)
)

(instance wave3 of Prop
	(properties
		x 275
		y 179
		view 780
		loop 2
		priority 1
		signal (| ignrAct fixPriOn)
		detailLevel 3
	)
	
	(method (doVerb theVerb theItem)
		(curRoom doVerb: theVerb theItem)
	)
)

(instance sky of Feature
	(properties
		x 159
		y 176
		z 171
		nsBottom 11
		nsRight 319
		description {the sky}
		sightAngle 40
		lookStr {Looking at the sky, you remember a better time, just a few minutes ago in fact, when you were much closer to the sky than you are right now.}
	)
)

(instance boardwalk of Feature
	(properties
		x 159
		y 176
		z 122
		nsTop 12
		nsBottom 96
		nsRight 319
		description {the boardwalk}
		sightAngle 40
		lookStr {You have a strange sensation--almost like you're looking up someone's skirt!}
	)
)

(instance post1 of Feature
	(properties
		x 44
		y 134
		nsTop 96
		nsLeft 26
		nsBottom 173
		nsRight 62
		description {the post}
		sightAngle 40
		lookStr {These posts look much taller than they did from above.}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(TimePrint 780 0)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance post2 of Feature
	(properties
		x 118
		y 98
		nsTop 21
		nsLeft 104
		nsBottom 175
		nsRight 132
		description {the post}
		sightAngle 40
	)
	
	(method (doVerb)
		(post1 doVerb: &rest)
	)
)

(instance post3 of Feature
	(properties
		x 203
		y 99
		nsTop 21
		nsLeft 190
		nsBottom 177
		nsRight 216
		description {the post}
		sightAngle 40
	)
	
	(method (doVerb)
		(post1 doVerb: &rest)
	)
)

(instance post4 of Feature
	(properties
		x 303
		y 101
		nsTop 24
		nsLeft 287
		nsBottom 179
		nsRight 319
		description {the post}
		sightAngle 40
	)
	
	(method (doVerb)
		(post1 doVerb: &rest)
	)
)

(instance swimActions of Actions
	(properties)
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(verbLook
					(TimePrint 780 1)
				)
				(verbDo
					(TimePrint 780 2)
				)
				(else
					(return FALSE)
				)
			)
		)
	)
)
