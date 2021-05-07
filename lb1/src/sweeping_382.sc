;;; Sierra Script 1.0 - (do not remove this comment)
(script# 382)
(include sci.sh)
(use Main)
(use Intrface)
(use Avoider)
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
	local0
	local1
)
(instance sweeping of Rgn
	(properties)
	
	(method (init)
		(super init:)
		(= global195 1024)
		(LoadMany 128 459 910)
		(Jeeves view: 447)
		(boa setPri: 1 ignoreActors: 1 init:)
		(if (== ((inventory at: 9) owner?) 36)
			(Load rsVIEW 17)
			(bRecord setPri: 1 ignoreActors: 1 init:)
			(footprint setPri: 1 ignoreActors: 1 init:)
		)
		(Jeeves illegalBits: -32752 x: 340 y: 100)
		(self setScript: rm36Actions)
	)
	
	(method (doit)
		(super doit:)
	)
	
	(method (dispose)
		(DisposeScript 985)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return 1))
		(return
			(if
				(and
					(== (event type?) evSAID)
					(Said 'examine/mud,spot,bootprint')
				)
				(Print 382 0)
			else
				0
			)
		)
	)
)

(instance rm36Actions of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 30))
			(1
				(= gMySound 1)
				(Jeeves
					view: 447
					setCycle: Walk
					ignoreActors: 0
					setAvoider: ((Avoid new:) offScreenOK: 1)
					init:
				)
				(Jeeves setMotion: MoveTo 153 110 self)
			)
			(2
				(Jeeves setMotion: MoveTo 132 100 self)
			)
			(3
				(Jeeves view: 459 cel: 0 loop: 1 setCycle: End self)
			)
			(4
				(Jeeves setLoop: 3 setCycle: Fwd)
				(boa dispose:)
				(= global141 (| global141 $0004))
				(if (cast contains: bRecord) (bRecord dispose:))
				(= seconds 4)
			)
			(5
				(Jeeves cel: 2 loop: 1 setCycle: Beg self)
			)
			(6
				(Jeeves
					view: 447
					setCycle: Walk
					ignoreActors: 0
					setLoop: -1
					setMotion: MoveTo 117 106 self
				)
			)
			(7
				(Jeeves view: 459 cel: 0 loop: 1 setCycle: End self)
			)
			(8
				(Jeeves setLoop: 3 setCycle: Fwd)
				(footprint dispose:)
				(= seconds 3)
			)
			(9
				(Jeeves cel: 2 loop: 1 setCycle: Beg self)
			)
			(10
				(Jeeves view: 447 setCycle: Walk ignoreActors: 0)
				(Jeeves setLoop: -1 setMotion: MoveTo 340 98 self)
			)
			(11
				(Jeeves dispose:)
				(= gMySound 0)
				(DisposeScript 985)
				(client setScript: 0)
			)
		)
	)
)

(instance pickUp of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(Face ego bRecord)
				(= cycles 2)
			)
			(1
				(ego view: 17 cel: 0 setMotion: 0 setCycle: End self)
			)
			(2
				(ego get: 9)
				(bRecord hide:)
				(= gotItem 1)
				(Print 382 1)
				(= cycles 2)
			)
			(3 (ego setCycle: Beg self))
			(4
				(HandsOn)
				(ego view: 0 setCycle: Walk)
				(client dispose: setScript: 0)
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
				(Say 0 382 2)
			)
			(
				(and
					(& global207 $0400)
					(or (MousedOn self event 3) (Said 'examine/butler'))
				)
				(event claimed: 1)
				(Print 382 3)
			)
			((Said 'ask,tell//*<about')
				(= theTalker 11)
				(switch (Random 1 7)
					(1 (Say 1 382 4))
					(2 (Say 1 382 5))
					(3 (Print 382 6))
					(4 (Say 1 382 7))
					(5 (Say 1 382 8))
					(6 (Print 382 9))
					(else  (Say 1 382 10))
				)
			)
			((Said 'deliver,hold/*')
				(if (and theInvItem haveInvItem)
					(switch (Random 1 5)
						(1 (Print 382 11))
						(2 (Print 382 12))
						(3 (Print 382 13))
						(4 (Print 382 14))
						(else  (Print 382 15))
					)
				else
					(DontHave)
				)
			)
			((Said '/butler>')
				(cond 
					((Said 'converse')
						(= theTalker 11)
						(switch local1
							(0 (Say 1 382 16))
							(1 (Say 1 382 17))
							(2 (Say 1 382 18))
							(else  (Print 382 19))
						)
						(++ local1)
					)
					((Said 'hear') (Print 382 20))
					((Said 'get') (Print 382 21))
					((Said 'kill') (Print 382 22))
					((Said 'kiss') (Print 382 23))
					((Said 'embrace') (Print 382 24))
				)
			)
			((Said 'flirt//butler<with') (Print 382 25))
		)
	)
)

(instance bRecord of Prop
	(properties
		y 96
		x 115
		view 136
		loop 2
		cel 7
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			(
			(or (MousedOn self event 3) (Said 'examine/record')) (Print 382 26) (event claimed: 1))
			((Said 'get/record[<broken]')
				(if (< (ego distanceTo: bRecord) 15)
					(self setScript: pickUp)
				else
					(NotClose)
				)
			)
		)
	)
)

(instance boa of Prop
	(properties
		y 100
		x 90
		view 136
		loop 9
		cel 3
	)
	
	(method (handleEvent event)
		(cond 
			(
			(or (MousedOn self event 3) (Said 'examine/feather')) (Print 382 27) (event claimed: 1))
			((Said 'get/feather') (Print 382 28))
		)
	)
)

(instance footprint of Prop
	(properties
		y 106
		x 100
		view 136
		loop 3
		cel 3
	)
	
	(method (handleEvent event)
		(cond 
			((Said 'get/mud') (Print 382 29))
			(
				(or
					(MousedOn self event 3)
					(Said 'examine/mud,spot,bootprint')
				)
				(Print 382 30)
				(event claimed: 1)
			)
		)
	)
)
