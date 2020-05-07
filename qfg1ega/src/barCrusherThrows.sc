;;; Sierra Script 1.0 - (do not remove this comment)
(script# 337)
(include game.sh)
(use Main)
(use Time)
(use Reverse)
(use Motion)
(use Actor)
(use System)

(public
	crusherThrows 0
	crusherEscorts 1
)

(procedure (localproc_000e param1 &tmp egoPriority)
	(HandsOff)
	(cast eachElementDo: #stopUpd)
	((ScriptID 331 7) cycleSpeed: 1 setCycle: EndLoop)
	(if (<= (ego distanceTo: (ScriptID 331 7)) 25)
		(if
			(==
				(= egoPriority (ego priority?))
				((ScriptID 331 7) priority?)
			)
			(++ egoPriority)
		)
		(ego
			setPri: egoPriority
			setLoop: 1
			setCycle: Reverse
			cycleSpeed: 1
			moveSpeed: 1
			ignoreActors: 1
			setMotion: MoveTo 125 150 param1
		)
	else
		(param1 cue:)
	)
)

(instance chair of Prop
	(properties
		y 149
		x 75
		view vCrusher
		loop 2
	)
)

(instance crusherThrows of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (localproc_000e self))
			(1
				(ego setCycle: Walk)
				(chair init: ignoreActors: setCycle: EndLoop)
				((ScriptID 331 7)
					ignoreActors:
					loop: 1
					cel: 0
					cycleSpeed: 1
					setCycle: EndLoop self
				)
			)
			(2
				((ScriptID 331 7) cel: 2)
				(= cycles 3)
			)
			(3
				((ScriptID 331 7) setCycle: EndLoop self)
			)
			(4
				(cast eachElementDo: #dispose)
				(curRoom drawPic: 400 15)
				(Bset TAVERN_THROWN_OUT)
				(AdvanceTime 3)
				(curRoom newRoom: 330)
			)
		)
	)
)

(instance crusherEscorts of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (localproc_000e self))
			(1
				(ego setCycle: Walk)
				(chair
					init:
					ignoreActors:
					cycleSpeed: 1
					setCycle: EndLoop self
				)
				((ScriptID 331 7)
					ignoreActors:
					setLoop: 1
					setCel: 0
					setCycle: 0
					posn: 98 152
					stopUpd:
				)
			)
			(2
				((ScriptID 331 6) cycleSpeed: 2 setCycle: EndLoop self)
			)
			(3
				(cast eachElementDo: #dispose)
				(curRoom drawPic: 400 15)
				(curRoom newRoom: 332)
			)
		)
	)
)
