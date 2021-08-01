;;; Sierra Script 1.0 - (do not remove this comment)
(script# 979)
(include sci.sh)
(use Main)
(use Intrface)
(use Motion)
(use System)


(instance COn of Code
	(properties)
	
	(method (doit param1)
		(MousedOn param1 &rest)
	)
)

(instance MTM of Motion
	(properties)
	
	(method (doit)
		(super doit: &rest)
		(if (client isStopped:) (self moveDone:))
	)
)

(class MDH of EventHandler
	(properties
		elements 0
		size 0
		x 0
		y 0
		modifiers $0000
		targetObj 0
		shiftParser 0
	)
	
	(method (handleEvent event &tmp userAlterEgo temp1 temp2)
		(= temp1 ((= userAlterEgo (user alterEgo?)) mover?))
		(= x (event x?))
		(= y (event y?))
		(= modifiers (event modifiers?))
		(if (& modifiers $000c)
			(super handleEvent: event)
		else
			(= temp2 (FirstNode elements))
			(while (and temp2 (= targetObj (NodeValue temp2)))
				(if
					(= targetObj
						(cond 
							((targetObj isKindOf: Collect) (targetObj firstTrue: #perform COn event))
							((MousedOn targetObj event) targetObj)
						)
					)
					(if (& modifiers $0003)
						(event type: 128)
						(shiftParser doit: targetObj event)
						(targetObj handleEvent: event)
						(event type: 1)
						(event claimed?)
						(return)
						(break)
					)
					(if
						(and
							(user controls?)
							(IsObject userAlterEgo)
							(cast contains: userAlterEgo)
						)
						(userAlterEgo
							setMotion: MTM (targetObj x?) (targetObj y?) self
						)
						(user prevDir: 0)
						(event claimed: 1)
						(break)
					)
					(super handleEvent: event)
					(break)
				)
				(= temp2 (NextNode temp2))
			)
			(if (== targetObj 0) (super handleEvent: event))
		)
	)
	
	(method (cue &tmp newEvent)
		((= newEvent (Event new:))
			type: 1
			x: x
			y: y
			modifiers: modifiers
		)
		(targetObj handleEvent: newEvent)
		(= targetObj 0)
		(newEvent dispose:)
	)
)
