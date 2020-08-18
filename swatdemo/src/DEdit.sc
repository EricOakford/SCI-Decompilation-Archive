;;; Sierra Script 1.0 - (do not remove this comment)
;;;;
;;;;	DEDIT.SC
;;;;
;;;;	(c) Sierra On-Line, Inc, 1993
;;;;
;;;;	Author: 	Bob Heitman
;;;;	Updated:	Brian K. Hughes
;;;;
;;;;	The dialog edit field class.
;;;;
;;;;	Classes:
;;;;		DEdit


(script# DEDIT)
(include game.sh)
(use DText)
(use Array)


(class DEdit kindof DText
	;
	; A text field which is editable by the user.

	(properties
		type			dEdit
		state 		dActive
		width			0	  			; maximum number of characters allowed in field
		title			0
		titleFore	0
		titleBack	0
		titleFont	0
		frameOut		TRUE			; should a FrameOut be done after editing?
		lastKey		0				; last event during edit
		borderColor	0
	)

	(method (dispose)
		;
		; Overridden to tell DText that we don't want to kill the text

		(super dispose: TRUE)
	)

	(method (hilite tOrF &tmp origBM)
		(if (and argc tOrF)
			(= origBM bitmap)
			(= bitmap 0)
			(DeleteScreenItem self)
			(if (EditText self)
				(Bitmap MapDispose origBM)
				(self draw:)
			else
				(= bitmap origBM)
			)
		 	(AddScreenItem self)
		)
	)

	(method (setSize &tmp r margin w)
		;
		; Calculate the size of the nsRect if there is no bitmap associated
		;	with us.

 		(= margin (if (!= borderColor -1) 2 else 0))

		(= nsLeft 0)
		(= nsTop 0)
		(= textLeft margin)
		(= textTop	margin)
		(if (== view -1)
			(= r (IntArray with: 0 0 0 0))
			(Text TextSize
				(r data?)
				{M}
				font
				0
			)
			(= w (* (+ (r at: 2) 1) width))
			(= nsRight (+ nsLeft w (* margin 2)))
			(= nsBottom (+ nsTop (r at: 3) (- (* margin 2) 1)))
			(= textRight (+ textLeft w))
			(= textBottom (+ textTop (r at: 3)))
			(r dispose:)
		else
			(= nsRight (+ nsLeft (CelWide view loop cel)))
			(= nsBottom (+ nsTop (CelHigh view loop cel)))
			(= textRight (- nsRight margin))
			(= textBottom (- nsBottom margin))
		)
	)
)
