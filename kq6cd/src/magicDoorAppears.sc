;;; Sierra Script 1.0 - (do not remove this comment)
(script# 711)
(include sci.sh)
(use Main)
(use Motion)
(use Actor)
(use System)

(public
	magicDoorAppears 0
)

(instance magicDoorAppears of Script
	(properties)
	
	(method (dispose)
		(super dispose: &rest)
		(DisposeScript 711)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego posn: 36 156 reset: 0 hide:)
				(magicDoor init:)
				(= cycles 2)
			)
			(1
				(theGlobalSound number: 231 setLoop: 1 play:)
				(magicDoor cycleSpeed: 8 setCycle: CycleTo 6 1 self)
			)
			(2
				(ego show:)
				(magicDoor cel: 7 setCycle: EndLoop self)
			)
			(3
				(magicDoor dispose:)
				(messager say: 9 0 14 0 self)
			)
			(4 (= cycles 3))
			(5
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance magicDoor of Prop
	(properties
		x 27
		y 131
		view 714
		priority 10
		signal $0010
	)
)
