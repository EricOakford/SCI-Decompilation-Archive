;;; Sierra Script 1.0 - (do not remove this comment)
(script# 267)
(include sci.sh)
(use Main)
(use Intrface)
(use Avoider)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	dusting 0
)
(synonyms
	(fifi person girl)
)

(local
	local0
	local1
	[local2 12] = [96 145 216 143 165 155 189 104 111 104 34 92]
	local14
)
(instance dusting of Rgn
	(properties)
	
	(method (init)
		(super init:)
		(= global192 1)
		(Load rsFONT 4)
		(LoadMany 143 243 294)
		(LoadMany 128 470 904)
		(= global208 16)
		(= [global377 4] 294)
		(Fifi
			view: 464
			setAvoider: ((Avoid new:) offScreenOK: 1)
			init:
			setScript: fifiActions
		)
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
	)
)

(instance fifiActions of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (== local0 3)
					(Fifi setMotion: MoveTo 177 144 self)
				else
					(= cycles 1)
				)
			)
			(1
				(if (== local0 3)
					(Fifi setMotion: MoveTo 170 124 self)
				else
					(= cycles 1)
				)
			)
			(2
				(= local14 0)
				(Fifi
					view: 464
					setCycle: Walk
					ignoreActors: 0
					setMotion:
						MoveTo
						[local2 (* local0 2)]
						[local2 (+ (* local0 2) 1)]
						self
				)
			)
			(3
				(Fifi
					view: 470
					cel: 0
					loop:
					(switch local0
						(0 4)
						(1 4)
						(2 5)
						(3 1)
						(4 0)
						(5 1)
					)
					setCycle: End self
				)
			)
			(4
				(Fifi
					loop:
					(switch local0
						(0 6)
						(1 6)
						(2 7)
						(3 3)
						(4 2)
						(5 3)
					)
					setCycle: Fwd
				)
				(= seconds 4)
			)
			(5
				(Fifi
					cel: 2
					loop:
					(switch local0
						(0 4)
						(1 4)
						(2 5)
						(3 1)
						(4 0)
						(5 1)
					)
					setCycle: Beg self
				)
			)
			(6
				(Fifi view: 464 setCycle: Walk ignoreActors: 0)
				(if (< local0 5) (++ local0) (= state -1))
				(= cycles 1)
			)
			(7
				(Fifi setMotion: MoveTo -20 98 self)
			)
			(8
				(= global192 2)
				(Fifi dispose:)
			)
		)
	)
)

(instance Fifi of Act
	(properties
		y 140
		x 196
	)
	
	(method (handleEvent event)
		(cond 
			(
			(and (MousedOn self event 3) (not (& global207 $0010))) (event claimed: 1) (ParseName {fifi}))
			(
				(and
					(& global207 $0010)
					(or (MousedOn self event 3) (Said 'examine/fifi'))
				)
				(event claimed: 1)
				(Print 267 0)
			)
			(
			(and (== (event type?) evSAID) (Said '*/fifi>'))
				(cond 
					((Said 'converse')
						(= theTalker 5)
						(switch local1
							(0 (Say 1 267 1))
							(1 (Say 1 267 2))
							(2 (Say 1 267 3))
							(3 (Say 1 267 4))
							(else  (Say 1 267 5))
						)
						(++ local1)
					)
					((Said 'hear') (Print 267 6))
				)
			)
		)
	)
)
