;;; Sierra Script 1.0 - (do not remove this comment)
(script# 201)
(include sci.sh)
(use Main)
(use Scaler)
(use Motion)
(use System)

(public
	startupScr 0
)

(instance startupScr of Script
	(properties)
	
	(method (dispose)
		(super dispose:)
		(DisposeScript 201)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego
					setSpeed: 6
					posn: 173 133
					show:
					view: 201
					loop: 0
					cel: 0
					normal: 0
					setScale: 0
					setCycle: End self
				)
			)
			(1 (messager say: 1 0 1 1 self))
			(2
				(ego loop: 1 cel: 0 setCycle: End self)
			)
			(3
				(ego
					posn: 177 127
					reset: 5
					setScale: Scaler 100 50 112 57
				)
				(= ticks 20)
			)
			(4 (ego loop: 2) (= cycles 2))
			(5 (messager say: 1 0 1 2 self))
			(6
				(messager say: 1 0 1 3 self oneOnly: 0)
			)
			(7
				(theGame handsOn:)
				(theIconBar curIcon: (theIconBar at: 0))
				(theGame setCursor: ((theIconBar curIcon?) cursor?))
				(self dispose:)
			)
		)
	)
)
