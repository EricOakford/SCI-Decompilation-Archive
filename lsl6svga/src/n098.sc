;;; Sierra Script 1.0 - (do not remove this comment)
(script# 98)
(include sci.sh)
(use Main)
(use fileScr)
(use Print)
(use System)

(public
	proc98_0 0
)

(procedure (proc98_0 &tmp theTheCursor)
	(= theTheCursor theCursor)
	(theGame setCursor: normalCursor)
	(sounds eachElementDo: #pause)
	(SetCursor 275 130)
	(switch
		(Print
			font: userFont
			posn: -1 80
			addTitle: 1 0 1 1 98
			addText: 1 0 0 1 50 3 98
			addIcon: 1910 0 0 0 0
			addButton: 100 1 0 2 1 50 33 98
			addButton: -1 1 0 3 1 93 33 98
			addButton: 110 1 0 4 1 128 33 98
			addButton: 200 1 0 5 1 153 33 98
			addButton: 400 1 0 7 1 183 33 98
			addButton: 300 1 0 6 1 229 33 98
			init:
		)
		(100 (theGame restore:))
		(110 (curRoom newRoom: 130))
		(200 (curRoom newRoom: 120))
		(300
			(cond 
				((theMusic handle?) (theMusic fade:))
				((theMusic2 handle?) (theMusic2 fade:))
			)
			(curRoom newRoom: 200)
		)
		(400
			(if
			(and (not (OneOf curRoomNum 100 110 120)) (Btst 105))
				(global208 show:)
				(Bclr 105)
			else
				(global208 hide:)
				(Bset 105)
			)
		)
	)
	(theGame setCursor: theTheCursor 1)
	(sounds eachElementDo: #pause 0)
	(UnLoad 128 1910)
	(DisposeScript 98)
)
