;;; Sierra Script 1.0 - (do not remove this comment)
(script# 348)
(include game.sh)
(use Main)
(use Intrface)
(use n396)
(use Submarine_806)
(use EgoDead)
(use System)

(public
	battleShip 0
)

(instance battleShip of Script
	
	(method (dispose)
		(cls)
		(DisposeScript 396)
		(if (< state 4)
			(EgoDead 926 1 0 348 0)
		else
			(super dispose:)
			(DisposeScript 348)
		)
	)
	
	(method (changeState newState &tmp [temp0 20])
		(switch (= state newState)
			(0
				((ScriptID 27 4) posn: 57 56)
				(Submarine longitude: 170 latitude: 65)
				(= seconds 10)
			)
			(1
				(SubPrint 4 348 4)
				(= seconds 5)
			)
			(2
				(Format @temp0 348 5 18800)
				(SubPrint 4 @temp0)
				(= seconds 5)
			)
			(3
				(Format @temp0 348 6 17600)
				(SubPrint 4 @temp0)
				(= seconds 5)
			)
			(4
				(Print 348 7)
				(Print 348 8)
				(Print 348 9)
				(Print 348 10)
				(client setScript: (ScriptID 339 0))
			)
		)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((== (event type?) 1024)
				(switch (event message?)
					(0
						(SubPrint 5 348 1)
						(self setScript: (ScriptID 326 0))
					)
					(1
						(SubPrint 5 348 2)
					)
					(2
						(SubPrint 5 348 3)
						(self setScript: (ScriptID 326 0))
					)
				)
			)
		)
	)
)
