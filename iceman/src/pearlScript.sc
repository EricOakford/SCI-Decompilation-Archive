;;; Sierra Script 1.0 - (do not remove this comment)
(script# 373)
(include game.sh)
(use Main)
(use Intrface)
(use subMarine)
(use n396)
(use Submarine_806)
(use EgoDead)
(use Monitor)
(use Eval)
(use System)

(public
	pearlScript 0
	checkSpeed 1
	checkRudder 2
	checkDive 3
	checkDepthScript 4
	holdSteadyScript 5
	checkHeading 6
	PearlDeath 7
)

(local
	local0 =  1
)
(procedure (PearlDeath)
	(DisposeScript 370)
	(DisposeScript 822)
	(EgoDead 926 3 0 &rest)
)

(instance pearlScript of Script
	
	(method (doit)
		(checkSpeed doit:)
		(checkRudder doit:)
		(checkDive doit:)
		(checkHeading doit:)
		(super doit: &rest)
		(if
			(and
				(> (Submarine depth:) 45)
				(& (Submarine flags?) $0008)
			)
			(PearlDeath 373 3)
		)
		(switch state
			(3
				(if
					(and
						(< 353 (Submarine absHeading:))
						(< (Submarine absHeading:) 357)
					)
					(= cycles 1)
				)
			)
			(4
				(if (== (Submarine hSpeed?) 60) (= cycles 1))
			)
		)
	)
	
	(method (dispose)
		(cls)
		(cond 
			((< state 6)
				(EgoDead 7 0 0 373 8)
			)
			((!= (Submarine throttle?) 0)
				(EgoDead 7 0 0 373 9)
			)
			((!= (Submarine pitch?) 0)
				(EgoDead 7 0 0 373 10)
			)
			(else
				(subMarine cue:)
				(theGame
					changeScore:
						(-
							5
							(/
								(+
									(checkSpeed count?)
									(checkRudder count?)
									(checkDive count?)
									(checkHeading count?)
								)
								2
							)
						)
				)
				(super dispose:)
				(DisposeScript 396)
				(DisposeScript 826)
				(DisposeScript 373)
			)
		)
	)
	
	(method (changeState newState &tmp temp0)
		(if modelessDialog (modelessDialog dispose:))
		(switch (= state newState)
			(0
				(ScriptID 396)
				(ScriptID 822)
				Monitor
				((ScriptID 27 4) posn: 51 64)
				(self setScript: (ScriptID 383) self)
			)
			(1 (= cycles 2))
			(2
				(self setScript: (ScriptID 384) self)
			)
			(3
				(SubPrint 5 373 0)
				(checkDive init: 354 0 0)
				(checkRudder init: 356 3 0)
			)
			(4
				(= start state)
				(checkRudder active: 0)
				(SubPrint 5 373 1)
				(checkHeading init: 353 355 5)
			)
			(5
				(checkSpeed init: 358 60 2)
				(SubPrint 5 373 2)
				(= seconds 20)
			)
			(6
				(checkDive active: 0)
				(checkSpeed active: 0)
				(checkHeading active: 0)
				(self setScript: (ScriptID 389) self)
			)
			(7
				((ScriptID 27 4) posn: 47 59)
				(self dispose:)
			)
		)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '/attain,acknowledge,confirm<depth')
				(Print 373 4)
			)
			((Said 'hold[<on][/jockstrap,strap[<jock]]')
				(Print 373 5)
			)
			((Said 'blow/puppy')
				(Print 373 6)
				(Print 373 7)
			)
		)
	)
)

(instance checkSpeed of Monitor
	
	(method (warn)
		(SubPrint 3 373 11)
	)
	
	(method (die)
		(PearlDeath 373 12)
	)
)

(instance checkRudder of Monitor

	(method (warn)
		(SubPrint 3 373 13)
	)
	
	(method (die)
		(PearlDeath 373 14)
	)
)

(instance checkDive of Monitor
	
	(method (warn)
		(SubPrint 3 373 15)
	)
	
	(method (die)
		(PearlDeath 373 16)
	)
)

(instance checkHeading of Monitor
	
	(method (doit)
		(if
			(and
				active
				(not (umod (++ cycles) 50))
				(<
					variance
					(umod (- value (Eval Submarine theSelector)) 360)
				)
				(<
					(umod (- value (Eval Submarine theSelector)) 360)
					(- 360 variance)
				)
			)
			(if (<= (++ count) 3)
				(if modelessDialog (-- count) else (self warn:))
			else
				(self die:)
			)
		)
	)
	
	(method (warn)
		(SubPrint 4 373 17)
	)
	
	(method (die)
		(PearlDeath 373 18)
	)
)

(instance checkDepthScript of Script

	(method (doit)
		(cond 
			((umod (++ local0) 50))
			((== register -1))
			((> state 3)
				(PearlDeath 373 19)
			)
			((!= register 200)
				(if (> (Abs (- (Submarine depth:) register)) 10)
					(SubPrint 3 373 20)
					(= cycles 1)
				)
			)
			((> (Submarine depth:) 220)
				(PearlDeath 373 21)
			)
			((> (Submarine depth:) 190)
				(checkDive value: 0)
				(SubPrint 3 373 22)
				(= cycles 1)
			)
		)
		(super doit:)
	)
)

(instance holdSteadyScript of Script
	
	(method (doit)
		(if
			(or
				(Submarine rudder?)
				(Submarine pitch?)
				((ScriptID 806 1) incPerTime?)
			)
			(= register 0)
			(self dispose:)
		else
			(super doit: &rest)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 10))
			(1
				(self register: 1 dispose:)
				(return)
			)
		)
	)
)
