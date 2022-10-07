;;; Sierra Script 1.0 - (do not remove this comment)
(script# 408)
(include sci.sh)
(use Main)
(use rLab)
(use n402)
(use PolyPath)
(use Motion)
(use Actor)
(use System)

(public
	rm408 0
)

(instance rm408 of LabRoom
	(properties
		north 400
		south 400
	)
	
	(method (init)
		(proc402_0)
		(super init: &rest)
		(if (== ((inventory at: 43) owner?) 408)
			(theShield init:)
		)
		(curRoom setScript: (ScriptID 30 1))
		((ScriptID 30 7) addToPic:)
		((ScriptID 30 0) initCrypt: 2)
	)
	
	(method (notify)
		((ScriptID 30 7) addToPic:)
		((ScriptID 30 3) show:)
	)
)

(instance theShield of View
	(properties
		x 66
		y 150
		z 40
		noun 15
		modNum 400
		view 400
		loop 4
	)
	
	(method (init)
		(super init: &rest)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(5
				(curRoom setScript: getShield)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance getShield of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: PolyPath 84 149 self)
			)
			(1
				(theShield hide:)
				(ego
					normal: 0
					view: 401
					setLoop: 0
					posn: 75 152
					cycleSpeed: 8
					setCycle: EndLoop self
				)
			)
			(2
				(messager say: 15 5 0 1 self 400)
			)
			(3
				(theGame givePoints: 1)
				(theShield dispose:)
				(ego posn: 84 149 get: 43 reset: 1)
				(= cycles 2)
			)
			(4
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)
