;;; Sierra Script 1.0 - (do not remove this comment)
(script# 67)
(include game.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use Actor)

(public
	Room67 0
)

(local
	shakespeareBook
	secretDoor
)
(instance Room67 of Room
	(properties
		picture 67
		style (| BLACKOUT IRISOUT)
	)
	
	(method (init)
		(Load VIEW 510)
		(self setRegions: HAUNTED_HOUSE)
		(super init:)
		(= isIndoors TRUE)
		(if isNightTime
			((Prop new:)
				view: 536
				loop: 1
				cel: 0
				ignoreActors: 1
				posn: 205 129
				init:
				setPri: 12
				setCycle: Forward
			)
		)
		(if (== prevRoomNum 66)
			(ego posn: 85 123 view: 4 xStep: 4 yStep: 2 init:)
		)
		(if (== prevRoomNum 68)
			(ego posn: 264 130 view: 4 xStep: 4 yStep: 2 init:)
		)
		(if (== ((inventory at: iShakespeareBook) owner?) 67)
			((= shakespeareBook (View new:))
				view: 510
				loop: 0
				cel: 0
				posn: 207 74
				stopUpd:
				init:
			)
		)
		((= secretDoor (Prop new:))
			view: 510
			loop: 2
			cel: 0
			ignoreActors: TRUE
			stopUpd:
			posn: 74 101
			init:
			setPri: 8
		)
		(if mansionSecretDoorOpen
			(secretDoor setCel: 255 stopUpd:)
			(ego illegalBits: cWHITE)
		else
			(ego illegalBits: -32764)
		)
		(if
			(and
				(< 0 mansionPhase)
				(< mansionPhase 255)
				(== ghostRoomNum curRoomNum)
			)
			(NotifyScript HAUNTED_HOUSE -1)
		)
	)
	
	(method (doit)
		(super doit:)
		(if (& (ego onControl: FALSE) $0040)
			(ego illegalBits: cWHITE)
			(curRoom newRoom: 68)
		)
		(if (and (& (ego onControl: TRUE) $0004) mansionSecretDoorOpen)
			(ego illegalBits: cWHITE)
			(curRoom newRoom: 66)
		)
		(if (and (& (ego onControl:) $0020) mansionSecretDoorOpen)
			(ego setPri: 8)
		else
			(ego setPri: -1)
		)
	)
	
	(method (handleEvent event)
		(return
			(cond 
				((event claimed?) (return TRUE))
				((== (event type?) saidEvent)
					(cond 
						(
							(or
								(Said 'look[<around][/!*]')
								(Said 'look/room,parlor')
							)
							(Print 67 0)
						)
						((Said 'look>')
							(cond 
								((and (ego has: iShakespeareBook) (Said '/book')) ((inventory at: iShakespeareBook) showSelf:))
								((Said '/book,shelf,bookshelf')
									(if (> (ego x?) 160)
										(if (== ((inventory at: iShakespeareBook) owner?) 67)
											(Print 67 1)
											(= shakespeareBookKnown TRUE)
										else
											(Print 67 2)
										)
									else
										(Print 67 3)
									)
								)
								((Said '<behind,under/painting') (Print 67 4))
								((Said '/painting') (Print 67 5) (= lookedAtStrangeMansionPainting TRUE))
								((Said '/wall<left')
									(cond 
										(mansionSecretDoorOpen (Print 67 6))
										(lookedAtStrangeMansionPainting (Print 67 7) (= hiddenMansionLatchKnown TRUE))
										(else (Print 67 8))
									)
								)
								((Said '/wall<right') (Print 67 9))
								((Said '/wall')
									(cond 
										(mansionSecretDoorOpen (Print 67 6))
										(lookedAtStrangeMansionPainting (Print 67 7) (= hiddenMansionLatchKnown TRUE))
										(else (Print 67 10))
									)
								)
								((Said '/latch')
									(cond 
										(mansionSecretDoorOpen (Print 67 11))
										(lookedAtStrangeMansionPainting
											(if hiddenMansionLatchKnown
												(if (& (ego onControl:) $0020)
													(Print 67 12)
												else
													(Print 67 13)
												)
											)
										)
										(else (Print 67 14))
									)
								)
								((Said '<in/fireplace') (Print 67 15))
								((Said '/fireplace') (Print 67 16))
								((Said '/chandelier') (Print 67 17))
								((Said '/couch') (Print 67 18))
								((Said '/door') (if mansionSecretDoorOpen (Print 67 19) else (Print 67 20)))
								((Said '/cabinet,cabinet') (Print 67 21))
								((Said '/table,chair,chest,furniture') (Print 67 22))
								(
								(or (Said '/dirt,carpet,carpet') (Said '<down')) (Print 67 23))
							)
						)
						((Said 'find/door') (if mansionSecretDoorOpen (Print 67 19) else (Print 67 20)))
						((Said 'sit') (Print 67 24))
						(
						(or (Said 'get/book') (Said 'get/shakespeare'))
							(cond 
								((ego inRect: 83 119 127 127) (Print 67 25))
								((& (ego onControl:) $0010)
									(cond 
										((!= ((inventory at: iShakespeareBook) owner?) 67) (Print 67 26))
										(shakespeareBookKnown
											(Print 67 27)
											(ego get: iShakespeareBook)
											(theGame changeScore: 2)
											(shakespeareBook dispose:)
											(= gotItem TRUE)
										)
										(else
											(Print 67 28)
											(ego get: iShakespeareBook)
											(= gotItem TRUE)
											(theGame changeScore: 2)
											(shakespeareBook dispose:)
										)
									)
								)
								(else (Print 800 1))
							)
						)
						((Said 'get/painting') (Print 67 4))
						((Said 'open/door') (if mansionSecretDoorOpen (Print 67 29) else (Print 67 30)))
						((Said 'open/cabinet,cabinet') (Print 67 21))
						((Said 'close/door') (if mansionSecretDoorOpen (Print 67 31) else (Print 67 32)))
						((Said 'flip,move,lift/latch')
							(if hiddenMansionLatchKnown
								(cond 
									(mansionSecretDoorOpen (Print 67 33))
									((& (ego onControl:) $0020)
										(Print 67 34)
										(ego setMotion: 0)
										(secretDoor setCycle: EndLoop)
										(= mansionSecretDoorOpen TRUE)
										(theGame changeScore: 4)
										(ego illegalBits: cWHITE)
									)
									(else (Print 800 1))
								)
							else
								(Print 67 14)
							)
						)
					)
				)
			)
		)
	)
	
	(method (newRoom newRoomNumber)
		(ego setPri: -1)
		(super newRoom: newRoomNumber)
	)
)
