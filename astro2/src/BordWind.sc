;;; Sierra Script 1.0 - (do not remove this comment)
(script# BORDWIND)
(include game.sh)
(use Window)

;;;(procedure 
;;;	DrawBeveledWindow
;;;	PushOnScreen
;;;)

(class BorderWindow kindof	SysWindow
	(properties
		color				0		; foreground color
		back				5		; background color
		underBits 		0		; handle to saved visual map
		pUnderBits 		0		; handle to saved priority map

		topBordColor	7
		lftBordColor	6
		rgtBordColor	4
		botBordColor	3
		
		bevelWid			3
		shadowWid		2
		
		lsTop				0
		lsLeft			0
		lsBottom			0
		lsRight			0
		eraseOnly		FALSE
	)
	
	(method (open &tmp savePort wMap)
		(SetPort 0)

		(= wMap VMAP)
		(if (!= priority -1)
			(|= wMap PMAP)
		)
		(PushOnScreen self)
		
		(= lsTop (- top bevelWid))
		(= lsLeft (- left bevelWid))
		(= lsRight (+ right bevelWid shadowWid))
		(= lsBottom (+ bottom bevelWid shadowWid))
		
		(= underBits (Graph GSaveBits lsTop lsLeft lsBottom lsRight VMAP))
		(if (!= priority -1)
			(= pUnderBits (Graph GSaveBits lsTop lsLeft lsBottom lsRight PMAP))
		)

		(DrawBeveledWindow
			top left bottom right
			back topBordColor lftBordColor botBordColor rgtBordColor
			bevelWid
			shadowWid
			priority
			wMap
		)

		(Graph GShowBits lsTop lsLeft lsBottom lsRight VMAP)
 		(= type $81)
		(super open:)


		;**	reset the type property to indicate we're manually drawing it
 	)

	(method (dispose)
		(SetPort 0)
		(Graph GRestoreBits underBits)
		(Graph GRestoreBits pUnderBits)
		(if eraseOnly
			(Graph GShowBits lsTop lsLeft lsBottom lsRight VMAP)
		else
			(Graph GReAnimate lsTop lsLeft lsBottom lsRight)
		)
		(if window
			(DisposeWindow window eraseOnly)
			(= window 0)
		)
		(DisposeClone self)
	)
)

(class InsetWindow kindof BorderWindow
	(properties
		color				0
		ck				3

		topBordColor	5
		lftBordColor	4
		botBordColor	1
		rgtBordColor	2

		;; colors for inset
		insideColor		2
		topBordColor2	0
		lftBordColor2	1
		botBordColor2	5
		rgtBordColor2	4

		topBordHgt		10
		botBordHgt		24
		sideBordWid		2

		shadWid			0
		bevWid			2

		bevelWid			3
		shadowWid		2
		
		xOffset			0	; offset of inset window from port
		yOffset			0
	)

	(method (open &tmp wMap savePort saveTop saveLeft saveBot saveRight)
		(= wMap VMAP)
		(if (!= priority -1)
			(|= wMap PMAP)
		)
		
		(= saveTop top)
		(= saveLeft left)
		(= saveBot bottom)
		(= saveRight right)
		
		(-= top (+ bevelWid topBordHgt))
		(-= left (+ bevelWid sideBordWid))
		(+= bottom (+ bevelWid botBordHgt))
		(+= right (+ bevelWid sideBordWid))
		
		(= xOffset (+ bevelWid sideBordWid))
		(= yOffset (+ bevelWid topBordHgt))
		
		(super open:)
		
		(DrawBeveledWindow
			saveTop saveLeft saveBot saveRight
			insideColor topBordColor2 lftBordColor2 botBordColor2 rgtBordColor2
			bevWid
			shadWid
			priority
			wMap
		)

		(= savePort (GetPort))
		(SetPort 0)
		(Graph GShowBits
			(- saveTop bevWid) (- saveLeft bevWid)
			(+ saveBot bevWid) (+ saveRight bevWid)
			VMAP
		)
		(SetPort savePort)
	)
)

(procedure (DrawBeveledWindow
						t l b r
						theColor topColor leftColor bottomColor rightColor
						theBevelWid theShadowWid thePri theMaps
						&tmp savePort i
			  )
	(= savePort (GetPort))
	(SetPort 0)
	(Graph GFillRect t l (+ b 1) (+ r 1) theMaps theColor thePri)

	(-= t theBevelWid)
	(-= l theBevelWid)
	(+= r theBevelWid)
	(+= b theBevelWid)

	; draw top and bottom bevels
	(Graph GFillRect
		t l (+ t theBevelWid) r
		theMaps topColor thePri
	)
	(Graph GFillRect
		(- b theBevelWid) l b r
		theMaps bottomColor thePri
	)
	(for ((= i 0)) (< i theBevelWid) ((++ i))
		(Graph GDrawLine
			(+ t i) (+ l i) (- b (+ i 1)) (+ l i)
			leftColor thePri -1
		)
		(Graph GDrawLine
			(+ t i) (- r (+ i 1)) (- b (+ i 1)) (- r (+ i 1))
			rightColor thePri	-1
		)
	)
	
	(if theShadowWid
		(Graph GFillRect
			(+ t theShadowWid) r
			(+ b theShadowWid) (+ r theShadowWid)
			theMaps 0 thePri
		)
		(Graph GFillRect
			b (+ l theShadowWid)
			(+ b theShadowWid) r
			theMaps 0 thePri
		)
	)
	
	(SetPort savePort)
)

	(define TOPBORDER		10)
	(define LEFTBORDER	5)
	(define RIGHTBORDER	315)
	(define BOTTOMBORDER	185)

(procedure (PushOnScreen wind &tmp dX dY)
;;;	(define TOPBORDER		10)
;;;	(define LEFTBORDER	5)
;;;	(define RIGHTBORDER	315)
;;;	(define BOTTOMBORDER	185)
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
