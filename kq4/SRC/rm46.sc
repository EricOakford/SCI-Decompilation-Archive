;;; Sierra Script 1.0 - (do not remove this comment)
(script# 46)
(include game.sh)
(use Main)
(use Intrface)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	Room46 0
)
(synonyms
	(room room castle)
)

(local
	fairy1
	fairy2
)
(instance theMusic of Sound
	(properties
		number 33
	)
)

(instance Room46 of Room
	(properties
		picture 46
		style (| BLACKOUT IRISOUT)
	)
	
	(method (init)
		(Load VIEW 110)
		(Load VIEW 114)
		(super init:)
		(= currentStatus egoNormal)
		(if (or (== prevRoomNum 47) (== prevRoomNum 0))
			(ego
				view: 4
				baseSetter: (ScriptID 0 1)
				posn: 250 164
				setStep: 4 2
				setPri: -1
				init:
			)
			(HandsOff)
		)
		(if (== prevRoomNum 45)
			(ego
				view: 4
				baseSetter: (ScriptID 0 1)
				posn: 184 23
				setStep: 4 2
				setPri: 6
				illegalBits: 16384
				init:
			)
		)
		((= fairy1 (Actor new:))
			view: 111
			posn: 231 65
			illegalBits: 8192
			setPri: 14
			xStep: 1
			yStep: 3
			setMotion: Wander 5
			setCycle: Forward
			init:
		)
		((= fairy2 (Actor new:))
			view: 113
			posn: 132 144
			illegalBits: 8192
			setPri: 14
			xStep: 1
			yStep: 3
			setMotion: Wander 5
			setCycle: Forward
			init:
		)
		(theMusic play:)
		(ego
			observeBlocks: stairBlock1 stairBlock2 stairBlock3 stairBlock4
		)
	)
	
	(method (doit)
		(super doit:)
		(if (& (ego onControl: FALSE) $0040)
			(ego setPri: -1 baseSetter: 0)
			(curRoom newRoom: 47)
		)
		(if (& (ego onControl: FALSE) $0020)
			(HandsOn)
			(ego setPri: -1 illegalBits: cWHITE baseSetter: 0)
			(curRoom newRoom: 45)
		)
		(if (& (ego onControl: FALSE) $0200) (ego setPri: 4))
		(if (& (ego onControl: FALSE) $0010)
			(if
			(and (== currentStatus egoOnStairs) (== (ego script?) 0))
				(ego illegalBits: cWHITE setPri: 4)
			else
				(ego setPri: 6)
			)
		)
		(if
			(and
				(& (ego onControl: FALSE) $0008)
				(not (& (ego signal?) $0010))
			)
			(ego setPri: 4)
		)
		(if (& (ego onControl: FALSE) $1000) (ego setPri: -1))
		(if
			(and
				(!= currentStatus egoOnStairs)
				(or
					(& (ego onControl: FALSE) $0800)
					(& (ego onControl: TRUE) $0400)
				)
			)
			(HandsOff)
			(= currentStatus egoOnStairs)
			(ego setScript: moveOnTheStairs)
			(if (& (ego onControl: FALSE) $0800)
				(moveOnTheStairs changeState: 10)
			else
				(moveOnTheStairs changeState: 1)
			)
		)
	)
	
	(method (dispose)
		(theMusic dispose:)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(return
			(cond 
				((event claimed?) (return TRUE))
				((== (event type?) saidEvent)
					(cond 
						((Said 'look>')
							(cond 
								((Said '<up[/!*]') (Print 46 0))
								((Said '<down[/!*]') (Print 46 1))
								((Said '[<up,down]/stair[<tower]') (Print 46 2))
								((Said '[<out,through]/window')
									(if (ego inRect: 33 81 67 112)
										(Print 46 3)
									else
										(Print 46 4)
									)
								)
								((Said '/wall') (Print 46 5))
								((Said '[<down]/dirt') (Print 46 6))
								((Said '/painting,tapestries') (Print 46 7))
								((Said '/flora,blossom') (Print 46 8))
								((Said '/fairies[<little]') (Print 46 9))
								((Said '[<around][/room,tower]') (Print 46 10))
							)
						)
						(
							(or
								(Said 'converse/fairies[<little]')
								(Said 'converse[/!*]')
							)
							(Print 46 11)
						)
						((Said 'kill/fairies[<little]') (Print 46 12))
						((Said 'get/fairies[<little]') (Print 46 13))
						((Said 'capture/fairies[<little]') (Print 46 13))
						(
						(or (Said 'kiss/fairies[<little]') (Said 'kiss[/!*]')) (Print 46 14))
						((Said 'help/fairies[<little]') (Print 46 15))
					)
				)
			)
		)
	)
)

(instance stairBlock1 of Block
	(properties
		top 83
		left 89
		bottom 85
		right 91
	)
)

(instance stairBlock2 of Block
	(properties
		top 83
		left 115
		bottom 85
		right 117
	)
)

(instance stairBlock3 of Block
	(properties
		top 93
		left 103
		bottom 95
		right 105
	)
)

(instance stairBlock4 of Block
	(properties
		top 93
		left 75
		bottom 95
		right 77
	)
)

(instance moveOnTheStairs of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(1
				(ego
					ignoreBlocks: stairBlock1 stairBlock2 stairBlock3 stairBlock4
				)
				(= inCutscene TRUE)
				(= isHandsOff FALSE)
				(if (> (ego y?) 150)
					(ego illegalBits: 0)
					(ego setMotion: MoveTo 199 133 self)
				else
					(ego illegalBits: 0)
					(ego setMotion: MoveTo 261 172 self)
				)
			)
			(2
				(if (& (ego onControl:) $0040)
					(ego setPri: -1 illegalBits: cWHITE baseSetter: 0)
					(HandsOn)
					(curRoom newRoom: 47)
				else
					(ego
						observeBlocks: stairBlock1 stairBlock2 stairBlock3 stairBlock4
					)
					(ego illegalBits: cWHITE setScript: 0)
					(HandsOn)
					(= currentStatus egoNormal)
				)
			)
			(10
				(= inCutscene TRUE)
				(= isHandsOff FALSE)
				(ego
					ignoreBlocks: stairBlock1 stairBlock2 stairBlock3 stairBlock4
				)
				(if (< (ego y?) 50)
					(ego illegalBits: 0)
					(ego setMotion: MoveTo 69 105 self)
				else
					(ego illegalBits: 0)
					(ego setMotion: MoveTo 205 7 self)
				)
			)
			(11
				(ego
					observeBlocks: stairBlock1 stairBlock2 stairBlock3 stairBlock4
				)
				(if (& (ego onControl:) $0020)
					(ego setPri: -1 illegalBits: -32768 baseSetter: 0)
					(HandsOn)
					(curRoom newRoom: 45)
				else
					(HandsOn)
					(ego illegalBits: cWHITE setScript: 0)
					(= currentStatus 0)
				)
			)
		)
	)
)
