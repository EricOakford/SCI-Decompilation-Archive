;;; Sierra Script 1.0 - (do not remove this comment)
(script# 80)
(include game.sh)
(use Main)
(use Intrface)
(use Sound)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	Room80 0
)

(local
	doors
)
(instance doorSound of Sound
	(properties
		number 300
	)
)

(instance fallMusic of Sound
	(properties
		number 52
	)
)

(instance Room80 of Room
	(properties
		picture 80
	)
	
	(method (init)
		(Load VIEW 603)
		(Load SOUND 52)
		(super init:)
		(= isIndoors FALSE)
		((= doors (Prop new:))
			view: 603
			posn: 288 116
			init:
			stopUpd:
		)
		(if (or (== prevRoomNum 79) (== prevRoomNum 0))
			(ego posn: 279 166 view: 2 xStep: 3 yStep: 2 init:)
		)
		(if (== prevRoomNum 92)
			(ego view: 2 posn: 266 122 xStep: 3 yStep: 2 init:)
		)
		(if (== prevRoomNum 94)
			(ego view: 2 posn: 185 134 xStep: 3 yStep: 2 init:)
		)
		(doorSound init:)
	)
	
	(method (doit)
		(super doit:)
		(if (& (ego onControl:) cRED)
			(ego loop: loopW)
			(RedrawCast)
			(curRoom newRoom: 94)
		)
		(if (& (ego onControl:) cLRED)
			(curRoom newRoom: 79)
		)
		(if (& (ego onControl: origin) cGREEN)
			(self setScript: falling)
		)
	)
	
	(method (handleEvent event)
		(return
			(cond 
				((event claimed?) (return TRUE))
				((== (event type?) saidEvent)
					(cond 
						((Said 'open/door')
							(if (< (ego distanceTo: doors) 25)
								(HandsOff)
								(ego setMotion: 0)
								(doors ignoreActors: TRUE setCycle: EndLoop)
								(doorSound play: doDoor)
							else
								(Print 800 1)
							)
						)
						((Said 'look>')
							(cond 
								(
									(or
										(Said '/room')
										(Said '/around')
										(Said '[<around][/noword]')
									)
									(Print 80 0)
								)
								((Said '/boulder')
									(Print 80 1)
								)
								((Said '/castle')
									(Print 80 2)
								)
								((Said '/barn')
									(Print 80 3)
								)
								((Said '/cliff')
									(Print 80 4)
								)
								((Said '/path')
									(Print 80 5)
								)
								((Said '/door')
									(Print 80 6)
								)
							)
						)
					)
				)
			)
		)
	)
)

(instance doDoor of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (!= (doors cel?) (doors lastCel:))
					(-- state)
					(doors setCycle: EndLoop self)
				else
					(HandsOn)
					(curRoom newRoom: 92)
				)
			)
		)
	)
)

(instance falling of Script
	(method (init who)
		(super init: who)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(User canControl: 0 canInput: 0)
				(ego
					view: 17
					setStep: 5 6
					setLoop: 3
					setMotion: MoveTo (- (ego x?) 15) 230 self
				)
				(fallMusic play:)
			)
			(1
				(= seconds 3)
			)
			(2
				(fallMusic dispose:)
				(ShakeScreen 6)
				(= seconds 3)
			)
			(3
				(= dead TRUE)
			)
		)
	)
)
