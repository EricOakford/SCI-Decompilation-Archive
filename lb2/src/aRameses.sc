;;; Sierra Script 1.0 - (do not remove this comment)
(script# 36)
(include sci.sh)
(use Main)
(use n027)
(use MuseumRgn)

(public
	aRameses 0
)

(instance aRameses of MuseumActor
	(properties
		noun 1
		modNum 1891
		originalView 823
		room 370
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
				(switch temp0
					(258
						(cond 
							((Btst 134)
								(if (proc27_0 6 global364)
									(messager say: noun 6 1 0 0 modNum)
								else
									(messager say: noun 6 72 0 0 modNum)
									(proc27_1 6 @global364)
								)
							)
							((proc27_0 6 global297) (messager say: noun 6 1 0 0 modNum))
							(else
								(messager say: noun 6 3 0 0 modNum)
								(proc27_1 6 @global297)
							)
						)
					)
					(259
						(cond 
							((Btst 171)
								(if (proc27_0 6 global363)
									(messager say: noun 6 1 0 0 modNum)
								else
									(messager say: noun 6 69 0 0 modNum)
									(proc27_1 6 @global363)
								)
							)
							((proc27_0 6 global298) (messager say: noun 6 1 0 0 modNum))
							(else
								(messager say: noun 6 4 0 0 modNum)
								(proc27_1 6 @global298)
							)
						)
					)
					(266
						(cond 
							((Btst 161)
								(if (proc27_0 6 global367)
									(messager say: noun 6 1 0 0 modNum)
								else
									(messager say: noun 6 73 0 0 modNum)
									(proc27_1 6 @global367)
								)
							)
							((proc27_0 6 global305) (messager say: noun 6 1 0 0 modNum))
							(else
								(messager say: noun 6 11 0 0 modNum)
								(proc27_1 6 @global305)
							)
						)
					)
					(267
						(cond 
							((Btst 158)
								(if (proc27_0 6 global365)
									(messager say: noun 6 1 0 0 modNum)
								else
									(messager say: noun 6 71 0 0 modNum)
									(proc27_1 6 @global365)
								)
							)
							((proc27_0 6 global306) (messager say: noun 6 1 0 0 modNum))
							(else
								(messager say: noun 6 12 0 0 modNum)
								(proc27_1 6 @global306)
							)
						)
					)
					(else 
						(cond 
							((not (Message msgGET modNum noun 6 temp1 1)) (messager say: noun 6 81 0 0 modNum))
							((proc27_0 6 [global296 (- temp1 2)]) (messager say: noun 6 1 0 0 modNum))
							(else
								(messager say: noun 6 temp1 0 0 modNum)
								(proc27_1 6 @[global296 (- temp1 2)])
							)
						)
					)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)
