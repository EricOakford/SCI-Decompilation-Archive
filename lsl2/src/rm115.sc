;;; Sierra Script 1.0 - (do not remove this comment)
(script# 115)
(include game.sh)
(use Main)
(use Intrface)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm115 0
)

(local
	local0
	aClerk
	aBell
	aOnklunk
)
(instance rm115 of Room
	(properties
		picture 115
		horizon 5
		south 15
	)
	
	(method (init)
		(Load VIEW 221)
		(Load VIEW 222)
		(super init:)
		((View new:)
			view: 221
			loop: 2
			cel: 3
			posn: 225 63
			addToPic:
		)
		((View new:)
			view: 221
			loop: 2
			cel: 4
			posn: 267 87
			addToPic:
		)
		((View new:)
			view: 221
			loop: 2
			cel: 5
			posn: 149 45
			addToPic:
		)
		((View new:)
			view: 221
			loop: 3
			cel: 4
			posn: 102 99
			addToPic:
		)
		((View new:)
			view: 221
			loop: 3
			cel: 4
			posn: 120 96
			addToPic:
		)
		((View new:)
			view: 221
			loop: 3
			cel: 3
			posn: 134 100
			addToPic:
		)
		((View new:)
			view: 221
			loop: 3
			cel: 3
			posn: 145 100
			addToPic:
		)
		((View new:)
			view: 221
			loop: 2
			cel: 0
			posn: 162 101
			addToPic:
		)
		((View new:)
			view: 221
			loop: 2
			cel: 0
			posn: 186 101
			addToPic:
		)
		((View new:)
			view: 221
			loop: 2
			cel: 0
			posn: 156 109
			addToPic:
		)
		((View new:)
			view: 221
			loop: 2
			cel: 0
			posn: 191 109
			addToPic:
		)
		((View new:)
			view: 221
			loop: 2
			cel: 0
			posn: 90 101
			addToPic:
		)
		((View new:)
			view: 221
			loop: 3
			cel: 0
			posn: 69 151
			setPri: 15
			ignoreActors:
			addToPic:
		)
		((View new:)
			view: 221
			loop: 3
			cel: 2
			posn: 23 103
			addToPic:
		)
		((View new:)
			view: 221
			loop: 3
			cel: 2
			posn: 0 113
			addToPic:
		)
		((View new:)
			view: 221
			loop: 3
			cel: 1
			posn: 31 79
			addToPic:
		)
		((View new:)
			view: 221
			loop: 3
			cel: 1
			posn: 41 86
			addToPic:
		)
		((View new:)
			view: 221
			loop: 2
			cel: 1
			posn: 172 77
			setPri: 6
			addToPic:
		)
		((View new:)
			view: 221
			loop: 2
			cel: 2
			posn: 89 104
			setPri: 7
			addToPic:
		)
		((View new:)
			view: 221
			loop: 3
			cel: 5
			posn: 209 135
			ignoreActors:
			addToPic:
		)
		((View new:)
			view: 221
			loop: 3
			cel: 7
			posn: 140 70
			addToPic:
		)
		((View new:)
			view: 221
			loop: 3
			cel: 6
			posn: 42 118
			setPri: 13
			ignoreActors:
			addToPic:
		)
		((View new:)
			view: 221
			loop: 0
			posn: 67 105
			setPri: 7
			ignoreActors:
			addToPic:
		)
		((= aBell (Prop new:))
			view: 221
			setLoop: 1
			posn: 63 62
			setCycle: Forward
			init:
			setScript: bellScript
		)
		(aBigClerk
			view: 222
			setLoop: 1
			posn: 273 1058
			setPri: 15
			setCycle: Forward
			init:
		)
		((= aClerk (Actor new:))
			view: 222
			setLoop: 0
			setPri: 14
			illegalBits: 0
			posn: 41 152
			stopUpd:
			ignoreActors:
			init:
		)
		(self setRegions: CITY setScript: rm115Script)
		(if (== currentStatus egoDOPPLEGANGER)
			(NormalEgo loopE)
			(= currentStatus egoDOPPLEGANGER)
			(HandsOff)
			(rm115Script changeState: 13)
		else
			(NormalEgo loopE)
			(Load SOUND 105)
			(theSound init:)
		)
		(ego posn: 64 115 init:)
	)
)

