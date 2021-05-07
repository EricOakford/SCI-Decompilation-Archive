;;; Sierra Script 1.0 - (do not remove this comment)
(script# 53)
(include sci.sh)
(use Main)
(use Intrface)
(use RFeature)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	Room53 0
)
(synonyms
	(cracker box)
	(room bedroom)
)

(local
	[local0 2]
	local2
	local3
)
(instance Room53 of Rm
	(properties
		picture 53
	)
	
	(method (init)
		(= horizon 60)
		(= north 12)
		(super init:)
		(Load rsVIEW 910)
		(addToPics
			add: bed chair sofa chest dresser1 sink toilet
			eachElementDo: #init
			doit:
		)
		(self
			setRegions: 246
			setFeatures: bed chest sink toilet sofa chair dresser1
		)
		(if howFast
			(lamp1 setCycle: Fwd init:)
		else
			(lamp1 init: stopUpd:)
		)
		(door1 init: stopUpd:)
		(door2 init: stopUpd:)
		(if (not (ego has: 11))
			(crackerBox setPri: 10 init: stopUpd:)
		)
		(ego view: 0 illegalBits: -32768 posn: 155 64 init:)
		(self setScript: stairWell)
	)
	
	(method (doit)
		(if (FirstEntry) (Print 53 0))
		(super doit:)
		(if
			(and
				(& (ego onControl:) $0002)
				(== (ego loop?) 0)
				(== local2 0)
			)
			(= local2 1)
			(self setScript: stairWell)
		)
	)
	
	(method (dispose)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(super handleEvent: event)
		(if (event claimed?) (return 1))
		(return
			(if (== (event type?) evSAID)
				(cond 
					((Said 'examine>')
						(cond 
							((Said '[<around,at][/room]') (Print 53 0))
							((Said '/brick') (Print 53 1))
							((Said '/stair,upstair') (Print 53 2))
							((Said '/wall') (Print 53 3))
							((Said '<in/closet') (Print 53 4))
							((Said '/closet') (Print 53 5))
						)
					)
					((Said 'open>')
						(cond 
							((Said '/dresser') (Print 53 6))
							((Said '/closet') (Print 53 4))
						)
					)
					((Said 'get/brick') (Print 53 7))
					((Said 'get/drink') (Print 53 8))
					((Said 'get,move/carpet') (Print 53 9))
					((Said 'sit,go,use/bathroom,toilet') (Print 53 10))
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

(instance dresser1 of RPicView
	(properties
		y 131
		x 271
		view 153
		loop 1
		cel 2
		priority 9
	)
	
	(method (handleEvent event)
		(if (MousedOn self event 3)
			(event claimed: 1)
			(Print 53 11)
		)
	)
)

(instance bed of RPicView
	(properties
		y 162
		x 254
		view 153
		loop 1
		priority 12
	)
	
	(method (handleEvent event)
		(if
		(or (MousedOn self event 3) (Said 'examine[<at]/bed'))
			(cond 
				((== currentAct 4) (Print 53 12))
				((== currentAct 4) (Print 53 13))
				(else (Print 53 14))
			)
			(event claimed: 1)
		)
	)
)

(instance chair of RPicView
	(properties
		y 120
		x 260
		view 153
		loop 1
		cel 3
		priority 8
	)
	
	(method (handleEvent event)
		(if
		(or (MousedOn self event 3) (Said 'examine/chair'))
			(event claimed: 1)
			(Print 53 15)
		)
	)
)

(instance sofa of RPicView
	(properties
		y 162
		x 94
		view 153
		loop 1
		cel 4
		priority 12
	)
	
	(method (handleEvent event)
		(if
		(or (MousedOn self event 3) (Said 'examine/couch'))
			(event claimed: 1)
			(Print 53 16)
		)
	)
)

(instance chest of RPicView
	(properties
		y 96
		x 189
		view 153
		loop 1
		cel 1
		priority 6
	)
	
	(method (handleEvent event)
		(cond 
			((Said 'open,(examine<in)/nightstand') (Print 53 17))
			((Said 'examine/nightstand')
				(if (ego has: 11)
					(Print 53 18)
				else
					(event claimed: 0)
				)
			)
			((Said 'examine<in/dresser') (Print 53 6))
			(
			(or (MousedOn self event 3) (Said 'examine/dresser')) (event claimed: 1) (Print 53 11))
		)
	)
)

(instance sink of RPicView
	(properties
		y 160
		x 35
		view 153
		cel 4
		priority 15
	)
	
	(method (handleEvent event)
		(cond 
			(
			(or (Said 'scrub,rotate/deliver,water') (Said 'scrub')) (Print 53 19))
			((Said 'examine<in/sink') (Print 53 20))
			(
			(or (MousedOn self event 3) (Said 'examine/sink')) (event claimed: 1) (Print 53 21))
		)
	)
)

(instance toilet of RPicView
	(properties
		y 115
		x 78
		view 153
		cel 5
		priority 7
	)
	
	(method (handleEvent event)
		(cond 
			((Said 'examine/bathroom') (Print 53 22))
			(
				(or
					(Said 'flush,use/toilet,bathroom')
					(Said 'drag/chain,handle')
				)
				(Print 53 23)
			)
			((Said 'open,(examine<in)/toilet') (Print 53 24))
			(
				(or
					(MousedOn self event 3)
					(Said 'examine/toilet')
					(Said 'sit/toilet')
				)
				(event claimed: 1)
				(Print 53 23)
			)
		)
	)
)

(instance door1 of Prop
	(properties
		y 89
		x 216
		view 153
		cel 2
		priority 5
	)
)

(instance door2 of Prop
	(properties
		y 89
		x 238
		view 153
		cel 2
		priority 5
	)
)

(instance lamp1 of Prop
	(properties
		y 86
		x 282
		view 153
		loop 5
	)
	
	(method (handleEvent event)
		(if
		(or (MousedOn self event 3) (Said 'examine/lamp'))
			(event claimed: 1)
			(Print 53 25)
		)
	)
)

(instance crackerBox of Prop
	(properties
		y 117
		x 270
		view 153
		loop 3
	)
	
	(method (handleEvent event)
		(cond 
			((not (ego has: 11))
				(cond 
					(
					(or (MousedOn self event 3) (Said 'examine/cracker')) (event claimed: 1) (Print 53 26))
					((Said 'ask/butler/cracker<for') (= theTalker 11) (Say 1 53 27) (++ local3))
					((Said 'get/cracker')
						(if (< (ego distanceTo: crackerBox) 30)
							(if (not local3) (Print 53 28))
							(= gotItem 1)
							(ego get: 11)
							(crackerBox dispose:)
						else
							(NotClose)
						)
					)
					((Said 'examine/nightstand') (Print 53 29))
				)
			)
			((Said 'get/cracker') (Print 53 30))
		)
	)
)

(instance stairWell of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if local2
					(ego illegalBits: 0 setMotion: MoveTo 187 37 self)
				else
					(ego setMotion: MoveTo 121 82 self)
				)
			)
			(1
				(HandsOn)
				(client setScript: 0)
			)
		)
	)
)
