;;; Sierra Script 1.0 - (do not remove this comment)
(script# 66)
(include game.sh)
(use Main)
(use Intrface)
(use Sound)
(use Jump)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	Room66 0
)

(local
	shovel
	onStairs
	fatalFall
	local3
)
(instance fallSound of Sound
	(properties
		number 51
	)
)

(instance Room66 of Room
	(properties
		picture 66
		style (| BLACKOUT IRISOUT)
	)
	
	(method (init)
		(Load VIEW 512)
		(Load VIEW 44)
		(Load VIEW 42)
		(Load VIEW 41)
		(Load VIEW 4)
		(Load SCRIPT JUMP)
		(self setRegions: HAUNTED_HOUSE)
		(super init:)
		((View new:)
			view: 512
			loop: 1
			cel: 0
			posn: 208 79
			setPri: 4
			addToPic:
		)
		((Prop new:)
			view: 512
			loop: 0
			posn: 206 64
			setPri: 3
			init:
			setCycle: Forward
		)
		(if (== ((inventory at: iShovel) owner?) 66)
			((= shovel (View new:))
				view: 512
				loop: 2
				cel: 0
				posn: 194 122
				init:
				stopUpd:
			)
		)
		(if (or (== prevRoomNum 67) (== prevRoomNum 0))
			(ego
				illegalBits: (| cWHITE cYELLOW)
				posn: 240 127
				view: 4
				xStep: 4
				yStep: 2
				init:
				setPri: -1
			)
			(= onStairs TRUE)
			(ego observeBlocks: stair1)
		)
		(if (== prevRoomNum 61)
			(ego
				illegalBits: cWHITE
				view: 4
				xStep: 4
				setPri: 7
				yStep: 1
				posn: 102 37
				baseSetter: (ScriptID 0 1)
				init:
			)
			(= onStairs 0)
		)
		(ego observeBlocks: stair2)
	)
	
	(method (doit)
		(super doit:)
		(if (!= (self script?) stairTrip)
			(if (& (ego onControl: 0) cBROWN)
				(curRoom newRoom: 67)
			)
			(if (& (ego onControl: 0) cMAGENTA)
				(curRoom newRoom: 61)
			)
			(if (and (& (ego onControl: 0) cGREEN) (not onStairs))
				(self setScript: stairTrip)
			)
			(if (& (ego onControl: origin) cLCYAN)
				(if (> (ego heading?) 180)
					(= onStairs 0)
					(if (ego blocks?)
						(ego ignoreBlocks: stair1)
					)
					(ego
						illegalBits: cWHITE
						baseSetter: (ScriptID 0 1)
						setPri: 12
					)
				else
					(= onStairs TRUE)
					(ego
						observeBlocks: stair1
						illegalBits: -16384
						baseSetter: 0
						setPri: -1
					)
				)
			)
		)
		(cond 
			(
				(and
					(ego inRect: 183 150 205 168)
					(& (ego onControl:) cLCYAN)
					onStairs
				)
				(ego setPri: 12)
			)
			(
				(and
					onStairs
					(not (ego inRect: 183 150 205 168))
					(== (ego priority?) 12)
				)
				(ego setPri: -1)
			)
		)
		(if
			(and
				(!= (self script?) stairTrip)
				(or
					(ego inRect: 92 14 128 36)
					(ego inRect: 74 13 112 48)
				)
			)
			(ego setPri: 7)
		)
		(if (& (ego onControl: origin) cLGREEN)
			(if
			(or (< (ego heading?) 90) (> (ego heading?) 270))
				(ego setPri: 7)
			else
				(ego setPri: 12)
			)
		)
		(if
			(and
				(> (ego y?) 60)
				(< (ego y?) 116)
				(!= (ego script?) stairTrip)
			)
			(ego setPri: 12)
		)
	)
	
	(method (dispose)
		(DisposeScript JUMP)
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
								((Said '/stair')
									(Print 66 0)
								)
								((Said '/door')
									(Print 66 1)
								)
								((or (Said '/dirt') (Said '<down'))
									(Print 66 2)
								)
								((Said '/wall')
									(Print 66 3)
								)
								((Said '/torch')
									(Print 66 4)
								)
								((Said '<up')
									(Print 66 5)
								)
								((Said '[<around][/room,tower]')
									(Print
										(Format @str 66 6
											(if (== ((inventory at: iShovel) owner?) 66)
												{You notice a shovel leaning against the wall.}
											else
												{_}
											)
										)
									)
								)
								(else
									(event claimed: FALSE)
								)
							)
						)
						((Said 'anyword/stair')
							(Print 66 7)
						)
						((Said 'get/shovel')
							(if
								(and
									(== ((inventory at: iShovel) owner?) 66)
									(cast contains: shovel)
								)
								(if (< (ego distanceTo: shovel) 15)
									(ego get: iShovel)
									(= gotItem TRUE)
									(theGame changeScore: 2)
									(shovel dispose:)
								else
									(Print 800 1)
								)
							else
								(Print 66 8)
							)
						)
						((Said 'get/torch')
							(Print 66 9)
						)
						((Said 'close/door')
							(Print 66 10)
						)
						((Said 'open/door')
							(Print 66 11)
						)
					)
				)
			)
		)
	)
)

(instance stairTrip of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego illegalBits: 0 ignoreActors: TRUE)
				(if (< (ego y?) 90)
					(= fatalFall TRUE)
				)
				(self cue:)
			)
			(1
				(ego
					view: 44
					moveSpeed: 0
					setMotion: 0
					setLoop: 0
					setCycle: EndLoop self
				)
				(fallSound play:)
			)
			(2
				(ego setPri: 11)
				(RedrawCast)
				(ego
					setStep: 10 10
					setLoop: 2
					setCycle: Forward
					setMotion: JumpTo 185 142 self
				)
			)
			(3
				(if fatalFall
					(ego view: 42 loop: 0 cel: 0 setPri: 8)
					((ScriptID 0 4) setCycle: self 2)
				else
					(= onStairs TRUE)
					(ego observeBlocks: stair1)
					(ego
						view: 41
						setPri: -1
						cel: 255
						setLoop: 0
						setCycle: EndLoop self
					)
				)
			)
			(4
				(if fatalFall
					(ShakeScreen 10)
					(= seconds 3)
				else
					(ego
						view: 4
						xStep: 4
						yStep: 2
						setCycle: Walk
						setLoop: -1
						setPri: -1
						illegalBits: (| cWHITE cYELLOW)
						baseSetter: 0
						ignoreActors: 0
					)
					(= state -1)
					(HandsOn)
					(client setScript: 0)
				)
			)
			(5
				(= dead TRUE)
			)
		)
	)
)

(instance stair1 of Block
	(properties
		top 116
		left 147
		bottom 117
		right 159
	)
)

(instance stair2 of Block
	(properties
		top 194
		left 198
		bottom 149
		right 151
	)
)
