;;; Sierra Script 1.0 - (do not remove this comment)
(script# PROCS)
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
	(|= [gameFlags (/ flagEnum 16)] (>> $8000 (mod flagEnum 16)))
	(return oldState)
)

(procedure (Bclr flagEnum &tmp oldState)
	(= oldState (Btst flagEnum))
	(&= [gameFlags (/ flagEnum 16)] (~ (>> $8000 (mod flagEnum 16))))
	(return oldState)
)

(procedure (NextAct)
	(++ currentAct)
	(Bclr fBeenInBookstore)
	(Bclr fBeenInPawnshop)
	(Bclr fBeenAtCastleGate)
)

(procedure (Face who theObjOrX theY whoCares &tmp theHeading lookX lookY whoToCue)
	(= whoToCue 0)
	(if (IsObject theObjOrX)
		(= lookX (theObjOrX x?))
		(= lookY (theObjOrX y?))
		(if (== argc 3)
			(= whoToCue theY)
		)
	else
		(= lookX theObjOrX)
		(= lookY theY)
		(if (== argc 4)
			(= whoToCue whoCares)
		)
	)
	(= theHeading (GetAngle (who x?) (who y?) lookX lookY))
	(who
		setHeading: theHeading (if (IsObject whoToCue) whoToCue else 0)
	)
)
