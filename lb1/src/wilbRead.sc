;;; Sierra Script 1.0 - (do not remove this comment)
(script# 220)
(include sci.sh)
(use Main)
(use servent)
(use Intrface)
(use Avoider)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	wilbRead 0
)
(synonyms
	(c person fellow)
)

(local
	local0
	local1
)
(procedure (localproc_000c)
	(Gloria stopUpd:)
	(Wilbur loop: 2 setCycle: Fwd)
	(Print
		&rest
		#at
		80
		15
		#font
		4
		#width
		125
		#mode
		1
		#dispose
	)
)

(procedure (localproc_0040)
	(Wilbur stopUpd:)
	(Gloria setCycle: Fwd)
	(Print
		&rest
		#at
		160
		15
		#font
		4
		#width
		125
		#mode
		1
		#dispose
	)
)

(instance Jeeves of servent
	(properties)
)

(instance wilbRead of Rgn
	(properties)
	
	(method (init)
		(super init:)
		(LoadMany 135 4 41)
		(LoadMany 128 642 907)
		(LoadMany 132 29 94 95 96)
		(LoadMany 143 243 219 406)
		(= global208 128)
		(= [global377 7] 219)
		(Wilbur init: stopUpd: setScript: reading)
		(cond 
			((and (> [global368 2] 1) (== global199 1))
				(= [global368 2] 0)
				(Load rsVIEW 361)
				(if (== prevRoomNum 33)
					(ego posn: (ego x?) 145)
					(Gloria posn: 290 151)
				)
				(Gloria init: setScript: wilbGlor)
				(= global199 2)
			)
			((not (& global194 $0008))
				(= global194 (| global194 $0008))
				(Jeeves
					view: 444
					posn: 320 150
					setCycle: Walk
					guest1: Wilbur
					exitX: 340
					exitY: 150
					itemX: 197
					itemY: 149
					setAvoider: ((Avoid new:) offScreenOK: 1)
					init:
				)
				(= global167 1)
			)
		)
		(ego observeBlocks: WilburBlock)
	)
	
	(method (doit)
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
					((Said '/c>')
						(cond 
							((Said 'hear') (Print 220 0))
							((Said 'converse')
								(= theTalker 8)
								(switch local0
									(0
										(Say 1 220 1)
										(= theTalker 12)
										(Say 0 220 2)
									)
									(1
										(Say 1 220 3)
										(= theTalker 12)
										(Say 0 220 4)
									)
									(2
										(Say 1 220 5)
										(= theTalker 12)
										(Say 0 220 6)
									)
									(3 (Print 220 7))
									(else  (Print 220 8))
								)
								(++ local0)
							)
						)
					)
					((Said '/magazine>')
						(cond 
							((Said 'get,rob') (Print 220 9))
							((Said 'examine,read')
								(if (< (ego distanceTo: Wilbur) 20)
									(Bset 17)
									(Print 220 10)
								else
									(Print 220 11)
								)
							)
						)
					)
				)
			else
				0
			)
		)
	)
)

(instance reading of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(Wilbur loop: (if local1 5 else 0) setCycle: End self)
			)
			(1
				(= temp0 (Random 1 100))
				(cond 
					(local1
						(cond 
							((< temp0 43) (= state 2))
							((> temp0 56) (= state 5))
							(else (= state -1))
						)
					)
					((< temp0 51) (= state -1))
				)
				(= seconds (Random 5 10))
			)
			(2
				(= local1 1)
				(Wilbur loop: 4 cel: 0 setCycle: End)
				(= seconds (Random 5 10))
				(= state 0)
			)
			(3
				(Wilbur loop: 6 cel: 0 setCycle: End self)
			)
			(4
				(Wilbur loop: 7 cel: 0 setCycle: Fwd)
				(= seconds 3)
			)
			(5
				(Wilbur loop: 6 cel: 3 setCycle: Beg self)
				(= state 0)
			)
			(6
				(= local1 0)
				(Wilbur loop: 4 cel: 4 setCycle: End self)
				(= state 0)
			)
		)
	)
)

(instance wilbGlor of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(Bset 24)
				(Gloria setCycle: Walk setMotion: MoveTo 218 142 self)
			)
			(1
				(Wilbur loop: 1 cel: 0 setCycle: End)
				(Gloria view: 361 loop: 2)
				(localproc_0040 220 12)
				(= seconds 7)
			)
			(2
				(cls)
				(localproc_000c 220 13)
				(= seconds 7)
			)
			(3
				(cls)
				(localproc_0040 220 14)
				(= seconds 7)
			)
			(4
				(cls)
				(localproc_000c 220 15)
				(= seconds 5)
			)
			(5
				(cls)
				(localproc_0040 220 16)
				(= seconds 2)
			)
			(6
				(cls)
				(Gloria view: 360 setMotion: MoveTo 340 152 self)
				(Wilbur loop: 1 cel: 1 setCycle: Beg)
			)
			(7
				(HandsOn)
				(Wilbur setScript: reading)
				(Gloria dispose:)
				(client setScript: 0)
			)
		)
	)
)

(instance Wilbur of Act
	(properties
		y 142
		x 196
		view 424
		illegalBits $0000
	)
	
	(method (handleEvent event)
		(cond 
			(
			(and (MousedOn self event 3) (not (& global207 $0080))) (event claimed: 1) (ParseName {wilbur}))
			(
				(and
					(& global207 $0080)
					(or (MousedOn self event 3) (Said 'examine/c'))
				)
				(event claimed: 1)
				(Print 220 17)
			)
		)
	)
)

(instance WilburBlock of Blk
	(properties
		top 138
		left 196
		bottom 142
		right 204
	)
)

(instance Gloria of Act
	(properties
		y 150
		x 310
		view 360
		loop 1
	)
)
