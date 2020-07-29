;;; Sierra Script 1.0 - (do not remove this comment)
(script# rgnHollywood)
(include game.sh)
(use Main)
(use Game)
(use System)

(public
	HollywoodRgn 0
)

(instance HollywoodRgn of Region
	
	(method (init)
		(if (Btst fAfterCoffee)
			(self setScript: sRemember)
		)
		(super init:)
	)
	
	(method (newRoom n)
		(= keep (OneOf n 160 170 180 190))
	)
)

(instance sRemember of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 30))
			(1
				(TimePrint 40 0)
				(TimePrint 40 1)
				(= seconds 120)
			)
			(2
				(TimePrint 40 2)
				(TimePrint 40 3)
				(TimePrint 40 4 67 -1 185)
				(= seconds 120)
			)
			(3
				(TimePrint 40 5)
				(TimePrint 40 6)
				(= seconds 120)
			)
			(4
				(TimePrint 40 7)
				(TimePrint 40 8)
				(TimePrint 40 9)
				(= seconds 120)
			)
			(5
				(TimePrint 40 10)
				(TimePrint 40 11)
				(= seconds 120)
			)
		)
	)
)
