;;; Sierra Script 1.0 - (do not remove this comment)
(script# 403)
(include game.sh)
(use Main)
(use Window)


(local
	saveCursor
	saveCursorX
	saveCursorY
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
		priority -1
		lineColor 0
	)
	
	(method (open &tmp wMap t l b r cHigh cWide oldPort pnv)
		(self
			color:
			(switch numColors
				(256 8)
				(32 8)
				(else  vBLACK)
			)
			back:
			(switch numColors
				(256 23)
				(32 15)
				(else  vLGREY)
			)
			lineColor:
			(switch numColors
				(256 19)
				(32 9)
				(else  vRED)
			)
		)
		(= cHigh (CelHigh 944 0 0))
		(= cWide (CelWide 944 0 0))
		(localproc_0422 self)
		(= t (- top cHigh))
		(= l (- left cWide))
		(= b (+ bottom cHigh))
		(= r (+ right cWide))
		(= lsTop t)
		(= lsLeft l)
		(= lsBottom (+ b 2))
		(= lsRight (+ r 2))
		(= type wCustom)
		(super open:)
		(= wMap VMAP)
		(if (!= priority -1)
			(|= wMap PMAP)
		)
		(= oldPort (GetPort))
		(SetPort 0)
		(Graph GFillRect (+ t 2) (+ l 2) (+ b 2) (+ r 2) wMap 0 priority)
		(Graph GFillRect t l b r wMap back priority)
		(= pnv (PicNotValid))
		(PicNotValid TRUE)
		(DrawCel 944 0 0 l t -1)
		(DrawCel 944 0 1 l (- b cHigh) -1)
		(DrawCel 944 0 2 (- r cWide) t -1)
		(DrawCel 944 0 3 (- r cWide) (- b cHigh) -1)
		(PicNotValid pnv)
		(Graph GDrawLine t (+ l cWide) t (- r cWide) lineColor -1 -1)
		(Graph GDrawLine (+ t 2) (+ l cWide) (+ t 2) (- r cWide) lineColor -1 -1)
		(Graph GDrawLine (- b 1) (+ l cWide) (- b 1) (- r cWide) lineColor -1 -1)
		(Graph GDrawLine (- b 3) (+ l cWide) (- b 3) (- r cWide) lineColor -1 -1)
		(Graph GDrawLine (+ t cHigh) l (- b cHigh) l lineColor -1 -1)
		(Graph GDrawLine (+ t cHigh) (+ l 2) (- b cHigh) (+ l 2) lineColor -1 -1)
		(Graph GDrawLine (+ t cHigh) (- r 1) (- b cHigh) (- r 1) lineColor -1 -1)
		(Graph GDrawLine (+ t cHigh) (- r 3) (- b cHigh) (- r 3) lineColor -1 -1)
		(SetPort oldPort)
	)
)

(class KQ5Window of BertaWindow
	
	(method (dispose)
		(if (not (HaveMouse))
			(if (== theCursor waitCursor)
				(SetCursor theCursor TRUE saveCursorX saveCursorY)
			else
				(theGame setCursor: saveCursor TRUE)
			)
		)
		(super dispose:)
	)
	
	(method (open &tmp thePort)
		(super open:)
		(= thePort (GetPort))
		(SetPort 0)
		(Graph GShowBits lsTop lsLeft lsBottom lsRight VMAP)
		(SetPort thePort)
		(if (not (HaveMouse))
			(if (== theCursor waitCursor)
				(= saveCursorX mouseX)
				(= saveCursorY mouseY)
				(SetCursor theCursor TRUE 310 170)
			else
				(= saveCursor (theGame setCursor: invCursor TRUE))
			)
		)
	)
)
