;;; Sierra Script 1.0 - (do not remove this comment)
(script# 54)
(include game.sh)
(use Main)
(use Intrface)
(use Avoider)
(use Motion)
(use Game)
(use Invent)
(use User)
(use Actor)
(use System)

(public
	Room54 0
)
(synonyms
	(sink counter)
	(cottage room)
)

(local
	soupPot
	closetDoor
	local2
	[bowl 8]
	i
	dwarfBouncer
	local13
	aDishes1
	aDishes2
	aDishes3
	aDishes4
	aDishes5
	aDishes6
	aDishes7
	aDishes8
	aDishes9
	aDishes10
	diamondPouch
	busy
)
;EO: Using the Avoider in this room crashes the game with an "Out of Heap Space" error!
; In fact, this room uses so much heap that saving, restoring, and inventory are disabled.
; For this reason, the Avoider is no longer loaded. It doesn't seem necessary anyway.

(instance Room54 of Room
	(properties
		picture 54
		style (| BLACKOUT IRISOUT)
	)
	
	(method (init)
		(Load VIEW 677)
		(Load VIEW 678)
		(Load VIEW 630)
		(Load VIEW 290)
		(Load VIEW 500)
		(Load VIEW 533)
		(self setRegions: DWARF_HOUSE)
		(if (== prevRoomNum 654)
			(curRoom style: IRISIN)
		)
		(super init:)
		((View new:)
			view: 533
			loop: 1
			cel: 0
			posn: 295 106
			setPri: 9
			addToPic:
		)
		((= dwarfBouncer (Actor new:))
			view: 290
			loop: 3
			illegalBits: 0
			ignoreActors: 0
			setCycle: Walk
			setScript: bounceBitchScript
			init:
			hide:
		)
		(= isIndoors TRUE)
		(NormalEgo)
		(ego
			view: 4
			observeControl: cGREEN
			setStep: 4 2
		)
		(cond 
			((== prevRoomNum 53)
				(if (== dwarfBouncesEgo TRUE)
					(dwarfBouncer posn: 253 86 setMotion: Follow ego show:)
					(ego posn: 205 104 loop: 1 init:)
					(bounceBitchScript changeState: 6)
				else
					(dwarfBouncer posn: 147 1194 show:)
					(ego posn: 222 87 init:)
					(if cleaningUpHouse
						(Load VIEW 61)
						(Load VIEW 62)
						(ego setScript: cleanKitchen)
					)
				)
			)
			((== prevRoomNum 654)
				(ego posn: 270 135 loop: 2 init:)
			)
			(else
				(dwarfBouncer posn: 147 1194 show:)
				(ego posn: 147 157 loop: 3 init:)
				(= cleaningUpHouse FALSE)
			)
		)
		(if (not dwarfTableClean)
			(for ((= i 7)) (>= i 0) ((-- i))
				((= [bowl i] (View new:))
					view: 500
					loop: 1
					cel: 2
					ignoreActors: TRUE
					setPri: 12
					posn:
						(switch i
							(0 189)
							(1 193)
							(2 218)
							(3 228)
							(4 240)
							(5 262)
							(6 270)
							(7 295)
						)
						(switch i
							(0 136)
							(1 133)
							(2 133)
							(3 136)
							(4 133)
							(5 136)
							(6 133)
							(7 136)
						)
					init:
					stopUpd:
				)
			)
		else
			((= [bowl 0] (View new:))
				view: 677
				posn: 78 102
				loop: 3
				cel: 0
				init:
				stopUpd:
			)
		)
		(if (== ((inventory at: iDiamondPouch) owner?) 54)
			((= diamondPouch (View new:))
				view: 500
				ignoreActors: TRUE
				loop: 0
				cel: 0
				setPri: 12
				posn: 236 134
				init:
				stopUpd:
			)
		)
		(if (not dwarfHouseState)
			((= aDishes1 (View new:))
				view: 677
				loop: 0
				cel: 0
				posn: 100 98
				init:
				stopUpd:
			)
			((= aDishes2 (View new:))
				view: 677
				loop: 0
				cel: 1
				posn: 110 98
				init:
				stopUpd:
			)
			((= aDishes3 (View new:))
				view: 677
				loop: 0
				cel: 2
				posn: 120 98
				init:
				stopUpd:
			)
			((= aDishes4 (View new:))
				view: 677
				loop: 1
				cel: 0
				posn: 130 98
				init:
				stopUpd:
			)
			((= aDishes5 (View new:))
				view: 677
				loop: 1
				cel: 1
				posn: 140 98
				init:
				stopUpd:
			)
			((= aDishes6 (View new:))
				view: 677
				loop: 1
				cel: 2
				posn: 150 98
				init:
				stopUpd:
			)
			((= aDishes7 (View new:))
				view: 678
				loop: 0
				cel: 0
				posn: 160 98
				init:
				stopUpd:
			)
			((= aDishes8 (View new:))
				view: 678
				loop: 0
				cel: 1
				posn: 170 98
				init:
				stopUpd:
			)
			((= aDishes9 (View new:))
				view: 678
				loop: 0
				cel: 2
				posn: 180 98
				init:
				stopUpd:
			)
			((= aDishes10 (View new:))
				view: 677
				loop: 0
				cel: 3
				posn: 107 96
				init:
				stopUpd:
			)
		)
		(= ateSoup 0)
		((= soupPot (Prop new:))
			view: 630
			posn: 83 124
			cycleSpeed: 0
			setCycle: Forward
			init:
		)
		(if (not howFast)
			(soupPot addToPic:)
		)
		((= closetDoor (Prop new:))
			view: 677
			setLoop: 2
			cel: 3
			setPri: 9
			posn: 56 137
			ignoreActors: TRUE
			init:
			stopUpd:
		)
	)
	
	(method (doit)
		(super doit:)
		(if (and (== busy 0) (== (ego script?) 0))
			(cond 
				((& (ego onControl:) cMAGENTA)
					(curRoom newRoom: 53)
				)
				((& (ego onControl:) cLBLUE)
					(ego setScript: useSteps)
					(useSteps changeState: 1)
				)
				((& (ego onControl:) cLRED)
					(ego setScript: useSteps)
					(useSteps changeState: 4)
				)
				((& (ego onControl:) cBROWN)
					(ego
						illegalBits: cWHITE
						ignoreActors: FALSE
						loop: 2
					)
					(curRoom newRoom: 22)
				)
			)
		)
	)
	
	(method (handleEvent event)
		(super handleEvent: event)
		(if (or (!= (event type?) saidEvent) (event claimed?))
			(return)
		)
		(cond 
			((or (Said 'is<how<time') (Said '[noword]/time'))
				(Print 54 0)
			)
			((Said 'open,(look<in)/cabinet')
				(Print 54 1)
			)
			((Said 'close/cabinet')
				(if (not dwarfHouseState)
					(Print 54 2)
				else
					(Print 54 3)
				)
			)
			((Said 'open/door,closet,pantry')
				(cond 
					((== (closetDoor cel?) 0)
						(Print 54 4)
					)
					((ego inRect: 40 137 70 150)
						(closetDoor setScript: doorOpen)
					)
					(else
						(Print 800 1)
					)
				)
			)
			((Said 'close/door,closet,pantry')
				(cond 
					((> (closetDoor cel?) 0)
						(Print 54 3)
					)
					((ego inRect: 0 137 70 145)
						(closetDoor setScript: doorClose)
					)
					(else
						(Print 800 1)
					)
				)
			)
			((Said 'sit')
				(Print 54 5)
			)
			((Said 'clean[/cottage]')
				(if (== dwarfBouncesEgo TRUE)
					(Print 54 5)
					(return)
				)
				(cond 
					((& (ego onControl:) cGREEN)
						(Print 54 6)
					)
					((== dwarfHouseState 0)
						(ego setScript: startClean)
					)
					((not dwarfTableClean)
						(ego setScript: cleanTable)
					)
					(else
						(Print 54 7)
					)
				)
			)
			((Said 'clean,scrub/table,dish')
				(cond 
					((and dwarfHouseState (not dwarfTableClean))
						(ego setScript: cleanTable)
					)
					((and dwarfHouseState dwarfTableClean)
						(Print 54 8)
					)
					(else
						(Print 54 2)
					)
				)
			)
			((Said 'clean,do,scrub,sweep,dust[/dish,dirt,furniture]')
				(if dwarfHouseState
					(Print 54 9)
				else
					(Print 54 2)
				)
			)
			((Said 'eat,drink,eat/soup')
				(if (not ateSoup)
					(Print 54 5)
				else
					(Print 54 10)
				)
			)
			((Said 'get>')
				(cond 
					((Said '/dish[<dirty]')
						(if (or (not dwarfHouseState) (not dwarfTableClean))
							(Print 54 11)
						else
							(Print 54 12)
						)
					)
					((Said '/broom')
						(if (& (ego onControl: origin) cGREEN)
							(Print 54 13)
						else
							(Print 54 14)
						)
					)
					((Said '/caldron')
						(Print 54 15)
					)
					((Said '/bowl')
						(Print 54 16)
					)
					((Said '/soup')
						(if ateSoup
							(Print 54 17)
						else
							(Print 54 18)
						)
					)
					((Said '/pouch,diamond')
						(cond 
							((ego has: iDiamondPouch)
								(event claimed: FALSE)
							)
							((== ((inventory at: iDiamondPouch) owner?) curRoomNum)
								(if (ego inRect: 200 140 276 164)
									(ego get: iDiamondPouch)
									(= gotItem TRUE)
									(theGame changeScore: 2)
									(diamondPouch dispose:)
								else
									(Print 800 1)
								)
							)
							(else
								(Print 54 19)
							)
						)
					)
				)
			)
			((Said 'look>')
				(cond 
					((and (== ateSoup TRUE) (Said '/soup'))
						(Print 54 20)
					)
					((and ((Inventory at: iDiamondPouch) ownedBy: 54) (Said '/pouch,diamond'))
						(Print 54 21)
					)
					((Said '/window')
						(if (ego inRect: 124 117 197 130)
							(Print 54 22)
						else
							(Print 800 1)
						)
					)
					((Said '/mantel,mantel')
						(if
							(and
								(cast contains: [bowl 0])
								(== ([bowl 0] view?) 677)
							)
							(Print 54 23)
						else
							(Print 54 24)
						)
					)
					((Said '<under/table')
						(Print 54 25)
					)
					((Said '/table')
						(if dwarfTableClean
							(Print 54 26)
							(if (== ((inventory at: iDiamondPouch) owner?) 54)
								(Print 54 27)
							)
						else
							(Print 54 28)
							(if (== ((inventory at: iDiamondPouch) owner?) curRoomNum)
								(Print 54 27)
							)
						)
					)
					((Said '/stair')
						(Print 54 29)
					)
					((Said '/cabinet')
						(Print 54 30)
					)
					((Said '<in/cabinet')
						(Print 54 1)
					)
					((Said '/wall')
						(Print 54 31)
					)
					((or (Said '/dirt') (Said '<down'))
						(Print 54 32)
					)
					((Said '/bowl')
						(if dwarfTableClean
							(Print 54 33)
						else
							(Print 54 34)
						)
					)
					((Said '/caldron,soup')
						(Print 54 35)
					)
					((Said '/fire,fireplace')
						(Print 54 36)
					)
					((Said '/carpet')
						(Print 54 37)
					)
					((Said '/sink')
						(if dwarfHouseState
							(Print 54 38)
						else
							(Print 54 39)
						)
					)
					((Said '/dish[<dirty]')
						(if (or (not dwarfHouseState) (not dwarfTableClean))
							(Print 54 40)
						else
							(Print 54 41)
						)
					)
					((Said '/clock[<cuckoo]')
						(Print 54 42)
					)
					((Said '/time')
						(Print 54 43)
					)
					((Said '<in/closet')
						(cond 
							((& (ego onControl: origin) cGREEN)
								(Print 54 44)
							)
							((> (closetDoor cel?) 0)
								(Print 54 45)
							)
							(else
								(Print 800 1)
							)
						)
					)
					((Said '/closet')
						(if (& (ego onControl: origin) cGREEN)
							(Print 54 46)
						else
							(Print 54 47)
						)
					)
					((Said '/broom')
						(if (& (ego onControl: origin) cGREEN)
							(Print 54 48)
						else
							(Print 54 49)
						)
					)
					((Said '[<around][/cottage,kitchen]')
						(Print 54 50)
						(if (== dwarfHouseState houseDIRTY)
							(Print 54 51)
						)
					)
				)
			)
		)
	)
	
	(method (newRoom n)
		(if (== n 22)
			((ScriptID 601) keep: FALSE)
		)
		(= noWearCrown FALSE)
		(super newRoom: n)
	)
)

(instance startClean of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= busy TRUE)
				(= isHandsOff FALSE)
				(= inCutscene TRUE)
				(= cleaningUpHouse TRUE)
				(ego
					illegalBits: 0
					;setAvoider: Avoider	;not enough heap for avoider
				)
				(NotifyScript DWARF_HOUSE 1)
				(ego setMotion: MoveTo 135 (ego y?) self)
			)
			(1
				(ego setMotion: MoveTo 135 130 self)
			)
			(2
				(ego setMotion: MoveTo 192 123 self)
			)
			(3
				(ego setLoop: 0 setMotion: MoveTo 232 88 self)
			)
			(4
				(ego setMotion: MoveTo 243 90 self)
			)
			(5
				(curRoom newRoom: 53)
			)
		)
	)
)

