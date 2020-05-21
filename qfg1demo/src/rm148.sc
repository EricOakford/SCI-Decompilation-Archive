;;; Sierra Script 1.0 - (do not remove this comment)
(script# 148)
(include game.sh)
(use Main)
(use Print)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm148 0
)

(instance rm148 of Room
	(properties
		picture 148
		style FADEOUT
	)
	
	(method (init)
		(super init: &rest)
		(= currentPic 148)
		(self setScript: roomScript)
	)
)

(instance leftHair of Prop
	(properties
		x 120
		y 51
		view 148
		cel 1
		priority 15
		signal fixPriOn
		cycleSpeed 12
	)
)

(instance leftCape of Prop
	(properties
		x 65
		y 166
		view 148
		loop 1
		cel 1
		cycleSpeed 24
	)
)

(instance rightCape of Prop
	(properties
		x 220
		y 184
		view 148
		loop 2
		cel 1
		cycleSpeed 12
	)
)

(instance metal of View
	(properties
		x 126
		y 99
		view 148
		loop 3
		priority 15
		signal fixPriOn
	)
)

(instance roomScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(soundFx loop: 1 number: 996 play: self)
				(leftHair init: posn: 126 42 setLoop: 0 stopUpd:)
				(metal init: addToPic:)
				(leftCape init: setLoop: 1 posn: 81 151 setCycle: Forward)
				(rightCape init: setLoop: 2 posn: 223 161 setCycle: Forward)
				(= cycles 2)
			)
			(1
				(Print
					width: 240
					addText:
						{Play Quest For Glory I in stunning 256 color VGA and an intuitive Icon Interface.}
					modeless: TRUE
					x: 40
					y: 5
					init:
				)
				(= seconds 12)
			)
			(2
				(if modelessDialog (modelessDialog dispose:))
				(leftHair setCycle: Forward)
				(= ticks 30)
			)
			(3
				(Print
					addText: {Coming Spring 1992 from Sierra}
					modeless: TRUE
					x: 60
					y: 5
					init:
				)
			)
			(4
				(if modelessDialog
					(modelessDialog dispose:)
				)
				(curRoom drawPic: 400 DISSOLVE)
				(cast eachElementDo: #hide)
				(= cycles 2)
			)
			(5
				(= showSierraLogo FALSE)
				(curRoom newRoom: 100)
			)
		)
	)
)
