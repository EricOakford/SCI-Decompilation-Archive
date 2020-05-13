;;; Sierra Script 1.0 - (do not remove this comment)
(script# 43)
(include game.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm43 0
)

(local
	knifeInRoom
	gotKnife
	waitingForMaitreD
	canSitAtTable
)
(instance rm43 of Room
	(properties
		picture 43
		horizon 5
		south 40
	)
	
	(method (init)
		(Load VIEW 433)
		(super init:)
		(if ((inventory at: iKnife) ownedBy: curRoomNum)
			(= knifeInRoom TRUE)
		)
		((View new:)
			view: 433
			posn: 22 134
			setPri: 6
			ignoreActors:
			addToPic:
		)
		((View new:)
			view: 433
			cel: 1
			posn: 63 121
			setPri: 6
			ignoreActors:
			addToPic:
		)
		((View new:)
			view: 433
			cel: 2
			posn: 37 163
			setPri: 13
			ignoreActors:
			addToPic:
		)
		((View new:)
			view: 433
			loop: 3
			cel: 1
			posn: 116 81
			setPri: 3
			ignoreActors:
			addToPic:
		)
		((View new:)
			view: 433
			loop: 3
			posn: 65 119
			setPri: 7
			ignoreActors:
			addToPic:
		)
		((View new:)
			view: 433
			loop: 3
			cel: 1
			posn: 178 100
			setPri: 5
			ignoreActors:
			addToPic:
		)
		((View new:)
			view: 433
			loop: 3
			cel: 1
			posn: 242 100
			setPri: 5
			ignoreActors:
			addToPic:
		)
		((View new:)
			view: 433
			loop: 3
			posn: 168 143
			setPri: 9
			ignoreActors:
			addToPic:
		)
		((View new:)
			view: 433
			loop: 3
			cel: 2
			posn: 230 171
			setPri: 13
			ignoreActors:
			addToPic:
		)
		((View new:)
			view: 433
			loop: 2
			cel: 1
			posn: 53 77
			setPri: 4
			ignoreActors:
			addToPic:
		)
		((View new:)
			view: 433
			loop: 2
			posn: 178 55
			setPri: 2
			ignoreActors:
			addToPic:
		)
		(aChair setPri: 7 init: hide:)
		(aTable setPri: 8 init: hide:)
		(Load VIEW 101)
		(Load VIEW 434)
		(Load VIEW 435)
		(Load VIEW 438)
		(Load VIEW 432)
		(aRope setPri: 12 ignoreActors: stopUpd: init:)
		(aMaitreD setCycle: Walk ignoreActors: stopUpd: init:)
		(aMan setCycle: Walk ignoreActors: init:)
		(aWoman setCycle: Walk ignoreActors: init:)
		(= canSitAtTable 1)
		(NormalEgo 3)
		(ego posn: 195 178 observeControl: cYELLOW init:)
		(self setScript: rm43Script)
	)
)

(instance rm43Script of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if
		(and (== currentStatus egoSITTING) (== 0 (mod state 20)))
			(self cue:)
		)
		(if waitingForMaitreD (User canInput: FALSE) else (User canInput: TRUE))
		(if (< state 101) (ego observeControl: cYELLOW))
		(if (& (ego onControl:) $0002) (curRoom newRoom: 40))
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(1
				(aMan setScript: enterScript)
				(enterScript changeState: 1)
			)
			(2 (Print 43 43))
			(3
				(Print 43 44)
				(aMaitreD setScript: (to1Script new:))
				(= waitingForMaitreD 0)
				(= cycles 15)
				(= state 8)
			)
			(9
				(aWoman setScript: (to1Script new:))
				(= cycles 7)
			)
			(10
				(aMan setScript: (to1Script new:))
			)
			(11
				(groupScript init:)
				(aMaitreD setMotion: MoveTo 80 82 groupScript)
			)
			(12
				(aWoman setMotion: MoveTo 95 77 groupScript)
			)
			(13
				(aMan setMotion: MoveTo 135 77 groupScript)
			)
			(14
				(aMaitreD loop: 0)
				(aWoman loop: 0)
				(aMan loop: 1)
				(= seconds 3)
			)
			(15
				(aMan hide:)
				(aWoman hide:)
				((View new:)
					view: 438
					loop: 4
					posn: 99 73
					setPri: 4
					ignoreActors:
					addToPic:
				)
				((View new:)
					view: 435
					loop: 4
					posn: 135 73
					setPri: 4
					ignoreActors:
					addToPic:
				)
				(Print 43 45 #draw)
				(= seconds 3)
			)
			(16
				(aMaitreD setMotion: MoveTo 122 89 self)
			)
			(17
				(aMaitreD setMotion: MoveTo 140 101 self)
			)
			(18 (MDscript changeState: 8))
			(19 (= seconds (Random 2 4)))
			(21
				(aMan setScript: enterScript)
				(enterScript changeState: 1)
			)
			(22 (Print 43 46))
			(23
				(Print 43 47)
				(aMaitreD setScript: (to2Script new:))
				(= cycles 15)
				(= state 27)
			)
			(28
				(aWoman setScript: (to2Script new:))
				(= cycles 7)
			)
			(29
				(aMan setScript: (to2Script new:))
			)
			(30
				(groupScript init:)
				(aMaitreD setMotion: MoveTo 68 90 groupScript)
			)
			(31
				(aWoman setMotion: MoveTo 47 105 groupScript)
			)
			(32
				(aMan setMotion: MoveTo 90 105 groupScript)
			)
			(33
				(aMaitreD loop: 0)
				(aWoman loop: 0)
				(aMan loop: 1)
				(= seconds 3)
			)
			(34
				(aMan hide:)
				(aWoman hide:)
				((View new:)
					view: 438
					loop: 4
					posn: 44 110
					setPri: 8
					ignoreActors:
					addToPic:
				)
				((View new:)
					view: 435
					loop: 4
					posn: 87 109
					setPri: 8
					ignoreActors:
					addToPic:
				)
				(Print 43 45 #draw)
				(= seconds 3)
			)
			(35
				(aMaitreD setMotion: MoveTo 122 89 self)
			)
			(36
				(aMaitreD setMotion: MoveTo 140 101 self)
			)
			(37 (MDscript changeState: 8))
			(38
				(= state 39)
				(= seconds (Random 2 4))
			)
			(41
				(aMan setScript: enterScript)
				(enterScript changeState: 1)
			)
			(42 (Print 43 48))
			(43
				(Print 43 49)
				(aMaitreD setScript: (to3Script new:))
				(= waitingForMaitreD 0)
				(= cycles 15)
				(= state 47)
			)
			(48
				(aWoman setScript: (to3Script new:))
				(= cycles 7)
			)
			(49
				(aMan setScript: (to3Script new:))
			)
			(50
				(groupScript init:)
				(aMaitreD setMotion: MoveTo 147 101 groupScript)
			)
			(51
				(aWoman setMotion: MoveTo 158 96 groupScript)
			)
			(52
				(aMan setMotion: MoveTo 202 97 groupScript)
			)
			(53
				(aMaitreD loop: 0)
				(aWoman loop: 0)
				(aMan loop: 1)
				(= seconds 3)
			)
			(54
				(aMan hide:)
				(aWoman hide:)
				((View new:)
					view: 438
					loop: 4
					posn: 161 92
					setPri: 6
					ignoreActors:
					addToPic:
				)
				((View new:)
					view: 435
					loop: 4
					posn: 197 92
					setPri: 6
					ignoreActors:
					addToPic:
				)
				(Print 43 45 #draw)
				(= seconds 3)
			)
			(55 (MDscript changeState: 8))
			(56
				(= state 59)
				(= seconds (Random 2 4))
			)
			(61
				(aMan setScript: enterScript)
				(enterScript changeState: 1)
			)
			(62 (Print 43 50))
			(63
				(Print 43 51)
				(aMaitreD setScript: (to4Script new:))
				(= cycles 15)
				(= waitingForMaitreD 0)
				(= state 67)
			)
			(68
				(aWoman setScript: (to4Script new:))
				(= cycles 7)
			)
			(69
				(aMan setScript: (to4Script new:))
			)
			(70
				(groupScript init:)
				(aMaitreD setMotion: MoveTo 277 100 groupScript)
			)
			(71
				(aWoman setMotion: MoveTo 220 96 groupScript)
			)
			(72
				(aMan setMotion: MoveTo 263 97 groupScript)
			)
			(73
				(aMaitreD loop: 1)
				(aWoman loop: 0)
				(aMan loop: 1)
				(= seconds 3)
			)
			(74
				(aMan hide:)
				(aWoman hide:)
				((View new:)
					view: 438
					loop: 4
					posn: 225 92
					setPri: 6
					ignoreActors:
					addToPic:
				)
				((View new:)
					view: 435
					loop: 4
					posn: 261 92
					setPri: 6
					ignoreActors:
					addToPic:
				)
				(Print 43 45 #draw)
				(= seconds 3)
			)
			(75 (MDscript changeState: 8))
			(76
				(= state 79)
				(= seconds (Random 2 4))
			)
			(81
				(aMan setScript: enterScript)
				(enterScript changeState: 1)
			)
			(82 (Print 43 52))
			(83
				(Print 43 53)
				(aMaitreD setMotion: MoveTo 190 142 self)
				(= waitingForMaitreD 0)
				(= state 87)
			)
			(88
				(aMaitreD setMotion: MoveTo 134 139 self)
			)
			(89
				(aMaitreD loop: 0)
				(aWoman setMotion: MoveTo 190 142 self)
			)
			(90
				(aWoman setMotion: MoveTo 149 136 self)
			)
			(91
				(aWoman loop: 0)
				(aMan setMotion: MoveTo 190 142 self)
			)
			(92
				(aMan setMotion: MoveTo 189 136 self)
			)
			(93
				(aMan loop: 1)
				(= seconds 3)
			)
			(94
				(aMan hide:)
				(aWoman dispose:)
				((View new:)
					view: 438
					loop: 4
					posn: 151 134
					setPri: 10
					ignoreActors:
					addToPic:
				)
				((View new:)
					view: 435
					loop: 4
					posn: 190 133
					setPri: 10
					ignoreActors:
					addToPic:
				)
				(Print 43 45 #draw)
				(= seconds 3)
			)
			(95
				(aMaitreD setMotion: MoveTo 154 167 self)
			)
			(96
				(aMaitreD loop: 0)
				(= canSitAtTable TRUE)
				(= state 99)
				(= seconds 3)
			)
			(101
				(= waitingForMaitreD TRUE)
				(User canControl: FALSE)
				(= seconds 0)
				(Print 43 54)
				(Print 43 55 #at -1 152)
				(MDscript changeState: 1)
				(ego
					setScript: 0
					setLoop: 0
					setCel: 255
					setCycle: BegLoop self
					setMotion: 0
				)
			)
			(102
				(aRope setLoop: 7 stopUpd:)
				(ego
					view: 100
					setLoop: 2
					setLoop: -1
					cycleSpeed: 0
					setCycle: Walk
					illegalBits: 0
					ignoreActors:
					setMotion: MoveTo 193 172 self
				)
			)
			(103
				(ego setMotion: MoveTo 193 150 self)
			)
			(104 (= seconds 2))
			(105
				(Print 43 56)
				(Print 43 57)
				(aMaitreD setScript: (toLarryScript new:))
				(= cycles 10)
			)
			(106
				(ego setScript: (toLarryScript new:))
			)
			(107
				(aMaitreD setMotion: MoveTo 253 105 self)
			)
			(108
				(ego setMotion: MoveTo 240 113 self)
			)
			(109
				(ego setLoop: 0)
				(Print 43 58 #draw)
				(= seconds 3)
			)
			(110
				(aMan
					view: 432
					setLoop: -1
					loop: 1
					posn: 333 109
					setPri: 4
					show:
					setMotion: MoveTo 275 110 self
				)
			)
			(111
				(aMan loop: 0)
				(= cycles 5)
			)
			(112
				(aChair show:)
				(aTable show: setCycle: EndLoop self)
			)
			(113
				(aChair setCycle: EndLoop self)
			)
			(114 (= cycles 5))
			(115
				(Print 43 59 #at -1 20 #draw)
				(theGame changeScore: 1)
				(Print (Format @str 43 60 tritePhrase) #at -1 20)
				(aTable stopUpd:)
				(aChair stopUpd:)
				(aMan setPri: 6 setMotion: MoveTo 311 110)
				(aMaitreD setPri: 6 setMotion: MoveTo 311 110 self)
			)
			(116
				(aMan dispose:)
				(aMaitreD dispose:)
				((View new:)
					ignoreActors:
					view: 433
					loop: 1
					posn: 287 63
					setPri: 4
					addToPic:
				)
				(Print 43 61 #at -1 152 #draw)
				(NormalEgo)
				(= waitingForMaitreD 0)
			)
			(121
				(= waitingForMaitreD 1)
				(User canControl: FALSE)
				(= currentStatus egoSTOPPED)
				(ego setMotion: 0)
				(Print 43 62)
				(Print 43 63)
				(= seconds 3)
			)
			(122
				(Print 43 64)
				(Print 43 65)
				(= seconds 3)
			)
			(123
				(Print 43 66)
				(= currentStatus egoDEAD)
			)
		)
	)
	
	(method (handleEvent event)
		(if
		(or (!= (event type?) saidEvent) (event claimed?))
			(return)
		)
		(if (Said 'look<below/buffet')
			(Print 43 0)
			(Print 43 1 #at -1 152)
		)
		(if (Said 'look>')
			(if (Said '/appetizer,pate,buffet')
				(cond 
					((ego inRect: 30 130 80 176) (if knifeInRoom (Print 43 2) else (Print 43 3)))
					((== currentStatus egoATTABLE) (Print 43 4))
					((ego inRect: 266 111 296 124) (Print 43 5))
					((< state 3) (Print 43 6))
					(else (Print 43 7))
				)
			)
			(if (Said '/barstool') (Print 43 8))
			(if (Said '/man,agent,bimbo,children')
				(cond 
					((not state) (Print 43 9))
					((< state 15) (Print 43 10))
					(else (Print 43 11) (Print 43 12))
				)
			)
			(if (Said '/crackers,crystal,bowl') (Print 43 13))
			(if (Said '[/airport,cafe]') (Print 43 14))
		)
		(if (Said '/bathing') (Print 43 15))
		(if
			(or
				(Said 'call,ask/man')
				(Said 'call,ask/man/buffet')
				(Said 'call,ask/man<buffet')
				(Said 'call,ask/buffet<man')
				(Said 'call,ask')
			)
			(cond 
				((> state 100) (Print 43 16))
				((!= currentEgoView 100) (Print 43 17))
				((== knifeInRoom FALSE) (Print 43 18))
				((== canSitAtTable 0) (Print 43 19))
				(else
					(Print 43 20)
					(Print 43 21)
					(if (!= currentEgoView 100)
						(Print 43 22)
					else
						(= talkedToMaitreD TRUE)
						(Print 43 23)
						(Print introductoryPhrase)
						(Print 43 24)
					)
				)
			)
		)
		(if (Said 'get,(get<up),eat/appetizer')
			(cond 
				((ego inRect: 30 130 80 176)
					(if knifeInRoom
						(Print 43 25)
						(Print 43 26)
					else
						(Print 43 27)
						(Print 43 28)
					)
				)
				((== currentStatus egoATTABLE) (self changeState: 121))
				((ego inRect: 266 111 296 124) (Print 43 29))
				((< state 3) (Print 43 30))
				(else (Print 43 31))
			)
		)
		(if (Said '(get<up),get/crackers,crystal,bowl')
			(Print 43 32)
		)
		(if (Said '(get<up),get/gun')
			(cond 
				((== knifeInRoom FALSE) (AlreadyTook))
				((not (ego inRect: 32 142 55 161)) (NotClose))
				(else
					(Print 43 33)
					(Print 43 34)
					(ego get: 17)
					(= knifeInRoom FALSE)
					(= gotKnife 1)
					(theGame changeScore: 3)
				)
			)
		)
		(if (Said 'bath')
			(cond 
				(
				(or (== currentStatus egoSITTING) (== currentStatus egoATTABLE)) (Print 43 35))
				((!= currentEgoView 100) (Print 43 36))
				((and (== knifeInRoom FALSE) (== gotKnife 0)) (Print 43 18))
				((!= currentStatus egoNORMAL) (NotNow))
				((ego inRect: 266 111 296 124)
					(if (not (ego inRect: 268 111 290 116))
						(Print 43 37)
					else
						(Ok)
						(ego setScript: TSscript)
						(TSscript changeState: 0)
					)
				)
				((not (ego inRect: 175 160 241 189)) (NotClose))
				((not talkedToMaitreD) (Print 43 38))
				(else
					(Ok)
					(if (not servedAtResortRestaurant)
						(= servedAtResortRestaurant TRUE)
						(theGame changeScore: 1)
					)
					(ego setScript: SITscript)
					(SITscript changeState: 1)
				)
			)
		)
		(if (Said 'new,(new<up),(get<up)')
			(cond 
				((== currentStatus egoNORMAL) (YouAre))
				((== currentStatus egoSITTING)
					(Ok)
					(ego setScript: SITscript)
					(SITscript changeState: 5)
				)
				((== currentStatus egoATTABLE)
					(Ok)
					(ego setScript: TSscript)
					(TSscript changeState: 3)
				)
				(else (Print 43 39))
			)
		)
		(if
			(and
				(< state 101)
				(ego inRect: 170 155 194 179)
				(== canSitAtTable 1)
				(or (Said 'give/man/buck') (Said 'tip,bribe/agent,man'))
			)
			(Print 43 40)
			(Print 43 41)
		)
		(if (Said 'drink') (Print 43 42))
	)
)

(instance SITscript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(1
				(if seconds (= seconds 0))
				(= waitingForMaitreD 1)
				(User canControl: 0)
				(if (< (ego y?) 170)
					(ego setMotion: MoveTo 200 170 self)
					(-- state)
				else
					(ego setMotion: MoveTo 227 172 self)
				)
			)
			(2
				(ego
					view: 101
					setLoop: 0
					setCel: 0
					cycleSpeed: 2
					setMotion: 0
					setCycle: EndLoop self
				)
			)
			(3
				(= currentStatus egoSITTING)
				(= waitingForMaitreD FALSE)
				(= seconds (Random 2 6))
			)
			(4
				(ego setLoop: (Random 1 2))
				(if (== (ego cel?) 0)
					(ego setCycle: EndLoop)
				else
					(ego setCycle: BegLoop)
				)
				(-- state)
				(= seconds (Random 2 6))
			)
			(5
				(if seconds (= seconds 0))
				(ego
					setLoop: 0
					setCel: 255
					setCycle: BegLoop self
					setMotion: 0
				)
				(= waitingForMaitreD 1)
			)
			(6 (NormalEgo 2) (= waitingForMaitreD 0))
		)
	)
)

(instance TSscript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if seconds (= seconds 0))
				(= waitingForMaitreD 1)
				(User canControl: 0)
				(ego
					illegalBits: 0
					ignoreActors:
					setMotion: MoveTo 283 113 self
				)
			)
			(1
				(ego
					view: 101
					setLoop: 0
					setCel: 0
					cycleSpeed: 2
					setMotion: 0
					setCycle: EndLoop self
				)
			)
			(2
				(= currentStatus egoATTABLE)
				(= waitingForMaitreD 0)
			)
			(3
				(ego
					setLoop: 0
					setCel: 255
					setCycle: BegLoop self
					setMotion: 0
				)
				(= waitingForMaitreD 1)
			)
			(4 (NormalEgo 2) (= waitingForMaitreD 0))
		)
	)
)

(instance MDscript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(1
				(= canSitAtTable 0)
				(aMaitreD
					setLoop: -1
					setCycle: Walk
					setMotion: MoveTo 172 155 self
				)
			)
			(2
				(aMaitreD setMotion: MoveTo 189 155 self)
			)
			(3
				(aMaitreD cel: 0 setMotion: MoveTo 210 155 self)
				(aRope setCycle: EndLoop)
			)
			(4 (rm43Script cue:))
			(8
				(aMaitreD setMotion: MoveTo 209 102 self)
			)
			(9
				(aMaitreD setMotion: MoveTo 209 139 self)
			)
			(10
				(aMaitreD setMotion: MoveTo 172 155 self)
			)
			(11
				(aMaitreD setMotion: MoveTo 154 167 self)
			)
			(12
				(aMaitreD setLoop: 2)
				(= canSitAtTable 1)
				(rm43Script cue:)
			)
		)
	)
)

(instance to1Script of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(client setMotion: MoveTo 209 139 self)
			)
			(1
				(client setMotion: MoveTo 209 102 self)
			)
			(2
				(client setMotion: MoveTo 140 101 self)
			)
			(3
				(client setMotion: MoveTo 122 89 self)
			)
			(4
				(client setMotion: MoveTo 116 86 self)
			)
			(5
				(rm43Script cue:)
				(client setScript: 0)
			)
		)
	)
)

