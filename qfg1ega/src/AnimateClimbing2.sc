;;; Sierra Script 1.0 - (do not remove this comment)
(script# 291)
(include game.sh)
(use Main)
(use Intrface)
(use Motion)
(use User)
(use System)

(public
	badClimb 0
	goodClimb 1
	fallDown 2
	catchIt 3
)

(instance badClimb of Script
	(properties)
	
	(method (dispose)
		(super dispose:)
		(DisposeScript 291)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if (not (Btst CLIMBED_SPIREA_LEDGE))
					(HighPrint 291 0)
					;It looks difficult. You look around for a good place to climb.
					)
				(ego setMotion: MoveTo (ego x?) 50)
				(= cycles 20)
			)
			(1
				(if (not (Btst CLIMBED_SPIREA_LEDGE)) (Bset CLIMBED_SPIREA_LEDGE)
					(HighPrint 291 1)
					;This looks like a good place to you.
					)
				(ego
					view: vEgoClimbing
					setMotion: 0
					illegalBits: 0
					setLoop: loopE
					x: (+ (ego x?) 6)
					cel: 0
				)
				(= cycles 2)
			)
			(2
				(ego setCycle: Forward)
				(self cue:)
			)
			(3
				(Print 291 2 #at -1 140 #width 300 #dispose #time 6)
				(= seconds 6)
				; You valiantly scrabble against the cliff wall, but it looks like your climbing skill could stand some improvement.
			)
			(4
				(ego
					view: vEgo
					setCycle: Walk
					setLoop: loopS
					x: (- (ego x?) 6)
				)
				(= cycles 2)
			)
			(5
				(ego setMotion: MoveTo (ego x?) (+ (ego y?) 5) self)
			)
			(6
				(HandsOn)
				(NormalEgo)
				(HighPrint 291 3)
				;Perhaps there is some other way to get to your objective.
				(client setScript: 0)
			)
		)
	)
)

(instance goodClimb of Script
	(properties)
	
	(method (dispose)
		(super dispose:)
		(DisposeScript 291)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego illegalBits: 0 setMotion: MoveTo (ego x?) 150 self)
				(if (not (Btst CLIMBED_SPIREA_LEDGE))
					(HighPrint 291 4)
					;You look for an appropriate place to climb.
					(Bset CLIMBED_SPIREA_LEDGE)
					)
			)
			(1
				(if (ego inRect: 135 140 143 154)
					(ego posn: 139 150)
					(self changeState: 3)
				else
					(ego setMotion: MoveTo 139 150 self)
				)
			)
			(2
				(ego setMotion: MoveTo 139 137 self)
			)
			(3
				(ego
					view: vEgoClimbing
					setLoop: 1
					cel: 0
					ignoreActors:
					posn: 144 105
					cycleSpeed: 2
					setCycle: EndLoop self
				)
			)
			(4
				(= spireaStatus 1)
				(HandsOn)
				(User canControl: FALSE)
				(self dispose:)
			)
		)
	)
)

(instance fallDown of Script
	(properties)
	
	(method (dispose)
		(super dispose:)
		(DisposeScript 291)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= spireaStatus 0)
				(self cue:)
			)
			(1
				(ego
					view: vEgoClimbing
					setLoop: 2
					cel: 0
					posn: 158 99
					cycleSpeed: 1
					setCycle: EndLoop self
				)
			)
			(2
				(if (not (TakeDamage 5))
					(EgoDead 291 5
						#icon vEgoClimbing 2 5
						#title {Your figure remains still and silent.})
						;Had you been healthier, you probably could have survived that fall. In your weakened condition, however, you succumbed to your injuries.
				else
					(TimePrint 3 291 6)
					;Ohhhhhhh!
					;
					(= seconds 3)
				)
			)
			(3
				(Print 291 7)
				(NormalEgo)
				(ego loop: 2 posn: 164 136)
				(HandsOn)
				(ego setScript: 0)
				; You remain unconscious on the ground for an indeterminate amount of time. As you finally revive, you realize that you've caused yourself a fair amount of damage.
			)
		)
	)
)

(instance catchIt of Script
	(properties)
	
	(method (dispose)
		(super dispose:)
		(DisposeScript 291)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(NormalEgo)
				(HandsOff)
				(ego
					illegalBits: 0
					ignoreActors:
					posn: 148 96
					setLoop: 3
					setMotion: MoveTo 157 92 self
				)
			)
			(1
				(ego setLoop: 1 setPri: 4 setMotion: MoveTo 149 88 self)
			)
			(2
				(if (== seedTarget 0) (= seedTarget (Random 1 3)))
				(= spireaStatus 3)
				(User canInput: TRUE)
				(ego
					view: vEgoCatchSeed
					setLoop: 5
					setCel: 0
					posn: 145 88
					setScript: 0
				)
			)
		)
	)
)
