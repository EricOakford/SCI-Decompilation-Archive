;;; Sierra Script 1.0 - (do not remove this comment)
(script# 368)
(include game.sh)
(use Main)
(use volleyRm)
(use Motion)
(use User)
(use Actor)
(use System)

(public
	cprEndScript 0
)

(instance Doctor of Actor
	(properties
		y 124
		x 330
		view 303
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/doc,man]>')
				(cond 
					((Said 'look[<at]')
						(HighPrint 368 0)
					)
					((Said 'address')
						(HighPrint 368 1)
					)
					((Said 'thank')
						(HighPrint 368 2)
					)
				)
			)
		)
	)
)

(instance cprEndScript of Script
	
	(method (dispose)
		(super dispose:)
		(DisposeScript 368)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HighPrint 368 3)
				(HighPrint 368 4)
				(HighPrint 368 5)
				(HighPrint 368 6)
				(ego setLoop: 1 cycleSpeed: 3 setCycle: Forward)
				(= seconds 10)
			)
			(1
				(ego setLoop: 0 setCel: 0 setCycle: CycleTo 3 1)
				(= seconds 2)
			)
			(2
				(HighPrint 368 7)
				(ego setCel: 3 setCycle: BegLoop self)
			)
			(3
				(ego setLoop: 1 cycleSpeed: 3 setCycle: Forward)
				(= seconds 10)
			)
			(4
				(ego cycleSpeed: 0 setCycle: BegLoop self)
			)
			(5
				(HighPrint 368 8)
				(= seconds 3)
			)
			(6
				(Doctor
					init:
					illegalBits: 0
					setCycle: Walk
					setMotion: MoveTo 248 124 self
				)
			)
			(7
				(Doctor setLoop: 4 setCel: 0 setCycle: EndLoop self)
			)
			(8
				(Doctor setLoop: 6 setCel: 0 setCycle: EndLoop self)
			)
			(9
				(HighPrint 368 9)
				(Doctor setLoop: 7 cycleSpeed: 3 setCycle: Forward)
				(= seconds 5)
			)
			(10
				(Doctor
					setLoop: 6
					setCel: 5
					cycleSpeed: 0
					setCycle: BegLoop self
				)
			)
			(11
				(HighPrint 368 10)
				(HighPrint 368 11)
				(= cycles 2)
			)
			(12
				(HighPrint 368 12)
				(HighPrint 368 13)
				(ego setLoop: 2 setCycle: EndLoop self)
			)
			(13
				(curRoom notify: 3 1)
				(Doctor setLoop: 5 setCel: 5 setCycle: BegLoop self)
			)
			(14
				(Doctor
					setLoop: -1
					setCycle: Walk
					setMotion: MoveTo 350 (Doctor y?) self
				)
			)
			(15
				(User canInput: TRUE)
				(Doctor dispose:)
				(ego
					view: 200
					setLoop: -1
					posn: 240 121
					setCycle: Walk
					heading: 180
				)
				(ego cel: 2)
				(DirLoop ego (ego heading?))
				((ScriptID 3 2)
					init:
					setCycle: Walk
					view: 203
					setLoop: -1
					posn: 246 122
					heading: 270
				)
				(DirLoop (ScriptID 3 2) ((ScriptID 3 2) heading?))
				(HighPrint 368 14)
				(HighPrint 368 15)
				(= seconds 4)
			)
			(16
				((ScriptID 3 2)
					illegalBits: 0
					ignoreActors:
					setMotion: MoveTo 360 ((ScriptID 3 2) y?)
				)
				((ScriptID 3 6)
					illegalBits: 0
					ignoreActors:
					setLoop: -1
					setCycle: Walk
					setMotion: MoveTo 360 ((ScriptID 3 2) y?) self
				)
				(curRoom notify: 3 0)
			)
			(17
				((ScriptID 3 2) dispose:)
				((ScriptID 3 6) dispose:)
				(HandsOn)
				(curRoom notify: 0 0)
				(self dispose:)
			)
		)
	)
)
