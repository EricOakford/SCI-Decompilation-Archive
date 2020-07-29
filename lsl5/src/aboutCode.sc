;;; Sierra Script 1.0 - (do not remove this comment)
(script# ABOUT)
(include game.sh)
(use Main)
(use DCIcon)
(use Motion)
(use System)

(public
	aboutCode 0
)

(instance aboutCode of Code
	
	(method (doit &tmp [str 200])
		(Load VIEW 993)
		(TimePrint 15 0
			#font smallFont
			#mode teJustCenter
			#width 222
			#title {Sierra Presents}
		)
		(cycleIcon loop: 2)
		(TimePrint 15 1
			#font smallFont
			#mode teJustCenter
			#icon cycleIcon
			#width 180
			#title {The Bosses}
		)
		(cycleIcon loop: 3)
		(TimePrint 15 2
			#font smallFont
			#mode teJustCenter
			#icon cycleIcon
			#width 200
			#title {The Workers}
		)
		(cycleIcon loop: 1)
		(TimePrint 15 3
			#font smallFont
			#mode teJustCenter
			#icon cycleIcon
			#width 200
			#title {The Boys in the Band}
		)
		(cycleIcon loop: 0)
		(Format @str 15 4 version buildDate)
		(TimePrint @str
			#font smallFont
			#mode teJustCenter
			#icon cycleIcon
			#width 222
			#title {The Hackers}
		)
		(Format @str 15 5 usPhone intPhone)
		(TimePrint @str #font smallFont
			#mode teJustCenter
			#width 222
			#title {A Paid Commercial Announcement}
		)
		(TimePrint 15 6
			#font smallFont
			#mode teJustCenter
			#width 266
			#title {More Laffer Laughs}
		)
		(TimePrint 15 7
			#font smallFont
			#mode teJustCenter
			#width 234
			#title {Discontinued Products}
		)
	)
)

(instance cycleIcon of DCIcon
	(properties
		view 993
		cycleSpeed 15
	)
	
	(method (init)
		((= cycler (Forward new:)) init: self)
	)
)
