;;; Sierra Script 1.0 - (do not remove this comment)
(script# 899)
(include game.sh)
(use Main)
(use Intrface)
(use System)


(procedure (ClaimEvent event claimed)
	(event claimed: &rest)
)

(class NewFeature of Object
	(properties
		x 0
		y 0
		z 0
		heading 0
		noun 0
		nsTop 0
		nsLeft 0
		nsBottom 0
		nsRight 0
		description 0
		sightAngle 90
		getableDist 50
		seeableDist 100
		shiftClick 0
		contClick 0
		lookStr 0
	)
	
	(method (init)
		(features add: self)
		(if (not shiftClick)
			(= shiftClick 369)
		)
	)
	
	(method (handleEvent event &tmp eMod)
		(if (event claimed?)
			(return TRUE)
		)
		(switch (event type?)
			(saidEvent
				(if (Said noun)
					(event claimed: FALSE)
					(return
						(cond 
							(
								(or
									(>
										(Abs
											(- (GetAngle (ego x?) (ego y?) x y) (ego heading?))
										)
										sightAngle
									)
									(> (GetDistance (ego x?) (ego y?) x y) seeableDist)
								)
								(self dontSee:)
								(event claimed: TRUE)
							)
							((Said 'look,check')
								(self doLook:)
							)
							((> (GetDistance (ego x?) (ego y?) x y) getableDist)
								(self notClose:)
								(event claimed: TRUE)
							)
							((Said 'move,pull')
								(self doMove:)
							)
							((Said 'get,take')
								(if contClick
									(self doGet:)
								else
									(event claimed: FALSE)
								)
							)
						)
					)
				)
			)
			(mouseDown
				(cond 
					((not (= eMod (event modifiers?))))
					(
						(not
							(if
								(and
									(<= nsLeft (event x?))
									(<= (event x?) nsRight)
									(<= nsTop (event y?))
								)
								(<= (event y?) nsBottom)
							)
						)
					)
					((& eMod shiftDown)
						(ClaimEvent self shiftClick)
						(event claimed: TRUE)
					)
				)
			)
		)
		(return (event claimed?))
	)
	
	(method (doLook)
		(if lookStr
			(Print lookStr)
		else
			(Printf 899 2 description description)
		)
	)
	
	(method (doMove)
		(Printf 899 3 description)
	)
	
	(method (doGet)
		(Printf 899 4 description)
	)
	
	(method (dontSee)
		(Printf 899 0 description)
	)
	
	(method (notClose)
		(Printf 899 1 description)
	)
)
