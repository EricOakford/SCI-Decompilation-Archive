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
	oldTime
	thisTime
	saveBits
	oldSignal
	oldPriority
	oldIllBits
	oldSpeed
)
(instance nosePick of Code
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
			(if (!= oldTime (GetTime SYSTIME1))
				(= oldTime (GetTime SYSTIME1))
				(if (> (++ thisTime) 120)
					(HandsOff)
					(curRoom setScript: nosePickScript)
				)
			)
		else
			(= thisTime 0)
		)
	)
)

(instance nosePickScript of Script
	(properties
		name "nPS"
	)
	
	(method (changeState newState &tmp theLoop textY)
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
				(= theLoop (Random 0 2))
				(= oldSignal (ego signal?))
				(= oldPriority (ego priority?))
				(= oldIllBits (ego illegalBits?))
				(= oldSpeed (ego cycleSpeed?))
				(ego
					view: 65
					setLoop: theLoop
					cycleSpeed: 1
					cel: 0
					setCycle: EndLoop self
				)
				(Animate (cast elements?) FALSE)
				(if (not theLoop)
					(if (< (ego y?) 85)
						(= textY -20)
					else
						(= textY 80)
					)
					(= saveBits
						(DoDisplay {"So why am I standing around?"}
							#at 100 (- (ego y?) textY)
							#width 219
						)
					)
				)
			)
			(2
				(if (not (ego loop?))
					(= seconds 3)
				else
					(= cycles 1)
				)
			)
			(3
				(if (not (ego loop?))
					(DoDisplay saveBits)
				)
				(NormalEgo 2 0 4)
				(ego
					signal: oldSignal
					priority: oldPriority
					illegalBits: oldIllBits
					cycleSpeed: oldSpeed
				)
				(= oldTime (= start 0))
				(= thisTime 0)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)
