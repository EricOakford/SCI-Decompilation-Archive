;;; Sierra Script 1.0 - (do not remove this comment)
(script# 93)
(include game.sh)
(use Main)
(use Intrface)
(use Sound)
(use Jump)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	Room93 0
)

(local
	slippedOnStairs
	henchman
)
(instance theMusic of Sound)

(instance fallMusic of Sound
	(properties
		number 51
	)
)

(instance Room93 of Room
	(properties
		picture 93
		style (| BLACKOUT IRISOUT)
	)
	
	(method (init)
		(Load SCRIPT JUMP)
		(Load VIEW 634)
		(Load VIEW 512)
		(Load SOUND 51)
		(self setRegions: LOLOTTE)
		(super init:)
		(NotifyScript LOLOTTE 0)
		((View new:)
			view: 634
			loop: 1
			cel: 0
			posn: 101 75
			setPri: 4
			init:
			addToPic:
		)
		((Prop new:)
			view: 512
			loop: 0
			posn: 103 63
			setPri: 3
			init:
			setCycle: Forward
		)
		(ego baseSetter: (ScriptID 0 1))
		(if (or (== prevRoomNum 92) (== prevRoomNum 0))
			(ego
				posn: 73 137
				view: 4
				setStep: 4 2
				illegalBits: (| cWHITE cYELLOW)
				baseSetter: 0
				init:
				observeBlocks: stair1 stair2
			)
			(= slippedOnStairs 0)
			(if henchChasingEgo
				(if (> (Random 0 100) 90)
					(Print 93 0 #at -1 10 #font smallFont)
					(= henchChasingEgo FALSE)
				else
					((= henchman (Actor new:))
						view: 141
						illegalBits: 0
						ignoreActors:
						posn: 45 134
						setStep: 6 4
						setCycle: Walk
						setScript: henchChase93
						init:
					)
				)
			)
		)
		(if (== prevRoomNum 88)
			(ego
				posn: 195 25
				view: 4
				setStep: 4 2
				setPri: 12
				baseSetter: (ScriptID 0 1)
				illegalBits: cWHITE
				init:
				observeBlocks: stair1 stair2
			)
			(= slippedOnStairs TRUE)
		)
	)
	
	(method (doit)
		(if (& (ego onControl: 0) cBROWN)
			(ego illegalBits: cWHITE)
			(curRoom newRoom: 92)
		)
		(if (& (ego onControl: 0) cMAGENTA)
			(ego setPri: -1 illegalBits: cWHITE)
			(curRoom newRoom: 88)
		)
		(if
			(and
				slippedOnStairs
				(!= (ego script?) falling)
				(& (ego onControl: 0) cGREEN)
			)
			(ego setScript: falling)
		)
		(if
			(and
				(& (ego onControl: origin) cBLUE)
				(!= (ego script?) falling)
			)
			(if (and (< (ego heading?) 180) (> (ego heading?) 0))
				(ego
					setPri: 12
					illegalBits: cWHITE
					baseSetter: (ScriptID 0 1)
				)
				(= slippedOnStairs TRUE)
			else
				(ego
					setPri: -1
					illegalBits: (| cWHITE cYELLOW)
					baseSetter: 0
				)
				(= slippedOnStairs FALSE)
			)
		)
		(super doit:)
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
						(
							(or
								(Said 'look[<around][/noword]')
								(Said 'look/room,castle,tower')
							)
							(Print 93 1)
						)
						((Said 'look>')
							(cond 
								((Said '/stair')
									(Print 93 2)
								)
								((or (Said '/dirt') (Said '<down'))
									(Print 93 3)
								)
								((or (Said '/sky') (Said '<up'))
									(Print 93 4)
								)
								((Said '/stair')
									(Print 93 5)
								)
								((Said '/door')
									(Print 93 6)
								)
								((Said '/door')
									(Print 93 7)
								)
							)
						)
					)
				)
			)
		)
	)
)

(instance falling of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(User canControl: FALSE canInput: FALSE)
				(if (> (ego y?) 110)
					(self changeState: 10)
				else
					(self changeState: 20)
				)
			)
			(10
				(ego
					view: 44
					baseSetter: 0
					setStep: 8 3
					setLoop: 1
					illegalBits: 0
					setPri: 9
					setCycle: EndLoop
				)
				(if (<= (ego y?) 135)
					(ego setMotion: JumpTo 137 145 self)
				else
					(ego setMotion: MoveTo 137 145 self)
				)
				(fallMusic play:)
			)
			(11
				(ego view: 41 cel: 255 setPri: -1 setCycle: EndLoop self)
			)
			(12
				(ego
					view: 4
					setStep: 4 2
					setLoop: -1
					illegalBits: (| cWHITE cYELLOW)
					setPri: -1
					setCycle: Walk
				)
				(= slippedOnStairs FALSE)
				(fallMusic dispose:)
				(User canInput: TRUE canControl: TRUE)
				(client setScript: 0)
			)
			(20
				(User canInput: FALSE canControl: FALSE)
				(= currentStatus egoFallDownStairs)
				(ego
					view: 44
					illegalBits: 0
					baseSetter: 0
					setPri: 9
					setMotion:
						((Jump new:)
							y: (if (<= (ego y?) 145) 145 else (ego y?))
							gy: 4
							gx: (if (< (ego y?) 60) -1 else -2)
						)
						self
				)
				(fallMusic play:)
			)
			(21
				(ego view: 42 setPri: 3)
				(RedrawCast)
				(ShakeScreen 5)
				(= seconds 4)
			)
			(22
				(= dead TRUE)
				(fallMusic dispose:)
				(client setScript: 0)
			)
		)
	)
)

(instance henchChase93 of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theMusic number: 41 loop: 1 play:)
				(henchman setMotion: Chase ego 15 self)
			)
			(1
				(User canControl: 0 canInput: 0)
				(ego moveSpeed: 0 setMotion: 0)
				(theMusic number: 42 loop: 1 play:)
				(= seconds 4)
			)
			(2
				(= inCutscene TRUE)
				(curRoom newRoom: 81)
			)
		)
	)
)

(instance stair1 of Block
	(properties
		top 127
		left 192
		bottom 129
		right 194
	)
)

(instance stair2 of Block
	(properties
		top 144
		left 167
		bottom 146
		right 169
	)
)
