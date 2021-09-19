;;; Sierra Script 1.0 - (do not remove this comment)
(script# 85)
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
	Room85 0
)

(instance fallMusic of Sound
	(properties
		number 52
	)
)

(instance Room85 of Room
	(properties
		picture 85
		style (| BLACKOUT IRISOUT)
	)
	
	(method (init)
		(Load SOUND 52)
		(self setRegions: LOLOTTE)
		(super init:)
		((View new:)
			view: 634
			loop: 1
			cel: 1
			posn: 223 163
			setPri: 12
			addToPic:
		)
		((Prop new:)
			view: 512
			loop: 0
			cel: 4
			posn: 223 151
			setPri: 11
			init:
			setCycle: Forward
		)
		(if (or (== prevRoomNum 90) (== prevRoomNum 0))
			(ego
				posn: 117 169
				view: 4
				baseSetter: (ScriptID 0 1)
				setStep: 4 2
				init:
			)
		)
		(if (== prevRoomNum 81)
			(ego
				posn: 131 35
				view: 4
				baseSetter: (ScriptID 0 1)
				looper: myLooper
				setLoop: -1
				setStep: 4 2
				init:
			)
		)
	)
	
	(method (doit)
		(super doit:)
		(if (!= (self script?) fallStairs)
			(if (& (ego onControl: 0) cBROWN)
				(ego baseSetter: 0 looper: 0)
				(curRoom newRoom: 90)
			)
			(if (& (ego onControl: 0) cMAGENTA)
				(ego baseSetter: 0 looper: 0)
				(curRoom newRoom: 81)
			)
			(if (& (ego onControl:) cGREEN)
				(self setScript: fallStairs)
			)
			(cond 
				((& (ego onControl: 0) cBLUE)
					(ego looper: myLooper)
				)
				((> (ego y?) 110)
					(ego looper: 0)
				)
			)
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
								(Said 'look/room,tower,castle')
							)
							(Print 85 0)
						)
						((Said 'look>')
							(cond 
								((or (Said '/dirt') (Said '<down'))
									(Print 85 1)
								)
								((or (Said '/sky') (Said '<up'))
									(Print 85 2)
								)
								((Said '/stair')
									(Print 85 3)
								)
								((Said '/stair')
									(Print 85 4)
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

(instance fallStairs of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(User canControl: FALSE canInput: FALSE)
				(= currentStatus 16)
				(ego
					view: 44
					illegalBits: 0
					ignoreActors: TRUE
					moveSpeed: 0
					setLoop: (if (== (ego loop?) 1) 1 else 0)
					setCycle: EndLoop self
				)
				(fallMusic play:)
			)
			(1
				(ego
					setStep: 10 10
					setLoop: (+ 2 (ego loop?))
					setCycle: Forward
					setPri: -1
					setMotion: MoveTo
						(if (< (ego y?) 130)
							(- (ego x?) 20)
						else
							(+ (ego x?) 10)
						)
						220
						self
				)
			)
			(2
				(cast eachElementDo: #hide)
				(DrawPic 90)
				((View new:)
					view: 634
					loop: 1
					cel: 1
					posn: 209 77
					setPri: 4
					addToPic:
				)
				((Prop new:)
					view: 512
					loop: 0
					posn: 209 65
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
						posn: 188 127
						init:
						addToPic:
					)
				)
				(ego
					show:
					posn: 190 9
					setStep: 10 30
					setMotion: MoveTo 190 147 self
				)
			)
			(3
				(ego view: 42 loop: (if (== (ego loop?) 2) 0 else 1))
				(RedrawCast)
				(fallMusic dispose:)
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
