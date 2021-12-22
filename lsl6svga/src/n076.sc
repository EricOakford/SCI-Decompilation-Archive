;;; Sierra Script 1.0 - (do not remove this comment)
(script# 76)
(include sci.sh)
(use Main)
(use String)
(use Print)
(use System)

(public
	proc76_0 0
)

(procedure (proc76_0 &tmp temp0 temp1 temp2 theTheCursor)
	(= theTheCursor theCursor)
	(theGame setCursor: normalCursor)
	(= temp0 (Str format: {%d} global183))
	(SetCursor 237 65)
	(if
		(OneOf
			(= temp2
				(Print
					addTitle: 10 0 4 1 0
					addText: 10 0 0 1 50 1 0
					addIcon: 972 0 0 0 0
					addEdit: temp0 3 160 10 temp0
					addButton: 100 10 0 2 1 50 33 0
					addButton: 200 10 0 3 1 160 33 0
					init:
				)
			)
			-1
			100
			0
		)
		(DisposeScript 76)
		(return)
	)
	(if temp0 (= temp1 (temp0 asInteger:)))
	(if temp1
		(= global183 temp1)
		((ScriptID 0 12) setReal: theGame 0 temp1)
	else
		(if (!= ((ScriptID 0 12) seconds?) -1)
			((ScriptID 0 12) seconds: -1 client: (ScriptID 0 12))
		)
		(= global183 0)
	)
	(theGame setCursor: theTheCursor)
	(DisposeScript 76)
)
