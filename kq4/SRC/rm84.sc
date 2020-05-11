;;; Sierra Script 1.0 - (do not remove this comment)
(script# 84)
(include game.sh)
(use Main)
(use Intrface)
(use Sound)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	Room84 0
)

(local
	pandoraBox
	hen
)
(instance chickenMusic of Sound
	(properties
		number 79
		priority 1
		loop SND_DONE
	)
)

(instance Room84 of Room
	(properties
		picture 84
		style (| BLACKOUT IRISOUT)
	)
	
	(method (init)
		(self setRegions: LOLOTTE)
		(Load VIEW 360)
		(Load VIEW 519)
		(Load VIEW 649)
		(Load VIEW 634)
		(Load VIEW 512)
		(Load VIEW 40)
		(Load SOUND 79)
		(super init:)
		((View new:)
			view: 634
			loop: 1
			cel: 0
			posn: 31 77
			setPri: 4
			init:
			addToPic:
		)
		((View new:)
			view: 634
			loop: 1
			cel: 0
			posn: 71 78
			setPri: 4
			init:
			addToPic:
		)
		(if isNightTime
			((View new:)
				view: 649
				loop: 1
				posn: 102 66
				setPri: 3
				init:
				addToPic:
			)
			((View new:)
				view: 649
				loop: 1
				posn: 138 66
				setPri: 3
				init:
				addToPic:
			)
			((View new:)
				view: 649
				loop: 1
				posn: 177 66
				setPri: 3
				init:
				addToPic:
			)
			((View new:)
				view: 649
				loop: 1
				posn: 215 66
				setPri: 3
				init:
				addToPic:
			)
			((Prop new:)
				view: 512
				loop: 0
				posn: 33 65
				setPri: 3
				init:
				setCycle: Forward
			)
			((Prop new:)
				view: 512
				loop: 0
				posn: 73 66
				setPri: 3
				init:
				setCycle: Forward
			)
		)
		(if (== ((inventory at: iPandorasBox) owner?) 84)
			((= pandoraBox (View new:))
				view: 519
				ignoreActors: 0
				posn: 200 135
				init:
			)
		)
		(if (== ((inventory at: iMagicHen) owner?) 84)
			((= hen (Actor new:))
				view: 360
				illegalBits: 0
				posn: 80 133
				init:
				setScript: henPacing
			)
			(chickenMusic play:)
		)
		(if (or (== prevRoomNum 87) (== prevRoomNum 0))
			(ego posn: 157 162 view: 4 xStep: 4 yStep: 2 init:)
			(if henchChasingEgo (= henchChasingEgo FALSE))
		)
	)
	
	(method (doit)
		(super doit:)
		(if (& (ego onControl: 0) $0040) (curRoom newRoom: 87))
	)
	
	(method (handleEvent event &tmp [temp0 80])
		(return
			(cond 
				((event claimed?) (return TRUE))
				((== (event type?) saidEvent)
					(cond 
						(
						(or (Said 'look[<around][/!*]') (Said 'look/room'))
							(Print 84 0)
							(cond 
								(
									(and
										(== ((inventory at: iPandorasBox) owner?) 84)
										(== ((inventory at: iMagicHen) owner?) 84)
									)
									(Print 84 1)
								)
								((== ((inventory at: iMagicHen) owner?) 84) (Print 84 2))
								((== ((inventory at: iPandorasBox) owner?) 84) (Print 84 3))
								(else (Print 84 4))
							)
						)
						((Said 'look>')
							(cond 
								((Said '/box') (Print 84 5))
								((Said '/barrel') (Print 84 6))
								((Said '/chest') (Print 84 7))
								((Said '/shelf') (Print 84 8))
								((Said '/[') (Print 84 9))
								((Said '/window') (Print 84 10))
								((Said '/wall') (Print 84 11))
								((or (Said '/dirt') (Said '<down')) (Print 84 12))
								((Said '/chicken')
									(if (== ((inventory at: iMagicHen) owner?) 84)
										(Print 84 13)
									else
										(Print 84 14 #at -1 10)
									)
								)
							)
						)
						((Said 'get>')
							(cond 
								((Said '/box<pandora')
									(cond 
										((ego has: iPandorasBox) (Print 800 0))
										((!= ((inventory at: iPandorasBox) owner?) 84) (Print 84 15))
										((< (ego distanceTo: pandoraBox) 20)
											(ego get: iPandorasBox)
											(theGame changeScore: 2)
											(egoPickUp changeState: 0)
											(pandoraBox dispose:)
										)
										(else (Print 800 1))
									)
								)
								((Said '/chicken')
									(cond 
										((ego has: iMagicHen) (Print 84 16))
										((!= ((inventory at: iMagicHen) owner?) 84) (Print 84 15))
										((< (ego distanceTo: hen) 20)
											(chickenMusic dispose:)
											(ego get: iMagicHen)
											(theGame changeScore: 2)
											(egoPickUp changeState: 0)
											(hen dispose:)
										)
										(else (Print 800 1))
									)
								)
								((Said '/box') (Print 84 5))
								((Said '/chest') (Print 84 7))
								((Said 'open/window') (Print 84 17))
								((Said 'break/window') (Print 84 18))
								((Said '/[') (Print 84 9))
							)
						)
						((Said 'open>')
							(cond 
								((Said '/box<pandora') (event claimed: FALSE))
								((Said '/box') (Print 84 5))
								((Said '/barrel') (Print 84 6))
								((Said '/chest') (Print 84 7))
							)
						)
					)
				)
			)
		)
	)
)

(instance egoPickUp of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= gotItem TRUE)
				(User canControl: 0 canInput: 0)
				(ego view: 40 cel: 255 setMotion: 0 setCycle: EndLoop self)
			)
			(1
				(ego view: 4 setCycle: Walk)
				(User canControl: 1 canInput: 1)
			)
		)
	)
)

(instance henPacing of Script
	(properties)
	
	(method (init param1)
		(super init: param1)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (cast contains: hen)
					(hen loop: (+ (hen loop?) 2) setCycle: EndLoop self)
				)
			)
			(1
				(if (cast contains: hen)
					(hen
						setCycle: Walk
						setMotion:
							MoveTo
							(if (> (hen x?) 87)
								(Random 67 85)
							else
								(Random 90 120)
							)
							134
							self
					)
				)
			)
			(2 (= state -1) (self cue:))
		)
	)
)
