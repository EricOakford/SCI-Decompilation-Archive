;;; Sierra Script 1.0 - (do not remove this comment)
(script# 56)
(include game.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use Invent)
(use Actor)
(use System)

(public
	Room56 0
)

(local
	dwarf5
	dwarf4
	dwarf3
	[local3 5]
	dwarf2
	local9
	dwarf1
	local11
	sparkle1
	sparkle2
	sparkle3
	sparkle4
	local16
	lantern
)
(instance Room56 of Room
	(properties
		picture 56
		style (| BLACKOUT IRISOUT)
		west 55
	)
	
	(method (init)
		(self setRegions: DWARF_HOUSE)
		(Load VIEW 516)
		(Load VIEW 283)
		(Load VIEW 286)
		(Load VIEW 287)
		(Load VIEW 4)
		(Load VIEW 518)
		(super init:)
		(= isIndoors TRUE)
		(ego view: 4 posn: 65 139 init:)
		(if ((Inventory at: iDiamondPouch) ownedBy: ego)
			(Load VIEW 288)
			(Load VIEW 281)
			(Load VIEW 523)
			((View new:)
				view: 287
				loop: 3
				cel: 0
				posn: 118 68
				addToPic:
			)
			((View new:)
				view: 286
				loop: 4
				cel: 0
				posn: 70 70
				addToPic:
			)
			((View new:)
				view: 283
				loop: 1
				cel: 0
				posn: 211 99
				addToPic:
			)
			((View new:)
				view: 516
				loop: 0
				cel: 0
				posn: 234 118
				addToPic:
			)
			((= dwarf1 (Actor new:))
				isExtra: 1
				view: 287
				setLoop: 1
				illegalBits: 0
				posn: 118 52
				setCycle: Forward
				init:
			)
			((= dwarf2 (Actor new:))
				isExtra: TRUE
				view: 286
				illegalBits: 0
				posn: 70 54
				setLoop: 2
				setCycle: Forward
				init:
			)
			((= dwarf3 (Actor new:))
				view: 283
				setLoop: 0
				illegalBits: 0
				posn: 211 89
				setCycle: Forward
				init:
			)
			((= dwarf4 (Actor new:))
				view: 281
				loop: 4
				cel: 0
				illegalBits: 0
				posn: 233 129
				setPri: -1
				init:
				stopUpd:
			)
			((= dwarf5 (Actor new:))
				view: 281
				illegalBits: 0
				posn: (dwarf4 x?) (- (dwarf4 y?) 18)
				ignoreActors: 1
				init:
				setPri: (CoordPri (dwarf4 y?))
				setLoop: 0
				setCycle: Forward
			)
			(if (not (ego has: iLantern))
				((= lantern (View new:))
					view: 523
					loop: 1
					posn: (- (dwarf4 x?) 18) (- (dwarf4 y?) 1)
					init:
					stopUpd:
				)
			)
			(dwarf5 setScript: dwarvesMove)
		else
			(Load VIEW 280)
			((View new:)
				view: 283
				loop: 1
				cel: 0
				posn: 82 70
				addToPic:
			)
			((View new:)
				view: 286
				loop: 4
				cel: 0
				posn: 83 121
				addToPic:
			)
			((View new:)
				view: 287
				loop: 2
				cel: 0
				posn: 109 120
				addToPic:
			)
			((View new:)
				view: 516
				loop: 0
				cel: 0
				posn: 99 72
				addToPic:
			)
			((= dwarf4 (View new:))
				view: 280
				loop: 4
				cel: 0
				posn: 211 98
				setPri: -1
				init:
				stopUpd:
			)
			((= dwarf5 (Actor new:))
				view: 280
				illegalBits: 0
				posn: (dwarf4 x?) (- (dwarf4 y?) 18)
				setLoop: 1
				ignoreActors: TRUE
				setPri: (CoordPri (dwarf4 y?))
				setCycle: Forward
				init:
			)
			((= dwarf3 (Actor new:))
				view: 283
				setLoop: 0
				illegalBits: 0
				posn: 82 60
				setCycle: Forward
				init:
			)
			((= dwarf2 (Actor new:))
				isExtra: TRUE
				view: 286
				setLoop: 2
				illegalBits: 0
				posn: 89 105
				setPri: 8
				setCycle: Forward
				init:
			)
			((= dwarf1 (Actor new:))
				isExtra: TRUE
				view: 287
				setLoop: 0
				illegalBits: 0
				posn: 110 106
				setPri: 8
				setCycle: Forward
				init:
			)
			(dwarf5 setScript: dwarvesMove)
		)
		(if howFast
			((= sparkle1 (Prop new:))
				view: 518
				cel: 2
				posn: 86 154
				setPri: 15
				cycleSpeed: 1
				setLoop: 3
				init:
				ignoreActors: TRUE
				stopUpd:
			)
			((= sparkle2 (Prop new:))
				view: 518
				cel: 1
				posn: 261 117
				setPri: 15
				setLoop: 3
				init:
				ignoreActors: TRUE
				stopUpd:
			)
			((= sparkle3 (Prop new:))
				view: 518
				posn: 212 118
				cycleSpeed: 1
				setPri: 9
				setLoop: 3
				init:
				ignoreActors: TRUE
				stopUpd:
			)
			((= sparkle4 (Prop new:))
				view: 518
				cel: 3
				posn: 136 48
				setLoop: 3
				init:
				ignoreActors: TRUE
				stopUpd:
			)
			(sparkle1 setScript: sparkle)
		)
	)
	
	(method (doit)
		(super doit:)
		(if (& (ego onControl: FALSE) $0040) (curRoom newRoom: 55))
	)
	
	(method (handleEvent event)
		(return
			(cond 
				((event claimed?) (return TRUE))
				((== (event type?) saidEvent)
					(cond 
						(
							(or
								(Said 'deliver,return/pouch,diamond[/dwarf]')
								(Said 'deliver,return/dwarf/pouch,diamond')
							)
							(if
								(and
									(== ((Inventory at: iDiamondPouch) owner?) ego)
									(!= ((Inventory at: iLantern) owner?) ego)
								)
								(if (ego inRect: 198 122 222 134)
									(HandsOff)
									(ego setScript: handOff)
								else
									(Print 56 0)
								)
							else
								(Print 56 1)
							)
						)
						(
							(or
								(Said 'look[<around][/!*]')
								(Said 'look[<around]/room')
								(Said 'look[<around]/mine[<diamond]')
							)
							(Print 56 2)
						)
						((Said 'look>')
							(cond 
								((Said '/dwarf') (Print 56 3))
								((Said '/bucket') (Print 56 4))
							)
						)
						(
							(and
								(< (ego distanceTo: dwarf4) 30)
								(Said 'converse[/dwarf]')
							)
							(if (and (ego has: iDiamondPouch) (not (ego has: iLantern)))
								(answer1 cue:)
							else
								(Print 56 5)
							)
						)
						(
							(and
								(< (ego distanceTo: dwarf4) 20)
								(or (Said 'kiss/dwarf') (Said 'kiss[/!*]'))
							)
							(Print 56 6)
						)
						((Said 'get,rob/bucket') (Print 56 7))
						((Said 'rob/lantern') (Print 56 8))
					)
				)
			)
		)
	)
)

(instance dwarvesMove of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0 (= cycles 4))
			(1
				(switch (= temp0 (Random 1 6))
					(1
						(if (cast contains: dwarf2)
							(dwarf2 setCycle: EndLoop self)
						else
							(= seconds 2)
						)
					)
					(2
						(dwarf5 loop: 3 cycleSpeed: 2 setCycle: EndLoop self)
						(dwarf4 setLoop: 5)
					)
					(3
						(if (cast contains: dwarf1)
							(dwarf1 setLoop: 1 setCycle: EndLoop self)
						else
							(= seconds 2)
						)
					)
					(4
						(if (cast contains: dwarf2)
							(dwarf2 setCycle: EndLoop self)
						else
							(= seconds 2)
						)
					)
					(5
						(if (cast contains: dwarf1)
							(dwarf1 setLoop: 1 setCycle: EndLoop self)
						else
							(= seconds 2)
						)
					)
					(6
						(dwarf5 loop: 1 cycleSpeed: 0 setCycle: EndLoop self)
						(dwarf4 setLoop: 5)
					)
					(else  (self cue:))
				)
			)
			(2
				(dwarf5 setMotion: 0 stopUpd:)
				(if (cast contains: dwarf2)
					(dwarf2 setMotion: 0 stopUpd:)
				)
				(if (cast contains: dwarf1)
					(dwarf1 setMotion: 0 stopUpd:)
				)
				(self changeState: 1)
			)
		)
	)
)

