;;; Sierra Script 1.0 - (do not remove this comment)
(script# 89)
(include game.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	Room89 0
)

(local
	rightCabinetDoor
	leftCabinetDoor
	egoInventory
	dishes
	node
	stolenInventory
	local6
	recoveredItems
)
(procedure (RecoverItems &tmp retVal)
	(= retVal 0)
	(= node (inventory first:))
	(while node
		(if
			(and
				(= stolenInventory (NodeValue node))
				(== (stolenInventory owner?) 89)
			)
			(= retVal TRUE)
		)
		(= node (inventory next: node))
	)
	(return retVal)
)

(instance Room89 of Room
	(properties
		picture 89
		style (| BLACKOUT IRISOUT)
	)
	
	(method (init)
		(Load VIEW 606)
		(Load VIEW 649)
		(Load VIEW 634)
		(Load VIEW 512)
		(self setRegions: LOLOTTE)
		(super init:)
		((View new:)
			view: 634
			loop: 1
			cel: 0
			posn: 57 88
			setPri: 6
			init:
			addToPic:
		)
		(if isNightTime
			((Prop new:)
				view: 512
				loop: 0
				posn: 59 76
				setPri: 6
				init:
				setCycle: Forward
			)
			((View new:)
				view: 649
				loop: 2
				cel: 0
				posn: 264 105
				setPri: 7
				init:
				addToPic:
			)
		)
		((= rightCabinetDoor (Prop new:))
			view: 606
			loop: 0
			posn: 241 108
			init:
			setPri: 8
			stopUpd:
		)
		((= leftCabinetDoor (Prop new:))
			view: 606
			posn: 76 108
			loop: 1
			init:
			setPri: 8
			stopUpd:
		)
		((= egoInventory (View new:))
			view: 606
			posn: 222 108
			loop: 2
			cel: 0
			ignoreActors: TRUE
			init:
			stopUpd:
		)
		(if (not (= recoveredItems (RecoverItems)))
			(egoInventory cel: 2 forceUpd:)
		)
		((= dishes (View new:))
			view: 606
			posn: 96 108
			loop: 2
			cel: 1
			ignoreActors: 1
			init:
			stopUpd:
		)
		(if (or (== prevRoomNum 91) (== prevRoomNum 0))
			(ego posn: 38 151 view: 4 xStep: 4 yStep: 2 init:)
			(if henchChasingEgo
				(= henchChasingEgo FALSE)
			)
		)
	)
	
	(method (doit)
		(super doit:)
		(if (& (ego onControl: 0) cBROWN)
			(curRoom newRoom: 91)
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
								(Said 'look[<around][/noword]')
								(Said 'look/room,castle,kitchen')
							)
							(Print 89 0)
						)
						((Said 'look>')
							(cond 
								((Said '<in/cabinet')
									(if
										(or
											(< (ego distanceTo: rightCabinetDoor) 25)
											(< (ego distanceTo: leftCabinetDoor) 25)
										)
										(if (< (ego distanceTo: rightCabinetDoor) 25)
											(if (== (rightCabinetDoor cel?) 0)
												(rightCabinetDoor setCycle: EndLoop rightCabinet)
											)
											(if recoveredItems
												(Print 89 1)
											else
												(Print 89 2)
											)
										)
										(if (< (ego distanceTo: leftCabinetDoor) 25)
											(if (== (leftCabinetDoor cel?) 0)
												(leftCabinetDoor setCycle: EndLoop leftCabinet)
											)
											(Print 89 3)
										)
									else
										(Print 800 1)
									)
								)
								((Said '/cabinet')
									(Print 89 4)
								)
								((Said '<under/table')
									(Print 89 5)
								)
								((Said '/table')
									(Print 89 6)
								)
								((Said '/fireplace')
									(Print 89 7)
								)
								((Said '/caldron')
									(Print 89 8)
								)
								((Said '/barrel')
									(Print 89 9)
								)
								((Said '/wall')
									(Print 89 10)
								)
								((or (Said '/dirt') (Said '<down'))
									(Print 89 11)
								)
								((Said '/window')
									(if (ego inRect: 220 124 282 146)
										(Print 89 12)
									else
										(Print 800 1)
									)
								)
								((Said '/door')
									(Print 89 13)
								)
							)
						)
						((Said 'open>')
							(cond 
								((Said '/barrel')
									(Print 89 14)
								)
								((Said '/window')
									(Print 89 15)
								)
								((Said '/cabinet,door')
									(cond 
										((ego inRect: 210 123 249 133)
											(if (== (rightCabinetDoor cel?) 0)
												(rightCabinetDoor setCycle: EndLoop rightCabinet)
												(if recoveredItems
													(Print 89 16)
												else
													(Print 89 2)
												)
											else
												(Print 89 17)
											)
										)
										((ego inRect: 74 122 100 133)
											(if (== (leftCabinetDoor cel?) 0)
												(leftCabinetDoor setCycle: EndLoop leftCabinet)
												(Print 89 3)
											else
												(Print 89 17)
											)
										)
										(else
											(Print 800 1)
										)
									)
								)
							)
						)
						((Said 'close/cabinet,door')
							(cond 
								((< (ego distanceTo: rightCabinetDoor) 25)
									(if (rightCabinetDoor cel?)
										(rightCabinetDoor setCycle: BegLoop rightCabinet)
									else
										(Print 89 18)
									)
								)
								((< (ego distanceTo: leftCabinetDoor) 25)
									(if (leftCabinetDoor cel?)
										(leftCabinetDoor setCycle: BegLoop leftCabinet)
									else
										(Print 89 18)
									)
								)
								(else (Print 800 1))
							)
						)
						((Said 'break/window')
							(Print 89 19)
						)
						((Said 'get>')
							(cond 
								((Said '/caldron')
									(Print 89 20)
								)
								((Said '/dish')
									(if (not (leftCabinetDoor cel?))
										(Print 89 21)
									else
										(Print 89 22)
									)
								)
								((Said '/all,possessions,inventory')
									(cond 
										((not recoveredItems)
											(Print 89 2)
										)
										((not (rightCabinetDoor cel?))
											(Print 89 21)
										)
										((> (ego distanceTo: rightCabinetDoor) 25)
											(Print 800 1)
										)
										(else
											(= local6 1)
											(= node (inventory first:))
											(while node
												(if
													(and
														(= stolenInventory (NodeValue node))
														(== (stolenInventory owner?) 89)
													)
													(stolenInventory owner: ego)
													(= local6 0)
												)
												(= node (inventory next: node))
											)
											(if (not local6)
												(theGame changeScore: 4)
												(egoInventory loop: 2 cel: 2 startUpd: forceUpd:)
												(= gotItem TRUE)
												(= recoveredItems FALSE)
											else
												(Print 89 2)
											)
											(egoInventory stopUpd:)
										)
									)
								)
								(
									(and
										recoveredItems
										(< (ego distanceTo: rightCabinetDoor) 25)
										(!= (rightCabinetDoor cel?) (- (NumCels rightCabinetDoor) 1))
									)
									(Print 89 23)
									(event claimed: TRUE)
								)
							)
						)
					)
				)
			)
		)
	)
)

(instance rightCabinet of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(rightCabinetDoor stopUpd:)
				(= state -1)
			)
		)
	)
)

(instance leftCabinet of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(leftCabinetDoor stopUpd:)
				(= state -1)
			)
		)
	)
)
