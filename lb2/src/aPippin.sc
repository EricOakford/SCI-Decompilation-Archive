;;; Sierra Script 1.0 - (do not remove this comment)
(script# 35)
(include game.sh)
(use Main)
(use n027)
(use MuseumRgn)

(public
	aPippin 0
)

(instance aPippin of MuseumActor
	(properties
		noun 1
		modNum 1882
		originalView 822
		room 350
	)
	
	(method (doVerb theVerb theItem &tmp temp0 theSeq temp2)
		(switch theVerb
			(V_ASK
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
				(= theSeq
					(switch (& temp0 $ff00)
						(256 (+ temp2 1))
						(512 (+ temp2 18))
						(768 (+ temp2 26))
						(1024 (+ temp2 61))
					)
				)
				(cond 
					((not (Message MsgGet modNum noun V_ASK theSeq 1))
						(messager say: noun V_ASK 81 0 0 modNum)
					)
					((proc27_0 5 [global296 (- theSeq 2)])
						(messager say: noun V_ASK 1 0 0 modNum)
					)
					(else
						(messager say: noun V_ASK theSeq 0 0 modNum)
						(proc27_1 5 @[global296 (- theSeq 2)])
					)
				)
			)
			(else
				(super doVerb: theVerb)
			)
		)
	)
	
	(method (cue)
		(super cue: &rest)
		(Bset 1)
		(self dispose:)
	)
)
