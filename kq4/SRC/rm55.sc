;;; Sierra Script 1.0 - (do not remove this comment)
(script# 55)
(include game.sh)
(use Main)
(use Intrface)
(use Jump)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	Room55 0
)
(synonyms
	(path ledge)
)

(local
	local0
	dwarf1
	local2
	dwarf2
	dwarf3
	local5
	dwarf4
	local7
	dwarf5
	local9
	sparkle1
	sparkle2
	sparkle3
	sparkle4
)
(instance Room55 of Room
	(properties
		picture 55
		style (| BLACKOUT IRISOUT)
		east 56
	)
	
	(method (init)
		(Load SCRIPT JUMP)
		(Load VIEW 4)
		(Load VIEW 44)
		(Load VIEW 41)
		(Load VIEW 518)
		(Load VIEW 18)
		(self setRegions: DWARF_MINE)
		(super init:)
		(= local0 0)
		(= isIndoors TRUE)
		(if (or (== prevRoomNum 28) (== prevRoomNum 0))
			(ego
				posn: 100 82
				view: 4
				loop: 0
				xStep: 4
				yStep: 2
				ignoreActors: 0
				illegalBits: cWHITE
				init:
			)
		else
			(ego
				posn: 290 149
				view: 4
				xStep: 4
				yStep: 2
				ignoreActors: 0
				illegalBits: cWHITE
				init:
			)
		)
		(if howFast
			((= sparkle1 (Prop new:))
				view: 518
				posn: 60 67
				setPri: 5
				cel: 2
				cycleSpeed: 1
				setLoop: 3
				init:
				stopUpd:
			)
			((= sparkle2 (Prop new:))
				view: 518
				posn: 95 120
				setPri: 10
				cel: 1
				setLoop: 3
				init:
				stopUpd:
			)
			((= sparkle3 (Prop new:))
				view: 518
				posn: 220 89
				cycleSpeed: 1
				setLoop: 3
				init:
				stopUpd:
			)
			((= sparkle4 (Prop new:))
				view: 518
				posn: 245 48
				cel: 3
				setLoop: 3
				init:
				stopUpd:
			)
			(sparkle1 setScript: sparkle)
		)
		(if (== dwarfHouseState houseCLEAN)
			(Load VIEW 284)
			(Load VIEW 285)
			(Load VIEW 282)
			((= dwarf1 (Actor new:))
				view: 282
				illegalBits: 0
				posn: 219 153
				setLoop: 0
				init:
				setScript: Watch
			)
			((= dwarf2 (Actor new:))
				isExtra: TRUE
				view: 284
				illegalBits: 0
				posn: 188 128
				setCycle: Forward
				init:
			)
			((= dwarf3 (Actor new:))
				isExtra: TRUE
				view: 285
				illegalBits: 0
				posn: 200 74
				setLoop: 1
				setCycle: Forward
				init:
			)
		else
			(Load VIEW 289)
			(Load VIEW 286)
			(Load VIEW 287)
			(ego illegalBits: cWHITE)
			((View new:)
				view: 287
				loop: 2
				cel: 0
				posn: 213 129
				addToPic:
			)
			((View new:)
				view: 286
				loop: 5
				cel: 0
				posn: 233 129
				addToPic:
			)
			((= dwarf4 (Actor new:))
				view: 287
				illegalBits: 0
				posn: 213 113
				setPri: 11
				setLoop: 0
				setCycle: Forward
				cycleSpeed: 1
				ignoreActors: 1
				init:
			)
			((= dwarf5 (Actor new:))
				view: 286
				illegalBits: 0
				posn: 233 113
				setPri: 11
				setLoop: 3
				setCycle: Forward
				cycleSpeed: 2
				ignoreActors: 1
				init:
			)
			((= dwarf1 (Actor new:))
				view: 289
				illegalBits: 0
				posn: 60 107
				setCycle: Walk
				init:
				ignoreActors: 1
			)
			(dwarf1 setScript: SendOut)
		)
	)
	
	(method (doit)
		(if (== (ego script?) 0) (ego setPri: -1))
		(if
			(and
				(& (ego onControl: 0) $0040)
				(!= (ego script?) Tripped)
			)
			(curRoom newRoom: 28)
		)
		(if
			(and
				(& (ego onControl: TRUE) $1000)
				(== local0 0)
				(== (ego script?) 0)
			)
			(ego setScript: WalkPath)
		)
		(if
			(and
				(or
					(& (ego onControl:) $0002)
					(& (ego onControl:) $1000)
				)
				(== local0 0)
				(== (ego script?) 0)
			)
			(ego setScript: WalkPath)
		)
		(if
			(and
				(& (ego onControl: TRUE) $0004)
				(== dwarfHouseState houseCLEAN)
				(!= (ego script?) Tripped)
			)
			(ego setScript: Tripped)
		)
		(if
		(and (& (ego onControl:) $0010) (== (ego script?) 0))
			(ego setPri: 12)
			(if
			(or (>= (ego heading?) 180) (== (ego heading?) 0))
				(= local0 0)
				(ego illegalBits: cWHITE)
			else
				(= local0 1)
				(ego illegalBits: -28672)
			)
			(if (> (ego x?) 121) (ego setPri: -1))
		)
		(super doit:)
	)
	
	(method (dispose)
		(DisposeScript JUMP)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(return
			(cond 
				((event claimed?) (return TRUE))
				(
				(and (== (event type?) saidEvent) (Said 'look>'))
					(cond 
						((Said '/door') (Print 55 0))
						((Said '<out[/(mine[<diamond]),(door[<mine])]') (Print 55 1))
						((Said '[<around][/!*]') (Print 55 2))
						(
							(or
								(Said '<around')
								(Said '[<around]/room,mine<[diamond]')
							)
							(Print 55 2)
						)
					)
				)
			)
		)
	)
	
	(method (newRoom n)
		(ego ignoreActors: FALSE)
		(if (== n 28)
			((ScriptID DWARF_MINE) keep: FALSE)
		)
		(super newRoom: n)
	)
)

