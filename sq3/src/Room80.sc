;;; Sierra Script 1.0 - (do not remove this comment)
(script# 80)
(include game.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use System)

(public
	Room80 0
)

(instance Room80 of Room
	(properties
		picture 80
	)
	
	(method (init)
		(= north (= west (= south (= east 81))))
		(= horizon 55)
		(super init:)
		(NormalEgo)
		(ego view: 63)
		(switch prevRoomNum
			(14
				(ego posn: 180 135 init:)
				(curRoom setScript: Actions)
			)
			(81
				(ego posn: gGEgoX_4 gGEgoY_3 loop: gGEgoX_5)
			)
			(85
				(if (== egoInvisible TRUE) (ego view: 123))
				(ego posn: 1 144)
				(= notifyCountdown 5)
				((ScriptID PESTULON) notify:)
			)
			(else 
				(ego view: 0 posn: 160 188)
			)
		)
		(ego init:)
		(if (== egoInvisible TRUE)
			(Print 80 0)
			(= egoInvisible FALSE)
		)
	)
	
	(method (doit)
		(super doit:)
		(if (!= curRoomNum newRoomNum) (return))
		(if
			(and
				(& (ego onControl: 0) $0040)
				(== (curRoom script?) 0)
			)
			(curRoom newRoom: 14)
		)
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return TRUE))
		(return
			(if (== (event type?) saidEvent)
				(cond 
					((Said 'look>')
						(cond 
							(
								(or
									(Said '/area')
									(Said '/around')
									(Said '[<around][/!*]')
								)
								(Print 80 1)
							)
							((Said '/tree') (Print 80 2))
							((Said '/rock,banner,boulder') (Print 80 3))
							((Said '/craft') (Print 80 4))
							((or (Said '<up') (Said '/air')) (Print 80 5))
							((or (Said '<down') (Said '/dirt,pass')) (Print 80 6))
						)
					)
					((Said 'get,get/rock,banner') (Print 80 7))
					((Said 'climb/tree') (Print 80 8))
				)
			else
				0
			)
		)
	)
	
	(method (newRoom newRoomNumber)
		(cond 
			((> (ego x?) 317) (= gGEgoX_4 317) (= gGEgoY_3 (ego y?)) (= gGEgoX_5 1))
			((> (ego y?) 187) (= gGEgoY_3 186) (= gGEgoX_4 (ego x?)) (= gGEgoX_5 3))
			((< (ego x?) 2) (= gGEgoX_4 3) (= gGEgoY_3 (ego y?)) (= gGEgoX_5 0))
			(else
				(= gGEgoX_4 (ego x?))
				(= gGEgoY_3 (+ horizon 3))
				(= gGEgoX_5 2)
			)
		)
		(super newRoom: newRoomNumber)
	)
)

(instance Actions of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: MoveTo 202 160 self)
			)
			(1
				(HandsOn)
				(curRoom setScript: 0)
			)
		)
	)
)
