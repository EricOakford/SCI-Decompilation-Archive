;;; Sierra Script 1.0 - (do not remove this comment)
(script# DCICON)
(include game.sh)
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
		cycleSpeed 16	; 60ths second between cels. 
		signal 0		; just to satisfy cycler
		;KQ5 properties
		count -1
		talker 0
		loops 0		
	)

;;;	(methods
;;;		lastCel			; required by cycler class
;;;	)

	;;; Do not pass a caller to this cycler
	(method (init)
		(cond 
			((== cycler -1)
				(= cycler 0)
			)
			(cycler
				((= cycler (cycler new:)) init: self)
			)
			(else
				((= cycler (Forward new:)) init: self)
			)
		)
		(= loops 0)
	)

	;;; invoked at 60 times per second by the Dialog doit: loop
	(method (cycle &tmp oldCel)
		(if cycler
			;; remember current cel
			(= oldCel cel)
			(cycler doit:)
			
			;;; show new cel if it changed
			(if (!= cel oldCel)
				(if (and (!= count -1) (> loops count))
					(if talker
						(= loop (= cel 0))
					else
						(= cel (self lastCel:))
					)
				)
				(self draw:)
				(if
					(and
						(!= count -1)
						(== cel (self lastCel:))
						(<= loops count)
					)
					(++ loops)
					(if
						(and
							talker
							(> (NumLoops self) 1)
							(or loop (< (Random 1 100) 51))
						)
						(^= loop $0001)
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

	(method (setCel)
	)
	
	(method (startUpd)
	)	
)
