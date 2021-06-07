;;; Sierra Script 1.0 - (do not remove this comment)
(script# 78)
(include game.sh)
(use Main)
(use Intrface)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm78 0
)

(local
	aWoodChopper
	local1
	aHulaHooper
	aDancer
	aPhotographer
	aDrummer
	aPedaler
	aDoctor
	aChief
	aMouth
	aKalalau
	aCameraman
	thePedaler
	theChief
	theDrummer
	theMouth
)
(instance rm78 of Room
	(properties
		picture 78
		horizon 5
		west 77
	)
	
	(method (init)
		(if (== endGameState 105)
			(self style: DISSOLVE)
		)
		(super init:)
		(NormalEgo)
		(ego posn: 9 133 init:)
		(self setScript: rm78Script)
		(if global111 (= endGameState 105))
		(if (!= endGameState 105)
			(self setRegions: ISLAND)
			(Load VIEW 716)
			((= aWoodChopper (Prop new:))
				view: 716
				posn: 55 102
				cycleSpeed: 1
				init:
				setScript: woodScript
			)
		else
			(HandsOff)
			(rm78Script changeState: 1)
			(Load VIEW 833)
			(Load VIEW 212)
			(Load VIEW 711)
			(Load VIEW 710)
			(Load VIEW 714)
			(Load VIEW 713)
			(Load VIEW 707)
			(Load VIEW 704)
			(Load VIEW 706)
			(Load VIEW 822)
			(Load VIEW 823)
			(Load SOUND 111)
			(theSound init:)
			((View new:)
				view: 833
				ignoreActors:
				loop: 0
				setCel: 255
				posn: 90 122
				addToPic:
			)
			((View new:)
				view: 833
				ignoreActors:
				loop: 1
				setCel: 255
				posn: 105 119
				addToPic:
			)
			((View new:)
				view: 833
				ignoreActors:
				loop: 2
				setCel: 255
				posn: 120 116
				addToPic:
			)
			((View new:)
				view: 833
				ignoreActors:
				loop: 3
				setCel: 255
				posn: 135 113
				addToPic:
			)
			((View new:)
				view: 714
				ignoreActors:
				loop: 3
				cel: 1
				posn: 67 105
				addToPic:
			)
			((View new:)
				view: 714
				ignoreActors:
				loop: 2
				cel: 1
				posn: 206 116
				addToPic:
			)
			((View new:)
				view: 714
				ignoreActors:
				loop: 3
				cel: 0
				posn: 16 108
				addToPic:
			)
			((= aDrummer (Prop new:))
				view: 714
				ignoreActors:
				setLoop: 1
				posn: 233 145
				stopUpd:
				init:
			)
			((= aPedaler (Prop new:))
				view: 707
				ignoreActors:
				setLoop: 7
				posn: 212 163
				stopUpd:
				init:
			)
			((= aDoctor (Actor new:))
				view: 711
				loop: 3
				posn: 159 171
				setPri: 14
				setCycle: Walk
				illegalBits: 0
				stopUpd:
				init:
			)
			((= aHulaHooper (Actor new:))
				view: 823
				ignoreActors:
				illegalBits: 0
				setLoop: 0
				setCycle: Walk
				setStep: 1 1
				posn: 146 128
				stopUpd:
				init:
			)
			((= aDancer (Actor new:))
				view: 823
				ignoreActors:
				illegalBits: 0
				setLoop: 1
				setCycle: Walk
				setStep: 2 1
				posn: 118 144
				stopUpd:
				init:
			)
			((= aKalalau (Actor new:))
				view: 704
				loop: 1
				ignoreActors:
				illegalBits: 0
				posn: -28 141
				setCycle: Walk
				setStep: 3 2
				cycleSpeed: 1
				moveSpeed: 1
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
				posn: -22 137
				init:
			)
			((= aMouth (Prop new:))
				view: 710
				ignoreActors:
				setLoop: 4
				setPri: 10
				posn: 999 999
				setCycle: Forward
				init:
			)
			((= aCameraman (Actor new:))
				view: 212
				ignoreActors:
				illegalBits: 0
				loop: 0
				cel: 0
				posn: 99 179
				moveSpeed: 2
				cycleSpeed: 2
				setCycle: Walk
				setStep: 3 2
				init:
				setScript: minicamScript
			)
			((= aPhotographer (Actor new:))
				view: 823
				ignoreActors:
				illegalBits: 0
				setLoop: 2
				setCel: 5
				setCycle: Walk
				setPri: 13
				posn: 56 169
				stopUpd:
				init:
			)
		)
	)
)

