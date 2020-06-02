;;; Sierra Script 1.0 - (do not remove this comment)
(script# EGOSEZ)
(include game.sh) (include "120.shm")
(use Main)
(use CastFlame)
(use CastDagger)
(use CastRock)
(use Procs)
(use Sound)
(use User)
(use System)

(public
	egoSez 0
)

(enum 1 ;use cues
	useRations
	useHealing
	useMana
	useVigor
	useDisenchant
	useGhostOil
	useFruit
	useVegetables
	useWater
)

(local
	trippySound
	useCue
)
(procedure (HaveEaten)
	(if (Btst fHungry)
		(Bclr fHungry)
		(Bclr fStarving)
	else
		(= freeMeals 1)
	)
)

(instance egoSez of Code
	(properties)
	
	(method (doit theVerb &tmp theX theY evt)
		(switch theVerb
			(V_FLAME
				(= theX ((= evt (Event new:)) x?))
				(= theY (+ (evt y?) 25))
				(evt dispose:)
				(CastFlame 0 0 theX theY)
			)
			(V_RATIONS
				(= useCue useRations)
				(messager say: N_EGO V_RATIONS NULL 0 0 EGOSEZ)
				(HaveEaten)
				(ego setScript: cueIt)
			)
			(V_DAGGER
				(CastDagger 0)
			)
			(V_LOCKPICK
				(ego setScript: pickScript)
			)
			(V_ROCK
				(CastRock 0)
			)
			(V_HEALING
				(messager say: N_EGO V_HEALING NULL 0 0 EGOSEZ)
				(TakeDamage (- (/ (MaxHealth) 2)))
				(= useCue useHealing)
				(ego setScript: cueIt)
			)
			(V_MANA
				(messager say: N_EGO V_MANA NULL 0 0 EGOSEZ)
				(UseMana (- (/ (MaxMana) 2)))
				(= useCue useMana)
				(ego setScript: cueIt)
			)
			(V_VIGOR
				(messager say: N_EGO V_VIGOR NULL 0 0 EGOSEZ)
				(UseStamina (- (MaxStamina)))
				(= useCue useVigor)
				(ego setScript: cueIt)
			)
			(V_DISENCHANT
				(messager say: N_EGO V_DISENCHANT NULL 0 0 EGOSEZ)
				(= useCue useDisenchant)
				(ego setScript: cueIt)
			)
			(V_GHOSTOIL
				(messager say: N_EGO V_GHOSTOIL NULL 0 0 EGOSEZ)
				(Bset fGhostOil)
				(Bclr fGhostsAttack)
				(= ghostOilTimer 150)
				(= useCue useGhostOil)
				(ego setScript: cueIt)
			)
			(V_FRUIT
				(messager say: N_EGO V_FRUIT NULL 0 0 EGOSEZ)
				(HaveEaten)
				(= useCue useFruit)
				(ego setScript: cueIt)
			)
			(V_VEGETABLES
				(messager say: N_EGO V_VEGETABLES NULL 0 0 EGOSEZ)
				(HaveEaten)
				(= useCue useVegetables)
				(ego setScript: cueIt)
			)
			(V_ACORN
				(ego setScript: acornScript)
			)
			(V_FAIRYDUST
				(messager say: N_EGO V_FAIRYDUST NULL 0 0 EGOSEZ)
			)
			(V_WATER
				(messager say: N_EGO V_WATER NULL 0 0 EGOSEZ)
				(= useCue 9)
				(ego setScript: cueIt)
			)
			(V_MUSHROOM
				(cond 
					((Btst fHaveToadstools)
						(ego setScript: toadScript)
					)
					(
						(not
							(OneOf curRoomNum
								11 12 17 18 19 23 24 25 26 27 33 34
								35 36 42 43 44 51 52 56 57 61 62 63
								69 70 71 72 74 75 79 80 81 85 86 92
								170
							)
						)
						(messager say: N_EGO V_MUSHROOM C_NOSHROOMSHERE 0 0 EGOSEZ)
					)
					(else
						(Bset fShroomTrip)
						(ego setScript: mushScript)
					)
				)
			)
			(else 
				(Ego doVerb: theVerb &rest)
			)
		)
		(if (not (ego script?))
			(DisposeScript EGOSEZ)
		)
	)
)

(instance pickScript of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(messager say: N_EGO V_LOCKPICK NULL 0 self EGOSEZ)
			)
			(1
				(if (not (TrySkill PICK tryPickNose))
					(EgoDead C_DIE_PICK_NOSE C_DIE_PICK_NOSE_TITLE)
				else
					(messager say: N_EGO V_LOCKPICK C_PICKSUCCESS 0 self EGOSEZ)
				)
			)
			(2
				(HandsOn)
				(self dispose:)
				(DisposeScript EGOSEZ)
			)
		)
	)
)

