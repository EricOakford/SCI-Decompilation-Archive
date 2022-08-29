;;; Sierra Script 1.0 - (do not remove this comment)
(script# 471)
(include game.sh)
(use Main)
(use Procs)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm471 0
)

(local
	oldCur
)
(instance rm471 of Room
	(properties
		picture 99
		style FADEOUT
	)
	
	(method (init)
		(super init:)
		(Bset fBeenToLake)
		(theGame handsOff:)
		(= oldCur (theGame setCursor:))
		(theGame setCursor: INVIS_CURSOR)
		(theMusic1
			number: 471
			flags: mNOPAUSE
			loop: -1
			play:
			setVol: 0
			fade: 80 25 10 0
		)
		(curRoom setScript: doTheCloseUp)
	)
	
	(method (dispose)
		(super dispose:)
		(theGame setCursor: oldCur)
	)
)

(instance doTheCloseUp of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(= seconds 3)
			)
			(1
				(curRoom drawPic: 600 PIXELDISSOLVE)
				(if (theGame keepBar?)
					(theIconBar draw:)
				)
				(= cycles 1)
			)
			(2
				(blinker init:)
				(= seconds 2)
			)
			(3
				(windowPiece init:)
				(carWindow init: setPri: 12)
				(= seconds 1)
			)
			(4
				(theSound1 number: 474 play:)
				(carWindow cycleSpeed: 12 setCycle: EndLoop self)
			)
			(5
				(= seconds 2)
			)
			(6
				(smilePiece init:)
				(= seconds 3)
			)
			(7
				(maliaPiece init: setPri: 14)
				(= seconds 3)
			)
			(8
				(gabEyes init:)
				(raiseEyes init: setPri: 12)
				(= seconds 1)
			)
			(9
				(raiseEyes cycleSpeed: 12 setCycle: EndLoop self)
			)
			(10
				(= seconds 2)
			)
			(11
				(theMusic1 fade: 0 10 25 1)
				(curRoom newRoom: 470)
				(self dispose:)
			)
		)
	)
)

(instance blinker of Prop
	(properties
		x 13
		y 86
		view 601
	)
	
	(method (init)
		(super init:)
		(self setPri: 14 cycleSpeed: 12 setCycle: Forward)
	)
)

(instance carWindow of Prop
	(properties
		x 115
		y 22
		view 602
	)
)

(instance raiseEyes of Prop
	(properties
		x 236
		y 102
		view 603
	)
)

(instance windowPiece of View
	(properties
		x 84
		y 22
		view 600
	)
)

(instance smilePiece of View
	(properties
		x 112
		y 96
		view 600
		loop 1
	)
)

(instance maliaPiece of View
	(properties
		x 227
		y 29
		view 600
		loop 2
	)
)

(instance gabEyes of View
	(properties
		x 227
		y 102
		view 600
		loop 3
	)
)