(instance cleanKitchen of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= busy TRUE)
				(ego
					illegalBits: 0
					;setAvoider: Avoider	;not enough heap for avoider
					loop: 1
					setMotion: MoveTo 194 123 self
				)
			)
			(1
				(cleanTable cue:)
			)
			(2
				(ego setLoop: -1 setMotion: MoveTo 103 120 self)
			)
			(3
				(aDishes1 dispose:)
				(ego setMotion: MoveTo 110 (ego y?) self)
			)
			(4
				(aDishes2 dispose:)
				(aDishes10 dispose:)
				(ego setMotion: MoveTo 120 (ego y?) self)
			)
			(5
				(aDishes3 dispose:)
				(ego setMotion: MoveTo 130 (ego y?) self)
			)
			(6
				(aDishes4 dispose:)
				(ego setMotion: MoveTo 140 (ego y?) self)
			)
			(7
				(aDishes5 dispose:)
				(ego setMotion: MoveTo 150 (ego y?) self)
			)
			(8
				(aDishes6 dispose:)
				(ego setMotion: MoveTo 160 (ego y?) self)
			)
			(9
				(aDishes7 dispose:)
				(ego setMotion: MoveTo 170 (ego y?) self)
			)
			(10
				(aDishes8 dispose:)
				(aDishes9 dispose:)
				(ego setMotion: MoveTo 180 (ego y?) self)
			)
			(11
				(ego setMotion: MoveTo 160 (ego y?) self)
			)
			(12
				(dishDust init:)
				(ego view: 62 loop: 3 setCycle: Forward)
				(= seconds 5)
			)
			(13
				(dishDust dispose:)
				(ego view: 4 setLoop: -1 setMotion: MoveTo 83 134 self)
			)
			(14
				((= [bowl 0] (View new:))
					view: 677
					posn: 78 102
					loop: 3
					cel: 0
					init:
				)
				(closetDoor setPri: 6 ignoreActors: TRUE setCycle: BegLoop)
				(ego setMotion: MoveTo 65 140 self)
			)
			(15
				(ego setMotion: MoveTo 45 140 self)
			)
			(16
				(ego view: 61 setMotion: MoveTo 150 145 self)
			)
			(17
				(sweepDust init:)
				(ego setLoop: 2 setMotion: MoveTo 170 122 self)
			)
			(18
				(ego setMotion: MoveTo 120 155 self)
			)
			(19
				(ego setMotion: MoveTo 45 140 self)
			)
			(20
				(sweepDust dispose:)
				(closetDoor setCycle: EndLoop setPri: 6)
				(ego
					view: 4
					setLoop: -1
					illegalBits: cWHITE
					setMotion: MoveTo 140 155 self
				)
			)
			(21
				(ego setMotion: MoveTo 150 125 self)
			)
			(22
				(= isHandsOff FALSE)
				(= busy FALSE)
				(= cleaningUpHouse FALSE)
				(NotifyScript DWARF_HOUSE 0)
				(= inCutscene TRUE)
				(= dwarfHouseState houseCLEAN)
				(ego loop: 2 forceUpd:)
				(closetDoor dispose:)
				(theGame changeScore: 5)
				(curRoom newRoom: 654)
			)
		)
	)
)

