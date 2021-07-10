;;; Sierra Script 1.0 - (do not remove this comment)
(script# 398)
(include game.sh)
(use n396)
(use Submarine_806)
(use EgoDead)
(use System)

(public
	subBattleScript 0
)

(instance subBattleScript of Script
		
	(method (dispose)
		(super dispose:)
		(DisposeScript 398)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Submarine floor: 2312 longitude: 30 latitude: 60)
				(= seconds 8)
			)
			(1
				(SubPrint 5 398 3)
				(= seconds 10)
			)
			(2
				(SubPrint 3 398 4)
				(= seconds 3)
			)
			(3
				(SubPrint 5 398 5)
				(= seconds 15)
			)
			(4
				(SubPrint 5 398 6)
				(= seconds 6)
			)
			(5
				(SubPrint 5 398 7)
				(= seconds 6)
			)
			(6
				(SubPrint 3 398 8)
				(= seconds 4)
			)
			(7
				(SubPrint 4 398 9)
				(= seconds 5)
			)
			(8
				(SubPrint 5 398 10)
				(= seconds 5)
			)
			(9
				(SubPrint 5 398 11)
				(= seconds 6)
			)
			(10
				(SubPrint 5 398 12)
				(= seconds 6)
			)
			(11
				(SubPrint 5 398 13)
				(= seconds 6)
			)
			(12
				(SubPrint 5 398 14)
				(= seconds 6)
			)
			(13
				(self dispose:)
			)
		)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			;EO: this seems to be a unique event type
			((== (event type?) $0400)
				(switch (event message?)
					(0
						(SubPrint 5 398 0)
						(self setScript: firedTooSoon)
					)
					(1
						(SubPrint 5 398 1)
					)
					(2
						(SubPrint 5 398 2)
						(self setScript: firedTooSoon)
					)
				)
			)
		)
	)
)

(instance firedTooSoon of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 4))
			(1 (EgoDead 926 1 0 398 15))
		)
	)
)
