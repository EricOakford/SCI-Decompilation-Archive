;;; Sierra Script 1.0 - (do not remove this comment)
(script# 92)
(include game.sh)
(use Main)
(use Intrface)
(use Sound)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	Room92 0
)

(local
	lolotte
	edgar
	heart
	henchman
	printObj
	stolenInventory
	node
	candle
	local8
	local9
	triedToEscape
)
(instance theMusic of Sound
	(properties
		number 44
	)
)

(instance Room92 of Room
	(properties
		picture 92
		style (| BLACKOUT IRISOUT)
	)
	
	(method (init)
		(if (<= gamePhase getPandoraBox)
			(Load VIEW 82)
			(Load VIEW 121)
			(Load VIEW 132)
			(Load VIEW 681)
			(Load VIEW 81)
			(Load VIEW 145)
			(Load VIEW 141)
		)
		(Load VIEW 634)
		(Load VIEW 512)
		(self setRegions: LOLOTTE)
		(super init:)
		(NotifyScript LOLOTTE 0)
		(= local8 0)
		((View new:)
			view: 634
			loop: 1
			cel: 0
			posn: 32 79
			setPri: 10
			init:
			startUpd:
		)
		((View new:)
			view: 634
			loop: 1
			cel: 1
			posn: 289 80
			setPri: 10
			init:
			addToPic:
		)
		((Prop new:)
			view: 512
			loop: 0
			posn: 289 68
			setPri: 10
			init:
			setCycle: Forward
		)
		((= candle (Prop new:))
			view: 512
			loop: 0
			posn: 34 67
			setPri: 10
			init:
			setCycle: Forward
		)
		(= isIndoors TRUE)
		(if (== prevRoomNum 80)
			(ego posn: 153 156 view: 4 xStep: 4 yStep: 2 init:)
		)
		(if (== prevRoomNum 91)
			(ego posn: 53 131 view: 4 xStep: 4 yStep: 2 init:)
		)
		(if (== prevRoomNum 93)
			(ego posn: 271 131 view: 4 xStep: 4 yStep: 2 init:)
		)
		(if (== prevRoomNum 86)
			((= lolotte (Prop new:))
				posn: 162 102
				view: 121
				cycleSpeed: 1
				setLoop: 0
				init:
				stopUpd:
				setScript: lipLooper
			)
			((= edgar (Prop new:))
				posn: 210 94
				view: 132
				loop: 2
				cel: 0
				init:
				stopUpd:
			)
			(User canControl: FALSE canInput: FALSE)
			(= inCutscene TRUE)
			(ego
				posn: 251 131
				view: 81
				setLoop: 1
				init:
				setCycle: Walk
			)
			(lotTalk2 start: 0)
			(self setScript: lotTalk2)
		)
		(if
			(or
				(== prevRoomNum 79)
				(== prevRoomNum 0)
				(== prevRoomNum 333)
			)
			(ego
				posn: 153 156
				view: 82
				xStep: 4
				yStep: 2
				setLoop: 0
				init:
				setCycle: Walk
			)
			((= lolotte (Prop new:))
				posn: 162 102
				view: 121
				cycleSpeed: 1
				setLoop: 0
				init:
				stopUpd:
				setScript: lipLooper
			)
			((= edgar (Prop new:))
				posn: 210 94
				view: 132
				cel: 0
				loop: 2
				init:
				stopUpd:
			)
			((= henchman (Actor new:))
				view: 141
				setStep: 4 2
				illegalBits: 0
				ignoreActors: TRUE
				posn: 350 129
				setCycle: Walk
				init:
				stopUpd:
			)
			(User canControl: FALSE canInput: FALSE)
			(= inCutscene TRUE)
			(curRoom setScript: walkIn)
		)
		(if (cast contains: lolotte)
			(theMusic play:)
		)
	)
	
	(method (doit)
		(if (& (ego onControl: 0) cBROWN)
			(cond 
				((and (== gamePhase endGame) (== lolotteAlive FALSE))
					(curRoom newRoom: 80)
				)
				((not triedToEscape)
					(Print 92 0)
					(= triedToEscape TRUE)
				)
			)
		else
			(= triedToEscape FALSE)
		)
		(if (and (& (ego onControl: 0) cMAGENTA) (!= (ego view?) 81))
			(curRoom newRoom: 91)
		)
		(if (and (& (ego onControl: 0) cRED) (!= (ego view?) 81))
			(curRoom newRoom: 93)
		)
		(super doit:)
	)
	
	(method (handleEvent event)
		(return
			(if (event claimed?)
				(return TRUE)
			else
				(if
					(and
						local8
						(== (event type?) keyDown)
						(== (event message?) ENTER)
					)
					((self script?) seconds: 1)
					(= local9 0)
					(= local8 0)
				)
				(if (== (event type?) saidEvent)
					(cond 
						((Said 'look>')
							(cond 
								((Said '/throne')
									(Print 92 1)
								)
								((Said '/stair')
									(Print 92 2)
								)
								((Said '<behind,under/tapestries,painting')
									(Print 92 3)
								)
								((Said '/tapestries,painting')
									(Print 92 4)
								)
								((Said '/door')
									(Print 92 5)
								)
								((Said '/wall')
									(Print 92 6)
								)
								((or (Said '/dirt') (Said '<down'))
									(Print 92 7)
								)
								((Said '/carpet')
									(Print 92 8)
								)
								(
									(or
										(Said '<around')
										(Said '/room[<throne]')
										(Said '/castle')
									)
									(Print 92 9)
								)
							)
						)
						((Said 'sit/throne')
							(Print 92 10)
						)
						((Said 'open/door')
							(if (and (== gamePhase endGame) (== lolotteAlive FALSE))
								(Print 92 11)
							else
								(Print 92 12)
								(Print 92 13)
							)
						)
						((Said 'unlatch/door')
							(if (and (== gamePhase endGame) (== lolotteAlive FALSE))
								(Print 92 14)
							else
								(Print 92 15)
							)
						)
					)
				)
			)
		)
	)
	
	(method (newRoom n)
		(if (or (== n 80) (== n 30))
			((ScriptID LOLOTTE) keep: 0)
			(= noWearCrown FALSE)
		)
		(super newRoom: n)
	)
)

