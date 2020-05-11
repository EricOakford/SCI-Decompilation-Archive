;;; Sierra Script 1.0 - (do not remove this comment)
(script# 53)
(include game.sh)
(use Main)
(use Intrface)
(use Avoider)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	Room53 0
)
(synonyms
	(room bedroom cottage)
)

(local
	messyBed1
	messyBed2
	messyBed3
	messyBed4
	messyBed5
	messyBed6
	messyBed7
	messyClothing1
	messyClothing2
	messyClothing3
	messyClothing4
	messyClothing5
	messyClothing6
	messyClothing7
	messyClothing8
	messyClothing9
	messyClothing10
	messyClothing11
	messyClothing12
	messyClothing13
	messyClothing14
	messyClothing15
	messyClothing16
	messyClothing17
	[local24 2]
	dwarf
	local27
)
(instance Room53 of Room
	(properties
		picture 53
		style (| BLACKOUT IRISOUT)
	)
	
	(method (init)
		(Load VIEW 676)
		(Load VIEW 290)
		(self setRegions: DWARF_HOUSE)
		(= noWearCrown TRUE)
		(super init:)
		((View new:)
			view: 676
			loop: 3
			cel: 0
			posn: 230 76
			setPri: 4
			addToPic:
		)
		((View new:)
			view: 676
			loop: 3
			cel: 1
			posn: 42 87
			setPri: 10
			addToPic:
		)
		(if (or (== prevRoomNum 54) (== prevRoomNum 0))
			(ego
				posn: 72 131
				view: 4
				setStep: 4 2
				illegalBits: -32768
				init:
			)
		)
		(if cleaningUpHouse (self setScript: cleanBedroom))
		(if (not dwarfHouseState)
			(Load VIEW 675)
			(Load VIEW 676)
			(Load VIEW 63)
			(Load VIEW 678)
			(Load SOUND 62)
			((= dwarf (Actor new:))
				view: 290
				illegalBits: 0
				loop: 0
				setCycle: Walk
				posn: 72 1131
				illegalBits: 0
				ignoreActors: TRUE
				setCycle: Walk
				setScript: chaseEgo
				init:
			)
			((= messyClothing1 (View new:))
				view: 676
				loop: 0
				cel: 0
				posn: 102 88
				ignoreActors: TRUE
				stopUpd:
				init:
			)
			((= messyClothing2 (View new:))
				view: 676
				loop: 0
				cel: 1
				posn: 261 124
				ignoreActors: TRUE
				stopUpd:
				init:
			)
			((= messyClothing3 (View new:))
				view: 676
				loop: 0
				cel: 2
				posn: 246 135
				ignoreActors: TRUE
				stopUpd:
				init:
			)
			((= messyClothing4 (View new:))
				view: 676
				loop: 0
				cel: 3
				posn: 101 111
				ignoreActors: TRUE
				stopUpd:
				init:
			)
			((= messyClothing5 (View new:))
				view: 676
				loop: 0
				cel: 4
				posn: 198 106
				ignoreActors: TRUE
				stopUpd:
				init:
			)
			((= messyClothing6 (View new:))
				view: 676
				loop: 0
				cel: 5
				posn: 97 149
				ignoreActors: TRUE
				stopUpd:
				init:
			)
			((= messyClothing7 (View new:))
				view: 676
				loop: 0
				cel: 6
				posn: 174 146
				ignoreActors: TRUE
				setPri: 13
				stopUpd:
				init:
			)
			((= messyClothing8 (View new:))
				view: 676
				loop: 1
				cel: 0
				posn: 275 149
				ignoreActors: TRUE
				setPri: 11
				stopUpd:
				init:
			)
			((= messyClothing9 (View new:))
				view: 676
				loop: 1
				cel: 1
				posn: 255 146
				ignoreActors: TRUE
				setPri: 11
				stopUpd:
				init:
			)
			((= messyClothing10 (View new:))
				view: 676
				loop: 1
				cel: 2
				posn: 201 146
				ignoreActors: TRUE
				setPri: 13
				stopUpd:
				init:
			)
			((= messyClothing11 (View new:))
				view: 676
				loop: 1
				cel: 3
				posn: 195 83
				ignoreActors: TRUE
				stopUpd:
				init:
			)
			((= messyClothing12 (View new:))
				view: 676
				loop: 2
				cel: 0
				posn: 182 125
				ignoreActors: TRUE
				stopUpd:
				init:
			)
			((= messyClothing13 (View new:))
				view: 676
				loop: 2
				cel: 1
				posn: 17 146
				ignoreActors: TRUE
				stopUpd:
				init:
			)
			((= messyClothing14 (View new:))
				view: 676
				loop: 2
				cel: 2
				posn: 24 136
				ignoreActors: TRUE
				stopUpd:
				init:
			)
			((= messyClothing15 (View new:))
				view: 676
				loop: 2
				cel: 3
				posn: 292 139
				ignoreActors: TRUE
				stopUpd:
				init:
			)
			((= messyClothing16 (View new:))
				view: 676
				loop: 2
				cel: 4
				posn: 264 133
				ignoreActors: TRUE
				stopUpd:
				init:
			)
			((= messyClothing17 (View new:))
				view: 676
				loop: 2
				cel: 5
				posn: 270 135
				ignoreActors: TRUE
				stopUpd:
				init:
			)
			((= messyBed1 (View new:))
				view: 675
				loop: 0
				cel: 0
				posn: 128 92
				ignoreActors: TRUE
				stopUpd:
				init:
			)
			((= messyBed2 (View new:))
				view: 675
				loop: 0
				cel: 1
				posn: 178 91
				ignoreActors: TRUE
				stopUpd:
				init:
			)
			((= messyBed3 (View new:))
				view: 675
				loop: 0
				cel: 2
				posn: 130 117
				ignoreActors: TRUE
				stopUpd:
				init:
			)
			((= messyBed4 (View new:))
				view: 675
				loop: 0
				cel: 3
				posn: 177 120
				ignoreActors: TRUE
				stopUpd:
				init:
			)
			((= messyBed5 (View new:))
				view: 675
				loop: 0
				cel: 4
				posn: 237 120
				ignoreActors: TRUE
				stopUpd:
				init:
			)
			((= messyBed6 (View new:))
				view: 675
				loop: 0
				cel: 5
				posn: 132 156
				ignoreActors: TRUE
				setPri: 13
				stopUpd:
				init:
			)
			((= messyBed7 (View new:))
				view: 675
				loop: 0
				cel: 6
				posn: 173 156
				ignoreActors: TRUE
				setPri: 13
				stopUpd:
				init:
			)
		)
	)
	
	(method (doit)
		(super doit:)
		(if (& (ego onControl: FALSE) $0040) (curRoom newRoom: 54))
	)
	
	(method (handleEvent event)
		(super handleEvent: event)
		(if
		(or (!= (event type?) saidEvent) (event claimed?))
			(return)
		)
		(cond 
			((or (Said 'clean/room') (Said 'clean[/!*]'))
				(if (!= dwarfHouseState TRUE)
					(self setScript: cleanBedroom)
				else
					(Print 53 0)
				)
			)
			((Said 'look>')
				(cond 
					((Said '/bed') (Print 53 1))
					((Said '/stair') (Print 53 2))
					((Said '<under/bed') (Print 53 3))
					((Said '/window')
						(if (ego inRect: 211 125 271 146)
							(Print 53 4)
						else
							(Print 800 1)
						)
					)
					((Said '<in/chest,dresser,drawer') (Print 53 5))
					((Said '/chest,dresser') (Print 53 6))
					((Said '/mirror')
						(if (ego inRect: 217 142 295 161)
							(Print 53 7)
						else
							(NotClose)
						)
					)
					((Said '/shelf') (Print 53 8))
					((Said '/wall') (Print 53 9))
					((or (Said '/dirt') (Said '<down')) (Print 53 10))
					((Said '/carpet,carpet') (Print 53 11))
					((Said 'look[<around][/room]')
						(Print
							(Format @str 53 12
								(if (not dwarfHouseState)
									{My goodness, these dwarfs are sloppy!}
								else
									{_}
								)
							)
						)
					)
				)
			)
			(
				(or
					(Said 'create/bed')
					(Said 'dust,sweep[/room,dirt,furniture]')
				)
				(if dwarfHouseState (Print 53 0) else (Print 53 13))
			)
			((Said 'close,close/drawer,dresser,chest') (Print 53 14))
			((Said 'open/chest,dresser,drawer') (Print 53 5))
			(
				(or
					(Said 'get<in,on/bed')
					(Said 'sleep,lay[<down,on,in]')
				)
				(Print 53 15)
			)
		)
	)
)

