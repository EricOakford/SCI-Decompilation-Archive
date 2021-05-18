;;; Sierra Script 1.0 - (do not remove this comment)
(script# 278)
(include game.sh)
(use Main)
(use Intrface)
(use FndBody)
(use Sound)
(use Game)
(use Actor)
(use System)

(public
	Dclar 0
)
(synonyms
	(attorney body fellow)
)

(instance Body of Prop
	(properties
		y 94
		x 199
		view 403
		loop 1
		cel 1
	)
)

(instance myMusic of Sound
	(properties
		number 120
		priority 5
	)
)

(instance Dclar of Region
	
	(method (init)
		(if (== (= gCurRoomNum_3 curRoomNum) 73)
			(Body view: 403 loop: 1 cel: 2 posn: 241 141)
		else
			(Bclr 35)
		)
		(Body init:)
		(= global195 64)
		(if (< global201 200)
			(= global201 200)
			(proc415_1 32)
			(self setScript: showCloseup)
		)
	)
	
	(method (doit)
		(super doit:)
	)
	
	(method (dispose)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return TRUE))
		(return
			(if (== (event type?) saidEvent)
				(cond 
					((Said 'examine/dagger') (Print 278 0))
					((Said 'get/dagger') (Print 278 1))
					((Said '/attorney>')
						(cond 
							((Said 'kill') (Print 278 2))
							((Said 'kiss') (Print 278 3))
							((Said 'embrace') (Print 278 4))
							((Said 'get,drag,drag,press,move') (Print 278 5))
							((Said '(examine<in),search')
								(if (< (ego distanceTo: Body) 40)
									(if (not (ego has: iMatches))
										(Print 278 6)
										(Print 278 7)
										(= gotItem TRUE)
										(ego get: iMatches)
									else
										(Print 278 8)
									)
								else
									(NotClose)
								)
							)
							((Said 'examine') (Print 278 9))
							((Said 'help') (Print 278 10))
							((Said 'converse') (Print 278 11))
						)
					)
				)
			else
				FALSE
			)
		)
	)
)

(instance showCloseup of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (HandsOff) (= seconds 2))
			(1 (= cycles 1))
			(2
				(myMusic play:)
				(Print 278 12
					#at 10 10
					#icon 403 0 0
					#mode teJustCenter
				)
				(= cycles 1)
			)
			(3
				(HandsOn)
				(client setScript: 0)
			)
		)
	)
)