(instance rm115Script of Script
	(method (doit)
		(super doit:)
		(if (& (ego onControl:) cBLUE)
			(curRoom newRoom: 15)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(1
				(HandsOff)
				(Print (Format @str 115 15 introductoryPhrase))
				(aClerk setLoop: 2 setCycle: Forward)
				(= seconds 3)
			)
			(2
				(aClerk setLoop: 0 setCel: 0)
				(Print 115 16 #at -1 20 #font userFont #dispose)
				(Print 115 17 #at -1 152 #font smallFont)
				(cls)
				(= seconds 3)
			)
			(3
				(Print 115 18)
				(Print 115 19)
				(Print 115 20)
				(= seconds 3)
			)
			(4
				(if (> filthLevel 4)
					(Print 115 21)
				else
					(Print 115 22)
				)
				(Print 115 23)
				(Print 115 24 #at -1 20 #font userFont #dispose)
				(Print 115 25 #at -1 152 #font smallFont)
				(cls)
				(aClerk setLoop: 2 setCycle: Forward)
				(= seconds 3)
			)
			(5
				(aClerk setLoop: 0 setCel: 0)
				(Print 115 26)
				(Print 115 27 #at -1 20 #font userFont #dispose)
				(Print 115 28 #at -1 152 #font smallFont)
				(cls)
				(= seconds 3)
			)
			(6
				(Print 115 29)
				(Print 115 30)
				(Print 115 31 #at -1 20 #font userFont #dispose)
				(Print 115 32 #at -1 152 #font smallFont)
				(cls)
				(aClerk setLoop: 2 setCycle: Forward)
				(= seconds 3)
			)
			(7
				(aClerk setLoop: 0 setCel: 0)
				(Print 115 33 #at -1 20 #font userFont #dispose)
				(Print 115 34 #at -1 152 #font smallFont)
				(cls)
				(Print 115 35 #at -1 20 #font userFont #dispose)
				(Print 115 36 #at 20 130 #width 270 #font smallFont)
				(cls)
				(aClerk
					setLoop: 3
					setCel: 0
					cycleSpeed: 1
					setCycle: EndLoop self
				)
			)
			(8
				(aClerk setLoop: 0 cycleSpeed: 0 setCel: 0)
				((= aOnklunk (View new:))
					view: 222
					setLoop: 4
					setPri: 14
					posn: 51 132
					ignoreActors:
					stopUpd:
					init:
				)
				(= seconds 3)
			)
			(9
				(Print 115 37 #at -1 20 #font userFont #dispose)
				(Print 115 38 #at -1 152 #font smallFont)
				(cls)
				(= seconds 3)
			)
			(10
				(Print 115 39)
				(ego get: iOnklunk)
				(theGame changeScore: 7)
				(aOnklunk dispose:)
				(= seconds 3)
			)
			(11
				(Print 115 40 #at -1 20 #font userFont #dispose)
				(Print
					(Format @str 115 41 tritePhrase)
					#at -1 152
					#font smallFont
				)
				(cls)
				(ego setMotion: MoveTo 220 166 self)
			)
			(12
				(ego setMotion: MoveTo 222 182)
			)
			(13
				(ego setMotion: MoveTo 73 141 self)
				(= cycles 6)
			)
			(14
				(Print 115 42)
			)
			(15
				(ego setLoop: loopW)
				(Print 115 43 #draw)
				(Print 115 44)
				(Print 115 45)
				(theSound play:)
				(Print 115 46)
				(Print 115 47)
				(= seconds 3)
			)
			(16
				(Print 115 48)
				(= seconds 3)
			)
			(17
				(Print 115 49)
				(Print 115 50)
				(Print 115 51)
				(= seconds 3)
			)
			(18
				(Print 115 52)
				(Print 115 53)
				(= seconds 2)
			)
			(19
				(ego setLoop: -1 setMotion: MoveTo 220 166 self)
			)
			(20
				(ego loop: loopS setMotion: 0)
				(Print 115 54 #draw)
				(Print 115 55)
				(Print 115 56)
				(SetRegionTimer rgCITY 4 30)
				(curRoom newRoom: 21)
			)
		)
	)
	
	(method (handleEvent event)
		(if (or (!= (event type?) saidEvent) (event claimed?))
			(return)
		)
		(if (Said 'look>')
			(cond 
				((Said '/amp')
					(Print 115 0)
				)
				((Said '/buffet')
					(Print 115 1)
				)
				((Said '/carpet')
					(Print 115 2)
				)
				((Said '/door')
					(Print 115 3)
					(Print 115 4)
				)
				((Said '/carpet')
					(Print 115 5)
				)
				((Said '/agent,children,bimbo')
					(aBigClerk posn: 273 58 stopUpd:)
					(Timer setReal: aBigClerk 5)
					(HandsOff)
				)
				((Said '[/airport,building,rack,rack,new,music]')
					(Print 115 6)
					(Print 115 7 #at -1 152)
				)
			)
		)
		(if (Said 'apply,get,play/amp')
			(Print 115 8)
		)
		(if (Said 'buy')
			(if (ego has: iMillionDollarBill)
				(Print 115 9)
				(Print 115 10)
			else
				(Print 115 11)
				(Print 115 10)
			)
		)
		(if (Said 'open/door')
			(Print 115 3)
			(Print 115 4)
		)
		(if (Said 'call/bimbo,children,agent')
			(cond 
				((not (ego inRect: 61 139 76 157))
					(NotClose)
				)
				((not ((inventory at: iOnklunk) ownedBy: curRoomNum))
					(AlreadyTook)
				)
				((== gotHaircutInCity FALSE)
					(Print (Format @str 115 12 introductoryPhrase))
					(Print 115 13)
					(Print 115 14 #at -1 152)
				)
				(else
					(self changeState: 1)
				)
			)
		)
	)
)

(instance bellScript of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= cycles 5)
			)
			(1
				(aBell cycleSpeed: 1)
				(= cycles 7)
			)
			(2
				(aBell cycleSpeed: 2)
				(= cycles 9)
			)
			(3
				(aBell cycleSpeed: 3)
				(= cycles 11)
			)
			(4
				(aBell setCel: 2 stopUpd:)
			)
		)
	)
)

(instance aBigClerk of Prop
	(method (cue)
		(Print 115 57)
		(Print 115 58 #at 55 155 #width 210)
		(self posn: 273 1058)
		(HandsOn)
	)
)

(instance theSound of Sound
	(properties
		number 105
	)
)
