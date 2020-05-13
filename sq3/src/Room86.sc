;;; Sierra Script 1.0 - (do not remove this comment)
(script# 86)
(include game.sh)
(use Main)
(use Intrface)
(use Avoider)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	Room86 0
)

(instance door of Prop
	(properties)
)

(instance Room86 of Room
	(properties
		picture 86
	)
	
	(method (init)
		(super init:)
		(NormalEgo)
		(HandsOff)
		(door view: 125 loop: 0 cel: 0 posn: 161 136 init:)
		(switch prevRoomNum
			(90
				(ego view: 0 init: posn: 159 130)
				(curRoom setScript: inFromNinety)
			)
			(else 
				(NormalEgo)
				(ego
					view: 123
					loop: 3
					posn: 159 155
					observeControl: 16384
					init:
				)
				(curRoom setScript: Actions)
			)
		)
		(if (ego has: iCoveralls) (ego view: 113))
	)
	
	(method (doit)
		(super doit:)
		(if (!= curRoomNum newRoomNum) (return))
		(if (& (ego onControl: 0) $0040) (curRoom newRoom: 85))
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
								(Print 86 0)
							)
							((Said '/door,elevator') (Print 86 1))
							((Said '/partition') (Print 86 2))
							((Said '/ramp,stair') (Print 86 3))
							((Said '/button') (Print 86 4))
						)
					)
					((Said 'open/door') (Print 86 5))
					((Said 'press/button') (ego setScript: pushButton))
				)
			else
				0
			)
		)
	)
	
	(method (newRoom newRoomNumber)
		(if (not enteredScumSoftHQ)
			(= enteredScumSoftHQ TRUE)
			(theGame changeScore: 25)
		)
		(super newRoom: newRoomNumber)
	)
)

(instance Actions of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 2))
			(1
				(HandsOff)
				(ego setMotion: 0 view: 134 setLoop: 4 setCycle: Forward)
				(RedrawCast)
				(= seconds 2)
			)
			(2
				(ego setLoop: 3 cel: 0)
				(RedrawCast)
				(Print 86 6)
				(= seconds 2)
			)
			(3
				(ego setLoop: 1)
				(= seconds 2)
			)
			(4
				(NormalEgo)
				(ego view: 0 loop: 2)
				(= beltState beltDEPLETED)
				(= egoInvisible FALSE)
				(RedrawCast)
				(Print 86 7)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance inFromNinety of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(door ignoreActors: setCycle: EndLoop self)
			)
			(1
				(ego setMotion: MoveTo 159 155 self)
			)
			(2
				(door setCycle: BegLoop self)
				(NormalEgo)
				(ego observeControl: 16384)
				(HandsOn)
			)
		)
	)
)

(instance pushButton of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setAvoider: Avoider setMotion: MoveTo 134 143 self)
			)
			(1 (= seconds 2))
			(2
				(ego setAvoider: 0)
				(door setCycle: EndLoop self)
			)
			(3
				(door ignoreActors:)
				(ego ignoreControl: 16384 setMotion: MoveTo 159 141 self)
			)
			(4
				(ego setMotion: MoveTo 159 130 self)
			)
			(5
				(ego loop: 2)
				(door setCycle: BegLoop self)
			)
			(6 (curRoom newRoom: 90))
		)
	)
)
