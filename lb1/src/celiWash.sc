;;; Sierra Script 1.0 - (do not remove this comment)
(script# 229)
(include game.sh)
(use Main)
(use Intrface)
(use Avoider)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	celiWash 0
)
(synonyms
	(celie person girl)
)

(local
	talkCount
	[local1 2]
	gaveBoneToDog
)
(instance celiWash of Region
	
	(method (init)
		(super init:)
		(LoadMany 143 243 228)
		(LoadMany VIEW 480 22 522 901)
		(= global208 2)
		(= [global377 1] 228)
		(Celie illegalBits: 0 init: setScript: wash)
		(if (== ((inventory at: iSoupBone) owner?) 0)
			(Rover
				view: 522
				loop: 4
				cycleSpeed: 2
				setCycle: Forward
				init:
			)
		else
			(Rover init: setScript: dogActions)
		)
	)
	
	(method (doit)
		(super doit:)
	)
	
	(method (dispose)
		(DisposeScript AVOIDER)
		(super dispose:)
	)
	
	(method (handleEvent event &tmp temp0)
		(if (event claimed?) (return TRUE))
		(return
			(if (== (event type?) saidEvent)
				(cond 
					((Said 'converse/celie')
						(= theTalker talkCELIE)
						(switch talkCount
							(0 (Say 1 229 0))
							(1 (Say 1 229 1))
							(2 (Say 1 229 2))
							(else  (Say 1 229 3))
						)
						(++ talkCount)
					)
					(
						(or
							(Said 'feed,deliver/bone/beauregard')
							(Said 'feed,deliver/beauregard/bone')
						)
						(if (ego has: iSoupBone)
							(if (< (ego distanceTo: Rover) 60)
								(self setScript: giveBone)
							else
								(NotClose)
							)
						else
							(DontHave)
						)
					)
					(
						(or
							(Said 'deliver,feed/*<beauregard')
							(Said 'deliver,feed/*/beauregard')
							(Said 'feed/beauregard')
						)
						(if theInvItem
							(if haveInvItem
								(Print 229 4)
							else
								(DontHave)
							)
						else
							(Print 229 5)
						)
					)
					(
						(or
							(Said 'hold/*/beauregard')
							(Said 'hold/*<beauregard')
						)
						(if (and theInvItem haveInvItem)
							(if (== whichItem iSoupBone)
								(Print 229 6)
							else
								(Print 229 4)
							)
						else
							(DontHave)
						)
					)
					(
						(or
							(Said 'deliver/*/beauregard')
							(Said 'deliver/*<beauregard')
						)
						(if haveInvItem
							(Print 229 4)
						else
							(DontHave)
						)
					)
					((Said '/beauregard>')
						(cond 
							((Said 'get,move')
								(Print 229 7)
							)
							((Said 'pat')
								(if (< (ego distanceTo: Rover) 60)
									(Print 229 8)
									(self setScript: petDog)
								else
									(NotClose)
								)
							)
							((Said 'converse')
								(Print 229 9)
							)
							((Said 'call')
								(Print 229 10)
							)
							((Said 'kill')
								(Print 229 11)
							)
							((Said 'kiss')
								(Print 229 12)
							)
						)
					)
					(
						(or
							(Said 'dry,scrub/dish')
							(Said 'help/celie')
							(Said 'help/dish/scrub<celie')
						)
						(Print 229 13)
					)
				)
			else
				FALSE
			)
		)
	)
)

(instance wash of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(cls)
				(Celie loop: 0 setCycle: Forward)
				(= seconds (Random 6 12))
			)
			(1
				(Celie cel: 0 loop: 1 setCycle: EndLoop self)
				(= state
					(switch (Random 1 2)
						(1 -1)
						(2 1)
					))
			)
			(2
				(Celie loop: 0 stopUpd:)
				(= state -1)
				(= seconds (Random 3 5))
			)
		)
	)
)

(instance petDog of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					setAvoider: (Avoider new:)
					setMotion: MoveTo (+ (Rover x?) 26) (+ (Rover y?) 5) self
				)
			)
			(1
				(ego view: 22 loop: 0 setAvoider: 0 setCycle: EndLoop self)
			)
			(2
				(ego loop: 5 cel: 0 setCycle: EndLoop self)
			)
			(3
				(ego loop: 7 setCycle: Forward)
				(= seconds 3)
			)
			(4
				(ego loop: 5)
				(ego cel: (- (NumCels ego) 1) setCycle: BegLoop self)
			)
			(5
				(ego loop: 0)
				(ego cel: (- (NumCels ego) 1) setCycle: BegLoop self)
			)
			(6
				(HandsOn)
				(ego view: 0 loop: 1 setCycle: Walk)
				(client setScript: 0)
			)
		)
	)
)

(instance dogActions of Script
	
	(method (doit)
		(super doit:)
		(cond 
			((not gaveBoneToDog)
				(if (and (< (ego distanceTo: Rover) 30) (== state 0))
					(= cycles 1)
				)
				(if (and (> (ego distanceTo: Rover) 30) (== state 2))
					(= state -1)
					(= cycles 1)
				)
			)
			((< state 3)
				(= state 2)
				(= cycles 1)
			)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Rover
					loop: 0
					cycleSpeed: 2
					cel: (- (NumCels Rover) 1)
					setCycle: BegLoop
				)
			)
			(1
				(Rover loop: 0 cycleSpeed: 2 setCycle: EndLoop self)
			)
			(2
				(Rover loop: 2 cycleSpeed: 2 setCycle: Forward)
			)
			(3
				(Bone dispose:)
				(Rover view: 522 loop: 4 cycleSpeed: 2 setCycle: Forward)
			)
		)
	)
)

(instance giveBone of Script

	(method (doit)
		(super doit:)
		(return
			(if (and (== state 1) (== (ego cel?) 4))
				(ego put: 12 0)
				(Bone init:)
				(++ gaveBoneToDog)
			else
				0
			)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					setAvoider: (Avoider new:)
					setMotion: MoveTo (+ (Rover x?) 31) (+ (Rover y?) 3) self
				)
			)
			(1
				(ego view: 48 setLoop: 7 cel: 8 setCycle: BegLoop self)
				(Bone cel: 0 posn: (- (ego x?) 13) (- (ego y?) 1))
			)
			(2
				(Print 229 14)
				(ego view: 0 loop: 1)
				(= cycles 1)
			)
			(3
				(ego view: 0 setAvoider: FALSE setLoop: -1 setCycle: Walk)
				(HandsOn)
				(self setScript: 0)
			)
		)
	)
)

(instance Bone of Prop
	(properties
		y 95
		x 97
		view 522
		loop 8
	)
)

(instance Rover of Prop
	(properties
		y 90
		x 97
		view 527
	)
	
	(method (handleEvent event)
		(if
			(or
				(MousedOn self event shiftDown)
				(Said 'examine/beauregard,dirt')
			)
			(event claimed: TRUE)
			(if (Btst fLookedAtDog)
				(if gaveBoneToDog
					(Print 229 15)
				else
					(Print 229 16)
				)
			else
				(Bset fLookedAtDog)
				(Print 229 17)
			)
		)
	)
)

(instance Celie of Actor
	(properties
		y 103
		x 214
		view 486
	)
	
	(method (handleEvent event)
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
				(Print 229 18)
			)
		)
	)
)