(instance rm78Script of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if
			(and
				(!= endGameState 105)
				(== (ego loop?) 3)
				(== 6 (aWoodChopper cel?))
				(ego inRect: 34 100 43 107)
				(== currentStatus egoNORMAL)
			)
			(self changeState: 47)
		)
	)
	
	(method (changeState newState &tmp egoCycleSpeed)
		(switch (= state newState)
			(1 (= cycles 2))
			(2 (Print 78 10) (= cycles 20))
			(3
				(theSound play:)
				(Print 78 11)
				(aDrummer setCycle: Forward)
				(aHulaHooper setMotion: MoveTo 222 123 self)
				(aDancer setMotion: MoveTo 46 111 self)
			)
			(5
				(Print 78 12)
				(aHulaHooper setLoop: 5 cel: 0 stopUpd:)
				(aDancer cel: 0 stopUpd:)
				(ego setMotion: MoveTo 163 134 self)
			)
			(6
				(ego setLoop: 2 stopUpd:)
				(= seconds 3)
			)
			(7 (Print 78 13) (= seconds 3))
			(8
				(aKalalau setMotion: MoveTo 182 152 self)
				(aChief setMotion: MoveTo 100 138 self)
				(aPedaler setCycle: Forward)
				(minicamScript changeState: 5)
			)
			(10
				(aKalalau setLoop: 1)
				(ego setMotion: MoveTo 165 152 self)
			)
			(11
				(aKalalau hide:)
				(ego view: 822 setLoop: 0 cel: 0 posn: 172 151)
				(Print 78 14 #draw)
				(aChief stopUpd:)
				(aMouth
					posn: (+ (aChief x?) -1) (+ (aChief y?) -25)
				)
				(= seconds 3)
			)
			(12
				(aMouth posn: 666 666)
				(= theMouth aMouth)
				(= theChief aChief)
				(ego stopUpd:)
				(Print 78 15 #draw)
				(AddViewToPic aChief)
				(theChief posn: 888 888)
				(= seconds 3)
			)
			(13
				(Print 78 16)
				(minicamScript changeState: 3)
				(= seconds 3)
			)
			(14
				(Print 78 17)
				(= seconds 3)
			)
			(15
				(Print 78 18)
				(theSound stop:)
				(AddViewToPic aDrummer)
				((= theDrummer aDrummer) posn: 777 777)
				(Print 78 19)
				(= seconds 3)
			)
			(16
				(ego cycleSpeed: 2 setCycle: EndLoop self)
			)
			(17
				(ego cycleSpeed: 6 setLoop: 1 setCel: 0 setCycle: Forward)
				(= cycles 12)
			)
			(18
				(if (= egoCycleSpeed (ego cycleSpeed?))
					(-- egoCycleSpeed)
					(ego cycleSpeed: egoCycleSpeed)
					(-- state)
				)
				(= cycles 8)
			)
			(19 (= seconds 6))
			(20
				(if (< (= egoCycleSpeed (ego cycleSpeed?)) 8)
					(++ egoCycleSpeed)
					(ego cycleSpeed: egoCycleSpeed)
					(-- state)
				)
				(= cycles 8)
			)
			(21
				(ego setLoop: 2)
				(Print 78 20)
				(= cycles 10)
			)
			(22
				(ego cycleSpeed: 2 setLoop: 6 setCycle: EndLoop self)
			)
			(23 (= cycles 20))
			(24
				(Print 78 21)
				(= cycles 22)
			)
			(25
				(Print 78 22 #at -1 152)
				(= cycles 22)
			)
			(26
				(aKalalau
					show:
					setLoop: 0
					cycleSpeed: 0
					moveSpeed: 0
					setCycle: Walk
					setMotion: MoveTo 62 152
				)
				(ego
					view: 100
					setLoop: 0
					cycleSpeed: 0
					moveSpeed: 0
					setCycle: Walk
					setMotion: MoveTo 45 152 self
				)
			)
			(27
				(aKalalau setLoop: 1 setMotion: MoveTo 189 152)
				(ego setLoop: 1 setMotion: MoveTo 172 152 self)
			)
			(28
				(ego setLoop: 0)
				(Print 78 23 #draw)
				(= cycles 10)
			)
			(29
				(aKalalau hide:)
				(ego
					view: 706
					setLoop: 0
					cel: 0
					cycleSpeed: 1
					setCycle: EndLoop self
				)
			)
			(30 (= seconds 5))
			(31
				(Print 78 24 #at -1 152)
				(ego setCycle: BegLoop self)
			)
			(32
				(ego view: 822 setLoop: 2 cycleSpeed: 0 setCycle: Walk)
				(= seconds 3)
			)
			(33
				(Print 78 25)
				(AddViewToPic aPedaler)
				((= thePedaler aPedaler) posn: 999 999)
				(aPhotographer setMotion: MoveTo 132 160 self)
			)
			(34
				(aPhotographer setLoop: 3 cel: 0 setCycle: EndLoop self)
			)
			(35
				(aDoctor view: 713 setLoop: 1 setCycle: Forward)
				(= cycles 50)
			)
			(36
				(theDrummer
					view: 822
					loop: 3
					posn: 170 138
					cel: 0
					cycleSpeed: 2
					setPri: 13
					setCycle: EndLoop self
				)
			)
			(37
				(aDoctor view: 711 setLoop: 3 setCycle: Walk stopUpd:)
				(thePedaler
					view: 822
					setLoop: 4
					posn: 154 105
					setCycle: Forward
				)
				(theChief
					view: 822
					setLoop: 5
					posn: 186 105
					setCycle: Forward
				)
				(= cycles 30)
			)
			(38
				(theMouth
					view: 823
					setLoop: 4
					setPri: 12
					cel: 0
					posn: (+ (aPhotographer x?) 5) (- (aPhotographer y?) 35)
					cycleSpeed: 0
					setCycle: EndLoop self
				)
			)
			(39
				(theMouth dispose:)
				(aPhotographer setCycle: BegLoop self)
			)
			(40 (= cycles 10))
			(41
				(theDrummer setCycle: BegLoop self)
				(thePedaler dispose:)
				(theChief dispose:)
			)
			(42
				(theDrummer dispose:)
				(= seconds 3)
			)
			(43
				(Print 78 26)
				(Print 78 27)
				(aKalalau setLoop: 1 show:)
				(ego
					view: 100
					setPri: -1
					setLoop: -1
					loop: 2
					posn: 165 151
				)
				(aDoctor setPri: -1 setMotion: MoveTo 150 152 self)
			)
			(44
				(aDoctor setMotion: MoveTo 165 116 self)
				(= cycles 5)
			)
			(45
				(Print 78 28)
				(ego setMotion: MoveTo 171 120)
			)
			(46 (curRoom newRoom: 178))
			(47
				(HandsOff)
				(= currentStatus egoSTOPPED)
				(aWoodChopper setScript: 0 setCel: 255 stopUpd:)
				(Print 78 29 #draw)
				(Print 78 30)
				(= seconds 3)
			)
			(48
				(= currentStatus egoDYING)
				(Print 78 31)
			)
		)
	)
	
	(method (handleEvent event)
		(if
		(or (!= (event type?) saidEvent) (event claimed?))
			(return)
		)
		(if (Said 'look>')
			(if (Said '/man,children') (Print 78 0))
			(if (Said '/door') (Print 78 1))
			(if (Said '[/airport,angeles,hut]')
				(Print 78 2)
				(Print 78 3)
			)
		)
		(if
			(or
				(Said '(board<in),(disembark<in),climb,board/stair,hut')
				(Said 'look/cup')
				(Said 'bang,open/door')
			)
			(Print 78 4)
		)
		(if (Said 'call/man,children')
			(if (ego inRect: 0 0 91 120)
				(Print 78 5)
				(Print 78 6 #at -1 152)
			else
				(NotClose)
			)
		)
		(if (Said '/man,children') (Print 78 7))
		(if (Said '/axe') (Print 78 8))
		(if (Said '/[') (Print 78 9))
	)
)

(instance woodScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 8))
			(1 (aWoodChopper setCycle: EndLoop self))
			(2
				(= cycles (++ local1))
				(= state 0)
			)
		)
	)
)

(instance minicamScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(1
				(aCameraman loop: 2 setCycle: EndLoop self)
			)
			(2 (aCameraman stopUpd:))
			(3
				(aCameraman setCycle: BegLoop self)
			)
			(4
				(aCameraman setCycle: Walk setMotion: MoveTo 180 179 self)
				(= state 0)
			)
			(5
				(aCameraman setCycle: BegLoop self)
			)
			(6
				(aCameraman setCycle: Walk setMotion: MoveTo 120 179 self)
				(= state 0)
			)
		)
	)
)

(instance theSound of Sound
	(properties
		number 111
		loop -1
	)
)
