;;; Sierra Script 1.0 - (do not remove this comment)
(script# GLORY_EGO)
(include game.sh) (include "28.shm")
(use Main)
(use EgoDead)
(use PolyPath)
(use StopWalk)
(use Grooper)
(use Ego)
(use Sound)
(use Motion)
(use System)

(public
	GloryEgo 0
	stopGroop 1
)

(procedure (PaladinCheck skillNum difficulty)
	(if (< skillNum difficulty)
		(if
			(>=
				(cond 
					((< (if paladinStat (- [egoStats HONOR] paladinStat)) 0) 0)
					(paladinStat (- [egoStats HONOR] paladinStat))
				)
				difficulty
			)
			(return TRUE)
		)
	)
	(if (>= skillNum difficulty)
		(if
			(<
				(cond 
					((< (if paladinStat (- [egoStats HONOR] paladinStat)) 0) 0)
					(paladinStat (- [egoStats HONOR] paladinStat))
				)
				difficulty
			)
			(return -1)
		)
	)
	(return 0)
)

(procedure (statCheck statNum statMult)
	(self useSkill: statNum statMult)
	(return (* [egoStats statNum] statMult))
)

(class GloryEgo of Ego
	(properties
		name {hero}
		noun N_HERO
		modNum GLORY_EGO
	)
	
	(method (handleEvent event &tmp dir eType eMsg temp3 retVal)
		(= retVal 0)
		(= eType (event type?))
		(= eMsg (event message?))
		(cond 
			((and script (script handleEvent: event)) 1)
			((& eType direction)
				(if
					(and
						(== (= dir eMsg) 0)
						(& eType keyDown)
					)
					(return (event claimed?))
				)
				(if
					(and
						(& eType keyDown)
						(== dir (user prevDir?))
						(IsObject mover)
					)
					(= dir 0)
				)
				(user prevDir: dir)
				(self setDirection: dir)
				(= retVal 1)
			)
			((& eType userEvent)
				(if (& eType walkEvent)
					(if (and (self mover?) (OneOf curRoomNum 150 160 170 180))
						(= temp3 ((self mover?) caller?))
					else
						(= temp3 0)
					)
					(switch useObstacles
						(FALSE
							(self setMotion: MoveTo (event x?) (+ (event y?) z) temp3)
						)
						(TRUE
							(self setMotion: PolyPath (event x?) (+ (event y?) z) temp3)
						)
						(2
							(self setMotion: PolyPath (event x?) (+ (event y?) z) 0 0)
						)
					)
					(user prevDir: 0)
					(= retVal TRUE)
				else
					(= retVal (super handleEvent: event))
				)
			)
			(else
				(= retVal (super handleEvent: event))
			)
		)
		(return retVal)
	)
	
	(method (doVerb theVerb &tmp n v c m len str)
		(= n N_HERO)
		(= v theVerb)
		(= c NULL)
		(= m GLORY_EGO)
		(switch theVerb
			(V_HEALPILLS
				(if (< [egoStats HEALTH] (self maxHealth:))
					(if (Btst fPoisoned)
						(Bclr fPoisoned)
						(ego takeDamage: (- (/ (self maxHealth:) 3)))
						(Bset fPoisoned)
					else
						(ego takeDamage: (- (/ (self maxHealth:) 3)))
					)
					(if (> ((inventory at: iHealPills) amount?) 1)
						((inventory at: iHealPills)
							amount: (- ((inventory at: iHealPills) amount?) 1)
						)
					else
						((inventory at: iHealPills) amount: 0 owner: 0 realOwner: 0)
						(theIconBar curInvIcon: 0 disable: ICON_USEIT advanceCurIcon:)
					)
					(= c C_PILLED)
				else
					(= v NULL)
					(= c C_DONT_NEED_PILL)
				)
			)
			(V_MANAPILLS
				(if (< [egoStats MANA] (ego maxMana:))
					(ego useMana: (- (/ (ego maxMana:) 3)))
					(if (> ((inventory at: iManaPills) amount?) 1)
						((inventory at: iManaPills)
							amount: (- ((inventory at: 13) amount?) 1)
						)
					else
						((inventory at: iManaPills) amount: 0 owner: 0 realOwner: 0)
						(theIconBar curInvIcon: 0 disable: ICON_USEIT advanceCurIcon:)
					)
					(= c C_PILLED)
				else
					(= v NULL)
					(= c C_DONT_NEED_PILL)
				)
			)
			(V_CUREPILLS
				(if (Btst fPoisoned)
					(Bclr fPoisoned)
					(if (> ((inventory at: iCurePills) amount?) 1)
						((inventory at: iCurePills)
							amount: (- ((inventory at: iCurePills) amount?) 1)
						)
					else
						((inventory at: iCurePills) amount: 0 owner: 0 realOwner: 0)
						(theIconBar curInvIcon: 0 disable: ICON_USEIT advanceCurIcon:)
					)
					(= c C_PILLED)
				else
					(= v NULL)
					(= c C_DONT_NEED_PILL)
				)
			)
			(V_RATIONS
				(= v 0)
				(if (< freeMeals 2)
					(if (> ((inventory at: iRations) amount?) 1)
						((inventory at: iRations)
							amount: (- ((inventory at: 14) amount?) 1)
						)
					else
						((inventory at: iRations) amount: 0 owner: 0 realOwner: 0)
						(theIconBar curInvIcon: 0 disable: ICON_USEIT advanceCurIcon:)
					)
					(cond 
						((Btst fStarving)
							(Bclr fStarving)
						)
						((Btst fHungry)
							(Bclr fHungry)
						)
						(else
							(++ freeMeals)
						)
					)
					(= c C_EAT_IT)
				else
					(= c C_TOO_FULL)
				)
			)
			(V_MEAT
				(= v 0)
				(if (< freeMeals 2)
					(if (> ((inventory at: iMeat) amount?) 1)
						((inventory at: iMeat)
							amount: (- ((inventory at: iMeat) amount?) 1)
						)
					else
						((inventory at: iMeat) amount: 0 owner: 0 realOwner: 0)
						(theIconBar curInvIcon: 0 disable: ICON_USEIT advanceCurIcon:)
					)
					(cond 
						((Btst fStarving)
							(Bclr fStarving)
						)
						((Btst fHungry)
							(Bclr fHungry)
						)
						(else
							(++ freeMeals)
						)
					)
					(= c C_EAT_IT)
				else
					(= c C_TOO_FULL)
				)
			)
			(V_FISH
				(= v 0)
				(if (< freeMeals 2)
					(if (> ((inventory at: iFish) amount?) 1)
						((inventory at: iFish)
							amount: (- ((inventory at: iFish) amount?) 1)
						)
					else
						((inventory at: iFish) amount: 0 owner: 0 realOwner: 0)
						(theIconBar curInvIcon: 0 disable: ICON_USEIT advanceCurIcon:)
					)
					(cond 
						((Btst fStarving)
							(Bclr fStarving)
						)
						((Btst fHungry)
							(Bclr fHungry)
						)
						(else
							(++ freeMeals)
						)
					)
					(= c C_EAT_IT)
				else
					(= c C_TOO_FULL)
				)
			)
			(V_FRUIT
				(= v 0)
				(if (< freeMeals 2)
					(if (> ((inventory at: iFruit) amount?) 1)
						((inventory at: iFruit)
							amount: (- ((inventory at: 19) amount?) 1)
						)
					else
						((inventory at: iFruit) amount: 0 owner: 0 realOwner: 0)
						(theIconBar curInvIcon: 0 disable: ICON_USEIT advanceCurIcon:)
					)
					(cond 
						((Btst fStarving)
							(Bclr fStarving)
						)
						((Btst fHungry)
							(Bclr fHungry)
						)
						(else
							(++ freeMeals)
						)
					)
					(= c C_EAT_IT)
				else
					(= c C_TOO_FULL)
				)
			)
			(V_VINEFRUIT
				(if (> ((inventory at: iVineFruit) amount?) 1)
					((inventory at: iVineFruit)
						amount: (- ((inventory at: iVineFruit) amount?) 1)
					)
				else
					((inventory at: iVineFruit) amount: 0 owner: 0 realOwner: 0)
					(theIconBar curInvIcon: 0 disable: ICON_USEIT advanceCurIcon:)
				)
				(= v 0)
				(= c C_EAT_IT)
				(Bset fPoisoned)
			)
			(V_HONEY
				(= v 0)
				(if (< freeMeals 2)
					(if (> ((inventory at: iHoney) amount?) 1)
						((inventory at: iHoney)
							amount: (- ((inventory at: iHoney) amount?) 1)
						)
					else
						((inventory at: iHoney) amount: 0 owner: 0 realOwner: 0)
						(theIconBar curInvIcon: 0 disable: ICON_USEIT advanceCurIcon:)
					)
					(cond 
						((Btst fStarving)
							(Bclr fStarving)
						)
						((Btst fHungry)
							(Bclr fHungry)
						)
						(else
							(++ freeMeals)
						)
					)
					(= c C_EAT_IT)
				else
					(= c C_TOO_FULL)
				)
			)
			(V_PEACEWATER
				(= [egoStats STAMINA] (ego maxStamina:))
				(ego drop: iPeaceWater 1 get: iWaterskin)
			)
			(V_LOOK
				(= len
					(Memory MNeedPtr
						(+ (Message MsgSize GLORY_EGO N_HERO V_LOOK NULL 1) 40)
					)
				)
				(= str (Message MsgGet GLORY_EGO N_HERO V_LOOK NULL 1 len))
				(messager sayFormat: str len @userName)
				(Memory MDisposePtr len)
				(return TRUE)
			)
			(V_HEAL
				(if (< [egoStats HEALTH] (ego maxHealth:))
					(= c C_HEALED)
					(= str
						(Min
							(- (ego maxHealth:) [egoStats HEALTH])
							(/ [egoStats STAMINA] 2)
						)
					)
					(= [egoStats HEALTH] (+ [egoStats HEALTH] str))
					(= [egoStats STAMINA] (- [egoStats STAMINA] str))
				else
					(= c C_FULL_HEALTH)
				)
			)
			(else 
				(cond 
					((>= theVerb V_OPEN)
						(= v NULL)
						(= c C_MAGIC_ON_ME)
					)
					((>= theVerb V_ROYALS)
						(= v NULL)
						(= c C_ITEM_ON_ME)
					)
				)
			)
		)
		(return (messager say: n v c 0 0 m))
	)
	
	(method (facingMe)
		(return TRUE)
	)
	
	(method (motionCue)
		(if (and (IsObject mover) (mover completed?))
			(mover motionCue:)
		)
		(if (and (IsObject cycler) (cycler completed?))
			(cycler motionCue:)
		)
	)
	
	(method (setMotion mType)
		(if (and mType (== (ego view?) 20)) (ego normalize:))
		(super setMotion: mType &rest)
	)
	
	(method (get what howMany &tmp obj num oldNum maxW)
		(= num (if (== argc 1) 1 else howMany))
		(= oldNum ((inventory at: what) amount?))
		(cond 
			((and (> curRoomNum 100) (== what 0))
				(if
					(and
						(== ((inventory at: what) message?) V_DINARS)
						(== oldNum 0)
					)
					((inventory at: what) message: V_ROYALS)
				)
			)
			((== what 0)
				(= num 0)
			)
		)
		(cond 
			(
				(>
					(= totalEncumbrance
						(-
							(= maxW
								(+
									(self wtCarried:)
									(* num ((inventory at: what) value?))
								)
							)
							(self maxLoad:)
						)
					)
					0
				)
				(if (not (Btst fOverloaded))
					(messager say: N_EGO_CUE V_DOIT C_OVERENCUMBERED 1 0 GLORY_EGO)
					(Bset fOverloaded)
					(self setStep: 1 1)
				)
			)
			((Btst fOverloaded)
				(Bclr fOverloaded)
				(self changeGait: -1 FALSE)
			)
		)
		(if (< (= num (+ num oldNum)) 0)
			(= num 0)
		)
		(if (and (!= what 0) (== num 0))
			((inventory at: what)
				amount: num
				realOwner: (if (OneOf curRoomNum 310 430) 0 else curRoom)
			)
			(theIconBar
				curInvIcon: 0
				disable: ICON_USEIT
				curIcon: (theIconBar at: ICON_WALK)
			)
		else
			((inventory at: what) amount: num realOwner: self)
		)
		(return (- num oldNum))
	)
	
	(method (has what)
		(return
			(if (== what 0)
				(return TRUE)
			else
				(return ((inventory at: what) amount?))
			)
		)
	)
	
	(method (use what howMany &tmp obj num oldNum)
		(if
			(>
				(= num (if (== argc 1) 1 else howMany))
				((inventory at: what) amount?)
			)
			(= num ((inventory at: what) amount?))
		)
		(self get: what (- num))
		(if (< ((inventory at: what) amount?) 1)
			(theIconBar curInvIcon: 0 advanceCurIcon:)
		)
		(return num)
	)
	
	(method (drop what howMany)
		(self use:
			what
			(if (== argc 2)
				howMany
			else
				((inventory at: what) amount?)
			)
		)
	)
	
	(method (learn what howWell &tmp obj num)
		(= num (if (== argc 1) 5 else howWell))
		(if (and [egoStats MAGIC] (> num [egoStats what]))
			(= [egoStats what] num)
		)
		(return [egoStats what])
	)
	
	(method (canPickLocks)
		(if [egoStats PICK] (self has: iToolkit))
	)
	
	(method (useSkill skillNum learnValue &tmp learnSign)
		(asm
			lap      skillNum
			lagi     egoStats
			not     
			bnt      code_0c76
			ldi      0
			ret     
code_0c76:
			lsp      skillNum
			ldi      3
			eq?     
			bnt      code_0c88
			pushi    1
			pushi    115
			callb    Btst,  2
			bnt      code_0c88
			ldi      0
			ret     
code_0c88:
			lsp      skillNum
			ldi      10
			eq?     
			bnt      code_0c96
			pushi    #useStamina
			pushi    1
			pushi    1
			self     6
code_0c96:
			lsp      learnValue
			ldi      0
			ge?     
			bnt      code_0ca1
			ldi      1
			jmp      code_0ca3
code_0ca1:
			ldi      65535
code_0ca3:
			sat      learnSign
			pushi    1
			lsp      learnValue
			callk    Abs,  2
			push    
			lap      skillNum
			lagi     egoStats
			gt?     
			bnt      code_0cbc
			lap      skillNum
			lsgi     egoStats
			lat      learnSign
			mul     
			sap      learnValue
code_0cbc:
			ldi      15
			lsgi     egoStats
			pushi    1
			lsp      learnValue
			callk    Abs,  2
			push    
			ldi      19
			add     
			push    
			ldi      20
			div     
			add     
			push    
			ldi      15
			sagi     egoStats
			lap      skillNum
			lsgi     skillTicks
			lap      learnValue
			add     
			push    
			lap      skillNum
			sagi     skillTicks
			lap      skillNum
			lsgi     skillTicks
			lagi     egoStats
			ge?     
			bnt      code_0d1e
			lap      skillNum
			lsgi     skillTicks
			lagi     egoStats
			sub     
			push    
			lap      skillNum
			sagi     skillTicks
			lap      skillNum
			lsgi     egoStats
			pushi    2
			pushi    2
			pushi    4
			callk    Random,  4
			add     
			push    
			lap      skillNum
			sagi     egoStats
			push    
			ldi      300
			gt?     
			bnt      code_0d59
			pushi    300
			lap      skillNum
			sagi     egoStats
			jmp      code_0d59
code_0d1e:
			lap      skillNum
			lsgi     skillTicks
			ldi      0
			lt?     
			bnt      code_0d56
			lap      skillNum
			lsgi     skillTicks
			lagi     egoStats
			add     
			push    
			lap      skillNum
			sagi     skillTicks
			lap      skillNum
			lsgi     egoStats
			pushi    2
			pushi    2
			pushi    4
			callk    Random,  4
			sub     
			push    
			lap      skillNum
			sagi     egoStats
			push    
			ldi      5
			lt?     
			bnt      code_0d59
			pushi    5
			lap      skillNum
			sagi     egoStats
			jmp      code_0d59
code_0d56:
			ldi      0
			ret     
code_0d59:
			ret     
		)
	)
	
	(method (useMana pointsUsed)
		(if [egoStats MAGIC]
			(if (< (= [egoStats MANA] (- [egoStats MANA] pointsUsed)) 0)
				(= [egoStats MANA] 0)
			)
			(if (> [egoStats MANA] (self maxMana:))
				(= [egoStats MANA] (self maxMana:))
			)
		)
		(return [egoStats MANA])
	)
	
	(method (useStamina pointsUsed &tmp foo noStamina)
		(if (< argc 2)
			(= noStamina TRUE)
		else
			(= noStamina FALSE)
		)
		(if (> pointsUsed 0)
			(self useSkill: VIT (>> (+ pointsUsed 4) $0002))
		)
		(cond 
			(
				(>
					(= foo (= [egoStats STAMINA] (- [egoStats STAMINA] pointsUsed)))
					4
				)
				(Bclr fWornOut)
				(if (> foo (ego maxStamina:))
					(= [egoStats STAMINA] (ego maxStamina:))
				)
			)
			((>= foo 0))
			((self takeDamage: (>> (- 2 [egoStats STAMINA]) $0002))
				(= [egoStats STAMINA] 0)
				(if (not (Btst fWornOut))
					(Bset fWornOut)
					(if (and (!= curRoomNum 550) noStamina)
						(messager say: N_EGO_CUE V_DOIT C_NO_STAMINA 1 0 GLORY_EGO)
					)
				)
			)
			((> [egoStats HEALTH] 0))
			(noStamina
				(EgoDead C_DEATH_OVERWORK GLORY_EGO)
			)
		)
		(return [egoStats HEALTH])
	)
	
	(method (addHonor howMuch &tmp num gainedASkill [temp2 20])
		(= num
			(cond 
				(
					(<
						(if paladinStat
							(- [egoStats 14] paladinStat)
						)
						0
					)
					0
				)
				(paladinStat (- [egoStats HONOR] paladinStat))
			)
		)
		(self useSkill: HONOR (* howMuch 3))
		(cond 
			((= gainedASkill (PaladinCheck num 10))
				(if (== gainedASkill 1)
					((inventory at: iSword) state: 1)
					(ego solvePuzzle: fLearnFlamingSword 2)
					(messager say: N_EGO_CUE V_DOIT C_GAIN_FLAME_SWORD 0 0 GLORY_EGO)
				else
					((inventory at: iSword) state: 0)
					(messager say: N_EGO_CUE V_DOIT C_LOSE_FLAME_SWORD 0 0 GLORY_EGO)
				)
			)
			((= gainedASkill (PaladinCheck num 25))
				(if (== gainedASkill 1)
					;paladin automatically gains magic if he doesn't have it
					(if (not [egoStats MAGIC])
						(= [egoStats MAGIC] 5)
					)
					(= [egoStats HEALING] 10)
					(ego solvePuzzle: fLearnHealing 2)
					(messager say: N_EGO_CUE V_HEAL C_GAIN_HEAL 0 0 GLORY_EGO)
				else
					(= [egoStats HEALING] 0)
					(messager say: N_EGO_CUE V_HEAL C_LOSE_HEAL 0 0 GLORY_EGO)
				)
			)
			((= gainedASkill (PaladinCheck num 50))
				(if (== gainedASkill 1)
					(Bset fSenseDanger)
					(ego solvePuzzle: fLearnSenseDanger 2)
					(messager say: N_EGO_CUE V_DOIT C_GAIN_SENSE 0 0 GLORY_EGO)
				else
					(Bclr fSenseDanger)
					(messager say: N_EGO_CUE V_DOIT C_LOSE_SENSE 0 0 GLORY_EGO)
				)
			)
			((= gainedASkill (PaladinCheck num 80))
				(if (== gainedASkill 1)
					(Bset fHonorShield)
					(ego solvePuzzle: fLearnHonorShield 2)
					(messager say: N_EGO_CUE V_DOIT 18 0 0 GLORY_EGO)
				else
					(Bclr fHonorShield)
					(messager say: N_EGO_CUE V_DOIT 16 0 0 GLORY_EGO)
				)
			)
		)
	)
	
	(method (trySkill skillNum difficulty bonus &tmp skVal skDiv skRef success)
		(if (and (> (ego view?) 17) (< (ego view?) 21))
			(if
				(and
					(< (ego view?) 21)
					(OneOf skillNum
						OPEN DETMAGIC TRIGGER DAZZLE ZAP
						CALM FLAMEDART FETCH FORCEBOLT LEVITATE
						REVERSAL JUGGLE STAFF LIGHTNING HEALING
					)
				)
				(return TRUE)
			)
		)
		(= success TRUE)
		(if (= skVal [egoStats skillNum])
			(if (== argc 3) (+= skVal bonus))
			(if difficulty
				(if (and (< skillNum COMM) (!= skillNum INT) (!= skillNum LUCK))
					(self useStamina: (/ (+ difficulty 39) 40))
				)
			else
				(if (and (>= skillNum WEAPON) (< skillNum COMM))
					(self useStamina: (Random 2 8))
				)
				(= difficulty (Rand300))
			)
			(if (>= (statCheck LUCK 1) (Random 1 500))
				(+= skVal (Random 1 20))
			)
			(if (<= difficulty skVal)
				(= success TRUE)
			else
				(= success -1)
			)
			(if (= skDiv 2)
				(cond 
					((== skillNum WEAPON)
						(self useSkill: STR 3)
						(self useSkill: AGIL 2)
					)
					((or (== skillNum PARRY) (== skillNum DODGE) (== skillNum STEALTH))
						(self useSkill: AGIL 4)
						(self useSkill: INT 2)
					)
					((== skillNum PICK)
						(self useSkill: AGIL 8)
						(self useSkill: INT 5)
					)
					((or (== skillNum THROW) (== skillNum CLIMB))
						(self useSkill: STR 5)
						(self useSkill: AGIL 4)
					)
					((== skillNum COMM)
						(self useSkill: INT 6)
					)
					((>= skillNum OPEN)
						(self useSkill: MAGIC 8)
						(self useSkill: INT 4)
					)
				)
				(= skRef 100)
				(if (or (< skillNum COMM) (== skillNum WEAPON))
					(= skRef 25)
				)
				(self useSkill: skillNum (Abs (/ skRef skDiv)))
			)
		else
			(= success FALSE)
		)
		(return success)
	)
	
	(method (solvePuzzle pFlag pValue charType &tmp ret rightType)
		(= ret 0)
		(if (>= argc 3)
			(cond 
				((and (& charType puzzleFIGHTER) (== heroType FIGHTER))
					(= rightType FIGHTER)
				)
				((and (& charType puzzleWIZARD) (== heroType MAGIC_USER))
					(= rightType MAGIC_USER)
				)
				((and (& charType puzzleTHIEF) (== heroType THIEF))
					(= rightType THIEF)
				)
				((and (& charType puzzlePALADIN) (== heroType PALADIN))
					(= rightType PALADIN)
				)
				(else
					(= rightType -1)
				)
			)
			(if (and (>= argc 3) (!= heroType rightType))
				(return FALSE)
			)
			(if
				(and
					(== rightType PALADIN)
					(!= heroType PALADIN)
					(not (Btst fCantBePaladin))
					(not (Btst pFlag))
				)
				(+= paladinPoints pValue)
			)
			(if (and (== heroType PALADIN) (== rightType origHeroType))
				(= charType PALADIN)
			)
		)
		(if (not (Btst pFlag))
			(Bset pFlag)
			(self useSkill: INT (* pValue 3))
			(if (> (+= score pValue) 500)
				(= score 500)
			)
			(puzzleSound play:)
			(= ret TRUE)
		)
		(return ret)
	)
	
	(method (takeDamage damage)
		(self useSkill: VIT (Abs damage))
		(if
			(<
				(= [egoStats HEALTH]
					(-
						[egoStats HEALTH]
						(if (Btst fPoisoned)
							(Abs damage)
						else
							damage
						)
					)
				)
				0
			)
			(= [egoStats HEALTH] 0)
		)
		(if (> [egoStats HEALTH] (self maxHealth:))
			(= [egoStats HEALTH] (self maxHealth:))
		)
		(return (> [egoStats HEALTH] 0))
	)
	
	(method (castSpell spellNum spellObj &tmp retVal)
		(cond 
			((< argc 2)
				(if (and (> (ego view?) 17) (< (ego view?) 21))
					(if (and (< (ego view?) 21) (!= curRoomNum 550))
						(return TRUE)
					)
				)
			)
			((and spellObj (!= curRoomNum 550))
				(return TRUE)
			)
		)
		(return
			(if (< [egoStats MANA] [spellCost (- spellNum OPEN)])
				(messager say: N_EGO_CUE V_DOIT C_NO_MANA 1 0 GLORY_EGO)
				(return 0)
			else
				(return
					(self
						useMana: [spellCost (- spellNum OPEN)]
						trySkill: spellNum 0
					)
				)
			)
		)
	)
	
	(method (maxMana &tmp egoMagic)
		(return
			(if (= egoMagic [egoStats MAGIC])
				(/ (+ [egoStats INT] egoMagic egoMagic) 3)
			else
				0
			)
		)
	)
	
	(method (maxStamina)
		(return (/ (+ [egoStats AGIL] [egoStats VIT]) 2))
	)
	
	(method (maxHealth)
		(return (/ (+ [egoStats STR] [egoStats VIT] [egoStats VIT]) 3))
	)
	
	(method (maxLoad)
		(return (+ 2400 (* [egoStats STR] 30)))
	)
	
	(method (eatMeal &tmp haveEaten)
		(= haveEaten 0)
		(cond 
			(freeMeals
				(-- freeMeals)
				(= haveEaten TRUE)
			)
			(((inventory at: iRations) amount?)
				((inventory at: iRations)
					amount: (- ((inventory at: iRations) amount?) 1)
				)
				(if (not ((inventory at: iRations) amount?))
					((inventory at: iRations) owner: 0 realOwner: 0)
				)
				(= haveEaten TRUE)
			)
			(((inventory at: iMeat) amount?)
				(= haveEaten TRUE)
				((inventory at: iMeat)
					amount: (- ((inventory at: iMeat) amount?) 1)
				)
				(if (not ((inventory at: iMeat) amount?))
					((inventory at: iMeat) owner: 0 realOwner: 0)
				)
			)
			(((inventory at: iFish) amount?)
				(= haveEaten TRUE)
				((inventory at: iFish)
					amount: (- ((inventory at: iFish) amount?) 1)
				)
				(if (not ((inventory at: iFish) amount?))
					((inventory at: iFish) owner: 0 realOwner: 0)
				)
			)
			(((inventory at: iFruit) amount?)
				(= haveEaten TRUE)
				((inventory at: iFruit)
					amount: (- ((inventory at: iFruit) amount?) 1)
				)
				(if (not ((inventory at: iFruit) amount?))
					((inventory at: iFruit) owner: 0 realOwner: 0)
				)
			)
			((Btst fStarving)
				(if (self useStamina: 8 0)
					(messager say: N_EGO_CUE V_DOIT C_STARVING 1 0 GLORY_EGO)
				else
					(EgoDead C_DEATH_STARVE GLORY_EGO)
				)
			)
			((Btst fHungry)
				(Bset fStarving)
				(messager say: N_EGO_CUE V_DOIT C_HUNGRY 1 0 GLORY_EGO))
			(else
				(Bset fHungry)
				(messager say: N_EGO_CUE V_DOIT C_NO_RATIONS 1 0 GLORY_EGO)
			)
		)
		(if haveEaten
			(cond 
				((Btst fStarving)
					(Bclr fStarving)
				)
				((Btst fHungry)
					(Bclr fHungry)
				)
			)
		)
	)
	
	(method (wtCarried &tmp tot index)
		(= tot 0)
		(for ((= index 1)) (< index iLastInvItem) ((++ index))
			(= tot
				(+
					tot
					(*
						((inventory at: index) amount?)
						((inventory at: index) value?)
					)
				)
			)
		)
		(= tot
			(+ (= tot (+ tot (* commons 2))) (* numDinars 2))
		)
	)
	
	(method (changeGait newGait gaitMsg &tmp theView saveMover)
		(= saveMover 0)
		(if (and gaitMsg (== egoGait newGait))
			(return)
		)
		(if mover
			(= saveMover mover)
			(= mover 0)
		)
		(if (!= newGait -1)
			(= egoGait newGait)
		)
		(if (or (< view 4) (== view 5))
			(switch egoGait
				(MOVE_RUN
					(self
						view: 1
						origStep: 2053
						setStep:
						setCycle: StopWalk 5
					)
				)
				(MOVE_SNEAK
					(self
						view: 2
						origStep: 1026
						setStep:
						setCycle: StopWalk 2
					)
				)
				(else 
					(self
						view: 0
						origStep: 1027
						setStep:
						setCycle: StopWalk 5
					)
				)
			)
		)
		(cond 
			((Btst fOverloaded)
				(self setStep: 1 1 0)
			)
			(
				(and
					(not (OneOf curRoomNum 150 160 170 180))
					(or (== xStep 1) (== yStep 1))
				)
				(if (== view 1)
					(self setStep: 8 4 0)
				else
					(self setStep: 4 2 0)
				)
			)
		)
		(if saveMover
			(self mover: saveMover)
			((self mover?) init:)
		)
	)
	
	(method (normalize theLoop)
		(if argc
			(self loop: theLoop)
		)
		(self
			view: 0
			z: 0
			setPri: -1
			setLoop: -1
			setLoop: stopGroop
			setCycle: StopWalk 5
			setMotion: 0
			changeGait: -1 FALSE
			illegalBits: cWHITE
			ignoreHorizon:
			setSpeed: speed
		)
	)
)

(instance stopGroop of GradualLooper
	
	(method (doit)
		(if
			(and
				(IsObject (ego cycler?))
				((ego cycler?) isKindOf: StopWalk)
			)
			(ego view: ((ego cycler?) vWalking?))
		)
		(super doit: &rest)
	)
	
	(method (cue &tmp whoCares)
		(cond 
			((not (IsObject (client mover?)))
				(client mover: oldMover)
			)
			((IsObject oldMover)
				(oldMover dispose:)
			)
		)
		(if (IsObject oldCycler)
			(if (IsObject (client cycler?))
				(oldCycler dispose:)
			else
				(client cycler: oldCycler)
			)
		)
		(= whoCares caller)
		(= caller
			(= oldMover
				(= oldCycler 0)
			)
		)
		(if (IsObject whoCares)
			(whoCares cue: &rest)
		)
	)
)

(instance puzzleSound of Sound
	(properties
		number 950
	)
)
