;;; Sierra Script 1.0 - (do not remove this comment)
(script# DCICON)
(use Main)
(use Intrface)
(use Motion)

(class DCIcon 	kindof DIcon
	;;; Cycling Icons are a sub-class of DIcon.
	;;; An instance of DCIcon may be passed to Print for use in
	;;; a dialog.

	;;; additional properties are required to 
	;;;allow cycling via the Cycler classes.
	(properties
		cycler 0		; a cycler must be attached dynamically
		;cycleSpeed 6	; 60ths second between cels. 
		cycleSpeed 16
		signal 0		; just to satisfy cycler
		count -1
	)

;;;	(methods
;;;		lastCel			; required by cycler class
;;;	)

	;;; Do not pass a caller to this cycler
	(method (init)
		((= cycler (Forward new:)) init: self)
	)

	;;; invoked at 60 times per second by the Dialog doit: loop
;;;	(method (cycle &tmp oldCel)
;;;		(if cycler
;;;			;; remember current cel
;;;			(= oldCel cel)
;;;			(cycler doit:)
;;;			
;;;			;;; show new cel if it changed
;;;			(if (!= cel oldCel)
;;;				(self draw:)
;;;			)
;;;		)
;;;	)
	(method (cycle &tmp oldCel i)
		(return
			(if cycler
				;; remember current cel
				(= oldCel cel)
				(cycler doit:)
				
				;;; show new cel if it changed
				(if (!= cel oldCel)
					(if (and (!= count -1) (> i count))
						(if global213
							(= cel 0)
						else
							(= cel (- (NumCels self) 1))
						)
					)
					(self draw:)
					(if (and (!= count -1) (== cel (- (NumCels self) 1)))
						(++ i)
					)
				)
			else
				0
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
