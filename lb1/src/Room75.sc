;;; Sierra Script 1.0 - (do not remove this comment)
(script# 75)
(include sci.sh)
(use Main)
(use ElevGate)
(use Intrface)
(use RFeature)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	Room75 0
)
(synonyms
	(newspaper letter)
	(room attic)
)

(local
	local0
	local1
)
(procedure (localproc_000c)
	(Print
		&rest
		#at
		10
		125
		#font
		4
		#width
		250
		#mode
		1
		#dispose
	)
)

(instance Room75 of Rm
	(properties
		picture 75
	)
	
	(method (init)
		(= currentPalette 0)
		(super init:)
		(if (not global388)
			(= global388
				(| (<< gameHours $0008) (* gameMinutes 15))
			)
		)
		(self setFeatures: Window1 Shaft Junk)
		(if (& deadGuests $0040)
			(User canControl: 0 canInput: 1)
			(= saveDisabled 1)
			(self setRegions: 290)
		else
			(head init: hide: stopUpd:)
			(arms init: hide: stopUpd:)
		)
		(if (!= prevRoomNum 66)
			(if (== prevRoomNum 74)
				(ego posn: 230 188)
			else
				(if (== prevRoomNum 42) (Bclr 46))
				(ego posn: 80 188)
			)
			(ego view: 0 illegalBits: -32768)
		)
		(paper init: stopUpd:)
		((= gGate gate)
			chainID: chain
			elevatorID: elevator
			downID: down
			upID: up
			msgID:
				{What a dark and creepy attic! It helps to have the moonlight shining in through those big windows. Among all the junk, a stack of old newspapers catches your eye.}
			init:
		)
	)
	
	(method (doit)
		(if
			(and
				(not (& deadGuests $0040))
				(not (& global109 $0010))
				(FirstEntry)
			)
			(Print 75 0)
		)
		(if (== (ego edgeHit?) 3)
			(if (< (ego x?) 189)
				(curRoom newRoom: 76)
			else
				(curRoom newRoom: 74)
			)
		)
		(cond 
			((< (ego x?) 130) (= vertAngle 30))
			((< (ego x?) 190) (= vertAngle 0))
			(else (= vertAngle 150))
		)
		(super doit:)
	)
	
	(method (dispose)
		(DisposeScript 201)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(super handleEvent: event)
		(if (event claimed?) (return))
		(if (== (event type?) evSAID)
			(cond 
				((Said 'get,move/box') (Print 75 1))
				((Said 'examine>')
					(cond 
						((Said '[<around,at][/room]')
							(if (& deadGuests $0040)
								(Print 75 2)
							else
								(Print 75 0)
							)
						)
						((Said '<in/box') (Print 75 3))
						((Said '/box') (Print 75 4))
						((Said '<in/chest') (Print 75 5))
						((Said '/chest') (Print 75 6))
						((Said '[<at]/window') (Print 75 7))
					)
				)
				((Said 'open>')
					(cond 
						((Said '/box') (Print 75 3))
						((Said '/chest') (Print 75 5))
					)
				)
			)
		)
	)
	
	(method (newRoom n)
		(super newRoom: n)
	)
)

(instance readNewspaper of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego illegalBits: 0 setMotion: MoveTo 32 127 self)
			)
			(1
				(ego
					view: 61
					cel: 0
					loop: 1
					cycleSpeed: 1
					setCycle: End self
				)
			)
			(2
				(paper hide: forceUpd:)
				(ego loop: 2 cel: 0)
				(head posn: (+ (ego x?) 4) (- (ego y?) 38) show:)
				(arms posn: (- (ego x?) 3) (- (ego y?) 26) show:)
				(= cycles 2)
			)
			(3 (Print 75 8) (= cycles 1))
			(4
				(++ local1)
				(head setCycle: Fwd)
				(switch local1
					(1 (localproc_000c 75 9))
					(2 (localproc_000c 75 10))
					(3 (localproc_000c 75 11))
				)
			)
			(5
				(cls)
				(if (< local1 3)
					(= state 3)
					(arms setCycle: End self)
				else
					(= cycles 1)
				)
			)
			(6
				(paper show: forceUpd:)
				(ego loop: 1)
				(ego cel: (ego lastCel:) setCycle: Beg self)
				(head hide: forceUpd:)
				(arms hide: forceUpd:)
			)
			(7
				(Print 75 12)
				(= local0 1)
				(HandsOn)
				(ego
					view: 0
					loop: 1
					cycleSpeed: 0
					illegalBits: -32768
					setCycle: Walk
				)
				(head dispose:)
				(arms dispose:)
				(client setScript: 0)
			)
		)
	)
	
	(method (handleEvent event)
		(if
			(and
				(not (event claimed?))
				(or
					(== (event type?) evMOUSEBUTTON)
					(== (event type?) evKEYBOARD)
					(== (event type?) evJOYSTICK)
				)
				(== state 4)
			)
			(if (!= (event type?) evJOYSTICK)
				(= seconds 0)
				(= cycles 1)
			)
			(event claimed: 1)
		)
	)
)

(instance paper of Prop
	(properties
		y 127
		x 17
		view 61
	)
	
	(method (handleEvent event)
		(if
			(or
				(Said 'get,read,examine/newspaper')
				(MousedOn self event 3)
			)
			(event claimed: 1)
			(cond 
				((& global109 $0010) (Print 75 13))
				(local0 (Print 75 14))
				((< (ego distanceTo: paper) 50) (Room75 setScript: readNewspaper))
				(else (NotClose))
			)
		)
	)
)

(instance head of Prop
	(properties
		view 61
		loop 3
		cycleSpeed 3
	)
)

(instance arms of Prop
	(properties
		view 61
		loop 4
		cycleSpeed 1
	)
)

(instance Window1 of RFeature
	(properties
		nsTop 16
		nsLeft 97
		nsBottom 82
		nsRight 207
	)
	
	(method (handleEvent event)
		(if (MousedOn self event 3)
			(event claimed: 1)
			(ParseName {window})
		)
	)
)

(instance Shaft of RFeature
	(properties
		nsTop 52
		nsLeft 38
		nsBottom 113
		nsRight 66
	)
	
	(method (handleEvent event)
		(if (MousedOn self event 3)
			(event claimed: 1)
			(ParseName {elevator})
		)
	)
)

(instance Junk of RFeature
	(properties
		nsTop 30
		nsLeft 266
		nsBottom 129
		nsRight 319
	)
	
	(method (handleEvent event)
		(if
			(or
				(MousedOn self event 3)
				(Said 'examine/garbage,possession,furniture')
			)
			(Print 75 15)
			(event claimed: 1)
		)
	)
)

(instance gate of ElevGate
	(properties)
)

(instance chain of Act
	(properties)
)

(instance elevator of Act
	(properties
		y -10
	)
)

(instance down of View
	(properties)
)

(instance up of View
	(properties)
)
