;;; Sierra Script 1.0 - (do not remove this comment)
(script# 133)
(include game.sh)
(use Main)
(use ErasmusTower)
(use Motion)
(use System)

(public
	eranaTalk 0
	peaceTalk 1
	zaraTalk 2
	babaTalk 3
	curseTalk 4
	counterCurseTalk 5
	fenrusTalk 6
	hermitTalk 7
	initiationTalk 8
)

(instance eranaTalk of Script
	(properties)
	
	(method (doit)
		(if (and (not erasmusTalking) (not fenrusTalking)) (self cue:))
		(super doit:)
	)
	
	(method (dispose)
		(super dispose:)
		(DisposeScript 133)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= enableErasmusTeaCountdown 0)
				(ErasmusPrint 5 133 0)
				;Never met her.  I only built this vacation home here a century ago.
			)
			(1
				(ErasmusPrint 9 133 1)
				;Erana has a nice little magical meadow in her name northeast of here.  Pretty respectable Spellcaster, I imagine.
				)
			(2
				(ErasmusPrint 9 133 2)
				;The Protection spell she cast over the town is fairly good, but I believe she missed a few places.
				)
			(3
				(FenrusPrint 8 133 3)
				;You're just jealous because the only thing anyone named after you was a soft drink.
				)
			(4
				(= fenrusTalking 1)
				((ScriptID 31 4)
					cycleSpeed: 0
					setLoop: 6
					setCel: -1
					setCycle: Forward
				)
				(= cycles 6)
			)
			(5
				(= fenrusTalking 0)
				((ScriptID 31 4)
					cycleSpeed: 1
					setLoop: 5
					setCel: 0
					setCycle: 0
				)
				(ErasmusPrint 10 133 4)
				;I thought that "Erasmus' Rootin' Tootin' Root Beer" was a WONDERFUL name for a product!  It's a shame the company went broke after that.
			)
			(6
				(= enableErasmusTeaCountdown 1)
				(client setScript: 0)
			)
		)
	)
)

(instance peaceTalk of Script
	(properties)
	
	(method (doit)
		(if (and (not erasmusTalking) (not fenrusTalking)) (self cue:))
		(super doit:)
	)
	
	(method (dispose)
		(super dispose:)
		(DisposeScript 133)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= enableErasmusTeaCountdown 0)
				(ErasmusPrint 9 133 5)
				;Erana's spell prevents the use of excessive violence or hostile magic within Spielburg's walls, with the exception of a couple of places.
			)
			(1
				(ErasmusPrint 9 133 6)
				;It looks to me from up here as if she missed the back part of the alley, and the spell applies only to areas in town above the ground.
				)
			(2
				(ErasmusPrint 5 133 7)
				;Pretty shoddy work, if you ask me!
				)
			(3
				(FenrusPrint 4 133 8)
				;Nobody asked you.
				)
			(4
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
			(5
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

(instance zaraTalk of Script
	(properties)
	
	(method (doit)
		(if (and (not erasmusTalking) (not fenrusTalking)) (self cue:))
		(super doit:)
	)
	
	(method (dispose)
		(super dispose:)
		(DisposeScript 133)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= enableErasmusTeaCountdown 0)
				(ErasmusPrint 9 133 9)
				;Zara has a real flair for the theatric.  Her method of greeting customers at her shop is showy but effective.
			)
			(1
				(ErasmusPrint 6 133 10)
				;It's a pity she has no sense of humor.
				)
			(2
				(FenrusPrint 8 133 11)
				;Just because she never laughs at your jokes doesn't mean that she lacks a sense of humor.
				)
			(3
				(FenrusPrint 5 133 12)
				;It just means that she has good taste.
				)
			(4
				(= fenrusTalking 1)
				(ego setCel: 3)
				((ScriptID 31 4)
					cycleSpeed: 0
					setLoop: 6
					setCel: -1
					setCycle: Forward
				)
				(= cycles 8)
			)
			(5
				(ErasmusCastTeleport)
				(= fenrusTalking 0)
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

(instance babaTalk of Script
	(properties)
	
	(method (doit)
		(if (and (not erasmusTalking) (not fenrusTalking)) (self cue:))
		(super doit:)
	)
	
	(method (dispose)
		(super dispose:)
		(DisposeScript 133)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= enableErasmusTeaCountdown 0)
				(ErasmusPrint 9 133 13)
				;Baba Yaga is good at curses and shape-\nchanging spells.  She has a nasty temper, so it's best to stay on her good side.
			)
			(1
				(ErasmusPrint 6 133 14)
				;You have to watch her.  She cheats at cards.
				)
			(2
				(FenrusPrint 4 133 15)
				;So do you!
				)
			(3
				(ErasmusPrint 7 133 16)
				;She started it!  I just wanted to give her a taste of her own medicine.
				)
			(4
				(FenrusPrint 5 133 17)
				;It's a shame she still beats you!
				)
			(5
				(= fenrusTalking 1)
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
				(= fenrusTalking 0)
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

(instance curseTalk of Script
	(properties)
	
	(method (doit)
		(if (and (not erasmusTalking) (not fenrusTalking)) (self cue:))
		(super doit:)
	)
	
	(method (dispose)
		(super dispose:)
		(DisposeScript 133)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= enableErasmusTeaCountdown 0)
				(ErasmusPrint 7 133 18)
				;The curse Baba Yaga placed upon the Baron von Spielburg was a fine example of creative cursing.
			)
			(1
				(ErasmusPrint 10 133 19)
				;"Upon von Spielburg and all his clan, this Curse I now demand:   What I will shall come full measure, so shall ye lose all that ye treasure."
				)
			(2
				(ErasmusPrint 7 133 20)
				;Of course, the problem is that for every curse there is an equal and opposite countercurse.
				)
			(3 (ErasmusCastTeleport))
		)
	)
)

(instance counterCurseTalk of Script
	(properties)
	
	(method (doit)
		(if (and (not erasmusTalking) (not fenrusTalking)) (self cue:))
		(super doit:)
	)
	
	(method (dispose)
		(super dispose:)
		(DisposeScript 133)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= enableErasmusTeaCountdown 0)
				(ErasmusPrint 10 133 21)
				;A countercurse is the cure for a curse.  It usually works against the one who cast the curse in the first place.
			)
			(1
				(ErasmusPrint 8 133 22)
				;The countercurse for the curse on the Baron goes:
				)
			(2
				(HeroPrint 133 23)
				;"Come a hero from the East..."
				)
			(3
				(HeroPrint 133 24)
				;"Free the man from in the beast..."
				)
			(4
				(HeroPrint 133 25)
				;"Bring the child from out the band..."
				)
			(5
				(HeroPrint 133 26)
				;"Drive the Curser from the land."
				)
			(6
				(ErasmusPrint 11 133 27)
				;So, to break the curse, a hero must get rid of Baba Yaga, in addition to all the other things.  It does not reflect well on Baba Yaga.
				)
			(7
				(FenrusPrint 8 133 28)
				;Nothing reflects well upon that Ogress.  She has a face that would break all but a Magic Mirror.
				)
			(8
				(ErasmusCastTeleport)
				)
		)
	)
)

