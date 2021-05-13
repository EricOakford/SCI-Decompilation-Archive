;;; Sierra Script 1.0 - (do not remove this comment)
(script# 31)
(include sci.sh)
(use Main)
(use n027)
(use MuseumRgn)

(public
	aErnie 0
)

(instance aErnie of MuseumActor
	(properties
		noun 1
		modNum 1893
		originalView 824
		room 630
	)
	
	(method (doVerb theVerb theItem &tmp temp0 temp1 temp2)
		(switch theVerb
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
				(cond 
					((not (Message msgGET modNum noun 6 temp1 1)) (messager say: noun 6 81 0 0 modNum))
					((proc27_0 1 [global296 (- temp1 2)]) (messager say: noun 6 1 0 0 modNum))
					(else
						(messager say: noun 6 temp1 0 0 modNum)
						(proc27_1 1 @[global296 (- temp1 2)])
					)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
	
	(method (cue)
		(super cue: &rest)
		(Bset 4)
		(self dispose:)
	)
)
