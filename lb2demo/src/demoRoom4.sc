;;; Sierra Script 1.0 - (do not remove this comment)
(script# 4)
(include game.sh)
(use Main)
(use Intrface)
(use PolyPath)
(use Polygon)
(use LoadMany)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	demoRoom4 0
)

(instance demoRoom4 of Room
	(properties
		picture 330
		style IRISIN
	)
	
	(method (init)
		(LoadMany PICTURE 440 331)
		(LoadMany VIEW 804 807)
		(ego
			view: 807
			posn: 5 158
			cycleSpeed: 6
			moveSpeed: 6
			setStep: 3 2
			init:
		)
		(super init:)
		(music number: 1003 loop: -1 play:)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: PContainedAccess
					init:
						41 142
						64 149
						97 145
						125 157
						293 141
						185 116
						181 96
						151 96
						156 136
						110 142
					yourself:
				)
		)
		(frontFountain init: setCycle: Forward)
		(farFountain init: setCycle: Forward)
		(self setScript: sCartoon)
	)
)

(instance sCartoon of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if modelessDialog (modelessDialog dispose:))
				(= cycles 1)
			)
			(1
				(DoDisplay
					{Later, Laura goes to the scene of the crime.}
					1
					20071
					290
					30
					176
					global116
					myBordColor
				)
				(= cycles 5)
			)
			(2
				(ego setMotion: PolyPath 174 146 self)
			)
			(3
				(ego setHeading: 1)
				(= cycles 2)
			)
			(4
				(ego view: 807 loop: 3 setMotion: PolyPath 173 138 self)
			)
			(5
				(cast eachElementDo: #hide)
				(curRoom drawPic: 331 IRISIN)
				(= cycles 4)
			)
			(6
				(museumDoor init:)
				(ego
					show:
					view: 804
					posn: 153 200
					setMotion: PolyPath 154 169 self
				)
			)
			(7
				(museumDoor setCycle: EndLoop self)
			)
			(8
				(ego hide:)
				(museumDoor hide:)
				(curRoom drawPic: 440 IRISIN)
				(= cycles 2)
			)
			(9
				(Print 4 0 #dispose #at 10 10 #font 69)
				(ego posn: 239 143 show:)
				(= seconds 4)
			)
			(10
				(ego setStep: 2 2 setMotion: PolyPath 180 180 self)
			)
			(11
				(if modelessDialog
					(modelessDialog dispose:)
				)
				(UnLoad PICTURE 330 440 331)
				(curRoom newRoom: 5)
			)
		)
	)
)

(instance frontFountain of Prop
	(properties
		x 77
		y 138
		view 330
	)
)

(instance farFountain of Prop
	(properties
		x 263
		y 126
		view 330
		loop 1
	)
)

(instance museumDoor of Prop
	(properties
		x 164
		y 162
		view 336
	)
)
