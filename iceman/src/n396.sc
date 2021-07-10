;;; Sierra Script 1.0 - (do not remove this comment)
(script# 396)
(include game.sh)
(use Main)
(use Intrface)

(public
	SubPrint 0
)

(procedure (SubPrint numSeconds &tmp [sizeRect 4] [str 100])
	(cls)
	(Format @str &rest)
	(TextSize @[sizeRect 0] @str userFont 0)
	(Print @str
		#at -1 -10
		#width (if (> [sizeRect 2] 24) 300 else 0)
		#mode teJustCenter
		#font 30
		#time numSeconds
		#dispose
	)
)
