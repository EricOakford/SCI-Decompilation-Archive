;;; Sierra Script 1.0 - (do not remove this comment)
(script# 70)
(include game.sh)
(use Main)
(use Intrface)
(use Avoider)
(use Sound)
(use Motion)
(use Game)
(use Invent)
(use Actor)
(use System)

(public
	Room70 0
)

(local
	board
	local1
	local2
	poof
	cascade1
	cascade2
	cascade3
	cascade4
	cascade5
	ripple1
	ripple2
	local11
)
(instance poofSound of Sound
	(properties)
)

(instance Room70 of Room
	(properties
		picture 70
	)
	
	(method (init)
		(= isIndoors TRUE)
		(super init:)
		(= cascade1 (Prop new:))
		(= cascade2 (Prop new:))
		(= cascade3 (Prop new:))
		(= cascade4 (Prop new:))
		(= cascade5 (Prop new:))
		(= ripple1 (Prop new:))
		(= ripple2 (Prop new:))
		(cascade1
			isExtra: TRUE
			view: 668
			loop: 0
			cel: 2
			posn: 120 42
			setPri: 1
			setCycle: Forward
			cycleSpeed: 1
			ignoreActors:
			init:
		)
		(cascade2
			isExtra: TRUE
			view: 668
			loop: 1
			cel: 0
			posn: 171 39
			setPri: 0
			setCycle: Forward
			cycleSpeed: 1
			ignoreActors:
			init:
		)
		(cascade2
			isExtra: TRUE
			view: 668
			loop: 2
			cel: 1
			posn: 142 97
			setPri: 6
			setCycle: Forward
			cycleSpeed: 1
			ignoreActors:
			init:
		)
		(cascade4
			isExtra: TRUE
			view: 668
			loop: 3
			cel: 1
			posn: 121 102
			setPri: 6
			setCycle: Forward
			cycleSpeed: 1
			ignoreActors:
			init:
		)
		(cascade5
			isExtra: TRUE
			view: 668
			loop: 4
			cel: 1
			posn: 131 122
			setPri: 0
			setCycle: Forward
			cycleSpeed: 1
			ignoreActors:
			init:
		)
		(ripple1
			isExtra: TRUE
			view: 668
			loop: 5
			cel: 2
			posn: 105 122
			setPri: 0
			setCycle: Forward
			cycleSpeed: 1
			ignoreActors:
			init:
		)
		(ripple2
			isExtra: TRUE
			view: 668
			loop: 6
			cel: 0
			posn: 145 118
			setPri: 0
			setCycle: Forward
			cycleSpeed: 1
			ignoreActors:
			init:
		)
		(Load VIEW 7)
		(Load VIEW 8)
		(Load VIEW 377)
		(Load VIEW 378)
		(Load VIEW 680)
		(Load VIEW 21)
		(Load VIEW 10)
		(if (== prevRoomNum 24)
			(ego setScript: swimIn illegalBits: cWHITE)
		else
			(ego
				view: 2
				setStep: 3 2
				illegalBits: cWHITE
				posn: 224 114
				init:
			)
		)
		(if ((Inventory at: iBoard) ownedBy: 70)
			((= board (View new:))
				view: 515
				posn: 195 112
				loop: 1
				cel: 0
				init:
				stopUpd:
			)
		)
		(ego init:)
		(= inCutscene FALSE)
	)
	
	(method (doit)
		(super doit:)
		(= local2 (ego onControl: 1))
		(if (== (ego script?) 0)
			(cond 
				(
					(or
						(ego inRect: 142 110 166 125)
						(ego inRect: 49 117 111 128)
					)
					(ego setScript: sweptOut)
				)
				((& local2 $0001) (= currentStatus egoNormal) (ego setCycle: Walk) (ego view: 2))
				((& local2 $0800) (ego setCycle: Walk) (ego view: 7) (= currentStatus egoInWaistDeepWater))
				((& local2 $0002) (ego view: 8) (= currentStatus egoSwimming) (ego setCycle: Forward))
			)
			(if (ego inRect: 209 112 225 120)
				(ego baseSetter: (ScriptID 0 1))
			else
				(ego baseSetter: 0)
			)
		)
		(if (& (ego onControl:) $0040) (curRoom newRoom: 71))
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return TRUE))
		(return
			(if (== (event type?) saidEvent)
				(cond 
					((Said 'look>')
						(cond 
							((Said '/falls') (Print 70 0))
							((Said '/cliff') (Print 70 1))
							((Said '/cave') (Print 70 2))
							((Said '/boulder') (Print 70 3))
							((Said '/flora') (Print 70 4))
							((Said '/dirt')
								(if ((Inventory at: iBoard) ownedBy: 70)
									(Print 70 5)
								else
									(Print 70 6)
								)
							)
							((Said '<under/water,pool') (if (== (ego view?) 2) (Print 70 7) else (Print 70 8)))
							((Said '<in,in/water,pool') (Print 70 9))
							((Said '/falls') (Print 70 10))
							((Said '<in/pool,water') (Print 70 11))
							((Said '/pool,water') (Print 70 12))
							((Said '[<around][/room]')
								(Print
									(Format @str 70 13
										(if ((Inventory at: iBoard) ownedBy: 70)
											{You see an old board lying by the cave entrance.}
										else
											{}
										)
									)
								)
							)
						)
					)
					((Said 'climb/cliff') (Print 70 14))
					((Said 'climb/boulder') (Print 70 15))
					((Said 'get/board')
						(if ((Inventory at: iBoard) ownedBy: 70)
							(if (< (ego distanceTo: board) 12)
								(ego setScript: boardActions)
								(boardActions changeState: 1)
								(theGame changeScore: 2)
							else
								(Print 800 1)
							)
						else
							(Print 70 16)
						)
					)
					((or (Said 'drink') (Said 'get/drink'))
						(if (== (ego view?) 2)
							(if
								(or
									(& (= local1 (IsObjectOnControl ego 10)) $0008)
									(& local1 $0800)
									(& local1 $0002)
									(& local1 $0200)
								)
								(ego setScript: drinking)
							else
								(Print 800 1)
							)
						else
							(Print 70 17)
						)
					)
					((Said 'get/water') (Print 70 18))
					((Said 'dive,bathe[<enter]')
						(if (== (ego view?) 2)
							(Print 70 19)
						else
							(Print 70 20)
						)
					)
				)
			else
				FALSE
			)
		)
	)
	
	(method (newRoom newRoomNumber)
		(HandsOn)
		(ego baseSetter: 0)
		(super newRoom: newRoomNumber)
	)
)

