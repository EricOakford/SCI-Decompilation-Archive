;;; Sierra Script 1.0 - (do not remove this comment)
(script# 262)
(include game.sh)
(use Main)
(use Intrface)
(use Avoider)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	celihome 0
)
(synonyms
	(celie person girl)
)

(local
	talkCount
	local1
)
(instance Celie of Actor
	(properties
		view 480
	)
)

(instance CHead of Prop
	(properties
		y 102
		x 227
		view 489
		loop 1
		priority 10
		signal fixPriOn
	)
)

(instance celihome of Region
	
	(method (init)
		(super init:)
		(Load FONT 4)
		(Load VIEW 489)
		(Load FONT 4)
		(LoadMany VIEW 480 901)
		(= theTalker talkCELIE)
		(if enteredCelieHouse
			(Celie
				view: 480
				illegalBits: 0
				loop: 4
				cel: 0
				posn: 123 147
				init:
				setScript: cook
			)
		else
			(Celie posn: 123 147 init: setScript: sitDown)
		)
	)
	
	(method (doit)
		(super doit:)
	)
	
	(method (dispose)
		(DisposeScript AVOIDER)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return TRUE))
		(return
			(if (== (event type?) saidEvent)
				(cond 
					((Said 'examine/celie')
						(= theTalker talkCELIE)
						(if (& global207 $0002)
							(if enteredCelieHouse
								(Print 262 0) 
							else
								(Print 262 1)
							)
						else
							(Say 0 262 2)
						)
					)
					((Said 'converse>')
						(if (Said '/celie')
							(= theTalker talkCELIE)
							(if enteredCelieHouse
								(switch talkCount
									(0 (Say 1 262 3))
									(1 (Say 1 262 4))
									(2 (Say 1 262 5))
									(3 (Say 1 262 6))
									(4 (Say 1 262 7))
									(else  (Say 1 262 8))
								)
								(++ talkCount)
							else
								(switch talkCount
									(0 (Say 1 262 9))
									(1
										(Say 1 262 10)
										(Say 1 262 11)
										(Say 1 262 12)
										(Say 1 262 13)
										(Say 1 262 14)
										(Say 1 262 15)
									)
									(2 (Say 1 262 16))
									(3 (Say 1 262 17))
									(4 (Say 1 262 18))
									(5 (Say 1 262 19))
									(6 (Say 1 262 20))
									(else  (Print 262 21))
								)
								(++ talkCount)
							)
						)
					)
					((Said 'hear/celie')
						(Print 262 22)
					)
				)
			else
				FALSE
			)
		)
	)
)

(instance cook of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Celie
					setCycle: Walk
					illegalBits: 0
					setAvoider: (Avoider new:)
					setMotion: MoveTo 192 127 self
				)
			)
			(1
				(Celie
					view: 482
					loop: 4
					setAvoider: 0
					cycleSpeed: 1
					setCycle: EndLoop self
				)
			)
			(2
				(gDoor hide:)
				(Celie loop: 5 cycleSpeed: 1 setCycle: Forward)
				(= seconds (Random 3 8))
			)
			(3
				(gDoor show:)
				(Celie loop: 4 cel: 3 setCycle: BegLoop)
				(= state 0)
				(= seconds (Random 3 8))
			)
		)
	)
)

(instance sitDown of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Celie
					setCycle: Walk
					illegalBits: 0
					setAvoider: (Avoider new:)
					setMotion: MoveTo 213 138 self
				)
			)
			(1
				(Celie view: 489 setAvoider: 0 cel: 0 setCycle: EndLoop self)
				(DisposeScript 985)
			)
			(2
				(= theTalker talkCELIE)
				(Say 1 262 23)
				(Celie loop: 2)
				(CHead init:)
				(cls)
				(= seconds (Random 6 12))
			)
			(3
				(CHead setCycle: EndLoop)
				(= seconds (Random 6 12))
			)
			(4
				(CHead setCycle: BegLoop)
				(= state 2)
				(= seconds (Random 6 12))
			)
		)
	)
)
