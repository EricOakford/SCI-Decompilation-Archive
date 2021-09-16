;;; Sierra Script 1.0 - (do not remove this comment)
(script# GENESTA)
(include game.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use Invent)
(use Actor)
(use System)

(public
	gfReg 0
)
(synonyms
	(ocean seawater ocean)
)

(local
	featherY
	featherX
	featherRoom
	peacockFeather
	peacock
)
(instance gfReg of Region
	(method (init)
		(super init:)
		(switch curRoomNum
			(33
				(= featherX 198)
				(= featherY 155)
			)
			(34
				(= featherX 172)
				(= featherY 133)
			)
			(35
				(= featherX 126)
				(= featherY 144)
			)
			(39
				(= featherX 224)
				(= featherY 113)
			)
			(40
				(= featherX 241)
				(= featherY 117)
			)
			(41
				(= featherX 102)
				(= featherY 109)
			)
		)
		(if (< (Random 1 100) 75)
			(= featherRoom (+ (Random 1 3) 32))
		else
			(= featherRoom (+ (Random 1 3) 38))
		)
		(if ((Inventory at: iFeather) ownedBy: 888)
			((Inventory at: iFeather) moveTo: featherRoom)
		)
		(if ((Inventory at: iFeather) ownedBy: curRoomNum)
			((= peacockFeather (View new:))
				view: 514
				posn: featherX featherY
				loop: 0
				cel: 0
				init:
				stopUpd:
			)
		)
		(if (and (<= (Random 1 100) 40) (> featherX 0))
			(Load VIEW 337)
			(= peacock (Actor new:))
			(peacock
				view: 336
				moveSpeed: 1
				cycleSpeed: 1
				setCycle: Walk
				setMotion: Wander 30000
				illegalBits: -2
				posn: featherX (- featherY 4)
				init:
				setScript: peacockActions
				setStep: 1 1
			)
		)
	)
	
	(method (doit)
		(if
			(and
				(cast contains: peacock)
				(not (peacock inRect: -10 50 329 199))
			)
			(peacock dispose:)
		)
	)
	
	(method (dispose)
		(timers eachElementDo: #dispose 84)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return TRUE))
		(return
			(if (== (event type?) saidEvent)
				(cond 
					((Said 'look>')
						(cond 
							((Said '/beach,beach')
								(if ((Inventory at: iFeather) ownedBy: curRoomNum)
									(Print 505 0)
								else
									(Print 505 1)
								)
							)
							((Said '/feather')
								(cond 
									((cast contains: peacockFeather)
										(Print 505 2)
									)
									((ego has: iFeather)
										((Inventory at: iFeather) showSelf:)
									)
									(else (Print 505 3))
								)
							)
							((Said '/tamir')
								(Print 505 4)
							)
							((Said '<in,under/ocean,water')
								(if (!= (ego view?) 2)
									(Print 505 5)
								else
									(Print 505 6)
								)
							)
							((Said '/ocean,water')
								(Print 505 7)
							)
							((or (Said '/dirt') (Said '<down'))
								(cond 
									(((Inventory at: iFeather) ownedBy: curRoomNum)
										(Print 505 0)
									)
									((and (>= curRoomNum 36) (<= curRoomNum 38))
										(Print 505 8)
									)
									(else
										(Print 505 9)
									)
								)
							)
							((Said '/boulder')
								(Print 505 10)
							)
							((Said '/forest')
								(Print 505 11)
							)
							((Said '/grass')
								(Print 505 12)
							)
							((Said '/bush')
								(Print 505 13)
							)
							((Said '/flora')
								(Print 505 14)
							)
							((Said '/blossom')
								(Print 505 15)
							)
							((Said '/garden')
								(Print 505 16)
							)
							((Said '/castle')
								(Print 505 17)
							)
							((Said '/peacock')
								(if (cast contains: peacock)
									(Print 505 18)
								else
									(Print 505 19)
								)
							)
							((Said '/bird')
								(if (cast contains: peacock)
									(Print 505 18)
								else
									(event claimed: FALSE)
								)
							)
						)
					)
					((Said 'climb/forest')
						(Print 505 20)
					)
					((Said 'converse>')
						(cond 
							((Said '/peacock')
								(if (cast contains: peacock)
									(Print 505 21)
								else
									(Print 505 19)
								)
							)
							((Said '/bird')
								(if (cast contains: peacock)
									(Print 505 21)
								else
									(event claimed: FALSE)
								)
							)
						)
					)
					((Said 'get/feather')
						(cond 
							(((Inventory at: iFeather) ownedBy: curRoomNum)
								(if (< (ego distanceTo: peacockFeather) 15)
									(ego get: iFeather)
									(theGame changeScore: 2)
									(ego setScript: getFeather)
								else
									(Print 800 1)
								)
							)
							((ego has: iFeather)
								(event claimed: FALSE)
							)
							(else
								(Print 505 22)
							)
						)
					)
					((Said 'get/blossom')
						(Print 505 23)
					)
					((Said 'get,capture/peacock')
						(if (cast contains: peacock)
							(Print 505 24)
						else
							(Print 505 3)
						)
					)
					((Said 'get,capture/bird,parrot')
						(Print 505 25)
					)
					((Said 'kiss')
						(if (cast contains: peacock)
							(Print 505 26)
						else
							(event claimed: FALSE)
						)
					)
				)
			else
				FALSE
			)
		)
	)
)

(instance getFeather of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego view: 21)
				(Face ego peacockFeather)
				(ego setCycle: EndLoop self)
			)
			(1
				(ego setCycle: BegLoop self)
				(= gotItem TRUE)
				(peacockFeather dispose:)
			)
			(2
				(ego view: 2 setScript: 0 setCycle: Walk)
				(HandsOn)
			)
		)
	)
)

(instance peacockActions of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				((ScriptID 0 4) setReal: self (Random 10 40))
			)
			(1
				(peacock
					view: 337
					setLoop: 0
					setMotion: 0
					cel: 0
					setCycle: EndLoop self
				)
			)
			(2
				((ScriptID 0 4) setReal: self 4)
			)
			(3
				(peacock setCycle: BegLoop self)
			)
			(4
				(peacock
					view: 336
					setLoop: -1
					setCycle: Walk
					setMotion: Wander 30000
				)
				(self changeState: 0)
			)
		)
	)
)
