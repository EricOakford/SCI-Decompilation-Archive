;;; Sierra Script 1.0 - (do not remove this comment)
(script# 27)
(include game.sh)
(use Main)
(use Actor)


(class useObj of View
	(properties
		verb 0
		myCursorView 0
		myCursorLoop 0
		myCursorCel 0
		position 0
	)
	
	(method (init param1 &tmp temp0)
		(= temp0 (if argc param1 else cast))
		(&= signal $fff7)
		(temp0 add: self)
		(= plane (temp0 plane?))
		(AddScreenItem self)
		(|= -info- $0010)
		(if (not (temp0 contains: self))
			(= lsBottom (= lsRight (= lsTop (= lsLeft 0))))
			(&= signal $fff7)
		)
		(= useInsetRect 0)
		(if (& signal $0020)
			(BaseSetter self)
		)
		(if (!= view -1)
			(SetNowSeen self)
		)
		(self initialize: checkDetail:)
	)
	
	(method (show)
		(super show:)
		(self
			x: (theExitFeature x?)
			y: (+ (theExitFeature y?) 1)
		)
		(= curInvItem 0)
		(user message: (user dftMessage?))
		(if (user canInput:)
			(invCursor view: 999 loop: 0 setCel: 0 init:)
		)
		(theGame setCursor: normalCursor 1)
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 8)
			(self setHotspot: 0)
			(if (not myCursorView)
				(invCursor view: view loop: loop cel: cel init:)
			else
				(invCursor
					view: myCursorView
					loop: myCursorLoop
					cel: myCursorCel
					init:
				)
			)
			(= curInvItem self)
			(user message: verb)
			(theGame setCursor: invCursor TRUE)
			(self hide:)
			(if position
				(= position 0)
			)
		else
			(super doVerb: theVerb &rest)
		)
	)
)
