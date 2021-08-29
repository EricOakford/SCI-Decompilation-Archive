;;; Sierra Script 1.0 - (do not remove this comment)
(script# 288)
(include game.sh)
(use Main)
(use Motion)
(use System)

(public
	vaseScript 0
	upScript 1
	downScript 2
	rm321Script 3
	raisePainting 4
	lowerPainting 5
)

(local
	egoUpstairs
)
(procedure (BreakInPrint)
	(if egoUpstairs
		(CenterPrint &rest)
	else
		(HighPrint &rest)
	)
)

(instance vaseScript of Script
	(method (dispose)
		(super dispose:)
		(DisposeScript 288)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(BreakInPrint 288 0)
				;As you reach toward the painting...
				((ScriptID 321 1) startUpd: setCycle: EndLoop self)
			)
			(1
				((ScriptID 321 1) cel: 0 setCycle: EndLoop self)
			)
			(2
				((ScriptID 321 1)
					setLoop: 6
					setCel: 0
					posn: 253 123
					setStep: 0 8
					setMotion: MoveTo 253 147 self
				)
			)
			(3
				((ScriptID 321 2)
					loop: 1
					number: (SoundFX 14)
					play:
				)
				((ScriptID 321 1) setCel: 1)
				(= cycles 1)
			)
			(4
				((ScriptID 321 1) setCel: 2 stopUpd:)
				(HandsOn)
				(= cycles 2)
			)
			(5
				(BreakInPrint 288 1)
				;From somewhere upstairs, you hear the Sheriff shout: "Otto!".
				(ego setScript: (ScriptID 289 0))
			)
		)
	)
)

(instance upScript of Script
	(method (dispose)
		(super dispose:)
		(DisposeScript 288)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego illegalBits: 0 setMotion: MoveTo 83 60 self)
			)
			(1
				(HandsOn)
				(ego illegalBits: (| cWHITE cLCYAN))
				(ego setScript: 0)
			)
		)
	)
)

(instance downScript of Script
	(method (dispose)
		(super dispose:)
		(DisposeScript 288)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego illegalBits: 0 setMotion: MoveTo 36 86 self)
			)
			(1
				(HandsOn)
				(ego illegalBits: (| cWHITE cLCYAN))
				(ego setScript: 0)
			)
		)
	)
)

(instance rm321Script of Script
	(method (dispose)
		(super dispose:)
		(DisposeScript 288)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (and (!= prevRoomNum 0) (not (Btst fBeenIn321)))
					(= cycles 8)
				else
					((ScriptID 321 0) setScript: 0)
				)
			)
			(1
				(BreakInPrint 288 2)
				;The people who own this house must have some money. Everything looks new and there is not a speck of dust visible.
				(BreakInPrint 288 3)
				;The room smells vaguely of sauerkraut and bratwurst, with just a faint odor of smoke from pinewood.
				;From somewhere in the house you can hear someone snoring.
				((ScriptID 321 0) setScript: 0)
			)
		)
	)
)

(instance raisePainting of Script
	(method (dispose)
		(super dispose:)
		(DisposeScript 288)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				((ScriptID 321 4) posn: 277 109)
				(Bset fUncoveredSafe)
				(= cycles 2)
			)
			(1
				(BreakInPrint 288 4)
				;By lifting the painting, you can see what certainly must be a safe, hidden in the wall.
				((ScriptID 321 0) setScript: 0)
			)
		)
	)
)

(instance lowerPainting of Script
	(method (dispose)
		(super dispose:)
		(DisposeScript 288)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				((ScriptID 321 4) posn: 277 121)
				(Bclr fUncoveredSafe)
				(= cycles 2)
			)
			(1
				(BreakInPrint 288 5)
				;You carefully lower the painting into its original position.
				((ScriptID 321 0) setScript: 0)
			)
		)
	)
)
