;;; Sierra Script 1.0 - (do not remove this comment)
;******************************************************
;***
;*** DODISP.SC
;***
;***	 This is a class that handles displays with
;***	 backdrops.  It handles the underbits and the
;***	 such.
;***
;***										  Robert W. Lindsley
;***
;******************************************************

(script#	DODISP)
(include game.sh)
(use Main)
(use System)


(class DoDisplay kindof Object
	(properties
		saveBits		0
		saveBits2	0
		x				-1
		y				-1
		front			-1
		back			-1
		width			300
		font			NULL
	)
;;;	(methods
;;;		restore
;;;		posn
;;;		setColor
;;;		addText
;;;	)
	(method (posn theX theY)
		(= x theX)
		(= y theY)
	)
	(method (addText theText &tmp theX theY)
		(if (not font)
			(= font userFont)
		)
		(if (or saveBits saveBits2)
			(self restore:)
		)
		(= theX x)
		(= theY y)			 
		(if (not (== back -1))
			(= saveBits
				(Display theText 
					p_at (+ theX 1) theY 
					p_font	font
					p_width	width
					p_color back
					p_save
				)
			)
		)
		(= saveBits2
			(Display theText 
				p_at theX theY
				p_font	font
				p_width	width
				p_color front
				p_save
			)
		)
		(= font 0)
	)
	(method (setColor f b)
		(= front f)
		(= back b)
	)
	(method (restore)
		(= width 300)
		(Display {} p_restore	saveBits2)
		(Display {} p_restore	saveBits)
	)
)
