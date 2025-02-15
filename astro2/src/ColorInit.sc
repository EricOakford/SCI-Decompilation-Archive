;;; Sierra Script 1.0 - (do not remove this comment)
(script# COLORINIT) ;802
(include game.sh)
(use Main)

(public
	ColorInit 0
)

(procedure (ColorInit)
	(if (> (Graph GDetect) 16)
		;                                   red grn blu   	# in std palette
		(= colBlack			(Palette PALMatch  31  31  31))	; 1
		(= colGray1			(Palette PALMatch  63  63  63))	; 2 is darkest gray
		(= colGray2			(Palette PALMatch  95  95  95))	; 3
		(= colGray3			(Palette PALMatch 127 127 127))	; 4
		(= colGray4			(Palette PALMatch 159 159 159))	; 5
		(= colGray5			(Palette PALMatch 191 191 191))	; 6 is lightest gray
		(= colWhite			(Palette PALMatch 223 223 223))	; 7
		(= colDRed			(Palette PALMatch 151  27  27))	; 10
		(= colLRed			(Palette PALMatch 231 103 103))	; 14
		(= colVLRed			(Palette PALMatch 235 135 135))	; 15
		(= colDYellow		(Palette PALMatch 187 187  35))	; 27
		(= colYellow		(Palette PALMatch 219 219  39))	; 28
		(= colLYellow		(Palette PALMatch 223 223  71))	; 29
		;(= colVDGreen		(Palette PALMatch  15  87  15))  ; 32
		(= colDGreen		(Palette PALMatch  27 151  27))	; 34
		(= colLGreen		(Palette PALMatch  71 223  71))	; 37
		(= colVLGreen		(Palette PALMatch 135 235 135))	; 39
		(= colDBlue			(Palette PALMatch  23  23 119))	; 49
		(= colBlue			(Palette PALMatch  35  35 187))	; 51
		(= colLBlue			(Palette PALMatch  71  71 223))	; 53
		(= colVLBlue		(Palette PALMatch 135 135 235))	; 55
		(= colMagenta		(Palette PALMatch 219  39 219))	; 60
		;(= colLMagenta		(Palette PALMatch 223  71 223))	; 61
		(= colCyan			(Palette PALMatch 27 151 151))  ; 42
		;(= colLCyan			(Palette PALMatch	135 235 235))	; 47
		
		(= colLED (Palette PALMatch 187 35 35))
		(= colLaser (Palette PALMatch 255 100 100))
		(= myTextColor14 (Palette PALMatch 151 27 27))
		(= myTextColor15 (Palette PALMatch 219 127 39))
		(= myTextColor16 (Palette PALMatch 231 231 103))
		(= myTextColor17 (Palette PALMatch 15 87 87))
		(= myTextColor18 (Palette PALMatch 27 27 151))
	else
		(= colBlack vBLACK)
		(= colBlue vBLUE)
		(= colDGreen vGREEN)
		(= colCyan vCYAN)
		(= colDRed vRED)
		(= colMagenta vMAGENTA)
		(= colYellow vBROWN)
		(= colGray1 vLGREY)
		(= colGray2 vGREY)
		(= colLBlue vLBLUE)
		(= colLGreen vLGREEN)
		(= colLCyan vLCYAN)
		(= colLRed vLRED)
		(= colLMagenta vLMAGENTA)
		(= colLYellow vYELLOW)
		(= colWhite vWHITE)
		(= colLED colDRed)
		(= colLaser colLRed)
		(= myTextColor14 colYellow)
		(= myTextColor15 colYellow)
		(= myTextColor16 colYellow)
		(= myTextColor17 colYellow)
		(= myTextColor18 colYellow)
	)
)
