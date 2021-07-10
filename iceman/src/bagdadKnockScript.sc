;;; Sierra Script 1.0 - (do not remove this comment)
(script# 365)
(include game.sh)
(use Main)
(use Intrface)
(use tunisia)
(use GoToSaid)
(use Avoider)
(use Motion)
(use User)
(use System)

(public
	bagdadKnockScript 0
	handsUpScript 1
)

(local
	local0
)
(instance bagdadKnockScript of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(Print 365 0)
				(Print 365 1)
				(= seconds 10)
			)
			(1
				(Print 365 2)
				(= seconds 10)
			)
			(2
				(Print 365 3)
				(= seconds 5)
			)
			(3
				(tunisia flags: (| (tunisia flags?) $0004))
				(self dispose:)
			)
		)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			(
				(and
					(> state 1)
					(or (Said 'answer,open/door') (Said 'answer'))
				)
				(Print 365 4)
			)
			((GoToIfSaid self event 93 156 'answer,open/door')
				(= seconds 0)
			)
			((Said 'answer,open/door')
				((ScriptID 310 4) init: posn: 126 200)
				(= caller 0)
				(client setScript: bagdadInScript)
			)
		)
	)
)

(instance bagdadInScript of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(tunisia flags: (| (tunisia flags?) $0002))
				(ego setAvoider: Avoider setMotion: MoveTo 95 159 self)
			)
			(1
				(Print 365 5)
				((ScriptID 310 4)
					setCycle: Walk
					setMotion: MoveTo 126 150 self
				)
			)
			(2
				(Print 365 6)
				((ScriptID 310 4)
					illegalBits: 0
					setMotion: MoveTo 210 112 self
				)
				(ego setAvoider: 0 setMotion: MoveTo 131 112)
			)
			(3
				((ScriptID 310 4)
					view: 584
					setLoop: 3
					cel: 0
					cycleSpeed: 2
					setCycle: CycleTo 1 1 self
				)
			)
			(4
				((ScriptID 310 4) cycleSpeed: 0 setCycle: CycleTo 4 1 self)
				((ScriptID 310 5) init: setPri: 9 stopUpd:)
			)
			(5
				((ScriptID 310 4)
					setCycle: Walk
					setMotion: MoveTo
						(- ((ScriptID 310 4) x?) 15)
						((ScriptID 310 4) y?)
						self
				)
			)
			(6
				(= seconds 2)
			)
			(7
				(Print 365 7)
				(= cycles 1)
			)
			(8
				(ego heading: 90 cycleSpeed: 1)
				((ego looper?) doit: ego (ego heading?))
				(= cycles 3)
			)
			(9
				(ego cycleSpeed: 0)
				(tunisia flags: (| (tunisia flags?) $0001))
				(HandsOn)
				(= seconds 10)
			)
			(10
				(Print 365 8)
				(= seconds 8)
			)
			(11
				(Print 365 9)
				(= seconds 6)
			)
			(12
				(HandsOff)
				(Print 365 10)
				(client setScript: bagdadOutScript)
			)
		)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			(
				(Said
					'shoot,draw,use/man,bagdad,gun,tranquilizer,(gun<tranquilizer)'
				)
				(cond 
					((not (ego has: iTranquilizerGun))
						(DontHave)
					)
					((== (ego view?) 584)
						(Print 365 11)
					)
					(else
						(client setScript: getInPosnScript)
					)
				)
			)
			(
				(or
					(Said 'change,detach<tell<bagdad,man[/clothes]')
					(Said 'change,detach,(get<off)/clothes')
					(Said 'undress')
				)
				(HandsOff)
				(Print 365 12)
				(client setScript: bagdadOutScript)
			)
			(
				(or
					(Said 'pay,give/man,bagdad,money')
					(Said 'give/money<to/man,bagdad')
					(Said 'give/money<man,bagdad')
				)
				(Print 365 13)
			)
			((Said 'hit/man,bagdad[/gun<with]')
				(Print 365 14)
			)
			((Said 'heist,get/man,money[/man<from]')
				(Print 365 15)
			)
		)
	)
)

