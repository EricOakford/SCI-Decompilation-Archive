;;; Sierra Script 1.0 - (do not remove this comment)
(script# 135)
(include game.sh)
(use Main)
(use ErasmusTower)
(use Intrface)
(use Motion)
(use User)
(use System)

(public
	rexJoke 0
	torchJoke 1
	ottoJoke 2
	commaJoke 3
	dingeJoke 4
	noJoke 5
	flyJoke 6
	broomJoke 7
)

(local
	local0
)
(instance rexJoke of Script
	(properties)
	
	(method (dispose)
		(super dispose:)
		(DisposeScript 135)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= erasmusTellingJoke TRUE)
				(= enableErasmusTeaCountdown 0)
				(ErasmusPrint 11 135 0)
				;Do you know what you get when a Tyranno-\nsaurus running eastward meets a Tyranno-\nsaurus running westward?
			)
			(1
				(if saidYesToErasmus
					(ErasmusPrint 6 135 1)
					;That's a smashing good joke, I think.
					(= erasmusTellingJoke FALSE)
					(ErasmusCastTeleport)
				else
					(self cue:)
				)
				(= saidYesToErasmus 0)
			)
			(2
				(ErasmusPrint 5 135 2)
				;Tyranno-saurus wrecks!
				(= cycles 5)
			)
			(3
				(ego setCel: 3)
				((ScriptID 31 4)
					cycleSpeed: 0
					setLoop: 6
					setCel: -1
					setCycle: Forward
				)
				(= cycles 20)
			)
			(4
				(ErasmusCastTeleport)
				(= erasmusTellingJoke 0)
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

(instance torchJoke of Script
	(properties)
	
	(method (dispose)
		(super dispose:)
		(DisposeScript 135)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= erasmusTellingJoke 1)
				(= enableErasmusTeaCountdown 0)
				(ErasmusPrint 8 135 3)
				;Is it true that a monster can't hurt you if you carry a torch?
			)
			(1
				(ErasmusPrint 4 135 4)
				;Actually, it depends on how fast you carry it!
				(= cycles 5)
			)
			(2
				(ego setCel: 3)
				((ScriptID 31 4)
					cycleSpeed: 0
					setLoop: 6
					setCel: -1
					setCycle: Forward
				)
				(= cycles 20)
			)
			(3
				(ErasmusCastTeleport)
				(= erasmusTellingJoke 0)
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

(instance ottoJoke of Script
	(properties)
	
	(method (dispose)
		(super dispose:)
		(DisposeScript 135)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= erasmusTellingJoke 1)
				(= enableErasmusTeaCountdown 0)
				(ErasmusPrint 8 135 5)
				;Do you know what time it is when Otto walks through your door?
			)
			(1
				(if saidYesToErasmus
					(ErasmusPrint 6 135 6)
					;So you've met Otto already.
					(= erasmusTellingJoke 0)
					(ErasmusCastTeleport)
				else
					(self cue:)
				)
				(= saidYesToErasmus 0)
			)
			(2
				(ErasmusPrint 5 135 7)
				;Time to get a new door!
				(= cycles 5)
			)
			(3
				(ego setCel: 3)
				((ScriptID 31 4)
					cycleSpeed: 0
					setLoop: 6
					setCel: -1
					setCycle: Forward
				)
				(= cycles 20)
			)
			(4
				(ErasmusCastTeleport)
				(= erasmusTellingJoke 0)
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

(instance commaJoke of Script
	(properties)
	
	(method (dispose)
		(super dispose:)
		(DisposeScript 135)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= erasmusTellingJoke 1)
				(= enableErasmusTeaCountdown 0)
				(ErasmusPrint 6 135 8)
				;Do you know the difference between a Cheetaur and a comma?
			)
			(1
				(if saidYesToErasmus
					(ErasmusPrint 3 135 9)
					;Spoilsport!
					(= erasmusTellingJoke 0)
					(ErasmusCastTeleport)
				else
					(self cue:)
				)
				(= saidYesToErasmus 0)
			)
			(2
				(ErasmusPrint 5 135 10)
				;A Cheetaur has claws at the end of its paws, and a comma is a pause at the end of a clause!
				(= cycles 5)
			)
			(3
				(ego setCel: 3)
				((ScriptID 31 4)
					cycleSpeed: 0
					setLoop: 6
					setCel: -1
					setCycle: Forward
				)
				(= cycles 20)
			)
			(4
				(ErasmusCastTeleport)
				(= erasmusTellingJoke 0)
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

(instance dingeJoke of Script
	(properties)
	
	(method (doit)
		(if
		(and (not erasmusTalking) (not fenrusTalking) (not local0))
			(self cue:)
		)
		(super doit:)
	)
	
	(method (dispose)
		(super dispose:)
		(DisposeScript 135)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= erasmusTellingJoke 1)
				(= enableErasmusTeaCountdown 0)
				(= local0 1)
				(ErasmusPrint 6 135 11)
				;Do you know what's big, purple, and eats ungefaehr-liche dinge?
			)
			(1
				(= local0 0)
				(if saidYesToErasmus
					(ErasmusPrint 4 135 12)
					;Well...let me figure out something tougher.
					(= erasmusTellingJoke 0)
					(ErasmusCastTeleport)
				)
				(= saidYesToErasmus 0)
			)
			(2
				(User canInput: FALSE)
				(ErasmusPrint 4 135 13)
				;Big, purple ungefaehr-liche dinge eaters!
			)
			(3
				(FenrusPrint 6 135 14)
				;Large purple ungefaehr-liche dinge eaters are all too common in the forest these days.
				)
			(4
				(ego setCel: 3)
				((ScriptID 31 4)
					cycleSpeed: 0
					setLoop: 6
					setCel: -1
					setCycle: Forward
				)
				(= cycles 12)
			)
			(5
				(User canInput: TRUE)
				(ErasmusCastTeleport)
				(= erasmusTellingJoke 0)
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

(instance noJoke of Script
	(properties)
	
	(method (dispose)
		(super dispose:)
		(DisposeScript 135)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= erasmusTellingJoke 1)
				(= enableErasmusTeaCountdown 0)
				(ErasmusPrint 6 135 15)
				;Do you know which has more legs, one cow or no cow?
			)
			(1
				(if saidYesToErasmus
					(ErasmusPrint 8 135 16)
					;Too easy, huh?  Well, I'll just have to think of a better one.
					(= erasmusTellingJoke 0)
					(ErasmusCastTeleport)
				else
					(self cue:)
				)
				(= saidYesToErasmus 0)
			)
			(2
				(ErasmusPrint 4 135 17)
				;You're right!  NO cow has more than four legs.  These are too easy.
				(= erasmusTellingJoke 0)
				(ErasmusCastTeleport)
			)
		)
	)
)

(instance flyJoke of Script
	(properties)
	
	(method (doit)
		(if
		(and (not erasmusTalking) (not fenrusTalking) (not local0))
			(self cue:)
		)
		(super doit:)
	)
	
	(method (dispose)
		(super dispose:)
		(DisposeScript 135)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= erasmusTellingJoke 1)
				(= enableErasmusTeaCountdown 0)
				(= local0 1)
				(ErasmusPrint 8 135 18)
				;Do you know the difference between a wounded Triceratops and a magic user casting the "Fly" spell?
			)
			(1
				(= local0 0)
				(if saidYesToErasmus
					(ErasmusPrint 4 135 19)
					;Darn.  I thought I made that one up myself!
					(= erasmusTellingJoke 0)
					(ErasmusCastTeleport)
				)
				(= saidYesToErasmus FALSE)
			)
			(2
				(User canInput: FALSE)
				(ErasmusPrint 4 135 20)
				;One is a raging Saurus and the other is a soaring Magus!
			)
			(3
				(FenrusPrint 4 135 21)
				;I figured one was a dying lizard and the other was a flying wizard.
				)
			(4
				((ScriptID 31 4)
					cycleSpeed: 0
					setLoop: 6
					setCel: -1
					setCycle: Forward
				)
				(ego setCel: 3)
				(ErasmusPrint 4 135 22)
				;Sorry, Fenrus, but "He who tells the jokes gets to make up the punch lines".
			)
			(5
				(User canInput: TRUE)
				((ScriptID 31 4)
					cycleSpeed: 1
					setLoop: 5
					setCel: 0
					setCycle: 0
				)
				(ErasmusCastTeleport)
				(= erasmusTellingJoke 0)
			)
		)
	)
)