(instance fenrusTalk of Script
	(properties)
	
	(method (doit)
		(if (and (not erasmusTalking) (not fenrusTalking)) (self cue:))
		(super doit:)
	)
	
	(method (dispose)
		(super dispose:)
		(DisposeScript 133)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= enableErasmusTeaCountdown 0)
				(FenrusPrint 9 133 29)
				;What am I, chopped liver?  Am I invisible or something that you talk about me behind my front?
			)
			(1
				(ErasmusPrint 6 133 30)
				;It's hard to take you seriously when you wear that silly hat!
				)
			(2 (ErasmusCastTeleport))
		)
	)
)

(instance hermitTalk of Script
	(properties)
	
	(method (doit)
		(if (and (not erasmusTalking) (not fenrusTalking)) (self cue:))
		(super doit:)
	)
	
	(method (dispose)
		(super dispose:)
		(DisposeScript 133)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= enableErasmusTeaCountdown 0)
				(ErasmusPrint 7 133 31)
				;Henry is a hermit who lives by the Flying Falls to the south. Great sense of humor.
			)
			(1 (FenrusPrint 6 133 32)
				;That means he actually laughs at Erasmus' jokes.
				)
			(2
				(ErasmusPrint 8 133 33)
				;I think he borrowed something the last time I saw him, but I can't remember exactly what it was.
				)
			(3
				(ErasmusPrint 5 133 34)
				;Oh, well, Henry is a great one to talk to.
				)
			(4
				(FenrusPrint 5 133 35)
				;But 'e 'ardly ever 'appens to 'ave much to say.
				)
			(5 (ErasmusCastTeleport))
		)
	)
)

(instance initiationTalk of Script
	(properties)
	
	(method (doit)
		(if (and (not erasmusTalking) (not fenrusTalking)) (self cue:))
		(super doit:)
	)
	
	(method (dispose)
		(super dispose:)
		(DisposeScript 133)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= enableErasmusTeaCountdown 0)
				(ErasmusPrint 7 133 36)
				;You need to complete your quest here before you can become initiated as a Wizard.
			)
			(1 (ErasmusCastTeleport))
		)
	)
)