(instance cleanBedroom of Script
	(properties)
	
	(method (init param1)
		(super init: param1)
		(ego
			illegalBits: 0
			ignoreActors: TRUE
			setCycle: Walk
			setLoop: -1
		)
	)
	
	(method (changeState newState)
		(super changeState: newState)
		(switch (= state newState)
			(0
				;cleaning time!
				(HandsOff)
				(= inCutscene TRUE)
				(if (not cleaningUpHouse)
					(NotifyScript DWARF_HOUSE 1)
					(= cleaningUpHouse TRUE)
				)
				(ego setCycle: Walk setMotion: MoveTo 137 131 self)
			)
			(1
				(ego setMotion: MoveTo 130 145 self)
			)
			(2
				(ego view: 63 setCycle: Forward)
				(= seconds 2)
			)
			(3
				(messyBed6 dispose:)
				(messyClothing6 dispose:)
				(ego view: 4 setMotion: MoveTo 185 145 self)
			)
			(4
				(ego view: 63 loop: 2 setCycle: Forward)
				(= seconds 2)
			)
			(5
				(messyBed7 dispose:)
				(messyClothing7 dispose:)
				(messyClothing10 dispose:)
				(ego view: 4 setMotion: MoveTo 231 145 self)
			)
			(6 (self cue:))
			(7
				(ego view: 4 setMotion: MoveTo 237 157 self)
			)
			(8
				(ego view: 63 loop: 0 setCycle: Forward)
				(= seconds 2)
			)
			(9
				(messyClothing17 dispose:)
				(messyClothing16 dispose:)
				(messyClothing15 dispose:)
				(messyClothing9 dispose:)
				(messyClothing8 dispose:)
				(ego view: 4 setMotion: MoveTo 232 130 self)
			)
			(10
				(ego view: 63 loop: 0 setCycle: Forward)
				(= seconds 2)
			)
			(11
				(messyBed5 dispose:)
				(messyClothing2 dispose:)
				(messyClothing3 dispose:)
				(ego view: 4 setMotion: MoveTo 187 126 self)
			)
			(12
				(ego view: 63 loop: 3 setCycle: Forward)
				(= seconds 2)
			)
			(13
				(messyBed2 dispose:)
				(messyBed4 dispose:)
				(messyClothing5 dispose:)
				(messyClothing11 dispose:)
				(messyClothing12 dispose:)
				(ego view: 4 setMotion: MoveTo 115 126 self)
			)
			(14
				(ego view: 63 loop: 3 setCycle: Forward)
				(= seconds 2)
			)
			(15
				(messyBed1 dispose:)
				(messyBed3 dispose:)
				(messyClothing1 dispose:)
				(messyClothing4 dispose:)
				(ego view: 4 setMotion: MoveTo 41 151 self)
			)
			(16
				(ego view: 63 loop: 1 setCycle: Forward)
				(= seconds 2)
			)
			(17
				(messyClothing13 dispose:)
				(messyClothing14 dispose:)
				(ego view: 4 setLoop: -1 setMotion: MoveTo 91 133 self)
			)
			(18
				(= isHandsOff 0)
				(ego setMotion: MoveTo 71 133 self)
			)
			(19 (curRoom newRoom: 54))
		)
	)
)

(instance chaseEgo of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 60))
			(1
				(if
					(or
						cleaningUpHouse
						dwarfBouncesEgo
						isHandsOff
						dwarfHouseState
						(not (ego inRect: 95 127 241 145))
					)
					(-- state)
					(= seconds 5)
				else
					(dwarf
						setAvoider: Avoider
						posn: 55 130
						setMotion: MoveTo 78 131 self
					)
					(HandsOff)
					(= isHandsOff 0)
					(= inCutscene TRUE)
					(= dwarfBouncesEgo TRUE)
				)
			)
			(2
				(Print 53 16)
				(ego
					illegalBits: 0
					setAvoider: Avoider
					setMotion: MoveTo 75 135 self
				)
			)
			(3
				(dwarf setMotion: Follow ego 20)
				(ego setMotion: MoveTo 10 135 self)
			)
		)
	)
)