(instance getInPosnScript of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(cond 
					((ego inRect: 223 94 271 124)
						(ego illegalBits: 0 setMotion: MoveTo 235 91 self)
					)
					((ego inRect: 209 83 254 93)
						(ego setMotion: MoveTo 206 93 self)
					)
					((ego inRect: 218 132 317 153)
						(ego setMotion: MoveTo 214 136 self)
					)
					((and (== (ego x?) 131) (== (ego y?) 112))
						(= cycles 1)
						(= register TRUE)
					)
					(else (ego setMotion: MoveTo 131 112 self)
						(= register TRUE)
					)
				)
			)
			(1
				(if register
					(= cycles 1)
				else
					(self init:)
				)
			)
			(2
				(ego setMotion: MoveTo (+ (ego x?) 5) (ego y?) self)
			)
			(3
				(ego
					view: 584
					illegalBits: cWHITE
					cel: 0
					loop: 2
					setCycle: EndLoop
				)
				(Print 365 16)
				(client setScript: handsUpScript)
				(User canInput: TRUE)
			)
		)
	)
)

(instance bagdadOutScript of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (ego inRect: 84 142 142 160)
					(ego setMotion: MoveTo 80 140)
				)
				((ScriptID 310 4) loop: 9 cel: 0 setCycle: CycleTo 1 1 self)
			)
			(1
				((ScriptID 310 4)
					setAvoider: Avoider
					setCycle: Walk
					setMotion:
						MoveTo
						(+ ((ScriptID 310 4) x?) 15)
						((ScriptID 310 4) y?)
						self
				)
			)
			(2
				((ScriptID 310 4) setCycle: EndLoop self)
				((ScriptID 310 5) dispose:)
			)
			(3
				((ScriptID 310 4)
					view: 184
					setCel: -1
					setLoop: -1
					setCycle: Walk
					setMotion: MoveTo 126 150 self
				)
			)
			(4
				((ScriptID 310 4)
					setMotion: MoveTo ((ScriptID 310 4) x?) 210 self
				)
				(ego heading: 180 cycleSpeed: 1)
				((ego looper?) doit: ego (ego heading?))
			)
			(5
				(tunisia flags: (| (tunisia flags?) $0004))
				(client cue:)
			)
		)
	)
)

