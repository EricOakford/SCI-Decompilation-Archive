;;; Sierra Script 1.0 - (do not remove this comment)
(script# 131)
(include game.sh)
(use Main)
(use ErasmusTower)
(use Intrface)
(use Motion)
(use User)
(use Actor)
(use System)

(public
	intoTheTower 0
	gameOver 1
)

(local
	local0
	local1 =  1
	local2
	local3
	local4
	spellMask = [1 2 4 8 16]
	spellAsked = [{Open} {Fetch} {Flame Dart} {Trigger}]
)
(instance cheese of View
	(properties
		y 122
		x 161
		view vWizardTable
		cel 4
	)
)

(instance intoTheTower of Script
	(method (init)
		(cheese init: setPri: 7 ignoreActors: stopUpd:)
		(super init: &rest)
	)
	
	(method (doit)
		(super doit:)
		(if (and local4 (not erasmusTalking) (not local2))
			(self cue:)
		)
	)
	
	(method (dispose)
		(super dispose:)
		(DisposeScript 131)
	)
	
	(method (changeState newState &tmp temp0 [str 40])
		(switch (= state newState)
			(0
				(ego
					illegalBits: 0
					setPri: 8
					posn: 200 208
					setMotion: MoveTo 175 183 self
				)
			)
			(1
				((ScriptID 31 1) setCycle: Forward)
				(= cycles 4)
			)
			(2
				(Print
					131 1
					#at 5 40
					#width 80
					#mode teJustCenter
					#title { Erasmus_}
					#dispose
					#time 3
				)
				(ego setMotion: MoveTo 155 163 self)
				; Come in and sit down.
			)
			(3
				(NormalEgo)
				(ego illegalBits: 0 setMotion: MoveTo 155 158 self)
			)
			(4
				((ScriptID 31 1) setCel: 0 setCycle: 0)
				(ego setMotion: MoveTo 174 152 self)
			)
			(5
				((ScriptID 31 2) setCel: 7)
				(ego
					view: vWizardTable
					setLoop: 4
					setCel: 2
					illegalBits: 0
					posn: 186 121
					stopUpd:
				)
				(= cycles 5)
			)
			(6
				((ScriptID 31 1) setCel: -1 setCycle: Forward)
				(Print 131 2
					#at 5 40
					#width 80
					#mode teJustCenter
					#title { Erasmus_}
					#dispose
					#time 4
				)
				(= seconds 4)
				; Fenrus, our guest has arrived.
			)
			(7
				((ScriptID 31 1) setCel: 0 setCycle: 0 stopUpd:)
				(ego setCel: 2)
				((ScriptID 31 3) setCycle: BegLoop self)
			)
			(8
				((ScriptID 31 3) setCycle: EndLoop self)
			)
			(9
				((ScriptID 31 4) setCycle: EndLoop)
				(= cycles 10)
			)
			(10
				(Print 131 3
					#at 219 85
					#width 90
					#mode teJustCenter
					#title { Fenrus_}
					#dispose
					#time 3
				)
				(ego setCycle: EndLoop)
				(= seconds 2)
				; Cheese, please!
			)
			(11
				((ScriptID 31 3)
					cycleSpeed: 0
					cel: 0
					posn: 197 102
					setPri: 14
					setCycle: EndLoop self
				)
			)
			(12
				((ScriptID 31 3) cel: 0 posn: 161 115 setCycle: EndLoop)
				(= cycles 10)
			)
			(13
				(cheese posn: (+ (cheese x?) 7) (- (cheese y?) 3))
				(++ local0)
				(= cycles 1)
			)
			(14
				(if (< local0 5)
					(self changeState: 13)
				else
					(cheese dispose:)
					((ScriptID 31 4) setLoop: 5 setCel: 0)
					(self cue:)
				)
			)
			(15
				((ScriptID 31 4) setCel: 1)
				(Print 131 4
					#at 225 85
					#width 90
					#mode teJustCenter
					#title { Fenrus_}
					#dispose
					#time 3
				)
				(ego setCycle: CycleTo 3 -1)
				(= seconds 3)
				; Ahhhhhh!
			)
			(16
				((ScriptID 31 4) cycleSpeed: 1 setCel: 0)
				(= cycles 2)
			)
			(17
				(if (>= [egoStats MAGIC] 20)
					(= cycles 1)
				else
					(User canInput: TRUE)
					(ego setCel: 2)
					(client setScript: 0)
				)
			)
			(18
				(HandsOff)
				(= local4 1)
				(= enableErasmusTeaCountdown 0)
				(= local1 0)
				(if (not (Btst fWizKnowsEgoHasMagic))
					(Bset fWizKnowsEgoHasMagic)
					(ErasmusPrint 8 131 5)
					;Since you are a practitioner of the magical arts, you might be interested in a little game I have.
				else
					(= cycles 1)
				)
			)
			(19
				(while (& wizAskedSpells2 [spellMask register])
					(++ register)
				)
				(cond 
					(local3
						(Bset fErasmusAskedMaze)
						(ErasmusPrint 9 131 6)
						;I'll teach you the Dazzle spell if you win.  You do want to play, don't you?
						)
					((& wizAskedSpells [spellMask register])
						(ErasmusPrint 5 (Format @str 131 7 [spellAsked register])
							;Have you learned the %s spell yet?
						)
					)
					((< register 4)
						(|= wizAskedSpells [spellMask register])
						(ErasmusPrint 7 (Format @str 131 8 [spellAsked register])
							;Do you know how to cast the %s spell?
						)
					)
					((Btst fErasmusAskedMaze)
						(ErasmusPrint 7 131 9)
						;Nice to see you back.  Shall we play a game of Mage's Maze?
					)
					(else (= local3 1) (-- state)
						(ErasmusPrint 7 131 10)
						;Wonderful!  Then you can play a game of Mage's Maze with me.
					)
				)
			)
			(20
				(= local1 (= local2 1))
				(HandsOn)
				(User canControl: FALSE)
				(= seconds 14)
			)
			(21
				(HandsOff)
				(= local2 0)
				(cond 
					(local1
						(switch register
							(4
								(ego setCel: 2)
								(User canInput: TRUE)
								(client setScript: 0)
								(ErasmusPrint 8 131 11)
								;Oh, well.  It is a good game.  Good practice, too.  Maybe we can play the next time you visit.
							)
							(3
								(ErasmusPrint 8 131 12)
								;I believe I gave a learning scroll to the Hermit.  You should talk to him about it.
							)
							(else
								(ErasmusPrint 8 131 13)
								;Too bad.  You really can't play the game without it.  You can buy a learning scroll at Zara's magic shop in town.
							)
						)
					)
					((>= register 4) (client setScript: (ScriptID 132 5)))
					(else
						(|= wizAskedSpells2 [spellMask register])
						(++ register)
						(self changeState: 19)
					)
				)
			)
			(22
				(ego setCel: 2)
				(HandsOn)
				(User canControl: FALSE)
				(self dispose:)
			)
		)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((and (== (event type?) saidEvent) local2)
				(= seconds 0)
				(cond 
					((Said 'affirmative,please')
						(= local1 0)
						(self cue:)
					)
					((Said 'n')
						(self cue:)
					)
					(else
						(event claimed: TRUE)
						(ErasmusPrint 4 131 0)
						;A simple yes or no will suffice.
					)
				)
			)
		)
	)
)

