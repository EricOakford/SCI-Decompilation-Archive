;;; Sierra Script 1.0 - (do not remove this comment)
(script# 63)
(include sci.sh)
(use Main)
(use Intrface)
(use RFeature)
(use Game)
(use Actor)

(public
	Room63 0
)
(synonyms
	(blackboard board)
	(doll bear toy)
)

(instance Room63 of Rm
	(properties
		picture 63
	)
	
	(method (init)
		(super init:)
		(if (>= currentAct 6) (addToPics add: Chalk))
		(if
			(and
				(not (== currentAct 2))
				(not (== currentAct 6))
				(not (& global118 $0002))
			)
			(addToPics add: chair2)
		)
		(addToPics
			add: table chair1 BigDoll SmallDoll MedDoll Bear Poster
			eachElementDo: #init
			doit:
		)
		(self setFeatures: BigDoll chair2 chair1 table)
		(ego
			view: 0
			loop: 1
			posn: 200 153
			illegalBits: -32768
			init:
		)
		(switch currentAct
			(2 (self setRegions: 257))
			(6
				(if (not (& global118 $0002)) (self setRegions: 282))
			)
		)
	)
	
	(method (doit)
		(if (FirstEntry) (Print 63 0))
		(if (& (ego onControl: 0) $0002) (curRoom newRoom: 14))
		(super doit:)
	)
	
	(method (dispose)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return))
		(if (== (event type?) evSAID)
			(if
				(and
					global208
					(Said
						'ask,tell,hold,deliver,examine,get,kill,kiss,embrace,flirt>'
					)
				)
				(DisposeScript 990)
				(self setScript: (ScriptID 243 0))
				((self script?) handleEvent: event)
				(if (event claimed?) (return))
			)
			(cond 
				((Said 'examine>')
					(cond 
						(
							(or
								(Said '[<around,at][/room,dock]')
								(Said '/cabin<little')
								(Said '/playhouse')
								(Said '/cabin<play')
							)
							(Print 63 1)
						)
						((Said '/door') (Print 63 2))
						((Said '/window') (Print 63 3))
						((Said '/wall')
							(if (>= currentAct 5)
								(Print 63 4)
							else
								(event claimed: 0)
							)
						)
					)
				)
				((Said 'write,draw,erase/blackboard')
					(if (>= currentAct 5)
						(Print 63 5)
					else
						(event claimed: 0)
					)
				)
				((Said 'open/window') (Print 63 6))
			)
		)
	)
	
	(method (newRoom n)
		(super newRoom: n)
	)
)

(instance table of RPicView
	(properties
		y 133
		x 170
		view 163
		priority 2
	)
	
	(method (handleEvent event)
		(if
		(or (MousedOn self event 3) (Said 'examine/nightstand'))
			(Print 63 7)
			(event claimed: 1)
		)
	)
)

(instance chair1 of RPicView
	(properties
		y 141
		x 205
		view 163
		cel 2
	)
	
	(method (handleEvent event)
		(if
		(or (MousedOn self event 3) (Said 'examine/chair'))
			(Print 63 8)
			(event claimed: 1)
		)
	)
)

(instance chair2 of RPicView
	(properties
		y 141
		x 187
		view 163
		cel 3
	)
	
	(method (handleEvent event)
		(if (MousedOn self event 3)
			(Print 63 9)
			(event claimed: 1)
		)
	)
)

(instance BigDoll of RPicView
	(properties
		y 144
		x 113
		view 163
		loop 1
	)
	
	(method (handleEvent event)
		(cond 
			((Said 'play[/doll]') (Print 63 10) (event claimed: 1))
			((Said 'examine/doll')
				(if (== currentAct 2)
					(event claimed: 1)
				else
					(Print 63 11)
				)
			)
			((Said 'get/doll') (Print 63 12))
		)
	)
)

(instance SmallDoll of PV
	(properties
		y 141
		x 204
		z 10
		view 163
		loop 1
		cel 1
		priority 10
	)
)

(instance MedDoll of PV
	(properties
		y 138
		x 134
		view 163
		loop 1
		cel 2
	)
)

(instance Bear of PV
	(properties
		y 137
		x 150
		view 163
		loop 1
		cel 4
	)
)

(instance Poster of PV
	(properties
		y 139
		x 208
		view 163
		loop 2
		priority 2
	)
)

(instance Chalk of PV
	(properties
		y 139
		x 235
		view 163
		loop 2
		cel 1
	)
)
