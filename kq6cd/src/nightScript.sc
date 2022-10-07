;;; Sierra Script 1.0 - (do not remove this comment)
(script# 93)
(include sci.sh)
(use Main)
(use LoadMany)
(use Sound)
(use Motion)
(use System)

(public
	nightScript 0
)

(local
	local0
	egoCel
)
(instance nightScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff: killSound: 1)
				(localMusic4 number: 930 loop: 1 play:)
				(= egoCel (ego cel?))
				(ego
					normal: 0
					view: 883
					ignoreActors: 1
					illegalBits: 0
					ignoreHorizon: 1
					cycleSpeed: 10
				)
				(if (OneOf (ego cel?) 0 2 4 6)
					(= local0 1)
					(ego setLoop: 1)
				else
					(ego setLoop: 0)
				)
				(ego setCycle: EndLoop self)
			)
			(1
				(messager say: 1 37 0 0 self 0)
			)
			(2
				(localMusic4 number: 931 loop: 1 play: self)
				(if local0
					(ego setLoop: 7 setCycle: Forward)
				else
					(ego setLoop: 6 setCycle: Forward)
				)
			)
			(3
				(theGame killSound: 0)
				(= cycles 2)
			)
			(4
				(theGame handsOn:)
				(localMusic4 stop: dispose:)
				(ego reset: egoCel)
				(= cycles 1)
			)
			(5
				(self dispose:)
				(LoadMany 0 93)
			)
		)
	)
)

(instance localMusic4 of Sound
	(properties)
)
