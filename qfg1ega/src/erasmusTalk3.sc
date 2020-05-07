;;; Sierra Script 1.0 - (do not remove this comment)
(script# 134)
(include game.sh)
(use Main)
(use ErasmusTower)
(use Motion)
(use System)

(public
	mirrorTalk 0
	wizardTalk 1
	poopTalk 2
	deadTalk 3
	magicTalk 4
	gameTalk 5
)

(instance mirrorTalk of Script
	(properties)
	
	(method (doit)
		(if (and (not erasmusTalking) (not fenrusTalking)) (self cue:))
		(super doit:)
	)
	
	(method (dispose)
		(super dispose:)
		(DisposeScript 134)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= enableErasmusTeaCountdown 0)
				(ErasmusPrint 10 134 0)
				;There are many types of magic mirrors.  One of the more common types is the one that has a strange face popping up every so often.
			)
			(1
				(FenrusPrint 5 134 1)
				;Reminds me of the one in the master bedroom.
				)
			(2
				(ErasmusPrint 5 134 2)
				;That's not a magic mirror.
				)
			(3
				(FenrusPrint 6 134 3)
				;No, but it does show a strange face.  Have you looked in it lately?
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
				(= cycles 12)
			)
			(5
				(= fenrusTalking FALSE)
				((ScriptID 31 4)
					cycleSpeed: 1
					setLoop: 5
					setCel: 0
					setCycle: 0
				)
				(ego setCel: 2)
				(ErasmusPrint 11 134 4)
				;At any rate, I used to have a nice magic hand mirror, but I misplaced it somewhere.  It could be used to reflect a spell back upon the caster.
			)
			(6
				(ErasmusPrint 8 134 5)
				;So, if you happen upon a small golden magic mirror with a dragon on the handle, you'll know it's mine.
				)
			(7
				(FenrusPrint 8 134 6)
				;It was the flagon with the dragon.  The mirror had a leer.
				)
			(8
				(ErasmusPrint 4 134 7)
				;Oh.  That's right!
				)
			(9
				(ErasmusPrint 8 134 8)
				;Well, if you find a magic mirror with a face on it, you'll know it reflects spells.
				)
			(10 (ErasmusCastTeleport))
		)
	)
)

(instance wizardTalk of Script
	(properties)
	
	(method (doit)
		(if (and (not erasmusTalking) (not fenrusTalking)) (self cue:))
		(super doit:)
	)
	
	(method (dispose)
		(super dispose:)
		(DisposeScript 134)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= enableErasmusTeaCountdown 0)
				(ErasmusPrint 7 134 9)
				;Basically, anyone who uses magic is a magic user;   anyone who casts spells is a spellcaster.
				
			)
			(1
				(ErasmusPrint 10 134 10)
				;However, in order to be a wizard, you need to have undergone initiation into the "Wizard's Institute of Technocery" in the South.
				)
			(2
				(ErasmusPrint 5 134 11)
				;Magicians and sorcerers are wizards who specialize.
				)
			(3
				(ErasmusPrint 7 134 12)
				;For myself, I prefer to master all aspects of Magic unlike the narrow-minded specialists.
				)
			(4
				(FenrusPrint 5 134 13)
				;You wouldn't want to lose your amateur standing, after all.
				)
			(5
				(ErasmusPrint 5 134 14)
				;I prefer to think of myself as a dilettante.
				)
			(6
				(FenrusPrint 6 134 15)
				;Does that mean you tease pickles?
				)
			(7
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
			(8
				(= fenrusTalking FALSE)
				((ScriptID 31 4)
					cycleSpeed: 1
					setLoop: 5
					setCel: 0
					setCycle: 0
				)
				(ego setCel: 2)
				(ErasmusCastTeleport)
			)
		)
	)
)

