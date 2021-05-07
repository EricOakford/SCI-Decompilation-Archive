;;; Sierra Script 1.0 - (do not remove this comment)
(script# 256)
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
	Dwilb 0
)
(synonyms
	(c c body fellow)
)

(instance Body of Prop
	(properties
		y 165
		x 87
		view 423
	)
	
	(method (handleEvent event)
		(if
		(or (MousedOn self event 3) (Said 'examine/c'))
			(event claimed: 1)
			(Print 256 0)
		)
	)
)

(instance WilburBlock of Blk
	(properties)
)

(instance myMusic of Sound
	(properties
		number 120
		priority 5
	)
)

(instance Dwilb of Rgn
	(properties)
	
	(method (init)
		(if (== curRoomNum 69) (Body posn: 272 145))
		(Body init:)
		(= global195 128)
		(if (< global198 200)
			(= global198 200)
			(proc415_1 2)
			(self setScript: showCloseup)
		)
	)
	
	(method (dispose)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return 1))
		(return
			(if (== (event type?) evSAID)
				(cond 
					((Said 'get/monocle')
						(if (< (ego distanceTo: Body) 25)
							(HandsOff)
							(self setScript: pickUp)
						else
							(NotClose)
						)
					)
					((Said '/c>')
						(cond 
							((Said 'kill') (Print 256 1))
							((Said 'kiss') (Print 256 2))
							((Said 'embrace') (Print 256 3))
							((Said 'get,drag,drag,press,move') (Print 256 4))
							((Said '(examine<in),search')
								(if (< (ego distanceTo: Body) 25)
									(HandsOff)
									(self setScript: pickUp)
								else
									(NotClose)
								)
							)
							((Said 'help') (Print 256 5))
							((Said 'converse') (Print 256 6))
						)
					)
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
			(0 (HandsOff) (= seconds 2))
			(1
				(WilburBlock
					top: (Body nsTop?)
					bottom: (Body nsBottom?)
					right: (Body nsRight?)
					left: (Body nsLeft?)
				)
				(ego observeBlocks: WilburBlock)
				(= cycles 1)
			)
			(2
				(myMusic play:)
				(Print 256 7 #at 10 75 #icon 423 1 0 #mode 1)
				(= cycles 1)
			)
			(3
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
			(2
				(Print 256 8)
				(if (not (ego has: 1))
					(Print 256 9)
					(= gotItem 1)
					(ego get: 1)
				)
				(= cycles 1)
			)
			(3 (ego setCycle: Beg self))
			(4
				(HandsOn)
				(ego view: 0 setCycle: Walk)
				(client setScript: 0)
			)
		)
	)
)
