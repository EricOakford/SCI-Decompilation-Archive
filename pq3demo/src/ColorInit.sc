;;; Sierra Script 1.0 - (do not remove this comment)
(script# COLORINIT)
(include game.sh)
(use Main)

(public
	ColorInit 0
)

(procedure (ColorInit)
	(if (> (Graph GDetect) 16)
		(= colMap (Palette PALMatch 0 0 0))
		(= colBlack (Palette PALMatch 31 31 31))
		(= colGray1 (Palette PALMatch 63 63 63))
		(= colGray2 (Palette PALMatch 95 95 95))
		(= colGray3 (Palette PALMatch 127 127 127))
		(= colGray4 (Palette PALMatch 159 159 159))
		(= colGray5 (Palette PALMatch 191 191 191))
		(= colWhite (Palette PALMatch 223 223 223))
		(= colRed (Palette PALMatch 187 35 35))
		(= colBlue (Palette PALMatch 39 39 219))
	else
		(= colBlack vBLACK)
		(= colGray1 vLGREY)
		(= colGray2 vGREY)
		(= colWhite vWHITE)
	)
)
