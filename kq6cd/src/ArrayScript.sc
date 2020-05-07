;;; Sierra Script 1.0 - (do not remove this comment)
(script# 759)
(include sci.sh)
(use Main)
(use Print)
(use Reverse)
(use Motion)
(use System)


(class ArrayScript of Script
	(properties
		client 0
		state $ffff
		start 1
		timer 0
		cycles 0
		seconds 0
		lastSeconds 0
		ticks 0
		lastTicks 0
		register 0
		script 0
		caller 0
		next 0
		value 0
	)
	
	(method (init)
		(theGame handsOff:)
		(= start 1)
		(super init: &rest)
	)
	
	(method (changeState &tmp arrayScriptGetValue arrayScriptGetValue_2 arrayScriptGetValue_3 arrayScriptGetValue_4 arrayScriptGetValue_5 temp5)
		(= temp5 0)
		(= value (self at: state))
		(++ state)
		(switch value
			(-4095
				(= arrayScriptGetValue (self getValue:))
				(= arrayScriptGetValue_2 (self getValue:))
				(ego
					setCycle: CT arrayScriptGetValue arrayScriptGetValue_2 self
				)
			)
			(-4092 (ego setCycle: Beg self))
			(-4094 (ego setCycle: End self))
			(-4088 (ego setCycle: Fwd))
			(-4080 (ego setCycle: Rev))
			(-4064
				(= cycles (self getValue:))
			)
			(-4032
				(= seconds (self getValue:))
			)
			(-3968
				(= arrayScriptGetValue (self getValue:))
				(= arrayScriptGetValue_2 (self getValue:))
				(= arrayScriptGetValue_3 (self getValue:))
				(self
					play: arrayScriptGetValue arrayScriptGetValue_2 arrayScriptGetValue_3
				)
			)
			(-14
				(theGame handsOn:)
				(self cue:)
			)
			(-15
				(theGame handsOff:)
				(self cue:)
			)
			(-3840
				(if
				(== (= arrayScriptGetValue (self getValue:)) -1)
					(= arrayScriptGetValue curRoomNum)
				)
				(= arrayScriptGetValue_2 (self getValue:))
				(= arrayScriptGetValue_3 (self getValue:))
				(= arrayScriptGetValue_4 (self getValue:))
				(= arrayScriptGetValue_5 (self getValue:))
				(messager
					say:
						arrayScriptGetValue_2
						arrayScriptGetValue_3
						arrayScriptGetValue_4
						arrayScriptGetValue_5
						self
						arrayScriptGetValue
				)
			)
			(-16
				(= arrayScriptGetValue (self getValue:))
				(= arrayScriptGetValue_2 (self getValue:))
				(UnLoad arrayScriptGetValue arrayScriptGetValue_2)
				(self cue:)
			)
			(-14
				(theGame handsOn:)
				(self cue:)
			)
			(-15
				(theGame handsOff:)
				(self cue:)
			)
			(-1 (self dispose:))
			(else 
				(-- state)
				(ego view: (self getValue:) loop: (self getValue:))
				(if
				(!= (= arrayScriptGetValue (self getValue:)) -1)
					(ego cel: arrayScriptGetValue)
				)
				(ego x: (self getValue:) y: (self getValue:))
				(self cue:)
			)
		)
	)
	
	(method (cue)
		(self changeState: state)
	)
	
	(method (play)
		(Prints {Need play: method})
		(= cycles 1)
	)
	
	(method (at)
		(Prints {Need at: method})
		(SetDebug)
		(= quit 1)
	)
	
	(method (getValue)
		(= value (self at: state))
		(++ state)
		(return value)
	)
)