(instance gameOver of Script
	(method (dispose)
		(super dispose:)
		(DisposeScript 131)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				((ScriptID 31 2) setCel: 7)
				(ego
					view: vWizardTable
					setLoop: 4
					setCel: 0
					illegalBits: 0
					posn: 186 121
					stopUpd:
				)
				((ScriptID 31 4) setLoop: 5 cel: 1)
				(= cycles 5)
			)
			(1
				((ScriptID 31 3)
					cycleSpeed: 0
					cel: 0
					posn: 160 102
					setPri: 14
					setCycle: EndLoop self
				)
			)
			(2
				((ScriptID 31 9)
					setLoop: 0
					setCel: 0
					setMotion: MoveTo 153 119
					startUpd:
				)
				((ScriptID 31 10)
					setLoop: 0
					setCel: 0
					setMotion: MoveTo 168 119 self
					startUpd:
				)
			)
			(3
				((ScriptID 31 10) stopUpd:)
				((ScriptID 31 9) stopUpd:)
				(ego setCel: 2)
				(= cycles 5)
			)
			(4
				((ScriptID 31 1) setCel: -1 setCycle: Forward)
				(ego setCel: 1)
				(Print
					131 14
					#at 5 40
					#width 80
					#mode teJustCenter
					#title { Erasmus_}
					#dispose
					#time 5
				)
				(= seconds 5)
				; I so enjoy playing the Mage's Maze.
			)
			(5
				(ego setCel: 2)
				((ScriptID 31 1) setCycle: BegLoop)
				(HandsOn)
				(User canControl: FALSE)
				(self dispose:)
			)
		)
	)
)
