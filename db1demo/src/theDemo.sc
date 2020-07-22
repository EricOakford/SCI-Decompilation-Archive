;;; Sierra Script 1.0 - (do not remove this comment)
(script# 1)
(include game.sh)
(use Main)
(use Procs)
(use Osc)
(use LoadMany)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	theDemo 0
)

(local
	local0
	local1
	local2
	local3
	[local4 7]
)
(instance theDemo of Room
	(properties
		picture 510
		style FADEOUT
	)
	
	(method (init &tmp temp0)
		(Load SCRIPT OSC)
		(LoadMany PICTURE 100 500 510 520)
		(LoadMany VIEW 501 500)
		(LoadMany SOUND 100 101 102 103)
		(lightning init: hide:)
		(bridgeZap init: hide:)
		(bridgeZap2 init: hide:)
		(zapCastle init:)
		(zapLeft init:)
		(zapRight init:)
		(electrons init: hide:)
		(drip init: hide:)
		(lightBulb init: hide:)
		(clock init: hide:)
		(eyeBalls init: hide:)
		(poles init: hide:)
		(windupKey init: hide:)
		(telescope init: hide:)
		(frontDoor init: hide:)
		(stone1 init: hide:)
		(stone2 init: hide:)
		(stone3 init: hide:)
		(stone4 init: hide:)
		(stone5 init: hide:)
		(stone6 init: hide:)
		(leftLamp init: hide:)
		(rightLamp init: hide:)
		(leftFlamingo init: hide:)
		(rightFlamingo init: hide:)
		(super init:)
		(theIconBar disable:)
		(HandsOff)
		(theGame setCursor: 50 TRUE 1000 1000)
		(self setScript: demo1)
	)
)

(instance demo1 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(curRoom drawPic: 510 DISSOLVE)
				(cSound setPri: 12 number: 100 loop: -1 playBed:)
				(= ticks 1)
			)
			(1 (= ticks 80))
			(2
				(zapCastle setCycle: EndLoop self)
			)
			(3 (= ticks 40))
			(4 (zapLeft setCycle: EndLoop self))
			(5 (= ticks 30))
			(6
				(zapRight setCycle: EndLoop self)
			)
			(7 (= ticks 30))
			(8
				(zapRight setCycle: BegLoop)
				(zapLeft setCycle: BegLoop)
				(= ticks 50)
			)
			(9 (zapLeft setCycle: EndLoop self))
			(10 (= ticks 40))
			(11
				(zapRight setCycle: EndLoop self)
			)
			(12 (= ticks 30))
			(13
				(zapRight setCycle: BegLoop)
				(zapLeft setCycle: BegLoop)
				(zapCastle setCycle: BegLoop)
				(= ticks 50)
			)
			(14
				(zapLeft setCycle: EndLoop)
				(zapRight setCycle: EndLoop)
				(= ticks 40)
			)
			(15
				(zapLeft setCycle: BegLoop)
				(zapRight setCycle: BegLoop)
				(= ticks 30)
			)
			(16
				(zapLeft setCycle: EndLoop)
				(zapCastle setCycle: EndLoop)
				(zapRight setCycle: EndLoop)
				(= ticks 40)
			)
			(17
				(zapRight setCycle: BegLoop)
				(zapLeft setCycle: BegLoop)
				(= ticks 50)
			)
			(18
				(zapRight setCycle: EndLoop)
				(zapLeft setCycle: EndLoop)
				(zapCastle setCycle: BegLoop)
				(= ticks 10)
			)
			(19
				(zapLeft setCycle: BegLoop)
				(zapCastle setCycle: EndLoop)
				(zapRight setCycle: BegLoop)
				(= ticks 10)
			)
			(20
				(zapRight setCycle: EndLoop)
				(zapLeft setCycle: EndLoop)
				(zapCastle setCycle: BegLoop)
				(= ticks 10)
			)
			(21 (curRoom setScript: demo2))
		)
	)
)

(instance demo2 of Script
	(properties)
	
	(method (doit)
		(super doit: &rest)
		(if (and local3 (== (cSound prevSignal?) 7))
			(= local3 0)
			(self cue:)
		)
	)
	
	(method (dispose)
		(telescope setCycle: 0)
		(DisposeScript 939)
		(super dispose:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= local3 1))
			(1 (= ticks 25))
			(2
				(lightning show: setCycle: EndLoop)
				(= ticks 3)
			)
			(3
				(lightning hide: setCel: 0)
				(= ticks 1)
			)
			(4
				(lightning show: setCycle: EndLoop self)
			)
			(5
				(lightning hide: setCel: 0)
				(= ticks 1)
			)
			(6
				(bridgeZap show: setCycle: EndLoop self)
			)
			(7
				(bridgeZap2 show:)
				(bridgeZap show: setCycle: CycleTo 2 -1 self)
			)
			(8
				(curRoom drawPic: 500 DISSOLVE)
				(bridgeZap hide:)
				(bridgeZap2 hide:)
				(electrons show: setCycle: Forward)
				(drip show: setCycle: Forward)
				(lightBulb show: setCycle: Forward)
				(clock show: setCycle: Forward)
				(eyeBalls show: setCycle: Forward)
				(poles show: setCycle: Forward)
				(windupKey show: setCycle: Forward)
				(telescope show: setCycle: Oscillate)
				(= seconds 10)
			)
			(9 (drip setCycle: EndLoop self))
			(10 (drip hide:) (= ticks 10))
			(11
				(curRoom overlay: 520 2)
				(= seconds 7)
			)
			(12 (curRoom setScript: demo3))
		)
	)
)

