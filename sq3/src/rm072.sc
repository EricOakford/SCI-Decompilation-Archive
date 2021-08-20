;;; Sierra Script 1.0 - (do not remove this comment)
(script# 72)
(include game.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use System)

(public
	rm072 0
)

(instance rm072 of Room
	(properties
		picture 72
		north 74
		south 71
	)
	
	(method (init &tmp [temp0 50])
		(HandsOn)
		(self setRegions: ORTEGA)
		(super init:)
		(switch prevRoomNum
			(71
				(ego
					init:
					posn:
						(cond 
							((< (ego x?) 67) 67)
							((< (ego x?) 193) 193)
							(else (ego x?))
						)
						187
				)
				(RedrawCast)
				(if forceBeamDestroyed
					(theMusic number: 71 loop: -1 priority: 0 play:)
				else
					(theMusic number: 51 loop: -1 priority: 0 play:)
				)
				(Print 72 0)
			)
			(74
				(curRoom setScript: EnterScript)
			)
		)
	)
	
	(method (doit &tmp temp0)
		(super doit:)
		(if
		(and (== (ego onControl:) 3) (not (curRoom script?)))
			(self setScript: LeaveScript)
		)
	)
	
	(method (handleEvent event)
		(super handleEvent: event)
		(if
		(or (!= (event type?) saidEvent) (event claimed?))
			(return)
		)
		(cond 
			((Said 'look/device,building,generator')
				(cond 
					((not forceBeamDestroyed) (if decodedMessage (Print 72 1) else (Print 72 2)))
					(decodedMessage (Print 72 3))
					(else (Print 72 4))
				)
			)
			((Said 'look/beam')
				(if (not forceBeamDestroyed)
					(if decodedMessage (Print 72 5) else (Print 72 6))
				else
					(Print 72 7)
				)
			)
			((Said 'look/butte') (Print 72 8))
		)
	)
)

(instance EnterScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					setPri: 3
					setLoop: 0
					illegalBits: 0
					posn: 217 156
					init:
					setMotion: MoveTo 241 132 self
				)
			)
			(1
				(ego setLoop: 2 setMotion: MoveTo 241 144 self)
			)
			(2
				(ego setLoop: -1 illegalBits: cWHITE setPri: -1)
				(HandsOn)
				(curRoom setScript: 0)
			)
		)
	)
)

(instance LeaveScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					setPri: 3
					setLoop: 1
					illegalBits: 0
					setMotion: MoveTo 217 156 self
				)
			)
			(1
				(ego setLoop: -1)
				(HandsOn)
				(if forceBeamDestroyed (++ ortegaPostBeamRooms))
				(curRoom newRoom: 74)
			)
		)
	)
)
