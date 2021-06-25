;;; Sierra Script 1.0 - (do not remove this comment)
(script# LBABOUT)
(include game.sh) (include "13.shm")
(use Main)
(use Print)
(use DCIcon)
(use Motion)
(use System)

(public
	aboutCode 0
)

(local
	oldCur
	fwdIcon
)
(procedure (DoCredits param1 param2 theLoop)
	(Print
		addText: N_CREDITS NULL NULL param1 param2 0 13
		addIcon: (theIcon1 loop: theLoop yourself:)
		init:
	)
)

(instance aboutCode of Code
	(properties)
	
	(method (doit &tmp [str 300] [str2 300])
		(= oldCur theCursor)
		(theGame setCursor: ARROW_CURSOR)
		(Load RES_VIEW 993 989)
		(if
			(not
				(Print
					font: userFont
					addText: N_ABOUT NULL NULL 1 0 0 13
					init:
				)
			)
			(self dispose:)
			(return)
		)
		(if
			(not
				(Print
					font: userFont
					addText: N_ABOUT NULL NULL 2 0 0 13
					init:
				)
			)
			(self dispose:)
			(return)
		)
		(Message MsgGet 13 N_ABOUT NULL NULL 3 @str)
		(Format @str2 @str version)
		(if (not (Print addText: @str2 init:))
			(self dispose:)
			(return)
		)
		(if (not (Print addText: N_ABOUT NULL NULL 4 0 0 13 init:))
			(self dispose:)
			(return)
		)
		(if (not (Print addText: N_CREDITS 0 0 1 0 0 13 init:))
			(self dispose:)
			(return)
		)
		(if (not (Print addText: N_CREDITS NULL NULL 2 0 0 13 init:))
			(self dispose:)
			(return)
		)
		(= fwdIcon TRUE)
		(if
			(not
				(Print
					addText: N_CREDITS NULL NULL 3 0 0 13
					addIcon: (theIcon1 view: 993 loop: 0 yourself:) 0 0 0 50
					addIcon: (theIcon2 view: 993 loop: 1 yourself:) 0 0 50 50
					addIcon: (theIcon3 view: 993 loop: 2 yourself:) 0 0 75 50
					init:
				)
			)
			(self dispose:)
			(return)
		)
		(if
			(not
				(Print
					addText: N_CREDITS NULL NULL 4 0 0 13
					addIcon: theIcon1 0 0 0 50
					addIcon: theIcon2 0 0 50 50
					addIcon: theIcon3 0 0 75 50
					init:
				)
			)
			(self dispose:)
			(return)
		)
		(if (not (DoCredits 5 35 3))
			(self dispose:)
			(return)
		)
		(if (not (DoCredits 6 35 3))
			(self dispose:)
			(return)
		)
		(= fwdIcon FALSE)
		(theIcon1 view: 989)
		(if (not (DoCredits 31 35 1))
			(self dispose:)
			(return)
		)
		(theIcon1 view: 993)
		(if (not (DoCredits 7 35 4))
			(self dispose:)
			(return)
		)
		(= fwdIcon FALSE)
		(theIcon1 view: 989)
		(if (not (DoCredits 8 35 1))
			(self dispose:)
			(return)
		)
		(= fwdIcon TRUE)
		(theIcon1 view: 993)
		(if (not (DoCredits 9 35 12))
			(self dispose:)
			(return)
		)
		(if (not (DoCredits 10 35 5))
			(self dispose:)
			(return)
		)
		(= fwdIcon FALSE)
		(if (not (DoCredits 11 45 6 0))
			(self dispose:)
			(return)
		)
		(= fwdIcon TRUE)
		(if (not (DoCredits 12 35 7 1))
			(self dispose:)
			(return)
		)
		(if (not (DoCredits 13 50 8))
			(self dispose:)
			(return)
		)
		(= fwdIcon TRUE)
		(if (not (DoCredits 32 35 7 1))
			(self dispose:)
			(return)
		)
		(if (not (DoCredits 14 35 9))
			(self dispose:)
			(return)
		)
		(if (not (DoCredits 29 35 9))
			(self dispose:)
			(return)
		)
		(if (not (DoCredits 15 35 10))
			(self dispose:)
			(return)
		)
		(= fwdIcon FALSE)
		(theIcon1 view: 989)
		(if (not (DoCredits 16 50 0))
			(self dispose:)
			(return)
		)
		(= fwdIcon TRUE)
		(if (not (DoCredits 17 35 1))
			(self dispose:)
			(return)
		)
		(= fwdIcon TRUE)
		(if (not (DoCredits 30 35 1))
			(self dispose:)
			(return)
		)
		(if
			(not
				(Print
					addText: N_CREDITS NULL NULL 18 40 0 13
					addIcon: (theIcon1 view: 989 loop: 2 yourself:) 0 0 0 0
					addIcon: (theIcon2 view: 989 loop: 3 yourself:) 0 0 130 0
					init:
				)
			)
			(self dispose:)
			(return)
		)
		(= fwdIcon FALSE)
		(if
			(not
				(Print
					addText: N_CREDITS NULL NULL 19 0 0 13
					addIcon: (theIcon1 loop: 4 yourself:) 0 0 150 20
					init:
				)
			)
			(self dispose:)
			(return)
		)
		(if (not (DoCredits 20 35 5))
			(self dispose:)
			(return)
		)
		(if (not (DoCredits 21 35 6))
			(self dispose:)
			(return)
		)
		(if (not (DoCredits 22 35 7))
			(self dispose:)
			(return)
		)
		(if (not (DoCredits 23 35 8))
			(self dispose:)
			(return)
		)
		(if (not (DoCredits 24 75 9))
			(self dispose:)
			(return)
		)
		(theIcon1 view: 993)
		(= fwdIcon TRUE)
		(if (not (DoCredits 25 55 4))
			(self dispose:)
			(return)
		)
		(theIcon1 view: 989)
		(= fwdIcon FALSE)
		(if (not (DoCredits 26 35 10))
			(self dispose:)
			(return)
		)
		(if (not (DoCredits 27 35 5))
			(self dispose:)
			(return)
		)
		(= fwdIcon TRUE)
		(if (not (DoCredits 28 35 11))
			(self dispose:)
			(return)
		)
		(self dispose:)
	)
	
	(method (dispose)
		(= normalCursor oldCur)
		(theGame setCursor: normalCursor)
		(DisposeScript DCICON)
		(DisposeScript LBABOUT)
	)
)

(instance theIcon1 of DCIcon
	(properties
		cycleSpeed 15
	)
	
	(method (init)
		(if fwdIcon
			((= cycler (Forward new:)) init: self)
		else
			(= cel 0)
			((= cycler (EndLoop new:)) init: self)
		)
	)
)

(instance theIcon2 of DCIcon
	(properties
		cycleSpeed 15
	)
	
	(method (init)
		((= cycler (Forward new:)) init: self)
	)
)

(instance theIcon3 of DCIcon
	(properties
		cycleSpeed 15
	)
	
	(method (init)
		((= cycler (Forward new:)) init: self)
	)
)