(instance demo3 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(electrons dispose:)
				(drip dispose:)
				(lightBulb dispose:)
				(clock dispose:)
				(eyeBalls dispose:)
				(poles dispose:)
				(windupKey dispose:)
				(telescope dispose:)
				(curRoom drawPic: 100 8)
				(frontDoor show:)
				(stone1 show:)
				(stone2 show:)
				(stone3 show:)
				(stone4 show:)
				(stone5 show:)
				(stone6 show:)
				(leftLamp show:)
				(rightLamp show:)
				(leftFlamingo show:)
				(rightFlamingo show:)
				(= cycles 12)
			)
			(1
				(= local1
					(DoDisplay
						{I wonder if anybody is home?}
						67
						10
						15
						70
						320
						28
						12
					)
				)
				(= seconds 4)
			)
			(2
				(DoDisplay local1)
				(= cycles 1)
			)
			(3
				(stone1 setCel: 1)
				(= cycles 2)
			)
			(4
				(stone1 setCel: 0)
				(= cycles 2)
			)
			(5
				(stone2 setCel: 1)
				(= cycles 2)
			)
			(6
				(stone2 setCel: 0)
				(= cycles 2)
			)
			(7
				(stone3 setCel: 1)
				(= cycles 2)
			)
			(8
				(stone3 setCel: 0)
				(= cycles 2)
			)
			(9
				(stone4 setCel: 1)
				(= cycles 2)
			)
			(10
				(stone4 setCel: 0)
				(= cycles 2)
			)
			(11
				(stone5 setCel: 1)
				(= cycles 2)
			)
			(12
				(stone5 setCel: 0)
				(= cycles 2)
			)
			(13
				(stone6 setCel: 1)
				(= cycles 2)
			)
			(14
				(stone6 setCel: 0)
				(= cycles 2)
			)
			(15
				(leftLamp setCel: 1)
				(= cycles 2)
			)
			(16
				(leftLamp setCel: 0)
				(= cycles 2)
			)
			(17
				(rightLamp setCel: 1)
				(= cycles 2)
			)
			(18
				(rightLamp setCel: 0)
				(= cycles 2)
			)
			(19
				(leftFlamingo setCycle: EndLoop self)
			)
			(20
				(leftFlamingo setCel: 0)
				(= cycles 2)
			)
			(21
				(rightFlamingo setCycle: EndLoop self)
			)
			(22
				(rightFlamingo setCel: 0)
				(= cycles 2)
			)
			(23
				(frontDoor setCycle: EndLoop self)
			)
			(24 (curRoom newRoom: 200 8))
		)
	)
)

(instance lightning of Prop
	(properties
		x 133
		view 501
		priority 15
		signal $0010
	)
)

(instance bridgeZap of Prop
	(properties
		x 116
		y 93
		view 501
		loop 1
		priority 14
		signal $0010
		cycleSpeed 12
	)
)

(instance bridgeZap2 of Prop
	(properties
		x 116
		y 93
		view 501
		loop 1
		cel 3
		priority 14
		signal $0010
		cycleSpeed 12
	)
)

(instance electrons of Prop
	(properties
		x 169
		y 27
		view 500
	)
)

(instance drip of Prop
	(properties
		x 137
		y 110
		view 500
		loop 1
	)
)

(instance lightBulb of Prop
	(properties
		x 295
		y 34
		view 500
		loop 2
	)
)

(instance clock of Prop
	(properties
		x 261
		y 83
		view 500
		loop 3
	)
)

(instance eyeBalls of Prop
	(properties
		x 203
		y 189
		view 500
		loop 4
		cycleSpeed 18
	)
)

(instance poles of Prop
	(properties
		x 55
		y 100
		view 500
		loop 5
		priority 14
		signal $0010
	)
)

(instance windupKey of Prop
	(properties
		x 245
		y 18
		view 500
		loop 6
	)
)

(instance telescope of Actor
	(properties
		x 201
		y 4
		view 500
		loop 7
		cycleSpeed 12
	)
)

(instance frontDoor of Prop
	(properties
		x 158
		y 64
		view 100
		cycleSpeed 12
	)
)

(instance leftLamp of Prop
	(properties
		x 36
		y 64
		view 100
		loop 1
	)
)

(instance rightLamp of Prop
	(properties
		x 282
		y 65
		view 100
		loop 2
	)
)

(instance leftFlamingo of Prop
	(properties
		x 22
		y 125
		view 100
		loop 3
		cycleSpeed 18
	)
)

(instance rightFlamingo of Prop
	(properties
		x 300
		y 125
		view 100
		loop 4
		cycleSpeed 18
	)
)

(instance stone1 of Prop
	(properties
		x 98
		y 39
		view 105
	)
)

(instance stone2 of Prop
	(properties
		x 127
		y 39
		view 105
		loop 1
		signal $4000
	)
)

(instance stone3 of Prop
	(properties
		x 146
		y 33
		view 105
		loop 2
	)
)

(instance stone4 of Prop
	(properties
		x 171
		y 33
		view 105
		loop 3
		signal $4000
	)
)

(instance stone5 of Prop
	(properties
		x 190
		y 40
		view 105
		loop 4
		signal $4000
	)
)

(instance stone6 of Prop
	(properties
		x 220
		y 40
		view 105
		loop 5
	)
)

(instance zapCastle of Prop
	(properties
		x 201
		view 505
		cycleSpeed 24
	)
)

(instance zapRight of Prop
	(properties
		x 306
		y 31
		view 505
		loop 1
		cycleSpeed 18
	)
)

(instance zapLeft of Prop
	(properties
		x 98
		view 505
		loop 2
		cycleSpeed 18
	)
)

(instance thunder of Sound
	(properties
		number 101
		priority 15
	)
)
