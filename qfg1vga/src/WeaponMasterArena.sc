;;; Sierra Script 1.0 - (do not remove this comment)
(script# 223)
(include game.sh) (include "39.shm")
(use Main)
(use Procs)
(use Motion)
(use User)
(use System)

(public
	tooGood 0
	tooTired 1
)

(instance tooGood of Script
	(method (dispose)
		(super dispose:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Bset fStopFightingMaster)
				((ScriptID rCastleCourtyard 1) stop:)
				(= cycles 2)
			)
			(1
				(messager say: N_ROOM NULL C_ENOUGH)
				((ScriptID rCastleCourtyard 2)
					view: vWeaponsMaster
					setLoop: (if (client fightLeft?) 5 else 4)
					setCel: 0
				)
				(= cycles 6)
			)
			(2
				(SkillUsed LUCK 50)
				(if (== heroType FIGHTER)
					(SolvePuzzle f39BeatMaster 10 FIGHTER)
				)
				(messager say: N_ROOM NULL C_BEATHIM)
				((ScriptID rCastleCourtyard 2)
					setLoop: (if (== ((ScriptID rCastleCourtyard 2) loop?) 5) 1 else 0)
					cycleSpeed: 0
					moveSpeed: 0
					setCycle: Walk
					setMotion: MoveTo
						(if ((ScriptID rCastleCourtyard 2) fightLeft?) 340 else -20)
						((ScriptID rCastleCourtyard 2) y?)
						self
				)
				(Bclr fMasterIsHere)
				(Bset fBeatMaster)
			)
			(3
				(Bclr fStopFightingMaster)
				((client opponent?) dispose:)
				(ego
					posn: ((client opponent?) x?) ((client opponent?) y?)
					loop: (if ((ScriptID rCastleCourtyard 2) fightLeft?) 0 else 1)
					cel: 0
					show:
				)
				(NormalEgo)
				(HandsOn)
				(User canInput: TRUE)
				(client dispose:)
			)
		)
	)
)

(instance tooTired of Script
	(method (dispose)
		(super dispose:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(Bset fStopFightingMaster)
				((ScriptID rCastleCourtyard 1) stop:)
				(= cycles 2)
			)
			(1
				(theGame setCursor: waitCursor TRUE)
				(client
					view: vEgoTiredSword
					setLoop: 0
					cel: 0
					cycleSpeed: 6
					setCycle: EndLoop self
				)
			)
			(2
				(if (< [egoStats STAMINA] 2)
					(messager say: N_ROOM NULL C_TIREDOUT 1 self)
				else
					(messager say: N_ROOM NULL C_OUTOFBOUNDS 1 self)
				)
			)
			(3
				(client
					setLoop: 1
					cel: 0
					setCycle: EndLoop self
				)
			)
			(4
				(= ticks 120)
			)
			(5
				(client
					setLoop: 2
					cel: 0
					setCycle: EndLoop self
				)
			)
			(6
				(client hide: forceUpd:)
				(= ticks 1)
			)
			(7
				(ego
					loop: (if (client fightLeft?) 1 else 0)
					posn: (client x?) (client y?)
					show:
				)
				(NormalEgo)
				(= ticks 20)
			)
			(8
				(if ((ScriptID 218 0) endFight?)
					(((ScriptID 218 0) opponent?)
						setScript: (ScriptID 224 0)
					)
				)
				(Bclr fStopFightingMaster)
				(= ticks 14)
			)
			(9
				(client dispose:)
			)
		)
	)
)
