;;; Sierra Script 1.0 - (do not remove this comment)
(script# GLORY_EGO)
(include game.sh) (include "28.shm")
(use Main)
(use EgoDead)
(use String)
(use Print)
(use IconBar)
(use PolyPath)
(use StopWalk)
(use Cursor)
(use Grooper)
(use Actor)
(use System)

(public
	GloryEgo 0
	stopGroop 1
)

(local
	local0
)
(procedure (PaladinCheck skillNum difficulty)
	(if (< skillNum difficulty)
		(if
			(>=
				(if paladinStat
					(- (+ paladinStat [egoStats HONOR]) paladinPoints)
				else
					0
				)
				difficulty
			)
			(return 1)
		)
	)
	(if (>= skillNum difficulty)
		(if
			(<
				(if paladinStat
					(- (+ paladinStat [egoStats 14]) paladinPoints)
				else
					0
				)
				difficulty
			)
			(return -1)
		)
	)
	(return FALSE)
)

(procedure (statCheck skillNum statMult)
	(self useSkill: skillNum statMult)
	(return (* [egoStats skillNum] statMult))
)

(class GloryEgo of Actor
	(properties
		name {hero}
		noun N_HERO
		modNum GLORY_EGO
		sightAngle 90
	)
	
	(method (handleEvent event &tmp temp0 eType eMsg)
		(= eType (event type?))
		(= eMsg (event message?))
		(cond 
			((and script (script handleEvent: event)) 1)
			((& eType userEvent)
				(cond 
					((& eType walkEvent)
						(cond 
							(
								(and
									(curRoom exitList?)
									((curRoom exitList?)
										firstTrue: #onMe (event x?) (event y?)
									)
								)
								(return TRUE)
							)
							((and (curRoom east?) (>= (event x?) 313))
								(if (!= (curRoom rightY?) -1)
									(self
										setMotion: ((ScriptID 17) new:) 313 (curRoom rightY?)
									)
								else
									(self setMotion: ((ScriptID 17) new:) 313 (event y?))
								)
							)
							((and (curRoom west?) (<= (event x?) 7))
								(if (!= (curRoom leftY?) -1)
									(self
										setMotion: ((ScriptID 17) new:) 7 (curRoom leftY?)
									)
								else
									(self setMotion: ((ScriptID 17) new:) 7 (event y?))
								)
							)
							(
								(and
									(curRoom north?)
									(<= (event y?) (curRoom horizon?))
								)
								(if (!= (curRoom topX?) -1)
									(self
										setMotion: ((ScriptID 17) new:) (curRoom topX?) (curRoom horizon?)
									)
								else
									(self
										setMotion: ((ScriptID 17) new:) (event x?) (curRoom horizon?)
									)
								)
							)
							((and (curRoom south?) (>= (event y?) 182))
								(if (!= (curRoom bottomX?) -1)
									(self
										setMotion: ((ScriptID 17) new:) (curRoom bottomX?) 182
									)
								else
									(self setMotion: ((ScriptID 17) new:) (event x?) 182)
								)
							)
							(else (self setMotion: PolyPath (event x?) (+ (event y?) z)))
						)
						(user prevDir: dirS)
						(event claimed: TRUE)
					)
					((Btst 356) 0)
					(else
						(super handleEvent: event)
					)
				)
			)
			(else
				(super handleEvent: event)
			)
		)
		(return (event claimed?))
	)
	
	(method (doVerb theVerb &tmp n v c m str str2 temp6)
		(= n N_HERO)
		(= v theVerb)
		(= c 0)
		(= m GLORY_EGO)
		(return
			(if (== theVerb V_JUMP)
				(if (not (curRoom doVerb: V_JUMP))
					(theGame pragmaFail:)
				)
			else
				(switch theVerb
					(V_HEAL_POTION
						(if (< [egoStats HEALTH] (self maxHealth:))
							(if (Btst fPoisoned)
								(Bclr fPoisoned)
								(ego takeDamage: (- (/ (self maxHealth:) 3)))
								(Bset fPoisoned)
							else
								(ego takeDamage: (- (/ (self maxHealth:) 3)))
							)
							(ego drop: iHealingPotion 1 get: iFlask)
							((ScriptID 0 21) doit: curRoomNum)
							(= c C_PILLED)
						else
							(= v 0)
							(= c C_DONT_NEED_PILL)
						)
					)
					(V_MANA_FRUIT
						(if (< [egoStats MANA] (ego maxMana:))
							(ego useMana: (- (/ (ego maxMana:) 3)) drop: iManaFruit 1)
							(= c 9)
							((ScriptID 0 21) doit: curRoomNum)
						else
							(= v 0)
							(= c C_DONT_NEED_PILL)
						)
					)
					(V_CURE_POTION
						(if (Btst fPoisoned)
							(Bclr fPoisoned)
							(= poisonLevel 0)
							(ego drop: iPoisonCure 1 get: iFlask)
							(= c C_PILLED)
							((ScriptID 0 21) doit: curRoomNum)
						else
							(= v 0)
							(= c C_DONT_NEED_PILL)
						)
					)
					(V_DO
						(if (== curRoomNum 520)
							(= c C_AT_LAKE)
						else
							(= c 0)
						)
					)
					(V_RATIONS
						(= v 0)
						(if (< freeMeals 2)
							(ego drop: iRations 1)
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
					(V_LOOK
						(= str (String new:))
						(Message MsgGet GLORY_EGO N_HERO V_LOOK NULL 1 (str data?))
						(= str2 (String new:))
						(str2 format: {%s} userName)
						(str format: (str data?) str2)
						(Print addText: str init:)
						(Message MsgGet GLORY_EGO N_HERO V_LOOK C_WEIGHT 1 (str data?))
						(str format: (str data?) (self wtCarried:) (self maxLoad:))
						(Print addText: str init:)
						(str dispose:)
						(str2 dispose:)
						(return 1)
					)
					(V_HEAL
						(if (< [egoStats HEALTH] (ego maxHealth:))
							(= c C_HEALED)
							(= str2
								(Min
									(- (ego maxHealth:) [egoStats HEALTH])
									(/ [egoStats STAMINA] 2)
								)
							)
							(+= [egoStats HEALTH] str2)
							(-= [egoStats STAMINA] str2)
							((ScriptID 0 21) doit: curRoomNum)
						else
							(= c C_FULL_HEALTH)
						)
					)
					(V_CLOTH
						(if
							(and
								(== curRoomNum 710)
								(> (ego x?) 190)
								(< (ego y?) 125)
							)
							(self setScript: (ScriptID 710 1))
							(return TRUE)
						)
					)
					(else 
						(if (and (>= theVerb V_FROSTBITE) (<= theVerb 98))
							(= v 0)
							(= c C_MAGIC_ON_ME)
						)
					)
				)
				(if (Message MsgSize m n v c 1)
					(messager say: n v c 1 0 m)
				else
					(messager say: n NULL C_DOESNT_DO_ANYTHING 1 0 m)
				)
			)
		)
	)
	
	(method (facingMe)
		(return TRUE)
	)
	
	(method (motionCue)
		(if (and mover (mover completed?))
			(mover motionCue:)
		)
		(if (and cycler (cycler completed?))
			(cycler motionCue:)
		)
	)
	
	(method (setMotion mType)
		(if mType
			(cond 
				((== (ego view?) 20)
					(ego normalize:)
				)
				((not (ego isNotHidden:))
					(ego show:)
				)
			)
		)
		(super setMotion: mType &rest)
	)
	
	(method (get what howMany &tmp obj num oldNum maxW invI keyMask curObj)
		(= num (if (== argc 1) 1 else howMany))
		(cond 
			;these are not actual inventory items, so they need special handling
			((OneOf what iLabKey iInnKey iCryptKey iGuildKey iCrestKey iBurgoKey)
				(= keyMask
					(switch what
						(iLabKey KEY_LAB)
						(iInnKey KEY_INN)
						(iCryptKey KEY_CRYPT)
						(iGuildKey KEY_GUILD)
						(iCrestKey KEY_CREST)
						(iBurgoKey KEY_BURGO)
					)
				)
				((inventory at: iKeyRing)
					maskCel: (| ((inventory at: iKeyRing) maskCel?) keyMask)
				)
				(= what iKeyRing)
			)
			((== what iSword)
				(switch ((= obj (inventory at: iSword)) state?)
					(swordBATTERED
						(obj loop: 0)
						(obj cel: 12)
					)
					(swordFINE
						(obj loop: 0)
						(obj cel: 0)
					)
					(swordAXE
						(obj loop: 0)
						(obj cel: 15)
					)
				)
			)
			((== what iArmor)
				(if ((= obj (inventory at: iArmor)) state?)
					(obj cel: 13)
				else
					(obj cel: 2)
				)
			)
		)
		(= oldNum ((inventory at: what) amount?))
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
					(if (Btst fInMainGame)
						(messager say: N_EGO_CUE V_DOIT C_OVERENCUMBERED 1 0 GLORY_EGO)
					)
					(Bset fOverloaded)
					(self setStep: 1 1)
				)
			)
			((Btst fOverloaded)
				(Bclr fOverloaded)
				(self changeGait: -1 FALSE)
			)
		)
		(if (< (+= num oldNum) 0)
			(= num 0)
		)
		(if (and (!= what iPurse) (== num 0))
			((inventory at: what)
				amount: num
				owner: (if (OneOf curRoomNum 330) 0 else curRoom)
			)
			(theIconBar
				curInvIcon: 0
				disable: ICON_USEIT
				curIcon: (theIconBar at: 0)
			)
		else
			((= curObj (inventory at: what))
				amount: num
				owner: self
			)
			(theIconBar
				curInvIcon: curObj
				enableIcon: (theIconBar useIconItem?)
				curIcon: (theIconBar useIconItem?)
			)
			(Cursor
				view: (curObj view?)
				loop: (curObj loop?)
				cel: (curObj cel?)
			)
			(= oldCurs (theIconBar at: ICON_USEIT))
			(oldCurs
				cursorView: (curObj view?)
				cursorLoop: (curObj loop?)
				cursorCel: (curObj cel?)
			)
			(= invI (ScriptID 36 1))
			(invI
				view: (curObj view?)
				loop: (curObj loop?)
				cel: (curObj cel?)
				show:
			)
			(UpdateScreenItem invI)
			(theGame
				setCursor:
					(IconBarCursor
						view: (curObj view?)
						loop: (curObj loop?)
						cel: (curObj cel?)
						yourself:
					)
			)
		)
		(return (- num oldNum))
	)
	
	(method (has what &tmp keyMask)
		(return
			(cond 
				((OneOf what iLabKey iInnKey iCryptKey iGuildKey iCrestKey iBurgoKey)
					(= keyMask
						(switch what
							(iLabKey KEY_LAB)
							(iInnKey KEY_INN)
							(iCryptKey KEY_CRYPT)
							(iGuildKey KEY_GUILD)
							(iCrestKey KEY_CREST)
							(iBurgoKey KEY_BURGO)
						)
					)
					(if (& ((inventory at: iKeyRing) maskCel?) keyMask)
						(return ((inventory at: iKeyRing) amount?))
					else
						(return FALSE)
					)
				)
				((== what iPurse)
					(return TRUE)
				)
				(else
					(return ((inventory at: what) amount?))
				)
			)
		)
	)
	
	(method (use what howMany &tmp obj num)
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
			(= obj (ScriptID 36 1))
			(obj signal: (| (obj signal?) $0008))
			(DeleteScreenItem obj)
		)
		(return num)
	)
	
	(method (drop what howMany &tmp obj keyMask)
		(if (OneOf what iLabKey iInnKey iCryptKey iGuildKey iCrestKey iBurgoKey)
			(= keyMask
				(switch what
					(iLabKey KEY_LAB)
					(iInnKey KEY_INN)
					(iCryptKey KEY_CRYPT)
					(iGuildKey KEY_GUILD)
					(iCrestKey KEY_CREST)
					(iBurgoKey KEY_BURGO)
				)
			)
			(if (= obj ((inventory at: iKeyRing) maskCel?))
				((inventory at: iKeyRing) maskCel: (& obj (~ keyMask)))
			else
				(return FALSE)
			)
			(if ((inventory at: iKeyRing) maskCel?)
				(return FALSE)
			)
			(= what iKeyRing)
		)
		(return
			(self use: what
				(if (== argc 2)
					howMany
				else
					((inventory at: what) amount?)
				)
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
		;EO: This need some trickery to get decompiled
		(* learnValue 8)
		(if (not [egoStats skillNum])
			(return FALSE)
		)
		(if (and (== skillNum VIT) (Btst fPoisoned))
			(return FALSE)
		)
		(if (== skillNum THROW)
			(self useStamina: 1)
		)
		(= learnSign (if (>= learnValue 0) 1 else -1))
		(if (> (Abs learnValue) [egoStats skillNum])
			(= learnValue (* [egoStats skillNum] learnSign))
		)
		(+= [egoStats EXPER] (/ (+ (Abs learnValue) 19) 20))
		;CLIMB gets special treatment, presumably due to being underused in QFG3
		(if (== skillNum CLIMB)
			(+= [skillTicks skillNum] learnValue)
		)
		(+= [skillTicks skillNum] learnValue)
		;these did not decompile properly; I have attempted to fix this code
		(cond
			;these did not decompile properly; I have attempted to fix this code
			((>= [skillTicks skillNum] [egoStats skillNum])	;
				(-= [skillTicks skillNum] [egoStats skillNum])
				(if (> (+= [egoStats skillNum] (Random 2 4)) 400)
					(= [egoStats skillNum] 400)	;stats max out at 400
				)
			)
			((< [skillTicks skillNum] 0)	;losing the skill
				(+= [skillTicks skillNum] [egoStats skillNum])
				(if (< (-= [egoStats skillNum] (Random 2 4)) 5)
					(= [egoStats skillNum] 5)	;can't go lower than 5
				)
			)
			(else	;no change yet
				(return FALSE)
			)
		)
	)
	
	(method (useMana pointsUsed)
		(if [egoStats MAGIC]
			(if (< (-= [egoStats MANA] pointsUsed) 0)
				(= [egoStats MANA] 0)
			)
			(if (> [egoStats MANA] (self maxMana:))
				(= [egoStats MANA] (self maxMana:))
			)
			((ScriptID 0 21) doit:)
		)
		(return [egoStats MANA])
	)
	
	(method (useStamina pointsUsed &tmp foo mess)
		(if (< argc 2)
			(= mess TRUE)
		else
			(= mess FALSE)
		)
		(if (> pointsUsed 0)
			(self useSkill: VIT (>> (+ pointsUsed 4) 2))
		)
		(cond 
			((> (= foo (-= [egoStats STAMINA] pointsUsed)) 4)
				(Bclr fWornOut)
				(if (> foo (ego maxStamina:))
					(= [egoStats STAMINA] (ego maxStamina:))
				)
			)
			((>= foo 0))
			((self takeDamage: (>> (- 2 [egoStats STAMINA]) 2))
				(= [egoStats STAMINA] 0)
				(if (not (Btst fWornOut))
					(Bset fWornOut)
					(if (and (!= curRoomNum 810) mess)
						(messager say: N_EGO_CUE V_DOIT C_NO_STAMINA 1 0 GLORY_EGO)
					)
				)
			)
			((> [egoStats HEALTH] 0))
			(mess
				(EgoDead C_DEATH_OVERWORK GLORY_EGO)
			)
		)
		((ScriptID 0 21) doit:)
		(return [egoStats HEALTH])
	)
	
	(method (addHonor howMuch &tmp num i obj)
		(/= howMuch 5)
		(= num
			(if paladinStat
				(- (+ paladinStat [egoStats HONOR]) paladinPoints)
			else
				0
			)
		)
		(if (< howMuch 0)	;lose honor
			(for ((= i howMuch)) (< i 0) ((++ i))
				(self useSkill: HONOR -15)
			)
		else	;gain honor
			(for ((= i 0)) (< i howMuch) ((++ i))
				(self useSkill: HONOR 15)
			)
		)
		(cond 
			((!= heroType PALADIN))
			((= i (PaladinCheck num 210))
				(if (== i 1)
					(Bset fLearnedFlamingSword)
					(if (== ((inventory at: iSword) state?) swordPALADIN)
						(messager say: N_EGO_CUE V_DOIT C_GAIN_FLAME_SWORD 0 0 GLORY_EGO)
						(SetCursor 905 0 14)
						((= obj (ScriptID 36 1)) loop: 0 cel: 14 show:)
						(UpdateScreenItem obj)
						((inventory at: iSword) cel: 14 mainCel: 14)
						((theIconBar at: ICON_USEIT) cursorLoop: 0 cursorCel: 14)
					else
						(messager say: N_EGO_CUE V_DOIT C_FLAME_NO_SWORD 0 0 GLORY_EGO)
					)
				else
					(Bclr fLearnedFlamingSword)
					(messager say: N_EGO_CUE V_DOIT C_LOSE_FLAME_SWORD 0 0 GLORY_EGO)
					(SetCursor 905 0 0)
					((= obj (ScriptID 36 1)) loop: 0 cel: 0 show:)
					(UpdateScreenItem obj)
					((inventory at: iSword) cel: 0 mainCel: 0)
					((theIconBar at: ICON_USEIT) cursorLoop: 0 cursorCel: 0)
				)
			)
			((= i (PaladinCheck num 25))
				(if (== i 1)
					(if (not [egoStats MAGIC])
						(= [egoStats MAGIC] 5)
					)
					(if (not [egoStats HEALING])
						(= [egoStats HEALING] 10)
						(messager say: N_EGO_CUE V_HEAL C_GAIN_HEAL 0 0 GLORY_EGO)
					)
				else
					(= [egoStats HEALING] 0)
					(messager say: N_EGO_CUE V_HEAL C_LOSE_HEAL 0 0 GLORY_EGO)
				)
			)
			((= i (PaladinCheck num 50))
				(if (== i 1)
					(Bset fSenseDanger)
					(messager say: N_EGO_CUE V_DOIT C_GAIN_SENSE 0 0 GLORY_EGO)
				else
					(Bclr fSenseDanger)
					(messager say: N_EGO_CUE V_DOIT C_LOSE_SENSE 0 0 GLORY_EGO)
				)
			)
			((= i (PaladinCheck num 80))
				(if (== i 1)
					(Bset fHonorShield)
					(= protectTimer 1)
					((ScriptID 0 21) doit: curRoomNum)
					(messager say: N_EGO_CUE V_DOIT C_GAIN_SHIELD 0 0 GLORY_EGO)
				else
					(Bclr fHonorShield)
					(messager say: N_EGO_CUE V_DOIT C_LOSE_SHIELD 0 0 GLORY_EGO)
				)
			)
		)
	)
	
	(method (trySkill skillNum difficulty bonus &tmp skVal skDiv skRef success)
		(if
			(and
				;he's wieldng his staff
				(> (ego view?) 17)
				(< (ego view?) 21)
				(< (ego view?) 21)	;EO: why the redundant line?
				(OneOf skillNum
					OPEN DETMAGIC TRIGGER DAZZLE ZAP
					CALM FLAMEDART FETCH FORCEBOLT LEVITATE
					REVERSAL JUGGLE STAFF LIGHTNING HEALING
					;QFG4-added spells
					FROSTBITE RITUAL
				)
			)
			(return TRUE)
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
					((>= skillNum OPEN)	;casting a spell
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
			(theMusic3 number: 149 setLoop: 1 play:)
			(= ret TRUE)
		)
		(return ret)
	)
	
	(method (takeDamage damage)
		(self useSkill: VIT (Abs damage))
		(if
			(< (-= [egoStats HEALTH] (if (Btst fPoisoned) (Abs damage) else damage)) 0)
			(= [egoStats HEALTH] 0)
		)
		(if (> [egoStats HEALTH] (self maxHealth:))
			(= [egoStats HEALTH] (self maxHealth:))
		)
		((ScriptID 0 21) doit:)
		(return (> [egoStats HEALTH] 0))
	)
	
	(method (castSpell spellNum spellObj &tmp temp0)
		(cond 
			((< argc 2)
				(if
					(and
						(> (ego view?) 17)
						(< (ego view?) 21)
						(< (ego view?) 21)
						(!= curRoomNum 810)
					)
					(return 1)
				)
			)
			((and spellObj (!= curRoomNum 810))
				(return TRUE)
			)
		)
		(return
			(if (< [egoStats MANA] [spellCost (- spellNum OPEN)])
				(messager say: N_EGO_CUE V_DOIT C_NO_MANA 1 0 GLORY_EGO)
				(return FALSE)
			else
				(return
					(self
						useStamina: 2
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
				(return (/ (+ [egoStats INT] egoMagic egoMagic) 3))
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
		(= haveEaten FALSE)
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
					((inventory at: iRations) owner: 0)
				)
				(= haveEaten TRUE)
			)
			((Btst fStarving)
				(if (self useStamina: 8 0)
					(messager say: N_EGO_CUE V_DOIT C_STARVING 1 0 GLORY_EGO)
				else
					(EgoDead C_DEATH_STARVE GLORY_EGO 995 1)
				)
			)
			((Btst fHungry)
				(Bset fStarving)
				(messager say: N_EGO_CUE V_DOIT C_HUNGRY 1 0 GLORY_EGO)
			)
			(else (Bset fHungry)
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
			(+
				(+= tot kopeks)
				((inventory at: iPurse) amount?)
			)
		)
	)
	
	(method (changeGait newGait gaitMsg &tmp theView saveMover)
		(= saveMover 0)
		(if (and gaitMsg (== egoGait newGait))
			(return))
		(if mover
			(= saveMover mover)
			(= mover 0)
		)
		(if (!= newGait -1)
			(= egoGait newGait)
		)
		(if
			(or
				(< view 4)
				(== view 5)
				(and (<= 47 view) (<= view 49))
				(== view 52)
			)
			(switch egoGait
				(MOVE_RUN
					(if (Btst fHoldingTorch)
						(self
							view: 52
							origStep: 2053
							setStep:
							setCycle: StopWalk 48
						)
					else
						(self
							view: 1
							origStep: 2053
							setStep:
							setCycle: StopWalk 5
						)
					)
				)
				(MOVE_SNEAK
					(if (Btst fHoldingTorch)
						(self
							view: 49
							origStep: 1026
							setStep:
							setCycle: StopWalk 48
						)
					else
						(self
							view: 2
							origStep: 1026
							setStep:
							setCycle: StopWalk 2
						)
					)
				)
				(else 
					(if (Btst fHoldingTorch)
						(self
							view: 47
							origStep: 1027
							setStep:
							setCycle: StopWalk 48
						)
					else
						(self
							view: 0
							origStep: 1027
							setStep:
							setCycle: StopWalk 5
						)
					)
				)
			)
		)
		(cond 
			((Btst fOverloaded)
				(self setStep: 1 1 0)
			)
			((or (== xStep 1) (== yStep 1))
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
		(if argc (self loop: theLoop))
		(self
			view: 0
			z: 0
			setPri: -1
			setLoop: -1
			setLooper: stopGroop
			setCycle: StopWalk 5
			setMotion: 0
			changeGait: -1 0
			illegalBits: 0
			ignoreHorizon:
			state: 2
		)
	)
	
	(method (notBlockedByEdge)
		(return
			(if (and mover (mover isKindOf: PolyPath))
				(return
					(if (== x (mover finalX?))
						(== y (mover finalY?))
					else
						0
					)
				)
			else
				0
			)
		)
	)
)

(instance stopGroop of GradualLooper
	(method (doit)
		(if (and (ego cycler?) ((ego cycler?) isKindOf: StopWalk))
			(ego view: ((ego cycler?) vWalking?))
		)
		(super doit: &rest)
	)
	
	(method (cue &tmp whoCares)
		(cond 
			((not (client mover?)) (client mover: oldMover))
			(oldMover (oldMover dispose:))
		)
		(if oldCycler
			(if (client cycler?)
				(oldCycler dispose:)
			else
				(client cycler: oldCycler)
			)
		)
		(= whoCares caller)
		(= caller (= oldMover (= oldCycler 0)))
		(if whoCares
			(whoCares cue: &rest)
		)
	)
)
