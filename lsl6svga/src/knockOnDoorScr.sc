;;; Sierra Script 1.0 - (do not remove this comment)
(script# 96)
(include sci.sh)
(use Main)
(use fileScr)
(use Sound)
(use Motion)
(use System)

(public
	knockOnDoorScr 0
)

(local
	theRegister
	local1 =  -1
	newSound
)
(instance knockOnDoorScr of Script
	(properties)
	
	(method (dispose)
		(proc79_12 38)
		(super dispose:)
		(DisposeScript 96)
	)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= theRegister register)
				(ego cycleSpeed: 1)
				(proc79_11 38)
				(= cycles 2)
			)
			(1
				(ego view: 901 loop: 7 cel: 0 setCycle: End self)
				((= newSound (Sound new:)) number: 38 loop: 1)
				(= register 2)
			)
			(2
				(newSound play:)
				(= ticks 10)
			)
			(3
				(ego setCycle: CT (- (ego cel?) 1) -1 self)
			)
			(4 (ego setCycle: End self))
			(5
				(newSound play:)
				(= ticks 10)
				(if (-- register) (= state (- state 3)))
			)
			(6
				(newSound number: 0 dispose:)
				(= newSound 0)
				(ego normalize: 900 8 1 cel: 3)
				(if theRegister (++ state))
				(= cycles 2)
			)
			(7
				(theGame changeScore: 1 211)
				(messager say: 2 4 0 (Random 1 28) self 205)
			)
			(8
				(if (not theRegister) (theGame handsOn:))
				(self dispose:)
			)
		)
	)
)
