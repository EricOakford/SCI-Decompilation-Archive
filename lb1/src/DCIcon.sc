;;; Sierra Script 1.0 - (do not remove this comment)
(script# DCICON)
(use Intrface)
(use Main)
(use Motion)

(class DCIcon 	kindof DIcon
	;;; Cycling Icons are a sub-class of DIcon.
	;;; An instance of DCIcon may be passed to Print for use in
	;;; a dialog.

	;;; additional properties are required to 
	;;;allow cycling via the Cycler classes.
	(properties
		cycler 0		; a cycler must be attached dynamically
		cycleSpeed 6	; 60ths second between cels. 
		signal 0		; just to satisfy cycler
		count -1		;LB-specific property
	)

;;;	(methods
;;;		lastCel			; required by cycler class
;;;	)

	;;; Do not pass a caller to this cycler
;;;	(method (init)
;;;		((= cycler (Forward new:)) init: self)
;;;	)

	;;; invoked at 60 times per second by the Dialog doit: loop
	(method (cycle &tmp oldCel temp1)
		;LB-specific code
		(if cycler
			(= oldCel cel)
			(cycler doit:)
			(if (!= cel oldCel)
				(if (and (!= count -1) (> temp1 count))
					(if theTalker
						(= cel 0)
					else
						(= cel (- (NumCels self) 1))
					)
				)
				(self draw:)
				(if (and (!= count -1) (== cel (- (NumCels self) 1)))
					(++ temp1)
					(if
						(and
							theTalker
							(> (NumLoops self) 1)
							(or loop (< (Random 1 100) 51))
						)
						(= loop (^ loop $0001))
					)
				)
			)
		)
	)
	;;; A completion type cycler may have already disposed of itself
	(method (dispose)
		(if cycler
			(cycler dispose:)
		)
		(super dispose:)
	)
	
	;; Return the number of the last cel in the current loop of this object.
	;; this method is called by cycler		
	(method (lastCel)
		(return (- (NumCels self) 1))
	)
)
