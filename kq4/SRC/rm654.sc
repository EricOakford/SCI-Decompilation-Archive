;;; Sierra Script 1.0 - (do not remove this comment)
(script# 654)
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
	Room654 0
)
(synonyms
	(soup gruel food)
)

(local
	pot
	[aDwarf 49]
	egoSoup
	[dishes 7]
	i
	diamondPouch
	printObj
	[local61 2]
	local63
	local64
)
(instance dwarfMusic of Sound
	(properties
		number 31
		loop -1
	)
)

(instance Room654 of Room
	(properties
		picture 54
		style DISSOLVE
	)
	
	(method (init)
		(= isIndoors TRUE)
		(Load VIEW 630)
		(Load VIEW 677)
		(Load VIEW 678)
		(Load VIEW 500)
		(Load VIEW 76)
		(Load VIEW 533)
		(super init:)
		((View new:)
			view: 533
			loop: 1
			cel: 0
			posn: 295 106
			setPri: 9
			addToPic:
		)
		(= local63 0)
		(ego
			posn: 150 125
			view: 4
			xStep: 4
			yStep: 2
			init:
			setCycle: Walk
		)
		((= [dishes 0] (View new:))
			view: 677
			posn: 78 102
			loop: 3
			cel: 0
			init:
			stopUpd:
		)
		(= ateSoup FALSE)
		((= pot (Prop new:))
			view: 630
			posn: 83 124
			cycleSpeed: 0
			setCycle: Forward
			init:
		)
		(if (not howFast)
			(pot addToPic:)
		)
		(Print 654 0 #at -1 20 #time 5)
		(self setScript: dwarfsInToEat)
	)
	
	(method (handleEvent event)
		(super handleEvent: event)
		(return
			(cond 
				((event claimed?) (return TRUE))
				((== (event type?) saidEvent)
					(cond 
						((or (Said 'look[<around][/noword]') (Said 'look/room'))
							(Print 654 1)
						)
						((Said 'look>')
							(cond 
								((Said '/window')
									(Print 654 2)
								)
								((Said '/table')
									(if (not dwarfHouseState)
										(Print 654 3)
									)
									(if (and dwarfHouseState ateSoup)
										(Print 654 4)
									)
								)
								((Said '<under/table')
									(Print 654 5)
								)
								((Said '/stair')
									(Print 654 6)
								)
								((Said '/cabinet')
									(Print 654 7)
								)
								((Said '/wall')
									(Print 654 8)
								)
								((or (Said '/dirt') (Said '<down'))
									(Print 654 9)
								)
								((Said '/caldron')
									(Print 654 10)
								)
								((Said '/fire,fireplace')
									(Print 654 11)
								)
								((Said '/carpet')
									(Print 654 12)
								)
								((Said '/clock')
									(Print 654 13)
								)
								((Said '/bowl')
									(Print 654 14)
								)
							)
						)
					)
				)
			)
		)
	)
)

(instance dwarfsInToEat of Script
	(method (init who)
		(Load VIEW 290)
		(Load VIEW 291)
		(Load VIEW 292)
		(Load VIEW 293)
		(Load VIEW 294)
		(Load VIEW 295)
		(Load VIEW 296)
		(Load VIEW 297)
		(Load VIEW 298)
		(Load VIEW 299)
		(Load VIEW 275)
		(Load VIEW 276)
		(Load VIEW 277)
		(Load VIEW 278)
		(= [aDwarf 1] 296)
		(= [aDwarf 2] 278)
		(= [aDwarf 3] 0)
		(= [aDwarf 4] 218)
		(= [aDwarf 5] 138)
		(= [aDwarf 8] 295)
		(= [aDwarf 9] 277)
		(= [aDwarf 10] 1)
		(= [aDwarf 11] 295)
		(= [aDwarf 12] 160)
		(= [aDwarf 15] 294)
		(= [aDwarf 16] 276)
		(= [aDwarf 17] 1)
		(= [aDwarf 18] 262)
		(= [aDwarf 19] 160)
		(= [aDwarf 22] 293)
		(= [aDwarf 23] 275)
		(= [aDwarf 24] 0)
		(= [aDwarf 25] 194)
		(= [aDwarf 26] 138)
		(= [aDwarf 29] 292)
		(= [aDwarf 30] 299)
		(= [aDwarf 31] 1)
		(= [aDwarf 32] 228)
		(= [aDwarf 33] 160)
		(= [aDwarf 36] 291)
		(= [aDwarf 37] 298)
		(= [aDwarf 38] 1)
		(= [aDwarf 39] 190)
		(= [aDwarf 40] 160)
		(= [aDwarf 43] 290)
		(= [aDwarf 44] 297)
		(= [aDwarf 45] 0)
		(= [aDwarf 46] 240)
		(= [aDwarf 47] 137)
		(super init: who)
	)
	
	(method (doit)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(User canControl: FALSE canInput: FALSE)
				(= i 0)
				(= printObj
					(Print 654 15
						#font smallFont
						#at 200 10
						#width 110
						#dispose
					)
				)
				(dwarfMusic play:)
				(ego
					loop: 2
					setCycle: Walk
					setMotion: MoveTo 116 122 self
				)
			)
			(1
				(ego loop: 2 cel: 0 stopUpd:)
				(= [aDwarf i]
					((Actor new:)
						view: [aDwarf (+ i 1)]
						posn: 155 185
						illegalBits: 0
						setStep: 4 3
						cycleSpeed: 0
						init:
						yourself:
					)
				)
				([aDwarf i]
					setCycle: Walk
					setMotion: MoveTo 135 140 self
				)
			)
			(2
				([aDwarf i]
					setCycle: Walk
					setMotion: MoveTo 83 134 self
				)
			)
			(3
				([dishes 0] cel: (+ ([dishes 0] cel?) 1) forceUpd:)
				([aDwarf i]
					view: [aDwarf (+ i 2)]
					loop: 1
					setCycle: Forward
				)
				(= seconds 5)
			)
			(4
				([dishes 0] stopUpd:)
				(if (== ([dishes 0] cel?) 7)
					(cls)
					(= state 2)
					(= printObj
						(Print 654 16 #font smallFont #at -1 10 #dispose)
					)
					(= seconds 1)
				else
					([aDwarf i] loop: 2 cel: 0 setCycle: EndLoop self)
				)
			)
			(5
				([aDwarf i]
					loop: 0
					cel: 0
					setCycle: Walk
					setMotion: MoveTo 135 130 self
				)
			)
			(6
				(if (== [aDwarf (+ i 3)] 1)
					([aDwarf i]
						setLoop: 7
						setMotion: MoveTo 135 160 self
					)
				else
					(++ state)
					([aDwarf i]
						setMotion: MoveTo [aDwarf (+ i 4)] [aDwarf (+ i 5)] self
					)
				)
			)
			(7
				([aDwarf i]
					setLoop: -1
					setMotion: MoveTo [aDwarf (+ i 4)] [aDwarf (+ i 5)] self
				)
			)
			(8
				(= [aDwarf (+ i 6)]
					((View new:)
						view: [aDwarf (+ i 2)]
						loop: 6
						posn: [aDwarf (+ i 4)] (- [aDwarf (+ i 5)] 3)
						init:
						ignoreActors: 1
						stopUpd:
						yourself:
					)
				)
				([aDwarf i]
					loop: 3
					cel: 0
					setPri: (if (== [aDwarf (+ i 3)] 1) 13 else 12)
					posn:
						[aDwarf (+ i 4)]
						(if (== [aDwarf (+ i 3)] 1)
							(- [aDwarf (+ i 5)] 12)
						else
							(- [aDwarf (+ i 5)] 6)
						)
					setCycle: EndLoop self
				)
			)
			(9
				([aDwarf i] loop: 4 cel: 0 ignoreActors: 1 stopUpd:)
				(if (< (+= i 7) 48)
					(= state 0)
					(self cue:)
				else
					([dishes 0] dispose:)
				)
				(ego startUpd:)
				(if (> i 48)
					(cls)
					(= printObj
						(Print 654 17
							#font smallFont
							#at 7 5
							#width 290
							#draw
							#dispose
						)
					)
				)
				(= seconds 15)
			)
			(10
				(ego
					illegalBits: 0
					ignoreActors: TRUE
					setMotion: MoveTo 270 136 self
				)
			)
			(11
				(User canInput: TRUE)
				(cls)
				(= printObj
					(Print 654 18 #font smallFont #at -1 10 #dispose)
				)
				(ego view: 76 loop: 5)
				(dwarfMusic dispose:)
				(= ateSoup TRUE)
				(ego setScript: eatOut)
				(= seconds 60)
			)
			(12
				(= local63 1)
			)
			(13
				(cls)
				(= printObj
					(Print 654 19 #font smallFont #at -1 10 #dispose)
				)
				(= ateSoup 2)
				(User canInput: FALSE)
				(for ((= i 0)) (< i 7) ((++ i))
					([aDwarf (* i 7)] cel: 0)
				)
				(ego cel: 0)
				(= i 49)
				(= seconds 5)
			)
			(14
				(dwarfMusic play:)
				(cls)
				(= printObj
					(Print 654 20
						#font smallFont
						#at -1 10
						#dispose
					)
				)
				(self cue:)
			)
			(15
				(-= i 7)
				([aDwarf i] loop: 5 cel: 0 setCycle: EndLoop self)
				((= egoSoup (View new:))
					view: 500
					loop: 1
					cel: 2
					setPri: 12
					ignoreActors: TRUE
					posn: ([aDwarf i] x?) (if [aDwarf (+ i 3)] 136 else 133)
					addToPic:
				)
				(if (== i 28)
					((= diamondPouch (View new:))
						view: 500
						loop: 0
						cel: 0
						posn: (+ (egoSoup x?) 8) (- (egoSoup y?) 1)
						ignoreActors: TRUE
						setPri: 12
						init:
						stopUpd:
					)
					((inventory at: iDiamondPouch) moveTo: 54)
				)
			)
			(16
				([aDwarf i]
					view: [aDwarf (+ i 1)]
					setPri: -1
					cel: 0
					loop: 1
					posn: ([aDwarf (+ i 6)] x?) (+ ([aDwarf (+ i 6)] y?) 2)
					cycleSpeed: 0
					moveSpeed: 0
					setCycle: Walk
					setMotion: MoveTo 140 (+ ([aDwarf (+ i 6)] y?) 2) self
				)
				([aDwarf (+ i 6)] dispose:)
			)
			(17
				([aDwarf i]
					setPri: ([aDwarf i] priority?)
					setMotion: MoveTo 150 207 self
				)
			)
			(18
				([aDwarf i] dispose:)
				(if i
					(= state 14)
				)
				(self cue:)
			)
			(19
				(dwarfMusic loop: 1 changeState:)
				(cls)
				(Print 654 21 #font smallFont #at -1 20)
				(= egoSoup (View new:))
				(egoSoup
					view: 500
					loop: 1
					cel: 2
					posn: (ego x?) 132
					setPri: 12
					ignoreActors:
					init:
				)
				(egoSoup addToPic:)
				(RedrawCast)
				(ego
					ignoreActors: 0
					illegalBits: cWHITE
					posn: 270 135
					view: 4
					loop: 2
					setCycle: Walk
				)
				(self cue:)
			)
			(20
				(dwarfMusic dispose:)
				(User canControl: TRUE canInput: TRUE)
				(= inCutscene 0)
				(curRoom newRoom: 54)
			)
		)
	)
	
	(method (handleEvent event &tmp invIndex)
		(return
			(cond 
				((event claimed?) (return TRUE))
				((== (event type?) saidEvent)
					(cond 
						((Said 'look/dwarf,man')
							(Print 654 22)
						)
						((Said 'look/table')
							(Print 654 23)
						)
						((Said 'look/soup')
							(Print 654 24)
						)
						((Said 'converse[/dwarf,man]')
							(tacodoco1 cue:)
						)
						((or (Said 'get/soup') (Said 'get/bowl'))
							(cond 
								((== ateSoup 0)
									(Print 654 25)
								)
								((== ateSoup 1)
									(Print 654 26)
								)
								(else
									(Print 654 27)
								)
							)
						)
						((Said 'kill/dwarf,man')
							(Print 654 28)
						)
						((Said 'get/dwarf,man')
							(Print 654 29)
						)
						((or (Said 'kiss[/noword]') (Said 'kiss/dwarf,man'))
							(Print 654 30)
						)
						((Said 'deliver>')
							(if
								(and
									(= invIndex (inventory saidMe:))
									(ego has: (inventory indexOf: invIndex))
								)
								(Print 654 31)
							else
								(event claimed: FALSE)
							)
						)
						((or (Said 'eat/soup') (Said 'eat'))
							(switch ateSoup
								(0
									(Print 654 32)
									(= ateSoup TRUE)
								)
								(1 (Print 654 33))
								(2 (Print 654 27))
							)
						)
						((Said '(stand<up),(get<up),exit,/table,chair')	;EO: fixed said spec
							(Print 654 34)
						)
					)
				)
			)
		)
	)
)

(instance tacodoco1 of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Print 654 35)
			)
			(1
				(Print 654 36)
				(Print 654 37)
			)
			(2
				(Print 654 38)
			)
			(3
				(Print 654 39)
				(= state 2)
			)
		)
	)
)

(instance eatOut of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego
					posn: (ego x?) (- (ego y?) 8)
					setLoop: 0
					setCycle: EndLoop
				)
				(= seconds 7)
			)
			(1
				(cls)
				(ego setLoop: 1 stopUpd:)
				(= i (* (Random 0 6) 7))
				(if (< (Random 1 10) 3) (ego setCycle: EndLoop))
				([aDwarf i] setCycle: EndLoop self)
			)
			(2
				(if (> (ego cel?) 0) (ego setCycle: CycleTo 0 1))
				([aDwarf i] setCycle: CycleTo 0 1 self)
			)
			(3
				([aDwarf i] stopUpd:)
				(if (not local63)
					(= state 0)
					(self cue:)
				else
					(dwarfsInToEat cue:)
				)
			)
		)
	)
)
