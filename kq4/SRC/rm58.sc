;;; Sierra Script 1.0 - (do not remove this comment)
(script# 58)
(include game.sh)
(use Main)
(use Intrface)
(use Sound)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	Room58 0
)

(local
	sittingEgo
	drawerOpen
	drawer
	keyInDrawer
	sheetMusic
	candleLight
	candleLight2
)
(instance Room58 of Room
	(properties
		picture 58
		style (| BLACKOUT IRISOUT)
	)
	
	(method (init)
		(Load VIEW 58)
		(Load VIEW 42)
		(Load VIEW 44)
		(Load VIEW 521)
		(Load SOUND 51)
		(Load SOUND 52)
		(Load PICTURE 66)
		(Load PICTURE 61)
		(super init:)
		(self setRegions: HAUNTED_HOUSE)
		(theMusic number: 57 loop: 0)
		(if ((inventory at: iSkeletonKey) ownedBy: curRoomNum)
			(= keyInDrawer TRUE)
		)
		(if playedOrgan (= drawerOpen TRUE) else (= drawerOpen FALSE))
		((View new:)
			view: 521
			loop: 2
			cel: 0
			posn: 102 74
			setPri: 4
			addToPic:
		)
		((View new:)
			view: 521
			loop: 2
			cel: 1
			posn: 231 74
			setPri: 4
			addToPic:
		)
		((= candleLight (Prop new:))
			view: 521
			loop: 3
			posn: 104 58
			setPri: 2
			init:
			setCycle: Forward
		)
		((= candleLight2 (Prop new:))
			view: 521
			loop: 3
			posn: 228 58
			setPri: 2
			init:
			setCycle: Forward
		)
		((= drawer (Prop new:))
			view: 521
			loop: 1
			posn: 181 95
			setPri: 5
			init:
		)
		(if playedOrgan
			(drawer setCel: 255 addToPic:)
		else
			(drawer hide:)
		)
		(ego
			view: 4
			setStep: 4 2
			setPri: 11
			illegalBits: 16384
			posn: 121 154
			baseSetter: (ScriptID 0 1)
			init:
		)
	)
	
	(method (doit)
		(if
			(and
				(& (ego onControl:) $0002)
				(== (ego illegalBits?) cWHITE)
				(not (ego script?))
			)
			(ego
				setPri: 11
				illegalBits: 16384
				baseSetter: (ScriptID 0 1)
			)
		)
		(if
			(and
				(& (ego onControl:) $0010)
				(not (& (ego onControl: TRUE) $0002))
				(not (ego script?))
			)
			(ego setPri: -1 illegalBits: cWHITE baseSetter: 0)
		)
		(if
			(and
				(& (ego onControl: FALSE) $0040)
				(!= (ego script?) fallHole)
			)
			(curRoom newRoom: 61)
		)
		(if
			(and
				(& (ego onControl:) $0004)
				(!= (ego illegalBits?) 16384)
				(!= (ego script?) fallHole)
			)
			(HandsOff)
			(ego setScript: fallHole)
		)
		(super doit:)
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
								(Said 'look/room,tower')
							)
							(Print 58 0)
						)
						((Said 'look>')
							(cond 
								((or (Said '<down') (Said '/stair')) (Print 58 1))
								((Said '<behind/organ') (Print 58 2))
								((Said '<in/organ') (Print 58 3))
								((Said '/organ') (Print 58 4))
								((Said '/bench') (Print 58 5))
								((Said '/wall') (Print 58 6))
								((or (Said '/dirt') (Said '<down')) (Print 58 7))
								((Said '/drawer')
									(if drawerOpen
										(if keyInDrawer (Print 58 8) else (Print 58 9))
									else
										(Print 58 10)
									)
								)
							)
						)
						((Said 'move/bench') (Print 58 11))
						((Said 'play,use/music<sheet')
							(cond 
								((== (ego view?) 58)
									(if (ego has: iSheetMusic)
										(Print 58 12)
										(playOrgan changeState: 10)
									else
										(Print 58 13)
									)
								)
								((ego has: iSheetMusic) (Print 58 14))
								(else (Print 58 13))
							)
						)
						(
						(or (Said 'play,use/organ,music') (Said 'play[/!*]'))
							(if (== (ego view?) 58)
								(if (ego has: 26) (Print 58 15))
								(playOrgan changeState: 0)
							else
								(Print 58 16)
							)
						)
						((or (Said 'sit') (Said 'sit/organ,bench'))
							(cond 
								((== (ego view?) 58) (Print 58 17))
								((ego inRect: 129 121 195 126) (self setScript: sitOrgan))
								((ego inRect: 122 126 211 137) (Print 58 18))
								(else (Print 800 1))
							)
						)
						(
							(or
								(Said 'get<up')
								(Said 'stand[<up]')
								(Said '(/organ,bench')
							)
							(if (== (ego view?) 58)
								(self setScript: standOrgan)
							else
								(Print 58 19)
							)
						)
						((Said 'get/key')
							(if drawerOpen
								(if keyInDrawer
									(Print 58 20)
									(= keyInDrawer FALSE)
									(= gotItem TRUE)
									(ego get: iSkeletonKey)
									(theGame changeScore: 2)
								else
									(AlreadyTook)
								)
							else
								(Print 58 21)
							)
						)
						((Said 'close/drawer') (if drawerOpen (Print 58 22) else (Print 58 23)))
						((Said 'open/drawer') (if drawerOpen (Print 58 24) else (Print 58 23)))
						((Said 'open/bench') (Print 58 25))
						((Said 'find/drawer') (if drawerOpen (Print 58 26) else (Print 58 27)))
					)
				)
			)
		)
	)
)

