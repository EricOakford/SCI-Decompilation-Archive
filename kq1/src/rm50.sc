;;; Sierra Script 1.0 - (do not remove this comment)
(script# 50)
(include game.sh)
(use Main)
(use Intrface)
(use Block)
(use LoadMany)
(use RFeature)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm50 0
)

(instance rm50 of Room
	(properties
		picture 50
		horizon 48
		north 22
		east 51
	)
	
	(method (init)
		(LoadMany VIEW 251 274 701)
		(self style:
			(switch prevRoomNum
				(north IRISIN)
				(east WIPELEFT)
			)
		)
		(super init:)
		(NormalEgo)
		(switch prevRoomNum
			(north
				(ego posn: 58 51 loop: 0)
			)
			(east
				(ego posn: 289 143)
			)
			(else
				(ego posn: 289 143)
			)
		)
		(ego init:)
		(if (not (Btst fDragonDoused))
			(addToPics add: rock eachElementDo: #init doit:)
			(ego observeBlocks: rockBlock)
		)
		(if (Btst fGoatFollows)
			(Print 50 0)
			(Bclr fGoatFollows)
		)
		(drip1 init: setScript: doDrip1)
		(puddle1 init:)
		(drip2 init: setScript: doDrip2)
		(puddle2 init:)
	)
	
	(method (doit &tmp nRoom)
		(cond 
			(script
				(script doit:)
			)
			(
				(= nRoom
					(switch ((User alterEgo?) edgeHit?)
						(NORTH north)
						(EAST east)
					)
				)
				(if (and (== nRoom north) (== prevRoomNum 51) (Btst fDragonDoused))
					(SolvePuzzle fEnteredRockCave 2)
				)
				(curRoom newRoom: nRoom)
			)
		)
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?) (return))
			((super handleEvent: event) (return))
			((Said 'look,check/boulder,granite')
				(if (Btst fDragonDoused)
					(Print 50 1)
				else
					(Print 50 2)
				)
			)
			((Said 'look,check>')
				(cond 
					((Said '[<at,around][/cave,room]')
						(if (Btst fDragonDoused)
							(Print 50 3)
						else
							(Print 50 4)
						)
					)
					((Said '/stalactite,grass,wall')
						(Print 50 5)
					)
					((Said '/hole')
						(cond 
							((ego inRect: 0 0 169 82)
								(Print 50 6)
							)
							((ego inRect: 200 105 321 191)
								(if (Btst fDragonDoused)
									(Print 50 7)
								else
									(Print 50 8)
								)
							)
							(else
								(Print 50 9)
							)
						)
					)
				)
			)
			((Said 'get,take/boulder')
				(Print 50 10)
			)
			((Said 'move,pull/boulder')
				(if (> (ego distanceTo: rock) 50)
					(Print 50 11)
				else
					(Print 50 12)
				)
			)
			((Said 'take,(pick<up)/slime')
				(Print 50 13)
			)
			((Said 'climb,scale/boulder')
				(Print 50 14)
			)
			((Said 'get,take,drink/water,puddle')
				(Print 50 15)
			)
		)
	)
)

(instance rock of RPicView
	(properties
		x 311
		y 146
		view 251
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbLook
				(if (Btst fDragonDoused)
					(Print 50 1)
				else
					(Print 50 2)
				)
			)
		)
	)
)

(instance rockBlock of Block
	(properties
		top 132
		left 276
		bottom 146
		right 346
	)
)

(instance drip1 of Prop
	(properties
		x 176
		y 51
		view 274
		loop 2
		priority 12
		signal (| ignrAct fixPriOn)
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?) (return))
			((super handleEvent: event) (return))
			((Said 'look,check/drip')
				(self doVerb: verbLook)
			)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbLook
				(Print 50 16)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance drip2 of Prop
	(properties
		x 252
		y 65
		view 274
		loop 2
		priority 13
		signal (| ignrAct fixPriOn)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbLook
				(Print 50 16)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance puddle1 of Prop
	(properties
		x 178
		y 165
		view 701
		signal ignrAct
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?) (return))
			((super handleEvent: event) (return))
			((Said 'look,check/puddle,water')
				(self doVerb: verbLook)
			)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbLook
				(Print 50 17)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance puddle2 of Prop
	(properties
		x 252
		y 179
		view 701
		loop 1
		signal ignrAct
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbLook
				(Print 50 17)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance doDrip1 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= cycles
					(Random (* howFast 10) (* howFast 30))
				)
			)
			(1 (drip1 setCycle: EndLoop self))
			(2
				(drip1 stopUpd:)
				(puddle1 setCycle: EndLoop self)
			)
			(3
				(puddle1 stopUpd:)
				(self changeState: 0)
			)
		)
	)
)

(instance doDrip2 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= cycles
					(Random (* howFast 10) (* howFast 30))
				)
			)
			(1 (drip2 setCycle: EndLoop self))
			(2
				(drip2 stopUpd:)
				(puddle2 setCycle: EndLoop self)
			)
			(3
				(puddle2 stopUpd:)
				(self changeState: 0)
			)
		)
	)
)
