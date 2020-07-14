;;; Sierra Script 1.0 - (do not remove this comment)
(script# 912)
(include game.sh)
(use Window)


(class Kq6Window of SysWindow
	(properties
		color 34
		back 44
		colorOne 32
		colorFive 18
		tlColorTwo 17
		tlColorThree 18
		tlColorFour 17
		brColorTwo 18
		brColorThree 17
		brColorFour 16
	)
	
	(method (open &tmp temp0 temp1)
		(= temp0 1)
		(if (!= priority -1) (= temp0 (| temp0 $0002)))
		(= lsTop (- top 5))
		(= lsLeft (- left 5))
		(= lsRight (+ right 6))
		(= lsBottom (+ bottom 6))
		(= type 128)
		(= priority 15)
		(super open:)
		(= temp1 (GetPort))
		(SetPort 0)
		(self drawEdgedWindow: temp0)
		(DrawCel 930 0 0 (- left 5) (- top 5) -1)
		(DrawCel 930 0 1 (- left 5) (- bottom 1) -1)
		(DrawCel 930 0 2 (- right 1) (- top 5) -1)
		(DrawCel 930 0 3 (- right 1) (- bottom 1) -1)
		(Graph GShowBits lsTop lsLeft lsBottom lsRight 1)
		(SetPort temp1)
	)
	
	(method (drawEdgedWindow param1 &tmp [temp0 2] temp2 temp3)
		(Graph
			GFillRect
			top
			left
			(+ bottom 1)
			(+ right 1)
			param1
			back
			priority
		)
		(= temp2 1)
		(while (< temp2 6)
			(= temp3
				(switch temp2
					(1 colorOne)
					(2 tlColorTwo)
					(3 tlColorThree)
					(4 tlColorFour)
					(5 colorFive)
				)
			)
			(Graph
				GDrawLine
				(- top temp2)
				(- left temp2)
				(- top temp2)
				(+ right temp2)
				temp3
				priority
				-1
			)
			(Graph
				GDrawLine
				(- top temp2)
				(- left temp2)
				(+ bottom temp2)
				(- left temp2)
				temp3
				priority
				-1
			)
			(++ temp2)
		)
		(= temp2 1)
		(while (< temp2 6)
			(= temp3
				(switch temp2
					(1 colorOne)
					(2 brColorTwo)
					(3 brColorThree)
					(4 brColorFour)
					(5 colorFive)
				)
			)
			(Graph
				GDrawLine
				(+ bottom temp2)
				(- left temp2)
				(+ bottom temp2)
				(+ right temp2)
				temp3
				priority
				-1
			)
			(Graph
				GDrawLine
				(- top temp2)
				(+ right temp2)
				(+ bottom temp2)
				(+ right temp2)
				temp3
				priority
				-1
			)
			(++ temp2)
		)
	)
)