(instance dishDust of Prop
	(properties
		view 678
	)
	
	(method (init)
		(super init:)
		(self
			loop: 1
			setCycle: Forward
			ignoreActors:
			posn: (ego x?) (- (ego y?) 20)
			setPri: (- (ego priority?) 1)
		)
	)
	
	(method (doit)
		(super doit:)
		(self
			posn: (ego x?) (- (ego y?) 20)
			setPri: (- (ego priority?) 1)
		)
	)
)

(instance sweepDust of Prop
	(properties
		view 678
	)
	
	(method (init)
		(super init:)
		(self
			loop: 2
			setCycle: Forward
			ignoreActors: TRUE
			posn: (ego x?) (ego y?)
			setPri: (ego priority?)
		)
	)
	
	(method (doit)
		(super doit:)
		(self posn: (ego x?) (ego y?) setPri: (ego priority?))
	)
)

(instance cleanTable of Script
	(method (init who)
		(Load VIEW 63)
		(super init: who)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= busy TRUE)
				(if (not cleaningUpHouse)
					(= cleaningUpHouse TRUE)
					(NotifyScript DWARF_HOUSE 1)
				)
				(ego
					illegalBits: 0
					;setAvoider: Avoider	;not enough heap for avoider
					setCycle: Walk
				)
				(if (> (ego y?) 145)
					(ego setMotion: MoveTo 135 159 self)
				else
					(ego setMotion: MoveTo 135 150 self)
				)
			)
			(1
				(= i 7)
				(ego setMotion: MoveTo 290 (ego y?) self)
			)
			(2
				([bowl i] dispose:)
				(ego
					view: 63
					setLoop: (if (> (ego y?) 151) 3 else 2)
					setMotion: MoveTo (+ (* i 10) 190) (ego y?) self
				)
			)
			(3
				(if (> i 0)
					(-- i)
					(= state 1)
					(self cue:)
				else
					(ego
						view: 4
						setLoop: -1
						setMotion: MoveTo 135 (ego y?) self
					)
				)
			)
			(4
				(if (not dwarfHouseState)
					(cleanKitchen cue:)
				else
					(ego setMotion: MoveTo 150 120 self)
				)
			)
			(5
				(dishDust init:)
				(ego view: 62 loop: 3 setCycle: Forward)
				(= seconds 5)
			)
			(6
				(dishDust dispose:)
				(ego view: 4 setLoop: -1 setMotion: MoveTo 83 134 self)
			)
			(7
				((= [bowl 0] (View new:))
					view: 677
					posn: 78 102
					loop: 3
					cel: 0
					init:
				)
				(ego
					setAvoider: 0
					setCycle: Walk
					setMotion: MoveTo 123 134 self
				)
			)
			(8
				(= dwarfTableClean TRUE)
				(= busy FALSE)
				(= cleaningUpHouse FALSE)
				(NotifyScript DWARF_HOUSE 0)
				(NormalEgo)
				(ego observeControl: cGREEN)
				(HandsOn)
				(= noWearCrown 1)
				(client setScript: 0)
				(Print 54 52)
			)
		)
	)
)

