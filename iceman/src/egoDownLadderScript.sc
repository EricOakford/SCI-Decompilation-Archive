;;; Sierra Script 1.0 - (do not remove this comment)
(script# 391)
(include game.sh)
(use Main)
(use Intrface)
(use subMarine)
(use Reverse)
(use Motion)
(use System)

(public
	egoDownLadderScript 0
	captainfallsScript 1
)

(instance egoDownLadderScript of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					illegalBits: 0
					setScript: 0
					view: 238
					posn: 298 42
					setLoop: 0
					heading: 0
					setCycle: Reverse
					setMotion: MoveTo 298 91 self
				)
			)
			(1
				(ego
					observeControl: cWHITE
					view: 232
					setLoop: -1
					setCycle: Walk
					setMotion: MoveTo 253 102 self
				)
			)
			(2
				(HandsOn)
				(ego setScript: (ScriptID 25 3))
			)
		)
	)
)

(instance captainfallsScript of Script
	
	(method (dispose)
		(super dispose:)
		(DisposeScript 391)
	)
	
	(method (changeState newState &tmp saveATPs)
		(switch (= state newState)
			(0
				(ego setScript: egoDownLadderScript self)
			)
			(1
				((ScriptID 25 1)
					view: 425
					init:
					illegalBits: 0
					posn: 302 87
					setLoop: 0
					heading: 0
					cycleSpeed: 1
					setCycle: EndLoop self
				)
			)
			(2
				(Print 391 0)
				(Print 391 1)
				((ScriptID 25 1) dispose:)
				(subMarine roomFlags: (| (subMarine roomFlags?) $0002))
				(cast eachElementDo: #hide)
				(= saveATPs addToPics)
				(= addToPics 0)
				(curRoom drawPic: 101)
				(Print 391 2)
				(Print 391 3)
				(Print 391 4)
				(Print 391 5)
				(Print 391 6)
				(Print 391 7)
				(curRoom drawPic: (curRoom picture?))
				((= addToPics saveATPs) doit:)
				(cast eachElementDo: #show)
				(HandsOn)
				(ego
					view: 232
					setCycle: Walk
					posn: 204 111
					setLoop: -1
					loop: 1
					illegalBits: cWHITE
				)
				(ego setScript: (ScriptID 25 3))
			)
		)
	)
)
