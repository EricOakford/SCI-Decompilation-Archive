;;; Sierra Script 1.0 - (do not remove this comment)
(script# 344)
(include game.sh)
(use Main)
(use Intrface)
(use subMarine)
(use n396)
(use Submarine_806)
(use EgoDead)
(use System)

(public
	iceStationScript 0
)

(instance iceStationScript of Script
	(properties
		seconds 5
	)
	
	(method (dispose)
		(cond 
			((< state 8)
				(EgoDead 926 4 0 344 15)
			)
			((== state 12))
			(else (subMarine cue:))
		)
		(Submarine ceiling: 0)
		(super dispose:)
		(DisposeScript 396)
		(DisposeScript 344)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (== ((subMarine script?) state?) 12)
					(Print 344 0)
					(curRoom newRoom: 25)
				)
				((ScriptID 27 4) posn: 64 43)
				(Submarine
					depth: (if (> (Submarine depth:) 200) 195 else 75)
					ceiling: 50
					floor: 200
					longitude: 120
					latitude: 80
				)
				(SubPrint 5 344 1)
				((ScriptID 364 1) init:)
				(= seconds 6)
			)
			(1
				(SubPrint 5 344 2)
				(= seconds 20)
			)
			(2
				(self setScript: (ScriptID 364) self)
			)
			(3
				((ScriptID 27 4) posn: 80 40)
				(Submarine longitude: 15 latitude: 85)
				(= cycles 2)
			)
			(4
				(SubPrint 5 344 3)
				(= seconds 6)
			)
			(5
				(SubPrint 5 344 4)
				(= seconds 6)
			)
			(6
				(SubPrint 10 344 5)
				(= seconds 12)
			)
			(7
				(SubPrint 7 344 6)
				(= seconds 10)
			)
			(8
				(if register
					(SubPrint 7 344 7)
					(subMarine cue:)
				else
					(SubPrint 7 344 8)
					(EgoDead 7 0 0 344 9)
				)
				(= seconds 10)
			)
			(9
				(= start state)
				(if (<= (++ register) 3)
					(SubPrint 5 344 10)
					(= seconds 10)
				else
					(EgoDead 7 0 0 344 11)
				)
			)
			(10 (self init:))
		)
	)
	
	(method (cue)
		(if script (script cue:) else (super cue:))
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said 'contact/station[<ice]')
				(cond 
					((< state 7)
						(Print 344 12)
					)
					((< state 8)
						(Print 344 13)
						(= register 1)
					)
					(else
						(Print 344 14)
					)
				)
			)
		)
	)
)
