;;; Sierra Script 1.0 - (do not remove this comment)
(script# 222)
(include game.sh)
(use Main)
(use Motion)
(use System)

(public
	intro 0
	intro2 1
	startFight 2
)

(local
	local0
)

(instance intro of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(switch prevRoomNum
					(38
						((ScriptID 221 0)
							posn: 318 140
							fightLeft: 1
							setMotion: MoveTo 200 140 self
						)
					)
					(40
						((ScriptID 221 0)
							posn: 1 140
							setMotion: MoveTo 100 140 self
						)
					)
					(41
						(if (> egoX 160)
							((ScriptID 221 0)
								posn: 1 170
								setMotion: MoveTo 100 170 self
							)
						else
							((ScriptID 221 0)
								posn: 318 170
								fightLeft: 1
								setMotion: MoveTo 200 170 self
							)
						)
					)
					(else 
						((ScriptID 221 0)
							posn: 1 130
							setMotion: MoveTo 100 130 self
						)
					)
				)
			)
			(1
				(if (not (Btst fMasterShowedOff))
					(Bset fMasterShowedOff)
				)
				(self cue:)
			)
			(2
				((ScriptID 221 0) view: vWeaponMaster)
				(++ local0)
				(self
					setScript: (ScriptID 217 (Random 0 6)) 0 (ScriptID 221 0)
				)
				(= cycles 15)
			)
			(3
				(if
					(and
						(< local0 (Random 7 12))
						(not (< (ego distanceTo: (ScriptID 221 0)) 40))
					)
					(self changeState: 2)
				else
					(= local0 0)
					(self cue:)
				)
			)
			(4
				((ScriptID 221 0)
					view: vWeaponMasterTalk
					setLoop: (if (< (ego x?) ((ScriptID 221 0) x?)) 5 else 4)
					setCel: 0
				)
				(HandsOn)
				(client setScript: 0)
			)
		)
	)
)

(instance intro2 of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(switch prevRoomNum
					(38
						((ScriptID 221 0) posn: 200 140 fightLeft: 1)
					)
					(40
						((ScriptID 221 0) posn: 100 140)
					)
					(41
						(if (> egoX 160)
							((ScriptID 221 0) posn: 100 170)
						else
							((ScriptID 221 0) posn: 200 170 fightLeft: 1)
						)
					)
					(else 
						((ScriptID 221 0) posn: 100 130)
					)
				)
				(= cycles 1)
			)
			(1
				((ScriptID 221 0) view: vWeaponMaster)
				(++ local0)
				(self
					setScript: (ScriptID 217 (Random 0 6)) 0 (ScriptID 221 0)
				)
				(= cycles 12)
			)
			(2
				(if
					(and
						(< local0 (Random 7 12))
						(not (< (ego distanceTo: (ScriptID 221 0)) 40))
					)
					(self changeState: 1)
				else
					(= local0 0)
					(self cue:)
				)
			)
			(3
				((ScriptID 221 0)
					view: vWeaponMasterTalk
					setLoop: (if (< (ego x?) ((ScriptID 221 0) x?)) 5 else 4)
					setCel: 0
				)
				(client setScript: 0)
			)
		)
	)
)

(instance startFight of Script
	(method (dispose)
		(super dispose:)
		(DisposeScript 222)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				((ScriptID 221 0) ignoreActors:)
				(ego ignoreActors:)
				(if (> ((ScriptID 221 0) x?) (ego x?))
					((ScriptID 221 0)
						view: vWeaponMasterTalk
						setLoop: -1
						setCycle: Walk
						fightLeft: 1
						warriorX: 125
						setMotion: MoveTo 187 150
					)
				else
					((ScriptID 221 0)
						view: vWeaponMasterTalk
						setLoop: -1
						setCycle: Walk
						fightLeft: 0
						setMotion: MoveTo 125 150
					)
				)
				(if ((ScriptID 221 0) fightLeft?)
					(ego setMotion: MoveTo 125 150)
				else
					(ego setMotion: MoveTo 187 150)
				)
				(= cycles 3)
			)
			(1
				(cond 
					(((ScriptID 221 0) fightLeft?)
						(if
							(and
								(== (ego x?) 125)
								(== (ego y?) 150)
								(== ((ScriptID 221 0) x?) 187)
								(== ((ScriptID 221 0) y?) 150)
							)
							(self changeState: 3)
						else
							(= cycles 1)
						)
					)
					(
						(and
							(== (ego x?) 187)
							(== (ego y?) 150)
							(== ((ScriptID 221 0) x?) 125)
							(== ((ScriptID 221 0) y?) 150)
						)
						(self changeState: 3)
					)
					(else (= cycles 1))
				)
			)
			(2 (self changeState: 1))
			(3
				(Face ego (ScriptID 221 0))
				(if ((ScriptID 221 0) fightLeft?)
					((ScriptID 221 0) setLoop: 5 cel: 0)
				else
					((ScriptID 221 0) setLoop: 4 cel: 0)
				)
				(= cycles 5)
			)
			(4
				((ScriptID 221 0) cycleSpeed: 2 setCycle: CycleTo 2 1)
				(= cycles 10)
			)
			(5
				(HighPrint 222 0)
				;"I salute you!"
				(ego
					view: vEgoTalkWeaponMaster
					setLoop: (if ((ScriptID 221 0) fightLeft?) 8 else 9)
					setCycle: CycleTo 2 1
				)
				(= cycles 10)
			)
			(6
				(HighPrint 222 1)
				;"En garde!" shouts the Weapon Master...
				((ScriptID 221 0) setCel: 3)
				(ego setCel: 3)
				(= cycles 5)
			)
			(7
				(HighPrint 222 2)
				;And the lesson begins!
				(HandsOn)
				((ScriptID 221 0) setScript: (ScriptID 220 0))
				(client setScript: 0)
			)
		)
	)
)