(instance playOrgan of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(User canInput: FALSE)
				(sittingEgo loop: 2 setCycle: Forward)
				(theMusic number: 56 loop: 1 play: self)
			)
			(1
				(Print 58 28)
				(sittingEgo cel: 0 loop: 4 setCycle: 0)
				(User canInput: TRUE)
				(theMusic loop: 0)
			)
			(10
				(HandsOff)
				((= sheetMusic (View new:))
					view: 521
					loop: 0
					cel: 0
					posn: 162 94
					setPri: 6
					init:
				)
				(sittingEgo loop: 2 setCycle: Forward)
				(theMusic number: 57 loop: 1 play: self)
			)
			(11
				(if (== playedOrgan FALSE)
					(++ playedOrgan)
					(Print 58 29)
					(theGame changeScore: 4)
					(drawer show: setCycle: EndLoop)
					(= drawerOpen TRUE)
				else
					(theMusic loop: 0)
					(sheetMusic dispose:)
				)
				(sittingEgo cel: 0 loop: 4 setCycle: 0)
				(sheetMusic dispose:)
				(User canInput: TRUE)
			)
		)
	)
)

(instance theMusic of Sound
	(properties)
)

(instance sitOrgan of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(User canControl: FALSE)
				(ego setMotion: MoveTo 164 125 self)
			)
			(1 (ego loop: 2) (self cue:))
			(2
				((= sittingEgo (Actor new:))
					view: 58
					loop: 0
					cel: 0
					setPri: 9
					posn: (ego x?) (- (ego y?) 13)
					ignoreActors: TRUE
					illegalBits: 0
					init:
				)
				(ego view: 58 loop: 1 cel: 0)
				(ego setCycle: EndLoop)
				(sittingEgo setCycle: EndLoop self)
			)
			(3 (client setScript: 0))
		)
	)
)

(instance standOrgan of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego cel: 255 loop: 5 setCycle: EndLoop)
				(sittingEgo cel: 255 loop: 4 setCycle: EndLoop self)
			)
			(1
				(ego setPri: -1 view: 4 loop: 3 setCycle: Walk)
				(sittingEgo dispose:)
				(HandsOn)
				(client setScript: 0)
			)
		)
	)
)

(instance fallHole of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theMusic number: 51 loop: 1 play: self)
				(ego setMotion: 0)
				(HandsOff)
				(ego
					view: 44
					setStep: -1 10
					setCycle: Forward
					setPri: (if (< (ego priority?) 11) (ego priority?) else 11)
					illegalBits: 0
					setMotion: MoveTo 160 252
				)
			)
			(1
				(cast eachElementDo: #hide)
				(theMusic client: 0 stop:)
				(ego hide:)
				(curRoom drawPic: 61 6)
				(theMusic number: 52 loop: 1 play:)
				(ego
					setStep: -1 10
					setCycle: Forward
					illegalBits: 0
					posn: 160 9
					setPri: 14
					show:
					setLoop: (ego loop?)
					setMotion: MoveTo 160 150 self
				)
			)
			(2
				(ego setPri: 14 setMotion: MoveTo 160 256 self)
			)
			(3
				(theMusic client: 0 stop:)
				(ego hide:)
				(curRoom drawPic: 66 IRISIN)
				(theMusic number: 52 loop: 1 play:)
				(ego
					posn: 160 9
					show:
					setPri: -1
					setMotion: MoveTo 212 136 self
				)
			)
			(4
				(sounds eachElementDo: #stop 1)
				(ego view: 42)
				(RedrawCast)
				(ShakeScreen 10)
				(= seconds 3)
			)
			(5 (= dead TRUE))
		)
	)
)
