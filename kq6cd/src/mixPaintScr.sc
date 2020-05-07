;;; Sierra Script 1.0 - (do not remove this comment)
(script# MIXPAINT) ;915
(include game.sh) (include "0.shm")
(use Main)
(use Kq6Procs)
(use Motion)
(use System)

(public
	mixPaintScr 0
)

(instance mixPaintScr of Script
	(properties)
	
	(method (dispose)
		(super dispose:)
		(= register 0)
		(DisposeScript MIXPAINT)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(inventory hide:)
				(= cycles 2)
			)
			(1 (ego setHeading: 180 self))
			(2
				(ego setSpeed: 6 normal: 0 view: 915 setLoop: 0)
				(= seconds 3)
			)
			(3
				(messager say: N_DOIT NULL C_PAINT_SPELL 1 self 0)
			)
			(4
				(ego setLoop: 1 setCycle: Forward)
				(= seconds 2)
			)
			(5
				(messager say: N_DOIT NULL C_PAINT_SPELL 2 self 0)
			)
			(6
				(ego setLoop: 2 setCycle: Forward)
				(= seconds 2)
			)
			(7
				(ego setLoop: 3 setCycle: Forward)
				(= seconds 2)
			)
			(8
				(ego setLoop: 4 setCycle: Forward)
				(= seconds 2)
			)
			(9
				(ego setLoop: 5 setCycle: Forward)
				(= seconds 2)
			)
			(10
				(soundFx2 loop: 1 number: 946 play:)
				(messager say: N_DOIT NULL C_PAINT_SPELL 3 self 0)
			)
			(11
				(ego reset: 2)
				(= cycles 2)
			)
			(12
				(ego put: iFeather)
				(theGame givePoints: 1)
				(messager say: N_DOIT NULL C_PAINT_SPELL 4 self 0)
			)
			(13
				(Bset fMadeMagicPaint)
				(self dispose:)
				(theGame handsOn:)
			)
		)
	)
)
