;;; Sierra Script 1.0 - (do not remove this comment)
(script# COLORINIT) ;802
(include game.sh)
(use Main)

(public
	ColorInit 0
)

(procedure (ColorInit)
	(if (> (Graph GDetect) 16)
		(= myLowlightColor (Palette PALMatch 31 31 31))
		(= myInsideColor (Palette PALMatch 63 63 63))
		(= myBotBordColor (Palette PALMatch 95 95 95))
		(= myRgtBordColor (Palette PALMatch 127 127 127))
		(= myBackColor (Palette PALMatch 159 159 159))
		(= myLftBordColor (Palette PALMatch 191 191 191))
		(= myTopBordColor (Palette PALMatch 223 223 223))
		(= myTextColor (Palette PALMatch 151 27 27))
		(= myTextColor2 (Palette PALMatch 231 103 103))
		(= myTextColor3 (Palette PALMatch 235 135 135))
		(= myTextColor4 (Palette PALMatch 187 187 35))
		(= myTextColor5 (Palette PALMatch 219 219 39))
		(= myTextColor6 (Palette PALMatch 223 223 71))
		(= myTextColor20 (Palette PALMatch 27 151 27))
		(= myTextColor7 (Palette PALMatch 71 223 71))
		(= myTextColor8 (Palette PALMatch 135 235 135))
		(= myTextColor9 (Palette PALMatch 23 23 119))
		(= myTextColor19 (Palette PALMatch 35 35 187))
		(= myTextColor22 (Palette PALMatch 71 71 223))
		(= myTextColor23 (Palette PALMatch 135 135 235))
		(= myTextColor10 (Palette PALMatch 219 39 219))
		(= myTextColor11 (Palette PALMatch 27 151 151))
		(= myTextColor12 (Palette PALMatch 187 35 35))
		(= myTextColor13 (Palette PALMatch 255 100 100))
		(= myTextColor14 (Palette PALMatch 151 27 27))
		(= myTextColor15 (Palette PALMatch 219 127 39))
		(= myTextColor16 (Palette PALMatch 231 231 103))
		(= myTextColor17 (Palette PALMatch 15 87 87))
		(= myTextColor18 (Palette PALMatch 27 27 151))
	else
		(= myLowlightColor 0)
		(= myTextColor19 1)
		(= myTextColor20 2)
		(= myTextColor11 3)
		(= myTextColor 4)
		(= myTextColor10 5)
		(= myTextColor5 6)
		(= myInsideColor 7)
		(= myBotBordColor 8)
		(= myTextColor22 9)
		(= myTextColor7 10)
		(= zondraTextColor 11)
		(= myTextColor2 12)
		(= global190 13)
		(= myTextColor6 14)
		(= myTopBordColor 15)
		(= myTextColor12 myTextColor)
		(= myTextColor13 myTextColor2)
		(= myTextColor14 myTextColor5)
		(= myTextColor15 myTextColor5)
		(= myTextColor16 myTextColor5)
		(= myTextColor17 myTextColor5)
		(= myTextColor18 myTextColor5)
	)
)