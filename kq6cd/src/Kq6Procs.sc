;;; Sierra Script 1.0 - (do not remove this comment)
(script# PROCS) ;913
(include game.sh)
(use Main)

(public
	Btst 0
	Bset 1
	Bclr 2
	NextAct 3
	Face 4
)

(procedure (Btst flagEnum)
	(return
		(&
			[gameFlags (/ flagEnum 16)]
			(>> $8000 (mod flagEnum 16))
		)
	)
)

(procedure (Bset flagEnum &tmp oldState)
	(= oldState (Btst flagEnum))
	(= [gameFlags (/ flagEnum 16)]
		(|
			[gameFlags (/ flagEnum 16)]
			(>> $8000 (mod flagEnum 16))
		)
	)
	(return oldState)
)

(procedure (Bclr flagEnum &tmp oldState)
	(= oldState (Btst flagEnum))
	(= [gameFlags (/ flagEnum 16)]
		(&
			[gameFlags (/ flagEnum 16)]
			(~ (>> $8000 (mod flagEnum 16)))
		)
	)
	(return oldState)
)

(procedure (NextAct)
	(++ currentAct)
	(Bclr fBeenInBookstore)
	(Bclr fBeenInPawnshop)
	(Bclr fBeenAtCastleGate)
)

(procedure (Face actor1 actor2 both whoToCue &tmp ang1To2 theX theY i)
	(= i 0)
	(if (IsObject actor2)
		(= theX (actor2 x?))
		(= theY (actor2 y?))
		(if (== argc 3) (= i both))
	else
		(= theX actor2)
		(= theY both)
		(if (== argc 4) (= i whoToCue))
	)
	(= ang1To2
		(GetAngle (actor1 x?) (actor1 y?) theX theY)
	)
	(actor1
		setHeading: ang1To2 (if (IsObject i) i else 0)
	)
)
