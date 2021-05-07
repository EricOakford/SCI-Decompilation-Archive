;;; Sierra Script 1.0 - (do not remove this comment)
(script# 409)
(include game.sh)
(use Main)
(use Intrface)
(use DCIcon)
(use Motion)
(use Menu)
(use System)

(public
	FirstTimeCk 0
)

(instance FirstTimeCk of Script

	(method (doit &tmp [str 500])
		(super doit:)
		(DrawPic 992 IRISOUT)
		(cast eachElementDo: #hide)
		(= savedCursor ARROW_CURSOR)
		(theGame setCursor: ARROW_CURSOR TRUE)
		(cSound number: 17 loop: 1 play:)
		(if
			(Print 409 0
				#icon myIcon 0 1
				#mode teJustCenter
				#font SYSFONT
				#button {______Yes______} 0
				#button {_______No_______} 1
			)
			(cSound fade:)
			(curRoom newRoom: 777)
		else
			(cSound fade:)
			(TheMenuBar state: TRUE)
			(curRoom newRoom: 44)
		)
	)
	
	(method (dispose)
		(super dispose:)
		(DisposeScript 409)
	)
)

(instance myIcon of DCIcon
	(properties
		view 899
		cel 1
		cycleSpeed 24
	)
	
	(method (init)
		((= cycler (EndLoop new:)) init: self)
	)
)
