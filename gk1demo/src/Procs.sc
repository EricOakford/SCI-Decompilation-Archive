;;; Sierra Script 1.0 - (do not remove this comment)
(script# PROCS)
(include game.sh)
(use Main)

(public
	Btst 0
	Bset 1
	Bclr 2
	Face 3
)

(procedure (Btst flagEnum)
	(& [gameFlags (/ flagEnum 16)] (>> $8000 (mod flagEnum 16)))
	(DisposeScript PROCS)
)

(procedure (Bset flagEnum &tmp oldState)
	(= oldState (Btst flagEnum))
	(|= [gameFlags (/ flagEnum 16)] (>> $8000 (mod flagEnum 16)))
	oldState
	(DisposeScript PROCS)
)

(procedure (Bclr flagEnum &tmp oldState)
	(= oldState (Btst flagEnum))
	(&= [gameFlags (/ flagEnum 16)] (~ (>> $8000 (mod flagEnum 16))))
	oldState
	(DisposeScript PROCS)
)

(procedure (Face actor1 actor2 both whoToCue &tmp ang1To2 theX theY obj)
	(= obj 0)
	(if (IsObject actor2)
		(= theX (actor2 x?))
		(= theY (actor2 y?))
		(if (== argc 3)
			(= obj both)
		)
	else
		(= theX actor2)
		(= theY both)
		(if (== argc 4)
			(= obj whoToCue)
		)
	)
	(= ang1To2
		(GetAngle (actor1 x?) (actor1 y?) theX theY)
	)
	(actor1
		setHeading: ang1To2 (if (IsObject obj) obj else 0)
	)
	(DisposeScript PROCS)
)