(instance to2Script of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(client setMotion: MoveTo 209 139 self)
			)
			(1
				(client setMotion: MoveTo 209 102 self)
			)
			(2
				(client setMotion: MoveTo 140 101 self)
			)
			(3
				(client setMotion: MoveTo 122 89 self)
			)
			(4
				(client setMotion: MoveTo 112 86 self)
			)
			(5
				(rm43Script cue:)
				(client setScript: 0)
			)
		)
	)
)

(instance to3Script of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(client setMotion: MoveTo 209 139 self)
			)
			(1
				(client setMotion: MoveTo 209 102 self)
			)
			(2
				(client setMotion: MoveTo 200 102 self)
			)
			(3
				(rm43Script cue:)
				(client setScript: 0)
			)
		)
	)
)

(instance to4Script of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(client setMotion: MoveTo 209 139 self)
			)
			(1
				(client setMotion: MoveTo 209 102 self)
			)
			(2
				(client setMotion: MoveTo 208 102 self)
			)
			(3
				(rm43Script cue:)
				(client setScript: 0)
			)
		)
	)
)

(instance toLarryScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(client setMotion: MoveTo 209 139 self)
			)
			(1
				(client setMotion: MoveTo 241 113 self)
			)
			(2
				(rm43Script cue:)
				(client setScript: 0)
			)
		)
	)
)

