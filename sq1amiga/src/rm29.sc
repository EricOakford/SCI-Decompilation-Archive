;;; Sierra Script 1.0 - (do not remove this comment)
(script# 29)
(include sci.sh)
(use Main)
(use Game)
(use Actor)
(use System)

(public
	rm29 0
)

(local
	[fallPts 18] = [149 101 127 75 158 107 146 146 112 147 134 133 122 157 113 159 109 155]
)
(instance rm29 of Rm
	(properties
		picture 29
		style $8007
	)
	
	(method (init)
		(super init: &rest)
		(self setScript: falling)
	)
)

(instance falling of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(theIconBar disable: hide:)
				(theGame setCursor: waitCursor 0)
				(ego2
					posn: [fallPts register] [fallPts (+ (= register 0) 1)]
					init:
				)
				(= ticks 18)
			)
			(1
				(if (< (= register (+ register 2)) 18)
					(ego2
						posn: [fallPts register] [fallPts (+ register 1)]
						setCel: (/ register 2)
					)
					(-- state)
					(= ticks 18)
				else
					(ego2 dispose:)
					(= ticks 18)
				)
			)
			(2
				(HandsOn)
				(theIconBar enable:)
				(theGame setCursor: normalCursor 1 160 100)
				(curRoom newRoom: 30)
				(self dispose:)
			)
		)
	)
)

(instance ego2 of Prop
	(properties
		view 129
		priority 15
		signal $0010
	)
)
