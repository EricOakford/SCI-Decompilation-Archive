;;; Sierra Script 1.0 - (do not remove this comment)
(script# ABOUT)
(include game.sh) (include "556.shm")
(use Main)
(use Print)
(use System)

(public
	aboutCode 0
)

(local
	sequenceNum =  1
)
(instance aboutCode of Code
	(properties)
	
	(method (doit &tmp [buf1 50] [buf2 50])
		(if (== sequenceNum 8)
			(Message MsgGet ABOUT N_ABOUT NULL NULL 8 @buf1)
			(Print
				font: userFont
				mode: teJustCenter
				width: 180
				addTextF: @buf2 @buf1 version
				init: dummyCue
			)
		else
			(Print
				font: userFont
				mode: teJustCenter
				width: 260
				addText: N_ABOUT NULL NULL sequenceNum 0 0 ABOUT
				init: dummyCue
			)
		)
	)
)

(instance dummyCue of Script
	(properties)
	
	(method (cue)
		(if (<= (++ sequenceNum) 8)
			(aboutCode doit:)
		else
			(= sequenceNum 1)
		)
	)
)