(instance doorOpen of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: MoveTo 64 146 self)
			)
			(1
				(ego loop: 1)
				(closetDoor setCycle: BegLoop self)
			)
			(2
				(ego ignoreControl: cGREEN)
				(HandsOn)
			)
		)
	)
)

(instance doorClose of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: MoveTo 64 144 self)
			)
			(1
				(ego loop: 1)
				(closetDoor setCycle: EndLoop self)
			)
			(2
				(HandsOn)
				(ego observeControl: cGREEN)
			)
		)
	)
)

(instance useSteps of Script
	(method (changeState newState)
		(switch (= state newState)
			(1
				(HandsOff)
				(= busy TRUE)
				(ego ignoreControl: cWHITE setMotion: MoveTo 232 88 self)
			)
			(2
				(ego setMotion: MoveTo 243 90 self)
			)
			(3
				(HandsOn)
				(= noWearCrown TRUE)
				(ego observeControl: cWHITE setScript: 0)
				(curRoom newRoom: 53)
			)
			(4
				(HandsOff)
				(= busy TRUE)
				(ego ignoreControl: cWHITE setMotion: MoveTo 192 123 self)
			)
			(5
				(HandsOn)
				(= busy FALSE)
				(= noWearCrown TRUE)
				(ego observeControl: cWHITE setScript: 0)
			)
		)
	)
)

