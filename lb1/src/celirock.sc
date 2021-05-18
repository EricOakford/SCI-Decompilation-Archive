;;; Sierra Script 1.0 - (do not remove this comment)
(script# 266)
(include game.sh)
(use Main)
(use Intrface)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	celirock 0
)
(synonyms
	(celie chair person girl)
)

(local
	talkCount
)
(instance cBlock of Block
	(properties
		top 101
		left 260
		bottom 103
		right 290
	)
)

(instance celirock of Region
	
	(method (init)
		(super init:)
		(Load FONT 4)
		(LoadMany VIEW 480 901)
		(= global208 2)
		(if global135
			(LoadMany 143 243 283)
			(= [global377 1] 283)
		else
			(LoadMany 143 243 228)
			(= [global377 1] 228)
		)
		(Celie cycleSpeed: 1 setCycle: Forward init:)
		(chair init: hide:)
		(myMusic number: 33 loop: -1 play:)
		(ego observeBlocks: cBlock)
	)
	
	(method (doit)
		(super doit:)
	)
	
	(method (dispose)
		(super dispose:)
	)
	
	(method (handleEvent event &tmp temp0 temp1)
		(super handleEvent: event)
		(if (event claimed?) (return TRUE))
		(= theTalker talkCELIE)
		(return
			(cond 
				((Said 'examine/darning')
					(Print 266 0)
				)
				((Said 'get/darning')
					(Print 266 1)
				)
				((Said 'hold,deliver/necklace')
					(if (ego has: iNecklace)
						(if (< (ego distanceTo: Celie) 40)
							(Say 1 266 2)
							(ego put: iNecklace)
							(= global135 1)
							(= temp0 7)
							(= temp1 0)
							(while (< temp1 7)
								(= [global225 temp0] 0)
								(++ temp0)
								(++ temp1)
							)
							(self setScript: enter)
						else
							(NotClose)
						)
					else
						(DontHave)
					)
				)
				((Said 'hear/celie')
					(Print 266 3)
				)
				((Said 'converse/celie')
					(if global135
						(switch talkCount
							(0 (Say 1 266 4))
							(1 (Say 1 266 5))
							(2 (Say 1 266 6))
							(3 (Say 1 266 7))
							(4 (Say 1 266 8))
							(5 (Say 1 266 9))
							(else  (Print 266 10))
						)
					else
						(switch talkCount
							(0 (Say 1 266 11))
							(1 (Say 1 266 12))
							(2 (Say 1 266 13))
							(3 (Print 266 14))
							(else  (Print 266 15))
						)
					)
					(++ talkCount)
				)
			)
		)
	)
)

(instance enter of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if (not (& (ego onControl: origin) cBLACK))
					(ego setMotion: MoveTo 218 100 self)
				else
					(= cycles 1)
				)
			)
			(1
				(Celie loop: 3 cel: 0 setCycle: EndLoop self)
			)
			(2
				(ego loop: 3)
				(chair show:)
				(Celie
					view: 480
					posn: 250 102
					setCycle: Walk
					setMotion: MoveTo 240 97 self
				)
			)
			(3
				(Face Celie gDoor)
				(gDoor setCycle: EndLoop self)
			)
			(4
				(Celie
					illegalBits: 0
					ignoreActors: TRUE
					setMotion: MoveTo 240 91 self
				)
			)
			(5
				(curRoom newRoom: 59)
				(client setScript: 0)
			)
		)
	)
)

(instance Celie of Actor
	(properties
		y 102
		x 270
		view 484
		loop 2
	)
	
	(method (handleEvent event)
		(super handleEvent: event)
		(cond 
			((and (MousedOn self event shiftDown) (not (& global207 $0002)))
				(event claimed: TRUE)
				(ParseName {celie})
			)
			(
				(and
					(& global207 $0002)
					(or (MousedOn self event shiftDown) (Said 'examine/celie'))
				)
				(event claimed: TRUE)
				(Print 266 16)
			)
		)
	)
)

(instance frontDoor of Prop
	(properties
		y 93
		x 227
		view 106
		priority 5
	)
)

(instance chair of Prop
	(properties
		y 102
		x 270
		view 484
		loop 1
		cel 1
	)
)

(instance myMusic of Sound)