(instance toadScript of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego use: iMushroom 3)
				(HandsOff)
				(= ticks 60)
			)
			(1
				(if (Btst fHaveFaeryShrooms)
					(messager say: N_EGO V_MUSHROOM C_TOADSTOOLS 1 self EGOSEZ)
				else
					(self cue:)
				)
			)
			(2
				(messager say: N_EGO V_MUSHROOM C_TOADSTOOLS 2 self EGOSEZ)
			)
			(3
				(if (<= [egoStats HEALTH] 20)
					(EgoDead C_DIE_TOADSTOOLS)
				else
					(TakeDamage 20)
					(messager say: N_EGO V_MUSHROOM C_TOADSTOOLS 4 self EGOSEZ)
				)
				(if (not (ego has: iMushroom))
					(Bclr fHaveToadstools)
				)
			)
			(4
				(HandsOn)
				(self dispose:)
				(DisposeScript EGOSEZ)
			)
		)
	)
)

(instance acornScript of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(messager say: N_EGO V_ACORN NULL 0 0 EGOSEZ)
				(= seconds 2)
			)
			(1
				(SolvePuzzle fEatAcorn -5)
				(= ticks 30)
			)
			(2
				(HandsOn)
				(ego use: iAcorn 1)
				(self dispose:)
			)
		)
	)
)

(instance mushScript of Script
	
	(method (doit)
		(if monsterNum
			(Bset fAteFaeryShrooms)
			(Bclr fShroomTrip)
		)
		(if (and (Btst fShroomTrip) (> numColors 16) (not (Btst fCharSheetActive)))
			(Palette PALCycle 75 254 1)
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(messager say: N_EGO V_MUSHROOM C_TRIPPY 1 self EGOSEZ)
			)
			(1
				((= trippySound (Sound new:))
					number: 126
					loop: -1
					flags: -1
					play:
				)
				(= cycles 180)
			)
			(2
				(messager say: N_EGO V_MUSHROOM C_TRIPPY 2 self EGOSEZ)
			)
			(3 (= cycles 90))
			(4
				(messager say: N_EGO V_MUSHROOM C_TRIPPY 3 self EGOSEZ)
			)
			(5
				(trippySound fade:)
				(= cycles 90)
			)
			(6
				(trippySound dispose:)
				(Bclr fShroomTrip)
				(DrawPic (curRoom picture?) DISSOLVE)
				(if addToPics
					(addToPics doit:)
				)
				(if (Btst fAteFaeryShrooms)
					(ego
						view: 536
						setLoop: 0
						setCel: 255
						posn: (ego x?) (+ (ego y?) 10)
						;setCycle: EndLoop	;lets ego complete collapsing animation
					)
					(= ticks 5)
				else
					(messager say: N_EGO V_MUSHROOM C_AFTERSHROOMS 3 0 EGOSEZ)
					(Bset fAteFaeryShrooms)
					(HandsOn)
					(self dispose:)
					(DisposeScript EGOSEZ)
				)
			)
			(7
				(EgoDead C_DIE_MUSHROOMS C_DIE_MUSHROOMS_TITLE 0 0 800)
			)
		)
	)
)

(instance cueIt of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= ticks 20)
			)
			(1
				(switch useCue
					(useRations
						(ego use: iRations)
					)
					(useHealing
						(ego use: iHealingPotion)
						(ego get: iFlask)
					)
					(useMana
						(ego use: iManaPotion)
						(ego get: iFlask)
					)
					(useVigor
						(ego use: iStaminaPotion)
						(ego get: iFlask)
					)
					(useDisenchant
						(ego use: iDisenchant)
						(ego get: iFlask)
					)
					(useGhostOil
						(ego use: iGhostOil)
						(ego get: iFlask)
					)
					(useFruit
						(ego use: iFruit 3)
					)
					(useVegetables
						(ego use: iVegetables 2)
					)
					(useWater
						(ego use: iFlyingWater 1)
						(ego get: iFlask 1)
					)
				)
				(self cue:)
			)
			(2
				(self dispose:)
			)
		)
	)
)
