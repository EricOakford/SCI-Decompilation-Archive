;;; Sierra Script 1.0 - (do not remove this comment)
(script# RENTAROOM) ;167
(include game.sh)
(use Main)
(use Motion)
(use System)

(public
	rentARoom 0
)

(instance rentARoom of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (Btst SLEPT_AT_INN)
					(self changeState: 4)
				else
					(SolvePuzzle POINTS_RENTROOM 1)
					(if (Btst SHAMEEN_SIGNALING)
						(self cue:)
					else
						((ScriptID 301 1) setCycle: CycleTo 2 1 self)
						(Bset SHAMEEN_SIGNALING)
					)
				)
			)
			(1
				(HandsOff)
				(HighPrint 167 0)
				;"May you dream of all the rewards you deserve."
				(HighPrint 167 1)
				;You thank Shameen and pay him 5 silvers for the room.
				((ScriptID 301 1) setLoop: 4 cel: 2 setCycle: BegLoop)
				((ScriptID 301 4) setCycle: EndLoop self)
			)
			(2
				(Bclr SHAMEEN_SIGNALING)
				(ego ignoreActors: illegalBits: 0)
				(if (ego inRect: 239 125 319 133)
					(ego setMotion: MoveTo (ego x?) 130 self)
				else
					(= cycles 1)
				)
			)
			(3
				(ego setMotion: MoveTo 10 130 self)
			)
			(4
				(Bset RENTED_ROOMATINN)
				(cast eachElementDo: #dispose)
				(curRoom drawPic: 400 16)
				(self cue:)
			)
			(5
				(Bclr RENTED_ROOMATINN)
				(curRoom newRoom: 302)
			)
		)
	)
)