(instance swimIn of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					view: 377
					posn: 158 119
					setCycle: Walk
					setAvoider: Avoider
					setMotion: MoveTo 167 123 self
				)
			)
			(1
				(ego setMotion: MoveTo 167 123 self)
			)
			(2
				(ego setMotion: MoveTo 183 122 self)
			)
			(3
				(ego
					view: 378
					cel: 255
					setCycle: EndLoop
					setMotion: MoveTo 207 120 self
				)
			)
			(4
				(poofSound number: 59 play:)
				(= poof (Prop new:))
				(poof
					view: 680
					posn: (ego x?) (ego y?)
					loop: 1
					setPri: (+ (ego priority?) 1)
					cel: 9
					setCycle: CycleTo 2 -1 self
					init:
				)
			)
			(5
				(ego view: 2 setAvoider: 0 setCycle: Walk)
				(poof setCycle: BegLoop self)
			)
			(6
				(poof dispose:)
				(Print 70 21 #draw)
				(ego setScript: 0)
				(HandsOn)
			)
		)
	)
)

(instance boardActions of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(1
				(HandsOff)
				(Face ego board)
				(ego view: 21 cel: 255 setMotion: 0 setCycle: EndLoop self)
			)
			(2
				(= gotItem TRUE)
				(board dispose:)
				(ego get: iBoard)
				(ego setCycle: BegLoop self)
			)
			(3
				(ego view: 2 setCycle: Walk)
				(HandsOn)
				(ego setScript: 0)
			)
		)
	)
)

(instance drinking of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego view: 21 cel: 255 setCycle: EndLoop self)
			)
			(1
				(Timer setReal: self 5)
				(Print 70 22 #at -1 10 #dispose)
			)
			(2 (ego setCycle: BegLoop self))
			(3
				(if modelessDialog (modelessDialog dispose:))
				(HandsOn)
				(ego view: 2 setCycle: Walk setScript: 0)
			)
		)
	)
)

(instance sweptOut of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= inCutscene TRUE)
				(if (ego inRect: 142 110 166 125)
					(ego setMotion: MoveTo 152 113)
				else
					(ego setMotion: MoveTo 80 118)
				)
				(ego view: 10 cel: 255 loop: 0 setCycle: EndLoop self)
			)
			(1
				(ego hide:)
				(curRoom newRoom: 24)
			)
		)
	)
)