(instance groupScript of Script
	(properties)
	
	(method (init)
		(= state 0)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(3 (rm43Script cue:))
		)
	)
)

(instance aChair of Prop
	(properties
		y 111
		x 287
		view 433
		loop 5
		cycleSpeed 2
	)
)

(instance aTable of Prop
	(properties
		y 116
		x 272
		view 433
		loop 6
		cycleSpeed 2
	)
)

(instance aRope of Prop
	(properties
		y 143
		x 225
		view 433
		loop 4
	)
)

(instance aMaitreD of Actor
	(properties
		y 166
		x 154
		view 434
		illegalBits $0000
	)
)

(instance aMan of Actor
	(properties
		y 234
		x 188
		view 435
		loop 3
		illegalBits $0000
	)
)

(instance aWoman of Actor
	(properties
		y 234
		x 204
		view 438
		loop 3
		illegalBits $0000
	)
)

(instance enterScript of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 102])
		(switch (= state newState)
			(1
				(if (!= currentStatus egoSITTING)
					(-- state)
					(= seconds (Random 2 4))
				else
					(= seconds 0)
					(= waitingForMaitreD TRUE)
					(User canControl: 0)
					(aMan
						posn: 188 234
						setCycle: Walk
						show:
						setMotion: MoveTo 188 175 self
					)
					(aWoman
						posn: 204 234
						setCycle: Walk
						show:
						setMotion: MoveTo 204 175
					)
				)
			)
			(2
				(= canSitAtTable 0)
				(aMaitreD
					setLoop: -1
					setCycle: Walk
					setMotion: MoveTo 172 155 self
				)
			)
			(3
				(aMaitreD setMotion: MoveTo 189 155 self)
			)
			(4
				(aMaitreD cel: 0 setMotion: MoveTo 210 155 self)
				(aRope setCycle: EndLoop)
			)
			(5
				(rm43Script cue:)
				(= cycles 10)
			)
			(6
				(aWoman setMotion: MoveTo 193 165 self)
			)
			(7
				(aWoman setMotion: MoveTo 193 149)
				(aMan setMotion: MoveTo 193 165 self)
			)
			(8
				(aMan setMotion: MoveTo 193 152 self)
			)
			(9
				(aMaitreD setMotion: MoveTo 185 155 self)
				(aRope setCycle: BegLoop)
			)
			(10
				(aMaitreD setMotion: MoveTo 210 155 self)
				(aRope stopUpd:)
			)
			(11
				(Print 43 56)
				(= waitingForMaitreD FALSE)
				(rm43Script cue:)
			)
		)
	)
)
