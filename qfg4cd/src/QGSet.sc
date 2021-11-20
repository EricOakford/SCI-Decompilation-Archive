;;; Sierra Script 1.0 - (do not remove this comment)
(script# 39)
(include sci.sh)
(use System)


(class QGSet of Set
	(properties
		scratch 0
		elements 0
		size 0
		nextNode 0
	)
	
	(method (eachElementDo param1 &tmp theNextNode_2 temp1 theNextNode)
		(= theNextNode_2 (FirstNode elements))
		(while theNextNode_2
			(= nextNode (NextNode theNextNode_2))
			(= temp1 (NodeValue theNextNode_2))
			(= theNextNode nextNode)
			(temp1 param1: &rest)
			(= nextNode theNextNode)
			(= theNextNode_2 nextNode)
		)
	)
)
