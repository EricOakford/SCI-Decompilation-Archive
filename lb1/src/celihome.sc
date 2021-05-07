;;; Sierra Script 1.0 - (do not remove this comment)
(script# 262)
(include sci.sh)
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
	local0
	local1
)
(instance Celie of Act
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
		signal $0010
	)
)

(instance celihome of Rgn
	(properties)
	
	(method (init)
		(super init:)
		(Load rsFONT 4)
		(Load rsVIEW 489)
		(Load rsFONT 4)
		(LoadMany 128 480 901)
		(= theTalker 2)
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
		(DisposeScript 985)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return 1))
		(return
			(if (== (event type?) evSAID)
				(cond 
					((Said 'examine/celie')
						(= theTalker 2)
						(if (& global207 $0002)
							(if enteredCelieHouse (Print 262 0) else (Print 262 1))
						else
							(Say 0 262 2)
						)
					)
					((Said 'converse>')
						(if (Said '/celie')
							(= theTalker 2)
							(if enteredCelieHouse
								(switch local0
									(0 (Say 1 262 3))
									(1 (Say 1 262 4))
									(2 (Say 1 262 5))
									(3 (Say 1 262 6))
									(4 (Say 1 262 7))
									(else  (Say 1 262 8))
								)
								(++ local0)
							else
								(switch local0
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
								(++ local0)
							)
						)
					)
					((Said 'hear/celie') (Print 262 22))
				)
			else
				0
			)
		)
	)
)

(instance cook of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Celie
					setCycle: Walk
					illegalBits: 0
					setAvoider: (Avoid new:)
					setMotion: MoveTo 192 127 self
				)
			)
			(1
				(Celie
					view: 482
					loop: 4
					setAvoider: 0
					cycleSpeed: 1
					setCycle: End self
				)
			)
			(2
				(gDoor hide:)
				(Celie loop: 5 cycleSpeed: 1 setCycle: Fwd)
				(= seconds (Random 3 8))
			)
			(3
				(gDoor show:)
				(Celie loop: 4 cel: 3 setCycle: Beg)
				(= state 0)
				(= seconds (Random 3 8))
			)
		)
	)
)

(instance sitDown of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Celie
					setCycle: Walk
					illegalBits: 0
					setAvoider: (Avoid new:)
					setMotion: MoveTo 213 138 self
				)
			)
			(1
				(Celie view: 489 setAvoider: 0 cel: 0 setCycle: End self)
				(DisposeScript 985)
			)
			(2
				(= theTalker 2)
				(Say 1 262 23)
				(Celie loop: 2)
				(CHead init:)
				(cls)
				(= seconds (Random 6 12))
			)
			(3
				(CHead setCycle: End)
				(= seconds (Random 6 12))
			)
			(4
				(CHead setCycle: Beg)
				(= state 2)
				(= seconds (Random 6 12))
			)
		)
	)
)
