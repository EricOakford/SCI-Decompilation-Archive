;;; Sierra Script 1.0 - (do not remove this comment)
(script# 1)
(include game.sh)
(use Main)
(use LoadMany)
(use Sound)
(use Game)
(use Actor)
(use System)

(public
	demoRoom1 0
)

(instance demoRoom1 of Room
	(properties
		picture 780
		style IRISIN
	)
	
	(method (init)
		(Load PICTURE 555)
		(LoadMany SOUND 911 1002)
		(Load VIEW 994)
		(super init:)
		(lauraBow init: hide:)
		(isBack init: hide:)
		(self setScript: sCartoon)
	)
)

(instance sCartoon of Script

	(method (doit)
		(if (== (sting prevSignal?) -1)
			(self cue:)
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 1))
			(1
				(theGame setCursor: INVIS_CURSOR TRUE)
				(lauraBow show:)
				(isBack show:)
				(= seconds 2)
			)
			(2 (sting play:) (= seconds 3))
			(3
				(lauraBow dispose:)
				(isBack dispose:)
				(curRoom drawPic: 555 IRISIN)
				(scream play:)
				(= cycles 1)
			)
			(4 0)
			(5
				(if modelessDialog
					(modelessDialog dispose:)
				)
				(UnLoad PICTURE 780 555)
				(curRoom newRoom: 2)
			)
		)
	)
)

(instance scream of Sound
	(properties
		number 911
	)
)

(instance sting of Sound
	(properties
		number 1002
	)
)

(instance lauraBow of View
	(properties
		x 34
		y 67
		view 103
	)
)

(instance isBack of View
	(properties
		x 94
		y 85
		view 103
		loop 1
	)
)
