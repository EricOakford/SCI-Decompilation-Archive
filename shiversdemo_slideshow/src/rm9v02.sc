;;; Sierra Script 1.0 - (do not remove this comment)
(script# 997)
(include sci.sh)
(use Main)
(use Procs)
(use Motion)
(use System)

(public
	rm9v02 0
)

(instance rm9v02 of ShiversRoom
	(properties
		picture 2150
	)
	
	(method (init)
		(Purge 2500)
		(hsArea init:)
		(super init: &rest)
		(self setScript: sRunDemo)
	)
	
	(method (dispose)
		(sRunDemo dispose:)
		(super dispose: &rest)
	)
)

(instance hsArea of HotSpot
	(properties
		nsLeft -27
		nsTop -7
		nsRight 293
		nsBottom 193
	)
	
	(method (init)
		(super init: &rest)
		(mouseDownHandler delete: self)
		(mouseDownHandler addToFront: self)
	)
	
	(method (dispose)
		(mouseDownHandler delete: self)
		(super dispose: &rest)
	)
	
	(method (doVerb)
		(sounds stop: 7112)
		(curRoom newRoom: 910)
	)
)

(instance pMyProp1 of ShiversProp
	(properties
		priority 100
		fixPriority 1
		view 6030
		cycleSpeed 2
	)
	
	(method (init)
		(super init: &rest)
	)
)

(instance pMyProp2 of ShiversProp
	(properties
		priority 100
		fixPriority 1
		view 7110
		loop 5
	)
	
	(method (init)
		(super init: &rest)
	)
)

(instance pMyProp3 of ShiversProp
	(properties
		priority 100
		fixPriority 1
		view 7110
		loop 7
	)
	
	(method (init)
		(super init: &rest)
	)
)

(instance pMyProp4 of ShiversProp
	(properties
		x 138
		y 110
		priority 100
		fixPriority 1
		view -13476
		loop 7
	)
	
	(method (init)
		(super init: &rest)
	)
)

(instance sRunDemo of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(proc951_7 7112)
				(proc951_9 7112)
				(sounds play: 7112 -1 0 0)
				(sounds fade: 7112 100 1 30 0 0)
				(curRoom drawPic: 2050)
				(= seconds 1)
			)
			(1
				(curRoom drawPic: 2170)
				(= seconds 1)
			)
			(2
				(curRoom drawPic: 2190)
				(= seconds 1)
			)
			(3
				(curRoom drawPic: 2210)
				(= seconds 1)
			)
			(4
				(curRoom drawPic: 2230)
				(= seconds 1)
			)
			(5
				(curRoom drawPic: 3020)
				(= seconds 1)
			)
			(6
				(curRoom drawPic: 3070)
				(= seconds 1)
			)
			(7
				(curRoom drawPic: 3110)
				(= seconds 1)
			)
			(8
				(curRoom drawPic: 3220)
				(= seconds 1)
			)
			(9
				(curRoom drawPic: 3260)
				(= seconds 1)
			)
			(10
				(curRoom drawPic: 3300)
				(= seconds 1)
			)
			(11
				(curRoom drawPic: 6060)
				(= seconds 1)
			)
			(12
				(curRoom drawPic: 6100)
				(= seconds 1)
			)
			(13
				(curRoom drawPic: 6111)
				(= seconds 1)
			)
			(14
				(curRoom drawPic: 6030)
				(pMyProp1 view: 6030 loop: 0 init:)
				(= seconds 1)
			)
			(15
				(pMyProp1 cel: (pMyProp1 lastCel:) show:)
				(= seconds 1)
			)
			(16
				(pMyProp1 hide:)
				(curRoom drawPic: 9020)
				(= seconds 1)
			)
			(17
				(curRoom drawPic: 9060)
				(= seconds 1)
			)
			(18
				(curRoom drawPic: 9140)
				(= seconds 1)
			)
			(19
				(curRoom drawPic: 9540)
				(= seconds 1)
			)
			(20
				(curRoom drawPic: 11500)
				(= seconds 1)
			)
			(21
				(curRoom drawPic: 11080)
				(= seconds 1)
			)
			(22
				(curRoom drawPic: 11220)
				(= seconds 1)
			)
			(23
				(curRoom drawPic: 19040)
				(= seconds 1)
			)
			(24
				(curRoom drawPic: 19240)
				(= seconds 1)
			)
			(25
				(curRoom drawPic: 19210)
				(= seconds 1)
			)
			(26
				(curRoom drawPic: 19130)
				(= seconds 1)
			)
			(27
				(curRoom drawPic: 19180)
				(= seconds 1)
			)
			(28
				(curRoom drawPic: 12020)
				(= seconds 1)
			)
			(29
				(curRoom drawPic: 12110)
				(= seconds 1)
			)
			(30
				(curRoom drawPic: 12240)
				(= seconds 1)
			)
			(31
				(curRoom drawPic: 12370)
				(= seconds 1)
			)
			(32
				(curRoom drawPic: 12050)
				(= seconds 1)
			)
			(33
				(curRoom drawPic: 16000)
				(= seconds 1)
			)
			(34
				(curRoom drawPic: 16700)
				(= seconds 1)
			)
			(35
				(curRoom drawPic: 16340)
				(= seconds 1)
			)
			(36
				(curRoom drawPic: 18040)
				(= seconds 1)
			)
			(37
				(curRoom drawPic: 18060)
				(= seconds 1)
			)
			(38
				(curRoom drawPic: 18220)
				(= seconds 1)
			)
			(39
				(curRoom drawPic: 17100)
				(= seconds 1)
			)
			(40
				(curRoom drawPic: 17120)
				(= seconds 1)
			)
			(41
				(curRoom drawPic: 25040)
				(= seconds 1)
			)
			(42
				(curRoom drawPic: 14010)
				(= seconds 1)
			)
			(43
				(curRoom drawPic: 15060)
				(= seconds 1)
			)
			(44
				(curRoom drawPic: 15080)
				(= seconds 1)
			)
			(45
				(curRoom drawPic: 15140)
				(= seconds 1)
			)
			(46
				(curRoom drawPic: 8550)
				(= seconds 1)
			)
			(47
				(curRoom drawPic: 9540)
				(= seconds 1)
			)
			(48
				(curRoom drawPic: 8440)
				(= seconds 1)
			)
			(49
				(curRoom drawPic: 21020)
				(= seconds 1)
			)
			(50
				(curRoom drawPic: 21030)
				(= seconds 1)
			)
			(51
				(curRoom drawPic: 21060)
				(= seconds 1)
			)
			(52
				(curRoom drawPic: 21340)
				(= seconds 1)
			)
			(53
				(curRoom drawPic: 21290)
				(= seconds 1)
			)
			(54
				(curRoom drawPic: 22100)
				(= seconds 1)
			)
			(55
				(curRoom drawPic: 22040)
				(= seconds 1)
			)
			(56
				(curRoom drawPic: 22060)
				(= seconds 1)
			)
			(57
				(curRoom drawPic: 22240)
				(= seconds 1)
			)
			(58
				(curRoom drawPic: 22180)
				(= seconds 1)
			)
			(59
				(curRoom drawPic: 9290)
				(= seconds 1)
			)
			(60
				(curRoom drawPic: 9320)
				(= seconds 1)
			)
			(61
				(curRoom drawPic: 9390)
				(= seconds 1)
			)
			(62
				(curRoom drawPic: 9520)
				(= seconds 1)
			)
			(63
				(curRoom drawPic: 9480)
				(= seconds 1)
			)
			(64
				(curRoom drawPic: 9200)
				(= seconds 1)
			)
			(65
				(curRoom drawPic: 1021)
				(= seconds 1)
			)
			(66
				(curRoom drawPic: 1000)
				(= seconds 1)
			)
			(67
				(pMyProp1 view: 1017 loop: 0 cel: 0 show:)
				(= seconds 1)
			)
			(68
				(pMyProp1 hide:)
				(curRoom drawPic: 1521)
				(= seconds 1)
			)
			(69
				(curRoom drawPic: 1441)
				(= seconds 1)
			)
			(70
				(curRoom drawPic: 1380)
				(= seconds 1)
			)
			(71
				(curRoom drawPic: 1342)
				(= seconds 1)
			)
			(72
				(curRoom drawPic: 1212)
				(= seconds 1)
			)
			(73
				(curRoom drawPic: 4190)
				(= seconds 1)
			)
			(74
				(pMyProp1 view: 4193 loop: 0 cel: 0 show:)
				(= seconds 1)
			)
			(75
				(pMyProp1 cel: (pMyProp1 lastCel:))
				(= seconds 1)
			)
			(76
				(pMyProp1 hide:)
				(curRoom drawPic: 5010)
				(= seconds 1)
			)
			(77
				(curRoom drawPic: 6140)
				(= seconds 1)
			)
			(78
				(curRoom drawPic: 6200)
				(= seconds 1)
			)
			(79
				(curRoom drawPic: 6220)
				(= seconds 1)
			)
			(80
				(curRoom drawPic: 6221)
				(pMyProp1 view: 6221 loop: 0 cel: 0 show:)
				(= seconds 2)
			)
			(81
				(pMyProp1 hide:)
				(curRoom drawPic: 6070)
				(= seconds 1)
			)
			(82
				(curRoom drawPic: 6280)
				(= seconds 1)
			)
			(83
				(sounds setVol: 7112 40)
				(sounds play: 20605 0 85 0)
				(pMyProp4 view: -13476 cel: 0 init:)
				(pMyProp4 show:)
				(self cue:)
			)
			(84
				(pMyProp4 setCycle: End self)
			)
			(85
				(pMyProp4 view: -13472 cel: (pMyProp4 lastCel:))
				(self cue:)
			)
			(86
				(pMyProp4 setCycle: Beg self)
			)
			(87
				(sounds setVol: 7112 100)
				(pMyProp4 hide:)
				(self cue:)
			)
			(88
				(curRoom drawPic: 6160)
				(= seconds 1)
			)
			(89
				(curRoom drawPic: 7050)
				(= seconds 1)
			)
			(90
				(curRoom drawPic: 7111)
				(pMyProp1 view: 7110 loop: 2)
				(pMyProp1 cel: (pMyProp1 lastCel:) show:)
				(pMyProp2 view: 7110 loop: 5 init:)
				(pMyProp2 cel: (pMyProp2 lastCel:) show:)
				(pMyProp3 view: 7110 loop: 7 init:)
				(pMyProp3 cel: (pMyProp3 lastCel:) show:)
				(= seconds 2)
			)
			(91
				(pMyProp1 hide:)
				(pMyProp2 hide:)
				(pMyProp3 hide:)
				(curRoom drawPic: 7080)
				(= ticks 100)
			)
			(92
				(curRoom drawPic: 29270)
				(= seconds 1)
			)
			(93
				(curRoom drawPic: 29260)
				(= seconds 1)
			)
			(94
				(curRoom drawPic: 29020)
				(= seconds 1)
			)
			(95
				(curRoom drawPic: 29040)
				(= seconds 1)
			)
			(96
				(curRoom drawPic: 29160)
				(= seconds 1)
			)
			(97
				(curRoom drawPic: 29250)
				(= seconds 1)
			)
			(98
				(curRoom drawPic: 20101)
				(= seconds 1)
			)
			(99
				(curRoom drawPic: 20102)
				(= seconds 1)
			)
			(100
				(curRoom drawPic: 20103)
				(= seconds 1)
			)
			(101
				(curRoom drawPic: 20104)
				(= seconds 1)
			)
			(102
				(curRoom drawPic: 20105)
				(= seconds 1)
			)
			(103
				(curRoom drawPic: 20106)
				(= seconds 1)
			)
			(104
				(= state -1)
				(sounds fade: 7112 0 1 60 1 0)
				(curRoom drawPic: 921)
				(= seconds 15)
			)
		)
	)
)
