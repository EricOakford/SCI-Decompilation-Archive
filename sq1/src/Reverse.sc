;;; Sierra Script 1.0 - (do not remove this comment)
(script# REVERSE)
(use Motion)

(class Reverse kindof Cycle
	;;; Cycles client's cel constantly in reverse, wrapping to the last cel
	;;; in the client's loop when the cel goes below 0.
	
	(properties
		name "Rev"
		cycleDir -1
	)
	
	(method (doit &tmp newCel)
		(= newCel (self nextCel:))
		(if (< newCel 0)
			(self cycleDone:)
		else
			(client cel:newCel)
		)
	)
	
	
	(method (cycleDone)
		;; When 'done', reset to last cel and keep going.
		(client cel: (client lastCel:))
	)
)

