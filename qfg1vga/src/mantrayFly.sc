;;; Sierra Script 1.0 - (do not remove this comment)
(script# 436)
(include game.sh)
(use Main)
(use Procs)
(use PolyPath)
(use Motion)
(use System)

(public
	flyRight 0
	flyLeft 1
	startup 2
	killMantray 3
)

(local
	local0
	oldCycleSpeed
	oldMoveSpeed
)
(instance flyRight of Script
	(method (doit)
		(cond 
			((<= (client distanceTo: ego) 52)
				((ScriptID 210 0) cue:)
			)
			((and (< (client y?) 30) (Btst fMantrayLeft))
				(Bclr fMantrayLeft)
			)
			((and (> (client y?) 80) (not (Btst fMantrayLeft)))
				(Bset fMantrayLeft)
			)
		)
		(super doit:)
	)
	
	(method (dispose)
		(= local0 0)
		(super dispose:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= local0 1)
				(client
					x: (+ (client x?) 90)
					cycleSpeed: 9
					setLoop: 0
					cel: 0
					targDeltaX: -40
					setStep: 5 2
					setCycle: EndLoop
					setMotion:
						MoveTo
						280
						(if (Btst fMantrayLeft)
							(- (client y?) 5)
						else
							(+ (client y?) 5)
						)
						self
				)
			)
			(1 (self cue:))
			(2
				(= local0 0)
				(client
					cycleSpeed: 6
					setLoop: 2
					cel: 0
					setStep: 4 1
					setMotion:
						MoveTo
						(- (client x?) 2)
						(if (Btst fMantrayLeft)
							(- (client y?) 5)
						else
							(+ (client y?) 5)
						)
					setCycle: EndLoop self
				)
			)
			(3 (= cycles 2))
			(4 (client setScript: flyLeft))
		)
	)
)

(instance flyLeft of Script
	(method (doit)
		(cond 
			((<= (client distanceTo: ego) 35)
				((ScriptID 210 0) cue:)
			)
			((and (< (client y?) 30) (Btst fMantrayLeft))
				(Bclr fMantrayLeft)
			)
			((and (> (client y?) 80) (not (Btst fMantrayLeft)))
				(Bset fMantrayLeft)
			)
		)
		(super doit:)
	)
	
	(method (dispose)
		(= local0 0)
		(super dispose:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= local0 0)
				(client
					x: (- (client x?) 90)
					cycleSpeed: 9
					setLoop: 1
					cel: 0
					targDeltaX: 40
					setStep: 5 2
					setCycle: EndLoop
					setMotion: MoveTo 20
						(if (Btst fMantrayLeft)
							(- (client y?) 5)
						else
							(+ (client y?) 5)
						)
						self
				)
			)
			(1 (self cue:))
			(2
				(= local0 1)
				(client
					setLoop: 3
					cycleSpeed: 6
					cel: 0
					setStep: 4 1
					setMotion: MoveTo
						(+ (client x?) 2)
						(if (Btst fMantrayLeft)
							(- (client y?) 5)
						else
							(+ (client y?) 5)
						)
					setCycle: EndLoop self
				)
			)
			(3
				(= cycles 2)
			)
			(4
				(client setScript: flyRight)
			)
		)
	)
)

(instance startup of Script
	(method (doit)
		(if (<= (client distanceTo: ego) 10)
			((ScriptID ENCOUNTER 0) cue:)
			(ego setMotion: 0)
		)
		(super doit:)
	)
	
	(method (dispose)
		(= local0 0)
		(super dispose:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(client
					ignoreHorizon:
					posn: 144 111
					setLoop: 5
					moveSpeed: 3
					cycleSpeed: 12
					setPri: (if (< (ego y?) 100) 9 else -1)
					setCycle: Forward
					setStep: 4 2
					illegalBits: 0
					stopUpd:
				)
				(= cycles 5)
			)
			(1
				(client setCel: 1 posn: 145 110)
				(= cycles 2)
			)
			(2
				(client setCel: 0 posn: 144 111)
				(= cycles 2)
			)
			(3
				(client setCel: 1 posn: 145 110)
				(= cycles 2)
			)
			(4 (client setCycle: EndLoop self))
			(5
				(client
					posn: 209 103
					setLoop: 0
					setCel: -1
					setCycle: Forward
					setMotion: MoveTo 279 90 self
				)
			)
			(6 (self cue:))
			(7
				(client
					setLoop: 2
					cel: 0
					cycleSpeed: 6
					setPri: (if (== curRoomNum 81) 9 else -1)
					setStep: 4 1
					setCycle: EndLoop self
				)
			)
			(8 (= cycles 2))
			(9
				(Bset fMantrayLeft)
				(Bset fMantrayRight)
				(client setPri: 9 setScript: flyLeft)
			)
		)
	)
)

(instance killMantray of Script
	(method (dispose)
		(super dispose:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Bclr fMantrayDies)
				(HandsOff)
				(= oldMoveSpeed (ego moveSpeed?))
				(= oldCycleSpeed (ego cycleSpeed?))
				(if
					(and
						(< (register nsLeft?) (ego x?))
						(< (ego x?) (register nsRight?))
					)
					(ego moveSpeed: 0 cycleSpeed: 0 ignoreActors:)
					(if (< (ego x?) (+ (register nsLeft?) 35))
						(ego setMotion: PolyPath (- (ego x?) 30) (ego y?) self)
					else
						(ego setMotion: PolyPath (+ (ego x?) 30) (ego y?) self)
					)
				else
					(= ticks 1)
				)
				(register
					view: 435
					setLoop: (if local0 6 else 7)
					setCel: -1
					cycleSpeed: 6
					setCycle: EndLoop self
					yStep: 6
					ignoreActors:
					ignoreHorizon:
					illegalBits: 0
					setMotion: MoveTo (register x?) 130 self
				)
			)
			(1 1)
			(2 1)
			(3
				(ego
					moveSpeed: oldMoveSpeed
					cycleSpeed: oldCycleSpeed
					ignoreActors: 0
				)
				(NormalEgo)
				(= monsterHealth 0)
				(register setPri: -1)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)
