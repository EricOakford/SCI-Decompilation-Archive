;;; Sierra Script 1.0 - (do not remove this comment)
;;;;
;;;;	STYLER.SC
;;;;
;;;;	(c) Sierra On-Line, Inc, 1993
;;;;
;;;;	Author: 	Brian K. Hughes
;;;;	Updated:	Greg Tomko-Pavia
;;;;
;;;;	Class which governs the showstyles, including mirroring, fading,
;;;;	and scrolling.  This class is most often used by Plane's drawPic
;;;;	method.
;;;;
;;;;	Classes:
;;;;		Styler
;;;;
;;;;  Procedures:
;;;;     ShakePlane
;;;;


(script# STYLER)
(include game.sh)
(use Main)
(use Array)
(use System)

(public
	ShakePlane	0
)

;;;(procedure
;;;	ShakePlane
;;;)


;
; ShakePlane is a procedure which duplicates some of the functionality
;		of the old ShakeScreen kernel call. While ShakeScreen is no longer
;		reliable (it can't work in windows, and doesn't work on some graphics
;		cards in DOS), this allows you to create vertical or horizontal shakes
;		of any magnitude and number
;
; Written by: Greg Tomko-Pavia
; Copyright (C) 1994 by Sierra On-Line
; 

; Examples
; (ShakePlane thePlane) -- 20 horizontal shakes of magnitude 2
; (ShakePlane thePlane (| SHAKE_HORIZONTAL SHAKE_VERTICAL) )
;								-- 20 shakes (both horizontal and vertical)
; (ShakePlane thePlane SHAKE_HORIZONTAL 100) -- 100 horizontal shakes
; (ShakePlane thePlane SHAKE_VERTICAL 20 6) 
;								-- 20 vertical shakes of magnitude 6 (plane moves
;									6 pixels up and down each shake)
;

(procedure (ShakePlane  aPlane 		; the plane to shake
								shakeDir		; optional: SHAKE_HORIZONTAL is default
								numShakes 	; optional: 20 shakes is default
								magnitude	; optional: 2 pixel amplitude is default
							  &tmp l t r b l1 t1 r1 b1  l2 t2 r2 b2 m n d)

	; set all shake specifications
	(= d (if (and (> argc 1) shakeDir) shakeDir else SHAKE_HORIZONTAL) )
	(= n (if (> argc 2) numShakes else 20) )
	(= m (if (> argc 3) magnitude else 2) )

	; read properties from the plane
	(= l (= l1 (= l2 (aPlane left?))))
	(= t (= t1 (= t2 (aPlane top?))))
	(= r (= r1 (= r2 (aPlane right?))))
	(= b (= b1 (= b2 (aPlane bottom?))))

	; set dimensions of the shake
	(if (& d SHAKE_HORIZONTAL)
		(-= l1 m)
		(-= r1 m)
		(+= l2 m)
		(+= r2 m)
	)
	(if (& d SHAKE_VERTICAL)
		(-= t1 m)
		(-= b1 m)
		(+= t2 m)
		(+= b2 m)
	)

	; time to rock 'n roll
	(for (TRUE) n ((-- n))
		(aPlane setRect: l1 t1 r1 b1 1)
		;(aPlane setRect: l1 t1 r1 b1)
		;(UpdatePlane aPlane)
		(FrameOut)
		(aPlane setRect: l2 t2 r2 b2 1)
		;(aPlane setRect: l2 t2 r2 b2)
		;(UpdatePlane aPlane)
		(FrameOut)
	)

	; put plane back to normal
	;(aPlane setRect: l t r b)
	(aPlane setRect: l t r b 1)
)


(class Styler kindof Object
	(properties
		style			SHOW_PLAIN	; Style in which to draw the pic
		plane			0				; Plane in which styling is done
		seconds		1				; Seconds to effect style
		back			-1				; Color to which to style (-1 is skip)
		priority		200			; Priority of style
		animate		FALSE			; TRUE if animation should be done while styling
		refFrame		FALSE			; Capture a reference frame first
		divisions	0				; # of divisions in style
		captureList	0				; List used for objects in cast
		pFadeArray	0				;SWAT addition
	)

	(method (init)
		(super init: &rest)
		(= pFadeArray (IntArray new: 0))
	)	

	(method (doit aPlane aStyle time aBack ani &tmp xDir yDir)
		(if argc
			(= plane aPlane)
			(if (> argc 1)
				(= style aStyle)
				(if (> argc 2)
					(= seconds time)
					(if (> argc 3)
						(= back aBack)
						(if (> argc 4)
							(= animate ani)
						else
							(= animate FALSE)
						)
					else
						(= back -1)
					)
				else
					(= seconds 1)
				)
			else
				(= style SHOW_PLAIN)
			)
		else
			(= plane thePlane)
		)

		; Turn off the non-style bits
		(&= style $00ff)

		; Call the appropriate style call
		(cond
			((OneOf style SHOW_SCROLL_LEFT SHOW_SCROLL_RIGHT SHOW_SCROLL_UP SHOW_SCROLL_DOWN)	;SHOW_SCROLLS
				(switch style
					(SHOW_SCROLL_LEFT
						(= xDir (* (ego xStep?) -1))
						(= yDir 0)
					)
					(SHOW_SCROLL_RIGHT
						(= xDir (ego xStep?))
						(= yDir 0)
					)
					(SHOW_SCROLL_UP
						(= xDir 0)
						(= yDir (* (ego yStep?) -1))
					)
					(SHOW_SCROLL_DOWN
						(= xDir 0)
						(= yDir (ego yStep?))
					)
				)
				(SetScroll plane xDir yDir (plane picture?) animate (ego cycleSpeed?))
			)
			(divisions
				(SetShowStyle style plane seconds back priority animate refFrame (pFadeArray data?) divisions)
			)
			(else
				(SetShowStyle style plane seconds back priority animate (pFadeArray data?) refFrame)
			)
		)
	)
	
	;SWAT methods
	(method (addInclusionRange aStart aEnd &tmp tSize)
		(if pFadeArray
			(= tSize (pFadeArray size:))
			(pFadeArray at: tSize aStart aEnd)
		)
	)
	
	(method (changeDivisions newDiv)
		(= divisions newDiv)
	)
)
