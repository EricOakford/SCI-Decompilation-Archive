;;; Sierra Script 1.0 - (do not remove this comment)
(script# 76)
(include game.sh)
(use Main)
(use Intrface)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm76 0
)

(local
	aComputer
	aComputerCarrier
	aEgoProgramming
	aComputerCode
	aDrummer
	aKalalau
	aChief
	aChiefHead
)
(instance rm76 of Room
	(properties
		picture 76
		horizon 37
		north 79
		east 77
	)
	
	(method (init)
		(super init:)
		(NormalEgo)
		(self setScript: rm76Script)
		(if global111
			(= endGameState endMEETTRIBE)
		)
		(if (!= endGameState endMEETTRIBE)
			(self setRegions: ISLAND)
		else
			(= currentStatus egoMEETTRIBE)
			(rm76Script changeState: 1)
			(Load VIEW 194)
			(Load VIEW 195)
			(Load VIEW 704)
			(Load VIEW 707)
			(Load VIEW 710)
			(Load VIEW 714)
			(Load SOUND 114)
			(theSound init:)
			((View new:)
				view: 707
				ignoreActors:
				loop: 6
				cel: 3
				posn: 208 93
				setPri: 5
				addToPic:
			)
			((View new:)
				view: 707
				ignoreActors:
				loop: 6
				cel: 2
				posn: 190 96
				addToPic:
			)
			((View new:)
				view: 707
				ignoreActors:
				loop: 6
				cel: 1
				posn: 224 96
				addToPic:
			)
			((View new:)
				view: 714
				ignoreActors:
				loop: 2
				cel: 1
				posn: 144 88
				addToPic:
			)
			((View new:)
				view: 714
				ignoreActors:
				loop: 3
				cel: 0
				posn: 272 90
				addToPic:
			)
			((View new:)
				view: 714
				ignoreActors:
				loop: 3
				cel: 1
				posn: 229 86
				addToPic:
			)
			((View new:)
				view: 714
				ignoreActors:
				loop: 2
				cel: 0
				posn: 190 82
				addToPic:
			)
			((= aEgoProgramming (Prop new:))
				view: 195
				ignoreActors:
				setLoop: 0
				setPri: 15
				posn: 275 1040
				setCycle: Forward
				init:
			)
			((= aComputerCode (Prop new:))
				view: 195
				ignoreActors:
				setLoop: 1
				setPri: 15
				posn: 275 1040
				setCycle: Forward
				init:
			)
			((= aDrummer (Prop new:))
				view: 714
				ignoreActors:
				setLoop: 0
				posn: 92 160
				stopUpd:
				init:
			)
			((= aComputer (Prop new:))
				view: 707
				ignoreActors:
				setLoop: 7
				posn: 177 98
				stopUpd:
				init:
			)
			((= aChief (Actor new:))
				view: 710
				ignoreActors:
				illegalBits: 0
				setPri: 9
				setCycle: Walk
				cycleSpeed: 1
				moveSpeed: 1
				posn: -21 86
				init:
			)
			((= aChiefHead (Prop new:))
				view: 710
				ignoreActors:
				setLoop: 4
				setPri: 10
				posn: 999 999
				setCycle: Forward
				init:
			)
			((= aComputerCarrier (Actor new:))
				view: 707
				ignoreActors:
				illegalBits: 0
				setLoop: 5
				setCycle: Walk
				posn: 233 95
				stopUpd:
				init:
			)
			((= aKalalau (Actor new:))
				view: 704
				loop: 1
				ignoreActors:
				illegalBits: 0
				posn: 300 131
				setCycle: Walk
				init:
			)
		)
		(cond 
			((== prevRoomNum 79)
				(ego posn: 197 40)
			)
			((== prevRoomNum 77)
				(ego posn: 306 125)
			)
			(else
				(ego posn: 306 125)
			)
		)
		(ego init:)
	)
)

