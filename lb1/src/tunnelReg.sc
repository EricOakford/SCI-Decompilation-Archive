;;; Sierra Script 1.0 - (do not remove this comment)
(script# 242)
(include game.sh)
(use Main)
(use Intrface)
(use Sound)
(use Motion)
(use Game)
(use System)

(public
	tunnelReg 0
)

(instance myMusic of Sound)

(instance tunnelReg of Region
	
	(method (init)
		(super init:)
		(if (!= curRoomNum 52)
			(myMusic number: 61 loop: -1 play:)
		)
	)
	
	(method (dispose)
		(super dispose:)
	)
	
	(method (handleEvent event &tmp evt temp1 temp2 node nextNode obj)
		(if (event claimed?) (return TRUE))
		(if
			(and
				(== (event type?) mouseDown)
				(not (& (event modifiers?) shiftDown))
				(or
					(== curRoomNum 51)
					(== curRoomNum 55)
					(and (== curRoomNum 56) (& (ego onControl:) cGREEN))
				)
			)
			(event claimed: TRUE)
			(while (!= 2 ((= evt (Event new:)) type?))
				(ego setMotion: MoveTo (evt x?) (ego y?))
				(evt dispose:)
			)
			(evt dispose:)
		)
		(return
			(if (== (event type?) saidEvent)
				(cond 
					((Said 'examine>')
						(= temp1 (= temp2 0))
						(= node (cast first:))
						(while node
							(= nextNode (cast next: node))
							(if
								(and
									(IsObject (= obj (NodeValue node)))
									(== (obj view?) 155)
									(not (& (obj signal?) actorHidden))
								)
								(if (== (obj loop?) 0)
									(= temp2 1)
								else
									(= temp1 1)
								)
							)
							(= node nextNode)
						)
						(cond 
							((Said '/beam')
								(if (or temp2 (== curRoomNum 51))
									(Print 242 0)
								else
									(DontSee)
								)
							)
							((or (Said '/dirt') (Said '<down[/!*]'))
								(Print 242 1)
							)
							((Said '/wall')
								(Print 242 2)
							)
							((Said '/boulder')
								(if (and (== curRoomNum 55) temp1)
									(Print 242 3)
								else
									(DontSee)
								)
							)
							((Said '/eye')
								(Print 242 4)
							)
							((Said '/mouse')
								(DontSee)
							)
						)
					)
					((Said 'hear')
						(Print 242 5)
					)
					((Said 'extinguish,extinguish,(rotate<off)')
						(Print 242 6)
					)
				)
			else
				FALSE
			)
		)
	)
)
