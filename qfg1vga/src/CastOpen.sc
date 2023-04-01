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
	savSignal
	savPriority
	savIllegalBits
	savSpeed
	thisX
	thisY
	soundObj
	wasHandsOn
)
(procedure (CastOpen onWhat whoCares onX onY &tmp obj prScript)
	;EO: Seems like this was going to become a projectile spell
	(if (not isHandsOff)
		(= wasHandsOn TRUE)
	)
	(LoadMany SOUND sTeleport sOpenLock)
	(= thisX 0)
	(= thisY 0)
	(= obj ego)
	(= prScript 0)
	(if argc
		(if (> argc 1)
			(= prScript whoCares)
		)
		(if (> argc 2)
			(= thisX onX)
			(= thisY onY)
		)
		(= obj onWhat)
	)
	(obj setScript: clientCastOpen prScript)
)

(instance clientCastOpen of Script
	(method (dispose)
		(if wasHandsOn
			(HandsOn)
		)
		(if (IsObject soundObj)
			(soundObj stop: dispose:)
		)
		(NormalEgo)
		(ego
			loop: (if (not (ego loop?)) loopSW else loopSE)
			priority: savPriority
			illegalBits: savIllegalBits
			signal: savSignal
			cycleSpeed: savSpeed
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
				(= savSignal (ego signal?))
				(= savPriority (ego priority?))
				(= savIllegalBits (ego illegalBits?))
				(= savSpeed (ego cycleSpeed?))
				(NormalEgo)
				(theGame setCursor: waitCursor TRUE)
				(if thisX
					(ego setMotion: PolyPath thisX thisY self)
				else
					(self cue:)
				)
			)
			(2
				(theGame setCursor: waitCursor TRUE)
				(ego
					setMotion: 0
					setHeading: (if (OneOf (ego loop?) facingSouth facingSE facingEast facingNE) 135 else 225) self
				)
			)
			(3
				(ego
					view: vEgoCastSpell
					setLoop: (if (== (ego loop?) facingSW) 0 else 1)
					setCel: 0
					setPri: (ego priority?)
					cycleSpeed: 12
					setCycle: CycleTo 2 1 self
				)
			)
			(4
				((= soundObj (Sound new:))
					number: sTeleport
					priority: 6
					init:
					play:
				)
				(ego setCycle: EndLoop self)
			)
			(5
				(ego
					view: vEgoStand
					loop: facingSouth
					setCel: 0
				)
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
					(messager say: N_NO_EFFECT NULL NULL 1 self SPELLS)
				else
					(soundObj
						number: sOpenLock
						play:
					)
					(= ticks 30)
				)
			)
			(7
				(self dispose:)
			)
		)
	)
)
