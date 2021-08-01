;;; Sierra Script 1.0 - (do not remove this comment)
(script# MOUSER)
(include game.sh)
(use Main)
(use Intrface)
(use Motion)
(use User)
(use System)

(instance ClickedOn of Code
	(properties
		name "COn"
	)
	(method (doit who)	;return TRUE if we're a legit target
		(return
			(MousedOn who &rest)
		)
	)
)

(instance MoveToMouse of Motion ; MoveTo?
	(properties
		name "MTM"
	)
	(method (doit)
		(super doit: &rest)
		(if (client isStopped:)
			(self moveDone:)
		)
	)
)

(class MouseDownHandler kindof EventHandler
	
	;; Author: Pablo Ghenis
	;;	updated 6/27/89
	;;
	;;	handle mouseDowns and shift-mouseDowns intelligently
	;;	Usage:
	;;	
	;;	(instance MyMouseSays of Code
	;;		(method (doit what event)
	;;			(Parse {look} event)	;or (Parse (what name) event)
	;;		)
	;;	)
	;;	(instance MyMouseDownHandler of MouseDownHandler)
	;;	
	;;	(instance FooQuest of Game
	;;		...
	;;		(method (init)
	;;			...
	;;			((= mouseDownHandler MyMouseDownHandler)
	;;				shiftParser: MyMouseSays
	;;				,add: cast features
	;;			)
	;;			...
	;;		);init
	;;		...
	;;	);FooQuest
	
	(properties
		name "MDH"
		x 0 y 0			;remember about last mouseDown
		modifiers 0
		targetObj	NULL
		shiftParser	NULL
	)
;;;	(methods
;;;		cue
;;;	)
	
	(method (cue &tmp event)
		((= event (Event new:)) type:mouseDown, x:x, y:y, modifiers:modifiers)
		(targetObj  handleEvent: event)
		(= targetObj  NULL)
		(event dispose:)
	)	
	
	(method (handleEvent event &tmp thisEgo thisMover node)
		(= thisEgo (User alterEgo?))
		(= thisMover (thisEgo mover?))
		(= x (event x?))
		(= y (event y?))
		(= modifiers (event modifiers?))
		
		(cond
			((&  modifiers (| altDown ctrlDown))
				;;DEBUGGING click
				(super  handleEvent: event)
			)	
			(else
				;;NORMAL click
				(for
					((= node (FirstNode elements)))
					(and node (= targetObj (NodeValue node)))
					((= node (NextNode node)))
					
					(if 
						(= targetObj 
							(cond
								((targetObj isKindOf: Collection)
									(targetObj firstTrue: #perform ClickedOn event)
								)
								((MousedOn targetObj event)
									targetObj
								)
							)
						)
						(cond
							((& modifiers shiftDown)
								(event type: saidEvent)
								(shiftParser doit: targetObj event)	;(Parse {look} event)
								(targetObj handleEvent: event)
								(event type: mouseDown)	;restore event type
								(return (event claimed?))
							)
							((and
									(User controls?)
									(IsObject thisEgo)
									(cast contains: thisEgo)
								)
								(thisEgo setMotion:
									MoveToMouse (targetObj x?) (targetObj y?) self
								)
								(User prevDir: 0)
								(event claimed:TRUE)
							)
							(else ;had target but didn't let it try
								(super  handleEvent: event)
							)
						)
						(break)
					)
				);for
				(if (== targetObj NULL) ;never found a target in the for-loop
					(super  handleEvent: event)
				)
			)
		)
	)
)
