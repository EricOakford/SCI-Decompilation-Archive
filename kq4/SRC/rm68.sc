;;; Sierra Script 1.0 - (do not remove this comment)
(script# 68)
(include game.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	Room68 0
)

(local
	pendulum
	egoReflection
	thisControl
	local3
)
(instance Room68 of Room
	(properties
		picture 68
		style (| BLACKOUT IRISOUT)
		south 17
	)
	
	(method (init)
		(Load VIEW 533)
		(Load VIEW 536)
		(super init:)
		(self setRegions: HAUNTED_HOUSE)
		(if (== mansionPhase mansionLORD)
			(Load VIEW 206)
			(Load VIEW 204)
		)
		((View new:)
			view: 536
			loop: 7
			cel: 0
			posn: 77 109
			setPri: 8
			addToPic:
		)
		(= currentStatus egoNormal)
		(if isNightTime
			((Prop new:)
				view: 536
				loop: 4
				posn: 79 98
				setPri: 11
				init:
				setCycle: Forward
			)
		)
		(= isIndoors TRUE)
		((= pendulum (Prop new:))
			isExtra: TRUE
			view: 533
			loop: 0
			posn: 223 131
			init:
			setPri: 11
			cycleSpeed: 4
			setCycle: Forward
		)
		(ego
			view: 4
			setLoop: -1
			setCycle: Walk
			illegalBits: cWHITE
			setStep: 4 2
		)
		(switch prevRoomNum
			(67
				(ego
					posn: 56 165
					loop: 0
					init:
					baseSetter: (ScriptID 0 1)
				)
			)
			(64
				(ego
					posn: 262 167
					loop: 1
					init:
					baseSetter: (ScriptID 0 1)
				)
			)
			(62
				(ego posn: 97 79 loop: 2 init: baseSetter: 0)
			)
			(60
				(ego posn: 198 81 loop: 2 init: baseSetter: 0)
			)
			(17
				(ego
					posn: 153 182
					loop: 3
					init:
					baseSetter: (ScriptID 0 1)
				)
			)
		)
		((= egoReflection (Actor new:))
			illegalBits: 0
			ignoreActors: TRUE
			setScript: mirrorTricks
		)
		(if
			(and
				(< 0 mansionPhase)
				(< mansionPhase mansionFINAL)
				(== ghostRoomNum curRoomNum)
			)
			(NotifyScript HAUNTED_HOUSE -1)
		)
	)
	
	(method (doit)
		(if (& (= thisControl (ego onControl: 0)) cBROWN)
			(ego loop: 2 baseSetter: 0)
			(RedrawCast)
			(curRoom newRoom: 17)
		)
		(if (& thisControl cMAGENTA)
			(ego loop: 1 baseSetter: 0)
			(RedrawCast)
			(curRoom newRoom: 67)
		)
		(if (& thisControl cRED)
			(ego loop: 3 baseSetter: 0)
			(RedrawCast)
			(curRoom newRoom: 60)
		)
		(if (& thisControl cCYAN)
			(ego loop: 3 baseSetter: 0)
			(RedrawCast)
			(curRoom newRoom: 62)
		)
		(if (& thisControl cGREEN)
			(ego loop: 0 baseSetter: 0)
			(RedrawCast)
			(curRoom newRoom: 64)
		)
		(if (and (& thisControl cLGREEN)
				(!= currentStatus egoOnStairs)
			)
			(= currentStatus egoOnStairs)
			(ego setScript: moveOnTheStairs)
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
								(Said 'look[<around][/noword]')
								(Said 'look[<around]/room')
							)
							(Print 68 0)
						)
						(
							(or
								(Said 'is<how<time')
								(Said 'is<how/time')
								(Said 'read/time')
								(Said 'read/clock')
								(Said '[noword]/time')
								(Said 'look/time')
							)
							(Print
								(Format @str 68 1
									(if (mod gameHours 12) (mod gameHours 12) else 12)
									gameMinutes
									(if (and (> gameHours 11) (< gameHours 24))
										{P.M.}
									else
										{A.M.}
									)
								)
								#title {TAMIR TIME}
								#at -1 20
								#font smallFont
							)
						)
						((Said 'look>')
							(cond 
								((Said '<behind/painting')
									(Print 68 2)
								)
								((Said '/painting')
									(Print 68 3)
								)
								((Said '/mirror')
									(if (ego inRect: 161 154 195 189)
										(ego loop: 3)
										(Print 68 4)
									else
										(Print 800 1)
									)
								)
								((Said '/chandelier')
									(Print 68 5)
								)
								((Said '/stair')
									(Print 68 6)
								)
								((Said '<in/clock')
									(Print 68 7)
								)
								((Said '/clock')
									(Print 68 8)
								)
								((Said '/door')
									(Print 68 9)
								)
								((Said '/wall')
									(Print 68 10)
								)
								((or (Said '/dirt') (Said '<down'))
									(Print 68 11)
								)
								((Said '/carpet')
									(Print 68 12)
								)
								((Said '/table')
									(Print 68 13)
								)
							)
						)
						((Said 'wind/clock')
							(Print 68 14)
						)
						((Said 'get/clock')
							(Print 68 15)
						)
						((Said 'open/clock')
							(Print 68 7)
						)
						((Said 'get/carpet')
							(Print 68 16)
						)
						((Said 'get/painting')
							(Print 68 17)
						)
					)
				)
			)
		)
	)
	
	(method (newRoom n)
		(if (== n 17)
			((ScriptID HAUNTED_HOUSE) keep: 0)
			(= noWearCrown 0)
		)
		(super newRoom: n)
	)
)

(instance mirrorTricks of Script
	(method (init who)
		(super init: who)
		(client view: (ego view?) setPri: 0 init: hide:)
	)
	
	(method (doit)
		(if (ego inRect: 161 154 195 191)
			(client
				show:
				loop:
				(switch (ego loop?)
					(loopE loopE)
					(loopW loopW)
					(loopS loopN)
					(loopN loopS)
				)
				cel: (ego cel?)
				posn: (ego x?) (- 160 (/ (- (ego y?) 160) 3))
				setCycle: Walk
			)
		else
			(client hide:)
		)
	)
)

(instance moveOnTheStairs of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setStep: 3 3)
				(if (> (ego y?) 100)
					(ego illegalBits: 0 setMotion: MoveTo 196 81 self)
				else
					(ego illegalBits: 0 setMotion: MoveTo 99 139 self)
				)
			)
			(1
				(NormalEgo)
				(HandsOn)
				(= noWearCrown TRUE)
				(ego setScript: 0 setStep: 4 2)
				(if (< (ego y?) 130)
					(ego baseSetter: 0)
				else
					(ego baseSetter: (ScriptID 0 1))
				)
				(= currentStatus egoNormal)
			)
		)
	)
)
