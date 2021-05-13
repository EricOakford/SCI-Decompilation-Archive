;;; Sierra Script 1.0 - (do not remove this comment)
(script# 562)
(include sci.sh)
(use Main)
(use Inset)
(use Timer)
(use Sound)
(use Actor)

(public
	theIntercom 0
)

(procedure (localproc_00b4)
	(olympiaButton setCel: 1)
	(yvetteButton setCel: 1)
	(ernieButton setCel: 1)
	(heimlichButton setCel: 1)
	(nextButton setCel: 1)
	(lastButton setCel: 1)
)

(procedure (localproc_00f1)
	(DisposeScript 1892)
	(DisposeScript 1889)
	(DisposeScript 1885)
	(DisposeScript 1888)
	(DisposeScript 1893)
	(DisposeScript 1890)
)

(instance theIntercom of Inset
	(properties
		view 563
		loop 3
		x 105
		y 109
		disposeNotOnMe 1
		modNum 562
		noun 54
	)
	
	(method (init)
		(super init: &rest)
		(onOff init:)
		(sendReceive init:)
		(olympiaButton init:)
		(yvetteButton init:)
		(ernieButton init:)
		(heimlichButton init:)
		(nextButton init:)
		(lastButton init:)
	)
	
	(method (dispose)
		(localproc_00f1)
		(sFX dispose:)
		(super dispose:)
		(DisposeScript 562)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(2
				(if
				(and (== (onOff cel?) 2) (== (sendReceive cel?) 2))
					(messager say: 54 theVerb 0 (Random 2 7) 0 562)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance onOff of Prop
	(properties
		x 133
		y 133
		noun 57
		modNum 562
		view 563
		loop 3
		cel 1
		priority 14
		signal $0010
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(if (== cel 1) (self setCel: 2) else (self setCel: 1))
				(sFX number: 558 flags: 1 setLoop: 1 play:)
			)
			(8
				(if (== cel 2)
					(messager say: noun theVerb 8 0 0 562)
				else
					(messager say: noun theVerb 9 0 0 562)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance sendReceive of Prop
	(properties
		x 139
		y 117
		noun 77
		modNum 562
		view 563
		loop 3
		cel 1
		priority 14
		signal $0010
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(if (== (self cel?) 1)
					(self setCel: 2)
				else
					(self setCel: 1)
				)
				(sFX number: 558 flags: 1 setLoop: 1 play:)
			)
			(8
				(if (== cel 2)
					(messager say: noun theVerb 6 0 0 562)
				else
					(messager say: noun theVerb 7 0 0 562)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(class Button of Prop
	(properties
		x 0
		y 0
		z 0
		heading 0
		noun 84
		modNum 562
		nsTop 0
		nsLeft 0
		nsBottom 0
		nsRight 0
		sightAngle 26505
		actions 0
		onMeCheck $6789
		state $0000
		approachX 0
		approachY 0
		approachDist 0
		_approachVerbs 0
		yStep 2
		view 563
		loop 3
		cel 1
		priority 14
		underBits 0
		signal $0010
		lsTop 0
		lsLeft 0
		lsBottom 0
		lsRight 0
		brTop 0
		brLeft 0
		brBottom 0
		brRight 0
		scaleSignal $0000
		scaleX 128
		scaleY 128
		maxScale 128
		cycleSpeed 6
		script 0
		cycler 0
		timer 0
		detailLevel 0
		scaler 0
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(if (== cel 1)
					(localproc_00b4)
					(localproc_00f1)
					(self pushButton:)
				else
					(self setCel: 1)
				)
				(sFX number: 558 flags: 1 setLoop: 1 play:)
			)
			(8
				(if (== cel 2)
					(messager say: noun theVerb 12 0 0 562)
				else
					(messager say: noun theVerb 13 0 0 562)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
	
	(method (cue)
		(if
		(and (== (onOff cel?) 2) (== (sendReceive cel?) 1))
			(sFX number: 567 flags: 1 setLoop: 1 play:)
			(messager say: 54 4 5 0 0 562)
		)
	)
	
	(method (pushButton)
		(self setCel: 2)
		((Timer new:) setCycle: self 2)
	)
)

(instance olympiaButton of Button
	(properties
		x 139
		y 135
	)
	
	(method (cue)
		(if
		(and (== (onOff cel?) 2) (== (sendReceive cel?) 1))
			(sFX number: 567 flags: 1 setLoop: 1 play:)
			(if (Btst 51)
				(messager say: 54 4 2 0 0 562)
			else
				(messager say: 54 4 1 0 0 562)
				(Bset 51)
			)
		)
	)
)

(instance yvetteButton of Button
	(properties
		x 140
		y 132
	)
	
	(method (cue)
		(if
		(and (== (onOff cel?) 2) (== (sendReceive cel?) 1))
			(sFX number: 567 flags: 1 setLoop: 1 play:)
			(if (not (Btst 52))
				(Bset 52)
				(if (not (Btst 5))
					(messager say: 54 4 3 0 0 562)
				else
					(messager say: 54 4 5 0 0 562)
				)
			else
				(messager say: 54 4 2 0 0 562)
			)
		)
	)
)

(instance ernieButton of Button
	(properties
		x 141
		y 129
	)
	
	(method (cue)
		(if
		(and (== (onOff cel?) 2) (== (sendReceive cel?) 1))
			(sFX number: 567 flags: 1 setLoop: 1 play:)
			(if (not (Btst 53))
				(Bset 53)
				(if (and (not (Btst 2)) (not (Btst 4)))
					(messager say: 54 4 4 0 0 562)
				else
					(messager say: 54 4 5 0 0 562)
				)
			else
				(messager say: 54 4 2 0 0 562)
			)
		)
	)
)

(instance heimlichButton of Button
	(properties
		x 142
		y 126
	)
)

(instance nextButton of Button
	(properties
		x 143
		y 123
	)
)

(instance lastButton of Button
	(properties
		x 144
		y 120
	)
)

(instance sFX of Sound
	(properties
		flags $0001
	)
)
