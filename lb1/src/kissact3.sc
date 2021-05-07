;;; Sierra Script 1.0 - (do not remove this comment)
(script# 383)
(include sci.sh)
(use Main)
(use Intrface)
(use Avoider)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	kissact3 0
)
(synonyms
	(fifi girl)
	(butler fellow)
)

(local
	[local0 12] = [107 139 163 139 230 139 98 155 175 155 258 155]
	[local12 8] = [163 94 222 112 177 92 90 94]
	local20
	local21
	local22
	local23
)
(instance myMusic of Sound
	(properties)
)

(instance kissact3 of Rgn
	(properties)
	
	(method (init)
		(super init:)
		(Bset 22)
		(LoadMany 128 467 470 447 459)
		(Load rsFONT 4)
		(Load rsSCRIPT 985)
		(Load rsSOUND 51)
		(= global192 1)
		(LoadMany 143 243 294 377)
		(LoadMany 128 470 904 910)
		(= global208
			(| (= global208 (| global208 $0010)) $0400)
		)
		(= [global377 4] 294)
		(= [global377 10] 377)
		(myMusic number: 112 loop: 0)
		(Duster illegalBits: 0 ignoreActors: 1 init:)
		(Fifi setAvoider: (Avoid new:) init:)
		(self setScript: kiss)
	)
	
	(method (doit)
		(super doit:)
	)
	
	(method (dispose)
		(DisposeScript 985)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(super handleEvent: event)
		(if (event claimed?) (return))
	)
)

(instance kiss of Script
	(properties)
	
	(method (doit)
		(if (and (== state 5) (== (Fifi cel?) 4))
			(Duster dispose:)
		)
		(if (and (== state 5) (== (Fifi cel?) 1))
			(Jeeves loop: 8 cel: 0 setCycle: End)
		)
		(if
			(and
				(== state 5)
				(== (Jeeves cel?) 1)
				(== (Jeeves loop?) 8)
			)
			(Jeeves loop: 9 setCycle: Fwd)
		)
		(if (and (== state 1) (== (Fifi cel?) 1))
			(Duster
				setStep: 3 3
				setLoop: 1
				setCycle: Fwd
				setMotion: MoveTo 136 142
			)
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (HandsOff) (= cycles 1))
			(1
				(Fifi
					view: 475
					loop: 0
					cel: 0
					cycleSpeed: 2
					setCycle: End self
				)
			)
			(2
				(myMusic number: 51 loop: 1 play:)
				(Duster setCycle: 0 loop: 2 cel: 0)
				(Fifi loop: 3 cel: 0 setCycle: End self)
			)
			(3
				(Fifi loop: 4 cel: 0 setCycle: End self)
			)
			(4
				(Fifi cel: 0 loop: 5 setCycle: Fwd)
				(Jeeves setCycle: End setAvoider: (Avoid new:) init:)
				(= seconds 3)
			)
			(5
				(Fifi loop: 7 setCycle: End self)
			)
			(6
				(HandsOn)
				(Fifi cycleSpeed: 0 setScript: fifiActions)
				(Jeeves cycleSpeed: 0 setScript: jeevesActions)
				(client setScript: 0)
			)
		)
	)
)

(instance jeevesActions of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= local20 (Random 0 5))
				(Jeeves
					view: 447
					setCycle: Walk
					ignoreActors: 0
					setMotion:
						MoveTo
						[local0 (* local20 2)]
						[local0 (+ (* local20 2) 1)]
						self
				)
			)
			(1
				(Jeeves
					view: 459
					cel: 0
					loop: (if (< (Random 1 100) 50) 0 else 1)
					setCycle: End self
				)
			)
			(2
				(Jeeves
					loop: (if (== (Jeeves loop?) 0) 2 else 3)
					setCycle: Fwd
				)
				(= seconds 4)
			)
			(3
				(Jeeves
					cel: 2
					loop: (if (== (Jeeves loop?) 2) 0 else 1)
					setCycle: Beg self
				)
				(= state -1)
			)
		)
	)
)

(instance fifiActions of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Fifi
					view: 464
					setCycle: Walk
					ignoreActors: 0
					setMotion: MoveTo 64 139 self
				)
			)
			(1
				(Fifi setMotion: MoveTo 56 103 self)
			)
			(2
				(= local21 (Random 0 3))
				(Fifi
					view: 464
					setCycle: Walk
					ignoreActors: 0
					setMotion:
						MoveTo
						[local12 (* local21 2)]
						[local12 (+ (* local21 2) 1)]
						self
				)
			)
			(3
				(Fifi
					view: 470
					cel: 0
					loop:
					(switch local21
						(0 1)
						(1 0)
						(2 1)
						(else  0)
					)
					setCycle: End self
				)
			)
			(4
				(Fifi
					loop:
					(switch local21
						(0 3)
						(1 2)
						(2 3)
						(else  2)
					)
					setCycle: Fwd
				)
				(= seconds 4)
			)
			(5
				(Fifi cel: 2 setCycle: Beg self)
				(= state 1)
			)
		)
	)
)

(instance Jeeves of Act
	(properties
		y 153
		x 169
		view 475
		loop 6
	)
	
	(method (handleEvent event)
		(cond 
			(
				(and
					(not (& global207 $0400))
					(or (MousedOn self event 3) (Said 'examine/butler'))
				)
				(= theTalker 11)
				(= global207 (| global207 $0400))
				(event claimed: 1)
				(Say 0 383 0)
			)
			(
				(and
					(& global207 $0400)
					(or (MousedOn self event 3) (Said 'examine/butler'))
				)
				(event claimed: 1)
				(Print 383 1)
			)
			((Said 'converse,examine/person') (Print 383 2))
			((Said 'converse/people') (Print 383 3))
			((Said 'examine/people') (Print 383 4))
			((Said '/butler>')
				(cond 
					((Said 'converse')
						(= theTalker 11)
						(switch local22
							(0 (Say 1 383 5))
							(1 (Say 1 383 6))
							(2 (Say 1 383 7))
							(else  (Print 383 8))
						)
						(++ local22)
					)
					((Said 'hear') (Print 383 9))
				)
			)
		)
	)
)

(instance Fifi of Act
	(properties
		y 153
		x 148
		view 475
	)
	
	(method (handleEvent event)
		(if
		(< (ego distanceTo: Fifi) (ego distanceTo: Jeeves))
			(= global214 16)
		else
			(= global214 1024)
		)
		(cond 
			(
			(and (MousedOn self event 3) (not (& global207 $0010))) (event claimed: 1) (ParseName {fifi}))
			(
				(and
					(& global207 $0010)
					(or (MousedOn self event 3) (Said 'examine/fifi'))
				)
				(event claimed: 1)
				(Print 383 10)
			)
			(
			(and (== (event type?) evSAID) (Said '*/fifi>'))
				(cond 
					((Said 'converse')
						(= theTalker 5)
						(switch local23
							(0 (Say 1 383 11))
							(1 (Say 1 383 12))
							(2 (Say 1 383 13))
							(3 (Say 1 383 14))
							(else  (Say 1 383 15))
						)
						(++ local23)
					)
					((Said 'hear') (Print 383 16))
				)
			)
		)
	)
)

(instance Duster of Act
	(properties
		y 124
		x 158
		view 475
		loop 1
	)
)

(instance Sweeper of Prop
	(properties
		y 119
		x 158
		view 475
		loop 2
		cel 2
	)
)
