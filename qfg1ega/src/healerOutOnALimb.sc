;;; Sierra Script 1.0 - (do not remove this comment)
(script# 237)
(include game.sh)
(use Main)
(use Motion)
(use System)

(public
	outOnALimb 0
	climbDown 1
)

(local
	stepCount
)
(instance outOnALimb of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					view: vEgoWalkTree
					posn: (ego x?) (- (ego y?) 10)
					setLoop: 8
					setCel: 0
					setPri: 14
				)
				(= cycles 3)
			)
			(1
				(++ stepCount)
				(ego setCel: 1)
				(= cycles 3)
			)
			(2
				(ego setCel: 2 posn: (+ (ego x?) 2) (ego y?))
				(= cycles 3)
			)
			(3
				(ego setCel: 3 posn: (+ (ego x?) 2) (- (ego y?) 1))
				(= cycles 3)
			)
			(4
				(ego setCel: 4 posn: (+ (ego x?) 2) (- (ego y?) 1))
				(= cycles 3)
			)
			(5
				(ego setCel: 0)
				(cond 
					((== stepCount 3)
						(if (TrySkill AGIL tryWalkTreeLimb)
							(self changeState: 1)
						else
							(= stepCount 0)
							(ego setScript: (ScriptID 54 6))
							(DisposeScript 237)
						)
					)
					((< stepCount 5)
						(self changeState: 1)
					)
					(else
						(= stepCount 0)
						(self cue:)
					)
				)
			)
			(6
				(HighPrint 237 0)
				;You carefully bend down and reach into the nest.
				(ego posn: 59 72 setLoop: 9 setCycle: EndLoop self)
			)
			(7
				(HighPrint 237 1)
				;In the nest, you find a gold ring.
				(HighPrint 237 2)
				;You take the ring and move carefully back along the limb.
				(Bset fGotRing)
				(ego get: iRing setCycle: BegLoop self)
				(SolvePuzzle f54GetRing 3)
			)
			(8
				(ego setLoop: 8 setCel: 0)
				(++ stepCount)
				(= cycles 3)
			)
			(9
				(ego setCel: 4)
				(= cycles 3)
			)
			(10
				(ego posn: (- (ego x?) 2) (+ (ego y?) 1) setCel: 3)
				(= cycles 3)
			)
			(11
				(ego posn: (- (ego x?) 2) (+ (ego y?) 1) setCel: 2)
				(= cycles 3)
			)
			(12
				(ego posn: (- (ego x?) 2) (ego y?) setCel: 1)
				(= cycles 3)
			)
			(13
				(if (< stepCount 5)
					(self changeState: 8)
				else
					(= stepCount 0)
					(self cue:)
				)
			)
			(14
				(HighPrint 237 3)
				;Made it!  Now back down the tree.
				(ego setScript: climbDown)
			)
		)
	)
)

(instance climbDown of Script
	(method (dispose)
		(super dispose:)
		(DisposeScript 237)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					view: vEgoClimbing
					setLoop: 1
					cel: 6
					posn: 28 93
					setStep: 0 4
					cycleSpeed: 2
					moveSpeed: 2
					setCycle: BegLoop self
				)
			)
			(1
				(ego
					setLoop: 0
					setCel: 0
					x: (+ (ego x?) 12)
					y: (+ (ego y?) 33)
				)
				(self cue:)
			)
			(2 (= cycles 4))
			(3
				(++ stepCount)
				(ego
					setCel: (if (== (ego cel?) 0) 3 else (- (ego cel?) 1))
					posn:
						(ego x?)
						(if (== (mod (ego cel?) 2) 1)
							(+ (ego y?) 19)
						else
							(ego y?)
						)
				)
				(self cue:)
			)
			(4
				(if (< stepCount 6)
					(self changeState: 2)
				else
					(= stepCount 0)
					(= cycles 4)
				)
			)
			(5
				(NormalEgo)
				(HandsOn)
				(Bclr fClimbedTree)
				(client setScript: 0)
			)
		)
	)
)
