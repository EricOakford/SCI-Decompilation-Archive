;;; Sierra Script 1.0 - (do not remove this comment)
(script# 705)
(include game.sh)
(use Main)
(use Intrface)
(use Talker)
(use Actor)
(use System)

(public
	launchBayScript 0
	printGuard 1
)

(instance launchBayScript of Script
	(method (dispose)
		(super dispose: &rest)
		(DisposeScript 705)
	)
	
	(method (changeState newState &tmp guardT guardBust guardMouth guardEyes egoT egoBust egoMouth egoEyes [temp8 2])
		(if (< (ego x?) 160)
			(= guardT guardTl)
			(= guardBust guardBustl)
			(= guardMouth guardMouthl)
			(= guardEyes guardEyesl)
			(= egoT egoTl)
			(= egoBust egoBustl)
			(= egoMouth egoMouthl)
			(= egoEyes egoEyesl)
		else
			(= guardT guardTr)
			(= guardBust guardBustr)
			(= guardMouth guardMouthr)
			(= guardEyes guardEyesr)
			(= egoT egoTr)
			(= egoBust egoBustr)
			(= egoMouth egoMouthr)
			(= egoEyes egoEyesr)
		)
		(if (> (ego y?) 120)
			(egoT nsTop: 1 y: 3)
			(guardT nsTop: 1 y: 3)
		else
			(guardT nsTop: 85 y: 96)
			(egoT nsTop: 85 y: 96)
		)
		(switch (= state newState)
			(0
				(HandsOff)
				(guardT
					init: guardBust guardMouth guardEyes 170 10 0 0 self
				)
			)
			(1
				(guardT
					init: guardBust guardMouth guardEyes 170 11 0 1 self
				)
			)
			(2
				(egoT
					init: egoBust egoMouth egoEyes 170 12 0 1 self
				)
			)
			(3
				(= seconds 1)
			)
			(4
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance printGuard of Script
	(method (dispose)
		(super dispose: &rest)
		(DisposeScript 705)
	)
	
	(method (changeState newState &tmp guardT guardBust guardMouth guardEyes egoT egoBust egoMouth egoEyes [temp8 2])
		(if (< (ego x?) 160)
			(= guardT guardTl)
			(= guardBust guardBustl)
			(= guardMouth guardMouthl)
			(= guardEyes guardEyesl)
			(= egoT egoTl)
			(= egoBust egoBustl)
			(= egoMouth egoMouthl)
			(= egoEyes egoEyesl)
		else
			(= guardT guardTr)
			(= guardBust guardBustr)
			(= guardMouth guardMouthr)
			(= guardEyes guardEyesr)
			(= egoT egoTr)
			(= egoBust egoBustr)
			(= egoMouth egoMouthr)
			(= egoEyes egoEyesr)
		)
		(if (> (ego y?) 120)
			(egoT nsTop: 1 y: 3)
			(guardT nsTop: 1 y: 3)
		else
			(guardT nsTop: 85 y: 96)
			(egoT nsTop: 85 y: 96)
		)
		(switch register
			(0
				(switch (= state newState)
					(0
						(HandsOff)
						(Face ego client self)
					)
					(1
						(egoT
							init: egoBust egoMouth egoEyes 170 0 0 1 self
						)
					)
					(2
						(guardT
							init: guardBust guardMouth guardEyes 170 1 0 1 self
						)
					)
					(3
						(= seconds 1)
					)
					(4
						(HandsOn)
						(self dispose:)
					)
				)
			)
			(1
				(switch (= state newState)
					(0
						(HandsOff)
						(Face ego client self)
					)
					(1
						(egoT
							init: egoBust egoMouth egoEyes 170 2 0 1 self
						)
					)
					(2
						(guardT
							init: guardBust guardMouth guardEyes 170 3 0 1 self
						)
					)
					(3
						(= seconds 1)
					)
					(4
						(Print 705 0)
						(HandsOn)
						(self dispose:)
					)
				)
			)
			(2
				(switch (= state newState)
					(0
						(HandsOff)
						(Face ego client self)
					)
					(1
						(guardT
							init: guardBust guardMouth guardEyes 170 4 0 1 self
						)
					)
					(2
						(= seconds 1)
					)
					(3
						(Print 705 1)
						(HandsOn)
						(self dispose:)
					)
				)
			)
			(3
				(switch (= state newState)
					(0
						(HandsOff)
						(Face ego client self)
					)
					(1
						(egoT
							init: egoBust egoMouth egoEyes 170 5 0 1 self
						)
					)
					(2
						(guardT
							init: guardBust guardMouth guardEyes 170 6 0 1 self
						)
					)
					(3
						(egoT
							init: egoBust egoMouth egoEyes 170 7 0 1 self
						)
					)
					(4
						(= seconds 1)
					)
					(5
						(Print 705 2)
						(HandsOn)
						(self dispose:)
					)
				)
			)
			(4
				(switch (= state newState)
					(0
						(HandsOff)
						(Face ego client self)
					)
					(1
						(guardT
							init: guardBust guardMouth guardEyes 170 8 0 1 self
						)
					)
					(2
						(egoT
							init: egoBust egoMouth egoEyes 170 9 0 1 self
						)
					)
					(3
						(= seconds 1)
					)
					(4
						(HandsOn)
						(self dispose:)
					)
				)
			)
		)
	)
)

(instance guardTr of Talker
	(properties
		x 120
		y 3
		nsTop 1
		nsLeft 9
		view 504
		loop 1
	)
)

(instance guardBustr of View
	(properties
		view 504
		cel 1
	)
)

(instance guardMouthr of Prop
	(properties
		nsTop 40
		nsLeft 35
		view 504
		loop 5
		cycleSpeed 36
	)
)

(instance guardEyesr of Prop
	(properties
		nsTop 24
		nsLeft 34
		view 504
		loop 3
		cycleSpeed 90
	)
)

(instance guardTl of Talker
	(properties
		x 3
		y 3
		nsTop 1
		nsLeft 228
		view 504
	)
)

(instance guardBustl of View
	(properties
		view 504
		cel 1
	)
)

(instance guardMouthl of Prop
	(properties
		nsTop 40
		nsLeft 19
		view 504
		loop 4
		cycleSpeed 12
	)
)

(instance guardEyesl of Prop
	(properties
		nsTop 24
		nsLeft 13
		view 504
		loop 2
		cycleSpeed 30
	)
)

(instance egoTl of Talker
	(properties
		x 3
		y 3
		nsTop 1
		nsLeft 238
		view 507
	)
)

(instance egoBustl of View
	(properties
		view 507
		cel 1
	)
)

(instance egoMouthl of Prop
	(properties
		nsTop 51
		nsLeft 23
		view 507
		loop 4
		cycleSpeed 12
	)
)

(instance egoEyesl of Prop
	(properties
		nsTop 32
		nsLeft 10
		view 507
		loop 2
		cycleSpeed 30
	)
)

(instance egoTr of Talker
	(properties
		x 120
		y 3
		nsTop 1
		nsLeft 9
		view 507
		loop 1
	)
)

(instance egoBustr of View
	(properties
		view 507
		cel 1
	)
)

(instance egoMouthr of Prop
	(properties
		nsTop 51
		nsLeft 42
		view 507
		loop 4
		cycleSpeed 12
	)
)

(instance egoEyesr of Prop
	(properties
		nsTop 32
		nsLeft 24
		view 507
		loop 3
		cycleSpeed 30
	)
)