(instance rm76Script of Script
	(method (doit)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(1
				(= cycles 3)
			)
			(2
				(HandsOff)
				(ego setMotion: MoveTo 145 126 self)
				(aKalalau setMotion: MoveTo 138 132)
				(= cycles 10)
			)
			(3
				(Print 76 4 #draw)
			)
			(4
				(Print 76 5)
				(aKalalau setCel: 255 stopUpd:)
				(ego stopUpd:)
				(aChief setMotion: MoveTo 81 91 self)
			)
			(5
				(Print 76 6)
				(aChief stopUpd:)
				(aChiefHead
					posn: (+ (aChief x?) -1) (+ (aChief y?) -26)
				)
				(= seconds 3)
			)
			(6
				(aChiefHead hide:)
				(Print 76 7 #draw)
				(= seconds 3)
			)
			(7
				(Print 76 8)
				(aChiefHead show:)
				(= seconds 3)
			)
			(8
				(aChiefHead hide:)
				(Print 76 9 #draw)
				(Print 76 10 #at -1 130)
				(= seconds 3)
			)
			(9
				(aComputerCarrier
					setLoop: 4
					setMotion: MoveTo 444 96 self
				)
				(aDrummer setCycle: Forward)
				(theSound play:)
			)
			(10
				(aComputerCarrier
					setLoop: 1
					setMotion: MoveTo 221 96 self
				)
			)
			(11
				(aComputerCarrier
					setLoop: 3
					cel: 0
					setCycle: CycleTo 7 1 self
				)
			)
			(12
				((View new:)
					view: 707
					ignoreActors:
					loop: 6
					posn: 207 81
					setPri: 5
					addToPic:
				)
				(aComputerCarrier setCycle: EndLoop self)
			)
			(13
				(aComputerCarrier
					setLoop: 4
					setMotion: MoveTo 233 96 self
				)
			)
			(14
				(aComputerCarrier setLoop: 5 forceUpd: stopUpd:)
				(aChiefHead show:)
				(= cycles 20)
			)
			(15
				(Print 76 11)
				(aChiefHead hide:)
				(= seconds 3)
			)
			(16
				(Print 76 12)
				(aChiefHead show:)
				(= seconds 3)
			)
			(17
				(Print 76 13)
				(Print 76 14)
				(Print 76 15)
				(Print 76 16 #at -1 130)
				(aChiefHead hide:)
				(= cycles 20)
			)
			(18
				(ego setMotion: MoveTo 206 100 self)
				(aKalalau setLoop: 3)
				(= cycles 10)
			)
			(19
				(Print 76 17)
			)
			(20
				(ego
					view: 194
					posn: 206 99
					cel: 0
					cycleSpeed: 1
					setCycle: EndLoop self
				)
			)
			(21
				(ego stopUpd:)
				(aComputer setCycle: Forward)
				(= seconds 3)
			)
			(22
				(aEgoProgramming posn: 275 39)
				(aComputerCode posn: 275 39 cel: 0 setCycle: EndLoop self)
				(= cycles 10)
			)
			(23
				(aComputerCode setLoop: 2 setCycle: Forward)
				(cls)
				(Print 76 18 #at -1 130 #dispose)
				(= cycles (Random 20 30))
			)
			(24
				(cls)
				(Print 76 19 #at -1 130 #dispose)
				(= cycles (Random 20 30))
			)
			(25
				(cls)
				(Print 76 20 #at -1 130 #dispose)
				(= cycles (Random 20 30))
			)
			(26
				(cls)
				(Print 76 21 #at -1 130 #dispose)
				(= cycles (Random 20 30))
			)
			(27
				(cls)
				(Print 76 22 #at -1 100 #dispose)
				(= cycles (Random 20 30))
			)
			(28
				(cls)
				(Print 76 18 #at -1 130 #dispose)
				(= cycles (Random 20 30))
			)
			(29
				(cls)
				(Print 76 23 #at -1 100 #dispose)
				(= cycles (Random 20 30))
			)
			(30
				(cls)
				(= seconds 3)
			)
			(31
				(aComputerCode dispose:)
				(aEgoProgramming dispose:)
				(aComputer stopUpd:)
				(ego setCycle: BegLoop self)
			)
			(32
				(ego
					view: 100
					setCycle: Walk
					setLoop: -1
					loop: 1
					cycleSpeed: 0
				)
				(aDrummer stopUpd:)
				(theSound stop:)
				(= cycles 15)
			)
			(33
				(Print 76 24)
				(aChiefHead show:)
				(= seconds 3)
			)
			(34
				(Print 76 25)
				(aChiefHead hide:)
				(= seconds 3)
			)
			(35
				(Print 76 26)
				(aChiefHead show:)
				(= seconds 3)
			)
			(36
				(Print 76 27)
				(aChiefHead hide:)
				(= seconds 3)
			)
			(37
				(Print 76 28)
				(aChiefHead show:)
				(= seconds 3)
			)
			(38
				(Print 76 29)
				(Print 76 30)
				(Print 76 31 #at -1 130)
				(aChiefHead hide:)
				(aChief setMotion: MoveTo 119 127 self)
			)
			(39
				(aChief setPri: -1 setMotion: MoveTo 251 103 self)
			)
			(40
				(Print 76 32)
				(aChief setMotion: MoveTo 251 95 self)
				(ego setMotion: MoveTo 240 100)
			)
			(41
				(curRoom newRoom: 79)
			)
		)
	)
	
	(method (handleEvent event)
		(if (or (!= (event type?) saidEvent) (event claimed?))
			(return)
		)
		(if (Said 'look>')
			(if (Said '/door')
				(Print 76 0)
			)
			(if (Said '[/airport,angeles,hut]')
				(Print 76 1)
				(Print 76 2)
			)
		)
		(if
			(or
				(Said '(board<in),(disembark<in),climb,board/stair,hut')
				(Said 'look/cup')
				(Said 'bang,open/door')
			)
			(Print 76 3)
		)
	)
)

(instance theSound of Sound
	(properties
		number 114
		loop -1
	)
)