(instance bounceBitchScript of Script
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
						(> (ego y?) 155)
						(< (ego y?) 119)
					)
					(-- state)
					(= seconds 5)
				else
					(Print 54 53)
					(= dwarfBouncesEgo TRUE)
					(HandsOff)
					(dwarfBouncer
						posn: 140 221
						setMotion: MoveTo 140 160 self
						;setAvoider: Avoider	;not enough heap for avoider
					)
				)
			)
			(2
				(dwarfBouncer setMotion: MoveTo 110 160 self)
			)
			(3
				(dwarfBouncer loop: 0 stopUpd:)
				(= busy TRUE)
				(ego illegalBits: 0 setMotion: MoveTo 140 (ego y?) self)
			)
			(4
				(ego setMotion: MoveTo 140 162 self)
			)
			(5
				(= dwarfHouseState houseKICKEDOUT)
				(= dwarfBouncesEgo FALSE)
				(NormalEgo)
				(ego observeControl: cGREEN)
				(HandsOn)
				(User canInput: FALSE)
				(curRoom newRoom: 22)
			)
			(6
				(= seconds 0)
				(HandsOff)
				(= busy TRUE)
				(ego setMotion: MoveTo 192 123 self)
			)
			(7
				(ego setMotion: MoveTo 140 131 self)
				(= state 3)
			)
		)
	)
)
