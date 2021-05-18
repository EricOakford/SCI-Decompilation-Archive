;;; Sierra Script 1.0 - (do not remove this comment)
(script# 41)
(include sci.sh)
(use Main)
(use Intrface)
(use RFeature)
(use Avoider)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	Room41 0
)
(synonyms
	(drawer chest dresser)
	(room bedroom)
	(armoire armoire)
)

(local
	local0
)
(instance Room41 of Rm
	(properties
		picture 41
	)
	
	(method (init)
		(= east 42)
		(super init:)
		(addToPics
			add: wardrobe chair1 chair2 dresser
			eachElementDo: #init
			doit:
		)
		(self
			setFeatures: lamp1 lamp2 wardrobe dresser chair1 chair2 Boxes
		)
		(cond 
			((<= currentAct 1) (bag init: stopUpd:) (Bclr 35))
			(
				(and
					(>= currentAct 6)
					(not (& deadGuests $0020))
					(not (& deadGuests $0040))
				)
				(cond 
					((== gCurRoomNum_3 41) (++ local0) (self setRegions: 278))
					((not (== gCurRoomNum_3 73))
						(switch (Random 1 2)
							(1
								(++ local0)
								(self setRegions: 278)
							)
							(2 (= gCurRoomNum_3 73))
						)
					)
				)
			)
		)
		(Bed
			cel: (if (Btst 35) 0 else 4)
			ignoreActors: 1
			init:
			stopUpd:
		)
		(Load rsVIEW 30)
		(if howFast
			(lamp1 setCycle: Fwd init:)
			(lamp2 setCycle: Fwd init:)
		else
			(lamp1 init: stopUpd:)
			(lamp2 init: stopUpd:)
		)
		(ego view: 0 posn: 304 122 illegalBits: -32760 init:)
	)
	
	(method (doit)
		(if (FirstEntry) (Print 41 0))
		(if (< (ego x?) 250)
			(= vertAngle 44)
		else
			(= vertAngle 0)
		)
		(super doit:)
	)
	
	(method (dispose)
		(super dispose:)
	)
	
	(method (handleEvent event &tmp temp0)
		(if (event claimed?) (return 1))
		(return
			(if (== (event type?) evSAID)
				(cond 
					((Said '*/blind,curtain') (Print 41 1))
					((Said 'examine>')
						(cond 
							(
							(and (not (cast contains: bag)) (Said '/bag')) (DontSee))
							((Said '[<around,at][/room]') (Print 41 0))
							((Said '/furniture[<covered]') (Print 41 2))
							((Said '<in/closet') (Print 41 3))
							((Said '/closet') (Print 41 4))
							((Said '<below/cover[<furniture]') (Print 41 5) (Print 41 6))
						)
					)
					((Said 'get/cover[<furniture]') (Print 41 5))
					((Said 'open/closet') (Print 41 7))
					(
						(or
							(Said 'uncover/furniture')
							(Said 'detach,get/cover')
						)
						(Print 41 5)
					)
					(
						(or
							(Said 'open/(bed[<murphy]),door')
							(Said 'lower,drag/bed[<murphy]')
						)
						(if (Btst 35)
							(self setScript: myBed)
						else
							(AlreadyOpen)
						)
					)
					(
					(Said 'close,lift,lift,attach,press/bed[<murphy,up,away]')
						(if (not (cast contains: bag))
							(cond 
								(
								(and (== gCurRoomNum_3 41) (not (& deadGuests $0020))) (Print 41 8))
								((not (Btst 35)) (self setScript: myBed))
								(else (AlreadyClosed))
							)
						else
							(Bset 31)
							(Print 41 9)
						)
					)
				)
			else
				0
			)
		)
	)
	
	(method (newRoom n)
		(DisposeScript 985)
		(super newRoom: n)
	)
)

