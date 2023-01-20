;;; Sierra Script 1.0 - (do not remove this comment)
(script# 303)
(include sci.sh)
(use Main)
(use Intrface)
(use Motion)
(use User)
(use Actor)
(use System)

(public
	prepareToDie 0
	slotGuy 1
	slotGuyBody 2
)

(instance prepareToDie of Script
	(properties)
	
	(method (init)
		(= register (Random 7 14))
		(super init: &rest)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles (Random 30 60)))
			(1
				(if
					(and
						(<= (-- register) 0)
						(not (== (ego view?) 30))
						(not (& (ego onControl:) $4002))
					)
					(HandsOff)
					(if (not (== (ego view?) 30)) (Face ego slotGuy))
					(self changeState: 6)
				else
					(slotGuy setLoop: 1 cycleSpeed: 8 setCycle: End self)
				)
			)
			(2
				((ScriptID 43 2) setCycle: Fwd)
				(= cycles (Random 15 60))
			)
			(3
				((ScriptID 43 2) setCycle: 0)
				(slotGuy
					setLoop: 2
					cel: 0
					cycleSpeed: 1
					setCycle: End self
				)
			)
			(4
				(= state 0)
				(slotGuy
					setLoop: 2
					cel: 2
					cycleSpeed: 1
					setCycle: Beg self
				)
			)
			(5
				(if (== client ego) (= seconds 2) else (= cycles 3))
			)
			(6
				(self setScript: (ScriptID 43 1) self (ScriptID 303 1))
			)
			(7
				(cond 
					((== client ego) (curRoom newRoom: 42))
					((not sittingAtBar)
						(HandsOn)
						(if (== (ego view?) 30) (User canControl: 0))
					)
				)
				(theMusic2 stop:)
				(self dispose:)
			)
		)
	)
)

(instance slotGuy of Prop
	(properties
		x 265
		y 82
		description {slot guy}
		lookStr {Some strange, blue dude currently monopolizes the Slots-O-Death machine.}
		view 443
		loop 1
		priority 7
		signal $4010
	)
	
	(method (dispose)
		(self setScript: 0 setCycle: 0)
		(slotGuyBody dispose:)
		(super dispose:)
		(self delete:)
		(UnLoad 443)
		(DisposeScript 303)
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(11 (Print 303 0))
			(5 (Print 303 1))
			(12 (Print 303 2))
			(4
				(switch theItem
					(10 (Print 303 3))
					(0 (Print 303 4))
					(15 (Print 303 5))
					(2 (Print 303 6))
					(4 (Print 303 7))
					(5 (Print 303 8))
					(11 (Print 303 9))
					(17 (Print 303 10))
					(18 (Print 303 10))
					(else 
						(super doVerb: theVerb theItem)
					)
				)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance slotGuyBody of Prop
	(properties
		x 263
		y 104
		view 443
		cycleSpeed 4
	)
	
	(method (dispose)
		(self setCycle: 0)
		(super dispose:)
		(self delete:)
	)
	
	(method (doVerb theVerb)
		(slotGuy doVerb: theVerb &rest)
	)
)
