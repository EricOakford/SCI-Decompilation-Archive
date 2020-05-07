;;; Sierra Script 1.0 - (do not remove this comment)
(script# 336)
(include game.sh)
(use Main)
(use Intrface)
(use Motion)
(use User)
(use Actor)
(use System)

(public
	drinkDown 0
)

(instance drinkDown of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setCycle: EndLoop)
				(= cycles 10)
			)
			(1
				(switch drinkInHand
					(drinkAle
						(switch numberOfAlesDrunk
							(1
								(HighPrint 336 0)
								;This tastes as sour as it smells, and it burns your throat as you swallow it. Still, it isn't the worst beer you've ever drunk.
								(= cycles 5)
							)
							(2
								(HighPrint 336 1)
								;You know, that actually tasted fine! This really isn't such a bad place, after all,
								;and the bartender reminds you of an old friend you used to know.
								(= cycles 5)
							)
							(3
								(HighPrint 336 2)
								;Suddenly, you don't feel so good...
								(client setScript: tooDrunk)
							)
						)
					)
					(drinkSweat
						(HighPrint 336 3)
						;Smells like Troll's Sweat. Tastes like Troll's Sweat. By golly, it IS Troll's Sweat....
						(client setScript: tooDrunk)
					)
					(drinkBreath
						(HighPrint 336 4)
						;You've never tasted anything like it before.
						(client setScript: breathDeath)
					)
				)
			)
			(2 (ego setCycle: BegLoop self))
			(3
				(= drinkInHand drinkNothing)
				(User canInput: TRUE)
				(client setScript: 0)
				(DisposeScript 336)
			)
		)
	)
)

(instance tooDrunk of Script
	(properties)
	
	(method (dispose)
		(super dispose:)
		(DisposeScript 336)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setCycle: BegLoop)
				(= cycles 15)
			)
			(1
				(ego loop: 6 cel: 0)
				(deadMug init:)
				(addToPics add: deadMug doit:)
				(= cycles 2)
			)
			(2
				(switch drinkInHand
					(drinkAle
						(HighPrint 336 5)
						;Too much beer.
						)
					(drinkSweat
						(HighPrint 336 6)
						;...and one Troll's Sweat is too many.
						)
				)
				(= cycles 2)
			)
			(3
				(ego cycleSpeed: 2 setCycle: CycleTo 6 1)
				(= cycles 25)
			)
			(4
				(ego cycleSpeed: 0 setCycle: EndLoop)
				(= cycles 20)
			)
			(5
				(cast eachElementDo: #dispose)
				(curRoom drawPic: 400 16)
				(self cue:)
			)
			(6
				(SolvePuzzle POINTS_GOTDRUNK -5)
				(Bset TAVERN_DRUNK)
				(curRoom newRoom: 330)
			)
		)
	)
)

(instance breathDeath of Script
	(properties)
	
	(method (dispose)
		(super dispose:)
		(DisposeScript 336)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(Print 336 7)
				(ego loop: 5 cel: 0 cycleSpeed: 2 setCycle: CycleTo 12 1 self)
				; Ohhhhhhhhh woooowwwwwww!!!
			)
			(1
				(dust init:)
				(addToPics add: dust doit:)
				(ego setCycle: EndLoop self)
			)
			(2
				(EgoDead 336 8
					#title { Talk about a "fiery brew"._}
					#icon vBarInside 0 0)
					;Maybe you really shouldn't have tried the Dragon's Breath!  Better luck next time, and we hope you saved your game.
			)
		)
	)
)

(instance deadMug of PicView
	(properties
		y 80
		x 165
		view vBarInside
		cel 2
		priority 12
	)
)

(instance dust of PicView
	(properties
		y 89
		x 149
		view vEgoInsideBar
		loop 2
		cel 4
		priority 15
	)
)
