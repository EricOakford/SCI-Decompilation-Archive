;;; Sierra Script 1.0 - (do not remove this comment)
(script# 6)
(include game.sh)
(use Main)
(use Print)
(use Feature)
(use System)

(public
	eFeatureR 0
)

(instance eFeatureR of Feature
	(method (init)
		(if (not (curRoom exitList?))
			(curRoom exitList: (List new:))
		)
		((curRoom exitList?) add: self)
	)
	
	(method (doVerb theVerb)
		(curRoom doVerb: theVerb)
	)
	
	(method (onMe theObjOrX theY)
		(return
			(if
				(and
					(<= nsLeft theObjOrX)
					(<= theObjOrX nsRight)
					(<= nsTop theY)
					(<= theY nsBottom)
				)
				(if (and approachX approachY)
					(ego
						setMotion: ((ScriptID 17) new:) approachX approachY
					)
				else
					(= quit TRUE)
					(Prints
						{YOU HAVE SET approachX approachY of your eFeature}
					)
					(SetDebug)
				)
				(return TRUE)
			else
				FALSE
			)
		)
	)
)
