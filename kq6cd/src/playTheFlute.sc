;;; Sierra Script 1.0 - (do not remove this comment)
(script# 85)
(include sci.sh)
(use Main)
(use Sound)
(use Motion)
(use System)

(public
	playTheFlute 0
)

(local
	egoCel
	local1
	local2
	local3
	local4
	local5
)
(instance playTheFlute of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame killSound: 1)
				(if (< (* (/ (ego scaleY?) 128) 100) 80)
					(= local5 1)
				)
				(if (theGame isHandsOn?)
					(theGame handsOff:)
					(= local4 1)
				)
				(= cycles 2)
			)
			(1
				(= egoCel (ego cel?))
				(= ticks 6)
			)
			(2
				(ego normal: 0 view: 920 cel: 0 cycleSpeed: 6)
				(cond 
					((< (ego heading?) 136)
						(= local3 0)
						(= local2 -2)
						(= local1 0)
						(ego setLoop: 0 posn: (ego x?) (+ (ego y?) 2))
					)
					((< (ego heading?) 260)
						(= local3 2)
						(if local5
							(= local2 0)
							(= local1 -3)
							(ego setLoop: 2 posn: (+ (ego x?) 3) (ego y?))
						else
							(= local1 0)
							(= local2 -1)
							(ego setLoop: 2 posn: (ego x?) (+ (ego y?) 1))
						)
					)
					(else
						(= local3 1)
						(= local1 -2)
						(= local2 -1)
						(ego setLoop: 1 posn: (+ (ego x?) 2) (+ (ego y?) 1))
					)
				)
				(= cycles 4)
			)
			(3
				(localMusic number: 942 setLoop: 1 play: self)
				(ego setCycle: Forward)
			)
			(4
				(ego
					reset: egoCel
					posn: (+ (ego x?) local1) (+ (ego y?) local2)
				)
				(= cycles 4)
			)
			(5
				(if local4
					(messager say: 1 31 0 1 self 0)
				else
					(= cycles 1)
				)
			)
			(6
				(theGame handsOn:)
				(localMusic stop: dispose:)
				(theGame killSound: 0)
				(self dispose:)
				(DisposeScript 85)
			)
		)
	)
)

(instance localMusic of Sound
	(properties)
)