(instance SendOut of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 3))
			(1
				(HandsOff)
				(Print 55 3)
				(dwarf1 setPri: -1 setMotion: MoveTo 92 89)
				(ego
					illegalBits: 0
					ignoreActors: 1
					setMotion: MoveTo 101 81 self
				)
			)
			(2
				(ego setMotion: MoveTo 74 81 self)
			)
			(3
				(ego illegalBits: cWHITE)
				(= inCutscene TRUE)
				(= isHandsOff FALSE)
				(curRoom newRoom: 28)
			)
		)
	)
)

(instance Tripped of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 40])
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					illegalBits: 0
					setPri: (if (> (ego y?) 115) 11 else 10)
					ignoreActors: 1
					view: 44
					setStep: 10 20
					setLoop: 2
					setCycle: Forward
					setMotion:
						JumpTo
						(if (< (ego x?) 100) 100 else (+ (ego x?) 20))
						149
						self
				)
			)
			(1
				(ego view: 18 setLoop: 1 cel: 0 setCycle: Forward)
				(= seconds 5)
			)
			(2
				(ego view: 41 setLoop: 0 cel: 255 setCycle: EndLoop self)
			)
			(3
				(= local0 1)
				(ego
					view: 4
					setStep: 4 2
					setCycle: Walk
					setLoop: -1
					setPri: -1
					illegalBits: -28672
					ignoreActors: 0
				)
				(= state 0)
				(client setScript: 0)
				(HandsOn)
			)
		)
	)
)

(instance WalkPath of Script
	(properties)
	
	(method (doit)
		(cond 
			(
				(and
					(or
						(& (ego onControl:) $0002)
						(& (ego onControl:) $1000)
					)
					(== local0 0)
				)
				(ego setPri: 13)
				(if (& (ego onControl: FALSE) $0008)
					(ego setScript: Tripped)
				)
			)
			((not (& (ego onControl: TRUE) $0010))
				(ego setPri: -1)
				(if (== (ego script?) WalkPath) (ego setScript: 0))
			)
		)
	)
)

(instance Watch of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (dwarf1 setCycle: EndLoop self))
			(1 (= seconds 5))
			(2
				(dwarf1 setCel: 0)
				(= seconds 10)
				(= state -1)
			)
		)
	)
)

(instance sparkle of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(switch (Random 1 4)
					(1 (sparkle1 setCycle: EndLoop self))
					(2
						(sparkle2 setCycle: EndLoop self)
					)
					(3
						(sparkle3 setCycle: EndLoop self)
					)
					(4
						(sparkle4 setCycle: EndLoop self)
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
