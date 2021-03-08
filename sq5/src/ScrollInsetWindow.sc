;;; Sierra Script 1.0 - (do not remove this comment)
(script# SCROLLINSET)
(include game.sh)
(use BordWind)


(procedure (DrawBeveledWindow
				t l b r theColor topColor leftColor bottomColor rightColor
				theBevelWid theShadowWid thePri theMaps &tmp savePort i
			)
	(= savePort (GetPort))
	(SetPort 0)
	(Graph GFillRect
		t l (+ b 1) (+ r 1)
		theMaps theColor thePri
	)
	(= t (- t theBevelWid))
	(= l (- l theBevelWid))
	(= r (+ r theBevelWid))
	(= b (+ b theBevelWid))
	(Graph GFillRect
		t l (+ t theBevelWid) r theMaps topColor thePri
	)
	(Graph GFillRect
		(- b theBevelWid) l b r theMaps bottomColor thePri
	)
	(= i 0)
	(while (< i theBevelWid)
		(Graph GDrawLine
			(+ t i) (+ l i) (- b (+ i 1)) (+ l i)
			leftColor thePri -1
		)
		(Graph GDrawLine
			(+ t i) (- r (+ i 1)) (- b (+ i 1)) (- r (+ i 1))
			rightColor thePri -1
		)
		(++ i)
	)
	(if theShadowWid
		(Graph GFillRect
			(+ t theShadowWid) r (+ b theShadowWid) (+ r theShadowWid)
			theMaps 0 thePri
		)
		(Graph GFillRect
			b (+ l theShadowWid) (+ b theShadowWid) r
			theMaps 0 thePri
		)
	)
	(SetPort savePort)
)

(class ScrollInsetWindow of BorderWindow
	(properties
		topBordColor 5
		lftBordColor 4
		rgtBordColor 2
		botBordColor 1
		bevelWid 3
		shadowWid 2
		ck 3
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
		xOffset 0
		yOffset 0
	)
	
	(method (open param1 &tmp wMap savePort saveTop saveLeft saveBot saveRight temp6)
		(if (and argc param1)
			(= temp6 1)
		else
			(= temp6 0)
		)
		(= wMap VMAP)
		(if (!= priority -1) (= wMap (| wMap $0002)))
		(= saveTop top)
		(= saveLeft left)
		(= saveBot bottom)
		(= saveRight right)
		(= top (- top (+ bevelWid topBordHgt)))
		(= left (- left (+ bevelWid sideBordWid)))
		(= bottom (+ bottom bevelWid botBordHgt))
		(= right (+ right bevelWid sideBordWid))
		(= xOffset (+ bevelWid sideBordWid))
		(= yOffset (+ bevelWid topBordHgt))
		(if (not temp6) (super open:))
		(DrawBeveledWindow
			saveTop
			saveLeft
			saveBot
			saveRight
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
		(Graph GShowBits
			(- saveTop bevWid)
			(- saveLeft bevWid)
			(+ saveBot bevWid)
			(+ saveRight bevWid)
			1
		)
		(SetPort savePort)
	)
)
