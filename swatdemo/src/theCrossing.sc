;;; Sierra Script 1.0 - (do not remove this comment)
(script# 15)
(include game.sh)
(use Main)
(use PQRoom)
(use Motion)
(use Actor)
(use System)

(public
	theCrossing 0
)

(instance theCrossing of PQRoom
	(properties
		picture 151
	)
	
	(method (init)
		(super init: &rest)
		(self setScript: demoScript)
	)
)

(instance demoScript of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(swatPups init:)
				(= cycles 3)
			)
			(1
				(swatPups setCycle: EndLoop self)
			)
			(2
				(swatPups
					setLoop: 1
					cel: 0
					posn: 123 137
					setCycle: EndLoop self
				)
			)
			(3
				(Bclr fScrollingEnabled)
				(Bclr 12)
				(= gScript demoScript)
				(theInterface newInvLevel: -1)
			)
			(4 (= seconds 3))
			(5
				(theMusic number: 400 loop: 1 play:)
				(curRoom newRoom: 18)
			)
		)
	)
)

(instance swatPups of Prop
	(properties
		x 130
		y 102
		priority 150
		fixPriority 1
		view 151
		cycleSpeed 10
	)
)
