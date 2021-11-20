;;; Sierra Script 1.0 - (do not remove this comment)
(script# 17)
(include sci.sh)
(use Main)
(use PolyPath)
(use Motion)

(public
	pOffMover 0
	iOffMover 1
)

(local
	local0
)
(instance pOffMover of PolyPath
	(properties)
	
	(method (dispose)
		(= local0 0)
		(super dispose:)
	)
	
	(method (moveDone)
		(if (== (points at: value) 30583)
			(if (not local0) (curRoom leaveRoom:))
			(if (ego notBlockedByEdge:)
				(cond 
					((and (>= finalX 313) (not local0))
						(theGame handsOff:)
						(= local0 1)
						(= value 2)
						(self init: ego 335 finalY)
					)
					((and (<= finalX 7) (not local0))
						(theGame handsOff:)
						(= local0 1)
						(= value 2)
						(self init: ego -15 finalY)
					)
					(
					(and (<= (ego y?) (curRoom horizon?)) (not local0))
						(theGame handsOff:)
						(= local0 1)
						(= value 2)
						(self init: ego finalX (- (curRoom horizon?) 1))
					)
					((and (>= finalY 182) (not local0))
						(theGame handsOff:)
						(= local0 1)
						(= value 2)
						(self init: ego finalX 240)
					)
					(else
						(cond 
							((>= finalX 335) (curRoom newRoom: (curRoom east?)))
							((<= finalX -15) (curRoom newRoom: (curRoom west?)))
							((> finalY 182) (curRoom newRoom: (curRoom south?)))
							(else
								(theGame handsOff:)
								(= local0 1)
								(curRoom newRoom: (curRoom north?))
							)
						)
						(super moveDone:)
					)
				)
			else
				(theGame handsOn:)
				(super moveDone:)
			)
		else
			(self setTarget: init:)
		)
	)
)

(instance iOffMover of MoveTo
	(properties)
	
	(method (dispose)
		(= local0 0)
		(super dispose:)
	)
	
	(method (moveDone)
		(cond 
			((and (>= x 313) (not local0)) (theGame handsOff:) (= local0 1) (self init: ego 335 y))
			((and (<= x 7) (not local0)) (theGame handsOff:) (= local0 1) (self init: ego -15 y))
			(
			(and (<= (ego y?) (curRoom horizon?)) (not local0))
				(theGame handsOff:)
				(= local0 1)
				(self init: ego x (- (curRoom horizon?) 1))
			)
			((and (>= y 182) (not local0)) (theGame handsOff:) (= local0 1) (self init: ego x 240))
			((not local0) (theGame handsOn:) (super moveDone:))
			(else
				(cond 
					((>= x 335) (curRoom newRoom: (curRoom east?)))
					((<= x -15) (curRoom newRoom: (curRoom west?)))
					((> y 182) (curRoom newRoom: (curRoom south?)))
					(else
						(theGame handsOff:)
						(= local0 1)
						(curRoom newRoom: (curRoom north?))
					)
				)
				(super moveDone:)
			)
		)
	)
)
