;;; Sierra Script 1.0 - (do not remove this comment)
(script# 24)
(include sci.sh)
(use Main)
(use Intrface)
(use SQRoom)
(use Polygon)
(use Feature)
(use Motion)
(use User)
(use System)

(public
	rm24 0
)

(instance rm24 of SQRoom
	(properties
		lookStr {A section of vertebra, near where the head used to be attached, extends north and south. To the east is the ominous-looking skull.}
		picture 24
		horizon 20
		north 21
		east 38
		south 27
		west 23
		walkOffTop 1
	)
	
	(method (init)
		(if (== currentFloor 2)
			(self
				addObstacle:
					((Polygon new:)
						type: 2
						init:
							319
							127
							194
							127
							136
							128
							152
							115
							209
							114
							272
							107
							257
							100
							224
							106
							203
							105
							160
							94
							129
							94
							113
							94
							134
							81
							160
							81
							199
							61
							277
							54
							319
							76
						yourself:
					)
					((Polygon new:)
						type: 2
						init: 0 189 0 55 37 62 128 160 126 189
						yourself:
					)
			)
		else
			(self
				addObstacle:
					((Polygon new:)
						type: 2
						init:
							93
							189
							86
							173
							61
							148
							50
							130
							58
							128
							53
							121
							37
							120
							14
							99
							13
							88
							0
							76
							0
							0
							318
							0
							319
							189
						yourself:
					)
					((Polygon new:)
						type: 2
						init: 0 102 36 143 52 170 58 189 0 189
						yourself:
					)
			)
		)
		(switch prevRoomNum
			(west
				(= style 12)
				(HandsOn)
				(ego init:)
			)
			(south
				(= style 14)
				(HandsOn)
				(cond 
					((and (< 113 (ego x?)) (< (ego x?) 131)) (ego init: x: 131))
					((and (< 90 (ego x?)) (< (ego x?) 110)) (ego init: x: 89))
					(else (ego init:))
				)
			)
			(north
				(= style 13)
				(HandsOn)
				(ego init:)
			)
			(east
				(= style 8)
				(HandsOn)
				(ego init:)
			)
			(else 
				(= style 10)
				(ego init:)
				(self setScript: outOfCave)
			)
		)
		(teeth init:)
		(skull init:)
		(self setRegions: 704)
		(super init:)
	)
	
	(method (doit)
		(cond 
			(script 0)
			((& (ego onControl: 1) $4000) (self newRoom: 28))
		)
		(super doit:)
	)
)

(instance outOfCave of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego posn: 240 112 setMotion: MoveTo 153 108 self)
			)
			(1 (HandsOn) (self dispose:))
		)
	)
)

(instance teeth of Feature
	(properties
		description {teeth}
		onMeCheck $2000
		lookStr {No doubt about it. Those are some serious teeth. It could've used a little dental work - maybe some braces even. It's just a good thing you never had the opportunity to be on the business end of these things when it was still a living being.}
	)
	
	(method (doVerb theVerb)
		(= x ((User curEvent?) x?))
		(= y ((User curEvent?) y?))
		(switch theVerb
			(3 (Print 24 0))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance skull of Feature
	(properties
		description {skull}
		onMeCheck $1000
		lookStr {This must be the skull previously attached to the rest of the mammoth bones partially assembled here.}
	)
	
	(method (doVerb theVerb)
		(= x ((User curEvent?) x?))
		(= y ((User curEvent?) y?))
		(switch theVerb
			(3 (Print 24 1))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)
