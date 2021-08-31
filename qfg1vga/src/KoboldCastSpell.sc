;;; Sierra Script 1.0 - (do not remove this comment)
(script# 111)
(include game.sh) (include "15.shm")
(use Main)
(use KoboldCave)
(use CastCalm)
(use Procs)
(use Motion)
(use User)
(use Actor)

(public
	castDazzle 0
	castDet 1
	castOpen 2
	castTrig 3
	castCalm 4
	castFetch 5
)

(local
	lasso
	savCycleSpeed
	savMoveSpeed
)
(instance castDazzle of KScript
	(method (dispose)
		(KoboldFight TRUE)
		(Face ego theKobold)
		(super dispose:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= ticks 10)
			)
			(1
				(theKobold view: 179 setCel: 0)
				(theKobold setScript: 0)
				(= register (User canControl:))
				(theGame setCursor: waitCursor TRUE)
				(Face ego theKobold)
				(ego
					view: 521
					setLoop: (- 3 egoKoboldBattleLoop)
					cel: 0
					setCycle: CycleTo 4 1 self
				)
			)
			(2
				(theKobold setScript: (ScriptID 15 4))
				(ego setCycle: EndLoop)
				(= cycles 6)
			)
			(3
				(self dispose:)
			)
		)
	)
)

(instance castDet of KScript
	(method (dispose)
		(HandsOn)
		(super dispose:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= register 0)
				(if
					(or
						(ego has: iBrassKey)
						[invDropped iBrassKey]
						(cast contains: (ScriptID 15 3))
					)
					(= register TRUE)
					(messager say: N_CASTDETECT NULL C_DETECT_KEY 0 self)
				else
					(= ticks 1)
				)
			)
			(1
				(cond 
					((cast contains: (ScriptID 15 2))
						(messager say: N_CASTDETECT NULL C_DETECT_CHEST 1)
						(= register TRUE)
						((ScriptID 15 2) setCel: 1)
						(= ticks 120)
					)
					((not (Btst fKoboldChestSearched))
						(= register TRUE)
						(messager say: N_CASTDETECT NULL C_DETECT_CHEST 2 self)
					)
					(else
						(= ticks 1)
					)
				)
			)
			(2
				(if (not register)
					(messager say: N_CASTDETECT NULL C_NO_MORE_MAGIC 1 self)
				)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance castOpen of KScript
	(method (dispose)
		(super dispose:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= ticks 10)
			)
			(1
				(theGame setCursor: waitCursor TRUE)
				(ego
					view: 521
					setLoop: (not (mod (ego loop?) 2))
					setCycle: EndLoop self
				)
			)
			(2
				(Bset fKoboldChestKnown)
				(if (TrySkill OPEN 50 0)
					((ScriptID 15 2) setCel: 1)
					(= ticks 60)
				else
					((ScriptID 15 2) setScript: (ScriptID 15 5))
					(self dispose:)
				)
			)
			(3
				(ego
					get: iGold 10
					get: iSilver 60
				)
				(= cycles 1)
			)
			(4
				(Bset fKoboldChestUnlocked)
				(Bset fKoboldChestSearched)
				(SolvePuzzle f15GetTreasure 5)
				(messager say: N_TREASURE NULL C_DETECT_CHEST)
				(KoboldFight 1)
				(Face ego (ScriptID 15 2))
				(self dispose:)
			)
		)
	)
)

(instance castTrig of KScript
	(method (dispose)
		(super dispose:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= ticks 10)
			)
			(1
				(theGame setCursor: waitCursor TRUE)
				(ego view: 521 setLoop: 1 setCycle: EndLoop self)
			)
			(2
				(Bset fKoboldChestKnown)
				((ScriptID 15 2) setScript: (ScriptID 15 5) 0 1)
				(self dispose:)
			)
		)
	)
)

(instance castCalm of KScript
	(method (dispose)
		(= register FALSE)
		(super dispose:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= register TRUE)
				(CastCalm self self)
			)
			(1
				(KoboldFight 0)
				(if (not (Btst fKoboldDead))
					(messager say: N_CANTCALM NULL NULL 0 self)
				)
			)
			(2
				(if register
					(self dispose:)
				)
			)
		)
	)
)

(instance castFetch of KScript	
	(method (dispose)
		(NormalEgo)
		(Face ego theKobold)
		(ego illegalBits: koboldIllBits)
		(HandsOn)
		(super dispose:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= ticks 10)
			)
			(1
				(= savCycleSpeed (ego cycleSpeed?))
				(= savMoveSpeed (ego moveSpeed?))
				(theGame setCursor: waitCursor TRUE)
				(ego
					view: 520
					cycleSpeed: 12
					loop: 3
					cel: 6
					setCycle: BegLoop self
				)
			)
			(2
				(theGame setCursor: waitCursor TRUE)
				(ego loop: 1 setCel: 6)
				((= lasso (Actor new:))
					view: 520
					illegalBits: 0
					ignoreActors:
					posn: (+ (ego x?) 11) (- (ego y?) 44)
					setLoop: 5
					setPri: 7
					setStep: 6 4
					setCycle: Forward
					init:
					setMotion: MoveTo (+ (theKobold x?) 3) (- (theKobold y?) 9) self
				)
			)
			(3
				(= ticks 100)
			)
			(4
				(if (not (TrySkill FETCH 35 0))
					(lasso dispose:)
					(if (AwakenKobold)
						(messager say: N_CASTFETCH NULL NULL 1)
					)
					(self dispose:)
				else
					(lasso
						setPri: 11
						setMotion: MoveTo (+ (ego x?) 11) (- (ego y?) 44) self
					)
					((ScriptID 15 3)
						posn: (lasso x?) (lasso y?)
						show:
						setLoop: 6
						setCel: 0
						setPri: 10
						setStep: 6 4
						setMotion: MoveTo (+ (ego x?) 11) (- (ego y?) 44)
					)
					(if (not (Btst fKoboldAwake))
						(theKobold setLoop: 5)
					)
				)
			)
			(5
				(lasso dispose:)
				((ScriptID 15 3) dispose:)
				(ego
					get: iBrassKey
					loop: loopN
					cel: 0
					setCycle: EndLoop self
				)
				(Bset fGotKoboldKey)
				(SolvePuzzle f15GetKey 7)
			)
			(6
				(messager say: N_CASTFETCH NULL NULL 2)
				(ego cycleSpeed: savCycleSpeed moveSpeed: savMoveSpeed)
				(self dispose:)
			)
		)
	)
)
