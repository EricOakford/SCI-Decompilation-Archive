;;; Sierra Script 1.0 - (do not remove this comment)
(script# CASTOPEN)
(include game.sh) (include "558.shm")
(use Main)
(use Procs)
(use PolyPath)
(use LoadMany)
(use Sound)
(use Motion)
(use System)

(public
	CastOpen 0
)

(local
	oldSignal
	oldPriority
	oldIllBits
	oldCycleSpeed
	local4
	local5
	openSound
	wasHandsOn
)
(procedure (CastOpen theEgo theObj param3 param4 &tmp oldEgo temp1)
	(if (not isHandsOff)
		(= wasHandsOn TRUE)
	)
	(LoadMany SOUND 28 35)
	(= local4 0)
	(= local5 0)
	(= oldEgo ego)
	(= temp1 0)
	(if argc
		(if (> argc 1)
			(= temp1 theObj)
		)
		(if (> argc 2)
			(= local4 param3)
			(= local5 param4)
		)
		(= oldEgo theEgo)
	)
	(oldEgo setScript: clientCastOpen temp1)
)

(instance clientCastOpen of Script
	
	(method (dispose)
		(if wasHandsOn
			(HandsOn)
		)
		(if (IsObject openSound)
			(openSound stop: dispose:)
		)
		(NormalEgo)
		(ego
			loop: (if (not (ego loop?)) 5 else 4)
			priority: oldPriority
			illegalBits: oldIllBits
			signal: oldSignal
			cycleSpeed: oldCycleSpeed
		)
		(super dispose:)
		(DisposeScript CASTOPEN)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= ticks 10)
			)
			(1
				(HandsOff)
				(= oldSignal (ego signal?))
				(= oldPriority (ego priority?))
				(= oldIllBits (ego illegalBits?))
				(= oldCycleSpeed (ego cycleSpeed?))
				(NormalEgo)
				(theGame setCursor: waitCursor TRUE)
				(if local4
					(ego setMotion: PolyPath local4 local5 self)
				else
					(self cue:)
				)
			)
			(2
				(theGame setCursor: waitCursor TRUE)
				(ego
					setMotion: 0
					setHeading: (if (OneOf (ego loop?) 2 4 0 6) 135 else 225) self
				)
			)
			(3
				(ego
					view: 521
					setLoop: (if (== (ego loop?) 5) 0 else 1)
					setCel: 0
					setPri: (ego priority?)
					cycleSpeed: 12
					setCycle: CycleTo 2 1 self
				)
			)
			(4
				((= openSound (Sound new:))
					number: 28
					priority: 6
					init:
					play:
				)
				(ego setCycle: EndLoop self)
			)
			(5
				(ego view: 4 loop: 2 setCel: 0)
				(= ticks 20)
			)
			(6
				(if
					(not
						(OneOf curRoomNum
							10 13 15 16 29 30 31 41 65 67 73 76
							82 83 84 91 93 94 96 97 141
						)
					)
					(messager say: N_NOEFFECT NULL NULL 1 self SPELLS)
				else
					(openSound number: 35 play:)
					(= ticks 30)
				)
			)
			(7
				(self dispose:)
			)
		)
	)
)
