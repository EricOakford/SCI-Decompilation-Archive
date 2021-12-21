;;; Sierra Script 1.0 - (do not remove this comment)
(script# 64896)
(include sci.sh)

(public
	proc64896_0 0
	proc64896_1 1
)

(procedure (proc64896_0 param1 param2 param3 param4 &tmp temp0 temp1 temp2 temp3)
	(= temp3 0)
	(if (not (> argc 3))
		(= temp1 (param2 x?))
		(= temp2 (param2 y?))
		(if (== argc 3) (= temp3 param3))
	else
		(= temp1 param2)
		(= temp2 param3)
		(if (== argc 4) (= temp3 param4))
	)
	(= temp0
		(GetAngle (param1 x?) (param1 y?) temp1 temp2)
	)
	(param1 setHeading: temp0 temp3)
	(DisposeScript -640)
)

(procedure (proc64896_1 param1 param2 param3 param4 &tmp temp0 temp1 temp2)
	(if (or (< argc 2) (== param2 0))
		(= temp1 1)
	else
		(= temp1 param2)
	)
	(if (and (>= argc 4) param4)
		(= temp2 235)
	else
		(= temp2 187)
	)
	(if (or (== argc 0) (== param1 1))
		(= temp0 100)
		(while (> temp0 0)
			(Palette 2 0 temp2 temp0)
			(FrameOut)
			(= temp0 (- temp0 temp1))
		)
	else
		(= temp0 0)
		(while (< temp0 100)
			(Palette 2 0 temp2 temp0)
			(FrameOut)
			(= temp0 (+ temp0 temp1))
		)
	)
	(if (== param1 1)
		(Palette 2 0 temp2 0)
	else
		(Palette 2 0 temp2 100)
	)
	(if (and (== argc 3) param3) (param3 cue:))
	(DisposeScript -640)
)
