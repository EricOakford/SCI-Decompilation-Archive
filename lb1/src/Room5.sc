;;; Sierra Script 1.0 - (do not remove this comment)
(script# 5)
(include game.sh)
(use Main)
(use Intrface)
(use RFeature)
(use Game)

(public
	Room5 0
)
(synonyms
	(rose blossom)
	(room garden)
)

(instance Room5 of Room
	(properties
		picture 5
	)
	
	(method (init)
		(= south 4)
		(super init:)
		(addToPics
			add:
				planter1
				planter2
				treerose1
				treerose2
				treerose3
				treerose4
				bench1
				bench2
			eachElementDo: #init
			doit:
		)
		(self
			setFeatures:
				planter1
				planter2
				treerose1
				treerose2
				treerose3
				treerose4
				bench1
				bench2
		)
		(if
			(and
				(>= currentAct 4)
				(!= global200 101)
				(not (& deadGuests $0040))
			)
			(cond 
				((== global170 5)
					(self setRegions: 268)
				)
				((not (== global170 61))
					(switch (Random 1 2)
						(1 (self setRegions: 268))
						(2 (= global170 61))
					)
				)
			)
		)
		(if (and (>= currentAct 2) (< currentAct 4))
			(self setRegions: 202)
		)
		(ego view: 0 init:)
	)
	
	(method (doit)
		(if (FirstEntry)
			(Print 5 0)
		)
		(super doit:)
	)
	
	(method (dispose)
		(super dispose:)
	)
	
	(method (handleEvent event &tmp temp0)
		(if (event claimed?) (return TRUE))
		(return
			(if (== (event type?) saidEvent)
				(cond 
					((Said 'examine>')
						(cond 
							((Said '[<around,at][/room]')
								(Print 5 0)
							)
							((Said '/arbor[<rose]')
								(Print 5 1)
							)
							((Said '/path,(boulder<stepping)')
								(Print 5 2)
							)
							((Said '/rose,bush,oak,foliage')
								(Print 5 3)
							)
							((Said '/fence')
								(Print 5 4)
							)
							((Said '/field')
								(Print 5 5)
							)
						)
					)
					((Said 'get/rose,rose,foliage')
						(Print 5 6)
					)
					((Said 'smell/rose')
						(Print 5 7)
					)
					((Said 'climb/fence')
						(Print 5 8)
					)
				)
			else
				FALSE
			)
		)
	)
	
	(method (newRoom n)
		(super newRoom: n)
	)
)

(instance planter1 of RPicView
	(properties
		y 68
		x 107
		view 105
		priority 5
	)
	
	(method (handleEvent event)
		(cond 
			((Said 'press,move,drag/urn')
				(Print 5 9)
			)
			((Said 'examine<below/urn')
				(Print 5 10)
			)
			((Said 'examine<in/urn')
				(if (& (ego onControl: FALSE) cYELLOW)
					(Print 5 11)
				else
					(NotClose)
				)
			)
			((Said 'get/urn')
				(Print 5 12)
			)
			(
				(or
					(MousedOn self event shiftDown)
					(Said 'examine/urn,pedestal,base')
				)
				(event claimed: TRUE)
				(Print 5 13)
			)
		)
	)
)

(instance planter2 of RPicView
	(properties
		y 68
		x 212
		view 105
		priority 5
	)
	
	(method (handleEvent event)
		(if (MousedOn self event shiftDown)
			(event claimed: TRUE)
			(Print 5 13)
		)
	)
)

(instance treerose1 of RPicView
	(properties
		y 119
		x 133
		view 105
		loop 1
	)
	
	(method (handleEvent event)
		(cond 
			((Said 'climb/oak,bush,rose')
				(Print 5 14)
			)
			(
				(or
					(MousedOn self event shiftDown)
					(Said 'examine/oak,bush,rose')
				)
				(event claimed: TRUE)
				(Print 5 3)
			)
		)
	)
)

(instance treerose2 of RPicView
	(properties
		y 118
		x 186
		view 105
		loop 1
		cel 1
	)
	
	(method (handleEvent event)
		(if (MousedOn self event shiftDown)
			(event claimed: TRUE)
			(Print 5 3)
		)
	)
)

(instance treerose3 of RPicView
	(properties
		y 105
		x 71
		view 105
		loop 2
	)
	
	(method (handleEvent event)
		(if (MousedOn self event shiftDown)
			(event claimed: TRUE)
			(Print 5 3)
		)
	)
)

(instance treerose4 of RPicView
	(properties
		y 102
		x 254
		view 105
		loop 2
	)
	
	(method (handleEvent event)
		(if (MousedOn self event shiftDown)
			(event claimed: TRUE)
			(Print 5 3)
		)
	)
)

(instance bench1 of RPicView
	(properties
		y 52
		x 106
		view 105
		loop 3
		priority 1
	)
	
	(method (handleEvent event)
		(cond 
			((Said 'examine<below/bench')
				(Print 5 15)
			)
			((Said 'sit/[<bench]')
				(Print 5 16)
			)
			((or (MousedOn self event shiftDown) (Said 'examine/bench'))
				(event claimed: TRUE)
				(Print 5 17)
			)
		)
	)
)

(instance bench2 of RPicView
	(properties
		y 52
		x 216
		view 105
		loop 3
		cel 1
		priority 1
	)
	
	(method (handleEvent event)
		(if (MousedOn self event shiftDown)
			(event claimed: TRUE)
			(Print 5 17)
		)
	)
)
