;;; Sierra Script 1.0 - (do not remove this comment)
(script# 186)
(include game.sh)
(use Main)
(use System)

(public
	fallBridge 0
)

(instance fallBridge of Script
	(properties)
	
	(method (dispose)
		(super dispose:)
		(DisposeScript 186)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setLoop: 0)
				(= cycles 1)
			)
			(1
				(ego
					view: vEgoFalling
					setLoop: 0
					setCel: 0
					illegalBits: 0
					setPri: 7
					posn: (+ (ego x?) 7) (+ (ego y?) 1)
				)
				((ScriptID 94 9) setCel: 1)
				(= cycles 3)
			)
			(2
				(ego setCel: 1 posn: (+ (ego x?) 7) (+ (ego y?) 0))
				(= cycles 3)
			)
			(3
				(ego setCel: 2 posn: (+ (ego x?) 12) (+ (ego y?) 6))
				(= cycles 3)
			)
			(4
				(ego setCel: 3 posn: (+ (ego x?) 15) (+ (ego y?) 0))
				(= cycles 3)
			)
			(5
				(ego setCel: 4 posn: (+ (ego x?) 1) (+ (ego y?) 0))
				(= cycles 3)
			)
			(6
				(ego posn: (+ (ego x?) 2) (+ (ego y?) 5))
				(= cycles 3)
			)
			(7
				(ego posn: (+ (ego x?) 0) (+ (ego y?) 6))
				(= cycles 3)
			)
			(8
				(ego posn: (+ (ego x?) 0) (+ (ego y?) 7))
				(= cycles 3)
			)
			(9
				(ego hide:)
				((ScriptID 94 9) setCel: 0)
				(if ((ScriptID 94 0) notify: 0)
					((ScriptID 94 1) setScript: (ScriptID 94 5))
					((ScriptID 94 2) setScript: (ScriptID 94 6))
					((ScriptID 94 3) setScript: (ScriptID 94 7))
					((ScriptID 94 4) setScript: (ScriptID 94 8))
				)
				(= cycles 20)
			)
			(10
				(EgoDead 186 0
					#icon vEgoFall2 0 2
					#title {I wasn't ready.})
					;I score that about a 3.  Those diving lessons were a complete waste.  Maybe you should have taken up bowling.
				(self dispose:)
			)
		)
	)
)
