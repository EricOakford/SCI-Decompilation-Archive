;;; Sierra Script 1.0 - (do not remove this comment)
(script# 137)
(include game.sh)
(use Main)
(use Intrface)
(use Motion)
(use System)

(public
	goodClimb 0
	badClimb 1
	climbDown 2
)

(instance goodClimb of Script
	(method (dispose)
		(super dispose:)
		(DisposeScript 137)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego illegalBits: 0 ignoreActors:)
				(if (> (ego y?) 142)
					(self cue:)
				else
					(HighPrint 137 0)
					;You look for a good place to climb.
					(ego setMotion: MoveTo (ego x?) 143 self)
				)
			)
			(1
				(ego setMotion: MoveTo 88 143 self)
			)
			(2
				(ego setLoop: 3)
				(= cycles 2)
			)
			(3
				(ego view: vEgoClimbing setLoop: 0 setCel: 0 posn: 96 143)
				(= cycles 2)
			)
			(4
				(ego setCel: 1 y: 122)
				(= cycles 2)
			)
			(5
				(ego setCel: 2)
				(= cycles 2)
			)
			(6
				(ego setCel: 3 y: 101)
				(= cycles 2)
			)
			(7
				(ego setCel: 0)
				(= cycles 2)
			)
			(8
				(ego
					setLoop: 1
					posn: 84 68
					setCel: -1
					cycleSpeed: 1
					setCycle: EndLoop self
				)
			)
			(9
				(if (not (Btst fLadderKnown))
					(Print 137 1 #mode teJustCenter)
					; That was almost too easy...almost like climbing a ladder.
				)
				(= cycles 10)
			)
			(10
				(ego posn: 86 55 setLoop: 3)
				(NormalEgo)
				(HandsOn)
				(Bset fClimbedHenryCliff)
				(client setScript: 0)
			)
		)
	)
)

(instance badClimb of Script
	(method (dispose)
		(super dispose:)
		(DisposeScript 137)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego illegalBits: 0)
				(if (< (ego x?) 90)
					(ego setMotion: MoveTo 47 134 self)
				else
					(ego setMotion: MoveTo 120 140 self)
				)
			)
			(1
				(ego
					view: vEgoClimbing
					x: (+ (ego x?) 10)
					y: (- (ego y?) 1)
					setLoop: 0
					cel: 0
					setCycle: Forward
				)
				(= cycles 20)
			)
			(2
				(HighPrint 137 2)
				;The rocks are too slick from the spray of the waterfall for your limited climbing skill.
				(ego x: (- (ego x?) 10) y: (+ (ego y?) 1))
				(= cycles 2)
			)
			(3
				(ego setMotion: MoveTo (+ (ego x?) 6) (ego y?) self)
			)
			(4
				(NormalEgo)
				(HandsOn)
				(client setScript: 0)
			)
		)
	)
)

(instance climbDown of Script
	(method (dispose)
		(super dispose:)
		(DisposeScript 137)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego illegalBits: 0 setMotion: MoveTo 86 55 self)
			)
			(1
				(ego view: vEgoClimbing setLoop: 1 cel: 6 posn: 84 68)
				(= cycles 2)
			)
			(2
				(ego cycleSpeed: 1 setCycle: BegLoop self)
			)
			(3
				(ego setLoop: 0 setCel: 0 posn: 96 101)
				(= cycles 2)
			)
			(4
				(ego setCel: 3)
				(= cycles 2)
			)
			(5
				(ego y: 122 setCel: 2)
				(= cycles 2)
			)
			(6
				(ego setCel: 1)
				(= cycles 2)
			)
			(7
				(ego posn: 96 143 setCel: 0)
				(= cycles 2)
			)
			(8
				(ego loop: 2)
				(NormalEgo)
				(HandsOn)
				(Bclr fClimbedHenryCliff)
				(client setScript: 0)
			)
		)
	)
)
