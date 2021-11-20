;;; Sierra Script 1.0 - (do not remove this comment)
(script# 9)
(include sci.sh)
(use Main)
(use Print)
(use System)

(public
	DistObj 0
)

(class TellerObj of Obj
	(properties
		scratch 0
		telObject 0
		telDistance 0
	)
)

(class DistObj of Obj
	(properties
		scratch 0
		objList 0
	)
	
	(method (init param1 &tmp temp0)
		(ego actions: self)
		(= objList myList)
		(= temp0 0)
		(while (< temp0 argc)
			(if [param1 temp0]
				(objList
					add:
						((TellerObj new:)
							init:
							telObject: [param1 temp0]
							telDistance: [param1 (+ temp0 1)]
							yourself:
						)
				)
			else
				(Prints {You have passed a wrong parameter})
				(SetDebug)
			)
			(= temp0 (+ temp0 2))
		)
	)
	
	(method (dispose)
		(objList dispose:)
		(super dispose:)
	)
	
	(method (doVerb)
		(objList firstTrue: #perform checkDist)
	)
)

(instance myList of List
	(properties)
	
	(method (firstTrue param1 &tmp theNextNode temp1)
		(= theNextNode (FirstNode elements))
		(while theNextNode
			(= nextNode (NextNode theNextNode))
			(= temp1 (NodeValue theNextNode))
			(if (temp1 param1: &rest) (return (temp1 telObject?)))
			(= theNextNode nextNode)
		)
		(return 0)
	)
)

(instance checkDist of Code
	(properties)
	
	(method (doit param1)
		(if
			(<
				(ego distanceTo: (param1 telObject?) 65)
				(param1 telDistance?)
			)
			(return param1)
		)
		(return 0)
	)
)
