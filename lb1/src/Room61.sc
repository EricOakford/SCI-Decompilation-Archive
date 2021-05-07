;;; Sierra Script 1.0 - (do not remove this comment)
(script# 61)
(include sci.sh)
(use Main)
(use Intrface)
(use RFeature)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	Room61 0
)
(synonyms
	(room cabin)
)

(local
	local0
	local1
)
(instance Room61 of Rm
	(properties
		picture 61
	)
	
	(method (init)
		(super init:)
		(if (= local0 (== ((inventory at: 3) owner?) 61))
			(OilCan init: stopUpd:)
		)
		(addToPics
			add: anchor harness preserver minnow
			eachElementDo: #init
			doit:
		)
		(self
			setFeatures: harness anchor preserver Car Box Boat Table
		)
		(Load rsVIEW 60)
		(if
			(and
				(>= currentAct 4)
				(not (& global123 $0040))
				(!= global200 101)
			)
			(cond 
				((== global170 61) (self setRegions: 268) (= local1 1))
				((not (== global170 5))
					(switch (Random 1 2)
						(1
							(= local1 1)
							(self setRegions: 268)
						)
						(2 (= global170 5))
					)
				)
			)
		)
		(ego view: 0 illegalBits: -32768 posn: 102 173 init:)
	)
	
	(method (doit)
		(if (FirstEntry) (Print 61 0))
		(if (& (ego onControl: 1) $0002) (curRoom newRoom: 20))
		(super doit:)
	)
	
	(method (dispose)
		(super dispose:)
	)
	
	(method (handleEvent event &tmp temp0)
		(super handleEvent: event)
		(if (event claimed?) (return 1))
		(return
			(if (== (event type?) evSAID)
				(cond 
					((Said 'examine>')
						(cond 
							((Said '[<around,at][/room]') (Print 61 0))
							((or (Said '/dirt') (Said '<down')) (Print 61 1))
							((Said '/wall') (Print 61 2))
							((or (Said '/ceiling') (Said '<up')) (Print 61 3))
							((Said '<(out,through)/window') (Print 61 4))
							((Said '/window') (Print 61 5))
							((Said '/door') (Print 61 6))
							((Said '<below/nightstand') (Print 61 7))
						)
					)
					((Said 'break/window') (Print 61 8))
					((Said 'open/window') (Print 61 9))
					((Said 'open/door') (Print 61 10))
					((Said 'get/crowbar')
						(if (& (ego onControl: 0) $0004)
							(if (== ((inventory at: 7) owner?) 61)
								(self setScript: getBar)
							else
								(Print 61 11)
							)
						else
							(NotClose)
						)
					)
				)
			else
				0
			)
		)
	)
	
	(method (newRoom n)
		(super newRoom: n)
	)
)

(instance getBar of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego illegalBits: 0 setMotion: MoveTo 173 129 self)
			)
			(1
				(Print 61 12)
				(ego get: 7 view: 60 cel: 0 setCycle: End self)
			)
			(2
				(= gotItem 1)
				(ego view: 0 loop: 0 setCycle: Walk illegalBits: -32768)
				(HandsOn)
			)
		)
	)
)

(instance anchor of RPicView
	(properties
		y 110
		x 26
		view 161
		priority 7
	)
	
	(method (handleEvent event)
		(cond 
			((Said 'get/anchor') (Print 61 13))
			(
			(or (MousedOn self event 3) (Said 'examine/anchor')) (event claimed: 1) (Print 61 14))
		)
	)
)

(instance harness of RPicView
	(properties
		y 79
		x 301
		view 161
		cel 1
		priority 4
	)
	
	(method (handleEvent event)
		(if
		(or (MousedOn self event 3) (Said 'examine/bit'))
			(event claimed: 1)
			(Print 61 15)
		)
	)
)

(instance preserver of RPicView
	(properties
		y 75
		x 23
		view 161
		cel 2
		priority 4
	)
	
	(method (handleEvent event)
		(cond 
			((Said 'get/preserver[<life]') (Print 61 16))
			(
				(or
					(MousedOn self event 3)
					(Said 'examine/preserver[<life]')
				)
				(event claimed: 1)
				(Print 61 17)
			)
		)
	)
)

(instance minnow of PV
	(properties
		y 141
		x 69
		view 161
		cel 3
		priority 11
	)
)

(instance Boat of RFeature
	(properties
		nsTop 120
		nsBottom 156
		nsRight 88
	)
	
	(method (handleEvent event)
		(cond 
			(
				(or
					(Said '(sit,go,climb,get)<in/boat')
					(Said 'enter/boat')
				)
				(Print 61 18)
			)
			((Said 'get/boat') (Print 61 19))
			((Said 'examine<below/boat,buggy') (Print 61 20))
			((Said 'search,(examine<in)/boat')
				(if (& (ego onControl: 0) $0008)
					(if local1 (Print 61 21) else (Print 61 22))
				else
					(NotClose)
				)
			)
			(
			(or (MousedOn self event 3) (Said 'examine/boat')) (event claimed: 1) (Print 61 23))
		)
	)
)

(instance Box of RFeature
	(properties
		nsTop 89
		nsLeft 243
		nsBottom 142
		nsRight 308
	)
	
	(method (handleEvent event)
		(cond 
			((Said 'examine,get/oar') (Print 61 24))
			((Said 'open/box,box') (Print 61 25))
			((Said 'get,move/box,box') (Print 61 26))
			((Said 'examine<in/box') (Print 61 25))
			(
			(or (MousedOn self event 3) (Said 'examine/box')) (event claimed: 1) (Print 61 27))
		)
	)
)

(instance Car of RFeature
	(properties
		nsTop 73
		nsLeft 170
		nsBottom 120
		nsRight 227
	)
	
	(method (handleEvent event)
		(cond 
			(
				(or
					(Said '(go,sit,climb,get)<in/buggy')
					(Said 'enter/buggy')
				)
				(Print 61 28)
			)
			((Said 'get/buggy') (Print 61 29))
			((Said 'search,(examine<in)/buggy')
				(if (& (ego onControl: 0) $0004)
					(if (== ((inventory at: 7) owner?) 61)
						(Room61 setScript: getBar)
					else
						(Print 61 11)
					)
				else
					(NotClose)
				)
			)
			(
			(or (MousedOn self event 3) (Said 'examine/buggy')) (event claimed: 1) (Print 61 30))
		)
	)
)

(instance OilCan of Prop
	(properties
		y 100
		x 90
		view 161
		cel 4
	)
	
	(method (handleEvent event)
		(cond 
			(
			(or (MousedOn self event 3) (Said 'examine/can')) (event claimed: 1) (Print 61 31))
			((Said 'get/can')
				(if (& (ego onControl: 1) $0010)
					(Ok)
					(ego get: 3)
					(= local0 0)
					(= gotItem 1)
					(OilCan dispose:)
				else
					(NotClose)
				)
			)
		)
	)
)

(instance myMusic of Sound
	(properties)
)

(instance Table of RFeature
	(properties
		nsTop 92
		nsLeft 65
		nsBottom 103
		nsRight 134
	)
	
	(method (handleEvent event)
		(if
		(or (MousedOn self event 3) (Said 'examine/nightstand'))
			(if local0 (Print 61 32) else (Print 61 33))
			(event claimed: 1)
		)
	)
)
