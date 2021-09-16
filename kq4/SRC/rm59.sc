;;; Sierra Script 1.0 - (do not remove this comment)
(script# 59)
(include game.sh)
(use Main)
(use Intrface)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	Room59 0
)
(synonyms
	(ghost baby)
)

(local
	crib
)
(instance babySound of Sound
	(properties)
)

(instance Room59 of Room
	(properties
		picture 59
		style (| BLACKOUT IRISOUT)
	)
	
	(method (init)
		(self setRegions: HAUNTED_HOUSE)
		(Load VIEW 527)
		(Load VIEW 648)
		(Load VIEW 510)
		(Load VIEW 536)
		(super init:)
		((View new:)
			view: 510
			loop: 1
			cel: 0
			posn: 80 103
			setPri: 10
			addToPic:
		)
		(if isNightTime
			((View new:)
				view: 648
				loop: 0
				cel: 0
				posn: 51 103
				setPri: 6
				addToPic:
			)
			((Prop new:)
				view: 536
				loop: 2
				cel: 1
				posn: 80 97
				init:
				setPri: 11
				setCycle: Forward
			)
		)
		((= crib (Prop new:))
			view: 527
			loop: 1
			posn: 147 117
			setPri: 1
			init:
			stopUpd:
		)
		(ego posn: 270 129 view: 4 xStep: 4 yStep: 2 init:)
		(self setScript: rm59Script)
		(if (and (== ghostRoomNum 59) (== mansionPhase mansionBABY))
			(rockAgain cue:)
			(rm59Script changeState: 1)
		)
	)
	
	(method (doit)
		(if (& (ego onControl: FALSE) $0040) (curRoom newRoom: 62))
		(super doit:)
	)
	
	(method (handleEvent event &tmp inventorySaidMe)
		(return
			(if (event claimed?)
				(return TRUE)
			else
				(if (== (event type?) saidEvent)
					(cond 
						((Said 'look>')
							(cond 
								((Said '[<around][/room,bedroom,nursery]') (Print 59 0))
								((Said '<in/chest,dresser,drawer') (Print 59 1))
								((Said '/chest,dresser') (Print 59 2))
								((Said '/chair,rocker') (Print 59 3))
								((Said '/window')
									(if (ego inRect: 76 125 109 147)
										(Print 59 4)
									else
										(Print 800 1)
									)
								)
								(
								(or (Said '<in/cradle') (Said 'ghost[<ghost]'))
									(if (< (ego distanceTo: crib) 15)
										(if (!= mansionPhase mansionBABY) (Print 59 5) else (Print 59 6))
									else
										(Print 800 1)
									)
								)
								((Said '/cradle') (if (!= mansionPhase mansionBABY) (Print 59 7) else (Print 59 8)))
								((Said '/wall') (Print 59 9))
								((or (Said '/dirt') (Said '<down')) (Print 59 10))
								((Said '/horse,toy') (Print 59 11))
								((Said '/carpet') (Print 59 12))
								(else (event claimed: FALSE))
							)
						)
						((Said 'open/chest,dresser,drawer') (Print 59 1))
						((Said 'sit') (Print 59 13))
						((Said 'boulder[/cradle]')
							(cond 
								((== mansionPhase mansionBABY) (Print 59 14))
								((< (ego distanceTo: crib) 10) (rockAgain changeState: 0) (event claimed: TRUE))
								(else (Print 800 1))
							)
						)
						((Said '(get<on),mount,play/horse,toy') (Print 59 15))
						((Said 'get/horse,toy') (Print 59 16))
					)
				)
				(if
					(or
						(event claimed?)
						(!= (event type?) saidEvent)
						(!= mansionPhase mansionBABY)
					)
					(return (!= mansionPhase mansionBABY))
				)
				(cond 
					(
						(or
							(Said 'look<in/cradle')
							(Said
								'look,(feel[<in,around]),find/cradle,(ghost[<ghost])'
							)
						)
						(if (< (ego distanceTo: crib) 15)
							(if (!= mansionPhase mansionBABY) (Print 59 5) else (Print 59 6))
						else
							(Print 800 1)
						)
					)
					((Said 'converse,calm,calm') (Print 59 17))
					((Said 'kiss') (Print 59 18))
					((Said '/ghost[<ghost]>')
						(cond 
							((Said 'get,hold,capture,(get<up)') (Print 59 19))
							((Said 'kill') (Print 59 20))
							((Said 'help,,') (Print 59 21))
							((Said 'feed') (Print 59 22))
						)
					)
					((Said 'hum[/ghost,lullaby]') (Print 59 23))
					((Said 'deliver,throw,place,drop>')
						(cond 
							(
								(or
									(not (= inventorySaidMe (inventory saidMe:)))
									(not (ego has: (inventory indexOf: inventorySaidMe)))
								)
								(event claimed: FALSE)
							)
							((>= (ego distanceTo: crib) 17) (NotClose))
							((!= (inventory indexOf: inventorySaidMe) iRattle) (Print 59 24))
							(else
								(Print 59 25)
								(ego put: iRattle 59)
								(theGame changeScore: 2)
								(NotifyScript HAUNTED_HOUSE 2)
							)
						)
					)
				)
			)
		)
	)
)

(instance rockMeBaby of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(crib
					cycleSpeed: 1
					setCycle: Forward
					brTop: (- (crib y?) 2)
				)
			)
			(1
				(crib stopUpd: brTop: (- (crib y?) 2) setCel: 0)
				(= state -1)
			)
		)
	)
)

(instance rockAgain of Script
	(properties)
	
	(method (init param1)
		(super init: param1)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(rockMeBaby changeState: 0)
				(babySound number: 22 play: self)
			)
			(1
				(rockMeBaby cue:)
				(if (== mansionPhase mansionBABY) (self changeState: 0))
			)
		)
	)
)

(instance rm59Script of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 102])
		(switch (= state newState)
			(1 (= seconds 5))
			(2 (Print 59 26))
		)
	)
)
