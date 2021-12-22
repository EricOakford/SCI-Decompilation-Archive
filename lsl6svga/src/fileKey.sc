;;; Sierra Script 1.0 - (do not remove this comment)
(script# 77)
(include sci.sh)
(use Main)
(use Sound)
(use Motion)
(use System)

(public
	fileKey 0
	strikeMatch 1
	stripTWire 2
)

(instance fileKey of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setHeading: 45 self)
			)
			(1
				(ego
					view: 87
					loop: 0
					setSpeed: 12
					setCel: 0
					setCycle: End self
				)
			)
			(2
				(ego loop: 1 setCycle: Fwd)
				(sFx number: 37 play: setLoop: 1)
				(= ticks 100)
			)
			(3
				(theGame changeScore: 10 173 changeScore: 20 150)
				(ego put: 34 put: 12)
				((inventory at: 21) noun: 55 message: 52)
				(= ticks 30)
			)
			(4
				(Message 0 85 6 0 14 1 (global186 data?))
				(messager say: 6 63 1 0 self 85)
			)
			(5
				(ego normalize: 900 3 1)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance strikeMatch of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego
					view: 86
					loop: 0
					setCel: 0
					setSpeed: 12
					setCycle: End self
				)
				(sFx number: 332 setLoop: 1 play: setVol: 127)
			)
			(1
				(theGame changeScore: 12 157)
				(Message 0 85 22 0 14 1 (global186 data?))
				(messager say: 22 6 0 1 self 85)
			)
			(2
				(ego normalize: 900 2 1)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance stripTWire of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego
					setCel: 0
					setLoop: 0
					view: 89
					setSpeed: 12
					setCycle: End self
				)
			)
			(1
				(ego setCel: 0 setLoop: 1 setCycle: Fwd)
				(= ticks 30)
			)
			(2
				(ego setCel: 0 setLoop: 2 setCel: 0 setCycle: End self)
			)
			(3
				(Message 0 85 11 0 14 1 (global186 data?))
				(messager say: 11 4 0 0 self 85)
			)
			(4
				(theGame changeScore: 12 151)
				(ego normalize: 900 2 1)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sFx of Sound
	(properties)
)
