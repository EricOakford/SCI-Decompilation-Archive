;;; Sierra Script 1.0 - (do not remove this comment)
(script# 244)
(include sci.sh)
(use Main)
(use Intrface)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	fifiprim 0
)
(synonyms
	(fifi girl)
)

(local
	[local0 2]
	local2
	local3
)
(instance Fifi of Prop
	(properties
		y 129
		x 242
		view 468
		priority 13
		signal $0010
	)
	
	(method (handleEvent event)
		(if (> (ego x?) 64)
			(cond 
				((Said 'hear/fifi') (Print 244 0))
				((Said 'converse/fifi')
					(= theTalker 5)
					(switch local2
						(0 (Say 1 244 1))
						(1 (Say 1 244 2))
						(2 (Say 1 244 3))
						(else  (Say 1 244 4))
					)
					(++ local2)
				)
				(
				(and (MousedOn self event 3) (not (& global207 $0010))) (event claimed: 1) (ParseName {fifi}))
				(
					(and
						(& global207 $0010)
						(or (MousedOn self event 3) (Said 'examine/fifi'))
					)
					(event claimed: 1)
					(Print 244 5)
				)
			)
		)
	)
)

(instance fifiprim of Rgn
	(properties)
	
	(method (init)
		(super init:)
		(Bset 20)
		(LoadMany 143 243 250)
		(Load rsVIEW 904)
		(= global208 16)
		(= [global377 4] 250)
		(LoadMany 132 224 229)
		(Fifi init: stopUpd:)
		(FifiButt init: stopUpd:)
	)
	
	(method (doit)
		(if (and (> (ego x?) 64) (not script))
			(self setScript: primp)
			(gDoor init: setScript: playRecord)
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

(instance primp of Script
	(properties)
	
	(method (doit)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Fifi view: 468 loop: 0 cycleSpeed: 1 setCycle: End self)
			)
			(1
				(Fifi loop: 1 setCycle: Fwd)
				(= seconds 3)
			)
			(2
				(Fifi loop: 2 cel: 0 setCycle: End self)
			)
			(3
				(Fifi loop: 3 setCycle: Fwd)
				(= seconds 3)
			)
			(4
				(Fifi loop: 0 cel: 1 setCycle: Beg self)
			)
			(5 (= seconds 8))
			(6
				(Fifi loop: 4 cel: 0 setCycle: End self)
			)
			(7
				(Fifi loop: 5 cel: 0 setCycle: Fwd)
				(= seconds 3)
			)
			(8
				(Fifi loop: 4 cel: 1 setCycle: Beg self)
			)
			(9 (= seconds 8))
			(10
				(Fifi loop: 6 cel: 0 setCycle: End self)
			)
			(11
				(Fifi loop: 7 cel: 0 setCycle: Fwd)
				(= seconds 3)
			)
			(12
				(Fifi loop: 6 cel: 1 setCycle: Beg self)
			)
			(13
				(if local3
					(switch (Random 1 3)
						(1 (= state -1))
						(2 (= state 5))
						(3 (= state 9))
					)
				)
				(= seconds 6)
			)
			(14
				(++ local3)
				(Fifi view: 477 loop: 0 cel: 0 setCycle: End self)
			)
			(15
				(Fifi loop: 1 setCycle: Fwd)
				(= seconds 3)
			)
			(16
				(Fifi loop: 2 cel: 0 setCycle: End self)
			)
			(17
				(Fifi loop: 3 cel: 0 setCycle: Fwd)
				(= seconds 3)
			)
			(18
				(Fifi loop: 2 cel: 3 setCycle: Beg self)
				(= state -1)
			)
		)
	)
)

(instance playRecord of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(client setCycle: Fwd)
				(mySound
					number: (if (== (mySound number?) 229) 224 else 229)
					loop: 1
					play: self
				)
				(= state -1)
			)
		)
	)
)

(instance mySound of Sound
	(properties)
)

(instance FifiButt of Prop
	(properties
		y 153
		x 252
		view 477
		loop 4
	)
)
