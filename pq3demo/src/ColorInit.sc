;;; Sierra Script 1.0 - (do not remove this comment)
(script# COLORINIT) ;897
(include game.sh)
(use Main)

(public
	ColorInit 0
)

(procedure (ColorInit)
	(if (> (Graph GDetect) 16)
		(= mapTextColor (Palette 5 0 0 0))
		(= myTextColor (Palette 5 31 31 31))
		(= myInsideColor (Palette 5 63 63 63))
		(= myBotBordColor (Palette 5 95 95 95))
		(= myRgtBordColor (Palette 5 127 127 127))
		(= myBackColor (Palette 5 159 159 159))
		(= myLftBordColor (Palette 5 191 191 191))
		(= myTopBordColor (Palette 5 223 223 223))
		(= myHighlightColor (Palette 5 187 35 35))
		(= mapBackColor (Palette 5 39 39 219))
	else
		(= myTextColor vBLACK)
		(= myInsideColor vLGREY)
		(= myBotBordColor vGREY)
		(= myTopBordColor vWHITE)
	)
)