(instance handsUpScript of Script

	(method (init)
		(super init: &rest)
		(keyDownHandler add: self)
	)
	
	(method (doit)
		(super doit:)
		(if (and register (== (-- register) 1))
			(tunisia flags: (| (tunisia flags?) $0004))
			(client cue:)
		)
	)
	
	(method (dispose)
		(keyDownHandler delete: self)
		(super dispose: &rest)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				((ScriptID 310 4)
					view: 584
					setLoop: 4
					cel: 0
					cycleSpeed: 1
					setCycle: EndLoop self
				)
			)
			(1 (= seconds 2))
			(2
				(Print 365 17)
				(= register 100)
			)
			(3
				(= register 0)
				(HandsOff)
				((ScriptID 310 4)
					setCel: 0
					setLoop: 5
					ignoreActors: TRUE
					setCycle: CycleTo 14 1 self
				)
			)
			(4
				((ScriptID 310 7)
					init:
					posn: ((ScriptID 310 4) x?) (+ ((ScriptID 310 4) y?) 1)
					stopUpd:
					ignoreActors: TRUE
				)
				((ScriptID 310 4) cel: 15)
				(= cycles 2)
			)
			(5
				(= register 100)
				(Print 365 18)
				(User canInput: TRUE)
			)
			(6
				(HandsOff)
				(= register 0)
				(ego setCycle: BegLoop self)
			)
			(7
				(ego
					view: 84
					setCycle: Walk
					setMotion: MoveTo
						(- ((ScriptID 310 4) x?) 7)
						(+ ((ScriptID 310 4) y?) 1)
						self
				)
			)
			(8
				(ego heading: 0 cycleSpeed: 1)
				((ego looper?) doit: ego (ego heading?))
				(= cycles 1)
			)
			(9
				((ScriptID 310 4) loop: 8 cel: (+ 0 (* 3 local0)))
				(ego loop: 7 cel: 0)
				(= cycles 2)
			)
			(10
				(ego cel: 1)
				(= cycles 2)
			)
			(11
				(ego cel: 2)
				(= cycles 2)
				((ScriptID 310 4) cel: (+ 1 (* 3 local0)))
			)
			(12
				(ego cel: 1)
				(= cycles 2)
			)
			(13
				(ego cel: 2)
				(= cycles 2)
				((ScriptID 310 4) cel: (+ 2 (* 3 local0)))
			)
			(14
				((ScriptID 310 4) ignoreActors: 0)
				(ego cel: 1)
				(= cycles 2)
			)
			(15 
				(ego cel: 2)
				(= cycles 2)
			)
			(16
				(ego view: 84)
				(= seconds 2)
			)
			(17
				(Print 365 19)
				(theGame changeScore: 3)
				(if local0
					(HandsOn)
					(= register 2)
				else
					(= cycles 1)
				)
			)
			(18
				(ego heading: 270 cycleSpeed: 1)
				((ego looper?) doit: ego (ego heading?))
				(= cycles 1)
			)
			(19
				(ego
					view: 584
					cel: 0
					loop: 0
					cycleSpeed: 1
					setCycle: CycleTo 14 1 self
				)
			)
			(20
				((ScriptID 310 6)
					init:
					posn: (ego x?) (+ (ego y?) 1)
					stopUpd:
					ignoreActors: TRUE
				)
				(ego cel: 15)
				(= cycles 2)
			)
			(21
				(ego cel: 0 loop: 6 setCycle: CycleTo 13 1 self)
			)
			(22
				((ScriptID 310 7) dispose:)
				(ego cel: 14)
				(= cycles 1)
			)
			(23
				(ego cel: 15)
				(= cycles 2)
			)
			(24
				(Print 365 20)
				(tunisia bagBound: 1)
				(tunisia flags: (| (tunisia flags?) $0001))
				((ScriptID 310 4) stopUpd:)
				(HandsOn)
				(ego
					view: 684
					setCycle: Walk
					loop: 1
					setLoop: -1
					cycleSpeed: 0
				)
				(= cycles 2)
			)
			(25 (client cue:))
		)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			(
				(or
					(and
						(== (event type?) keyDown)
						(== (event message?) `#a)
					)
					(Said 'shoot,fire,kill[/man,bagdad,gun]')
				)
				(Print 365 11)
			)
			(
				(or
					(Said 'change<tell<bagdad,man[/clothes]')
					(Said 'change,detach,(get<off)/clothes')
					(Said 'get/clothes[/man,bagdad]')
					(Said 'undress')
				)
				(cond 
					((and (> state 4) (not local0)) (Print 365 21))
					((and (> state 4) local0) (Print 365 22))
					(else (Print 365 23) (Print 365 24) (self cue:))
				)
			)
			(
				(or
					(Said 'bind,bind[<up]/man,bagdad[<up]')
					(Said 'use/tape')
					(Said '/man,bagdad<tape')
					(Said '//clothes<for')
				)
				(cond 
					((not (ego has: iDuctTape))
						(Print 365 25)
						(tunisia flags: (| (tunisia flags?) $0004))
						(client cue:)
					)
					((< state 3)
						(= local0 1)
						(= start 6)
						((ScriptID 310 4) ignoreActors: TRUE)
						(self init:)
					)
					(else (self cue:))
				)
			)
			(
				(or
					(Said 'pay,give/man,bagdad,money')
					(Said 'give/money<to/man,bagdad')
					(Said 'give/money<man,bagdad')
				)
				(Print 365 13)
			)
			((Said 'hit/man,bagdad[/gun<with]') (Print 365 14))
			((Said 'heist,get/man,money[/man<from]') (Print 365 15))
		)
	)
)
