;;; Sierra Script 1.0 - (do not remove this comment)
(script# 87)
(include sci.sh)
(use Main)
(use KQ5Room)

(public
	rm087 0
)

(instance rm087 of KQ5Room
	(properties
		picture 946
	)
	
	(method (init)
		(super init:)
		(self overlay: 87)
		(ego posn: 154 149 init:)
	)
	
	(method (doit &tmp temp0)
		(cond 
			(script (script doit:))
			((& (ego onControl: 0) $4000) (curRoom newRoom: 46))
		)
	)
	
	(method (handleEvent event &tmp [temp0 100])
		(cond 
			((event claimed?) (return))
			(script (return))
		)
	)
)