(instance broomJoke of Script
	(properties)
	
	(method (doit)
		(if
		(and (not erasmusTalking) (not fenrusTalking) (not local0))
			(self cue:)
		)
		(super doit:)
	)
	
	(method (dispose)
		(super dispose:)
		(DisposeScript 135)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= erasmusTellingJoke 1)
				(= enableErasmusTeaCountdown 0)
				(= local0 1)
				(ErasmusPrint 4 135 23)
				;Did you hear the joke about the witches broom?
			)
			(1
				(= local0 0)
				(if saidYesToErasmus
					(ErasmusPrint 4 135 24)
					;Oh.  That's a pity!
					(= erasmusTellingJoke 0)
					(ErasmusCastTeleport)
				)
				(= saidYesToErasmus 0)
			)
			(2
				(User canInput: FALSE)
				(ErasmusPrint 4 135 25)
				;That's odd.  It's sweeping the valley!
			)
			(3
				(FenrusPrint 4 135 26)
				;That one usually floors me, but I'm going to brush it aside before I'm swept away with laughter.
				)
			(4
				(ego setCel: 3)
				(= cycles 5)
			)
			(5
				(Print 135 27 #at 164 85)
				(= erasmusTellingJoke 0)
				(User canInput: 1)
				(ErasmusCastTeleport)
				; (Gag!)
			)
		)
	)
)
