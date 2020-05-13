;;; Sierra Script 1.0 - (do not remove this comment)
(script# 73)
(include game.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use User)
(use System)

(public
	rm073 0
)

(instance rm073 of Room
	(properties
		picture 73
		east 74
		south 74
	)
	
	(method (init &tmp [temp0 50])
		(User canInput: TRUE canControl: TRUE)
		(self setRegions: ORTEGA)
		(Load VIEW 68)
		(super init:)
		(ego
			view: 68
			setStep: 2 2
			illegalBits: cWHITE
			setLoop: -1
			init:
		)
		(if global255
			(self setScript: WalkIn)
		else
			(ego posn: 316 (ego y?))
		)
	)
	
	(method (doit)
		(super doit:)
	)
	
	(method (handleEvent event)
		(super handleEvent: event)
		(if
		(or (!= (event type?) saidEvent) (event claimed?))
			(return)
		)
		(cond 
			((Said 'look>')
				(cond 
					((Said '[/area,around]') (Print 73 0))
					((Said '/lava') (Print 73 1))
					((Said '/device,building,generator')
						(cond 
							((not forceBeamDestroyed) (if decodedMessage (Print 73 2) else (Print 73 3)))
							(decodedMessage (Print 73 4))
							(else (Print 73 5))
						)
					)
					((Said '/beam')
						(if (not forceBeamDestroyed)
							(if decodedMessage (Print 73 6) else (Print 73 7))
						else
							(Print 73 8)
						)
					)
				)
			)
			((Said 'climb') (Print 73 9))
		)
	)
	
	(method (newRoom newRoomNumber &tmp [temp0 50])
		(if (not script)
			(if (> (ego y?) 140)
				(= global255 1)
			else
				(= global255 0)
			)
			(if forceBeamDestroyed (++ ortegaPostBeamRooms))
			(super newRoom: newRoomNumber)
		)
	)
)

(instance WalkIn of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					posn: 195 197
					loop: 3
					setPri: -1
					setMotion: MoveTo 195 187 self
				)
			)
			(1
				(HandsOn)
				(client setScript: 0)
			)
		)
	)
)
