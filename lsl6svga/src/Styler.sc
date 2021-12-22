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
(use System)


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
			((OneOf style SHOW_SCROLL_LEFT SHOW_SCROLL_RIGHT SHOW_SCROLL_UP SHOW_SCROLL_DOWN) ;SHOW_SCROLLS)
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
				(SetShowStyle style plane seconds back priority animate refFrame divisions)
			)
			(else
				(SetShowStyle style plane seconds back priority animate refFrame)
			)
		)
	)
)