(instance walkIn of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: MoveTo 153 137 self)
			)
			(1
				(ego cel: 1)
				(switch gamePhase
					(startingOut
						(= lolotteAlive TRUE)
						(curRoom setScript: lotTalk1)
					)
					(getTheUnicorn
						(curRoom setScript: lotTalk3)
					)
					(getTheHen
						(curRoom setScript: lotTalk4)
					)
					(getPandoraBox
						(curRoom setScript: lotTalk5)
					)
					(else
						;fail-safe error.
						(Print 92 16)
					)
				)
			)
		)
	)
)

(instance lotTalk1 of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= local9 3)
				(= printObj
					(Print 92 17
						#at 5 15
						#font smallFont
						#width 125
						#draw
						#dispose
					)
				)
				(= local8 1)
				(= seconds 7)
			)
			(1
				(= local9 0)
				(cls)
				(= printObj
					(Print 92 18 #at 5 15 #font smallFont #width 125 #dispose)
				)
				(= local8 1)
				(= seconds 7)
			)
			(2
				(= local9 9)
				(cls)
				(= printObj
					(Print 92 19 #at 5 15 #font smallFont #width 125 #dispose)
				)
				(= local8 1)
				(= seconds 10)
			)
			(3
				(cls)
				(= printObj
					(Print 92 20 #at 5 15 #font smallFont #width 125 #dispose)
				)
				(= local8 1)
				(= seconds 16)
			)
			(4
				(cls)
				(= printObj
					(Print 92 21 #at 5 15 #font smallFont #width 125 #dispose)
				)
				(= local8 1)
				(= seconds 8)
			)
			(5
				(cls)
				(= local9 8)
				(= printObj
					(Print 92 22 #font smallFont #at 5 15 #width 125 #dispose)
				)
				(= local8 1)
				(= seconds 9)
			)
			(6
				(cls)
				(= printObj
					(Print 92 23 #font smallFont #at 5 15 #width 125 #dispose)
				)
				(lolotte loop: 1 cel: 255 setCycle: EndLoop)
				(= local8 1)
				(= seconds 4)
			)
			(7 ; changed from duplicate (6
				(= local8 0)
				(ego
					view: 81
					setLoop: 0
					illegalBits: 0
					setMotion: MoveTo 325 132
				)
				(= seconds 5)
			)
			(8 ; (7 to (8 to make room for renaming duplicate (6 to (7
				(candle setCycle: Forward)
				(cls)
				(curRoom newRoom: 86)
			)
		)
	)
)

(instance lotTalk2 of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: MoveTo 153 137 self)
			)
			(1
				(ego view: 82 loop: 0 cel: 1)
				(= local9 4)
				(= printObj
					(Print 92 24 #at 5 15 #font smallFont #width 125 #dispose)
				)
				(= local8 1)
				(= seconds 7)
			)
			(2
				(= local9 7)
				(cls)
				(= printObj
					(Print 92 25 #at 5 15 #font smallFont #width 125 #dispose)
				)
				(= local8 1)
				(= seconds 10)
			)
			(3
				(cls)
				(= printObj
					(Print 92 26 #at 5 15 #font smallFont #width 125 #dispose)
				)
				(edgar view: 132 loop: 2 setCycle: EndLoop)
				(= local8 1)
				(= seconds 6)
			)
			(4
				(= local9 5)
				(cls)
				(edgar setCycle: BegLoop)
				(= printObj
					(Print 92 27 #at 5 15 #font smallFont #width 125 #dispose)
				)
				(= local8 1)
				(= seconds 6)
			)
			(5
				(= local9 5)
				(cls)
				(= printObj
					(Print 92 28 #at 5 15 #font smallFont #width 125 #dispose)
				)
				(= local8 1)
				(= seconds 7)
			)
			(6
				(= local9 3)
				(candle setCycle: Forward)
				(cls)
				(= printObj
					(Print 92 29 #font smallFont #at 5 15 #width 125 #dispose)
				)
				(lolotte loop: 2 cel: 255 setCycle: EndLoop)
				(= local8 1)
				(= seconds 6)
			)
			(7
				(= local8 0)
				(cls)
				(= gamePhase getTheUnicorn)
				(User canControl: TRUE canInput: TRUE)
				(= inCutscene FALSE)
				(curRoom newRoom: 30)
			)
		)
	)
)

(instance lotTalk3 of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= local9 6)
				(= printObj
					(Print 92 30 #at 5 15 #font smallFont #width 125 #dispose)
				)
				(= unicornState 99)
				(theGame changeScore: 7)
				(edgar view: 132 loop: 2 setCycle: EndLoop)
				(= local8 1)
				(= seconds 7)
			)
			(1
				(= local9 8)
				(cls)
				(= printObj
					(Print 92 31 #at 5 15 #font smallFont #width 125 #dispose)
				)
				(= local8 1)
				(= seconds 8)
			)
			(2
				(cls)
				(edgar setCycle: BegLoop)
				(= printObj
					(Print 92 32 #font smallFont #width 125 #dispose)
				)
				(= local8 1)
				(= seconds 7)
			)
			(3
				(= local9 12)
				(cls)
				(= printObj
					(Print 92 33 #at 5 15 #font smallFont #width 125 #dispose)
				)
				(= local8 1)
				(= seconds 12)
			)
			(4
				(= local9 6)
				(cls)
				(= printObj
					(Print 92 34 #at 5 15 #font smallFont #width 125 #dispose)
				)
				(= local8 1)
				(= seconds 6)
			)
			(5
				(candle setCycle: Forward)
				(cls)
				(= printObj
					(Print 92 35 #font smallFont #at 5 15 #width 125 #dispose)
				)
				(lolotte loop: 2 cel: 255 setCycle: EndLoop)
				(= local8 1)
				(= seconds 5)
			)
			(6
				(cls)
				(= gamePhase getTheHen)
				(User canControl: TRUE canInput: TRUE)
				(= inCutscene FALSE)
				(curRoom newRoom: 30)
			)
		)
	)
)

(instance lotTalk4 of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= local9 8)
				(= printObj
					(Print 92 36 #at 5 15 #font smallFont #width 125 #dispose)
				)
				(ego put: iMagicHen 84)
				(theGame changeScore: 7)
				(edgar view: 132 loop: 2 setCycle: EndLoop)
				(= local8 1)
				(= seconds 10)
			)
			(1
				(cls)
				(= printObj
					(Print 92 37 #at 5 15 #font smallFont #width 125 #dispose)
				)
				(= local8 1)
				(= seconds 4)
			)
			(2
				(edgar setCycle: BegLoop)
				(= local9 6)
				(cls)
				(= printObj
					(Print 92 38 #at 5 15 #font smallFont #width 125 #dispose)
				)
				(= local8 1)
				(= seconds 8)
			)
			(3
				(= local9 8)
				(cls)
				(= printObj
					(Print 92 39 #at 5 15 #font smallFont #width 125 #dispose)
				)
				(= local8 1)
				(= seconds 8)
			)
			(4
				(cls)
				(= printObj
					(Print 92 40 #at 5 15 #font smallFont #width 125 #dispose)
				)
				(= local8 1)
				(= seconds 4)
			)
			(5
				(= local9 6)
				(cls)
				(= printObj
					(Print 92 41 #at 5 15 #font smallFont #width 125 #dispose)
				)
				(= local8 1)
				(= seconds 10)
			)
			(6
				(= local9 7)
				(cls)
				(= printObj
					(Print 92 42 #at 5 15 #font smallFont #width 125 #dispose)
				)
				(= local8 1)
				(= seconds 10)
			)
			(7
				(cls)
				(= printObj
					(Print 92 43 #at 5 15 #font smallFont #width 125 #dispose)
				)
				(= local8 1)
				(= seconds 6)
			)
			(8
				(cls)
				(= printObj
					(Print 92 44 #at 5 15 #font smallFont #width 125 #dispose)
				)
				(= local8 1)
				(= seconds 8)
			)
			(9
				(= local9 8)
				(cls)
				(= printObj
					(Print 92 45 #at 5 15 #font smallFont #width 125 #dispose)
				)
				(= local8 1)
				(= seconds 10)
			)
			(10
				(cls)
				(= printObj
					(Print 92 46 #font smallFont #at 5 15 #width 125 #dispose)
				)
				(lolotte loop: 2 cel: 255 setCycle: EndLoop)
				(= local8 1)
				(= seconds 6)
			)
			(11
				(candle setCycle: Forward)
				(cls)
				(= gamePhase getPandoraBox)
				(User canControl: TRUE canInput: TRUE)
				(= inCutscene FALSE)
				(curRoom newRoom: 30)
			)
		)
	)
)

(instance lotTalk5 of Script
	(method (init who)
		(super init: who)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= local9 6)
				(= printObj
					(Print 92 47 #at 5 15 #font smallFont #width 125 #dispose)
				)
				(ego put: iPandorasBox 84)
				(theGame changeScore: 7)
				(edgar view: 132 loop: 2 setCycle: EndLoop)
				(= local8 1)
				(= seconds 10)
			)
			(1
				(cls)
				(= printObj
					(Print 92 48 #at 25 35 #font smallFont #width -1 #dispose)
				)
				(= local8 1)
				(= seconds 4)
			)
			(2
				(= local9 3)
				(cls)
				(edgar setCycle: BegLoop)
				(= printObj
					(Print 92 49 #at 5 15 #font smallFont #width 125 #dispose)
				)
				(= local8 1)
				(= seconds 8)
			)
			(3
				(= local9 4)
				(cls)
				(= printObj
					(Print 92 50 #at 5 15 #font smallFont #width 125 #dispose)
				)
				(= local8 1)
				(= seconds 8)
			)
			(4
				(= local9 6)
				(cls)
				(= printObj
					(Print 92 51 #at 5 15 #font smallFont #width 125 #dispose)
				)
				(lolotte loop: 2 cel: 255 setCycle: EndLoop)
				(= local8 1)
				(= seconds 10)
			)
			(5
				(cls)
				(= printObj
					(Print 92 52 #at 5 15 #font smallFont #width 125 #dispose)
				)
				(= local8 1)
				(= seconds 6)
			)
			(6 (= seconds 1))
			(7
				((= heart (Prop new:))
					view: 681
					posn: (edgar x?) (- (edgar y?) 35)
					cel: 255
					init:
					cycleSpeed: 1
					setCycle: EndLoop self
				)
				(lolotte loop: 3 cel: 255 setCycle: EndLoop)
				(= local8 0)
			)
			(8
				(heart dispose:)
				(lolotte loop: 3 cel: 3 setCycle: BegLoop)
				(= seconds 10)
			)
			(9
				(cls)
				(lolotte loop: 2 cel: 3 setCycle: BegLoop self)
			)
			(10
				(lolotte loop: 0)
				(= local9 6)
				(= printObj
					(Print 92 53 #at 5 15 #font smallFont #width 125 #dispose)
				)
				(= local8 1)
				(= seconds 10)
			)
			(11
				(cls)
				(lolotte loop: 0)
				(= local9 6)
				(= printObj
					(Print 92 54 #at 5 15 #font smallFont #width 125 #dispose)
				)
				(= local8 1)
				(= seconds 7)
			)
			(12
				(cls)
				(= local9 5)
				(getShit changeState: 0)
				(= printObj
					(Print 92 55 #at 5 15 #font smallFont #width 125 #dispose)
				)
				(= local8 0)
				(= seconds 10)
			)
			(13
				(cls)
				(= printObj
					(Print 92 56 #at 5 15 #font smallFont #width 125 #dispose)
				)
				(= node (inventory first:))
				(while node
					(if
						(and
							(= stolenInventory (NodeValue node))
							(== (stolenInventory owner?) ego)
						)
						(stolenInventory owner: 89)
					)
					(= node (inventory next: node))
				)
				(= local8 0)
				(= seconds 8)
			)
			(14
				(= seconds 2)
			)
			(15
				(= local9 6)
				(candle setCycle: Forward)
				(cls)
				(= printObj
					(Print 92 57 #at 5 15 #font smallFont #width 125 #dispose)
				)
				(= local9 3)
				(= local8 1)
				(= seconds 6)
			)
			(16
				(ego
					view: 81
					illegalBits: 0
					setLoop: 1
					setMotion: MoveTo -5 132 self
				)
			)
			(17
				(cls)
				(= gamePhase endGame)
				(ego hide: illegalBits: cWHITE)
				(curRoom newRoom: 81)
			)
		)
	)
)

(instance lipLooper of Script
	(method (doit)
		(if
			(and
				(> local9 0)
				(== (lolotte loop?) 0)
				(== (lolotte cycler?) 0)
			)
			(-- local9)
			(lolotte setCycle: EndLoop)
		)
	)
)

(instance getShit of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(henchman posn: 290 129 setMotion: MoveTo 153 129 self)
			)
			(1
				(henchman loop: 2)
				(henchman setMotion: MoveTo 320 130 self)
			)
			(2
				(henchman dispose:)
			)
		)
	)
)
