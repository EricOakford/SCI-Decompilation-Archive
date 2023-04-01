;;; Sierra Script 1.0 - (do not remove this comment)
(script# PROCS) ;814
(include game.sh) (include "815.shm")
(use Main)
(use CastCalm)
(use CastOpen)
(use CastDazzle)
(use Print)
(use PolyPath)
(use StopWalk)
(use Invent)
(use User)
(use System)

(public
	EgoDead 0
	ShowTime 1
	ChangeGait 2
	NormalEgo 3
	AlreadyDone 5
	CantDo 6
	DontHave 7
	RedrawCast 8
	HaveMem 9
	Face 10
	GiveMoney 11
	FixTime 12
	NextDay 13
	Rand100 14
	TrySkill 15
	SkillUsed 16
	UseStamina 17
	UseMana 18
	TakeDamage 19
	MaxStamina 20
	MaxHealth 21
	MaxMana 22
	MaxLoad 23
	CastSpell 24
	SoundFX 25
	SolvePuzzle 26
	EatMeal 27
	WtCarried 28
	CanPickLocks 29
	SaveCurIcon 30
	LoadCurIcon 31
	SetCurIcon 32
	VerbSpell 33
)

(local
	local0
)
(procedure (EgoDead message title cel loop view
		&tmp deadMsg deadTitle deadCel deadLoop deadView
		msgY msgX msgHeight
		[msgBuf 150] [titleBuf 50] [msgLen 4] [titleLen 4])
		;EO: msgBuf increased from 120 to 150. This prevents really long messages from overlapping into the title.
	;set up the death message, title, and icon
	(= deadMsg
		(if (and argc message) message else C_DIE_GENERIC)
	)
	(= deadTitle
		(if (and (> argc 1) title) title else C_DIE_GENERIC_TITLE)
	)
	(= deadCel (Random 0 2))
	(= deadLoop 0)
	(= deadView vDeathIcons)
	(if (>= argc 3)
		(= deadCel cel)
		(if (>= argc 4)
			(= deadLoop loop)
			(if (>= argc 5)
				(= deadView view)
			)
		)
	)
	;disable control, wait a bit, play the death music
	(HandsOff)
	(Wait 100)
	(theGame setCursor: normalCursor TRUE)
	(sounds eachElementDo: #stop)
	(if deathMusic
		(theMusic
			number: deathMusic
			priority: 15
			loop: 1
			init:
			play:
		)
	)
	(= msgX (+ (CelWide deadView deadLoop deadCel) 10))
	(Message MsgGet 815 1 0 deadTitle 1 @titleBuf)
	(TextSize @titleLen @titleBuf 123 260)
	(Message MsgGet 815 1 0 deadMsg 1 @msgBuf)
	(TextSize @msgLen @msgBuf userFont (- 260 msgX))
	(= msgY (+ (- [titleLen 2] [titleLen 0]) 10))
	(= msgHeight
		(+
			(Max
				(+ (- [msgLen 2] [msgLen 0]) 10)
				(CelHigh deadView deadLoop deadCel)
			)
			msgY
			10
		)
	)
	(repeat
		(switch
			(Print
				width: 260
				font: 123
				addText: @titleBuf 0 0
				font: userFont
				mode: teJustCenter
				addIcon: deadView deadLoop deadCel 0 msgY
				width: (- 260 msgX)
				addText: @msgBuf msgX msgY
				addButton: 1 {Restore} 0 msgHeight
				addButton: 2 {Restart} 70 msgHeight
				addButton: 3 {____Quit____} 140 msgHeight
				init:
			)
			(1
				(theGame restore:)
			)
			(2
				(theGame restart:)
			)
			(3
				(= quit TRUE)
				(break)
			)
		)
	)
)


(procedure (ShowTime &tmp whatDay [timeBuf 20] [dayBuf 30])
	(= whatDay Day)
	(Format
		@timeBuf
		(switch timeODay
			(TIME_DAWN
				{Day is dawning}
			)
			(TIME_MIDMORNING
				{It is mid-morning}
			)
			(TIME_MIDDAY
				{It is midday}
			)
			(TIME_MIDAFTERNOON
				{It is mid-afternoon}
			)
			(TIME_SUNSET
				{Sunset approaches}
			)
			(TIME_NIGHT
				{The night is still young}
			)
			(TIME_MIDNIGHT
				{It is the middle of the night}
			)
			(TIME_NOTYETDAWN
				{It is not yet dawn}
			)
		)
	)
	(if (or (!= timeODay TIME_MIDNIGHT) (> Clock 500))
		(++ whatDay)
	)
	(Print
		font: userFont
		addTextF: @dayBuf {%s on day %d.} @timeBuf whatDay
		init:
	)
)

(procedure (ChangeGait newGait gaitMsg &tmp temp0)
	(if (and gaitMsg (not (User canControl:)))
		(messager say: N_PROCS NULL NULL 4 0 PROCS)
		(return)
	)
	(if (!= newGait -1)
		(= egoGait newGait)
	)
	(switch egoGait
		(MOVE_RUN
			(ego
				view: vEgoRun
				setStep: 8 4
				setCycle: StopWalk vEgoStand
			)
			((theIconBar at: ICON_WALK)
				loop: 6
				cursor: vRunCursor
			)
		)
		(MOVE_SNEAK
			(ego
				view: vEgoSneak
				setStep: 3 2
				setCycle: StopWalk vEgoSneakStand
			)
			((theIconBar at: ICON_WALK)
				loop: 8
				cursor: vSneakCursor
			)
		)
		(else 
			(ego
				view: vEgo
				setStep: 3 2
				cycleSpeed: (ego moveSpeed?)
				setCycle: StopWalk vEgoStand
			)
			((theIconBar at: ICON_WALK)
				loop: 0
				cursor: vWalkCursor
			)
		)
	)
	(if
		(and
			(IsObject (ego mover?))
			((ego mover?) isKindOf: PolyPath)
		)
		(ego
			setMotion: PolyPath ((ego mover?) finalX?) ((ego mover?) finalY?)
		)
	)
)

(procedure (NormalEgo theLoop theView stopView &tmp moveView sView dir)
	(= moveView
		(switch egoGait
			(MOVE_RUN vEgoRun)
			(MOVE_SNEAK vEgoSneak)
			(else  vEgo)
		))
	(= sView (if (== egoGait MOVE_SNEAK) vEgoSneakStand else vEgoStand))
	(ChangeGait -1 FALSE)
	(if (not (User controls?))
		(theGame setCursor: waitCursor)
	)
	(if argc
		(ego loop: theLoop)
		(if (> argc 1)
			(= moveView theView)
			(if (> argc 2)
				(= sView stopView)
			)
		)
	)
	(= dir
		(switch (ego loop?)
			(facingNorth 0)
			(facingNE 45)
			(facingEast 90)
			(facingSE 135)
			(facingSouth 180)
			(facingSW 225)
			(facingWest 270)
			(facingNW 315)
		)
	)
	(ego
		setPri: -1
		view: moveView
		setLoop: -1
		setLoop: (ScriptID 0 1)
		setCycle: StopWalk sView
		illegalBits: cWHITE
		ignoreHorizon:
		ignoreActors: FALSE
		heading: dir
	)
)

(procedure (AlreadyDone)
	(messager say: N_PROCS NULL NULL 5 0 PROCS)
)

(procedure (CantDo)
	(messager say: N_PROCS NULL NULL 6 0 PROCS)
)

(procedure (DontHave)
	(messager say: N_PROCS NULL NULL 7 0 PROCS)
)

(procedure (RedrawCast)
	(Animate (cast elements?) FALSE)
)

(procedure (HaveMem howMuch)
	(return (> (MemoryInfo FreeHeap) howMuch))
)

(procedure (Face who theObjOrX theY whoCares &tmp theHeading lookX lookY obj)
	(= obj 0)
	(if (IsObject theObjOrX)
		(= lookX (theObjOrX x?))
		(= lookY (theObjOrX y?))
		(if (== argc 3)
			(= obj theY)
		)
	else
		(= lookX theObjOrX)
		(= lookY theY)
		(if (== argc 4)
			(= obj whoCares)
		)
	)
	(= theHeading (GetAngle (who x?) (who y?) lookX lookY))
	(cond 
		((> (Abs (- theHeading (ego heading?))) 23)
			(who setHeading: theHeading (if (IsObject obj) obj else 0))
		)
		((IsObject obj)
			(obj cue:)
		)
	)
)

(procedure (GiveMoney itemPrice &tmp costSilvers rc costG haveS haveG costS)
	(= costSilvers itemPrice)
	(= rc TRUE)
	(= haveS ((inventory at: iSilver) amount?))
	(= haveG (/ ((inventory at: iSilver) amount?) 10))
	(= costG (* ((inventory at: iGold) amount?) 10))
	(= costS (not (mod costSilvers 10)))
	(cond 
		((< (+ costG haveS) costSilvers)
			(= rc FALSE)
		)
		((== (+ costG haveS) costSilvers)
			((inventory at: iSilver) amount: 0)
			((inventory at: iGold) amount: 0)
		)
		((> haveS costSilvers)
			((inventory at: iSilver)
				amount: (- ((inventory at: iSilver) amount?) costSilvers)
			)
		)
		(haveG
			((inventory at: iSilver)
				amount: (- ((inventory at: iSilver) amount?) (* haveG 10))
			)
			(-= costSilvers (* haveG 10))
			((inventory at: iGold)
				amount:
					(-
						((inventory at: iGold) amount?)
						(+ (/ costSilvers 10) (if costS 0 else 1))
					)
			)
			(if (not costS)
				((inventory at: iSilver)
					amount: (+ ((inventory at: iSilver) amount?) (- 10 (mod costSilvers 10)))
				)
			)
		)
		(else
			((inventory at: iGold)
				amount:
					(-
						((inventory at: iGold) amount?)
						(+ (/ costSilvers 10) (if costS 0 else 1))
					)
			)
			(if (not costS)
				((inventory at: iSilver)
					amount: (+ ((inventory at: iSilver) amount?) (- 10 (mod costSilvers 10)))
				)
			)
		)
	)
	(return rc)
)

(procedure (FixTime newClock newMinutes &tmp oldTime newTime temp2)
	(= newTime (^ Clock 1))
	(if (>= argc 1)
		(= Clock (* GAMEHOUR newClock))
		(= oldSysTime (GetTime SYSTIME1))
		(if (>= argc 2)
			(= Clock (+ Clock (/ (* GAMEHOUR newMinutes) 60)))
		)
	)
	(^= Clock 1)
	(= oldTime timeODay)
	(cond 
		((< Clock 300)
			(= timeODay TIME_MIDNIGHT)
		)
		((< Clock 750)
			(= timeODay TIME_NOTYETDAWN)
		)
		((< Clock 1200)
			(= timeODay TIME_DAWN)
		)
		((< Clock 1650)
			(= timeODay TIME_MIDMORNING)
		)
		((< Clock 2100)
			(= timeODay TIME_MIDDAY)
		)
		((< Clock 2550)
			(= timeODay TIME_MIDAFTERNOON)
		)
		((< Clock 3000)
			(= timeODay TIME_SUNSET)
		)
		((< Clock 3450)
			(= timeODay TIME_NIGHT)
		)
		(else
			(= timeODay TIME_MIDNIGHT)
		)
	)
	(if (> timeODay TIME_SUNSET)
		(= Night TRUE)
		;EO: NextDay already clears this flag
		;(Bclr fStableClean)
		(PalVary PALVARYSTART (curRoom picture?) 1)
		(if nightPalette (PalVary PALVARYTARGET nightPalette))
	else
		(= Night FALSE)
		(PalVary PALVARYREVERSE 1)
	)
)

(procedure (NextDay)
	(++ Day)
	(Bclr fStableClean)
)

(procedure (Rand100)
	(return (+ 1 (/ (Random 0 999) 10)))
)

(procedure (TrySkill skillNum difficulty bonus &tmp skVal skDiv skRef success)
	(if (not (= skVal [egoStats skillNum])) (return FALSE))
	(if (== argc 3) (+= skVal bonus))
	(if difficulty
		(if (>= skillNum WEAPON) (UseStamina (/ difficulty 10)))
	else
		(if (>= skillNum WEAPON) (UseStamina (Random 1 6)))
		(= difficulty (Rand100))
	)
	(if (>= (statCheck LUCK 1) (Random 1 200))
		(= skVal (+ skVal (Random 1 20)))
	)
	(= success (<= difficulty skVal))
	(= skDiv
		(cond 
			((<= (= skDiv (Abs (- difficulty skVal))) 10) 2)
			((<= skDiv 30) 4)
			((<= skDiv 50) 6)
			(else (return success))
		)
	)
	(= skRef
		(cond 
			((== skillNum WEAPON)
				(/ (+ (statCheck AGIL 2) (statCheck STR 2)) 16)
			)
			((or (== skillNum PARRY) (== skillNum DODGE) (== skillNum 8))
				(/ (+ (statCheck AGIL 3) (statCheck INT 1)) 8)
			)
			((== skillNum PICK)
				(/ (+ (statCheck AGIL 3) (statCheck INT 1)) 4)
			)
			((or (== skillNum THROW) (== skillNum CLIMB))
				(/ (+ (statCheck AGIL 3) (statCheck STR 2)) 5)
			)
			((>= skillNum OPEN)
				(/ (+ (statCheck MAGIC 4) (statCheck INT 2)) 6)
			)
			(else 10)
		)
	)
	(SkillUsed skillNum (/ skRef skDiv))
	(return success)
)

(procedure (SkillUsed skillNum learnValue)
	(if (not [egoStats skillNum])
		(return FALSE)
	)
	(if (> (= learnValue (Abs learnValue)) [egoStats skillNum])
		(= learnValue [egoStats skillNum])
	)
	(+= [egoStats EXPER] (/ learnValue 4))
	(if
		(>=
			[skillTicks skillNum]
			[egoStats (+= [skillTicks skillNum] learnValue)]
		)
		(-= [skillTicks skillNum] [egoStats skillNum])
		(if
			(>
				(+= [egoStats skillNum] (Random 1 3))
				100
			)
			(= [egoStats skillNum] 100)	;stats max out at 100
		)
		(return TRUE)
	)
	(return FALSE)
)

(procedure (UseStamina pointsUsed &tmp foo)
	(if (> pointsUsed 0)
		(SkillUsed VIT (/ (+ pointsUsed 3) 4))
	)
	(cond 
		((<
			(= foo (-= [egoStats STAMINA] pointsUsed)) 0)
			(TakeDamage (/ (- -3 [egoStats STAMINA]) 4))
			(= [egoStats STAMINA] 0)
			(if (not fastCast)
				(cond 
					((not (Btst fWornOut))
						(Bset fWornOut)
						(Wait 10)
						(messager say: N_PROCS NULL NULL 10 0 PROCS)
					)
					((<= [egoStats HEALTH] 0)
						(EgoDead C_DIE_EXHAUSTION C_DIE_EXHAUSTION_TITLE)
					)
				)
			)
		)
		((> foo 4)
			(Bclr fWornOut)
			(if (> foo (MaxStamina))
				(= [egoStats STAMINA] (MaxStamina))
			)
		)
	)
)

(procedure (UseMana pointsUsed)
	(if [egoStats MAGIC]
		(if
		(< (-= [egoStats MANA] pointsUsed) 0)
			(= [egoStats MANA] 0)
		)
		(if (> [egoStats MANA] (MaxMana))
			(= [egoStats MANA] (MaxMana))
		)
		(if (> pointsUsed 0)
			(SkillUsed INT (/ pointsUsed 5))
			(SkillUsed MAGIC (/ pointsUsed 2))
		)
	)
)

(procedure (TakeDamage damage)
	(if (> damage 0) (SkillUsed VIT (/ (+ damage 1) 2)))
	(if (< (-= [egoStats HEALTH] damage) 0)
		(= [egoStats HEALTH] 0)
	)
	(if (> [egoStats HEALTH] (MaxHealth))
		(= [egoStats HEALTH] (MaxHealth))
	)
	(return (> [egoStats HEALTH] 0))
)

(procedure (MaxStamina)
	(return (* (+ [egoStats AGIL] [egoStats VIT]) 2))
)

(procedure (MaxHealth &tmp tmpHealth)
	(return
		(+
			(= tmpHealth
				(/ (+ [egoStats STR] [egoStats VIT] [egoStats VIT]) 3)
			)
			tmpHealth
		)
	)
)

(procedure (MaxMana &tmp egoMagic)
	(return
		(if (= egoMagic [egoStats MAGIC])
			(return (/ (+ [egoStats INT] egoMagic egoMagic) 3))
		else
			(return FALSE)
		)
	)
)

(procedure (MaxLoad)
	(return (+ 40 (/ [egoStats STR] 2)))
)

(procedure (CastSpell spellNum &tmp retVal)
	(if (< [egoStats MANA] [spellCost (+ (- spellNum OPEN) 1)])
		(messager say: N_PROCS NULL NULL 11 0 PROCS)
		(Wait 30)
		(= retVal FALSE)
	else
		(TrySkill spellNum 0)
		(UseMana [spellCost (+ (- spellNum OPEN) 1)])
		(= retVal TRUE)
	)
	(return retVal)
)

(procedure (SoundFX soundNum)
	;EGA remnant
;;;	(return
;;;		(if (> numVoices 4)
			(return soundNum)
;;;		else
;;;			(return (+ 100 soundNum))
;;;		)
;;;	)
)

(procedure (SolvePuzzle pFlag pValue charType)
	(if (and (>= argc 3) (!= heroType charType))
		(return)
	)
	(if (not (Btst pFlag))
		(Bset pFlag)
		(+= score pValue)
		((ScriptID 0 9) doit: curRoomNum)
		(SkillUsed INT pValue)
	)
)

(procedure (EatMeal)
	(cond 
		(freeMeals
			(-- freeMeals)
		)
		(((inventory at: iRations) amount?)
			(if
				(not
					((inventory at: iRations)
						amount: (- ((inventory at: iRations) amount?) 1)
					)
				)
				(messager say: N_PROCS NULL NULL 12 0 PROCS)
			)
		)
		((Btst fHungry)
			(Bset fStarving)
			(messager say: N_PROCS NULL NULL 13 0 PROCS)
			(TakeDamage 1)
		)
		(else
			(Bset fHungry)
			(messager say: N_PROCS NULL NULL 14 0 PROCS)
		)
	)
)

(procedure (WtCarried &tmp tot index)
	(for ((= index 0) (= tot 0)) (< index iLastInvItem) ((++ index))
		(+= tot
			(*
				(((ScriptID GLORYINV 0) at: index) amount?)
				(((ScriptID GLORYINV 0) at: index) weight?)
			)
		)
	)
	(= tot (/ (+ tot 59) 60))
)

(procedure (CanPickLocks)
	(if [egoStats PICK]
		(if (ego has: iLockPick)
			else (ego has: iThiefKit)
		)
	)
)

(procedure (SaveCurIcon)
	(if (not theCurIcon)
		(= theCurIcon (theIconBar curIcon?))
	)
)

(procedure (LoadCurIcon)
	(if theCurIcon
		(theIconBar curIcon: theCurIcon)
		(theGame setCursor: ((theIconBar curIcon?) cursor?))
		(= theCurIcon 0)
		(if
			(and
				(== (theIconBar curIcon?) (theIconBar at: ICON_USEIT))
				(not (theIconBar curInvIcon?))
			)
			(theIconBar advanceCurIcon:)
		)
	)
)

(procedure (SetCurIcon theIcon &tmp newCur)
	(if (>= argc 2)
		(= newCur (Inventory at: theIcon))
		(theIconBar select: (theIconBar at: ICON_USEIT) curInvIcon: newCur)
	else
		(= newCur (theIconBar at: theIcon))
		(theIconBar select: newCur)
	)
	(theGame setCursor: (newCur cursor?) TRUE)
)

(procedure (VerbSpell theVerb)
	(return
		(switch theVerb
			(V_FLAME
				(return FALSE)
			)
			(V_OPEN
				(CastOpen)
			)
			(V_DETECT
				(messager say: N_PROCS NULL NULL 15 0 PROCS)
			)
			(V_TRIGGER
				(messager say: N_PROCS NULL NULL 16 0 PROCS)
			)
			(V_DAZZLE
				(CastDazz)
			)
			(V_CALM
				(CastCalm)
			)
			(V_FETCH
				(messager say: N_PROCS NULL NULL 17 0 PROCS)
			)
			(V_ZAP
				(if (or (ego has: iSword) (ego has: iDagger))
					(= zapPower (+ 5 (/ [egoStats ZAP] 10)))
					(messager say: N_PROCS NULL NULL 18 0 PROCS)
				else
					(messager say: N_PROCS NULL NULL 19 0 PROCS)
				)
			)
			(else 
				(messager say: N_PROCS NULL NULL 20 0 PROCS)
			)
		)
	)
)

(procedure (statCheck statNum statMult)
	(SkillUsed statNum statMult)
	(return (* [egoStats statNum] statMult))
)
