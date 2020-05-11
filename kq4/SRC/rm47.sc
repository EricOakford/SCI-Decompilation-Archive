;;; Sierra Script 1.0 - (do not remove this comment)
(script# 47)
(include game.sh)
(use Main)
(use Intrface)
(use Sound)
(use Motion)
(use Game)
(use Actor)

(public
	Room47 0
)
(synonyms
	(room room castle)
)

(local
	candleLight1
	candleLight2
	fairy1
	fairy2
)
(instance theMusic of Sound
	(properties
		number 33
	)
)

(instance Room47 of Room
	(properties
		picture 47
		style (| BLACKOUT IRISOUT)
	)
	
	(method (init)
		(Load VIEW 632)
		(Load VIEW 111)
		(Load VIEW 113)
		(super init:)
		(HandsOn)
		(= inCutscene FALSE)
		(if (or (== prevRoomNum 37) (== prevRoomNum 0))
			(ego posn: 156 176 view: 4 xStep: 4 yStep: 2 init:)
		)
		(if (== prevRoomNum 46)
			(ego
				posn: 72 141
				view: 4
				illegalBits: cWHITE
				xStep: 4
				yStep: 2
				init:
			)
		)
		((View new:)
			view: 632
			loop: 0
			cel: 3
			posn: 38 105
			setPri: 14
			addToPic:
		)
		((View new:)
			view: 632
			loop: 0
			cel: 4
			posn: 281 106
			setPri: 14
			addToPic:
		)
		((= candleLight1 (Actor new:))
			view: 632
			loop: 2
			cel: 2
			illegalBits: 0
			setPri: 14
			posn: 41 97
			setCycle: Forward
			init:
		)
		((= candleLight2 (Actor new:))
			view: 632
			loop: 2
			cel: 2
			illegalBits: 0
			setPri: 14
			setCycle: Forward
			posn: 278 98
			init:
		)
		((= fairy1 (Actor new:))
			view: 111
			posn: 250 79
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
			posn: 65 74
			illegalBits: 8192
			setPri: 14
			xStep: 1
			yStep: 3
			setMotion: Wander 5
			setCycle: Forward
			init:
		)
		(theMusic play:)
	)
	
	(method (doit)
		(super doit:)
		(if (& (ego onControl: FALSE) $0040) (curRoom newRoom: 37))
		(if (& (ego onControl: FALSE) $0020) (curRoom newRoom: 46))
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return TRUE))
		(return
			(if (== (event type?) saidEvent)
				(cond 
					(
						(or
							(Said 'look[<around][/!*]')
							(Said 'look[<around]/room')
						)
						(Print 47 0)
					)
					((Said 'look>')
						(cond 
							(
								(or
									(Said '<around')
									(Said '[<around]/room,room,hall,entry')
								)
								(Print 47 0)
							)
							((Said '/door') (Print 47 1))
							((Said '/flora,blossom') (Print 47 2))
							((Said '/wall') (Print 47 3))
							((Said '[<down]/dirt') (Print 47 4))
							((Said '/fairies[<little]') (Print 47 5))
						)
					)
					((Said 'open/door')
						(if
							(or
								(ego inRect: 146 127 176 131)
								(ego inRect: 238 141 260 153)
							)
							(Print 47 6)
						else
							(Print 800 1)
						)
					)
					((Said 'unlatch/door')
						(if
							(or
								(ego inRect: 146 127 176 131)
								(ego inRect: 238 141 260 153)
							)
							(if (or (ego has: iGoldKey) (ego has: iSkeletonKey))
								(Print 47 7)
							else
								(Print 47 8)
							)
						else
							(Print 800 1)
						)
					)
					((Said 'bang/door')
						(if
							(or
								(ego inRect: 146 127 176 131)
								(ego inRect: 238 141 260 153)
							)
							(Print 47 9)
						else
							(Print 800 1)
						)
					)
					((Said 'break/door')
						(if
							(or
								(ego inRect: 146 127 176 131)
								(ego inRect: 238 141 260 153)
							)
							(Print 47 10)
						else
							(Print 800 1)
						)
					)
					((Said 'use/key')
						(if
							(or
								(ego inRect: 146 127 176 131)
								(ego inRect: 238 141 260 153)
							)
							(if (or (ego has: iSkeletonKey) (ego has: iGoldKey))
								(Print 47 11)
							else
								(Print 47 12)
							)
						else
							(Print 47 13)
						)
					)
					(
						(or
							(Said 'converse/fairies[<little]')
							(Said 'converse[/!*]')
						)
						(Print 47 14)
					)
					((Said 'kill/fairies[<little]') (Print 47 15))
					((Said 'get/fairies[<little]') (Print 47 16))
					((Said 'capture/fairies[<little]') (Print 47 16))
					(
					(or (Said 'kiss/fairies[<little]') (Said 'kiss[/!*]')) (Print 47 17))
					((Said 'help/fairies[<little]') (Print 47 18))
					((Said 'deliver/*/fairies') (Print 47 19))
				)
			else
				FALSE
			)
		)
	)
)
