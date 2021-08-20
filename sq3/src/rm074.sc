;;; Sierra Script 1.0 - (do not remove this comment)
(script# 74)
(include game.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use User)
(use System)

(public
	rm074 0
)

(local
	local0
	local1
)
(instance rm074 of Room
	(properties
		picture 74
		south 72
		west 73
	)
	
	(method (init &tmp [temp0 50])
		(User canInput: TRUE canControl: TRUE)
		(self setRegions: ORTEGA)
		(Load VIEW 68)
		(Load VIEW 97)
		(super init:)
		(switch prevRoomNum
			(72
				(ego init: illegalBits: 16384 setPri: 14 posn: 225 187)
			)
			(73
				(if global255
					(curRoom setScript: UpStairs)
				else
					(ego posn: 2 (ego y?) init:)
				)
			)
			(75
				(if forceBeamDestroyed
					(theMusic number: 71 loop: -1 priority: 0 play:)
				else
					(theMusic number: 51 loop: -1 priority: 0 play:)
				)
				(curRoom setScript: DownLadder)
			)
		)
	)
	
	(method (doit &tmp temp0)
		(super doit:)
		(if local0 (curRoom newRoom: 73))
		(if local1 (curRoom newRoom: 75))
		(if (== (ego view?) 68)
			(if (> (ego y?) 174)
				(ego setPri: 13)
			else
				(ego setPri: -1)
			)
		)
		(if
			(and
				(not (curRoom script?))
				(== (ego onControl:) 5)
				(!= (ego view?) 68)
			)
			(self setScript: DownStairs)
		)
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
					((Said '[/area,around]') (if (== (ego view?) 0) (Print 74 0) else (Print 74 1)))
					((Said '/lava') (Print 74 2))
					((Said '/ladder') (Print 74 3))
					((Said '/stair') (Print 74 4))
					((Said '/banister') (Print 74 5))
					((Said '/device,building,generator')
						(cond 
							((not forceBeamDestroyed) (if decodedMessage (Print 74 6) else (Print 74 7)))
							(decodedMessage (Print 74 8))
							(else (Print 74 9))
						)
					)
					((Said '/beam')
						(if (not forceBeamDestroyed)
							(if decodedMessage (Print 74 10) else (Print 74 11))
						else
							(Print 74 12)
						)
					)
					((Said '/pedestal') (Print 74 13))
				)
			)
			((Said 'up,climb,(climb[<up])[/ladder]')
				(if (ego inRect: 146 108 163 112)
					(curRoom setScript: UpLadder)
				else
					(Print 74 14)
				)
			)
		)
	)
	
	(method (newRoom newRoomNumber)
		(if (not script)
			(if local0 (= global255 1) else (= global255 0))
			(if forceBeamDestroyed (++ ortegaPostBeamRooms))
			(super newRoom: newRoomNumber)
		)
	)
)

(instance DownStairs of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					setLoop: 1
					illegalBits: 0
					setMotion: MoveTo 88 216 self
				)
			)
			(1
				(= local0 1)
				(HandsOn)
				(curRoom setScript: 0)
			)
		)
	)
)

(instance UpStairs of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					view: 0
					posn: 88 216
					setLoop: 0
					setPri: 14
					setStep: 3 2
					illegalBits: 0
					init:
				)
				(= cycles 2)
			)
			(1
				(ego setMotion: MoveTo 138 175 self)
			)
			(2
				(ego setLoop: -1 illegalBits: cYELLOW)
				(HandsOn)
				(curRoom setScript: 0)
			)
		)
	)
)

(instance UpLadder of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego illegalBits: 0)
				(RedrawCast)
				(ego
					posn: 154 107
					view: 97
					setLoop: 0
					setMotion: MoveTo 154 65 self
				)
			)
			(1
				(ego posn: 153 65 setMotion: MoveTo 153 31 self)
			)
			(2
				(ego posn: 152 31 setMotion: MoveTo 152 13 self)
			)
			(3
				(ego posn: 151 13 setMotion: MoveTo 151 -2 self)
			)
			(4
				(HandsOn)
				(= local1 TRUE)
				(curRoom setScript: 0)
			)
		)
	)
)

(instance DownLadder of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					init:
					posn: 151 -2
					view: 97
					illegalBits: 0
					setLoop: 0
					setMotion: MoveTo 151 13 self
				)
			)
			(1
				(ego posn: 152 13 setMotion: MoveTo 152 31 self)
			)
			(2
				(ego posn: 153 31 setMotion: MoveTo 153 65 self)
			)
			(3
				(ego posn: 154 65 setMotion: MoveTo 154 109 self)
			)
			(4
				(ego
					view: 68
					setLoop: -1
					setStep: 2 2
					illegalBits: -32768
				)
				(= cycles 2)
			)
			(5
				(ego loop: 3)
				(HandsOn)
				(curRoom setScript: 0)
			)
		)
	)
)
