;;; Sierra Script 1.0 - (do not remove this comment)
(script# 37)
(include sci.sh)
(use Main)
(use n027)
(use MuseumRgn)

(public
	aSteve 0
)

(instance aSteve of MuseumActor
	(properties
		noun 1
		modNum 1887
		scaleSignal $0001
		originalView 812
		room 370
	)
	
	(method (doVerb theVerb theItem &tmp temp0 temp1 temp2)
		(switch theVerb
			(1
				(switch currentAct
					(1
						(messager say: noun theVerb 0 0 0 modNum)
					)
					(5
						(messager say: noun theVerb 27 0 0 modNum)
					)
					(else 
						(messager say: noun theVerb 24 0 0 modNum)
					)
				)
			)
			(6
				(if
					(==
						(= temp0
							(if (== argc 2)
								theItem
							else
								(curRoom setInset: (ScriptID 20 0))
							)
						)
						-1
					)
					(return)
				)
				(= temp2 (& temp0 $00ff))
				(= temp1
					(switch (& temp0 $ff00)
						(256 (+ temp2 1))
						(512 (+ temp2 18))
						(768 (+ temp2 26))
						(1024 (+ temp2 61))
					)
				)
				(switch temp0
					(258
						(cond 
							((Btst 134)
								(if (proc27_0 10 global364)
									(messager say: noun 6 1 0 0 modNum)
								else
									(messager say: noun 6 72 0 0 modNum)
									(proc27_1 10 @global364)
								)
							)
							((proc27_0 10 global297) (messager say: noun 6 1 0 0 modNum))
							(else
								(messager say: noun 6 3 0 0 modNum)
								(proc27_1 10 @global297)
							)
						)
					)
					(else 
						(cond 
							((not (Message msgGET modNum noun 6 temp1 1)) (messager say: noun 6 81 0 0 modNum))
							((proc27_0 10 [global296 (- temp1 2)]) (messager say: noun 6 1 0 0 modNum))
							(else
								(messager say: noun 6 temp1 0 0 modNum)
								(proc27_1 10 @[global296 (- temp1 2)])
							)
						)
					)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)
