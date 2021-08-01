;;; Sierra Script 1.0 - (do not remove this comment)
(script# 403)
(include sci.sh)
(use Main)
(use Window)


(local
	local0
	theGPEventX_2
	theGPEventY_2
)
(procedure (localproc_0422 param1 &tmp temp0 temp1)
	(= temp1
		(cond 
			((> (param1 bottom?) 178) (- 178 (param1 bottom?)))
			((< (param1 top?) 18) (- 18 (param1 top?)))
			(else 0)
		)
	)
	(= temp0
		(cond 
			((> (param1 right?) 307) (- 307 (param1 right?)))
			((< (param1 left?) 13) (- 13 (param1 left?)))
			(else 0)
		)
	)
	(param1
		top: (+ (param1 top?) temp1)
		bottom: (+ (param1 bottom?) temp1)
		left: (+ (param1 left?) temp0)
		right: (+ (param1 right?) temp0)
	)
)

(class BertaWindow of SysWindow
	(properties
		top 0
		left 0
		bottom 0
		right 0
		color 0
		back 15
		priority -1
		window 0
		type $0000
		title 0
		brTop 0
		brLeft 0
		brBottom 190
		brRight 320
		lsTop 0
		lsLeft 0
		lsBottom 0
		lsRight 0
		eraseOnly 0
		lineColor 0
	)
	
	(method (open &tmp temp0 theLsTop theLsLeft temp3 temp4 temp5 temp6 temp7 temp8)
		(self
			color:
			(switch numColors
				(256 8)
				(32 8)
				(else  0)
			)
			back:
			(switch numColors
				(256 23)
				(32 15)
				(else  7)
			)
			lineColor:
			(switch numColors
				(256 19)
				(32 9)
				(else  4)
			)
		)
		(= temp5 (CelHigh 944 0 0))
		(= temp6 (CelWide 944 0 0))
		(localproc_0422 self)
		(= theLsTop (- top temp5))
		(= theLsLeft (- left temp6))
		(= temp3 (+ bottom temp5))
		(= temp4 (+ right temp6))
		(= lsTop theLsTop)
		(= lsLeft theLsLeft)
		(= lsBottom (+ temp3 2))
		(= lsRight (+ temp4 2))
		(= type 128)
		(super open:)
		(= temp0 1)
		(if (!= priority -1) (= temp0 (| temp0 $0002)))
		(= temp7 (GetPort))
		(SetPort 0)
		(Graph
			grFILL_BOX
			(+ theLsTop 2)
			(+ theLsLeft 2)
			(+ temp3 2)
			(+ temp4 2)
			temp0
			0
			priority
		)
		(Graph
			grFILL_BOX
			theLsTop
			theLsLeft
			temp3
			temp4
			temp0
			back
			priority
		)
		(= temp8 (PicNotValid))
		(PicNotValid 1)
		(DrawCel 944 0 0 theLsLeft theLsTop -1)
		(DrawCel 944 0 1 theLsLeft (- temp3 temp5) -1)
		(DrawCel 944 0 2 (- temp4 temp6) theLsTop -1)
		(DrawCel 944 0 3 (- temp4 temp6) (- temp3 temp5) -1)
		(PicNotValid temp8)
		(Graph
			grDRAW_LINE
			theLsTop
			(+ theLsLeft temp6)
			theLsTop
			(- temp4 temp6)
			lineColor
			-1
			-1
		)
		(Graph
			grDRAW_LINE
			(+ theLsTop 2)
			(+ theLsLeft temp6)
			(+ theLsTop 2)
			(- temp4 temp6)
			lineColor
			-1
			-1
		)
		(Graph
			grDRAW_LINE
			(- temp3 1)
			(+ theLsLeft temp6)
			(- temp3 1)
			(- temp4 temp6)
			lineColor
			-1
			-1
		)
		(Graph
			grDRAW_LINE
			(- temp3 3)
			(+ theLsLeft temp6)
			(- temp3 3)
			(- temp4 temp6)
			lineColor
			-1
			-1
		)
		(Graph
			grDRAW_LINE
			(+ theLsTop temp5)
			theLsLeft
			(- temp3 temp5)
			theLsLeft
			lineColor
			-1
			-1
		)
		(Graph
			grDRAW_LINE
			(+ theLsTop temp5)
			(+ theLsLeft 2)
			(- temp3 temp5)
			(+ theLsLeft 2)
			lineColor
			-1
			-1
		)
		(Graph
			grDRAW_LINE
			(+ theLsTop temp5)
			(- temp4 1)
			(- temp3 temp5)
			(- temp4 1)
			lineColor
			-1
			-1
		)
		(Graph
			grDRAW_LINE
			(+ theLsTop temp5)
			(- temp4 3)
			(- temp3 temp5)
			(- temp4 3)
			lineColor
			-1
			-1
		)
		(SetPort temp7)
	)
)

(class KQ5Window of BertaWindow
	(properties
		top 0
		left 0
		bottom 0
		right 0
		color 0
		back 15
		priority -1
		window 0
		type $0000
		title 0
		brTop 0
		brLeft 0
		brBottom 190
		brRight 320
		lsTop 0
		lsLeft 0
		lsBottom 0
		lsRight 0
		eraseOnly 0
		lineColor 0
	)
	
	(method (dispose)
		(if (not (HaveMouse))
			(if (== theCursor waitCursor)
				(SetCursor theCursor 1 theGPEventX_2 theGPEventY_2)
			else
				(theGame setCursor: local0 1)
			)
		)
		(super dispose:)
	)
	
	(method (open &tmp temp0)
		(super open:)
		(= temp0 (GetPort))
		(SetPort 0)
		(Graph grUPDATE_BOX lsTop lsLeft lsBottom lsRight 1)
		(SetPort temp0)
		(if (not (HaveMouse))
			(if (== theCursor waitCursor)
				(= theGPEventX_2 mouseX)
				(= theGPEventY_2 mouseY)
				(SetCursor theCursor 1 310 170)
			else
				(= local0 (theGame setCursor: invCursor 1))
			)
		)
	)
)
