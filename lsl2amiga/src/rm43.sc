;;; Sierra Script 1.0 - (do not remove this comment)
(script# 43)
(include sci.sh)
(use Main)
(use Intrface)
(use DPath)
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
	maitreDIsReady
)
(instance rm43 of Rm
	(properties
		picture 43
		horizon 5
		south 40
	)
	
	(method (init)
		(Load rsVIEW 433)
		(super init:)
		(if ((inventory at: 17) ownedBy: curRoomNum)
			(= knifeInRoom 1)
		)
		(addToPics
			add: aCheese aSalad aFood aT1 aT2 aT3 aT4 aT5 aLchair aWpicture aEpicture
			doit:
		)
		(aChair setPri: 7 init: hide:)
		(aTable setPri: 8 init: hide:)
		(Load rsVIEW 101)
		(Load rsVIEW 434)
		(Load rsVIEW 435)
		(Load rsVIEW 438)
		(Load rsVIEW 432)
		(aRope setPri: 12 ignoreActors: init:)
		(aMaitreD setCycle: Walk ignoreActors: stopUpd: init:)
		(aMan setCycle: Walk ignoreActors: init:)
		(aWoman setCycle: Walk ignoreActors: init:)
		(= maitreDIsReady 1)
		(NormalEgo 3)
		(ego posn: 195 178 observeControl: 16384 init:)
		(self setScript: rm43Script)
	)
	
	(method (dispose)
		(DisposeScript 964)
		(super dispose:)
	)
)

