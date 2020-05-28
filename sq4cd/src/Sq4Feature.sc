;;; Sierra Script 1.0 - (do not remove this comment)
(script# SQFEATURE) ;820
(include game.sh)
(use Main)
(use Feature)
(use Actor)

;EO: These are subclasses that retain the old lookStr property.

(class Sq4Feature of Feature
	(properties
		lookStr 0
	)
	
	(method (doVerb theVerb)
		(if (and lookStr (== theVerb V_LOOK))
			(narrator say: lookStr)
		else
			(doVerbCode doit: theVerb)
		)
	)
)

(class Sq4View of View
	(properties
		lookStr 0
	)
	
	(method (doVerb theVerb)
		(if (and lookStr (== theVerb V_LOOK))
			(narrator say: lookStr)
		else
			(doVerbCode doit: theVerb)
		)
	)
)

(class Sq4Prop of Prop
	(properties
		lookStr 0
	)
	
	(method (doVerb theVerb)
		(if (and lookStr (== theVerb V_LOOK))
			(narrator say: lookStr)
		else
			(doVerbCode doit: theVerb)
		)
	)
)

(class Sq4Actor of Actor
	(properties
		lookStr 0
	)
	
	(method (doVerb theVerb)
		(if (and lookStr (== theVerb V_LOOK))
			(narrator say: lookStr)
		else
			(doVerbCode doit: theVerb)
		)
	)
)
