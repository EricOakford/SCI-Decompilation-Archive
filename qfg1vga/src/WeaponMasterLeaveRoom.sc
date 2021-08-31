;;; Sierra Script 1.0 - (do not remove this comment)
(script# 224)
(include game.sh) (include "39.shm")
(use Main)
(use Motion)
(use System)

(public
	leaveRoom 0
)

(instance leaveRoom of Script
	(method (dispose)
		(super dispose:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Bset fStopFightingMaster)
				((ScriptID 39 2)
					view: 639
					setLoop: (if ((ScriptID 39 2) fightLeft?) 0 else 1)
					cel: 0
				)
				(= cycles 2)
			)
			(1 (messager say: N_ROOM NULL C_GETTINGBETTER 0 self))
			(2
				(if ((client opponent?) endFight?)
					(messager say: N_ROOM NULL C_GIVEIN 0 self)
				else
					(messager say: N_ROOM NULL C_TIREDOUT 0 self)
				)
			)
			(3
				(switch (Random 0 4)
					(0
						(messager say: N_ROOM NULL C_TIP5 0 self)
					)
					(1
						(messager say: N_ROOM NULL C_TIP2 0 self)
					)
					(2
						(messager say: N_ROOM NULL C_TIP4 0 self)
					)
					(3
						(messager say: N_ROOM NULL C_TIP3 0 self)
					)
					(4
						(messager say: N_ROOM NULL C_TIP1 0 self)
					)
				)
			)
			(4
				(messager say: N_ROOM NULL C_FAREWELL 0 self)
			)
			(5
				((ScriptID 39 2)
					setLoop: (if ((ScriptID 39 2) loop?) 0 else 1)
					cycleSpeed: 6
					moveSpeed: 6
					setCycle: Walk
					setMotion:
						MoveTo
						(if ((ScriptID 39 2) fightLeft?) 340 else -20)
						(+ ((ScriptID 39 2) y?) 20)
						self
				)
				(Bclr fMasterIsHere)
				(Bclr fStopFightingMaster)
			)
			(6 (HandsOn) (client dispose:))
		)
	)
)
