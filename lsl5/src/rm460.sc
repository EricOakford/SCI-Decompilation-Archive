;;; Sierra Script 1.0 - (do not remove this comment)
(script# 460)
(include game.sh)
(use Main)
(use LLRoom)
(use Talker)
(use ForCount)
(use LoadMany)
(use StopWalk)
(use Grooper)
(use Sound)
(use Motion)
(use User)
(use Actor)
(use System)

(public
	rm460 0
)

(local
	local0
)
(instance rm460 of LLRoom
	(properties
		picture 460
	)
	
	(method (init)
		(LoadMany PICTURE 470 465 1)
		(LoadMany VIEW
			460 461 462 470 471 551
			467 570 466 1465 1466
		)
		(LoadMany SOUND 470 464 461 462 463 465 466)
		(LoadMany FONT giantFont2)
		(quayle init:)
		(desmond init:)
		(larry init: setCycle: Walk)
		(bigg init:)
		(pattiChair init:)
		(patDaisChair init:)
		(desDaisChair init:)
		(marilyn init:)
		(roger init:)
		(humphrey init:)
		(sleeper init:)
		(ego
			init:
			normalize: 462
			get: iHooterShooter
			setCycle: 0
			setLoop: 4
			setCel: 0
			cycleSpeed: 5
			moveSpeed: 5
			posn: 131 127
			actions: pattiActions
		)
		(theMusic number: 460 flags: mNOPAUSE setLoop: -1 play:)
		(dinnerFX flags: 1 loop: -1 play:)
		(super init:)
		(SetFFRoom 480)
		(HandsOff)
		(curRoom setScript: sCartoon)
	)
)

(instance sCartoon of Script
	
	(method (doit)
		(super doit:)
		(if local0 (Palette PALCycle 243 255 -1))
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 2))
			(1
				(TimePrint 460 0 #dispose self #at -1 20)
			)
			(2 (= ticks 30))
			(3
				(TimePrint 460 1 #dispose self #at -1 185)
			)
			(4 (= ticks 30))
			(5
				(TimePrint 460 2 #dispose self #at -1 15 #width 280)
			)
			(6 (= ticks 30))
			(7
				(TimePrint 460 3 #dispose self #at -1 15 #width 280)
			)
			(8 (= ticks 30))
			(9
				(larry setMotion: MoveTo 300 157 self)
			)
			(10
				(TimePrint 460 4 #at -1 15 #width 280)
				(TimePrint 460 5 #at -1 185)
				(larry setMotion: MoveTo 247 157 self)
			)
			(11
				(dinnerFX stop:)
				(theMusic pause: 1)
				(theMusic2 number: 470 flags: mNOPAUSE setLoop: -1 play:)
				(Say ego 460 6 #at -1 20)
				(TimePrint 460 7 #title {Larry} #at -1 185)
				(= cycles 1)
			)
			(12
				(larry
					setLoop: sGroop
					cycleSpeed: 4
					moveSpeed: 4
					setStep: 5 3
					setCycle: StopWalk -1
					setMotion: MoveTo 168 142 self
				)
				(= cycles 4)
			)
			(13
				(ego
					setPri: 10
					setCycle: CycleTo (- (ego lastCel:) 1) 1 self
				)
			)
			(14
				(ego
					normalize:
					loop: 4
					posn: 129 130
					setPri: 10
					cycleSpeed: 4
					moveSpeed: 4
					setMotion: MoveTo 153 142
				)
			)
			(15
				(ego hide:)
				(pattiChair hide:)
				(desmond hide:)
				(patDaisChair hide:)
				(desDaisChair hide:)
				(quayle hide:)
				(bigg hide:)
				(larry hide:)
				(marilyn hide:)
				(roger hide:)
				(humphrey hide:)
				(sleeper hide:)
				(heart init:)
				(larryHead init:)
				(pattiHead init:)
				(pattiLeg init:)
				(curRoom drawPic: 470)
				(= cycles 2)
			)
			(16 (= local0 1) (= seconds 3))
			(17
				(pattiLeg setCel: (pattiLeg lastCel:))
				(pattiHead setCycle: ForwardCounter 4)
				(Say ego 460 8 #dispose self #at -1 185)
			)
			(18 (= ticks 30))
			(19
				(pattiHead setCycle: 0)
				(larryHead setCycle: ForwardCounter 5)
				(TimePrint 460 9 #title {Larry} #dispose self #at -1 185)
			)
			(20 (= ticks 30))
			(21
				(larryHead setCycle: 0)
				(pattiHead setCycle: ForwardCounter 5)
				(Say ego 460 10 #dispose self #at -1 185)
			)
			(22 (= ticks 30))
			(23
				(pattiHead setCycle: 0)
				(larryHead setCycle: ForwardCounter 5)
				(TimePrint 460 11 #title {Larry} #dispose self #at -1 185)
			)
			(24 (= ticks 30))
			(25
				(larryHead setCycle: 0)
				(pattiHead setCycle: ForwardCounter 3)
				(Say ego 460 12 #dispose self #at -1 185)
			)
			(26 (= ticks 30))
			(27
				(pattiHead setCycle: 0)
				(pattiLeg dispose:)
				(= seconds 3)
			)
			(28
				(heart dispose:)
				(larryHead dispose:)
				(pattiHead dispose:)
				(quayle show:)
				(bigg show:)
				(ego show: cycleSpeed: 6 moveSpeed: 6)
				(desmond
					show:
					cycleSpeed: 6
					moveSpeed: 6
					setLoop: 3
					setCel: 0
					posn: 238 132
				)
				(larry show: cycleSpeed: 6 moveSpeed: 6)
				(pattiChair show:)
				(desmondChair init:)
				(patDaisChair show:)
				(desDaisChair show:)
				(marilyn show:)
				(roger show:)
				(humphrey show:)
				(sleeper show:)
				(= local0 0)
				(curRoom drawPic: 460)
				(theMusic2 fade: 0 15 12 1 self)
			)
			(29
				(dinnerFX play:)
				(theMusic pause: 0)
				(= seconds 3)
			)
			(30
				(Say ego 460 13 #at -1 20)
				(larry setHeading: 90 self)
			)
			(31
				(desmond
					setLoop: 4
					setCycle: Walk
					setMotion: MoveTo 194 147 self
				)
			)
			(32
				(TimePrint 460 14 #title {Larry} #at -1 20)
				(TimePrint 460 15 #at -1 185 #title {Inspector Desmond})
				(desmond setMotion: MoveTo 165 147 self)
			)
			(33
				(larry setHeading: 135 self)
			)
			(34
				(larry setHeading: 270)
				(ego normalize: setMotion: MoveTo -10 142)
				(desmond setMotion: MoveTo -10 147)
				(= ticks 120)
			)
			(35
				(larry setStep: 3 2 setMotion: MoveTo -10 139 self)
			)
			(36 (= seconds 5))
			(37
				(ego
					view: 462
					setLoop: 5
					setCycle: Walk
					x: -10
					y: 87
					setPri: 3
					setMotion: MoveTo 102 87 self
				)
				(desDaisChair setPri: 4)
				(= ticks 90)
			)
			(38
				(desmond setScript: sDesmondSits)
			)
			(39
				(ego
					setLoop: 6
					setCel: 0
					setCycle: 0
					setMotion: MoveTo 103 95 self
				)
			)
			(40
				(ego
					setLoop: 1
					setCel: 0
					setPri: 5
					cycleSpeed: 12
					x: 101
					y: 71
				)
				(= cycles 1)
			)
			(41
				(larry
					view: 551
					loop: 0
					setPri: 4
					x: -10
					y: 82
					setCycle: Walk
					setMotion: MoveTo 26 82 self
				)
			)
			(42 (= ticks 60))
			(43
				(larry view: 461 setLoop: 6 setCel: 2)
				(= ticks 45)
			)
			(44
				(larry setCycle: CycleTo 1 1 self)
			)
			(45 (= ticks 45))
			(46
				(larry setCycle: CycleTo 2 -1 self)
			)
			(47
				((larry looper?) dispose:)
				(= ticks 45)
			)
			(48
				(larry view: 551 setLoop: 0 setLoop: -1 setCycle: Walk)
				(= cycles 1)
			)
			(49
				(TimePrint 460 16 #at 15 -1 #width 280 #title {Larry})
				(= seconds 2)
			)
			(50
				(patDaisChair
					setLoop: 0
					setCel: 2
					setMotion: MoveTo (+ (patDaisChair x?) 3) (patDaisChair y?)
				)
				(ego setMotion: MoveTo 105 72 self)
			)
			(51 (= cycles 3))
			(52
				(desDaisChair
					setLoop: 0
					setCel: 2
					setMotion: MoveTo (+ (desDaisChair x?) 4) (desDaisChair y?)
				)
				(desmond setMotion: MoveTo 81 71 self)
			)
			(53 (= cycles 5))
			(54
				(patDaisChair
					setMotion: MoveTo (+ (patDaisChair x?) 4) (patDaisChair y?)
				)
				(ego setMotion: MoveTo #dispose 72 self)
			)
			(55
				(patDaisChair stopUpd:)
				(= cycles 3)
			)
			(56
				(desDaisChair
					setMotion: MoveTo (+ (desDaisChair x?) 3) (desDaisChair y?)
				)
				(desmond setMotion: MoveTo 86 71 self)
			)
			(57
				(desDaisChair stopUpd:)
				(ego setScript: sTalkDesmond)
				(ssguy init: setCycle: Walk setMotion: MoveTo 49 81 self)
			)
			(58
				(ssguy setLoop: 1 cycleSpeed: 10 setCycle: CycleTo 5 1 self)
				(TimePrint 460 17)
			)
			(59
				(chairBack init:)
				(= cycles 1)
			)
			(60
				(ssguy setLoop: 1 setCycle: EndLoop self)
			)
			(61
				(ssguy
					setLoop: 2
					setCycle: Walk
					cycleSpeed: 6
					setPri: 0
					setMotion: MoveTo -50 81
				)
				(larry setMotion: MoveTo 64 82 self)
			)
			(62
				(larry setHeading: 180 self)
			)
			(63
				(larry
					loop: 2
					setCel: 0
					setCycle: 0
					setMotion: MoveTo 63 86 self
				)
			)
			(64
				(larry
					view: 461
					setLoop: 0
					setCel: 0
					setPri: 5
					cycleSpeed: 10
					x: 62
					y: 74
				)
				(= seconds 3)
			)
			(65
				(ssguy dispose:)
				(larry setCycle: EndLoop self)
			)
			(66
				(TimePrint 460 18 #at 15 -1 #width 280 #title {Larry})
				(= seconds 3)
			)
			(#at
				(TimePrint 460 19 #title {Mr. Vice President})
				(TimePrint 460 20 #title {Larry})
				(larry setCycle: BegLoop self)
			)
			(68 (= seconds 3))
			(69
				(larry setLoop: 1 setCycle: EndLoop self)
			)
			(70 (= seconds 3))
			(71 (larry setCycle: BegLoop self))
			(72
				(sTalkDesmond dispose:)
				(= seconds 3)
			)
			(73 (bigg setCycle: EndLoop self))
			(74
				(ego setLoop: 0 setCycle: EndLoop self)
			)
			(75
				(TimePrint 460 21 #at -1 185)
				(= cycles 1)
			)
			(76
				(ego hide:)
				(pattiChair hide:)
				(desmondChair hide:)
				(desmond hide:)
				(patDaisChair hide:)
				(desDaisChair hide:)
				(quayle hide:)
				(bigg hide:)
				(larry hide:)
				(marilyn hide:)
				(roger hide:)
				(humphrey hide:)
				(sleeper hide:)
				(chairBack hide:)
				(curRoom drawPic: 465)
				(dinnerFX stop:)
				(theMusic number: 465 play:)
				(= seconds 3)
			)
			(77
				(Say Mr__Bigg 460 22 #dispose 139 self)
			)
			(78
				(Say You 460 23 #dispose 139 self)
			)
			(79
				(Say Mr__Bigg 460 24 139 self)
			)
			(#width
				(Say Mr__Bigg 460 25 #dispose 139 self)
			)
			(81 (= seconds 3))
			(82
				(TimePrint 460 26 #at -1 15 #width 280)
				(TimePrint 460 27 #at -1 185)
				(= seconds 3)
			)
			(83
				(Say Mr__Bigg 460 28 #at -1 185 #dispose 139 self)
			)
			(84
				(Say You 460 29 #dispose 139 self)
			)
			(85
				(Say Mr__Bigg 460 30 #at -1 185 #dispose 139 self)
			)
			(86 (= seconds 3))
			(87
				(TimePrint 460 31 #at -1 15 #width 280)
				(TimePrint 460 32 #at -1 20)
				(= seconds 3)
			)
			(88
				(TimePrint 460 33 #at -1 185)
				(Say Mr__Bigg 460 34 #dispose 139 self)
			)
			(89
				(TimePrint 460 35 #at -1 185)
				(= seconds 3)
			)
			(90
				(Say Mr__Bigg 460 36 139 self)
			)
			(91
				(Say Mr__Bigg 460 37 #dispose 139 self)
			)
			(92
				(TimePrint 460 38 #at -1 15 #width 280 #dispose self)
			)
			(93
				(Say You 460 39 #dispose 139 self)
			)
			(94
				(Say Mr__Bigg 460 40 #at -1 185 #dispose 139 self)
			)
			(95 (= seconds 3))
			(96
				(TimePrint 460 41 #at -1 20)
				(= seconds 3)
			)
			(97
				(ego show:)
				(pattiChair show:)
				(desmondChair show:)
				(desmond show:)
				(patDaisChair show:)
				(desDaisChair show:)
				(quayle show:)
				(bigg show:)
				(larry show:)
				(marilyn show:)
				(roger show:)
				(humphrey show:)
				(sleeper show:)
				(chairBack show:)
				(curRoom drawPic: 460)
				(theMusic number: 463 play:)
				(= ticks 60)
			)
			(98
				(ego setCycle: BegLoop)
				(bigg setCycle: BegLoop self)
			)
			(99
				(larry setLoop: 2 setCycle: EndLoop self)
			)
			(100 (= seconds 2))
			(101
				(TimePrint 460 42 #title {Larry} #at -1 185 #width 280)
				(Say ego 460 43)
				(TimePrint 460 44 #title {Mr. Bigg})
				(Say ego 460 45 #at -1 185 #width 280)
				(Say ego 460 46 #at -1 185 #width 280)
				(TimePrint 460 47 #title {Mr. Bigg})
				(TimePrint 460 48)
				(bigg setLoop: 1 setPri: 3 setCycle: EndLoop self)
			)
			(102
				(theMusic number: 466 play:)
				(TimePrint 460 49 #title {Mr. Bigg})
				(TimePrint 460 50 #title {Larry})
				(= cycles 1)
			)
			(103
				(marilyn setCycle: CycleTo 8 1 self)
			)
			(104
				(pie init:)
				(marilyn setCycle: EndLoop self)
			)
			(105
				(larry
					setLoop: 3
					setCel: 0
					cycleSpeed: 10
					setCycle: CycleTo 2 1 self
				)
			)
			(106
				(quayle hide:)
				(pie setPri: 5)
				(larry setCycle: EndLoop self)
			)
			(107
				(marilyn setLoop: 0 setCel: 0 setCycle: EndLoop self)
				(pieInFace play:)
			)
			(108
				(HandsOn)
				(User canControl: 0)
				(= seconds 6)
			)
			(109
				(TimePrint 460 51)
				(= seconds 6)
			)
			(110
				(curRoom setScript: sCartoon2)
				(self dispose:)
			)
		)
	)
)

(instance sCartoon2 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(TimePrint 460 52)
				(ego
					setLoop: 7
					cycleSpeed: 5
					setCel: 0
					setPri: 4
					setCycle: EndLoop self
				)
			)
			(1 (= ticks 30))
			(2
				(fireBra play:)
				(ego
					setLoop: 2
					cycleSpeed: 6
					setCel: 0
					setCycle: CycleTo 2 1 self
				)
			)
			(3
				(bigg setLoop: 2 setCycle: EndLoop)
				(= cycles 5)
			)
			(4
				(fireBra play:)
				(ego setCycle: CycleTo 5 1 self)
			)
			(5
				(theMusic number: 461 setLoop: 1 play:)
				(ego
					setLoop: 3
					setCel: 0
					cycleSpeed: 10
					setCycle: EndLoop self
				)
			)
			(6 (= ticks 180))
			(7
				(bigg hide:)
				(desmond
					x: 140
					y: 74
					setPri: 0
					setLoop: 1
					setCel: 0
					cycleSpeed: 12
					setCycle: EndLoop self
				)
			)
			(8
				(desmond setLoop: 2 setCel: 0 setCycle: EndLoop self)
			)
			(9 (= ticks 180))
			(10
				(desmond
					cycleSpeed: 6
					setMotion: MoveTo (ego x?) 74 self
				)
			)
			(11
				(TimePrint 460 53 #title {Inspector Desmond})
				(TimePrint 460 54 #title {Inspector Desmond})
				(desmond cycleSpeed: 5 setMotion: MoveTo -50 74)
				(ego setMotion: MoveTo (ego x?) (+ (ego y?) 4) self)
			)
			(12
				(ego setLoop: 1 x: 108 y: 72 setPri: 5 setCel: 0)
				(theMusic fade:)
				(= ticks 240)
			)
			(13
				(theMusic number: 462 setVol: 127 setLoop: -1 play:)
				(larry setLoop: 4 setCel: 0 setCycle: EndLoop self)
			)
			(14
				(larry setLoop: 5 setCel: 0 setCycle: EndLoop self)
			)
			(15
				(pie setPri: 6)
				(larry setLoop: 0 setCel: 0 x: 62 y: 74)
				(quayle
					show:
					setLoop: 1
					setCel: 0
					x: 42
					y: 72
					setCycle: EndLoop self
				)
			)
			(16
				(TimePrint 460 55 #title {Larry})
				(quayle
					setLoop: 2
					setCel: 0
					posn: 38 71
					setCycle: EndLoop self
				)
			)
			(17 (= seconds 3))
			(18
				(larry setLoop: 1 setCel: 0 setCycle: EndLoop self)
			)
			(19
				(Say ego 460 56)
				(= ticks 90)
			)
			(20
				(quayle
					setLoop: 5
					setCel: 0
					posn: 42 72
					cycleSpeed: 11
					setCycle: EndLoop self
				)
			)
			(21 (= ticks 60))
			(22 (quayle setCycle: BegLoop self))
			(23
				(quayle
					setLoop: 3
					setCel: 0
					posn: 38 71
					cycleSpeed: 6
					setCycle: ForwardCounter 2 self
				)
			)
			(24
				(TimePrint 460 57 #at 15 -1 #width 280 #title {Mr. Vice President})
				(= cycles 1)
			)
			(25 (larry setCycle: BegLoop self))
			(26
				(larry setLoop: 0 setCycle: EndLoop self)
			)
			(27
				(TimePrint 460 58 #title {Larry})
				(= seconds 2)
			)
			(28
				(curRoom drawPic: 1 6)
				(DoDisplay 1 gLowlightColor_3 460 59)
				(= seconds 3)
			)
			(29
				(curRoom newRoom: 480)
				(self dispose:)
			)
		)
	)
)

(instance sDesmondSits of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(desmond
					setLoop: 5
					x: -10
					y: 84
					setPri: 4
					setMotion: MoveTo 76 84 self
				)
			)
			(1
				(desmond
					setLoop: 6
					setCel: 0
					setCycle: 0
					setMotion: MoveTo 76 95 self
				)
				(desDaisChair setPri: 1)
			)
			(2
				(desmond setLoop: 0 setCel: 0 x: 76 y: 70 stopUpd:)
				(= cycles 1)
			)
			(3 (self dispose:))
		)
	)
)

(instance sTalkDesmond of Script
	(properties)
	
	(method (dispose)
		(if (desmond cel?) (desmond setCycle: BegLoop))
		(if (ego cel?) (ego setCycle: BegLoop))
		(super dispose: &rest)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 4))
			(1
				(desmond setCycle: EndLoop self)
				(ego setCycle: EndLoop)
			)
			(2 (= seconds 4))
			(3
				(ego setCycle: BegLoop)
				(desmond setCycle: BegLoop self)
			)
			(4 (self changeState: 0))
		)
	)
)

(instance sRogerSleeps of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(roger setCel: 0 setCycle: ForwardCounter 1 self)
			)
			(1 (= seconds (Random 3 5)))
			(2 (self changeState: 0))
			(3 (self dispose:))
		)
	)
)

(instance sHumphreyDrinks of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(humphrey setCel: 0 setCycle: ForwardCounter 1 self)
			)
			(1 (= seconds (Random 4 6)))
			(2 (self changeState: 0))
			(3 (self dispose:))
		)
	)
)

(instance sSleeperSleeps of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(sleeper setCel: 0 setCycle: ForwardCounter 1 self)
			)
			(1 (= seconds 3))
			(2 (self changeState: 0))
			(3 (self dispose:))
		)
	)
)

(instance larry of Actor
	(properties
		x 320
		y 157
		description {Larry Laffer}
		view 550
		loop 1
		signal (| ignrAct stopUpdOn)
		cycleSpeed 5
		moveSpeed 5
	)
)

(instance sGroop of GradualLooper)

(instance pattiActions of Actions
	(properties)
	
	(method (doVerb theVerb theItem)
		(return
			(if
				(or
					(and (== theVerb verbUse) (== theItem iHooterShooter))
					(and (== theVerb verbDo) (Btst fWearingHooterShooter))
				)
				(SolvePuzzle 100)
				(curRoom setScript: sCartoon2)
				(return TRUE)
			else
				(return FALSE)
			)
		)
	)
)

(instance desmond of Actor
	(properties
		x 225
		y 127
		description {Desmond}
		view 464
		loop 3
		cel 1
		signal (| ignrAct stopUpdOn)
	)
)

(instance desmondChair of View
	(properties
		x 236
		y 127
		description {the chair}
		view 460
		cel 1
		signal (| ignrAct stopUpdOn)
	)
)

(instance pattiChair of View
	(properties
		x 120
		y 127
		description {the chair}
		view 460
		signal (| ignrAct stopUpdOn)
	)
)

(instance roger of Prop
	(properties
		x 139
		y 144
		description {Roger}
		view 460
		loop 1
		priority 14
		signal (| ignrAct fixPriOn)
		cycleSpeed 24
		detailLevel 1
	)
	
	(method (init)
		(super init:)
		(self setScript: sRogerSleeps)
	)
)

(instance humphrey of Prop
	(properties
		x 139
		y 101
		description {Humphrey}
		view 460
		loop 3
		priority 10
		signal (| ignrAct fixPriOn)
		cycleSpeed 12
		detailLevel 2
	)
	
	(method (init)
		(super init:)
		(self setScript: sHumphreyDrinks)
	)
)

(instance sleeper of Prop
	(properties
		x 224
		y 169
		description {the sleeping guest}
		view 460
		loop 2
		priority 14
		signal (| ignrAct fixPriOn)
		cycleSpeed 24
		detailLevel 3
	)
	
	(method (init)
		(super init:)
		(self setScript: sSleeperSleeps)
	)
)

(instance ssguy of Actor
	(properties
		x -50
		y 81
		description {the Secret Service man}
		view 466
		priority 4
		signal (| ignrAct fixPriOn stopUpdOn)
	)
)

(instance quayle of Prop
	(properties
		x 40
		y 74
		description {the Vice President}
		view 463
		priority 5
		signal (| fixPriOn stopUpdOn)
	)
	
	(method (init)
		(self setCycle: Forward)
		(super init: &rest)
	)
)

(instance bigg of Prop
	(properties
		x 140
		y 74
		description {Mr. Bigg}
		view 465
		priority 5
		signal (| ignrAct fixPriOn stopUpdOn)
		cycleSpeed 10
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(4
				(switch theItem
					(13
						(SolvePuzzle 100)
						(curRoom setScript: sCartoon2)
					)
					(else 
						(super doVerb: theVerb theItem &rest)
					)
				)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance pattiHead of Prop
	(properties
		x 158
		y 74
		view 471
		priority 5
		signal fixPriOn
	)
)

(instance larryHead of Prop
	(properties
		x 176
		y 84
		view 471
		loop 1
		priority 5
		signal fixPriOn
	)
)

(instance heart of Prop
	(properties
		x 176
		y 132
		view 470
		signal fixPriOn
	)
	
	(method (init)
		(super init:)
		(self setCycle: Forward)
	)
)

(instance pattiLeg of Prop
	(properties
		x 132
		y 149
		view 471
		loop 2
	)
)

(instance chairBack of Actor
	(properties
		x 61
		y 71
		description {the chair}
		view 460
		cel 3
		signal (| ignrAct stopUpdOn)
	)
)

(instance desDaisChair of Actor
	(properties
		x 75
		y 70
		description {the chair}
		view 460
		cel 2
		signal (| ignrAct stopUpdOn)
	)
)

(instance patDaisChair of Actor
	(properties
		x 101
		y 70
		description {the chair}
		view 460
		cel 2
		signal (| ignrAct stopUpdOn)
	)
)

(instance marilyn of Prop
	(properties
		x 14
		y 75
		description {Marilyn Quayle}
		view 467
		loop 1
		priority 5
		signal (| ignrAct fixPriOn stopUpdOn)
		cycleSpeed 10
	)
)

(instance pie of View
	(properties
		x 37
		y 74
		description {the pie}
		view 463
		loop 4
		priority 6
		signal (| ignrAct fixPriOn stopUpdOn)
	)
)

(instance dinnerFX of Sound
	(properties
		number 467
	)
)

(instance pieInFace of Sound
	(properties
		number 464
		priority 15
	)
)

(instance fireBra of Sound
	(properties
		number 434
	)
)

(instance Mr__Bigg of Talker
	(properties
		x 20
		y 185
		nsTop 60
		nsLeft 150
		view 1466
		loop 3
		talkWidth 280
		name "Mr. Bigg"
	)
	
	(method (init)
		(= mouth biggMouth)
		(super init: &rest)
	)
)

(instance biggMouth of Prop
	(properties
		nsTop 8
		nsLeft 6
		view 1466
		cycleSpeed 5
	)
)

(instance You of Talker
	(properties
		x 20
		y 5
		nsTop 70
		nsLeft 100
		view 1465
		loop 3
		talkWidth 160
	)
	
	(method (init)
		(= mouth pattiMouth)
		(= eyes pattiEyes)
		(super init: &rest)
	)
)

(instance pattiMouth of Prop
	(properties
		nsTop 9
		nsLeft 4
		view 1465
		cycleSpeed 5
	)
)

(instance pattiEyes of Prop
	(properties
		nsTop 1
		nsLeft 8
		view 1465
		loop 2
		cycleSpeed 5
	)
)
