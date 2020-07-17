;;; Sierra Script 1.0 - (do not remove this comment)
(script# NOSEPICK) ;808
(include game.sh)
(use Main)
(use SQRoom)
(use StopWalk)
(use Motion)
(use User)
(use System)

(public
	nosePick 0
)

(local
	nosePickTimer
	local1
	mD
	oldSignal
	oldPriority
	oldIllBits
	oldSpeed
)
(instance nosePick of Code
	(properties)
	
	(method (doit)
		(if
			(and
				(User canControl:)
				(User canInput:)
				(IsObject curRoom)
				(curRoom isKindOf: SQRoom)
				(not (curRoom script?))
				(ego normal?)
				(not (& (ego signal?) hideActor))
				(ego isStopped:)
				(IsObject (ego cycler?))
				((ego cycler?) isKindOf: StopWalk)
				(== (ego view?) ((ego cycler?) vStopped?))
				(== ((ego cycler?) vStopped?) 4)
			)
			(if (!= nosePickTimer (GetTime 1))
				(= nosePickTimer (GetTime 1))
				(if (> (++ local1) 120)
					(HandsOff)
					(curRoom setScript: nosePickScript)
				)
			)
		else
			(= local1 0)
		)
	)
)

(instance nosePickScript of Script
	(properties
		name "nPS"
	)
	
	(method (changeState newState &tmp temp0 temp1)
		(switch (= state newState)
			(0
				(Load VIEW 65)
				(if (!= (ego loop?) 2)
					(ego setHeading: 180 self)
				else
					(= cycles 1)
				)
			)
			(1
				(= temp0 (Random 0 2))
				(= oldSignal (ego signal?))
				(= oldPriority (ego priority?))
				(= oldIllBits (ego illegalBits?))
				(= oldSpeed (ego cycleSpeed?))
				(ego
					view: 65
					setLoop: temp0
					cycleSpeed: 1
					cel: 0
					setCycle: EndLoop self
				)
				(Animate (cast elements?) FALSE)
				(if (not temp0)
					(if (< (ego y?) 85) (= temp1 -20) else (= temp1 80))
					(= mD
						(DoDisplay {"So why am I standing around?"}
							#at 100 (- (ego y?) temp1)
							#width 219
						)
					)
				)
			)
			(2
				(if (not (ego loop?)) (= seconds 3) else (= cycles 1))
			)
			(3
				(if (not (ego loop?)) (DoDisplay mD))
				(NormalEgo 2 0 4)
				(ego
					signal: oldSignal
					priority: oldPriority
					illegalBits: oldIllBits
					cycleSpeed: oldSpeed
				)
				(= nosePickTimer (= start 0))
				(= local1 0)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)
