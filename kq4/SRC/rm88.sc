;;; Sierra Script 1.0 - (do not remove this comment)
(script# 88)
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
	Room88 0
)

(local
	henchman
	local1
)
(instance theMusic of Sound)

(instance fallSound of Sound
	(properties
		number 52
	)
)

(instance Room88 of Room
	(properties
		picture 88
		style (| BLACKOUT IRISOUT)
	)
	
	(method (init)
		(Load VIEW 634)
		(Load VIEW 512)
		(Load SOUND 52)
		(self setRegions: LOLOTTE)
		(super init:)
		((View new:)
			view: 634
			loop: 1
			cel: 0
			posn: 105 79
			setPri: 4
			init:
			addToPic:
		)
		((Prop new:)
			view: 512
			loop: 0
			posn: 107 67
			setPri: 3
			init:
			setCycle: Forward
		)
		(= local1 0)
		(ego baseSetter: (ScriptID 0 1))
		(if (or (== prevRoomNum 93) (== prevRoomNum 0))
			(ego posn: 162 142 view: 4 looper: 0 setStep: 4 2 init:)
		)
		(if (== prevRoomNum 87)
			(ego
				posn: 91 130
				view: 4
				looper: 0
				setStep: 4 2
				illegalBits: cWHITE
				init:
			)
			(if henchChasingEgo
				((= henchman (Actor new:))
					view: 141
					illegalBits: 0
					ignoreActors:
					posn: 75 129
					setStep: 6 4
					setLoop: 0
					setCycle: Walk
					init:
					setScript: henchChase88
				)
			)
		)
		(if (== prevRoomNum 82)
			(= local1 1)
			(ego
				posn: 211 62
				view: 4
				looper: myLooper
				setStep: 4 2
				illegalBits: (| cWHITE cYELLOW)
				init:
			)
		)
	)
	
	(method (doit)
		(super doit:)
		(if
			(and
				(!= (self script?) fallStairs)
				(& (ego onControl: 0) cBROWN)
			)
			(ego baseSetter: 0 looper: 0)
			(curRoom newRoom: 93)
		)
		(if
			(and
				(!= (self script?) fallStairs)
				(& (ego onControl: 0) cMAGENTA)
			)
			(ego baseSetter: 0 looper: 0)
			(curRoom newRoom: 82)
		)
		(if (& (ego onControl: 0) cRED)
			(ego baseSetter: 0 looper: 0)
			(curRoom newRoom: 87)
		)
		(if
			(and
				(not local1)
				(& (ego onControl: 0) cGREEN)
				(!= (self script?) fallStairs)
			)
			(ego baseSetter: 0 looper: 0)
			(self setScript: fallStairs)
		)
		(if
			(and
				local1
				(& (ego onControl: 0) cLRED)
				(!= (self script?) fallStairs)
			)
			(ego baseSetter: 0 looper: 0)
			(self setScript: fallStairs)
		)
		(if (and (not local1) (& (ego onControl: origin) cLGREEN))
			(ego illegalBits: -16384 looper: myLooper)
			(= local1 1)
		)
		(if (and local1 (& (ego onControl: origin) cGREY))
			(ego illegalBits: (| cWHITE cCYAN) looper: 0)
			(= local1 0)
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
								(Said 'look/room,castle,tower')
							)
							(Print 88 0)
						)
						((Said 'look>')
							(cond 
								((or (Said '/dirt') (Said '<down'))
									(Print 88 1)
								)
								((Said '/wall')
									(Print 88 2)
								)
								((or (Said '/sky') (Said '<up'))
									(Print 88 3)
								)
								((Said '/stair')
									(Print 88 4)
								)
								((Said '/stair')
									(Print 88 5)
								)
								((Said '/door')
									(Print 88 6)
								)
							)
						)
					)
				)
			)
		)
	)
)

(instance myLooper of Code
	(method (doit obj)
		(obj
			loop:
				(cond 
					(
						(or
							(>= (obj heading?) 305)
							(<= (obj heading?) 45)
						)
						loopS
					)
					(
						(and
							(>= (obj heading?) 135)
							(<= (obj heading?) 225)
						)
						loopN
					)
					(
						(and
							(> (obj heading?) 45)
							(< (obj heading?) 135)
						)
						loopE
					)
					(else loopW)
				)
		)
	)
)

(instance henchChase88 of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theMusic number: 41 play:)
				(henchman
					view: 141
					setCycle: Walk
					setMotion: Chase ego 15 self
				)
			)
			(1
				(User canControl: FALSE canInput: FALSE)
				(ego moveSpeed: 0 setMotion: 0)
				(theMusic number: 42 play:)
				(= seconds 4)
			)
			(2
				(= inCutscene TRUE)
				(curRoom newRoom: 81)
			)
		)
	)
)

(instance fallStairs of Script
	(method (init who)
		(super init: who)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(User canControl: FALSE canInput: FALSE)
				(= currentStatus egoFallDownStairs)
				(ego
					illegalBits: 0
					ignoreActors: TRUE
					view: 44
					moveSpeed: 0
					setLoop: (if (== (ego loop?) 1) 1 else 0)
					setCycle: EndLoop self
				)
				(fallSound play:)
			)
			(1
				(ego
					setStep: 10 10
					setLoop: (+ 2 (ego loop?))
					setCycle: Forward
					setPri: -1
					setMotion:
						MoveTo
						(if (< (ego y?) 130)
							(- (ego x?) 10)
						else
							(+ (ego x?) 10)
						)
						220
						self
				)
			)
			(2
				(curRoom drawPic: 93 IRISOUT)
				(cast eachElementDo: #hide)
				((View new:)
					view: 634
					loop: 1
					cel: 0
					posn: 101 75
					setPri: 4
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
				(if lolotteAlive
					((View new:)
						view: 145
						ignoreActors: TRUE
						loop: 4
						cel: 0
						posn: 124 128
						init:
						addToPic:
					)
				)
				(ego
					posn: 155 9
					show:
					setStep: 10 30
					setMotion: MoveTo 150 140 self
				)
			)
			(3
				(ego view: 42 loop: (if (== (ego loop?) 2) 0 else 1))
				(RedrawCast)
				(fallSound dispose:)
				(ShakeScreen 5)
				(= seconds 4)
			)
			(4
				(= dead TRUE)
				(client setScript: 0)
			)
		)
	)
)
