;;; Sierra Script 1.0 - (do not remove this comment)
(script# 13)
(include sci.sh)
(use Main)
(use Print)
(use DCIcon)
(use Motion)
(use System)

(public
	aboutCode 0
)

(local
	theGForm
	local1
)
(procedure (localproc_0572 param1 param2 param3)
	(Print
		addText: 2 0 0 param1 param2 0 13
		addIcon: (theIcon1 loop: param3 yourself:)
		init:
	)
)

(instance aboutCode of Code
	(properties)
	
	(method (doit &tmp [temp0 300] [temp300 300])
		(= theGForm theCursor)
		(theGame setCursor: 999)
		(Load rsVIEW 993 989)
		(if
			(not
				(Print font: userFont addText: 1 0 0 1 0 0 13 init:)
			)
			(self dispose:)
			(return)
		)
		(if
			(not
				(Print font: userFont addText: 1 0 0 2 0 0 13 init:)
			)
			(self dispose:)
			(return)
		)
		(Message msgGET 13 1 0 0 3 @temp0)
		(Format @temp300 @temp0 version)
		(if (not (Print addText: @temp300 init:))
			(self dispose:)
			(return)
		)
		(if (not (Print addText: 1 0 0 4 0 0 13 init:))
			(self dispose:)
			(return)
		)
		(if (not (Print addText: 2 0 0 1 0 0 13 init:))
			(self dispose:)
			(return)
		)
		(if (not (Print addText: 2 0 0 2 0 0 13 init:))
			(self dispose:)
			(return)
		)
		(= local1 1)
		(if
			(not
				(Print
					addText: 2 0 0 3 0 0 13
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
					addText: 2 0 0 4 0 0 13
					addIcon: theIcon1 0 0 0 50
					addIcon: theIcon2 0 0 50 50
					addIcon: theIcon3 0 0 75 50
					init:
				)
			)
			(self dispose:)
			(return)
		)
		(if (not (localproc_0572 5 35 3))
			(self dispose:)
			(return)
		)
		(if (not (localproc_0572 6 35 3))
			(self dispose:)
			(return)
		)
		(= local1 0)
		(theIcon1 view: 989)
		(if (not (localproc_0572 31 35 1))
			(self dispose:)
			(return)
		)
		(theIcon1 view: 993)
		(if (not (localproc_0572 7 35 4))
			(self dispose:)
			(return)
		)
		(= local1 0)
		(theIcon1 view: 989)
		(if (not (localproc_0572 8 35 1))
			(self dispose:)
			(return)
		)
		(= local1 1)
		(theIcon1 view: 993)
		(if (not (localproc_0572 9 35 12))
			(self dispose:)
			(return)
		)
		(if (not (localproc_0572 10 35 5))
			(self dispose:)
			(return)
		)
		(= local1 0)
		(if (not (localproc_0572 11 45 6 0))
			(self dispose:)
			(return)
		)
		(= local1 1)
		(if (not (localproc_0572 12 35 7 1))
			(self dispose:)
			(return)
		)
		(if (not (localproc_0572 13 50 8))
			(self dispose:)
			(return)
		)
		(= local1 1)
		(if (not (localproc_0572 32 35 7 1))
			(self dispose:)
			(return)
		)
		(if (not (localproc_0572 14 35 9))
			(self dispose:)
			(return)
		)
		(if (not (localproc_0572 29 35 9))
			(self dispose:)
			(return)
		)
		(if (not (localproc_0572 15 35 10))
			(self dispose:)
			(return)
		)
		(= local1 0)
		(theIcon1 view: 989)
		(if (not (localproc_0572 16 50 0))
			(self dispose:)
			(return)
		)
		(= local1 1)
		(if (not (localproc_0572 17 35 1))
			(self dispose:)
			(return)
		)
		(= local1 1)
		(if (not (localproc_0572 30 35 1))
			(self dispose:)
			(return)
		)
		(if
			(not
				(Print
					addText: 2 0 0 18 40 0 13
					addIcon: (theIcon1 view: 989 loop: 2 yourself:) 0 0 0 0
					addIcon: (theIcon2 view: 989 loop: 3 yourself:) 0 0 130 0
					init:
				)
			)
			(self dispose:)
			(return)
		)
		(= local1 0)
		(if
			(not
				(Print
					addText: 2 0 0 19 0 0 13
					addIcon: (theIcon1 loop: 4 yourself:) 0 0 150 20
					init:
				)
			)
			(self dispose:)
			(return)
		)
		(if (not (localproc_0572 20 35 5))
			(self dispose:)
			(return)
		)
		(if (not (localproc_0572 21 35 6))
			(self dispose:)
			(return)
		)
		(if (not (localproc_0572 22 35 7))
			(self dispose:)
			(return)
		)
		(if (not (localproc_0572 23 35 8))
			(self dispose:)
			(return)
		)
		(if (not (localproc_0572 24 75 9))
			(self dispose:)
			(return)
		)
		(theIcon1 view: 993)
		(= local1 1)
		(if (not (localproc_0572 25 55 4))
			(self dispose:)
			(return)
		)
		(theIcon1 view: 989)
		(= local1 0)
		(if (not (localproc_0572 26 35 10))
			(self dispose:)
			(return)
		)
		(if (not (localproc_0572 27 35 5))
			(self dispose:)
			(return)
		)
		(= local1 1)
		(if (not (localproc_0572 28 35 11))
			(self dispose:)
			(return)
		)
		(self dispose:)
	)
	
	(method (dispose)
		(= normalCursor theGForm)
		(theGame setCursor: normalCursor)
		(DisposeScript 967)
		(DisposeScript 13)
	)
)

(instance theIcon1 of DCIcon
	(properties
		cycleSpeed 15
	)
	
	(method (init)
		(if local1
			((= cycler (Fwd new:)) init: self)
		else
			(= cel 0)
			((= cycler (End new:)) init: self)
		)
	)
)

(instance theIcon2 of DCIcon
	(properties
		cycleSpeed 15
	)
	
	(method (init)
		((= cycler (Fwd new:)) init: self)
	)
)

(instance theIcon3 of DCIcon
	(properties
		cycleSpeed 15
	)
	
	(method (init)
		((= cycler (Fwd new:)) init: self)
	)
)
