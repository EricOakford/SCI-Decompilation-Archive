;;; Sierra Script 1.0 - (do not remove this comment)
(script# 690)
(include game.sh)
(use Main)
(use Intrface)
(use Extra)
(use Motion)
(use Game)
(use Invent)
(use User)
(use Actor)
(use System)

(public
	Room690 0
)

(local
	rosellaEye
	edgar
	genesta
	local3
	hen
	poof
	ripple
	edgarFace
	edgarHands
	wave1
	wave2
)
(instance wave1 of Prop
	(properties)
)

(instance wave2 of Prop
	(properties)
)

(instance fairyCage of Cage
	(properties
		top 52
		left 101
		bottom 92
		right 228
	)
)

(instance Room690 of Room
	(properties
		picture 40
		horizon 98
	)
	
	(method (init)
		(super init:)
		(self setRegions: END)
		(= userFont smallFont)
		(wave1
			view: 664
			loop: 3
			cel: 0
			posn: 40 178
			setPri: 0
			ignoreActors:
			cycleSpeed: 4
			setCycle: Forward
			init:
		)
		(wave2
			view: 664
			loop: 4
			cel: 2
			posn: 280 178
			setPri: 0
			ignoreActors:
			cycleSpeed: 4
			setCycle: Forward
			init:
		)
		(= ripple (Prop new:))
		(ripple
			view: 650
			loop: 2
			cel: 2
			posn: 61 84
			setPri: 0
			ignoreActors:
			setCycle: Forward
			init:
		)
		((View new:)
			view: 613
			loop: 1
			cel: 0
			posn: 155 78
			setPri: 2
			init:
			addToPic:
		)
		((Actor new:)
			view: 114
			illegalBits: 0
			posn: 110 59
			setCycle: Walk
			setMotion: Wander
			setPri: 3
			observeBlocks: fairyCage
			moveSpeed: 2
			ignoreHorizon:
			init:
		)
		((Actor new:)
			view: 110
			illegalBits: 0
			posn: 190 79
			setCycle: Walk
			setMotion: Wander
			setPri: 3
			observeBlocks: fairyCage
			moveSpeed: 2
			ignoreHorizon:
			init:
		)
		(ego
			view: 106
			posn: 158 129
			setLoop: 5
			cel: 0
			setScript: egoActions
			init:
		)
		(= inCutscene TRUE)
	)
	
	(method (dispose)
		(DisposeScript EXTRA)
		(super dispose:)
	)
)

(instance egoActions of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(User canControl: FALSE canInput: FALSE)
				(ego setCycle: EndLoop self)
				(cond 
					((and (ego has: iMagicFruit) (ego has: iMagicHen)) ((ScriptID END 1) number: 201 play:))
					((ego has: iMagicFruit) ((ScriptID END 1) number: 200 play:))
					((ego has: iMagicHen) ((ScriptID END 1) number: 203 play:))
					(else ((ScriptID END 1) number: 202 play:))
				)
			)
			(1
				(= genesta (Actor new:))
				(genesta
					posn: 185 129
					view: 106
					setLoop: 4
					cel: 0
					init:
					setCycle: EndLoop self
				)
			)
			(2
				(= timedMessage (Print 690 0 #at -1 0 #dispose))
				(= seconds 5)
			)
			(3
				(cls)
				(if (ego has: iMagicHen)
					(self changeState: 10)
				else
					(self changeState: 4)
				)
			)
			(4
				(if (ego has: iMagicFruit)
					(self changeState: 20)
				else
					(self changeState: 100)
				)
			)
			(10
				(= timedMessage
					(Print 690 1 #at -1 10 #title {Genesta} #draw #dispose)
				)
				(= seconds 6)
			)
			(11
				(if modelessDialog (modelessDialog dispose:))
				(ego
					view: 106
					setLoop: 1
					cel: 0
					cycleSpeed: 1
					setCycle: EndLoop self
				)
			)
			(12
				(genesta setLoop: 0 cel: 0 setCycle: EndLoop self)
				(ego setLoop: 5 cel: 5)
				(theGame changeScore: 2)
				((Inventory at: iMagicHen) moveTo: -1)
			)
			(13
				(genesta loop: 2 cycleSpeed: 1 setCycle: Forward)
				(= timedMessage (Print 690 2 #at -1 0 #dispose))
				(= seconds 8)
			)
			(14
				(if modelessDialog (modelessDialog dispose:))
				(= timedMessage
					(Print 690 3 #at -1 10 #title {Genesta} #dispose)
				)
				(= seconds 6)
			)
			(15
				(if modelessDialog (modelessDialog dispose:))
				(genesta setLoop: 3 cel: 0 setCycle: CycleTo 3 1 self)
			)
			(16
				(genesta setCycle: EndLoop)
				(= hen (Actor new:))
				(hen
					view: 360
					xStep: 1
					yStep: 1
					cycleSpeed: 3
					posn: (genesta x?) (+ (genesta y?) 6)
					moveSpeed: 3
					setCycle: Walk
					setMotion: Wander
					illegalBits: -2
					init:
				)
				(= seconds 4)
			)
			(17
				(genesta cycleSpeed: 0)
				(self changeState: 4)
			)
			(20
				(= timedMessage
					(Print 690 4 #at -1 10 #title {Genesta} #dispose)
				)
				(= seconds 15)
			)
			(21
				(if modelessDialog (modelessDialog dispose:))
				(= timedMessage (Print 690 5 #at -1 0 #dispose))
				(= seconds 6)
			)
			(22
				(if modelessDialog (modelessDialog dispose:))
				(= timedMessage
					(Print 690 6 #at -1 10 #title {Genesta} #dispose)
				)
				(= seconds 10)
			)
			(23
				(if modelessDialog (modelessDialog dispose:))
				(genesta view: 755 loop: 2 cel: 0 setCycle: EndLoop self)
			)
			(24
				(= poof (Prop new:))
				(poof
					view: 680
					cel: 0
					posn: (ego x?) (ego y?)
					setPri: (+ (ego priority?) 1)
					setCycle: CycleTo 5 1 self
					init:
				)
			)
			(25
				(ego view: 757 loop: 0 cel: 1)
				(poof setCycle: EndLoop self)
			)
			(26
				(poof hide:)
				(= timedMessage
					(Print 690 7 #at -1 10 #title {Genesta} #dispose)
				)
				(genesta loop: 3 setCycle: Forward)
				(= seconds 10)
			)
			(27
				(if modelessDialog (modelessDialog dispose:))
				(genesta loop: 2 cel: 0 setCycle: EndLoop self)
			)
			(28
				(= edgar (Actor new:))
				(edgar
					view: 107
					posn: 136 130
					loop: 0
					cel: 0
					setCycle: EndLoop self
					init:
				)
			)
			(29
				(ego cel: 0)
				(edgar loop: 1 cel: 0 setCycle: EndLoop self)
			)
			(30
				(= timedMessage
					(Print 690 8 #at -1 10 #title {Genesta} #dispose)
				)
				(= seconds 8)
				(genesta loop: 3 setCycle: Forward)
			)
			(31
				(if modelessDialog (modelessDialog dispose:))
				(genesta loop: 2 cel: 0 setCycle: EndLoop self)
			)
			(32
				(edgar loop: 2 cel: 0 setCycle: EndLoop self)
				(genesta setLoop: -1 loop: 3 setCycle: Forward)
			)
			(33 (= seconds 4))
			(34
				(edgar
					view: 108
					loop: 0
					cel: 0
					cycleSpeed: 3
					setCycle: CycleTo 3 1 self
				)
			)
			(35
				(cast eachElementDo: #hide)
				(curRoom drawPic: 696)
				((= edgarFace (Prop new:))
					view: 787
					loop: 0
					cel: 0
					posn: 84 127
					ignoreActors:
					init:
				)
				(= wave1 (Prop new:))
				(= wave2 (Prop new:))
				(wave1
					view: 787
					loop: 4
					cel: 1
					posn: 17 111
					setPri: 0
					cycleSpeed: 4
					setCycle: Forward
					init:
				)
				(wave2
					view: 787
					loop: 5
					cel: 3
					posn: 126 94
					setPri: 0
					cycleSpeed: 4
					setCycle: Forward
					init:
				)
				((= rosellaEye (Extra new:))
					view: 787
					loop: 3
					cel: 2
					posn: 180 54
					setPri: 13
					pauseCel: 0
					minPause: 30
					maxPause: 30
					minCycles: 4
					maxCycles: 4
					init:
				)
				(= timedMessage (Print 690 9 #at -1 0 #dispose))
				(= seconds 6)
			)
			(36
				(if modelessDialog (modelessDialog dispose:))
				(edgarFace cycleSpeed: 5 setCycle: EndLoop self)
				(= timedMessage
					(Print 690 10 #at -1 10 #title {Edgar} #dispose)
				)
				((= edgarHands (View new:))
					view: 787
					loop: 2
					cel: 0
					posn: 92 192
					setPri: 14
					init:
				)
			)
			(37
				(if modelessDialog (modelessDialog dispose:))
				(= timedMessage (Print 690 11 #at -1 0 #dispose))
				(= seconds 5)
			)
			(38
				(if modelessDialog (modelessDialog dispose:))
				(= timedMessage (Print 690 12 #at -1 0 #dispose))
				(= seconds 5)
			)
			(39
				(if modelessDialog (modelessDialog dispose:))
				(= timedMessage (Print 690 13 #at -1 0 #dispose))
				(= seconds 3)
			)
			(40
				(if modelessDialog (modelessDialog dispose:))
				(= timedMessage
					(Print 690 14 #at -1 10 #title {Rosella} #dispose)
				)
				(edgarFace
					view: 787
					loop: 1
					cel: 0
					posn: 72 157
					setPri: 14
				)
				(edgarHands dispose:)
				(= seconds 7)
			)
			(41
				(if modelessDialog (modelessDialog dispose:))
				(edgarFace dispose:)
				(rosellaEye dispose:)
				(= timedMessage
					(Print 690 15 #at -1 10 #title {Rosella} #dispose)
				)
				(= seconds 4)
			)
			(42
				(if modelessDialog (modelessDialog dispose:))
				(wave1 dispose:)
				(wave2 dispose:)
				(Timer setCycle: self 2)
			)
			(43
				(curRoom drawPic: 40)
				(cast eachElementDo: #show)
				((View new:)
					view: 613
					loop: 1
					cel: 0
					posn: 156 77
					setPri: 2
					init:
					addToPic:
				)
				(ego cel: 1)
				(edgar setCycle: EndLoop)
				(= timedMessage
					(Print 690 16 #at -1 10 #title {Genesta} #dispose)
				)
				(= seconds 6)
			)
			(44
				(if modelessDialog (modelessDialog dispose:))
				(= timedMessage
					(Print 690 17 #at -1 10 #title {Genesta} #dispose)
				)
				(= seconds 5)
			)
			(45
				(if modelessDialog (modelessDialog dispose:))
				(genesta loop: 2 cel: 0 setCycle: EndLoop self)
			)
			(46
				(= poof (Prop new:))
				(poof
					view: 680
					loop: 0
					cel: 0
					posn: (ego x?) (ego y?)
					setPri: (+ (ego priority?) 1)
					setCycle: CycleTo 5 1 self
					init:
				)
			)
			(47
				(ego hide:)
				(poof setCycle: EndLoop self)
			)
			(48 (curRoom newRoom: 693))
			(100
				(genesta view: 755 setLoop: 3 cel: 8)
				(= timedMessage
					(Print 690 18 #at -1 10 #title {Genesta} #dispose)
				)
				(= seconds 15)
			)
			(101
				(if modelessDialog (modelessDialog dispose:))
				(= timedMessage (Print 690 19 #at -1 0 #dispose))
				(= seconds 8)
			)
			(102
				(if modelessDialog (modelessDialog dispose:))
				(= timedMessage
					(Print 690 20 #at -1 10 #title {Genesta} #dispose)
				)
				(= seconds 10)
			)
			(103
				(genesta setLoop: 2 cel: 0 setCycle: EndLoop self)
			)
			(104
				(if modelessDialog (modelessDialog dispose:))
				(= poof (Prop new:))
				(poof
					view: 680
					cel: 0
					posn: (ego x?) (ego y?)
					setPri: (+ (ego priority?) 1)
					setCycle: CycleTo 5 1 self
					init:
				)
			)
			(105
				(ego view: 757 loop: 0 cel: 1)
				(poof setCycle: EndLoop self)
			)
			(106
				(poof hide:)
				(= timedMessage
					(Print 690 21 #at -1 10 #title {Genesta} #dispose)
				)
				(= seconds 10)
			)
			(107
				(if modelessDialog (modelessDialog dispose:))
				(poof cel: 0 show: setCycle: CycleTo 5 1 self)
			)
			(108
				(ego hide:)
				(poof setCycle: EndLoop self)
			)
			(109
				(poof dispose:)
				(curRoom newRoom: 693)
			)
		)
	)
)