(instance poopTalk of Script
	(properties)
	
	(method (doit)
		(if (and (not erasmusTalking) (not fenrusTalking)) (self cue:))
		(super doit:)
	)
	
	(method (dispose)
		(super dispose:)
		(DisposeScript 134)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= enableErasmusTeaCountdown 0)
				(ErasmusPrint 5 134 16)
				;He laughs too much when he casts his spells.
			)
			(1
				(FenrusPrint 5 134 17)
				;That's because his jokes are funnier than yours.
				)
			(2
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
			(3
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

(instance deadTalk of Script
	(properties)
	
	(method (doit)
		(if (and (not erasmusTalking) (not fenrusTalking)) (self cue:))
		(super doit:)
	)
	
	(method (dispose)
		(super dispose:)
		(DisposeScript 134)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= enableErasmusTeaCountdown 0)
				(FenrusPrint 7 134 18)
				;Isn't that like the vampire who likes to take necks out for a good time?
			)
			(1
				(ErasmusPrint 5 134 19)
				;No, no!  That's a "neck romancer"!
				)
			(2
				(ErasmusPrint 6 134 20)
				;A necromancer is a magic user who has a rather unsavory relationship with corpses.
				)
			(3
				(FenrusPrint 5 134 21)
				;Does that mean a necromancer has ghoul friends?
				)
			(4
				(ErasmusPrint 7 134 22)
				;It means, my dear Fenrus, that a necromancer is someone who deals with the dead.
				)
			(5
				(FenrusPrint 4 134 23)
				;Canasta with cadavers?
				)
			(6
				(FenrusPrint 4 134 24)
				;Spades with shades?
				)
			(7
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
			(8
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

(instance magicTalk of Script
	(properties)
	
	(method (doit)
		(if (and (not erasmusTalking) (not fenrusTalking)) (self cue:))
		(super doit:)
	)
	
	(method (dispose)
		(super dispose:)
		(DisposeScript 134)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= enableErasmusTeaCountdown 0)
				(ErasmusPrint 6 134 25)
				;"If Magic be the lute of life, play on!"
			)
			(1
				(FenrusPrint 8 134 26)
				;The quote was "Music", not "Magic"!  And it was "food", not "lute"!
				)
			(2
				(ErasmusPrint 3 134 27)
				;Oh!
				)
			(3
				(ErasmusPrint 6 134 28)
				;How about:  "Magic makes the world go 'round"?
				)
			(4
				(FenrusPrint 5 134 29)
				;"MONEY makes the world go 'round"!
				)
			(5
				(ErasmusPrint 9 134 30)
				;Very well, then.  To put it in my own words:  "Magic is the essence and the soul of life, and the Wizard is Her poet".
				)
			(6
				(FenrusPrint 7 134 31)
				;Actually, it was Merlin who said that first, but he used the term "Magician", not "Wizard".
				)
			(7
				(ErasmusPrint 6 134 32)
				;Fenrus, there are times when you'd look better as a newt!
				)
			(8
				(FenrusPrint 3 134 33)
				;Why is that?
				)
			(9
				(ErasmusPrint 4 134 34)
				;Because newts can't talk!
				)
			(10
				(FenrusPrint 4 134 35)
				;Neither can most rats.
				)
			(11
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
			(12
				(= fenrusTalking 0)
				((ScriptID 31 4)
					cycleSpeed: 1
					setLoop: 5
					setCel: 0
					setCycle: 0
				)
				(ErasmusCastTeleport)
			)
		)
	)
)

(instance gameTalk of Script
	(properties)
	
	(method (doit)
		(if (and (not erasmusTalking) (not fenrusTalking)) (self cue:))
		(super doit:)
	)
	
	(method (dispose)
		(super dispose:)
		(DisposeScript 134)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= enableErasmusTeaCountdown 0)
				(ErasmusPrint 11 134 36)
				;Just a little something to challenge the mind and the magic.
				;I like to play it every now and then, but Fenrus isn't much of a challenge anymore.
			)
			(1 (FenrusPrint 5 134 37)
				;That's because I always win.
				)
			(2
				(ErasmusPrint 5 134 38)
				;I still think you're cheating, somehow!
				)
			(3
				(ErasmusPrint 9 134 39)
				;Anyway, the game involves casting four spells:   "Flame Dart", "Open", "Fetch", and "Trigger".
				)
			(4
				(ErasmusPrint 9 134 40)
				;And to make it worth your while, if you beat me, I'll teach you a spell called "Dazzle".
				)
			(5 (ErasmusCastTeleport))
		)
	)
)
