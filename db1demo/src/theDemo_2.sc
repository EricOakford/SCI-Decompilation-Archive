;;; Sierra Script 1.0 - (do not remove this comment)
(script# 2)
(include game.sh)
(use Main)
(use Procs)
(use LoadMany)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	theDemo 0
)

(local
	local0
	saveBits
	saveBits2
	saveBits3
	saveBits4
	[local6 5]
)
(instance theDemo of Room
	(properties
		picture 500
	)
	
	(method (init &tmp temp0)
		(Load SCRIPT OSC)
		(LoadMany VIEW 500)
		(electrons init:)
		(drip init:)
		(lightBulb init:)
		(clock init:)
		(eyeBalls init:)
		(poles init:)
		(windupKey init:)
		(super init:)
		(theIconBar disable:)
		(HandsOff)
		(theGame setCursor: 50)
		(self setScript: demo)
	)
)

(instance demo of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(electrons init: show: setCycle: Forward)
				(lightBulb init: show: setCycle: Forward)
				(clock init: show: setCycle: Forward)
				(eyeBalls init: show: setCycle: Forward)
				(poles init: show: setCycle: Forward)
				(windupKey init: show: setCycle: Forward)
				(= ticks 1)
			)
			(1
				(curRoom overlay: 520 WIPELEFT)
				(= ticks 200)
			)
			(2
				(= saveBits
					(DoDisplay {From SIERRA ON-LINE...} 67 80 130 70 320)
				)
				(= ticks 60)
			)
			(3
				(= saveBits4 (DoDisplay {It's waiting for you!} 67 80 158))
				(= ticks 180)
			)
			(4 (cSound fade: 0 10 5 1 self))
			(5 (= ticks 50))
			(6
				(DoDisplay saveBits)
				(DoDisplay saveBits4)
				(curRoom newRoom: 1 8)
			)
		)
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
		signal fixPriOn
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
