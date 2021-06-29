;;; Sierra Script 1.0 - (do not remove this comment)
(script# 889)
(include game.sh)
(use TwistyWindow)


(procedure (DrawTheWindow
				t l b r theColor topColor leftColor bottomColor rightColor
				theBevelWid theShadowWid thePri theMaps
				&tmp savePort i)
	(= savePort (GetPort))
	(SetPort 0)
	(Graph GFillRect t l (+ b 1) (+ r 1) theMaps theColor thePri)
	(-= t theBevelWid)
	(-= l theBevelWid)
	(+= r theBevelWid)
	(+= b theBevelWid)
	(Graph GFillRect t l (+ t theBevelWid) r theMaps topColor thePri)
	(Graph GFillRect (- b theBevelWid) l b r theMaps bottomColor thePri)
	(for ((= i 0)) (< i theBevelWid) ((++ i))
		(Graph GDrawLine (+ t i) (+ l i) (- b (+ i 1)) (+ l i) leftColor thePri -1)
		(Graph GDrawLine (+ t i) (- r (+ i 1)) (- b (+ i 1)) (- r (+ i 1)) rightColor thePri -1)
	)
	(if theShadowWid
		(Graph GFillRect (+ t theShadowWid) r (+ b theShadowWid) (+ r theShadowWid) theMaps 0 thePri)
		(Graph GFillRect b (+ l theShadowWid) (+ b theShadowWid) r theMaps 0 thePri)
	)
	(SetPort savePort)
)

(class TwistyInsetWindow of TwistyWindow
	(properties
		ck 3
		topBordColor 5
		lftBordColor 4
		botBordColor 1
		rgtBordColor 2
		insideColor 2
		topBordColor2 0
		lftBordColor2 1
		botBordColor2 5
		rgtBordColor2 4
		topBordHgt 10
		botBordHgt 24
		sideBordWid 2
		shadWid 0
		bevWid 2
		bevelWid 3
		shadowWid 2
		xOffset 0
		yOffset 0
	)
	
	(method (open param1 &tmp wMap savePort t l b r temp6)
		(if (and argc param1)
			(= temp6 1)
		else
			(= temp6 0)
		)
		(= wMap VMAP)
		(if (!= priority -1)
			(|= wMap PMAP)
		)
		(= t top)
		(= l left)
		(= b bottom)
		(= r right)
		(= top (- top (+ bevelWid topBordHgt)))
		(= left (- left (+ bevelWid sideBordWid)))
		(= bottom (+ bottom bevelWid botBordHgt))
		(= right (+ right bevelWid sideBordWid))
		(= xOffset (+ bevelWid sideBordWid))
		(= yOffset (+ bevelWid topBordHgt))
		(if (not temp6)
			(super open:)
		)
		(DrawTheWindow
			t
			l
			b
			r
			insideColor
			topBordColor2
			lftBordColor2
			botBordColor2
			rgtBordColor2
			bevWid
			shadWid
			priority
			wMap
		)
		(= savePort (GetPort))
		(SetPort 0)
		(Graph GShowBits (- t bevWid) (- l bevWid) (+ b bevWid) (+ r bevWid) VMAP)
		(SetPort savePort)
	)
)
