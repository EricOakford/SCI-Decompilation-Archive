;;; Sierra Script 1.0 - (do not remove this comment)
(script# 265)
(include sci.sh)
(use Main)
(use Intrface)
(use Game)
(use Actor)

(public
	lillbed 0
)

(local
	local0
	local1
)
(instance lillbed of Rgn
	(properties)
	
	(method (init)
		(super init:)
		(Load rsFONT 4)
		(Lillian setPri: 11 init:)
		(= theTalker 6)
	)
	
	(method (doit)
		(super doit:)
	)
	
	(method (dispose)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(= theTalker 6)
		(super handleEvent: event)
		(if (event claimed?) (return 1))
		(return
			(cond 
				((Said 'converse[/lil]')
					(switch local1
						(0 (Say 1 265 0) (Say 1 265 1))
						(1 (Say 1 265 2))
						(2 (Say 1 265 3))
						(else  (Print 265 4))
					)
					(++ local1)
				)
				((Said 'hear/lil') (Print 265 5))
				((Said 'ask,tell,deliver,hold') (Say 1 265 6))
				((Said 'get/lil') (Print 265 7))
				((Said 'kill/lil') (Print 265 8))
				((Said 'kiss/lil') (Print 265 9))
				((Said 'embrace/lil') (Print 265 10))
			)
		)
	)
)

(instance Lillian of Act
	(properties
		y 134
		x 285
		view 514
		loop 1
		illegalBits $0000
	)
	
	(method (handleEvent event)
		(= theTalker 6)
		(if
		(or (MousedOn self event 3) (Said 'examine/lil'))
			(if (& global207 $0020)
				(Print 265 11)
			else
				(= global207 (| global207 $0020))
				(Print 265 12)
			)
			(event claimed: 1)
		)
	)
)

(instance lHead of Prop
	(properties
		y 133
		x 265
		view 512
		loop 4
	)
)
