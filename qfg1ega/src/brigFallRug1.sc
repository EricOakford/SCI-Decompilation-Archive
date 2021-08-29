;;; Sierra Script 1.0 - (do not remove this comment)
(script# 190)
(include game.sh)
(use Main)
(use Motion)
(use System)

(public
	fallRug 0
)

(instance fallRug of Script
	(method (dispose)
		(super dispose:)
		(DisposeScript 190)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if (== (ego loop?) 3) (ego setLoop: 1))
				(= cycles 5)
			)
			(1
				(ego setLoop: 2)
				(= cycles 5)
			)
			(2
				(ego
					view: vEgoShock
					setLoop: 0
					cel: 0
					setPri: 9
					yStep: 6
					posn: (ego x?) (+ (ego y?) 3)
				)
				((ScriptID 94 12) setCel: 1)
				(= cycles 5)
			)
			(3
				((ScriptID 94 12) dispose:)
				((ScriptID 94 0) notify: 8)
				(ego
					setPri: 9
					cycleSpeed: 5
					posn: (ego x?) (+ (ego y?) 3)
					setCycle: EndLoop self
				)
			)
			(4
				(ego posn: (ego x?) (+ (ego y?) 5))
				(= cycles 1)
			)
			(5
				(ego posn: (ego x?) (+ (ego y?) 8))
				(= cycles 1)
			)
			(6
				(ego posn: (ego x?) (+ (ego y?) 12))
				(= cycles 1)
			)
			(7
				(ego posn: (ego x?) (+ (ego y?) 13))
				(= cycles 1)
			)
			(8
				(ego posn: (ego x?) (+ (ego y?) 13))
				(= cycles 1)
			)
			(9
				(if ((ScriptID 94 0) notify: 0)
					((ScriptID 94 1) setScript: (ScriptID 94 5))
					((ScriptID 94 2) setScript: (ScriptID 94 6))
					((ScriptID 94 3) setScript: (ScriptID 94 7))
					((ScriptID 94 4) setScript: (ScriptID 94 8))
				)
				(= cycles 20)
			)
			(10
				(EgoDead 190 0
					#icon vEgoFall2 0 2
					#title {The old "rug over the pit" trick.}
				)
					;There is a small sign which reads "Booby Trap".
					;You are now in a pitiful situation as you hear the brigands approach to see the booby they've caught.
					;You think to yourself, "If it looks like a trap, and it feels like a trap, then maybe it IS a trap".
				(self dispose:)
			)
		)
	)
)
