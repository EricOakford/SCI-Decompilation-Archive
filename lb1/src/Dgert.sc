;;; Sierra Script 1.0 - (do not remove this comment)
(script# 235)
(include sci.sh)
(use Main)
(use Intrface)
(use FndBody)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	Dgert 0
)
(synonyms
	(gertie gertie body girl)
)

(instance Body of Prop
	(properties)
)

(instance myMusic of Sound
	(properties
		number 120
		priority 5
	)
)

(instance Dgert of Rgn
	(properties)
	
	(method (init)
		(proc415_1 1)
		(Body view: 343 cel: 0 posn: 55 126 init:)
		(= deadGuests (| deadGuests $0001))
		(= global195 1)
		(self setScript: showCloseup)
	)
	
	(method (doit)
		(ego observeControl: 256)
		(super doit:)
	)
	
	(method (dispose)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return 1))
		(return
			(if (== (event type?) evSAID)
				(cond 
					((Said '(examine<in),search/cloth')
						(if (< (ego distanceTo: Body) 20)
							(self setScript: pickUp)
						else
							(NotClose)
						)
					)
					((Said '/gertie>')
						(cond 
							((Said 'kill') (Print 235 0))
							((Said 'kiss') (Print 235 1))
							((Said 'embrace') (Print 235 2))
							((Said 'get,drag,drag,press,move') (Print 235 3))
							((Said '(examine<in),search')
								(if (< (ego distanceTo: Body) 20)
									(self setScript: pickUp)
								else
									(NotClose)
								)
							)
							((Said 'examine,find') (Print 235 4))
							((Said 'help') (Print 235 5))
							((Said 'converse') (Print 235 6))
						)
					)
					((Said 'ask,hold,tell,deliver') (Print 235 6))
					((Said 'get/pearl,necklace') (Print 235 7))
				)
			else
				0
			)
		)
	)
)

(instance showCloseup of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 2))
			(1
				(if (not (& (ego onControl: 1) $0001)) (= state 0))
				(= cycles 1)
			)
			(2 (HandsOff) (= seconds 3))
			(3
				(myMusic play:)
				(Print 235 8 #at 10 75 #icon 343 1 0 #mode 1)
				(= cycles 1)
			)
			(4
				(HandsOn)
				(client setScript: 0)
			)
		)
	)
)

(instance pickUp of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Face ego Body)
				(= cycles 2)
			)
			(1
				(ego view: 17 cel: 0 setMotion: 0 setCycle: End self)
			)
			(2 (Print 235 9) (= cycles 1))
			(3 (ego setCycle: Beg self))
			(4
				(ego view: 0 setCycle: Walk)
				(client setScript: 0)
			)
		)
	)
)
