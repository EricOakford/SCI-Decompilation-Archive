;;; Sierra Script 1.0 - (do not remove this comment)
(script# SPEAKWIN)
(include game.sh)
(use LoadMany)
(use Window)


(class SpeakWindow of SysWindow
	(properties
		priority -1
		hMargin 0
		vMargin 0
		tailTop 0
		tailLeft 0
		tailBottom 0
		tailRight 0
		tailBits 0
		tailX 0
		tailY 0
		isBottom 1
		xOffset 0
		underBits1 0
		underBits2 0
	)
	
	(method (init)
		(super init: &rest)
		(LoadMany RES_VIEW 993)
	)
	
	(method (dispose &tmp savePort)
		(super dispose: &rest)
		(= savePort (GetPort))
		(SetPort 0)
		(Graph GRestoreBits underBits1)
		(Graph GShowBits tailTop tailLeft tailBottom tailRight VMAP)
		(SetPort savePort)
	)
	
	(method (open &tmp [temp0 2] temp2 t l b r savePort theMaps temp9)
		(SetPort 0)
		(= priority 15)
		(= color 0)
		(= back 6)
		(= theMaps VMAP)
		(if (!= priority -1) (= theMaps (| theMaps $0002)))
		(= temp2 (- right left))
		(= temp9 (- bottom top))
		(cond 
			((not xOffset) (= t 2))
			((< 0 xOffset) (= t 0))
			(else (= t 1))
		)
		(if (not isBottom)
			(= t (+ t 3))
			(= lsTop (+ tailY (CelHigh 993 0 t)))
			(= top (+ lsTop 3))
			(= bottom (+ top temp9))
			(= lsBottom (+ bottom 3))
		else
			(= lsBottom tailY)
			(= bottom (- lsBottom 3))
			(= top (- bottom temp9))
			(= lsTop (- top 3))
		)
		(= lsLeft
			(- (= l (+ (- tailX (/ temp2 2)) xOffset)) 3)
		)
		(= left l)
		(= right (+ left temp2))
		(= lsRight (+ 4 right))
		(= type 128)
		(= tailTop tailY)
		(= tailLeft tailX)
		(= tailBottom
			(+ tailY (if isBottom -2 else 1) (CelHigh 993 0 t))
		)
		(= tailRight (+ tailX (CelWide 993 0 t)))
		(= underBits1
			(Graph
				GSaveBits
				tailTop
				tailLeft
				tailBottom
				tailRight
				theMaps
			)
		)
		(super open: &rest)
		(= savePort (GetPort))
		(SetPort 0)
		(Graph
			GFillRect
			(- top 3)
			(+ left 3)
			(+ bottom 2)
			(- right 3)
			theMaps
			back
			priority
		)
		(Graph
			GFillRect
			(+ top 3)
			(- left 3)
			(- bottom 3)
			(+ right 3)
			theMaps
			back
			priority
		)
		(DrawCel 993 1 0 (- left 3) (- top 3) priority)
		(DrawCel
			993
			1
			1
			(+ (- right (CelWide 993 1 0)) 3)
			(- top 3)
			priority
		)
		(DrawCel
			993
			1
			3
			(+ (- right (CelWide 993 1 0)) 3)
			(+ (- bottom (CelHigh 993 1 0)) 3)
			priority
		)
		(DrawCel
			993
			1
			2
			(- left 3)
			(+ (- bottom (CelHigh 993 1 0)) 3)
			priority
		)
		(= temp2 6)
		(= temp9 3)
		(Graph
			GDrawLine
			lsTop
			(+ left temp2)
			(- top temp9)
			(- right temp2)
			7
			-1
			-1
		)
		(Graph
			GDrawLine
			(+ top (* temp9 2))
			lsLeft
			(- bottom (* temp9 2))
			lsLeft
			7
			-1
			-1
		)
		(Graph
			GDrawLine
			(+ top (* temp9 2))
			(- lsRight 2)
			(- bottom (* temp9 2))
			(- lsRight 2)
			5
			-1
			-1
		)
		(Graph
			GDrawLine
			(- lsBottom 2)
			(+ left temp2)
			(- lsBottom 2)
			(- right temp2)
			4
			-1
			-1
		)
		(Graph
			GDrawLine
			(- lsBottom 1)
			(+ left temp2)
			(- lsBottom 1)
			(- right temp2)
			0
			-1
			-1
		)
		(DrawCel
			993
			0
			t
			tailX
			(+ tailY (if isBottom -2 else 1))
			priority
		)
		(Graph GShowBits lsTop lsLeft lsBottom lsRight VMAP)
		(Graph GReAnimate
			tailY
			tailX
			(+ tailY (if isBottom -2 else 1) (CelHigh 993 0 t))
			(+ tailX (CelWide 993 0 t))
		)
		(SetPort savePort)
	)
	
	(method (move x y)
		(= top (+ top y))
		(= left (+ left x))
		(= bottom (+ bottom y))
		(= right (+ right x))
	)
	
	(method (moveTo x y)
		(self move: (- x left) (- y top))
	)
	
	(method (repos x y)
		(= top (+ top y))
		(= left (+ left x))
		(= bottom (- bottom y))
		(= right (- right x))
	)
)
