;;; Sierra Script 1.0 - (do not remove this comment)
(script# FEATURE)
(include game.sh)
(use Main)
(use Intrface)
(use System)


(class Feature of Object
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
		closeRangeDist 50
		longRangeDist 100
		shiftClick -32767
		contClick 7
		actions 0
		control 0
		verbChecks1 -17483
		verbChecks2 -17477
		verbChecks3 -17477
	)
	
	(procedure (PrintVerbMsg theVerb)
		(switch theVerb
			(verbLook
				(Printf FEATURE 0 description description)
			)
			(verbOpen
				(Printf FEATURE 1 description)
			)
			(verbClose
				(Printf FEATURE 2 description)
			)
			(verbSmell
				(Printf FEATURE 3 description)
			)
			(verbMove
				(Printf FEATURE 4 description)
			)
			(verbEat
				(Printf FEATURE 5 description)
			)
			(verbGet
				(Printf FEATURE 6 description)
			)
			(verbClimb
				(Printf FEATURE 7 description)
			)
			(verbTalk
				(Printf FEATURE 8 description)
			)
		)
	)
	
	
	(method (init)
		(features add: self)
		(if (not description)
			(= description name)
		)
	)
	
	(method (dispose)
		(features delete: self)
		(super dispose:)
	)
	
	(method (handleEvent event &tmp theMods theVerb)
		(if (event claimed?) (return TRUE))
		(switch (event type?)
			(saidEvent
				(cond 
					((not (Said noun)))
					((not (= theVerb (event message?))) (event claimed: FALSE))
					((self passedChecks: theVerb)
						(self doVerb: theVerb)
					)
					((IsObject actions)
						(actions handleEvent: event self)
					)
				)
			)
			(mouseDown
				(cond 
					((not (= theMods (event modifiers?))))
					((not (self onMe: event)))
					((& theMods shiftDown)
						(if
							(or
								(& shiftClick NOCHECKMOUSE)
								(self passedChecks: shiftClick)
							)
							(self doVerb: (& (~ NOCHECKMOUSE) shiftClick))
						)
						(event claimed: TRUE)
					)
					((& theMods shiftDown)
						(if
						(or (& contClick NOCHECKMOUSE) (self passedChecks: contClick))
							(self doVerb: (& (~ NOCHECKMOUSE) contClick))
						)
						(event claimed: TRUE)
					)
				)
			)
		)
		(return (event claimed?))
	)
	
	(method (setChecks theVerb theChecks &tmp theVal theShift)
		(= theVal
			(<< theChecks (= theShift (* 4 (mod (- theVerb 1) 4))))
		)
		(cond 
			((<= theVerb 4)
				(&= verbChecks1 (~ (<< $F theShift)))
				(|= verbChecks1 theVal)
			)
			((<= theVerb 8)
				(&= verbChecks2 (~ (<< $F theShift)))
				(|= verbChecks2 theVal)
			)
			(else
				(&= verbChecks3 (~ (<< $F theShift)))
				(|= verbChecks3 theVal)
			)
		)
	)
	
	(method (doVerb theVerb)
		(PrintVerbMsg theVerb description)
	)
	
	(method (notInFar)
		(Printf FEATURE 9 description)
	)
	
	(method (notInNear)
		(Printf FEATURE 10 description)
	)
	
	(method (notFacing &tmp event)
		(Printf FEATURE 11 description)
	)
	
	(method (facingMe act &tmp theActor theAngle)
		(= theActor (if argc act else ego))
		(if
			(>
				(= theAngle
					(Abs
						(-
							(GetAngle (theActor x?) (theActor y?) x y)
							(theActor heading?)
						)
					)
				)
				180
			)
			(= theAngle (- 360 theAngle))
		)
		(return
			(if (<= theAngle sightAngle)
				(return TRUE)
			else
				(self notFacing:)
				(return FALSE)
			)
		)
	)
	
	(method (nearCheck obj &tmp theObj)
		(= theObj (if argc obj else ego))
		(return
			(if
				(<=
					(GetDistance (theObj x?) (theObj y?) x y)
					closeRangeDist
				)
				(return TRUE)
			else
				(self notInNear:)
				(return FALSE)
			)
		)
	)
	
	(method (farCheck obj &tmp theObj)
		(= theObj (if argc obj else ego))
		(return
			(if
				(<=
					(GetDistance (theObj x?) (theObj y?) x y)
					longRangeDist
				)
				(return TRUE)
			else
				(self notInFar:)
				(return FALSE)
			)
		)
	)
	
	(method (isNotHidden)
		(return TRUE)
	)
	
	(method (onMe theObjOrX theY &tmp oX oY)
		(if (IsObject theObjOrX)
			(= oX (theObjOrX x?))
			(= oY (theObjOrX y?))
		else
			(= oX theObjOrX)
			(= oY theY)
		)
		(return
			(if
				(and
					(<= nsLeft oX)
					(<= oX nsRight)
					(<= nsTop oY)
					(<= oY nsBottom)
				)
				(if control
					(& control (OnControl CMAP oX oY)) else TRUE
				)
			else
				FALSE
			)
		)
	)
	
	(method (passedChecks theVerb &tmp theChecks)
		(if
			(and
				(or
					(not
						(&
							(= theChecks
								(&
									(>>
										(if (<= theVerb 4) verbChecks1 else verbChecks2)
										(* 4 (mod (- theVerb 1) 4))
									)
									$F
								)
							)
							ISNOTHIDDEN
						)
					)
					(self isNotHidden:)
				)
				(or (not (& theChecks FARCHECK)) (self farCheck:))
				(or (not (& theChecks NEARCHECK)) (self nearCheck:))
			)
			(if (not (& theChecks FACINGME)) else (self facingMe:))
		)
	)
)
