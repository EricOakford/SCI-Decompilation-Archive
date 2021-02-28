;;; Sierra Script 1.0 - (do not remove this comment)
(script# GLORY_ABOUT) ;19
(include game.sh) (include "19.shm")
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
	local1
	[local2 5]
)
(instance aboutCode of Code
	
	(method (doit &tmp [str 300])
		(= oldCur ((theIconBar curIcon?) cursor?))
		(theGame setCursor: ARROW_CURSOR)
		(cond 
			(
				(not
					(if (or (Message MsgGet GLORY_ABOUT N_CREDITS NULL NULL 1 @str) 1)
						(Print
							font: SYSFONT
							mode: teJustCenter
							addTextF: @str version
							init:
						)
					)
				)
				0
			)
			(
				(not
					(Print
						font: SYSFONT
						mode: teJustCenter
						addText: N_CREDITS NULL NULL 2 0 0 GLORY_ABOUT
						init:
					)
				)
				0
			)
			(
				(not
					(Print
						font: SYSFONT
						mode: teJustCenter
						addText: N_CREDITS NULL NULL 3 0 0 GLORY_ABOUT
						init:
					)
				)
				0
			)
			(
				(not
					(Print
						font: SYSFONT
						mode: teJustCenter
						addText: N_CREDITS NULL NULL 4 0 0 GLORY_ABOUT
						init:
					)
				)
				0
			)
			(
				(not
					(Print
						font: SYSFONT
						mode: teJustCenter
						addText: N_CREDITS NULL NULL 5 0 0 GLORY_ABOUT
						init:
					)
				)
				0
			)
			(
				(not
					(Print
						font: SYSFONT
						mode: teJustCenter
						addText: N_CREDITS NULL NULL 6 0 0 GLORY_ABOUT
						init:
					)
				)
				0
			)
			(
				(not
					(Print
						font: SYSFONT
						mode: teJustCenter
						addText: N_CREDITS NULL NULL 7 0 0 GLORY_ABOUT
						init:
					)
				)
				0
			)
			(
				(not
					(Print
						font: SYSFONT
						mode: teJustCenter
						addText: N_CREDITS NULL NULL 8 0 0 GLORY_ABOUT
						init:
					)
				)
				0
			)
			(
				(not
					(Print
						font: SYSFONT
						mode: teJustCenter
						addText: N_CREDITS NULL NULL 9 0 0 GLORY_ABOUT
						init:
					)
				)
				0
			)
			(
				(not
					(Print
						font: SYSFONT
						mode: teJustCenter
						addText: N_CREDITS NULL NULL 10 0 0 GLORY_ABOUT
						init:
					)
				)
				0
			)
			(
				(not
					(Print
						font: SYSFONT
						mode: teJustCenter
						addText: N_BACK_TO_GAME NULL NULL 1 0 0 GLORY_ABOUT init:)
				)
				0
			)
		)
		(self dispose:)
	)
	
	(method (dispose)
		(theGame setCursor: oldCur TRUE)
		(DisposeScript GLORY_ABOUT)
	)
)

(instance theIcon1 of DCIcon
	(properties
		cycleSpeed 15
	)
	
	(method (init)
		(if local1
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