(instance rm43Script of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if
		(and (== currentStatus 1009) (== 0 (mod state 20)))
			(self cue:)
		)
		(if waitingForMaitreD
			(User canInput: 0)
		else
			(User canInput: 1)
		)
		(if (< state 101) (ego observeControl: 16384))
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
				(aMaitreD
					setMotion: DPath 209 139 209 102 140 101 122 89 116 86 self
				)
				(= waitingForMaitreD 0)
				(= cycles 15)
				(= state 8)
			)
			(9
				(aWoman
					setMotion: DPath 209 139 209 102 140 101 122 89 116 86 self
				)
				(= cycles 7)
			)
			(10
				(aMan
					setMotion: DPath 209 139 209 102 140 101 122 89 116 86 self
				)
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
				(addToPics add: aWoman1 aMan1 doit:)
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
				(aMaitreD
					setMotion: DPath 209 139 209 102 140 101 122 89 116 86 self
				)
				(= cycles 15)
				(= state 27)
			)
			(28
				(aWoman
					setMotion: DPath 209 139 209 102 140 101 122 89 116 86 self
				)
				(= cycles 7)
			)
			(29
				(aMan
					setMotion: DPath 209 139 209 102 140 101 122 89 116 86 self
				)
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
				(addToPics add: aWoman2 aMan2 doit:)
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
				(aMaitreD setMotion: DPath 209 139 209 102 200 102 self)
				(= waitingForMaitreD 0)
				(= cycles 15)
				(= state 47)
			)
			(48
				(aWoman setMotion: DPath 209 139 209 102 200 102 self)
				(= cycles 7)
			)
			(49
				(aMan setMotion: DPath 209 139 209 102 200 102 self)
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
				(addToPics add: aWoman3 aMan3 doit:)
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
				(aMaitreD setMotion: DPath 209 139 209 102 208 102 self)
				(= cycles 15)
				(= waitingForMaitreD 0)
				(= state 67)
			)
			(68
				(aWoman setMotion: DPath 209 139 209 102 208 102 self)
				(= cycles 7)
			)
			(69
				(aMan setMotion: DPath 209 139 209 102 208 102 self)
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
				(addToPics add: aMan4 aWoman4 doit:)
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
				(addToPics add: aMan5 aWoman5 doit:)
				(Print 43 45 #draw)
				(= seconds 3)
			)
			(95
				(aMaitreD setMotion: MoveTo 154 167 self)
			)
			(96
				(aMaitreD loop: 0)
				(= maitreDIsReady 1)
				(= state 99)
				(= seconds 3)
			)
			(101
				(= waitingForMaitreD 1)
				(User canControl: 0)
				(= seconds 0)
				(Print 43 54)
				(Print 43 55 #at -1 130)
				(MDscript changeState: 1)
				(ego
					setScript: 0
					setLoop: 0
					setCel: 255
					setCycle: Beg self
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
				(aMaitreD setMotion: MoveTo 209 139 241 113 self)
				(= cycles 10)
			)
			(106
				(ego setMotion: MoveTo 209 139 241 113 self)
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
				(aTable show: setCycle: End self)
			)
			(113
				(aChair setCycle: End self)
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
				(addToPics add: aOldDoor doit:)
				(Print 43 61 #at -1 130 #draw)
				(NormalEgo)
				(= waitingForMaitreD 0)
			)
			(121
				(= waitingForMaitreD 1)
				(User canControl: 0)
				(= currentStatus 1000)
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
				(= currentStatus 1001)
			)
		)
	)
	
	(method (handleEvent event)
		(if
		(or (!= (event type?) evSAID) (event claimed?))
			(return)
		)
		(if (Said 'look<below/buffet')
			(Print 43 0)
			(Print 43 1 #at -1 130)
		)
		(if (Said 'look>')
			(if (Said '/appetizer,pate,buffet')
				(cond 
					((ego inRect: 30 130 80 176) (if knifeInRoom (Print 43 2) else (Print 43 3)))
					((== currentStatus 1010) (Print 43 4))
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
				((== knifeInRoom 0) (Print 43 18))
				((== maitreDIsReady 0) (Print 43 19))
				(else
					(Print 43 20)
					(Print 43 21)
					(if (!= currentEgoView 100)
						(Print 43 22)
					else
						(= talkedToMaitreD 1)
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
				((== currentStatus 1010) (self changeState: 121))
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
				((== knifeInRoom 0) (AlreadyTook))
				((not (ego inRect: 32 142 55 161)) (NotClose))
				(else
					(Print 43 33)
					(Print 43 34)
					(ego get: 17)
					(= knifeInRoom 0)
					(= gotKnife 1)
					(theGame changeScore: 3)
				)
			)
		)
		(if (Said 'bath')
			(cond 
				(
				(or (== currentStatus 1009) (== currentStatus 1010)) (Print 43 35))
				((!= currentEgoView 100) (Print 43 36))
				((and (== knifeInRoom 0) (== gotKnife 0)) (Print 43 18))
				((!= currentStatus 0) (NotNow))
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
						(= servedAtResortRestaurant 1)
						(theGame changeScore: 1)
					)
					(ego setScript: SITscript)
					(SITscript changeState: 1)
				)
			)
		)
		(if (Said 'new,(new<up),(get<up)')
			(cond 
				((== currentStatus 0) (YouAre))
				((== currentStatus 1009)
					(Ok)
					(ego setScript: SITscript)
					(SITscript changeState: 5)
				)
				((== currentStatus 1010) (Ok) (ego setScript: TSscript) (TSscript changeState: 3))
				(else (Print 43 39))
			)
		)
		(if
			(and
				(< state 101)
				(ego inRect: 170 155 194 179)
				(== maitreDIsReady 1)
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
					setCycle: End self
				)
			)
			(3
				(= currentStatus 1009)
				(= waitingForMaitreD 0)
				(= seconds (Random 2 6))
			)
			(4
				(ego setLoop: (Random 1 2))
				(if (== (ego cel?) 0)
					(ego setCycle: End)
				else
					(ego setCycle: Beg)
				)
				(-- state)
				(= seconds (Random 2 6))
			)
			(5
				(if seconds (= seconds 0))
				(ego
					setLoop: 0
					setCel: 255
					setCycle: Beg self
					setMotion: 0
				)
				(= waitingForMaitreD 1)
			)
			(6
				(NormalEgo 2)
				(= waitingForMaitreD 0)
			)
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
					setCycle: End self
				)
			)
			(2
				(= currentStatus 1010)
				(= waitingForMaitreD 0)
			)
			(3
				(ego
					setLoop: 0
					setCel: 255
					setCycle: Beg self
					setMotion: 0
				)
				(= waitingForMaitreD 1)
			)
			(4
				(NormalEgo 2)
				(= waitingForMaitreD 0)
			)
		)
	)
)

