;;; Sierra Script 1.0 - (do not remove this comment)
(script# 878)
(include game.sh)
(use Main)
(use Window)


(class TalkerWindow of SysWindow
	
	(method (dispose)
		(super dispose: &rest)
		(if (not (HaveMouse))
			(theGame setCursor: global190 TRUE)
		)
	)
	
	(method (open &tmp savePort)
		(if (not (HaveMouse))
			(= global190 (theGame setCursor: INVIS_CURSOR TRUE))
		)
		(= top (- top (if title 19 else 0)))
		(= lsLeft (- left (/ (CelWide 905 1 0) 2)))
		(= lsTop (- top (/ (CelHigh 905 1 0) 2)))
		(= lsRight (+ right (/ (CelWide 905 1 0) 2)))
		(= lsBottom (+ bottom (/ (CelHigh 905 1 0) 2)))
		(= priority 15)
		(super open:)
		(= savePort (GetPort))
		(SetPort 0)
		(Graph GFillRect top left bottom right 3 back 15)
		(DrawCel 905 1 0 lsLeft lsTop -1)
		(DrawCel 905 1 1 (- lsRight (CelWide 905 1 0)) lsTop -1)
		(DrawCel 905 1 2 lsLeft (- lsBottom (CelHigh 905 1 0)) -1)
		(DrawCel 905 1 3
			(- lsRight (CelWide 905 1 0))
			(- lsBottom (CelHigh 905 1 0))
			-1
		)
		(Graph GDrawLine
			(+ lsTop (CelHigh 905 1 0))
			lsLeft
			(- lsBottom (CelHigh 905 1 0))
			lsLeft
			29
			15
		)
		(Graph GDrawLine
			(+ lsTop (CelHigh 905 1 0))
			(+ lsLeft 1)
			(- lsBottom (CelHigh 905 1 0))
			(+ lsLeft 1)
			25
			15
		)
		(Graph GDrawLine
			(+ lsTop (CelHigh 905 1 0))
			(+ lsLeft 2)
			(- lsBottom (CelHigh 905 1 0))
			(+ lsLeft 2)
			16
			15
		)
		(Graph GDrawLine
			(+ lsTop 16)
			(+ lsLeft 3)
			(- lsBottom 11)
			(+ lsLeft 3)
			back
			15
		)
		(Graph GDrawLine
			(+ lsTop 16)
			(+ lsLeft 4)
			(- lsBottom 11)
			(+ lsLeft 4)
			back
			15
		)
		(Graph GDrawLine
			(+ lsTop 15)
			(+ lsLeft 5)
			(- lsBottom 11)
			(+ lsLeft 5)
			back
			15
		)
		(Graph GDrawLine
			(+ lsTop 14)
			(+ lsLeft 6)
			(- lsBottom 11)
			(+ lsLeft 6)
			back
			15
		)
		(Graph GDrawLine
			(+ lsTop 11)
			(+ lsLeft 7)
			(- lsBottom 11)
			(+ lsLeft 7)
			back
			15
		)
		(Graph GDrawLine
			(+ lsTop 11)
			(+ lsLeft 8)
			(- lsBottom 11)
			(+ lsLeft 8)
			back
			15
		)
		(Graph GDrawLine
			(+ lsTop 11)
			(+ lsLeft 9)
			(- lsBottom 11)
			(+ lsLeft 9)
			back
			15
		)
		(Graph GDrawLine
			(+ lsTop (CelHigh 905 1 0))
			(- lsRight 1)
			(- lsBottom (CelHigh 905 1 0))
			(- lsRight 1)
			29
			15
		)
		(Graph GDrawLine
			(+ lsTop (CelHigh 905 1 0))
			(- lsRight 2)
			(- lsBottom (CelHigh 905 1 0))
			(- lsRight 2)
			25
			15
		)
		(Graph GDrawLine
			(+ lsTop (CelHigh 905 1 0))
			(- lsRight 3)
			(- lsBottom (CelHigh 905 1 0))
			(- lsRight 3)
			16
			15
		)
		(Graph GDrawLine
			(+ lsTop 16)
			(- lsRight 4)
			(- lsBottom 11)
			(- lsRight 4)
			back
			15
		)
		(Graph GDrawLine
			(+ lsTop 16)
			(- lsRight 5)
			(- lsBottom 11)
			(- lsRight 5)
			back
			15
		)
		(Graph GDrawLine
			(+ lsTop 15)
			(- lsRight 6)
			(- lsBottom 11)
			(- lsRight 6)
			back
			15
		)
		(Graph GDrawLine
			(+ lsTop 14)
			(- lsRight 7)
			(- lsBottom 11)
			(- lsRight 7)
			back
			15
		)
		(Graph GDrawLine
			(+ lsTop 11)
			(- lsRight 8)
			(- lsBottom 11)
			(- lsRight 8)
			back
			15
		)
		(Graph GDrawLine
			(+ lsTop 11)
			(- lsRight 9)
			(- lsBottom 11)
			(- lsRight 9)
			back
			15
		)
		(Graph GDrawLine
			(+ lsTop 11)
			(- lsRight 10)
			(- lsBottom 11)
			(- lsRight 10)
			back
			15
		)
		(Graph GDrawLine
			(- lsBottom 1)
			(+ lsLeft (CelHigh 905 1 0))
			(- lsBottom 1)
			(- lsRight (CelHigh 905 1 0))
			29
			15
		)
		(Graph GDrawLine
			(- lsBottom 2)
			(+ lsLeft (CelHigh 905 1 0))
			(- lsBottom 2)
			(- lsRight (CelHigh 905 1 0))
			25
			15
		)
		(Graph GDrawLine
			(- lsBottom 3)
			(+ lsLeft (CelHigh 905 1 0))
			(- lsBottom 3)
			(- lsRight (CelHigh 905 1 0))
			16
			15
		)
		(Graph GDrawLine
			(- lsBottom 4)
			(+ lsLeft 7)
			(- lsBottom 4)
			(- lsRight 8)
			back
			15
		)
		(Graph GDrawLine
			(- lsBottom 5)
			(+ lsLeft 7)
			(- lsBottom 5)
			(- lsRight 8)
			back
			15
		)
		(Graph GDrawLine
			(- lsBottom 6)
			(+ lsLeft 7)
			(- lsBottom 6)
			(- lsRight 8)
			back
			15
		)
		(Graph GDrawLine
			(- lsBottom 7)
			(+ lsLeft 6)
			(- lsBottom 7)
			(- lsRight 7)
			back
			15
		)
		(Graph GDrawLine
			(- lsBottom 8)
			(+ lsLeft 3)
			(- lsBottom 8)
			(- lsRight 4)
			back
			15
		)
		(Graph GDrawLine
			(- lsBottom 9)
			(+ lsLeft 3)
			(- lsBottom 9)
			(- lsRight 4)
			back
			15
		)
		(Graph GDrawLine
			(- lsBottom 10)
			(+ lsLeft 3)
			(- lsBottom 10)
			(- lsRight 4)
			back
			15
		)
		(Graph GDrawLine
			lsTop
			(+ lsLeft (CelHigh 905 1 0))
			lsTop
			(- lsRight (CelHigh 905 1 0))
			29
			15
		)
		(Graph GDrawLine
			(+ lsTop 1)
			(+ lsLeft (CelHigh 905 1 0))
			(+ lsTop 1)
			(- lsRight (CelHigh 905 1 0))
			25
			15
		)
		(Graph GDrawLine
			(+ lsTop 2)
			(+ lsLeft (CelHigh 905 1 0))
			(+ lsTop 2)
			(- lsRight (CelHigh 905 1 0))
			16
			15
		)
		(Graph GDrawLine
			(+ lsTop 3)
			(+ lsLeft 16)
			(+ lsTop 3)
			(- lsRight 17)
			back
			15
		)
		(Graph GDrawLine
			(+ lsTop 4)
			(+ lsLeft 16)
			(+ lsTop 4)
			(- lsRight 17)
			back
			15
		)
		(Graph GDrawLine
			(+ lsTop 5)
			(+ lsLeft 16)
			(+ lsTop 5)
			(- lsRight 17)
			back
			15
		)
		(Graph GDrawLine
			(+ lsTop 6)
			(+ lsLeft 15)
			(+ lsTop 6)
			(- lsRight 16)
			back
			15
		)
		(Graph GDrawLine
			(+ lsTop 7)
			(+ lsLeft 14)
			(+ lsTop 7)
			(- lsRight 15)
			back
			15
		)
		(Graph GDrawLine
			(+ lsTop 8)
			(+ lsLeft 12)
			(+ lsTop 8)
			(- lsRight 13)
			back
			15
		)
		(Graph GDrawLine
			(+ lsTop 9)
			(+ lsLeft 12)
			(+ lsTop 9)
			(- lsRight 13)
			back
			15
		)
		(Graph GShowBits lsTop lsLeft lsBottom lsRight VMAP)
		(SetPort savePort)
	)
)
