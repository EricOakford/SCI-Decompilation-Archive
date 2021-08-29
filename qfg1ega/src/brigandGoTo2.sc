;;; Sierra Script 1.0 - (do not remove this comment)
(script# 228)
(include game.sh)
(use Main)
(use Motion)
(use User)
(use System)

(public
	goTo2 0
	goTo6 1
	goTo12 2
	goTo15 3
)

(procedure (Disoriented)
	(switch (++ yesNoTimer)
		(1
			(HighPrint 228 0)
			;You feel disoriented.
			)
		(2
			(HighPrint 228 1)
			;How does he DO this?
			)
		(3
			(HighPrint 228 2)
			;There's an ECHO IN HERE...Echo In Here...echo in here...
			)
		(4
			(HighPrint 228 3)
			;M. C. Escher would LOVE this place.
			)
		(5
			(HighPrint 228 4)
			;OK, enough fooling around!
			)
		(6
			(HighPrint 228 5)
			;This is getting old!
			)
		(7
			(HighPrint 228 6)
			;A person COULD get used to this.
		)
	)
)

(instance goTo2 of Script
	(method (dispose)
		(super dispose:)
		(DisposeScript 228)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: MoveTo (+ (ego x?) 20) (ego y?) self)
			)
			(1
				(Disoriented)
				(= cycles 10)
			)
			(2
				(ego
					setPri: 7
					illegalBits: 0
					posn: 22 73
					setMotion: MoveTo 37 73 self
				)
			)
			(3
				(HandsOn)
				(ego illegalBits: cWHITE)
				(Bclr fGoThruDoorway)
				(client setScript: 0)
			)
		)
	)
)

(instance goTo6 of Script
	(method (dispose)
		(super dispose:)
		(DisposeScript 228)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					illegalBits: 0
					setLoop: 3
					setMotion: MoveTo 215 97 self
				)
			)
			(1
				(ego setPri: 3 setMotion: MoveTo 194 83 self)
			)
			(2
				((ScriptID 96 9) setCycle: BegLoop self)
			)
			(3
				(Disoriented)
				(NormalEgo)
				((ScriptID 96 9) stopUpd:)
				(ego posn: 49 102 setMotion: MoveTo 87 102 self)
			)
			(4
				(HandsOn)
				(User canInput: TRUE)
				(Bclr fGoThruDoorway)
				(client setScript: 0)
			)
		)
	)
)

(instance goTo12 of Script
	(method (dispose)
		(super dispose:)
		(DisposeScript 228)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					illegalBits: 0
					setMotion: MoveTo (- (ego x?) 25) (ego y?) self
				)
			)
			(1
				(Disoriented)
				((ScriptID 96 10) setCycle: BegLoop self)
			)
			(2
				(ego
					view: vEgoJesterRoom
					setLoop: 8
					cel: 0
					posn: 265 102
					cycleSpeed: 1
					moveSpeed: 1
					setMotion: MoveTo 234 102 self
				)
			)
			(3
				(ego setLoop: 9 setCel: 0 posn: 231 101)
				(= cycles 3)
			)
			(4
				(ego setLoop: 1)
				(NormalEgo)
				((ScriptID 96 10) setCycle: EndLoop self)
			)
			(5
				(HandsOn)
				(Bset fNoMoreTalking)
				(Bclr fGoThruDoorway)
				(client setScript: 0)
			)
		)
	)
)

(instance goTo15 of Script
	(method (dispose)
		(super dispose:)
		(DisposeScript 228)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					illegalBits: 0
					setMotion: MoveTo (- (ego x?) 15) (ego y?) self
				)
			)
			(1
				(Disoriented)
				(ego posn: 325 129 setMotion: MoveTo 281 129 self)
			)
			(2
				(if (Btst fPulledChain)
					(Bset fNoMoreTalking)
				)
				(NormalEgo)
				(HandsOn)
				(Bclr fGoThruDoorway)
				(client setScript: 0)
			)
		)
	)
)
