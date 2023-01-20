;;; Sierra Script 1.0 - (do not remove this comment)
(script# 20)
(include sci.sh)
(use Main)
(use Intrface)
(use SQRoom)
(use Polygon)
(use Feature)
(use LoadMany)
(use Motion)
(use System)

(public
	rm20 0
)

(instance rm20 of SQRoom
	(properties
		lookStr {This is the north-central boundary of a massive skeletal structure surrounded by arid terra not-so-firma.}
		picture 20
		horizon 20
		north 38
		east 21
		south 23
		west 19
		walkOffTop 1
	)
	
	(method (init)
		(if (== currentFloor 2)
			(self
				addObstacle:
					((Polygon new:)
						type: 2
						init:
							0
							71
							64
							77
							126
							92
							319
							108
							319
							171
							316
							176
							293
							176
							288
							172
							276
							170
							277
							173
							251
							173
							250
							170
							212
							169
							206
							164
							193
							163
							174
							154
							130
							155
							135
							151
							108
							148
							101
							145
							86
							145
							86
							149
							66
							151
							58
							157
							33
							152
							0
							154
						yourself:
					)
			)
			(LoadMany 128 72 120)
			(LoadMany 132 410 406)
		else
			(self
				addObstacle:
					((Polygon new:)
						type: 2
						init:
							0
							0
							319
							0
							319
							92
							298
							88
							222
							87
							202
							86
							188
							82
							149
							79
							137
							72
							117
							70
							95
							60
							88
							63
							67
							62
							52
							60
							25
							60
							9
							51
							0
							54
						yourself:
					)
					((Polygon new:)
						type: 2
						init:
							0
							65
							60
							65
							116
							78
							137
							84
							159
							86
							187
							95
							214
							94
							235
							98
							309
							100
							319
							102
							319
							189
							0
							189
						yourself:
					)
			)
		)
		(switch prevRoomNum
			(east (= style 11) (HandsOn))
			(west (= style 12) (HandsOn))
			(south (= style 14) (HandsOn))
			(else  (= style 10))
		)
		(ego init:)
		(self setRegions: 704)
		(super init:)
		(if (and (!= currentFloor 1) (> (ego y?) 100))
			(hole init:)
		)
	)
)

(instance hole of Feature
	(properties
		x 46
		y 136
		description {hole}
		onMeCheck $4000
		approachX 56
		approachY 157
	)
	
	(method (init)
		(super init: &rest)
		(if (and (!= currentFloor 1) (> (ego y?) 100))
			(self approachVerbs: 3 1)
		)
	)
	
	(method (doVerb theVerb)
		(if (and (!= currentFloor 1) (> (ego y?) 100))
			(switch theVerb
				(2 (Print 20 0))
				(3
					(if spiderRoom
						(Print 20 1)
					else
						(curRoom setScript: enterHole)
					)
				)
				(11 (Print 20 2))
				(5 (Print 20 3))
				(12 (Print 20 4))
				(else 
					(super doVerb: theVerb &rest)
				)
			)
		else
			(Print 20 5)
		)
	)
)

(instance enterHole of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Print 20 6)
				(sounds eachElementDo: #fade)
				(HandsOff)
				(ego
					view: 72
					loop: 0
					cel: 0
					cycleSpeed: 16
					moveSpeed: 16
					setCycle: End
					setStep: 3 2
					setPri: 7
					setMotion: MoveTo 30 120 self
				)
			)
			(1
				(ego hide:)
				(soundFx number: 410 loop: 1 play: self)
			)
			(2 (= seconds 2))
			(3
				(ego
					show:
					view: 120
					cel: 0
					posn: 64 145
					setPri: -1
					cycleSpeed: 25
					setCycle: CT 4 1 self
				)
			)
			(4
				(soundFx number: 406 loop: 1 play:)
				(ego cel: 5 posn: 85 175)
				(= seconds 2)
			)
			(5 (EgoDead 943 0 0 20 7))
		)
	)
)
