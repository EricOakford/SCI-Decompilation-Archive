;;; Sierra Script 1.0 - (do not remove this comment)
(script# 22)
(include game.sh)
(use Main)
(use Window)

(public
	GloryWindow 0
)

	(define TOPBORDER		10)
	(define LEFTBORDER	5)
	(define RIGHTBORDER	315)
	(define BOTTOMBORDER	185)

(local
	local0
)
(procedure (DrawBeveledWindow t l b r theColor topColor theBevelWid theShadowWid thePri theMaps &tmp savePort)
	(= savePort (GetPort))
	(SetPort 0)
	(Graph GFillRect (- t 3) (- l 20) (+ b 4) (+ r 21) theMaps 57 thePri)
	(Graph GDrawLine (- t 1) (+ l 1) (- t 1) (- r 1) 58 -1 -1)
	(Graph GDrawLine (- t 3) l (- t 3) r 21 -1 -1)
	(Graph GDrawLine (+ t 10) (- l 2) (- b 10) (- l 2) 58 -1 -1)
	(Graph GDrawLine (- t 2) (- l 20) (+ b 3) (- l 20) 21 -1 -1)
	(Graph GDrawLine (+ t 3) (- l 18) (- b 3) (- l 18) 58 -1 -1)
	(Graph GDrawLine (+ t 10) (- l 14) (- b 10) (- l 14) 19 -1 -1)
	(Graph GDrawLine (+ t 10) (- l 13) (- b 10) (- l 13) 20 -1 -1)
	(Graph GDrawLine (+ t 10) (- l 12) (- b 10) (- l 12) 21 -1 -1)
	(Graph GDrawLine (+ t 10) (- l 11) (- b 10) (- l 11) 22 -1 -1)
	(Graph GDrawLine (+ t 10) (- l 10) (- b 10) (- l 10) 23 -1 -1)
	(Graph GDrawLine (+ t 10) (- l 9) (- b 10) (- l 9) 22 -1 -1)
	(Graph GDrawLine (+ t 10) (- l 8) (- b 10) (- l 8) 21 -1 -1)
	(Graph GDrawLine (+ t 10) (- l 7) (- b 10) (- l 7) 20 -1 -1)
	(Graph GDrawLine (+ t 10) (- l 6) (- b 10) (- l 6) 56 -1 -1)
	(Graph GDrawLine (+ t 10) (+ r 2) (- b 10) (+ r 2) 58 -1 -1)
	(Graph GDrawLine (- t 2) (+ r 20) (+ b 3) (+ r 20) 21 -1 -1)
	(Graph GDrawLine (+ t 3) (+ r 18) (- b 3) (+ r 18) 58 -1 -1)
	(Graph GDrawLine (+ t 10) (+ r 14) (- b 10) (+ r 14) 19 -1 -1)
	(Graph GDrawLine (+ t 10) (+ r 13) (- b 10) (+ r 13) 20 -1 -1)
	(Graph GDrawLine (+ t 10) (+ r 12) (- b 10) (+ r 12) 21 -1 -1)
	(Graph GDrawLine (+ t 10) (+ r 11) (- b 10) (+ r 11) 22 -1 -1)
	(Graph GDrawLine (+ t 10) (+ r 10) (- b 10) (+ r 10) 23 -1 -1)
	(Graph GDrawLine (+ t 10) (+ r 9) (- b 10) (+ r 9) 22 -1 -1)
	(Graph GDrawLine (+ t 10) (+ r 8) (- b 10) (+ r 8) 21 -1 -1)
	(Graph GDrawLine (+ t 10) (+ r 7) (- b 10) (+ r 7) 20 -1 -1)
	(Graph GDrawLine (+ t 10) (+ r 6) (- b 10) (+ r 6) 56 -1 -1)
	(Graph GDrawLine (+ b 1) (+ l 1) (+ b 1) (- r 1) 58 -1 -1)
	(Graph GDrawLine (+ b 3) l (+ b 3) r 21 -1 -1)
	(DrawCel 935 0 0 (- l 20) (- t 3) -1)
	(DrawCel 935 0 1 (+ r 1) (- t 3) -1)
	(DrawCel 935 0 2 (- l 20) (- b 9) -1)
	(DrawCel 935 0 3 (+ r 1) (- b 9) -1)
	(SetPort savePort)
)


(procedure (PushOnScreen wind &tmp dX dY)
	;; top
	(= dY 
		(cond
			((> (wind bottom?) BOTTOMBORDER)	
				(- BOTTOMBORDER (wind bottom?))
			)
			((< (wind top?) TOPBORDER)
				(- TOPBORDER (wind top?))
			)
			(else
				0
			)
		)
	)
	(= dX
		(cond
			((> (wind right?) RIGHTBORDER)
				(- RIGHTBORDER (wind right?))
			)
			((< (wind left?) LEFTBORDER)
				(- LEFTBORDER (wind left?))
			)
			(else
				0
			)
		)
	)
	(wind 
		top:		(+ (wind top?) 	dY),
		bottom:	(+ (wind bottom?) dY),
		left:		(+ (wind left?) 	dX),
		right:	(+ (wind right?) 	dX)
	)
)

(class GloryWindow of SysWindow
	(properties
		color 17
		back 57
		priority 15
		underBits 0
		pUnderBits 0
		innerBordColor 21
		midBordColor 58
		outerBordColor 53
		yOffset 0
		updateList 0
	)

	(method (dispose)
		(-= top 3)
		(-= right 3)
		(SetPort 0)
		(Graph GRestoreBits underBits)
		(Graph GRestoreBits pUnderBits)
		(if eraseOnly
			(Graph GShowBits lsTop lsLeft lsBottom lsRight 1)
		else
			(Graph GReAnimate lsTop lsLeft lsBottom lsRight)
		)
		(if window
			(DisposeWindow window eraseOnly)
			(= window 0)
		)
		(if (and (== gGloryWindow_2 self) palVaryInfo)
			(PalVary PALVARYPAUSE 0)
			(= gGloryWindow_2 0)
			(Bclr fFlag121)
			(= palVaryInfo 0)
		)
		(DisposeClone self)
	)
	
	(method (open &tmp savePort wMap [temp2 3])
		(SetPort 0)
		(= wMap VMAP)
		(if (!= priority -1)
			(|= wMap PMAP)
		)
		(PushOnScreen self)
		(-= top yOffset)
		(= lsTop (- top 3))
		(= lsLeft (- left 20))
		(= lsRight (+ right 21))
		(= lsBottom (+ bottom 4))
		(= underBits
			(Graph GSaveBits lsTop lsLeft lsBottom lsRight VMAP)
		)
		(if (!= priority -1)
			(= pUnderBits (Graph GSaveBits lsTop lsLeft lsBottom lsRight PMAP))
		)
		(DrawBeveledWindow top left bottom right back innerBordColor midBordColor outerBordColor priority wMap)
		(Graph GShowBits lsTop lsLeft lsBottom lsRight VMAP)
		(+= bottom 4)
		(+= right 4)
		(= type 131)
		(if (not palVaryInfo)
			(= palVaryInfo (PalVary PALVARYINFO))
		)
		(if
			(and
				(!= palVaryInfo 0)
				(!= palVaryInfo 64)
				(not (Btst fFlag121))
			)
			(= gGloryWindow_2 self)
			(Bset fFlag121)
			(PalVary PALVARYPAUSE 1)
		)
		(super open:)
	)
)