(instance MDscript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(1
				(= maitreDIsReady 0)
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
				(aRope setCycle: End)
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
				(= maitreDIsReady 1)
				(rm43Script cue:)
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

(instance aMaitreD of Act
	(properties
		y 166
		x 154
		view 434
		illegalBits $0000
	)
)

(instance aMan of Act
	(properties
		y 234
		x 188
		view 435
		loop 3
		illegalBits $0000
	)
)

(instance aWoman of Act
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
				(if (!= currentStatus 1009)
					(-- state)
					(= seconds (Random 2 4))
				else
					(= seconds 0)
					(= waitingForMaitreD 1)
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
				(= maitreDIsReady 0)
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
				(aRope setCycle: End)
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
				(aRope setCycle: Beg)
			)
			(10
				(aMaitreD setMotion: MoveTo 210 155 self)
			)
			(11
				(Print 43 56)
				(= waitingForMaitreD 0)
				(rm43Script cue:)
			)
		)
	)
)

(instance aCheese of PV
	(properties
		y 134
		x 22
		view 433
		priority 6
		signal $4000
	)
)

(instance aSalad of PV
	(properties
		y 121
		x 63
		view 433
		cel 1
		priority 6
		signal $4000
	)
)

(instance aFood of PV
	(properties
		y 163
		x 37
		view 433
		cel 2
		priority 13
		signal $4000
	)
)

(instance aT1 of PV
	(properties
		y 81
		x 116
		view 433
		loop 3
		cel 1
		priority 3
		signal $4000
	)
)

(instance aT2 of PV
	(properties
		y 119
		x 65
		view 433
		loop 3
		priority 7
		signal $4000
	)
)

(instance aT3 of PV
	(properties
		y 100
		x 178
		view 433
		loop 3
		cel 1
		priority 5
		signal $4000
	)
)

(instance aT4 of PV
	(properties
		y 100
		x 242
		view 433
		loop 3
		cel 1
		priority 5
		signal $4000
	)
)

(instance aT5 of PV
	(properties
		y 143
		x 168
		view 433
		loop 3
		priority 9
		signal $4000
	)
)

(instance aLchair of PV
	(properties
		y 171
		x 230
		view 433
		loop 3
		cel 2
		priority 13
		signal $4000
	)
)

(instance aWpicture of PV
	(properties
		y 77
		x 53
		view 433
		loop 2
		cel 1
		priority 4
		signal $4000
	)
)

(instance aEpicture of PV
	(properties
		y 55
		x 178
		view 433
		loop 2
		priority 2
		signal $4000
	)
)

(instance aMan1 of PV
	(properties
		y 73
		x 135
		view 435
		loop 4
		priority 4
		signal $4000
	)
)

(instance aWoman1 of PV
	(properties
		y 73
		x 99
		view 438
		loop 4
		priority 4
		signal $4000
	)
)

(instance aMan2 of PV
	(properties
		y 109
		x 87
		view 435
		loop 4
		priority 8
		signal $4000
	)
)

(instance aWoman2 of PV
	(properties
		y 110
		x 44
		view 438
		loop 4
		priority 8
		signal $4000
	)
)

(instance aMan3 of PV
	(properties
		y 92
		x 197
		view 435
		loop 4
		priority 6
		signal $4000
	)
)

(instance aWoman3 of PV
	(properties
		y 92
		x 161
		view 438
		loop 4
		priority 6
		signal $4000
	)
)

(instance aMan4 of PV
	(properties
		y 92
		x 261
		view 435
		loop 4
		priority 6
		signal $4000
	)
)

(instance aWoman4 of PV
	(properties
		y 92
		x 225
		view 438
		loop 4
		priority 6
		signal $4000
	)
)

(instance aMan5 of PV
	(properties
		y 133
		x 190
		view 435
		loop 4
		priority 10
		signal $4000
	)
)

(instance aWoman5 of PV
	(properties
		y 134
		x 151
		view 438
		loop 4
		priority 10
		signal $4000
	)
)

(instance aOldDoor of PV
	(properties
		y 63
		x 287
		view 433
		loop 1
		signal $4000
	)
)
