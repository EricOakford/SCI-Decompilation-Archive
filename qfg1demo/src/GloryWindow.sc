;;; Sierra Script 1.0 - (do not remove this comment)
(script# GLORY_WINDOW)
(include game.sh)
(use Main)
(use Window)
(use System)

(public
	GloryWindow 0
)

(define TOPBORDER		10)
(define LEFTBORDER	5)
(define RIGHTBORDER	315)
(define BOTTOMBORDER	185)

(class GloryWindow of SysWindow
	(properties
		color 66
		back 69
		underBits 0
		pUnderBits 0
		innerBordColor 68
		midBordColor 70
		outerBordColor 69
		yOffset 0
	)
	
	(method (open &tmp savePort wMap)
		(SetPort 0)
		(= wMap VMAP)
		(if (!= priority -1)
			(|= wMap PMAP)
		)
		
		(PushOnScreen self)
		
		(= top (- top yOffset))
		(= lsTop (- top 3))
		(= lsLeft (- left 20))
		(= lsRight (+ right 21))
		(= lsBottom (+ bottom 4))
		(= underBits
			(Graph GSaveBits lsTop lsLeft lsBottom lsRight 1)
		)
		(if (!= priority -1)
			(= pUnderBits
				(Graph GSaveBits lsTop lsLeft lsBottom lsRight 2)
			)
		)
		(DrawWindow
			top left bottom right
			back innerBordColor midBordColor outerBordColor
			priority
			wMap
		)
		(Graph GShowBits (- top 7) (- right 11) (+ top 13) (+ right 12) 1)
		(Graph GShowBits (- top 7) (- left 11) (+ top 13) (+ left 11) 1)
		(Graph GShowBits lsTop lsLeft lsBottom lsRight VMAP)
		(= bottom (+ bottom 4))
		(= right (+ right 4))
		(= type $83)
		(super open:)
	)
	
	(method (dispose)
		(= top (- top 3))
		(= right (- right 3))
		(SetPort 0)
		(Graph GRestoreBits underBits)
		(Graph GRestoreBits pUnderBits)
		(if eraseOnly
			(Graph GShowBits lsTop lsLeft lsBottom lsRight VMAP)
		else
			(Graph GReAnimate lsTop lsLeft lsBottom lsRight)
		)
		(Graph GShowBits (- top 7) (- right 9) (+ top 13) (+ right 15) VMAP)
		(Graph GShowBits (- top 7) (- left 11) (+ top 13) (+ left 9) VMAP)
		(if window
			(DisposeWindow window eraseOnly)
			(= window 0)
		)
		(DisposeClone self)
	)	
)

(procedure (DrawWindow t l b r theColor innerColor midColor outerColor thePri theMaps &tmp savePort theView)
	(= savePort (GetPort))
	(SetPort 0)
	(Graph GFillRect t (- l 13) (+ b 1) (+ r 14) theMaps theColor thePri)
	(Graph GDrawLine (- t 1) l (- t 1) r 69 thePri -1)
	(Graph GDrawLine (- t 2) l (- t 2) r 66 thePri -1)
	(Graph GDrawLine (- t 3) l (- t 3) r 67 thePri -1)
	(Graph GDrawLine (+ t 10) (- l 1) (- b 10) (- l 1) innerColor thePri -1)
	(Graph GDrawLine (+ t 10) (- l 2) (- b 10) (- l 2) midColor thePri -1)
	(Graph GDrawLine (+ t 10) (- l 13) (- b 10) (- l 13) 67 thePri -1)
	(Graph GDrawLine (+ t 10) (- l 12) (- b 10) (- l 12) 66 thePri -1)
	(Graph GDrawLine (+ t 10) (- l 11) (- b 10) (- l 11) 67 thePri -1)
	(Graph GDrawLine (+ t 10) (- l 10) (- b 10) (- l 10) 67 thePri -1)
	(Graph GDrawLine (+ t 10) (- l 9) (- b 10) (- l 9) midColor thePri -1)
	(Graph GDrawLine (+ t 10) (+ r 1) (- b 10) (+ r 1) innerColor thePri -1)
	(Graph GDrawLine (+ t 10) (+ r 2) (- b 10) (+ r 2) midColor thePri -1)
	(Graph GDrawLine (+ t 10) (+ r 13) (- b 10)(+ r 13) 67 thePri -1)
	(Graph GDrawLine (+ t 10) (+ r 12) (- b 10) (+ r 12) 66 thePri -1)
	(Graph GDrawLine (+ t 10) (+ r 11) (- b 10) (+ r 11) 67 thePri -1)
	(Graph GDrawLine (+ t 10) (+ r 10) (- b 10) (+ r 10) 67 thePri -1)
	(Graph GDrawLine (+ t 10) (+ r 9) (- b 10) (+ r 9) midColor thePri -1)
	(Graph GDrawLine (+ b 1) l (+ b 1) r 69 thePri -1)
	(Graph GDrawLine (+ b 2) l (+ b 2) r 66 thePri -1)
	(Graph GDrawLine (+ b 3) l (+ b 3) r 67 thePri -1)
	(if (OneOf currentPic pIntro pCharSel)
		(= theView 895)
	else
		(= theView vControlPanel)
	)
	(DrawCel theView 9 0 (- l 20) (- t 3) thePri)
	(DrawCel theView 9 1 (+ r 1) (- t 3) thePri)
	(DrawCel theView 9 2 (- l 20) (- b 9) thePri)
	(DrawCel theView 9 3 (+ r 1) (- b 9) thePri)
	(SetPort savePort)
)

(procedure (PushOnScreen wind &tmp dX dY)
	(= dY
		(cond 
			((> (wind bottom?) BOTTOMBORDER)
				(- BOTTOMBORDER (wind bottom?))
			)
			((< (wind top?) TOPBORDER)
				(- TOPBORDER (wind top?))
			)
			(else 0)
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
			(else 0)
		)
	)
	(wind
		top: (+ (wind top?) dY)
		bottom: (+ (wind bottom?) dY)
		left: (+ (wind left?) dX)
		right: (+ (wind right?) dX)
	)
)