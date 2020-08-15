;;; Sierra Script 1.0 - (do not remove this comment)
;;;;
;;;;	DTEXT.SC
;;;;
;;;;	(c) Sierra On-Line, Inc, 1993
;;;;
;;;;	Author: 	Bob Heitman
;;;;	Updated:	Brian K. Hughes
;;;;
;;;;	The dialog text item class.
;;;;
;;;;	Classes:
;;;;		DText


(script# DTEXT)
(include game.sh)
(use Main)
(use DItem)
(use Array)
(use System)


(class DText kindof DItem
	;;; A non-editable, generally non-selectable text field.

	(properties
		type			dText
		textLeft		0				; rectangle wherein text will be put
		textTop		0
		textRight	0
		textBottom	0
		text			0 				; the text in the field
		mode			teJustLeft	; possible alignment of text
		fore			0				; foreground color
		back			254			; background color
		skip			254			; skip color
		font			USERFONT		; font to use for print text
										;	teJustLeft		left justified
										;	teJustRight		right justified
										;	teJustCenter	center each line
		borderColor	-1				; border around text (-1 means no border)
		dimmed		0
		rects			NULL 			; pointer to array of rectangle points
	)
;;;	(methods
;;;		draw			; create the bitmap
;;;	)

	(method (init)
		;
		; Create the bitmap for the control

		(self draw:)
		(super init: &rest)
	)

	(method (dispose keepText &tmp bm)
		;
		; Dispose of the bitmap (if any) and rects.  If 'keepText' is passed
		;	and TRUE, don't free the text string

		(if (or (not argc) (not keepText))
			(String StrFree text)
		)
		(if rects
			(rects dispose:)
			(= rects 0)
		)
		(= bm 0)
		(if bitmap
			(= bm bitmap)
			(= bitmap 0)
		)
		(super dispose:)
		(if bm
			(Bitmap MapDispose bm)
		)
	)

	(method (new)
		(return
			((super new:)
				font: 		userFont,
				yourself:
			)
		)
	)

	(method (setSize w &tmp r margin theX theY theWidth)
		;
		; Calculate the size of the nsRect if there is no bitmap associated
		;	with us.  If 'w' is present it is the fixed width of the text
		;	rectangle.

 		(= margin (if (!= borderColor -1) 3 else 0))

		; The width is either 'w', the width of my bitmap (if any), or 0
		;	(which defaults to 190)
		(= theWidth
			(cond
				((!= view -1)
					(- (CelWide view loop cel) (* margin 2))
				)
				(argc
					w
				)
				(else
					0
				)
			)
		)
			
		; Start by calculating the textRect as the size of the text
		(= textLeft 0)
		(= textTop 0)
		(= r (IntArray with: 0 0 0 0))
		(Text TextSize
			(r data?)
			text
			font
			theWidth
		)
		(= textRight (+ textLeft (r at: 2)))
		(= textBottom (+ textTop (r at: 3)))
		(r dispose:)

		; Now calculate the nsRect by either adding a margin (if no bitmap)
		;	or by the size of the bitmap
		(= nsLeft 0)
		(= nsTop 0)
		(if (== view -1)
			(+= textLeft margin)
			(+= textTop margin)
			(+= textRight margin)
			(+= textBottom margin)
			(= nsRight (+ textRight margin))
			(= nsBottom (+ textBottom margin))
		else
			(= theX (CelWide view loop cel))
			(= theY (CelHigh view loop cel))
			(= nsRight (+ nsLeft (- theX 1)))
			(= nsBottom (+ nsTop (- theY 1)))

			; Center the textRect within the nsRect
			(= margin (/ (- theX (+ (- textRight textLeft) 1)) 2))
			(+= textLeft margin)
			(+= textRight margin)
			(= margin (/ (- theY (+ (- textBottom textTop) 1)) 2))
			(+= textTop margin)
			(+= textBottom margin)
		)
	)

	(method (handleEvent event &tmp rect t l b r)
		;
		; Check for the existence of rectangles, indicating interactive text.
		;	If found, see if the event is in a rectangle, and if so pass that
		;	rectangle's number to any textCode that may exist.

 	 	(if (and	textCode
					rects
					(or	(OneOf (event type?) mouseDown joyDown)
							(and	(== (event type?) keyDown)
									(== (event message?) ENTER)
							)
					)
			)
 	 		(= rect -1)

 	 		(event
				globalize:	,
				claimed:		TRUE
			)
			(while (!= (rects at: (+ rect 1)) $7777)
				(= l (rects at: (++ rect)))
				(= t (rects at: (++ rect)))
				(= r (rects at: (++ rect)))
				(= b (rects at: (++ rect)))
				(if (and	(<= l (event x?) r)
							(<= t (event y?) b)
					)
					(textCode doit: (/ rect 4))
					(event
						type:		nullEvt,
						claimed: FALSE
					)
					(break)
				)
			)
		)
		(super handleEvent: event)
	)

	(method (draw &tmp oldBM)
		(if (= oldBM bitmap)
			(DeleteScreenItem self)
			(Bitmap MapDispose bitmap)
		)
		(= bitmap
			(if (!= view -1)
				(CreateTextBitmap TBMWithBitmap self)
			else
				(CreateTextBitmap
					TBMWithoutBitmap
					(+ (- nsRight nsLeft) 1)
					(+ (- nsBottom nsTop) 1)
					self
				)
			)
		)
		(if oldBM
			(AddScreenItem self)
		)
	)
)