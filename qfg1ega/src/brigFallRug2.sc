;;; Sierra Script 1.0 - (do not remove this comment)
(script# 191)
(include game.sh)
(use Main)
(use System)

(public
	fallRugs 0
)

(instance fallRugs of Script
	(properties)
	
	(method (dispose)
		(super dispose:)
		(DisposeScript 191)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setLoop: 1)
				(= cycles 1)
			)
			(1
				(ego
					view: vEgoFalling
					setLoop: 1
					setCel: 0
					illegalBits: 0
					setPri: 10
					posn: (- (ego x?) 7) (+ (ego y?) 1)
				)
				((ScriptID 94 13) setCel: 1)
				(= cycles 3)
			)
			(2
				(ego setCel: 1 posn: (- (ego x?) 7) (+ (ego y?) 0))
				(= cycles 3)
			)
			(3
				(ego setCel: 2 posn: (- (ego x?) 10) (+ (ego y?) 6))
				(= cycles 3)
			)
			(4
				((ScriptID 94 12) setCel: 1)
				(ego setCel: 3 posn: (- (ego x?) 13) (+ (ego y?) 0))
				(= cycles 3)
			)
			(5
				(ego setCel: 4 posn: (- (ego x?) 0) (+ (ego y?) 0))
				(= cycles 3)
			)
			(6
				((ScriptID 94 12) dispose:)
				(ego posn: (- (ego x?) 0) (+ (ego y?) 5))
				(= cycles 3)
			)
			(7
				(ego posn: (- (ego x?) 0) (+ (ego y?) 6))
				(= cycles 3)
			)
			(8
				(ego posn: (- (ego x?) 0) (+ (ego y?) 7))
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
				(EgoDead 191 0
					#icon vEgoFall2 0 2
					#title {A travelling man.})
					;You seem to be having a rugged time of it.  You've had a terrible ca-tapestry.
				(self dispose:)
			)
		)
	)
)
