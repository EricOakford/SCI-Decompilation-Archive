;;; Sierra Script 1.0 - (do not remove this comment)
(script# 6)
(include game.sh)
(use Main)
(use LoadMany)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	demoRoom6 0
)

(instance demoRoom6 of Room
	(properties
		picture 470
		style IRISIN
	)
	
	(method (init)
		(Load PICTURE 465)
		(Load VIEW 470)
		(LoadMany SOUND 912 1004)
		(super init:)
		(sting play:)
		(blood init: setScript: sBloodDrip)
		(self setScript: sCartoon)
	)
)

(instance sCartoon of Script
	
	(method (doit)
		(super doit:)
		(if (== (scream prevSignal?) -1) (self cue:))
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(DoDisplay
					{...murder!}
					1
					1151
					100
					0
					0
					global116
					myBordColor
				)
				(= seconds 4)
			)
			(1
				(if modelessDialog (modelessDialog dispose:))
				(blood setScript: 0 dispose:)
				(curRoom drawPic: 465)
				(sting stop:)
				(= cycles 2)
			)
			(2 (scream play:))
			(3
				(if modelessDialog (modelessDialog dispose:))
				(UnLoad PICTURE 470 465)
				(curRoom newRoom: 7)
			)
		)
	)
)

(instance sBloodDrip of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(blood
					loop: 0
					posn: 180 100
					cycleSpeed: 8
					setCycle: EndLoop self
				)
			)
			(1
				(blood
					loop: 1
					cel: 0
					posn: 186 150
					moveSpeed: 3
					setMotion: MoveTo 184 189 self
				)
			)
			(2
				(blood hide:)
				(= seconds (Random 1 4))
			)
			(3 (blood show:) (self init:))
		)
	)
)

(instance blood of Actor
	(properties
		x 180
		y 100
		view 470
	)
)

(instance scream of Sound
	(properties
		number 912
	)
)

(instance sting of Sound
	(properties
		number 1004
	)
)