(instance sparkle of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(switch (Random 1 4)
					(1
						(sparkle1 startUpd: setCycle: EndLoop self)
					)
					(2
						(sparkle2 startUpd: setCycle: EndLoop self)
					)
					(3
						(sparkle3 startUpd: setCycle: EndLoop self)
					)
					(4
						(sparkle4 startUpd: setCycle: EndLoop self)
					)
				)
			)
			(1
				(sparkle1 stopUpd:)
				(sparkle2 stopUpd:)
				(sparkle3 stopUpd:)
				(sparkle4 stopUpd:)
				(= state -1)
				(self cue:)
			)
		)
	)
)

(instance answer1 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (Print 56 9))
			(1 (Print 56 10))
			(2 (Print 56 11) (= state 1))
		)
	)
)

(instance handOff of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(dwarvesMove changeState: 3)
				(if howFast (sparkle changeState: 2))
				(if (cast contains: dwarf1) (dwarf1 dispose: delete:))
				(if (cast contains:) (dwarf2 dispose: delete:))
				(if (cast contains: sparkle4)
					(sparkle4 dispose: delete:)
				)
				(ego loop: 0)
				(dwarf5 view: 288 loop: 0 setCycle: EndLoop)
				(Print 56 12 #at -1 15 #width 290 #font smallFont #draw)
				(self cue:)
			)
			(1
				(dwarf5 loop: 1 cycleSpeed: 1 setCycle: EndLoop self)
			)
			(2
				(dwarf5 hide:)
				(lantern dispose:)
				(dwarf4
					view: 288
					loop: 2
					cel: 255
					cycleSpeed: 1
					setCycle: EndLoop self
				)
				(if (>= (ego y?) (lantern y?))
					(dwarf4 setPri: (- (ego priority?) 1))
				)
				(ego get: iLantern)
				(theGame changeScore: 3)
			)
			(3
				(if howFast
					((= dwarf1 (Actor new:))
						view: 287
						setLoop: 1
						illegalBits: 0
						posn: 118 52
						setCycle: Forward
						init:
					)
					((= dwarf2 (Actor new:))
						view: 286
						illegalBits: 0
						posn: 70 54
						setLoop: 2
						setCycle: Forward
						init:
					)
					((= sparkle4 (Prop new:))
						view: 518
						cel: 3
						posn: 136 48
						setLoop: 3
						init:
						ignoreActors: 1
						stopUpd:
					)
					(sparkle changeState: 0)
				)
				(dwarvesMove changeState: 1)
				(dwarf4 setPri: -1)
				(dwarf5 view: 281 loop: 3 cel: 0 cycleSpeed: 0 show:)
				(dwarf4
					view: 281
					loop: 5
					cel: 0
					stopUpd:
					ignoreActors: 0
					init:
				)
				(= seconds 3)
			)
			(4
				(HandsOn)
				(client setScript: 0)
			)
		)
	)
)
