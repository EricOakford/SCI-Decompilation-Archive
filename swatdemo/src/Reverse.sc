;;; Sierra Script 1.0 - (do not remove this comment)
;;;;
;;;;	REVERSE.SC
;;;;
;;;;	(c) Sierra On-Line, Inc, 1993
;;;;
;;;;	Author: 	Unknown
;;;;	Updated:	Greg Tomko-Pavia
;;;;
;;;;	Cycles client's cel constantly in reverse, wrapping to the last cel
;;;;	in the client's loop when the cel goes below 0.
;;;;
;;;;	Classes:
;;;;		Reverse


(script# REVERSE)
(include game.sh)
(use Motion)


(class Reverse kindof Cycle
	(properties
		name "Rev"
		cycleDir -1
	)
	
	(method (doit &tmp newCel)
		(if 
				(!= (client cel?) (= newCel (self nextCel:)))
				;Do nothing if no change, avoids updating every cycle
			(if (< newCel 0)
				(self cycleDone:)
			else
				(client cel: newCel)
			)
		)
	)
	
	
	(method (cycleDone)
		;; When 'done', reset to last cel and keep going.
		(client cel: (client lastCel:))
	)
)
