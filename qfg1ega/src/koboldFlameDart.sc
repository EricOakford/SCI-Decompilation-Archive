;;; Sierra Script 1.0 - (do not remove this comment)
(script# 110)
(include game.sh)
(use Main)
(use KoboldCave)
(use Sound)
(use Motion)
(use Actor)

(public
	castFlame 0
)

(local
	newSound
	local1
	hitKobold
	koboldX
	koboldY
	[theGKoboldX 6] = [195 225 262 17 17 44]
	[theGKoboldY 6] = [47 43 92 47 134 155]
)
(instance flScript of KScript
	(properties)
	
	(method (dispose)
		(newSound dispose:)
		(Bclr FLAG_283)
		(KoboldFight (not (Btst DEFEATED_KOBOLD)))
		(DisposeScript 110)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(client
					setCycle: Forward
					setMotion: MoveTo koboldX koboldY self
				)
			)
			(1
				(if (and hitKobold (Btst FLAG_281))
					(= hitKobold FALSE)
					(Bset FLAG_280)
					(client
						y: (+ (client y?) 15)
						z: 15
						setMotion: MoveTo (ego x?) (ego y?) self
					)
				else
					(self cue:)
				)
			)
			(2
				(if hitKobold 
					(= hitKobold FALSE) 
					(theKobold getHurt: damageToKoboldFlame)
				)
				;if the flame dart goes off too close to ego, ego gets hurt instead.
				(if (< (client distanceTo: ego) 20)
					(KoboldHurtEgo damageToKoboldFlame)
				)
				(client
					view: vExplosion
					setLoop: 0
					cel: 0
					cycleSpeed: 1
					setCycle: EndLoop self
				)
				(newSound number: (SoundFX 45) play:)
			)
			(3 (client dispose:))
		)
	)
)

(instance castFlame of KScript
	(properties)
	
	(method (changeState newState &tmp temp0 egoLoop)
		(switch (= state newState)
			(0
				(HandsOff)
				(Face ego theKobold)
				(Bset FLAG_283)
				(= egoLoop (ego loop?))
				(if
					(or
						(and fightingKobold egoLoop)
						(and (not fightingKobold) (& egoLoop $0001))
					)
					(= egoLoop loopW)
				else
					(= egoLoop loopE)
				)
				(ego
					view: vEgoMagicFlameDart
					setLoop: egoLoop
					cel: 0
					setCycle: CycleTo 5 1 self
				)
			)
			(1
				(if
				(TrySkill MAGIC 0 (- 50 (/ (ego distanceTo: theKobold) 5)))
					(= hitKobold TRUE)
					(= koboldX (theKobold x?))
					(= koboldY (theKobold y?))
				else
					(= temp0 (+ (Random 0 2) (* egoKoboldBattleLoop 3)))
					(= hitKobold FALSE)
					(= koboldX [theGKoboldX temp0])
					(= koboldY [theGKoboldY temp0])
				)
				(ego setCycle: EndLoop)
				((= newSound (Sound new:))
					number: (SoundFX 33)
					priority: 3
					init:
					play:
				)
				((Actor new:)
					view: vEgoMagicFlameDart
					setLoop: 2
					setCel: 0
					illegalBits: 0
					ignoreActors:
					x: (if (== (ego loop?) 1)
						(- (ego x?) 19)
					else
						(+ (ego x?) 19)
					)
					y: (- (ego y?) 21)
					setStep: 12 8
					init:
					setScript: flScript
				)
				(self dispose:)
			)
		)
	)
)
