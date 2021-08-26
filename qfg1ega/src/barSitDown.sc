;;; Sierra Script 1.0 - (do not remove this comment)
(script# 338)
(include game.sh)
(use Main)
(use Motion)
(use User)
(use System)

(public
	sitDown 0
	getDown 1
)

(instance sitDown of Script
	(method (dispose)
		(super dispose:)
		(DisposeScript 338)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Bset fEgoSitting)
				(HandsOff)
				(ego illegalBits: 0)
				(if (> (ego x?) 149)
					(ego setMotion: MoveTo 163 (ego y?) self)
				else
					(ego setMotion: MoveTo 134 (ego y?) self)
				)
			)
			(1
				(if (> (ego x?) 149)
					(ego setMotion: MoveTo 163 114 self)
				else
					(ego setMotion: MoveTo 134 114 self)
				)
			)
			(2
				(ego
					view: vEgoInsideBar
					loop: (if (> (ego x?) 149) 1 else 0)
					posn: 149 111
					setPri: 9
				)
				(self cue:)
			)
			(3
				(ego setCycle: EndLoop self)
			)
			(4
				(ego loop: 2 cel: 0 stopUpd:)
				((ScriptID 331 5) show: setPri: 8)
				(HandsOn)
				(User canControl: FALSE)
				((ScriptID 331 8) changeState: 6)
				(self dispose:)
			)
		)
	)
)

(instance getDown of Script
	(method (dispose)
		(super dispose:)
		(DisposeScript 338)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				((ScriptID 331 5) hide:)
				(ego loop: 1 cel: 7 setCycle: BegLoop self)
			)
			(1
				(NormalEgo)
				(ego
					loop: loopS
					cel: 5
					illegalBits: cWHITE
					setCycle: Walk
					posn: 162 114
				)
				(Bclr fEgoSitting)
				((ScriptID 331 8) changeState: 0)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)
