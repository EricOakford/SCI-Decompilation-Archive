;;; Sierra Script 1.0 - (do not remove this comment)
(script# 441)
(include sci.sh)
(use Main)
(use PolyPath)
(use Timer)
(use Motion)
(use System)

(public
	sCountessMeeting 0
	sCountessNoMeet 1
	sCountessLeaves 2
	sTalkWithCountess 3
	countTimer 4
)

(instance sCountessMeeting of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 3))
			(1 (= cycles 1))
			(2
				(self setScript: sCountessEnters self)
			)
			(3
				((ScriptID 90 1) setMotion: PolyPath 141 171 self)
			)
			(4
				((ScriptID 90 1) view: 444 loop: 0 cel: 0)
				(theGame handsOn:)
				(if (== (ego view?) 443)
					(theIconBar disable: 1 2 6 7 5)
				)
				(= cycles 1)
			)
			(5 (= seconds 10))
			(6
				(if (== (ego view?) 443)
					(theIconBar disable: 1 2 6 7 5)
				)
				(theGame handsOff:)
				(= cycles 3)
			)
			(7
				(if (== (ego view?) 443)
					((ScriptID 90 1) setCycle: End self)
				else
					(theGame handsOn: 1)
					(self dispose:)
				)
			)
			(8 (= seconds 2))
			(9
				((ScriptID 90 1) setCycle: Beg self)
			)
			(10 (= seconds 3))
			(11
				(theGame handsOn: 1)
				(client setScript: sCountessLeaves)
			)
		)
	)
)

(instance sCountessNoMeet of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 3))
			(1 (= cycles 1))
			(2
				(self setScript: sCountessEnters self)
			)
			(3 (= seconds 2))
			(4
				(client setScript: sCountessLeaves)
			)
		)
	)
)

(instance sCountessEnters of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				((ScriptID 90 1)
					moveTo: 440
					loop: 1
					x: 240
					y: 134
					actions: askQuestions
				)
				(if ((ScriptID 90 1) scaler?)
					(((ScriptID 90 1) scaler?) doit:)
				)
				(= cycles 1)
			)
			(1
				((ScriptID 90 1) view: 825)
				(if (== ((ScriptID 440 2) doorState?) 0)
					((ScriptID 440 2) caller: self open:)
				else
					(= cycles 2)
				)
			)
			(2
				((ScriptID 90 1) setMotion: PolyPath 122 154 self)
				(= ticks 480)
			)
			(3 (theIconBar disable: 7))
			(4 (self dispose:))
		)
	)
)

(instance sCountessLeaves of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (== (ego view?) 443)
					(theIconBar disable: 1 2 6 7 5)
				else
					(walkHandler delete: curRoom)
					(directionHandler delete: curRoom)
				)
				(theGame handsOff:)
				(= cycles 1)
			)
			(1
				((ScriptID 90 1)
					view: 825
					setCycle: Walk
					setMotion: PolyPath 122 154 self
				)
			)
			(2
				((ScriptID 90 1) setMotion: PolyPath 233 134 self)
			)
			(3
				((ScriptID 90 1) setMotion: PolyPath 239 134 self)
			)
			(4
				(if (== (ego view?) 443)
					(theGame handsOn: 1)
					(user canControl: 1)
				else
					(theGame handsOn:)
				)
				((ScriptID 90 1) actions: 0 moveTo: 430 wandering: 1)
				(countTimer dispose:)
				(theMusic2 fade:)
				(WrapMusic pause: 0)
				(theIconBar enable: 7)
				(self dispose:)
				(DisposeScript 441)
			)
		)
	)
)

(instance sTalkWithCountess of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: PolyPath 100 172 self)
			)
			(1
				(messager say: 1 0 1 0 self 1440)
			)
			(2
				(Bset 120)
				(countTimer setReal: countTimer 15)
				(theGame handsOn: 1)
				(theIconBar disable: 7)
				(self dispose:)
			)
		)
	)
)

(instance askQuestions of Actions
	(properties)
	
	(method (doVerb theVerb)
		(switch theVerb
			(6
				(if (== (ego view?) 443)
					(curRoom setScript: (ScriptID 440 1))
				else
					(switch (curRoom setInset: (ScriptID 20 0))
						(1030
							(messager say: 1 6 2 0 0 1440)
							(countTimer seconds: (+ (countTimer seconds?) 10))
						)
						(else 
							(messager say: 1 6 4 0 0 1440)
							(countTimer seconds: 1)
						)
					)
				)
				1
			)
			(2
				(if (== (ego view?) 443)
					(curRoom setScript: (ScriptID 440 1))
				else
					(messager say: 1 2 0 0 0 1440)
				)
			)
			(17
				(messager say: 1 17 0 0 0 1440)
				(countTimer seconds: (+ (countTimer seconds?) 10))
			)
			(else  0)
		)
	)
)

(instance countTimer of Timer
	(properties)
	
	(method (cue)
		(cond 
			((not ((ScriptID 90 1) mover?)) ((ScriptID 90 1) setScript: sCountessLeaves))
			(((ScriptID 90 1) script?) (((ScriptID 90 1) script?) next: sCountessLeaves))
		)
	)
)
