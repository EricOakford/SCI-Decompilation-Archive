;;; Sierra Script 1.0 - (do not remove this comment)
(script# 10)
(include sci.sh)
(use Main)
(use Feature)
(use Game)
(use System)

(public
	rgCrown 0
	proc10_2 2
	rocks 4
)

(procedure (proc10_2 param1 param2)
	(if (curRoom script?)
		((curRoom script?)
			setScript: param1 (curRoom script?) (if (> argc 1) param2 else 0)
		)
	else
		(curRoom
			setScript: param1 0 (if (> argc 1) param2 else 0)
		)
	)
)

(class rgCrown of Rgn
	(properties
		script 0
		number 0
		modNum -1
		noun 0
		timer 0
		keep 0
		initialized 0
		flag1 0
		flag2 0
		singSingHas 0
	)
	
	(method (init)
		(super init: &rest)
	)
	
	(method (newRoom n)
		(= keep
			(OneOf n 200 210 220 230 240 250 260 270 280 290)
		)
		(= initialized 0)
		(super newRoom: n &rest)
	)
	
	(method (isSet param1 param2 &tmp [temp0 2])
		(return (if (< argc 2) (& flag1 param1) else (& flag2 param2)))
	)
	
	(method (clrIt param1 param2 &tmp [temp0 2])
		(if (< argc 2)
			(= flag1 (& flag1 (~ param1)))
		else
			(= flag2 (& flag2 (~ param2)))
		)
	)
	
	(method (setIt param1 param2 &tmp [temp0 2])
		(if (< argc 2)
			(= flag1 (| flag1 param1))
		else
			(= flag2 (| flag2 param2))
		)
	)
)

(instance rocks of Feature
	(properties
		noun 2
	)
	
	(method (init)
		(super init: &rest)
		(= sightAngle 26505)
	)
	
	(method (doVerb theVerb)
		(if (OneOf theVerb 1 5 2)
			(messager
				say: noun theVerb (if (== theVerb 1) 1 else 0) 0 0 0
			)
		else
			(messager say: noun 0 0 0 0 0)
		)
	)
)
