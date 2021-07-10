;;; Sierra Script 1.0 - (do not remove this comment)
(script# rgSubmarine)
(include game.sh)
(use Main)
(use Intrface)
(use Submarine_806)
(use EgoDead)
(use Game)
(use System)

(public
	subMarine 0
)

(class subMarine of Region
	(properties
		roomFlags 0
		nutSize 0
		washerSize 0
		cylDiam 0
		holeSize 0
		dist1 0
		head1 0
		dist2 0
		head2 0
		suitRoom 0
		msg12 0
		msg34 0
		msg56 0
		pointFlag1 0
		pointFlag2 0
		invStatus1 0
		invStatus2 0
		invStatus3 0
		invStatus4 0
	)
	
	(method (init)
		(super init: &rest)
		(Submarine init: floor: 1000)
		(self setScript: subMarineScript)
		(globalSound number: 11 loop: -1 owner: self play:)
	)
	
	(method (doit)
		(Submarine doit:)
		(super doit:)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said 'use/device[<electronic]')
				(if (ego has: iDevice)
					(Print 314 0)
				else
					(event claimed: FALSE)
				)
			)
			((Said '/cylinder>')
				(cond 
					((== curRoomNum 33))
					(
						(and
							(not (HaveMem 4352))
							(or (Said 'look') (Said 'measure'))
						)
						(Print 314 1)
						(event claimed: TRUE)
					)
					((Said 'look')
						((ScriptID 318 0) doit:)
					)
					((Said 'measure')
						((ScriptID 318 1) doit:)
					)
				)
			)
		)
	)
	
	(method (newRoom roomNum &tmp temp0)
		(= keep
			(OneOf roomNum
				25 26 27 28 29 30 31 32 33 34
				35 36 37 38 39 40 41 42 51 50
			)
		)
		(super newRoom: roomNum &rest)
	)
)

(instance subMarineScript of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(3
				(= cycles 2000)
			)
			(4
				(if (>= argc 2)
					(= cycles 1)
				else
					(EgoDead 7 0 0 314 2)
				)
			)
			(8
				(= cycles 400)
			)
			(9
				(if (< argc 2)
					(EgoDead 7 0 0 314 3)
				else
					(= cycles 0)
				)
			)
			(15
				(= cycles 12000)
			)
			(16
				(if (>= argc 2)
					(= cycles 1)
				else
					(EgoDead 7 0 0 314 4)
				)
			)
			(else  0)
		)
	)
)
