;;; Sierra Script 1.0 - (do not remove this comment)
;;;;
;;;;	DBUTTON.SC
;;;;
;;;;	(c) Sierra On-Line, Inc, 1993
;;;;
;;;;	Author: 	Bob Heitman
;;;;	Updated:	Brian K. Hughes
;;;;
;;;;	The dialog button class.
;;;;
;;;;	Classes:
;;;;		DButton


(script# DBUTTON)
(include game.sh)
(use Intrface)
(use DText)
(use System)


(class DButton kindof DText
	(properties
		state 			(| dActive dExit)
		fore				255
		back				0
		borderColor		0
		font				SYSFONT
		mode				teJustCenter
	)
	(method (init)
		;
		; Overridden to determine if border color should be used

		(if (!= view -1)
			(= borderColor -1)
			(= back skip)
		)
		(super init: &rest)
	)

	(method (hilite bool)
		;
		; Overridden to change border color
		
		(if (and argc bool)
			(|= state dSelected)
			(cond
				((!= view -1)
					(= cel 1)
				)
				((!= borderColor -1)
					(= borderColor fore)
				)
			)
		else
			(&= state (~ dSelected))
			(cond
				((!= view -1)
					(= cel 0)
				)
				((!= borderColor -1)
					(= borderColor 0)
				)
			)
		)
		(self draw:)
		(UpdateScreenItem self)
	)

	(method (setSize &tmp r w1 w2 h1 h2 margin)
		;
		; Overridden to size the button on even 16-pixel widths

		(super setSize: &rest)
;		(= r (* (/ (+ (- nsRight nsLeft) 15) 16) 16))
;		(= nsRight (+ r nsLeft))
;
;		; Now center the textRect within the nsRect
;		(= w1 (+ (- textRight textLeft) 1))
;		(= h1 (+ (- textBottom textTop) 1))
;		(= w2 (+ (- nsRight nsLeft) 1))
;		(= h2 (+ (- nsBottom nsTop) 1))
;		(= margin (/ (- w2 w1) 2))
;		(= textLeft margin)
;		(= textRight (+ textLeft w1))
;		(= margin (/ (- h2 h1) 2))
;		(= textTop margin)
;		(= textBottom (+ textTop h1))
	)

	(method (draw)
		(= dimmed (not (& state dActive)))
		(super draw:)
	)

 	(method (track event &tmp in lastIn)
		;; Track control to confirm selection.
		;; NOTE: Only a mouseDown requires a mouse track.

		(if (== mouseDown (event type?))
			(= lastIn 0)
			(repeat
				(= event (Event new: leaveIt))
				(event localize: plane)
				(= in (self onMe: event))
				(if (!= in lastIn)
					(if in
						(= cel 2)
						(++ textTop)
						(++ textBottom)
					else
						(= cel 0)
						(-- textTop)
						(-- textBottom)
					)
					(self draw:)
					(UpdateScreenItem self)
					(FrameOut)
					(= lastIn in)
				)
				(event dispose:)
				(breakif (not (StillDown)))
			)

			(if in
 				(-- textTop)
 				(-- textBottom)
			)
			(self hilite: (& state dSelected))
;			(if in
;				(= cel 2)
;				(Bitmap MapDispose bitmap)
;				(self draw:)
;				(UpdateScreenItem self)
;			)
			(return (if in self else 0))
		else
			(return self)
		)
	)
)