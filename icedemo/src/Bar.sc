;;; Sierra Script 1.0 - (do not remove this comment)
(script# BAR)
(include game.sh)
(use Main)
(use Intrface)
(use LoadMany)
(use Follow)
(use Extra)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	hotelBar 0
)

(instance hotelBar of Room
	(properties
		picture 11
		style WIPERIGHT
	)
	
	(method (init)
		(LoadMany SCRIPT FOLLOW EXTRA)
		(LoadMany VIEW 206 411 212 11 211 511 611 611 811)
		(Load SOUND 6)
		(super init:)
		(ladyAtBar init:)
		(bartender init:)
		(wife init:)
		(localAtBar init:)
		(stoolView init:)
		(lush init:)
		((Clone localAtBar) loop: 9 x: 201 y: 108 init:)
		(ego
			illegalBits: 0
			view: 206
			posn: 182 113
			illegalBits: cWHITE
			init:
			setScript: goDance
		)
		(agent init:)
		(addToPics
			add:
				primoPic
				tablePic
				chairPic
				ladyAtBarAss
				manAtBar
				hChairPic
				mansFriend
				manInBar
				plantPic
				centerPiece
				egosChair
				((Clone centerPiece)
					x: 200
					y: 155
					z: 23
					priority: 11
					yourself:
				)
				((Clone centerPiece)
					x: 264
					y: 155
					priority: 15
					yourself:
				)
			eachElementDo: #init
			doit:
		)
		(globalSound number: (SoundFX 6) loop: 1 play:)
	)
)

(instance agent of Actor
	(properties
		y 169
		x 288
		z 15
		view 211
		loop 1
		cel 2
		priority 15
		signal (| ignrAct fixPriOn stopUpdOn)
		illegalBits $0000
	)
)

(instance egosChair of PicView
	(properties
		y 171
		x 231
		z 1
		view 11
		loop 6
	)
)

(instance agentsCup of View
	(properties
		y 152
		x 283
		view 11
		cel 4
		priority 15
		signal (| ignrAct staticView fixPriOn stopUpdOn)
	)
)

(instance Champagne of View
	(properties
		y 152
		x 273
		view 11
		cel 5
		priority 15
		signal (| ignrAct staticView fixPriOn stopUpdOn)
	)
)

(instance goDance of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: MoveTo 273 162 self)
			)
			(1
				(Print 11 0 #at -1 30 #dispose)
				(agent ignoreControl: cWHITE setPri: -1 setCycle: BegLoop)
				(lush setCycle: EndLoop self)
			)
			(2
				(ego setMotion: MoveTo 263 158 self)
				(agent
					view: 212
					setCycle: Walk
					setLoop: -1
					z: 0
					setMotion: MoveTo 267 158 self
				)
			)
			(3
				(ego setMotion: MoveTo 125 158 self)
				(agent setMotion: Follow ego setPri: -1)
			)
			(4
				(DisposeScript EXTRA)
				(DisposeScript FOLLOW)
				(curRoom newRoom: 199)
			)
		)
	)
)

(instance lush of Actor
	(properties
		y 106
		x 145
		view 511
		loop 7
		signal stopUpdOn
		illegalBits $0000
	)
)

(instance bartenderBubble of View
	(properties
		y 66
		x 162
		view 611
		loop 2
		priority 15
		signal (| staticView fixPriOn stopUpdOn)
	)
)

(instance bartender of Actor
	(properties
		y 66
		x 193
		view 411
		loop 1
		signal (| ignrAct stopUpdOn)
		illegalBits $0000
	)
)

(instance mansFriend of PicView
	(properties
		y 179
		x 233
		view 11
		loop 9
		priority 15
	)
)

(instance wife of Extra
	(properties
		y 160
		x 230
		z 10
		view 11
		loop 3
		priority 10
		signal (| anExtra fixPriOn)
		cycleType 2
		hesitation 15
		minPause 55
		maxPause 200
		minCycles 1
		maxCycles 1
	)
)

(instance manInBar of PicView
	(properties
		y 181
		x 176
		view 11
		loop 4
	)
)

(instance localAtBar of Extra
	(properties
		y 120
		x 225
		view 511
		loop 8
		cycleType 2
		hesitation 15
		minPause 80
		maxPause 200
		minCycles 1
		maxCycles 1
	)
)

(instance primoPic of PicView
	(properties
		y 54
		x 95
		view 11
	)
)

(instance tablePic of PicView
	(properties
		y 155
		x 205
		view 11
		loop 1
	)
)

(instance stoolView of View
	(properties
		y 106
		x 169
		heading 180
		view 11
		loop 2
	)
)

(instance hChairPic of PicView
	(properties
		y 146
		x 169
		heading 90
		view 11
		loop 6
		priority 10
		signal (| ignrAct fixPriOn)
	)
)

(instance chairPic of PicView
	(properties
		y 167
		x 295
		view 211
		loop 2
	)
)

(instance ladyAtBar of Prop
	(properties
		y 98
		x 98
		z 15
		view 611
		signal stopUpdOn
	)
)

(instance ladyAtBarAss of PicView
	(properties
		y 106
		x 98
		view 511
		loop 2
	)
)

(instance manAtBar of PicView
	(properties
		y 105
		x 121
		view 511
		loop 2
		cel 1
	)
)

(instance plantPic of PicView
	(properties
		y 96
		x 45
		view 611
		loop 4
	)
)

(instance centerPiece of PicView
	(properties
		y 179
		x 206
		view 811
		loop 6
		priority 15
		signal ignrAct
	)
)

(instance extraControl of Code
	(method (doit obj startIt)
		(if (obj isKindOf: Extra)
			(if startIt
				(obj startExtra:)
			else
				(obj stopExtra:)
			)
		)
	)
)
