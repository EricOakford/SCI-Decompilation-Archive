;;; Sierra Script 1.0 - (do not remove this comment)
(script# 64017)
(include sci.sh)
(use Main)
(use PArray)
(use Flags)

(public
	oFlags 0
)

(class oFlagsClass of Flags
	(properties
		scratch 0
		size 0
		array 0
		oFlagValues 0
		oScoreFlags 0
	)
	
	(method (init)
		(= oScoreFlags (Flags new:))
		(oScoreFlags setSize: 35)
		(= oFlagValues (PArray new:))
		(super init: &rest)
	)
	
	(method (dispose)
		(oScoreFlags dispose:)
		(oFlagValues dispose:)
		(super dispose: &rest)
	)
	
	(method (set param1 &tmp temp0 temp1 temp2 temp3)
		(super set: param1 &rest)
		(= temp0 0)
		(while (< temp0 argc)
			(if (< [param1 temp0] (oFlagValues size:))
				(= temp2 (oFlagValues at: [param1 temp0]))
				(= temp3 (oScoreFlags test: [param1 temp0]))
				(if (and temp2 (not temp3))
					(theGame changeScore: temp2)
					(oScoreFlags set: [param1 temp0])
				)
			)
			(= temp0 (+ temp0 1))
		)
	)
	
	(method (clear param1 &tmp temp0)
		(super clear: param1 &rest)
		(== temp0 0)
		(while (< temp0 argc)
			(= temp0 (+ temp0 1))
		)
	)
	
	(method (unSet param1 &tmp temp0 temp1 temp2)
		(super clear: param1 &rest)
		(= temp0 0)
		(while (< temp0 argc)
			(= temp1 (oFlagValues at: [param1 temp0]))
			(= temp2 (oScoreFlags test: [param1 temp0]))
			(if (and temp1 temp2)
				(theGame changeScore: (- 0 temp1))
				(oScoreFlags clear: [param1 temp0])
			)
			(= temp0 (+ temp0 1))
		)
	)
	
	(method (getScore param1)
		(oFlagValues at: param1)
	)
)

(instance oFlags of oFlagsClass
	(properties
		size 35
	)
	
	(method (init)
		(super init: &rest)
		(oFlagValues add:)
	)
)
