;;; Sierra Script 1.0 - (do not remove this comment)
(script# 132)
(include game.sh)
(use Main)
(use ErasmusTower)
(use Motion)
(use System)

(public
	openTalk 0
	fetchTalk 1
	triggerTalk 2
	dazzleTalk 3
	trapTalk 4
	goGame 5
)

(instance openTalk of Script
	(properties)
	
	(method (doit)
		(if (and (not erasmusTalking) (not fenrusTalking)) (self cue:))
		(super doit:)
	)
	
	(method (dispose)
		(super dispose:)
		(DisposeScript 132)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= enableErasmusTeaCountdown 0)
				(ErasmusPrint 7 132 0)
				;The "Open" spell lets you open things with a mere gesture.
			)
			(1
				(ErasmusPrint 7 132 1)
				;If you're really good at it, you can open locks.  It's useful when you lose your keys.
				)
			(2
				(FenrusPrint 5 132 2)
				;Your keys are in the study under the skull.
				)
			(3
				(ErasmusPrint 4 132 3)
				;Why would they be there?
				)
			(4
				(FenrusPrint 4 132 4)
				;They're skeleton keys, aren't they?
				)
			(5
				(= fenrusTalking TRUE)
				(ego setCel: 3)
				((ScriptID 31 4)
					cycleSpeed: 0
					setLoop: 6
					setCel: -1
					setCycle: Forward
				)
				(= cycles 8)
			)
			(6
				(ErasmusCastTeleport)
				(= fenrusTalking FALSE)
				((ScriptID 31 4)
					cycleSpeed: 1
					setLoop: 5
					setCel: 0
					setCycle: 0
				)
			)
		)
	)
)

(instance fetchTalk of Script
	(properties)
	
	(method (doit)
		(if (and (not erasmusTalking) (not fenrusTalking)) (self cue:))
		(super doit:)
	)
	
	(method (dispose)
		(super dispose:)
		(DisposeScript 132)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= enableErasmusTeaCountdown 0)
				(ErasmusPrint 7 132 5)
				;"Fetch" is a useful spell for catching and retrieving small objects at a distance.
			)
			(1
				(ErasmusPrint 5 132 6)
				;It's good for putting things in their place.
				)
			(2
				(FenrusPrint 4 132 7)
				;Like uppity familiars?
				)
			(3
				(ErasmusPrint 6 132 8)
				;I'd put you in your place, but I'm above such things.
				)
			(4
				(FenrusPrint 5 132 9)
				;Besides, my place is above your reach.
				)
			(5 (ErasmusCastTeleport))
		)
	)
)

(instance triggerTalk of Script
	(properties)
	
	(method (doit)
		(if (and (not erasmusTalking) (not fenrusTalking)) (self cue:))
		(super doit:)
	)
	
	(method (dispose)
		(super dispose:)
		(DisposeScript 132)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= enableErasmusTeaCountdown 0)
				(ErasmusPrint 9 132 10)
				;The "Trigger" spell is used to set off an existing spell, just like pulling the spell's "trigger".
			)
			(1
				(ErasmusPrint 9 132 11)
				;For example, if a chest has a magical trap on it, you can set off the trap by casting a "Trigger" spell.
				)
			(2
				(ErasmusPrint 6 132 12)
				;Just make sure you're out of range of the trap.
				)
			(3
				(ErasmusPrint 7 132 13)
				;I can set up other spells and let someone else "Trigger" them.
				)
			(4
				(ErasmusPrint 7 132 14)
				;I fixed Henry the Hermit's cave for him so that he can do alot of magic with only one spell.
				)
			(5 (ErasmusCastTeleport))
		)
	)
)

(instance dazzleTalk of Script
	(properties)
	
	(method (doit)
		(if (and (not erasmusTalking) (not fenrusTalking)) (self cue:))
		(super doit:)
	)
	
	(method (dispose)
		(super dispose:)
		(DisposeScript 132)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= enableErasmusTeaCountdown 0)
				(ErasmusPrint 5 132 15)
				;Now that's a spell with charm!
			)
			(1
				(ErasmusPrint 6 132 16)
				;It's true name is Erasmus' Razzle Dazzle, by the way.
				)
			(2
				(ErasmusPrint 10 132 17)
				;You cast the spell and anyone looking directly at its corruscating light is blinded for awhile and can't do anything.
				)
			(3 (ErasmusCastTeleport))
		)
	)
)

(instance trapTalk of Script
	(properties)
	
	(method (doit)
		(if (and (not erasmusTalking) (not fenrusTalking)) (self cue:))
		(super doit:)
	)
	
	(method (dispose)
		(super dispose:)
		(DisposeScript 132)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= enableErasmusTeaCountdown 0)
				(ErasmusPrint 8 132 18)
				;Magical traps are a good way to keep thieves out of your chests and other places.
			)
			(1
				(ErasmusPrint 9 132 19)
				;Put a fire trap, for example, on a chest.  When someone tries to open it, the thief will go up in smoke!
				)
			(2
				(FenrusPrint 7 132 20)
				;Tell our hero here what happens when you forget and try to open the chest yourself.
				)
			(3
				(= fenrusTalking TRUE)
				(ego setCel: 3)
				((ScriptID 31 4)
					cycleSpeed: 0
					setLoop: 6
					setCel: -1
					setCycle: Forward
				)
				(= cycles 12)
			)
			(4
				(= fenrusTalking FALSE)
				((ScriptID 31 4)
					cycleSpeed: 1
					setLoop: 5
					setCel: 0
					setCycle: 0
				)
				(ego setCel: 2)
				(ErasmusPrint 5 132 21)
				;I only got singed the last time.  Nothing major.
			)
			(5 (ErasmusCastTeleport))
		)
	)
)

(instance goGame of Script
	(properties)
	
	(method (dispose)
		(super dispose:)
		(DisposeScript 132)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				((ScriptID 31 3)
					cycleSpeed: 0
					cel: 0
					posn: 160 102
					setPri: 14
					setCycle: EndLoop self
				)
			)
			(1
				(ego setCel: 0)
				((ScriptID 31 9)
					setLoop: 0
					setCel: 0
					setMotion: MoveTo 143 119
					startUpd:
				)
				((ScriptID 31 10)
					setLoop: 0
					setCel: 0
					setMotion: MoveTo 178 119 self
					startUpd:
				)
			)
			(2 (curRoom newRoom: 32))
		)
	)
)
