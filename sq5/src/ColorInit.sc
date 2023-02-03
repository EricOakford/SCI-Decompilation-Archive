;;; Sierra Script 1.0 - (do not remove this comment)
(script# COLORINIT)
(include game.sh)
(use Main)

(public
	ColorInit 0
)

(procedure (ColorInit)
	(= colBlack 0)
	(= colGray2 (Palette PALMatch 159 159 159))
	(= colWhite (Palette PALMatch 223 223 223))
	(= colRed (Palette PALMatch 223 71 71))
	(= colYellow (Palette PALMatch 219 219 39))
	(= colGreen (Palette PALMatch 71 223 71))
	(= colBlue (Palette PALMatch 35 35 187))
	(= colMagenta (Palette PALMatch 219 39 219))
	(= colCyan (Palette PALMatch 71 223 223))
	(= colGray1 5)
)
