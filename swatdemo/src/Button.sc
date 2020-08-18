;;; Sierra Script 1.0 - (do not remove this comment)
(script# BUTTON)
(include game.sh)
(use Main)
(use Actor)


(class Button of View
	(properties
		code 0
		active 1
		allowModifiers 0
		upView -1
		upLoop -1
		upCel 0
		downView -1
		downLoop -1
		downCel 1
		useRect 0
		keyMessage -1
	)
	
	(method (init)
		(if (== upView -1)
			(= upView view)
		)
		(if (== upLoop -1)
			(= upLoop loop)
		)
		(if (== downView -1)
			(= downView view)
		)
		(if (== downLoop -1)
			(= downLoop loop)
		)
		(self view: upView loop: upLoop cel: upCel)
		(super init: &rest)
		(if (!= keyMessage -1)
			(directionHandler add: self)
			(keyDownHandler add: self)
		)
		(if (== plane thePlane)
			(mouseDownHandler addToFront: self)
		)
	)
	
	(method (dispose)
		(if (!= keyMessage -1)
			(directionHandler delete: self)
			(keyDownHandler delete: self)
		)
		(if (== plane thePlane) (mouseDownHandler delete: self))
		(super dispose: &rest)
	)
	
	(method (handleEvent event &tmp temp0 temp1 theView theLoop theCel)
		(if (or (not active) (event claimed?)) (return))
		(if
			(or
				(and
					(& (event type?) mouseEvt)
					(not (self onMe: event))
				)
				(not (event type?))
				(not (user input?))
				(and (not allowModifiers) (event modifiers?))
			)
			(super handleEvent: event)
			(return)
		)
		(if (& (event type?) (| keyDown direction))
			(if
				(and
					(!= keyMessage -1)
					(== keyMessage (event message?))
				)
				(self doVerb:)
				(event claimed: TRUE)
			)
			(event claimed?)
			(return)
		)
		(= temp1 0)
		(repeat
			((= temp0 ((user curEvent?) new:)) localize: plane)
			(if (== (temp0 type?) 2) (break))
			(= theView view)
			(= theLoop loop)
			(= theCel cel)
			(if (self onMe: temp0)
				(self view: downView loop: downLoop cel: downCel)
				(= temp1 1)
			else
				(self view: upView loop: upLoop cel: upCel)
				(= temp1 0)
			)
			(if
				(or
					(!= theView view)
					(!= theLoop loop)
					(!= theCel cel)
				)
				(UpdateScreenItem self)
				(FrameOut)
			)
			(temp0 dispose:)
		)
		(temp0 dispose:)
		(self view: upView loop: upLoop cel: upCel)
		(UpdateScreenItem self)
		(FrameOut)
		(if temp1
			(event claimed: TRUE)
			(self doVerb:)
		else
			(super handleEvent: event)
		)
		(event claimed?)
	)
	
	(method (doVerb)
		(if code 
			(code doit:)
		)
	)
	
	(method (onMe theObjOrX &tmp ret)
		(if
		(and (not (= ret (super onMe: theObjOrX))) useRect)
			(= ret
				(if
					(and
						(<= nsLeft (theObjOrX x?))
						(<= (theObjOrX x?) nsRight)
						(<= nsTop (theObjOrX y?))
					)
					(<= (theObjOrX y?) nsBottom)
				else
					0
				)
			)
		)
		(return ret)
	)
)
