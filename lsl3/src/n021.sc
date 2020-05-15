;;; Sierra Script 1.0 - (do not remove this comment)
(script# CHANGE_SCRIPT)
(include game.sh)
(use Main)

(public
	ChangeScriptState 1
)

(procedure (ChangeScriptState theScript newState param3 fgColor &tmp [str 33])
	(if (and debugging (not (Btst fQAEnabled)))
		(if (< argc 2) (= fgColor vBLUE))
		(if (< argc 3) (= fgColor vLGREY))
		(Display
			(Format @str 21 0
				(theScript name?)
				(theScript state?)
				newState
			)
			p_at 1 (- (* 8 param3) 7)
			p_font 999
			p_color fgColor
			p_back vBLACK
		)
	)
)