(instance myBed of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if (Btst 35)
					(ego
						setAvoider: (Avoid new:)
						setMotion: MoveTo 243 90 self
					)
				else
					(ego
						setAvoider: (Avoid new:)
						setMotion: MoveTo 189 99 self
					)
				)
			)
			(1
				(if (Btst 35)
					(Bclr 35)
					(ego loop: 1 observeControl: 8)
					(Bed cycleSpeed: 3 setCycle: End self)
				else
					(Bset 35)
					(ego
						view: 30
						cel: 0
						loop: 1
						setCycle: End
						ignoreControl: 8
					)
					(Bed cycleSpeed: 3 setCycle: Beg self)
				)
			)
			(2
				(Bed stopUpd:)
				(if (Btst 35)
					(ego
						view: 0
						loop: 3
						posn: 179 99
						setAvoider: 0
						setCycle: Walk
					)
				)
				(HandsOn)
				(self setScript: 0)
			)
		)
	)
)

(instance wardrobe of RPicView
	(properties
		y 95
		x 112
		view 141
		loop 1
		priority 3
	)
	
	(method (handleEvent event)
		(cond 
			((Said '(examine<in),open/armoire') (Print 41 7))
			(
			(or (MousedOn self event 3) (Said 'examine/armoire')) (event claimed: 1) (Print 41 4))
		)
	)
)

(instance chair1 of RPicView
	(properties
		y 90
		x 132
		view 141
		loop 1
		cel 3
		priority 5
	)
	
	(method (handleEvent event)
		(if (MousedOn self event 3)
			(event claimed: 1)
			(ParseName {chair})
		)
	)
)

(instance dresser of RPicView
	(properties
		y 89
		x 292
		view 141
		loop 1
		cel 1
		priority 5
	)
	
	(method (handleEvent event)
		(if
			(or
				(MousedOn self event 3)
				(Said 'examine[<!*]/drawer')
			)
			(event claimed: 1)
			(Print 41 10)
		)
	)
)

(instance chair2 of RPicView
	(properties
		y 113
		x 93
		view 141
		loop 1
		cel 2
		priority 7
	)
	
	(method (handleEvent event)
		(cond 
			((MousedOn self event 3) (event claimed: 1) (ParseName {chair}))
			((Said '(examine<in),open/box') (Print 41 11))
			((Said 'examine/box') (Print 41 12))
			((Said 'move,get/box') (Print 41 13))
		)
	)
)

(instance Bed of Prop
	(properties
		y 29
		x 232
		view 141
		loop 2
	)
	
	(method (handleEvent event)
		(cond 
			((Said 'examine<below/bed') (Print 41 14))
			(
				(or
					(MousedOn self event 3)
					(Said 'examine/bed[<murphy]')
				)
				(event claimed: 1)
				(cond 
					(local0 (Print 41 15))
					((== (Bed cel?) 0) (Print 41 16))
					((cast contains: bag) (Bset 31) (Print 41 17))
					(else (Print 41 18))
				)
			)
		)
	)
)

(instance bag of Prop
	(properties
		y 79
		x 212
		view 141
		loop 1
		cel 4
	)
	
	(method (handleEvent event)
		(if (not (event claimed?))
			(cond 
				((Said 'search,move,get,open,(examine<in)/bag') (Print 41 19))
				(
				(or (MousedOn self event 3) (Said 'examine/bag')) (event claimed: 1) (Print 41 20))
			)
			(if (event claimed?) (Bset 31))
		)
	)
)

(instance lamp1 of Prop
	(properties
		y 51
		x 89
		view 141
		priority 1
	)
	
	(method (handleEvent event)
		(if (MousedOn self event 3)
			(event claimed: 1)
			(ParseName {lamp})
		)
	)
)

(instance lamp2 of Prop
	(properties
		y 63
		x 61
		view 141
		cel 1
		priority 2
	)
	
	(method (handleEvent event)
		(if (MousedOn self event 3)
			(event claimed: 1)
			(ParseName {lamp})
		)
	)
)

(instance Boxes of RFeature
	(properties
		nsTop 108
		nsLeft 50
		nsBottom 169
		nsRight 308
	)
	
	(method (handleEvent event)
		(cond 
			((Said '(examine<in),open/box') (Print 41 11))
			((Said 'move,get/box') (Print 41 13))
			(
			(or (MousedOn self event 3) (Said 'examine/box')) (event claimed: 1) (Print 41 12))
		)
	)
)
