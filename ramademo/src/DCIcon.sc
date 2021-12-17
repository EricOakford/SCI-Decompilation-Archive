;;; Sierra Script 1.0 - (do not remove this comment)
;;;;
;;;;	DCICON.SC
;;;;
;;;;	(c) Sierra On-Line, Inc, 1993
;;;;
;;;;	Author: 	Unknown
;;;;	Updated:	Brian K. Hughes
;;;;
;;;;	A DCIcon is a cycling icon that can be used in Dialogs.
;;;;
;;;;	Classes:
;;;;		DCIcon


(script# DCICON)
(include game.sh)
(use DIcon)
(use Motion)


(class DCIcon 	kindof DIcon
	(properties
		cycler		0		; a cycler must be attached dynamically
		cycleSpeed	6		; 60ths second between cels. 
		signal		0		; just to satisfy cycler
	)
;;;	(methods
;;;		lastCel			; required by cycler class
;;;	)

	;;; Do not pass a caller to this cycler
	(method (init)
		((= cycler (Forward new:)) init: self)
	)

	;;; invoked at 60 times per second by the Dialog doit: loop
	(method (cycle &tmp oldCel)
		(if cycler
			;; remember current cel
			(= oldCel cel)
			(cycler doit:)
			
			;;; show new cel if it changed
			(if (!= cel oldCel)
				(self draw:)
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
