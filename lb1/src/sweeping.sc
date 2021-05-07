;;; Sierra Script 1.0 - (do not remove this comment)
(script# 269)
(include sci.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	sweeping 0
)
(synonyms
	(butler person fellow)
)

(local
	[local0 6] = [136 141 69 147 139 141]
	local6
	local7
	local8
)
(instance sweeping of Rgn
	(properties)
	
	(method (init)
		(super init:)
		(DisposeScript 990)
		(= global195 1024)
		(LoadMany 128 459 910)
		(Jeeves
			view: 447
			setCycle: Walk
			posn: 330 150
			init:
			setScript: sweepFeather
		)
		(Feather setPri: 8 ignoreActors: 1 init:)
	)
	
	(method (doit)
		(if (Jeeves isBlocked:)
			(if (not local8) (= local8 1) (Print 269 0))
		else
			(= local8 0)
		)
		(super doit:)
	)
	
	(method (dispose)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(return (if (event claimed?) (return 1) else 0))
	)
)

(instance sweepFeather of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 5))
			(1
				(Jeeves setMotion: MoveTo 250 151 self)
				(= gDoor 1)
			)
			(2
				(= global141 (| global141 $0002))
				(Jeeves view: 459 cel: 0 loop: 1 setCycle: End self)
			)
			(3
				(Jeeves loop: 3 setCycle: Fwd)
				(Feather dispose:)
				(= seconds 4)
			)
			(4
				(Jeeves cel: 2 loop: 1 setCycle: Beg self)
			)
			(5
				(Jeeves
					view: 447
					setCycle: Walk
					setMotion:
						MoveTo
						[local0 (* local6 2)]
						[local0 (+ (* local6 2) 1)]
						self
				)
			)
			(6
				(Jeeves view: 459 cel: 0 loop: 0 setCycle: End self)
			)
			(7
				(Jeeves loop: 2 setCycle: Fwd)
				(= seconds 4)
			)
			(8
				(Jeeves cel: 2 loop: 0 setCycle: Beg self)
			)
			(9
				(Jeeves view: 447 setCycle: Walk ignoreActors: 0)
				(if (< local6 2) (++ local6) (= state 4))
				(= cycles 1)
			)
			(10
				(Jeeves setMotion: MoveTo 330 150 self)
			)
			(11
				(= gDoor 0)
				(Jeeves setScript: 0 dispose:)
			)
		)
	)
)

(instance Jeeves of Act
	(properties
		y 140
		x 196
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
				(Say 0 269 1)
			)
			(
				(and
					(& global207 $0400)
					(or (MousedOn self event 3) (Said 'examine/butler'))
				)
				(event claimed: 1)
				(Print 269 2)
			)
			((Said 'ask,tell//*<about')
				(= theTalker 11)
				(switch (Random 1 7)
					(1 (Say 1 269 3))
					(2 (Say 1 269 4))
					(3 (Print 269 5))
					(4 (Say 1 269 6))
					(5 (Say 1 269 7))
					(6 (Print 269 8))
					(else  (Say 1 269 9))
				)
			)
			((Said 'deliver,hold/*')
				(if (and theInvItem haveInvItem)
					(switch (Random 1 5)
						(1 (Print 269 10))
						(2 (Print 269 11))
						(3 (Print 269 12))
						(4 (Print 269 13))
						(else  (Print 269 14))
					)
				else
					(DontHave)
				)
			)
			((Said '/butler>')
				(cond 
					((Said 'converse')
						(= theTalker 11)
						(switch local7
							(0 (Say 1 269 15))
							(1 (Say 1 269 16))
							(2 (Say 1 269 17))
							(else  (Print 269 18))
						)
						(++ local7)
					)
					((Said 'hear') (Print 269 19))
					((Said 'get') (Print 269 20))
					((Said 'kill') (Print 269 21))
					((Said 'kiss') (Print 269 22))
					((Said 'embrace') (Print 269 23))
					((Said 'flirt') (Print 269 24))
				)
			)
		)
	)
)

(instance Feather of Prop
	(properties
		y 151
		x 227
		view 132
		loop 5
	)
	
	(method (handleEvent event)
		(cond 
			((Said 'get/feather') (Print 269 25))
			(
			(or (Said 'examine/feather') (MousedOn self event 3)) (Print 269 26) (event claimed: 1))
		)
	)
)
